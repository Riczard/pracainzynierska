package kuklinski;

import java.util.Vector;

public class CarbonNode {

    private int number;
    private Vector<Double> actualVector;
    private Vector<Double> previousVector;
    private double E;
    public int neighbor1Index;
    public int neighbor2Index;
    public int neighbor3Index;

    public CarbonNode(int number) {
        this.actualVector = new Vector<>(3);
        this.previousVector = new Vector<>(3);
        this.number = number;
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

    public int getNeighbor1Index() {
        return neighbor1Index;
    }

    public void setNeighbor1Index(int neighbor1Index) {
        this.neighbor1Index = neighbor1Index;
    }

    public int getNeighbor2Index() {
        return neighbor2Index;
    }

    public void setNeighbor2Index(int neighbor2Index) {
        this.neighbor2Index = neighbor2Index;
    }

    public int getNeighbor3Index() {
        return neighbor3Index;
    }

    public void setNeighbor3Index(int neighbor3Index) {
        this.neighbor3Index = neighbor3Index;
    }

    @Override
    public String toString() {
        return "CarbonNode{" +
                "number=" + number +
                ", actualVector=" + actualVector +
                ", previousVector=" + previousVector +
                ", E=" + E +
                ", neighbor1Index=" + neighbor1Index +
                ", neighbor2Index=" + neighbor2Index +
                ", neighbor3Index=" + neighbor3Index +
                '}';
    }
}
