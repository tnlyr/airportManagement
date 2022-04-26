package management;

import objects.Airplane;
import objects.Airport;
import objects.Helicopter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class HeliManager {
    private InputValidator inputValidator = new InputValidator();
    private AirportManager apMgr = new AirportManager();

    public void createHeli(Scanner s, ArrayList<Airplane> airplanes) {
        System.out.print("Enter helicopter tail number: ");
        String tailNumber = inputTailNumber(s, airplanes);
        System.out.print("Enter helicopter model: ");
        String model = inputValidator.validateString(s);
        System.out.print("Enter helicopter capacity: ");
        int capacity = inputValidator.validateInt(s);
        System.out.print("Enter helicopter range (in NM): ");
        int range = inputValidator.validateInt(s);
        System.out.print("Enter helicopter cruisng speed (in kts): ");
        int speed = inputValidator.validateInt(s);
        System.out.print("Enter helicopter empty weight (in kg): ");
        double emptyWeight = inputValidator.validateDouble(s);
        System.out.print("Enter helicopter max weight (in kg): ");
        double maxWeight = inputValidator.validateDouble(s);
        System.out.print("Enter helicopter rotor speed (in rpm): ");
        int rotorSpeed = inputValidator.validateInt(s);
        Helicopter heli = new Helicopter(model, tailNumber, capacity, range, speed, emptyWeight, maxWeight, rotorSpeed);
        airplanes.add(heli);

    }

    public void add(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> unmatched = listUnmatched(airplanes, airports);
        ArrayList<Helicopter> helicopters = new ArrayList<Helicopter>();
        for (Airplane airplane : airplanes) {
            helicopters.add((Helicopter) airplane); // cast to helicopter
        }
        if (helicopters.size() == 0) {
            System.out.println("No helicopters to add");
            return;
        }
        System.out.println("Unmatched helicopters: ");
        for (Airplane airplane : unmatched) {
            System.out.println(airplane.toString());
        }

        while (true) {
            System.out.print("How many helicopters do you want to add? ");
            int numToAdd = inputValidator.validateInt(s);
            if (numToAdd > unmatched.size() || numToAdd < 0) {
                System.out.println("Invalid input. Please enter a number between 0 and " + helicopters.size());
                continue;
            }
            if (numToAdd == 0) {
                System.out.printf("No helicopters added.\n");
                return;
            }
            ArrayList<String> list = new ArrayList<String>();
            while (numToAdd > 0) {
                System.out.print("Enter helicopter tail number: ");
                String tailNumber = inputValidator.validateString(s);
                if (list.contains(tailNumber)) {
                    System.out.println("Helicopter already added. Please enter a different tail number.");
                    continue;
                }
                if (isExisting(airplanes, tailNumber)) {
                    if (isExisting(unmatched, tailNumber)) {
                        list.add(tailNumber);
                        numToAdd--;
                    }
                    else {
                        System.out.println("Helicopter already assigned to an airport. Please enter a different tail number.");
                    }
                }
                else {
                    System.out.println("Helicopter does not exist. Please enter a different tail number.");
                }
            }
            addToAirport(s, list, helicopters, airports);
            break;
        }
    }

    private void addToAirport(Scanner s, ArrayList<String> list, ArrayList<Helicopter> helicopters, ArrayList<Airport> airports) {
        System.out.println("Enter airport IATA code: ");
        String iata = inputValidator.validateIata(s);
        if (apMgr.airportExists(iata, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iata)) {
                    for (String tailNumber : list) {
                        for (Helicopter helicopter : helicopters) {
                            if (helicopter.getTailNumber().equalsIgnoreCase(tailNumber)) {
                                if (airport.getMaxHelicopter() > airport.getHelicopterList().size()) {
                                    airport.getHelicopterList().add(tailNumber);
                                }
                                else {
                                    System.out.println(iata + " is full. Please enter a different airport.");
                                    return;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        else {
            System.out.println("Airport does not exist. Please enter a different airport.");
        }
    }

    public void removefromAirport(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Enter airport IATA code: ");
        String iata = inputValidator.validateIata(s);
        if (apMgr.airportExists(iata, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iata)) {
                    if (airport.getHelicopterList().isEmpty()) {
                        System.out.println("No helicopters assigned to " + iata + ".");
                        return;
                    }
                    System.out.println("All helicopters stationed at " + iata + ":");
                    for (String tailNumber : airport.getHelicopterList()) {
                        for (Airplane airplane : airplanes) {
                            if (airplane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                                System.out.println(airplane.toString());
                                break;
                            }
                        }
                    }
                    while (true) {
                        System.out.print("Enter number of helicopter to remove: ");
                        int num = inputValidator.validateInt(s);
                        if (num > airport.getHelicopterList().size() || num < 0) {
                            System.out.println("Invalid number. Please enter a different number.");
                            continue;
                        }
                        else if (num == 0) {
                            System.out.println("No helicopters removed.");
                            return;
                        }
                        while (num > 0) {
                            System.out.print("Enter tail number of helicopter to remove: ");
                            String tailNumber = s.nextLine();
                            if (airport.getHelicopterList().contains(tailNumber)) {
                                if(!list.contains(tailNumber)) {
                                    list.add(tailNumber);
                                    num--;
                                }
                                else {
                                    System.out.println("Helicopter already removed. Please enter a different helicopter.");
                                }
                            } else {
                                System.out.println("That helicopter is not at " + iata + ".");
                            }
                        }
                        airport.getHelicopterList().removeAll(list);
                        break;
                    }
                    break;
                }
            }
        }
        else {
            System.out.println("Airport does not exist. Please enter a different airport.");
        }
    }

    private ArrayList<Airplane> listUnmatched(ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> list = new ArrayList<Airplane>();
        for (Airplane airplane : airplanes) {
            if (airplane instanceof Helicopter) {
                list.add(airplane);
            }
        }
        list.removeAll(listMatched(airplanes, airports));
        return list;
    }

    private ArrayList<Airplane> listMatched(ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        ArrayList<Airplane> list = new ArrayList<Airplane>();
        for (Airport airport : airports) {
            for (String tailNumber : airport.getHelicopterList()) {
                for (Airplane airplane : airplanes) {
                    if (airplane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                        list.add(airplane);
                        break;
                    }
                }
            }
        }
        return list;
    }

    private String inputTailNumber(Scanner s, ArrayList<Airplane> airplanes) {
        String planeId = "";
        while (true) {
            System.out.println("Enter plane ID: ");
            planeId = s.nextLine();
            if (isExisting(airplanes, planeId)) {
                System.out.println("That plane ID already exists.");
            }
            else {
                return planeId;
            }
        }
    }

    private boolean isExisting(ArrayList<Airplane> airplanes, String tailNumber) {
        for (Airplane airplane : airplanes) {
            if (airplane.getTailNumber().equalsIgnoreCase(tailNumber)) {
                return true;
            }
        }
        return false;
    }

    public void displayById(Scanner s, ArrayList<Airplane> airplanes, ArrayList<Airport> airports) {
        System.out.println("Enter airport IATA code: ");
        String iata = inputValidator.validateIata(s);
        if (apMgr.airportExists(iata, airports)) {
            for (Airport airport : airports) {
                if (airport.getIata().equalsIgnoreCase(iata)) {
                    for (Airplane airplane : airplanes) {
                        if (airport.getHelicopterList().contains(airplane.getTailNumber())) {
                            System.out.println(airplane.toString());
                        }
                    }
                }
            }
        }
        else {
            System.out.println("Airport does not exist.");
        }
    }

    public void listAllHelicopters(ArrayList<Airplane> airplanes) {
        for (Airplane airplane : airplanes) {
            if (airplane instanceof Helicopter) {
                System.out.println(airplane.toString());
            }
        }
    }
}


