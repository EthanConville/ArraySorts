# ArraySorts
Project to test different methods of array sorting and compare actual execution time


Algorithm Complexity
Insertion Sort-
Worst case for insertion sort is O(N^2) if the list is in reverse sorted order, then this algorithm will
take longer O(N) time because it will have to change the position of every element N times.
Merge Sort
Overall Complexity O(NlogN). Recursion is done log n times and the time to do overall merge is
n time so the overall complexity is O(NlogN).
Heap Sort
Heapsort Complexity is O(NlogN). Calls a function that is basically deleteMin (complexity logN)
N times, so has complexity O(NlogN).
Quick Sort
Quick sort is worst case O(N^2). This is because the pivot position could thoerectically always
be the smallest possible (in the implementation done, it would be 3rd smallest because of 3
values used)
