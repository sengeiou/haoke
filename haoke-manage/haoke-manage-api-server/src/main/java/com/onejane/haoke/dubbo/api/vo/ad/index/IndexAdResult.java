package com.onejane.haoke.dubbo.api.vo.ad.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: haoke
 * @description: 广告返回
 * @author: OneJane
 * @create: 2020-02-07 10:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexAdResult {
    private List<IndexAdResultData> list;
}