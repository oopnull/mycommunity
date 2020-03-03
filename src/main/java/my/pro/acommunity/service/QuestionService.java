package my.pro.acommunity.service;

import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.dto.QuestionDTO;
import my.pro.acommunity.mapper.QuestionMapper;
import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.model.Question;
import my.pro.acommunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserMapper userMapper;
//产生分页列表
    public PaginationDTO list(Integer page, Integer size) {
        //偏移量
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
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
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.list(offset,page);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //
        for (Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            //快速拷贝对象上的属性
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countById(userId);
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

        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.listById(userId,offset,page);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //
        for (Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            //快速拷贝对象上的属性
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
      Question question= questionMapper.getById(id);
      QuestionDTO questionDTO=new QuestionDTO();
      BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return  questionDTO;
    }
}
