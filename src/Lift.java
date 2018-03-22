import java.util.LinkedList;

public class Lift {
	
    private int number;
    private int bottom;
    private int top;
    private int level;
    private int direction;
    private LinkedList<Person> passengers = new LinkedList<Person>();
    private LinkedList<Person> queue = new LinkedList<Person>();
    
    public Lift(int number, int bottom, int top, int level){
    	this.number = number;
        this.bottom = bottom;
        this.top = top;
        this.level = level;
        this.direction = 0;
    }
    
    public void addCaller(Person person) {
    	queue.add(person);
    }
    
    public int suitability(int buildingDistance,int from,int to){
    	if (!(serviceRange(from) && serviceRange(to))) {
			return 0;
		}
    	else if (!checkDirection(from)) {
			return 1;
		}
    	else if (!checkDirection(to)) {
			return ((buildingDistance) + 1) - Math.abs(from - level);
		}
    	else {
			return ((buildingDistance) + 2) - Math.abs(from - level);
		}
    }


	private boolean checkDirection(int targetLevel) {
		if ((direction == 1 && targetLevel > level) || (direction == -1 && targetLevel < level) || direction == 0) {
			return true;
		}
		return false;
	}

	private boolean serviceRange(int place) {
		if (top >= place && bottom <= place) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	public void run() {
		
		if (direction == 0) {
			direction = findDirection();
		}
		
		performPickup();
		performAlight();
		
		changeLevel(direction);
		
		if (level == top || level == bottom){
			direction = 0;
		}
		
		if (passengers.size() > 0 && direction == 0) {
			direction = findDirection();
		}
	}
	
	
	
	
	
	private int findDirection() {
		int newDirection = 0;
		
		if (queue.size() > 0) {
			int firstInQueue = queue.getFirst().getLevel();
			if (firstInQueue > level) {
				newDirection = 1;
			}
			else if (firstInQueue < level) {
				newDirection = -1;
			}
		}
		
		if (passengers.size() > 0){
			int firstPassenger = passengers.getFirst().getDest();
			if (firstPassenger > level) {
				newDirection = 1;
			}
			else if (firstPassenger < level) {
				newDirection = -1;
			}
			else if (firstPassenger == level) {
				newDirection = 0;
			}
		}
		
		return newDirection;
	}
	
	
	
	
	

	private void performPickup() {
		LinkedList<Person> boarding = new LinkedList<Person>();
		for (Person person : queue) {
			if (person.getLevel() == level && person.getAboard() == false) {
				boarding.add(person);
				passengers.add(person);
				person.setAboard(true);
			}
		}
		queue.removeAll(boarding);
		if (boarding.size() > 0) {
			direction = 0;
		}
	}

	private void performAlight() {
		LinkedList<Person> alighting = new LinkedList<Person>();
		for (Person passenger : passengers) {
			if (passenger.getDest() == level) {
				alighting.add(passenger);
				passenger.setAboard(false);
			}
		}
		passengers.removeAll(alighting);
		if (alighting.size() > 0) {
			direction = 0;
		}
	}
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		String s = "Lift " + number;;
		s += drawBlanks();
		s += "|";
		s += drawLevels(bottom, level);
		s += passengers.size();
		s += drawLevels(level, top);
		s += "|";
		if (direction == 1) {
			s += " UP";
		}
		else if (direction == -1) {
			s += " DOWN";
		}
		return s;
	}
	
	private String drawBlanks() {
		String spaces = "";
		for (int i = 0; i <= bottom; i++){
			spaces += " ";
		}
		return spaces;
	}
    
	private String drawLevels(int start, int end) {
		String levels = "";
		for (int i = start; i < end; i++){
			levels += "-";
		}
		return levels;
	}
	
	private void changeLevel(int change) {
		if (level + change >= bottom && level + change <= top ){
			level += change;
		}
		else {
			direction = 0;
		}
		
		for (Person passenger : passengers) {
			passenger.setLevel(level);
		}
	}
    
}
