/**************************************************************************************
 * Copyright (C) 2014 Vitaly&Pavel team. All rights reserved.                         *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package home.vitaly.camel.routes;

import home.vitaly.camel.processors.DummyProcessor;
import home.vitaly.camel.processors.EsperEventProcessor;
import home.vitaly.camel.processors.StringEventProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 25.11.12
 * Time: 9:56
 *   camel routing написанный на JAVA-DSL (просто как удобный вариант написания обработчиков)
 */
public class CamelDSL extends RouteBuilder {

/* !!!!! не используется так как нет активации через application-context см файл application-context.xml*/


    static Logger LOG = Logger.getLogger(CamelDSL.class.getName());
    EsperEventProcessor esperProc = new EsperEventProcessor();
    StringEventProcessor strProc = new StringEventProcessor();
    DummyProcessor dummyProc = new DummyProcessor();

    public void configure() {
        LOG.info("Start camel components...");
        esperProc.setEnablePrint(false);

        from("activemq:TrQueue").to("esper:XXX");

// Java DSL variant
        from("esper:rule2a?eql=insert into TrPerWin select cardnum, sum(tsum) as s1, count(1) as cnt from home.vitaly.datamodel.Transaction.win:time(10 min) group by cardnum").process(dummyProc);
        from("esper:rule2a?eql=insert into rule2 select cardnum, cnt, s1 from TrPerWin where s1>1000000 and cnt>10 and cnt<20").process(dummyProc);
        from("esper:rule2a?eql=insert into rule3 select cardnum, cnt, s1 from TrPerWin where s1>1000000 and cnt>20 and cnt<30").process(dummyProc);
        from("esper:rule2a?eql=insert into rule4 select cardnum, cnt, s1 from TrPerWin where s1>1000000 and cnt>30").process(dummyProc);

// ИЛИ:
// основные правила работают в из  EPL-module    esper.rules.epl
//

// извлечение event из esper в MQ
        from("esper:rule2a?eql=select * from rule2").process(esperProc).to("activemq:rule2");
        from("esper:rule3a?eql=select cardnum, cnt, s1 from rule3").process(esperProc).to("activemq:rule3");
        from("esper:rule4a?eql=select cardnum, cnt, s1 from rule4").process(esperProc).to("activemq:rule4");

// print messages from MQ
        from("activemq:rule2").process(strProc);
        from("activemq:rule3").process(strProc);
        from("activemq:rule4").process(strProc);
    }
}
