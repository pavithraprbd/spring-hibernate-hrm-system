/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoleDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author pavithra prabodha
 */
public class RoleController implements Controller{
  
    @Override
 public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    ModelAndView mv = null;
    String method = hsr.getMethod();
    if (method == "POST") {
        method = hsr.getParameter("methodhide");
    }
    if (method.equals("POST") || method.equals("PUT")) {
        if (hsr.getParameter("rolename") == null || hsr.getParameter("rolename").isEmpty()) {
         mv = getRoles();
         mv.addObject("error", true);
        } else {
         if (method.equals("POST")) {
          System.out.println("post");
          saveRole(hsr);
          mv = getRoles();
          mv.addObject("ok", true);
          mv.addObject("message", "Role Added Successfully");
         } else if (method.equals("PUT")) {
          updateRole(hsr);
          mv = getRoles();
          mv.addObject("ok", true);
          mv.addObject("message", "Role Edited Successfully");
         }
        }
    }
    if (method.equals("GET")) {
        mv = getRoles();
        mv.addObject("ok", false);

    } else if (method.equals("DELETE")) {
        System.out.println("delete rejected");
        mv = getRoles();
    }
    mv.addObject("page", "role");
    return mv;
 }

    /**
     * Returns Role List View
     * @return 
     */
    private ModelAndView getRoles() {

        ModelAndView mv = new ModelAndView("roles");

        try {
            List<Role> roles = RoleDAO.get();

            mv.addObject("All_roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * Saves a Role through POST
     * @param hsr 
     */
    private void saveRole(HttpServletRequest hsr) {
        System.out.println("saveRole");
        Role role = new Role();
        role.setTitle(hsr.getParameter("rolename"));
        RoleDAO.saveOrUpdateRole(role);

    }

    /**
     * Updates a Role through PUT
     * @param hsr 
     */
    private void updateRole(HttpServletRequest hsr) {

        Role role = new Role();
        role.setTitle(hsr.getParameter("rolename"));
        try{
             role.setId(Integer.parseInt(hsr.getParameter("roleid")));
        }catch(Exception e){
            System.out.println("error updating will insert as a new record");
        }
        RoleDAO.saveOrUpdateRole(role);

    }
}
