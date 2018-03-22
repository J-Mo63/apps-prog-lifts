import java.util.LinkedList;

public class Building {
	
    private int entrance = 2;
    private int bottom = 1;
    private int top = 6;
    private LinkedList<Lift> lifts = new LinkedList<Lift>();
    private LinkedList<Person> people = new LinkedList<Person>();
    private String mode;
    
    public Building(){
    	this.mode = readString("Mode: ");
    	this.lifts.add(new Lift(1, 1, 6, entrance));
    	this.lifts.add(new Lift(2, 2, 6, entrance));
    	this.lifts.add(new Lift(3, 2, 5, entrance));
    }
    
    public static void main(String[] args) {
    	Building building = new Building(); 
    	building.use();
    }

	private void use() {
		char choice = 'n';
		while(choice != 'x'){
			
			if (choice != 'n') {
				displayMenu(choice);
			}
			
			if (mode.contains("a")) {
				showLifts();
			}
			
			choice = readMenu();
		}
	}
	
	private void displayMenu(char choice) {
		switch (choice) {
			case 'a': addPerson(); break;
			case 'r': removePerson(); break;
			case 'p': showPeople(); break;
			case 'c': callLift(); break;
			case 'l': showLifts(); break;
			case 'o': operate(); break;
			default: help(); break;
		}
	}

	private void displayModes() {
		if (mode.contains("i")) {
			showIdle();
		}
		if (mode.contains("w")) {
			showWaiting();
		}
	}



	private void showIdle() {
		String display = "Idle:    ";
		for (int i = bottom; i <= top; i++) {
			int count = 0;
			for (Person person : people) {
				if (person.getLevel() == i && person.isIdle()) {
					count += 1;
				}
			}
			if (count != 0) {
				display += count;
			}
			else {
				display += " ";
			}
		}
		System.out.println(display);
	}

	private void showWaiting() {
		String display = "Waiting: ";
		for (int i = bottom; i <= top; i++) {
			int count = 0;
			for (Person person : people) {
				if (person.getLevel() == i && person.isWaiting()) {
					count += 1;
				}
			}
			if (count != 0) {
				display += count;
			}
			else {
				display += " ";
			}
		}
		System.out.println(display);
	}

	private void operate() {
		for (Lift lift: lifts) {
			lift.run();
		}
	}

	private void callLift() {
		Person caller = searchPerson(readInt("Person ID: "));
		int desiredDest = readInt("Destination level: ");
		
		Lift bestLift = getBest(caller.getLevel(), desiredDest);
		if (bestLift.suitability(top - bottom, caller.getLevel(), desiredDest) != 0) {
			caller.callLift(desiredDest);
			bestLift.addCaller(caller);
		}
		else {
			System.out.println("No suitable lift found");
		}
	}

	private Lift getBest(int currentLevel, int destination) {
		int buildingHeight = top - bottom;
		Lift best = null;
		for (Lift lift : lifts) {
			if (best != null) {
				if (lift.suitability(buildingHeight, currentLevel, destination) > best.suitability(buildingHeight, currentLevel, destination)) {
					best = lift;
				}
			}
			else {
				best = lift;
			}
		}
		return best;
	}

	private void showLifts() {
		displayModes();
		for (Lift lift : lifts) {
			System.out.println(lift.toString());
		}
	}

	private void removePerson() {
		int removeID = readInt("Person ID: ");
		int toRemove = searchID(removeID);
		if (toRemove != -1) {
			people.remove(toRemove);
		}
		else {
			System.out.println("No such person");
		}
	}

	private int searchID(int ID) {
		for (Person person : people) {
			if (person.getID() == ID) {
				return people.indexOf(person);
			}
		}
		return -1;
	}
	
	private Person searchPerson(int ID) {
		for (Person person : people) {
			if (person.getID() == ID) {
				return person;
			}
		}
		return null;
	}

	private void showPeople() {
		for (Person person : people) {
			System.out.println(person.toString());
		}
	}

	private void addPerson() {
		boolean found = false;
		int checkedID = readInt("Person ID: ");
		for (Person person : people) {
			if (person.getID() == checkedID) {
				System.out.println("ID already exists");
				found = true;
			}
		}
		if (!found) {
			people.add(new Person(checkedID, readString("Name: "), entrance));
		}
	}

	private void help() {
		System.out.println("Menu");
		System.out.println("a = add person");
		System.out.println("r = remove person");
		System.out.println("p = show people");
		System.out.println("c = call lift");
		System.out.println("l = show lifts");
		System.out.println("o = operate");
	}

	private String readString(String copy) {
		System.out.print(copy);
		return In.nextLine();
	}
	
	private int readInt(String copy) {
		System.out.print(copy);
		return In.nextInt();
	}
	
	private char readMenu(){
    	System.out.print("Choice (a/r/p/c/l/o/x): ");
    	return In.nextChar();
    }

}