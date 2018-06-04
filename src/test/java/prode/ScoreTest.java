package prode;

import prode.Score;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("ScoreTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("ScoreTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  /*
    Los puntajes obtenidos, como tambien los partidos acertados no pueden ser negativos
  */
  @Test
  public void validacionFechasMatchCorrectas(){
      Score s1 = new Score();
      s1.set("amount_points", -1);
      s1.set("correct_predicted_matches", -1);

      Score s2 = new Score();
      s2.set("amount_points", -1);
      s2.set("correct_predicted_matches", 0);

      Score s3 = new Score();
      s3.set("amount_points", 0);
      s3.set("correct_predicted_matches", 0);

      Score s4 = new Score();
      s4.set("amount_points", 0);
      s4.set("correct_predicted_matches", -1);

      Score s5 = new Score();
      s5.set("amount_points", 23);
      s5.set("correct_predicted_matches", 3);


      assertEquals(s1.isValid(), false);
      assertEquals(s2.isValid(), false);
      assertEquals(s3.isValid(), true);
      assertEquals(s4.isValid(), false);
      assertEquals(s5.isValid(), true);
  }
}
