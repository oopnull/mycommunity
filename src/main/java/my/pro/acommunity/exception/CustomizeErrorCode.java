package my.pro.acommunity.exception;

/**
 * 枚举：不同的错误码
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你要找的问题不在了，请换一个问题试试！"),
    TARGET_FROM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登陆，请登陆后重试！"),
    SYSTEM_ERROR(2004,"服务器繁忙，请稍后再试！"),
    TYPE_PRAM_WRONG(2005,"评论类型错误或不存在！"),
    COMMENT_NOT_FOUND (2006,"您回复的评论不存在了") ,
    COMMENT_IS_EMPTY(2007,"输入内容不能为空！"),
    READ_NOTIFICATION_FAIL(2008,"信息读取失败！"),
    NOTIFICATION_NOT_FOUND(2009,"抱歉,信息找不到!");
    private Integer code;
    private String message;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
