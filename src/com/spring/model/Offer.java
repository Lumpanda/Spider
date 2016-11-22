package com.spring.model;

/**
 * Created by Lumpanda on 2016/11/14.
 */
public class Offer {
    /*
        page.putField("company", page.getHtml().xpath(      "//div[@class='ui-block-b'][2]/p/text()").toString() );
        page.putField("job", page.getHtml().xpath(          "//div[@class='ui-block-b'][4]/p/text()").toString() );
        page.putField("area", page.getHtml().xpath(         "//div[@class='ui-block-b'][6]/p/text()").toString() );
        page.putField("salary", page.getHtml().xpath(       "//div[@class='ui-block-b'][8]/p/text()").toString() );
        page.putField("score", page.getHtml().xpath(        "//div[@class='ui-block-b'][10]/p/text()").toString() );
        page.putField("createTime", page.getHtml().xpath(   "//div[@class='ui-block-b'][12]/p/text()").toString() );
        page.putField("tip", page.getHtml().xpath(          "//div[@class='ui-block-b'][14]/p/text()").toString() );
        page.putField("looked", page.getHtml().xpath(       "//div[@data-role='header']/h1/text()").toString() );
    */

    private int offerId;
    private String company;
    private String job;
    private String area;
    private String salary;
    private int score;
    private String createTime;
    private String tip;
    private int looked;

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int socre) {
        this.score = socre;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getLooked() {
        return looked;
    }

    public void setLooked(int looked) {
        this.looked = looked;
    }

    public String toString(){
        return "Offer:"
                + "  offerId="+offerId
                + " | company="+company
                + " | job="+job
                + " | area="+area
                + " | salary="+salary
                + " | score="+score
                //+ "|createTime="+createTime
                //+ "|tip="+tip
                //+ "|looked="+looked
                ;
    }

}
