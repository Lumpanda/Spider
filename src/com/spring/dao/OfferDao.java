package com.spring.dao;

/**
 * Created by Lumpanda on 2016/11/14.
 */

import com.spring.model.Offer;

public interface OfferDao {

    public Offer getOfferById( int offerId );

    public Offer getOfferByCompany( String company );

    public void addOffer( Offer offer );

    public void updateOffer( Offer offer );

    public void deleteOffer( int offerId );

}
