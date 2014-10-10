<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>

    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type = "text/javascript" >
        $(document).ready(function() {
            $("#buscar").click(function() {
                getDocumentos();
                $("#formPedidoDocumento").submit();
            });

            function getDocumentos() {
                $.ajax({
                    url: 'getDocumentos',
                    type: 'post',
                    data: $('form#formPedidoDocumento').serialize()
                });

            }

            $("#agregar").click(function() {
                agregarDocumento();
                $("#formPedidoDocumento").submit();
            });

            function agregarDocumento() {
                alert("!");
                $.ajax({
                    url: 'agregarDocumento',
                    type: 'post',
                    data: $('form#formPedidoDocumento').serialize()
                });
            }

            $("#registrar").click(function() {
                registrarPedido();
            });

            function registrarPedido() {

                $.ajax({
                    url: 'registrarPedido',
                    type: 'post',
                    data: $('form#formPedidoDocumento').serialize(),
                    success: function(response) {
                        alert(response);
                    }
                });
            }

        });



    </script>

</head>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="container">



            <form id="formPedidoDocumento" modelAttribute="PedidoDocumento" method="post">
                <div class="page-header">
                    <h1>Pedido de documentos</h1>
                </div>        


                <div class="panel-body">
                    <div class="row">

                        <div class="col-lg-6">

                            <div class="form-group">
                                <label for="InputName">Área</label>
                                <div class="input-group">
                                    <select class="form-control" name="area">
                                        <c:forEach items="${areas}" var="area">
                                            <option value="${area.idArea}">${area.nombre}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="InputName">Tipo de Documento</label>
                                <div class="input-group">
                                    <select class="form-control" name="tipoDocumento">
                                        <c:forEach items="${tiposDocumento}" var="tipoDocumento">
                                            <option value="${tipoDocumento.codigo}">${tipoDocumento.descripcion}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>                            

                            <div class="form-group">
                                <label for="InputDocumento">Documento</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="nroDocumento" id="InputName" placeholder="Ingrese documento" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>                            

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-20">
                                    <div class="pull-right">

                                        <input type="button" value="Buscar" name="buscar" class="btn btn-info" id="buscar">
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>



                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <small><h3 id="tables">Documentos disponibles [<c:out value="${fn:length(inventarios)}" />]</h3></small>
                        </div>

                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>

                                        <th>Sel</th>
                                        <th>Nro. Documento</th>
                                        <th>Nro. Serie</th>
                                        <th>Observaci&oacute;n</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${inventarios}" var="inventario">
                                        <tr>
                                            <td><input type="checkbox" class="checkbox" value="${inventario.id}-${inventario.idDetalle}" name="x"></td>
                                            <td><c:out value="${inventario.nroDocumento}"></c:out></td>
                                            <td><c:out value="${inventario.nroSerie}"></c:out></td>
                                            <td><c:out value="${inventario.observacion}"></c:out></td>
                                            </tr>

                                    </c:forEach>
                                </tbody>
                            </table> 
                        </div><!-- /example -->
                    </div>
                </div>


                <div class="bs-docs-section">

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="pull-right">
                                <button type="button" class="btn btn-info" id="agregar" name="buscar">Agregar</button>
                            </div>
                        </div>
                    </div>

                </div>    

                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <small><h3 id="tables">Documentos seleccionados [<c:out value="${fn:length(agregados)}"/>]</h3></small>
                        </div>

                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>
                                        <th>Nro. Documento</th>
                                        <th>Nro. Serie</th>
                                        <th>Observaci&oacute;n</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${agregados}" var="agregado">
                                        <tr>

                                            <td><c:out value="${agregado.nroDocumento}"></c:out></td>
                                            <td><c:out value="${agregado.nroSerie}"></c:out></td>
                                            <td><c:out value="${agregado.observacion}"></c:out></td>
                                            </tr>

                                    </c:forEach>
                                </tbody>
                            </table> 
                        </div><!-- /example -->
                    </div>
                </div>            

                <div class="bs-docs-section">

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="pull-right">
                                <button type="reset" class="btn btn-danger">Cancelar</button>
                                <button type="button" name="registrar" id="registrar" class="btn btn-success">Registrar</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-sm-10" style="padding: 30px ">&nbsp;</div>
                </div>    
            </form>
        </div>                




    </tiles:putAttribute>
</tiles:insertDefinition>