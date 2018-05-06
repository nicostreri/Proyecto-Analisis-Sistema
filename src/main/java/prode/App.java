package prode;

import org.javalite.activejdbc.Base;

import prode.User;



/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/prode?nullNamePatternMatchesAll=true", "root", "root");

        User u = User.findFirst("username=?", "nico");
	    //u.set("username", "nico");
        //u.set("username", "Riquelme");
        //u.set("password", "password");
        //u.saveIt();

        System.out.println(u);
        Base.close();

        System.out.println( "Hello World!" );
    }
}
