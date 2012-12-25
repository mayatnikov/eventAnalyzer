package home.vitaly.camel.processors;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;


/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 22.12.12
 * Time: 10:46
 * converter from POJO to MongoDB writable object
 * used in Camel router
 */
public class Pojo2DBObjectProcessor implements Processor {
    static Logger LOG = Logger.getLogger(Pojo2DBObjectProcessor.class.getName());

    @Override
    public void process(Exchange exch) throws Exception {
        exch.getOut().setBody(obj2map(exch.getIn().getBody()));
    }

    private static DBObject obj2map(Object o)
            throws Exception {
        DBObject obj = new BasicDBObject();
        BeanInfo info = Introspector.getBeanInfo(o.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method getter = pd.getReadMethod();
            if (getter != null) {
                obj.put(pd.getName(), ""+getter.invoke(o));
            }
        }
        return obj;
    }
}