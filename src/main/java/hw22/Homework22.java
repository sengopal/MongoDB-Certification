package hw22;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sengopal on 1/17/15.
 */
public class Homework22 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");

        QueryBuilder builder = QueryBuilder.start("type").is("homework");
        System.out.println(builder.get());

        DBCursor cursor = collection.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", 1).append("score", 1));
        System.out.println(cursor.count());
        int prevStudent = -1;
        int deleteRec = 0;
        List<ObjectId> removeList = new ArrayList<ObjectId>();

        try {
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                int currStudent = (Integer) obj.get("student_id");

                if(prevStudent!=currStudent){
                  removeList.add((ObjectId) obj.get("_id"));
                }
                prevStudent=currStudent;
            }
        } finally {
            cursor.close();
        }
        System.out.println("removeList: "+ removeList.size());
        for(ObjectId id : removeList){
            collection.remove(new BasicDBObject("_id", id));
        }

    }
}