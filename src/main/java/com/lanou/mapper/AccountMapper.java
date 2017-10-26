package com.lanou.mapper;

import com.lanou.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);
//添加
    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    Account login(String loginName);
    
//    查询所有
    List<Account> findAllAccount();
//    删除
    void deleteAccount(Integer accountId);
//    开启
   void beginAccount(Integer accountId);
    //    暂停
   void pauseAccount(Integer accountId);
//    通过id查找对应的account
    Account findAccount(Integer accountId);
//    记载删除时间
    void deleteTime(String deletTime,Integer accountId);
    //条件查询
    List<Account> findByFuzzy(@Param("idcardNo") String idcardNo,@Param("realName") String realName, @Param("loginName") String loginName,@Param("status") String status);


}