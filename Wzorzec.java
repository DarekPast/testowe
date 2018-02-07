import java.util.Random;


public class Wzorzec {
	static int MAXGAMES = 10;
	static int FRAME_MAXSCOPE = 10;
//	static int WYNIK=1;
//	static int RZUT=0;
	int los[],wynik[];
	Random generator;
	public Wzorzec(){
	Random generator = new Random();
	int first=-1,second=-1,third=-1;  // random pins in round, -1 oznacza brak rzutu 	
	int i=0; //counter start from "0";
	int k=0; // licznik kolejek
	los= new int[(2*MAXGAMES)+1]; // max liczba rzutów to 2*10 plus 1 extra 

	for(int a=0;a<((2*MAXGAMES)+1);a++){ // zerowanie bazy
		los[a]=-1;
		wynik[a]=-1;
	}
		
	while (k<(MAXGAMES-1)) {  //
		first=generator.nextInt(FRAME_MAXSCOPE); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		los[i++]=first;
		if (first==FRAME_MAXSCOPE){
			// brak drugiego rzutu w kolejce
			k++;
//			System.out.println(""+k+"rounda, rzuty:"+first);
		
		}	
		else{	
			los[i++]=generator.nextInt(FRAME_MAXSCOPE-first);
			k++;

//			System.out.println(""+k+"rounda, rzuty:"+first+", drugi="+los[i-1]+" ");

		}
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
	}	//(first==FRAME_MAXSCOPE)
	else{	
		second=generator.nextInt(FRAME_MAXSCOPE-first);
		//second=(FRAME_MAXSCOPE-first); //testing rule
		if (((first+second)==FRAME_MAXSCOPE)){
			third=generator.nextInt(FRAME_MAXSCOPE);
		}	
		else{	
			third=-1; // "-1" means "null"
	}

		//
	
		}
		
		los[i++]=first;
		los[i++]=second;
		los[i++]=third;
		System.out.println("Ostatnia runda: pierwszy="+first+", drugi="+second+", trzeci"+third);
	
	// po wygenerowaniu wszystkich rzutów poni¿sza metoda wylicza punkty 
		wylicz();
	}
	
	
private void wylicz(){
	int i=0,runda=0,first=0,second=0;//,third=0;
	
	while (runda<MAXGAMES) {  //
		first=los[i];
		if (i==0){
			wynik[i]=los[i]; // to pierwszy rzut
		}
		else{
			wynik[i]=wynik[i-1]+los[i]; // dodaj rzut do wyniku z poprzedniego rzutu
		}
		
		if (first == FRAME_MAXSCOPE){ // gdy strike dodaj wyniki z 2 nastêpncyh rzutów;
			wynik[i]=wynik[i]+los[i+1]+los[i+2]; // gdy strike suma 2 kolejnych rzutów
		}
		else{
			i++;
			second =los[i];
			wynik[i]=wynik[i-1]+los[i]; // dodaj rzut do wyniku z poprzedniego rzutu
		
			if ((first+second) == FRAME_MAXSCOPE){ // gdy SPARE dodaj wyniki z 1 nastêpnego rzutu;
				wynik[i]=wynik[i]+los[i+1]; // gdy spare dodaj kolejny rzut
			}
		} //else (first=FRAME_MAXSCOPE)
		i++; // 
		runda++; //
//		System.out.println("Punktacja "+runda+"rundy wynosi "+los[i-1]+" ");
	} // while (runda<MAXGAMES)

	if (los[i]!=-1){ // jest ekstra rzut
		wynik[i]=wynik[i-1]+los[i+1];
//		System.out.println("Jest ekstra rzut");
	}
	else{ // nie ma ekstra rzutu
		i--;
//		System.out.println("Nie ma ekstra rzutu");

	}
			
	return ;
}

public int [] getpins() {
	return los;
}
public int [] getscore() {
	return wynik;
}

public void wypisz(){
	System.out.println("Wygenerowano dane testowe dla gry:");
	for (int i=0;i<(2*MAXGAMES+1);i++) {
		if (los[i]!=-1){
			System.out.println("Rzut"+(i+1)+": "+los[i]+", Wynik"+wynik[i]);
		}		
		}		
	}// wypisz
	
}// class Rzut