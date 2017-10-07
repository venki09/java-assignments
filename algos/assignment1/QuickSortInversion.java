package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuickSortInversion {

    private static long invCount = 0;
    public static void main(String args[]) throws IOException, URISyntaxException {

        // Read input from file and construct an array out of it
        List<Integer> inputList = new ArrayList<>();
        URL url = MergeSortInversion.class.getResource("QuickSort.txt");
        File f = new File(url.toURI());
        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        while((line = br.readLine()) != null) {
            inputList.add(Integer.valueOf(line));
        }

        Integer[] inputArray = inputList.toArray(new Integer[0]);
        new QuickSortInversion().quickSort(inputArray, 0, inputArray.length - 1);

        // Call the quickSort method to get the number of inversions applied on that unsorted array.
        System.out.print("Number of comparison: " + invCount);
    }

    private void quickSort(Integer arr[], int low, int high)
    {
        if (low < high)
        {
            /* p is partitioning index, arr[p] is
              now at right place */
            int p = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, p-1);
            quickSort(arr, p+1, high);
        }
    }

    private int partition(Integer arr[], int low, int high)
    {
        int mid = (low+high)/2;
        int median = max(min(arr[low], arr[mid]), min(max(arr[low], arr[mid]), arr[high]));

        // Swap arr[median] with arr[high] to follow randomized partition logic
        int temp = arr[median];
        arr[median] = arr[high];
        arr[high] = temp;

        int pivot = arr[high];

        int i = low-1;

        for (int j=low; j<high; j++)
        {
            if (arr[j] <= pivot)
            {
                i++;

                // Increment the inversionCount since we are going to swap the values to perform sorting
                invCount++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Increment the inversionCount since we are going to swap the values to perform sorting
        invCount++;
        temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    private int max(int a, int b) {
        return a>b ? a:b;
    }

    private int min(int a, int b) {
        return a<b ? a:b;
    }
}
