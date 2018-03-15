package main.java;

/* look -> https://tools.ietf.org/html/rfc4180*/


public class RowCSV {

	static int MAX_SEPARATORS=5;
	
	String rowCSV;
	//@Autowired
	String separators[]; // in separator[0] is the actual separate symbol.  
	int separatorsQuantity[];


	public RowCSV() {
		rowCSV="";
		int separatorsQuantity[] = new int[separators.length];
		//separatorsQuantity[0]=0;
	}

	public String getRowCSV() {
		return rowCSV;
	}
	public void setRowCSV(String rowCSV) {
		this.rowCSV=rowCSV;
	}
	public void concatenateRowCSV(String rowCSV2) { //clumping strings
		this.rowCSV.concat(rowCSV2);//=this.rowCSV+rowCSV2;
	}

	
//	search the separator with quotes in string rowCSV
	public	char findSeparatorWithQuotes(char quotes){
//		String separatorWithQuotes = "-1";// -1 means not finds
		char charBeforeQuotes=0;//='';
		char charQuotes=0;
		char charAfterQuotes=0;
//		char separator=0;
		int indexInRow=0;
//		boolean evenEvent = true; // 0 is even event :)
		
		if(rowCSV.isEmpty()){
			return 0; // error. Empty row String
		}
		indexInRow=rowCSV.indexOf(quotes, indexInRow);
		if (indexInRow!=-1) {	
			charBeforeQuotes= rowCSV.charAt(indexInRow-1);
			charQuotes= rowCSV.charAt(indexInRow);
			charAfterQuotes= rowCSV.charAt(indexInRow+1);
			System.out.println(rowCSV+" Index="+indexInRow+", 3 znaki"+charBeforeQuotes+charQuotes+charAfterQuotes);
			if(charAfterQuotes!='"'){
				return charBeforeQuotes; // finded separator
			}
			return 0; //error?? two quotes 
		}//(indexInRow!=-1)
		else{
			return 0; //not finded quotes
		}
	}


// quantity numbers of using string str in string rowCSV 
	public	int quantityOf(String str){
		int counter=0;
		int indexInRow=0;
	
		while(indexInRow!=-1) {// when -1 => end of string
			indexInRow=rowCSV.indexOf(str, indexInRow);
			if (indexInRow>0) {
				counter++;
				System.out.println(rowCSV+" Index="+indexInRow+", licznik"+counter);
			}
		}
		return counter;	
	}
	
	private	void separatorsStatisticOf(){
		int counter=0;
		int indexInRow=0;
		
		System.out.println(rowCSV);
		
		for (int i = 1; i < separators.length; i++) {
			while(indexInRow!=-1) {// when -1 => end of row string
				indexInRow=rowCSV.indexOf(separators[i], indexInRow);
				if (indexInRow>0) {
					counter++;
					System.out.println(separators[i]+" Index="+indexInRow+", licznik"+counter);
				}
			}
			separatorsQuantity[i]=counter; // how often the number repeated in the line
			counter=0;
			indexInRow=0;
		}
		// ????
		for (int i = 1; i < separators.length; i++) {
			separatorsQuantity[0]+=separatorsQuantity[i]; // in 0 position is sume of all
		}
	}

}//RowCSV