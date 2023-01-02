package com.example.test.controller;

import java.util.Arrays;
import java.util.List;
import io.micrometer.common.util.StringUtils;

public class Translator {
    private static List<String> vowelList = Arrays.asList("a", "e", "i","o","u","y","A","E","I","O","U","Y");
	private static List<String> points = Arrays.asList(",", ".","?","!","-","_",";",")");
    private final static String endLetter = "ay";
    
    /*
     * Given a sentence it will be translated to an ancient language
     */
    public static String translate(String sentence) {
        
        if (sentence.chars().allMatch( Character::isDigit) || StringUtils.isEmpty(sentence)) {
            return sentence;
        }
    
        String[] words = sentence.split(" ");
        String translated = "";
        for (String w : words) {
            translated = translated.concat(translateWord(endLetter, w)).concat(" ");
        }
        if (isFirstLetterCapital(String.valueOf(sentence.charAt(0)))) {
            translated = translated.substring(0, 1).toUpperCase() + translated.substring(1);
        }
        return translated;

    }

	private static String translateWord(String endLetter, String word) {
		if (word.chars().allMatch( Character::isDigit)) {
			return word;
		}

		String prefix = getPrefix(word);
		
		String stem = getStem(word,prefix.length());
		String pointing = "";
		if (points.contains(stem.substring(stem.length()-1))) {
			pointing = stem.substring(stem.length()-1);
			stem = stem.substring(0, stem.length()-1);
		}

		String translated = stem.concat(prefix).concat(endLetter).concat(pointing);
		//TODO I am not sure if all sentence must be passed to lower case. If this is not the case we just can coment the next line
		translated = translated.toLowerCase();
		
		return translated;
	}
	
private static String getPrefix(String example) {
	String prefix = "";
	
	for (int i = 0; i < example.length(); i++) {
		String letter = String.valueOf(example.charAt(i));
		if (vowelList.contains(letter)) {
			return prefix;
		}
		prefix = prefix.concat(letter);
	}

	return prefix;
}

private static String getStem(String example, int position) {
	return example.substring(position);
}

private static Boolean isFirstLetterCapital(String firstLetter) {
		if (StringUtils.isEmpty(firstLetter)) {
			   return false;
		}
		return !firstLetter.equals(firstLetter.toLowerCase());
 }
}
