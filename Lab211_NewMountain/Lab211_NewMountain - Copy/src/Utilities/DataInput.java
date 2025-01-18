package Utilities;

import java.util.Scanner;

/**
 *
 * @author SwordLake
 */
public class DataInput {
    //-------------------------------------------------
    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;      
        System.out.print(displayMessage);
        number = getIntegerNumber();
        return number;
    }

    //---------------------------------------------------
    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        //if (s.matches("\\d{1,10}") == false) {
        if(!DataValidation.checkStringWithFormat(s, "\\d{1,10}"))
        {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    //--------------------------------------------------
    public static String getString(String displayMessage) {
        String s;
        System.out.print(displayMessage);
        s = getString();
        return s;
    }

    //---------------------------------------------------
    public static String getString() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        return s;
    }
    //--------------------------------------------------  
    //To do here..........
}
