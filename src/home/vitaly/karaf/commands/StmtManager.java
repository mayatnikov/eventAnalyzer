/**************************************************************************************
 * Copyright (C) 2014 Vitaly&Pavel team. All rights reserved.                         *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package home.vitaly.karaf.commands;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EPStatementException;
import home.vitaly.transaction.analyzer.EsperBeanInterface;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 09.12.12
 * Time: 22:18
 */

@Command(scope = "esper", name = "stmt", description="display list of all statements or contents of one esper-statement")
public class StmtManager extends OsgiCommandSupport {
    EsperBeanInterface eb;

    public EsperBeanInterface getEb() {
        return eb;
    }

    public void setEb(EsperBeanInterface eb) {
        this.eb = eb;
    }


    @Argument(index = 0, name = "stmtName", description = "Statement name | null(display all statements)", required = false, multiValued = false)
    String stmtName = null;

    @Argument(index = 1, name = "action", description = "Action: [info|stop|start|destroy|create]", required = false, multiValued = false)
    String action = null;

    @Argument(index = 2, name = "epl", description = "EPL or Pattern expretion", required = false, multiValued = false)
    String epl = null;

    @Override
    protected Object doExecute() throws Exception {
        if(stmtName == null || stmtName.equalsIgnoreCase("all")) printStmtList();
        else if (action==null || action.equalsIgnoreCase("info")) printStmtInfo();
        else if (action!=null && action.equalsIgnoreCase("stop")) stopStatement();
        else if (action!=null && action.equalsIgnoreCase("start")) startStatement();
        else if (action!=null && action.equalsIgnoreCase("destroy")) destroyStatement();
        else if (action!=null && action.equalsIgnoreCase("create")) createStatement();

        return null;
    }

    private void printStmtList() {

            String[] statements;
            statements = eb.getAdm().getStatementNames();
            System.out.println("List of statements:");
            for (int i = 0, statementNamesLength = statements.length; i < statementNamesLength; i++) {
                String s = statements[i];
                EPStatement st2 =  eb.getAdm().getStatement(s);
                System.out.println("STMT NAME:\"" + s + "\" STATE:[" + st2.getState()+"]");
            }
        }

    private void printStmtInfo() {
            String ret="Statement =["+stmtName+"] - not found";
            EPStatement st =  eb.getAdm().getStatement(stmtName);
            if(st!=null) {
                ret = "Name:\""+st.getName()+"\"\n"+
                        "Type:["+  (st.isPattern() ? "Pattern" : "EPL")+"]\n"+
                        "Status:["+st.getState().toString()+"]\n"+
                        "Info:["+st.getText()+"]";
            }
        System.out.println(ret);
        }

    public boolean stopStatement() {
        EPStatement st =  eb.getAdm().getStatement(stmtName);
        if(st!=null && st.isStarted()) {
            st.stop();
            System.out.println("Stop statement:[" + st.getName()+"]");
            return true;
        }
        else { throw new EPStatementException("stop statement",stmtName); }
    }

    public boolean startStatement() {
        EPStatement st =  eb.getAdm().getStatement(stmtName);
        if(st!=null && st.isStopped()) {
            st.start();
            System.out.println("Start statement:["+st.getName()+"]");
            return true;
        }
        else { throw new EPStatementException("start statement",stmtName); }
    }

    public boolean destroyStatement() {
        EPStatement st =  eb.getAdm().getStatement(stmtName);
        if(st!=null) {
            st.destroy();
            System.out.println(stmtName+" - destored"   );
            return true;
        }
        else { throw new EPStatementException("destroy",stmtName); }
    }

        public boolean createStatement() {
        System.out.println("StmtName=" +  stmtName + "epl="+epl);
        EPStatement  st;
        if(epl!=null)  {
            st = eb.getAdm().createEPL(epl,stmtName);
            if(st!=null)
            {
                st.start();
                System.out.println("stmt "+stmtName+" was created and started");
                    return true;
            }
            else { throw new EPStatementException("Error create of statement",stmtName); }
        }
        else throw new EPStatementException("EPL-statement test is null",stmtName);
        }

}
