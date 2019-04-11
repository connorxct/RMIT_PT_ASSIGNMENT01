package MiRde;

public class Booking 
{
       private String id;
       private double bookingFee = 1.5;
       private DateTime pickupDateTime;
       private String firstName;
       private String lastName;
       private int numPassengers;
       private double kilometersTravelled;
       private double tripFee;
       private Car car;
       
       public Booking(String firstName, String lastName, DateTime required, int numPassengers, Car car) 
       {
    	   if(firstName.substring(0, 3).matches("[a-zA-Z].*+")) 
    	   {
    	       this.firstName = firstName;
    	   }
    	   else 
    	   {
    		   System.out.println("Wrong First Name!");
    	   }
    	   System.out.println(firstName);
    	   
    	   if(lastName.substring(0,3).matches("[a-zA-Z].*+")) 
    	   {
    	       this.lastName = lastName;
    	   }
    	   else 
    	   {
    		   System.out.println("Wrong Last Name!");
    	   }
    	   System.out.println(lastName);
    	   this.pickupDateTime = required;
           this.numPassengers = numPassengers;
           this.car = car;
           this.id = car.getregNo() + "_" +firstName.substring(0,3).toUpperCase().concat(lastName.substring(0,3).toUpperCase())+ "_" + required.getEightDigitDate();
           
       }
       
       public String getid() 
       {
    	   return id;
       }
       public double getbookingFee() 
       {
    	   return bookingFee;
       }
       public DateTime getpickupDateTime() 
       {
    	   return pickupDateTime;
       }
       public String getfirstName() 
       {
    	   return firstName;
       }
       public String getlastName() 
       {
    	   return lastName;
       }
       public int getnumPassengers() 
       {
    	   return numPassengers;
       }
       public double getkilometersTravelled() 
       {
    	   return kilometersTravelled;
       }
       public double gettripFee() 
       {
    	   return tripFee;
       }
       public Car getcar() 
       {
    	   return car;
       }
       
       public String getDetail() 
       {
    		 return "id:"+"            "+id+"\n"
    			    +"Booking Fee:"+"  "+"$"+bookingFee+"\n"
    			    +"Pick up Date:"+" "+pickupDateTime.getFormattedDate()+"\n"
    			    +"Name:"+"         "+numPassengers+"\n"
    			    +"Travelled:"+"    "+kilometersTravelled+"\n"
    			    +"Trip Fee:"+"     "+tripFee+"\n"
    			    +"Car Id:"+"       "+id;  
    	  
    	   
       }
       public String toString() 
       {
    	   return id+":"+bookingFee+":"+pickupDateTime+":"+firstName+" "+lastName+":"+numPassengers+":"+kilometersTravelled+":"+tripFee+":"+id;
       
       }
}
