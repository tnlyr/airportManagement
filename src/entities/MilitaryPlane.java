package entities;

public class MilitaryPlane extends Airplane {
    private int maxAmmo;

    public MilitaryPlane() {
        super();
    }

    public MilitaryPlane(String model, String id, int capacity, int range, int speed, double emptyWeight, double maxWeight, int maxAmmo) {
        super(model, id, capacity, range, speed, emptyWeight, maxWeight);
        this.maxAmmo = maxAmmo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    @Override
    public String toString() {
        return super.toString() + "; Max ammo: " + maxAmmo;
    }
}
