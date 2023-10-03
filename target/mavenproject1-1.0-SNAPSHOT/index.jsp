<!-- Incluimos la Templeate de header -->

<%@include file="Templates/header.jsp" %>

<!-- Ponemos como header la imagen -->
<header>
    <img src="Recursos/Encabezado.jpeg" alt="encabezado"  width="100%" >

    <!-- Establecemos el navbar -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <!-- Pagina principal -->
            <a class="navbar-brand" href="index.jsp">Exposicion Canina</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Mostrar opciones para ordenar -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <!-- Mostrar opciones para ordenar -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Ordenar
                        </a>
                        <ul class="dropdown-menu">
                            <!-- Segun la opcion mandamos los datos por la PATH, indicando el tipo y segun que se va a ordenar -->
                            <li><a class="dropdown-item" href="SvCanino?tipo=ordenar&orden=nombre">Nombre</a></li>
                            <li><a class="dropdown-item" href="SvCanino?tipo=ordenar&orden=raza">Raza</a></li>
                            <li><a class="dropdown-item" href="SvCanino?tipo=ordenar&orden=puntos">Puntos</a></li>
                            <li><a class="dropdown-item" href="SvCanino?tipo=ordenar&orden=edad">Edad</a></li>

                        </ul>
                    </li>
                    <!-- Mostrar opciones para buscar -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Buscar
                        </a>
                        <ul class="dropdown-menu">
                            <!-- Segun la opcion mandamos los datos por la PATH, indicando el tipo y segun que se va a ordenar -->
                            <li><a class="dropdown-item" href="SvCanino?tipo=buscar&indice=mayorPun">Ganador de la exposicion</a></li>
                            <li><a class="dropdown-item" href="SvCanino?tipo=buscar&indice=menorPun">Perro con menor puntaje</a></li>
                            <li><a class="dropdown-item" href="SvCanino?tipo=buscar&indice=mayorEdad">Perro mas viejo</a></li>
                            
                        </ul>
                    </li>

                    <!-- Boton para ver los autores -->
                    <li class="nav-item">
                        <a class="nav-link" href="autores.jsp">Autores</a>
                    </li>
                </ul>

                <!-- Form por metodo GET que envia el nombre a buscar -->
                <form action="SvCanino" method="GET" class="d-flex" role="search">
                    <input class="form-control me-2" name="perroBuscar" type="search" placeholder="Search" aria-label="Search">
                    <!-- Input Hidden como bandera que manda tipo=search -->
                    <input type="hidden" name="tipo" value="search">
                    <button class="btn btn-outline-success" type="submit">Buscar Nombre</button>
                </form>
            </div>
        </div>
    </nav>

</header>
<%
    /**
     * Declaramos variables, muchas ya con un valor para prevenir errores por valores nulos
     */        
    int varPuntos=0;String nombre = "";String raza = "";
    
    String accion=request.getParameter("accion");// Llamamos la accion que aplica en editar
    
    /**
     * If en caso de que los valores no sean nulos, los establezca. Para asi ponerlos como valor en el form -> Aplica para el editar
     */
    if (request.getParameter("puntos") != null){ varPuntos = Integer.parseInt(request.getParameter("puntos"));}
    if(request.getParameter("nombre") != null && request.getParameter("tipo").equals("editar")){nombre = request.getParameter("nombre");}
    if(request.getParameter("raza") != null){raza = request.getParameter("raza");}
%>
        
        <!-- Primera clase contenedora -->
        <div class="container p-4"> 
            
            <div class="row">
            <div class="col-md-4">  <!-- clase division por 4 columnas -->
                <div class="card card-body"> <!-- tarjeta de trabajo -->
                    <h3>Insertar nuevo perro</h3>
                    <!-- Formulario que conecta con el servlet y manda por el metodo POST especificamos enctype para 
                    manejar el formulario que manda el FILE. Basado: https://es.stackoverflow.com/questions/48643/como-guardar-imagen-en-proyecto-servlet
                    -->
                    <form action="SvCanino" method="POST" enctype="multipart/form-data">
                        
                      <!-- Formulario basado de: https://getbootstrap.com/docs/5.3/forms/input-group/  --> 
                      
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Nombre:</span>
                         <!-- Aqui se obtiene el identificador unico, por lo que es necesario que al momento de presionar el boton de editar esta no se vuelva modificable -->
                        <input type="text" name="nombre" class="form-control" value="<% out.println(nombre); %>" <% if (!nombre.equals("")){%>readonly<%}else{%> required<%}%>><br>
                      
                      </div>
                      
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Raza:</span>
                        <!-- Cargamos informacion en caso de editar-->
                        <input type="text" name="raza" class="form-control"value="<% out.println(raza); %>" required><br>
                      </div>
                      
                      <!--Basado: https://es.stackoverflow.com/questions/48643/como-guardar-imagen-en-proyecto-servlet -->
                      
                       <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Foto:</span>
                        <!-- Cargamos informacion en caso de editar-->
                        <input type="file" id="imagen" name="imagen" class="form-control" accept="image/*" value="<% out.println(request.getParameter("foto")); %>"required><br>
                      </div>
                      
                       <!--Basado: https://getbootstrap.com/docs/5.3/forms/select/ -->
                      
                      <div class="input-group mb-3">
                       <span class="input-group-text" id="basic-addon1" >Puntos</span>
                       <select class="form-select" name="puntos" required>
                            <option >Seleccione...</option>
                             <!--Añadir if para seleccionar en caso que las variables sean iguales, sirve para el editar-->
                            <option <% if (varPuntos == 1) {%> selected <% } %>  value="1">Uno</option>
                            <option <% if (varPuntos == 2) {%> selected <% } %> value="2">Dos</option>
                            <option <% if (varPuntos == 3) {%> selected <% } %> value="3">Tres</option>
                            <option <% if (varPuntos == 4) {%> selected <% } %> value="4">Cuatro</option>
                            <option <% if (varPuntos == 5) {%> selected <% } %> value="5">Cinco</option>
                            <option <% if (varPuntos == 6) {%> selected <% } %> value="6">Seis</option>
                            <option <% if (varPuntos == 7) {%> selected <% } %> value="7">Siete</option>
                            <option <% if (varPuntos == 8) {%> selected <% } %> value="8">Ocho</option>
                            <option <% if (varPuntos == 9) {%> selected <% } %> value="9">Nueve</option>
                            <option <% if (varPuntos == 10) {%> selected <% } %> value="10">Diez</option>

                            
                        </select>
                       
                      </div>
                            <!-- Banderas invisibles que se activan al presionar el boton de editar, se envian en la PATH del metodo
                            GET cuando hay que editar, ayudan a identificar que debemos editar en el metodo POST -->
                            <input type="text" name="tipo" value ="<%out.println(accion);%>" style="display: none">
                            <input type="text" name="perroedicion" value ="<%out.println(nombre);%>" style="display: none">
                            
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Edad:</span>
                        <!-- Cargamos informacion en caso de editar -->
                        <input type="number" name="edad" class="form-control" min="0" step="1" value="<% out.print(request.getParameter("edad")); %>" required><br>
                      </div>



                    <div class="col-12">

                        <!--Basado: https://getbootstrap.com/docs/5.3/components/buttons/ -->

                        <input  class="btn btn-success" type="submit" name="accion" value="Insertar Perro">
                        <input class="btn btn-success" type="reset" value="Limpiar"/>
                        
                        

                    </div>
            </div> </div> </form> <br>

        <!-- Creamos la tabla interactiva -->

        <div class="col-md-8">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Raza</th>
                        <th>Foto</th>
                        <th>Puntos</th>
                        <th>Edad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        /**
                         * Obtenemos parametros que se llenan al presionar ciertos botones. Ayudan a reconocer que se debe mostrar en la lista
                         */
                        String perroBuscar = request.getParameter("perroBuscar");
                        String orden = request.getParameter("orden");
                        String indice = request.getParameter("indice");

                        /**
                         * Obtenemos el objeto de ServletContext para obtener la
                         * informacion del servlet, lo usamos para obtener la
                         * PATH en la deserializacion
                         */
                        ServletContext context = getServletContext();

                        /**
                         * Obtenemos el array y le establecemos los valores del
                         * archivo txt para que los muestre inmediatamente
                         */
                        ArrayList<Perro> perros = ExpocicionPerros.listarPerros(context, perroBuscar, orden, indice);

                        /**
                         * Manejo de excepciones en caso de estar vacio o nulo
                         */
                        if (perros != null && !perros.isEmpty()) {

                            /**
                             * Recorremos el array
                             */
                            for (Perro p : perros) {

                                /**
                                 * Escribir los datos en la tabla
                                 */
                    %>
                    <tr>
                        <td><% out.println(p.getNombre()); %> </td>
                        <td><% out.println(p.getRaza()); %> </td>

                        <!-- Mostramos la imagen usando el getContextPath mas la ruta relativa, obteniendo la relativa. Request va hacia el HttpServletRequest
                        Se usa este metodo para que vaya con el contexto de los archivos. Basado: https://www.roseindia.net/jsp/request-getcontextpath.shtml?expand_article=1-->

                        <td><%out.println(p.getImagen()); %></td>
                        <td><% out.println(p.getPuntos()); %> </td>
                        <td><% out.println(p.getEdad());%> </td>
                        <td><center>
                            <!-- Ventana modal -->
                            <a href="#" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#exampleModal" data-nombre="<%= p.getNombre()%>"><i class="fa fa-eye"> </i></a>

                            <!-- Editar Perro -->
                            <a href="SvCanino?tipo=editar&nombre=<%= p.getNombre()%>" class="btn btn-outline-warning" data-bs-toggle="editar" data-bs-target="#editar" data-nombre="<%= p.getNombre()%>"><i class="fa-solid fa-pen"></i></a>                                        

                            <!-- Eliminar Perro -->
                            <!-- Llamamos un metodo para confirmar si deseamos eliminar (Solo avanza en true) y redirigimos con las variables-->
                            <a href="#" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#eliminar" data-nombre="<%= p.getNombre()%>"><i class="fa-solid fa-trash"></i></a>
                            </center>
                        </td>
                    </tr> 
                    
                        <!--                Modal eliminar
                        BASADO: https://getbootstrap.com/docs/5.3/components/modal/-->
                        
                    <div class="modal fade" id="eliminar" tabindex="-1" role="dialog" aria-labelledby="eliminarModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="eliminarModalLabel">Confirmar Eliminación</h5>
                                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        ¿Estás seguro de que deseas eliminar este perro?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button> 
                                        <!-- Agrega aquí un botón para realizar la eliminación -->
                                        <a href="SvCanino?tipo=delete&nombre=<%= p.getNombre()%>" class="btn btn-danger" id="confirmarEliminacion">Eliminar</a>
                                    </div>
                                </div>
                            </div>
                        </div
                    <%
                        
                        }
                    } else /**
                     * En caso de no tener objetos
                     */
                    {  %>

                <td><% out.println("No hay perros"); %> </td>
                <td><% out.println(""); %> </td>
                <td><% out.println(""); %> </td>
                <td><% out.println(""); %> </td>
                <td><% out.println(""); %> </td>
                <td>
                    <!-- Iconos no interactivos ya que no hay perro-->
                    <i class="fa fa-eye"> </i>
                    <i class="fa-solid fa-pen"></i>                                        
                    <i class="fa-solid fa-trash"></i>
                </td>
                <%    }
                %>


                </tbody> 
            </table>
        </div>
    </div>  
</div>    
</div>
<!-- modal 1 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalles del Perro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="perro-details">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>





<script>
    // funcion para mostrar los datos en la ventana modal
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Botón que desencadenó el evento
        var nombre = button.data('nombre'); // Obtener  el nombre del perro

        // Realiza una solicitud AJAX al servlet para obtener los detalles del perro por su nombre
        $.ajax({
            url: 'SvCanino?tipo=modal&nombre=' + nombre, // Enviamos el nombre y tipo de accion para saber el camino del switch
            method: 'GET',
            success: function (data) {
                // Actualiza el contenido del modal con los detalles del perro
                $('#perro-details').html(data);
            },
            error: function () {
                // Maneja errores aquí si es necesario
                console.log('Error al cargar los detalles del perro.');
            }
        });
    });

</script>


<!-- Incluimos la Templeate de footer -->
<%@include file="Templates/footer.jsp" %>

