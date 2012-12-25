package home.vitaly.karaf.commands;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 09.12.12
 * Time: 22:28
 */
public class EsperCompleter  implements Completer {

    /**
     * @param buffer it's the beginning string typed by the user
     * @param cursor it's the position of the cursor
     * @param candidates the list of completions proposed to the user
     */
    public int complete(String buffer, int cursor, List candidates) {

        StringsCompleter delegate = new StringsCompleter();
        delegate.getStrings().add("all");
        delegate.getStrings().add("[epl] info");
        delegate.getStrings().add("[epl] start");
        delegate.getStrings().add("[epl] stop");
        delegate.getStrings().add("[epl] destroy");
        return delegate.complete(buffer, cursor, candidates);
    }


}
