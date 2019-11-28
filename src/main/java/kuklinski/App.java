package kuklinski;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int numberOfAtoms = 0;
        List<String> data = Parsers.getFullereneDataFromTXT("c60card.txt");
        Fullerene fullerene = new Fullerene(Integer.parseInt(data.get(numberOfAtoms)));
        data.remove(numberOfAtoms);
        fullerene.fill(data);
        System.out.println(Arrays.toString(fullerene.getFullereneArray()));
    }
}
