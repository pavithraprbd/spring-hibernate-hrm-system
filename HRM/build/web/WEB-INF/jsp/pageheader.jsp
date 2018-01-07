<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <c:out value="${page}"></c:out></title>
        <link rel="stylesheet" href="https://bootswatch.com/slate/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
      
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
        
        <style>
            #roleform{
                margin-bottom: 50px;
            }
            .submitbtn{
                margin-top:20px; 
            }
            
            .activebtn{
                background-color: #303943!important;
                
            }
            
            .active{
                background-image: -webkit-linear-gradient(#020202, #101112 40%, #141618);
    background-image: -o-linear-gradient(#020202, #101112 40%, #141618);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#020202), color-stop(40%, #101112), to(#141618));
    background-image: linear-gradient(#020202, #101112 40%, #141618);
    background-repeat: no-repeat;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff020202', endColorstr='#ff141618', GradientType=0);
    -webkit-filter: none;
    filter: none;
            }
        </style>
        
    </head>

    <body>


<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Ead Assignment</a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a
              <c:if test="${page == 'role'}">
              class="active"
              </c:if>
              href="roles.htm">Roles</a></li>
      <li><a 
             <c:if test="${page == 'task'}">
              class="active"
              </c:if> 
              href="task.htm">Tasks</a></li>
      <li><a 
              <c:if test="${page == 'employee'}">
              class="active"
              </c:if>
              href="employees.htm">Employees</a></li>
    </ul>
  </div>
</nav>

        <div class="container">