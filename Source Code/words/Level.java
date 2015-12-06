package words;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import state.GameState;
import graphics.GameGui;
import graphics.InputBox;

public class Level
{
	private static int wordsGone = 0;
	private GameGui gui;
	private Random rand = new Random();
	private String text = "";
	private Word[] words;
	private boolean levelOver = false;
	private int numWords, points;
	private float level = 0, averageTime, counter;
	private float[] times;
	
	public Level(String[] read, int numWords, float time, int level, int numLevels)
	{
		this.numWords = numWords;
		this.level = level;
	   	averageTime = time / (float)numWords;
    	times = new float[numWords];
    	words = new Word[numWords];
    	for(int i = 0;i < numWords; i++)
    	{
    		int randNum = rand.nextInt(WordReader.TOTALWORDS);
    		words[i] = new Word(read[randNum]);
    		// Assigns time of word coming out. Multiplying the total time/number of words for level
    		times[i] = averageTime * (float)i * 3;
    	}
    	gui = new GameGui();
	}
	
	public boolean getLevelOver(){return levelOver;	}
	public static String getWord(){return Integer.toString(wordsGone);}
	public static void wordExpire(){wordsGone += 1;}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		if(wordsGone >= numWords)
		{
			levelOver = true;
			wordsGone -= wordsGone;
		}
		
		points = GameState.getPoints();
		counter += delta;
		
		gui.update(gc, sbg, delta);
		text = ((InputBox)gui.getInputKeys(GameGui.INPUT_ID)).getWord();
		
		for(int i = 0;i < numWords;i++)
    	{
			if(counter > times[i])
				words[i].update(gc, delta, this.level);
			
			if(words[i].getID().equalsIgnoreCase(text) && words[i].getVisible())
			{
				words[i].setVisible(false);
				GameState.setPoints();
				points = GameState.getPoints();
				wordsGone += 1;
				((InputBox)gui.getInputKeys(GameGui.INPUT_ID)).eraseWord();
			}
    	}
		gui.setPoints(points);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for(int i = 0;i < numWords;i++)
    	{
			if(counter > times[i])
				words[i].render(gc, g);
    	}
		gui.render(gc, sbg, g);
	}
}
