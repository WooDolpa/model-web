package com.model.web.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : com.model.web.exception
 * className : ManagedExceptionCode
 * user : jwlee
 * date : 2022/10/03
 */
public enum ManagedExceptionCode {

    ServerError                 (-1),    // 서버 에러
    InvalidParameter            (-2),    // 파라미터 오류
    InvalidContents             (-3),    // 상담내용 파라미터 오류
    InvalidName                 (-4),    // 이름 파라미터 오류
    InvalidPhone                (-5),    // 휴대폰 파라미터 오류
    InvalidEmail                (-6),    // 이메일 파라미터 오류
    SendEmailError              (-7),    // 이메일 전송 오류
    InvalidAdminNo              (-8),    // 관리자 번호 오류
    ;

    private int errorCode;

    private ManagedExceptionCode(int errorCode){this.errorCode = errorCode;}

    public int getErrorCode() {return this.errorCode;}

    public int toInt() {return errorCode;}

    private static final Map<Integer, ManagedExceptionCode> lookup = new HashMap<>();

    static {
        for(ManagedExceptionCode rt : ManagedExceptionCode.values()){
            lookup.put(new Integer(rt.errorCode),rt);
        }
    }

    public static ManagedExceptionCode get(int typeInt){ return lookup.get(typeInt); }

}
