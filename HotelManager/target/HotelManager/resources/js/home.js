$(document).ready(function()
{
 $.idleTimeout('#idletimeout', '#idletimeout a', {
	idleAfter: 1800,
 	onIdle: function(){
	   invalidateUserSession();
 	}
});
});
 function invalidateUserSession(){
 $.ajax({	
		   
		   url:'keepAliveServlet/invalidateUserSession',
		   dataType:'json',
		      
		   async: true,   
		   success:function(msg) 
		   {
		   console.log(msg["invalidatedUserSession"])
			if(msg["invalidatedUserSession"]){
                       window.location = "index.xhtml";
			 }
			  
		   },
		  error:function (xhr, ajaxOptions, thrownError)
		  {
		   
		  }    
});
}
 

 