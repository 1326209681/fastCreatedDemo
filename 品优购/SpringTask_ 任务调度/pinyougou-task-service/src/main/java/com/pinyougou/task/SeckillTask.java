package com.pinyougou.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Date;

/**
 * SeckillTask
 * hasee
 * 2018/12/26
 * 12:14
 *
 * @Version 1.0
 **/

@Component
public class SeckillTask{

    @Scheduled(cron = "* * * * * ?")
    public void refreshSeckillGoods(){
        System.out.println("zhi xing le ren wu diao du "+new Date());
    }
}