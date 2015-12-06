package words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordReader
{
	public static final int TOTALWORDS = 109583;
	private static String[] readWord = new String[TOTALWORDS];
	private BufferedReader dictFile;
	
	public String[] getWords()
	{
		try 
		{
			dictFile = new BufferedReader(new FileReader("res/wordsEn.txt"));
			for(int i = 0;i < TOTALWORDS; i++)
				readWord[i] = dictFile.readLine();
			dictFile.close();
		}
		catch(IOException e){ }
		return readWord;
	}
}