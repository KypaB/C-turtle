/*
#school - TUES
#class - 11B
#num - 15
#name - Dobromir Ivanov
#task - Да се разработи програма, която подрежда в самолет групи от по 1 до 3 души. 
		Когато дойдат повече от 1 човек, групата трябва да бъде настанена на съседни места. 
		Ако няма такива се отказва достъп.
*/
import java.util.Random;

public class PlaneSeats{
	
	private int[][] seats = new int[6][27];
	
	public static void main(String args[]) {
		PlaneSeats plane = new PlaneSeats();
		Integer people = 0;
		while(plane.planeFull()){
			int rVal = new Random().nextInt(3) + 1;
			if (people + rVal <= 162) people += rVal;
			
			plane.add(rVal);
		}
		
		//plane.printSeats();
		System.out.println(people.toString()+" people boarded the plane!");
	}
	
	public void add(Integer passengers) {
		int[] r = findSeat(passengers);
		int seat = r[0], row = r[1];
		System.out.println("Group of "+passengers.toString() + " arrives.");
		if(r[1] >= 0){
			for(int i = 0; i < passengers; i++){
				System.out.println("	Seat " + seatToReadable(seat, row) + " taken.");
				seats[seat++][row] = 1;
			}
		}else{
			System.out.println("	Sorry, no seats for you...");
		}
	}
	
	public void printSeats() {
		for(int i = 0; i < 27; i++){
			for(int j = 0; j < 6; j++){
				System.out.print(seats[j][i]);
				if(j == 2) System.out.print("|  |");
			}
			System.out.println();
		}
	}
	
	public boolean planeFull(){
		for(int i = 0; i < 27; i++){
			for(int j = 0; j < 6; j++){
				if(seats[j][i] == 0) return true;
			}
		}
		return false;
	}
	
	private boolean toTrail(int seat){
		if(seat == 2){
			return true;
		}
		return false;
	}
	
	private int[] findSeat(int passengers){
		int[] seat = {-1, -1}; 
		for(int i = 0; i < 27; i++){
			for(int j = 0; j < 6; j++){
				switch(passengers){
					case 1: if(seats[j][i] == 0){
								seat[0] = j; seat[1] = i;
								return seat;
							}
						break;
					case 2: if( (j + passengers <= 6) && !toTrail(j)){
								if(seats[j][i] == 0 && seats[j+1][i] == 0){
									seat[0] = j; seat[1] = i;
									return seat;
								}
							}
						break;
					case 3: if( (j + passengers <= 6) && !toTrail(j) ){
								if(seats[j][i] == 0 && seats[j+1][i] == 0 && seats[j+2][i] == 0){
									seat[0] = j; seat[1] = i;
									return seat;
								}
							}
						break;
				}
			}	
		}
		return seat;
	}
	
	private String seatToReadable(int seat, Integer row){
		String seatLetter = "";
		switch(seat){
			case 0: seatLetter = "A";
			break;
			case 1: seatLetter = "B";
			break;
			case 2: seatLetter = "C";
			break;
			case 3: seatLetter = "D";
			break;
			case 4: seatLetter = "E";
			break;
			case 5: seatLetter = "F";
			break;
		}
		
		return row.toString()+seatLetter;
		
	}
	
}
