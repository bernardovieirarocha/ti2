package app;

import java.util.Scanner;

import util.IA;


public class Main {
	   private static IA ia = new IA();

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);
	        String prompt;
	        System.out.println("Pergunte ao ChatGPT:");
	        prompt = sc.nextLine();
	        ia.consultarIA(prompt);
	        sc.close();

	    }
}
