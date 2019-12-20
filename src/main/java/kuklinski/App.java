package kuklinski;

import kuklinski.model.*;

import java.util.ArrayList;
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
        fullerene.calculateR0();
        fullerene.calculateR();
        fullerene.calculateCarbonEnergy();
        fullerene.calculateCarbonForce();
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        Bond[] bonds = fullereneArray[0].getBonds();

//        changePosition(fullerene);
        setNewForces(fullerene);
    }

    private static void setNewForces(Fullerene fullerene) {
        List<String> listToPrint = new ArrayList<>();
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        firstCarbon.getF().set(2,-1.0);
        secondCarbon.getF().set(2, 1.0);

        firstCarbon.setNeighboursToCalculate(true);
        secondCarbon.setNeighboursToCalculate(true);

        firstCarbon.calculateR();
        firstCarbon.calculateEnergy();
//        firstCarbon.calculateForce();

        secondCarbon.calculateR();
        secondCarbon.calculateEnergy();
//        secondCarbon.calculateForce();

        fullerene.calculateTotalEnergy();
        String heading = "index;actualPosition;previusPosition;Force;Energy;totalEnergy\n";

        listToPrint.add(heading);
        listToPrint.add(getFullereneData(fullerene));
        listToPrint.add("1");

        for(int i = 0 ; i < 80; i++) {
            listToPrint.add(i + 1 + "\n");
            for (CarbonNode carbon : fullereneArray) {

                if (carbon.isToCalculate()) {
                    carbon.calculateNewPosition();
                    carbon.calculateR();
                    carbon.setPreviousVector(carbon.getActualVector());
                    carbon.setToCalculate(false);
                    carbon.setNeighboursToCalculate(true);
                    carbon.calculateEnergy();
                    carbon.calculateForce();
                }

            }
            fullerene.calculateTotalEnergy();
            String fullereneData = getFullereneData(fullerene);
            listToPrint.add(fullereneData);

        }
        Parsers.createTxt(listToPrint);
    }

    private static void changePosition(Fullerene fullerene) {
        List<String> listToPrint = new ArrayList<>();
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        List<Double> firstCarbonVector = firstCarbon.getActualVector();
        firstCarbon.setPreviousVectorSameLikeActual();
        double v = firstCarbonVector.get(2) + 0.1;
        firstCarbonVector.set(2, v);
        firstCarbon.getActualVector().set(2, v);

        List<Double> secondCarbonVector = secondCarbon.getActualVector();
        secondCarbon.setPreviousVectorSameLikeActual();
        secondCarbon.getActualVector().set(2, secondCarbonVector.get(2) - 0.1);

        firstCarbon.setNeighboursToCalculate(true);
        secondCarbon.setNeighboursToCalculate(true);

        firstCarbon.calculateR();
        firstCarbon.calculateEnergy();
        firstCarbon.calculateForce();

        secondCarbon.calculateR();
        secondCarbon.calculateEnergy();
        secondCarbon.calculateForce();

        fullerene.calculateTotalEnergy();
        String heading = "index;actualPosition;previusPosition;Force;Energy;totalEnergy\n";

        listToPrint.add(heading);
        listToPrint.add(getFullereneData(fullerene));
        listToPrint.add("1");

        for(int i = 0 ; i < 80; i++) {
            listToPrint.add(i + 1 + "\n");
            for (CarbonNode carbon : fullereneArray) {

                if (carbon.isToCalculate()) {
                    carbon.calculateNewPosition();
                    carbon.calculateR();
                    carbon.setPreviousVector(carbon.getActualVector());
                    carbon.setToCalculate(false);
                    carbon.setNeighboursToCalculate(true);
                    carbon.calculateEnergy();
                    carbon.calculateForce();
                }

            }
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
            sb.append(carbon.getIndex()).append(";");
            sb.append(carbon.getActualVector()).append(";");
            sb.append(carbon.getPreviousVector()).append(";");
            sb.append(carbon.getF()).append(";");
            sb.append(carbon.getE()).append(";");
            sb.append(fullerene.getTotalEnergy()).append("\n");
        }
        return sb.toString();
    }

}
