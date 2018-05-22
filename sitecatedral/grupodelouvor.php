<?php 

include_once 'inc/header.php'; 

$conteudo = obterConteudoPorId("2c9f8a7463801a0201638572416e000b");

$titulo = $conteudo->titulo;

$imagens = obterImagens($conteudo->pasta->id);

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

?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <section class="dark-line-section">
        <div class="inner">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="title"><?php echo $titulo; ?></h1>
                    <nav class="breadcrumbs">
                        <a href="<?php echo URLSITE; ?>home">Home</a>
                        <a class="current" href="<?php echo URLSITE; ?>grupodelouvor"><?php echo $titulo; ?></a>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    
    <section class="blog-section" id="blog">
        <div class="inner">
            <div class="row">
                <div class="medium-12 columns">
                    <div class="blog-item">
                        <div class="info">
                            <h3 class="title"><?php echo $titulo; ?></h3>

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