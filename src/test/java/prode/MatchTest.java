package prode;

import prode.Match;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("MatchTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("MatchTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfFechasMatch(){
      Match match = new Match();
      match.set("fecha", "");

      assertEquals(match.isValid(), false);
  }

  /*
    Solo son validas fechas formadas por 0-9, guion espacio y dos puntos.
		FORMATO: 'YYYY-MM-DD HH:MM:SS'
    No se admiten ningun otro caracter  
  */
  @Test
  public void validacionFechasMatchCorrectas(){
      Match match1 = new Match();
      match1.set("fecha", "2018-05-dos");

      Match match2 = new Match();
      match2.set("fecha", "2018-05-03 21:04:33");

      Match match3 = new Match();
      match3.set("fecha", "2018-05-04 21:");

      Match match4 = new Match();
      match4.set("fecha", "20180502153000");

      assertEquals(match1.isValid(), false);
      //assertEquals(match2.isValid(), true);
      assertEquals(match3.isValid(), false);
      assertEquals(match4.isValid(), true);
  }
}
