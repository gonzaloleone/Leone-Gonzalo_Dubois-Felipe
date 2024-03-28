function fetchPatient("/pacientes") {
    const patientInfoDiv = document.getElementById('patientInfo');
    const loaderDiv = document.getElementById('loader');
    const errorMessageDiv = document.getElementById('error-message');

    // Mostrar el indicador de carga
    loaderDiv.style.display = 'block';
    errorMessageDiv.style.display = 'none';

    fetch('http://localhost:8081/pacientes/registrar')
    .then(response => {
        // Ocultar el indicador de carga
        loaderDiv.style.display = 'none';

        // Si la respuesta no es exitosa, lanzar un error
        if (!response.ok) {
            throw new Error('Error al obtener la información del paciente.');
        }
        return response.json();
    })
    .then(data => displayPatient(data))
    .catch(error => {
        // Ocultar el indicador de carga y mostrar el mensaje de error
        loaderDiv.style.display = 'none';
        errorMessageDiv.style.display = 'block';
        errorMessageDiv.textContent = error.message;
        console.error('Error al obtener la información del paciente:', error);
    });
}

// Muestra info del paciente
function displayPatient(patient) {
    const patientInfo = document.getElementById('patientInfo');
    const errorMessageDiv = document.getElementById('error-message');

    // Para ocultar  mensaje de error
    errorMessageDiv.style.display = 'none';

    const patientData = `
        <p><strong>Nombre:</strong> ${patient.name}</p>
        <p><strong>Edad:</strong> ${patient.age}</p>
        <p><strong>Problema:</strong> ${patient.problem}</p>
    `;

    patientInfo.innerHTML = patientData;
}

// Llamar a la función fetchPatient al cargar la página
fetchPatient();