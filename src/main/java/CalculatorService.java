import java.util.*;
import java.util.stream.Collectors;

public class CalculatorService {

    public long calculateTotalOrder(List<RectangularPrismBo> prisms) {
        return prisms.stream().mapToLong(this::calculateTotal).sum();
    }

    long calculateTotal(RectangularPrismBo prismBo) {
        return 2 * prismBo.getLength() * prismBo.getWidth()
                + 2 * prismBo.getWidth() * prismBo.getHeight()
                + 2 * prismBo.getHeight() * prismBo.getLength()
                + calculateExtra(prismBo.getHeight(), prismBo.getLength(), prismBo.getWidth());
    }

    long calculateExtra(int height, int length, int width) {
        int smallestA;
        int smallestB;
        int largest = height;

        if (largest < length) {
            smallestA = largest;
            largest = length;
        } else {
            smallestA = length;
        }

        if (largest < width) {
            smallestB = largest;
            largest = width;
        } else {
            smallestB = width;
        }

        return smallestA * smallestB;
    }

    public List<RectangularPrismBo> findCubicOrdered(List<RectangularPrismBo> prisms) {
        return prisms.stream()
                .filter(prismBo -> (prismBo.getLength() == prismBo.getWidth()) && (prismBo.getWidth() == prismBo.getHeight()))
                .sorted(Collections.reverseOrder(Comparator.comparingInt(RectangularPrismBo::getWidth)))
                .collect(Collectors.toList());
    }

    public Map<RectangularPrismBo, Integer> countPrisms(List<RectangularPrismBo> prisms) {
        Map<RectangularPrismBo, Integer> similar = new HashMap<>();
        prisms.forEach(prismBo -> similar.put(prismBo, 0));
        for (RectangularPrismBo prismBo : prisms) {
            Integer value = similar.get(prismBo);
            similar.put(prismBo, value + 1);
        }
        return similar;
    }
}