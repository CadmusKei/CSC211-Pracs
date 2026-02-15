import java.util.HashMap;

public class Test {
    public static void main(String[] args)
    {
        System.out.println(reverseString("This is a test"));
    }

    static String reverseString(String input){
        if (input == null) return "";
        return new StringBuilder(input).reverse().toString();
    }

    static HashMap<Character, Integer> LetterFreg(String input)
    {
        if (input == null) return new HashMap<>();
        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (char c : input.toCharArray())
        {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        return freqMap;
    }



}
