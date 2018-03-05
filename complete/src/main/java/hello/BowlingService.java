package hello;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import hello.BowlingGame;


/*
import io.swagger.annotations.ApiModelProperty;
 
import javax.persistence.*;
import java.math.BigDecimal;
 
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID")
    private Integer id;
    @Version
    @ApiModelProperty(notes = "The auto-generated version of the product")
    private Integer version;
    @ApiModelProperty(notes = "The application-specific product ID")
    private String productId;
    @ApiModelProperty(notes = "The product description")
    private String description;
    @ApiModelProperty(notes = "The image URL of the product")
    private String imageUrl;
    @ApiModelProperty(notes = "The price of the product", required = true)
    private BigDecimal price;
*/



//@Service("bowlingService") 
//@Component("bowlingService") 
public class BowlingService {
	static int ROLL_MAXSCOPE = 10;
	static int MAX_ROLL_IN_GAME = 22; // Max roll in game is 21 but table is started from 1 not from 0;
    private int playerId;  // id Game or long
    //private String content;
	private String name;
	//private String pins;
	private int idRoll;
	private int score;
	private BowlingGame bowlingGame;
// added 03.03
	//private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotatedcontext.xml");
	//context = new ClassPathXmlApplicationContext("annotatedcontext.xml");	
	//private Bowling bowlingUnderTest = context.getBean(AnnotatedBowlingGame2.class); //correct
	@Autowired	
	private BowlingGame bowlingGame2;//= context.getBean("AnnotatedBowlingGame"); //correct
	//private Bowling bowlingGame= (Bowling) context.getBean("AnnotatedBowlingGame"); //correct
	//@Autowired	
	//private AnnotatedBowlingGame bowlingGame;
	private int roll[];

	public BowlingService() {
        playerId =0;
		score=0;
		name = "Error: BowlingG basic constructor";
		score= 0;
		roll = new int[MAX_ROLL_IN_GAME];
		idRoll = 0; 
		//@Autowired		
		bowlingGame = new BowlingGame();
//		@Autowired		
//		bowlingGame2.calculateScore();
		
    }
    
	public BowlingService(int playerId, String name) {
		//this.super();
  
		this.playerId = playerId;
		this.name = name;
		score = 0;
		roll = new int[MAX_ROLL_IN_GAME];
		idRoll = 0;
		bowlingGame = new BowlingGame();
//		@Autowired
//		bowlingGame2.calculateScore();
    }

   
	public long getPlayerId() {
        return playerId;
    }

//	public String getPins() {
// 		return pins;     
//	}   
    public String getName() {
        return name;
    }
	public int getIdRoll(){
    
	return idRoll;
	}	
		
	public int getScore(){
    
	return score;
	}	
	public BowlingGame getBowlingGame(){
    
	return bowlingGame;
	}	
	public BowlingGame getBowlingGame2(){
    
	return bowlingGame2;
	}
	public void setId(int playerId) {
 		this.playerId=playerId;       
	}

	public void addPins(BowlingFormForRoll pins) {

		roll[idRoll+1]+=pins.getPins();
		bowlingGame.roll(pins.getPins());
		score=bowlingGame.calculateScore();
		//score+=pins.getPins(); // tylko na czas testów 
		idRoll++;        
	}
	public void addPinsint(int pins) {

		roll[idRoll+1]+=pins;
		bowlingGame.roll(pins);
		score=bowlingGame.calculateScore();
		idRoll++; 
		//score+=pins; // tylko na czas testów 
   
	}

	public void setName(String name) {
		// correlation with BowlingClass. To use the BowlingGame class 	
		this.name=name;       
	}
 
//	public void setPins(String pins) {
// 		this.pins=pins;       
//	}

	private boolean pinsVerification(int pins){ 
		// correlation with BowlingClass. To use the BowlingGame class
		if ((pins<0)||(pins>ROLL_MAXSCOPE)){ // Veryfication when pins in frame/round is out of <0,...,10>
			return false;
		}
		else{
			return true;		
		}	

    }
}
