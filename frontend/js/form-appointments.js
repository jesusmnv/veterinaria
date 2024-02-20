document.addEventListener('DOMContentLoaded', function() {

	let boton = document.getElementById('btnRegistrar');

	boton.addEventListener('click', function(e) {
		e.preventDefault();
		registrarDuenio();
	});
	
	let registrarDuenio = async () => {
	
		const formData = new FormData(form1);
		const data = Object.fromEntries(formData);
	
		const url = 'http://localhost:8080/appointments';
	
		const opcionesFetch = {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		};
	
		const peticion = await fetch(url, opcionesFetch)
			.then(response => {
				if (!response.ok) {
					throw new Error('Error en la solicitud');
				}
				return response.json();
			})
			.then(data => {
				console.log('Registrado correctamente:', data);
			})
			.catch(error => {
				console.error('Error al registrar:', error);
			});
	
	};


});

