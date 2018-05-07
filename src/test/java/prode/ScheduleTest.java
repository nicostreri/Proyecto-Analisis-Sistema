package prode;

import prode.Schedule;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScheduleTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("ScheduleTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("ScheduleTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfName(){
      Schedule sc = new Schedule();
      sc.set("nombre_fecha", "");
      assertEquals(sc.isValid(), false);
  }
}
