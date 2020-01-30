package kuklinski.calculation;

import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.List;

public class Case3 {

    private static int x = 0;
    private static int y = 1;
    private static int z = 2;


    public static void calculateCase3(Fullerene fullerene) {
        double externalF = 0.1;
        Fullerene.p = 0.002;
        CarbonNode[] fullereneArray = fullerene.getFullereneArray();
        for (CarbonNode node : fullereneArray) {
            List<Double> actualVector = node.getActualVector();
            double v = caluclateVlength(node);

            double externalFx = (actualVector.get(x) / v ) * externalF;
            double externalFy = (actualVector.get(y) / v ) * externalF;
            double externalFz = (actualVector.get(z) / v ) * externalF;

            node.setExternalFx(externalFx);
            node.setExternalFy(externalFy);
            node.setExternalFz(externalFz);

        }

        FullereneCalculation.calculation(fullerene, 1);
    }

    private static double caluclateVlength(CarbonNode node) {
        List<Double> vector = node.getActualVector();
        return Math.sqrt(vector.get(x) * vector.get(x) + vector.get(y) * vector.get(y) + vector.get(z) * vector.get(z));
    }
}
