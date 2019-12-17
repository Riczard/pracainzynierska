package kuklinski.model;

import java.util.Vector;

public class Bond {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private CarbonNode carbonNode;

    private double r0;
    private double r;
    private double energy;
    private Vector<Double> F;
    private double lennardPotential;

    public Bond(CarbonNode carbonNode) {
        this.carbonNode = carbonNode;
        createEmptyVector();
    }

    private void createEmptyVector() {
        this.F = new Vector<>(3);
        this.F.add(0.0);
        this.F.add(0.0);
        this.F.add(0.0);
    }

    public void calculateR(Vector<Double> vector) {
        this.r0 = calculateDistance(vector, carbonNode.getActualVector());
        this.r = r0;
    }

    public Double calculateDistance(Vector<Double> vector1, Vector<Double> vector2) {
        int x = 0;
        int y = 1;
        int z = 2;
        return Math.pow(
                Math.pow((vector2.get(x) - vector1.get(x)), 2) +
                Math.pow((vector2.get(y) - vector1.get(y)), 2) +
                Math.pow((vector2.get(z) - vector1.get(z)), 2)
                , 0.5);
    }


    public void calculateEnergy() {
        double epsilon;
        if(r0 == 1.3696) {
            epsilon = 4.858;
        }else {
            epsilon = 4.548;
        }
        this.energy = epsilon * (Math.pow(this.r0 / r, 12) - 2 * (Math.pow(this.r0 / r, 6)));
    }

    public void calculateForce(Vector<Double> vector) {
        calculateLennardPotential();

        double deltaX = vector.get(x) - carbonNode.getActualVector().get(x);
        double deltaY = vector.get(y) - carbonNode.getActualVector().get(y);
        double deltaZ = vector.get(z) - carbonNode.getActualVector().get(z);
        double Fx = this.lennardPotential * deltaX;
        double Fy = this.lennardPotential * deltaY;
        double Fz = this.lennardPotential * deltaZ;
        this.F.set(x, Fx);
        this.F.set(y, Fy);
        this.F.set(z, Fz);
    }

    private void calculateLennardPotential() {
        double sigma = r0 * Math.pow(2, (-1 / 6.0));
        this.lennardPotential = 6 * Math.pow(r0, 6) / Math.pow(r, 14) * (-Math.pow(r0, 6) + Math.pow(r, 6));
//        return 6* Math.pow(sigma, 6) / Math.pow(r, 14) * (-2 * Math.pow(sigma, 6) + Math.pow(r, 6));
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

    public double getFx() {
        return F.get(x);
    }

    public double getFy() {
        return F.get(y);
    }
    public double getFz() {
        return F.get(z);
    }

    public Vector<Double> getF() {
        return F;
    }

    public void setF(Vector<Double> f) {
        F = f;
    }
}
