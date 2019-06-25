package com.oj.mapper.dw;

import org.apache.ibatis.annotations.*;

@Mapper
public interface WithdrawMapper {
    //查询余额
    @Select("select AccountBalance from account where CardID = #{id}")
    public String selectMoney(String id);
    //取款
    @Update("update account set AccountBalance = #{money} where CardID = #{id}")
    public boolean withdraw(@Param("money") String money, @Param("id") String id);

    //交易保存 取款为1
    @Insert("insert into bill(CardID, AffairType, TradeTime, TradeBalance) values(#{id}, '1',#{time}, #{money})")
    public int addBill(@Param("id") String id, @Param("time") String time,@Param("money") String money);
}
