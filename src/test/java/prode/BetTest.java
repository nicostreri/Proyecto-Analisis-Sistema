package prode;

import prode.Bet;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
      bet.set("fecha_apuesta", "");

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
      bet1.set("fecha_apuesta", "2018-05-dos");

      Bet bet2 = new Bet();
      bet2.set("fecha_apuesta", "2018-05-03 13:21:04");

      Bet bet3 = new Bet();
      bet3.set("fecha_apuesta", "2018-04-10!");

      Bet bet4 = new Bet();
      bet4.set("fecha_apuesta", "20180504");

      assertEquals(bet1.isValid(), false);
      //assertEquals(bet2.isValid(), true);
      assertEquals(bet3.isValid(), false);
      assertEquals(bet4.isValid(), true);
  }
}
