<?php
$id = $_GET['id'];
$largura = $_GET['largura'];
$altura = $_GET['largura'];
$diretorio = "temp/";
function save_image($inPath,$outPath)
{ //Download images from remote server
    $in=    fopen($inPath, "rb");
    $out=   fopen($diretorio.$outPath, "wb");
    while ($chunk = fread($in,8192))
    {
        fwrite($out, $chunk, 8192);
    }
    fclose($in);
    fclose($out);
}

$img = "http://www.xlabi.com.br:8080/getImage/api/getFile/" . $_GET["id"];
$filename = $diretorio.$id.$largura.$altura;
if(!file_exists($filename)){
	save_image($img,$outPath)
}

$imginfo = getimagesize($filename); 
header("Content-type: {$imginfo['mime']}"); 
readfile($remoteImage);
?>