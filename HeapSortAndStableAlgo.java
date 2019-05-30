import java.util.Arrays;
import java.util.Random;

public class HeapSortAndStableAlgo {



	/* #1 HeapSort*/
	public void sort(int arr[]) 
	{ 
		int n = arr.length; 

		// Build heap (rearrange array) 
		for (int i = n / 2 - 1; i >= 0; i--) 
			heapify(arr, n, i); 

		// One by one extract an element from heap 
		for (int i=n-1; i>=0; i--) 
		{ 
			// Move current root to end 
			int temp = arr[0]; 
			arr[0] = arr[i]; 
			arr[i] = temp; 

			// call max heapify on the reduced heap 
			heapify(arr, i, 0); 
		} 
	} 

	void heapify(int arr[], int n, int i) 
	{ 
		int largest = i; // Initialize largest as root 
		int l = 2*i + 1; // left = 2*i + 1 
		int r = 2*i + 2; // right = 2*i + 2 

		// If left child is larger than root 
		if (l < n && arr[l] > arr[largest]) 
			largest = l; 

		// If right child is larger than largest so far 
		if (r < n && arr[r] > arr[largest]) 
			largest = r; 

		// If largest is not root 
		if (largest != i) 
		{ 
			int swap = arr[i]; 
			arr[i] = arr[largest]; 
			arr[largest] = swap; 

			// Recursively heapify the affected sub-tree 
			heapify(arr, n, largest); 
		} 
	} 




	static void radixsort(int arr[], int n) 
	{ 
		// Find the maximum number to know number of digits 
		int m = getMax(arr, n); 

		// Do counting sort for every digit. Note that instead 
		// of passing digit number, exp is passed. exp is 10^i 
		// where i is current digit number 
		for (int exp = 1; m/exp > 0; exp *= 10) 
			countSort(arr, n, exp); 
	}


	static int getMax(int arr[], int n) 
	{ 
		int mx = arr[0]; 
		for (int i = 1; i < n; i++) 
			if (arr[i] > mx) 
				mx = arr[i]; 
		return mx; 
	}

	static void countSort(int arr[], int n, int exp) 

	{ 
		int output[] = new int[n]; // output array 
		int i; 
		int count[] = new int[10]; 
		Arrays.fill(count,0); 

		// Store count of occurrences in count[] 
		for (i = 0; i < n; i++) 
			count[ (arr[i]/exp)%10 ]++; 

		// Change count[i] so that count[i] now contains 
		// actual position of this digit in output[] 
		for (i = 1; i < 10; i++) 
			count[i] += count[i - 1]; 

		// Build the output array 
		for (i = n - 1; i >= 0; i--) 
		{ 
			output[count[ (arr[i]/exp)%10 ] - 1] = arr[i]; 
			count[ (arr[i]/exp)%10 ]--; 
		} 

		// Copy the output array to arr[], so that arr[] now 
		// contains sorted numbers according to curent digit 
		for (i = 0; i < n; i++) 
			arr[i] = output[i]; 
	} 




	void countingSort(int arr[],int range) 
    { 
        int n = arr.length; 
  
        // The output integers array that will have sorted arr 
        int output[] = new int[n]; 
  
        // Create a count array to store count of inidividul 
        // integers and initialize count array as 0 
        int count[] = new int[range]; 
        for (int i=0; i<range; ++i) 
            count[i] = 0; 
  
        // store count of each integer 
        for (int i=0; i<n; ++i) 
            ++count[arr[i]]; 
  
        // Change count[i] so that count[i] now contains actual 
        // position of this character in output array 
        for (int i=1; i<=range-1; ++i) 
            count[i] += count[i-1]; 
  
        // Build the output character array 
        // To make it stable we are operating in reverse order. 

        for (int i = n-1; i>=0; i--) {
            output[count[arr[i]]-1] = arr[i]; 
			count[arr[i]] = count[arr[i]] - 1;
		}
  
        // Copy the output array to arr, so that arr now 
        // contains sorted integers 
        for (int i = 0; i<n; ++i) 
            arr[i] = output[i]; 
    } 
	
	public static int randomInt(int digits) {
	    int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
	    int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
	    Random random = new Random();
	    return minimum + random.nextInt((maximum - minimum) + 1);
	}
	static void printArray(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(arr[i]+" "); 
		System.out.println(); 
	} 


	static void generateRandomArr(int arr[],int min,int max) {
		

		int range = max - min + 1; 

		for (int i = 0; i < arr.length; i++) { 
			int rand = (int)(Math.random() * range) + min; 

			arr[i]= rand;
		}

	}
	static void genRandArray(int arr[],int length){
		
		for(int i=0; i < arr.length;++i) {
			
			arr[i] = randomInt(length);
		}
	}


	// driver code 
	public static void main(String args[]) 
	{ 
		// define the range 

		int n = 10;  //number of integers 
		int [] arr1  = new int [n];
		int [] arr2  = new int [n];
		int [] arr3  = new int [n];





		// Output is different everytime this code is executed 

		HeapSortAndStableAlgo hs = new HeapSortAndStableAlgo();
		hs.genRandArray(arr1, 3);
//		System.out.println(" Before Radix Sort : ");
//		hs.printArray(arr1);
//		hs.radixsort(arr1, arr1.length);
//		System.out.println(" Before Radix Sort : ");
		hs.printArray(arr1);
		hs.sort(arr1);
		hs.printArray(arr1);

		System.out.println();




		
		

		System.out.println(" Before Counting  Sort : ");
		int CountRange = 100;
		hs.generateRandomArr(arr3, 1, CountRange);
		hs.printArray(arr3);
		hs.countingSort(arr3,CountRange);
		System.out.println("after Counting Sort : ");
		hs.printArray(arr3);
//		
//		


	} 




}
