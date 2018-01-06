package befaster.solutions;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Map<Character, Integer> positions = new HashMap<>();
        System.out.println("SKUs " + skus);
        for (char ch: skus.toCharArray()) {
            if (!positions.containsKey(ch)) {
                positions.put(ch, 0);
            }
            positions.put(ch, positions.get(ch) + 1);
        }
        if (!isValid(positions)) {
            return -1;
        }
        Map<Character, Integer> receipt = translateReceipt(positions);
        return calculateSum(receipt);
    }

    private static Integer calculateSum(Map<Character, Integer> receipt) {
        int result = 0;
        for (Character ch : receipt.keySet()) {
            result += receipt.get(ch) * prices.get(ch);
        }
        return result;
    }

    private static boolean isValid(Map<Character, Integer> receipt) {
        Set<Character> skus = new HashSet<>(receipt.keySet());
        skus.removeAll(prices.keySet());
        return skus.isEmpty();
    }

    private static Map<Character, Integer> translateReceipt(Map<Character, Integer> receipt) {
        Map<Character, Integer> result = new HashMap<>();

        Integer countA = receipt.get('A');
        result.put('a', countA / 3);
        result.put('A', countA % 3);

        Integer countB = receipt.get('B');
        result.put('b', countB / 2);
        result.put('B', countB % 2);

        result.put('C', receipt.get('C'));
        result.put('D', receipt.get('D'));

        return result;
    }
}
