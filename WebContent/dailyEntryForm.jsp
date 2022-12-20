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
		<script defer src="dailyEntryForm.js"></script>
	</head>
	<body>
	
		<jsp:include page="header.jsp"/>
	
		<main>
		
			<section class="py-5 text-center container">
					<div class="col-lg-6 col-md-8 mx-auto">
						<p class="lead text-muted">Submit details about your day</p>
					</div>
			</section>

			<form class="py-5 bg-light container" action="DailyEntryFormServlet" method="POST" id="entryForm">
				<h4 class="mb-3">Meals</h4>
				<div class="mb-3">
					<label for="mealCalories" class="form-label">Calories</label>
					<input type="number" class="form-control" id="mealCalories" name="mealCalories">
				</div>
				
				<hr class="my-4">
				
				<h4 class="mb-3">Exercise</h4>
				<div class="mb-3">
					<label for="exerciseCalories" class="form-label">Calories burned</label>
					<input type="number" class="form-control" id="exerciseCalories" name="exerciseCalories">
				</div>
				<div class="mb-3">
					<label for="exerciseTime" class="form-label">Time exercising (minutes)</label>
					<input type="number" class="form-control" id="exerciseTime" name="exerciseTime">
				</div>
				<div class="mb-3">
					<label for="exerciseSteps" class="form-label">Step count</label>
					<input type="number" class="form-control" id="exerciseSteps" name="exerciseSteps">
				</div>
				
				<hr class="my-4">
				
				<h4 class="mb-3">Work</h4>
				<div class="mb-3">
					<label for="workTime" class="form-label">Time worked (hours)</label>
					<input type="number" class="form-control" id="workTime" name="workTime">
				</div>
				<div class="mb-3">
					<label for="workStress" class="form-label">How stressful was work?</label>
		            <select class="form-select" id="workStress" name="workStress">
			            <option value="">Choose...</option>
			            <option value="1">Not very stressful</option>
			            <option value="2">Mildly stressful</option>
			            <option value="3">Very stressful</option>
		            </select>
		            <div class="invalid-feedback">Please select an option.</div>
	            </div>
				
				<hr class="my-4">
				
				<h4 class="mb-3">Sleep</h4>
				<div class="mb-3">
					<label for="sleepTime" class="form-label">Time slept (hours)</label>
					<input type="number" class="form-control" id="sleepTime" name="sleepTime">
				</div>
				<div class="mb-3">
					<label for="sleepRestfulness" class="form-label">How restful was your sleep?</label>
		            <select class="form-select" id="sleepRestfulness" name="sleepRestfulness">
			            <option value="">Choose...</option>
			            <option value="1">Not very restful</option>
			            <option value="2">Mildly restful</option>
			            <option value="3">Very restful</option>
		            </select>
		            <div class="invalid-feedback">Please select an option.</div>
	            </div>
				
				<hr class="my-4">
				
				<h4 class="mb-3">Meditation</h4>
				<div class="mb-3">
					<label for="meditationTime" class="form-label">Time (minutes)</label>
					<input type="number" class="form-control" id="meditationTime" name="meditationTime">
				</div>
				
				<div class="container" id="errorElement"></div>
								
				<div class="text-center py-5">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
				<div class="text-center">
					<a href="/HLSP/dashboard.jsp">Cancel</a>
				</div>
			</form>

		</main>
	
		<jsp:include page="footer.jsp"/>
	
	
		<script src="/docs/5.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous"></script>
	</body>
</html>