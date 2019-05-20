package com.migu.music.framework.videoColorBell.task;

import java.util.Date;
import java.util.TimerTask;

/**
 * @program: Task
 * @author: 张磊
 * @create: 2019/4/2-17:29
 **/

public class Task extends TimerTask {

    @Override
    public void run() {
        Date date = new Date(this.scheduledExecutionTime());
        System.out.println("本次执行该线程的时间为：" + date);
    }
}