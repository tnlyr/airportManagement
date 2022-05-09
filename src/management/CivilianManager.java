package management;

import objects.Airplane;
import objects.CivilianPlane;
import objects.Airport;

import java.util.ArrayList;
import java.util.Scanner;

public class CivilianManager {
    private InputValidator inputValidator = new InputValidator();
    private AirportManager apMgr = new AirportManager();

    public void createPlane(Scanner s, ArrayList<Airplane> airplanes) {
        System.out.print("Enter plane tail number: ");
        String tailNumber = inputTailNumber(s, airplanes);
        System.out.print("Enter plane model: ");
        String model = inputValidator.validateString(s);
        System.out.print("Enter plane capacity: ");
        int capacity = inputValidator.validateInt(s);
        System.out.print("Enter plane range (in NM): ");
        int range = inputValidator.validateInt(s);
        System.out.print("Enter plane cruise speed (in kts): ");
        int speed = inputValidator.validateInt(s);
        System.out.print("Enter plane weight (in kg): ");
        double weight = inputValidator.validateDouble(s);
        System.out.print("Enter plane type (PAS for passenger, CAG for Cargo): ");
        String type = inputPlaneType(s);
        System.out.print("Enter minimum runway length needed (in m): ");
        int minRunwayLength = inputValidator.validateInt(s);
        CivilianPlane cvPlane = new CivilianPlane(model, tailNumber, capacity, range, speed, weight, type, minRunwayLength);
        airplanes.add(cvPlane);
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
                System.out.println("Invalid input. Please enter a number between 0 and " + unmatched.size());
                continue;
            }
            if (numToAdd == 0) {
                System.out.println("No airplanes added.");
                return;
            }
            ArrayList<String> list = new ArrayList<String>();
            while (numToAdd > 0) {
                System.out.print("Enter tail number of airplane to add: ");
                String tailNumber = s.nextLine();
                if(list.contains(tailNumber)) {
                    System.out.println("You already added that airplane.");
                    continue;
                }
                if (isExisting(airplanes, tailNumber)) {
                    if(isExisting(unmatched, tailNumber)) {
                        list.add(tailNumber);
                        numToAdd--;
                    }
                    else {
                        System.out.println("That airplane is already at an airport.");
                    }
                }
                else {
                    System.out.println("That airplane does not exist.");
                }
            }
            addToAirport(s, list, cvPlanes, airports);
            break;
        }
    }

    public void addToAirport(Scanner s, ArrayList<String> planeId, ArrayList<CivilianPlane> airplanes, ArrayList<Airport> airports) {
        System.out.print("Enter IATA code of airport to add to: ");
        String iata = inputValidator.validateIata(s);
        if (apMgr.airportExists(iata, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iata)) {
                    for (String tailNumber : planeId) {
                        for (CivilianPlane plane : airplanes) {
                            if (plane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                                if (airport.getMaxAircraft() > airport.getAircraftList().size()) {
                                    if (airport.getRwylength() >= plane.getRwySize()) {
                                        airport.getAircraftList().add(tailNumber);
                                    }
                                    else {
                                        System.out.println(iata + " does not have enough runway length.");
                                    }
                                }
                                else {
                                    System.out.println(iata + " does not have enough space for more aircraft.");
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        else {
            System.out.println("That airport does not exist.");
        }
    }

    public void removefromAirport(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<String> toRemove = new ArrayList<String>();
        System.out.print("Enter airport IATA code: ");
        String iataCode = s.nextLine();
        if (apMgr.airportExists(iataCode, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iataCode)) {
                    if (airport.getAircraftList().isEmpty()) {
                        System.out.println(iataCode + " has no airplanes to remove.");
                        return;
                    }
                    System.out.println("Airplanes at " + iataCode + ":");
                    for (String tailNumber : airport.getAircraftList()) {
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
                    if (numToRemove > airport.getAircraftList().size() || numToRemove <= 0) {
                        System.out.println("You can't remove more airplanes than there are in " + iataCode + ".");
                        continue;
                    }
                    while (numToRemove > 0) {
                        System.out.print("Enter tail number of airplane to remove: ");
                        String tailNumber = s.nextLine();
                        if (airport.getAircraftList().contains(tailNumber)) {
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
                    airport.getAircraftList().removeAll(toRemove);
                    break;
                }
                break;
            }
        } else {
            System.out.println("That airport does not exist.");
        }
    }

    public ArrayList<Airplane> listMatched(ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> matched = new ArrayList<Airplane>();
        for (Airport airport : airports) {
            for (String tailNumber : airport.getAircraftList()) {
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

    public boolean isExisting(ArrayList<Airplane> airplanes, String tailNumber) {
        for (Airplane airplane : airplanes) {
            if (airplane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                return true;
            }
        }
        return false;
    }

    public void displayById(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        System.out.println("Enter airport IATA code to display aircraft at: ");
        String iata = s.nextLine();
        if (apMgr.airportExists(iata, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iata)) {
                    for (Airplane airplane : airplanes) {
                        if (airport.getAircraftList().contains(airplane.getTailNumber())) {
                            System.out.println(airplane.toString());
                        }
                    }
                }
            }
        }
        else {
            System.out.println("That airport does not exist.");
        }
    }

    public String inputPlaneType(Scanner s) {
        String planeType;
        while (true) {
            planeType = s.nextLine();
            if (!planeType.matches("(PAS|CAG|pas|cag)")) {
                System.out.print("Enter plane type (PAS (Passenger) or CAG (Cargo)): ");
                continue;
            }
            return planeType;
        }
    }

    public String inputTailNumber(Scanner s, ArrayList<Airplane> airplanes) {
        String planeId = "";
        while (true) {
            planeId = s.nextLine();
            if (isExisting(airplanes, planeId)) {
                System.out.println("That plane ID already exists.");
            }
            else {
                return planeId;
            }
        }
    }

    public void listAllAirplanes(ArrayList<Airplane> airplanes) {
        for (Airplane airplane : airplanes) {
            if (airplane instanceof CivilianPlane) {
                System.out.println(airplane.toString());
            }
        }
    }
}
