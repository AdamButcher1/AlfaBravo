import java.util.ArrayList;


public class Plane {
	
	ArrayList<Party> parties;
	
	int size;
	int freeSeats;
	String name;
	
	Plane(int size, String name) {
		this.size = size;
		this.name = name;
		parties = new ArrayList();
		freeSeats = size;
	}
	
	void addParty(String which, String name, int size) {
		
		if(size > this.size)
			System.out.println("Party is too big, goodbye " + name + ".");
		else if (size > freeSeats) {
			System.out.println("Wait in lounge " + name + ".");
			MainActivity.sentToLounge = true;
		}
		else {
			System.out.println("Party " + name + " added to " + this.name + ".");
			parties.add(new Party(which, name, size));
			freeSeats = freeSeats - size;
		}
				
		if(freeSeats == 0)
			flyPlane();
	}
	
	void addToLounge(String which, String name, int size) {
		if(size > freeSeats)
			System.out.println("Party is too big, goodbye " + name + ".");
		else {
			System.out.println("Party " + name + " added to " + this.name);
			parties.add(new Party(which, name, size));
			freeSeats = freeSeats - size;	
		}
		
	}
	
	void flyPlane() {
		
		System.out.println("Plane " + name + " is boarding.");
		for(Party party: parties) {
			System.out.println(party.name);
		}
		
		freeSeats = size;
		parties.clear();
		MainActivity.flyAgain = true;
	}
	
}
