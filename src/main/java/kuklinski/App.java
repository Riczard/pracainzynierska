package kuklinski;

import kuklinski.calculation.Case1;
import kuklinski.calculation.Case2;
import kuklinski.calculation.Case3;
import kuklinski.model.CarbonNode;
import kuklinski.model.Fullerene;

import java.util.Arrays;
import java.util.List;


enum Persons {PK,JK};
//enum Types {type1,type2,type3};

//  C   14    0.684800    0.000000   -3.449997    1   18   39   41
//  C   24    0.684800    0.000000    3.449997    1   23   25   51
//  C   41   -0.684800    0.000000   -3.449997    1   14   42   45
//  C   51   -0.684800    0.000000    3.449997    1   24   30   32


public class App {
    public enum Types {type1,type2,type3};
    public static Types type;
    public static void main(String[] args) {

        int numberOfAtoms = 0;
        Persons person = Persons.JK;
        List<String> data = Parsers.getFullereneDataFromTXT("c60card.txt", person);

        Fullerene fullerene = new Fullerene(Integer.parseInt(data.get(numberOfAtoms)));
        data.remove(numberOfAtoms);
        fullerene.fill(data);
        fullerene.calculateR0();
        fullerene.calculateR();
        fullerene.setStartVector();

        type = Types.type3;

        switch (type) {
            case type1: Case1.calculateCase1(fullerene); break;
            case type2: Case2.calculateCase2(fullerene); break;
            case type3: Case3.calculateCase3(fullerene); break;
        }
    }

}
