import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CalculatorServiceTest {
    
    private final CalculatorService service = new CalculatorService();

    @Test
    public void calculateTotalOrder() {
        RectangularPrismBo smallPrism = new RectangularPrismBo(1, 2, 3);
        RectangularPrismBo largePrism = new RectangularPrismBo(10, 2, 3);
        Long result =  service.calculateTotalOrder(Arrays.asList(smallPrism, largePrism));
        assertThat("Should sum up totals", result,
                equalTo(service.calculateTotal(smallPrism) + service.calculateTotal(largePrism)));
    }

    @Test
    public void findCubicOrdered() {
        RectangularPrismBo smallPrism = new RectangularPrismBo(1, 2, 3);
        RectangularPrismBo largePrism = new RectangularPrismBo(10, 3, 3);
        RectangularPrismBo smallCubicPrism = new RectangularPrismBo(3, 3, 3);
        RectangularPrismBo largeCubicPrism = new RectangularPrismBo(10, 10, 10);
        List<RectangularPrismBo> result =  service.findCubicOrdered(Arrays.asList(smallPrism, largePrism, smallCubicPrism, largeCubicPrism));
        assertThat("Should be DESC ordered with cubic only", result.size(), equalTo(2));
        assertThat("Should be DESC ordered with cubic only", result.get(0), equalTo(largeCubicPrism));
        assertThat("Should be DESC ordered with cubic only", result.get(1), equalTo(smallCubicPrism));
    }

    @Test
    public void test_NoCubic() {
        RectangularPrismBo smallPrism = new RectangularPrismBo(1, 2, 3);
        RectangularPrismBo largePrism = new RectangularPrismBo(10, 3, 3);
        List<RectangularPrismBo> result =  service.findCubicOrdered(Arrays.asList(smallPrism, largePrism));
        assertThat("There is no cubic", result.isEmpty());
    }

    @Test
    public void countPrisms() {
        RectangularPrismBo smallPrism = new RectangularPrismBo(1, 2, 3);
        RectangularPrismBo largePrism = new RectangularPrismBo(10, 2, 3);
        RectangularPrismBo sameButDifferentPrism = new RectangularPrismBo(3, 2, 1);
        Map<RectangularPrismBo, Integer> result =  service.countPrisms(Arrays.asList(
                smallPrism,
                largePrism,
                largePrism,
                new RectangularPrismBo(1, 2, 3), // should be same as the smallPrism
                smallPrism,
                sameButDifferentPrism));
        assertThat("Count should be matching", result.get(smallPrism), equalTo(3));
        assertThat("Count should be matching", result.get(largePrism), equalTo(2));
        assertThat("Count should be matching", result.get(sameButDifferentPrism), equalTo(1));
    }

    @Test
    public void calculateTotal() {
        RectangularPrismBo smallPrism = new RectangularPrismBo(2, 3, 4);
        long result =  service.calculateTotal(smallPrism);
        // calculation should be 2hl+2lw+2hw+extra
        // extra is defined as smallest side so 'h*l' in this case
        assertThat("Calculated value should be", result, equalTo(2*2*3 + 2*3*4 + 2*2*4 + 2*3L));
     }

    @Test
    public void calculateExtra() {
        long result = service.calculateExtra(2, 3, 4);
        long expected = 6L;
        assertThat("Should be the two smallest", result, equalTo(expected));

        result = service.calculateExtra(3, 3, 4);
        expected = 9L;
        assertThat("Should be the two smallest", result, equalTo(expected));

        result = service.calculateExtra(4, 9, 7);
        expected = 28L;
        assertThat("Should be the two smallest", result, equalTo(expected));

        result = service.calculateExtra(1, 1, 1);
        expected = 1L;
        assertThat("Should be the two smallest", result, equalTo(expected));

        result = service.calculateExtra(5, 2, 1);
        expected = 2L;
        assertThat("Should be the two smallest", result, equalTo(expected));
    }
}