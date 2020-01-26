package kuklinski.cases;

import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.LinkedList;
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

    private static double externalF = 0.1;

    public static void calculateCase2(Fullerene fullerene) {

        List<String> listToPrint = new LinkedList<>();
        Fullerene.p = 0.1;
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        setWall1(fullereneArray);
        setWall2(fullereneArray);
    }

    private static void setWall1(CarbonNode[] fullereneArray) {
        CarbonNode c3 = fullereneArray[2];
        CarbonNode c4 = fullereneArray[3];
        CarbonNode c28 = fullereneArray[27];
        CarbonNode c31 = fullereneArray[30];
        CarbonNode c48 = fullereneArray[47];
        CarbonNode c60 = fullereneArray[59];

        c3.setExternalFx(-2.28 * externalF);
        c3.setExternalFy(6.03 * externalF);

        c4.setExternalFx(-2.28 * externalF);
        c4.setExternalFy(6.03 * externalF);

        c28.setExternalFx(-2.28 * externalF);
        c28.setExternalFy(6.03 * externalF);

        c31.setExternalFx(-2.34 * externalF);
        c31.setExternalFy(6.01 * externalF);

        c48.setExternalFx(-2.28 * externalF);
        c48.setExternalFy(6.03 * externalF);

        c60.setExternalFx(-2.34 * externalF);
        c60.setExternalFy(6.03 * externalF);
    }

    private static void setWall2(CarbonNode[] fullereneArray) {
        CarbonNode c1 = fullereneArray[0];
        CarbonNode c7 = fullereneArray[6];
        CarbonNode c16 = fullereneArray[15];
        CarbonNode c38 = fullereneArray[37];
        CarbonNode c50 = fullereneArray[49];
        CarbonNode c58 = fullereneArray[58];

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
    }
}
