package my.pro.acommunity.cache;

import lombok.Data;
import my.pro.acommunity.dto.HotTagDTO;
import org.springframework.stereotype.Component;

import java.util.*;

/*热门话题*/
@Component
@Data
public class HotTagCache {
    //定义数据模型map------实现无序的hashmap变成有序
    private List<String> hots= new ArrayList<>();
    /*优先级队列*/
    //实现topn堆排序 :
    public void updateTags(Map<String,Integer> tags){
        int max=7;//设置标签数
        //java优先队列实现小顶堆
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>();
       tags.forEach((name,priority)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if(priorityQueue.size()<max){
                priorityQueue.add(hotTagDTO);
            }else{
                //拿出队头元素
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot)>0){
                    //拿出最小的元素
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

       List<String> soredTags=new ArrayList<>();
       //最小的
        HotTagDTO poll = priorityQueue.poll();
        while (poll!=null){
            soredTags.add(0,poll.getName());
            poll = priorityQueue.poll();
        }
        hots=soredTags;
    }
}
