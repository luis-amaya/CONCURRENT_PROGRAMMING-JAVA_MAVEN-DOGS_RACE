package edu.eci.arsw.primefinder;

import java.io.BufferedReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		LinkedList<PrimeFinderThread> threads = new LinkedList<>();

		int range = 30000000;
		for (int i = 0; i< range; i += range/3){
			threads.add(new PrimeFinderThread(i, i + range/3));
		}

		for(PrimeFinderThread thread: threads){
			thread.start();;
		}

		Date startDate = new Date();
		int numberSeconds = 0;
		while(numberSeconds != 5){
			Date endDate = new Date();
			numberSeconds = (int)((endDate.getTime() - startDate.getTime())/1000);
		}

		for(PrimeFinderThread thread: threads){
			thread.setSuspend(true);
		}
		
		int numberOfPrimes = 0;
		for(PrimeFinderThread thread: threads){
			numberOfPrimes += thread.getNumberOfPrimes();
		}

		System.out.println("Enter para continuar");
		System.out.println("Numero de primos encontrados: "+ numberOfPrimes);
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		sc.close();
		
		if(entrada.equals("")){
			for(PrimeFinderThread thread: threads){
				thread.reanudarHilo();
			}
		}
	}
}
