package kuklinski;

import kuklinski.calculation.Case1;

import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

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
        //prosze wybrac tylko 1 case, reszte zakomentowac

        Case1.calculateCase1(fullerene);
//        Case2.calculateCase2(fullerene);
    }

}
