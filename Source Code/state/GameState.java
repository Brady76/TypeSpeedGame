package state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import words.Level;
import words.WordReader;

public class GameState extends BasicGameState
{
	private static boolean startBool = true;
	private static int score = 0;
	private static long timerStart, timerEnd;
	private WordReader wordReader = new WordReader();
	private String[] read = wordReader.getWords();
	private List<Level> levels = new ArrayList<Level>();
	private int totalLevels = 1, startWords = 10, levelNum = 0;
	private float startTime = 5000;
	
	public GameState(int id)
	{
		for(int i = 0; i < totalLevels; i++)
		{
			levels.add(new Level(read, startWords + (int)(i^2), startTime + (int)(i^2), i, totalLevels));
		}
	}
	
	public int getID(){return 1;}
	public static int getPoints(){return score;}
	public static void setPoints(){score += 10;}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		if(levels.get(levelNum) == levels.get(0) && startBool == true)
		{
			startBool = false;
			timerStart = System.currentTimeMillis();
		}
		if(levels.get(levelNum) == levels.get(levels.size() - 1) && levels.get(levelNum).getLevelOver())
		{
			sbg.enterState(0);
			try
			{
				timerEnd = System.currentTimeMillis();
				startBool = true;
				ScoreState.appendScore(score, timerStart, timerEnd);
			} catch (IOException e){e.printStackTrace();}
			
			levels.clear();
			for(int i = 0; i < totalLevels; i++)
			{
				levels.add(new Level(read, startWords + (int)(i^2), startTime + (int)(i^2), i, totalLevels));
			}
			levelNum -= levelNum;
			score -= score;
		}
		
		if(levels.get(levelNum).getLevelOver() && levelNum < totalLevels) 
			levelNum++;
		
		levels.get(levelNum).update(gc, sbg, delta);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		levels.get(levelNum).render(gc, sbg, g);
	}
}
