package kuklinski.model;

public class Bond {

    private int atomOneIndex;
    private int atomTwoIndex;
    private double r0;

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

    public double getR0() {
        return r0;
    }

    public void setR0(double r0) {
        this.r0 = r0;
    }

    public double getEnergy() {
        return Energy;
    }

    public void setEnergy(double energy) {
        Energy = energy;
    }

    public void calculateEnergy(double epsilon) {
        double r= r0;
        double energy = epsilon*(Math.pow(this.r0/r, 12) - 2*(Math.pow(this.r0/r,6)));
        setEnergy(energy);
    }

    @Override
    public String toString() {
        return "Bond{" +
                "atomOneIndex=" + atomOneIndex +
                ", atomTwoIndex=" + atomTwoIndex +
                ", distanceBetween=" + r0 +
                ", Energy=" + Energy +
                '}';
    }
}
