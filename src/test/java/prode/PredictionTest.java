package prode;

import prode.Prediction;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PredictionTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("PredictionTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("PredictionTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validateTipoPrediction(){
      Prediction pred = new Prediction();
      pred.setTipo("empate");
      assertEquals(pred.isValid(), true);
  
      Prediction pred2 = new Prediction();
      pred2.setTipo(null);
      assertEquals(pred2.isValid(), false);

      /*
		Faltan casos de Test para comprobar que solo puedan entrar valores GanaLocal, GanaVisitante, Empate
      */
  }
}
