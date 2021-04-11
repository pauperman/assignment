package com.mycompany.assignment.mapper;

import com.mycompany.assignment.model.output.City;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CityMapper {

    @Select("SELECT ID,NAME,COUNTRYCODE,DISTRICT,POPULATION FROM CITY WHERE COUNTRYCODE = #{countryCode}")
    List<City> findByCountryCode(@Param("countryCode") String countryCode);

    @Select("<script>"
            + "SELECT ID,NAME,COUNTRYCODE,DISTRICT,POPULATION FROM CITY"
            + "<where>"
            + "<if test=\"name != null\">NAME LIKE CONCAT('%',#{name},'%')</if>"
            + "<if test=\"countryCode != null\">AND COUNTRYCODE LIKE CONCAT('%',#{countryCode},'%')</if>"
            + "<if test=\"district != null\">AND DISTRICT LIKE CONCAT('%',#{district},'%')</if>"
            + "</where>"
            + "</script>")
    List<City> findAll(@Param("name") String name, @Param("countryCode") String countryCode, @Param("district") String district);

    @Select("SELECT ID,NAME,COUNTRYCODE,DISTRICT,POPULATION FROM CITY WHERE ID = #{id}")
    City findById(@Param("id") String id);

    @Delete("DELETE FROM CITY WHERE ID = #{id}")
    int delete(@Param("id") String id);

    @Update("UPDATE CITY SET NAME=#{name},COUNTRYCODE=#{countryCode},DISTRICT=#{district},POPULATION=#{population} WHERE ID = #{id}")
    int update(@Param("id") String id, @Param("name") String name, @Param("countryCode") String countryCode, @Param("district") String district, @Param("population") BigDecimal population);

    @Insert("INSERT INTO CITY(NAME,COUNTRYCODE,DISTRICT,POPULATION) VALUES(#{name},#{countryCode},#{district},#{population})")
    int insert(@Param("name") String name, @Param("countryCode") String countryCode, @Param("district") String district, @Param("population") BigDecimal population);
}
