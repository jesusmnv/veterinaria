window.onload = () => {
    listarDuenios();
}



let listarDuenios = async () => {

	const url = 'http://localhost:8080/consultations';

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
        <td>${elemento.consultationDate}</td>
        <td>${elemento.appointmentReason}</td>
        <td>

            <i onClick="editarDuenios(${elemento.id})" class="material-icons button edit">edit</i>
            <i onClick="eliminarDuenios(${elemento.id})" class="material-icons button delete">delete</i>
        </td>
        </tr>`;
        contenidoTabla += contenidoFila;
    }

    document.querySelector('#tabla tbody').outerHTML = contenidoTabla;

};

let eliminarDuenios = async (id) => {

	const url = `http://localhost:8080/consultations/${id}`;

	const opcionesFetch = {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	};

	const peticion = await fetch(url, opcionesFetch);

    listarDuenios();

};

let idEditar;

let editarDuenios = async (id) => {
    
    mostrarFormulario();

    idEditar = id;

    const url = `http://localhost:8080/consultations/${id}`;

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
    document.getElementById("consultationDateU").value = obj.consultationDate; 
    document.getElementById("appointmentReasonU").value = obj.appointmentReason; 
    
      

    let btnUpdate = document.getElementById('btnUpdate');
    
    
    
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


    
    const url = `http://localhost:8080/consultations/${id}`;

    const opcionesFetch = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    const peticion = await fetch(url, opcionesFetch);

    listarDuenios();


};

function mostrarFormulario() {
    let formulario = document.getElementById("formulario").style.visibility = 'visible';
}