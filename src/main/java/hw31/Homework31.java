package hw31;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

public class Homework31 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
    	Datastore ds = new Morphia().createDatastore(client, "school");
    	
    	List<Student> studentList = ds.createQuery(Student.class).asList();
    	System.out.println("size: "+studentList.size());
    	
    	for(Student student : studentList){
    		Iterator<Score> iterator = student.getScores().iterator();
    		double lowScoreHomework = Double.MAX_VALUE;
    		while(iterator.hasNext()){
    			Score score = iterator.next();
    			if("homework".equalsIgnoreCase(score.getType())){
    				if(score.getScore() < lowScoreHomework){
    					lowScoreHomework = score.getScore();
    				}
    			}
    		}
    		Iterator<Score> removeIter = student.getScores().iterator();
    		while(removeIter.hasNext()){
    			Score score = removeIter.next();
    			if("homework".equalsIgnoreCase(score.getType()) && score.getScore() == lowScoreHomework){
    				System.out.println("STUDENT BEFORE: "+ student);
    				removeIter.remove();
    				System.out.println("STUDENT AFTER: "+ student);
    			}
    		}
    		ds.save(student);
    	}
    	
    	/*
        DB db = client.getDB("school");
        DBCollection collection = db.getCollection("students");

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
		*/
    }
}
