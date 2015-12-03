package state;

import graphics.GameGui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import words.Level;
import words.WordReader;

public class PlayState extends BasicGameState
{
	private WordReader wordReader = new WordReader();
	private String[] read = wordReader.getWords();
	private int numLevels = 1, startWords = 10, levelNum = 0;
	private Level[] levels = new Level[numLevels];
	private float startTime = 5000;
	private static int score = 0;
	public PlayState(int id)
	{
		for(int i = 0; i < numLevels;i++)
		{
			levels[i] = new Level(read, startWords + (int)(i * i), startTime + (int)(i * i), i, numLevels);
		}
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		levels[levelNum].update(gc, sbg, delta);
		if(levels[levelNum].isLevelOver())levelNum++;
		if(levels[levelNum].isGameOver()){
			ScoreState.scoreAppend(score);
			sbg.enterState(0);
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		levels[levelNum].render(gc, sbg, g);
	}
	public int getID()
	{
		return 1;
	}
	public static void pointSetter(){
		score += 10;
	}
	public static int pointGetter(){
		return score;
	}
}
