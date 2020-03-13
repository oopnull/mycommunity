package my.pro.acommunity.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO<T> {
    private List<T> data;
    //向前一页
    private boolean showPrevious;
    //首页
    private boolean showFirstPage;
    //下一页
    private boolean showNext;
    //尾页
    private boolean showEndPage;
    //当前页
    private Integer page;
    //页码数
    private List<Integer> pages = new ArrayList<Integer>();
    private Integer totalPage;
    //计算可以显示的页数
    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage=totalPage;
        this.page=page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page - i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页标识
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //是否展示下一页标识
        if (page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }
        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
    }

}
