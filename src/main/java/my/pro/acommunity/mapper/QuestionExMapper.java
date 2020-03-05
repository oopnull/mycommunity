package my.pro.acommunity.mapper;
import my.pro.acommunity.model.Question;
import my.pro.acommunity.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 自定义mapper
 */
public interface QuestionExMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}