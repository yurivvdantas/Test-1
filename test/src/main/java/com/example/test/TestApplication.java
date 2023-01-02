package com.example.test;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.micrometer.common.util.StringUtils;

@SpringBootApplication
public class TestApplication {

	private static List<String> vowelList = Arrays.asList("a", "e", "i","o","u","y","A","E","I","O","U","Y");

	public static void main(String[] args) {
		String endLetter = "ay";
		String word = "Hey buddy, get away from my car!";

		translate(endLetter, word);
	}

	private static void translate(String endLetter, String example1) {
		if (example1.chars().allMatch( Character::isDigit )) {
			return;
		}

		String prefix = getPrefix(example1);
		// System.out.println(prefix);
		
		String stem = getStem(example1,prefix.length());
		// System.out.println(stem);

		String translated = stem.concat(prefix).concat(endLetter);
		translated = translated.toLowerCase();
		
		if (isFirstLetterCapital(String.valueOf(example1.charAt(0)))) {
			translated = translated.substring(0, 1).toUpperCase() + translated.substring(1);
		}
		System.out.println(translated);
	}

private static String getPrefix (String example) {
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
