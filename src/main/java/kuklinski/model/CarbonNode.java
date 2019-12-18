package kuklinski.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CarbonNode {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private int index;
    private List<Double> actualVector;
    private List<Double> previousVector;
    private List<Double> F;
    private double E;

    private boolean toCalculate;
    private Bond[] bonds;

    public CarbonNode(int index) {
        this.index = index;
        this.actualVector = new ArrayList<>(3);
        this.previousVector = new ArrayList<>(3);
        this.F = new ArrayList<>(3);
        this.bonds = new Bond[3];
        fillListsWith0Elements();
    }

    private void fillListsWith0Elements() {
        this.actualVector.add(0.0);
        this.actualVector.add(0.0);
        this.actualVector.add(0.0);
        this.previousVector.add(0.0);
        this.previousVector.add(0.0);
        this.previousVector.add(0.0);
        this.F.add(0.0);
        this.F.add(0.0);
        this.F.add(0.0);
    }

    public void calculateR0() {
        for (Bond bond : bonds) {
            bond.calculateR0(actualVector);
        }
    }

    public void calculateR() {
        for (Bond bond : bonds) {
            bond.calculateR(actualVector);
        }
    }

    public void calculateEnergy() {
        double energy = 0;
        for (Bond bond : bonds) {
            bond.calculateEnergy();
            energy += bond.getEnergy();
        }
        this.E = energy;
    }

    public void calculateForce() {
        double Fx = 0;
        double Fy = 0;
        double Fz = 0;
        for (Bond bond : bonds) {
            bond.calculateForce(actualVector);

            Fx += bond.getFx();
            Fy += bond.getFy();
            Fz += bond.getFz();
        }
        this.F.set(x, Fx);
        this.F.set(y, Fy);
        this.F.set(z, Fz);
    }

    public void calculateNewPosition() {
        double p = 0.1;
        double newX = F.get(x) * p + actualVector.get(x);
        double newY = F.get(y) * p + actualVector.get(y);
        double newZ = F.get(z) * p + actualVector.get(z);
        actualVector.set(x, newX);
        actualVector.set(y, newY);
        actualVector.set(z, newZ);
    }

    public int getIndex() {
        return index;
    }

    public List<Double> getActualVector() {
        return actualVector;
    }

    public void setActualVector(List<Double> actualVector) {
        this.actualVector = actualVector;
    }

    public List<Double> getPreviousVector() {
        return previousVector;
    }

    public void setPreviousVector(List<Double> previousVector) {
        this.previousVector = previousVector;
    }

    public List<Double> getF() {
        return F;
    }

    public void setF(List<Double> f) {
        F = f;
    }

    public double getE() {
        return E;
    }

    public void setE(double e) {
        E = e;
    }

    public Bond[] getBonds() {
        return bonds;
    }

    public void setBonds(Bond[] bonds) {
        this.bonds = bonds;
    }

    public boolean isToCalculate() {
        return toCalculate;
    }

    public void setToCalculate(boolean toCalculate) {
        this.toCalculate = toCalculate;
    }

    @Override
    public String toString() {
        return "CarbonNode{" +
                "index=" + index +
                ", actualVector=" + actualVector +
                ", previousVector=" + previousVector +
                ", F=" + F +
                ", E=" + E +
                '}' + "\n";
    }

    public void setPreviousVectorSameLikeActual() {
        previousVector.set(x, actualVector.get(x));
        previousVector.set(y, actualVector.get(y));
        previousVector.set(z, actualVector.get(z));
    }

    public void setNeighboursToCalculate(boolean b) {
        for (Bond bond : bonds) {
            bond.getCarbonNode().setToCalculate(b);
        }
    }
}
