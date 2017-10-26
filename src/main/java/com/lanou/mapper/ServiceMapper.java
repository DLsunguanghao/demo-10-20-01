package com.lanou.mapper;

import com.lanou.bean.Cost;
import com.lanou.bean.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
//    查找所有
    List<Service> findAllService();
//    通过id查找service
    Service findServiceById(@Param("serviceId")Integer serviceId);
//    删除
    void deleteService(@Param("serviceId")Integer serviceId,@Param("closeTime") String closeTime);
    //开启
    void beginService(@Param("serviceId") Integer serviceId);
//    关闭
    void closeService(@Param("serviceId") Integer serviceId);
//    查询
    List<Service>   findServiceByFuzzy(@Param("osUsername")String osUsername, @Param("unixHost")String unixHost, @Param("status")String status);


}