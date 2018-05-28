<?php

include_once("funcoes.php");

function listarCelulas(){
    $url = URL . "api/celula/list";
    
    $fields = array();
    
    $fields["maxResult"] = 200;
    $fields["pagina"] = 1;
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->list;
    }
}

function listarProximosEventos(){
    $url = URL . "api/evento/listproximos";
    
    $fields = array();
    
    $fields["maxResult"] = 2;
    $fields["pagina"] = 1;
    
    $result = enviarDados($url, $fields);

    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->list;
    }
}

function listarEventos($ano,$mes){
    $url = URL . "api/evento/list";
    
    $fields = array();
    
    $fields["maxResult"] = 200;
    $fields["pagina"] = 1;
    $fields["mes"] = $mes;
    $fields["ano"] = $ano;
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->list;
    }
}

function listarComentarios($id){
    $url = URL . "api/comentario/list";
    
    
    $fields = array();
    
    $fields["maxResult"] = 50;
    $fields["pagina"] = 1;
    
    $fields["campos"][] = 'conteudo.id';
    $fields["values"][] = $id;
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result;
    }
}

function listarConteudo($tipo,$itens,$pagina){
    $url = URL . "api/conteudo/list";
    
    
    $fields = array();
    
    //if(isset($_SESSION["busca"]) && isset($_SESSION["busca"]["search"])){
        $fields["search"] = $_SESSION["busca"]["search"];
    //}
    
    $fields["maxResult"] = $itens;
    $fields["pagina"] = $pagina;
    
    $fields["campos"][] = 'conteudotipo.id';
    $fields["values"][] = $tipo;
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result;
    }
}

function obterConteudoPorId($id){
    $url = URL . "api/conteudo/".$id;
    
    $result = enviarDadosGet($url);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->data;
    }
}

function incrementarVisualizacaoConteudo($id){
    $url = URL . "api/conteudo/incrementarVisualizacao/".$id;
    
    $result = enviarDadosGet($url);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->data;
    }
}

function obterConteudo($id){
    $url = URL . "api/conteudoPorUrl/".$id;
    
    
    $fields = array();
    
    $result = enviarDadosGet($url);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->data;
    }
}

if($_GET["acao"] == "buscarPalavra"){
    
    $_SESSION["busca"]["search"] = $_POST["palavra"];
}

function obterImagens($idpasta){
    
    $url = URL . "api/arquivo/listAll/".$idpasta;
    
    $fields = array("oi" => "oi");
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->list;
    }
}

function listarBanners(){
    $url = URL . "api/banner/listAll";
    
    
    $fields = array("maxResult"=>"10");
    
    $result = enviarDadosGet($url);
    
    if($result && $result->valid != "true"){
        return false;
    }else{
        return $result->list;
    }
}

if($_GET["acao"] == "enviaContato"){
    $url = URL . "api/contato/save";
    
    
    $fields['texto'] = $_POST['mensagem'];
    $fields['telefone'] = $_POST['telefone'];
    $fields['email'] = $_POST['email'];
    $fields['nome'] = $_POST['nome'];
    $fields['contatotipo']["id"] = $_POST['tipo'];
    
    $result = enviarDados($url, $fields);
    
    print_r($fields);
    
    if($result && $result->valid){
        echo "sucesso";
    }else{
        echo "falha";
    }
}

if($_GET["acao"] == "enviaComentario"){
    $url = URL . "api/comentario/save";
    
    
    $fields['mensagem'] = $_POST['mensagem'];
    $fields['email'] = $_POST['email'];
    $fields['nome'] = $_POST['nome'];
    if($_POST['conteudo']){
        $fields['conteudo']["id"] = $_POST['conteudo'];
    }
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid){
        echo "sucesso";
    }else{
        echo "falha";
    }
}


