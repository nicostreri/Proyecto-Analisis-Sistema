package prode.controladores;

import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;
import prode.*;

public final class Metrics {

	public static final MetricRegistry metrics = new MetricRegistry();
	public static Meter requestsPet = metrics.meter("peticiones-totales");
	public static Meter requestsLogin = metrics.meter("logins-totales");
	private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                                                .convertRatesTo(TimeUnit.SECONDS)
                                                .convertDurationsTo(TimeUnit.MILLISECONDS)
                                                .build(); 

    public static void iniciar(){
    	reporter.start(10,TimeUnit.SECONDS);
    }
}