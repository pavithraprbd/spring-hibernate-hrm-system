<%-- 
    Document   : tasks
    Created on : Oct 24, 2017, 11:03:54 PM
    Author     : pavithra prabodha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="pageheader.jsp" %>
<div style="padding: 30px;" class="text-center">
    <h1>Manage Tasks</h1>
    
</div>

<c:if test="${error == true}">
    <div class="alert alert-danger">
      Please enter the task description
    </div>
</c:if>
<c:if test="${null != ok && ok == true}">
    <div class="alert alert alert-success">
     ${message}
    </div>
</c:if>
<form id="taskform" action="task.htm" class="text-right"  method="post" >
    <textarea placeholder="Enter the Description of task" class="form-control" name="taskdesc" id="taskdesc" ></textarea>
     <input type="hidden" id="methodhide" name="methodhide" value="POST">
      <input type="hidden" name="taskid" id="taskid" value="">
    <input class="btn btn-primary submitbtn" id="submitform" type="submit" value="Add task" />
</form>
<br><br>
<a href="../../../../hrm-java-spring-hibernate-master/hrm-java-spring-hibernate-master/web/WEB-INF/jsp/roles.jsp"></a>


 <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr>
        <th style="width:20%">#</th>
        <th style="width:40%">Task description</th>
        <th style="width:40%">Action</th>
       
      </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td> <c:out value="${task.getId()}"></c:out> </td>
            <td> <c:out value="${task.getDescription()}"></c:out> </td>
                <td>  
                    <button descr="${task.getDescription()}" id="${task.getId()}"  style="width:100%" type="button" class="editbtn btn btn-primary" 
                            > Edit </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
  </table>
  </div>



<script >
    
    function addnew(){
       
        
    }
    
    $(document).ready(function(){
    $(".editbtn").click(function(){
        
        
        
        $(this).toggleClass("activebtn")
        if($(this).hasClass("activebtn")){
          $(".editbtn").removeClass("activebtn");
          $(this).toggleClass("activebtn")
                 $(".editbtn").html("Edit");
                $(this).html("Cancel Edit");
                $("#methodhide").val("PUT");
                $("#taskid").val($(this).attr("id"));
                $("#taskdesc").val($(this).attr("descr"))
                $("#submitform").val("Edit")
            
        }else{
            
                            $(this).html("Edit");
                             $("#methodhide").val("POST");
                             $("#taskid").val("");
                             $("#taskdesc").val("");
                              $("#submitform").val("Add")      

        }
        
    });
});
    
</script>


<%@include file="pagefooter.jsp" %>