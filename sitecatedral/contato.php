<?php 
include_once 'inc/header.php'; 
?>
        
</head>
<body id="pageTop" class="fixed-navbar">

    <?php include_once 'inc/menu.php'; ?>

    <section class="dark-line-section">
        <div class="inner">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="title">Contato</h1>
                    <nav class="breadcrumbs">
                        <a href="#">Home</a>
                        <a class="current" href="#">Contato</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>



    <!-- ***** Light line section ***** -->
    <section class="light-line-section" id="contactFormSection">
        <div class="inner">
            <div class="row">
                <div class="medium-6 columns info">
                    <div class="row">
                        <div class="medium-12 columns">
                            <div class="row">
                                <div class="medium-12 columns contact-detail">
                                    <span class="icon"><i class="fa fa-map-marker"></i></span>
                                    <div class="text">
                                        <h4>Endereço</h4>
                                        <p>Rua Sestilho Elizeu Possamai 196, Bairro Jardim Glória<br>Bento Gonçalves/RS</p>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="medium-12 columns contact-detail">
                                    <span class="icon"><i class="fa fa-envelope"></i></span>
                                    <div class="text">
                                        <h4>E-mail</h4>
                                        <p><a href="mailto:contato@catedralquadrangular.com.br">contato@catedralquadrangular.com.br</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="medium-6 columns">
                    <form class="light" id="contactForm" onsubmit="return false;">
                        <input type="hidden" name="tipo" value="2c9f8a7463801a02016399eeb2a40017" />
                        <div class="row">
                            <div class="medium-6 columns">
                                <div class="row collapse">
                                    <div class="small-10 columns">
                                        <input type="text" name="nome" id="nome" placeholder="Nome">
                                    </div>
                                    <div class="small-2 columns">
                                        <span class="postfix"><i class="fa fa-user"></i></span>
                                    </div>
                                </div>
                            </div><!-- .columns -->
                            <div class="medium-6 columns">
                                <div class="row collapse">
                                    <div class="small-10 columns">
                                        <input type="text" name="telefone" id="telefone" placeholder="Telefone">
                                    </div>
                                    <div class="small-2 columns">
                                        <span class="postfix"><i class="fa fa-mobile"></i></span>
                                    </div>
                                </div>
                            </div><!-- .columns -->
                        </div><!-- .row -->
                        <div class="row">
                            <div class="medium-12 columns">
                                <div class="row collapse">
                                    <div class="small-10 columns">
                                        <input type="text" name="email" id="email" placeholder="E-mail">
                                    </div>
                                    <div class="small-2 columns">
                                        <span class="postfix"><i class="fa fa-envelope"></i></span>
                                    </div>
                                </div>
                            </div><!-- .columns -->
                        </div><!-- .row -->
                        <div class="row">
                            <div class="medium-12 columns">
                                <div class="row collapse postfix-radius">
                                    <div class="small-12 columns">
                                        <textarea placeholder="Mensagem..." rows="10" name="mensagem" id="mensagem"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div><!-- .row -->
                        <div class="row">
                            <div class="medium-12 columns">
                                <a href="#" class="button btn-send" onclick="enviarContato(); return false;" ><i class="fa fa-paper-plane"></i> Enviar</a>
                            </div>
                        </div><!-- .row -->
                    </form>
                </div>
            </div><!-- .row -->
        </div>
    </section>



    <!-- ***** Google map section ***** -->
    <section>
        <div class="inner">
            <div class="map-canvas" id="contactMap">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1692.2237589565502!2d-51.52867017563228!3d-29.175886604562372!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x951c3cbbda9dd3d9%3A0xe13fe596c952b3ef!2sCatedral+Quadrangular!5e0!3m2!1spt-BR!2sbr!4v1525636832489" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
            </div>
        </div>
    </section>



    <?php include_once 'inc/footer.php'; ?>
    
    <script>
    function enviarContato(){
    	//openLoad();
    	var dados = $("form#formContato").serializeArray();
    	$.ajax({
    		method: "POST",
    		url: "<?php echo URLSITE; ?>service/service.php?acao=enviaContato",
    		data: dados,
    		type: 'POST',
    		success: function(dados) {
    			//closeLoad();
    			$("#msgContato").html("");
    			if(dados == "sucesso"){
    				$("#btnEnviar").remove();
    				$("#msgContato").html('<div class="alert alert-success wow fadeInLeft delay-03s" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><strong>Sucesso </strong>ao enviar contato!</div>');
    			}else{
    				$("#msgContato").html('<div class="alert alert-danger wow fadeInLeft delay-03s" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><strong>Falha </strong> ao enviar contato. Tente novamente.</div>');
    			}
    		}
    	});
    }

    </script>

</body>
</html>