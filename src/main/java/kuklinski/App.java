package kuklinski;

import kuklinski.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

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


//        System.out.println(Arrays.toString(fullereneArray));

        changePosition(fullerene);
    }

    private static void changePosition(Fullerene fullerene) {
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        List<Double> firstCarbonVector = firstCarbon.getActualVector();
        firstCarbon.setPreviousVectorSameLikeActual();
        firstCarbonVector.set(2, firstCarbonVector.get(2) + 0.05);

        Vector<Double> secondCarbonVector = (Vector<Double>) firstCarbon.getActualVector();
        secondCarbon.setPreviousVectorSameLikeActual();
        secondCarbonVector.set(2, secondCarbonVector.get(2) - 0.05);

        firstCarbon.setNeighboursToCalculate(true);
        secondCarbon.setNeighboursToCalculate(true);

        firstCarbon.calculateR();
        firstCarbon.calculateEnergy();
        firstCarbon.calculateForce();
        secondCarbon.calculateR();
        secondCarbon.calculateEnergy();
        secondCarbon.calculateForce();


//        for(int i = 0 ; i < 6; i++) {
//
//            for (CarbonNode carbon : fullereneArray) {
//
//                if (carbon.isToCalculate()) {
//                    carbon.calculateNewPosition();
//                    carbon.calculateR();
//                    carbon.setPreviousVector(carbon.getActualVector());
//                    carbon.setToCalculate(false);
//                    carbon.setNeighboursToCalculate(true);
//                    carbon.calculateEnergy();
//                    carbon.calculateForce();
//                }
//
//
//
//                System.out.println(Arrays.toString(carbon.getBonds()));
//
//            }
//        }
        System.out.println(Arrays.toString(fullereneArray));
    }

}
