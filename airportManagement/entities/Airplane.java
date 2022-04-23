package entities;

public abstract class Airplane {
    private String model, id;
    private int capacity, range, speed;
    private double emptyWeight, maxWeight;

    public Airplane() {}

    public Airplane(String model, String id, int capacity, int range, int speed, double emptyWeight, double maxWeight) {
        this.model = model;
        this.id = id;
        this.capacity = capacity;
        this.range = range;
        this.speed = speed;
        this.emptyWeight = emptyWeight;
        this.maxWeight = maxWeight;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "model='" + model + '\'' +
                ", id='" + id + '\'' +
                ", capacity=" + capacity +
                ", range=" + range +
                ", speed=" + speed +
                ", emptyWeight=" + emptyWeight +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
