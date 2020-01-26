package kuklinski.calculation;

import kuklinski.Parsers;
import kuklinski.model.Fullerene;

import java.util.LinkedList;
import java.util.List;

public class FullereneCalculation {

    public static void calculation(Fullerene fullerene, double endCondition) {
        List<String> listToPrint = new LinkedList<>();
        fullerene.calculateCarbonEnergy();
        fullerene.calculateTotalEnergy();

        String heading = "indexWęzła;sąsiad1;sąsiad2;sąsiad3;APx;APy;APz;PPx;PPy;PPz;Fx;Fy;Fz;Energia;EnergiaCałkowita\n";
        listToPrint.add(heading);
        listToPrint.add(fullerene.getFullereneData());

        fullerene.calculateTotalForce();
        double totalF = fullerene.getTotalForce();
        System.out.println("pierwiastek z sum sił = " + totalF);
        int i = 1;
        Parsers.createTxt(listToPrint);

        while (totalF > endCondition){
            Parsers.appendToTxt(i + "\n");
            fullerene.calculateCarbonForce();

            fullerene.setPrevVectorSameLikeActual();
            fullerene.calculateNewPositions();
            fullerene.calculateR();

            fullerene.calculateCarbonEnergy();
            fullerene.calculateTotalEnergy();

            String fullereneData = fullerene.getFullereneData();
            Parsers.appendToTxt(fullereneData);

            fullerene.calculateTotalForce();
            totalF = fullerene.getTotalForce();
            i++;
            System.out.println("pierwiastek z sum sił = " + totalF);
            if(i > 1000000) break;
        }
    }
}
