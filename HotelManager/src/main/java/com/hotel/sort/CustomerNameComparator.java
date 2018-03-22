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
public class CustomerNameComparator implements Comparator<HotelRequest> {
    
     private int sortingRequest;
    
    private int oppositeRequest;
    
    public CustomerNameComparator(){
        this(1,-1);
    }
    
    public CustomerNameComparator(int sortingRequst, int oppositeRequest){
        this.sortingRequest=sortingRequst;
        this.oppositeRequest= oppositeRequest;
    }
     @Override
    public int compare(HotelRequest oneHotelRequest, HotelRequest secondHotelRequest) {
        String customerName1 = oneHotelRequest.getRequesterName();
        String customerName2 = secondHotelRequest.getRequesterName();
         
        int compare = customerName1.compareTo(customerName2); 
        
        if(compare == 0){
             return new RoomCountComparator(sortingRequest, oppositeRequest).compare(oneHotelRequest, oneHotelRequest);
        }//reverse positive (cause we need descending)
        else if(compare > 0){
            return sortingRequest;
        }//reverse negative (cause we need descending)
        else{
            return oppositeRequest;
        }
    }
}
