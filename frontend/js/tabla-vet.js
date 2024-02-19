window.onload = () => {
    listarVeterinarios();
}

let listarVeterinarios = async () => {

	const url = 'http://localhost:8080/vets';

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
        <td>${elemento.nameVet}</td>
        <td>${elemento.surnameVet}</td>
        <td>${elemento.specialty}</td>
        <td>${elemento.email}</td>
        <td>${elemento.cellphone}</td>
        <td>
            <i onClick="editarVeterinario(${elemento.id})" class="material-icons button edit">edit</i>
            <i onClick="eliminarVeterinario(${elemento.id})" class="material-icons button delete">delete</i>
        </td>
        </tr>`;
        contenidoTabla += contenidoFila;
    }

    document.querySelector('#tabla tbody').outerHTML = contenidoTabla;

};

let eliminarVeterinario = async (id) => {

	const url = `http://localhost:8080/vets/${id}`;

	const opcionesFetch = {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	};

	const peticion = await fetch(url, opcionesFetch);

    listarVeterinarios();

};

let idEditar;

let editarVeterinario = async (id) => {
    
    mostrarFormulario();

    idEditar = id;

    const url = `http://localhost:8080/vets/${id}`;

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

    document.getElementById("nameU").value = obj.nameVet; 
    document.getElementById("surnameU").value = obj.surnameVet; 
    document.getElementById("maternalSurnameU").value = obj.maternalSurnameVet; 
    document.getElementById("emailU").value = obj.email; 
    document.getElementById("birthdateU").value = obj.birthdate;
    document.getElementById("cellphoneU").value = obj.cellphone; 
    document.getElementById("specialtyU").value = obj.specialty; 
    document.getElementById("entryTimeU").value = obj.entryTime; 
    document.getElementById("exitTimeU").value = obj.exitTime;

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

    const url = `http://localhost:8080/vets/${id}`;

    const opcionesFetch = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    const peticion = await fetch(url, opcionesFetch);

    listarVeterinarios();


};

function mostrarFormulario() {
    let formulario = document.getElementById("formulario").style.visibility = 'visible';
}