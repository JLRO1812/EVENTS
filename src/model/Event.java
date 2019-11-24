package model;

import java.time.LocalDate;

public class Event {

	//Attributes
	private String name;
	private LocalDate date;
	private int startHour;
	private int finishHour;
	private String nameTeacher;
	private String nameFaculty;
	private int assistedPeople;
	private boolean start;
	private boolean finalizated;
	
	//Constructor
	public Event(String name, int day, int month, int year, int startHour, int finishHour, String nameTeacher, String nameFaculty) {
		this.name = name;
		date = LocalDate.of(year, month, day);
		this.startHour = startHour;
		this.finishHour = finishHour;
		this.nameTeacher = nameTeacher;
		this.nameFaculty = nameFaculty;
		this.assistedPeople = 0;
		this.start = false;
		this.finalizated = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(int finishHour) {
		this.finishHour = finishHour;
	}

	public String getNameTeacher() {
		return nameTeacher;
	}

	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}

	public String getNameFaculty() {
		return nameFaculty;
	}

	public void setNameFaculty(String nameFaculty) {
		this.nameFaculty = nameFaculty;
	}

	public int getAssistedPeople() {
		return assistedPeople;
	}

	public void setAssistedPeople(int assistedPeople) {
		this.assistedPeople = assistedPeople;
	}

	public boolean isFinalizated() {
		return finalizated;
	}

	public void setFinalizated(boolean finalizated) {
		this.finalizated = finalizated;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
	
	
}
