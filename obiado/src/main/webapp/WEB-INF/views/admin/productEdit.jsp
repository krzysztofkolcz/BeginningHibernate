<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="title" value="Product List" scope="request"/>
<html>
  <jsp:include page="../common/header.jsp"/>
  <body>
    <jsp:include page="navTop.jsp"/>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="navLeft.jsp"/>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
          <h1 class="page-header">Dashboard</h1> 
          <h2 class="sub-header">Product Edit</h2> 

                <form:form modelAttribute="productCommand"  method="post" class="form-horizontal">

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="firstName">Name</label>
                        <form:input class="form-control" id="Name" path="Name" name="Name" value="" />
                        <form:errors path="Name" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="price">Price</label>
                        <form:input class="form-control" id="price" path="price" name="price" value="" />
                        <form:errors path="price" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="active">Active</label>
                        <form:input class="form-control" id="active" path="active" name="active" value="" />
                        <form:errors path="active" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="sku">Sku</label>
                        <form:input class="form-control" id="sku" path="sku" name="sku" value="" />
                        <form:errors path="sku" element="div" />
                    </div>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="productCategories">Product Categories</label>
                        <form:select path="productCategories" items="${productCategories}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="productCategories" class="help-inline"/>
                        </div>
                    </div>

                    <div class="input-group input-sm">
                        <button type="submit" class="btn btn-default" >Save</button>
                    </div>

                </form:form>

        </div><!-- col-sm-9 --> 

      </div><!-- row -->
    </div><!-- container-fluid -->
  <jsp:include page="../common/footer.jsp"/>
  </body>
</html>
