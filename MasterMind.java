// Name: Gurdev singh                                                     course:Program Design1150
// Student id:100376466                                                   section:003
package com.company;
/**
 * This array class is imported to use some properties of array class.
 */
import java.util.Arrays;
/**
 * This scanner class is used to take the input from the user .
 */
import java.util.Locale;
import java.util.Scanner;
/**
 * This character class is imported to check that all inputs must be only digits.
 */
import static java.lang.Character.isDigit;
import static javax.swing.DropMode.ON;
/**
 * This is an important class as it contains all methods that are required for this master mind game.
 * @author Gurdev singh
 * @since 28March,2022
 */
public class MasterMind
{
    static Scanner keyboard=new Scanner(System.in);
    /**
     * This is a main method that is used to call other method and print the values.
     * @param args This is a simple String type argument.
     */
    public static void main(String[] args)
    {
        printIndentification();
        int NumberOfTimeGamePlayed=0;
        int  finalTotalGuess=0;
        String action="Start";
        while(!(action.equals("STOP")))
        {
            NumberOfTimeGamePlayed++;
            System.out.println("please tell me the size of number to generate it must be equal or grater than2 " +
                    "but less than or equal to 10");
            int size = 0;
            size = keyboard.nextInt();
            while (size < 2 || size >= 10)
            {
                System.out.println("Enter size again which must be greater than 2 and less tha 10");
                size = keyboard.nextInt();
            }
            System.out.print("The number is : ");
            int n=0;
            while(n<size)
            {
                System.out.print("*");
                n++;
            }
            System.out.println("");
            //               **PART:1**
            // Random numbers are generated and checked by computer
            // method:1 generate array of random numbers and store it in array variable.
            int[] actualNumberArray = createNumber(size);
            //             ** PART2 **
            //   Numbers are guessed by user.
             int guess=1;
             while(guess<=10)
             {
                 // Method2:It takes the input from user as guess but in String form.
                 String guessedStringNumber = guessNumberInString(size);
                 if(guessedStringNumber.toUpperCase().equals("ON"))
                 {
                     break;
                 }
                 // Method3: To check all the values in guess are different
                 boolean answer = checkrepeation(guessedStringNumber);
                 while (!answer) // calling again method3 and method4,if values are Same .
                 {
                     System.out.println("Your guessed number have repeated digits so try again with unique digits");
                     guessedStringNumber = guessNumberInString(size);
                     answer = checkrepeation(guessedStringNumber);
                 }
                 // Method4: To create an array of unique guessed numbers.
                 int[] guessNumber = realguessedNumber(guessedStringNumber, size);
                 //Method:5  To check that is the guess is right or not
                 boolean checkGuessNumber=checkGuess(guessNumber,actualNumberArray);
                 if(checkGuessNumber)
                 {
                     System.out.println("Good job ,You guess is right :)");
                     System.out.println("You take "+guess+" Number of guess");
                     break;
                 }
                 else
                 {
                     System.out.printf("Number of guess\t\tno. of right digits\t\tright places\t\twrong places\t\tHINT(RightDigitIs)\n");
                     System.out.printf("%10d ",guess);
                     //Method 6: find number of digits are right  with computer generated number.
                     int correctDigits = findNumberOfRightDigit(actualNumberArray, guessNumber);
                     System.out.printf("%25d", correctDigits);
                     //Method7: to know right or Wrong places of digits.
                     int wrongplaces = wrongOrRightPlace(actualNumberArray, guessNumber);
                     System.out.printf("%24d", wrongplaces);
                     //   Method 8:    **HINT**
                     // In this hint ,after 3 guess user will come to know about one digit at right place ,after 6 guess he will know about
                     // 2 digits in number at right place and after 8 guess ,he will know about 4 digits at the right place .
                     int number=0;
                     System.out.print("\t\t\t\t");
                     int actualDigit,rightPlaceOfActualDigit;
                     while(number<size)
                     {
                         if(number==1 && guess>=3)
                         {
                             actualDigit = findRightDigit(actualNumberArray, number);
                             System.out.print(actualDigit);
                         }
                         else if((number==1||number==3) && guess>=5)
                         {
                             actualDigit = findRightDigit(actualNumberArray, number);
                             System.out.print(actualDigit);
                         }
                         else if((number==1||number==3||number==5||number==7)&& guess>7)
                         {
                             actualDigit = findRightDigit(actualNumberArray, number);
                             System.out.print(actualDigit);
                         }
                         else
                          {
                              System.out.print("*");
                          }
                         number++;
                     }
                     System.out.println("");
                     guess++;
                     finalTotalGuess++;
                 }
             }
             //                 **PART3**
            // MethodPRINT: print the actual generated random numbers of array
            System.out.print("The number is:");
            printArray(actualNumberArray);
            System.out.println("To be continue say continue or type \"STOP\" to stop");
            action=keyboard.next().toUpperCase();
        }
        int average=finalTotalGuess/NumberOfTimeGamePlayed;
        System.out.println(" Thanks for playing this game :) :) ");
        System.out.println("Your average performance  is "+average);
    }
    /**
     * This method is used to print my personal information including my name,course ,student id and section.
     */
    private static void printIndentification()
    {
        System.out.println("Name: Gurdev singh \t\t\t\t Course: Program design \n StudentId:100376466 \t\t\t\t Section:003");
    }
     // Method1: generate a random number of all different digits
    /**
     * This method is use to create unique random digits by computer. This method also call another
     * method to check if the random digits are unique or not.
     * @param size This is a user input that is used to create that long size of number
     * @return An array that has all unique digits .
     */
    private static int [] createNumber(int size)
    {
        int index=0;
        int randomDigit=0;
        int[] digitArray=new int[size];
        while(index<digitArray.length)
        {
           randomDigit=(int)(Math.random() *10);
           // compare the digit in the array element
            boolean notSameDigit=compareNotSameDigit(digitArray, randomDigit);

            while(!notSameDigit)
            {
                randomDigit=(int)(Math.random() *10);
                notSameDigit=compareNotSameDigit(digitArray, randomDigit);
            }
            // now  it is clear that digit is unique then pass that into an array.
            digitArray[index]=randomDigit;
            index++;
        }
        return digitArray;
    }
    //method (1.1): compare the content of an array whether it is same or not.
    /**
     * This method is used to compare digits in a given array which are randomly created by the computer.
     * @param array An array is passed from which repeated or Same digits are matched.
     * @param compareNumber A target that need to match in array whether it is already in array or not.
     * @return The boolean value .If the passed digit is not same as in array it returns true, or otherwise
     * it returns false.
     */
    private static boolean compareNotSameDigit(int[] array,int compareNumber)
    {
        int index=0;
        while(index<array.length)
        {
            if(array[index]==compareNumber)
            {
                return false;
            }
          index++;
        }
      return true;
    }
    //MethodPRINT:print the array
    /**
     * This method is used to print random generated number array created by a computer.
     * @param actualNumberArray An array is passed which we need to print.
     */
    private static void printArray(int[]actualNumberArray)
    {
        for(int element:actualNumberArray)
        {
            System.out.print(element+"");
        }
        System.out.println("");
    }
    // Method2 :takes guess from the user but in string form
    /**
     * This method is used to ask a number from user that he guessed.
     * @param size It is the size of original random number generated by the computer.
     * @return The Strig value but contain only all digits .
     */
    private static String guessNumberInString (int size)
    {
        System.out.println("please guess number and all digits must be different.Type \"ON\" to know the answer");
        String guessedStringNumber = keyboard.next();
        if(guessedStringNumber.toUpperCase().equals("ON"))
        {
            return guessedStringNumber;
        }
        else
        {
            int index=0;
            // check all the places in guess number, if all are digits.
            while(index<guessedStringNumber.length())
            {
                // It will give chance to user to enter a new number with all digits.
                while (!isDigit(guessedStringNumber.charAt(index)))
                {
                    if(guessedStringNumber.toUpperCase().equals("ON"))
                    {
                        return guessedStringNumber;
                    }
                    System.out.println("Your guessed number do not contain digit at index:" + index + " So please guess only digits");
                    guessedStringNumber = keyboard.next();
                    index = 0;
                }
                index++;
            }
        }
        // if entered number are of right Size.
        while (guessedStringNumber.length() != size) {
            System.out.println("You guessed wrong number with  wrong size.So guess again");
            guessedStringNumber = keyboard.next();
            if(guessedStringNumber.toUpperCase().equals("ON"))
            {
                return guessedStringNumber;
            }
        }
        return guessedStringNumber;
    }
    // Method 3: to check all the digits are different in guess.
    /**
     * This method is used to check that all digits entered by user must be unique.
     * @param guessedStringNumber The number that is guessed by the user is passed as a parameter .
     * @return This method return true if all digits entered by the user are unique or return false
     * if digits repeats in number.
     */
    private static boolean checkrepeation(String guessedStringNumber)
    {
        int charIndex = 0;
        char checkChar = guessedStringNumber.charAt(charIndex);
        while (charIndex < guessedStringNumber.length())
        {
            checkChar = guessedStringNumber.charAt(charIndex);
            int index = charIndex+1 ;
            while (index < guessedStringNumber.length()) {
                if (checkChar == guessedStringNumber.charAt(index))
                {
                    return false;
                }
                index++;
            }
            charIndex++;
        }
        return true;
    }
    // Method 4 : to create an array of guessed number.
    /**
     * This method is used to create an array from the user inputs.
     * @param guessedStringNumber This is passed as a parameter in this method which actually contain
     *                            the guessed digits from the user.
     * @param size The size of the guessed number which is also equals to random generated number.
     * @return A array that contain all unique user guessed digits .
     */
    private static int [] realguessedNumber( String guessedStringNumber, int size){
       int guessedNumber= Integer.parseInt(guessedStringNumber);
        int[] guessedNumberArray = new int[size];
        int lastIndex = guessedNumberArray.length-1;
        int digit = 0;
        // creating an array from guessedNumber
        {
            while (guessedNumber > 0 && lastIndex >=0)
            {
                digit = guessedNumber % 10;
                guessedNumberArray[lastIndex]=digit;
                lastIndex--;
                guessedNumber=guessedNumber/10;
            }
        }
        return guessedNumberArray;
    }
    //Method5: check if guess is right or not
    /**
     * This method is used to compare the guessed numbera and the actual number in an array form.
     * @param guessedNumber This is a number guessed by a user.
     * @param randomNumber This number is randomly generated by a computer.
     * @return It will return the true if guessed Number and random number matches otherwise it return false.
     */
    private static boolean checkGuess(int[] guessedNumber,int[]randomNumber)
    {
        boolean SameAnswer  =Arrays.equals(guessedNumber,randomNumber);
        if(SameAnswer)
        {
          return true;
        }
        return false;
    }
    //Method6: to find the number of right digits in guess.
    /**
     * This method is used to find the number of digits which are right in guess with the random
     * number generated by computer.
     * @param computerGeneratedArray The array that contains the random unique digits which was initially generated
     *                               by the computer.
     * @param guessedNumber  The array of unique guessed numbers by user.
     * @return The number of right digits .
     */
    private static int findNumberOfRightDigit(int [] computerGeneratedArray ,int [] guessedNumber)
    {
        int index=0;
        int correctIndex=0;
        int wrongIndex=0;
        int numberOfRightDigit=0;
        while(index<guessedNumber.length)
        {
            int guessedDigit=guessedNumber[index];
            int computerGeneratedArrayIndex=0;
            while(computerGeneratedArrayIndex<computerGeneratedArray.length)
            {
                int actualDigit=computerGeneratedArray[computerGeneratedArrayIndex];
                if(guessedDigit==actualDigit)
                {
                   numberOfRightDigit++;

                   if(index==computerGeneratedArrayIndex)
                   {
                     correctIndex++;
                   }
                   else
                   {
                       wrongIndex++;
                   }
                }
                computerGeneratedArrayIndex++;
            }
            index++;
        }
     return numberOfRightDigit;
    }
    //Method7: to know right or wrong places
    /**
     * This method is used to find howMany digits are at right place and howMany are at wrong place .
     * @param computerGeneratedArray The array that contains the random unique digits which was initially generated
     *      *                           by the computer.
     * @param guessedNumber The array of unique guessed numbers by user.
     * @return The number of digits which are at right place and which are at wrong place .
     */
    private static int wrongOrRightPlace(int [] computerGeneratedArray ,int [] guessedNumber)
    {
        int index=0;
        int correctIndex=0;
        int wrongIndex=0;
        while(index<guessedNumber.length)
        {
            int guessedDigit=guessedNumber[index];
            int computerGeneratedArrayIndex=0;
            while(computerGeneratedArrayIndex<computerGeneratedArray.length)
            {
                int actualDigit=computerGeneratedArray[computerGeneratedArrayIndex];
                if(guessedDigit==actualDigit)
                {
                    if(index==computerGeneratedArrayIndex)
                    {
                        correctIndex++;
                    }
                    else
                    {
                        wrongIndex++;
                    }
                }
                computerGeneratedArrayIndex++;
            }
            index++;
        }
        System.out.printf("%25d",correctIndex);
        return wrongIndex;
    }
    // Method 8: This is used for creating hint for user.
    /**
     * This method is used for hint part as it will reveal the right digit in number .
     * @param computerGeneratedArray a real number array generated by computer is passed as a parameter
     * @param startIndex This index is used as the place where we want to know the value .Mostly its is 3,6 ,8 atc.
     * @return a digit at a particular index in an array.
     */
    private static int findRightDigit(int [] computerGeneratedArray ,int startIndex)
    {
        int index=startIndex;
        int digit=computerGeneratedArray[index];
        return digit;
    }
}
