<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/qna/jsp">My Toy</a>
            </div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/qna/">Home</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.userId}">
							<li><a href="#">Services</a></li>
							<li><a href="/qna/users/${sessionScope.userId}/form">Settings</a></li>
							<li><a href="/qna/users/logout">Logout</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#">Services</a></li>
							<li><a href="/qna/users/form">Subscription</a></li>
							<li><a href="/qna/users/login/form">Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		<!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>