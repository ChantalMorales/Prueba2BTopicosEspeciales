# Prueba2BTopicosEspeciales
## Integrantes: 
  -Sebastián Morales
  -Chantal Morales
  -Kevin Segovia
  -Israel Vásquez
  -Nicole Zambrano
### 
Este proyecto muestra la implementación de una aplicación to-do list con android studio y firebase, este proyecto tiene como objetivo demostrar las funciones CRUD con lo mencionado anteriormente. 
### 
Login, para lo mismo cuenta con un login para lo cual utilizamos los métodos auth de firebase (signInWithEmailandPassword), y en la app un formulario para el ingreso del mail y contraseña. 
### 
Para Create utilizamos el método de almacenamiento con RealtimeDatabase, en la cual accedemos al nodo específico obtenemos los valores de los inputs como nombre de tare y prioridad y se almacena en el nodo mismo que esta referenciado con una ruta hacia la base, que se puede ver en el código. 
### 
Para el método Read utilizamos un metodo de lectura en el cual obtenemos todos los datos almacenados en el nodo y los mostramos a través de items ordenados en la pantalla principal. 
### 
Update, en este método tenemos que definir en la ruta hacia la base obtener la información del dato, mediante el uso de su clave única (id), en la app es necesario tener aplastado el item que deseamos y pulsar "actualizar" se nos mostrará un modal con la infromación que contiene el ítem mismo que podremos editar. 
### 
Delete por úlitmo al igual que en el método update, obtenemos el id del item que deseamo borrar, y enviamos ese id en el camino hacia la base que referencia el método delete y también está implementado una opción la cual da la opción de borrar todos los datos para esto se utiliza el camino hacia la base sin identificador con el método de eliminación. 
###
Video explicativo: 
https://youtu.be/EMJPuPzC3rE
### 
Capturas:
https://drive.google.com/drive/folders/1II9rWZnwbnDkREulsDjdukXxEZ_qKHyx?usp=sharing 
