/**************************************************************************************
 * Copyright (C) 2014 Vitaly&Pavel team. All rights reserved.                         *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package home.vitaly.restoreHistory;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;


/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 27.01.13
 * Time: 11:57
 */
public class GetEventsFromDB {
    private Mongo conn;
    private String dbName;
    private DBCursor cursor;

public void init() {

//    MongoDBManager manager = new MongoDBManager();
//    try {
//
//        // Get the database from the Connection (Schema in RDBMS World).
//        DB db = conn.getDB("db212");
//
//        // Get the collection (Table in RDBMS World).  Don't worry, even if it is not there,
//        // it can be a lazy fetch and will be created when the first object is inserted
//        DBCollection collection = manager.getCollection("coll1", db);
//
//        // Check whether there is any document (RDBMS world -> Row), present
////            if (collection.count() == 0) {
//        if(collection.count() < 10) {
//            // Create the document in the TEST collection (RDBMS world -> Table)
//            DBObject object = new BasicDBObject();
//            object.put("name", "Нечто такое");
//            object.put("message", "Здорово усатый !");
//            collection.insert(object);
//        }
//        manager.printCollection(collection);
//    } finally {
//        if (conn != null) {
//            conn.close();
//        }
//    }


}

   public DBObject getNextEvent() {
            if  (cursor.hasNext())      return cursor.next();
            else return null;
   }

}
