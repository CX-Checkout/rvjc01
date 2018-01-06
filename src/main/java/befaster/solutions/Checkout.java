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
        prices.put("E", 40);
        prices.put("F", 10);
        prices.put("G", 20);
        prices.put("H", 10);
        prices.put("I", 35);
        prices.put("J", 60);
        prices.put("K", 80);
        prices.put("L", 90);
        prices.put("M", 15);
        prices.put("N", 40);
        prices.put("O", 10);
        prices.put("P", 50);
        prices.put("Q", 30);
        prices.put("R", 50);
        prices.put("S", 30);
        prices.put("T", 20);
        prices.put("U", 40);
        prices.put("V", 50);
        prices.put("W", 20);
        prices.put("X", 90);
        prices.put("Y", 10);
        prices.put("Z", 50);
        //special stocks
        prices.put("3A", 130);
        prices.put("5A", 200);
        prices.put("2B", 45);
        prices.put("3F", 20);
        prices.put("5H", 45);
        prices.put("10H", 80);
        prices.put("2K", 150);
        prices.put("5P", 200);
        prices.put("3Q", 80);
        prices.put("4U", 120);
        prices.put("2V", 90);
        prices.put("3V", 130);
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
        Integer countB = nvl(receipt.get("B"));
        Integer countC = nvl(receipt.get("C"));
        Integer countD = nvl(receipt.get("D"));
        Integer countE = nvl(receipt.get("E"));
        Integer countF = nvl(receipt.get("F"));

        result.put("5A", countA / 5);
        countA = countA % 5;
        result.put("3A", countA / 3);
        result.put("A", countA % 3);


        countB = Math.max(0, countB - (countE / 2));
        result.put("2B", countB / 2);
        result.put("B", countB % 2);

        result.put("C", countC);
        result.put("D", countD);

        result.put("E", countE);

        result.put("3F", countF / 3);
        result.put("F", countF % 3);

        return result;
    }

    private static int nvl(Integer val) {
        if (val == null) {
            return 0;
        }
        return val;
    }
}
