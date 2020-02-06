package com.onejane.haoke.dubbo.server.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.onejane.haoke.dubbo.server.pojo.HouseResources;
import com.onejane.haoke.dubbo.server.service.HouseResourcesService;
import com.onejane.haoke.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;


@Service(version = "1.0.0")
// 分离dubbo服务和spring服务，易于扩展，业务写在spring服务，日后不用dubbo可以直接拆分
public class ApiHouseResourcesServiceImpl implements ApiHouseResourcesService {

    @Autowired
    private HouseResourcesService houseResourcesService;

    @Override
    public int saveHouseResources(HouseResources houseResources) {
        return this.houseResourcesService.saveHouseResources(houseResources);
    }

    @Override
    public PageInfo<HouseResources> queryHouseResourcesList(int page, int pageSize, HouseResources queryCondition) {
        return this.houseResourcesService.queryHouseResourcesList(page, pageSize, queryCondition);
    }

    public HouseResources queryHouseResourcesById(Long id) {
        return this.houseResourcesService.queryHouseResourcesById(id);
    }

    @Override
    public boolean updateHouseResources(HouseResources houseResources) {
        return this.houseResourcesService.updateHouseResources(houseResources);
    }
}
