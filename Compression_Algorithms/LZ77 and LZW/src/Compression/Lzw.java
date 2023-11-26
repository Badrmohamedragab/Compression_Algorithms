package Compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lzw implements Compression{

    @Override
    public String compress(String letter) {
        int Size = 127;
        Map<String, Integer> mp = new HashMap<>(Size);
        for(int i = 0 ; i <= Size;i++)
            {
                mp.put(String.valueOf((char)(i)), i);
            }

        StringBuilder currentChars = new StringBuilder();
        StringBuilder compressionResult = new StringBuilder();
        for(int i = 0;i< letter.length();i++)
        {
            currentChars.append(letter.charAt(i));
            if(mp.containsKey(currentChars.toString()))
            {
                continue;
            }
            else {
                mp.put(currentChars.toString(),++Size);
                currentChars = new StringBuilder(currentChars.substring(0, currentChars.length() - 1));
                compressionResult.append(mp.get(currentChars.toString()));
                compressionResult.append(',');
                currentChars = new StringBuilder();
                currentChars.append(letter.charAt(i));
            }
        }
        if(!currentChars.isEmpty())
        {
            compressionResult.append(mp.get(currentChars.toString()));
        }

        return compressionResult.toString();
    }

    @Override
    public String deCompress(String letter) {
        int Size = 127;
        Map<Integer, String> mp = new HashMap<>(Size);
        for(int i = 0 ; i <= Size;i++)
        {
            mp.put(i , String.valueOf((char)(i)));
        }
       String[] arr =  letter.split(",");
       StringBuilder matched = new StringBuilder();
        StringBuilder currentSub = new StringBuilder();
            for (String str : arr)
        {
            int num = Integer.parseInt(str);
            if(mp.containsKey(num))
            {
                matched.append(mp.get(num));
            }
            else {
                mp.put(++Size, currentSub.toString() + currentSub.charAt(0));
                matched.append(mp.get(num));
            }
            currentSub.append(mp.get(num).charAt(0));
            if(!mp.containsValue(currentSub.toString()))
            {
                mp.put(++Size, currentSub.toString());
                currentSub = new StringBuilder();
                currentSub.append(mp.get(num));
            }

        }
        return matched.toString();

    }

}
