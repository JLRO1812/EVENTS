package model;

import java.time.LocalDate;

public class Reservation {

	//Attributes
	private String name;
	private LocalDate date;
	private int startTime;
	private int finishTime;
	
	//Constructor
	public Reservation(String name, LocalDate date, int startTime, int finishTime) {
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.finishTime = finishTime;
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

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	
}
