/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.sort;

import com.hotel.entity.HotelRequest;
import java.util.Comparator;

/**
 *
 * @author praneetha
 */
public class OrderNoComparator implements Comparator<HotelRequest> {

   private int sortingRequest;
    
    private int oppositeRequest;
    
    public OrderNoComparator(){
        this(1,-1);
    }
    
    public OrderNoComparator(int sortingRequst, int oppositeRequest){
        this.sortingRequest=sortingRequst;
        this.oppositeRequest= oppositeRequest;
    }
    @Override
    public int compare(HotelRequest o1, HotelRequest o2) {
         int id1 = o1.getRequest_id().intValue();
        int id2 = o2.getRequest_id().intValue();
        if (id1 >id2) 
            return sortingRequest;
        else if (id1 < id2) 
            return oppositeRequest;
        else 
            return 0;
    }
    
}
