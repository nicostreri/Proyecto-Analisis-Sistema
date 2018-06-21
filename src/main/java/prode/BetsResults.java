package prode;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;

@CompositePK({ "bet_id", "result_id"})
public class BetsResults extends Model {
    public String getIdPrediction(){
        return this.getString("prediction_id");
    }
}
