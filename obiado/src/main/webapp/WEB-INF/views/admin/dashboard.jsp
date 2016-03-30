<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="User List" scope="request"/>
<html>
  <jsp:include page="../common/header.jsp"/>
  <body>
    <jsp:include page="navTop.jsp"/>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="navLeft.jsp"/>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
          <h1 class="page-header">Dashboard</h1> 
          <h2 class="sub-header">User Edit</h2> 


        </div><!-- col-sm-9 --> 

      </div><!-- row -->
    </div><!-- container-fluid -->
  <jsp:include page="../common/footer.jsp"/>
  </body>
</html>
