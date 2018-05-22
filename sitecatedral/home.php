<?php 

include_once 'inc/header.php'; 

$blogs = listarConteudo("40288a826328e88a016328e92bc90000",10,1);

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
                            <div class="small-4 columns">
                                <a href="' . URLSITE . 'artigo/' . $blog->id . '">
                                    <img src="' . $img . '" alt="' . $blog->titulo . '">
                                </a>
                            </div>
                            <div class="small-8 columns">
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

/**/

$grupodelouvor = obterConteudoPorId("2c9f8a7463801a0201638572416e000b");
$imggrupodelouvor = URLSITE . 'imagem.php?id=' . $grupodelouvor->pasta->id. '&a=ps&altura=600&largura=600';
?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <?php include_once 'inc/banner.php'; ?>

    <div class="events-section" id="eventsSection">
        <div class="inner">
            <div class="row">
                <div class="medium-8 columns">
                	<?php echo $html; ?>
                </div>
				<div class="medium-4 columns">
					<div class="blog-item">
                        <div class="element">
                        	<a href="<?php echo URLSITE; ?>grupodelouvor">
                            	<img src="<?php echo $imggrupodelouvor; ?>" alt="Grupo de Louvor">
                            </a>
                        </div>
                        <div class="element">
                            <h3 class="title"><a href="<?php echo URLSITE; ?>grupodelouvor">Grupo de Louvor</a></h3>
                            <p>Conheça mais sobre o ministério de Louvor da Catedral Quadrangular</p>
                        </div>
                    </div>
				</div>
                
            </div>
        </div>
    </div>

    <?php include_once 'inc/footer.php'; ?>

</body>
</html>