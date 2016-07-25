/**
 * A Program to implement Bridge and Truck weight Problem
 * @author Aishwary Pramanik
 * @author Shashank Gangadhara
 * @version 1.0 
 */

import java.util.Random;
// Class to invoke the threads and use the functionality
public class Bridge {
	int MAX_Weight=200000;
	int MAX_Trucks=4;
	static int current_weight;
	// Main method, Programs starts here
	public static void main(String[] args) throws InterruptedException {
		int index;
		Random random=new Random();
		int chance;
		for (index=0;index<=150;index++)
		{
			chance=random.nextInt(2);
			if(chance==0)
			{
				Truck T1=new Truck("Right",index);
				T1.start();
			}
			else
			{
				Truck T2=new Truck("Left",index);
				T2.start();
			}
		}
	}
}
