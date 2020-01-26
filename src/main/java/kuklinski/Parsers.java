package kuklinski;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parsers {


    public static List<String> getFullereneDataFromTXT(String fileName) {

        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Parsers.class.getResourceAsStream("/" + fileName)))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
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
}
