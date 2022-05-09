package objects;

public abstract class Airplane {
    private String model, tailNumber;
    private int capacity, range, speed;
    private double weight;
    private static int airplaneCounter;

    public Airplane() {}

    public Airplane(String model, String tailNumber, int capacity, int range, int speed, double weight) {
        this.model = model;
        this.tailNumber = tailNumber;
        this.capacity = capacity;
        this.range = range;
        this.speed = speed;
        this.weight = weight;
        airplaneCounter++;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Airplane{" +
                "model='" + model + '\'' +
                ", tail number='" + tailNumber + '\'' +
                ", capacity=" + capacity +
                ", range=" + range + " nm" +
                ", speed=" + speed + " kts" +
                ", weight=" + weight +
                ", =" +
                '}';
    }
}
