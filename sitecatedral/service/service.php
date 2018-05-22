<?php

include_once("funcoes.php");

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
    if(isset($_POST['imovelid'])){
        $fields['imovelid'] = $_POST['imovelid'];
    }
    $fields['contatotipo']["id"] = $_POST['tipo'];
    
    $result = enviarDados($url, $fields);
    
    if($result && $result->valid){
        echo "sucesso";
    }else{
        echo "falha";
    }
}


