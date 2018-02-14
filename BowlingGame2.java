package main.java;

public class BowlingGame2 {
	private static int MAXFRAME = 11; // MAX count of frame/round + we start from "1" in table
	private static int MAXROLLS = 24; // 2*MAXFRAME+EKSTRA ROLL + we start from "1" in table
	private static int ROLL_MAXSCOPE = 10;
	private static int PINS =0;
	private static int SCORE =1;
	private int roll_nb, frame_nb, roll_in_frame;
	private int rolls[][],gameScore[];// rolls[roll_nb][0-pins,1-score]; roll_nb <1,...,MAXROLLS-1[=23]>

	public BowlingGame2(){
		roll_nb=0; 
		frame_nb=0;
		roll_in_frame=0;
		rolls= new int[MAXROLLS][2]; // rolls table
		gameScore= new int[MAXFRAME]; // game scope table
		rolls[0][PINS]=0;
		rolls[0][SCORE]=0;  
		gameScore[0]=0;
		
//		for(int a=1;a<MAXROLLS;a++){ 
//			rolls[a][PINS]=-1; 
//			rolls[a][SCORE]=-1; 
//			gameScore[a]=-1;
//		}//for
	}// BowlingGame2()
	
	
	public void roll(int pins) {
		validation(pins); // if out of field or not game structure pins => error
		roll_nb++; // next roll number in game
		roll_in_frame++; // next roll in frame/round
		rolls[roll_nb][PINS]=pins; 	// remember pins in rollstable;
		bonuses(pins); 				// add bonuses to previous rolls in rollstable and gameScore
		frameNbControl(pins);		// check frame/roud number
		gameScore[frame_nb]+=pins;	// when roll_nb =1 => gameScore[0][SCORE]=0;

	}
	
	private void frameNbControl(int pins) {
		if ((frame_nb)!=MAXFRAME){
			if (roll_in_frame==2){ // 2 rolls in frame
				frame_nb++;
				roll_in_frame=0;
			}
			if ((roll_in_frame==1)&&((pins)==MAXFRAME)){ // STRIKE
				frame_nb++;
				roll_in_frame=0;
			}
		}
		if ((frame_nb)==MAXFRAME){
			// Do nothing?
		}
					
	}// frameNbControl(int pins)


	private void bonuses(int pins){ // lastframe ???
		// check frame ???
		if ((rolls[roll_nb-2][PINS]==ROLL_MAXSCOPE)&&((roll_nb-2)>0)){
			rolls[roll_nb-2][SCORE]+=pins;
			rolls[roll_nb-1][SCORE]+=pins;
			gameScore[frame_nb]+=pins;
		}
		if ((rolls[roll_nb-1][PINS]==ROLL_MAXSCOPE)&&((roll_nb-1)>0)){
			rolls[roll_nb-1][SCORE]+=pins;
			gameScore[frame_nb]+=pins;
		}
		// only in full frame ?? and last frame
		if ((rolls[roll_nb-2][PINS]+(rolls[roll_nb-1][PINS])==ROLL_MAXSCOPE)&&((roll_nb-2)>0)){
			rolls[roll_nb-1][SCORE]+=pins;
			gameScore[frame_nb]+=pins;
		}
	}
	

	
	
	private void validation(int pins) {
		validationRollMaxScope(pins); // pins validation <0,..,ROLL_MAXSCOPE>
		if (roll_in_frame == 1){ // when started second roll in frame/round
			// except STRIKE in last (frame frame_nb==MAXFRAME)
			validationFrameMaxScope(pins); // first + second roll in frame must be in <0,..,ROLL_MAXSCOPE-first_roll> except strike		
		}
		if ((roll_in_frame==2)&&(frame_nb==MAXFRAME)){ // // when started third roll in frame/round; Last round and extra roll  
			validationLastFrame(pins); // 
		}

	}
	
	private void validationFrameMaxScope(int pins2) {
		// TODO Auto-generated method stub
		
		
		
	}


	private void validationLastFrame(int pins2) {
		// TODO Auto-generated method stub
		
	}


	private void validationRollMaxScope(int pins2) {
		// TODO Auto-generated method stub
		
	}


	

	
	
///////////////////////////////////////////////
	public int calculateScore() {
		return gameScore[frame_nb];	
		}//calculateScore()
	
	




	/*	
		try{ 
			int bladpins=pins;
			Boolean isException=false;
			String komunikat="";
			if ((pins<0)||(pins>FRAME_MAXSCOPE)){ // Exception when pins in first roll is out of <0,...,10>
				isException=true;
				komunikat+=("BowlingGame: void roll(int pins). Pins must be from 0 to 10 -> outside the field of input data.\n The value of the given pins="+bladpins+" has been changed to the value ="+pins+"\n");
			}
			if ((pins_in_first_roll+pins>FRAME_MAXSCOPE)&&(roll_nb==FIRST_ROLL)){ // Second roll; Exception when pins in second roll is bigger than "10-pins_in_first_roll".
				if ((lastround)&&(pins_in_first_roll==FRAME_MAXSCOPE)){
					// OK. STRIKE in first roll
				}
				else{// (lastround)&&(pins_in_first_roll==FRAME_MAXSCOPE)&&(pins_in_first_roll+pins>FRAME_MAXSCOPE)
					pins=0;//FRAME_MAXSCOPE-pins_in_first_roll;
					isException=true;
					komunikat+=("BowlingGame: void roll(int pins). Sum of pins in two rolls must be from 0 to 10 -> outside the field of input data.\n The value of the given pins="+bladpins+" has been changed to the value ="+pins+"\n");
				}//(lastround)&&(pins_in_first_roll==FRAME_MAXSCOPE)
			}//if (pins_in_first_roll+pins>FRAME_MAXSCOPE)&&(roll_nb==FIRST_ROLL)
			
		
			if ((lastround)&&(roll_nb==SECOND_ROLL)){//roll == Third 
				
				
				if((pins_in_first_roll == FRAME_MAXSCOPE)||((pins_in_first_roll+pins_in_second_roll)==FRAME_MAXSCOPE)){	
					// OK. SECOND round is SPARE or STRIKE	=> extra roll;
					
					if ((pins_in_second_roll!=FRAME_MAXSCOPE)&&(pins_in_first_roll==FRAME_MAXSCOPE)){ // 
						if((pins_in_second_roll+pins>FRAME_MAXSCOPE)&&((pins_in_first_roll+pins_in_second_roll)!=FRAME_MAXSCOPE)){ // Exception when pins in third roll is bigger than "10-pins_in_second_roll".
							// 
							pins=0;
							isException=true;
							komunikat+=("BowlingGame: void roll(int pins). Without STRIKE in second roll in last frame/round sum of pins in two last rolls must be from 0 to 10 -> outside the field of input data.\n The value of the given pins="+bladpins+" has been changed to the value ="+pins+"\n");
			 	
						}
						else{
							// pins in third roll is less than "10-pins_in_second_rolls".						
						}
					}//if((pins_in_second_roll!=FRAME_MAXSCOPE)&&(pins_in_first_roll==FRAME_MAXSCOPE)) => only STRIKE in first roll
					else{
						// if (pins_in_second_roll==FRAME_MAXSCOPE) => OK (third roll from 0 to 10)
						// or ((pins_in_first_roll+pins_in_second_roll)==FRAME_MAXSCOPE) = OK (third roll from 0 to 10)
						
					}

				
				}
				else{// pins_in_first_roll == FRAME_MAXSCOPE or (pins_in_first_roll+pins_in_first_roll)==FRAME_MAXSCOPE else ERROR
					pins=0;
					isException=true;
					komunikat+=("BowlingGame: void roll(int pins). There is no ekstra roll (third) in last frame/round!!!\n");
					
				}// pins_in_first_roll == FRAME_MAXSCOPE)||((pins_in_first_roll+pins_in_second_roll)==FRAME_MAXSCOPE))
			}//if ((lastround)&&(roll_nb==SECOND_ROLL)){//roll == Third 
			
			if (isException){// sum of Exceptions
				throw new Exception(komunikat);
			} 
		}//try
		catch (Exception e){
			
			System.out.println(e);
			
		}// 
		
		
		return true;
	}
	
	
	
*/
}// class BowlingGame
