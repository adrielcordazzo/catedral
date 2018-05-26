<?php 

include_once 'inc/header.php'; 

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

$diasemana = array('Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab');

$eventos = listarProximosEventos();
$htmlEventos = '';
foreach($eventos as $evento){
    
    $data = explode("/",$evento->data);
    $dia = $data[0];
    $mes = $data[1];
    $ano = $data[2];
    
    $hora = explode(":",$evento->hora);
    
    $diasemananumero = date('w', strtotime($ano . '-' . $mes . '-' . $dia));
    
    $htmlEventos .= '<div class="medium-4 columns">
                        <h3>' . $evento->titulo . '</h3>
                        <div class="timer-count-down">
                            <div class="item"><span>' . $diasemana[$diasemananumero] . '</span>Dia</div>
                            <div class="item"><span>' . $dia . '</span>' . $meses[$mes] . '</div>
                            <div class="item"><span>' . $hora[0] . '</span>Horas</div>
                            <div class="item"><span>' . $hora[1] . '</span>Min</div>
                        </div>
                    </div>';
}
/**/


$blogs = listarConteudo("40288a826328e88a016328e92bc90000",10,1);

$html = '';
foreach($blogs->list as $blog){
    
    $img = URLSITE . 'imagem.php?id=' . $blog->pasta->id. '&a=ps&altura=600&largura=600';
    
    
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


$destaques = listarConteudo("2c9f8a74639a8fca01639a93cae200be",10,1);

$htmlDestaques = '';
foreach($destaques->list as $blog){
    
    $img = URLSITE . 'imagem.php?id=' . $blog->pasta->id. '&a=ps&altura=600&largura=600';
    
    $htmlDestaques .= '<div class="blog-item">
                    <div class="element">
                    	<a href="' . URLSITE . 'artigo/' . $blog->id . '">
                        	<img src="' . $img . '" alt="' . $blog->titulo . '">
                        </a>
                    </div>
                    <div class="element">
                        <h3 class="title"><a href="' . URLSITE . 'artigo/' . $blog->id . '">' . $blog->titulo . '</a></h3>
                        <p>' . $blog->subtitulo . '</p>
                    </div>
                </div>';
}

?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <?php include_once 'inc/banner.php'; ?>
    
    <?php if($htmlEventos != ''){ ?>
    <section class="upcoming-event-section" id="upcomingEventSection">
        <div class="inner">
            <div class="row">
                <div class="medium-2 columns">
                    <h3><i class="flaticon-calendar146"></i>Próximos</h3>
                    <h4>Eventos</h4>
                </div>
                <?php echo $htmlEventos; ?>
                <div class="medium-2 columns">
                    <a href="<?php echo URLSITE; ?>eventos/<?php echo date("Y")."/".date("m"); ?>" class="button">Todos os Eventos</a>
                </div>
            </div>
        </div>
    </section>
    <?php } ?>

    <div class="events-section" id="eventsSection">
        <div class="inner">
            <div class="row">
                <div class="medium-8 columns">
                	<?php echo $html; ?>
                </div>
				<div class="medium-4 columns">
					<div class="ws-title left-line">
	                    <h1 class="title">Destaques</h1>
                    </div>
					<?php echo $htmlDestaques; ?>
				</div>
                
            </div>
        </div>
    </div>

    <?php include_once 'inc/footer.php'; ?>

</body>
</html>