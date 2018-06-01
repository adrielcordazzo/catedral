<?php 

include_once 'inc/header.php'; 

$urlAux = explode('/', $_GET['p']);

if(!isset($urlAux[1]) || !$urlAux[1] >= 1)
    $pagina = '1';
else
    $pagina = $urlAux[1];
        
$itens = 10;
$blogs = listarComentarios(false,$itens,$pagina);
$total = $blogs->countResult;
$qtasPaginas = ceil($total/$itens);

$html = '';
foreach($blogs->list as $comentario){
    
    $html .= '<div class="item quote-wrapper">
                <blockquote class="quote-content">
                    <div class="quote-text">' . $comentario->mensagem . '</div>
                    <span class="quote-decor"><i class="fa fa-quote-right"></i></span>
                </blockquote>
                <div class="clearfix">
                    <div class="people">
                        <div class="name">' . $comentario->nome . '</div>
                        <div class="years"><i class="fa fa-calendar"></i> ' . $comentario->criado . '</div>
                    </div>
                </div>
            </div><br>';
}

$paginacao = '';

if($pagina >= 1){
    $disabledanterior = "";
    
    if($pagina==1)
        $disabledanterior = "disabled";
            
    $disabledproxima = '';
    /*if($pagina+1 > $qtasPaginas)
     $disabledproxima = 'disabled';*/
    
    $paginacao = '<li class="arrow unavailable" aria-disabled="true"><a href="' . URLSITE . 'depoimentos/' . ($pagina-1) . '">&laquo; Anterior</a></li>
                  <li class="arrow"><a href="' . URLSITE . 'depoimentos/' . ($pagina+1) . '">Próxima &raquo;</a></li>';
}
?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <section class="dark-line-section">
        <div class="inner">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="title">Depoimentos</h1>
                    <nav class="breadcrumbs">
                        <a href="<?php echo URLSITE; ?>home">Home</a>
                        <a class="current" href="<?php echo URLSITE; ?>depoimentos">Depoimentos</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>

    <section class="testimony-section" id="testimonySection" style="margin-top:25px;">
        <div class="inner">
            <div class="row">
                <div class="medium-8 columns">
                	<?php echo $html; ?>
                    <div class="pagination-centered">
                        <ul class="pagination" role="menubar" aria-label="Pagination">
                            <?php echo $paginacao; ?>
                        </ul>
                    </div>
                </div>
                <div class="medium-4 columns">
                	<div class="ws-title left-line">
                        <h1 class="title">Enviar Depoimento</h1>
                    </div>
                    <form name="reviewForm" id="rvwForm" method="post" onsubmit="return false;">
                        <div class="row">
                            <div class="medium-12 columns">
                                <input name="nome" id="rvwname" placeholder="Nome *" required="required" type="text">
                            </div>
                            <div class="medium-12 columns">
                                <input name="email" id="rvwemail" placeholder="E-mail * Não será divulgado" required="required" type="text">
                            </div>
                        </div>
                        <div class="row">
                            <div class="medium-12 columns">
                                <textarea name="mensagem" id="rvwmessage" rows="3" placeholder="Mensagem" required="required"></textarea>
                            </div>
                        </div>
                        <a class="button btn-send" href="#" onclick="enviarComentario(); return false;">Enviar Comentário</a>
                    </form>
                </div>
                
            </div>
        </div>
    </section>

    <?php include_once 'inc/footer.php'; ?>

</body>
</html>