package com.lanou.service.impl;





import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by dllo on 17/10/25.
 */
@Service
public class ServiceServiceImpl implements ServiceService {
    @Resource
    private ServiceMapper serviceMapper;
    //    查询所有
    public List<com.lanou.bean.Service> findAllService(){
//        System.out.println(serviceMapper.findAllService()+"11111");
        return serviceMapper.findAllService();
    }

    @Override
    public com.lanou.bean.Service findServiceById(Integer serviceId) {

      return   serviceMapper.findServiceById(serviceId);
    }
    //分页
    @Override
    public List<com.lanou.bean.Service> findWithPageInfo(Integer pageNum, Integer pageSize) {

        //获取PageInfo对象

        PageInfo<com.lanou.bean.Service> pageInfo =queryCostByPage(pageNum,pageSize);


        return pageInfo.getList();



    }

    public PageInfo<com.lanou.bean.Service> queryCostByPage(Integer pageNum, Integer pageSize){

        //判断参数的合法性
        pageNum = pageNum ==null?1: pageNum;
        pageSize = pageSize ==null?5:pageSize;

        PageHelper.startPage(pageNum,pageSize);

        //获取全部学院
        List<com.lanou.bean.Service> costList = serviceMapper.findAllService();

        //使用PageInfo对结果进行包装
        PageInfo<com.lanou.bean.Service> pageInfo = new PageInfo<com.lanou.bean.Service>(costList);

//        System.out.println(pageInfo);

        return pageInfo;

    }


    public PageInfo<com.lanou.bean.Service> pageinfo(Integer pageSize) {
        return queryCostByPage(null,pageSize);
    }
//    删除
    public void deleteService(Integer serviceId,String closeTime){
        serviceMapper.deleteService(serviceId,closeTime);


    }
//开启暂停
    @Override
    public void beginService(@Param("serviceId") Integer serviceId, @Param("status") String status) {

        if (status.equals("开通")){
           serviceMapper.closeService(serviceId);
        }
        else if (status.equals("暂停")){
            serviceMapper.beginService(serviceId);

        }

    }
//查询
    @Override
    public List<com.lanou.bean.Service> findServiceByFuzzy(String osUsername, String unixHost, String status) {

        System.out.println(osUsername);
        System.out.println(unixHost);
        System.out.println(status);
       return serviceMapper.findServiceByFuzzy(osUsername,unixHost,status);
    }
}
