package com.spring.controller;

import com.spider.controller.showOfferPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import us.codecraft.webmagic.*;

import com.spider.controller.showOfferPageProcessor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;


/**
 * Created by Lumpanda on 2016/11/13.
 */

@Controller
@RequestMapping( value = "/" )
public class startSpider {

    @Autowired
    private showOfferPageProcessor showOfferProcessor;
    @Autowired
    private showOfferPipeline showOfferPipeline;

    // 打印欢迎页面
    public startSpider(){
        System.out.println("--------------------------------------");
        System.out.println("| Ready to spide showoffer           |");
        System.out.println("| http://localhost/spider/local.html |");
        System.out.println("--------------------------------------");
    }

    @RequestMapping( value = "/startSpider", method = { RequestMethod.GET, RequestMethod.POST })
    public String startSpiderMethod( Model model ){
        System.out.println("-------------------------------------");
        System.out.println("| Begin to spide showoffer          |");
        System.out.println("-------------------------------------");
        // 创建Spider
        Spider spiderOne = Spider.create( showOfferProcessor );
        // 增加Pipeline保存
        spiderOne.addPipeline( showOfferPipeline );
        // 配置线程数量
        spiderOne.thread( 5 );
        // 完成后退出
        spiderOne.setExitWhenComplete( true );
        // 手动制定爬虫
        for( int i = 1; i<100; i++ ){
            spiderOne.addUrl( "http://www.ioffershow.com/offerdetail/" + i );
        }
        // 入口爬虫
        // spiderOne.addUrl( "http://www.ioffershow.com/sort/2" );
        // 开启爬虫
        spiderOne.run();
        return "started";
    }

}
