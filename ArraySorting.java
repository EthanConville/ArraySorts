import java.util.Random;
public class ArraySorting {
    static boolean useCutoff = true;
    static int cutoff = 10;

    //main will generate the different arrays and time their outputs
    public static void main(String[] args) {

        int[] size = {50, 500, 1000, 2000, 5000, 10000};

        //for loop to test different sizes of arrays
        for (int j : size) {
            int[] testArray = createArray(j);

            long startTime = System.nanoTime();
            Insertion(testArray, 0, testArray.length);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Insertion sort with " + j + " elements took " + duration + " Nanoseconds");

            startTime = System.nanoTime();
            Heap(testArray);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Heap sort with " + j + " elements took " + duration + " Nanoseconds");

            startTime = System.nanoTime();
            mergeSort(testArray, 0, (testArray.length -1));
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Merge sort with " + j + " elements took " + duration + "Nanoseconds");

            useCutoff = true;
            cutoff = 10;
            startTime = System.nanoTime();
            quickSort(testArray, 0, testArray.length -1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Quick sort with cutoff true at 10 and with " + j + " elements took " + duration + "Nanoseconds");

            useCutoff = true;
            cutoff = 50;
            startTime = System.nanoTime();
            quickSort(testArray, 0, testArray.length -1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Quick sort with cutoff true at 50 and with " + j + " elements took " + duration + "Nanoseconds");

            useCutoff = true;
            cutoff = 200;
            startTime = System.nanoTime();
            quickSort(testArray, 0, testArray.length -1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Quick sort with cutoff true at 100 and with " + j + " elements took " + duration + "Nanoseconds");

            useCutoff = false;
            startTime = System.nanoTime();
            quickSort(testArray, 0, testArray.length -1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Quick sort with  cutoff false and with " + j + " elements took " + duration + "Nanoseconds");

        }
    }

    //function to create arrays of input size
    public static int [] createArray(int size) {
    Random rand = new Random();
    int [] arr = new int[size];
    for(int i = 0; i < size; i++){
        arr[i] = rand.nextInt(10000);
    }
    return arr;
    }

    //Best case complexity is O(N) (presorted) worst case O(N^2) (list is in
    // reverse sorted order)
    //Outer for loop goes through every value in the array (starting at second value)
    //Inner for loop starts at whatever index the outer for loop is at minus one
    //Inner loop checks if it's value is greater than Outer loop's value. If it is, switches them
    // else continues.
    public static void Insertion(int[] array, int left, int right) {

        for (int i = left + 1; i < (right - left); i++) {
            int temp = array[i];
            for (int j = i; j > 0 && temp < array[j - left]; j--) {
                array[j] = array[j - left];
                array[j] = temp;
            }
        }
    }

    public static void Heap(int[] array) {
        int n = array.length;

        //build heap. called log(n) times
        for (int i = n / 2 - 1; i >= 0; i--)
            Heapify(array, i, n);

        //Take all elements out of heap one at a time
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            Heapify(array, i, 0);
        }
    }

    //heap sort function to create the heap
    public static void Heapify(int[] array, int i, int n) {
        //find largest
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        //check if largest is actually largest
        if (left < n && array[left] > largest) {
            largest = left;
        }
        //same but for other child
        if (right < n && array[left] > largest) {
            largest = right;
        }
        //recursive step
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            Heapify(array, n, largest);
        }

    }

    public static void swap(int[] array, int i, int j) {
        array[i] = (array[i] + array[j]) - (array[j] = array[i]);
    }

    public static void merge(int[] array, int start, int mid, int end) {
        int i, j ,k;
        int n1 = mid - start + 1;
        int n2 = end - mid;

        //temp arrays
        int [] leftArray = new int[n1];
        int [] rightArray = new int[n2];

        //copy data to temps
        for(i = 0; i < n1; ++i){
            leftArray[i] = array[start +i];
        }
        for(j = 0; j < n2; ++j){
            rightArray[j] = array[mid + 1 + j];
        }

        i = 0;
        j = 0;
        k = start;

        while( i < n1 && j < n2){
            if(leftArray[i] <= rightArray[j]){
                array[k] = leftArray[i];
                i++;
            }
            else{
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while(i<n1){
            array[k] = leftArray[i];
            i++;
            k++;
        }
         while(j<n2){
             array[k] = rightArray[j];
             j++;
             k++;
         }
        }

    public static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    public static void quickSort(int[] array, int left, int right) {
        int size = right - left + 1;

        if ((size <= cutoff && useCutoff)) {
            Insertion(array, left, right);
        } else if (size > 2){
            int median = medianOf3(array, left, right);
            int partition = partitionArray(array, left, right, median);
            quickSort(array, left, partition - 1);
            quickSort(array, partition + 1, right);
        }

    }

    public static int medianOf3(int[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[left] > array[center]) {
            swap(array, left, center);
        }

        if (array[left] > array[right]) {
            swap(array, left, right);
        }

        if (array[center] > array[right]) {
            swap(array, center, right);
        }

        swap(array, center, right - 1);
        return array[right - 1];
    }

    public static int partitionArray(int[] array, int left, int right, int pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;
        while (true) {
            while (array[++leftPtr] < pivot) ;
            while (array[--rightPtr] > pivot) ;
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(array, leftPtr, rightPtr);
            }
        }
        swap(array, leftPtr, right - 1);
        return leftPtr;
    }
}