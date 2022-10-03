package com.model.web.repository;

import com.model.common.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * packageName : com.model.web.repository
 * className : MainRepository
 * user : jwlee
 * date : 2022/10/03
 */
@Repository
@Mapper
public interface MainRepository {

    @Select("<script>                                                                               "
            +"select /* MainRepository.findCompany */                                               "
            +"*                                                                                     "
            +"from company                                                                          "
            +"</script>                                                                             "
    )
    CompanyModel findCompany ();

    @Select("<script>                                                                               "
            +"select /* MainRepository.findNavigationList */                                        "
            +"*                                                                                     "
            +"from navigation                                                                       "
            +"<where>                                                                               "
            +"<if test=\"dataStatus != null and dataStatus != '' \">                                "
            +"data_status = #{dataStatus}                                                           "
            +"</if>                                                                                 "
            +"</where>                                                                              "
            +"<if test=\"orderByList != null \">                                                    "
            +"order by                                                                              "
            +"<foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\">    "
            +"${item}                                                                               "
            +"</foreach>                                                                            "
            +"</if>                                                                                 "
            +"</script>                                                                             "
    )
    List<NavigationModel> findNavigationList (Map<String, Object> map);

    @Select("<script>                                                                               "
            +"select /* MainRepository.findSliderList */                                            "
            +"*                                                                                     "
            +"from slider                                                                           "
            +"<where>                                                                               "
            +"<if test=\"dataStatus != null and dataStatus != '' \">                                "
            +"data_status = #{dataStatus}                                                           "
            +"</if>                                                                                 "
            +"</where>                                                                              "
            +"<if test=\"orderByList != null \">                                                    "
            +"order by                                                                              "
            +"<foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\">    "
            +"${item}                                                                               "
            +"</foreach>                                                                            "
            +"</if>                                                                                 "
            +"</script>                                                                             "
    )
    List<SliderModel> findSliderList (Map<String, Object> map);

    @Select("<script>                                                                               "
            +"select /* MainRepository.findContents */                                              "
            +"*                                                                                     "
            +"from contents                                                                         "
            +"</script>                                                                             "
    )
    ContentsModel findContents ();

    @Select("<script>                                                                               "
            +"select /* MainRepository.findNavigation */                                            "
            +"*                                                                                     "
            +"from navigation                                                                       "
            +"<where>                                                                               "
            +"<if test=\"navNo != null and navNo != '' \">                                          "
            +"nav_no = #{navNo}                                                                     "
            +"</if>                                                                                 "
            +"</where>                                                                              "
            +"</script>                                                                             "
    )
    NavigationModel findNavigation (Map<String, Object> map);

    @Select("<script>                                                                               "
            +"select /* MainRepository.findSubOneList */                                            "
            +"*                                                                                     "
            +"from awards                                                                           "
            +"<where>                                                                               "
            +   "<if test=\"dataStatus != null and dataStatus != '' \">                             "
            +"      data_status = #{dataStatus}                                                     "
            +"  </if>                                                                               "
            +"  <if test=\"awardType != null and awardType != '' \">                                "
            +"      and award_type = #{awardType}                                                   "
            +"  </if>                                                                               "
            +"</where>                                                                              "
            +"<if test=\"orderByList != null \">                                                    "
            +"  order by                                                                            "
            +"<foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\">    "
            +"  ${item}                                                                             "
            +"</foreach>                                                                            "
            +"</if>                                                                                 "
            +"</script>                                                                             "
    )
    List<AwardModel> findSubOneList (Map<String, Object> map);

//    @Select("<script>                                                                               "
//            +"select /* MainRepository.findSubTwoList */                                            "
//            +"*                                                                                     "
//            +"from gallery                                                                          "
//            +"order by rank asc                                                                     "
//            +"</script>                                                                             "
//    )
//    List<GalleryModel> findSubTwoList ();

    @Select("<script>                                                                               "
            +"select /* MainRepository.findSubTwoText */                                            "
            +"*                                                                                     "
            +"from gallery_txt                                                                      "
            +"</script>                                                                             "
    )
    GalleryTxtModel findSubTwoText ();

    @Select("<script>                                                                               "
            +"   select /* MainRepository.findCategoryList */                                       "
            +"       *                                                                              "
            +"   from category                                                                      "
            +"<where>                                                                               "
            +"<if test=\"dataStatus != null and dataStatus != '' \">                                "
            +"data_status = #{dataStatus}                                                           "
            +"</if>                                                                                 "
            +"</where>                                                                              "
            +"<if test=\"orderByList != null \">                                                    "
            +"order by                                                                              "
            +"<foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\">    "
            +"${item}                                                                               "
            +"</foreach>                                                                            "
            +"</if>                                                                                 "
            +"</script>                                                                             "
    )
    List<CategoryModel> findCategoryList (Map<String, Object> map);

    @Select("<script>                                                                               "
            +"select /* MainRepository.findSubTwoList */                                            "
            +"*                                                                                     "
            +"from category_item                                                                    "
            +"<where>                                                                               "
            +"  <if test=\" categoryId != null and categoryId > 0 \">                               "
            +"       category_id = #{categoryId}                                                    "
            +"  </if>                                                                               "
            +"</where>                                                                              "
            +"<if test=\"orderByList != null \">                                                    "
            +"   order by                                                                           "
            +"<foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\">    "
            +"  ${item}                                                                             "
            +"</foreach>                                                                            "
            +"</if>                                                                                 "
            +"</script>                                                                             "
    )
    List<CategoryItemModel> findSubTwoList (Map<String, Object> map);

    @Select("<script>                                                                               "
            +"   select /* MainRepository.findSubOneTypeList */                                     "
            +"       *                                                                              "
            +"   from awards_type                                                                   "
            +"<where>                                                                               "
            +"<if test=\"dataStatus != null and dataStatus != '' \">                                "
            +"data_status = #{dataStatus}                                                           "
            +"</if>                                                                                 "
            +"</where>                                                                              "
            +"<if test=\"orderByList != null \">                                                    "
            +"order by                                                                              "
            +"<foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\">    "
            +"${item}                                                                               "
            +"</foreach>                                                                            "
            +"</if>                                                                                 "
            +"</script>                                                                             "
    )
    List<AwardTypeModel> findSubOneTypeList (Map<String, Object> map);

}
