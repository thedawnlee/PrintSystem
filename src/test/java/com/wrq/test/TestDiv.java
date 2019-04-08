package com.wrq.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by wangqian on 2019/4/7.
 */
@Slf4j
public class TestDiv {

    @Test
    public void div (){
        BigDecimal price = new BigDecimal("12.659");
        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
        log.info(" pricepricepricepricepricepricepricepricepricepricepricepricepricepricepricepricepricepricepricepriceprice = {}", price);
    }



}
