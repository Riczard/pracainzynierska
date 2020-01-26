package kuklinski.calculation;

import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

//  C   14    0.684800    0.000000   -3.449997    1   18   39   41
//  C   24    0.684800    0.000000    3.449997    1   23   25   51

//sciskanie przeciwleglych atomow
public class Case1 {

    public static void calculateCase1(Fullerene fullerene) {

        Fullerene.p = 0.001;
        double endCondition = Math.pow(10, -4);
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        CarbonNode firstCarbon = fullereneArray[13];
        CarbonNode secondCarbon = fullereneArray[23];

        firstCarbon.setExternalFz(5);
        secondCarbon.setExternalFz(-5);
        firstCarbon.calculateForce();
        secondCarbon.calculateForce();

        FullereneCalculation.calculation(fullerene, endCondition);
    }
}
