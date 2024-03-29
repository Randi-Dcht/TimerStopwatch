package gui;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import states.timer.*;

public class TestTimerLabels extends TestGUIAbstract {

	@Test
	public void testIdleTimerLabels() {
		c.setCurrentState(IdleTimer.Instance());
		g.updateUI(c);
		assertEquals(g.b1.getText(),c.getLeftText(), "button 1 for state IdleTimer ");
		assertEquals(g.b2.getText(),c.getUpText(), "button 2 for state IdleTimer ");
		assertEquals(g.b3.getText(),c.getRightText(), "button 3 for state IdleTimer ");
	}
	
	@Test
	public void testPausedTimerLabels() {
		c.setCurrentState(PausedTimer.Instance());
		g.updateUI(c);
		assertEquals(g.b1.getText(),c.getLeftText());
		assertEquals( g.b2.getText(),c.getUpText());
		assertEquals( g.b3.getText(),c.getRightText());
	}
	
	@Test
	public void testRingingTimerLabels() {
		c.setCurrentState(RingingTimer.Instance());
		g.updateUI(c);
		assertEquals( g.b1.getText(),c.getLeftText());
		assertEquals( g.b2.getText(),c.getUpText());
		assertEquals( g.b3.getText(),c.getRightText());
	}
	
	@Test
	public void testRunningTimerLabels() {
		c.setCurrentState(RunningTimer.Instance());
		g.updateUI(c);
		assertEquals( g.b1.getText(),c.getLeftText());
		assertEquals( g.b2.getText(),c.getUpText());
		assertEquals( g.b3.getText(),c.getRightText());
	}
	
	@Test
	public void testSetTimerLabels() {
		c.setCurrentState(SetTimer.Instance());
		g.updateUI(c);
		assertEquals( g.b1.getText(),c.getLeftText());
		assertEquals(g.b2.getText(),c.getUpText());
		assertEquals( g.b3.getText(),c.getRightText());
	}
	
	   @Test
	    public void testTimerButtonLabels1() {
	    	g.updateUI(c);
	    	assertEquals("change mode",g.b1.getText());
	    	assertEquals("run",g.b2.getText());
	    	assertEquals("set",g.b3.getText());    	
	    	assertEquals("IdleTimer",g.myText3.getText());
	    	assertEquals("timer",g.myText2.getText());
	    	assertEquals("memTimer = 0",g.myText1.getText());
	    }

	    @Test
	    public void testTimerButtonLabels2() {
	    	c.right(); //simulate clicking on the left button
	    	g.updateUI(c); //apply the effect on the user interface
	    	assertEquals("reset",g.b1.getText());     
	    	assertEquals("inc 5",g.b2.getText());
	    	assertEquals("done",g.b3.getText());
	    	assertEquals("SetTimer",g.myText3.getText());    	
	    	assertEquals("timer",g.myText2.getText());
	    	assertEquals("memTimer = 0",g.myText1.getText());
	    }

	    @Test
	    public void testStopwatchButtonLabels1() {
	    	c.left(); //simulate clicking on the left button
	    	g.updateUI(c); //apply the effect on the user interface
	    	assertEquals("change mode",g.b1.getText());     
	    	assertEquals("run",g.b2.getText());
	    	assertEquals("(unused)",g.b3.getText());
	    }

	    @Test
	    public void testStopwatchButtonLabels2() {
	    	c.left(); //simulate clicking on the left button
	    	c.up(); //simulate clicking on the right button
	    	g.updateUI(c); //apply the effect on the user interface
	    	assertEquals("change mode",g.b1.getText());     
	    	assertEquals("split",g.b2.getText());
	    	assertEquals("reset",g.b3.getText());
	    }


}
