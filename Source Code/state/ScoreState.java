package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import client.Game;
import client.MenuButton;

public class ScoreState extends BasicGameState
{
	private MenuButton backButton;
	private static List<Score> scoreBoard = new ArrayList<Score>();
	private BufferedReader scoreFile;
	
	public ScoreState(int id)
	{
		try 
		{
			scoreFile = new BufferedReader(new FileReader("res/topScores.txt"));
			for(int i = 0;i < 10; i++)
				scoreBoard.add(new Score(scoreFile.readLine()));
			scoreFile.close();
		}
		catch(IOException e){}
		//scoreBoard.addAll(Arrays.asList("100", "90", "80", "70", "60", "50", "40", "30", "20", "10"));
	}
	
	public int getID()	{	return 2;	}
	
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
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		int i = 0;
		if(!scoreBoard.isEmpty())
		{
			for(Score s: scoreBoard)
			{	
				i++;
				g.drawString(s.getAltString(), Game.WIDTH/2, 60*i+50);
			}
		}
		
		backButton.render(gc, sbg, g);
	}
	static class Score implements Comparator<Score>, Comparable<Score>
	{
		private int score;
		private String WPM;
		private String altString;
		
		Score(){
			
		}
		
		Score(int a, String b){
			score = a;
			WPM = b;
			altString = "Score: " + score + " Words per Minute: " + WPM;
		}
		
		Score(String a){
			altString = a;
			String split[] = altString.split(" ");
			score = Integer.parseInt(split[1]);
			WPM = split[5];
		}
		
		public String getScore()
		{
			return "Score: " + score + " Words per Minute: " + WPM;
		}
		
		public String getAltString()
		{
			return altString;
		}
		
		public int compareTo(Score s)
		{
			return(this.WPM).compareTo(s.WPM);
		}
		
		public int compare(Score s, Score s1)
		{
			return s.score - s1.score;
		}
		
	}
	public static void appendScore(int score, long timeStart, long timeEnd) throws IOException
	{
		//String scoreString = Integer.toString(score);
		long timeElapsed = timeEnd - timeStart;
		double secondsPassed = timeElapsed/1000.0;
		double minutesPassed = secondsPassed/60.0;
		double doubleScore = (double) score;
		double wordsPerMinute = doubleScore/minutesPassed/10;
		String wordsperMinuteString = Double.toString(wordsPerMinute);
		//String finalScoreString = "Score: " + scoreString + "  Words per Minute: " + wordsperMinuteString;
		scoreBoard.add(new Score(score,wordsperMinuteString));
		Collections.sort(scoreBoard);
		scoreBoard.subList(10, scoreBoard.size()).clear();
		FileWriter writer = new FileWriter("res/topScores.txt"); 
		for(Score s: scoreBoard) {
		  writer.write(s.getScore());
		  writer.write('\n');
		}
		writer.close();
	}
}
