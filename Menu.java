package MiRde;
import java.util.*;
public class Menu 
{
	private Scanner choice;
    public Menu()// get the input from users
    {
        choice = new Scanner(System.in);
    }
    public void menu()//show the menu
    {
        System.out.println();
        System.out.println("*** MiRides System Menu ***");
        System.out.println("Create Car                     CC");
        System.out.println("Book Car                       BC");
        System.out.println("Complete Booking               CB");
        System.out.println("Display ALL Cars               DA");
        System.out.println("Search Specific Car            SS");
        System.out.println("Search available cars          SA");
        System.out.println("Seed Data                      SD");
        System.out.println("Exit Program                   EX");
    }
    
    public String getoption()
    {
        String option = choice.nextLine();

        return option.toUpperCase();
    }// return the upper case of input to system whether user enter the lower case or upper case

    public String enter(String note)
    {
        System.out.print(note);
        return choice.nextLine();
    }// note is the something that users entered

    public int enterint(String note, int mini, int max)
    {
        while (true)
        {
            String value = enter(note);
            try
            {
                int a = Integer.parseInt(value);
                if (a < mini || a > max)
                {
                    System.out.printf("Wrong! The integer that you entered should be in [%s, %s].\n", mini, max);
                    continue;
                }

                return a;
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Wrong! Please enter new integer value.");
            }
        }
    }// make sure the users could enter the correct integer value
     //user also could try again when enter wrong integer

    public double enterdou(String note)
    {
        while (true)
        {
            String value = enter(note);

            try
            {
                return Double.parseDouble(value);
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Wrong! Please enter new double value.");
            }
        }
    }// make sure they could enter correct double value and could try again until they enter correctly
    public void close()
    {
        choice.close();
    }
}// close the choice and reflect the information
