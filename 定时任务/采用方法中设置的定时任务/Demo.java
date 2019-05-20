package com.migu.music.framework.videoColorBell.task;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Timer;

/**
 * @program: Demo
 * @author: 张磊
 * @create: 2019/4/2-17:28
 **/

public class Demo {
    public static void main(String[] args) {
        new Demo();
    }
        Timer timer;

        public Demo(){
            timer = new Timer();
            timer.schedule(new Task(), 1000, 1000*60*60*24);
        }
    }

