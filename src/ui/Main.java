package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class Main {
	
	//Relations
	private Scanner number;
	private Scanner lector;
	private University uni;
	
	//Constructor
	public static void main(String [] args) {
		Main main = new Main();
		main.menu();
	}
	
	public Main(){
		number = new Scanner(System.in);
		lector = new Scanner(System.in);
		uni = new University();
	}
	
	public void menu() {
		int option;
		boolean choose = false;
		while(!choose) {
			System.out.println("1. Auditoriums.\n2. Events.\n3. Exit.");
			option = number.nextInt();
			switch(option) {
			case 1: auditoriumMenu(nameAuditorium());
				break;
			case 2: eventMenu(nameEvent());
				break;
			case 3: choose = true;
				break;
			default:System.out.println("Not valid option"); 
				break;
			}
		}
	}
	
	public void auditoriumMenu(String name) {
		int option;
		boolean choose = false;
		if(uni.searchAuditorium(name).isAvailable()) {
			while(!choose) {
				System.out.println("1. Add reservation.\n2. Report defective chair.\n3. calculate percentage defective chairs.\n4. Back.");
				option = number.nextInt();
				switch(option) {
				case 1: addReservation(name);
					break;
				case 2: reportDefectiveChair(name);
					break;
				case 3: System.out.println(uni.percentageDefectiveC(name)+"%");
					break;
				case 4: choose = true;
					break;
				default:System.out.println("Not valid option"); 
					break;
				}
			}
		}
		else {
			while(!choose) {
				System.out.println("1. Add reservation.\n2. Report defective chair.\n3. calculate percentage defective chairs.\n4. Add people.\n5. Back.");
				option = number.nextInt();
				switch(option) {
				case 1: addReservation(name);
					break;
				case 2: reportDefectiveChair(name);
					break;
				case 3: System.out.println(uni.percentageDefectiveC(name)+"%");
					break;
				case 4: if(uni.setAvailableChair(name))
							System.out.println("Succesful");
						else
							System.out.println("all chairs ocupated");
					break;
				case 5: choose = true;
					break;
				default:System.out.println("Not valid option"); 
					break;
				}
			}
		}
		
	}
	
	public void addReservation(String name) {
		
		System.out.println("Choose the name of the event");
		String nameEvent = nameEvent();
		System.out.println("type the start hour");
		int startTime = number.nextInt();
		System.out.println("type the finish hour");
		int finishTime = number.nextInt();
		
		boolean add = uni.addReservation(name, nameEvent, startTime, finishTime);
		
		if(add) {
			System.out.println("Succesful");
		}
		else
			System.out.println("cant be registered");
		
	}
	
	public void reportDefectiveChair(String name) {
		System.out.println("Type file (letter)");
		String file = lector.nextLine();
		System.out.println("Type the column (number)");
		int column = number.nextInt();
		System.out.println("Type the problem");
		String description = lector.nextLine();
		
		boolean report = uni.setDefectiveChair(name, true, description, file, column);
		if(report)
			System.out.println("Succesful");
		else
			System.out.println("Can't be reported");
	}
	
	public void eventMenu(String name) {
		int option;
		boolean choose = false;
		if(!uni.searchEvent(name).isFinalizated() && !uni.searchEvent(name).isStart()) {
			while(!choose) {
				System.out.println("1. Start event.\n2. Back.");
				option = number.nextInt();
				if(option==1) {
					uni.startEvent(name);
					choose=true;
				}
				else {
					if(option==2) {
						choose=true;
					}
					else
						System.out.println("invalid option");
				}
			}
		}
		else {
			if(uni.searchEvent(name).isStart() && !uni.searchEvent(name).isFinalizated()) {
				while(!choose) {
					System.out.println("1. Finish event.\n2. Back.");
					option = number.nextInt();
					if(option==1) {
						System.out.println("The people assisted was: "+uni.finishEvent(name));
						choose=true;
					}
					else {
						if(option==2) {
							choose=true;
						}
						else
							System.out.println("invalid option");
					}
				}
			}
			else {
				if(uni.searchEvent(name).isStart() && uni.searchEvent(name).isFinalizated()) {
					System.out.println("this event has already taken place");
				}
			}
		}
	}
	/**
	 * This method allows you to choose an auditorium
	 * @return chosen auditorium
	 */
	public String nameAuditorium() {
		int option;
		boolean choose = false;
		ArrayList<Auditorium> array;
		int n;
		String name = "";
		array = uni.getAuditoriums();
		while(!choose) {
			n = 1;
			for(int i=0; i<array.size(); i++) {
				System.out.println(n+": "+array.get(i).getName());
				n++;
			}
			option = number.nextInt();
			if(option<=array.size() && option>0) {
				name = array.get(option-1).getName();
				choose = true;
			}
		}
		
		return name;
	}
	/**
	 * This method allows access to an event
	 * @return name of the event
	 */
	public String nameEvent() {
		int option;
		boolean choose = false;
		ArrayList<Event> array;
		int n;
		String name = "";
		array = uni.getEvents();
		while(!choose) {
			n = 1;
			for(int i=0; i<array.size(); i++) {
				System.out.println(n+": "+array.get(i).getName());
				n++;
			}
			option = number.nextInt();
			if(option<=array.size() && option>0) {
				name = array.get(option-1).getName();
				choose = true;
			}
		}
		
		return name;
	}
}
