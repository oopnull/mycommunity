package my.pro.acommunity.mapper;

import my.pro.acommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag} )")
    void create(Question question);

    @Select("select * from question")
    List<Question> list(@Param(value = "offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset} , #{size}")
    List<Question> listById(@Param(value = "userId")Integer userId,@Param(value = "offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{userId} ")
    Integer countById(@Param(value = "userId")Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param(value = "id") Integer id);
}
