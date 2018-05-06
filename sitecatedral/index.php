<?php

include_once("service/service.php");

if(isset($_GET['p'])) {
	$url = explode('/', $_GET['p']);
	
	include_pagina($url[0]);
	
} else {
	include_pagina("home");
}

function include_pagina($pagina){
	global $url;
	
	$CONTEUDO = obterConteudo($pagina);
	if(!file_exists($pagina . ".php")){
		if($CONTEUDO){
			$pagina_titulo = $CONTEUDO->titulo;
			include $CONTEUDO->conteudotipo->pagina;
		}else{
			include 'home.php';
		}
	}else{
		$pagina_titulo = $pagina;
		include($pagina.'.php');
	}
}
?>