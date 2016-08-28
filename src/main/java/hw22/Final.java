package hw22;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by sengopal on 2/17/15.
 */
public class Final {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("photos");
        DBCollection images = db.getCollection("images");
        DBCollection albums = db.getCollection("albums");

        /*
        Set<Integer> connectedImgList = new HashSet<Integer>();

        DBCursor cursor = albums.find().sort(new BasicDBObject("_id", 1));
        try {
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                BasicDBList imagesList = (BasicDBList) object.get("images");
                for(Object o :  imagesList) {
                    connectedImgList.add(Integer.parseInt(o.toString()));
                }
            }
        } finally {
            cursor.close();
        }

        System.out.println(connectedImgList.size());

        int cnt = 0;

        cursor = images.find().sort(new BasicDBObject("_id", 1));

        BasicDBObject query2 = new BasicDBObject();
        List<Integer> list = new ArrayList<Integer>();

        try {
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                Object id = object.get("_id");
                int i = Integer.parseInt(id.toString());
                if(!connectedImgList.contains(i)){
                    cnt++;
                    list.add(i);
                }
            }
        } finally {
            cursor.close();
        }
        System.out.println(cnt);
        query2.put("_id", new BasicDBObject("$in", list));
        images.remove(query2);
        */
                DB db1 = client.getDB("test");
                DBCollection animals = db.getCollection("animals");


                BasicDBObject animal = new BasicDBObject("animal", "monkey");

                animals.insert(animal);
                animal.removeField("animal");
                animal.append("animal", "cat");
                animals.insert(animal);
                animal.removeField("animal");
                animal.append("animal", "lion");
                animals.insert(animal);

            }
}
