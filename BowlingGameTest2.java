import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;


public class BowlingGameTest2 {
	static int MAX = 21;
	static int PERFECTGAME = 12;
    BowlingGame bowlingUnderTest; // = new BowlingGame();
	int [] referenceData,dataFromBowlingGame;
	
		
	
	@Before public void initializeBowlingGameTests() {
		// 

		referenceData = new int[MAX];;
    	dataFromBowlingGame = new int[MAX];	
    	for (int i=0;i<MAX;i++){
    		referenceData[i]=0;
    		dataFromBowlingGame[i]=0;
    		}
	}
	
	@Test public void noBonusesTestAfterSomeRolls() {
		bowlingUnderTest = new BowlingGame();
		int noBonusPins=4; // less that 5
		int someRolls=20;	//max 20 (without extra roll)
		
    	for (int i=0;i<someRolls;i++){
    		bowlingUnderTest.roll(noBonusPins);
    		dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
    		referenceData[i]=noBonusPins*(i+1);
    	}
    	// comparison of tables/array
    	assertArrayEquals(referenceData, dataFromBowlingGame); 	
  	
    } // public void noBonusesTestAfterSomeRolls
	
	@Test public void perfectGameScores300() {
		// Perfect Game 12 x STRIKE
    	bowlingUnderTest = new BowlingGame();
    	int [] patternData = new int[PERFECTGAME];
    	int somePerfectRolls=PERFECTGAME; //change it if error
    	
    	//the pattern of MAX STRIKE bowling game
    	patternData[0]=10;  // 1st round
    	patternData[1]=30; 
    	patternData[2]=60;
    	patternData[3]=90;
    	patternData[4]=120;
    	patternData[5]=150;
    	patternData[6]=180;
    	patternData[7]=210;
    	patternData[8]=240;
    	patternData[9]=270;
    	patternData[10]=290;
    	patternData[11]=300; // 12 round
    	// get scores from BowlingGame
    	for (int i=0;i<somePerfectRolls;i++){
    		bowlingUnderTest.roll(10);
    		dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
    		referenceData[i]=patternData[i];
        	
    	}
    	
   	
    	assertArrayEquals(referenceData, dataFromBowlingGame);
	}
	
	@Test public void perfectGameSpare190() {
		bowlingUnderTest = new BowlingGame();
    	int [] patternData = new int[MAX];
    	int somePerfectSpares=MAX; // change it if error
    	//the pattern of MAX SPARE bowling game
    	patternData[0]=9;
    	patternData[1]=10; 
    	patternData[2]=28;
    	patternData[3]=29;
    	patternData[4]=47;
    	patternData[5]=48;
    	patternData[6]=66;
    	patternData[7]=67;
    	patternData[8]=85;
    	patternData[9]=86;
    	patternData[10]=104;
    	patternData[11]=105;
    	patternData[12]=123;
    	patternData[13]=124;
    	patternData[14]=142;
    	patternData[15]=143;
    	patternData[16]=161;
    	patternData[17]=162;
    	patternData[18]=180;
    	patternData[19]=181;
    	patternData[20]=190;
    	
    	for (int i=0;i<somePerfectSpares;i++){
    		bowlingUnderTest.roll(9);
    		dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
    		referenceData[i]=patternData[i];
    		i++;
    		if (i!=MAX) { // without "MAX+1" roll
    			bowlingUnderTest.roll(1);
        		dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
        		referenceData[i]=patternData[i];
    		}
    	
    	}//(int i=0;i<MAX;i++
// the pattern of MAX SPARE bowling game

    	
    	assertArrayEquals(referenceData, dataFromBowlingGame);
    	
	}
	
	
	@Test public void strikeHasBonusPointsFromNextTwoRolls() {
		bowlingUnderTest = new BowlingGame();
		int oneStrike=2; // only odd numbers and less that 19
		int noBonusPins=1; // less that 5
		int i=0;
		

    	for (i=0;i<oneStrike;i++){
    		bowlingUnderTest.roll(0);
    		dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
    		referenceData[i]=0;
    	}

    	bowlingUnderTest.roll(10); 
        dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
        referenceData[i]=10; 
        	
    	bowlingUnderTest.roll(noBonusPins);
        dataFromBowlingGame[i+1]=bowlingUnderTest.calculateScore();
        referenceData[i+1]=10+2*noBonusPins;
        	
    	bowlingUnderTest.roll(noBonusPins); 
        dataFromBowlingGame[i+2]=bowlingUnderTest.calculateScore();
        referenceData[i+2]=10+4*noBonusPins; 


    	for (i=(oneStrike+3);i<MAX;i++){
        	bowlingUnderTest.roll(0);
        	dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
        	referenceData[i]=10+4*noBonusPins;
        }	
    	
    	// comparison of tables/array
    	assertArrayEquals(referenceData, dataFromBowlingGame); 	
  	
    } // public void strikeHasBonusPointsFromNextTwoRolls()
	
	@Test public void spareHasBonusPointsFromNextRoll() {
		bowlingUnderTest = new BowlingGame();
		int oneStrike=7; // only odd numbers and less that 19
		int noBonusPins=1; // less that 5
		int i=0;
		

    	for (i=0;i<oneStrike;i++){
    		bowlingUnderTest.roll(0);
    		dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
    		referenceData[i]=0;
    	}
    	if (i==oneStrike) {
    		bowlingUnderTest.roll(10); 
        	dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
        	referenceData[i]=10; 
        	
    		bowlingUnderTest.roll(noBonusPins);
        	dataFromBowlingGame[i+1]=bowlingUnderTest.calculateScore();
        	referenceData[i+1]=10+2*noBonusPins;
        	
    		bowlingUnderTest.roll(noBonusPins); 
        	dataFromBowlingGame[i+2]=bowlingUnderTest.calculateScore();
        	referenceData[i+2]=10+3*noBonusPins; 
    	}

    	for (i=(oneStrike+3);i<MAX;i++){
        	bowlingUnderTest.roll(0);
        	dataFromBowlingGame[i]=bowlingUnderTest.calculateScore();
        	referenceData[i]=10+3*noBonusPins;
        }	
    	
    	// comparison of tables/array
    	assertArrayEquals(referenceData, dataFromBowlingGame); 	
  	
    } // public void spareHasBonusPointsFromNextRoll()
} //BowlingGameTest2
