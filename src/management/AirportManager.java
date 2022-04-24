package management;

import entities.Airport;

import java.util.ArrayList;
import java.util.Scanner;

public class AirportManager {
    private InputValidator inputValidator = new InputValidator();

    public void createAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport name: ");
        String name = s.nextLine();
        System.out.print("Enter airport's IATA code: ");
        String iata = s.nextLine();
        System.out.print("Enter airport's runway length: ");
        int runwayLength = inputValidator.validateInt(s);
        System.out.print("Enter airport's airplane capacity: ");
        int airplaneCapacity = inputValidator.validateInt(s);
        System.out.print("Enter airport's helicopeter capacity: ");
        int helicopterCapacity = inputValidator.validateInt(s);
        Airport ap = new Airport(name, iata, runwayLength, airplaneCapacity, helicopterCapacity, new ArrayList<String>(), new ArrayList<String>());
        airports.add(ap);
    }

    public void deleteAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport's IATA code: ");
        String iata = s.nextLine();
        for (Airport ap : airports) {
            if (ap.getIata().equalsIgnoreCase(iata)) {
                airports.remove(ap);
                System.out.println("Airport successfully deleted.");
                return;
            }
        }
        System.out.println("Airport not found.");
    }

    public void displayAllAirports(ArrayList<Airport> airports) {
        for (Airport ap : airports) {
            System.out.println(ap);
        }
    }

    public void displaySpecifiedAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport's IATA code: ");
        String iata = s.nextLine();
        for (Airport ap : airports) {
            if (ap.getIata().equalsIgnoreCase(iata)) {
                System.out.println(ap);
                return;
            }
        }
        System.out.println("Airport not found.");
    }

    public boolean airportExists(String iata, ArrayList<Airport> airports) {
        for (Airport ap : airports) {
            if (ap.getIata().equalsIgnoreCase(iata)) {
                return true;
            }
        }
        return false;
    }
}
