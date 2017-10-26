package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {
//    查询所有
    List<Account> findAllAccount();
    //分页
    List<Account> findWithPageInfo(Integer pageNum, Integer pageSize);


    PageInfo<Account> pageinfo(Integer pageSize);
//    添加account
    void addAccount(Account account);
//    删除
    void deleteAccount(Integer accountId);
//    开启
    void beginAccount(Integer accountId,String stutas);
//    通过id查找对应的account
    Account findAccount(Integer accountId);
    //条件查询
    List<Account> findBythird(String idcardNo,String realName,String loginName,String status);

}
