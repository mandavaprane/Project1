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
public class RoomCountComparator implements Comparator<HotelRequest>{
    private int sortingRequest;
    
    private int oppositeRequest;
    
    public RoomCountComparator(){
        this(1,-1);
    }
    
    public RoomCountComparator(int sortingRequst, int oppositeRequest){
        this.sortingRequest=sortingRequst;
        this.oppositeRequest= oppositeRequest;
    }
    @Override
    public int compare(HotelRequest o1, HotelRequest o2) {
         int id1 = Integer.parseInt(o1.getNoOfRoom());
        int id2 = Integer.parseInt(o2.getNoOfRoom());
        if (id1 >id2) 
            return sortingRequest;
        else if (id1 < id2) 
            return oppositeRequest;
        else 
            return new OrderNoComparator(sortingRequest, oppositeRequest).compare(o1, o2);
    }
}
