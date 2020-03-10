package my.pro.acommunity.service;

import my.pro.acommunity.dto.CommentDTO;
import my.pro.acommunity.enums.CommentTypeEnum;
import my.pro.acommunity.enums.NotificationTypeEnum;
import my.pro.acommunity.enums.NotificationStatusEnum;
import my.pro.acommunity.exception.CustomizeErrorCode;
import my.pro.acommunity.exception.CustomizeException;
import my.pro.acommunity.mapper.*;
import my.pro.acommunity.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
/*增加评论数的mapper*/
    @Resource
    private CommentExMapper commentExMapper;
    @Resource
    private QuestionExMapper questionExMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment, User commentator) {
        if(comment.getParentId()==null||comment.getParentId()==0){
           //评论不存在,抛出异常2002
            throw new CustomizeException(CustomizeErrorCode.TARGET_FROM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PRAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            /*增加评论数*/
            Comment parentComment=new Comment();
            parentComment.setCommentCount(1);
            parentComment.setId(comment.getParentId());
            commentExMapper.incCommentCount(parentComment);

            /*评论通知*/
            creaetNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExMapper.incCommentCount(question);
            /*问题通知*/
            creaetNotify(comment, question.getCreator(),commentator.getName(),question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    protected void creaetNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        if(receiver==comment.getCommentator()){
            return;
        }
        /*评论之后插入通知*/
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        /*通知的类型*/
        notification.setType(notificationType.getType());
        /*回复对象的id*/
        notification.setOuterid(outerId);
        /*通知者：评论人*/
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        int insert = notificationMapper.insert(notification);
    }

    /**
     * 展示评论列表
     * @param id
     * @param type
     * @return
     */
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).
                andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        //拿出全部评论
        if (comments.size()==0){
            return new ArrayList<>();
        }
        /**
         * JDK8语法转化
         */
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds= new ArrayList<>();
        userIds.addAll(commentators);
        //获取评论人并转化为map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        //将所有的user对象与评论对象匹配-->转化成map
        //转换comment 为commentDTO
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }

}
