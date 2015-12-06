package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

// Parent Abstraction Layer, for the Input Box, base components for key id
public abstract class InputKeys
{
    protected String keyId;
    protected boolean isEnabled = true;
    public InputKeys(String inputId){this.keyId = inputId;}
    
    public String getId()
    {
    	return this.keyId;
    }
    public boolean getEnabled()
    {
    	return this.isEnabled;
    }
	public void setEnabled(boolean set)
	{
		this.isEnabled = set;
	}
	
	// Abstract method update, for updating the input box, defined in InputBox
	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);
}
