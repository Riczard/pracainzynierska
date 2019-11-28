package kuklinski;

import java.util.List;
import java.util.Vector;

public class FullereneData {

    private final int CARBON_INDEX = 1;
    private final int X_POS_INDEX = 2;
    private final int Y_POS_INDEX = 3;
    private final int Z_POS_INDEX = 4;
    private final int NEIGHBOR_1 = 6;
    private final int NEIGHBOR_2 = 7;
    private final int NEIGHBOR_3 = 8;


    private CarbonNode[] fullereneArray;

    FullereneData(int capacity) {
        this.fullereneArray = new CarbonNode[capacity];
    }

    void fill(List<String> data) {
        for(String carbonString : data) {
            String substring = carbonString.substring(2); //txt tiles have 2 whitespaces on start
            String[] carbonData = substring.split("\\s+");
            addCarbonToFullereneArray(carbonData);
        }
    }

    private void addCarbonToFullereneArray(String[] carbonData) {
        CarbonNode carbonNode = new CarbonNode(Integer.parseInt(carbonData[CARBON_INDEX]));


        carbonNode.setActualVector(changeStringsToVector(
                carbonData[X_POS_INDEX], carbonData[Y_POS_INDEX], carbonData[Z_POS_INDEX]));
        carbonNode.setNeighbor1Index(Integer.parseInt(carbonData[NEIGHBOR_1]));
        carbonNode.setNeighbor2Index(Integer.parseInt(carbonData[NEIGHBOR_2]));
        carbonNode.setNeighbor3Index(Integer.parseInt(carbonData[NEIGHBOR_3]));

        fullereneArray[carbonNode.getNumber() - 1] = carbonNode; // - 1 because array index start from 0
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
