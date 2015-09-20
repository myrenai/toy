<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>TOY Project</title>
<%@ include file="../common/_header.jsp"%>
</head>
<body>
	<%@ include file="../common/_top.jsp"%>
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				User create form
				<form:form modelAttribute="authenticate" cssClass="form-horizontal" action="/qna/users/login" method="post">
					<div class="control-group">
						<label class="control-label" for="userId">User Id</label>
						<div class="controls">
							<form:input path="userId" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="password">Password</label>
						<div class="controls">
                            <form:input path="password" />
						</div>
					</div>
					
					<c:if test="${not empty errorMessage }">
					<div class="control-group">
					   <div class="controls">
					       <div class="error">${errorMessage}</div>
					   </div>
					</div>
					</c:if>
					
					<br>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</div>

				</form:form>

			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>