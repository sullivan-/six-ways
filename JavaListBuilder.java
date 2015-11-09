import java.util.List;
import java.util.ArrayList;

public class JavaListBuilder {

    public List<Integer> buildListFromRange() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            result.add(i * i);
        }
        return result;
    }

}
