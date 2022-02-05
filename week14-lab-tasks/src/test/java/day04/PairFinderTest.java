package day04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairFinderTest {

    @Test
    void findPairsTest() {

        PairFinder pairFinder = new PairFinder();

        List<Integer> arr = Arrays.asList(7, 1, 5, 7, 3, 3, 5, 7, 6, 7);
        assertEquals(4, pairFinder.findPairs(arr));

        List<Integer> arr2 = Arrays.asList(5, 1, 4, 5);
        assertEquals(1, pairFinder.findPairs(arr2));

        List<Integer> arr3 = Arrays.asList(5, 5, 5, 5);
        assertEquals(2, pairFinder.findPairs(arr3));

        List<Integer> arr4 = Arrays.asList(5, 4, 4, 5, 5, 5, 4, 4, 5);
        assertEquals(4, pairFinder.findPairs(arr4));
    }

    @Test
    void findPairs2Test() {

        PairFinder pairFinder = new PairFinder();

        int[] arr = {7, 1, 5, 7, 3, 3, 5, 7, 6, 7};
        assertEquals(4, pairFinder.findPairs2(arr));

        int[] arr2 = {5, 1, 4, 5};
        assertEquals(1, pairFinder.findPairs2(arr2));

        int[] arr3 = {5, 5, 5, 5};
        assertEquals(2, pairFinder.findPairs2(arr3));

        int[] arr4 = {5, 4, 4, 5, 5, 5, 4, 4, 5};
        assertEquals(4, pairFinder.findPairs2(arr4));
    }
}