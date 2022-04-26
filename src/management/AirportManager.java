package management;

import objects.Airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AirportManager {
    private InputValidator inputValidator = new InputValidator();

    public void createAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport name: ");
        String name = inputValidator.validateString(s);
        System.out.print("Enter airport's IATA code: ");
        String iata = inputValidator.validateIata(s);
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

    public void searchForAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport's IATA code: ");
        String iata = s.nextLine();
        for (Airport ap : airports) {
            if (ap.getIata().equalsIgnoreCase(iata)) {
                System.out.println(ap.toString());
                return;
            }
        }
        System.out.println("Airport not found.");
    }

    public void searchForAirport(ArrayList<Airport> airports) {
        ArrayList<Airport> list = new ArrayList<Airport>(airports);
        Collections.sort(list, new Comparator<Airport>() {
            @Override
            public int compare(Airport a1, Airport a2) {
                return a1.getIata().toLowerCase().compareTo(a2.getIata().toLowerCase());
            }
        });
        for (Airport ap : list) {
            System.out.println(ap.toString());
        }
    }
}
