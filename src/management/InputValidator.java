package management;

import java.util.Scanner;

public class InputValidator {
    public int validateInt(Scanner s){
        int number;
        while (true){
            try{
                number = Integer.parseInt(s.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again: ");
            }
        }
    }

    public double validateDouble(Scanner s) {
        double number;
        while (true) {
            try {
                number = Double.parseDouble(s.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again: ");
            }
        }
    }

    public String validateString(Scanner s){
        String input;
        while (true) {
            input = s.nextLine();
            if (input.length() > 50) {
                System.out.println("The maximum length of the input is 50 characters! Please try again: ");
                continue;
            }
            return input;
        }
    }

}
