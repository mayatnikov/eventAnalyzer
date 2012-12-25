/**************************************************************************************
 * Copyright (C) 2008 Camel Extra Team. All rights reserved.                          *
 * http://code.google.com/p/camel-extra/                                              *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.esper;

import java.util.HashMap;
import java.util.Map;

import com.espertech.esper.client.EPRuntime;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.apache.log4j.Logger;

/**
 * @version $Revision: 1.1 $
 */
public class EsperProducer extends DefaultProducer {
    private EsperEndpoint endpoint;

    static Logger LOG = Logger.getLogger(EsperProducer.class.getName());

    public EsperProducer(EsperEndpoint endpoint) {
        super(endpoint);
        LOG.debug("Create esper producer: "+endpoint.getName());
        this.endpoint = endpoint;
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Object body = in.getBody();
        if (endpoint.isMapEvents()) {
            @SuppressWarnings("rawtypes")
			Map map = new HashMap(in.getHeaders());
            map.put("body", body);
            getEsperRuntime().sendEvent(map, endpoint.getName());
        }
        else {
            getEsperRuntime().sendEvent(body);
        }
    }

    public EPRuntime getEsperRuntime() {
        return endpoint.getEsperRuntime();
    }
}
