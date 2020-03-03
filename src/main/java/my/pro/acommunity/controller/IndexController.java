package my.pro.acommunity.controller;

import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.service.QuestionService;
import my.pro.acommunity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "2") Integer size
                        ){

        //把列表的信息获取得到
        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";

    }
}
