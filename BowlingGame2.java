public class BowlingGame2 {
	private static int MAXFRAME = 11; // MAX count of frame/round + we start from "1" in table
	private static int MAXROLLS = 24; // 2*MAXFRAME+EKSTRA ROLL + we start from "1" in table
	private static int ROLL_MAXSCOPE = 10;
	private static int PINS =0; 
	private static int BONUS =1;
	private static int SCORE =2;
	private static int STRIKE =2; 
	private static int SPARE =1;
	private int roll_nb, frame_nb, roll_in_frame;
	private int rolls[][];// rolls[roll_nb][0-pins,1-bonus,2-score]; roll_nb <1,...,MAXROLLS-1[=23]>
	private int gameScore[];// gameScore[frame_nb]; frame_nb <1,...,MAXFRAME-1[=10]>

	public BowlingGame2(){
		roll_nb=0; 
		frame_nb=0;
		roll_in_frame=0;
		rolls= new int[MAXROLLS][3]; // rolls table
		gameScore= new int[MAXFRAME]; // game scope table
		rolls[0][PINS]=0;
		rolls[0][SCORE]=0;
		rolls[0][BONUS]=0;
		gameScore[0]=0;
		
		for(int a=1;a<MAXROLLS;a++){ 
			rolls[a][PINS]=0; 
			rolls[a][SCORE]=0; 
			rolls[a][BONUS]=0; 

		}//for
	}// BowlingGame2()
	
	
	public void roll(int pins) {
		validation(pins); // if out of field or not game structure pins => error
		roll_nb++; // next roll number in all game
		roll_in_frame++; // next roll in current frame/round
		rolls[roll_nb][PINS]=pins; 	// remember pins in rollstable;
		setBonuses(pins);
		frameNbControl(pins);		// check frame/round number
		addBonuses(pins); 				// add bonuses to previous rolls in rollstable and gameScore
		if ((frame_nb)!=MAXFRAME) {
			gameScore[frame_nb]+=pins;	
		}
		if ((frame_nb)==MAXFRAME) {
			if (roll_in_frame==1) { // pins from first roll in last frame are added
				gameScore[frame_nb]+=pins;		
			}
			if ((roll_in_frame==2)&&(pins!=ROLL_MAXSCOPE)) { // pins from second and third rolls added in addbonuses when STRIKE
				if(rolls[roll_nb-1][PINS]!=ROLL_MAXSCOPE) {
					gameScore[frame_nb]+=pins;		// When not STRIKE add pins
				}
			}
			// if (roll_in_frame==3) => pins from third roll was added in addBonuses() when STRIKE or SPARE
		}
	}
	
	private void frameNbControl(int pins) {
		if ((frame_nb)!=MAXFRAME){
			if (roll_in_frame==2){ // 2 rolls in frame
				frame_nb++;      // new frame/round
				roll_in_frame=0; // reset rolls in frame/round 
			}
			if ((roll_in_frame==1)&&(pins==ROLL_MAXSCOPE)){ // STRIKE
				frame_nb++;
				roll_in_frame=0;
			}
		}
		else { //if ((frame_nb)==MAXFRAME)
			// Do nothing: Not new frame/round
		}
					
	}// frameNbControl(int pins)
	private void setBonuses(int pins){
		
		if ((pins == ROLL_MAXSCOPE)&&(roll_in_frame==1)) { 		// in last frame if STRIKE in second round => not bonus
			rolls[roll_nb][BONUS]=STRIKE;
		}
		// SPARE only when it is second roll in frame/round (last frame too)
		if (((rolls[roll_nb-1][PINS]+ pins) == ROLL_MAXSCOPE)&&(roll_in_frame==2)) {
			rolls[roll_nb][BONUS]=SPARE;
		}
		
	}

	private void addBonuses(int pins){ 
		// if not last round!!!
		
		if (roll_nb==1) {return;} // if first roll there isn't bonuses
		
		// if STRIKE in n-2 [second bonus] 
		if ((rolls[roll_nb-2][BONUS]-->0)&&((roll_nb-2)>0)){
			rolls[roll_nb-2][SCORE]+=pins;
			rolls[roll_nb-1][SCORE]+=pins;
			gameScore[frame_nb]+=pins;
		}
		// if STRIKE - first bonus
		// if SPARE - one bonus
		if ((rolls[roll_nb-1][BONUS]-->0)&&((roll_nb-1)>0)){
			rolls[roll_nb-1][SCORE]+=pins;
			gameScore[frame_nb]+=pins;
		}
	
		return;
	}
	

	
	
	private void validation(int pins) {
		validationRollOutOfScope(pins); // pins validation <0,..,ROLL_MAXSCOPE>
		if (roll_in_frame == 1){ // when started second roll in frame/round
			// except STRIKE in last frame (frame_nb==MAXFRAME)
			validationSecoundRollPinsInFrame(pins); // first + second roll in frame must be in <0,..,ROLL_MAXSCOPE-first_roll> except strike		
		}
		if ((roll_in_frame==2)&&(frame_nb==MAXFRAME)){ // // when started third roll in frame/round; Last round and extra roll  
			validationExtraRollInLastFrame(pins); // 
		}

	}
	
	private void validationRollOutOfScope(int pins2) {
		try{ 
			if ((pins2<0)||(pins2>ROLL_MAXSCOPE)){ // Exception when pins in first roll in frame/round is out of <0,...,10>
				throw new Exception("BowlingGame: void roll(int pins). Pins must be from 0 to 10 -> outside the field of input data.\n");
			}	
		}
		catch (Exception e){
			System.out.println(e);
			throw new IllegalArgumentException("validationRollOutOfScope()");// 
		}
	}
	
	private void validationSecoundRollPinsInFrame(int pins2) {
		try{ //validation:  when started second roll in frame/round
			// Pins in first roll in frame == ROLL_MAXSCOPE AND there is second roll only in last frame
			if ((rolls[roll_nb][PINS]+pins2>ROLL_MAXSCOPE)&&(rolls[roll_nb][PINS]!=ROLL_MAXSCOPE)){ // Second roll in frame/round; Exception when pins in second roll is bigger than "10-pins_in_first_roll".
				throw new Exception("BowlingGame: void roll(int pins). Sum of pins in two rolls must be from 0 to 10 -> outside the field of input data.\n");
			}
		}
		catch (Exception e){
			System.out.println(e);
			throw new IllegalArgumentException("validationSecoundRollPinsInFram()");// 
		}
	}


	private void validationExtraRollInLastFrame(int pins2) {
		// validation(int pins): if (roll_in_frame == 1) (second roll in frame)
		try{ // Extra roll in last frame
			// if no STRIKE or SPARE in last frame => no extra roll
			if ((rolls[roll_nb-1][PINS]!=ROLL_MAXSCOPE)||((rolls[roll_nb-1][PINS]+rolls[roll_nb][PINS])!=ROLL_MAXSCOPE)){
				throw new Exception("BowlingGame: void roll(int pins). There is no ekstra roll (third) in last frame/round!!!\n");
			}
			// if (roll in second roll in last frame != ROLL_MAXSCOPE) sum second and third must be <= ROLL_MAXSCOPE
			if ((rolls[roll_nb][PINS]!=ROLL_MAXSCOPE)&&((rolls[roll_nb][PINS]+pins2)>ROLL_MAXSCOPE)) {
				throw new Exception("BowlingGame: void roll(int pins). In last frame/round sum of pins in two last rolls (second and third) must be from 0 to 10 except STRIKE in second roll \n"); 
			}
		}
		catch (Exception e){
		System.out.println(e);
		throw new IllegalArgumentException("validationExtraRollInLastFrame()");// 
	}
		
}

	
//
	public int calculateScore() {
		return gameScore[frame_nb];	
		}//calculateScore()
	
	public int calculateRollScore(int roll) {
		return rolls[roll][SCORE];	
		}//calculateScore()
	
}// class BowlingGame