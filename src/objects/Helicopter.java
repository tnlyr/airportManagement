package objects;

public class Helicopter extends Airplane implements IHelicopter{
    private int rotorSpeed;
    private static int helicopterCount = 0;

    public Helicopter() {
        super();
    }

    public Helicopter(String model, String tailNumber, int capacity, int range, int speed, double weight, int rotorSpeed) {
        super(model, tailNumber, capacity, range, speed, weight);
        this.rotorSpeed = rotorSpeed;
        helicopterCount++;
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
