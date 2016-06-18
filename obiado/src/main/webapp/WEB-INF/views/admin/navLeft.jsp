<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-sidebar">
    <li class="active"><a href="<c:url value="/admin/user-list/" />">User List</a>
        <ul>
          <li class="active"><a href="<c:url value="/admin/add-user/" />">Add user</a></li>
        </ul>
    </li>
    <li class="active"><a href="<c:url value="/admin/product-list/" />">Product List</a>
        <ul>
          <li class="active"><a href="<c:url value="/admin/add-product/" />">Add product</a></li>
        </ul>
    </li>
    <li class="active"><a href="<c:url value="/admin/product-category-list/" />">Product Categories</a>
        <ul>
          <li class="active"><a href="<c:url value="/admin/add-product-category/" />">Add product category</a></li>
        </ul>
    </li>
  </ul>
  <%--
  <ul class="nav nav-sidebar">
    <li><a href="">TODO</a></li>
  </ul>
  <ul class="nav nav-sidebar">
    <li><a href="">TODO</a></li>
  </ul>
  --%>
</div>
