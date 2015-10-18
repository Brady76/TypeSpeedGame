package GUI;

import client.Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Gui
{
	// Public variables, both constants and static so other classes can use them
	// Class Level used this variable
	public static final String INPUT_ID = "InputBox";	
	// Classes Word and InputBox also use this variable
	public static final int GUI_HEIGHT = 50;	
	// Private variables
	private ArrayList<InputKeys> inputs = new ArrayList<InputKeys>();
	private int score = 0;
	private InputKeys returner;
	// Constructor
	public Gui(){this.inputs.add(new InputBox(INPUT_ID));}
	// Getter
	public InputKeys getInputKeys(String id)
	{
		this.returner = null;
		// Loops through inputs array
		for(InputKeys key : this.inputs)
		{
			// Checks if key id matches
			if(key.keyId == id) this.returner = key;
		}
		return this.returner;
	}
	// Setter
	public void setPoints(int number){this.score = number;}
	// Methods update and render, like InputBox, except these aren't abstract declarations
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		// Simply calls the update of InputKeys, which is defined in InputBox
		// Loops through inputs array
		for(InputKeys key : this.inputs)
		{
			// Input box updating for every key entering input array.
			key.update(gc, sbg, delta);
		}
	}
	// Render method, renders GUI
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		// Setting colors for gui drawings
		g.setColor(Color.orange);
		// Instructions
		g.drawString("Enter = Submit Word, ESC = Quit Game", Game.WIDTH - 350, Game.HEIGHT - 40);
		
		g.setColor(Color.gray);
		// Top Line Box
		g.drawRect(0, 0, Game.WIDTH, Gui.GUI_HEIGHT);
		// Bottom Line Box
		g.drawRect(0, Game.HEIGHT - Gui.GUI_HEIGHT, Game.WIDTH, Gui.GUI_HEIGHT);
		
		g.setColor(Color.green);
		// Score
		g.drawString(this.score+"", Game.WIDTH - 50, Gui.GUI_HEIGHT / 2);
		
		// Calls RenderKey's render method defined in InputBox
		for(InputKeys key : this.inputs)
		{
			if(key instanceof RenderKey)((RenderKey)key).render(gc, sbg, g);
		}
	}
}
