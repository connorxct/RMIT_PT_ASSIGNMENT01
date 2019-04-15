package MiRde;
/*
 * Class:			Menu & MiRideApp
 * Description:		The class represents the whole BookingApp function
 * Author:			ConnorXu - s3748848
 */
public class MenuAndMiRide 
{
    private Car[] cars;// the cars in the application
    private Booking[] book;// booking in the application
    private int carNum;// the number of cars in the application
    private int bookNum;// the number of bookings in the application
    private Menu me;// use the menu class
    
    public MenuAndMiRide()// initial the application
    {
        cars = new Car[5];
        book = new Booking[10];
        carNum = 0;
        bookNum = 0;
        me = new Menu();
    }
    public void menuDisplay()// the main function of application and the menu of this application
    {
        while(true) 
        {
        	me.menu();
        	System.out.print("Please enter your option: ");
            String option = me.getoption();
    	    if ("EX".equals(option)) 
    	    {
    	    	System.out.println("System Close.");
                break;
    	    }
    	    switch (option)
            {
                case "CC": createCar();
                break;

                case "BC": bookCar();
                break;

                case "CB": completebooking();
                break;

                case "DA": displayallcars();
                break;

                case "SS": searchSpecialCar();
                break;

                case "SA": searchAvailableCars();
                break;

                case "SD": seedData();
                break;

                default: System.out.println("Wrong! You enter wrong option.");
                break;
            }
        }
        me.close();
    }

    private void createCar()//  define the case create car
    {
        Car newCar = new_Car();
        if (newCar != null)
        {
            addnewCar(newCar);
            System.out.println("New Car added successfully and the registration number is" + newCar.getregNo()+"\n");
        }
    }
    private void addnewCar(Car newCar)// add new car into our application
    {
        CarArray(carNum + 1);
        cars[carNum] = newCar;
        carNum++;
    }
    
    private void CarArray(int capacity)// define the car array
    {
        if (capacity >= cars.length)
        {
            Car[] newARR = new Car[cars.length * 2];
            System.arraycopy(cars, 0, newARR, 0, cars.length);

            cars = newARR;
        } 
    }

    private Car new_Car()// the steps of add new car
    {
        String regNo = me.enter("Please enter the registration No:   ");
        if (existcarregnum(regNo))
        {
            System.out.println("Wrong! This Car already exist in the system.");
        }
        String make  = me.enter("Please enter your car's make:     ");
        String model = me.enter("Please enter your car's model:     ");
        String driverName = me.enter("Please enter the driver's Name:    ");
        int passengerCapacity = me.enterint("Please enter the passenger capacity of your car:  ", 1, 9);
        return new Car(regNo, make, model, driverName, passengerCapacity);
        
    }
    private boolean existcarregnum(String regNo)// return true when the registration number is exist in the application
    {
        for (int i = 0; i < carNum; i++)
        {
            Car car = cars[i];

            if (car.getregNo().equals(regNo))
            {
                return true;
            }
        }

        return false;
    }
    
    private void bookCar()// start to book a ride
    {
        Booking newbooking = new_Booking();

        if (newbooking != null)
        {
            addBooking(newbooking);
            System.out.printf("Thanks for your booking and %s will pick you up on %s.\n",newbooking.getcar().getdriverName(),newbooking.getpickupDateTime().getFormattedDate());
            System.out.println("Then this is your booking ID: " + newbooking.getid());
        }
    }

    private Booking new_Booking()// the steps of booking
    {
        String date = me.enter("Please enter your reuired time:       ");
        DateTime required;
        try// make sure user could try again when they enter wrong time
        {
            required = parseDate(date);
        }
        catch (Exception ex)
        {
            System.out.println("Wrong! Please enter new date.");
            return null;
        }
        Car[] availableCars = searchAvailableCars(required);
        if (availableCars.length == 0)
        {
            System.out.println("Sorry we have no car for this date.");
            return null;
        }

        System.out.println("These following cars are our application have now.");
        for (int b = 0; b < availableCars.length; b++)
        {
            System.out.printf("%d.  %s\n", b + 1, availableCars[b].getregNo());
        }

        int bookingIndex = me.enterint("Please select the number of our available car that you want to book: ", 1, availableCars.length);
        Car bookingCar = availableCars[bookingIndex - 1];

        String firstName = me.enter("Please enter your first name:      ");
        if (firstName.length() < 3) 
        {
            System.out.println("Wrong! You entered the wrong first name.");
            return null;
        }

        String lastName  = me.enter("Please enter your last name:             ");
        if (lastName.length() < 3) {
            System.out.println("Wrong! You entered the wrong last name.");
            return null;
        }

        int numPassengers = me.enterint("Please enter the number of passengers:   ", 1, 9);
        if (numPassengers > bookingCar.getpassengerCapacity()) 
        {
            System.out.println("Wrong! This car does not enough passenger capacity.");
            return null;
        }

        boolean booking = bookingCar.book(firstName, lastName, required, numPassengers);
        if (booking)  
        {
            return bookingCar.getlastBooking();
        }

        return null;
    }
    private DateTime parseDate(String date)// give the date time
    {
        String[] newdate = date.split("/");
        int day = Integer.parseInt(newdate[0]);
        int month = Integer.parseInt(newdate[1]);
        int year = Integer.parseInt(newdate[2]);
        return new DateTime(day, month, year);
    }
    
    private void addBooking(Booking newBooking)//add new booking into application
    {
        bookingArray(bookNum + 1);
        book[bookNum] = newBooking;
        bookNum++;
    }
    
    private void bookingArray(int capacity)// make sure about the booking array capacity
    {
        if (capacity >= book.length)
        {
            Booking[] newarray = new Booking[cars.length * 2];
            System.arraycopy(book, 0, newarray, 0, cars.length);
            book = newarray;
        }
    }

    private Car[] searchAvailableCars(DateTime required)// search the available car in the application
    {
        int availableCarNum = 0;
        for (int i = 0; i < carNum; i++)
        {
            if (cars[i].carforoneday(required))
            {
                availableCarNum++;
            }
        }

        Car[] availableCar = new Car[availableCarNum];
        int index = 0;
        for (int i = 0; i < carNum; i++)
        {
            if (cars[i].carforoneday(required))
            {
                availableCar[index++] = cars[i];
            }
        }

        return availableCar;
    }
    
    private void completebooking()// complete the booking
    {
        String bookingInfo   = me.enter("Please enter your registration number or your Booking Date:      ");
        String firstName     = me.enter("Please enter your first name:     ");
        String lastName      = me.enter("Please enter your last name:     ");
 
        Booking booking = null;
        for (int b = 0; b < bookNum; b++)
        {
            Booking specific = book[b];

            if (specific.getcar().getregNo().equals(bookingInfo) && specific.getpickupDateTime().getFormattedDate().equals(bookingInfo))
            {
                if (specific.getfirstName().equals(firstName) && specific.getlastName().equals(lastName))
                {
                    booking = specific;
                    break;
                }
            }
        }

        if (booking == null)
        {
            System.out.println("Wrong! This booking could not be found in our application.");
            return;
        }

        double kilometersTravelled = me.enterdou("Enter your travelled kilometers:  ");
        completeBooking(booking, kilometersTravelled);
        System.out.println("Thanks for using MiRide and our team hope you have a good trip.");
        System.out.printf("$%.4f have been transfromed from your account.\n", booking.getbookingFee() + booking.gettripFee());
    }

	private void completeBooking(Booking booking, double kilometersTravelled)
    {
        Car bookingCar = booking.getcar();
        bookingCar.completeBook(booking.getid());
    }
    
	private void displayallcars()// display all cars' inforamtion in the application
    {
        if (carNum == 0)
        {
            System.out.println( "Sorry, there is no car avaliable in the appliacation now.");
            return;
        }

        System.out.println("All cars' information:\n");
        for (int i = 0; i < carNum; i++)
        {
            System.out.println(cars[i].getDetail());
            System.out.println();
        }
    }
    
	private void searchSpecialCar()// search one specific car in the application
    {
        String regNo = me.enter("Please enter the registration number:    ");
        boolean found = false;

        for (int c = 0; c < carNum; c++)
        {
            Car car = cars[c];

            if (car.getregNo().equals(regNo))
            {
                found = true;

                System.out.println();
                System.out.println(car.getDetail());
                System.out.println();
                break;
            }
        }

        if (!found)
        {
            System.out.println("Srroy, we cannot find the car that you want.");
        }
    }
    
   

    private void searchAvailableCars()// search the available cars in the application
    {
        String date = me.enter("Please enter your time Required:         ");
        DateTime required;
        try
        {
            required = parseDate(date);
        }
        catch (Exception ex)
        {
            System.out.println("Wrong! Please enter new date.");
            return;
        }

        DateTime now = new DateTime();
        int days = DateTime.diffDays(required, now);
        if (days < 0 || days > 7)
        {
            System.out.println("Sorry we have no car that is available for this date.");
            return;
        }

        Car[] availableCars = searchAvailableCars(required);

        System.out.println("These following cars are available in our application now.");
        for (Car car : availableCars)
        {
            System.out.println(car.getDetail());
            System.out.println();
        }
    }

    private void seedData()// establish the initial data
    {
        if (carNum != 0)
        {
            System.out.println("Wrong! These cars already had their data.");
            return;
        }

       
        Car[] seedCars = // initial 6 cars in the system
            {
                new Car("CON123", "BENZ", "ModelG", "LeBron James", 5),
                new Car("CON234", "HONDA", "ModelA", "Lionel Messi", 8),
                new Car("CON345", "TESLA", "ModelS", "Michael Jordan", 6),
                new Car("CON456", "BUICK", "ModelB", "Lady Gaga", 6),
                new Car("CON567", "BMW", "ModelQ", "Kobe Bryant", 4),
                new Car("CON678", "FERRARI", "ModelS", "James Harden", 2)
            };

        for (Car seedCar : seedCars)// initial 6 bookings in the system
        {
            addnewCar(seedCar);
        }
        cars[0].book("Davide", "Chen", new DateTime(), 2);
        Booking car0 = cars[0].getlastBooking();
        addBooking(car0);

        cars[1].book("James", "Ding", new DateTime(), 4);
        Booking car1 = cars[1].getlastBooking();
        addBooking(car1);

        cars[2].book("Tiffany", "Xu", new DateTime(), 2);
        Booking car2 = cars[2].getlastBooking();
        addBooking(car2);
        completeBooking(car2, 40);
        
        cars[3].book("Dennis", "Zhao", new DateTime(), 4);
        Booking car3 = cars[3].getlastBooking();
        addBooking(car3);
        completeBooking(car3, 80);
        
        cars[4].book("Leo", "Liu", new DateTime(), 4);
        Booking car4 = cars[3].getlastBooking();
        addBooking(car4);
        completeBooking(car4, 120);
        
        cars[5].book("IVY", "Luo", new DateTime(), 4);
        Booking car5 = cars[3].getlastBooking();
        addBooking(car5);
        completeBooking(car5, 160);

        System.out.println("Seeding data are success!");
    }
    
	public Car[] getcar() 
	{
		return cars;// make sure get parameter
	}
	public Booking[] getbooking() 
	{
		return book;// make sure  get parameter
	}
	public int getcarNum() 
	{
		return carNum;// make sure get parameter
	}
	public int getbookNum() 
	{
		return bookNum;// make sure get parameter
	}
	public static void main(String[] args) // the main method of this application, start whole function
	{
		MenuAndMiRide APP = new MenuAndMiRide();
		APP.menuDisplay();
    }
   
    	
   
}
