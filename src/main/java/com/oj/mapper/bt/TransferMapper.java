package com.oj.mapper.bt;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface TransferMapper {
    //验证转账账号
    @Select("select CardID from account where CardID = #{account}")
    public String checkAccount(String account);

    //验证余额是否充足
    @Select("select AccountBalance from account where CardID = #{id}")
    public String checkMoney(String id);

    //转账
    @Update("update account set AccountBalance = #{money} where CardID = #{id}")
    public boolean transfer(@Param("money") String money, @Param("id") String id);

    //交易保存 转出为3
    @Insert("insert into bill(CardID, AffairType, TradeTime, TradeBalance) values(#{id}, '3',#{time}, #{money})")
    public int addBill3(@Param("id") String id, @Param("time") String time,@Param("money") String money);

    //交易保存 转入为4
    @Insert("insert into bill(CardID, AffairType, TradeTime, TradeBalance) values(#{id}, '4',#{time}, #{money})")
    public int addBill4(@Param("id") String id, @Param("time") String time,@Param("money") String money);
}
