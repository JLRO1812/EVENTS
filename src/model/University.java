package model;

import java.util.ArrayList;
import java.util.Random;

public class University {
	
	//Relations
	private ArrayList<Auditorium> auditoriums;
	private ArrayList<Event> events;
	
	//Constructor
	public University() {
		auditoriums = new ArrayList<Auditorium>();
		events = new ArrayList<Event>();
		addTest();
	}
	/**
	 * This method allows you to search for an event by name
	 * @param name is a string
	 * @return Show the event according to the given name
	 */
	public Event searchEvent(String name) {
		Event obj = null;
		boolean found = false;
		for(int i=0; i<events.size() && found==false; i++) {
			if(events.get(i).getName().equals(name)) {
				found = true;
				obj = events.get(i);
			}
		}
		
		return obj;
	}
	/**
	 * This method allows you to find the position of an event by name
	 * @param name is a string
	 * @return Give the position of the given event
	 */
	public int searchPositionEvent(String name) {
		int position = -1;
		boolean found = false;
		for(int i=0; i<events.size() && found==false; i++) {
			if(events.get(i).getName().equals(name)) {
				found = true;
				position = i;
			}
		}
		
		return position;
	}
	/**
	 * This method allows you to enter the number of people who will attend the event
	 * @param amount is a integer
	 * @param nameEvent is a string
	 */
	public void setAssistedPeople(int amount, String nameEvent) {
		events.get(searchPositionEvent(nameEvent)).setAssistedPeople(amount);
	}
	/**
	 * This method allows you to search for an auditorium by name
	 * @param name is a string
	 * @return The auditorium entered
	 */
	public Auditorium searchAuditorium(String name) {
		boolean found = false;
		Auditorium obj = null;
		for(int i=0; i<auditoriums.size() && found==false; i++) {
			if(auditoriums.get(i).getName().equals(name)) {
				found = true;
				obj = auditoriums.get(i);
			}
		}
		
		return obj;
	}
	/**
	 * This method allows to find the position of an auditorium according to its name
	 * @param name is a string
	 * @return The position of the auditorium entered
	 */
	public int searchPositionAuditorium(String name) {
		int position = -1;
		boolean found = false;
		for(int i=0; i<auditoriums.size() && found==false; i++) {
			if(auditoriums.get(i).getName().equals(name)) {
				found = true;
				position = i;
			}
		}
		
		return position;
	}
	/**
	 * This method allows to verify if an auditorium is available according to its name
	 * @param available is a boolean
	 * @param name is a string
	 * @return Validate the availability of the auditorium entered
	 */
	public boolean setAvailableAuditorium(boolean available, String name) {
		boolean report;
		if(searchAuditorium(name)!=null) {
			auditoriums.get(searchPositionAuditorium(name)).setAvailable(available);
			report = true;
		}
		else 
			report = false;
		
		return report;
	}
	/**
	 * This method allows to verify if a chair in an auditorium is available according to its name
	 * @param nameAuditorium is a string
	 * @return Validate the availability of the chair in the auditorium entered
	 */
	public boolean setAvailableChair(String nameAuditorium) {
		boolean report = false;
		Random random = new Random();
		boolean ocupated = false;
		int f,c;
		if(searchAuditorium(nameAuditorium)!=null) {
			if(!searchAuditorium(nameAuditorium).allChairsOcupated()) {
				while(!ocupated) {
					f = random.nextInt(searchAuditorium(nameAuditorium).getChairs().length-1);
					c = random.nextInt(searchAuditorium(nameAuditorium).getChairs()[0].length-1);
					if(searchAuditorium(nameAuditorium).searchChair(auditoriums.get(0).getFile(f), c)!=null && searchAuditorium(nameAuditorium).searchChair(auditoriums.get(0).getFile(f), c).isAvailable()) {
						ocupated = true;
						searchAuditorium(nameAuditorium).setAvailableChair(false, auditoriums.get(0).getFile(f), c);
						report = true;
					}
				}
			}
		}
		
		return report;
	}
	/**
	 * This method allows entry of defective chairs
	 * @param nameAuditorium is a string
	 * @param defective is a boolean
	 * @param description is a string
	 * @param f is a string
	 * @param c is a integer
	 * @return Validate defective chairs
	 */
	public boolean setDefectiveChair(String nameAuditorium, boolean defective, String description, String f, int c) {
		boolean report;
		if(searchAuditorium(nameAuditorium)!=null) {
			if(auditoriums.get(searchPositionAuditorium(nameAuditorium)).searchChair(f, c)!=null) {
				auditoriums.get(searchPositionAuditorium(nameAuditorium)).setDefectiveChair(defective, description, f, c);
				report = true;
			}
			else
				report = false;
		}
		else
			report = false;
		
		return report;
	}
	/**
	 * This method allows you to search for a chair according to the name of the auditorium
	 * @param nameAuditorium is a string
	 * @param f is a string
	 * @param c is a integer
	 * @return Chair result
	 */
	public Chair searchChair(String nameAuditorium, String f, int c) {
		Chair obj;
		if(searchAuditorium(nameAuditorium)!=null) {
			obj = auditoriums.get(searchPositionAuditorium(nameAuditorium)).searchChair(f, c);
		}
		else
			obj = null;
		
		return obj;
	}
	/**
	 * This method allows you to search for a reservation according to the name of the event and the name of the auditorium
	 * @param nameAuditorium is a string
	 * @param nameEvent is a string
	 * @return Result of the reservation
	 */
	public Reservation searchReservation(String nameAuditorium, String nameEvent) {
		Reservation obj;
		if(searchAuditorium(nameAuditorium)!=null && searchEvent(nameEvent)!=null) {
			obj = auditoriums.get(searchPositionAuditorium(nameAuditorium)).searchReservation(nameEvent);
		}
		else
			obj = null;
		
		return obj;
	}

	public void eraseReservation(String nameAuditorium, String nameEvent) {
		if(searchAuditorium(nameAuditorium)!=null && searchEvent(nameEvent)!=null) {
			auditoriums.get(searchPositionAuditorium(nameAuditorium)).eraseReservation(nameEvent);
		}
	}
	/**
	 * This method allows you to add a reservation
	 * @param nameAuditorium is a string
	 * @param nameEvent is a string
	 * @param startTime is a integer
	 * @param finishTime is a integer
	 * @return The reservation has been added
	 */
	public boolean addReservation(String nameAuditorium, String nameEvent, int startTime, int finishTime) {
		boolean add;
		if(searchAuditorium(nameAuditorium)!=null && searchEvent(nameEvent)!=null) {
			add = auditoriums.get(searchPositionAuditorium(nameAuditorium)).addReservation(nameEvent, searchEvent(nameEvent).getDate(), startTime, finishTime);
		}
		else
			add = false;
		
		return add;
	}
	/**
	 * This method lets you know what the percentage of defective chairs according to the name of auditorium
	 * @param nameAuditorium is a string
	 * @return Percentage of defective chairs
	 */
	public double percentageDefectiveC(String nameAuditorium) {
		double percentage;
		if(searchAuditorium(nameAuditorium)!=null) {
			percentage = auditoriums.get(searchPositionAuditorium(nameAuditorium)).percentageDefectiveC();
		}
		else
			percentage = -1;
		
		return percentage;
	}

	public void addAuditorium(String name, String ubication, int[] chairs) {
		Auditorium obj = new Auditorium(name, ubication, chairs);
		auditoriums.add(obj);
	}

	public void addEvent(String name, int day, int month, int year, int startHour, int finishHour, String nameTeacher, String nameFaculty) {
		Event obj = new Event(name, day, month, year, startHour, finishHour, nameTeacher, nameFaculty);
		events.add(obj);
	}

	public void startEvent(String name) {
		for(int i=0; i<auditoriums.size(); i++) {
			if(auditoriums.get(i).searchReservation(name)!=null) {
				auditoriums.get(i).setAvailable(false);
			}
		}
		
		events.get(searchPositionEvent(name)).setStart(true);
	}
	/**
	 * This method allows an event to end
	 * @param name is a string
	 * @return number of people in the event
	 */
	public int finishEvent(String name) {
		int people = 0;
		for(int i=0; i<auditoriums.size(); i++) {
			if(auditoriums.get(i).searchReservation(name)!=null) {
				people+=auditoriums.get(i).counthPeople();
				auditoriums.get(i).setAvailable(true);
				auditoriums.get(i).eraseReservation(name);
				auditoriums.get(i).vacateAllChairs();
			}
		}
		
		events.get(searchPositionEvent(name)).setFinalizated(true);
		
		return people;
	}

	public void addTest() {
		int[] num1 = {3,4,5,4,3,2,1};
		int[] num2 = {4,5,4,5,4,5,4};
		int[] num3 = {3,3,3,3,3,3,3};
		int[] num4 = {1,2,3,4,3,2,1};
		addAuditorium("manuelita", "east", num1);
		addAuditorium("sidoc", "north", num2);
		addAuditorium("gases de occident", "west", num3);
		addAuditorium("mantequilla", "south", num4);
		addEvent("lost level", 19, 5, 2020, 8, 10, null, null);
		addEvent("over level", 18, 6, 2020, 8, 15, null, null);
		addReservation("sidoc", "lost level", 9, 18);
	}

	public ArrayList<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}
	
	
}
