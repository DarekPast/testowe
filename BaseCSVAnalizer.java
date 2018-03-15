package main.java;

/* look -> https://tools.ietf.org/html/rfc4180*/


public class ColumnCSVAnalizer {
	static int DUPLICATES=1;
	
	int columnCSV[][][] = new int [256][256][2]; // in tab [0] is nothing;  [line=nr of char in string][unicode][1=counting;0-sume]
	int recordCounter=0;
	int columnCSVLenght[] = new int [256]; // preconfiguration to '-1'
	String records[][] = new String [256][2];
	int duplicates[] = new int [256];
	//int separatorsQuantity[];

	public ColumnCSVAnalizer() {
		for (int i = 0; i < 255; i++) {
			columnCSVLenght[i]=-1;
		}
	}

	public int [][][] getColumnCSV() {
		return columnCSV;
	}
	public void setColumnCSV(int[][][] ColumnCSV) {
		this.columnCSV=ColumnCSV;
	}
	public void addRecordToAnalize(String record) { 

		recordCounter++;
		if(record.isEmpty()){
			columnCSVLenght[recordCounter]=0; // 
			return;
		}
	
			
		byte[] byteRecord=record.getBytes();
		columnCSVLenght[recordCounter]=byteRecord.length;
		for (int i = 0; i < byteRecord.length; i++) {
			columnCSV[i][(int)(byteRecord[i])][1]++;
		}

	}
	
	private int findDuplicates(String record){
		int duplicat=0;
		for (int i = 1; i < recordCounter; i++) {
			if(record.equals(records[i]) ){
				duplicates[i]++; //???
				duplicat++;
			}
		}
		return duplicat;
	}
	
	
}

/*	*/