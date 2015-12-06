package graphics;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import client.GameClient;

public class GameGui
{
	public static final String INPUT_ID = "InputBox";	
	public static final int GUI_HEIGHT = 50;	
	private ArrayList<InputKeys> inputs = new ArrayList<InputKeys>();
	private InputKeys returner;
	private int score = 0;
	
	public GameGui()
	{
		this.inputs.add(new InputBox(INPUT_ID));
	}
	public InputKeys getInputKeys(String id)
	{
		this.returner = null;
		for(InputKeys key : this.inputs)
		{
			if(key.keyId == id) this.returner = key;
		}
		return this.returner;
	}
	
	public void setPoints(int number){this.score = number;}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		for(InputKeys key : this.inputs)
			key.update(gc, sbg, delta);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		// Instructions
		g.setColor(Color.orange);
		g.drawString("ESC = Pause Game", GameClient.WIDTH - 350, GameClient.HEIGHT - 40);
		
		// Top & Bottom Line Box
		g.setColor(Color.gray);
		g.drawRect(0, 0, GameClient.WIDTH, GameGui.GUI_HEIGHT);
		g.drawRect(0, GameClient.HEIGHT - GameGui.GUI_HEIGHT, GameClient.WIDTH, GameGui.GUI_HEIGHT);
		
		// Score
		g.setColor(Color.green);
		g.drawString(this.score+"", GameClient.WIDTH - 50, GameGui.GUI_HEIGHT / 2);
		
		// Calls RenderKey's render method defined in InputBox
		for(InputKeys key : this.inputs)
			if(key instanceof RenderKey)((RenderKey)key).render(gc, sbg, g);
	}
}
