package state;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import client.Game;
import client.MenuButton;

public class MenuState extends BasicGameState
{
	
	private MenuButton playButton, quitButton, difficultyButton, scoreButton;
	private String gameTitle;
	public MenuState(int id)
	{
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		gameTitle = "Type Speed Game";
		playButton = new MenuButton("Play Game", 150);
		difficultyButton = new MenuButton("Difficulty", 300);
		scoreButton = new MenuButton("Leaderboard", 450);
		quitButton = new MenuButton("Quit", 600);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		playButton.update(gc, sbg, delta);
		difficultyButton.update(gc, sbg, delta);
		scoreButton.update(gc, sbg, delta);
		quitButton.update(gc, sbg, delta);
		
		// Functionality
		if(playButton.isClicked()) 
		{
			playButton.setClicked(false);
			sbg.enterState(2);
		}
		if(quitButton.isClicked())
		{
			System.exit(0);
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(gameTitle, Game.WIDTH / 2 - 100, 50);
		playButton.render(gc, sbg, g);
		difficultyButton.render(gc, sbg, g);
		scoreButton.render(gc, sbg, g);
		quitButton.render(gc, sbg, g);
	}
	public int getID()
	{
		return 1;
	}

}
