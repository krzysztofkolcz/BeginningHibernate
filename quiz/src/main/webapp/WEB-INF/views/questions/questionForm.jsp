<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="Product List" scope="request"/>
<html>
<head>
<jsp:include page="../common/header.jsp"/>
</head>
<body>
<div class="container">

    <c:choose>
		<c:when test="${question['new']}">
			<h1>Add User</h1>
			<c:set var="buttonLabel" value="Save" />
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
			<c:set var="buttonLabel" value="Update" />
		</c:otherwise>
	</c:choose>
	<br />

    <spring:url value="/questions" var="questionActionUrl" />

	<form:form  method="post" modelAttribute="question" action="${questionActionUrl}">

        <form:hidden class="form-control" id="id" path="id" name="id" value="" />
        <div class="input-group input-sm">
            <label for="question">Question:</label>
            <form:input class="form-control" id="question" path="question" name="question" value="" />
            <form:errors path="question" element="div"/>
        </div>

        <div class="input-group input-sm">
            <label for="rightAnswer">Right answer:</label>
            <form:input class="form-control" id="rightAnswer" path="rightAnswer" name="rightAnswer" value="" />
            <form:errors path="rightAnswer" element="div"/>
        </div>

        <div class="input-group input-sm">
            <button type="submit" class="btn btn-default" >${buttonLabel}</button>
        </div>
	</form:form>
</div>
</body>
</html>
