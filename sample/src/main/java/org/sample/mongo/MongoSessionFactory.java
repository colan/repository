package org.sample.mongo;

import com.mongodb.DBCollection;

public interface MongoSessionFactory {
	
    public DBCollection openSession(String collectionName);

}
