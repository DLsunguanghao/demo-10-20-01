package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.mapper.AccountMapper;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Service
public class CostServiceImpl implements CostService {
    @Resource
    private CostMapper costMapper;

    @RequestMapping(value = "/fee_list")
    public String fee_list(){
        return "fee/fee_list";
    }

    @Override
    public List<Cost> findAll() {
        return costMapper.findAll();
    }


    public Cost findCostById(Integer costId) {

       return costMapper.findCostById(costId);
    }
    @Override
    public void addCost(Cost cost){
        costMapper.addCost(cost);
    }
    @Override
    public void begin(Integer costId){
        costMapper.updateByPrimaryKey(costId);
    }

    @Override
    public void deleteCost(Integer costId) {
        costMapper.deleteCost(costId);
    }
    @Override
    public void updataCost(Cost cost){
        costMapper.updateByPrimaryKeySelective(cost);
    }

//    登录
    @Resource
    private AccountMapper accountMapper;

    @Override
    public Integer login(String loginName, String loginPasswd) {

        Account account=accountMapper.login(loginName);
//        正确
        if (account.getLoginPasswd()==loginPasswd){
            return 1;
        }
        //        用户名或密码不匹配

        else {
            return 0;
        }



    }

    @Override
    public void beginStartTime(String beginTime, Integer costId) {
        System.out.println(beginTime+"111");
        System.out.println(costId+"2222");
        costMapper.beginStartTime(beginTime,costId);
    }
//分页
@Override
    public List<Cost> findWithPageInfo(Integer pageNum, Integer pageSize) {

        //获取PageInfo对象

        PageInfo<Cost> pageInfo =queryCostByPage(pageNum,pageSize);


        return pageInfo.getList();



    }

    public PageInfo<Cost> queryCostByPage(Integer pageNum,Integer pageSize){

        //判断参数的合法性
        pageNum = pageNum ==null?1: pageNum;
        pageSize = pageSize ==null?5:pageSize;

        PageHelper.startPage(pageNum,pageSize);

        //获取全部学院
        List<Cost> costList = costMapper.findAll();

        //使用PageInfo对结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

        System.out.println(pageInfo);

        return pageInfo;

    }


    public PageInfo<Cost> pageinfo(Integer pageSize) {
        return queryCostByPage(null,pageSize);
    }

}
