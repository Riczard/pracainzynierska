package kuklinski;

public class Bond {

    private int atomOneIndex;
    private int atomTwoIndex;
    private double distanceBetween;

    private double Energy;

    public int getAtomOneIndex() {
        return atomOneIndex;
    }

    public void setAtomOneIndex(int atomOneIndex) {
        this.atomOneIndex = atomOneIndex;
    }

    public int getAtomTwoIndex() {
        return atomTwoIndex;
    }

    public void setAtomTwoIndex(int atomTwoIndex) {
        this.atomTwoIndex = atomTwoIndex;
    }

    public double getDistanceBetween() {
        return distanceBetween;
    }

    public void setDistanceBetween(double distanceBetween) {
        this.distanceBetween = distanceBetween;
    }

    public double getEnergy() {
        return Energy;
    }

    public void setEnergy(double energy) {
        Energy = energy;
    }
}
