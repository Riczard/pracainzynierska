package kuklinski;

import kuklinski.model.Bond;
import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class App {
    public static void main(String[] args) {
        int numberOfAtoms = 0;
        List<String> data = Parsers.getFullereneDataFromTXT("c60card.txt");
        Fullerene fullerene = new Fullerene(Integer.parseInt(data.get(numberOfAtoms)));
        data.remove(numberOfAtoms);
        fullerene.fill(data);
        fullerene.calculateDistanceBetweenAtoms();
        fullerene.calculateEnergyForBonds();
        fullerene.calculateTotalEnergy();
        fullerene.calculateBondForce();
        fullerene.calculateCarbonForce();
        System.out.println(Arrays.toString(fullerene.getFullereneArray()));

        setNewR(fullerene);

        fullerene.calculateEnergyForBonds();
        fullerene.calculateTotalEnergy();
        fullerene.calculateBondForce();
        fullerene.calculateCarbonForce();
        System.out.println(Arrays.toString(fullerene.getFullereneArray()));

    }

    private static void setNewR(Fullerene fullerene) {
        Bond bond = fullerene.getBondList().get(0);
        int index = bond.getAtomOneIndex();
        CarbonNode carbonNode = fullerene.getFullereneArray()[index - 1];
        Vector<Double> actualVector = carbonNode.getActualVector();
        actualVector.set(0, actualVector.get(0) - 0.1);
        int atomTwoIndex = bond.getAtomTwoIndex();
        CarbonNode carbonNode1 = fullerene.getFullereneArray()[atomTwoIndex - 1];
        Vector<Double> actualVector1 = carbonNode1.getActualVector();
        Double distance = fullerene.calculateDistance(actualVector, actualVector1);
        bond.setR(distance);
    }
}
