package com.model.web.service;

import com.model.web.constants.ApiConstants;
import com.model.web.dto.ApiResponseDto;
import com.model.web.exception.ManagedException;
import com.model.web.exception.ManagedExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

/**
 * packageName : com.model.web.service
 * className : ValidatorService
 * user : jwlee
 * date : 2022/10/03
 */
@Service
@Slf4j
public class ValidatorService {

    /**
     * Controller 통해 전달되는 @RequestBody 파라미터의 유효성 검사 후 에러 발생시 BindingResult에 실패 내용을 포함되고 실패 내용을 응답으로 전달.
     * @param errors
     * @return
     */
    public Optional<ResponseEntity> validateParameter(final BindingResult errors){

        if(errors.hasErrors()){

            ManagedException me  = null;

            for(FieldError error : errors.getFieldErrors()){

                switch (error.getField()){
                    case ApiConstants.INVALID_FIELD_CONTENTS:
                        me = new ManagedException(ManagedExceptionCode.InvalidContents, ApiConstants.INVALID_CONTENTS);
                        break;
                    case ApiConstants.INVALID_FIELD_NAME:
                        me = new ManagedException(ManagedExceptionCode.InvalidName, ApiConstants.INVALID_NAME);
                        break;
                    case ApiConstants.INVALID_FIELD_PHONE:
                        me = new ManagedException(ManagedExceptionCode.InvalidPhone, ApiConstants.INVALID_PHONE);
                        break;
                    case ApiConstants.INVALID_FIELD_EMAIL:
                        me = new ManagedException(ManagedExceptionCode.InvalidEmail, ApiConstants.INVALID_EMAIL);
                        break;
                    default:
                        break;
                }
            }

            if(me == null){
                log.warn("validateParameter|unknown field");
                me = new ManagedException(ManagedExceptionCode.ServerError, ApiConstants.SERVER_ERROR);
            }

            return Optional.of(new ResponseEntity(ApiResponseDto.makeErrorResponse(me), HttpStatus.BAD_REQUEST));
        }

        return Optional.empty();
    }

}
