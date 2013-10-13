package home.vitaly.transaction.analyzer;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 22.01.13
 * Time: 8:51
 */
public class HistoryLoader {

    private int RestoreHours;



}


/*

Рекомендации разработчика по загрузке истории:
Start by disabling the internal timer in the configuration passed when obtaining an engine instance:

  Configuration config = new Configuration();
  config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);

Set time to zero or to the oldest event's timestamp:

  runtime.sendEvent(new CurrentTimeEvent(0));

Next create a bunch of EPL statements.
From your event source, read events and send events into the engine advancing time (here entry is your event object):

  CurrentTimeEvent timeEvent = new CurrentTimeEvent(entry.timestamp);
  cepRT.sendEvent(timeEvent);
  cepRT.sendEvent(entry);

It is not necessary to advance time for every event that your application sends in.
Instead and as an optimization you could decide on a time resolution
and advance time according to that resolution (i.e. every 100 milliseconds).


Date & Time:
import java.util.*;
import java.text.*;

public class DateDemo {
   public static void main(String args[]) {

      Date dNow = new Date( );
      SimpleDateFormat ft =
      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

      System.out.println("Current Date: " + ft.format(dNow));
   }
}

import java.util.*;
import java.text.*;

public class DateDemo {

   public static void main(String args[]) {
      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

      String input = args.length == 0 ? "1818-11-11" : args[0];

      System.out.print(input + " Parses as ");

      Date t;

      try {
          t = ft.parse(input);
          System.out.println(t);
      } catch (ParseException e) {
          System.out.println("Unparseable using " + ft);
      }
   }
}

*/