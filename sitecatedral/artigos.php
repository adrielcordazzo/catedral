<?php 

include_once 'inc/header.php'; 

$urlAux = explode('/', $_GET['p']);

if(!isset($urlAux[1]) || !$urlAux[1] >= 1)
    $pagina = '1';
else
    $pagina = $urlAux[1];
        
$itens = 10;
$blogs = listarConteudo("40288a826328e88a016328e92bc90000",$itens,$pagina);
$total = $blogs->countResult;
$qtasPaginas = ceil($total/$itens);

$html = '';
foreach($blogs->list as $blog){
    
    $img = URLSITE . 'imagem.php?id=' . $blog->pasta->id. '&a=pasta&altura=400&largura=400';

    
    $html .= '<div class="row item">
                <div class="columns medium-4 photo">
                    <img src="' . $img . '" alt="">
                </div>
                <div class="columns medium-8 info">
                    <h5><a class="font-lora" href="' . URLSITE . 'artigo/' . $blog->id . '">' . $blog->titulo . '</a></h5>
                    <div class="date-place">
                        <span><i class="fa fa-calendar"></i> ' . $blog->criado . '</span>
                        <!--span><i class="fa fa-user"></i> Phasellus ullamcorper</span-->
                    </div>
                    <p>' . substr($blog->conteudo,0,200) . '</p>
                    <a class="button btn-white" href="' . URLSITE . 'artigo/' . $blog->id . '">Detalhes</a>
                </div>
            </div>';
}

$paginacao = '';

if($pagina >= 1){
    $disabledanterior = "";
    
    if($pagina==1)
        $disabledanterior = "disabled";
            
    $disabledproxima = '';
    /*if($pagina+1 > $qtasPaginas)
     $disabledproxima = 'disabled';*/
    
    $paginacao = '<li class="arrow unavailable" aria-disabled="true"><a href="' . URLSITE . 'artigos/' . ($pagina-1) . '">&laquo; Anterior</a></li>
                  <li class="arrow"><a href="' . URLSITE . 'artigos/' . ($pagina+1) . '">Próxima &raquo;</a></li>';
}
?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <?php include_once 'inc/banner.php'; ?>

    <div class="events-section" id="eventsSection">
        <div class="inner">
            <div class="row">
                <div class="medium-12 columns">
                	<?php echo $html; ?>
                    <div class="pagination-centered">
                        <ul class="pagination" role="menubar" aria-label="Pagination">
                            <?php echo $paginacao; ?>
                        </ul>
                    </div>
                </div>
                
            </div>
        </div>
    </div>

    <?php include_once 'inc/footer.php'; ?>

</body>
</html>