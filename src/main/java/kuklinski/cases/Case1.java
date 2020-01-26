package kuklinski.cases;

import kuklinski.Parsers;
import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.LinkedList;
import java.util.List;

//  C   14    0.684800    0.000000   -3.449997    1   18   39   41
//  C   24    0.684800    0.000000    3.449997    1   23   25   51

//sciskanie przeciwleglych atomow
public class Case1 {

    public static void calculateCase2(Fullerene fullerene) {
        List<String> listToPrint = new LinkedList<>();

        Fullerene.p = 0.1;
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        firstCarbon.setExternalFz(0.01);
        secondCarbon.setExternalFz(-0.01);
        firstCarbon.calculateForce();
        secondCarbon.calculateForce();
        fullerene.calculateCarbonEnergy();
        fullerene.calculateTotalEnergy();

        String heading = "indexWęzła;sąsiad1;sąsiad2;sąsiad3;APx;APy;APz;PPx;PPy;PPz;Fx;Fy;Fz;Energia;EnergiaCałkowita\n";
        listToPrint.add(heading);
        listToPrint.add(fullerene.getFullereneData());

        fullerene.calculateTotalForce();
        double totalF = fullerene.getTotalForce();
        System.out.println(totalF);
        int i = 1;
        Parsers.createTxt(listToPrint);

        while (totalF > Math.pow(10, -4)){
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
            System.out.println(totalF);
        }
    }
}
