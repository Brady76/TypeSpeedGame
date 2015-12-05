package words;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import client.Game;
import graphics.GameGui;

public class Word
{
	protected String id;
	protected Vector2f pos;
	protected int buffer, y, startX, endX;
	protected float speed = .025f;
	protected boolean visible = true;
	protected Random rand = new Random();
	protected Color color;
	
	public Word(String id)
	{
	    startX = -75;
		this.id = id;
		int buffer = 10;
		y = rand.nextInt(Game.HEIGHT - (GameGui.GUI_HEIGHT + buffer) * 2) + GameGui.GUI_HEIGHT + buffer;
	    pos = new Vector2f(startX, y);
	    endX = Game.WIDTH;
	    float min = 0.15f;
	    float red = rand.nextFloat() + min;
	    float blue = rand.nextFloat() + min;
	    float green = rand.nextFloat() + min;
	    if(red > 1.0f)red = 1.0f;
	    if(blue > 1.0f)blue = 1.0f;
	    if(green > 1.0f)green = 1.0f;
	    color = new Color(red, blue, green);
	}
	
	public boolean getVisible()				{	return visible;				}
	public String getID()					{	return id;					}
	public void setVisible(boolean visible)	{	this.visible = visible;		}
	
	public void update(GameContainer gc, int delta, float level) throws SlickException
	{
		if(visible)
		{
			pos.x += speed + (0.05*level);
			if(pos.x > endX)
				Level.wordExpire();
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		if(visible)
		{
			g.setColor(color);
			g.drawString(id, pos.x, pos.y);
		}
	}
}
