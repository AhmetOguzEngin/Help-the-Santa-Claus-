import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class project4main {
	public static void main(String[] args) throws IOException {
		//Input handling and graph constructing part
		File infile = new File(args[0]);	Scanner sc = new Scanner(infile);
		int id = 0;
		//Delivery Address
		Santa deladdress =	new Santa(id++);
		//Green region trains
		int numofgreentrains = sc.nextInt();
		ArrayList<Vehicle> greentrains = new ArrayList<Vehicle>();
		for(int i = 0; i<numofgreentrains; i++) {
			int capacity = sc.nextInt();
			Vehicle greentrain = new Vehicle(id++);
			greentrain.giftpath.add(new Pair<Santa, Integer>(deladdress, capacity));
			greentrains.add(greentrain);
		}
		//Red region trains
		int numofredtrains = sc.nextInt();
		ArrayList<Vehicle> redtrains = new ArrayList<Vehicle>();
		for(int i = 0; i<numofredtrains; i++) {
			int capacity = sc.nextInt();
			Vehicle redtrain = new Vehicle(id++);
			redtrain.giftpath.add(new Pair<Santa, Integer>(deladdress, capacity));
			redtrains.add(redtrain);
		}
		//Green region reindeer
		int numofgreenreindeers = sc.nextInt();
		ArrayList<Vehicle> greenreindeers = new ArrayList<Vehicle>();
		for(int i = 0; i<numofgreenreindeers; i++) {
			int capacity = sc.nextInt();
			Vehicle greenreindeer = new Vehicle(id++);
			greenreindeer.giftpath.add(new Pair<Santa, Integer>(deladdress, capacity));
			greenreindeers.add(greenreindeer);
		}
		//Red region reindeer
		int numofredreindeers = sc.nextInt();
		ArrayList<Vehicle> redreindeers = new ArrayList<Vehicle>();
		for(int i = 0; i<numofredreindeers; i++) {
			int capacity = sc.nextInt();
			Vehicle redreindeer = new Vehicle(id++);
			redreindeer.giftpath.add(new Pair<Santa, Integer>(deladdress, capacity));
			redreindeers.add(redreindeer);
		}
		
		Santa mainbag = new Santa(id++);
		int totalgiftnum = 0;
		int numofgifts = sc.nextInt();
		//May cause problems
		if(numofgifts == 0) {
			File outfile = new File(args[1]);
			PrintStream out = new PrintStream(outfile);
			out.print(0);
			out.close();
			return;
		}
		sc.nextLine();
		String giftline = sc.nextLine();	String[] giftinfo = giftline.split(" ");
		for (int i = 0; i < numofgifts*2 - 1; i+=2) {
			String gifttype = giftinfo[i]; int giftnum = Integer.parseInt(giftinfo[i+1]);
			totalgiftnum += giftnum;
			int transmissioncapacity = 0;
			if(gifttype.contains("a"))	transmissioncapacity = 1;
			else transmissioncapacity = giftnum;
			
			Bag giftbag = new Bag(id++);
			//Green regions
			if(gifttype.contains("b")) {
				//only by train
				if(gifttype.contains("d")) {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle greentrain:greentrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greentrain, transmissioncapacity));
					}

					//only by reindeer
				}else if(gifttype.contains("e")) {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle greenreindeer:greenreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greenreindeer, transmissioncapacity));
					}
				}
				//by reindeer and train to the green region.
				else {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle greenreindeer:greenreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greenreindeer, transmissioncapacity));
					}

					for(Vehicle greentrain:greentrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greentrain, transmissioncapacity));
					}

				}

			}
			//Red regions
			else if(gifttype.contains("c")){
				//only by train
				if(gifttype.contains("d")) {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle redtrain:redtrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redtrain, transmissioncapacity));
					}

					//only by reindeer
				}else if(gifttype.contains("e")) {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle redreindeer:redreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redreindeer, transmissioncapacity));
					}
				}
				//by reindeer and train to the red region.
				else {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle redreindeer : redreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redreindeer, transmissioncapacity));
					}

					for(Vehicle redtrain:redtrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redtrain, transmissioncapacity));
					}

				}


			}
			//to the both red and green regions
			else {
				//only by train
				if(gifttype.contains("d")) {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle redtrain:redtrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redtrain, transmissioncapacity));
					}
					for(Vehicle greentrain:greentrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greentrain, transmissioncapacity));
					}
				}

				//only by reindeer 
				else if(gifttype.contains("e")) {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle redreindeer:redreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redreindeer, transmissioncapacity));
					}
					for(Vehicle greenreindeer:greenreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greenreindeer, transmissioncapacity));
					}
				}
				//both by train and reindeer.
				else {
					mainbag.giftpath.add(new Pair<Santa, Integer>(giftbag, giftnum));
					for(Vehicle redtrain:redtrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redtrain, transmissioncapacity));
					}
					for(Vehicle greentrain:greentrains) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greentrain, transmissioncapacity));
					}
					for(Vehicle redreindeer:redreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(redreindeer, transmissioncapacity));
					}
					for(Vehicle greenreindeer:greenreindeers) {
						giftbag.giftpath.add(new Pair<Santa, Integer>(greenreindeer, transmissioncapacity));
					}
				}
			}

		}
		sc.close();
		int totalnum = numofredreindeers + numofgreenreindeers + numofgifts + numofredtrains + numofgreentrains + 2;

		//Algorithm is executed here.
		int deliveredamount = GiftDelivery.startDelivery(totalnum, deladdress, mainbag);
		
		//Creating output file
		File outfile = new File(args[1]);
		PrintStream out = new PrintStream(outfile);
		int cantbedelivered = totalgiftnum - deliveredamount;
		out.print(cantbedelivered);
		out.close();
		
	}
}





