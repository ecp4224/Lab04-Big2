import java.nio.file.Path;
import java.util.Scanner;

public class HumanPlayer extends Player {
    @Override
    public List<Card> makeMove(Game game) {
        //TODO Scanner stuff Returns player
        List<Card> middleCards = game.getCurrentMiddle();
        List<Card> cardsToPlay = new List<>();
        Scanner in = new Scanner(System.in);
        printHand();

        System.out.println("Type pass if you would like to pass: ");
        System.out.println("Type in the cards you want to play, Seperate by commas: ");

        String cardInput = in.nextLine();
        if(cardInput.equalsIgnoreCase("pass")){
            return null;
        } else {
            String[] arrayCardInput = cardInput.replace(" ", "").split(",");//"2C","6D"
            for(String s : arrayCardInput){

                if(Integer.parseInt(s.charAt(0)+"") > 15 || Integer.parseInt(s.charAt(0) + "") < 3){
                    System.out.println("Re enter your cards!");
                    return makeMove(game);
                }
                Card c = new Card(Integer.parseInt(s.charAt(0) + ""),s.charAt(1));
                cardsToPlay.add(c);
            }
        }
        return null;
    }
}
