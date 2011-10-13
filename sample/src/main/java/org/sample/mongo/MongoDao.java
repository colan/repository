package org.sample.mongo;

import java.util.ArrayList;
import java.util.List;

import org.sample.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public abstract class MongoDao<T extends DBObject> {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	protected Class<T> entityClass;
	
	private DBCollection dbCollection;

	public abstract MongoSessionFactory getMongoSessionFactory();

	public DBCollection getSession() {
		if(dbCollection == null) {
			dbCollection = getMongoSessionFactory().openSession(entityClass.getSimpleName().toLowerCase());
		}
		return dbCollection;
	}

	public MongoDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public MongoDao(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public final DBCursor find(DBObject ref) {
		return getSession().find(ref);
	}

	public final DBCursor find(DBObject ref, DBObject keys) {
		return getSession().find(ref, keys);
	}

	public final List<DBObject> findAll() {
		List<DBObject> list = new ArrayList<DBObject>();
		DBCursor cursor = getSession().find();
		while(cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	
	public final DBObject findOne() throws MongoException {
		return getSession().findOne();
	}

	
	public final DBObject findOne(DBObject o) throws MongoException {
		return  getSession().findOne(o, null);
	}

	
	public final DBObject findOne(DBObject o, DBObject fields) {
		return  getSession().findOne(o, fields);
	}

	public final Object apply(DBObject o) {
		return getSession().apply(o);
	}

	public final Object apply(DBObject jo, boolean ensureID) {
		return getSession().apply(jo, ensureID);
	}

	public final WriteResult save(DBObject obj) {
		return getSession().save(obj);
	}

	public long count() throws MongoException {
		return getCount(new BasicDBObject(), null);
	}

	public long count(DBObject query) throws MongoException {
		return getCount(query, null);
	}

	public long getCount() throws MongoException {
		return getSession().getCount();
	}

	public long getCount(DBObject query) throws MongoException {
		return getSession().getCount(query);
	}

	public long getCount(DBObject query, DBObject fields) throws MongoException {
		return getSession().getCount(query, fields);
	}

	public long getCount(DBObject query, DBObject fields, long limit, long skip)
			throws MongoException {

		return getSession().getCount(query, fields, limit, skip);
	}

	
	public DBObject group(DBObject key, DBObject cond, DBObject initial,
			String reduce) throws MongoException {
		return  getSession().group(key, cond, initial, reduce);
	}

	@SuppressWarnings("rawtypes")
	public List distinct(String key) {
		return distinct(key, new BasicDBObject());
	}

	@SuppressWarnings("rawtypes")
	public List distinct(String key, DBObject query) {
		return (getSession().distinct(key, query));
	}

	public WriteResult insert(DBObject o, WriteConcern concern)
			throws MongoException {
		return getSession().insert(o, concern);
	}

	public WriteResult insert(DBObject... arr) throws MongoException {
		return getSession().insert(arr);
	}

	public WriteResult insert(List<DBObject> list) throws MongoException {
		return getSession().insert(list);
	}

	public WriteResult update(DBObject q, DBObject o, boolean upsert,
			boolean multi) throws MongoException {
		return getSession().update(q, o, upsert, multi);
	}

	public WriteResult update(DBObject q, DBObject o) throws MongoException {
		return getSession().update(q, o);
	}

	public WriteResult updateMulti(DBObject q, DBObject o)
			throws MongoException {
		return getSession().updateMulti(q, o);
	}

	public WriteResult remove(DBObject o) throws MongoException {
		return getSession().remove(o);
	}

	public final DBCursor find(DBObject ref, DBObject fields, int numToSkip,int batchSize, int options) throws MongoException {
		return getSession().find(ref, fields, numToSkip, batchSize, options);
	}

	public final DBCursor find(DBObject ref, DBObject fields, int numToSkip,int batchSize) {
		return getSession().find(ref, fields, numToSkip, batchSize);
	}

	
	public final DBObject findOne(Object obj) throws MongoException {
		return  getSession().findOne(obj);
	}

	
	public final DBObject findOne(Object obj, DBObject fields) {
		return  getSession().findOne(obj, fields);
	}

	
	public DBObject findAndModify(DBObject query, DBObject fields,
			DBObject sort, boolean remove, DBObject update, boolean returnNew,
			boolean upsert) {
		return  getSession().findAndModify(query, fields, sort, remove, update,
				returnNew, upsert);
	}

	
	public DBObject findAndModify(DBObject query, DBObject sort, DBObject update) {
		return  getSession().findAndModify(query, sort, update);
	}

	
	public DBObject findAndModify(DBObject query, DBObject update) {
		return  getSession().findAndModify(query, update);
	}
	
	
	public DBObject findAndRemove(DBObject query) {
		return getSession().findAndRemove(query);
	}

}
