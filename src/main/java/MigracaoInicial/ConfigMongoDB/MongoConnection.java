package ConfigMongoDB;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

public class MongoConnection {
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DB_NAME = "out";
    private static MongoConnection uniqInstance;
    private static int mongoInstance = 1;
    private static final String DBURI = "mongodb://" + HOST + ":" + PORT;
    private MongoClient mongo;
    private DB db;
    
    private MongoConnection(){        
    }
    
    //garante sempre uma unica instancia
    public static synchronized MongoConnection getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new MongoConnection();
        }        
        return uniqInstance;
    }
    
    //garante um unico objeto mongo
    public DB getDB() {
        if (mongo == null) {
            try {
                System.out.println(DBURI);
                mongo = new MongoClient(HOST, PORT);
                //mongo = new MongoClient(HOST, PORT);
                //mongo = new Mongo(HOST,PORT);
                db = mongo.getDB(DB_NAME);
            }
            catch (Exception e){            
                //catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return db;
    }
}
