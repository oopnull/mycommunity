package my.pro.acommunity.mapper;

import my.pro.acommunity.model.Comment;
import my.pro.acommunity.model.CommentExample;
import my.pro.acommunity.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExMapper {

    int incCommentCount(Comment record);
}