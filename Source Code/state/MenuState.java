package state;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import client.GameClient;
import client.MenuButton;

public class MenuState extends BasicGameState
{
	
	private MenuButton playButton, quitButton, scoreButton;
	private Font creditFont;
	private TrueTypeFont creditDisplay;
	private String credits;
	private Image logo;
	
	public MenuState(int id)
	{
		
	}
	
	public int getID(){return 0;}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		credits = "A Java game by Brady Auen, Nick Nocella, and Peter Huynh";
		creditFont = new Font("Calibri", Font.BOLD, 32);
		creditDisplay = new TrueTypeFont(creditFont, true);
		playButton = new MenuButton("  Play Game", 375);
		scoreButton = new MenuButton("Leaderboard", 500);
		quitButton = new MenuButton("  Quit Game", 625);
		logo = new Image("res/logo.png");
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
			System.exit(0);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		logo.drawCentered(GameClient.WIDTH /2, 175);
		playButton.render(gc, sbg, g);
		scoreButton.render(gc, sbg, g);
		quitButton.render(gc, sbg, g);
		creditDisplay.drawString((float) (GameClient.WIDTH /2.0 - 390), (float) (GameClient.HEIGHT - 50.0f), credits, Color.green);
	}
}
