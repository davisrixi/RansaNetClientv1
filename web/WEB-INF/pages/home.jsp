<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
</head>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">


        <div class="container">

            <div class="page-header">
                <h1 id="x">Bienvenido <c:out value="${nombre}"/>:</h1>
            </div>

            <br>
            <div>
                <h1 id="timeline">Estos son los servicios que se encuentran disponibles</h1>
            </div>
            <br>


            <ul class="timeline">
                <li>
                    <div class="timeline-badge"></div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4 class="timeline-title">Pedido de documentos</h4>

                        </div>
                        <div class="timeline-body">
                            <p>El usuario puede pedir uno o varios documentos desde el aplicativo. Posteriormente, se le hace la entrega mediante el envío del mismo en formato digital.</p>
                        </div>
                    </div>
                </li>
                <li class="timeline-inverted">
                    <div class="timeline-badge warning"></div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4 class="timeline-title">Recojo de Documentos</h4>
                        </div>
                        <div class="timeline-body">
                            <p>El usuario puede solicitar el recojo de sus documentos, así tambien como cajas, para un próximo servicio de Custodia de Documentos.</p>
                        </div>
                    </div>
                </li>

            </ul>
        </div>




    </tiles:putAttribute>
</tiles:insertDefinition>