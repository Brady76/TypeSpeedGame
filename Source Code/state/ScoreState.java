package state;

//import client.Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import graphics.GameGui;

import java.util.ArrayList;
import java.util.List;

import client.Game;
import client.MenuButton;

public class ScoreState extends BasicGameState
{
	private MenuButton backButton;
	private static List<String> scoreBoard = new ArrayList<String>();
	
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
	public static void scoreAppend(int score){
		String scoreString = Integer.toString(score);
		scoreBoard.add(scoreString);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		for(int i = 0; i <= scoreBoard.size(); i++){
			g.drawString(scoreBoard.get(i), Game.WIDTH - 50, GameGui.GUI_HEIGHT /2);
		}
		backButton.render(gc, sbg, g);
	}

	public int getID()
	{
		return 2;
	}

}
