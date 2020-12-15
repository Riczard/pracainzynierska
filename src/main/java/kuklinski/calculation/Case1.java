package kuklinski.calculation;

import kuklinski.Parsers;
import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.Arrays;

//  C   14    0.684800    0.000000   -3.449997    1   18   39   41
//  C   24    0.684800    0.000000    3.449997    1   23   25   51

//sciskanie przeciwleglych atomow
public class Case1 {

    public static void setExternalForce(Fullerene fullerene, double lenghtOfexternalF, boolean stretching) {
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        double externalF = lenghtOfexternalF*(stretching? 1: -1);

        firstCarbon.setExternalFz(externalF);
        secondCarbon.setExternalFz(-externalF);
        firstCarbon.calculateForce();
        secondCarbon.calculateForce();
    }


    public static void calculateCase1(Fullerene fullerene) {

        double lenghtOfexternalF = 0.001;
        boolean stretching = false;//true;
        int printStep = 10000000;

        Fullerene.p = 0.0025;
        setExternalForce(fullerene, lenghtOfexternalF, stretching);
        FullereneCalculation.calculation(fullerene, lenghtOfexternalF, stretching, printStep);
    }
}
