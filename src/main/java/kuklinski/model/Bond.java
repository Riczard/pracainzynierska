package kuklinski.model;

import java.util.Vector;

public class Bond {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private int atomOneIndex;
    private int atomTwoIndex;
    private double r0;
    private double r;
    private double forceOne;
    private double forceTwo;
    private double energy;

    public void calculateEnergy(double epsilon) {
        this.energy = epsilon*(Math.pow(this.r0/r, 12) - 2*(Math.pow(this.r0/r,6)));
    }

    public void calculateEnergy(Vector<Double> one, Vector<Double> two) {
        calculateForceOne(one, two);
        calculateForceTwo(one, two);
    }

    public void calculateForceOne(Vector<Double> one, Vector<Double> two) {
        double deltaX = one.get(x) - two.get(x);
        double deltaY = one.get(y) - two.get(y);
        double deltaZ = one.get(z) - two.get(z);

        this.forceOne = -1.0 * calculateLennardPotential(r) * (deltaX + deltaY + deltaZ);
    }

    public void calculateForceTwo(Vector<Double> one, Vector<Double> two) {
        double deltaX = two.get(x) - one.get(x);
        double deltaY = two.get(y) - one.get(y);
        double deltaZ = two.get(z) - one.get(z);

        this.forceTwo = -1.0 * calculateLennardPotential(r) * (deltaX + deltaY + deltaZ);
    }

    public double calculateLennardPotential(double r) {
        double sigma = r0()/Math.pow(2,(1.0/6));
        return  6 * Math.pow(sigma, 6) * (Math.pow(r, 6) - 2 * Math.pow(sigma, 6)) / Math.pow(r, 14);
    }


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

    public double r0() {
        return r0;
    }

    public void setR0(double r0) {
        this.r0 = r0;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getForceOne() {
        return forceOne;
    }

    public double getForceTwo() {
        return forceTwo;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Bond{" +
                ", atomOneIndex=" + atomOneIndex +
                ", atomTwoIndex=" + atomTwoIndex +
                ", r0=" + r0 +
                ", forceOne=" + forceOne +
                ", forceTwo=" + forceTwo +
                ", energy=" + energy +
                '}';
    }
}
