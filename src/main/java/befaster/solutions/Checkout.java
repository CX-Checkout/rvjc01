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

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<String, Integer> positionsAndCounts = new HashMap<>();
        for (char ch : alphabet.toCharArray()) {
            String sku = String.valueOf(ch);
            int count = nvl(receipt.get(sku));
            switch (sku) {
                case "E":
                    positionsAndCounts.put("B", Math.max(0, positionsAndCounts.get("B") - (count / 2)));
                    break;
                case "N":
                    positionsAndCounts.put("M", Math.max(0, positionsAndCounts.get("M") - (count / 3)));
                    break;
                case "R":
                    positionsAndCounts.put("Q", Math.max(0, positionsAndCounts.get("Q") - (count / 3)));
                    break;
            }
            positionsAndCounts.put(sku, count);
        }

        for (String sku : positionsAndCounts.keySet()) {
            int count = nvl(positionsAndCounts.get(sku));
            switch (sku) {
                case "A" :
                    result.put("5A", count / 5);
                    count = count % 5;
                    result.put("3A", count / 3);
                    result.put("A", count % 3);
                    break;
                case "B" :
                    result.put("2B", count / 2);
                    result.put("B", count % 2);
                    break;
                case "F" :
                    result.put("3F", count / 3);
                    result.put("F", count % 3);
                    break;
                case "H" :
                    result.put("10H", count / 10);
                    count = count % 10;
                    result.put("5H", count / 5);
                    result.put("H", count % 5);
                    break;
                case "K" :
                    result.put("2K", count / 2);
                    result.put("K", count % 2);
                    break;
                case "P" :
                    result.put("5P", count / 5);
                    result.put("P", count % 5);
                    break;
                case "Q" :
                    result.put("3Q", count / 3);
                    result.put("Q", count % 3);
                    break;
                case "U" :
                    result.put("4U", count / 4);
                    result.put("U", count % 4);
                    break;
                case "V" :
                    result.put("3V", count / 3);
                    count = count % 3;
                    result.put("2V", count / 2);
                    result.put("V", count % 2);
                    break;
                default:
                    result.put(sku, positionsAndCounts.get(sku));
            }
        }

        return result;
    }

    private static int nvl(Integer val) {
        if (val == null) {
            return 0;
        }
        return val;
    }
}
