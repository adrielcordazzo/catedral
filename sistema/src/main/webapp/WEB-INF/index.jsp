<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="pt_BR"><head>
  <meta charset="utf-8">
  <title>Catedral</title>
  <meta name="description" content="Sistema para Imobiliárias">
  <meta name="keywords" content="Imóveis, Imobiliária, Sistema, Gerenciamento">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

  <link rel="stylesheet" href="assets/components/bootstrap/dist/css/bootstrap.css" type="text/css">
  <link rel="stylesheet" href="assets/components/animate.css/animate.css" type="text/css">
  <link rel="stylesheet" href="assets/components/font-awesome/css/font-awesome.min.css" type="text/css">

  <link rel="stylesheet" href="assets/css/select2.min.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/bootstrap-select.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/ajax-bootstrap-select.css" type="text/css" />

  <link rel="stylesheet" href="assets/css/sweetalert.css" type="text/css" />
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.css">
  <link rel="stylesheet" href="assets/css/bootstrap-tagsinput.css">
  <link href="assets/css/prettyPhoto.css" rel="stylesheet"/>
  <link rel="stylesheet" href="assets/css/dropzone.css" type="text/css">
  <link href="assets/css/bootstrap-editable.css" rel="stylesheet"/>
  <link rel="stylesheet" href="assets/css/toast.css" type="text/css" />
  <link rel="stylesheet" href="assets/js/cropper/cropper.min.css" type="text/css" />

  <link rel="stylesheet" href="assets/css/font.css" type="text/css">

  <link rel="stylesheet" href="assets/js/dockmodal/prettify.css" type="text/css">
  <link rel="stylesheet" href="assets/js/dockmodal/jquery.dockmodal.css" type="text/css">
  <link rel="stylesheet" href="assets/css/app.css" type="text/css">

  


</head>
<body>
<div class="app app-header-fixed  ">
  
  <header id="header" class="app-header navbar" role="menu">
    <div class="navbar-header bg-dark">
      <button class="pull-right visible-xs dk" ui-toggle="show" target=".navbar-collapse">
        <i class="glyphicon glyphicon-cog"></i>
      </button>
      <button class="pull-right visible-xs" ui-toggle="off-screen" target=".app-aside" ui-scroll="app">
        <i class="glyphicon glyphicon-align-justify"></i>
      </button>
      <a href="#/" class="navbar-brand text-lt">
        <span class="hidden-folded m-l-xs">Catedral</span>
      </a>
    </div>

    <div class="collapse pos-rlt navbar-collapse box-shadow bg-white-only">

      <div class="nav navbar-nav hidden-xs">
          <a href="#" class="btn no-shadow navbar-btn" ui-toggle="app-aside-folded" target=".app">
            <i class="fa fa-dedent fa-fw text"></i>
            <i class="fa fa-indent fa-fw text-active"></i>
          </a>
        </div>

      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" data-toggle="dropdown" class="dropdown-toggle">
            <i class="icon-bell fa-fw"></i>
            <span class="visible-xs-inline">Notificações</span>
          </a>

        </li>
        <li class="dropdown">
          <a href="#" data-toggle="dropdown" class="dropdown-toggle clear" data-toggle="dropdown">
            <span class="hidden-sm hidden-md">
              <security:authentication property="principal.name"/>
            </span> 
            <b class="caret"></b>
          </a>
          <ul class="dropdown-menu animated fadeInRight w">
            <li>
              <a href="#">Minha Conta</a>
            </li>
            <li class="divider"></li>
            <li>
              <a href="logout">Sair</a> 
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </header>


  <aside id="aside" class="app-aside hidden-xs bg-dark">
    <div class="aside-wrap">
      <div class="navi-wrap">
        <nav ui-nav="" class="navi clearfix menuSistema"></nav>
      </div>
    </div>
  </aside>


  <div id="content" class="app-content" role="main">
    <div id="corpoDoSistema" class="app-content-body"></div>
  </div>


  <footer id="footer" class="app-footer" role="footer">
        <div class="wrapper b-t bg-light">
      <span class="pull-right"><a href="" ui-scroll="app" class="m-l-sm text-muted"><i class="fa fa-long-arrow-up"></i></a></span>
     xLabi &copy; 2017 Copyright.
    </div>
  </footer>
</div>

<script src="assets/components/jquery/dist/jquery.min.js"></script>
<script src="assets/components/bootstrap/dist/js/bootstrap.js"></script>
<script src="assets/js/ui-load.js"></script>
<script src="assets/js/ui-jp.config.js"></script>
<script src="assets/js/ui-jp.js"></script>
<script src="assets/js/ui-nav.js"></script>
<script src="assets/js/ui-toggle.js"></script>

<script src="assets/js/select2.min.js"></script>
<script src="assets/js/bootstrap-select.js"></script>
<script src="assets/js/ajax-bootstrap-select.js"></script>
<script src="assets/js/sweetalert.min.js"></script>
<script src="assets/js/jquery.blockui.min.js"></script>
<script src="assets/js/jquery.paging.min.js"></script>
<script src="assets/js/toastr.js"></script>
<script src="assets/js/DataBind.js"></script>
<script src="assets/js/sockjs-0.3.4.js"></script>
<script src="assets/js/stomp.js"></script>
<script src="assets/js/jquery.price.js"></script>
<script src="assets/js/bootstrap-tagsinput.js"></script>
<script src="assets/js/painter/sketch_x.js"></script>
<script src="assets/js/painter/canvas2image.js"></script>
<script src="assets/js/jquery-ui.min.js" type="text/javascript"></script>
<script src="assets/js/jquery.prettyPhoto.js"></script>
<script src="assets/js/jquery.mask.js"></script>
<script src="assets/js/say-cheese.js"></script>
<script src="assets/js/bootstrap-editable.js"></script>
<script src="assets/js/tinymce/tinymce.min.js"></script>
<script src="assets/js/cropper/cropper.min.js"></script>

<script src="assets/js/dockmodal/prettify.js"></script>
<script src="assets/js/dockmodal/jquery.dockmodal.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?key= AIzaSyAC2mz8HOip70us5t73PIDrCfyPibICpVE&callback=initMap"
    async defer></script>

<script src="assets/require.js" data-main="assets/main"></script>



</body>

</html>