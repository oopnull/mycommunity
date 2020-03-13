package my.pro.acommunity.dto;


import lombok.Data;
import my.pro.acommunity.model.User;
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer LikeCount;
    private User user;
}
