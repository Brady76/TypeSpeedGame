package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import client.Game;

// Child of RenderKey, which is a child of InputKey
public class InputBox extends RenderKey
{	
	// Private variables
	private String userWord = "";
	private int time = 0;
	private int delayTyping = 150;
	private boolean isSubmitted = false;
	private Input input;
	// Constructor
	public InputBox(String id){super(id);}
	// Getters
	public String getWord(){			return this.userWord;		}
	public boolean getEnter(){			return this.isSubmitted;	}
	// Setters
	public void setEnter(boolean set){	this.isSubmitted = set;		}
	// Setters for userWord
	public void eraseWord(){			this.userWord = "";			}
	public void spaceWord(){			this.userWord += "-";		}
	// Setter method typeKey, used for inputing every key into a word
	private void typeKey(Input input)
	{
		// Opens a loop, like a window in which the input is taken
		for(int timeWindow = 0; timeWindow < 100; timeWindow++)
		{
			// Checks if the key is pressed
			if(input.isKeyDown(timeWindow))
			{
				// Sets key to automatically lower case and grabs key 
				this.userWord += Input.getKeyName(timeWindow).toLowerCase();
				// Increments a delay to the time in which inputs can be pressed
				// Prevents double pressing
				this.time += this.delayTyping;
			}
		}
	}
	// Defining abstract methods update from InputKeys and render from RenderKey
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	{
		// sets input to slick input grabber
		this.input = gc.getInput();
		// checks what key is pressed
		if(this.time <= 0)
		{
			// Backspace deletes the word, calls method to do so
			if(this.input.isKeyDown(Input.KEY_BACK)) 		this.eraseWord();
			// Space calls method to add a whitespace
			else if(this.input.isKeyDown(Input.KEY_SPACE)) 	this.spaceWord();
			// Return submits the input box word as a word
			else if(this.input.isKeyDown(Input.KEY_RETURN)) this.isSubmitted = true;
			// TO-DO: Escape must exit play state and go to the menu
			else if(input.isKeyDown(Input.KEY_ESCAPE))		sbg.enterState(1);
			// Other inputs are translated into keys for the word
			else this.typeKey(this.input);
		}
		else this.time -= delta;		
	}
	// Defining abstract method render, this is the input box rendering each key together
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		// Words are set to white
		g.setColor(Color.white);
		// Draws the word into the window within a specified space
		g.drawString(userWord, Game.WIDTH - Game.WIDTH + 30, GameGui.GUI_HEIGHT / 2);
	}
}
