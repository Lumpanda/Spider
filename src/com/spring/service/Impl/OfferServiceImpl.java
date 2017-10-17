package com.spring.service.Impl;

import com.spring.dao.OfferDao;
import com.spring.model.Offer;
import com.spring.service.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lumpanda on 2016/11/14.
 */

@Service("offerService")
public class OfferServiceImpl implements OfferService{  //} implements OfferDao {

    @Resource
    private OfferDao offerDao;

    @Override
    public Offer getOfferById(int offerId) {
        return this.offerDao.getOfferById(offerId);
    }

    @Override
    public Offer getOfferByCompany(String company) {
        return this.offerDao.getOfferByCompany(company);
    }

    @Override
    public void addOffer(Offer offer) {
        //System.out.println("OfferService addOffer begin");

        try{
            this.offerDao.addOffer(offer);
        }
        catch( Exception e ){
            e.printStackTrace();
        }

        //System.out.println("OfferService addOffer end");
    }

    @Override
    public void updateOffer(Offer offer) {
        this.offerDao.updateOffer(offer);
    }

    @Override
    public void deleteOffer(int offerId) {
        this.offerDao.deleteOffer(offerId);
    }
}
