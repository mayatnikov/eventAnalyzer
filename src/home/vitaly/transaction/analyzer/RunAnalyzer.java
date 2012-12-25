package home.vitaly.transaction.analyzer;

import org.apache.camel.builder.RouteBuilder;

import home.vitaly.camel.processors.DummyProcessor;
import home.vitaly.camel.processors.EsperEventProcessor;
import home.vitaly.camel.processors.StringEventProcessor;
import org.apache.log4j.Logger;

public class RunAnalyzer {
    static Logger LOG = Logger.getLogger(RunAnalyzer.class.getName());

	public static void main(String... args) throws Exception {
        LOG.info("Start analyzer ...");
			org.apache.camel.spring.Main.main(args);
	}
}
