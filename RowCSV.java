package csv.file.reader;

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
	public void attachRowCSV(String rowCSV2) {
		this.rowCSV=this.rowCSV+rowCSV2;
	}

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


}//RowCSV
