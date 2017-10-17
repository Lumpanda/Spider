package com.spider.controller;

import com.websocket.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.spring.dao.OfferDao;
import com.spring.model.Offer;
import com.spring.service.OfferService;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

/**
 * Created by Lumpanda on 2016/11/13.
 */

@Component
@Scope("singleton")
public class showOfferPipeline implements Pipeline {

    @Resource
    private OfferService offerService;

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        // 只有明细页面才处理
        if( resultItems.get("type").equals("detail") ){

            // offer用来保存
            Offer offer = new Offer();
            offer.setOfferId(       resultItems.get("offerId") );
            offer.setCompany(       resultItems.get("company") );
            offer.setJob(           resultItems.get("job") );
            offer.setArea(          resultItems.get("area") );
            offer.setSalary(        resultItems.get("salary") );
            offer.setScore(         resultItems.get("score") );
            offer.setCreateTime(    resultItems.get("createTime") );
            offer.setTip(           resultItems.get("tip") );
            offer.setLooked(        resultItems.get("looked") );

            // JSON用来websocket返回
            JSONObject offerJson = new JSONObject();
            offerJson.put( "offerId"    , resultItems.get("offerId") );
            offerJson.put( "company"    , resultItems.get("company") );
            offerJson.put( "job"        , resultItems.get("job") );
            offerJson.put( "area"       , resultItems.get("area") );
            offerJson.put( "salary"     , resultItems.get("salary") );
            offerJson.put( "score"      , resultItems.get("score") );
            offerJson.put( "createTime" , resultItems.get("createTime") );
            offerJson.put( "tip"        , resultItems.get("tip") );
            offerJson.put( "looked"     , resultItems.get("looked") );


            try {
                // 数据库保存
                offerService.addOffer(offer);
                // WebSocket发送至页面
                for( WebSocketController item : WebSocketController.getWebSocketSet() ){
                    item.sendMessage( offerJson.toString() );
                }
            }
            catch( Exception e ){
                e.printStackTrace();
            }
            finally {
                // 控制台输出
                System.out.println( offer.toString() );
            }
        }



    }

}
