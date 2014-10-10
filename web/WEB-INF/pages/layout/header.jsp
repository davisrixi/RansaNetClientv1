<head>
    <style>
    @media screen and (min-width:768px){
        .navbar-brand-centered {
            position: absolute;
            left: 50%;
            display: block;
            width: 160px;
            text-align: center;
            background-color: #64B76D;
        }
        .navbar>.container .navbar-brand-centered, 
        .navbar>.container-fluid .navbar-brand-centered {
            margin-left: -80px;
        }
    }
    </style>
    
</head>

<nav class="navbar navbar-default" role="navigation">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-brand-centered">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="navbar-brand navbar-brand-centered">RansaNet</div>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbar-brand-centered">
      <ul class="nav navbar-nav">
        <li><a href="acceder">Home</a></li>
        <li><a href="pedido">Pedido</a></li>
        <li><a href="recojo">Recojo</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Salir</a></li>		        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>