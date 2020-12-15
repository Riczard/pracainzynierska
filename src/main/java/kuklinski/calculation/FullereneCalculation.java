package kuklinski.calculation;

import kuklinski.Parsers;
import kuklinski.model.Fullerene;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static kuklinski.App.type;

public class FullereneCalculation {

    public static void calculation(Fullerene fullerene, double lenghtOfexternalF, boolean stretching, int printStep) {
        double endCondition;
        String fullereneData, energyData;
        double maxF, maxW, externalF=lenghtOfexternalF;
        int i;
        List<String> listToPrint = new LinkedList<>();
        List<String> listToPrint1 = new LinkedList<>();

        fullerene.calculateCarbonEnergy();
        fullerene.calculateTotalEnergy();
        String heading = "indexWezła;sasiad1;sasiad2;sasiad3;APx;APy;APz;R;r1;r2;r3;przesuniecie;Energia;Fx;Fy;Fz;F1;F2;F3;EnergiaCałkowita;Rsrednie\n";
        String heading1 = "Sila;Energia;Rsr\n";

        listToPrint.add(heading);
        listToPrint.add(fullerene.getFullereneData());
        Parsers.createTxt(listToPrint);

        listToPrint1.add(heading1);
        Parsers.createEnergyTxt(listToPrint1);
        energyData = fullerene.getEnergyData(0);
        Parsers.appendEnergyToTxt(energyData);

        endCondition = 0.0001;

        for(int k = 0; k<50; k++) {
            i = 1;
            fullerene.calculateCarbonForce();
            maxF = fullerene.getMaxForce();
            System.out.println(" dlugosc sily zewnetrznej = " + externalF);
            while (maxF > endCondition) {
                fullerene.calculateNewPositions();
                fullerene.calculateMaxWayLength();
                maxW = fullerene.getMaxWayLength();
                if ( (stretching && maxW>0.05) || (!stretching && maxW>0.5) )
                    break;
                fullerene.calculateR();
                fullerene.calculateCarbonEnergy();
                fullerene.calculateTotalEnergy();
                if ( i%10000==0 )
                    System.out.println(" dlugosc maksymalnej drogi = " + maxW);
//                if ( i%1000000==0 )
//                    System.out.println(i+" dlugosc maksymalnej sily = " + maxF);
                if ( i%printStep==0) {
                    fullereneData = fullerene.getFullereneData();
                    Parsers.appendToTxt(" dlugosc sily zewnetrznej = " + externalF + " \n");
                    Parsers.appendToTxt(fullereneData);
                }
                i++;
                if (i > 100000000) break;
                fullerene.calculateCarbonForce();
                maxF = fullerene.getMaxForce();
            }
            fullereneData = fullerene.getFullereneData();
            Parsers.appendToTxt(" dlugosc sily zewnetrznej = " + externalF + " \n");
            Parsers.appendToTxt(fullereneData);
//            System.out.println(i + " dlugosc maksymalnej sily = " + maxF);
            fullerene.calculateMaxWayLength();
            maxW = fullerene.getMaxWayLength();
            System.out.println(" dlugosc maksymalnej drogi = " + maxW);

            if ( stretching )
                energyData = fullerene.getEnergyData(externalF);
            else
                energyData = fullerene.getEnergyData(-externalF);
            Parsers.appendEnergyToTxt(energyData);

            if ( (stretching && maxW>0.05) || (!stretching && maxW>0.5) )
                break;

            externalF += lenghtOfexternalF;
//            externalF += 0.001;
            externalF = Math.round(externalF*100)/100.0;

            switch (type) {
                case type1: Case1.setExternalForce(fullerene, externalF, stretching); break;
                case type2: Case2.setExternalForce(fullerene, externalF, stretching); break;
                case type3: Case3.setExternalForce(fullerene, externalF, stretching);break;
            }
        }
    }
}
