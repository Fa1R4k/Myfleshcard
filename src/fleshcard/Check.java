package fleshcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Check {

    public static void check(HashMap<String, String> hashMap) {
        System.out.println("How many times to ask?");
        List<String> keysAsArray = new ArrayList<String>(hashMap.keySet());
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            String value = null;
            Random random = new Random();
            value = hashMap.get(keysAsArray.get(((random.nextInt(keysAsArray.size())))));
            String key = getKey(hashMap,value);
            System.out.printf("Print the definition of \"%s\": \n", key);
            String userTerm = scanner.nextLine();
            if (userTerm.equals(value)) {
                System.out.println("Correct!");
            } else if (hashMap.containsValue(userTerm)) {
                String newKey = getKey(hashMap, userTerm);
                System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".\n", value, newKey);
                if (HardestCard.getMapHardestCard().containsKey(key)){
                    HardestCard.setMapHardestCard(key, HardestCard.getMapHardestCard().get(key) + 1);
                } else {
                    HardestCard.setMapHardestCard(key, 1);
                }
            } else {
                System.out.printf("Wrong. The right answer is \"%s\". \n", value);
                if (HardestCard.getMapHardestCard().containsKey(key)){
                    HardestCard.setMapHardestCard(key, HardestCard.getMapHardestCard().get(key) + 1);
                } else {
                    HardestCard.setMapHardestCard(key, 1);
                }
            }
        }
    }


    public static String getKey(HashMap<String, String> hashMap, String userTerm) {
        for (var getKey :
                hashMap.entrySet()) {
            if (getKey.getValue().equals(userTerm)) {
                return getKey.getKey();
            }
        }
        return null;
    }

}
