package fleshcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Cards {
    private static HashMap<String, String> cards = new HashMap<>();

    public static void createCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The card:");
        String cardName = scanner.nextLine();
        if (checkCardName(cardName)) {
            newCardName(cardName);
            return;
        }
        System.out.println("The definition of the card:");
        String cardTerm = scanner.nextLine();
        if (checkCardTerm(cardTerm)) {
            newCardTerm(cardTerm);
            return;
        }
        cards.put(cardName, cardTerm);
        System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", cardName, cardTerm);
    }

    public static boolean checkCardName(String cardName) {
        return cards.containsKey(cardName);
    }

    public static boolean checkCardTerm(String cardTerm) {
        return cards.containsValue(cardTerm);
    }

    public static void newCardName(String cardName) {
        System.out.printf("The card \"%s\" already exists.\n", cardName);
    }

    public static void newCardTerm(String cardTerm) {
        System.out.printf("The definition \"%s\" already exists.\n", cardTerm);

    }

    public static HashMap<String, String> getCard() {
        return cards;
    }

    public static void deleteCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which card?");
        String whatIsDelete = scanner.nextLine();
        if (cards.containsKey(whatIsDelete)) {
            cards.remove(whatIsDelete);
            System.out.println("The card has been removed.");
        } else {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", whatIsDelete);
        }
    }

    public static void importCard() {
        System.out.println("File name: ");
        Scanner scanner = new Scanner(System.in);
        String pathToFile = scanner.nextLine();
        HardestCard.importHardestCard(pathToFile);
        File file = new File(pathToFile);
        try (Scanner scannerFile = new Scanner(file)) {
            int n = 0;
            while (scannerFile.hasNext()) {
                String key_value = scannerFile.nextLine();
                String[] key_valueSplit= key_value.split("_");
                String key = null;
                String value = null;
                try {
                    key = key_valueSplit[0];
                    value = key_valueSplit[1];
                } catch (Exception e) {
                }
                if(!getCard().containsKey(key)){
                    n++;
                }
                getCard().put(key, value);

            }
            System.out.println(n +" cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static void exportCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        String fileStr = scanner.nextLine();
        File file = new File(fileStr);
        HardestCard.exportHardestCard(fileStr);

        try (FileWriter writer = new FileWriter(file,true)) {
            int n = 0;
            for (var cards:
                    getCard().entrySet()) {
                if (cards.getKey().equals(null) && cards.getValue().equals(null)){
                    writer.write(" " + "_" + " " + "\n");
                    n++;
                }else if (cards.getKey().equals(null)){
                    writer.write(" " + "_" + cards.getValue() + "\n");
                    n++;
                }else if (cards.getValue().equals(null)){
                    writer.write(cards.getKey() + "_" + " " + "\n");
                    n++;
                }else {
                    writer.write(cards.getKey() + "_" + cards.getValue() + "\n");
                    n++;
                }
            }
            System.out.println(n + " cards have been saved.");
        } catch (IOException e) {
            System.out.printf("An exception occurred %s \n", e.getMessage());
        }
    }


}