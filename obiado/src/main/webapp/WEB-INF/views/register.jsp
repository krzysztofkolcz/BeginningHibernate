<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
    <jsp:include page="common/header.jsp"/>
    <body>
        <div id="mainWrapper">
            <div class="registration-container">
                <div class="registration-card">
                    <div class="registration-form">
                        <c:url var="registrationUrl" value="/registration" />
                        <form:form modelAttribute="userCommand"  method="post" class="form-horizontal"><!-- action="${registrationUrl}" -->


                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="firstName">First Name</label>
                                <form:input class="form-control" id="firstName" path="firstName" name="firstName" value="" />
                                <form:errors path="firstName" element="div"/>
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="lastName">Last Name</label>
                                <form:input class="form-control" id="lastName" path="lastName" name="lastName" value="" />
                                <form:errors path="lastName" element="div"/>
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="email">Email</label>
                                <form:input class="form-control" id="email" path="email" name="email" value="" />
                                <form:errors path="email" element="div"/>
                            </div>

                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password">Password</label>
                                <form:input class="form-control" id="password" path="password" name="password" value="" />
                                <form:errors path="password" element="div"/>
                            </div>


                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="matchingPassword">Repeat password</label>
                                <form:input class="form-control" id="matchingPassword" path="matchingPassword" name="matchingPassword" value="" />
                                <form:errors element="div" />
                            </div>

                            <div class="input-group input-sm">
                                <button type="submit" class="btn btn-default" >Register</button>
                            </div>

                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    <jsp:include page="common/footer.jsp"/>
    </body>
</html>
