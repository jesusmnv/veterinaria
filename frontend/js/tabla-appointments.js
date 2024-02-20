window.onload = () => {
    listarCitas();
}



let listarCitas = async () => {

	const url = 'http://localhost:8080/appointments';

	const opcionesFetch = {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	};

	const peticion = await fetch(url, opcionesFetch);

    const listado = await peticion.json();

    let contenidoTabla = '';
    for (let elemento of listado) {
        let contenidoFila = `<tr>
        <td>${elemento.id}</td>
        <td>${elemento.appointmentDate}</td>
        <td>${elemento.appointmentTime}</td>
        <td>${elemento.firstAppointment}</td>
        <td>${elemento.appointmentReason}</td>
        <td>



            <i onClick="editarCitas(${elemento.id})" class="material-icons button edit">edit</i>
            <i onClick="eliminarCitas(${elemento.id})" class="material-icons button delete">delete</i>
        </td>
        </tr>`;
        contenidoTabla += contenidoFila;
    }

    document.querySelector('#tabla tbody').outerHTML = contenidoTabla;

};

let eliminarCitas = async (id) => {

	const url = `http://localhost:8080/appointments/${id}`;

	const opcionesFetch = {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	};

	const peticion = await fetch(url, opcionesFetch);

    listarCitas();

};

let idEditar;

let editarCitas = async (id) => {
    
    mostrarFormulario();

    idEditar = id;

    const url = `http://localhost:8080/appointments/${id}`;

	const opcionesFetch = {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	};

	const peticion = await fetch(url, opcionesFetch);

    const obj = await peticion.json();

    console.log(obj)

    document.getElementById("appointmentDateU").value = obj.appointmentDate; 
    document.getElementById("appointmentTimeU").value = obj.appointmentTime; 
    document.getElementById("firstAppointmentU").value = obj.firstAppointment; 
    document.getElementById("appointmentReasonU").value = obj.appointmentReason;
       
    
};


btnUpdate.addEventListener('click',  e =>  {
        e.preventDefault();
        aplicarActualizacion(idEditar);
    });

let aplicarActualizacion = async (id) => {
    const formData = new FormData(formU);
    const data = Object.fromEntries(formData);
    data.id = id;
    console.log(data);


    
    const url = `http://localhost:8080/appointments/${id}`;

    const opcionesFetch = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    const peticion = await fetch(url, opcionesFetch);

    listarCitas();


};

function mostrarFormulario() {
    let formulario = document.getElementById("formulario").style.visibility = 'visible';
}