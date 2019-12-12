package kuklinski.model;

import java.util.Vector;

public class CarbonNode {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private int index;
    private Vector<Double> actualVector;
    private Vector<Double> previousVector;
    private Vector<Double> F;
    private double E;

    private Bond[] bonds;

    public CarbonNode(int index) {
        this.index = index;
        this.actualVector = new Vector<>(3);
        this.previousVector = new Vector<>(3);
        this.F = new Vector<>(3);
        create0Vector();
        this.bonds = new Bond[3];
    }

    private void create0Vector() {
        this.F.add(0.0);
        this.F.add(0.0);
        this.F.add(0.0);
    }

    public void calculateR0() {
        for(Bond bond : bonds) {
            bond.calculateR0(actualVector);
        }
    }

    public void setRequalsR0(){
        for(Bond bond : bonds) {
            bond.setR(bond.getR0());
        }
    }

    public void calculateEnergy() {
        double energy = 0;
        for (Bond bond : bonds) {
            bond.calculateEnergy(1);
            energy += bond.getEnergy();
        }
        this.E = energy;
    }

    public void calculateForce() {
        double Fx = 0;
        double Fy = 0;
        double Fz = 0;
        for(Bond bond: bonds) {
            double deltaX = actualVector.get(x) - bond.getCarbonNode().getActualVector().get(x);
            double deltaY = actualVector.get(y) - bond.getCarbonNode().getActualVector().get(y);
            double deltaZ = actualVector.get(z) - bond.getCarbonNode().getActualVector().get(z);
            Fx += calculateForce(bond, deltaX);
            Fy += calculateForce(bond, deltaY);
            Fz += calculateForce(bond, deltaZ);
        }
        this.F.set(x, Fx);
        this.F.set(y, Fy);
        this.F.set(z, Fz);
    }

    private double calculateForce(Bond bond, double delta) {
        return calculateLennardPotential(bond.getR0(), bond.getR()) * delta;
    }

    private double calculateLennardPotential(double r0, double r) {
        double sigma = r0 * Math.pow(2, (-1 / 6.0));
        return 3 * Math.pow(r0, 6) / Math.pow(r, 14) * (-Math.pow(r0, 6) + Math.pow(r, 6));
//        return 6* Math.pow(sigma, 6) / Math.pow(r, 14) * (-2 * Math.pow(sigma, 6) + Math.pow(r, 6));
    }

    public int getIndex() {
        return index;
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

    public Vector<Double> getF() {
        return F;
    }

    public void setF(Vector<Double> f) {
        this.F = f;
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
}
