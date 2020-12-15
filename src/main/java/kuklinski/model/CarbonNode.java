package kuklinski.model;

import java.util.ArrayList;
import java.util.List;

public class CarbonNode {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private int index;
    private List<Double> actualVector;
    private List<Double> startVector;
    private List<Double> F;
    private List<Double> externalF;
    private double E;
    private double totalF;
    private double maxF;
    private double lengthF;
    private double wayLength;

    private Bond[] bonds;

    public CarbonNode(int index) {
        this.index = index;
        this.actualVector = new ArrayList<>(3);
        this.startVector = new ArrayList<>(3);
        this.F = new ArrayList<>(3);
        this.externalF = new ArrayList<>(3);
        this.bonds = new Bond[3];
        fillListsWith0Elements();
    }

    private void fillListsWith0Elements() {
        this.actualVector.add(0.0);
        this.actualVector.add(0.0);
        this.actualVector.add(0.0);
        this.startVector.add(0.0);
        this.startVector.add(0.0);
        this.startVector.add(0.0);
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

        double ex,ey,ez;
        ex = externalF.get(x);
        ey = externalF.get(y);
        ez = externalF.get(z);
        this.F.set(x, Fx + externalF.get(x));
        this.F.set(y, Fy + externalF.get(y));
        this.F.set(z, Fz + externalF.get(z));

        this.calculateLengthOfForce();
    }

    public void calculateLengthOfForce() {
        this.lengthF = Math.sqrt(Math.pow(this.F.get(x), 2) + Math.pow(this.F.get(y), 2) + Math.pow(this.F.get(z), 2));
    }


    public void calculateTotalForce() {
        this.totalF = 0.0;
        this.totalF = Math.sqrt(Math.pow(this.F.get(x), 2) + Math.pow(this.F.get(y), 2) + Math.pow(this.F.get(z), 2));
    }

    public void calculateMaxForce() {
        this.maxF = 0.0;
        this.maxF = Math.sqrt(Math.pow(this.F.get(x), 2) + Math.pow(this.F.get(y), 2) + Math.pow(this.F.get(z), 2));
    }

    public void calculateNewPosition() {
        double newX = F.get(x) * Fullerene.p + actualVector.get(x);
        double newY = F.get(y) * Fullerene.p + actualVector.get(y);
        double newZ = F.get(z) * Fullerene.p + actualVector.get(z);
        actualVector.set(x, newX);
        actualVector.set(y, newY);
        actualVector.set(z, newZ);
    }

    public void calculateWayLength() {
        wayLength = Math.sqrt(Math.pow(actualVector.get(x)-startVector.get(x),2)+Math.pow(actualVector.get(y)-startVector.get(y),2)+Math.pow(actualVector.get(z)-startVector.get(z),2));
    }

    public double getWayLength() {
        return wayLength;
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

    public List<Double> getStartVector() {
        return startVector;
    }

    public void setStartVector(List<Double> startVector) {
        this.startVector = startVector;
    }

    public List<Double> getF() {
        return F;
    }

    public double getLengthF() {
        return lengthF;
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

    public void setStartVectorSameLikeActual() {
        startVector.set(x, actualVector.get(x));
        startVector.set(y, actualVector.get(y));
        startVector.set(z, actualVector.get(z));
    }

    private double getF1() {
        List<Double> f = bonds[0].getF();
        return Math.sqrt(Math.pow(f.get(x),2) + Math.pow(f.get(y),2) + Math.pow(f.get(z),2));
    }

    private double getF2() {
        List<Double> f = bonds[1].getF();
        return Math.sqrt(Math.pow(f.get(x),2) + Math.pow(f.get(y),2) + Math.pow(f.get(z),2));
    }

    private double getF3() {
        List<Double> f = bonds[2].getF();
        return Math.sqrt(Math.pow(f.get(x),2) + Math.pow(f.get(y),2) + Math.pow(f.get(z),2));
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
    public double getMaxF() {
        return this.maxF;
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
                Math.sqrt(Math.pow(this.actualVector.get(x),2) + Math.pow(this.actualVector.get(y),2) + Math.pow(this.actualVector.get(z),2))+ ";" +
                r1() + ";" + r2() + ";" + r3() + ";" +
                this.wayLength + ";" +
                this.E+ ";" +
                this.F.get(x) + ";" + this.F.get(y) + ";" + this.F.get(z) + ";" +
                getF1() + ";" + getF2() + ";" + getF3();
    }
}
