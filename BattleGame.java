// Asu Simla Ayduran 
// 260822715

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class BattleGame {
  private static Random randomNum = new Random(123);

  public static void main(String[] args){
  playGame("player.txt", "monster.txt","spells.txt");
 }

 
 private static void playGame(String fileNameOne, String fileNameTwo, String fileNameThree){
  
  ArrayList<Spell> listSpells = FileIO.readSpells(fileNameThree);

  // Creates game players and displays the information
  System.out.println(":: Players :: ");
  Character player = FileIO.readCharacter(fileNameOne);
  Character enemy =  FileIO.readCharacter(fileNameTwo);
  System.out.println(FileIO.readCharacter(fileNameOne));
  System.out.println(FileIO.readCharacter(fileNameTwo));
  
  // Displays the spell information
  player.setSpells(listSpells);
  System.out.println(":: Spells :: ");
  for (int i = 0; i < listSpells.size(); i++) {
   System.out.print(listSpells.get(i));
  }
  
  
  // Allows user to control the player battle by input command
  while(player.getCurrHealth() > 0 && enemy.getCurrHealth() > 0){
   Scanner in = new Scanner(System.in);
   System.out.println("Enter a command :");
   System.out.println("(Attack / Quit / Name of Spell) ");
   String userInput = in.nextLine();   
   
   // When the input command is "attack"
   if (userInput.equalsIgnoreCase("attack")) {
    doAttack(player, enemy);
    doAttack(enemy, player);
    
   // When the input command is "quit"
   } else if (userInput.equalsIgnoreCase("quit")) {
    System.out.println("\nGoodbye! \n(The user has terminated the game)");
    return;
    
   // When the input command is a type of spell
   } else if (!userInput.equalsIgnoreCase("attack") || userInput.equalsIgnoreCase("quit")) {
    double tempDamage = player.castSpell(userInput,randomNum.nextInt());
    System.out.println(enemy.getName() + " takes " + tempDamage + " damage!");
    enemy.takeDamage(tempDamage);
    System.out.println(enemy.getName() + "'s current health : " + enemy.getCurrHealth());
    System.out.println();
   }
  // The player loses the battle
  } if (player.getCurrHealth() <= 0){
   System.out.println("Oh no! You lost!");
   enemy.increaseWins();
   System.out.println(enemy.getName() + "'s number of wins : " + enemy.getNumWins());
   FileIO.writeCharacter(enemy, "monster.txt");
   
  // The player wins the battle
  } else if (enemy.getCurrHealth() <= 0){
   System.out.println("Congratulation! You won!");
   player.increaseWins();
   System.out.println(player.getName() + "'s number of wins : " + player.getNumWins());
   FileIO.writeCharacter(player, "player.txt");
  }
 }
 
 
 // Allows the player to attack the counterpart and the proper attack value is subtracted from the current health
 private static void doAttack(Character one, Character two) {
  double attackValue = one.getAttackDamage(randomNum.nextInt());
  
  System.out.println(one.getName() + " attacks for " + attackValue + " damage!");
  two.takeDamage(attackValue);
  
  if(two.getCurrHealth() > 0){
   System.out.println(two.getName() + " takes " + attackValue + " damage! \n");
   System.out.println("\t Name : " + two.getName());
   System.out.println("\t [Health: " + two.getCurrHealth() + "]\n");
  }else {
   System.out.println(two.getName() + " takes " + attackValue + " damage! \n");
   System.out.println("\t Name : " + two.getName());
   System.out.println("\t [Health: " + two.getCurrHealth() + "]\n");
   System.out.println(two.getName() + " is knocked out.");
  }
 }
 
 
}
