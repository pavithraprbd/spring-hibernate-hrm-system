<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="pageheader.jsp" %>
<div style="padding: 30px;" class="text-center">
    <h1>Manage Roles</h1>
    
</div>

<c:if test="${error == true}">
    <div class="alert alert-danger">
      Please enter the role title
    </div>
</c:if>
<c:if test="${null != ok && ok == true}">
    <div class="alert alert alert-success">
     ${message}
    </div>
</c:if>

<form id="roleform" action="role.htm" class="text-right"  method="post" >
    <textarea placeholder="Enter the title of the role" class="form-control" name="rolename" id="rolename" ></textarea>
     <input type="hidden" id="methodhide" name="methodhide" value="POST">
      <input type="hidden" name="roleid" id="roleid" value="">
    <input class="btn btn-primary submitbtn" id="submitform" type="submit" value="Add role" />
</form>

<a href="../../../../hrm-java-spring-hibernate-master/hrm-java-spring-hibernate-master/web/WEB-INF/jsp/roles.jsp"></a>


 <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr>
        <th style="width:20%">#</th>
        <th style="width:40%">Role</th>
        <th style="width:40%">Action</th>
       
      </tr>
    </thead>
    <tbody>
     <c:forEach var="role" items="${All_roles}">
            <tr>
            <td style="width:20%"> <c:out value="${role.getId()}"></c:out> </td>
            <td style="width:20%"> <c:out value="${role.getTitle()}"></c:out> </td>
                <td style="width:20%">  
                    <button titleval="${role.getTitle()}" id="${role.getId()}"  style="width:100%" type="button" class="editbtn btn btn-primary" 
                            > Edit </button>
            </td >
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
                $("#roleid").val($(this).attr("id"));
                $("#rolename").val($(this).attr("titleval"))
                $("#submitform").val("Edit")
            
        }else{
            
                            $(this).html("Edit");
                             $("#methodhide").val("POST");
                             $("#roleid").val("");
                             $("#rolename").val("");
                              $("#submitform").val("Add")      

        }
        
    });
});
    
</script>


<%@include file="pagefooter.jsp" %>