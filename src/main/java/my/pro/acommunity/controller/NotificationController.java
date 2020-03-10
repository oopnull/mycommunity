package my.pro.acommunity.controller;

import my.pro.acommunity.dto.NotificationDTO;
import my.pro.acommunity.dto.PaginationDTO;
import my.pro.acommunity.enums.NotificationTypeEnum;
import my.pro.acommunity.model.Notification;
import my.pro.acommunity.model.User;
import my.pro.acommunity.service.NotificationService;
import my.pro.acommunity.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Resource
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //用户未登陆
        if (user==null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO= notificationService.read(id,user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()
        || NotificationTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType()
        ){
            return "redirect:/question/"+notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }
}
