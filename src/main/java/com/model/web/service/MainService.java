package com.model.web.service;

import com.model.common.enums.DataStatus;
import com.model.common.model.*;
import com.model.web.constants.ApiConstants;
import com.model.web.dto.MainDto;
import com.model.web.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * packageName : com.model.web.service
 * className : MainService
 * user : jwlee
 * date : 2022/10/03
 */
@Service
@RequiredArgsConstructor
public class MainService {

    private final MainRepository mainRepository;

    /**
     * 회사 정보 조회
     *
     * @return
     */
    public MainDto.ResCompany findCompany() {

        MainDto.ResCompany dto = new MainDto.ResCompany();

        Optional<CompanyModel> companyModelOptional = Optional.ofNullable(mainRepository.findCompany());

        if (companyModelOptional.isPresent()) {

            CompanyModel companyModel = companyModelOptional.get();

            dto.setCpNo(companyModel.getCpNo());
            dto.setCpName(companyModel.getCpName());
            dto.setCpImgUrl(companyModel.getCpImgUrl());
        }

        return dto;
    }

    /**
     * 네비게이션 조회
     *
     * @return
     */
    public List<MainDto.ResNavigationDto> findNavigationList() {

        List<MainDto.ResNavigationDto> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("dataStatus", DataStatus.Active.toStr());

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("sort").append(" asc").toString());

        map.put("orderByList", orderByList);

        Optional<List<NavigationModel>> navigationModelListOptional = Optional.ofNullable(mainRepository.findNavigationList(map));

        if (navigationModelListOptional.isPresent()) {

            List<NavigationModel> navigationModelList = navigationModelListOptional.get();

            for (NavigationModel navigationModel : navigationModelList) {

                MainDto.ResNavigationDto dto = new MainDto.ResNavigationDto();

                dto.setNavNo(navigationModel.getNavNo());
                dto.setTitle(navigationModel.getTitle());
                dto.setSort(navigationModel.getSort());
                dto.setLink(navigationModel.getLink());
                dto.setDataStatus(navigationModel.getDataStatus().toStr());

                list.add(dto);
            }
        }

        return list;
    }

    /**
     * 슬라이더 (Slider) 조회
     *
     * @return
     */
    public List<MainDto.ResSliderDto> findSliderList() {

        List<MainDto.ResSliderDto> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("dataStatus", DataStatus.Active.toStr());

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("sort").append(" asc").toString());

        map.put("orderByList", orderByList);

        Optional<List<SliderModel>> sliderModelListOptional = Optional.ofNullable(mainRepository.findSliderList(map));

        if (sliderModelListOptional.isPresent()) {

            List<SliderModel> sliderModelList = sliderModelListOptional.get();

            for (SliderModel sliderModel : sliderModelList) {

                MainDto.ResSliderDto dto = new MainDto.ResSliderDto();

                dto.setSliderNo(sliderModel.getSliderNo());
                dto.setTitle(sliderModel.getTitle());
                dto.setContent(sliderModel.getContent());
                dto.setImgUrl(sliderModel.getImgUrl());
                dto.setSort(sliderModel.getSort());

                list.add(dto);
            }
        }

        return list;
    }

    /**
     * 컨텐츠 (Contents) 조회
     *
     * @return
     */
    public MainDto.ResContentsDto findContents() {

        MainDto.ResContentsDto dto = new MainDto.ResContentsDto();

        Optional<ContentsModel> contentsModelOptional = Optional.ofNullable(mainRepository.findContents());

        if (contentsModelOptional.isPresent()) {

            ContentsModel contentsModel = contentsModelOptional.get();

            dto.setCtNo(contentsModel.getCtNo());
            dto.setCtTxt(contentsModel.getCtTxt());
        }

        return dto;
    }

    /**
     * sub-one 제목(title) 조회
     *
     * @return
     */
    public MainDto.ResSubOneTitle findSubOneTitle() {

        MainDto.ResSubOneTitle dto = new MainDto.ResSubOneTitle();

        Map<String, Object> map = new HashMap<>();
        map.put("navNo", ApiConstants.SUB_ONE_NO);

        Optional<NavigationModel> navigationModelOptional = Optional.ofNullable(mainRepository.findNavigation(map));

        if (navigationModelOptional.isPresent()) {

            NavigationModel navigationModel = navigationModelOptional.get();
            dto.setTitle(navigationModel.getTitle());

        }

        return dto;
    }

    /**
     * sub-one item 조회
     *
     * @return
     */
    public List<MainDto.ResSubOneDto> findSubOneList(String idx) {

        List<MainDto.ResSubOneDto> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("dataStatus", DataStatus.Active.toStr());

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("rank").append(" asc").toString());
        map.put("orderByList", orderByList);
        map.put("awardType", idx);

        Optional<List<AwardModel>> awardModelListOptional = Optional.ofNullable(mainRepository.findSubOneList(map));

        if(awardModelListOptional.isPresent()){

            List<AwardModel> awardModelList = awardModelListOptional.get();

            for(AwardModel awardModel : awardModelList){

                MainDto.ResSubOneDto dto = new MainDto.ResSubOneDto();

                dto.setAwardNo(awardModel.getAwardNo());
                dto.setAwardName(awardModel.getAwardName());
                dto.setRank(awardModel.getRank());
                dto.setUrl(awardModel.getUrl());
                dto.setDataStatus(awardModel.getDataStatus().toStr());

                list.add(dto);
            }

        }

        return list;
    }

    /**
     * sub-two 제목(title) 조회
     *
     * @return
     */
    public MainDto.ResSubTwoTitle findSubTwoTitle() {

        MainDto.ResSubTwoTitle dto = new MainDto.ResSubTwoTitle();

        Map<String, Object> map = new HashMap<>();
        map.put("navNo", ApiConstants.SUB_TWO_NO);

        Optional<NavigationModel> navigationModelOptional = Optional.ofNullable(mainRepository.findNavigation(map));

        if (navigationModelOptional.isPresent()) {

            NavigationModel navigationModel = navigationModelOptional.get();
            dto.setTitle(navigationModel.getTitle());

        }

        return dto;
    }

    /**
     * sub-two item 조회
     *
     * @return
     */
    public List<MainDto.ResSubTwoDto> findSubTwoList() {

        List<MainDto.ResSubTwoDto> list = new ArrayList<>();

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("rank").append(" asc").toString());

        Map<String, Object> map = new HashMap<>();
        map.put("orderByList", orderByList);
//        map.put("categoryId", idx);

//        Optional<List<CategoryItemModel>> galleryModelListOptional = Optional.ofNullable(mainRepository.findSubTwoList(map));
//
//        if(galleryModelListOptional.isPresent()){
//
//            List<GalleryModel> galleryModelList = galleryModelListOptional.get();
//
//            for(GalleryModel galleryModel : galleryModelList){
//
//                MainDto.ResSubTwoDto dto = new MainDto.ResSubTwoDto();
//
//                dto.setGalleryNo(galleryModel.getGalleryNo());
//                dto.setGalleryTitle(galleryModel.getGalleryTitle());
//                dto.setGalleryDescription(galleryModel.getGalleryDescription());
//                dto.setGalleryImg(galleryModel.getGalleryImg());
//                dto.setGalleryUrl(galleryModel.getGalleryUrl());
//
//                list.add(dto);
//            }
//        }

        return list;
    }

    /**
     * sub-two 버튼 텍스트 조회
     *
     * @return
     */
    public MainDto.ResSubTwoText findSubTwoText() {

        MainDto.ResSubTwoText dto = new MainDto.ResSubTwoText();

        Optional<GalleryTxtModel> galleryTxtModelOptional = Optional.ofNullable(mainRepository.findSubTwoText());

        if (galleryTxtModelOptional.isPresent()) {

            GalleryTxtModel galleryTxtModel = galleryTxtModelOptional.get();
            dto.setTxt(galleryTxtModel.getTxtContents());
        }

        return dto;
    }

    /**
     * 항목 가져오기
     *
     * @return
     */
    public List<MainDto.CategoryResDto> findCategoryList() {

        List<MainDto.CategoryResDto> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("dataStatus", DataStatus.Active.toStr());

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("rank").append(" asc").toString());
        map.put("orderByList", orderByList);

        Optional<List<CategoryModel>> categoryModelListOptional = Optional.ofNullable(mainRepository.findCategoryList(map));

        if (categoryModelListOptional.isPresent()) {

            List<CategoryModel> categoryModelList = categoryModelListOptional.get();
            for (CategoryModel categoryModel : categoryModelList) {

                MainDto.CategoryResDto dto = new MainDto.CategoryResDto();
                dto.setId(categoryModel.getId());
                dto.setName(categoryModel.getName());

                list.add(dto);
            }
        }

        return list;
    }

    /**
     * sub-two item 조회
     *
     * @param idx
     * @return
     */
    public List<MainDto.ResSubTwoDto> findSubTwoList(String idx) {

        List<MainDto.ResSubTwoDto> list = new ArrayList<>();

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("rank").append(" asc").toString());

        Map<String, Object> map = new HashMap<>();
        map.put("orderByList", orderByList);
        map.put("categoryId", idx);

        Optional<List<CategoryItemModel>> galleryModelListOptional = Optional.ofNullable(mainRepository.findSubTwoList(map));

        if (galleryModelListOptional.isPresent()) {

            List<CategoryItemModel> categoryItemModelList = galleryModelListOptional.get();

            for (CategoryItemModel model : categoryItemModelList) {

                MainDto.ResSubTwoDto dto = new MainDto.ResSubTwoDto();

                dto.setGalleryNo(model.getId());
                dto.setGalleryTitle(model.getTitle());
                dto.setGalleryDescription(model.getDescription());
                dto.setGalleryImg(model.getImg());
                dto.setGalleryUrl(model.getUrl());

                list.add(dto);
            }
        }

        return list;
    }

    /**
     * subOne Type 리스트
     *
     * @return
     */
    public List<MainDto.AwardsTypeResDto> findSubOneTypeList() {

        List<MainDto.AwardsTypeResDto> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("dataStatus", DataStatus.Active.toStr());

        List<String> orderByList = new ArrayList<>();
        orderByList.add(new StringBuilder().append("rank").append(" asc").toString());
        map.put("orderByList", orderByList);

        Optional<List<AwardTypeModel>> awardTypeModelListOptional = Optional.ofNullable(mainRepository.findSubOneTypeList(map));

        if (awardTypeModelListOptional.isPresent()) {

            List<AwardTypeModel> awardTypeModelList = awardTypeModelListOptional.get();
            for (AwardTypeModel model : awardTypeModelList) {

                MainDto.AwardsTypeResDto dto = new MainDto.AwardsTypeResDto();
                dto.setId(model.getId());
                dto.setName(model.getName());

                list.add(dto);
            }
        }

        return list;
    }

}
