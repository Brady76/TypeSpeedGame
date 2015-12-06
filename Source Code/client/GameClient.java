package client;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import state.MenuState;
import state.GameState;
import state.ScoreState;

public class GameClient extends StateBasedGame 
{
	public static final int WIDTH = 1250, HEIGHT = 850;
	public static final int MENU = 0, GAME = 1, SCORE = 2;
	public static final String TITLE = "Type Speed Game";
	public GameClient() throws SlickException
	{
		super(TITLE);
		this.addState(new MenuState(MENU));
		this.addState(new GameState(GAME));
		this.addState(new ScoreState(SCORE));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		getState(MENU).init(gc, this);
		getState(GAME).init(gc, this);
		getState(SCORE).init(gc, this);
		enterState(MENU);
	}

	public static void main(String args[]) throws SlickException
	{
		 AppGameContainer app = new AppGameContainer(new GameClient());
		 app.setDisplayMode(WIDTH, HEIGHT, false);
		 app.start();
	}

}
