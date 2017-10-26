package com.lanou.service;


import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface ServiceService {
    List<Service> findAllService();
//    通过id查找service
    Service findServiceById(Integer serviceId);
    //分页
    List<Service> findWithPageInfo(Integer pageNum, Integer pageSize);


    PageInfo<Service> pageinfo(Integer pageSize);
//    删除
    void deleteService(Integer serviceId,String closeTime);
    //开启暂停
    void beginService(@Param("serviceId")Integer serviceId, @Param("status")String status);
//    查询
    List<Service> findServiceByFuzzy(String osUsername, String unixHost, String status);

}
