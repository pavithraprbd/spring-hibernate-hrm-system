/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;
import models.Task;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Pavithra prabodha
 */
public class TaskDAO {

    /**
     * Get Task List
     * @return 
     */
    public static List getTaskList() {

        List<Task> taskList = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Task");
            taskList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }

        } finally {
            session.close();
        }

        return taskList;

    }

    /**
     * Save or Update Task
     * @param task 
     */
    public static void saveOrUpdateTask(Task task) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(task);
            transaction.commit();

        } catch (HibernateException e) {

            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }

        } finally {
            session.close();
        }

    }

    /**
     * Retrieve Unassigned Tasks as List
     * @return 
     */
    public static List getTaskUnasignedTasks() {

        List<Task> taskList = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Task where employee_id = null");
            taskList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }

        } finally {
            session.close();
        }

        return taskList;

    }

    
        /**
     * remove all tasks from a user
     * @param userid 
     */
    public static void remove_tasks(int id) {
        Transaction transaction = null;
        Session session = null;
        try {
            System.out.println("asd");
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                "update TASKS set employee_id = null where employee_id = "+id);
           
           query.executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }

        } finally {
            session.close();
        }
    }

    
    
    
    
    
    
    
    
    
    /**
     * Get a Task by ID
     * @param id
     * @return
     * @throws Exception 
     */
    public static Task getTaskById(Integer id) throws Exception {

        List<Task> taskList = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Task where id=" + id.toString());
            taskList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }

        } finally {
            session.close();
        }

        if (taskList.equals(null) || taskList.size() == 0) {
            throw new Exception("NOT FOUND");
        }
        return taskList.get(0);

    }
}
