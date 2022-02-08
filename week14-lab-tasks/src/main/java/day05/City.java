package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class City {

    private Map<String, List<Integer>> streets = new LinkedHashMap<>();

    public Map<String, List<Integer>> getStreets() {
        return streets;
    }

    public void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" ");
                if (!setUpStreet(temp[0], Integer.parseInt(temp[1]))) {
                    addNewNumber(temp[0], Integer.parseInt(temp[1]));
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Something went wrong", ioe);
        }
    }

    private void addNewNumber(String street, int number) {

//        if (number == 0) {
//            int maxEven = streets.get(street).stream()
//                    .mapToInt(i -> i)
//                    .filter(i -> i % 2 == 0)
//                    .max()
//                    .getAsInt();
//        } else {
//            int maxOdd = streets.get(street).stream().mapToInt(i -> i).filter(i -> i % 2 == 1).max().getAsInt();
//        }
        // vagy egy sorban:
        int maxNumber = streets.get(street).stream().mapToInt(i -> i).
                filter(i -> i % 2 == number).max().orElse(-number);
        // a .orElse(-number) azért kell a végére, mert ha csak páros, vagy csak páratlan számok vannak a listában,
        // akkor a max() hibát dobna...
        streets.get(street).add(maxNumber + 2);
    }

    private boolean setUpStreet(String street, int number) {
        if (!streets.containsKey(street)) {
            streets.put(street, new ArrayList<>());
            if (number == 0) {
                streets.get(street).add(2);
            } else {
                streets.get(street).add(1);
            }
            return true;
        }
        return false;
    }

    // stream szorgalmi: utcanév alapján lekérdezni a páros házszámok darabszámát

    public long countOddNumbers(String street) {
        return streets.entrySet().stream()
                .filter(e -> e.getKey().equals(street))
                .map(e -> e.getValue())
                .flatMap(e -> e.stream())
                // vagy:
//                .flatMap(List::stream)
                .filter(e -> e % 2 == 0)
                .count();
    }

    // sokkal egyszerűbben:
    public long countOddNumbersSimple(String street) {
        return streets.get(street).stream()
                .filter(k -> k % 2 == 0)
                .count();
    }

    public static void main(String[] args) {

        City city = new City();
        city.readFromFile(Path.of("src/main/resources/streets.txt"));

        city.streets.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        System.out.println(city.countOddNumbers("Kossuth"));
        System.out.println(city.countOddNumbers("Petofi"));
        System.out.println();

        System.out.println(city.countOddNumbersSimple("Kossuth"));
        System.out.println(city.countOddNumbersSimple("Petofi"));
    }
}
