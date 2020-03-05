package my.pro.acommunity.controller;

import my.pro.acommunity.dto.CommentCreateDTO;
import my.pro.acommunity.dto.CommentDTO;
import my.pro.acommunity.dto.QuestionDTO;
import my.pro.acommunity.service.CommentService;
import my.pro.acommunity.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;
    @Resource
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        List<CommentDTO> comments=commentService.listByQuestionId(id);
        //没访问一次，增加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
       return "question";
    }
}
