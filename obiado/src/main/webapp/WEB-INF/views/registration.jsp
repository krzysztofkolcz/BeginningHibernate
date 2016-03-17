<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registration page</title>
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>
 
    <body>
        <div id="mainWrapper">
            <div class="registration-container">
                <div class="registration-card">
                    <div class="registration-form">
                        <c:url var="registrationUrl" value="/registration" />
                        <form:form modelAttribute="userCommand" action="${registrationUrl}" method="post" class="form-horizontal">

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="userName"><i class="fa fa-user"></i></label>
                                <form:input class="form-control" id="userName" path="userName" name="userName" value="" />
                                <form:errors path="userName" />
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="firstName"><i class="fa fa-user"></i></label>
                                <form:input class="form-control" id="firstName" path="firstName" name="firstName" value="" />
                                <form:errors path="firstName" />
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="lastName"><i class="fa fa-user"></i></label>
                                <form:input class="form-control" id="lastName" path="lastName" name="lastName" value="" />
                                <form:errors path="lastName" />
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="email"><i class="fa fa-user"></i></label>
                                <form:input class="form-control" id="email" path="email" name="email" value="" />
                                <form:errors path="email" />
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-user"></i></label>
                                <form:input class="form-control" id="password" path="password" name="password" value="" />
                                <form:errors path="password" />
                            </div>


                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="matchingPassword"><i class="fa fa-user"></i></label>
                                <form:input class="form-control" id="matchingPassword" path="matchingPassword" name="matchingPassword" value="" />
                                <form:errors path="matchingPassword" />
                            </div>

                        </form:form>
                    </div>
                </div>
            </div>
        </div>
 
    </body>
</html>
