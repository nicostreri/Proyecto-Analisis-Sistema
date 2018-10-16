package prode.controladores;

import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;

import prode.*;

public class Metrics {

	public static final MetricRegistry metrics = new MetricRegistry();
	private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                                                .convertRatesTo(TimeUnit.SECONDS)
                                                .convertDurationsTo(TimeUnit.MILLISECONDS)
                                                .build(); 

    public static void iniciar(){
    	reporter.start(5,TimeUnit.SECONDS);
    }

}