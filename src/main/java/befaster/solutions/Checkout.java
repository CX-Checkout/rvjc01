package befaster.solutions;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Checkout {

    private static final Map<String, Integer> prices = new HashMap<>();

    static {
        prices.put("A", 50);
        prices.put("B", 30);
        prices.put("C", 20);
        prices.put("D", 15);
        //special stocks
        prices.put("3A", 130);
        prices.put("2B", 45);
    }

    public static Integer checkout(String skus) {
        Map<String, Integer> positions = new HashMap<>();
        System.out.println("SKUs " + skus);
        for (char ch: skus.toCharArray()) {
            String character = String.valueOf(ch);
            if (!positions.containsKey(character)) {
                positions.put(character, 0);
            }
            positions.put(character, positions.get(character) + 1);
        }
        if (!isValid(positions)) {
            return -1;
        }
        Map<String, Integer> receipt = translateReceipt(positions);
        return calculateSum(receipt);
    }

    private static Integer calculateSum(Map<String, Integer> receipt) {
        int result = 0;
        for (String ch : receipt.keySet()) {
            result += receipt.get(ch) * prices.get(ch);
        }
        return result;
    }

    private static boolean isValid(Map<String, Integer> receipt) {
        Set<String> skus = new HashSet<>(receipt.keySet());
        skus.removeAll(prices.keySet());
        return skus.isEmpty();
    }

    private static Map<String, Integer> translateReceipt(Map<String, Integer> receipt) {
        Map<String, Integer> result = new HashMap<>();

        Integer countA = nvl(receipt.get("A"));
        result.put("3A", countA / 3);
        result.put("A", countA % 3);

        Integer countB = nvl(receipt.get("B"));
        result.put("2B", countB / 2);
        result.put("B", countB % 2);

        result.put("C", nvl(receipt.get("C")));
        result.put("D", nvl(receipt.get("D")));

        return result;
    }

    private static int nvl(Integer val) {
        if (val == null) {
            return 0;
        }
        return val;
    }
}
