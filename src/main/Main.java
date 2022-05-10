package main;

import management.InputManagement;
import objects.Airplane;
import objects.Airport;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static final String MY_NAME = "Lounes";
    public static void main(String[] args) {
	// write your code here
        InputManagement inputManagement = new InputManagement();
        Scanner sc = new Scanner(System.in);

        ArrayList<Airport> listAirports = new ArrayList<>();
        ArrayList<Airplane> listAirplanes = new ArrayList<>();

        while (true) {

            System.out.println("----------= Main Menu =----------");
            System.out.println("1. Create new objects");
            System.out.println("2. Manage airports");
            System.out.println("3. Manage civilian airplanes");
            System.out.println("4. Manage helicopters");
            System.out.println("0. Exit");
            System.out.println("----------------------------------");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

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
                default:
                    final String FILE_NAME = "output.txt";
                    try {
                        File file = new File(FILE_NAME);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileWriter fileWriter = new FileWriter(FILE_NAME);
                        fileWriter.write("List of airports created: " + System.lineSeparator());
                        for (Airport airport : listAirports) {
                            fileWriter.write(airport.toString() + System.lineSeparator());
                        }
                        fileWriter.write("List of airplanes created: " + System.lineSeparator());
                        for (Airplane airplane : listAirplanes) {
                            fileWriter.write(airplane.toString() + System.lineSeparator());
                        }
                        fileWriter.write("Goodbye, " + MY_NAME + "!");
                        fileWriter.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
            }
        }
    }
}
