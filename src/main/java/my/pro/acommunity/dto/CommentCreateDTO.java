package my.pro.acommunity.dto;

import lombok.Data;

/***
 * 将json的数据反序列化成对象
 */
@Data
public class CommentCreateDTO {
    /**
     * 数据传送来的DTO
     */
    private  long parentId;
    private String content;
    private Integer type;

    }
