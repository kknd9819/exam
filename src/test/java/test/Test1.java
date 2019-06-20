package test;

import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        String str = "ABCDE";
        Random random = new Random();
        for(int i=0;i<100;i++){
            int index = random.nextInt(str.length());
            System.out.println(index);
        }
    }
}
