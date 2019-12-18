package kuklinski.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Bond {

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    private CarbonNode carbonNode;

    private double r0;
    private double r;
    private double energy;
    private List<Double> F;
    private double lennardPotential;

    public Bond(CarbonNode carbonNode) {
        this.carbonNode = carbonNode;
        this.F = new ArrayList<>(3);
    }

    public void calculateR0(List<Double> vector) {
        this.r0 = calculateDistance(vector, carbonNode.getActualVector());
    }

    public void calculateR(List<Double> vector) {
        this.r = calculateDistance(vector, carbonNode.getActualVector());
    }

    public Double calculateDistance(List<Double> vector1, List<Double> vector2) {
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
//            epsilon = 4.858;
            epsilon = 1.06816;
        }else {
//            epsilon = 4.548;
            epsilon = 1;
        }
        this.energy = epsilon * (Math.pow(this.r0 / r, 12) - 2 * (Math.pow(this.r0 / r, 6)));
    }

    public void calculateForce(List<Double> vector) {
        calculateLennardPotential();

        double deltaX = carbonNode.getActualVector().get(x) - vector.get(x);
        double deltaY = carbonNode.getActualVector().get(y) - vector.get(y);
        double deltaZ = carbonNode.getActualVector().get(z) - vector.get(z);
        double Fx = this.lennardPotential * deltaX;
        double Fy = this.lennardPotential * deltaY;
        double Fz = this.lennardPotential * deltaZ;
        this.F.add(x, Fx);
        this.F.add(y, Fy);
        this.F.add(z, Fz);
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

    public List<Double> getF() {
        return F;
    }

    public void setF(List<Double> f) {
        F = f;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "carbonNode=" + carbonNode.getIndex() +
                ", r0=" + r0 +
                ", r=" + r +
                ", energy=" + energy +
                ", F=" + F +
                '}';
    }
}
