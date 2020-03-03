package my.pro.acommunity.controller;

import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.model.User;
import my.pro.acommunity.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *个人资料列表控制
 */
@Controller
public class ProfileController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model, HttpServletRequest request, @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size" ,defaultValue = "2") Integer size
                          ){
        User user = (User) request.getSession().getAttribute("user");
        //用户未登陆
        if (user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            //显示我的提问
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }

}
