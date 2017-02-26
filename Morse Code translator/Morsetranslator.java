/*
 * This program translate between Morse code and English
 * @Author Xiao Feng
 * @variable inputChar - user input characters 
 * @variable translatechoice - user input translate type
 */
import java.util.Scanner;

public class Morsetranslator
{
    public static void main(String[] args)
    {
        {//user inputs translate type and translate sentence
            Scanner in =new Scanner(System.in);
            System.out.println("Please specify translate type, 1-charactor to Morse code, 2- Morse code to charactor:");
            int translateChoice=in.nextInt();
            
            // new add. check option if not equals 1 or 2 then Exit
            if ((translateChoice != 1) && (translateChoice!=2))
            {
                System.out.println("No such option: Exit");
            }
            in.nextLine();
            
            System.out.print("Please input translate sentence:");
            String inputChar= in.nextLine();
            in.close();
            
            // clean the input. so that bad input won't effect the program
            inputChar = inputChar.toLowerCase();
            inputChar = inputChar.trim();
            
            //store charaters and morsecodes dictionary 
            String [] Characters={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s"
                    ,"t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0"," "};
            String [] morseCodes={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-.."
                    ,"--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
                   ,".----","..---","...--","....-",".....","-....","--...","---..","----.","-----","|"};
            char singleChar;
            
            //logic condition for translate type 1 and 2
            switch (translateChoice)
            {
                case 1:          
                {//translate English to Morse codes
                    for(int letter=0;letter<inputChar.length();letter++)
                    {
                        singleChar=inputChar.charAt(letter);
                        //loop through characters dictionary to lookup index for morsecodes
                        for(int index=0; index<Characters.length;index++)
                        {
                            if(Characters[index].equalsIgnoreCase(String.valueOf(singleChar)))
                            {
                                System.out.print(morseCodes[index]);
                                break;
                            }                                   
                        }
                        System.out.print(" ");
                    }
                    break;
                }           
                case 2:
                {//translate Morse codes to English                 
                    int spaceIndex;
                    spaceIndex=inputChar.indexOf(' ');//look for index of next space  
                    //loop through morse code dictionary to lookup character value
                    int index=0;
                    while(spaceIndex>0 && index<morseCodes.length)
                    {       
                        if (morseCodes[index].equalsIgnoreCase(inputChar.substring(0,spaceIndex)))
                        {
                            System.out.print(Characters[index]);    
                            inputChar=inputChar.substring(spaceIndex+1);
                            spaceIndex=inputChar.indexOf(' ');
                            index=0;
                        }  
                        index++;
                     }
                    // for the case that input sentence is one character or the last character in a sentence
                    if (spaceIndex==-1)
                    {
                        for( index=0;index<morseCodes.length;index++)
                        {
                            if (morseCodes[index].equalsIgnoreCase(inputChar))
                                System.out.print(Characters[index]);
                        }
                    }                                               
                }            
            }
        }
    }
}


