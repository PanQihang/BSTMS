package com.oj.mapper.bt;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BillMapper {
    @Select("SELECT * FROM bill WHERE CardID = #{id}")
    public List<Map>getMapList(String id);
}
