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
    
    $img = URLSITE . 'imagem.php?id=' . $blog->pasta->id. '&a=ps&altura=600&largura=600';

    
    $html .= '<div class="row item">
                <div class="columns medium-3 photo">
                    <a href="' . URLSITE . 'artigo/' . $blog->id . '">
                        <img src="' . $img . '" alt="">
                    </a>
                </div>
                <div class="columns medium-9 info">
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

    <section class="dark-line-section">
        <div class="inner">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="title">Artigos</h1>
                    <nav class="breadcrumbs">
                        <a href="<?php echo URLSITE; ?>home">Home</a>
                        <a class="current" href="<?php echo URLSITE; ?>artigos">Artigos</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>

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