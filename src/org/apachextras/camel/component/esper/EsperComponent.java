/**************************************************************************************
 * Copyright (C) 2008 Camel Extra Team. All rights reserved.                          *
 * http://code.google.com/p/camel-extra/                                              *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.apachextras.camel.component.esper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.espertech.esper.client.*;
import com.espertech.esper.client.deploy.DeploymentException;
import com.espertech.esper.client.deploy.DeploymentResult;
import com.espertech.esper.client.deploy.EPDeploymentAdmin;
import com.espertech.esper.client.deploy.ParseException;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.log4j.Logger;

/**
 * A component for working with <a href="http//esper.codehaus.org/">Esper</a>
 *
 * @version $Revision: 1.1 $
 */
public class EsperComponent extends DefaultComponent {
    static Logger LOG = Logger.getLogger(EsperComponent.class.getName());

    private EPServiceProvider esperService;
    private EPRuntime esperRuntime;

    protected Endpoint createEndpoint(String uri, String remaining, @SuppressWarnings("rawtypes") Map parameters) throws Exception {
//        LOG.debug("Try create esper endpoint:");
        return new EsperEndpoint(uri, this, remaining);
    }

    public EPServiceProvider getEsperService() {
        if (esperService == null) {
            esperService = EPServiceProviderManager.getDefaultProvider();
        }
        return esperService;
    }

    public void setEsperService(EPServiceProvider esperService) {
        this.esperService = esperService;
    }

    public EPRuntime getEsperRuntime() {

        if (esperRuntime == null) {
            esperRuntime = getEsperService().getEPRuntime();
        }
        return esperRuntime;
    }


    @Override
    protected void doStart() throws Exception {
        super.doStart();
        // lets force lazy creation
        getEsperRuntime();
        LOG.debug("Starting esper component");

    }

}
