package prode;

import prode.Match;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

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
      match.set("match_date", "");

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
      match1.setDate("match_date", "2018-05-40 00:00:00"); //El dia no corresponde
	
			Date date1 = new Date();			

      Match match2 = new Match();
      match2.setDate("match_date",date1);

      Match match3 = new Match();
      //match3.setFecha("2018-04-10 25:33:04"); //La hora no corresponde

      Match match4 = new Match();
      match4.setFecha("2018-05-04 23:01:54");

      //assertEquals(match1.isValid(), false);
      assertEquals(match2.isValid(), true);
      //assertEquals(match3.isValid(), false);
      assertEquals(match4.isValid(), true);
  }
}
