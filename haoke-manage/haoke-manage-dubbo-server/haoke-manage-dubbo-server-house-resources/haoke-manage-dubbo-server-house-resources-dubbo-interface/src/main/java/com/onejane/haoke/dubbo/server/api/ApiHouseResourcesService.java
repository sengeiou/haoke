package com.onejane.haoke.dubbo.server.api;

import com.onejane.haoke.dubbo.server.pojo.HouseResources;
import com.onejane.haoke.dubbo.server.vo.PageInfo;

// dubbo service
public interface ApiHouseResourcesService {

    /**
     * 新增房源
     *
     * @param houseResources
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    int saveHouseResources(HouseResources houseResources);

    /**
     * 分页查询房源列表
     *
     * @param page 当前页
     * @param pageSize 页面大小
     * @param queryCondition 查询条件
     * @return
     */
    PageInfo<HouseResources> queryHouseResourcesList(int page, int pageSize,
                                                     HouseResources queryCondition);

    /**
     * 根据id查询房源数据
     *
     * @param id
     * @return
     */
    HouseResources queryHouseResourcesById(Long id);

    boolean updateHouseResources(HouseResources houseResources);
}