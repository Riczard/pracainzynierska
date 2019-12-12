package kuklinski.model;

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

    private CarbonNode[] fullereneArray;

    public Fullerene(int capacity) {
        this.fullereneArray = new CarbonNode[capacity];
        createEmptyCarbons();
    }

    public void fill(List<String> data) {
        for (String carbonString : data) {
            String substring = carbonString.substring(2); //txt tiles have 2 whitespaces on start
            String[] carbonData = substring.split("\\s+");
            addCarbonToFullereneArray(carbonData);
        }
    }

    public void calculateR0() {
        for (CarbonNode node : fullereneArray) {
            node.calculateR0();
        }
    }

    public void setRequalsR0() {
        for (CarbonNode node : fullereneArray) {
            node.setRequalsR0();
        }
    }

    public void calculateCarbonEnergy() {
        for (CarbonNode node : fullereneArray) {
            node.calculateEnergy();
        }
    }

    public void calculateCarbonForce() {
        for (CarbonNode node : fullereneArray) {
            node.calculateForce();
        }
    }

    private void createEmptyCarbons() {
        for (int i = 0; i < fullereneArray.length; i++) {
            fullereneArray[i] = new CarbonNode(i + 1);
        }
    }

    private void addCarbonToFullereneArray(String[] carbonData) {
        int carbonIndex = Integer.parseInt(carbonData[CARBON_INDEX]);

        int[] neighboursIndex = new int[3];
        parseNeighboursIndexes(carbonData, neighboursIndex);
        setBonds(fullereneArray[carbonIndex - 1], neighboursIndex);
        setCarbonVector(carbonData, fullereneArray[carbonIndex - 1]);
    }

    private void parseNeighboursIndexes(String[] carbonData, int[] neighboursIndex) {
        neighboursIndex[0] = Integer.parseInt(carbonData[NEIGHBOR_1_INDEX]);
        neighboursIndex[1] = Integer.parseInt(carbonData[NEIGHBOR_2_INDEX]);
        neighboursIndex[2] = Integer.parseInt(carbonData[NEIGHBOR_3_INDEX]);
    }

    private void setBonds(CarbonNode carbonNode, int[] neighboursIndex) {
        Bond[] bonds = carbonNode.getBonds();
        bonds[0] = new Bond(fullereneArray[neighboursIndex[0] - 1]);
        bonds[1] = new Bond(fullereneArray[neighboursIndex[1] - 1]);
        bonds[2] = new Bond(fullereneArray[neighboursIndex[2] - 1]);
    }

    private void setCarbonVector(String[] carbonData, CarbonNode carbonNode) {
        carbonNode.setActualVector(changeStringsToVector(
                carbonData[X_POS_INDEX], carbonData[Y_POS_INDEX], carbonData[Z_POS_INDEX]));
    }

    private Vector<Double> changeStringsToVector(String... strings) {
        Vector<Double> vector = new Vector<>(3);
        vector.insertElementAt(Double.parseDouble(strings[0]), 0);
        vector.insertElementAt(Double.parseDouble(strings[1]), 1);
        vector.insertElementAt(Double.parseDouble(strings[2]), 2);
        return vector;
    }

    public CarbonNode[] getFullereneArray() {
        return fullereneArray;
    }
}
