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
        System.out.print("Enter airport's runway length (in ft): ");
        int runwayLength = inputValidator.validateInt(s);
        System.out.print("Enter airport's airplane capacity: ");
        int airplaneCapacity = inputValidator.validateInt(s);
        System.out.print("Enter airport's helicopeter capacity: ");
        int helicopterCapacity = inputValidator.validateInt(s);
        Airport airport = new Airport(name, iata, runwayLength, airplaneCapacity, helicopterCapacity, new ArrayList<String>(), new ArrayList<String>());
        airports.add(airport);
    }

    public void deleteAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport's IATA code: ");
        String iata = s.nextLine();
        for (int i = 0; i < airports.size(); i++) {
            if (airports.get(i).getIata().equalsIgnoreCase(iata)) {
                airports.remove(i);
                System.out.println("Airport deleted.");
                break;
            }
        }
        if (!airportExists(iata, airports)) {
            System.out.println("Airport not found.");
        }
    }

    public void displayAllAirports(ArrayList<Airport> airports) {
        for (Airport ap : airports) {
            System.out.println(ap.toString());
        }
    }

    public void displaySpecifiedAirport(Scanner s, ArrayList<Airport> airports) {
        System.out.print("Enter airport's IATA code: ");
        String iata = s.nextLine();
        for (Airport ap : airports) {
            if (ap.getIata().equalsIgnoreCase(iata)) {
                System.out.println(ap.toString());
                return;
            }
        }
        if (!airportExists(iata, airports)) {
            System.out.println("Airport not found.");
        }
    }

    public void sortAlphabetically(ArrayList<Airport> airports) {
      ArrayList<Airport> temp = new ArrayList<Airport>(airports);
        Collections.sort(temp, new Comparator<Airport>() {
            @Override
            public int compare(Airport o1, Airport o2) {
                return o1.getIata().toLowerCase().compareTo(o2.getIata().toLowerCase());
            }
        });
        for (Airport ap : temp) {
            System.out.println(ap.toString());
        }
    }

    public void sortByRunwayLength(ArrayList<Airport> airports) {
      ArrayList<Airport> temp = new ArrayList<Airport>(airports);
        Collections.sort(temp, new Comparator<Airport>() {
            @Override
            public int compare(Airport o1, Airport o2) {
                return o1.getRwylength() - o2.getRwylength();
            }
        });
        for (Airport ap : temp) {
            System.out.println(ap.toString());
        }
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
