<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type = "text/javascript" >
        $(document).ready(function() {

            $("#registrar").click(function() {
                registrarRecojo();
                $("#registrarRecojo").submit();

            });

            function registrarRecojo() {

                $.ajax({
                    url: 'registrarRecojo',
                    type: 'post',
                    data: $('form#recojoForm').serialize(),
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

        <form id="recojoForm" modelAttribute="PedidoRecojo" method="post">

            <div class="container">


                <div class="page-header">
                    <h1>Recojo de documentos</h1>
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
                                <label for="InputCantidadCajas">Cantidad de cajas</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="cantidadCajas" id="InputCantidadCajas" placeholder="Ingrese documento" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>                               

                            <div class="form-group">
                                <label for="InputMessage">Descripción</label>
                                <div class="input-group">
                                    <textarea  id="InputMessage" class="form-control" rows="5" required placeholder="Ingrese descripción" name="comentario"></textarea>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>



                        </div>
                    </div>
                </div>
                <br>
                <c:if test="${codPedido}">
                    <div class="alert alert-success" role="alert">${codPedido}</div>
                </c:if>

                <br>
                <div class="bs-docs-section">

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="pull-right">
                                <button type="reset" class="btn btn-danger">Cancelar</button>
                                <button type="button" class="btn btn-success" name="registrar" id="registrar">Registrar</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">&nbsp;</div>
                </div>    

            </div>

        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>