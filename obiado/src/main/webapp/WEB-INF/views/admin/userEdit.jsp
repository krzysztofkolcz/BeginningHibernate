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

                <form:form modelAttribute="userCommand"  method="post" class="form-horizontal">

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="firstName">First Name</label>
                        <form:input class="form-control" id="firstName" path="firstName" name="firstName" value="" />
                        <form:errors path="firstName" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="lastName">Last Name</label>
                        <form:input class="form-control" id="lastName" path="lastName" name="lastName" value="" />
                        <form:errors path="lastName" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="email">Email</label>
                        <form:input class="form-control" id="email" path="email" name="email" value="" />
                        <form:errors path="email" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password">Password</label>
                        <form:input class="form-control" id="password" path="password" name="password" value="" />
                        <form:errors path="password" element="div" />
                    </div>


                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="matchingPassword">Repeat password</label>
                        <form:input class="form-control" id="matchingPassword" path="matchingPassword" name="matchingPassword" value="" />
                        <form:errors path="matchingPassword" element="div" />
                        <form:errors element="div" />
                    </div>


                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="userProfiles">Roles</label>
                        <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="userProfiles" class="help-inline"/>
                        </div>
                    </div>

                    <div class="input-group input-sm">
                        <button type="submit" class="btn btn-default" >Register</button>
                    </div>

                </form:form>

        </div><!-- col-sm-9 --> 

      </div><!-- row -->
    </div><!-- container-fluid -->
  <jsp:include page="../common/footer.jsp"/>
  </body>
</html>
