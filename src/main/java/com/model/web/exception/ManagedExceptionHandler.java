package com.model.web.exception;

import com.model.web.constants.ApiConstants;
import com.model.web.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * packageName : com.model.web.exception
 * className : ManagedExceptionHandler
 * user : jwlee
 * date : 2022/10/03
 */
@ControllerAdvice(annotations = {RestController.class})
@Slf4j
public class ManagedExceptionHandler {

    /**
     * It manages all exceptions accordingly
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class )
    public ResponseEntity managedExceptionHandler (Exception e, WebRequest request){

        ManagedException me;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.info("managedExceptionHandler|called|{}",e.getMessage());

        if(e instanceof ManagedException){
            me = (ManagedException) e;
        }else if(e instanceof HttpMessageNotReadableException){
            me = new ManagedException(ManagedExceptionCode.InvalidParameter, ApiConstants.INVALID_PARAMETER);
        }else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            me = new ManagedException(ManagedExceptionCode.ServerError, ApiConstants.SERVER_ERROR); // code : 500 || msg : 서버에러
        }

        String result = ApiResponseDto.makeErrorResponse(me);

        return new ResponseEntity(result, httpStatus);
    }

}
