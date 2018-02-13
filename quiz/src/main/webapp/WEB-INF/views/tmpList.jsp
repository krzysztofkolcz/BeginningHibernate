<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="Product List" scope="request"/>
<html>
<head>
</head>
<body>

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Pseudo</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty tmpList}">
                <c:forEach var="tmp" items="${tmpList}">
                    <tr>
                        <td>${tmp.id}</td>
                        <td>${tmp.pseudo}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>


