import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RenovationApp {

    public static void main(String[] args) {
        System.out.println("Hello Peridot!");

        if (args.length != 1) {
            throw new IllegalArgumentException("Please provide exactly one argument for the input file.");
        }

        List<RectangularPrismBo> prisms = readUpFile(args[0]);

        CalculatorService service = new CalculatorService();

        System.out.println("\nTask 1");
        System.out.println("Total wallpaper needed: " + service.calculateTotalOrder(prisms));

        System.out.println("\nTask 2");
        service.findCubicOrdered(prisms).forEach(prismBo -> System.out.println("Cubic=" + prismBo));

        System.out.println("\nTask 3");
        Map<RectangularPrismBo, Integer> countedPrisms = service.countPrisms(prisms);
        countedPrisms.keySet().stream()
                .filter(prismBo -> countedPrisms.get(prismBo) > 1)
                .map(prismBo -> "Similar=" + prismBo + " count=" + countedPrisms.get(prismBo))
                .forEach(System.out::println);
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
