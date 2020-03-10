package my.pro.acommunity.service;

import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.dto.QuestionDTO;
import my.pro.acommunity.dto.QuestionQueryDTO;
import my.pro.acommunity.exception.CustomizeErrorCode;
import my.pro.acommunity.exception.CustomizeException;
import my.pro.acommunity.mapper.QuestionExMapper;
import my.pro.acommunity.mapper.QuestionMapper;
import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.model.Question;
import my.pro.acommunity.model.QuestionExample;
import my.pro.acommunity.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    /**
     * 自定义mapper扩展
     */
    @Resource
    private QuestionExMapper questionExMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserMapper userMapper;
//产生分页列表
    public PaginationDTO list(String search,String tag
            ,Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        //偏移量
        PaginationDTO paginationDTO = new PaginationDTO();
        //Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        /*搜索功能*/
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        questionQueryDTO.setTag(tag);
        Integer totalCount = questionExMapper.countBySearch(questionQueryDTO);
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size + 1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset=size*(page-1);
        //分页

        //倒序排
        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = questionExMapper.selectBySearch(questionQueryDTO);

        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //
        for (Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            //快速拷贝对象上的属性
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size + 1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        Integer offset=size*(page-1);
        //List<Question> questions = questionMapper.listByUserId(userId,offset,page);

        QuestionExample example= new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //
        for (Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            //快速拷贝对象上的属性
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
      Question question= questionMapper.selectByPrimaryKey(id);

      if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
      QuestionDTO questionDTO=new QuestionDTO();
      BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return  questionDTO;
    }

    public void createOrUpdate(Question question) {

        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else {
            //更新
            Question updateQuestion=new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example=new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question= new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())){
            return  new ArrayList<>();
        }
        /*将标签分割*/
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        /*拼接成  | */
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question= new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questionList = questionExMapper.selectRelated(question);
        /*转化成questionDTO*/
        List<QuestionDTO> questionDTOS = questionList.stream().map(q -> {
           QuestionDTO questionDTO=new QuestionDTO();
           BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return  questionDTOS;
    }
}
