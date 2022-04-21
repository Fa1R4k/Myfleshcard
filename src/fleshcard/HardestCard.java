package fleshcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HardestCard {
    private static Map<String, Integer> mapHardestCard= new HashMap<>();
    private static HashSet<String> fileImportExportForStats = new HashSet<>();

    public static void resetHardestCards(){
        for (String fileStr:
                fileImportExportForStats) {
            File file = new File(fileStr);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(" ");
            } catch (IOException e) {
            }
        }
        mapHardestCard.clear();
        System.out.println("Card statistics have been reset.");
    }


    public static Map<String, Integer> getMapHardestCard() {
        return mapHardestCard;
    }

    public static void setMapHardestCard(Map<String, Integer> newMapHardestCard) {
        mapHardestCard = newMapHardestCard;
    }

    public static void setMapHardestCard(String key, Integer value){
        mapHardestCard.put(key, value);
    }

    public static void soutHardestCard(){
        if (getMapHardestCard().size() == 0){
            System.out.println("There are no cards with errors.");
        } else {
            int temp = 0;
            for (var entryHardestCard:
                    getMapHardestCard().entrySet()) {
                int value = entryHardestCard.getValue();
                if (value > temp){
                    temp = value;
                }
            }
            HardestCard.getKey(HardestCard.getMapHardestCard(), temp);
        }
    }

    public static void getKey(Map<String, Integer> hashMap, Integer userTerm) {
        List<String> getKeyList = new ArrayList<>();
        for (var getKey :
                hashMap.entrySet()) {
            if (getKey.getValue().equals(userTerm)) {
                getKeyList.add("\"" + getKey.getKey() + "\"");
            }
        }

        if (getKeyList.size() == 1) {
            System.out.print("The hardest card is ");
            System.out.print(getKeyList.get(0));
            System.out.printf(" . You have \"%d\" errors answering it.\n",userTerm);
        } else {
            System.out.print("The hardest cards are ");
            System.out.print(getKeyList.get(0));
            for (int i = 1; i < getKeyList.size(); i++) {
                System.out.print(", " +getKeyList.get(i));
            }
            System.out.printf(". You have %d errors answering them.\n", userTerm);

        }
    }

    public static void importHardestCard(String fileStr) {
        File file = new File(fileStr);
        fileImportExportForStats.add(fileStr);
        try (Scanner scannerFile = new Scanner(file)) {
            while (scannerFile.hasNext()) {
                String key_value = scannerFile.nextLine();
                String[] key_valueSplit = key_value.split("_");
                try {
                    String key = key_valueSplit[0];
                    String value = key_valueSplit[1];
                    getMapHardestCard().put(key, Integer.parseInt(value));
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException e) {
        }
    }

    public static void exportHardestCard(String fileStr){
        File file = new File(fileStr);
        fileImportExportForStats.add(fileStr);
        try (FileWriter writer = new FileWriter(file,true)) {
            for (var cards:
                    getMapHardestCard().entrySet()) {
                writer.write(cards.getKey() + "_" +  cards.getValue() + "\n");
            }
        } catch (IOException e) {
        }
    }


}
