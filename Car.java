package MiRde;
/*
 * Class:			Car
 * Description:		The class represents a specific car
 * Author:			ConnorXu - s3748848
 */
public class Car 
{
	   private String regNo;
	   private String make;
	   private String model;
	   private String driverName;
	   private int passengerCapacity;
	   private boolean available;
	   private Booking[] currentBookings;
	   private Booking[] pastBookings;
	   private int currentBookNum;
	   private int pastBookNum;
	   private static final int MAXBOOKNUM = 5;
	   private static final int CARMAXBOOKNUM = 1;
	   
	   public Car(String regNo, String make, String model, String driverName, int passengerCapacity) 
	   {
		   
		   if(regNo.substring(0,3).matches("[a-zA-z].*+") && regNo.substring(3,6).matches("[0-9].*+") && regNo.length() == 6) 
		   {
			   this.regNo =regNo;  
		   }
		   else 
		   {
			   System.out.println("Wrong registration number!");  
		   }
		   System.out.println(regNo);
		   
		   if(make.substring(0,3).matches("[a-zA-Z].*+")) 
		   {
			   this.make = make;
		   }
		   else 
		   {
			   System.out.println("Wrong make!");
		   }
		   System.out.println(make);
		   
		   if(model.substring(0,3).matches("[a-zA-Z].*+")) 
		   {
			   this.model = model;
		   }
		   else 
		   {
			   System.out.println("Wrong model!");
		   }
		   System.out.println(model);
		   
		   if(driverName.substring(0,3).matches("[a-zA-Z].*+")) 
		   {
			   this.driverName = driverName;
		   }
		   else 
		   {
			   System.out.println("Wrong driveName!");
		   }
		   System.out.println(driverName);
		   
		   if(passengerCapacity > 0 && passengerCapacity < 10) 
		   {
			   this.passengerCapacity = passengerCapacity;   
		   }
		   else 
		   {
			   System.out.println("Wrong Passenger Capacity!");
		   }
		   System.out.println(passengerCapacity);
		   
		   currentBookings = new Booking[MAXBOOKNUM];
	       pastBookings = new Booking[10];
	       currentBookNum = 0;
	       pastBookNum = 0;

	       available = true;
	   }
	   public boolean book(String firstName, String lastName, DateTime required,int numPassengers) 
	   {
		   if (!available)
	        {
	            return false;
	        }

	        Booking booknew = new Booking(firstName, lastName, required, numPassengers, this);
	        currentBookings[currentBookNum] = booknew;
	        currentBookNum++;
            DateTime currentdate = new DateTime(); 
	        if (currentBookNum == CARMAXBOOKNUM && currentdate == required) 
	        {
	        	available = false;
	        }
	        if (currentBookNum == MAXBOOKNUM)
	        {
	            available = false;
	        }
	        
	        if(passengerCapacity < numPassengers) 
	        {
	        	available = false;
	        }
	        

	        return true;
	    }
	   
	   public Booking getLastBooking()
	    {
	        if (currentBookNum == 0)
	        {
	            return null;
	        }

	        return currentBookings[currentBookNum  - 1];
	    }

	    public void completeBook(String id)
	    {
	        int index = 0;

	        for (int i = 0; i < currentBookNum; i++)
	        {
	            if (currentBookings[i].getid().equals(id))
	            {
	                index = i;
	                break;
	            }
	        }
	        
	        Booking completedBook = currentBookings[index];
	        for (int i = index; i < currentBookNum - 1; i++)
	        {
	            currentBookings[index] = currentBookings[index + 1];
	        }
	        currentBookings[currentBookNum - 1] = null;
	        currentBookNum--;
	        available = true;

	        PastBooking(pastBookNum + 1);
	        pastBookings[pastBookNum] = completedBook;
	        pastBookNum++;
	    }
	    
	    public String getregNo()
	    {
	    	return regNo;
	    }
	    public String getmake()
	    {
	    	return make;
	    }
	    public String getmodel()
	    {
	    	return model;
	    }
	    public String getdriverName()
	    {
	    	return driverName;
	    }
	    public int getpassengerCapacity()
	    {
	    	return passengerCapacity;
	    }
	    public boolean getavailable()
	    {
	    	return available;
	    }
	    public Booking[] getcurrentBookings()
	    {
	    	return currentBookings;
	    }
	    public Booking[] getpastBookings()
	    {
	    	return pastBookings;
	    }
	    public int getcurrentBookNum()
	    {
	    	return currentBookNum;
	    }
	    public int getpastBookNum()
	    {
	    	return pastBookNum;
	    }
	    public static final int getMAXBOOKNUM()
	    {
	    	return MAXBOOKNUM;
	    }
	    public static final int getCARMAXBOOKNUM()
	    {
	    	return CARMAXBOOKNUM;
	    }
	    
	    public String getDetail() 
	    {
	    	return "RegNo:"+"        "+regNo+"\n"
	    		  +"Make & Model:"+" "+make+model+"\n"
	    		  +"Driver Name:"+"  "+driverName+"\n"
	    		  +"Capacity:"+"     "+passengerCapacity+"\n"
	    		  +"Available:"+"    "+available;
	    }
	    
	    public String toString() 
	    {
	    	return regNo+":"+make+":"+model+":"+driverName+":"+passengerCapacity+":"+available;
	    }
	   
 
	   

}
