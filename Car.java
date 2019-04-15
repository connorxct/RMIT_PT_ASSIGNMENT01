package MiRde;
/*
 * Class:			Car
 * Description:		The class represents a specific car
 * Author:			ConnorXu - s3748848
 */
public class Car 
{
	   private String regNo;// the registration number
	   private String make;// the  make of car
	   private String model;//the model of car
	   private String driverName;// the name of driver of this car
	   private int passengerCapacity;//the passenger capacity of this car
	   private boolean available; // the available of one specific car
	   private Booking[] currentBookings;// the array of current booking
	   private Booking[] pastBookings;// the array of past booking
	   private int currentBookNum;// the number of current booking
	   private int pastBookNum;// the number of past booking
	   private static final int MAXBOOKNUM = 5;// set the static max booking number
	   private static final int CARMAXBOOKNUM = 1;// set the static max booking number of one car in one day
	   // set plenty of variables to build the information for one specific car
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
		   System.out.println(regNo);//set regNo(exactly 6 char-3 alpha char followed by 3 numeric char)
		   
		   
		   if(make.substring(0,3).matches("[a-zA-Z].*+")) 
		   {
			   this.make = make;
		   }
		   else 
		   {
			   System.out.println("Wrong make!");
		   }
		   System.out.println(make);//set make-have multiple words
		   
		   if(model.substring(0,3).matches("[a-zA-Z].*+")) 
		   {
			   this.model = model;
		   }
		   else 
		   {
			   System.out.println("Wrong model!");
		   }
		   System.out.println(model);//set model-have multiple words
		   
		   if(driverName.substring(0,3).matches("[a-zA-Z].*+")) 
		   {
			   this.driverName = driverName;
		   }
		   else 
		   {
			   System.out.println("Wrong driveName!");
		   }
		   System.out.println(driverName);//set drive name-have multiple words
		   
		   if(passengerCapacity > 0 && passengerCapacity < 10) 
		   {
			   this.passengerCapacity = passengerCapacity;   
		   }
		   else 
		   {
			   System.out.println("Wrong Passenger Capacity!");
		   }
		   System.out.println(passengerCapacity);//set Passenger capacity-greater than 0 and less than 10
		   
		   currentBookings = new Booking[MAXBOOKNUM];// the max current booking number for one car is 5
	       pastBookings = new Booking[10];
	       currentBookNum = 0;//initial the value of current book number
	       pastBookNum = 0;//initial the value of past book number

	       available = true;// initial the value of available
	   }
	   public boolean book(String firstName, String lastName, DateTime required,int numPassengers) 
	   {
		   if (!available)
	        {
	            return false;
	        }

	        Booking booknew = new Booking(firstName, lastName, required, numPassengers, this);
	        currentBookings[currentBookNum] = booknew;
	        currentBookNum++;// every new book will be added to array and cannot greater than 5
            DateTime currentdate = new DateTime(); 
	        if (currentBookNum == CARMAXBOOKNUM && currentdate == required) 
	        {
	        	available = false;
	        }
	        if (currentBookNum == MAXBOOKNUM)
	        {
	            available = false;
	        }
	        // one car cannot be booked more than one time in one day
	        if(passengerCapacity < numPassengers) 
	        {
	        	available = false;
	        }// passenger capacity cannot  be less than passenger number
	        DateTime now = new DateTime();
	        int days = DateTime.diffDays(required, now);
	        if (days < 0 || days > 7)
	        {
	            System.out.println("Wrong date!");
	        }
	        return true;
	    }// the car cannot be booked in the past and can be booked in recent 7 days
	   
	   public Booking getlastBooking()
	    {
	        if (currentBookNum == 0)
	        {
	            return null;
	        }

	        return currentBookings[currentBookNum  - 1];
	    }// get the last booking of one specific car

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
	        // complete the booking of one car
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
	    }// remove the completed booking from the current booking
	    private void PastBooking(int capacity)
	    {
	        if (capacity >= pastBookings.length)
	        {
	            Booking[] newARR = new Booking[pastBookings.length * 2];
	            System.arraycopy(pastBookings, 0, newARR, 0, pastBookings.length);
	            pastBookings = newARR;
	        }
	    }
	    public boolean carforoneday(DateTime required)// make sure the one car cannot be booked in one day
	    {
	        if (!available)
	        {
	            return false;
	        }

	        for (int i = 0; i < currentBookNum; i++)
	        {
	            Booking booking = currentBookings[i];

	            int daysDiff = DateTime.diffDays(required, booking.getpickupDateTime());
	            if (daysDiff == 0)
	            {
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    public String getregNo()
	    {
	    	return regNo;// make sure other class can get parameter
	    }
	    public String getmake()
	    {
	    	return make;// make sure other class can get parameter
	    }
	    public String getmodel()
	    {
	    	return model;// make sure other class can get parameter
	    }
	    public String getdriverName()
	    {
	    	return driverName;// make sure other class can get parameter
	    }
	    public int getpassengerCapacity()
	    {
	    	return passengerCapacity;// make sure other class can get parameter
	    }
	    public boolean getavailable()
	    {
	    	return available;// make sure other class can get parameter
	    }
	    public Booking[] getcurrentBookings()
	    {
	    	return currentBookings;// make sure other class can get parameter
	    }
	    public Booking[] getpastBookings()
	    {
	    	return pastBookings;// make sure other class can get parameter
	    }
	    public int getcurrentBookNum()
	    {
	    	return currentBookNum;// make sure other class can get parameter
	    }
	    public int getpastBookNum()
	    {
	    	return pastBookNum;// make sure other class can get parameter
	    }
	    public static final int getMAXBOOKNUM()
	    {
	    	return MAXBOOKNUM;// make sure other class can get parameter
	    }
	    public static final int getCARMAXBOOKNUM()
	    {
	    	return CARMAXBOOKNUM;// make sure other class can get parameter
	    }
	    
	    public String getDetail() 
	    {
	    	return "RegNo:"+"        "+regNo+"\n"
	    		  +"Make & Model:"+" "+make+model+"\n"
	    		  +"Driver Name:"+"  "+driverName+"\n"
	    		  +"Capacity:"+"     "+passengerCapacity+"\n"
	    		  +"Available:"+"    "+available;
	    }//get the detail of one car
	    
	    public String toString() 
	    {
	    	return regNo+":"+make+":"+model+":"+driverName+":"+passengerCapacity+":"+available;
	    }
	   
 
	   

}
