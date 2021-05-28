package utils;

public class Random4 {

	public static int[] getRandom() {
		int[] Array;
	    Array = getRand();		
		return Array;
	}
	
	public static String getRandomCharArray() {
		int[] Array = getRand();
		String str ="";		
		for(int index: Array)
			str += index;
		return str;
	}
	
	public static int[] getRand(){
		int[] num = new int[10];
		
		for(int i = 0; i < num.length; i++)
		  num[i] = i;
		
	    int[] arr = new int[4];
	    int n;
	    for(int i = 0; i < arr.length; i++){
	        n = (int)(Math.random()*(10-i));
	        arr[i] = num[n];
	        for(int j = n; j < num.length - 1; j++)
	            num[j] = num[j+1];
	    }
	    return arr;
	}
}
