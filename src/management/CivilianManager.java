package management;

import entities.Airplane;
import entities.CivilianPlane;
import entities.Airport;

import java.util.ArrayList;
import java.util.Scanner;

public class CivilianManager {
    private InputValidator inputValidator = new InputValidator();
    private AirportManager apMgr = new AirportManager();

    public void createPlane(Scanner s, ArrayList<Airplane> airplanes) {
        System.out.print("Enter plane tail number: ");
        String tailNumber = s.nextLine();
        System.out.print("Enter plane model: ");
        String model = inputValidator.validateString(s);
        System.out.print("Enter plane capacity: ");
        int capacity = inputValidator.validateInt(s);
        System.out.print("Enter plane range (in nm): ");
        int range = inputValidator.validateInt(s);
        System.out.print("Enter plane cruise speed (in kts): ");
        int speed = inputValidator.validateInt(s);
        System.out.print("Enter plane empty weight (in kg): ");
        double emptyWeight = inputValidator.validateDouble(s);
        System.out.print("Enter plane max weight (in kg): ");
        double maxWeight = inputValidator.validateDouble(s);
        System.out.println("Enter plane type (1 for cargo, 2 for passenger): ");
        int type = inputValidator.validateInt(s);
        System.out.println("Enter minimum runway length needed (in m): ");
        int minRunwayLength = inputValidator.validateInt(s);
        CivilianPlane cvPlane = new CivilianPlane(tailNumber, model, capacity, range, speed, emptyWeight, maxWeight, type, minRunwayLength);
        airplanes.add(cvPlane);
    }

    public void removefromAirport(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<String> toRemove = new ArrayList<String>();
        System.out.print("Enter airport IATA code: ");
        String iataCode = s.nextLine();
        if (apMgr.airportExists(iataCode, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iataCode)) {
                    if (airport.getAircarftList().isEmpty()) {
                        System.out.println(iataCode + " has no airplanes to remove.");
                        return;
                    }
                    System.out.println("Airplanes at " + iataCode + ":");
                    for (String tailNumber : airport.getAircarftList()) {
                        for (Airplane airplane : airplanes) {
                            if (airplane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                                System.out.println(airplane.toString());
                                break;
                            }
                        }
                    }
                }
                while (true) {
                    System.out.println("How many airplanes do you want to remove from " + iataCode + "? ");
                    int numToRemove = inputValidator.validateInt(s);
                    if (numToRemove > airport.getAircarftList().size() || numToRemove <= 0) {
                        System.out.println("You can't remove more airplanes than there are in " + iataCode + ".");
                        continue;
                    }
                    while (numToRemove > 0) {
                        System.out.print("Enter tail number of airplane to remove: ");
                        String tailNumber = s.nextLine();
                        if (airport.getAircarftList().contains(tailNumber)) {
                            if (!toRemove.contains(tailNumber)) {
                                toRemove.add(tailNumber);
                                numToRemove--;
                            } else {
                                System.out.println("You already removed that airplane.");
                            }
                        } else {
                            System.out.println("That airplane is not at " + iataCode + ".");
                        }
                    }
                    airport.getAircarftList().removeAll(toRemove);
                    break;
                }
                break;
            }
        } else {
            System.out.println("That airport does not exist.");
        }
    }

    public void add(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> unmatched = listUnmatched(airplanes, airports);
        ArrayList<CivilianPlane> cvPlanes = new ArrayList<CivilianPlane>();
        for (Airplane airplane : unmatched) {
            unmatched.add((CivilianPlane) airplane);
        }
        if (cvPlanes.isEmpty()) {
            System.out.println("There are no unmatched airplanes.");
            return;
        }
        System.out.println("Unmatched airplanes:");
        for (Airplane airplane : unmatched) {
            System.out.println(airplane.toString());
        }

        while (true) {
            System.out.print("How many airplanes do you want to add? ");
            int numToAdd = inputValidator.validateInt(s);
            if (numToAdd > unmatched.size() || numToAdd < 0) {
                System.out.println("Invalid input.");
                continue;
            }
            if (numToAdd == 0) {
                System.out.println("No airplanes added.");
                break;
            }
            ArrayList<Airplane> toAdd = new ArrayList<Airplane>();
            while (numToAdd > 0) {
                System.out.print("Enter tail number of airplane to add: ");

            }

        }
    }

    public ArrayList<Airplane> listMatched(ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> matched = new ArrayList<Airplane>();
        for (Airport airport : airports) {
            for (String tailNumber : airport.getAircarftList()) {
                for (Airplane airplane : airplanes) {
                    if (airplane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                        matched.add(airplane);
                        break;
                    }
                }
            }
        }
        return matched;
    }

    public ArrayList<Airplane> listUnmatched(ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> unmatched = new ArrayList<Airplane>();
        for (Airplane airplane : airplanes) {
            if (airplane instanceof CivilianPlane) {
                unmatched.add(airplane);
            }
        }
        unmatched.removeAll(listMatched(airplanes, airports));
        return unmatched;
    }







}
