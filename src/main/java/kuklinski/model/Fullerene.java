package kuklinski.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Fullerene {

    private final int CARBON_INDEX = 1;
    private final int X_POS_INDEX = 2;
    private final int Y_POS_INDEX = 3;
    private final int Z_POS_INDEX = 4;
    private final int NEIGHBOR_1_INDEX = 6;
    private final int NEIGHBOR_2_INDEX = 7;
    private final int NEIGHBOR_3_INDEX = 8;

    private double totalEnergy;
    private double totalForce;
    private List<Bond> bondList;

    private CarbonNode[] fullereneArray;

    public Fullerene(int capacity) {
        this.bondList = new ArrayList<>();
        this.fullereneArray = new CarbonNode[capacity];
    }

    public void fill(List<String> data) {
        for (String carbonString : data) {
            String substring = carbonString.substring(2); //txt tiles have 2 whitespaces on start
            String[] carbonData = substring.split("\\s+");
            addCarbonToFullereneArray(carbonData);
        }
    }

    private void addCarbonToFullereneArray(String[] carbonData) {
        int carbonIndex = Integer.parseInt(carbonData[CARBON_INDEX]);

        int[] neighbours = new int[3];
        neighbours[0] = Integer.parseInt(carbonData[NEIGHBOR_1_INDEX]);
        neighbours[1] = Integer.parseInt(carbonData[NEIGHBOR_2_INDEX]);
        neighbours[2] = Integer.parseInt(carbonData[NEIGHBOR_3_INDEX]);

        CarbonNode carbonNode = new CarbonNode(carbonIndex);

        setActualVector(carbonData, carbonNode);

        Bond[] bonds = createBonds(carbonIndex, neighbours);
        carbonNode.setBonds(bonds);
        fullereneArray[carbonNode.getNumber() - 1] = carbonNode; // - 1 because array index start from 0
    }

    private Bond[] createBonds(int carbonIndex, int[] neighbours) {

        Bond[] bonds = new Bond[3];
        for (int i = 0; i < bonds.length; i++) {

            Bond bond = getBondFromList(carbonIndex, neighbours[i]);

            if (bond == null) {
                bond = new Bond();
                bond.setAtomOneIndex(carbonIndex);
                bond.setAtomTwoIndex(neighbours[i]);
                bondList.add(bond);
            }
            bonds[i] = bond;
        }
        return bonds;
    }

    public void calculateDistanceBetweenAtoms() {
        for(Bond bond : bondList) {
            CarbonNode carbonOne = fullereneArray[bond.getAtomOneIndex() - 1];
            CarbonNode carbonTwo = fullereneArray[bond.getAtomTwoIndex() - 1];
            double distance = calculateDistance(carbonOne.getActualVector(), carbonTwo.getActualVector());
            bond.setR0(distance);
            bond.setR(distance);
        }
    }

    private Double calculateDistance(Vector<Double> vector1, Vector<Double> vector2) {
        int x = 0;
        int y = 1;
        int z = 2;
        return Math.pow(Math.pow((vector2.get(x) - vector1.get(x)), 2) +
                Math.pow((vector2.get(y) - vector1.get(y)), 2) +
                Math.pow((vector2.get(z) - vector1.get(z)), 2),0.5);
    }


    public void calculateEnergyForBonds() {
        for (Bond bond : bondList) {
            bond.calculateEnergy(1);
        }
    }

    public void calculateTotalEnergy() {
        this.totalEnergy = bondList.stream().mapToDouble(Bond::getEnergy).sum();
    }

    public void calculateForce() {
        for(Bond bond : bondList) {
            Vector<Double> one = fullereneArray[bond.getAtomOneIndex() - 1].getActualVector();
            Vector<Double> two = fullereneArray[bond.getAtomTwoIndex() - 1].getActualVector();
            bond.calculateEnergy(one, two);
        }
    }

    public void calculateTotalForce() {
        double totalForceOne = bondList.stream().mapToDouble(Bond::getForceOne).sum();
        double totalForceTwo = bondList.stream().mapToDouble(Bond::getForceTwo).sum();
        this.totalForce = totalForceOne + totalForceTwo;
    }

    private Vector<Double> changeStringsToVector(String... strings) {
        Vector<Double> vector = new Vector<>(3);
        vector.insertElementAt(Double.parseDouble(strings[0]), 0);
        vector.insertElementAt(Double.parseDouble(strings[1]), 1);
        vector.insertElementAt(Double.parseDouble(strings[2]), 2);
        return vector;
    }

    private Bond getBondFromList(int carbonIndex, int neighbour) {

        for (Bond bond : bondList) {
            if (bondExist(bond, carbonIndex, neighbour)) {
                return bond;
            }
        }
        return null;
    }

    private boolean bondExist(Bond bond, int indexOne, int indexTwo) {
        return bond.getAtomOneIndex() == indexOne && bond.getAtomTwoIndex() == indexTwo
                || bond.getAtomOneIndex() == indexTwo && bond.getAtomTwoIndex() == indexOne;
    }

    private void setActualVector(String[] carbonData, CarbonNode carbonNode) {
        carbonNode.setActualVector(changeStringsToVector(
                carbonData[X_POS_INDEX], carbonData[Y_POS_INDEX], carbonData[Z_POS_INDEX]));
    }

    public CarbonNode[] getFullereneArray() {
        return fullereneArray;
    }

    public List<Bond> getBondList() {
        return bondList;
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public double getTotalForce() {
        return totalForce;
    }
}
