package prode;

import prode.PlayersFixtures;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayersFixturesTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("PlayersFixtureTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("PlayersFixturesTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  /*@Test
  public void validatePrecenseOfPlayer(){
      PlayerFixture play = new PlayerFixture();
      play.obtenerPlayer("");
      assertEquals(play.isValid(), false);
  
  }
  
  @Test
  public void validatePrecenseOfFixture(){
      PlayerFixture play = new PlayerFixture();
      play.obtenerFixture("");
      assertEquals(play.isValid(), false);
  
  }*/

}
