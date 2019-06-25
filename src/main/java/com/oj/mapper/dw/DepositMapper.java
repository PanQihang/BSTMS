package com.oj.mapper.dw;

import org.apache.ibatis.annotations.*;

@Mapper
public interface DepositMapper {

    //查询余额
    @Select("select AccountBalance from account where CardID = #{id}")
    public String selectMoney(String id);
    //存款
    @Update("update account set AccountBalance = #{money} where CardID = #{id}")
    public boolean deposit(@Param("money") String money,@Param("id") String id);

    //交易保存 存款为0
    @Insert("insert into bill(CardID, AffairType, TradeTime, TradeBalance) values(#{id}, '0',#{time}, #{money})")
    public int addBill(@Param("id") String id, @Param("time") String time,@Param("money") String money);
}
