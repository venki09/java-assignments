package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeSortInversion {

    public static void main(String args[]) throws IOException {

        List<Integer> inputList = new ArrayList<Integer>();
        String absolutePath = new File("").getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(absolutePath + "/algos/assignment1/IntegerArray.txt"));
        String line;
        while((line = br.readLine()) != null) {
            inputList.add(Integer.valueOf(line));
        }

        Integer[] inputArray = inputList.stream().toArray(Integer[]::new);

        // Call the countInversion method to get the number of inversions applied on that unsorted array.
        System.out.print("Inversion count of the given array is: " +
                new MergeSortInversion().countInversion(inputArray, 0, inputArray.length - 1));
    }

    private long countInversion(Integer[] inputArray, int start, int end) {

        long inversionCount = 0;

        if (start < end) {
            int mid = (start + end) / 2;

            inversionCount = countInversion(inputArray, start, mid);
            inversionCount += countInversion(inputArray, mid + 1, end);

            inversionCount += mergeInversionCount(inputArray, start, mid, end);
        }

        return inversionCount;
    }

    private long mergeInversionCount(Integer[] array, int start, int mid, int end) {
        int size1 = mid - start + 1;
        int size2 = end - mid;
        long count = 0;

        int[] leftArray = new int[size1];
        int[] rightArray = new int[size2];

        for (int i = 0; i < size1; ++i)
            leftArray[i] = array[start + i];

        for (int j = 0; j < size2; ++j)
            rightArray[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = start;

        while (i < size1 && j < size2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                count += (mid - i + 1 - start);
                j++;
            }
            k++;
        }

        while (i < size1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < size2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
        return count;
    }
}
