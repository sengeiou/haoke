package com.onejane.haoke.dubbo.server.service;


import com.onejane.haoke.dubbo.server.pojo.Ad;
import com.onejane.haoke.dubbo.server.vo.PageInfo;

public interface AdService {

    PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize);
}