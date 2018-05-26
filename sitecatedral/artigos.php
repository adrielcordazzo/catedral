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
    
    $meses = array(
        "01" => "Jan",
        "02" => "Fev",
        "03" => "Mar",
        "04" => "Abr",
        "05" => "Mai",
        "06" => "Jun",
        "07" => "Jul",
        "08" => "Ago",
        "09" => "Set",
        "10" => "Out",
        "11" => "Nov",
        "12" => "Dez",
    );
    $data = explode("/",$blog->criado);
    $dia = $data[0];
    $mes = $data[1];
    $ano = $data[2];
    
    $html .= '<div class="blog-item">
                    <div class="info">
                        <div class="date">
                            <span class="year">' . $dia . '</span>
                            <span class="month">' . $meses[$mes] . '</span>
                            <span class="month">' . $ano . '</span>
                        </div>
                        <div class="format">
                            <i class="flaticon-picture13"></i>
                        </div>
                        <div class="row">
                            <div class="small-3 columns">
                                <a href="' . URLSITE . 'artigo/' . $blog->id . '">
                                    <img src="' . $img . '" alt="' . $blog->titulo . '">
                                </a>
                            </div>
                            <div class="small-9 columns">
                                <h3 class="title"><a href="blog-single.html">' . $blog->titulo . '</a></h3>
                                <div class="meta">
                                    <ul>
                                        <li>Em <a href="#">' . $blog->conteudotipo->tipo . '</a></li>
                                    </ul>
                                </div>
                                <p>' . substr($blog->conteudo,0,200) . '</p>
                                <a href="' . URLSITE . 'artigo/' . $blog->id . '" class="button">Detalhes</a>
                            </div>
                        </div>
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