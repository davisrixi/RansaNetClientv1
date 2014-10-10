<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">    
        <script src="<c:url value="/resources/js/jquery-1.11.0.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>


        <title>Principal</title>
        <style type="text/css">
            body {
                margin:0px;
                padding:0px;
                height:100%;
                overflow:hidden;
            }
        </style>
    </head>
    <body>
        <div >
            <tiles:insertAttribute name="header" />
            <div >
                <tiles:insertAttribute name="body" />
            </div>
            <div >
            <tiles:insertAttribute name="footer" />
            </div>
        </div>
    </body>
</html>