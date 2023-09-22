
<%@include file="Templates/header.jsp" %>
        <header>
        <img src="Recursos/Encabezado.jpeg" alt="encabezado"  width="1500" >
        </header>

        <div class="container p-4"> <!-- clase contenedora -->
            
            <div class="row">
            <div class="col-md-4">  <!-- clase division por 4 columnas -->
                <div class="card card-body"> <!-- tarjeta de trabajo -->
                  <form action="SvCanino" method="POST">
                       
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Nombre:</span>
                        <input type="text" name="nombre" class="form-control"><br>
                      </div>
                      
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Raza:</span>
                        <input type="text" name="raza" class="form-control"><br>
                      </div>
                      
                       <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Foto:</span>
                        <input type="text" name="imagen" class="form-control"><br>
                      </div>
                      
                      <div class="input-group mb-3">
                       <span class="input-group-text" id="basic-addon1" >Puntos</span>
                       <select class="form-select" name="puntos" >
                            <option selected>Seleccione...</option>
                            <option value="1">Uno</option>
                            <option value="2">Dos</option>
                            <option value="3">Tres</option>
                            <option value="4">Cuatro</option>
                            <option value="5">Cinco</option>

                            
                        </select>
                       
                      </div>
                      
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Edad:</span>
                        <input type="text" name="edad" class="form-control"><br>
                      </div>

                
            <div class="col-12">
                <!-- clase division por 4 columnas <button type="button" class="btn btn-success" type="submit">Insertar Perro</button>-->
               <!--  <input class="btn btn-success"id="boton" type="submit">-->
                
                <input  class="btn btn-success" type="submit" value="Insertar Perro">
                <input class="btn btn-success" type="reset" value="Eliminar"/>
            </div>
                </div>    
            </div>  </form><br>
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
                            * Obtenemos el array
                            */
                           ArrayList<Perro> perros= (ArrayList<Perro>)request.getAttribute("perros");
                           /**
                            * Manejo de excepciones
                            */
                            if (perros != null) { 
                                for (Perro p : perros) { 
                                    /**
                                     * Escribir los datos
                                     */ %>
                                 <tr>
                                    <td><% out.println(p.getNombre()); %> </td>
                                    <td><% out.println(p.getRaza()); %> </td>
                                    <td><% out.println(p.getImagen()); %> </td>
                                    <td><% out.println(p.getPuntos()); %> </td>
                                    <td><% out.println(p.getEdad()); %> </td>
                                       <td><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAbBJREFUSEvllttNxEAMRe9WAlQCVAJUAlQCnQCVAJWADsqNHK9nMvnI7geWIkXJxMeP65kcdCY7nImrCnwp6WvvgDIY6JukV0nPe8Iz+GeCkfGu8Ay+l/RyCnjV4x78RtL1FBj3GNXh+p6qNNShlqoz/F0Sz9aMAFiLProC7Y1ThFdAZ+rM45pVjfTAqLty+pQUzySw7lES9zmAqyrqFjhCid4OnQm+XHqeubTAc0t4fwSvwBHKSD1Mzqz2KgH6yjqM73Pm9jN/m8GUjA9tRHs7CaXX81j+1joCI4A/y+DPKdpc3jW4y41PssdPtkXJIzhGSnQXksgEG83czlvCnLPugSkLYtkKx6crl7MuwT4gfDpZiVvgFpH3/OZo9fZqekVvsVE44I+w30cwvvBZiouHjI1nMap1BB5FGaExiSaYUgOPh4BVPQLPfT2CVuPkjyq4DwDexV0rtiPvWiW0B+YdgLug6mI051HLO5XX5n199jHys4fTag+uAnFVYkXKP5kRcCy/+87PgLO0UlGz71f/ZLaAW6VuPe/C9wQTUIQvhLY32HA0sjiTTwEuW/H/wL/9CIofLBhWBgAAAABJRU5ErkJggg=="/><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAPFJREFUSEvt1t0NwjAMBODrJowCkwCTAJPAJjBKNwFZiqXIjZ04OMoD8FKB2nzc5QcWTHotk1xEw7sUZK0FioQJvQOg6wGAiUfBjO6zxCYeBZ9S2rxhSqziUTAlPgK4irml97fSfH8DE0bVPtLAEqfPz9oi64XzOc0BxumqovRlemC5kGgciYdvpxLKbZrVyso9iS1Upq6dH81Vh6KtcxyOtsBD0Bo8DLXgoagFP9OpVFqdrm3jPbneygMhqJW4BIehHjgUtWD+QefGX9WjyHmD58h0Dm3f/oe5H2079da9aVarehp86Y2mPLf5w/d7i+sDJzguHxksKkEAAAAASUVORK5CYII="/><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAPJJREFUSEvtllESwiAMRLcnsUfRk2hvpifRm6g3cdaxMxCBDbYj/YBPmuSRJaQZ0GgNjbioAZ8A7MRBnwDOnmS84CuAvScggBuAg7L1gEcAdxXIfCeYB8guD5iZMmOuUkCv3TvQZsCU9Wj04R4Li4uF88jop+wuoa/NOJSr8lqleXRNmwGHx56fkOt5mHylb6m4pHNBXOlbC567V9ihUnurg1MBvXuRQLUZeyE946867FKrBtKLiwp5VVj0jpt1Lvnv+xis3kD+CvbCUnacWKbUh1ID+WW6tIzscKiGPcI5DqlBPpVUNGNZAwVeInPRtxn4BeiHhh8duCnrAAAAAElFTkSuQmCC"/></td>
                                 </tr>        
                        <%
                            }}else {  %>
                                <td><% out.println("No hay videos"); %> </td>
                                <td><% out.println(""); %> </td>
                                <td><% out.println(""); %> </td>
                                <td><% out.println(""); %> </td>
                                <td><% out.println(""); %> </td>
                                  <td><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAbBJREFUSEvllttNxEAMRe9WAlQCVAJUAlQCnQCVAJWADsqNHK9nMvnI7geWIkXJxMeP65kcdCY7nImrCnwp6WvvgDIY6JukV0nPe8Iz+GeCkfGu8Ay+l/RyCnjV4x78RtL1FBj3GNXh+p6qNNShlqoz/F0Sz9aMAFiLProC7Y1ThFdAZ+rM45pVjfTAqLty+pQUzySw7lES9zmAqyrqFjhCid4OnQm+XHqeubTAc0t4fwSvwBHKSD1Mzqz2KgH6yjqM73Pm9jN/m8GUjA9tRHs7CaXX81j+1joCI4A/y+DPKdpc3jW4y41PssdPtkXJIzhGSnQXksgEG83czlvCnLPugSkLYtkKx6crl7MuwT4gfDpZiVvgFpH3/OZo9fZqekVvsVE44I+w30cwvvBZiouHjI1nMap1BB5FGaExiSaYUgOPh4BVPQLPfT2CVuPkjyq4DwDexV0rtiPvWiW0B+YdgLug6mI051HLO5XX5n199jHys4fTag+uAnFVYkXKP5kRcCy/+87PgLO0UlGz71f/ZLaAW6VuPe/C9wQTUIQvhLY32HA0sjiTTwEuW/H/wL/9CIofLBhWBgAAAABJRU5ErkJggg=="/><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAPFJREFUSEvt1t0NwjAMBODrJowCkwCTAJPAJjBKNwFZiqXIjZ04OMoD8FKB2nzc5QcWTHotk1xEw7sUZK0FioQJvQOg6wGAiUfBjO6zxCYeBZ9S2rxhSqziUTAlPgK4irml97fSfH8DE0bVPtLAEqfPz9oi64XzOc0BxumqovRlemC5kGgciYdvpxLKbZrVyso9iS1Upq6dH81Vh6KtcxyOtsBD0Bo8DLXgoagFP9OpVFqdrm3jPbneygMhqJW4BIehHjgUtWD+QefGX9WjyHmD58h0Dm3f/oe5H2079da9aVarehp86Y2mPLf5w/d7i+sDJzguHxksKkEAAAAASUVORK5CYII="/><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAPJJREFUSEvtllESwiAMRLcnsUfRk2hvpifRm6g3cdaxMxCBDbYj/YBPmuSRJaQZ0GgNjbioAZ8A7MRBnwDOnmS84CuAvScggBuAg7L1gEcAdxXIfCeYB8guD5iZMmOuUkCv3TvQZsCU9Wj04R4Li4uF88jop+wuoa/NOJSr8lqleXRNmwGHx56fkOt5mHylb6m4pHNBXOlbC567V9ihUnurg1MBvXuRQLUZeyE946867FKrBtKLiwp5VVj0jpt1Lvnv+xis3kD+CvbCUnacWKbUh1ID+WW6tIzscKiGPcI5DqlBPpVUNGNZAwVeInPRtxn4BeiHhh8duCnrAAAAAElFTkSuQmCC"/></td>
                        <%    }
                        %>


                    </tbody> 
                </table>
                </div>
               </div>  
            </div>    
        </div>
<%@include file="Templates/footer.jsp" %>

