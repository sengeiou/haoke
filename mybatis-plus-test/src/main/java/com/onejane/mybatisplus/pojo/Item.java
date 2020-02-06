package com.onejane.mybatisplus.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@Builder
public class Item {

    private Long id;
    private String title;
    private Long price;

    public Item() {
        log.info("写日志。。。。。");
    }

    public static void main(String[] args) {
        Item item1 = new Item(1L,"哈哈哈",10L);
        Item item2 =  Item.builder().price(100L)
                .title("hello").id(1L).build();

        System.out.println(item1.getId());
    }
}
