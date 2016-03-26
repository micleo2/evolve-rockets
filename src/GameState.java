import java.util.*;

public class GameState
{	
	private ArrayList<Screen> screens = new ArrayList<Screen>();
	private int indexOfCurrentScreen = 0;
	
	public GameState(int width, int height) {
		
		//create all the screens
		//Ex:
		//screens.add(new WelcomeScreen(this, width, height));
		//screens.add(new GameScreen(this, width, height));
		//screens.add(new GameOverScreen(this, width, height));
		
		screens.add(new EvolveScreen(this, width, height));
	}
	
	public Screen currentActiveScreen() {
		return screens.get(indexOfCurrentScreen);
	}
	
	public void switchToEvolveScreen(){
		indexOfCurrentScreen = 0;
		screens.get(indexOfCurrentScreen).start();
	}
	
}