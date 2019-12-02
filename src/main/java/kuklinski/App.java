package kuklinski;

import kuklinski.model.Bond;
import kuklinski.model.Fullerene;

import java.util.List;

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
        List<Bond> bondList = fullerene.getBondList();
        bondList.forEach(System.out::println);
        System.out.println("Fullerene total energy: " + fullerene.getTotalEnergy());
    }
}
