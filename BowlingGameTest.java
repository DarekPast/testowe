import org.junit.Test;
import static org.junit.Assert.*;


public class BowlingGameTest {
	static int MAX = 21;
	static int PROBE = 10;
	@Test public void testBowlingGameMethod() {
        BowlingGame bowlingUnderTest = new BowlingGame();
 //   	Wzorzec wzor = new Wzorzec(); // Klasa generujaca losowe dane do testowania klasy "BowlingGame"
    	int [] losowanie,danezwzorca,danezklasy;
    	//int i=0;
 // Test I (wszystkie rzuty =9)
    	danezwzorca = new int[MAX];;
    	danezklasy = new int[MAX];	
    	for (int i=0;i<MAX;i++){
    		bowlingUnderTest.roll(1);
    		danezklasy[i]=bowlingUnderTest.calculateScore();
    		danezwzorca[i]=9*(i+1);
    	}
    	assertArrayEquals(danezwzorca, danezklasy);	
 
//  Test II (wszystkie rzuty =10)
    	bowlingUnderTest = new BowlingGame();
    	danezwzorca = new int[PROBE];;
    	danezklasy = new int[PROBE];	
    	for (int i=0;i<PROBE;i++){
    		bowlingUnderTest.roll(10);
    		danezklasy[i]=bowlingUnderTest.calculateScore();
    	}
// wzorzec dla perfect game (12 x 10punktw)
    	danezwzorca[0]=10;
    	danezwzorca[1]=30; 
     	danezwzorca[2]=60;
    	danezwzorca[3]=90;
    	danezwzorca[4]=120;
    	danezwzorca[5]=150;
    	danezwzorca[6]=180;
    	danezwzorca[7]=210;
    	danezwzorca[8]=240;
    	danezwzorca[9]=270;
 //   	danezwzorca[10]=290;
 //   	danezwzorca[11]=300;

    	
    	
    	assertArrayEquals(danezwzorca, danezklasy);	
 
 //  Test III (wszystkie rzuty SAPRE 1+9)
/*    	bowlingUnderTest = new BowlingGame();
    	danezwzorca = new int[MAX];;
    	danezklasy = new int[MAX];	
    	for (int i=0;i<MAX;i++){
    		bowlingUnderTest.roll(9);
    		danezklasy[i]=bowlingUnderTest.calculateScore();
    		danezwzorca[i]=danezwzorca[i]+9;
    		i++;
    		bowlingUnderTest.roll(1);
    		danezklasy[i]=bowlingUnderTest.calculateScore();
    		danezwzorca[i]=danezwzorca[i]+1;
    	}
/ wzorzec dla perfect game (12 x 10punktw)
    	danezwzorca[0]=9;
    	danezwzorca[1]=10; 
     	danezwzorca[2]=20;
    	danezwzorca[3]=21;
    	danezwzorca[4]=30;
    	danezwzorca[5]=31;
    	danezwzorca[6]=;
    	danezwzorca[7]=210;
    	danezwzorca[8]=240;
    	danezwzorca[9]=270;
 //   	danezwzorca[10]=290;
 //   	danezwzorca[11]=300;

    	
    	
    	assertArrayEquals(danezwzorca, danezklasy);	
    	
  */  	
 // Test3   	
 //   	losowanie=wzor.getpins(); // generowanie rzutow
 //   	danezwzorca = wzor.getscore();
 //   	danezklasy = new int[MAX];
    	
 //   	for (int i=0;i<MAX;i++){
 //   		bowlingUnderTest.roll(losowanie[i]);
 //   		danezklasy[i]=bowlingUnderTest.calculateScore();
 //   	}

    	
 //   	bowlingUnderTest.roll(2);
 //   	bowlingUnderTest.roll(3);
//    	assertArrayEquals(danezwzorca, danezklasy);
    	
//    	assertArrayEquals(wzor.getscore(i),bowlingUnderTest.calculateScore());
        //assertTrue("someLibraryMethod should return 'true'", BowlingUnderTest.calculateScore());
        //BowlingUnderTest.someLibraryMethod();
        //classUnderTest.hello();
    }
}
