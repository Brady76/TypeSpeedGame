package words;

import graphics.GameGui;
import graphics.InputBox;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import state.PlayState;

public class Level
{
	private GameGui gui;
	private Random rnd = new Random();
	private int numWords;
	private float time, averageTime, counter;
	private float[] times;
	private Word[] words;
	private boolean levelOver = false;
	private boolean gameOver = false;
	private String text = "";
	private int points;
	private static int wordsGone = 0;
	private float level = 0;
	private int finalLevel;
	public Level(String[] read, int numWords, float time, int level, int numLevels)
	{
		this.time = time;
		this.numWords = numWords;
		this.level = level;
	   	averageTime = time / (float)numWords;
    	times = new float[numWords];
    	words = new Word[numWords];
    	this.finalLevel = numLevels;
    	for(int i = 0;i < numWords; i++)
    	{
    		int randNum = rnd.nextInt(WordReader.TOTALWORDS);
    		words[i] = new Word(read[randNum]);
    		//assign the time that the word should come out by multiplying the total time/number of words for the level by the index
    		times[i] = averageTime * (float)i * 3;
    	}
    	gui = new GameGui();
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		points = PlayState.pointGetter();
		counter += delta;
		if(wordsGone == numWords){
			levelOver = true;
			wordsGone -= wordsGone;
		}
		if(wordsGone == numWords && level==finalLevel){
			gameOver = true;
		}
		gui.update(gc, sbg, delta);
		text = ((InputBox)gui.getInputKeys(GameGui.INPUT_ID)).getWord();
		for(int i = 0;i < numWords;i++)
    	{
			if(counter > times[i])
			{
				words[i].update(gc, delta, this.level);
			}
			if(words[i].getID().equalsIgnoreCase(text) && words[i].isVisible())
			{
				words[i].setVisible(false);
				PlayState.pointSetter();
				points = PlayState.pointGetter();
				wordsGone += 1;
				((InputBox)gui.getInputKeys(GameGui.INPUT_ID)).eraseWord();
			}
    	}
		gui.setPoints(points);
	}
	public static void wordExpire(){
		wordsGone += 1;
	}
	public static String wordGetter(){
		return Integer.toString(wordsGone);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for(int i = 0;i < numWords;i++)
    	{
			if(counter > times[i])
			{
				words[i].render(gc, g);
			}
    	}
		gui.render(gc, sbg, g);
	}
	public boolean isLevelOver(){return levelOver;}
	public boolean isGameOver(){return gameOver;}
}
