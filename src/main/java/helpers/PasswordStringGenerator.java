package helpers;

import java.util.Random;

public class PasswordStringGenerator {
        public static String generateRandomPassword(){
            StringBuilder password = new StringBuilder();
            for (int i = 0; i<5;i++){
                char charUpperCase = (char) ('A'+Math.random()*('Z'-'A')+1);
                password.append(charUpperCase);
            }
            for (int i = 0; i<5;i++){
                char charLowwerCase = (char) ('a'+Math.random()*('z'-'a')+1);
                password.append(charLowwerCase);
            }
            Random random = new Random();
            for (int i =0; i<3;i++){
                int digit = random.nextInt(10);
                password.append(digit);
            }
            String specialChar = "[]!$";


        }



}
