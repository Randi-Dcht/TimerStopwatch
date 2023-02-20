package states;

import gui.TestGUIAbstract;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import states.Context;
import states.stopwatch.*;
import states.timer.*;

public class TestScenarios extends TestGUIAbstract {

	Context c;
	
    @Before
    public void setup() {
    	c = new Context();
     	//before each test, reset the timer values to avoid interference between tests:
    	AbstractTimer.resetInitialValues();
    	AbstractStopwatch.resetInitialValues();
    }
    
  //This is more a kind of integration test than a real unit test	
  @Test
  public void completeScenario()
  {
	  assertEquals(IdleTimer.Instance(),c.currentState);
	  assertEquals(0,AbstractTimer.getMemTimer());
	  
	  c.right(); // start incrementing the memTimer variable
	  c.tick();
	  assertSame(SetTimer.Instance(),c.currentState);
	  assertEquals(1,AbstractTimer.getMemTimer());
	  assertEquals(0,AbstractTimer.getTimer());

	  c.tick();
	  assertEquals(2,AbstractTimer.getMemTimer());
	  assertEquals(0,AbstractTimer.getTimer());

	  c.right(); // stop incrementing the memTimer variable
	  c.tick();
	  assertEquals(2,AbstractTimer.getMemTimer());
	  assertEquals(0,AbstractTimer.getTimer());
	  
	  c.up(); // start running the timer
	  assertEquals("value of timer ", 2, AbstractTimer.getTimer());
	  c.tick();
	  assertEquals("value of memTimer ", 2, AbstractTimer.getMemTimer());
	  assertEquals("value of timer ", 1, AbstractTimer.getTimer());
	  
	  
	  c.up(); // pause the timer
	  c.tick();
	  assertSame(PausedTimer.Instance(), c.currentState);
	  assertEquals("value of memTimer ", 2, AbstractTimer.getMemTimer());
	  assertEquals("value of timer ", 1, AbstractTimer.getTimer());
	  
	  c.left(); // go to stopwatch mode
	  c.tick();
	  assertSame(ResetStopwatch.Instance(), c.currentState);
	  assertEquals("value of totalTime ", 0, AbstractStopwatch.getTotalTime());
	  assertEquals("value of lapTime ", 0, AbstractStopwatch.getLapTime());
	  
	  c.up(); //start running the stopwatch
	  c.tick();
	  assertSame(RunningStopwatch.Instance(), c.currentState);
	  assertEquals("value of totalTime ", 1, AbstractStopwatch.getTotalTime());
	  assertEquals("value of lapTime ", 0, AbstractStopwatch.getLapTime());
	 
	  c.up(); // record stopwatch laptime
	  c.tick();
	  assertSame(LaptimeStopwatch.Instance(), c.currentState);
	  assertEquals("value of totalTime ", 2, AbstractStopwatch.getTotalTime());
	  assertEquals("value of lapTime ", 1, AbstractStopwatch.getLapTime());
	  
	  c.left(); // go back to timer mode (remembering history state)
	  c.tick();
	  assertSame(PausedTimer.Instance(), c.currentState);
	  assertEquals("value of memTimer ", 2, AbstractTimer.getMemTimer());
	  assertEquals("value of timer ", 1, AbstractTimer.getTimer());	 
	  
	  c.up(); // continue running timer
	  assertSame(RunningTimer.Instance(), c.currentState);
	  c.tick();
	  //automatic switch to ringing timer since timer has reached 0...
	  assertSame(RingingTimer.Instance(), c.currentState);
	  assertEquals("value of memTimer ", 2, AbstractTimer.getMemTimer());
	  assertEquals("value of timer ", 0, AbstractTimer.getTimer());	 
	  
	  c.right(); // return to idle timer state
	  c.tick();
	  assertSame(IdleTimer.Instance(), c.currentState);
	  assertEquals("value of memTimer ", 2, AbstractTimer.getMemTimer());
	  assertEquals("value of timer ", 0, AbstractTimer.getTimer());	 	  
	  }

	  @Test
	public void testInstance()
	  {
		  LaptimeStopwatch ls = LaptimeStopwatch.Instance();
		  assertNotNull(ls);
	  }

	  @Test
	public void testUp()
	  {
		  LaptimeStopwatch ls = LaptimeStopwatch.Instance();
		  ClockState cs = ls.up();
		  assertEquals(RunningStopwatch.Instance(), cs);
	  }

	  @Test
	public void testGetUpText()
	  {
		  LaptimeStopwatch ls = LaptimeStopwatch.Instance();
		  String s = ls.getUpText();
		  assertEquals("unsplit", s);
	  }
/*
	  @Test
	public void testEntry()
	  {
		  LaptimeStopwatch ls = LaptimeStopwatch.Instance();
		  ls.entry();
		  assertEquals(0, AbstractStopwatch.getLapTime());
		  assertEquals(5, ls.timeout);
	  }

	  @Test
	public void testDoIt()
	  {
		  LaptimeStopwatch ls = LaptimeStopwatch.Instance();
		  ls.entry();
		  ls.doIt();
		  assertEquals(1, AbstractStopwatch.getTotalTime());
		  assertEquals(4, ls.timeout);
	  }
*/
	  @Test
	public void testGetDisplayString()
	  {
		  LaptimeStopwatch ls = LaptimeStopwatch.Instance();
		  String s = ls.getDisplayString();
		  assertEquals("lapTime = 0", s);
	  }

}
