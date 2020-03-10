package my.pro.acommunity.controller;

import my.pro.acommunity.dto.CommentCreateDTO;
import my.pro.acommunity.dto.CommentDTO;
import my.pro.acommunity.dto.ResultDTO;
import my.pro.acommunity.enums.CommentTypeEnum;
import my.pro.acommunity.exception.CustomizeErrorCode;
import my.pro.acommunity.model.Comment;
import my.pro.acommunity.model.Notification;
import my.pro.acommunity.model.User;
import my.pro.acommunity.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 * json:网络传输的一种数据结构 key-value、数组格式、字典
 */
@Controller
public class CommentController {
    /**
     * 拿到前端的json，反序列化成对象做操作。返回前端object，由spring转化为json
     * @return
     */
    @Resource
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
        //获得session
        User user = (User) request.getSession().getAttribute("user");
       if(user==null){
           return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
       }
       //评论内容不能空
       if(commentCreateDTO==null|| StringUtils.isBlank(commentCreateDTO.getContent())){
           return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
       }
        Comment comment=new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }
    /*实现二级列表*/
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name="id") long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
