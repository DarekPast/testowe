package pl.opl.java.scorer;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Rzut extends BowlingGame {
	int los[][];
	int kolejny=0;
	Random generator;
	Rzut(){
	int first=-1,second=-1,third=-1;  // random pins in round, -1 oznacza brak rzutu 	
	int i=0; //licznik rund
		los= new int[MAXGAMES][3]; // pierwszy wymiar to roundy, drugi to rzuty
//		los[MAXGAMES][4]= new int[4];
		for (i=0;i<(MAXGAMES-1);i++) {  //
			first=myrandom(FRAME_MAXSCOPE);
			if (first==FRAME_MAXSCOPE){
				second=-1; // brak rzutu
			}	
			else{	
				second=myrandom(FRAME_MAXSCOPE-first);
			}
			
			los[i][0]=first;
			los[i][1]=second;
			los[i][2]=-1;// brak trzeciego rzutu
			// System.out.println("Rzut "+i+" pierwszy="+los[i][0]+", drugi="+los[i][1]+", trzeci"+los[i][2]);
		}
		
		// Last round i=(MAXGAME-1)
		first=myrandom(FRAME_MAXSCOPE);
		//first=10;
		if (first==FRAME_MAXSCOPE){
			second=myrandom(FRAME_MAXSCOPE);
			
			if ((second==FRAME_MAXSCOPE)){
				third=myrandom(FRAME_MAXSCOPE);
			}	
			else{
				third=myrandom(FRAME_MAXSCOPE-second);
			}
		}	
		else{	
			second=myrandom(FRAME_MAXSCOPE-first);
			//second=(FRAME_MAXSCOPE-first);
			if (((first+second)==FRAME_MAXSCOPE)){
				third=myrandom(FRAME_MAXSCOPE);
			}	
			else{	
				third=-1;
		}

		//
	
		}
		
		
		
		los[i][0]=first;
		los[i][1]=second;
		los[i][2]=third;
//		System.out.println("Rzut "+i+" pierwszy="+los[i][0]+", drugi="+los[i][1]+", trzeci"+los[i][2]);
		
	}
	
private int myrandom(int z){
	int l=0;
	if (z==0){
	return 0;
	}
	Random generator = new Random();
	l= generator.nextInt(z); //
			
	return l;
}



public void wypisz(){
	System.out.println("Wygenerowano dane testowe dla gry:");
	for (int i=0;i<(MAXGAMES);i++) {
		System.out.print("Runda"+(i+1)+": "+los[i][0]+","+los[i][1]);
		if (los[i][2]==-1){
			System.out.println("");
		}
		else{
			System.out.println(","+los[i][2]);
		}
		
		
		}		
	}// wypisz
	
}// class Rzut
