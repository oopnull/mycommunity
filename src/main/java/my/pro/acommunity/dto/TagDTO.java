package my.pro.acommunity.dto;

import lombok.Data;

import java.util.List;
@Data
public class TagDTO {
    private String categoryName;
    public List<String> tags;
}
