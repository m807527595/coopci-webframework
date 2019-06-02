package gubo.db.fixtures;

import gubo.db.ISimplePoJo;
import gubo.db.SimplePoJoDAO;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	向数据库灌基础数据的工具类。 
 * 
 **/
public class FixtureUtils {
	public static Logger logger = LoggerFactory.getLogger(FixtureUtils.class);
	
	/**
	 * 	把pojo插入数据库，如果有dup key，则忽略。
	 *  会抛出 dup key以外的异常。
	 **/
	public static void sync(Connection dbconn, ISimplePoJo pojo) throws Exception {
		try {
			SimplePoJoDAO dao = new SimplePoJoDAO(pojo.getClass());
			dao.insert(dbconn, pojo);
		} catch (Exception ex) {
			if (ex.getMessage().startsWith("Duplicate entry ")) {
				return;
			}
			logger.error("", ex);
		}
	}

	/**
	 *	按 unique key 获取PK。 
	 **/
	public static Object getPk(Connection dbconn, Class<? extends ISimplePoJo> clazz, Object... keyNamesAndvalues)
			throws Exception {
		SimplePoJoDAO dao = new SimplePoJoDAO(clazz);
		ISimplePoJo pojo = dao.loadPoJoByUniqueKey(dbconn, (String) keyNamesAndvalues[0], keyNamesAndvalues[1]);

		return pojo.getPk();
	}
}
