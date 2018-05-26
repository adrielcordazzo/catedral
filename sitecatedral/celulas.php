<?php 

include_once 'inc/header.php'; 

$celulas = listarCelulas();

$html = '';
foreach($celulas as $celula){
    
    $html .= '<div class="blog-item" style="padding:20px;">
                        <h3>' . $celula->nome . '</h3>
                        <div class="row">
                            <div class="medium-4 columns">
                                <h4>' . $celula->dia . '</h4>
                                <h4>' . $celula->hora . '</h4>
                                ' . $celula->endereco . '
                            </div>
                            <div class="medium-8 columns">
                                ' . $celula->descricao . '
                            </div>
                            <!--div class="medium-2 columns">
                                
                            </div-->
                        </div>
                    </div>';
}

?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <section class="dark-line-section">
        <div class="inner">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="title">Células</h1>
                    <nav class="breadcrumbs">
                        <a href="<?php echo URLSITE; ?>home">Home</a>
                        <a class="current" href="<?php echo URLSITE; ?>celulas">Células</a>
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
                </div>
                
            </div>
        </div>
    </div>

    <?php include_once 'inc/footer.php'; ?>

</body>
</html>