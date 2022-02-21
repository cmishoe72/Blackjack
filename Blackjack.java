import java.util.Arrays;

import javax.swing.JOptionPane;

// Class project Carson Mishoe and Zachary Anderson 10/28/2020 - 10/31/2020
public class Blackjack {
	static final String[] cards = { "ace", "ace", "ace", "ace", "2", "2", "2", "2", "3", "3", "3", "3", "4", "4", "4",
			"4", "5", "5", "5", "5", "6", "6", "6", "6", "7", "7", "7", "7", "8", "8", "8", "8", "9", "9", "9", "9",
			"10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10" };

// Holds a variable of all the cards we need. We do know that there is a 
// chance(about a one in 380 million that because they are not removed from 
// the deck we could get more than 4 of each card. However with the way we
// implemented the code it would not be worth to fix for a 2.63157895×10^-9 chance

	public static void main(String[] args) {
		rules();
		String[] humanChoice = dealHuman();
		String[] computerChoice = dealComputer();
		displayCards(humanChoice, computerChoice);
		int humanFinal = hitOrStand(humanChoice);
		int computerFinal = computerHit(computerChoice);
		pickWinner(humanFinal, computerFinal);
	}

	public static void rules() {
		System.out.println("How to Play Blackjack\r\n"
				+ "1. The goal is to get as close to 21 with out going over, while having a higher number then the dealer.\r\n"
				+ "2. Two numbers are given to both the player and dealer, for the dealer one number is hidden and the other number is shown, the hidden number is not revealed until the end.\r\n"
				+ "3. Based off your numbers, decide whether you would like to hit or stand.\r\n"
				+ "4. When you hit, you are given another card, total your cards to see how close you are to 21, decide whether to hit or stand again.\r\n"
				+ "5. Once you stand, the dealer will then reveal there hidden number and decide whether to hit or stand.\r\n"
				+ "6. The dealer will automatically hit on below 15. \r\n"
				+ "7. Then, a winner will be chosen off of the highest total of numbers. If it is a tie the dealer will win\r\n"
				+ "Note: If either the dealer or player go over 21, they lose.");
	} // The rules we set for our game of blackjack. With the limited knowledge we
		// have now we had to change some rules

	public static String[] dealHuman() {
		String[] card = {};
		for (int i = 0; i < 2; i++) {
			String choice = cards[(int) (0 + Math.random() * ((51 - 0) + 0))]; // Got this idea from
																				// https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
			card = Arrays.copyOf(card, card.length + 1);
			card[card.length - 1] = choice;
		}

		return card;
	} // Grabs 2 random cards for our initial hand

	public static String[] dealComputer() {
		String[] card = {};
		for (int i = 0; i < 2; i++) {
			String choice = cards[(int) (0 + Math.random() * ((51 - 0) + 0))];
			card = Arrays.copyOf(card, card.length + 1);
			card[card.length - 1] = choice;
		}
		return card;
	} // Grabs 2 random cards for the dealers hand

	public static void displayCards(String[] human, String[] computer) {
		System.out.println("Your cards are " + human[0] + " and " + human[1]);
		System.out.println("Your current total is " + value(human));
		System.out.println("The dealer cards are " + "BLANK" + " and " + computer[1]);
	} // Displays our cards and displays just one of the dealers cards using sysout
		// and object grabbing from a list.

	public static String[] hit(String[] choices) { // Got this from
													// https://www.educative.io/edpresso/how-to-append-to-an-array-in-java
		String choice = cards[(int) (0 + Math.random() * ((51 - 0) + 0))];
		choices = Arrays.copyOf(choices, choices.length + 1);
		choices[choices.length - 1] = choice;
		return choices;
	} // Grabs another random card and then creates an array that is a copy of the old
		// one with one extra spot. Then appends it to the last spot of the array.

	public static int value(String[] x) {
		int finale = 0;
		int size = x.length;
		int ace = 0;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			if (x[i].equals("ace")) {
				continue;
			} else {
				arr[i] = Integer.parseInt(x[i]);
			}
		}

		for (int i = 0; i < size; i++) {
			if (x[i].equals("ace")) {
				continue;
			} else {
				finale += arr[i];
			}
		}
		for (int i = 0; i < size; i++) {
			if (x[i].equals("ace")) {
				if (finale + 11 <= 21) {
					finale += 11;
				} else if (finale + 11 > 21) {
					finale += 1;
				}
			}
		}
		return finale;
	} // So this does a couple things. This simplified converts the list of strings
		// into a list of the numbers they hold.
		// If it is "ace" it will do math determining if the ace is a 1 or 11 depending
		// if it will bust or not. and then returns the value as an int.

	public static int checkBust(int x) {
		if (x > 21) {
			System.out.println("You busted! you lose.");
			System.exit(0);
		}
		return x;

	} // Will kill the program if you ever bust

	public static int hitOrStand(String[] cards) {
		String choice = JOptionPane.showInputDialog("hit or stand");
		if (choice.equals("hit")) {
			cards = hit(cards);
			System.out.println("Your new card is " + cards[cards.length - 1]);
			System.out.print("Your cards are ");
			for (int i = 0; i < cards.length; i++) {
				System.out.print("| " + cards[i] + " ");
			}
			System.out.print("|");
			System.out.println();
			System.out.println("Your new total is " + value(cards));
			checkBust(value(cards));
			if (value(cards) == 21) {
				System.out.print("Your cards are ");
				for (int i = 0; i < cards.length; i++) {
					System.out.print("| " + cards[i] + " ");
				}
				System.out.print("|");
				System.out.println();
				return value(cards);
			}
			hitOrStand(cards);
		} else if (choice.equals("stand")) {
			System.out.print("Your cards are ");
			for (int i = 0; i < cards.length; i++) {
				System.out.print("| " + cards[i] + " ");
			}
			System.out.print("|");
			System.out.println(); // I added this in to show your cards
			return value(cards);
		} else {
			System.out.println("You need to choose hit or stand!");
			hitOrStand(cards);
		}
		return value(cards);

	} // Will ask for user input of whether they want to hit or stand. If we ever get
		// 21 it determines the winner and doesn't let someone accidentally bust. Also
		// makes sure you type hit or stand and not anything else.

	

	public static int computerHit(String[] choices) {
		for(int i = 0; i < 10; i++ ) {
			if (value(choices) <= 15) {
			String choice = cards[(int) (0 + Math.random() * ((51 - 0) + 0))];
			choices = Arrays.copyOf(choices, choices.length + 1);
			choices[choices.length - 1] = choice;
			}
		}
		if (value(choices) > 15) {
				System.out.print("The dealers cards are ");
				for (int i = 0; i < choices.length; i++) {
					System.out.print("| " + choices[i] + " ");
				}
				System.out.print("|");
				System.out.println();
				return value(choices);
			}
			
		return value(choices);
	}
	 // this does the same thing as hit but will only do it if it less than 15 if it
		// is more than it will just return its value. It will also print the cards the
		// dealer has so you know what he got

	public static void pickWinner(int human, int computer) {
		if (computer > human && computer <= 21) {
			System.out.println("You lose! The dealer had " + computer + " and you had " + human + ".");
		} else if (computer == human) {
			System.out.println("You lose on ties! you both had " + computer);
		} else {
			System.out.println("You win! The dealer had " + computer + " and you had " + human + ".");
		}
	} // Just an if else if that determines who is the winner. If it is a tie or he
		// has more than me but less than 22 i lose
}
