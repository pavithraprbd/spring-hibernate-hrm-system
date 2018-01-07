/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoleDAO;
import daos.TaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.Task;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Maps to tasks.htm
 * @author pavithra prabodha
 */
public class TaskController implements Controller {

    /**
     * Main Request Handler for this controller
     * @param hsr
     * @param hsr1
     * @return
     * @throws Exception 
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = null;
        String method = hsr.getMethod();
        if (method == "POST") {
            method = hsr.getParameter("methodhide");
        }
        if(method.equals("POST") || method.equals("PUT")){
        if(hsr.getParameter("taskdesc") == null || hsr.getParameter("taskdesc").isEmpty()){
            mv = get();
             mv.addObject("error", true);
        }else{
        if(method.equals("POST")){
            save(hsr);
                mv = get();
                mv.addObject("ok", true);
                mv.addObject("message", "Task Added Successfully!");
        
        }
        else if(method.equals("PUT")){
         update(hsr);
                mv = get();
                mv.addObject("ok", true);
                mv.addObject("message", "Task Edited Successfully!");
        }
        }}
        if(method.equals("GET")){
           mv = get();
                mv.addObject("flag", false);
        }else if(method.equals("DELETE")){
         mv = get();
        }
        mv.addObject("page", "task");
        return mv;
    }

    /**
     * Generate view for Task List
     * @return 
     */
    private ModelAndView get() {

        ModelAndView mv = new ModelAndView("tasks");

        try {
            List<Task> tasks = TaskDAO.getTaskList();
            mv.addObject("tasks", tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * Save Task through POST
     * @param hsr 
     */
    private void save(HttpServletRequest hsr) {
        Task task = new Task();
        task.setDescription(hsr.getParameter("taskdesc"));
        TaskDAO.saveOrUpdateTask(task);
    }

    /**
     * Update Task through PUT
     * @param hsr 
     */
    private void update(HttpServletRequest hsr) {

        Task task = new Task();
        task.setDescription(hsr.getParameter("taskdesc"));
        try{
         task.setId(Integer.parseInt(hsr.getParameter("taskid")));
        }catch(Exception e){
        }
       
        TaskDAO.saveOrUpdateTask(task);

    }
}
