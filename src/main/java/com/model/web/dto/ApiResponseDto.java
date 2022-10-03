package com.model.web.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.web.constants.CommonConstants;
import com.model.web.exception.ManagedException;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * packageName : com.model.web.dto
 * className : ApiResponseDto
 * user : jwlee
 * date : 2022/10/03
 */
@Slf4j
public class ApiResponseDto {

    public static String makeResponse(Object data){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        String result = "";

        response.put("code", CommonConstants.RESPONSE_SUCCESS_CODE);
        response.put("msg", CommonConstants.RESPONSE_SUCCESS_MSG);
        response.put("data", data);

        try {
            result = objectMapper.writeValueAsString(response);
        }catch (Exception e){
            log.warn("ApiResponseDto makeResponse error : {}",e.getMessage());
        }

        return result;
    }


    public static String makeSuccessResponse()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> response = new LinkedHashMap<String, Object>();

        String result = "";

        response.put("code", CommonConstants.RESPONSE_SUCCESS_CODE);
        response.put("msg", CommonConstants.RESPONSE_SUCCESS_MSG);

        try {
            result = objectMapper.writeValueAsString(response);
        }catch (Exception e){

        }

        return result;
    }

    public static String makeFailResponse()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> response = new LinkedHashMap<String, Object>();

        String result = "";

        response.put("code", CommonConstants.RESPONSE_FAILURE_CODE);
        response.put("msg", CommonConstants.RESPONSE_FAILURE_MSG);

        try {
            result = objectMapper.writeValueAsString(response);
        }catch (Exception e){

        }

        return result;
    }

    public static String makeErrorResponse(ManagedException me)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> response = new LinkedHashMap<String, Object>();

        String result = "";

        response.put("code", me.getManagedExceptionCode().toInt());
        response.put("msg", me.getMsg());

        try {
            result = objectMapper.writeValueAsString(response);
        }catch (Exception e){

        }

        return result;
    }

}
