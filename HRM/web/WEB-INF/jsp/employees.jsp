<%-- 
    Document   : employees
    Created on : Oct 25, 2017, 1:19:54 AM
    Author     : pavithra prabodha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="pageheader.jsp" %>
<div style="padding: 30px;" class="text-center">
    <h1>Manage Employees</h1>
    
</div>
<c:if test="${error == true}">
    <div class="alert alert-danger">
     ${error_message}
    </div>
</c:if>
<c:if test="${null != ok && ok == true}">
    <div class="alert alert alert-success">
     ${message}
    </div>
</c:if>
<form id="empform" action="employee.htm" class="text-right"  method="post" >
    <input type="text" placeholder="Enter the name of employee" class="form-control" name="name" id="name" />
    <br>
     <select type="text" name="role" class="form-control">
                                <option value="0" selected>Select the Role</option>
                                <c:forEach var="role" items="${roles}">
                                    <c:if test="${role.getEmployee() == null}">
                                        <option value="${role.getId()}"> <c:out value="${role.getTitle()}"></c:out> </option>
                                    </c:if>
                                </c:forEach>
                            </select>
     <input type="hidden" id="methodhide" name="methodhide" value="POST">
     <input type="hidden" name="empid" id="empid" value="">
    <input class="btn btn-primary submitbtn" id="submitform" type="submit" value="Add employee" />
    
    
 
</form>
<br><br>
<a href="../../../../hrm-java-spring-hibernate-master/hrm-java-spring-hibernate-master/web/WEB-INF/jsp/roles.jsp"></a>


 <div class="">       
     
   

     
  <table class="table">
    <thead>
      <tr>
        <th style="width:5%">#</th>
        <th style="width:15%">Employee name</th>
        <th style="width:30%">Employee role</th>
        <th style="width:50%">Action</th>
       
      </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td> <c:out value="${employee.getId()}"></c:out> </td>
            <td> <c:out value="${employee.getName()}"></c:out> </td>
             <td>  <c:out value="${employee.getRole().getTitle()}"></c:out> </td>
                <td>  
                    <div class="row">
                        <div class="col-xs-6">
                            <form method="post" id="task_form${employee.getId()}">
                                <input type="hidden" name="empid" id="empid" value="${employee.getId()}">
                            <input type="hidden" name="form_verification"  value="true">
                             <input type="hidden"  id="methodhide" name="methodhide" value="POST">
                                 <select iduser="${employee.getId()}" name="tasklist" id="tasklist" class="selectpicker tasklist" multiple>
                                     <c:forEach var="task" items="${tasks}">
                                    <c:if test="${task.getEmployee() == null || task.getEmployee().getId() == employee.getId() }">
                                        
                                        <option 
                                            <c:if test="${task.getEmployee().getId() == employee.getId()}">
                                                selected
                                             </c:if>
                                            value="${task.getId()}"> <c:out value="${task.getDescription()}"></c:out> </option>
                                    </c:if>
                                    </c:forEach>
                                  </select>
                            </form>
                            
                                 
                        </div>
                            <div class="col-xs-6">
                                 <button role="${employee.getRole().getTitle()}" nameval="${employee.getName()}"  id="${employee.getId()}"  style="width:100%" type="button" class="editbtn  btn btn-primary" 
                            > Edit </button>      
                            </div>

                        
                    </div>
                    
                  
                     
                     
                            
            </td>
           
        </tr>
    </c:forEach>
    </tbody>
  </table>
  </div>



<script >
    
   
   $(document).ready(function(){
      
       $(".tasklist").change(function(){
          
               $("#task_form"+$(this).attr("iduser")).submit();
           
       })
       
               $('.selectpicker').selectpicker({
  style: 'btn-info',
  size: 4,
  noneSelectedText:"No task selected"
});
       
    $(".editbtn").click(function(){
        


        
        $(this).toggleClass("activebtn")
        if($(this).hasClass("activebtn")){
          $(".editbtn").removeClass("activebtn");
          $(this).toggleClass("activebtn")
                 $(".editbtn").html("Edit");
                $(this).html("Cancel Edit");
                $("#methodhide").val("PUT");
                $("#empid").val($(this).attr("id"));
                document.getElementsByName('role')[0].options[0].innerHTML = $(this).attr("role");
                $("#name").val($(this).attr("nameval"))
                $("#submitform").val("Edit")
            
        }else{
            
                            $(this).html("Edit");
                             $("#methodhide").val("POST");
                             $("#empid").val("");
                             $("#name").val("");
                              document.getElementsByName('role')[0].options[0].innerHTML = "Select the role";
                              $("#submitform").val("Add")      

        }
        
    });
});
    
</script>


<%@include file="pagefooter.jsp" %>