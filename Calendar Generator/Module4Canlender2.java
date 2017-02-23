/**********
 * This program generates a calendar for a year based on user inputs
 *pre-condition: user input of year must be an integer greater than 0
 *@author fengxiao 
 * 
 */


import java.util.Scanner;

public class Module4Canlender2
{

    public static void main(String[] args)
    {//user input month and year
        Scanner in =new Scanner(System.in);
        System.out.print("Enter year:");
        int year=in.nextInt();
        for (int month=1;month<=12;month++)
        {
            printMonthCalendar(month,year);
            System.out.println("\n");
        }      
        in.close();

       
    }
        
    //print month calendar method
    public static void printMonthCalendar(int m,int y)
    {   
        printMonthHeader(m,y);
        printMonthBoday(m,y);
    }
    // leap year identification method
    public static boolean isLeapYear( int y )
    {//leap year can be divided evenly by 4 and not 100 but or divided evenly by 400 
        boolean LeapYear =((y % 4 ==0) & (y%100!=0)) || (y%400==0);
        return LeapYear;         
    }
    // get number of days in one month method
    public static int getNumDaysInMonth(int m, int y)
    {
        int numDays=0;
        if (m==4|m==6|m==9|m==11)//months have 30 days
            numDays=30;
        else if (m==1|m==3|m==5|m==7|m==8|m==10|m==12)//months have 31 days
            numDays=31;
        else if (m==2 && isLeapYear(y))//leap year has 29 days in February
            numDays=29;
        else // non leap year has 28 days in February
            numDays=28;
        return numDays;           
      }
               
    //get start day method
    public static int getStartDay( int m, int d, int y )
    {
        // Adjust month number & year to fit Zeller's numbering system
        if (m < 3) 
        {
            m = m + 12;
            y = y - 1;
        }
        
        int k = y % 100;      // Calculate year within century
        int j = y / 100;      // Calculate century term
        int h = 0;            // Day number of first day in month 'm'
        
        h = ( d + ( 13 * ( m + 1 ) / 5 ) + k + ( k / 4 ) + ( j / 4 ) +
            ( 5 * j ) ) % 7;
        
        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ( ( h + 5 ) % 7 ) + 1;     
        
        return dayNum;
    }
    //get month name method
    public static String getMonthName (int m)
    {
        String monthName = "";
        switch (m)
        {            
            case 1: monthName="January";break;
            case 2: monthName="February";break;
            case 3: monthName="March";break;
            case 4: monthName="April";break;
            case 5: monthName="May";break;
            case 6: monthName="June";break;
            case 7: monthName="July";break;
            case 8: monthName="August";break;
            case 9: monthName="September";break;
            case 10: monthName="October";break;
            case 11: monthName="November";break;
            case 12: monthName="December";break;          
                    
        }
        return monthName;
        
    }
   
    //create calendar header method
    public static void printMonthHeader(int m, int y)
    {
        System.out.println("\t" + getMonthName(m)+" "+y );
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }
    
    //print monthly calendar body method
    public static void printMonthBoday(int m, int y)
    {
        int d=1;   //first day of month
        int rows=0;//initiate rows
        int startDay=0;
        if (getStartDay(m,d,y)!=7)//call method to get start day of the month and year, replace 7 with 0
            startDay=getStartDay(m,d,y);      
        int monthDays=getNumDaysInMonth(m,y);//call method to get number of days in month 
        int days=1;  //initiate fist day in month
        //calculate rows
        rows=(startDay+monthDays)/7+1; // calculate how many rows in the calendar

        //print out first row
        for (int s=1;s<=startDay;s++)
            System.out.print("    ");
        for (int firstRowDays=1;firstRowDays<=(7-startDay);firstRowDays++)
        {
            System.out.print("   "+days);
            days++;
        }
        System.out.println();
       
        for (int r=2;r<=rows;r++)
        {//loop through each row           
            for(int c=1;c<=7;c++)//loop through each column
                {
                    if (days<=9)
                    {//for single digit days
                         System.out.print("   "+days);
                         days++;
                    }
                    else if (days<=monthDays)
                    {// for double digit days
                        System.out.print("  "+days);
                        days++;
                    }
                    else 
                        break;                 
                 }
              System.out.println();//go to the other line  
                
           }
               
        }        
}
    
    
    


