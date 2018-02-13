<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="Product List" scope="request"/>
<html>
<jsp:include page="../common/header.jsp"/>
<body>
<div class="container">
<spring:url value="/questions/add" var="questionAdd" />
<a class="btn btn-info" href=${questionAdd}>Add Question</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>#</th>
                <th>Question</th>
                <th>Right Answer</th>
                <th>Commandw</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty questionList}">
                <c:forEach var="question" items="${questionList}">
                    <spring:url value="/questions/${question.id}" var="questionDetails" />
                    <spring:url value="/questions/aaa/${question.id}/delete" var="questionDelete" />
                    <spring:url value="/questions/${question.id}/update" var="questionUpdate" />
                    <tr>
                        <td>${question.id}</td>
                        <td>${question.question}</td>
                        <td>${question.rightAnswer}</td>
                        <td>
                            <input class="answerInput" id="answerInput${question.id}"></input>
                            <input class="answerQuestionId" id="answerQuestionId${question.id}" type="hidden"></input>
                            <a class="answerInputBtn btn btn-pimary" data-id="${question.id}" id="answerInputBtn${question.id}" class="btn btn-primary">Update</a>
                            <c:forEach var="answer" items="${question.answers}">
                            <p> $answer.answer </p>
                            </c:forEach>
                        </td>
                        <td>
                            <a class="btn btn-info" href=${questionDetails}>Details</a>
                            <form class="myform" action="${questionDelete}" method="POST">
                                <input id="id" type="hidden" value="${question.id}" />
                                <button type="submit" class="btn btn-danger">Delete</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <a class="btn btn-primary" href=${questionUpdate}>Update</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
