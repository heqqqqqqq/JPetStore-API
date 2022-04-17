package org.csu.mypetstore.api.common;

import lombok.Getter;

@Getter  //生成get()方法
//枚举类
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");//客户端返回参数有误

    private final int code;
    private final String description;

    //构造方法
    ResponseCode(int code,String description){
        this.code=code;
        this.description=description;
    }

}
