package state;


import java.awt.MenuBar;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState
{
	
	//private MenuButton playButton, quitButton;
	public MenuState(int id)
	{
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		//playButton = new MenuBar();
		//quitButton = new MenuBar();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		//playButton.update(gc, sbg, delta);
		//quitButton.update(gc, sbg, delta);
		/*
		if(playButton.isClicked()) 
		{
			playButton.setClicked(false);
			sbg.enterState(2);
		}
		if(quitButton.isClicked()) System.exit(0);
		*/
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		//playButton.render(gc, sbg, g);
		//quitButton.render(gc, sbg, g);
	}
	public int getID() {return 1;}

}
