package utils;

public class Random4 {

	public static int[] getRandom() {

		int[] Array;
	    Array = getRand();

	    for(int i = 0; i < 4; i++)
	    	System.out.print(Array[i] + ";");
	    
	    System.out.println();
		
		return Array;
	}
	
	public static String getRandomCharArray() {


		int[] Array = getRand();

	   String str ="";
	   for(int index: Array)
		   str += index;
	   System.out.println(str);
	   return str;
	}
	
	public static int[] getRand()
	{
		int[] num = new int[10];
		
		for(int i = 0; i < num.length; i++){
		  num[i] = i;
		  System.out.println(num[i]);
		}
		
	    int[] arr = new int[4];
	    int n;
	    for(int i = 0; i < arr.length; i++)
	    {
	        n = (int)(Math.random()*(10-i));
	        arr[i] = num[n];
	        for(int j = n; j < num.length - 1; j++)
	        {
	            num[j] = num[j+1];
	        }
	    }
	    return arr;
	}
}
