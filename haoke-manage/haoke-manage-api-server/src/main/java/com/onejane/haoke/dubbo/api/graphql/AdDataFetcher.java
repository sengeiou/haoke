package com.onejane.haoke.dubbo.api.graphql;

import com.onejane.haoke.dubbo.api.service.AdService;
import com.onejane.haoke.dubbo.api.vo.ad.index.IndexAdResult;
import com.onejane.haoke.dubbo.api.vo.ad.index.IndexAdResultData;
import com.onejane.haoke.dubbo.server.pojo.Ad;
import com.onejane.haoke.dubbo.server.vo.PageInfo;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdDataFetcher implements MyDataFetcher {
    @Autowired
    private AdService adService;
    @Override
    public String fieldName() {
        return "IndexAdList";
    }
    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        PageInfo<Ad> pageInfo = this.adService.queryAdList(1, 1, 3);
        List<Ad> ads = pageInfo.getRecords();
        List<IndexAdResultData> list = new ArrayList<>();
        for (Ad ad : ads) {
            list.add(new IndexAdResultData(ad.getUrl()));
        }
        return new IndexAdResult(list);
    }
}