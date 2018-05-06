package prode;

import prode.User;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
  @Before
  public void before(){
    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode_test?nullNamePatternMatchesAll=true", "root", "root");
    System.out.println("UserTest setup");
    Base.openTransaction();
  }

  @After
  public void after(){
      System.out.println("UserTest tearDown");
      Base.rollbackTransaction();
      Base.close();
  }

  @Test
  public void validatePrecenseOfUsernames(){
      User user = new User();
      user.set("username", "");

      assertEquals(user.isValid(), false);
  }

  /*
    Solo son validos usernames formados por A-Z a-z 0-9.
    No se admiten ningun otro caracter  
  */
  @Test
  public void validacionUsernamesCorrectos(){
      User user = new User();
      user.set("username", "prueba 123");
      user.set("password","123");

      User user1 = new User();
      user1.set("username", "prueba123");
      user1.set("password","123");

      User user2 = new User();
      user2.set("username", "prueba123!");
      user2.set("password","123");

      User user3 = new User();
      user3.set("username", "esteEsUnUsuarioValido151");
      user3.set("password","123");

      assertEquals(user.isValid(), false);
      assertEquals(user1.isValid(), true);
      assertEquals(user2.isValid(), false);
      assertEquals(user3.isValid(), true);
  }
}
