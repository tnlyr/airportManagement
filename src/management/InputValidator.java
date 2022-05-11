package management;

import java.util.Scanner;

public class InputValidator {
    public int validateInt(Scanner s){ // validates integers, throws an exception if it is not an integer
        int number;
        while (true){
            try{
                number = Integer.parseInt(s.nextLine());
                return number;
            } catch (Exception e) {
                System.out.print("Invalid input! Please try again: ");
            }
        }
    }

    public double validateDouble(Scanner s) { // validates doubles, throws an exception if it is not a double
        double number;
        while (true) {
            try {
                number = Double.parseDouble(s.nextLine());
                return number;
            } catch (Exception e) {
                System.out.print("Invalid input! Please try again: ");
            }
        }
    }

    public String validateString(Scanner s){ // throws exception if string is too long
        String input;
        while (true) {
            input = s.nextLine();
            if (input.length() > 75) {
                System.out.print("The maximum length of the input is 75 characters! Please try again: ");
                continue;
            }
            return input;
        }
    }

    public String validateIata(Scanner s){ // validates IATA codes, throws an exception if it is not a valid IATA code. IATA codes are 3 characters long
        String input;
        while (true) {
            input = s.nextLine();
            if (input.length() != 3) {
                System.out.print("The IATA code must be 3 characters! Please try again: ");
                continue;
            }
            return input;
        }
    }
}
