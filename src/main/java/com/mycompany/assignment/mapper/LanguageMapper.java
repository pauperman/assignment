package com.mycompany.assignment.mapper;

import com.mycompany.assignment.model.output.Language;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LanguageMapper {

    @Select("SELECT LANGUAGE,ISOFFICIAL,PERCENTAGE FROM COUNTRYLANGUAGE WHERE COUNTRYCODE = #{countryCode}")
    List<Language> findByCountryCode(@Param("countryCode") String countryCode);

    @Select("SELECT LANGUAGE,ISOFFICIAL,PERCENTAGE FROM COUNTRYLANGUAGE WHERE COUNTRYCODE = #{countryCode} AND LANGUAGE = #{language}")
    Language findByCountryCodeAndLanguage(@Param("countryCode") String countryCode, @Param("language") String language);
}
