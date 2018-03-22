
public class Person {
	
    private int id;
    private String name;
    private int level;
    private int destination;
    private boolean aboard;
    
    
    public Person(int id, String name, int destination) {
    	this.id = id;
        this.name = name;
        this.level = destination;
        this.destination = destination;
        this.aboard = false;
    }
    
    public int getID() {
    	return this.id;
    }
    
    public void callLift(int destination) {
    	this.destination = destination;
    }

	@Override
	public String toString() {
		if (level == destination && !aboard) {
			return name + "(" + id + ") on level " + level;
		}
		else {
			if (aboard){
				return name + "(" + id + ") on level " + level + " going to level " + destination;
			}
			return name + "(" + id + ") on level " + level + " waiting to go to level " + destination;
		}
	}

	public int getLevel() {
		return this.level;
	}

	public int getDest() {
		return this.destination;
	}
	
	public void setAboard(Boolean status) {
		aboard = status;
	}

	public void setLevel(int newLevel) {
		level = newLevel;
	}

	public boolean getAboard() {
		return aboard;
	}
    
	public boolean isIdle() {
		if (level == destination && !aboard) {
			return true;
		}
		return false;
	}
	
	public boolean isWaiting() {
		if (level != destination && !(aboard)) {
			return true;
		}
		return false;
	}
	
}




