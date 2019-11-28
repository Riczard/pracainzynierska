package kuklinski;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int numberOfAtoms = 0;
        List<String> data = Parsers.getFullereneDataFromTXT("c60card.txt");
        FullereneData fullereneData = new FullereneData(Integer.parseInt(data.get(numberOfAtoms)));
        data.remove(numberOfAtoms);
        fullereneData.fill(data);
        System.out.println(Arrays.toString(fullereneData.getFullereneArray()));
    }
}
