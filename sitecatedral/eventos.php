<?php 

include_once 'inc/header.php'; 

$urlAux = explode('/', $_GET['p']);

$ano = $urlAux[1];
$mes = $urlAux[2];

if(!$ano >= 2018){
    $ano = date("Y");
}

if(!$mes >=1){
    $mes = date("m");
}

$eventos = listarEventos($ano,$mes);


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

$htmlEventos = '';
foreach($eventos as $evento){
    
    $data = explode("/",$evento->data);
    $dia = $data[0];
    $mes = $data[1];
    $ano = $data[2];
    
    $hora = explode(":",$evento->hora);
    
    $diasemananumero = date('w', strtotime($ano . '-' . $mes . '-' . $dia));
    
    //$img = URLSITE . 'imagem.php?id=' . $evento->pasta->id. '&a=ps&altura=600&largura=600';
    
    $htmlEventos .= '<div class="blog-item" style="padding:20px;">
                        <h3>' . $evento->titulo . '</h3>
                        <div class="row">
                            <div class="medium-4 columns"> 
                                <div class="timer-count-down">
                                    <div class="item"><span>' . $diasemana[$diasemananumero] . '</span>Dia</div>
                                    <div class="item"><span>' . $dia . '</span>' . $meses[$mes] . '</div>
                                    <div class="item"><span>' . $hora[0] . '</span>Horas</div>
                                    <div class="item"><span>' . $hora[1] . '</span>Min</div>
                                </div>  
                            </div>
                            <div class="medium-6 columns">
                                <h4>' . $evento->descricao . '</h4>
                            </div>     
                            <!--div class="medium-2 columns">
                                <img src="' . $img . '" width="100%" />
                            </div-->
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
                    <h1 class="title">Eventos</h1>
                    <nav class="breadcrumbs">
                        <a href="<?php echo URLSITE; ?>home">Home</a>
                        <a class="current" href="<?php echo URLSITE; ?>eventos">Eventos</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>

	<div class="events-section" id="eventsSection">
        <section class="upcoming-event-section" id="upcomingEventSection">
            <div class="inner">
    			<div class="row">
                    <div class="medium-12 columns">
                    	<div class="filters row">
                            	<select id="ano" style="width:150px;">
                                	<?php 
                                	for($i = 2018; $i <= 2020; $i++){
                                	    $selAno = ''; if($ano == $i){$selAno='selected="selected"';}
                                	    echo '<option value="'.$i.'" ' . $selAno . '>' . $i . '</option>';
                                	}
                                	?>
                                </select>
                                <select id="mes" style="width:150px;">
                                    <?php 
                                    
                                	for($i = 1; $i <= 12; $i++){
                                	    $j = $i; if($j < 10){$j = "0".$j;}
                                	    $selMes = ''; if($mes == $j){$selMes='selected="selected"';}
                                	    echo '<option value="'.$j.'" ' . $selMes . '>'.$meses[$j].'</option>';
                                	}
                                	?>
                                </select>
                        </div>
                    	<?php echo $htmlEventos; ?>
                        <div class="pagination-centered">
                            <ul class="pagination" role="menubar" aria-label="Pagination">
                                <?php echo $paginacao; ?>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <?php include_once 'inc/footer.php'; ?>
    
    <script>
    $("#mes").change(function(){
		buscarEventos();
    });
    $("#ano").change(function(){
		buscarEventos();
    });
    function buscarEventos(){
		var mes = $("#mes").val();
		var ano = $("#ano").val();
		window.location.assign('<?php echo URLSITE; ?>eventos/'+ano+'/'+mes);
		return false;
	}
	</script>

</body>
</html>