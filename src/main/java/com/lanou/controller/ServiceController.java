package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.bean.Service;
import com.lanou.service.ServiceService;
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
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {
    @RequestMapping(value = "/service_add")
    public String service_add (){
        return "service/service_add";
    }
    @RequestMapping(value = "/service_detail")
    public String service_detail(){
        return "service/service_detail";
    }
    @RequestMapping(value = "/service_list")
    public String service_list(){
        return "service/service_list";
    }
    @RequestMapping(value = "/service_modi")
    public String service_modi(){
        return "service/service_modi";
    }
//    查找所有
    @Resource
    private ServiceService serviceService;
    @ResponseBody
    @RequestMapping(value = "/findAllService")
    public AjaxResult findAllService(){
//        System.out.println("1111111");
//        System.out.println(serviceService.findAllService());
//        System.out.println(serviceService.findAllService());

        return new AjaxResult(serviceService.findAllService());
    }
//    点击查看service.把id存入session
    @ResponseBody
    @RequestMapping("/findServiceById")
    public void findServiceById(@Param("serviceId")Integer serviceId, HttpSession session){
        session.setAttribute("serviceId",serviceId);

    }
//    detail页面加载的时候通过id查找service
    @ResponseBody
    @RequestMapping("/findDetailServiceById")
    public AjaxResult findDetailServiceById(HttpSession session){
        Integer serviceId= (Integer) session.getAttribute("serviceId");
//        System.out.println("*****************************************************");
//        System.out.println(serviceService.findServiceById(serviceId));
        return  new AjaxResult(serviceService.findServiceById(serviceId));
    }

    //分页
    @ResponseBody
    @RequestMapping(value = "/stu2",method = RequestMethod.POST)
    public List<Service> serviceList(@RequestParam("no")Integer num,
                               @RequestParam("size")Integer size){
//        System.out.println("11111");
        return serviceService.findWithPageInfo(num,size);

    }

    @ResponseBody
    @RequestMapping(value = "/pageInfo2",method = RequestMethod.POST)
    public PageInfo<Service> pageInfo(@RequestParam("pageSize")Integer size){
//        System.out.println("22222");
        return serviceService.pageinfo(size);
    }

//    删除
    @ResponseBody
    @RequestMapping("/deleteService")
    public void deleteService(@Param("serviceId")Integer serviceId){

        //        记载删除时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String closeTime = dateFormat.format( now );
        serviceService.deleteService(serviceId,closeTime);


    }
//    开启/暂停
    @ResponseBody
    @RequestMapping("/beginService")
    public void beginService(@Param("serviceId")Integer serviceId,@Param("status")String status){
        System.out.println(serviceId);
        System.out.println(status);
        serviceService.beginService(serviceId,status);


    }
//    查询
    @ResponseBody
    @RequestMapping(value = "/findServiceByFuzzy")
    public List<Service> findServiceByFuzzy(@Param("osUsername")String osUsername,
                                            @Param("unixHost")String unixHost,
                                            @Param("status")String status){


        System.out.println(osUsername);
        System.out.println(unixHost);
        System.out.println(status);
        System.out.println(serviceService.findServiceByFuzzy(osUsername,unixHost,status));

      return  serviceService.findServiceByFuzzy(osUsername,unixHost,status);

    }


}
