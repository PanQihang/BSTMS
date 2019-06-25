package com.oj.mapper.system;

import com.oj.mapper.provider.system.LoginProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
/**
 * @author lixu
 * @Time 2019年3月9日 15点21分
 * @Description 登陆功能对应数据库操作的功能接口
 */
@Mapper
public interface LoginMapper {
    //通过登录名和密码检索数据库
    @SelectProvider(type=LoginProvider.class, method = "getLoginQuerySql")
    public List<Map<String, String>> getUserByLoginInfo(@Param("condition")Map<String, String> loginInfo);

    //通过用户ID更新用户密码
    @Update("update account set Password = #{newPassword} where CardID = #{user_id}")
    public void resetPassword(@Param(value = "newPassword") String newPassword,@Param(value = "user_id") String user_id);

    //查询身份证是否开过卡
    @Select("select Identify from user where Identify = #{userID}")
    public String checkID(String userID);

    //新增用户
    @Insert("insert into user(Username, Identify, TelNum, Address) values(#{userName}, #{userID}, #{userPhone}, #{Address})")
    public int addUser(@Param("userName") String userName,@Param("userID") String userID,@Param("userPhone") String userPhone,@Param("Address") String address);

    //获取卡号
    @Select("SELECT MAX(CardID) FROM account WHERE CardID != 'admin'")
    public String getID();

    //新增银行卡
    @Insert("insert into account(CardID, AccountBalance, Identify , Password, Type, CreditLimit, effectiveDate) values(#{CardID}, '0', #{userID}," +
            " #{Password}, #{Type}, '0', '0')")
    public int addAccount(@Param("CardID") String CardID,@Param("userID") String userID,@Param("Password") String Password,@Param("Type") String Type);

    //通过身份证查询姓名
    @Select("select Username from user where Identify = #{Identify}")
    public String selectName(String Identify);
}
