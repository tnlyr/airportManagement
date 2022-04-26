package main;

import management.InputManagement;
import management.InputValidator;
import objects.Airplane;
import objects.Airport;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        InputManagement inputManagement = new InputManagement();
        InputValidator inputValidator = new InputValidator();
        Scanner sc = new Scanner(System.in);

        ArrayList<Airport> airports = new ArrayList<>();
        ArrayList<Airplane> airplanes = new ArrayList<>();

        System.out.println("----------= Main Menu =----------");
        System.out.println("1. Create new objects");
        System.out.println("2. Manage airports");
        System.out.println("3. Manage civilian airplanes");
        System.out.println("4. Manage helicopters");
        System.out.println("Exit");
        System.out.println("----------------------------------");
        System.out.print("Enter your choice: ");
        int choice = inputValidator.validateInt(sc);
        while (true) {
            switch (choice) {
                case 1:
                    inputManagement.inputData(sc, airports, airplanes);
                    break;
                case 2:
                    inputManagement.airportManagementInput(sc, airports);
                    break;
                case 3:
                    inputManagement.civilianManagementInput(sc, airplanes, airports);
                    break;
                case 4:
                    inputManagement.heliManagementInput(sc, airplanes, airports);
                    break;
                default:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }
}
