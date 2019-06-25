package com.oj.mapper.er;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExchangeMapper {
    @Select("select AccountBalance from account where CardID = #{id}")
    public String checkmoney(String id);
}
