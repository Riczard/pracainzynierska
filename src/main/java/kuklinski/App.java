package kuklinski;

import kuklinski.model.*;

import java.util.Arrays;
import java.util.List;

//  C   14    0.684800    0.000000   -3.449997    1   18   39   41
//  C   24    0.684800    0.000000    3.449997    1   23   25   51
//  C   41   -0.684800    0.000000   -3.449997    1   14   42   45
//  C   51   -0.684800    0.000000    3.449997    1   24   30   32


public class App {
    public static void main(String[] args) {
        int numberOfAtoms = 0;
        List<String> data = Parsers.getFullereneDataFromTXT("c60card.txt");
        Fullerene fullerene = new Fullerene(Integer.parseInt(data.get(numberOfAtoms)));
        data.remove(numberOfAtoms);
        fullerene.fill(data);
//        fullerene.calculateR();
        fullerene.calculateR0();
        fullerene.calculateCarbonEnergy();
        fullerene.calculateCarbonForce();
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        Bond[] bonds = fullereneArray[0].getBonds();
        Arrays.stream(bonds).forEach(bond -> System.out.println(bond.getR0()));

        System.out.println(Arrays.toString(fullereneArray));
//        setNewR(fullerene);
    }

    private static void setNewR(Fullerene fullerene) {
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode carbonNode = fullereneArray[0];
        carbonNode.getActualVector().set(0, carbonNode.getActualVector().get(0) - 0.1);
        Bond[] bonds = carbonNode.getBonds();
        for(Bond bond : bonds) {
            Double r = bond.calculateDistance(carbonNode.getActualVector(), bond.getCarbonNode().getActualVector());
            bond.setR(r);
        }
        fullerene.calculateCarbonEnergy();
        fullerene.calculateCarbonForce();
        Bond[] neighbours = carbonNode.getBonds();
        for(Bond bond : neighbours) {
            System.out.println("CarbonNode{index=" + bond.getCarbonNode().getIndex() +  " F" + bond.getF());
        }
        System.out.println(Arrays.toString(fullereneArray));
    }
}
