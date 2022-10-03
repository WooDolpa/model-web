package com.model.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * packageName : com.model.web.dto
 * className : MainDto
 * user : jwlee
 * date : 2022/10/03
 */
public class MainDto {

    @Data
    public static class ResCompany {

        private int cpNo;
        private String cpName;
        private String cpImgUrl;
    }

    @Data
    public static class ResNavigationDto {

        private int navNo;
        private String title;
        private int sort;
        private String link;
        private String dataStatus;

    }

    @Data
    public static class ResSliderDto {

        private int sliderNo;
        private String title;
        private String content;
        private String imgUrl;
        private int sort;
    }

    @Data
    public static class ResContentsDto {

        private int ctNo;
        private String ctTxt;
    }

    @Data
    public static class ResSubOneTitle {

        private String title;
    }

    @Data
    public static class ResSubOneDto {

        private int awardNo;
        private String awardName;
        private String url;
        private int rank;
        private String dataStatus;
    }

    @Data
    public static class ResSubTwoTitle {

        private String title;
    }

    @Data
    public static class ResSubTwoDto {

        private int galleryNo;
        private String galleryTitle;
        private String galleryDescription;
        private String galleryImg;
        private String galleryUrl;
    }

    @Data
    public static class ResSubTwoText {

        private String txt;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReqQnaDto {

        @NotBlank
        private String contents;
        @NotBlank
        private String name;
        @NotBlank
        private String phone;
        @NotBlank
        private String email;
    }

    @Data
    public static class CategoryResDto {

        private Integer id;
        private String name;
    }

    @Data
    public static class AwardsTypeResDto {

        private Integer id;
        private String name;

    }

}
