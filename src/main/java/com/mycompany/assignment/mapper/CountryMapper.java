package com.mycompany.assignment.mapper;

import com.mycompany.assignment.model.output.Country;
import com.mycompany.assignment.model.output.CountryDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CountryMapper {

    @Select("<script>"
            + "SELECT CODE,NAME,CONTINENT FROM COUNTRY"
            + "<where>"
            + "<if test=\"name != null\">NAME LIKE CONCAT('%',#{name},'%')</if>"
            + "<if test=\"continent != null\">AND CONTINENT LIKE CONCAT('%',#{continent},'%')</if>"
            + "</where>"
            + "</script>")
    List<Country> findAll(@Param("name") String name, @Param("continent") String continent);

    @Select("SELECT CODE,NAME,CONTINENT,REGION,SURFACEAREA FROM COUNTRY WHERE CODE = #{countryCode}")
    CountryDetail findByCode(@Param("countryCode") String countryCode);
}
