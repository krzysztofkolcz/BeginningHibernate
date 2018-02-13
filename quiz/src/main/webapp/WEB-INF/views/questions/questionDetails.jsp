<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="Product List" scope="request"/>
<html>
<jsp:include page="../common/header.jsp"/>
<body>
    <main role="main">
        <section class="jumbotron text-center">
            <div class="container">
                <div class="row">
                    <div class="col-sm">
                        <p>
                            ${question.id}
                        </p>
                    </div>
                    <div class="col-sm">
                        <p>
                            ${question.question}
                        </p>
                    </div>
                    <div class="col-sm">
                        <p>
                            ${question.rightAnswer}
                        </p>
                    </div>
                </div>
            </div>
        </section>
    </main>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
