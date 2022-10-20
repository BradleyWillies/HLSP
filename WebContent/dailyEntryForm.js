const errorElement = document.getElementById('errorElement')
const form = document.getElementById('entryForm')
const mealCalories = document.getElementById('mealCalories')
const exerciseCalories = document.getElementById('exerciseCalories')
const exerciseTime = document.getElementById('exerciseTime')
const exerciseSteps = document.getElementById('exerciseSteps')
const workTime = document.getElementById('workTime')
const sleepTime = document.getElementById('sleepTime')
const meditationTime = document.getElementById('meditationTime')

const minutesInDay = 1440
const hoursInDay = 24

form.addEventListener('submit', (e) => {
	let errorMessages = []
	
	if (mealCalories.value < 0) {
		errorMessages.push('Meals - Calories must be a positive number')
	}
	
	if (exerciseCalories.value < 0) {
		errorMessages.push('Exercise - Calories burned must be a positive number')
	}
	
	if (exerciseTime.value < 0) {
		errorMessages.push('Exercise - Time exercising must be a positive number')
	}
	
	if (exerciseTime.value > minutesInDay) {
		errorMessages.push('Exercise - Time must not exceed ' + minutesInDay + ', since this is the total minutes in a day')
	}
	
	if (exerciseSteps.value < 0) {
		errorMessages.push('Exercise - Step count must be a positive number')
	}
	
	if (workTime.value < 0) {
		errorMessages.push('Work - Time worked must be a positive number')
	}
	
	if (workTime.value > hoursInDay) {
		errorMessages.push('Work - Time worked must not exceed ' + hoursInDay + ', since this is the total hours in a day')
	}
	
	if (sleepTime.value < 0) {
		errorMessages.push('Sleep - Time slept must be a positive number')
	}
	
	if (sleepTime.value > hoursInDay) {
		errorMessages.push('Sleep - Time slept must not exceed ' + hoursInDay + ', since this is the total hours in a day')
	}
	
	if (meditationTime.value < 0) {
		errorMessages.push('Meditation - Time must be a positive number')
	}
	
	if (meditationTime.value > minutesInDay) {
		errorMessages.push('Meditation - Time must not exceed ' + minutesInDay + ', since this is the total minutes in a day')
	}
	
	if (errorMessages.length > 0) {
    	e.preventDefault()
		
		for (let i = 0; i < errorMessages.length; i++) {
			errorMessages[i] = '<div class="alert alert-danger" role="alert">' + errorMessages[i] + '</div>'
		}
		
    	errorElement.innerHTML = errorMessages.join('\n')
  	}
})