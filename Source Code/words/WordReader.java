package words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class WordReader
{
	private BufferedReader in;
	public static final int TOTALWORDS = 80368;
	private static String[] read = new String[TOTALWORDS];
    public String[] getWords()
    {
    	try 
        {
            in = new BufferedReader(new FileReader("res/dictionary.txt"));

            for(int i = 0;i < TOTALWORDS; i++)
            {
            	read[i] = in.readLine();
            }
            
            in.close();
        }
        catch(IOException e){}
    	return read;
    }
}
