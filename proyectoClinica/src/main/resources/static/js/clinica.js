document.addEventListener('DOMContentLoaded', () => {
    const registrarOdontologo = () => {
    document.getElementById('formularioOdontologo').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const odontologo = {
        numeroMatricula: formData.get('numeroMatricula'),
        nombre: formData.get('nombre'),
        apellido: formData.get('apellido')
    };

    fetch('http://localhost:8081/odontologos/registrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar los datos.');
        }
        return response.json();
    })
    .then(data => {
        alert('Odontólogo guardado exitosamente');
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
}
registrarOdontologo();
});