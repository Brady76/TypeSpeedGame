package state;

//import client.Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import client.MenuButton;

public class ScoreState extends BasicGameState
{
	private MenuButton backButton;
	
	public ScoreState(int id)
	{
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		backButton = new MenuButton("Menu Return", 700);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		backButton.update(gc, sbg, delta);
		// Functionality
		if(backButton.getClicked()) 
		{
			backButton.setClicked(false);
			sbg.enterState(0);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		backButton.render(gc, sbg, g);
	}

	public int getID()
	{
		return 2;
	}

}
