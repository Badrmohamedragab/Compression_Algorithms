package Compression;

import Compression.Compression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lz77 implements Compression {
    public String compress(String letter) {
        StringBuilder buffer = new StringBuilder();
        StringBuilder tags = new StringBuilder();
        String currentSubStr ="";
        int index;
        for(int i = 0; i < letter.length() ;i++)
        {
            currentSubStr+= letter.charAt(i);
            if(!(buffer.toString().contains(currentSubStr))) {
                String sub ="";
                if(!buffer.isEmpty()) {
                     sub = buffer.substring(0, buffer.length() - 1);
                }

                currentSubStr = currentSubStr.substring(0, currentSubStr.length()-1);

                index = sub.lastIndexOf(currentSubStr);
                if(currentSubStr.length() == 0)
                {
                    tags.append("<"+0+","+ (currentSubStr.length())+","+letter.charAt(i)+"> ");
                }
                else {
                    tags.append("<" + (i - currentSubStr.length() -index) + "," + (currentSubStr.length()) + "," + letter.charAt(i) + "> ");
                }
                currentSubStr ="";

            }
            buffer.append(letter.charAt(i));

        }

        if(!currentSubStr.isEmpty() && buffer.toString().contains(currentSubStr))
        {
            char c =  currentSubStr.charAt(currentSubStr.length()-1);
            currentSubStr = currentSubStr.substring(0, currentSubStr.length()-1);
            index = buffer.lastIndexOf(currentSubStr);
            String reCorrect = buffer.substring(0, index);
            int idx = 0;
            StringBuilder sub  = new StringBuilder() ;
            for(int i = 0; i< currentSubStr.length();i++)
            {
                sub.append(currentSubStr.charAt(i));
                if(reCorrect.contains(sub))
                {
                    idx = reCorrect.lastIndexOf(String.valueOf(sub));
                    reCorrect+= sub.charAt(sub.length()-1);

                }
                else {
                    break;
                }
            }

            tags.append("<" + (index -idx ) + "," + (currentSubStr.length()) +","+ c+ "> ");
        }
        return tags.toString();


    }

    @Override
    public String deCompress(String letter) {
        StringBuilder currentSubStr = new StringBuilder();
        letter = letter.replaceAll("[\\s<]", "");
        letter = letter.replaceAll("[,>]", " ");
        List<String> listOfChar = List.of(letter.split(" "));
        for (int i = 0; i< listOfChar.size(); i++)
        {
            if((i+1)%3 == 0)
            {
                int pos = Integer.parseInt(listOfChar.get(i-2));
                int length = Integer.parseInt(listOfChar.get(i-1));
                char nextChar = listOfChar.get(i).charAt(0);

                while(length!=0)
                {
                    int j = currentSubStr.length() - pos;
                    currentSubStr.append(currentSubStr.charAt(j));
                    j++;
                    length--;
                }
                currentSubStr.append(nextChar);
            }

        }
        return currentSubStr.toString();
    }
}
/*    public String compress(String letter) {
        StringBuilder buffer = new StringBuilder();
        StringBuilder tags = new StringBuilder();
        String currentSubStr ="";
        int index;
        for(int i = 0; i < letter.length() ;i++)
        {
            currentSubStr+= letter.charAt(i);

            // Check if the current substring is already in the buffer. If not, add it to the buffer and tags.
            if(!(buffer.toString().contains(currentSubStr))) {
                String sub ="";
                if(!buffer.isEmpty()) {
                     sub = buffer.substring(0, buffer.length() - 1);
                }

                currentSubStr = currentSubStr.substring(0, currentSubStr.length()-1);
                // Find the last index of the current substring in the buffer.
                index = sub.lastIndexOf(currentSubStr);

                // If the current substring is empty, add a tag to the tags string with a length of 0.
                if(currentSubStr.length() == 0)
                {
                    tags.append("<"+0+","+ (currentSubStr.length())+","+letter.charAt(i)+"> ");
                }
                // Otherwise, add a tag to the tags string with the offset, length, and current character.
                else {
                    tags.append("<" + (i - currentSubStr.length() - index) + "," + (currentSubStr.length()) + "," + letter.charAt(i) + "> ");
                }
                currentSubStr ="";
            }

            // Add the current character to the buffer.
            buffer.append(letter.charAt(i));
        }

        // If the current substring is not empty and is in the buffer, add a tag to the tags string with the offset, length, and current character.
        if(!currentSubStr.isEmpty() && buffer.toString().contains(currentSubStr))
        {
            index = buffer.lastIndexOf(currentSubStr);
            tags.append("<" + (buffer.length() - currentSubStr.length() - index) + "," + (currentSubStr.length()) +","+ currentSubStr.charAt(currentSubStr.length()-1)+ "> ");
        }

        // Return the tags string.
        return tags.toString();
    }*/