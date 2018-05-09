package prode;

import prode.Player;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("PlayerTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("PlayerTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfName(){
      Player persona = new Player();
      persona.setUsername("");
      assertEquals(persona.isValid(), false);
  }
  
  @Test
  public void validacionPlayer(){
  Player jugador1 = new Player();
  jugador1.setUsername("juan");
  
  Player jugador2 = new Player();
  jugador2.setUsername("jose1995");

  assertEquals(jugador1.isValid(), true);
  assertEquals(jugador2.isValid(), true);

 }
}
