package com.example.test;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.test.controller.Translator;

@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		Scanner inputReader = new Scanner(System.in);
        System.out.println("please enter a message to be translated:" );

        String input = inputReader.nextLine();
		String translated = Translator.translate(input);
		System.out.println(translated);
		inputReader.close();
	}

}
