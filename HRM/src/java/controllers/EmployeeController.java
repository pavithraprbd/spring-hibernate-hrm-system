/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.EmployeeDAO;
import daos.RoleDAO;
import daos.TaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Employee;
import models.Role;
import models.Task;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Maps to route employee.htm
 * @author pavithra prabodha
 */
public class EmployeeController implements Controller {

    /**
     * Handle  requests
     * @param hsr
     * @param hsr1
     * @return
     * @throws Exception 
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("employees");
        String method = hsr.getMethod();
        if (method == "POST") {
            method = hsr.getParameter("methodhide");
        }
        if((method.equals("POST") || method.equals("PUT"))  ){
        if((hsr.getParameter("name") == null || hsr.getParameter("name").isEmpty()) && hsr.getParameter("form_verification") == null){
             mv.addObject("error", true);
             mv.addObject("error_message", "Please enter name");
        }else if((hsr.getParameter("role") == null || hsr.getParameter("role").isEmpty() || (hsr.getParameter("role").equals("0") && !method.equals("PUT")  )) && hsr.getParameter("form_verification") == null  ){
             mv.addObject("error", true);
             mv.addObject("error_message", "Please select a role");
        }else{
            
            System.out.println(method+"as dsad sad sad sad sad ad sad sad asd sad sa");
                        if(method.equals("POST")){
                        if(hsr.getParameter("form_verification") == null){
                            
                            save(hsr);  
                            mv.addObject("ok", true);
                            mv.addObject("message", "Employee Added Successfully!");
                        }else{
                         String values[] =  hsr.getParameterValues("tasklist");
                         removeEmployeeTask(hsr);
                         if(values != null){
                           for( String val : values  ){
                              
                                addEmployeeTask(hsr,Integer.parseInt(val));
                            }
                         }
                        }
                    }else if(method.equals("PUT")){
                            update(hsr);
                           mv.addObject("ok", true);
                           mv.addObject("message", "Employee Updated Successfully!");
                    }
        }}
            mv = get(mv);
        mv.addObject("page", "employee");
        return mv;
    }

    /**
     * Generate Employee List View
     *
     * @return
     */
    private ModelAndView get(ModelAndView mv) {

//        ModelAndView mv = new ModelAndView("employees");

        try {
            List<Employee> emps = EmployeeDAO.getEmployeeList();
            List<Role> roles = RoleDAO.get();
            List<Task> task = TaskDAO.getTaskList();
            mv.addObject("roles", roles);
            mv.addObject("employees", emps);
            mv.addObject("tasks", task);
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    
    /**
     * Updates employee through PATCH request
     * @param hsr 
     */
    private void update(HttpServletRequest hsr) {
        Employee emp;
        try {
            emp = EmployeeDAO.getEmployee(Integer.parseInt(hsr.getParameter("empid")));
            emp.setName(hsr.getParameter("name"));
            int roleId = Integer.parseInt(hsr.getParameter("role"));
            if (roleId != 0) {
                Role role = new Role();
                role.setId(roleId);
                emp.setRole(role);
            }
            EmployeeDAO.saveOrUpdateEmployee(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Save new Employee
     *
     * @param hsr
     */
    private void save(HttpServletRequest hsr) {
        Employee emp = new Employee();
        System.out.println("asdasdasdasd");
        int roleId = Integer.parseInt(hsr.getParameter("role"));
        if (roleId != 0) {
            Role role = new Role();
            role.setId(roleId);
            emp.setRole(role);
        }
        emp.setName(hsr.getParameter("name"));

        EmployeeDAO.saveOrUpdateEmployee(emp);

    }
    
        /**
     * Add Employee Task from an existing Task
     * @param hsr 
     */
    private void addEmployeeTask(HttpServletRequest hsr,int taskid) {
        Employee emp;
        try {
            
            int taskId = taskid;
            if (taskId != 0) {
                Task task = TaskDAO.getTaskById(taskId);
                emp = EmployeeDAO.getEmployee(Integer.parseInt(hsr.getParameter("empid")));
                task.setEmployee(emp);
                TaskDAO.saveOrUpdateTask(task);
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Remove Employee Tasks
     * @param hsr 
     */
    private void removeEmployeeTask(HttpServletRequest hsr) {
        
        try {
            
           
                TaskDAO.remove_tasks(Integer.parseInt(hsr.getParameter("empid")));
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
