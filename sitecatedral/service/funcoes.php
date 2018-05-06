<?php

error_reporting(0);

session_start();

$tokenuser = "adriel";
$tokenapp = 'xlabi';

define("TOKENAPP",$tokenapp);
define("TOKENUSER",$tokenuser);

//define("URL","http://localhost:8080/");
define("URL","http://xlabi.com.br:8080/catedral/");

define("URLSITE","http://localhost/sitecatedral/");
//define("URLSITE","http://www.catedralquadrangular.com.br/");


function enviarDados($url, $fields){
	$fields_string = json_encode($fields);
	
	$ch = curl_init();
	
	curl_setopt($ch,CURLOPT_URL, $url);
	curl_setopt($ch,CURLOPT_HTTPHEADER, array('Content-Type: application/json', 'tokenapp: ' . TOKENAPP, 'tokenuser: ' . TOKENUSER));
	curl_setopt($ch,CURLOPT_POST, count($fields));
	curl_setopt($ch,CURLOPT_POSTFIELDS, $fields_string);
	curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);
	$result = curl_exec($ch);
	curl_close($ch);
	$result = json_decode($result);
	return $result;
}

function enviarDadosGet($url){
	$ch = curl_init();

	curl_setopt($ch,CURLOPT_URL, $url);
	curl_setopt($ch,CURLOPT_HTTPHEADER, array('Content-Type: application/json', 'tokenapp: ' . TOKENAPP, 'tokenuser: ' . TOKENUSER));
	curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);
	curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'GET');
	$result = curl_exec($ch);
	curl_close($ch);
	$result = json_decode($result);
	return $result;
}

function enviarArquivos($url, $file){
	$tmpfile = $file['arquivo']['tmp_name'];
	$filename = basename($file['arquivo']['name']);
	$filetype = $file['arquivo']['type'];
	
	$POST_DATA = array(
			'file' => curl_file_create($tmpfile, $filetype, $filename)
	);
	
	$curl_handle = curl_init($url);
	curl_setopt($curl_handle, CURLOPT_POST, 1);
	curl_setopt($curl_handle, CURLOPT_POSTFIELDS, $POST_DATA);
	curl_setopt($curl_handle, CURLOPT_RETURNTRANSFER, true);
	
	$returned_data = curl_exec($curl_handle);
	curl_close ($curl_handle);
	
	return $returned_data;
}
?>