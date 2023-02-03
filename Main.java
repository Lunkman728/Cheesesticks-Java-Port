import java.util.Arrays;
import java.util.*;


class Main {
  //testing123
  public static void clear(){
    System.out.print("\033[H\033[2J");
      System.out.flush();
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to Cheesesticks!");
    Functions.Global.deck = Functions.shufle();
    //Get the proper number of other bots
    int playerCount = 3;
    String instructRule = new String("");
    boolean instructCheck = false;
    boolean inputCheck = false;

    //Continue working on this
  
    System.out.println("Do you already know how to play cheesesticks?\nSay yes or no!");
    instructRule = scan.next();
    instructRule = instructRule.toLowerCase();
    while(inputCheck == false){
      if(instructRule.equals("yes") || instructRule.equals("no")){
        inputCheck = true;
        if(instructRule.equals("yes")){
          instructCheck = false;
        }
        if(instructRule.equals("no")){
          instructCheck = true;
        }
        break;
      }else{
        System.out.println("Please enter a valid move.\nYour options are either \"Cheesestick\" or \"Ask\".");
        instructRule = scan.next();
        instructRule = instructRule.toLowerCase();
      }
    }
    if(instructCheck == true){
      System.out.println("ok pal. think gofish");
    }else if(instructCheck == false){
      System.out.println("pojjers!");
    }

    System.out.println("Press any letter key and enter to continue.");
    scan.next();
    clear();
    System.out.println("How many bots do you want to play with? Answer 2 - 7.");
    playerCount = scan.nextInt();
    while(!(playerCount >= 2 && playerCount <= 7)){
      System.out.println(Arrays.toString(Functions.Global.deck)+" CURRENT DECK");
      System.out.println("Enter a proper value. 2-7 bots?");
      playerCount = scan.nextInt();
    }
    
    String[] oneCards = new String[5];
    String[] twoCards = new String[5];
    String[] threeCards = new String[5];
    String[] fourCards = new String[5];
    String[] fiveCards = new String[5];
    String[] sixCards = new String[5];
    String[] sevenCards = new String[5];

    oneCards = Functions.cardDivide(playerCount, 1);
    twoCards = Functions.cardDivide(playerCount, 2);
    threeCards = Functions.cardDivide(playerCount, 3);
    fourCards = Functions.cardDivide(playerCount, 4);
    fiveCards = Functions.cardDivide(playerCount, 5);
    sixCards = Functions.cardDivide(playerCount, 6);
    sevenCards = Functions.cardDivide(playerCount, 7);
    
    Player p1 = new Player(oneCards, 1, false);
    Player p2 = new Player(twoCards, 2, true);
    Player p3 = new Player(threeCards, 3, true);
    Player p4 = new Player(fourCards, 4, true);
    Player p5 = new Player(fiveCards, 5, true);
    Player p6 = new Player(sixCards, 6, true);
    Player p7 = new Player(sevenCards, 7, true);

    Player[] players = new Player[]{p1,p2,p3,p4,p5,p6,p7};
    int[] scoreboard = new int[]{p1.getScore(),p2.getScore(),p3.getScore(),p4.getScore(),p5.getScore(),p6.getScore(),p7.getScore()};
    String[] allCards = new String[]{p1.getCards(), p2.getCards(), p3.getCards(), p4.getCards(), p5.getCards(), p6.getCards(), p7.getCards()};
    Boolean[] emptHands = new Boolean[]{p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand(),p5.getHand(),p6.getHand(),p7.getHand()};
    String desire = new String("");
    int pAsk = 0;
    int gameEnd = 0;
    boolean gameConsole = true;
    String result = new String("This is the first move, there was no last move!");
    clear();
    //Game Loop
    while(gameConsole){
      for(int playerTurn = 0; playerTurn < playerCount; playerTurn++){
        scoreboard[playerTurn] = players[playerTurn].getScore();
        allCards[playerTurn] = players[playerTurn].getCards();
        emptHands[playerTurn] = players[playerTurn].updateHand(players[playerTurn]);
        if(players[playerTurn].getAI() == false){
          System.out.println("Here is the current score:");
          for(int scoreCheck = 0; scoreCheck < playerCount; scoreCheck++){
            System.out.printf("Player %s's score: %s\n", players[scoreCheck].playerNum, players[scoreCheck].getScore());
          }
          System.out.println("Last move results!!\n"+result);
          System.out.println("");
          System.out.printf("Here are your cards: %s\n",p1.getCards());
          System.out.println("You can do two things, check for a Cheesestick, or ask for a card.\nPlease type either \"Cheesestick\" or \"Ask\".");
          String move = new String("");

          //FALSE - CHEESECHECK
          //TRUE - ASK
          Boolean moveCheck = false;
          Boolean moveConfirm = false;
          move = scan.next();
          move = move.toUpperCase();
          while(moveConfirm == false){
            if(move.equals("CHEESESTICK") || move.equals("ASK")){
              moveConfirm = true;
              if(move.equals("CHEESESTICK")){
                moveCheck = false;
              }
              if(move.equals("ASK")){
                moveCheck = true;
              }
              break;
            }else{
              System.out.println("Please enter a valid move.\nYour options are either \"Cheesestick\" or \"Ask\".");
              move = scan.next();
              move = move.toUpperCase();
            }
          }

          //Code for asking cards
          if(moveCheck == true){
            System.out.println("What player would you like to ask? Please enter a number from 2 to "+playerCount+".");
            pAsk = scan.nextInt();
            while(pAsk == 1 || pAsk>playerCount){
              if(!(pAsk!=1) || !(pAsk<playerCount)){
                System.out.println("Enter a valid player. 2-"+playerCount+"!");
                pAsk = scan.nextInt();
              }
            }
            System.out.println("What card would you like to request from "+pAsk+"?\nHere are the cards you can ask for:"+Functions.getSet());
            desire = scan.next();
            desire = desire.toUpperCase();
            boolean cardCheck = false;
            while(cardCheck == false){
              int cardIndex = 0;
              while(cardIndex < Functions.Global.oneSet.length){
                if(Functions.Global.oneSet[cardIndex].equals(desire)){
                  cardCheck = true;
                }
                cardIndex++;
                if(desire.equals("A")){
                  cardCheck = true;
                }
                if(cardIndex == 12 && cardCheck == false){
                  System.out.println("That was not a valid card. The deck to choose from is: "+Functions.getSet());
                  desire = scan.next();
                  desire = desire.toUpperCase();
                }
              }
            }
            clear();
            result = Player.cardAsk(p1, players[pAsk-1], desire);
          }else{ 
            Player.cheeseCheck(p1);
          }
          }
        //clear();
        if(players[playerTurn].getAI() == true){
          //TimeUnit.SECONDS.sleep(5);
          boolean aiPlayerAsk = false;
          int aiPlayer = 0;
          String aiCard = "";
          while(!aiPlayerAsk){
            aiPlayer = Functions.getRandomNumber(1,playerCount);
            if(aiPlayer != players[playerTurn].getPlayerNum()){
              aiPlayerAsk = true;
            }
            aiPlayer = Functions.getRandomNumber(1,playerCount);
          }
          aiCard = Functions.Global.oneSet[Functions.getRandomNumber(0,12)];
          Player.cardAsk(players[playerTurn],players[aiPlayer-1],aiCard);
          Player.cheeseCheck(players[playerTurn]);
        }
        gameEnd = 1;
        for(int gameFinisher = 0; gameFinisher <playerCount; gameFinisher++){
          if(players[gameFinisher].getHand() == true){
            gameEnd++;
          }
          if(gameEnd == playerCount){
            gameConsole = false;
          }
          
        }
      }
    }
    scan.close();
    clear();
    String[] placement = new String[]{"First Place", "Second Place ","Third Place ", "Fourth Place ", "Fifth Place ", "Sixth Place ", "Seventh Place "};
    int place = 0;
    Arrays.sort(scoreboard);
    int[] temp = new int[scoreboard.length];
    int temporary = 0;
    for(int reverse = scoreboard.length-1; reverse>= 0; reverse--){
      temp[temporary] = scoreboard[reverse];
      temporary++;
    }
    scoreboard = temp;
    System.out.println("The grand game comes to a halt! Here's the scores!");
    for(Player scoreCreate: players){
      place = -1;
      for(int scoreCheck: scoreboard){
        place++; 
        if(scoreCreate.getScore() == scoreCheck){
          System.out.printf("Player %s got %s with a score of %s!\n",scoreCreate.getPlayerNum(),placement[place], scoreCheck);
        }
      }
    }
  }
}