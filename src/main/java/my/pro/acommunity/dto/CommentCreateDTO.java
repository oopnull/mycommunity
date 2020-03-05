package my.pro.acommunity.dto;

/***
 * 将json的数据反序列化成对象
 */
public class CommentCreateDTO {
    /**
     * 数据传送来的DTO
     */
    private  long parentId;
    private String content;
    private Integer type;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
