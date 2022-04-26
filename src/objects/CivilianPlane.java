package objects;

public class CivilianPlane extends Airplane {
    private String type;
    private int rwySize;

    public CivilianPlane() {
        super();
    }

    public CivilianPlane(String model, String tailNumber, int capacity, int range, int speed, double emptyWeight, double maxWeight, String type, int rwySize) {
        super(model, tailNumber, capacity, range, speed, emptyWeight, maxWeight);
        this.type = type;
        this.rwySize = rwySize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRwySize() {
        return rwySize;
    }

    public void setRwySize(int rwySize) {
        this.rwySize = rwySize;
    }

    @Override
    public String toString() {
        return super.toString() +
                "type=" + type + '\'' +
                ", rwySize=" + rwySize +
                '}';
    }
}
