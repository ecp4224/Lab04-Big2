import java.nio.file.Path;
import java.util.Scanner;

public class HumanPlayer extends Player {
    @Override
    public List<Card> makeMove(Game game) {
        List<Card> middleCards = game.getCurrentMiddle();
        List<Card> cardsToPlay = new List<>();

        Scanner in = new Scanner(System.in);
        for(Card c : middleCards){
            System.out.println(c.toString());
        }
        printHand();

        System.out.println("Type pass if you would like to pass: ");
        System.out.println("Type in the cards you want to play, Seperate by commas: ");

        String cardInput = in.nextLine();
        if(cardInput.equalsIgnoreCase("pass")){
            return null;
        } else {
            String[] arrayCardInput = cardInput.replace(" ", "").split(",");//"2C","6D"
            if(arrayCardInput.length >= 5) {
                System.out.println("Invalid amount of cards played, Re-try Move.");
                return makeMove(game);
            }
            for(String s : arrayCardInput) {
                if (Integer.parseInt(s.charAt(0) + "") > 15 || Integer.parseInt(s.charAt(0) + "") < 3) {
                    System.out.println("Invalid Move Entered, Value is not correct! Re-try Move.");
                    return makeMove(game);
                } else if (s.charAt(1) != 'D' || s.charAt(1) != 'S' || s.charAt(1) != 'C' || s.charAt(1) != 'H') {
                    System.out.println("Invalid Move Entered, Suit is not correct! Re-try Move.");
                    return makeMove(game);
                } else {
                    Card c = new Card(Integer.parseInt(s.charAt(0) + ""), s.charAt(1));
                    cardsToPlay.add(c);
                }
            }
        }
        return cardsToPlay;
    }
}