package client;

import java.util.Comparator;

public class Score implements Comparator<Score>, Comparable<Score>
{
	private String wordsPerMin;
	private String scoreLine;
	private String[] splitScoreLine;
	private int score;
	
	public Score()
	{
		
	}
	
	public Score(int score, String wpm)
	{
		this.score = score;
		wordsPerMin = wpm;
		if(wordsPerMin.equalsIgnoreCase("Minute:")) wordsPerMin = "0";
		scoreLine = "Score: " + this.score + " Words per Minute: " + wordsPerMin;
	}
	
	public Score(String scoreBoard)
	{
		scoreLine = scoreBoard;
		splitScoreLine = scoreLine.split(" ");
		score = Integer.parseInt(splitScoreLine[1]);
		wordsPerMin = splitScoreLine[4];
	}
	
	public String getScore()
	{
		if(wordsPerMin.equalsIgnoreCase("Minute:")) wordsPerMin = "0";
		return "Score: " + score + " Words per Minute: " + wordsPerMin;
	}
	
	public String getScoreLine()
	{
		return scoreLine;
	}
	
	public int compareTo(Score someScore)
	{
		return(wordsPerMin).compareTo(someScore.wordsPerMin);
	}
	
	public int compare(Score scoreOne, Score scoreTwo)
	{
		return scoreTwo.score - scoreOne.score;
	}
	
}