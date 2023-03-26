import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RenovationApp {

    public static void main(String[] args) {
        System.out.println("Hello Peridot!");

        if (args.length != 1) {
            throw new IllegalArgumentException("Please provide exactly one argument for the input file.");
        }

        List<RectangularPrismBo> prisms = readUpFile(args[0]);
    }

    private static List<RectangularPrismBo> readUpFile(String fileName) {
        List<RectangularPrismBo> prism = new ArrayList<>();
        File inputFile = new File(fileName);
        if (inputFile.exists()) {
            try (InputStream stream = new FileInputStream(inputFile)) {
                Scanner sc = new Scanner(stream);
                while (sc.hasNext()) {
                    String[] data = sc.next().split("x");
                    prism.add(RectangularPrismBo.builder()
                                .length(Integer.parseInt(data[0]))
                                .width(Integer.parseInt(data[1]))
                                .height(Integer.parseInt(data[2]))
                            .build());
                }
                return prism;
            } catch (IOException | NumberFormatException e) {
                throw new IllegalStateException("Error during parse input file.", e);
            }
        } else {
            throw new IllegalArgumentException("Input file cannot be found.");
        }
    }
}
