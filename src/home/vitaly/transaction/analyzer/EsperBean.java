package home.vitaly.transaction.analyzer;


import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.deploy.DeploymentResult;
import com.espertech.esper.client.EPStatement;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 10.11.12
 * Time: 23:38
 * Initialize esper engine and load esper rules (epl-file)
  */
public class EsperBean implements EsperBeanInterface {
    private String esperConfigFile;
    private String esperRulesFile;     // file with epl rules
    private String esperRulesURI;      // symbolic  name
    private String esperEngineName;

    private EPServiceProvider engine;

    private EPAdministrator adm;

    static Logger  LOG = Logger.getLogger(EsperBean.class.getName());

    public void init() {
        DeploymentResult result;
        Configuration config = new Configuration();
        URL url = EsperBean.class.getClassLoader().getResource(esperConfigFile);
// if file not found set default configuration
        if (url == null) {
            LOG.error("Error loading configuration file '" + esperConfigFile + "' from classpath, use default settings");
            config.getEngineDefaults().getExecution().setPrioritized(true);
            config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
            config.getEngineDefaults().getEventMeta().setDefaultEventRepresentation(Configuration.EventRepresentation.MAP);
        }
        else LOG.debug("Loading configuration file '" + esperConfigFile + "' from classpath");
        config.configure(url);

//        engine = EPServiceProviderManager.getProvider(esperEngineName,config);
        engine =   EPServiceProviderManager.getDefaultProvider(config);
        engine.initialize(); // clear all rules

        InputStream rulesStream = this.getClass().getClassLoader().getResourceAsStream(esperRulesFile);

        if (rulesStream == null) {
            throw new RuntimeException("Failed to find file ["+esperConfigFile+"] in classpath");
        }
        try {  // deploy epl-rules into engine
            result = engine.getEPAdministrator().getDeploymentAdmin().readDeploy(rulesStream, null, null, null);
        }
        catch (Exception e) {
            throw new RuntimeException("Error deploying EPL from epl-file:"+esperRulesFile + e.getMessage(), e);
        }
        LOG.info("Deployed size=" + result.getStatements().size() +" rules");
        adm =      engine.getEPAdministrator();
        if(LOG.isDebugEnabled()) dumpStatements();
    }

// Dump statement list with status
    public void dumpStatements() {
        LOG.debug("======= Dump of statements / status ==========");
        String[] ss = EPServiceProviderManager.getProviderURIs();
        for(int i=0, ssl = ss.length; i< ssl; i++) {
            LOG.debug("engine-name:" + ss[i]);
        }

        String[] statementNames;
        statementNames = adm.getStatementNames();
        for (int i = 0, statementNamesLength = statementNames.length; i < statementNamesLength; i++) {
            String s = statementNames[i];
            EPStatement st2 =  engine.getEPAdministrator().getStatement(s);
            LOG.debug("Stmt NAME:" + s + " STATE:" + st2.getState());
        }
    }

    public void printStatements() {
        String[] statementNames;
        statementNames = adm.getStatementNames();
        for (int i = 0, statementNamesLength = statementNames.length; i < statementNamesLength; i++) {
            String s = statementNames[i];
            EPStatement st2 =  engine.getEPAdministrator().getStatement(s);
            System.out.println("Stmt NAME:" + s + " STATE:" + st2.getState());
        }
    }

    public void stop() {

    }

    public String getEsperConfigFile() {
        return esperConfigFile;
    }

    public void setEsperConfigFile(String esperConfigFile) {
        this.esperConfigFile = esperConfigFile;
    }

    public String getEsperRulesFile() {
        return esperRulesFile;
    }

    public void setEsperRulesFile(String esperRulesFile) {
        this.esperRulesFile = esperRulesFile;
    }

    public String getEsperRulesURI() {
        return esperRulesURI;
    }

    public void setEsperRulesURI(String esperRulesURI) {
        this.esperRulesURI = esperRulesURI;
    }

    public String getEsperEngineName() {
        return esperEngineName;
    }

    public void setEsperEngineName(String esperEngineName) {
        this.esperEngineName = esperEngineName;
    }

    public EPServiceProvider getEngine() {
        return engine;
    }

    public void setEngine(EPServiceProvider engine) {
        this.engine = engine;
    }

    public EPAdministrator getAdm() {
        return adm;
    }

    public void setAdm(EPAdministrator adm) {
        this.adm = adm;
    }
}