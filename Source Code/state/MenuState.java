package state;


import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import client.Game;
import client.MenuButton;

public class MenuState extends BasicGameState
{
	
	private MenuButton playButton, quitButton, scoreButton;
	private String credits;
	private Font titleFont, creditFont;
	private TrueTypeFont title, creditDisplay;
	
	public MenuState(int id)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		credits = 			"By Brady Auen, Nick Nocella, and Peter Huynh";
		titleFont = 		new Font("Calibri", Font.BOLD, 48);
		title = 			new TrueTypeFont(titleFont, true);
		creditFont = 		new Font("Consolas", Font.BOLD, 32);
		creditDisplay = 	new TrueTypeFont(creditFont, true);
		playButton = 		new MenuButton("  Play Game", 250);
		scoreButton = 		new MenuButton("Leaderboard", 400);
		quitButton = 		new MenuButton("  Quit Game", 550);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		playButton.update(gc, sbg, delta);
		scoreButton.update(gc, sbg, delta);
		quitButton.update(gc, sbg, delta);
		
		// Functionality
		if(playButton.getClicked()) 
		{
			playButton.setClicked(false);
			sbg.enterState(1);
		}
		if(scoreButton.getClicked()) 
		{
			scoreButton.setClicked(false);
			sbg.enterState(2);
		}
		if(quitButton.getClicked())
		{
			System.exit(0);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		title.drawString((float) (Game.WIDTH /2.0 - 185), 100.0f, Game.TITLE, Color.orange);
		playButton.render(gc, sbg, g);
		scoreButton.render(gc, sbg, g);
		quitButton.render(gc, sbg, g);
		creditDisplay.drawString((float) (Game.WIDTH /2.0 - 400), (float) (Game.HEIGHT - 100.0f), credits, Color.green);
	}
	
	public int getID()
	{
		return 0;
	}

}
