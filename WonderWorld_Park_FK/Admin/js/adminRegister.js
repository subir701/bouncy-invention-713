document.querySelector('form').addEventListener('submit', adminSignup)

function adminSignup(event) {
	event.preventDefault()

	let name = document.getElementById('username').value
	let email = document.getElementById('email').value
	let password = document.getElementById('password').value
	let mobile = document.getElementById('mobile').value
	let address = document.getElementById('Address').value

	let obj = {
		username: name,
		email: email,
		password: password,
		address: address,
		mobileNumber: mobile,
	}

	userSignUpFun(obj)
}

async function userSignUpFun(obj) {
	try {
		let res = await fetch('http://localhost:8888/admin/registerAdmin', {
			method: 'POST',
			body: JSON.stringify(obj),
			headers: {
				'Content-Type': 'application/json',
			},
		})

		if (res.ok) {
			let data = await res.json()
			console.log('Admin created successfully:', data)
			alert('Admin created successfully')
			window.location.href = 'adminLogin.html'
		} else {
			let data = await res.json()
			console.log('Error creating admin:', data)
			alert(data.message) // Display the error message sent by the server
		}
	} catch (error) {
		console.error('Error:', error)
		alert('An error occurred while creating the admin.')
		window.location.href = '/Frontend/Admin/adminLogin.html'
	}
}
