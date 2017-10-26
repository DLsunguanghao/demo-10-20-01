package com.lanou.mapper;

import com.lanou.bean.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

//    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);
//修改cost单条信息
    int updateByPrimaryKeySelective(Cost cost);

//    更改启用状态
    int updateByPrimaryKey(Integer costId);


    List<Cost> findAll();
    Cost findCostById(Integer costId);
    void addCost(Cost cost);
//    删除cost
    void deleteCost(Integer costId);
//    启用时间更改beginStartTime
  void beginStartTime(@Param("startTime") String startTime, @Param("costId") Integer costId);


}