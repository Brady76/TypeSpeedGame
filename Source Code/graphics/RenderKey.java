package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

// Another abstraction layer class RenderKey, for components that will check for render of InputKey
public abstract class RenderKey extends InputKeys
{
	// Private boolean to check for visibility
	private boolean isVisible = true;
	// Constructor, uses InputKeys constructor
	public RenderKey(String keyId){			super(keyId);			}
	// Getter
    public boolean getVisible(){			return this.isVisible;	}
    // Setter
	public void setVisible(boolean set) {	this.isVisible = set;	}
	// Another Abstract method, for children to define. This method will render the input box
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;
}
