package management;

import main.Main;
import management.AirportManager;
import management.CivilianManager;
import management.HeliManager;
import management.InputValidator;
import objects.Airplane;
import objects.Airport;

import java.util.ArrayList;
import java.util.Scanner;

public class InputManagement {
    private InputValidator inputValidator = new InputValidator();
    private AirportManager apMgr = new AirportManager();
    private HeliManager heliMgr = new HeliManager();
    private CivilianManager civMgr = new CivilianManager();

    public void inputData(Scanner s, ArrayList<Airport> airports, ArrayList<Airplane> airplanes) {
        while (true) {
            System.out.println("----------= Create new object =----------");
            System.out.println("[1] Airport");
            System.out.println("[2] Civilian plane");
            System.out.println("[3] Helicopter");
            System.out.println("[0] Back to main menu");
            System.out.print("Your choice: ");
            int choice = inputValidator.validateInt(s);
            switch (choice) {
                case 1:
                    System.out.println("----------= Create new airport =----------");
                    apMgr.createAirport(s, airports);
                    break;
                case 2:
                    System.out.println("----------= Create new civilian plane =----------");
                    civMgr.createPlane(s, airplanes);
                    break;
                case 3:
                    System.out.println("----------= Create new helicopter =----------");
                    heliMgr.createHeli(s, airplanes);
                    break;
                default:
                    return;
            }
        }
    }

    public void airportManagementInput(Scanner s, ArrayList<Airport> airports) {
        while (true) {
            System.out.println("----------= Airport management =----------");
            System.out.println("[1] Delete airport");
            System.out.println("[2] Show all airports, sorted by IATA code");
            System.out.println("[3] Show all airports, sorted by runway length");
            System.out.println("[4] Show selected airport information");
            System.out.println("[0] Back to main menu");
            System.out.print("Your choice: ");
            int choice = inputValidator.validateInt(s);
            switch (choice) {
                case 1:
                    System.out.println("----------= Delete airport =----------");
                    apMgr.deleteAirport(s, airports);
                    break;
                case 2:
                    System.out.println("----------= Show all airports, sorted by IATA =----------");
                    apMgr.sortAlphabetically(airports);
                    break;
                case 3:
                    System.out.println("----------= Show all airports, sorted by runway length =----------");
                    apMgr.sortByRunwayLength(airports);
                    break;
                case 4:
                    System.out.println("----------= Show selected airport information =----------");
                    apMgr.displaySpecifiedAirport(s, airports);
                    break;
                default:
                    return;
            }

        }
    }

    public void civilianManagementInput(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        while (true) {
            System.out.println("----------= Civilian plane management =----------");
            System.out.println("[1] Add airplane to airport");
            System.out.println("[2] Remove airplane from airport");
            System.out.println("[3] Show all airplanes at selected airport");
            System.out.println("[0] Back to main menu");
            System.out.print("Your choice: ");
            int choice = inputValidator.validateInt(s);
            switch (choice) {
                case 1:
                    System.out.println("----------= Add airplane to airport =----------");
                    civMgr.add(s, airplanes, airports);
                    break;
                case 2:
                    System.out.println("----------= Remove airplane from airport =----------");
                    civMgr.removefromAirport(s, airplanes, airports);
                    break;
                case 3:
                    System.out.println("----------= Show all airplanes =----------");
                    civMgr.displayById(s, airplanes, airports);
                    break;
                default:
                    return;
            }
        }
    }

    public void heliManagementInput(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        while (true) {
            System.out.println("----------= Helicopter management =----------");
            System.out.println("[1] Add helicopter to airport");
            System.out.println("[2] Remove helicopter from airport");
            System.out.println("[3] Show all helicopters at selected airport");
            System.out.println("[0] Back to main menu");
            System.out.print("Your choice: ");
            int choice = inputValidator.validateInt(s);
            switch (choice) {
                case 1:
                    System.out.println("----------= Add helicopter to airport =----------");
                    heliMgr.add(s, airplanes, airports);
                    break;
                case 2:
                    System.out.println("----------= Remove helicopter from airport =----------");
                    heliMgr.removefromAirport(s, airplanes, airports);
                    break;
                case 3:
                    System.out.println("----------= Show all helicopters =----------");
                    heliMgr.displayById(s, airplanes, airports);
                    break;
                default:
                    return;
            }
        }
    }
}

