package model;

public class Chair {
	
	//Attributes
	private boolean available;
	private boolean defective;
	private String description;
	
	public Chair() {
		available = true;
		defective = false;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isDefective() {
		return defective;
	}

	public void setDefective(boolean defective) {
		this.defective = defective;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
