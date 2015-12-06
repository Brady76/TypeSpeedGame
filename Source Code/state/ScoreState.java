package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import client.GameClient;
import client.MenuButton;
import client.Score;

public class ScoreState extends BasicGameState
{
	private static FileWriter writeScore;
	private static List<Score> scoreBoard = new ArrayList<Score>();
	private static double wordsPerMinute;
	private static long timeElapsed;
	private MenuButton backButton;
	private BufferedReader readScore;
	private Font scoreFont;
	private TrueTypeFont score;
	
	public ScoreState(int id)
	{
		try 
		{
			readScore = new BufferedReader(new FileReader("res/topScores.txt"));
			for(int i = 0;i < 10; i++)
				scoreBoard.add(new Score(readScore.readLine()));
			readScore.close();
		}
		catch(IOException e){}
	}
	
	public int getID(){return 2;}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		scoreFont = new Font("Calibri", Font.BOLD, 28);
		score = new TrueTypeFont(scoreFont, true);
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
			try // Delay
			{
				// 100 milliseconds is 1/10 second.
			    Thread.sleep(150);
			} catch(InterruptedException ex){Thread.currentThread().interrupt();}
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		int i = 0;
		if(!scoreBoard.isEmpty())
		{
			for(Score s: scoreBoard)
			{	
				i++;
				score.drawString((float) (GameClient.WIDTH/2 - 250), 60*i+20, i + ") ", Color.orange);
				score.drawString((float) (GameClient.WIDTH/2 - 150), 60*i+20, s.getScoreLine(), Color.orange);
			}
		}
		
		backButton.render(gc, sbg, g);
	}
	
	public static void appendScore(int score, long timeStart, long timeEnd) throws IOException
	{
		timeElapsed = timeEnd - timeStart;
		wordsPerMinute = ((double) score / ((timeElapsed/1000.0) / 60.0)) / 10;
		
		scoreBoard.add(new Score(score, Double.toString(wordsPerMinute)));
		Collections.sort(scoreBoard, new Score());
		scoreBoard.subList(10, scoreBoard.size()).clear();

		writeScore = new FileWriter("res/topScores.txt");
		for(Score s: scoreBoard)
			writeScore.write(s.getScore() + '\n');
		writeScore.close();
	}
}
