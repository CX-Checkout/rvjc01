package befaster.solutions;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private static final Map<Character, Integer> prices = new HashMap<>();

    static {
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        //special stocks
        prices.put('a', 130);
        prices.put('b', 45);
    }

    public static Integer checkout(String skus) {
        int result = 0;
        Map<Character, Integer> positions = new HashMap<>();
        System.out.println("SKUs " + skus);
        for (char ch: skus.toCharArray()) {
            if (!positions.containsKey(ch)) {
                positions.put(ch, 0);
            }
            positions.put(ch, positions.get(ch) + 1);
        }
    }
}
