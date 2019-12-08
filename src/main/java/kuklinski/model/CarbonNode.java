package kuklinski.model;

import java.util.Arrays;
import java.util.Vector;

public class CarbonNode {

    private int number;
    private Vector<Double> actualVector;
    private Vector<Double> previousVector;
    private Vector<Double> force;
    private double E;

    private Bond[] bonds;

    public CarbonNode(int number) {
        this.actualVector = new Vector<>(3);
        this.previousVector = new Vector<>(3);
        this.force = new Vector<>(3);
        this.number = number;
        this.bonds = new Bond[3];
    }

    public int getNumber() {
        return number;
    }

    public Vector<Double> getActualVector() {
        return actualVector;
    }

    public void setActualVector(Vector<Double> actualVector) {
        this.actualVector = actualVector;
    }

    public Vector<Double> getPreviousVector() {
        return previousVector;
    }

    public void setPreviousVector(Vector<Double> previousVector) {
        this.previousVector = previousVector;
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

    public void calculateForce() {
        double Fx = 0;
        double Fy = 0;
        double Fz = 0;
        for (Bond bond : bonds) {
            Vector<Double> bondForce = bond.getBondForce();
            Fx += bondForce.get(0);
            Fy += bondForce.get(1);
            Fz += bondForce.get(2);
        }
        this.force.add(0, Fx);
        this.force.add(1, Fy);
        this.force.add(2, Fz);
    }

    @Override
    public String toString() {
        return "CarbonNode{" +
                "number=" + number +
                ", actualVector=" + actualVector +
                ", previousVector=" + previousVector +
                ", force=" + force +
                ", E=" + E +
                '}' +"\n";
    }
}
