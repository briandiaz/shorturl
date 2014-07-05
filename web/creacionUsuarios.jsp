
<%@ include file="template/header.jsp" %>

<div class="row">
    <div class="row vertical-offset-100">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Regístrate en short.me</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="./CreateUser" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="usuario_id" class="col-sm-4 control-label">Nombre de Usuario</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="usuario_id" name="usuario_id" placeholder="Nombre de Usuario" autofocus required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="usuario_nombre" class="col-sm-4 control-label">Nombre</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="usuario_nombre" name="usuario_nombre" placeholder="Ingrese su Nombre" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="usuario_apellido" class="col-sm-4 control-label">Apellido</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="usuario_apellido" name="usuario_apellido" placeholder="Ingrese su Nombre" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="usuario_contrasena" class="col-sm-4 control-label">Contraseña</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="usuario_contrasena" name="usuario_contrasena" placeholder="Ingrese su Contraseña" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-8 col-sm-4">
                                    <button type="submit" class="btn btn-success btn-block">Registrar</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>