package longestsubstring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {
    public static void main(String[] args) {

        System.out.println("Longest substring is: "+ lengthOfLongestSubstring("au"));
        System.out.println("Longest substring is: "+ lengthOfLongestSubstring("asdasdfdfsdgdfgd"));
        System.out.println("Longest substring is: "+ lengthOfLongestSubstring("qwsdfrgth"));
    }

    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> charMap = new HashMap<>();
        int maiorTamanho = 0;
        int cursorEsquerda = 0;
        int cursorDireita = 0;

        while (cursorDireita < s.length()) {
            if (charMap.containsKey(s.charAt(cursorDireita))) {
                cursorEsquerda = Math.max(charMap.get(s.charAt(cursorDireita)) + 1, cursorEsquerda);
            }
            charMap.put(s.charAt(cursorDireita), cursorDireita);
            maiorTamanho = Math.max(maiorTamanho, cursorDireita - cursorEsquerda + 1);
            cursorDireita++;
        }
        return maiorTamanho;
    }
}

