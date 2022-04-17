package org.csu.mypetstore.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)//JSON序列化时不包含空的数据
public class CommonResponse<T> implements Serializable {
    private int status;//状态码
    private String msg;
    private T data;//不知道返回什么类型数据，故直接使用范式

    //重载构造方法，为防止data为字符串时调用msg而不是data，将构造方法封装起来
    private CommonResponse(int status){
        this.status=status;
    }
    private CommonResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }
    private CommonResponse(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }
    private CommonResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    //判断响应是否成功
    @JsonIgnore //不被序列化
    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    //成功的响应
    public static <T> CommonResponse <T> createForSuccess(){
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode());//表示成功，使用枚举类可以提高代码的可维护性
    }
    public static <T> CommonResponse <T> createForSuccess(T data){//多返回一个数据
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> CommonResponse <T> createForSuccessMessage(String msg){
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> CommonResponse <T> createForSuccess(T data,String msg){
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    //错误的响应
    public static <T> CommonResponse <T> createForError(){
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDescription());//错误必须要给提示信息
    }
    public static <T> CommonResponse <T> createForError(String msg){
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }
    public static <T> CommonResponse <T> createForError(int code,String msg){
        return new CommonResponse<T>(code,msg);//指定错误的码值和信息
    }
}
