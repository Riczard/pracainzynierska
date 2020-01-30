package kuklinski.calculation;

import kuklinski.Parsers;
import kuklinski.model.Fullerene;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FullereneCalculation {

    public static void calculation(Fullerene fullerene, double printStep) {
        List<String> listToPrint = new LinkedList<>();
        fullerene.calculateCarbonEnergy();
        fullerene.calculateTotalEnergy();
        String heading = "indexWęzła;sąsiad1;sąsiad2;sąsiad3;APx;APy;APz;r1;r2;r3;Fx;Fy;Fz;F1;F2;F3;Energia;EnergiaCałkowita\n";
        listToPrint.add(heading);
        listToPrint.add(fullerene.getFullereneData());

        fullerene.calculateTotalForce();
        double totalF = fullerene.getTotalForce();
//        int i = 1;
        Parsers.createTxt(listToPrint);
        for(int i = 0; i< 10000000; i++) {

            fullerene.calculateCarbonForce();

            fullerene.setPrevVectorSameLikeActual();
            fullerene.calculateNewPositions();
            fullerene.calculateR();

            fullerene.calculateCarbonEnergy();
            fullerene.calculateTotalEnergy();

            String fullereneData = fullerene.getFullereneData();

            if(i%printStep ==0) {
                Parsers.appendToTxt(i + "\n");
                Parsers.appendToTxt(fullereneData);
            }

            fullerene.calculateTotalForce();
            totalF = fullerene.getTotalForce();
            i++;
//            if (i > 10000000) break;
        }
    }
}
