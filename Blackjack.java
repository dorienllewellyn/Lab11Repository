/*
Dorien Llewellyn
January 12, 2020
Programming Fundementals 1

Project 1

This program creates a game of Blackjack between the user and the computer.
It uses a pseudo-random program to draw each card from the program deck for a true feeling of Blackjack. 
*/

import java.util.Scanner; 
//Importing scanner to read user inputs throughout the program.
import java.util.Random;  
//Importing the random class to create random (pseudo-random) numbers for cards.   

public class Blackjack{

   public static void outputMenu(){
     //An outputMenu method is created here to reduce redundant code in main().
   
      System.out.println("");
   
      System.out.println("1. Get another card");
      System.out.println("2. Hold hand");
      System.out.println("3. Print statistics");
      System.out.println("4. Exit");
      System.out.println("");
      System.out.print("Choose an option: ");
      //A menu is output to give the user options after the first card draw and then after each input choice.
   }
   
   public static String getCardValueName(int card1){
   
      String cardValue;
   
      if (card1 == 1){
         cardValue = "ACE!";
         //As opposed to most blackjack, an ace in this game is only counted with a value of 1.
      }
            
      else if ((card1 > 1) && (card1 < 11)){
         cardValue = String.valueOf(card1) + "!";
         //If a card is between 2 and 10, the card's number is it's value for the hand.
      }
            
      else if (card1 == 11){
         cardValue = "JACK!";
      }
            
      else if (card1 == 12){
         cardValue = "QUEEN!";
      }
            
      else {
         cardValue = "KING!";
      }
         //11 is also given the string value of Jack as the cards name, 12 the value Queen, and 13 the value King.
      return cardValue;
   
   }

   public static void main(String[] args){
   
      Scanner scanner = new Scanner(System.in); 
      //Creating the scanner object to read input.
      P1Random randomNumber = new P1Random(); 
      //Creating random object, random Number, for card generating.
                
      int chipCount;
      int playerBet;
      int playerChoice = 0;
      int card1;
      String cardValue;
      int playerHand;
      int computerHand;
      int gameNumber;
      int computerWinCount = 0;
      int playerWinCount = 0;
      int tieGameCount = 0;
      double playerWinPercentage = 0;
      //Declaring all needed varialbes and initializing where needed. 
      
      gameNumber = 1; //The first game will start as soon as the program starts.
      
      while(playerChoice != 4){
      //Since playerChoice is initialied to 0, the while loop will be entered for first iteration.
      
         playerHand = 0;
         computerHand = 0; 
         //Both the player and computer hand starts each round at 0.
      
         System.out.println("START GAME #" + gameNumber);
         System.out.println("");
      
         do{
         //A do-while loop is used as the game always begins with user drawing a card and then input at the end of the round.
         //The stipulation to continue the do-while loop is that the userInput is 1 (draw another card).
         
            card1 = randomNumber.nextInt(13) + 1;
            //The user's (player's) card is drawn.
            
            cardValue = getCardValueName(card1);
            
            if((card1 > 10) && (card1 <= 13)){
            card1 = 10;
            //The get card value method is only for name of card, values of King, Queen, and Jack are changed to 10 in this statement.
            }
                     
            playerHand += card1;
            //The first random number between 1 and 13 is converted to it's respective value above and added to the hand.
         
            System.out.println("Your card is a " + cardValue);
            System.out.println("Your hand is: " + playerHand);
            //The player's hand and current card drawn is printed out in every round of every game.
            
            if (playerHand == 21){
               System.out.println("");
               System.out.println("BLACKJACK! You win!");
               playerWinCount += 1;
               //Each blackjack is recorded as 1 win for the user.
               System.out.println("");
               break;
               //If the user's hand adds up to 21, the user automatically wins the game and the next game begins.
               //Break is used to exit the current game, in the nested while loop, and begins the next game in the outer loop.
            }
            
            else if (playerHand > 21){
               System.out.println("");
               System.out.println("You exceeded 21! You lose.");
               computerWinCount += 1;
               //Each loss by the player is recorded as a win for the computer.
               System.out.println("");
               break;
               //If the player continues to draw new cards and the hand eclipses 21, the user automatically loses.
               //Again, the break is used to exit the current game and begins a next game in the outer loop.
            }
            
            outputMenu();
            //The outputMenu method is called to give the user the choices for his move.
         
            playerChoice = scanner.nextInt();
            
            System.out.println("");
            
         
            while ((playerChoice < 1) || (playerChoice > 4) || (playerChoice == 3)){
            //If the user chooses any number outside of the options 1-4, or if the input is 3, the while loop is entered.
               
               if((playerChoice < 1) || (playerChoice > 4)){
                  System.out.println("Invalid input!");
                  System.out.println("Please enter an integer value between 1 and 4.");
                  //If input is outside 1-4, the error message is printed and the user is prompted to enter a new input.
               }
               
               if(playerChoice == 3){
                  //If the input 3 is entered, the stats of the games, wins, losses, ties,and total games are given to the user.
                  System.out.println("Number of Player wins: " + playerWinCount);
                  System.out.println("Number of Dealer wins: " + computerWinCount);
                  System.out.println("Number of tie games: " + tieGameCount);
                  System.out.println("Total # of games played is: " + (gameNumber - 1));
               
                  double playerWinCountDouble = playerWinCount;
                  //The player win count is converted to a double in order to get a double for win percentage.
                  playerWinPercentage = (playerWinCountDouble / (gameNumber - 1)) * 100;
                  //Win percentage calcualted by dividing wins by total number of games (minus 1 for game currently being played).
                  
                  String formattedWinPercentage = String.format("%.1f", playerWinPercentage); 
                  //The win percentage is converted to a string and formatted to limit decimal places to 1.
                  System.out.println("Percentage of Player wins: " + formattedWinPercentage + "%");
               }
            
               outputMenu();
               //Menu and input prompt is outputted each time the input is either invalid or 3.
            
               playerChoice = scanner.nextInt();
            
               System.out.println("");
            
            }
         
         } while (playerChoice == 1); //The stipulation to remain in the nested loop, user input being 1.
         
         if (playerChoice == 2){
            computerHand = randomNumber.nextInt(11) + 16;
            //If the user's hand is neither above or equal to 21 and then the user inputs 2, the computer's hand is determined.
            //The computer's hand is a random number between 16 and 26 (inclusive).
            System.out.println("Dealer's hand: " + computerHand);
            System.out.println("Your hand is: " + playerHand);
            System.out.println("");
            
            if (computerHand > 21 || computerHand < playerHand){
               //If the computer's hand is either greater than 21 or less than the player hand, also under 21, the player wins.
               System.out.println("You win!");
               playerWinCount += 1;
               //The player win count is incremented by 1 for each game won.
               System.out.println("");
            } 
            else if (computerHand == playerHand){
               System.out.println("It's a tie! No one wins!");
               tieGameCount += 1;
               //If the computer and user hand's are equal the game is a tie and tie game count is incremented by 1.
               System.out.println("");
            }
            else{
               //If neither the computer goes above 21, isn't above the user's hand, or the game is a tie, the dealer must have won.
               System.out.println("Dealer wins!");
               computerWinCount += 1;
               //If the computer wins the computer's win count is incremented by 1.
               System.out.println("");
            }
         }
      
         
         gameNumber += 1;
         //After each round, the nested loop is exited and if the outer loop is still true (input isn't 4), game count in incremented and next game begins.
      
      }
      
   }

}