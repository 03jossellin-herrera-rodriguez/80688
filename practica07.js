const formulario = document.getElementById("formulario");
mostrarFormulario();

function mostrarFormulario() {
  const fieldnom = document.createElement("fieldset");
  const legend1 = document.createElement("legend");
  legend1.textContent = "Información Personal";
  const labelnom = document.createElement("label");
  labelnom.textContent = "Nombre:";
  const inputnom = document.createElement("input");
  inputnom.type = "text";

  const labelcor = document.createElement("label");
  labelcor.textContent = "Correo electrónico:";
  const inputcor = document.createElement("input");
  inputcor.type = "text";

  const fieldir = document.createElement("fieldset");
  const legend = document.createElement("legend");
  legend.textContent = "Información de Dirección";

  const labeldir = document.createElement("label");
  labeldir.textContent = "Dirección:";
  const inputdir = document.createElement("input");
  inputdir.type = "text";

  const labelciu = document.createElement("label");
  labelciu.textContent = "Ciudad:";
  const inputciu = document.createElement("input");
  inputciu.type = "text";
  const espacio = document.createElement("br");

  fieldnom.appendChild(legend1);
  fieldnom.appendChild(labelnom);
  fieldnom.appendChild(inputnom);
  fieldnom.appendChild(labelcor);
  fieldnom.appendChild(inputcor);

  fieldir.appendChild(legend);
  fieldir.appendChild(labeldir);
  fieldir.appendChild(inputdir);
  fieldir.appendChild(labelciu);
  fieldir.appendChild(inputciu);

  formulario.appendChild(fieldnom);
  formulario.appendChild(fieldir);

  
  const botonEnviar = document.createElement("button");
  botonEnviar.textContent = "Enviar";
  botonEnviar.className = "enviarButton";

  botonEnviar.style.display = "block"; 
  botonEnviar.style.margin = "0 auto";  
  botonEnviar.style.fontSize = "15px"; 
  botonEnviar.style.backgroundColor = "blue"; 
  botonEnviar.style.color = "white";  
  botonEnviar.style.width = "250px";

  botonEnviar.addEventListener("click", function (event) {
    alert("Enviado");
  });

  formulario.appendChild(botonEnviar);
}
