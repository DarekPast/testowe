package main;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;


public class Wzorzec extends BowlingGame {
	static int WYNIK=1;
	static int RZUT=0;
	int los[][];
	int kolejny=0;
	Random generator;
	public Wzorzec(){
	Random generator = new Random();
	int first=-1,second=-1,third=-1;  // random pins in round, -1 oznacza brak rzutu 	
	int i=0; //licznik rzutów
	int k=0; // licznik kolejek
		los= new int[(2*MAXGAMES)+1][2]; // max liczba rzutów to 2*10 plus 1 extra [pola 0- ilosc, pole 1 - suma punktów]

		for(int a=0;a<((2*MAXGAMES)+1);a++){ // zerowanie bazy
			los[a][RZUT]=-1;
			los[a][WYNIK]=-1;
		}
		
		while (k<(MAXGAMES-1)) {  //
			first=10;//generator.nextInt(FRAME_MAXSCOPE); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			los[i++][RZUT]=first;
			if (first==FRAME_MAXSCOPE){
				//second=-1; // brak rzutu
				k++;
			}	
			else{	
				los[i++][RZUT]=generator.nextInt(FRAME_MAXSCOPE-first);
				k++;
			}

			System.out.println(""+k+"rounda, rzuty:"+first+", drugi="+los[i-1][0]+" ");
		}
		
		// Last round i=(MAXGAME-1)
		first=generator.nextInt(FRAME_MAXSCOPE);

		if (first==FRAME_MAXSCOPE){
			second=generator.nextInt(FRAME_MAXSCOPE);
			
			if ((second==FRAME_MAXSCOPE)){
				third=generator.nextInt(FRAME_MAXSCOPE);
			}	
			else{
				third=generator.nextInt(FRAME_MAXSCOPE-second);
			}
		}	
		else{	
			second=generator.nextInt(FRAME_MAXSCOPE-first);
			//second=(FRAME_MAXSCOPE-first);
			if (((first+second)==FRAME_MAXSCOPE)){
				third=generator.nextInt(FRAME_MAXSCOPE);
			}	
			else{	
				third=-1;
		}

		//
	
		}
		
		los[i++][RZUT]=first;
		los[i++][RZUT]=second;
		los[i++][RZUT]=third;
		System.out.println("Ostatnia runda: pierwszy="+first+", drugi="+second+", trzeci"+third);
		
	}
	
	
public int wylicz(){
	int i=0,runda=0,first=1,second=0;//,third=0;
	// int a;
//	los[i][WYNIK]=los[i][RZUT];
	
	
	//i=1;
	while (runda<MAXGAMES) {  //
		first=los[i][RZUT];
		if (i==0){
			los[i][WYNIK]=los[i][RZUT]; // to pierwszy rzut
		}
		else{
			los[i][WYNIK]=los[i-1][WYNIK]+los[i][RZUT]; // dodaj rzut do wyniku z poprzedniego rzutu
		}
		
		if (first == FRAME_MAXSCOPE){ // gdy strike dodaj wyniki z 2 następncyh rzutów;
			los[i][WYNIK]=los[i][WYNIK]+los[i+1][RZUT]+los[i+2][RZUT]; // gdy strike suma 2 kolejnych rzutów
		}
		else{
			i++;
			second =los[i][RZUT];
			los[i][WYNIK]=los[i-1][WYNIK]+los[i][RZUT]; // dodaj rzut do wyniku z poprzedniego rzutu
		
			if ((first+second) == FRAME_MAXSCOPE){ // gdy SPARE dodaj wyniki z 1 następnego rzutu;
				los[i][WYNIK]=los[i][WYNIK]+los[i+1][RZUT]; // gdy strike suma 2 kolejnych rzutów
			}
		} //else (first=FRAME_MAXSCOPE)
		i++;
		runda++;
		System.out.println("Punktacja "+runda+"rundy wynosi "+los[i-1][1]+" ");
	} // while (runda<MAXGAMES)
// System.out.println("Punktacja roundy "+runda+", po rzucie pierw. "+los[i-2][1]+"i po drugim"+los[i-1][1]+" ");

	if (los[i][WYNIK]!=-1){ // jest ekstra rzut
		los[i][WYNIK]=los[i-1][WYNIK]+los[i+1][RZUT];
		System.out.println("Jest ekstra rzut");
	}
	else{ // nie ma ekstra rzutu
		i--;
		System.out.println("Nie ma ekstra rzutu");

	}
			
	return los[i][WYNIK];
}



public void wypisz(){
	System.out.println("Wygenerowano dane testowe dla gry:");
	for (int i=0;i<(2*MAXGAMES+1);i++) {
		if (los[i][WYNIK]!=-1){
			System.out.println("Rzut"+(i+1)+": "+los[i][RZUT]+", Wynik"+los[i][WYNIK]);
		}		
		}		
	}// wypisz
	
}// class Rzut
