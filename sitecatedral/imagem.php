<?php
include_once 'service/funcoes.php';

$id = $_GET ['id'];
$largura = $_GET ['largura'];
if(!$largura){
    $largura = "400";
}
$altura = $_GET ['altura'];
if(!$altura){
    $altura= "400";
}
$funcao = $_GET["a"];
if(!$funcao){
    $funcao = 'ps';
    
}

if($funcao == 'ps'){
    $img = URL . 'api/arquivo/getPastaSize/' . $id . '/'.$largura.'/' . $altura;
}else if($funcao == 'ar'){
    $img = URL . 'api/arquivo/getArquivo/' . $id . '/'.$largura.'/' . $altura;
}else if($funcao == 'aro'){
    $img = URL . 'api/arquivo/getArquivoObra/' . $id . '/'.$largura.'/' . $altura;
}else if($funcao == 'pasta'){
    $img = URL . 'api/arquivo/getPastaImage/' . $id . '/'.$largura.'/' . $altura;
}


$diretorio = "fotos/";
function save_image($inPath, $outPath) { // Download images from remote server
    $in = fopen ( $inPath, "rb" );
    $out = fopen (  $outPath, "wb" );
    while ( $chunk = fread ( $in, 8192 ) ) {
        fwrite ( $out, $chunk, 8192 );
    }
    fclose ( $in );
    fclose ( $out );
}

function compress($source, $destination, $quality) {
    
    $info = getimagesize($source);
    
    if ($info['mime'] == 'image/jpeg')
        $image = imagecreatefromjpeg($source);
        
        elseif ($info['mime'] == 'image/gif')
        $image = imagecreatefromgif($source);
        
        elseif ($info['mime'] == 'image/png')
        $image = imagecreatefrompng($source);
        
        imagejpeg($image, $destination, $quality);
        
        return $destination;
}


$filename = $diretorio . $id . $largura . $altura . '.png';
if($funcao == 'pasta'){
    $dataa = date("d-m-y");
    $filename = $diretorio . $id . $largura . $altura . $dataa . '.png';
}
if (!file_exists ( $filename )) {
    save_image($img,$filename);
    compress($filename, $filename, 90);
}

header("location:$filename");
?>
