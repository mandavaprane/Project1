
package com.hotel.managedbean;

import com.hotel.entity.HotelRequest;
import com.hotel.services.BookingServiceImpl;
import com.hotel.sort.CustomerNameComparator;
import com.hotel.sort.OrderNoComparator;
import com.hotel.sort.RoomCountComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="listBean")
@ViewScoped
public class ListBean implements Serializable{
    
    private List<HotelRequest> hotelRequestList= new ArrayList<HotelRequest>();
    
    private int sortingRequest;
    
    private int oppositeRequest;

    private String sortColumnName;
    
    @PostConstruct
    public void init(){
    	
    	hotelRequestList=new BookingServiceImpl().getCurrentBookings();
    }
    
	public List<HotelRequest> getHotelRequestList() {
		return hotelRequestList;
	}

	public void setHotelRequestList(List<HotelRequest> hotelRequestList) {
		this.hotelRequestList = hotelRequestList;
	}

	public int getSortingRequest() {
		return sortingRequest;
	}

	public void setSortingRequest(int sortingRequest) {
		this.sortingRequest = sortingRequest;
	}

	public int getOppositeRequest() {
		return oppositeRequest;
	}

	public void setOppositeRequest(int oppositeRequest) {
		this.oppositeRequest = oppositeRequest;
	}
    
        
        public String getSortColumnName() {
		return sortColumnName;
	}

	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}
    
        
        
        public void sortList(){
        
            if(sortingRequest==1){
                oppositeRequest=-1;
            }
            else
                oppositeRequest=1;
            if(sortColumnName.equalsIgnoreCase("orderno")){
           
                Collections.sort(hotelRequestList, new OrderNoComparator(sortingRequest,oppositeRequest));
            }
             
            else
                if(sortColumnName.equalsIgnoreCase("noforoom")){
                     Collections.sort(hotelRequestList, new RoomCountComparator(sortingRequest,oppositeRequest));
                }
            
            else
            {
                          
                 Collections.sort(hotelRequestList, new CustomerNameComparator(sortingRequest,oppositeRequest));
               }
                
        }
    
}
