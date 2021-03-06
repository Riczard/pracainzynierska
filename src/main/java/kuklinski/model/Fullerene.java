package kuklinski.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fullerene {

    private final int CARBON_INDEX = 1;
    private final int X_POS_INDEX = 2;
    private final int Y_POS_INDEX = 3;
    private final int Z_POS_INDEX = 4;
    private final int NEIGHBOR_1_INDEX = 6;
    private final int NEIGHBOR_2_INDEX = 7;
    private final int NEIGHBOR_3_INDEX = 8;

    private final int x = 0;
    private final int y = 1;
    private final int z = 2;

    public static double p;
    private double totalEnergy;
    private double totalForce;
    private double maxForce;
    private double maxWayLength;

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

    public void calculateR() {
        for (CarbonNode node : fullereneArray) {
            node.calculateR();
        }
    }

    public void calculateR0() {
        for (CarbonNode node : fullereneArray) {
            node.calculateR0();
        }
    }

    public void calculateCarbonEnergy() {
        for (CarbonNode node : fullereneArray) {
            node.calculateEnergy();
        }
    }

    public void calculateCarbonForce() {
        this.maxForce = 0.0;
        for (CarbonNode node : fullereneArray) {
            node.calculateForce();
            if ( maxForce < node.getLengthF() )
                this.maxForce = node.getLengthF();
        }
    }

    public void calculateTotalEnergy() {
        this.totalEnergy = 0.0;
        for (CarbonNode node : fullereneArray) {
            this.totalEnergy += node.getE();
        }
        this.totalEnergy = this.totalEnergy / 2.0;
    }

    public void calculateTotalForce() {
        this.totalForce = 0.0;
        for (CarbonNode node : fullereneArray) {
            node.calculateTotalForce();
            this.totalForce += node.getTotalF();
        }
    }

    public void setStartVector() {
        for (CarbonNode node : fullereneArray) {
            node.setStartVectorSameLikeActual();
        }
    }

    public void calculateNewPositions() {
        for (CarbonNode node : fullereneArray) {
            node.calculateNewPosition();
        }
    }

    public void calculateMaxWayLength() {
        maxWayLength=0.0;
        for (CarbonNode node : fullereneArray) {
            node.calculateWayLength();
            if ( maxWayLength < node.getWayLength() )
                maxWayLength = node.getWayLength();
        }
    }

    public double getMaxWayLength() {
        return maxWayLength;
    }

    public double getMaxForce() {
        return maxForce;
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

    private List<Double> changeStringsToVector(String... strings) {
        List<Double> vector = new ArrayList<>(3);
        vector.add(Double.parseDouble(strings[0]));
        vector.add(Double.parseDouble(strings[1]));
        vector.add(Double.parseDouble(strings[2]));
        return vector;
    }

    public double getRAverage() {
        double R = 0;
        for (CarbonNode carbon : fullereneArray) {
            R += Math.sqrt(Math.pow(carbon.getActualVector().get(x),2) + Math.pow(carbon.getActualVector().get(y),2) + Math.pow(carbon.getActualVector().get(z),2));
        }
        R /= 60;
        return R;
    }

    public double getTotalForce() {
        return totalForce;
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public CarbonNode[] getFullereneArray() {
        return fullereneArray;
    }

    public String getEnergyData(double lengthOfExternalForceWithSign) {
        StringBuilder sb = new StringBuilder();
        sb.append(lengthOfExternalForceWithSign).append(";");
        sb.append(this.totalEnergy).append(";");
        sb.append(this.getRAverage()).append("\n");

        String result = sb.toString();
        sb.setLength(0);
        return result;
    }

    public String getFullereneData() {
        StringBuilder sb = new StringBuilder();

        for (CarbonNode carbon : fullereneArray) {
            sb.append(carbon.toString()).append(";");
            sb.append(this.totalEnergy).append(";");
            sb.append(this.getRAverage()).append("\n");
        }
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }

}
