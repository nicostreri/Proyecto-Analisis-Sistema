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
      pred.setTipo("");
      assertEquals(pred.isValid(), false);
  
      Prediction pred2 = new Prediction();
      pred2.setTipo(null);
      assertEquals(pred2.isValid(), false);
  }
  @Test
  public void validateCambioGoles(){
	Prediction pred3 = new Prediction();
	pred3.setCantGV(3);

	Prediction pred4 = new Prediction();
	pred4.setCantGL(-49);

	Prediction pred5 = new Prediction();
	pred5.setPuntos(20);

	assertEquals(pred3.isValid(), true);
	assertEquals(pred4.isValid(), false);
	assertEquals(pred5.isValid(), true);

	//COMO HACER PARA QUE SOLO ALMACENEN NUMEROS POSITIVOS
  }

}
