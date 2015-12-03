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
	
	private MenuButton playButton, quitButton, difficultyButton, scoreButton;
	private String difficulty;
	private Font titleFont, diffFont;
	private TrueTypeFont title, diffDisplay;
	
	public MenuState(int id)
	{
		
	}
	
	public String getDifficulty()
	{
		return difficulty;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		titleFont = 		new Font("Calibri", Font.BOLD, 48);
		title = 			new TrueTypeFont(titleFont, true);
		diffFont = 			new Font("Consolas", Font.BOLD, 32);
		diffDisplay = 		new TrueTypeFont(diffFont, true);
		difficulty = 		"Normal";
		playButton = 		new MenuButton("  Play Game", 150, 1.0f);
		difficultyButton = 	new MenuButton("   Difficulty", 300, 1.0f);
		scoreButton = 		new MenuButton("Leaderboard", 550, 1.0f);
		quitButton = 		new MenuButton("  Quit Game", 700, 1.0f);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		playButton.update(gc, sbg, delta);
		difficultyButton.update(gc, sbg, delta);
		scoreButton.update(gc, sbg, delta);
		quitButton.update(gc, sbg, delta);
		
		// Functionality
		if(playButton.getClicked()) 
		{
			playButton.setClicked(false);
			sbg.enterState(1);
		}
		if(difficultyButton.getClicked()) 
		{
			if(difficulty == "Normal") difficulty = "Hard";
			else if (difficulty == "Hard") difficulty = "Easy";
			else difficulty = "Normal";
			difficultyButton.setClicked(false);
			try 
			{
				// 1000 milliseconds is one second.
			    Thread.sleep(150);
			} catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
		if(quitButton.getClicked())
		{
			System.exit(0);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		title.drawString((float) (Game.WIDTH /2.0 - 185), 50.0f, Game.TITLE, Color.orange);
		playButton.render(gc, sbg, g);
		difficultyButton.render(gc, sbg, g);
		diffDisplay.drawString((float) (Game.WIDTH/2 - 60), (float) (Game.HEIGHT/2 + 35), difficulty, Color.green);
		scoreButton.render(gc, sbg, g);
		quitButton.render(gc, sbg, g);
	}
	
	public int getID()
	{
		return 0;
	}

}
