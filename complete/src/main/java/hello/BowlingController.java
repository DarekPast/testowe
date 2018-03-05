package hello;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// added 2018.03.01
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

//import guru.springframework.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bowling")
@Api(value="onlinebowlinggame", description="Operations Bowling Games")
public class BowlingController {
	static int MAX_GAME_BASE=10;
	private int gameCounter=0;
	//private BowlingService gameControl = new BowlingService();
	private BowlingService gameBase[] = new BowlingService[MAX_GAME_BASE];
  
 	@ApiOperation(value = "Add new Game")
	@RequestMapping(value="/games/add", method=RequestMethod.POST)//, produces = "application/json")
	public ResponseEntity newPlayer(@RequestParam(value="name", defaultValue= "NoName") String name) {
		gameCounter++;
		gameBase[gameCounter]=new BowlingService(gameCounter,name);
		HttpHeaders head = new HttpHeaders();
		head.set("HttpHeaderResponse","New player game added");
		return new ResponseEntity<BowlingService>(gameBase[gameCounter],head, HttpStatus.OK);	

}


	@ApiOperation(value = "Info about bowling game with an ID",response = BowlingService.class)
	@RequestMapping(value="/games/{id}", method=RequestMethod.GET, produces = "application/json")
// 	public ResponseEntity getPlayerIdInParam(@RequestParam(value="id") int id) {
	public ResponseEntity getPlayer(@PathVariable("id") int id) {
		HttpHeaders head = new HttpHeaders();
		head.set("HttpHeaderResponse","Player Game info - ID in patch request");
//		head.set("HttpHeaderResponse","Player Game info - parameter request");
		return new ResponseEntity<BowlingService>(gameBase[id],head, HttpStatus.OK);		
	}

 	@ApiOperation(value = "Add the pins to the game")
	@RequestMapping(value="/games/{id}", method=RequestMethod.PUT, produces = "application/json")
    public ResponseEntity addPins1(	@PathVariable("id") int id, 
//								@RequestBody @Valid BowlingFormGETForRoll pins, BindingResult result) { 
								@RequestBody BowlingFormForRoll dane, BindingResult result) { 
								
		if (result.hasErrors()) { 

			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
//		if (gameBase[id].getPlayerId()==dane.getPlayerId()) { 
//			return new ResponseEntity(HttpStatus.BAD_REQUEST); // 30x - Id conflict
//		}
//		int pin=dane.getPins();		
		gameBase[id].addPins(dane); 

		HttpHeaders head = new HttpHeaders();
		head.set("HttpHeaderResponse","Roll Pins added. Game="+id+"pins="+dane.getPins());
		return new ResponseEntity<BowlingService>(gameBase[id],head, HttpStatus.OK);	

	}	

  
    @ApiOperation(value = "Delete a Game")
    @RequestMapping(value="/games/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable int id){
		gameBase[id]=new BowlingService(id,"deleted");

		HttpHeaders head = new HttpHeaders();
		head.set("HttpHeaderResponse","Game nr."+id+" deleted");
		return new ResponseEntity(head, HttpStatus.OK);
 
    }

}
