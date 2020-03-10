package my.pro.acommunity.controller;

import my.pro.acommunity.cache.HotTagCache;
import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.mapper.UserMapper;
import my.pro.acommunity.service.QuestionService;
import my.pro.acommunity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private HotTagCache hotTagCache;
    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size,
                        @RequestParam(name="tag",required = false) String tag
                        ,@RequestParam(name = "search",required = false) String search){

        //把列表的信息获取得到
        PaginationDTO pagination=questionService.list(search,tag,page,size);
        List<String> tags = hotTagCache.getHots();

        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("tags",tags);
        model.addAttribute("tag",tag);
        return "index";

    }
}
