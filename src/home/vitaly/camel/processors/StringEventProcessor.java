/**************************************************************************************
 * Copyright (C) 2014 Vitaly&Pavel team. All rights reserved.                         *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/

package home.vitaly.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Camel router, only print message
 */
public class StringEventProcessor implements Processor {

	private boolean enablePrint =true;

    public void process(Exchange exchange) throws Exception {

    	String rooleName=(String)exchange.getIn().getHeader("roole");
        String msg = (String)exchange.getIn().getBody();        

        exchange.getOut().setHeader("roole", rooleName);
        exchange.getOut().setBody(msg);
        if(enablePrint) System.out.println(msg);
        
    }

	public boolean isEnablePrint() {
		return enablePrint;
	}

	public void setEnablePrint(boolean enablePrint) {
		this.enablePrint = enablePrint;
	}

}