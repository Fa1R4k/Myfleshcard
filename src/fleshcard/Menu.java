package fleshcard;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {

        static boolean exit = true;
        static boolean log = true;

        public static void menu(HashMap<String, String> hashMap) {
                System.out.println("Input the action (add, remove, import, export, ask, exit, hardest card, reset stats):");
                Scanner scanner = new Scanner(System.in);
                String whatAreDo = scanner.nextLine();
                switch (whatAreDo){
                        case "add":
                                Cards.createCard();
                                break;
                        case "remove":
                                Cards.deleteCard();
                                break;
                        case "import":
                                Cards.importCard();
                                break;
                        case "export":
                                Cards.exportCard();
                                break;
                        case "ask":
                                Check.check(hashMap);
                                break;
                        case "exit":
                                exit = false;
                                Console.consoleExit();
                                System.out.println("Bye bye!");
                                break;
                        case "hardest card":
                                HardestCard.soutHardestCard();
                                break;
                        case "reset stats":
                                HardestCard.resetHardestCards();
                                break;
                }
                while (exit){
                        menu(Cards.getCard());
                }
        }
}