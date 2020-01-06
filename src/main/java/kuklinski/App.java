package kuklinski;

import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.ArrayList;
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
        fullerene.calculateR0();
        fullerene.calculateR();

        calculateNewPositionWithStartForce(fullerene);
    }

    private static void calculateNewPositionWithStartForce(Fullerene fullerene) {
        List<String> listToPrint = new ArrayList<>();

        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        firstCarbon.setExternalFz(5);
        secondCarbon.setExternalFz(-5);
        firstCarbon.calculateForce();
        secondCarbon.calculateForce();
        fullerene.calculateCarbonEnergy();
        fullerene.calculateTotalEnergy();

        String heading = "indexWęzła;sąsiad1;sąsiad2;sąsiad3;aktualnaPozycja;poprzedniaPozycja;Fx;Fy;Fz;Energia;EnergiaCałkowita\n";
        listToPrint.add(heading);
        listToPrint.add(getFullereneData(fullerene));

        System.out.println(fullereneArray[13].getF());

        for (int i = 0; i < 500; i++) {
            listToPrint.add(i + 1 + "\n");

            fullerene.calculateCarbonForce();

            fullerene.setPrevVectorSameLikeActual();
            fullerene.calculateNewPositions();
            fullerene.calculateR();

            fullerene.calculateCarbonEnergy();
            fullerene.calculateTotalEnergy();
            String fullereneData = getFullereneData(fullerene);
            listToPrint.add(fullereneData);
        }
        Parsers.createTxt(listToPrint);
    }


    private static String getFullereneData(Fullerene fullerene) {
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        StringBuilder sb = new StringBuilder();

        for (CarbonNode carbon : fullereneArray) {
            sb.append(carbon.toString()).append(";");
            sb.append(fullerene.getTotalEnergy()).append("\n");
        }
        return sb.toString();
    }

}
