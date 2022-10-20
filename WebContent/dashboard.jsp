<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Healthy-Life</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
			crossorigin="anonymous">
			
	</head>
	<body>
	
		<header>
			<div class="navbar navbar-dark bg-dark shadow-sm">
				<div class="container">
					<% out.println("<a href=\"" + request.getContextPath() + "/dashboard.jsp\" class=\"navbar-brand d-flex align-items-center\"><strong>Healthy-Life</strong></a>"); %>
					<button class="btn btn-outline-secondary btn-lg px-4">Logout</button>
				</div>
			</div>
		</header>
	
		<main>
	
			<section class="py-5 text-center container">
				<div class="row py-lg-5">
					<div class="col-lg-6 col-md-8 mx-auto">
						<% out.println("<h1 class=\"fw-light\">Hello " + session.getAttribute("userEmail") + "!</h1>"); %>
						<p class="lead text-muted">Submit a daily lifestyle entry using the button below</p>
						<form  action="DailyEntryFormServlet" method="GET">
						<p>
							<button class="btn btn-primary">Submit entry</button>
						</p>
						</form>
					</div>
				</div>
			</section>
	
			<div class="album py-5 bg-light">
				<div class="container">
				
					<div class="text-center py-5">
						<h3>Daily Statistics</h3>
					</div>
	
					<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="Placeholder: Thumbnail"
									preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
										fill="#55595c"></rect>
									<text x="50%" y="50%" fill="#eceeef" dy=".3em">Graph Placeholder</text></svg>
	
								<div class="card-body">
									<h4>Meals</h4>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Calories consumed: </p>
										<% 
											if (session.getAttribute("mealCalories") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("mealCalories") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="Placeholder: Thumbnail"
									preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
										fill="#55595c"></rect>
									<text x="50%" y="50%" fill="#eceeef" dy=".3em">Graph Placeholder</text></svg>
	
								<div class="card-body">
									<h4>Exercise</h4>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Calories burned: </p>
										<% 
											if (session.getAttribute("exerciseCalories") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("exerciseCalories") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Time exercised: </p>
										<% 
											if (session.getAttribute("exerciseTime") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("exerciseTime") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Step count: </p>
										<% 
											if (session.getAttribute("exerciseSteps") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("exerciseSteps") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="Placeholder: Thumbnail"
									preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
										fill="#55595c"></rect>
									<text x="50%" y="50%" fill="#eceeef" dy=".3em">Graph Placeholder</text></svg>
	
								<div class="card-body">
									<h4>Work</h4>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Time worked: </p>
										<% 
											if (session.getAttribute("workTime") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("workTime") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Stressfulness: </p>
										<% 
											if (session.getAttribute("workStress") != null) {
												switch((Integer) session.getAttribute("workStress")) {
												case 1:
													out.println("<p class=\"fw-bold\">Not very stressful</p>");
													break;
												case 2:
													out.println("<p class=\"fw-bold\">Mildly stressful</p>");
													break;
												case 3:
													out.println("<p class=\"fw-bold\">Very stressful</p>");
													break;
												}
											}
											else
												out.println("<p class=\"fw-bold\">-</p>");
										%>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="Placeholder: Thumbnail"
									preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
										fill="#55595c"></rect>
									<text x="50%" y="50%" fill="#eceeef" dy=".3em">Graph Placeholder</text></svg>
	
								<div class="card-body">
									<h4>Sleep</h4>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Time slept: </p>
										<% 
											if (session.getAttribute("sleepTime") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("sleepTime") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Restfulness: </p>
										<% 
											if (session.getAttribute("sleepRestfulness") != null) {
												switch((Integer) session.getAttribute("sleepRestfulness")) {
												case 1:
													out.println("<p class=\"fw-bold\">Not very restful</p>");
													break;
												case 2:
													out.println("<p class=\"fw-bold\">Mildly restful</p>");
													break;
												case 3:
													out.println("<p class=\"fw-bold\">Very restful</p>");
													break;
												}
											}
											else
												out.println("<p class=\"fw-bold\">-</p>");
										%>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="Placeholder: Thumbnail"
									preserveAspectRatio="xMidYMid slice" focusable="false">
									<title>Placeholder</title><rect width="100%" height="100%"
										fill="#55595c"></rect>
									<text x="50%" y="50%" fill="#eceeef" dy=".3em">Graph Placeholder</text></svg>
	
								<div class="card-body">
									<h4>Meditation</h4>
									<div class="d-flex justify-content-between align-items-center">
										<p class="card-text">Time meditated: </p>
										<% 
											if (session.getAttribute("meditationTime") != null)
												out.println("<p class=\"fw-bold\">" + session.getAttribute("meditationTime") + "</p>");
											else
												out.println("<p class=\"fw-bold\">0</p>");
										%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	
		</main>
	
		<footer class="text-muted py-5">
			<div class="container">
				<p class="text-center mb-1">
					<a href="#">Back to top</a>
				</p>
			</div>
		</footer>
	
	
		<script src="/docs/5.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous"></script>
	</body>
</html>