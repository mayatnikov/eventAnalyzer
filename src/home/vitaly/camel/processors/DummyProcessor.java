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
 * Camel dummy processor
 */
public class DummyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception { }

}