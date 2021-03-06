<?php 

include_once 'inc/header.php'; 

unset($_SESSION["busca"]);

$urlAux = explode('/', $_GET['p']);

if(!isset($urlAux[1]) || !$urlAux[1] >= 1)
    $id = '0';
else
    $id = $urlAux[1];

$conteudo = obterConteudoPorId($id);


if(!$conteudo){
    die( Header( "Location: " . URLSITE . "artigos" ) );
}

$titulo = $conteudo->titulo;

$imagens = obterImagens($conteudo->pasta->id);

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
$data = explode("/",$conteudo->criado);
$dia = $data[0];
$mes = $data[1];
$ano = $data[2];

/**/

$htmlImagens = '';
$count = 0;
if(count($imagens)>0){
    foreach($imagens as $imagem){
        
            //$img = URL . 'api/arquivo/getArquivo/' . $imagem->id;
            $img = URLSITE . 'imagem.php?id=' . $imagem->id. '&a=ar&altura=900&largura=900';
            
            $legenda = $conteudo->titulo;
            if($imagem->legenda){
                $legenda = $imagem->legenda;
            }
            
            $htmlImagens .= '<li class="item year2014" data-src="' . $img . '" style="cursor:pointer;">
                                    <div class="item-container">
                                            <figure>
                                                <img src="' . $img . '" alt="">
                                                <figcaption>
                                                    <div class="row">
                                                        <div class="small-12 columns">
                                                            <span>' . $legenda . '</span>
                                                        </div>
                                                    </div>
                                                </figcaption>
                                            </figure>
                                    </div>
                                </li>';
            $count++;
    }
}

/**/

$blogs = listarConteudo("40288a826328e88a016328e92bc90000",5,1);

$htmlUltimas = '';
foreach($blogs->list as $blog){
    
    $img = URLSITE . 'imagem.php?id=' . $blog->pasta->id. '&a=ps&altura=400&largura=400';
    
    $htmlUltimas .= '<div class="row item">
                <div class="medium-4 columns photo">
                    <img alt="" src="' . $img . '">
                </div>
                <div class="medium-8 columns">
                    <h1 class="title">' . $blog->titulo . '</h1>
                    <span class="date"><i class="fa fa-calendar"></i> ' . $blog->criado . '</span>
                    <!--ul class="stars">
                        <li class="active"><a href="#" class="icon"><i class="fa fa-star"></i></a></li>
                        <li class="active"><a href="#" class="icon"><i class="fa fa-star"></i></a></li>
                        <li class="active"><a href="#" class="icon"><i class="fa fa-star"></i></a></li>
                        <li><a href="#" class="icon"><i class="fa fa-star"></i></a></li>
                        <li><a href="#" class="icon"><i class="fa fa-star"></i></a></li>
                    </ul-->
                    <div class="clearfix"></div>
                    <a href="' . URLSITE . 'artigo/' . $blog->id . '"" class="button">Detalhes</a>
                </div>
            </div>';
}

/**/

$comentarios = listarComentarios($id,50);

$htmlComentarios = '';
foreach($comentarios->list as $comentario){
    
    $htmlComentarios .= '<li class="item">
                            <hr />
                            <div class="info">
                                <h3 class="comment-name">' . $comentario->nome . '</h3>
                                    <span class="date">
                                        <i class="fa fa-calendar"></i> ' . $comentario->criado . '
                                    </span>
                                <p class="comment-text">'.$comentario->mensagem.'</p>
                            </div>
                         </li>';
}

/**/

incrementarVisualizacaoConteudo($id);
?>
        
</head>
<body id="pageTop" class="fixed-navbar">

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/pt_BR/all.js#xfbml=1&appId=242554282548198";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
    </script>

    <?php include_once 'inc/menu.php'; ?>

    <section class="dark-line-section">
        <div class="inner">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="title"><?php echo $titulo; ?></h1>
                    <nav class="breadcrumbs">
                        <a href="<?php echo URLSITE; ?>home">Home</a>
                        <a href="<?php echo URLSITE; ?>artigos">Artigos</a>
                        <a class="current" href="<?php echo URLSITE; ?>artigo/<?php echo $id; ?>"><?php echo $titulo; ?></a>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    
    <section class="blog-section" id="blog">
        <div class="inner">
            <div class="row">
                <div class="medium-8 columns">
                    <div class="blog-item">
                        <div class="info">
                            <div class="date">
                                <span class="year"><?php echo $dia; ?></span>
                                <span class="month"><?php echo $meses[$mes]; ?></span>
                                <span class="month"><?php echo $ano; ?></span>
                            </div>
                            <div class="format">
                                <i class="flaticon-picture13"></i>
                            </div>
                            <h3 class="title"><?php echo $titulo; ?></h3>
                            <div class="meta">
                                <ul>
                                    <li>Em <?php echo $conteudo->conteudotipo->tipo; ?></li>
                                    <li><i class="fa fa-eye"></i> (<?php echo $conteudo->visualizacoes; ?>) Visualizações</li>
                                    <li class="pull-right">
                                    	<div class="fb-like float-right" data-href="<?php echo URLSITE; ?>artigo/<?php echo $id; ?>" data-send="true" 
                        				data-layout="button_count" data-width="450" data-show-faces="true"></div>
                                    </li>
                                </ul>
                            </div>
                            <?php echo nl2br($conteudo->conteudo); ?>
                        </div>
                    </div>
                    
                    <section class="gallery-section" id="gallery">
                        <div class="inner">
                            <div class="row">
                                <ul class="gallery-items four-columns clearfix" id="lightgallery">
                                    <?php echo $htmlImagens; ?>
                                </ul>
                            </div>
                        </div>
                    </section>
                    
                    <ul class="comment-list">
                    	<?php echo $htmlComentarios; ?>
                    </ul>
                    
                    <h3 class="text-center">Enviar Comentário</h3>
                    <form name="reviewForm" id="rvwForm" method="post" onsubmit="return false;">
                        <input name="conteudo" id="rvwsec" value="<?php echo $id; ?>" type="hidden">
                        <div class="row">
                            <div class="medium-6 columns">
                                <input name="nome" id="rvwname" placeholder="Nome *" required="required" type="text">
                            </div>
                            <div class="medium-6 columns">
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
                <div class="medium-4 columns">

                    <div class="blog-box">
                        <div class="inner">
                            <div class="ws-title left-line">
                                <h1 class="title">Últimos Artigos</h1>
                            </div>
                            <div class="content">
                                <?php echo $htmlUltimas; ?>
                            </div>
                            
                            <div class="ws-title left-line">
                                <h1 class="title">Facebook</h1>
                            </div>
                            <div class="content">
                                <div class="fb-comments" data-href="<?php echo URLSITE; ?>artigo/<?php echo $id; ?>" data-width="100%" data-num-posts="5" style="width:100%; max-width:450px;"></div>
                            </div>
                            
                            
                            
                        </div>
                    </div>
                    
                    
                    
                </div>
            </div>
        </div>
    </section>

    <?php include_once 'inc/footer.php'; ?>
    
    <script>
    $('#lightgallery').lightGallery({
        hash:false,
        thumbnail:true,
        download:false,
        zoom:true,
        fullScreen:false,
        autoplay: false,
        autoplayControls: false,
        share: false,
        actualSize: false
    });

    

    </script>

</body>
</html>