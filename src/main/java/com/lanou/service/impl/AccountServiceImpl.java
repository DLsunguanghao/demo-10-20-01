package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    //    查询所有
    public List<Account> findAllAccount(){
        return accountMapper.findAllAccount();
    }

//    分页
//分页

public List<Account> findWithPageInfo(Integer pageNum, Integer pageSize) {

    //获取PageInfo对象

    PageInfo<Account> pageInfo =queryAccountByPage(pageNum,pageSize);


    return pageInfo.getList();



}

    public PageInfo<Account> queryAccountByPage(Integer pageNum,Integer pageSize){

        //判断参数的合法性
        pageNum = pageNum ==null?1: pageNum;
        pageSize = pageSize ==null?5:pageSize;

        PageHelper.startPage(pageNum,pageSize);

        //获取全部学院
        List<Account> accountList = accountMapper.findAllAccount();

        //使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);

//        System.out.println(pageInfo);

        return pageInfo;

    }


    public PageInfo<Account> pageinfo(Integer pageSize) {
        return queryAccountByPage(null,pageSize);
    }
//添加
    @Override
    public void addAccount(Account account) {
        System.out.println("!111111");
        System.out.println(account);
        accountMapper.insertSelective(account);
    }
//    删除
    public void deleteAccount(Integer accountId){
        System.out.println(accountId+"删除id");
        accountMapper.deleteAccount(accountId);
    }
    //   开启暂停
    public void beginAccount(Integer accountId,String stutas){
        if (stutas.equals("暂停")){
        System.out.println("开启id"+accountId);
        accountMapper.beginAccount(accountId);
        }
       else{
            accountMapper.pauseAccount(accountId);
        }

    }
//    查找account
    public Account findAccount(Integer accountId){
       return accountMapper.findAccount(accountId);
    }
//查询
    @Override
    public List<Account> findBythird(String idcardNo,String realName,String loginName,String status) {
//        System.out.println("findIdCard"+idcardNo);
//        System.out.println(accountMapper.findByFuzzy(idcardNo,realName,loginName,status));
       return accountMapper.findByFuzzy(idcardNo,realName,loginName,status);

    }

}
