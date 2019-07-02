package com.oj.mapper.er;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExchangeMapper {
    @Select("select AccountBalance from account where CardID = #{id}")
    public String checkmoney(String id);

    @Select("select CN_name,EN_name,rate from exchange_interest_rate")
    public List<Map>get();
}
