package entities;

import java.util.ArrayList;

public class Airport {
    private String name, iata;
    private int rwylength, maxAircraft, maxHelicopter;
    private ArrayList<String> aircarftList, helicopterList;

    public Airport() {
    }
    public Airport(String name, String iata, int rwylength, int maxAircraft, int maxHelicopter, ArrayList<String> aircarftList, ArrayList<String> helicopterList) {
        this.name = name;
        this.iata = iata;
        this.rwylength = rwylength;
        this.maxAircraft = maxAircraft;
        this.maxHelicopter = maxHelicopter;
        this.aircarftList = aircarftList;
        this.helicopterList = helicopterList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public int getRwylength() {
        return rwylength;
    }

    public void setRwylength(int rwylength) {
        this.rwylength = rwylength;
    }

    public int getMaxAircraft() {
        return maxAircraft;
    }

    public void setMaxAircraft(int maxAircraft) {
        this.maxAircraft = maxAircraft;
    }

    public int getMaxHelicopter() {
        return maxHelicopter;
    }

    public void setMaxHelicopter(int maxHelicopter) {
        this.maxHelicopter = maxHelicopter;
    }

    public ArrayList<String> getAircraftList() {
        return aircarftList;
    }

    public void setAircraftList(ArrayList<String> aircarftList) {
        this.aircarftList = aircarftList;
    }

    public ArrayList<String> getHelicopterList() {
        return helicopterList;
    }

    public void setHelicopterList(ArrayList<String> helicopterList) {
        this.helicopterList = helicopterList;
    }

    public String toString() {
        return "Airport{" + "name=" + name + ", IATA=" + iata + ", rwylength=" + rwylength + ", maxAircraft=" + maxAircraft + ", maxHelicopter=" + maxHelicopter + ", aircarftList=" + aircarftList + ", helicopterList=" + helicopterList + '}';
    }
}
