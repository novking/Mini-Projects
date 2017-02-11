import java.util.Scanner;
import java.lang.Math;
/**
* This program calculates Body Mass Index(BMI) for an individual
**/

public class Module2BMI
{

    public static void main(String[] args)
    {
        //Define and initialize variable
        int weight = 0;     //user input of weight
        int height = 0;     //user input of height  
        double heightMeters = 0; //initiate height in meters
        double heightSquare = 0; //initiate squared height
        double finalBMI = 0;     //initiate BMI
        
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        System.out.println( "\n\n" );
        System.out.print( "Enter weight(in ponds) and hieght(in inches) speperat by black space:" );
        weight = input.nextInt() ;  //input weight
        height = input.nextInt() ;  //input height
        heightMeters = height * 0.0254;     //calculate height in meters
        heightSquare = Math.pow(heightMeters, 2);  //calculate squared height in meters
        finalBMI = (weight*0.45359237)/heightSquare;    //calculate BMI
        
        
        
        
        //output BMI 
        System.out.println( "\n\n" );
        System.out.println( "Your BMI is:" + "\t" + finalBMI);
        System.out.println( "\n" + "Underweight: less than 18.5" + "\n" + "Normal: 18.5 – 24.9"
                            + "\n" + "Overweight: 25 – 29.9" +"\n" + "Obese: 30 or greater");
        
        

    }

}
