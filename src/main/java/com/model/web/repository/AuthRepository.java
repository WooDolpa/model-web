package com.model.web.repository;

import com.model.common.model.AdminModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * packageName : com.model.web.repository
 * className : AuthRepository
 * user : jwlee
 * date : 2022/10/03
 */
@Repository
@Mapper
public interface AuthRepository {

    @Select("<script>                                           "
            +"select /* AuthRepository.findAdmin */             "
            +"* from admin                                      "
            +"<where>                                           "
            +"<if test=\"id != null and id != ''\">             "
            +"id = #{id}                                        "
            +"</if>                                             "
            +"<if test=\"adminNo != null\">                     "
            +"admin_no = #{adminNo}                             "
            +"</if>                                             "
            +"</where>                                          "
            +"</script>                                         "
    )
    AdminModel findAdmin(Map<String, Object> map);

}
