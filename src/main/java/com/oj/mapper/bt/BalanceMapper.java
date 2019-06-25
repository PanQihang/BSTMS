package com.oj.mapper.bt;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BalanceMapper {
    //查询余额
    @Select("select AccountBalance from account where CardID = #{id}")
    public String selectMoney(String id);
}
