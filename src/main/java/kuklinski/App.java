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
        fullerene.calculateForce();
        fullerene.calculateTotalForce();
        System.out.println("Fullerene total force: " + fullerene.getTotalForce());
        System.out.println("Fullerene total energy: " + fullerene.getTotalEnergy());
    }
}
