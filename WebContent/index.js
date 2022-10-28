const errorElement = document.getElementById('errorElement')
const form = document.getElementById('registerForm')
const password = document.getElementById('passwordInput')

const textList = ["exercise", "diet", "sleep cycle", "meditation"];
const cycle = document.getElementById('cycle');
const cycleInterval = 3000;

if(form) {
	form.addEventListener('submit', (e) => {
		let errorMessages = []
		
		let passMinLength = 8
		if (password.value.length < passMinLength) {
			errorMessages.push('Password must be longer than ' + passMinLength + ' characters')
		}
		
		let regex = /^(?=.*[\d])(?=.*[!@#$%^&*])[\w!@#$%^&*]{6,16}$/
		if (!regex.test(password.value)) {
			errorMessages.push('Password must contain at least one number and one special character')
		}
		
		if (errorMessages.length > 0) {
	    	e.preventDefault()
			
			for (let i = 0; i < errorMessages.length; i++) {
				errorMessages[i] = '<div class="alert alert-danger" role="alert">' + errorMessages[i] + '</div>'
			}
			
	    	errorElement.innerHTML = errorMessages.join('\n')
	  	}
	})
}

if(cycle){
	let i = 0;
	const cycleText = () => {
	  cycle.innerHTML = textList[i];
	  i = ++i % textList.length;
	};
	cycleText();
	setInterval(cycleText, cycleInterval);
}