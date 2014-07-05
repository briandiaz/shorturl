
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="template/header.jsp" %>
 
            <div class="row">
                <div class="row vertical-offset-100">
                    <div class="col-md-4 col-md-offset-3">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Log In </h3>
                            </div>
                            <div class="panel-body">
                                <form accept-charset="UTF-8" role="form" method="POST" action="./Session">
                                    <fieldset>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="Nombre" name="usuario_id" id="usuario_id" type="text" value="" autofocus >
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="Contraseña" id="usuario_contrasena" name="usuario_contrasena" type="password" value="" >
                                        </div>
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Iniciar Sesión">
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-success">
                            
                            <div class="panel-body">
                                <br/>
                                <p>Para disfrutar de un mejor servicio en línea debes tener una cuenta en <b>short.me</b> </p><br/>
                                <a href="creacionUsuarios.jsp" class="btn btn-lg btn-info btn-block">Regístrate</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                                        
<%@ include file="template/footer.jsp"%>