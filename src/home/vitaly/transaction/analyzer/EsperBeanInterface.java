package home.vitaly.transaction.analyzer;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 16.12.12
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
public interface EsperBeanInterface {

    public EPServiceProvider getEngine();
    public void setEngine(EPServiceProvider engine);
    public EPAdministrator getAdm();
    public void setAdm(EPAdministrator adm);
}
