package com.spider.controller;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Lumpanda on 2016/11/13.
 */

@Component
public class showOfferPageProcessor implements PageProcessor {

    //设置抓取网站的关键配置
    //重试次数3，抓取间隔100ms
    private Site site = Site.me().setRetryTimes(3).setSleepTime(200);

    public static final String URL_GROUP_FULL = "http://www.ioffershow.com/sort/\\w+";
    public static final String URL_GROUP = "/sort/\\w+";
    public static final String URL_DETAIL_FULL = "http://www.ioffershow.com/offerdetail/\\d+";
    public static final String URL_DETAIL = "/offerdetail/\\d+";
    public static final String URL_PRE = "http://www.ioffershow.com";

    private static int a = 0;

    @Override
    public void process(Page page) {

        //System.out.println(a++);
        //System.out.println( page.getUrl() );

        // 汇总页面
        if( page.getUrl().regex(URL_GROUP_FULL).match() ){
            // 标记为"汇总页面"
            page.putField("type","list");
            // 获取此汇总页面中包含的所有明细页面URL，加入到访问库中
            List<String> urllist = page.getHtml().links().regex(URL_DETAIL).all();
            for( String url : urllist ){
                page.addTargetRequest( URL_PRE + url );
            }
        }
        else{
            // 标记为"明细页面"
            page.putField("type","detail");
            // 数据提取
            String url = page.getUrl().toString();
            page.putField("offerId",    Integer.parseInt( url.substring( url.lastIndexOf('l')+2, url.length() ) ) );
            page.putField("company",    page.getHtml().xpath( "//div[@class='ui-block-b'][4]/p/a/text()").toString() );
            page.putField("job",        page.getHtml().xpath( "//div[@class='ui-block-b'][6]/p/a/text()").toString() );
            page.putField("area",       page.getHtml().xpath( "//div[@class='ui-block-b'][8]/p/a/text()").toString() );
            page.putField("salary",     page.getHtml().xpath( "//div[@class='ui-block-b'][10]/p/text()").toString() );
            String score = page.getHtml().xpath(                   "//div[@class='ui-block-b'][12]/p/a/text()").toString();
            page.putField("score",      Integer.parseInt( score ) );
            page.putField("createTime", page.getHtml().xpath( "//div[@class='ui-block-b'][14]/p/text()").toString() );
            page.putField("tip",        page.getHtml().xpath( "//div[@class='ui-block-b'][16]/p/text()").toString() );
            String looked = page.getHtml().xpath(       "//div[@data-role='header']/h1/text()").toString();
            page.putField("looked",     Integer.parseInt( looked.substring( 7, looked.length()-1 ) ) );
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
