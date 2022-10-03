package com.model.web.exception;

/**
 * packageName : com.model.web.exception
 * className : ManagedException
 * user : jwlee
 * date : 2022/10/03
 */
public class ManagedException extends RuntimeException{

    private ManagedExceptionCode managedExceptionCode;
    private String msg;

    public ManagedException(ManagedExceptionCode code,String msg){
        super(code.toString());
        this.managedExceptionCode = code;
        this.msg = msg;
    }

    public ManagedExceptionCode getManagedExceptionCode() { return managedExceptionCode; }

    public String getMsg(){ return msg;}

    public void setMsg(String msg) {this.msg = msg;}
}
