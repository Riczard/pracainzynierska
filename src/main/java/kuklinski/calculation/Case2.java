package kuklinski.calculation;

import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.List;

//  C    3    0.000000   -3.449997    0.684800    1    6   31   48
//  C    4    2.279007   -2.579483   -0.723694    1   10   28   60
//  C   28    2.279007   -2.579483    0.723694    1    4   31   54
//  C   31    1.170975   -3.002716    1.408493    1    3   28   55
//  C   48    0.000000   -3.449997   -0.684800    1    3   19   60
//  C   60    1.170975   -3.002716   -1.408493    1    4   40   48

//  C    1    0.000000    3.449997    0.684800    1    7   26   58
//  C    7   -1.170975    3.002716    1.408493    1    1   33   50
//  C   16   -2.279007    2.579483   -0.723694    1   12   38   50
//  C   38   -1.170975    3.002716   -1.408493    1   13   16   58
//  C   50   -2.279007    2.579483    0.723694    1    7   16   57
//  C   58    0.000000    3.449997   -0.684800    1    1   38   46

//sciskanie symateycznych scianek
public class Case2 {

    public static void setExternalForce(Fullerene fullerene, double lenghtOfexternalF, boolean stretching) {

        CarbonNode[] fullereneArray = fullerene.getFullereneArray();

        setWall1(fullereneArray, lenghtOfexternalF, stretching);
        setWall2(fullereneArray, lenghtOfexternalF, stretching);
    }

    public static void calculateCase2(Fullerene fullerene) {

        double lenghtOfexternalF = 0.001;
        boolean stretching = false;//true;
        int printStep = 10000000;

        Fullerene.p = 0.002;
        setExternalForce(fullerene, lenghtOfexternalF, stretching);
        FullereneCalculation.calculation(fullerene, lenghtOfexternalF, stretching, printStep);
    }


    private static void setWall1(CarbonNode[] fullereneArray, double lenghtOfexternalF, boolean stretching) {
        CarbonNode c3 = fullereneArray[2];
        CarbonNode c4 = fullereneArray[3];
        CarbonNode c28 = fullereneArray[27];
        CarbonNode c31 = fullereneArray[30];
        CarbonNode c48 = fullereneArray[47];
        CarbonNode c60 = fullereneArray[59];

        double externalF = lenghtOfexternalF*(stretching? 1: -1);

        double fx=0.356825948, fy=-0.934170885;

        c3.setExternalFx(fx * externalF);
        c3.setExternalFy(fy * externalF);

        c4.setExternalFx(fx * externalF);
        c4.setExternalFy(fy * externalF);

        c28.setExternalFx(fx * externalF);
        c28.setExternalFy(fy * externalF);

        c31.setExternalFx(fx * externalF);
        c31.setExternalFy(fy * externalF);

        c48.setExternalFx(fx * externalF);
        c48.setExternalFy(fy * externalF);

        c60.setExternalFx(fx * externalF);
        c60.setExternalFy(fy * externalF);

        c3.calculateForce();
        c4.calculateForce();
        c28.calculateForce();
        c31.calculateForce();
        c48.calculateForce();
        c60.calculateForce();

    }

    private static void setWall2(CarbonNode[] fullereneArray, double lenghtOfexternalF, boolean stretching) {
        CarbonNode c1 = fullereneArray[0];
        CarbonNode c7 = fullereneArray[6];
        CarbonNode c16 = fullereneArray[15];
        CarbonNode c38 = fullereneArray[37];
        CarbonNode c50 = fullereneArray[49];
        CarbonNode c58 = fullereneArray[58];

        double externalF = lenghtOfexternalF*(stretching? 1: -1);
        double fx=-0.356825948, fy=0.934170885;

        c1.setExternalFx(2.28 * externalF);
        c1.setExternalFy(-6.03 * externalF);

        c7.setExternalFx(2.34 * externalF);
        c7.setExternalFy(-6.01 * externalF);

        c16.setExternalFx(2.28 * externalF);
        c16.setExternalFy(-6.03 * externalF);

        c38.setExternalFx(2.34 * externalF);
        c38.setExternalFy(-6.01 * externalF);

        c50.setExternalFx(2.28 * externalF);
        c50.setExternalFy(-6.03 * externalF);

        c58.setExternalFx(2.28 * externalF);
        c58.setExternalFy(-6.03 * externalF);

        c1.calculateForce();
        c7.calculateForce();
        c16.calculateForce();
        c38.calculateForce();
        c50.calculateForce();
        c58.calculateForce();
    }
}
