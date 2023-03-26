import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class RectangularPrismBo {

    private final int height;
    private final int length;
    private final int width;

}
