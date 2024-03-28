window.addEventListener('load', function(){
    let formRegistrar = document.querySelector(".registrarForm");
    let formBuscar = document.querySelector(".buscarForm");
    let formBorrar = document.querySelector(".formularioBorrar");
    let formModificar = document.querySelector(".formularioModificar");
    let btnMostrar = document.querySelector(".mostrarPacientes");
    let mostrarMensajeEnList = document.querySelector(".lugarParaMostrarPacientes");

    let url = "http://localhost:8081/pacientes";

    // Registrar ENVIO
    formRegistrar.addEventListener('submit', function(event) {
        event.preventDefault();
        registrarPaciente();
    });

    formBuscar.addEventListener('submit', function(event){
        event.preventDefault();
        let id = document.querySelector("#idBuscar").value;
        if (id) {
            buscarPaciente(id);
        } else {
            alert("Por favor, ingrese un ID antes de buscar.");
        }
    });

    btnMostrar.addEventListener('click', ()=>{
        listarPacientes();
    })

    formBorrar.addEventListener('submit', function(event){
        event.preventDefault()
        let id = document.querySelector("#idBorrar").value;
        if (id){
            eliminarPaciente(id)
        }else{
            alert("Ingrese el id, para poder borrar el paciente.")
        }
    })

    formModificar.addEventListener('submit', (e) => {
        e.preventDefault();
        modificarPaciente();
    });

    // FUNCIONES

    function registrarPaciente(){
        // Resto del código para registrar paciente

        fetch(${url}/registrar, settings)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Error al registrar el paciente');
                }
            })
            .then(data => {
                console.log("El paciente se registró exitosamente:", data);
                mostrarDatosPaciente(data, formRegistrar);
                mostrarMensajeExito(formRegistrar);
                // Mostrar alerta de éxito
                alert("El paciente se registró correctamente.");
                return data;
            })
            .catch(error => {
                console.error('Error:', error);
                mostrarMensajeError(formRegistrar);
                throw error;
            });
    }

    // Resto del código

});