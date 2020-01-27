package kuklinski.model;

import java.util.ArrayList;
import java.util.List;

public class CarbonNode {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private int index;
    private List<Double> actualVector;
    private List<Double> previousVector;
    private List<Double> F;
    private List<Double> externalF;
    private double E;
    private double totalF;

    private Bond[] bonds;

    public CarbonNode(int index) {
        this.index = index;
        this.actualVector = new ArrayList<>(3);
        this.previousVector = new ArrayList<>(3);
        this.F = new ArrayList<>(3);
        this.externalF = new ArrayList<>(3);
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
        this.externalF.add(0.0);
        this.externalF.add(0.0);
        this.externalF.add(0.0);

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

//        System.out.println("Carbon:" + this.getIndex() + "Fx:" + (Fx + externalF.get(x)) + "; Fy:" + (Fy + externalF.get(y)) + "; Fz:" + (Fz+externalF.get(z)));

        this.F.set(x, Fx + externalF.get(x));
        this.F.set(y, Fy + externalF.get(y));
        this.F.set(z, Fz + externalF.get(z));
    }

    public void calculateTotalForce() {
        this.totalF = 0.0;
        this.totalF = Math.sqrt(Math.pow(this.F.get(x), 2) + Math.pow(this.F.get(y), 2) + Math.pow(this.F.get(z), 2));
    }

    public void calculateNewPosition() {
        double newX = F.get(x) * Fullerene.p + actualVector.get(x);
        double newY = F.get(y) * Fullerene.p + actualVector.get(y);
        double newZ = F.get(z) * Fullerene.p + actualVector.get(z);
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

    public void setPreviousVectorSameLikeActual() {
        previousVector.set(x, actualVector.get(x));
        previousVector.set(y, actualVector.get(y));
        previousVector.set(z, actualVector.get(z));
    }

    private double getF1() {
        CarbonNode C1 = bonds[0].getCarbonNode();
        return Math.sqrt(Math.pow(Fx() - C1.Fx(),2) + Math.pow(Fy() - C1.Fy(),2) + Math.pow(Fz() - C1.Fz(),2));
    }

    private double getF2() {
        CarbonNode C2 = bonds[1].getCarbonNode();
        return Math.sqrt(Math.pow(Fx() - C2.Fx(),2) + Math.pow(Fy() - C2.Fy(),2) + Math.pow(Fz() - C2.Fz(),2));
    }

    private double getF3() {
        CarbonNode C3 = bonds[2].getCarbonNode();
        return Math.sqrt(Math.pow(Fx() - C3.Fx(),2) + Math.pow(Fy() - C3.Fy(),2) + Math.pow(Fz() - C3.Fz(),2));
    }

    public List<Double> getExternalF() {
        return externalF;
    }

    public void setExternalF(List<Double> externalF) {
        this.externalF = externalF;
    }

    public void setExternalFx(double fx) {
        this.externalF.set(x, fx);
    }

    public void setExternalFy(double fy) {
        this.externalF.set(y, fy);
    }

    public void setExternalFz(double fz) {
        this.externalF.set(z, fz);
    }

    public double getTotalF() {
        return this.totalF;
    }

    public double Fx() {
        return this.F.get(x);
    }

    public double Fy() {
        return this.F.get(y);
    }

    public double Fz() {
        return this.F.get(z);
    }

    public double r1() {
        return this.bonds[0].getR();
    }

    public double r2() {
        return this.bonds[1].getR();
    }

    public double r3() {
        return this.bonds[2].getR();
    }

    @Override
    public String toString() {
        return this.index + ";" +
                this.bonds[0].getCarbonNode().getIndex() + ";" +
                this.bonds[1].getCarbonNode().getIndex() + ";" +
                this.bonds[2].getCarbonNode().getIndex() + ";" +
                this.actualVector.get(x) + "; " + this.actualVector.get(y) + "; " + this.actualVector.get(z) + ";" +
                r1() + ";" + r2() + ";" + r3() + ";" +
                this.F.get(x) + ";" + this.F.get(y) + ";" + this.F.get(z) + ";" +
                getF1() + ";" + getF2() + ";" + getF3() + ";" +
                this.E;
    }
}
