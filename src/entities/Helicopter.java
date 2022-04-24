package entities;

public class Helicopter extends Airplane implements IHelicopter{
    private int rotorSpeed;

    public Helicopter() {
        super();
    }

    public Helicopter(String model, String tailNumber, int capacity, int range, int speed, double emptyWeight, double maxWeight, int rotorSpeed) {
        super(model, tailNumber, capacity, range, speed, emptyWeight, maxWeight);
        this.rotorSpeed = rotorSpeed;
    }

    public int getRotorSpeed() {
        return rotorSpeed;
    }

    public void setRotorSpeed(int rotorSpeed) {
        this.rotorSpeed = rotorSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + "rotorSpeed=" + rotorSpeed + '}';
    }
}
