package org.sample.mongo;

import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.sample.mongo.TestDBObject.Style;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class TestMongo {
	
	public final static String HISTORY_COLLECTION = "historyquotationdbobject";
	
	public final static String REALTIME_COLLECTION = "realtimequotationdbobject";

	Dao dao = new Dao(new DataSource(),HISTORY_COLLECTION);
	
	public static void main(String[] args) throws Exception {
		
		new TestMongo().insertTest();
		//new TestMongo().save(1,System.currentTimeMillis());
		//new TestMongo().selectTest("JI_1");
		//new TestMongo().deleteTest();
	}

	void deleteTest() throws Exception{
		long curr = System.currentTimeMillis();
		long time = curr - (1000L * 60 * 60 * 24 * 30);
				
		BasicDBObject condition = new BasicDBObject();
		condition.put(TestDBObject.HISTORY_STYLE, String.valueOf(Style.ONE_MIN.getValue()));
		
		TestDBObject express = new TestDBObject();
		express.put("$lte", time);
		
		condition.put(TestDBObject.RECORD_TIME, express);
		
		//dao.findAndRemove(condition);
				
		DBCursor cursor = dao.find(condition);
		
		int i = 0;
		while(cursor.hasNext()){
			DBObject data = cursor.next();
			dao.remove(data);
			if(i % 1000 == 0)
				Thread.sleep(50);
			System.out.println(i++);
		}
	}
	
	@SuppressWarnings("deprecation")
	void selectTest(String code){
		TestDBObject condition = new TestDBObject();
		condition.put(TestDBObject.CODE, code);

		TestDBObject sort = new TestDBObject();
		sort.put(TestDBObject.RECORD_TIME, -1);
		
		//取出最新的100条实时行情
		DBCursor cursor = dao.find(condition).sort(sort).limit(100);
		//com.mongodb.DBObject quotation = null;
	
		if(cursor.size() >= 100) {
			//取出最老的一条行情
			List<com.mongodb.DBObject> list = cursor.toArray();
		
			DBObject data1 = list.get(99);
			Date d1 = new Date(Long.parseLong(data1.get(TestDBObject.RECORD_TIME).toString()));
			System.out.println(d1.toLocaleString());
			//2011-1-24 10:40:00
			
			/*
			DBObject data2 = cursor.next();
			Date d2 = new Date(Long.parseLong(data2.get(DBObject.RECORD_TIME).toString()));
			System.out.println(d2.toLocaleString());
			//2011-1-24 12:19:00
			*/
		} else {
			System.err.println("!!!");
		}
	}
	
	void insertTest() throws Exception{
		Calendar c1 = Calendar.getInstance();
		c1.set(2011, 1, 1, 0, 0, 0);
		Calendar c2 = Calendar.getInstance();
		c2.set(2011, 4, 1, 0, 0, 0);
		
		long start = c1.getTimeInMillis();
		long end = c2.getTimeInMillis();

		long j = 0,k = 0,profile = System.currentTimeMillis();
		while(start < end){
			for(int i = 1; i <= 100; i= i + 10){
				save(i,start);
			}
			start = start + (1000L * 60);
			
			j++;
			if(j % 1440 == 0){
				k++;
				long now = System.currentTimeMillis();
				System.err.println(k + " : 144,000 record has insert! use time: " + (now - profile));
				profile = now;
			}
			
			if(j % 14400 == 0){
				System.out.println("waiting.................");
				Thread.sleep(1000L * 2);
			}
		}
	}
	
	void save(int index,long time){
		String code;
		String price;
		for(int i = 0; i < 10; i++){
			TestDBObject data = new TestDBObject();
			code = "JI_" + (index + i);
			price = String.valueOf((float)(Math.random() * 10));
			
			data.put(TestDBObject.CODE, code);
			data.put(TestDBObject.RECORD_TIME, time);
			data.put(TestDBObject.T_OPENPRC, price);
			data.put(TestDBObject.T_HIGHPRC, price);
			data.put(TestDBObject.T_LOWPRC, price);
			data.put(TestDBObject.T_CLOSEPRC, price);
			data.put(TestDBObject.T_VOLUME, "-3");
			data.put(TestDBObject.T_HOLDINGAMT, "-1");
			data.put(TestDBObject.HISTORY_STYLE, "0");
			
			dao.save(data);
			//System.out.println(data.toString());
		}
	}
}

class Dao extends MongoDao<TestDBObject> {
	private DataSource ds;
	private String collection;
	
	public Dao(DataSource ds,String collection){
		this.ds = ds;
		this.collection = collection;
	}
	
	@Override
	public MongoSessionFactory getMongoSessionFactory(){
		return new MongoSessionFactory() {
			@Override
			public DBCollection openSession(String collectionName) {
				return ds.getCollection(collection);
			}
		};
	}
}

class DataSource {
	private String host = "127.0.0.1";
	private int port = 27017;
	private String dbname = "mnova_history";
	
	public DBCollection getCollection(String name) {
		try {
			Mongo m = new Mongo(host, port);
			DB db = m.getDB(dbname);
			return db.getCollection(name);
		} catch (UnknownHostException e) {
			System.err.println("Unknown Host:{" + host + ":" + port + "}");
		} catch (MongoException e) {
			System.err.println("Mongo is error!!!!");
		}
		return null;
	}
}

class TestDBObject extends BasicDBObject {
	private static final long serialVersionUID = -1649382943440783233L;
	
	public final static String CODE = "code";
	public final static String RECORD_TIME = "recordTime";
	public final static String HISTORY_STYLE = "historyStyle";
	
	public enum Style {
		ONE_MIN(0, "1分钟");
		
		private Integer value;
		private String name;

		private Style(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}
	
	public static final String T_OPENPRC = "1";
	public static final String T_CLOSEPRC = "2";
	public static final String T_HIGHPRC = "3";
	public static final String T_LOWPRC = "4";
	public static final String T_VOLUME = "60";
	public static final String T_HOLDINGAMT = "62";
	
	@SuppressWarnings("deprecation")
	@Override
    public String toString(){
        return new Date(Long.parseLong(this.get("recordTime").toString())).toLocaleString() + " - " + JSON.serialize(this);
    }
}