package my.pro.acommunity.schedule;

import lombok.extern.slf4j.Slf4j;
import my.pro.acommunity.cache.HotTagCache;
import my.pro.acommunity.mapper.QuestionMapper;
import my.pro.acommunity.model.Question;
import my.pro.acommunity.model.QuestionExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/*实现springboot定时器*/
@Component
@Slf4j
public class HotTagTasks {

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private HotTagCache hotTagCache;

   @Scheduled(fixedRate = 20000*10)
    public void hotTagSchedule(){
       log.info("HotTagSchedule start {}",new Date());
       int offset=0;
       int limit=10;
       List<Question> list=new ArrayList<>();
       /*设置优先级*/
       Map<String, Integer> priorities = new HashMap<>();

       while (offset==0||list.size()==limit){//判断还有没有下一页
           //从数据库取出所有问题
           list =questionMapper.selectByExampleWithRowbounds
                   (new QuestionExample(),new RowBounds(offset,limit));
           for (Question question : list) {
               //标签用逗号隔开
               String[] tags = StringUtils.split(question.getTag(), ",");
               for (String tag : tags) {
                   Integer priority = priorities.get(tag);
                   if (priority!=null){
                       priorities.put(tag,priority+5+question.getCommentCount());
                   }else {
                       priorities.put(tag,5+question.getCommentCount());
                   }
               }
               log.info("list question :{}",question.getId());
           }
           offset+=limit;
       }

       hotTagCache.updateTags(priorities);
        log.info("HotTagSchedule {}",new Date());
    }


}
