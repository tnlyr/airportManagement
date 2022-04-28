package main;

import management.InputManagement;
import objects.Airplane;
import objects.Airport;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
	// write your code here
        InputManagement inputManagement = new InputManagement();
        Scanner sc = new Scanner(System.in);

        ArrayList<Airport> listAirports = new ArrayList<>();
        ArrayList<Airplane> listAirplanes = new ArrayList<>();

        System.out.println("----------= Main Menu =----------");
        System.out.println("1. Create new objects");
        System.out.println("2. Manage airports");
        System.out.println("3. Manage civilian airplanes");
        System.out.println("4. Manage helicopters");
        System.out.println("Exit");
        System.out.println("----------------------------------");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());
        while (true) {
            switch (choice) {
                case 1:
                    inputManagement.inputData(sc, listAirports, listAirplanes);
                    break;
                case 2:
                    inputManagement.airportManagementInput(sc, listAirports);
                    break;
                case 3:
                    inputManagement.civilianManagementInput(sc, listAirplanes, listAirports);
                    break;
                case 4:
                    inputManagement.heliManagementInput(sc, listAirplanes, listAirports);
                    break;
                case 5:
                    //System.out.println(listAirports);
                    //System.out.println(listAirplanes);
                    //main(args);
                    break;
                default:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    return;
            }
        }
    }
}
