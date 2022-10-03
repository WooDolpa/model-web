package com.model.web.controller;

import com.model.web.constants.ApiConstants;
import com.model.web.dto.ApiResponseDto;
import com.model.web.dto.MainDto;
import com.model.web.exception.ManagedException;
import com.model.web.exception.ManagedExceptionCode;
import com.model.web.service.MailService;
import com.model.web.service.MainService;
import com.model.web.service.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.model.web.controller
 * className : MainController
 * user : jwlee
 * date : 2022/10/03
 */
@RestController
@RequestMapping(path = "main")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MainService mainService;
    private final ValidatorService validatorService;
    private final MailService mailService;

    /**
     * 회사 정보 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/company")
    public ResponseEntity getCompany (HttpServletRequest request){

        MainDto.ResCompany dto = mainService.findCompany();

        return new ResponseEntity(ApiResponseDto.makeResponse(dto), HttpStatus.OK);
    }

    /**
     * 네비게이션 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "navigation/list")
    public ResponseEntity navigationList (HttpServletRequest request){

        List<MainDto.ResNavigationDto> list = mainService.findNavigationList();

        return new ResponseEntity(ApiResponseDto.makeResponse(list), HttpStatus.OK);
    }

    /**
     * 슬라이더 (Slider) 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/slider/list")
    public ResponseEntity sliderList (HttpServletRequest request){

        List<MainDto.ResSliderDto> list = mainService.findSliderList();

        return new ResponseEntity(ApiResponseDto.makeResponse(list), HttpStatus.OK);
    }

    /**
     * 컨텐츠 (Contents) 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/contents")
    public ResponseEntity contents (HttpServletRequest request){

        MainDto.ResContentsDto dto = mainService.findContents();

        return new ResponseEntity(ApiResponseDto.makeResponse(dto), HttpStatus.OK);
    }

    /**
     * sub-one 제목(title) 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/subOne/title")
    public ResponseEntity subOneTitle (HttpServletRequest request){

        MainDto.ResSubOneTitle dto = mainService.findSubOneTitle();

        return new ResponseEntity(ApiResponseDto.makeResponse(dto), HttpStatus.OK);
    }

    /**
     * sub-one item 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/subOne/list")
    public ResponseEntity subOneList (HttpServletRequest request,
                                      @RequestParam(value = "idx") String idx){

        List<MainDto.ResSubOneDto> list = mainService.findSubOneList(idx);

        return new ResponseEntity(ApiResponseDto.makeResponse(list), HttpStatus.OK);
    }

    /**
     * sub-two 제목(title) 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/subTwo/title")
    public ResponseEntity subTwoTitle (HttpServletRequest request){

        MainDto.ResSubTwoTitle dto = mainService.findSubTwoTitle();

        return new ResponseEntity(ApiResponseDto.makeResponse(dto), HttpStatus.OK);
    }

    /**
     * sub-two 버튼 텍스트 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/subTwo/text")
    public ResponseEntity subTwoText (HttpServletRequest request){

        MainDto.ResSubTwoText dto = mainService.findSubTwoText();

        return new ResponseEntity(ApiResponseDto.makeResponse(dto), HttpStatus.OK);
    }

    /**
     * 이메일 문의
     *
     * @param request
     * @param dto
     * @param errors
     * @return
     */
    @PostMapping(path = "/qna/add")
    public ResponseEntity insertQna (HttpServletRequest request,
                                     @RequestBody @Valid MainDto.ReqQnaDto dto,
                                     BindingResult errors){

        Optional<ResponseEntity> responseEntityOptional = validatorService.validateParameter(errors);

        if(responseEntityOptional.isPresent()){
            return responseEntityOptional.get();
        }

        if(StringUtils.hasLength(dto.getName())){
            throw new ManagedException(ManagedExceptionCode.InvalidName, ApiConstants.INVALID_NAME);
        }

        mailService.sendMail(dto);

        return new ResponseEntity(ApiResponseDto.makeSuccessResponse(), HttpStatus.OK);
    }

    /**
     * 항목 가져오기
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/category/list")
    public ResponseEntity findCategoryList (HttpServletRequest request) {

        List<MainDto.CategoryResDto> list = mainService.findCategoryList();
        return new ResponseEntity(ApiResponseDto.makeResponse(list), HttpStatus.OK);
    }

    /**
     * sub-two item 조회
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/subTwo/list")
    public ResponseEntity subTwoList (HttpServletRequest request,
                                      @RequestParam(value = "idx") String idx){

        List<MainDto.ResSubTwoDto> list = mainService.findSubTwoList(idx);

        return new ResponseEntity(ApiResponseDto.makeResponse(list), HttpStatus.OK);
    }

    /**
     * sub-one type list
     *
     * @param request
     * @return
     */
    @GetMapping(path = "/subOne/type/list")
    public ResponseEntity findSubOneTypeList (HttpServletRequest request) {

        List<MainDto.AwardsTypeResDto> list = mainService.findSubOneTypeList();
        return new ResponseEntity(ApiResponseDto.makeResponse(list), HttpStatus.OK);
    }
}
