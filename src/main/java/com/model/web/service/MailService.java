package com.model.web.service;

import com.model.common.dto.MailDto;
import com.model.common.model.AdminModel;
import com.model.common.util.MailUtils;
import com.model.web.constants.ApiConstants;
import com.model.web.dto.MainDto;
import com.model.web.exception.ManagedException;
import com.model.web.exception.ManagedExceptionCode;
import com.model.web.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.model.web.service
 * className : MailService
 * user : jwlee
 * date : 2022/10/03
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final AuthRepository authRepository;

    @Value("${smtp.mail.host}")
    private String host;
    @Value("${smtp.mail.port}")
    private int port;
    @Value("${smtp.mail.id}")
    private String id;
    @Value("${smtp.mail.password}")
    private String password;
    @Value("${smtp.mail.sender.address}")
    private String address;

    /**
     * 문의하기 이메일 발송
     *
     * @param dto
     */
    public void sendMail (final MainDto.ReqQnaDto dto){

        try {

            Map<String, Object> map = new HashMap<>();
            map.put("adminNo", ApiConstants.ADMIN_NO);
            Optional<AdminModel> adminModelOptional = Optional.ofNullable(authRepository.findAdmin(map));

            if(adminModelOptional.isPresent()){

                AdminModel adminModel = adminModelOptional.get();

                if(adminModel.getEmail() == null){
                    throw new ManagedException(ManagedExceptionCode.InvalidEmail, ApiConstants.INVALID_EMAIL);
                }

                String mobileStr = dto.getPhone();
                if(!(mobileStr.length() == 10 || mobileStr.length() == 11)){
                    throw new ManagedException(ManagedExceptionCode.InvalidPhone, ApiConstants.INVALID_PHONE);
                }

                String phone = null;
                if(mobileStr.length() == 10){
                    phone = mobileStr.substring(0,3)+"-"+mobileStr.substring(3,6)+"-"+mobileStr.substring(6,10);
                }else{
                    phone = mobileStr.substring(0,3)+"-"+mobileStr.substring(3,7)+"-"+mobileStr.substring(7,11);
                }

                log.info("MailService|Send|Request|name:{}|phone:{}|email:{}|contents:{}", dto.getName(), dto.getPhone(), dto.getEmail(), dto.getContents());
                String content = MailUtils.getQnaHtmlCode(dto.getName(), phone, dto.getEmail(), dto.getContents());
                log.info("MailService|Send|Response||content:{}", content);
                MailDto.MailReqDto mailReqDto = new MailDto.MailReqDto();

                mailReqDto.setHost(host);
                mailReqDto.setPort(port);
                mailReqDto.setId(id);
                mailReqDto.setPassword(password);
                mailReqDto.setFromAddress(address);
                mailReqDto.setToAddress(adminModel.getEmail());
                mailReqDto.setTitle(ApiConstants.MAIL_QNA_TITLE);
                mailReqDto.setContent(content);

                MailUtils.sendMail(mailReqDto);

            }else{
                throw new ManagedException(ManagedExceptionCode.InvalidAdminNo, ApiConstants.INVALID_ADMIN_NO);
            }

        }catch (Exception e){
            log.warn("MailService|Send|Mail|Error|{}",e.getMessage());
            throw new ManagedException(ManagedExceptionCode.SendEmailError, ApiConstants.SEND_EMAIL_ERROR);
        }

    }

}
