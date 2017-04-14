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

    public startSpider(){
        System.out.println("-------------------------------------");
        System.out.println("| Ready to spide showoffer          |");
        System.out.println("| http://localhost/spider/          |");
        System.out.println("-------------------------------------");
    }

    @RequestMapping( value = "/startSpider", method = { RequestMethod.GET, RequestMethod.POST })
    public String startSpiderMethod( Model model ){
        System.out.println("-------------------------------------");
        System.out.println("| Begin to spide showoffer          |");
        System.out.println("-------------------------------------");
        Spider.create( showOfferProcessor )
                //.addPipeline( new ConsolePipeline() )
                .addPipeline( showOfferPipeline )
                //.addUrl( "http://www.offershow.online:8000/sort/1" )
                .addUrl( "http://www.ioffershow.com/sort/2" )
                //.addUrl( "http://www.offershow.online:8000/sort/3" )
                .thread( 5 )
                .setExitWhenComplete( true )
                .run();

        return "started";
    }

}
