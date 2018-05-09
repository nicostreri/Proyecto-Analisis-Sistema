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
      pred.setCantGV(0);
      pred.setCantGL(0);
      assertEquals(pred.isValid(), false);
  
      Prediction pred2 = new Prediction();
      pred2.setTipo(null);
      pred2.setCantGV(0);
      pred2.setCantGL(0);
      assertEquals(pred2.isValid(), false);

      /*
		Faltan casos de Test para comprobar que solo puedan entrar valores GanaLocal, GanaVisitante, Empate
      */
  }
  @Test
  public void validateCambioGoles(){
	Prediction pred = new Prediction();
	pred.setCantGV(3);
	pred.setCantGL(3);
	pred.setTipo("Empate"); //Se elige siempre el mismo, ya que se esta testeando la Cant de Goles, no el tipo
	pred.setPuntos(0); //Igual idea q setTipo

	Prediction pred1 = new Prediction();
	pred1.setCantGV(-3);
	pred1.setCantGL(3);
	pred1.setTipo("Empate");
	pred1.setPuntos(0);

	Prediction pred2 = new Prediction();
	pred2.setCantGV(3);
	pred2.setCantGL(-3);
	pred2.setTipo("Empate");
	pred2.setPuntos(0);
	
	Prediction pred3 = new Prediction();
	pred3.setCantGV(-3);
	pred3.setCantGL(-3);
	pred3.setTipo("Empate");
	pred3.setPuntos(0);

	assertEquals(pred.isValid(), true);
	assertEquals(pred1.isValid(), false);
	assertEquals(pred2.isValid(), false);
	assertEquals(pred3.isValid(), false);
  }

	  @Test
	  public void validatePuntos(){
		Prediction pred = new Prediction();
		pred.setCantGV(3);
		pred.setCantGL(3);
		pred.setTipo("Empate"); //Se elige siempre el mismo, ya que se esta testeando la Cant de Goles, no el tipo
		pred.setPuntos(0);

		Prediction pred1 = new Prediction();
		pred1.setCantGV(3);
		pred1.setCantGL(3);
		pred1.setTipo("Empate");
		pred1.setPuntos(100);

		Prediction pred2 = new Prediction();
		pred2.setCantGV(3);
		pred2.setCantGL(3);
		pred2.setTipo("Empate");
		pred2.setPuntos(-5);

		assertEquals(pred.isValid(), true);
		assertEquals(pred1.isValid(), true);
		assertEquals(pred2.isValid(), false);
	  }


}
