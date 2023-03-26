import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RectangularPrismBo {

    private final int height;
    private final int length;
    private final int width;

}
