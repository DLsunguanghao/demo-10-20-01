package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface CostService {
//    查询所有cost
    List<Cost> findAll();
//    通过id查询cost
    Cost findCostById(Integer costId);
//    添加cost
    void addCost(Cost cost);
//    启用状态更改
    void begin(Integer costId);
//    删除cost
    void deleteCost(Integer costId);
    void updataCost(Cost cost);

//    登录
    Integer login(String loginName,String loginPasswd);
    //更改启用时间
    void beginStartTime(String beginTime,Integer costId);
//分页
List<Cost> findWithPageInfo(Integer pageNum,Integer pageSize);


    PageInfo<Cost> pageinfo(Integer pageSize);
}
