package home.vitaly.camel.processors;

import java.util.Map;
// import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class EsperEventProcessor implements Processor {
  private boolean enablePrint =true;

  public void process(Exchange exchange) throws Exception {
	        String rooleName = exchange.getIn().getHeader("CamelEsperName", String.class);

	        com.espertech.esper.event.map.MapEventBean ev = (com.espertech.esper.event.map.MapEventBean)exchange.getIn().getBody();        
			@SuppressWarnings("rawtypes")
			Map map = (Map) ev.getUnderlying();
//			@SuppressWarnings("unchecked")
//			Set<String> kSet = map.keySet();
//			for(String key : kSet ) { 
//				String val = (String)map.get(key);
//				System.out.println(key+"="+val);
//				}

			String answer= rooleName+":"+map;
	        exchange.getOut().setHeader("roole", rooleName);
	        exchange.getOut().setBody(answer);

	        if(enablePrint) System.out.println(answer);
	    }

		public boolean isEnablePrint() {
	    	return enablePrint;
	    }
	    public void setEnablePrint(boolean enablePrint) {
	    	this.enablePrint = enablePrint;
	    }
	}