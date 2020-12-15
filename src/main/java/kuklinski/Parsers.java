package kuklinski;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parsers {

    public static List<String> getFullereneDataFromTXT(String fileName, Persons person) {

        List<String> list = new ArrayList<>();
        if ( person==Persons.JK ) {
            File file = new File("C:\\Users\\Jaś\\Documents\\Kraków\\Prace inżynierskie\\Fulereny\\pracainzynierska-master\\src\\main\\resources\\c60card.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                list = br.lines().collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
             try (BufferedReader br = new BufferedReader(new InputStreamReader(Parsers.class.getResourceAsStream("/" + fileName)))) {
                 list = br.lines().collect(Collectors.toList());
             } catch (IOException e) {
                e.printStackTrace();
             }
        }

        return list;
    }

    public static void createTxt(List<String> listToPrint) {
        try {
            FileWriter writer = new FileWriter("data");
            BufferedWriter out = new BufferedWriter(writer);
            for (String str: listToPrint){
                out.write(str);
                out.flush();
            }
            writer.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToTxt(String text) {
        try(FileWriter fw = new FileWriter("data", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.write(text);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static void createEnergyTxt(List<String> listToPrint) {
        try {
            FileWriter writer = new FileWriter("energy.txt");
            BufferedWriter out = new BufferedWriter(writer);
            for (String str: listToPrint){
                out.write(str);
                out.flush();
            }
            writer.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendEnergyToTxt(String text) {
        try(FileWriter fw = new FileWriter("energy.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.write(text);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
