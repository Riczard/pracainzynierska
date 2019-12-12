package kuklinski.model;

import java.util.Vector;

public class Bond {

    private CarbonNode carbonNode;

    private double r0;
    private double r;
    private double energy;

    public Bond(CarbonNode carbonNode) {
        this.carbonNode = carbonNode;
    }

    public void calculateR0(Vector<Double> vector) {
        this.r0 = calculateDistance(vector, carbonNode.getActualVector());
    }

    public Double calculateDistance(Vector<Double> vector1, Vector<Double> vector2) {
        int x = 0;
        int y = 1;
        int z = 2;
        return Math.pow(Math.pow((vector2.get(x) - vector1.get(x)), 2) +
                Math.pow((vector2.get(y) - vector1.get(y)), 2) +
                Math.pow((vector2.get(z) - vector1.get(z)), 2), 0.5);
    }


    public void calculateEnergy(double epsilon) {
        this.energy = epsilon * (Math.pow(this.r0 / r, 12) - 2 * (Math.pow(this.r0 / r, 6)));
    }

    public CarbonNode getCarbonNode() {
        return carbonNode;
    }

    public void setCarbonNode(CarbonNode carbonNode) {
        this.carbonNode = carbonNode;
    }

    public double getR0() {
        return r0;
    }

    public void setR0(double r0) {
        this.r0 = r0;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }
}
