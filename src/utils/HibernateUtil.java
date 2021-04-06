package utils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.mem.model.MemVO;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
//	static {
//		try {
//			sessionFactory = new Configuration().configure().buildSessionFactory();
//		} catch (Throwable e) {
//			// TODO: handle exception
//		}
//	}
//	
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//	
//	public static void shutdown() {
//		getSessionFactory().close();
//	}
	
	public static void saveMemVO(MemVO memVO) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(memVO);
			tx.commit();
			session.close();
			
			System.out.println("新增資料OK!請先用MySQL觀看結果！");
			
		} catch (Throwable e) {
			 System.err.println("建立SessionFactory失敗>>>" + e);
	         throw new ExceptionInInitializerError(e);
		}
	}
	
	public static void updateMemVO(MemVO memVO) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(memVO);
			tx.commit();
			session.close();
			
			System.out.println("修改資料OK!請先用MySQL觀看結果！");
			
		} catch (Throwable e) {
			 System.err.println("建立SessionFactory失敗>>>" + e);
	         throw new ExceptionInInitializerError(e);
		}
	}
	
	public static void deleteMemVO(MemVO memVO) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.delete(memVO);
			tx.commit();
			session.close();
			
			System.out.println("修改資料OK!請先用MySQL觀看結果！");
			
		} catch (Throwable e) {
			 System.err.println("建立SessionFactory失敗>>>" + e);
	         throw new ExceptionInInitializerError(e);
		}
	}
	
	public static List<MemVO> getAll() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MemVO.class);
			Transaction tx = session.beginTransaction();
			
			List<MemVO> list = criteria.list();
			
			tx.commit();
			session.close();
			
			System.out.println("修改資料OK!請先用MySQL觀看結果！");
			
			return list;
			
		} catch (Throwable e) {
			 System.err.println("建立SessionFactory失敗>>>" + e);
	         throw new ExceptionInInitializerError(e);
		}
	}
	
	public static List<MemVO> getOneByUsername(MemVO memVO) {
		try {
			
			sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MemVO.class);
			//設定查詢條件
	        criteria.add(Restrictions.eq("username", memVO.getUsername()));
//	        criteria.add(Restrictions.eq("email", "Kim@hotmail.com"));
			
			Transaction tx = session.beginTransaction();
			
			List<MemVO> list = criteria.list();
			
			tx.commit();
			session.close();
			
			System.out.println("修改資料OK!請先用MySQL觀看結果！");
			
			return list;
			
		} catch (Throwable e) {
			 System.err.println("建立SessionFactory失敗>>>" + e);
	         throw new ExceptionInInitializerError(e);
		}
	}
}
