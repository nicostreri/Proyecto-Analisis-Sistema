package prode;
import org.javalite.activejdbc.Model;
import java.util.Map;

public abstract class IGetDatos extends Model {
	public abstract Map<String,String> getDatos();
}