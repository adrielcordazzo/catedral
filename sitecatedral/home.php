<?php 

include_once 'inc/header.php'; 

$blogs = listarConteudo("40288a826328e88a016328e92bc90000",10,1);

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
                </div>
                
            </div>
        </div>
    </div>

    <?php include_once 'inc/footer.php'; ?>

</body>
</html>