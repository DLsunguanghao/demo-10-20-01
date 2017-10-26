package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.service.AccountService;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
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
 * Created by dllo on 17/10/20.
 */
@Controller
public class MainController {
    @Resource
    private CostService costService;

    @RequestMapping(value = "/fee_list")
    public String feelist(){
        return "fee/fee_list";
    }
    @RequestMapping(value ="/fee_detail" )
    public String fee_detail(){
        return "fee/fee_detail";
    }
    @RequestMapping(value = "/fee_add")
    public String fee_add(){
        return "fee/fee_add";
    }
    @RequestMapping(value = "/fee_modi")
    public String fee_modi(){
        return "fee/fee_modi";
    }
    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "/index";
    }

//    查询所有cost
    @ResponseBody
    @RequestMapping(value = "/findAllCost",method = RequestMethod.GET)
    public AjaxResult fidnAllCost(){
        return new AjaxResult(costService.findAll()) ;
    }

//    cost保存id
//    preservation保存
    @ResponseBody
    @RequestMapping("/preservation")
    public void preservation(@Param("costId")Integer costId,  HttpSession session){
//        System.out.println("costid111111111111111"+costId);
       session.setAttribute("costId",costId);

    }
//    通过id查找对应的cost
    @ResponseBody
    @RequestMapping("/findCostById")
    public AjaxResult findCostById(HttpSession session){
        Integer costId= (Integer) session.getAttribute("costId");
        Cost cost = costService.findCostById(costId);
//        System.out.println(cost+"cost12222222222222222222222");
        return new AjaxResult(cost);
    }
//    新增资费
    @ResponseBody
    @RequestMapping(value = "/addCost",method = RequestMethod.POST)
    public void addCost(Cost cost){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );
         cost.setCreatime(hehe);
//         cost.setStartime("暂未开通");
        costService.addCost(cost);
    }
//    启用状态更改
    @ResponseBody
    @RequestMapping(value = "/begin")
    public void begin(@Param("costId")Integer costId){
//        System.out.println(costId+"!1qqqqqqqqqqq");
//        Cost cost=new Cost();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );


          costService.begin(costId);
        System.out.println(hehe+"启用时间");
        costService.beginStartTime(hehe,costId);


    }
//    删除
    @ResponseBody
    @RequestMapping(value = "/delete")
    public void delete(@Param("costId")Integer costId){
//        System.out.println("costid"+costId);
        costService.deleteCost(costId);
    }
//    修改从前端获取id
    @ResponseBody
    @RequestMapping(value = "/change")
    public void change(@Param("costId") Integer costId,HttpSession httpSession){
//        System.out.println(costId+"costid");
        httpSession.setAttribute("changeId",costId);
    }
    //    通过id查找对应的cost
    @ResponseBody
 @RequestMapping("/findChaneCostById")
    public AjaxResult findChaneCostById(HttpSession session){
        Integer costId= (Integer) session.getAttribute("changeId");
        Cost cost = costService.findCostById(costId);
//        System.out.println(cost);
        return new AjaxResult(cost);
    }
//    修改cost
    @ResponseBody
    @RequestMapping("/updateCost")
    public void updateCost(Cost cost){
        System.out.println("1111"+cost);
        costService.updataCost(cost);
    }
//分页
@ResponseBody
@RequestMapping(value = "/stu",method = RequestMethod.POST)
public List<Cost> costList(@RequestParam("no")Integer num,
                              @RequestParam("size")Integer size){

    return costService.findWithPageInfo(num,size);

}

    @ResponseBody
    @RequestMapping(value = "/pageInfo",method = RequestMethod.POST)
    public PageInfo<Cost> pageInfo(@RequestParam("pageSize")Integer size){

        return costService.pageinfo(size);
    }


//登录功能
//    @ResponseBody
//    @RequestMapping(value = "/login")
//    public AjaxResult login( @Param("loginName")String loginName,@Param("loginPasswd")String loginPasswd){
//        Integer log=costService.login(loginName,loginPasswd);
//        return new AjaxResult(log);
//    }






}
