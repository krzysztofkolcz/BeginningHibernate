<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="ProductCategory List" scope="request"/>
<html>
  <jsp:include page="../common/header.jsp"/>
  <body>
    <jsp:include page="navTop.jsp"/>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="navLeft.jsp"/>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
          <h1 class="page-header">Dashboard</h1> 
          <h2 class="sub-header">Section title</h2> 
          <div class="table-responsive"> 
            <table class="table table-striped"> 
              <thead> 
                <tr> 
                  <th>#</th> 
                  <th>Name</th> 
                  <th width="100"></th> <!-- Edit -->
                  <th width="100"></th> <!-- Delete -->
                </tr> 
              </thead> 
              <tbody> 

              <c:if test="${not empty productCategoryList}">
                  <c:forEach var="productCategory" items="${productCategoryList}">
                  <tr> 
                    <td>${productCategory.id}</td> 
                    <td>${productCategory.name}</td> 
                    <td>
                        <a class="btn btn-default" href="<c:url value="/admin/edit-productCategory-${productCategory.id}" />" role="button">Edit</a>
                    </td>
                    <td>
                        <a class="btn btn-default" href="<c:url value="/admin/delete-productCategory-${productCategory.id}" />" role="button">Delete</a>
                    </td>
                  </tr> 
                  </c:forEach>
              </c:if>
              </tbody> 
            </table> 
          </div><!-- table-responsive --> 
        </div><!-- col-sm-9 --> 

      </div><!-- row -->
    </div><!-- container-fluid -->
  <jsp:include page="../common/footer.jsp"/>
  </body>
</html>
