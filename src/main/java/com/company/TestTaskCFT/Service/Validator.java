package com.company.TestTaskCFT.Service;


import java.util.Optional;

public class Validator {

    public static boolean validateCoordinates(int[] array, int quantityCoordinates){
        return array.length == quantityCoordinates;
    }

    public static Optional<int[]> StringToIntArrayConverter(String s) {
        String[] coordinatesSrtingArray = s.split(" ");
        int[] intArray = new int[coordinatesSrtingArray.length];
        for (int i = 0; i < coordinatesSrtingArray.length; i++) {
            try {
                intArray[i] = Integer.parseInt(coordinatesSrtingArray[i]);
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.of(intArray);
    }
}

