package my.pro.acommunity.dto;

import my.pro.acommunity.exception.CustomizeErrorCode;
import my.pro.acommunity.exception.CustomizeException;

/***
 *
 */
public class ResultDTO<T> {
   private Integer code;
   private String message;
   private T data;
   public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
       return resultDTO;
   }
//未登录
    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
       return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    //成功
    public static ResultDTO okOf(){
       ResultDTO resultDTO= new ResultDTO();
       resultDTO.setCode(200);
       resultDTO.setMessage("请求成功！");
       return resultDTO;
    }

    //成功
    public static <T> ResultDTO okOf(T t){
        ResultDTO resultDTO= new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！");
        resultDTO.setData(t);
        return resultDTO;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Object errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
