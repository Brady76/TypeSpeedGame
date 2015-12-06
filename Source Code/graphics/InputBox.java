package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import client.GameClient;

// Child of RenderKey, which is a child of InputKey
public class InputBox extends RenderKey
{	
	private Input input;
	private String userWord = "";
	private boolean isSubmitted = false;
	private int time = 0;
	private int delayTyping = 150;
	
	public InputBox(String id){super(id);}
	public String getWord(){return this.userWord;}
	public boolean getEnter(){return this.isSubmitted;}
	public void setEnter(boolean set){this.isSubmitted = set;}
	// Setters for userWord
	public void eraseWord(){this.userWord = "";}
	public void spaceWord()
	{
		this.userWord += " ";
		try 
		{
			// 100 milliseconds is 1/10 second.
		    Thread.sleep(100);
		} catch(InterruptedException ex){Thread.currentThread().interrupt();}
	}
	
	// Setter method typeKey, used for inputing every key into a word
	private void typeKey(Input input)
	{
		// Opens a loop, like a window in which the input is taken
		for(int timeWindow = 0; timeWindow < 100; timeWindow++)
		{
			if(input.isKeyDown(timeWindow))
			{
				// Sets key to automatically lower case and grabs key 
				this.userWord += Input.getKeyName(timeWindow).toLowerCase();
				// Prevents double pressing
				this.time += this.delayTyping;
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	{
		// Sets input to slick input grabber
		this.input = gc.getInput();
		// Checks what key is pressed
		if(this.time <= 0)
		{

			if(this.input.isKeyDown(Input.KEY_BACK))
				this.eraseWord();
			else if(this.input.isKeyDown(Input.KEY_SPACE))
				this.spaceWord();
			else if(this.input.isKeyDown(Input.KEY_RETURN))
				this.isSubmitted = true;
			else if(input.isKeyDown(Input.KEY_ESCAPE))
				sbg.enterState(0);
			else
				this.typeKey(this.input);
		}
		else this.time -= delta;		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.drawString(userWord, GameClient.WIDTH - GameClient.WIDTH + 30, GameGui.GUI_HEIGHT / 2);
	}
}
