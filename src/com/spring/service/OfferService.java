package com.spring.service;

import com.spring.model.Offer;

/**
 * Created by Lumpanda on 2016/11/16.
 */
public interface OfferService {

    public Offer getOfferById( int offerId );

    public Offer getOfferByCompany( String company );

    public void addOffer( Offer offer );

    public void updateOffer( Offer offer );

    public void deleteOffer( int offerId );

}
