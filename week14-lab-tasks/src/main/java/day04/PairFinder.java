package day04;

import java.util.*;

public class PairFinder {

    public int findPairs(List<Integer> arr) {

        Map<Integer, Integer> result = new HashMap<>();
        for (Integer actual : arr) {
            if (!result.containsKey(actual)) {
                result.put(actual, 1);
            } else {
                result.put(actual, result.get(actual) + 1);
            }
        }
        return result.values().stream()
                .mapToInt(o -> o / 2)
                .sum();
    }

    public int findPairs2(int[] arr) {
        int[] result = Arrays.copyOf(arr, arr.length);
        Arrays.sort(result);

        int count = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[i] == result[i - 1]) {
                count++;
                i++;
            }
        }
        return count;
    }

}