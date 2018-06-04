package prode;

import prode.Bet;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BetTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("BetTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("BetTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfFechasApuestas(){
      Bet bet = new Bet();
      bet.set("bet_date", "");

      assertEquals(bet.isValid(), false);
  }

  /*
    Solo son validas fechas formadas por 0-9, guion, espacio y dos puntos.
    FORMATO: 'YYYY-MM-DD HH:MM:SS'
    No se admiten ningun otro caracter   
  */
  @Test
  public void validacionFechasApuestasCorrectas(){
      Bet bet1 = new Bet();
      bet1.setDate("bet_date", "2018-05-40 00:00:00"); //El dia no corresponde
	
			Date date1 = new Date();			

      Bet bet2 = new Bet();
      bet2.setDate("bet_date",date1);

      Bet bet3 = new Bet();
      bet3.setFecha("2018-04-10 25:33:04"); //La hora no corresponde

      Bet bet4 = new Bet();
      bet4.setFecha("2018-05-04 23:01:54");

      //assertEquals(bet1.isValid(), false);  CONSULTAR
      assertEquals(bet2.isValid(), true);
      //assertEquals(bet3.isValid(), false);  CONSULTAR
      assertEquals(bet4.isValid(), true);
  }
}
