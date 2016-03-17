import java.util.Scanner;

public class HumanPlayer extends Player {
    @Override
    public List<Card> makeMove(Game game) {
        List<Card> middleCards = game.getCurrentMiddle();
        List<Card> cardsToPlay = new List<>();

        Scanner in = new Scanner(System.in);
        System.out.println("Cards in Middle: ");
        for(Card c : middleCards){
            System.out.println("    " + c.toString());
        }
        System.out.println();
        System.out.println("Hand: ");
        printHand();

        System.out.println("Type pass if you would like to pass");
        System.out.println("Type in the cards you want to play, Separated by commas: ");

        String cardInput = in.nextLine();
        if(cardInput.equalsIgnoreCase("pass")){
            return null;
        } else {
            String[] arrayCardInput = cardInput.toLowerCase().replace(" ", "").split(",");//"2C","6D"
            if(arrayCardInput.length >= 5) {
                System.out.println("Invalid amount of cards played, Re-try Move.");
                return makeMove(game);
            }
            for(String s : arrayCardInput) {
                char r = s.charAt(0);
                char cs = s.charAt(1);

                int rank = 0;
                switch (r) {
                    case 'j':
                        rank = 11;
                        break;
                    case 'q':
                        rank = 12;
                        break;
                    case 'k':
                        rank = 13;
                        break;
                    case 'a':
                        rank = 14;
                        break;
                    default:
                        if (r == '1' && cs == '0') {
                            rank = 10;
                            cs = s.charAt(2);
                        } else {
                            try {
                                rank = Integer.parseInt("" + r);
                            } catch (Throwable ignored) {
                            }
                        }
                        break;
                }

                if (rank > 14 || rank < 2) {
                    System.out.println("Invalid Move Entered, Rank is not correct! Re-try Move.");
                    return makeMove(game);
                } else if (cs != 'd' && cs != 's' && cs != 'c' && cs != 'h') {
                    System.out.println("Invalid Move Entered, Suit is not correct! Re-try Move.");
                    return makeMove(game);
                } else {
                    Card c = new Card(rank, cs);
                    cardsToPlay.add(c);
                }
            }
        }
        return cardsToPlay;
    }
}