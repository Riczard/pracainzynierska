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
    private Vector<Double> bondForce;
    private double energy;


    public Bond() {
        this.bondForce = new Vector<>(3);
    }

    public void calculateEnergy(double epsilon) {
        this.energy = epsilon * (Math.pow(this.r0 / r, 12) - 2 * (Math.pow(this.r0 / r, 6)));
    }

    public void calculateForce(Vector<Double> positionOne, Vector<Double> positionTwo) {
        double Fx = calculateLennardPotential() * (positionOne.get(x) - positionTwo.get(x));
        double Fy = calculateLennardPotential() * (positionOne.get(y) - positionTwo.get(y));
        double Fz = calculateLennardPotential() * (positionOne.get(z) - positionTwo.get(z));

        this.bondForce.add(x, Fx);
        this.bondForce.add(y, Fy);
        this.bondForce.add(z, Fz);
    }

    private double calculateLennardPotential() {
        double sigma = r0() * Math.pow(2, (-1 / 6.0));
        return 3 * Math.pow(r0, 6) / Math.pow(r, 14) * (-Math.pow(r0, 6) + Math.pow(r, 6));
//        return 6* Math.pow(sigma, 6) / Math.pow(r, 14) * (-2 * Math.pow(sigma, 6) + Math.pow(r, 6));
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

    public void setR(double r) {
        this.r = r;
    }

    public Vector<Double> getBondForce() {
        return bondForce;
    }

    @Override
    public String toString() {
        return "Bond{" +
                ", atomOneIndex=" + atomOneIndex +
                ", atomTwoIndex=" + atomTwoIndex +
                ", r0=" + r0 +
                ", energy=" + energy +
                '}';
    }
}
