package fleshcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Console {

    public static boolean write = false;
    public static String writeInFile;

    public static void console(String[] args){
        for (int i = 0; i < args.length; i+=2) {
            if (args[i].equals("-import")) {
                try {
                    importStartCard(args[i + 1]);
                    HardestCard.importHardestCard(args[i+1]);
                } catch (Exception e) {
                }
            } else if((args[i].equals("-export"))) {
                write = true;
                try {
                    writeInFile = args[i + 1];
                } catch (Exception e) {
                }
            }
        }
    }

    public static void consoleExit(){
        if(Console.write) {
            Console.exportStartCard(Console.writeInFile);
            HardestCard.exportHardestCard(Console.writeInFile);
        }
    }


    public static void importStartCard(String fileName) {
        Scanner scanner = new Scanner(System.in);
        File file = new File(fileName);
        try (Scanner scannerFile = new Scanner(file)) {
            int n = 0;
            while (scannerFile.hasNext()) {
                String key = scannerFile.next();
                String value = scannerFile.next();
                n++;
                Cards.getCard().put(key, value);
                scannerFile.nextLine();

            }
            System.out.println(n +" cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static void exportStartCard(String fileName){
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file,true)) {
            int n = 0;
            for (var cards:
                    Cards.getCard().entrySet()) {
                writer.write(cards.getKey() + " " + cards.getValue() + "\n");
                n++;
            }
            System.out.println(n + " cards have been saved.");
        } catch (IOException e) {
            System.out.printf("An exception occurred %s \n", e.getMessage());
        }
    }


}
