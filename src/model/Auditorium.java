package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Auditorium {
	
	//Constants
	static final int HOURMI=2;
	static final int HOURMA=12;
	static final int STARTH=7;
	static final int FINISHH=20;
	
	//Attributes
	private String name;
	private String ubication;
	private boolean available;
	private String[] alphabet;
	
	//Relations
	private Chair[][] chairs;
	private ArrayList<Reservation> reservations;
	
	//Constructor
	public Auditorium(String name, String ubication, int chairs[]) {
		this.name = name;
		this.ubication = ubication;
		available = true;
		reservations = new ArrayList<Reservation>();
		this.chairs = createChairs(chairs);
		this.alphabet = createAlphabet();
	}
	/**
	 * This method allows you to create the chairs
	 * @param n 
	 * @return 
	 */
	public Chair[][] createChairs(int [] n){
		int bigNumber = 0;
		for(int i=0; i<n.length; i++) {
			if(n[i]>bigNumber)
				bigNumber = n[i];
		}
		
		Chair[][] obj = new Chair[n.length][bigNumber];
		for(int i = 0; i<obj.length; i++) {
			for(int j = 0; j<n[i]; j++) {
				obj[i][j] = new Chair();
			}
		}
		
		return obj;
	}
	/**
	 * This method allows to create the alphabet
	 * @return Alphabet letters have been created
	 */
	public String[] createAlphabet() {
		String alphabet[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		return alphabet;
	}
	/**
	 * This method allows you to access a file and assign it a letter
	 * @param f
	 * @return file with letter 
	 */
	public int getFile(String f) {
		boolean found = false;
		int file = 0;
		for(int i=0; i<alphabet.length && found==false; i++) {
			if(alphabet[i].equals(f))
				file = i;
		}
		
		return file;
	}
	/**
	 * This method shows the rows with their respective letter
	 * @param f
	 * @return file and letter
	 */
	public String getFile(int f) {
		if(f<alphabet.length)
		return alphabet[f];
		else
			return null;
	}
	/**
	 * This method allows you to search for a chair according to its position
	 * @param f
	 * @param c
	 * @return chair position
	 */
	public Chair searchChair(String f, int c) {
		int file =  getFile(f);
		Chair obj;
		if(chairs[file][c]!=null) {
			obj = chairs[file][c];
		}
		else
			obj = null;
		
		return obj;
	}
	/**
	 * This method verifies the position of a chair
	 * @param available
	 * @param f
	 * @param c
	 */
	public void setAvailableChair(boolean available, String f, int c) {
		int file = getFile(f);
		chairs[file][c].setAvailable(available);
	}
	/**
	 * This method verifies the defective state of the chair
	 * @param defective
	 * @param description
	 * @param f
	 * @param c
	 */
	public void setDefectiveChair(boolean defective, String description, String f, int c) {
		int file = getFile(f);
		chairs[file][c].setDefective(defective);
		chairs[file][c].setDescription(description);
	}
	/**
	 * This method allows you to search for a reservation according to the name of the event
	 * @param nameEvent
	 * @return Reservation according to the event
	 */
	public Reservation searchReservation(String nameEvent) {
		boolean found = false;
		Reservation obj = null;
		for(int i=0; i<reservations.size() && found==false; i++) {
			if(reservations.get(i).getName().equals(nameEvent)) {
				obj = reservations.get(i);
				found = true;
			}
		}
		
		return obj;
	}
	/**
	 * 
	 * @param nameEvent
	 * @return
	 */
	public int searchPositionReservation(String nameEvent) {
		boolean found = false;
		int position = -1;
		for(int i=0; i<reservations.size() && found==false; i++) {
			if(reservations.get(i).getName().equals(nameEvent)) {
				position = i;
				found = true;
			}
		}
		
		return position;
	}
	/**
	 * This method allows you to delete a reservation
	 * @param nameEvent
	 */
	public void eraseReservation(String nameEvent) {
		int n = searchPositionReservation(nameEvent);
		reservations.remove(n);
	}
	/**
	 * This method allows to verify if a reservation crosses
	 * @param date
	 * @param startTime
	 * @param finishTime
	 * @return Validity on whether reservations are crossed
	 */
	public boolean searchCrossReservation(LocalDate date, int startTime, int finishTime) {
		boolean cross = false;
		boolean found = false;
		for(int i=0; i<reservations.size() && found==false; i++) {
			if(reservations.get(i).getDate().equals(date)) {
				found=true;
			}
		}
		
		if(found) {
			for(int i=0; i<reservations.size() && cross==false; i++) {
				for(int j=startTime; j<=finishTime && cross==false; j++) {
					if(reservations.get(i).getStartTime()==j || reservations.get(i).getFinishTime()==j)
						cross=true;
				}
			}
		}
		
		return cross;
	}
	/**
	 * This method allows you to add a reservation
	 * @param nameEvent
	 * @param date
	 * @param startTime
	 * @param finishTime
	 * @return Reservation made
	 */
	public boolean addReservation(String nameEvent, LocalDate date, int startTime, int finishTime) {
		boolean reservation;
		if(startTime<=finishTime) {
			if(searchCrossReservation(date, startTime, finishTime)==false) {
				if((finishTime-startTime)>=HOURMI && (finishTime-startTime)<= HOURMA) {
					if(startTime>= STARTH && finishTime<=FINISHH) {
						reservation = true;
						Reservation e = new Reservation(nameEvent, date, finishTime, finishTime);
						reservations.add(e);
					}
					else
						reservation = false;
				}
				else
					reservation = false;
			}
			else
				reservation = false;
		}
		else
			reservation = false;
		
		return reservation;
	}
	/**
	 * This method allows you to see the percentage of defective chairs
	 * @return Percentage of defective chairs
	 */
	public double percentageDefectiveC() {
		int amounth = 0;
		int amounthDefective = 0;
		for(int i=0; i<chairs.length; i++) {
			for(int j=0; j<chairs[0].length; j++) {
				if(chairs[i][j]!=null) {
					amounth+=1;
					if(chairs[i][j].isDefective())
						amounthDefective+=1;
				}
			}
		}
		
		double percentage = (amounthDefective*100)/amounth;
		
		return percentage;
	}
	/**
	 * This method allows to verify if all the chairs are occupied
	 * @return Report with occupied chairs
	 */
	public boolean allChairsOcupated() {
		boolean report = true;
		for(int i=0; i<chairs.length && report==true; i++) {
			for(int j=0; j<chairs[0].length && report==true; j++) {
				if(chairs[i][j].isAvailable() && !chairs[i][j].isDefective()) {
					report = false;
				}
			}
		}
		
		return report;
	}
	
	public void vacateAllChairs() {
		for(int i=0; i<chairs.length; i++) {
			for(int j=0; j<chairs[0].length; j++) {
				if(chairs[i][j]!=null) {
					if(!chairs[i][j].isAvailable())
						chairs[i][j].setAvailable(true);
				}

			}
		}
	}
	/**
	 * This method allows people to be counted in an event
	 * @return number of people
	 */
	public int counthPeople() {
		int n = 0;
		for(int i=0; i<chairs.length; i++) {
			for(int j=0; j<chairs[0].length; j++) {
				if(chairs[i][j]!=null) {
					if(!chairs[i][j].isAvailable())
						n+=1;
				}
			}
		}
		
		return n;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUbication() {
		return ubication;
	}

	public void setUbication(String ubication) {
		this.ubication = ubication;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String[] getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String[] alphabet) {
		this.alphabet = alphabet;
	}

	public Chair[][] getChairs() {
		return chairs;
	}

	public void setChairs(Chair[][] chairs) {
		this.chairs = chairs;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public int getHourmi() {
		return HOURMI;
	}

	public int getHourma() {
		return HOURMA;
	}

	public int getStarth() {
		return STARTH;
	}

	public int getFinishh() {
		return FINISHH;
	}
}
