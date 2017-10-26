package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.service.AccountService;
import com.lanou.utils.AjaxResult;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
public class AccountController {
    @RequestMapping(value = "/account_list")
    public String account_list(){
        return "account/account_list";
    }
    @RequestMapping(value = "/account_add")
    public String account_add(){
        return "account/account_add";
    }
    @RequestMapping(value = "/account_modi")
    public String account_modi(){
        return "account/account_modi";
    }
    @RequestMapping(value = "/account_detail")
    public String account_detail(){
        return "account/account_detail";
    }
    @Resource
    private AccountService accountService;
//    查询所有
    @ResponseBody
    @RequestMapping(value = "/findallaccount",method = RequestMethod.GET)
    public AjaxResult findallAccount(){
        System.out.println(accountService.findAllAccount());
        System.out.println("111");
        return new AjaxResult(accountService.findAllAccount());
    }
//分页
@ResponseBody
@RequestMapping(value = "/stu1")
public List<Account> accountList(@RequestParam("no")Integer num,
                              @RequestParam("size")Integer size){

    return accountService.findWithPageInfo(num,size);

}

    @ResponseBody
    @RequestMapping(value = "/pageInfo1")
    public PageInfo<Account> pageInfo(@RequestParam("pageSize")Integer size){

        return accountService.pageinfo(size);
    }
//    添加
    @ResponseBody
    @RequestMapping(value = "/addAccount")
    public void addAccount(Account account){
        System.out.println("111111111111");
        System.out.println(account);
        account.setStatus("0");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );
        account.setCreateDate(hehe);
         accountService.addAccount(account);

    }
//      删除
    @ResponseBody
    @RequestMapping(value = "/deleteAccount",method = RequestMethod.POST)
    public void deleteAccount(@Param("accountId")Integer accountId){
//        System.out.println("11111111111");
//        System.out.println(accountId);




        accountService.deleteAccount(accountId);

    }
//    开启

@ResponseBody
@RequestMapping(value = "/beginAccount",method = RequestMethod.POST)
public void beginAccount(@Param("beginId")Integer accountId,@Param("stutas")String stutas){
//    System.out.println("11111111111");
//    System.out.println(accountId);
    System.out.println(stutas);
    accountService.beginAccount(accountId,stutas);

}
//修改,将本条account的id存入session中
    @ResponseBody
    @RequestMapping(value = "/findAccountById",method = RequestMethod.POST)
    public void findAccountById(@Param("accountId")Integer accountId, HttpSession session){
        System.out.println(accountId);
        session.setAttribute("accountId",accountId);
    }
//  在account_modi页面加载的时候通过session中的id查找出对应的account
    @ResponseBody
    @RequestMapping(value = "/findAccount",method =RequestMethod.POST)
    public AjaxResult findAccount(HttpSession session){
        Integer accountId = (Integer) session.getAttribute("accountId");
//        System.out.println("1111111");
        System.out.println(accountService.findAccount(accountId));
        return  new AjaxResult(accountService.findAccount(accountId));
    }
//    点击查看详细保存id到session中
@ResponseBody
@RequestMapping(value = "/detailId",method = RequestMethod.POST)
public void detailId(@Param("accountId")Integer accountId, HttpSession session){
    System.out.println(accountId+"session");
    session.setAttribute("detailId",accountId);
}

//    点击查看详细
    @ResponseBody
    @RequestMapping(value = "/detailAccount",method = RequestMethod.POST)
    public AjaxResult detail(HttpSession session){
        Integer accountId= (Integer) session.getAttribute("detailId");
        System.out.println("111111111111111");
        System.out.println(accountId);
        return new AjaxResult(accountService.findAccount(accountId));
    }
//条件查询
    @ResponseBody
    @RequestMapping(value = "/findBythird")
    public AjaxResult findBythird(
            @Param("idcardNo") String idcardNo,
            @Param("realName") String realName,
            @Param("loginName") String loginName,
            @Param("status") String status) {
//        System.out.println(idcardNo);
//        System.out.println(realName);
//        System.out.println(loginName);
//        System.out.println(status);
        String staus = null;
//        if (findStaus.equals("全部")){
//            staus=null;
//        }
        if (status.equals("删除")){
            staus = "2";

        }
        if (status.equals("开通")){
            staus = "0";

        }if (status.equals("暂停")) {
            staus = "1";

        }

//        System.out.println(staus);
        return new AjaxResult( accountService.findBythird(idcardNo, realName, loginName, staus));


    }
}
