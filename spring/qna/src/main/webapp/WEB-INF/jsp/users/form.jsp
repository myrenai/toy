<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
				<form:form modelAttribute="user" cssClass="form-horizontal" action="/qna/users" method="post">
					<div class="control-group">
						<label class="control-label" for="userId">User Id</label>
						<div class="controls">
							<form:input path="userId" />
							<form:errors path="userId" cssClass="error" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="password">Password</label>
						<div class="controls">
                            <form:input path="password" />
                            <form:errors path="userId" cssClass="error" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="name">Name</label>
						<div class="controls">
							<form:input path="name" />
							<form:errors path="name" cssClass="error" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="email">Email</label>
						<div class="controls">
							<form:input path="email" />
							<form:errors path="email" cssClass="error" />
						</div>
					</div>
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