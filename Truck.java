/**
 * A Program to implement Bridge and Truck weight Problem
 * @author Aishwary Pramanik
 * @author Shashank Gangadhara
 * @version 1.0 
 */
import java.util.Random;
import java.util.Vector;

// Truck class to use Threads
public class Truck extends Thread {
	String side;
	int myWeight;
	static int current_weight;
	int truck_id;
	static int last_truck;
	int MAX_Weight=200000;
	int MAX_Trucks=4;
	static Vector<Truck> truck_on_bridge =new Vector<Truck>();
	static Object Lock=new Object();
	static int count;

	// Constructor to initialize variables
	public Truck(String side,int truck_id) {
		this.truck_id=truck_id;
		this.side=side;
		Random random=new Random();
		this.myWeight=random.nextInt(MAX_Weight/2);
		if(this.myWeight<100)
			this.myWeight=this.myWeight+100;
	}

	// Method to display current Situation
	void display()
	{
		int index;
		System.out.println("No. of Trucks on Bridge: "+truck_on_bridge.size());
		for(index=0;index<truck_on_bridge.size();index++)
		{
			System.out.println("Truck ID: "+
					truck_on_bridge.get(index).truck_id);
			System.out.println("Entered From: "+
					truck_on_bridge.get(index).side);
			if(truck_on_bridge.get(index).side.equalsIgnoreCase("left"))
			{
				System.out.println("|------|__");
				System.out.println("|------|[]");
				System.out.println(" 00   00 ");
			}
			else
			{
				System.out.println("__|------|");
				System.out.println("[]|------|");
				System.out.println(" 00    00");
			}
			System.out.println("Weight: "+
					truck_on_bridge.get(index).myWeight+" lbs");
		}
		System.out.println("\n-----------------------------------------");
		System.out.println("Total weight on Bridge: "+current_weight+" lbs");
		System.out.println("\n-----------------------------------------");

	}

	// Method to implement the functionality of thread
	@Override
	public void run() {
		while(count<150)
		{

			synchronized (Lock) {

				if(truck_on_bridge.size()<MAX_Trucks  
						&& (current_weight+this.myWeight)<=MAX_Weight 
						&& truck_id==(last_truck+1))
				{

					truck_on_bridge.add(this);
					last_truck=this.truck_id;
					current_weight=current_weight+this.myWeight;
					count++;
					display();

					Lock.notifyAll();
				}
				else
				{
					Lock.notifyAll();
					if(truck_on_bridge.size()==MAX_Trucks ||
							(current_weight+this.myWeight)>MAX_Weight)
					{
						try {
							Lock.wait(2000);
						} catch (InterruptedException e) {
						}
						if (!truck_on_bridge.isEmpty()) {
							current_weight=current_weight-truck_on_bridge.
									lastElement().myWeight;
							truck_on_bridge.remove(truck_on_bridge.size()-1);
						}

					}

				}


			}
		}

	}
}
