package gui;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestStopwatchLabels extends TestGUIAbstract {

    @Test
    public void testStopwatch1() {
    	c.left(); //simulate clicking on the left button
    	g.updateUI(c); //apply the effect on the user interface
    	assertEquals(g.b1.getText(), "change mode");
    	assertEquals(g.b2.getText(), "run");
    	assertEquals(g.b3.getText(), "(unused)");
    	assertEquals(g.myText1.getText(), "totalTime = 0");
    	assertEquals(g.myText2.getText(), "stopwatch");
    	assertEquals(g.myText3.getText(), "ResetStopwatch");
    }

    @Test
    public void testStopwatch2() {
    	c.left(); //simulate clicking on the left button
    	c.up(); //simulate clicking on the right button
    	g.updateUI(c); //apply the effect on the user interface
    	assertEquals(g.b1.getText(), "change mode");
    	assertEquals(g.b2.getText(), "split");
    	assertEquals(g.b3.getText(), "reset");
    	assertEquals(g.myText1.getText(), "totalTime = 0");
    	assertEquals(g.myText2.getText(), "stopwatch");
    	assertEquals(g.myText3.getText(), "RunningStopwatch");
    }

}
