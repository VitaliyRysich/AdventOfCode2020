package reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private final static String PATH = "src/main/resources/tasks/";

    public static List<Integer> readFileLineByLine (String fileName){
        List<Integer> list = new ArrayList<>();

        try  {

            Path path = Paths.get(PATH + fileName);
            Files.lines(path)
                    .forEach(line -> list.add(Integer.parseInt(line)));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return list;
        }
    }
}
