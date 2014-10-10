<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

        <title>Login</title>
    </head>
    <body class="body">

        <form:form id="loginForm" modelAttribute="usuario" method="post" action="acceder">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Ingreso a Ransanet</h3>
                            </div>
                            <div class="panel-body">

                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Usuario" name="usuario" type="text" required autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Contraseña" name="password" type="password" required autofocus>
                                    </div>

                            </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Ingresar">
                            </fieldset>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>

    <div class="container text-center" style="position: absolute; bottom: 15px; width: 100%" >
        <hr />

        <div class="row">
            <div class="col-lg-12">
                <ul class="nav nav-pills nav-justified">
                    <li>© 2014 RansaNet.</li>
                </ul>
            </div>
        </div>
    </div> 

</body>
</html>
