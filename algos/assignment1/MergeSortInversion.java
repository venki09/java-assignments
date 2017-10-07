package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MergeSortInversion {

    private static long invCount = 0;
    public static void main(String args[]) throws IOException, URISyntaxException {

        List<Integer> inputList = new ArrayList<>();

        // Read input from file and construct an array out of it
        URL url = MergeSortInversion.class.getResource("IntegerArray.txt");
        File f = new File(url.toURI());
        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        while((line = br.readLine()) != null) {
            inputList.add(Integer.valueOf(line));
        }

        Integer[] inputArray = inputList.toArray(new Integer[0]);
        new MergeSortInversion().countInversion(inputArray, 0, inputArray.length - 1);

        // Call the countInversion method to get the number of inversions applied on that unsorted array.
        System.out.println("Inversion count: " + invCount);
    }

    private void countInversion(Integer[] inputArray, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            countInversion(inputArray, start, mid);
            countInversion(inputArray, mid + 1, end);
            mergeInversionCount(inputArray, start, mid, end);
        }
    }

    private void mergeInversionCount(Integer[] array, int start, int mid, int end) {
        int size1 = mid - start + 1;
        int size2 = end - mid;

        int[] leftArray = new int[size1];
        int[] rightArray = new int[size2];

        for (int i = 0; i < size1; i++)
            leftArray[i] = array[start + i];

        for (int j = 0; j < size2; j++)
            rightArray[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = start;

        while (i < size1 && j < size2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                invCount += (mid - i + 1 - start);
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
    }
}
