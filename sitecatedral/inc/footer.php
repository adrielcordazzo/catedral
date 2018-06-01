

        <footer class="footer-section" id="footerSection">
            <div class="content">
                <div class="inner">
                    <div class="row">
                        <div class="large-3 columns">
                            <img alt="" src="<?php echo URLSITE; ?>img/logocatedral-vertical-branco.png">
                        </div>
                        <div class="large-3 columns info-box">

                            <div class="facebook-likebox">
        						<iframe src="//www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2Fcatedralquadrangular&amp;colorscheme=light&amp;show_faces=true&amp;header=true&amp;stream=true&amp;show_border=true" width="100%" height="300px"></iframe>
        					</div>
        					
        					

                        </div>
                        <div class="large-3 columns blogroll-box">
                            <h1>Menu</h1>
                            <ul class="list-arrow">
                                <li><a href="<?php echo URLSITE; ?>home">Home</a></li>
                                <li><a href="<?php echo URLSITE; ?>artigos">Artigos</a></li>
                                <li><a href="<?php echo URLSITE; ?>historia">História</a></li>
                                <li><a href="<?php echo URLSITE; ?>celulas">Células</a></li>
                                <li><a href="<?php echo URLSITE; ?>contato">Contato</a></li>
                            </ul>
                        </div>
                        <div class="large-3 columns contact-box">
                            <img alt="" src="<?php echo URLSITE; ?>img/logocatedral-branco.png">
                            <ul class="contact">
                                <li>Rua Sestilho Elizeu Possamai 196, Bairro Jardim Glória - Bento Gonçalves/RS</li>
                                <li>contato@catedralquadrangular.com.br</li>
                            </ul>
                            <div class="text-center">
                                <a href="<?php echo URLSITE; ?>contato" class="button btn-dark">Entre em Contato</a>
                            </div>
                            <ul class="socials">
                                <li><a href="http://www.facebook.com.br/catedralquadrangular" class="icon" target="_blank"><i class="fa fa-facebook-official"></i></a></li>
                                <li><a href="http://www.youtube.com/catedralquadrangular" class="icon" target="_blank"><i class="fa fa-youtube-square"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="copyright">
                <div class="inner">
                    <div class="row">
                        <div class="medium-10 columns">
                            Desenvolvido por <a href="http://www.xlabi.com.br" target="_blank"><img src="<?php echo URLSITE; ?>img/logo-xlabi-branco.png" width="60" style="margin-top: -5px;" /> Soluções Empresariais</a>
                        </div>
                        <div class="medium-2 columns">
                            <a href="pageTop" class="scroll-to"><i class="fa fa-angle-up"></i>Topo</a>
                        </div>
                    </div>
                </div>
            </div>
            
        </footer>


        <div class="ps-modal-overlay" id="searchModal">
            <a class="overlay-close"></a>
            <div class="search row">
                <div class="large-12 columns">
                    <form onsubmit="return false;">
                        <div class="row collapse">
                            <div class="small-8 columns">
                                <input type="text" placeholder="Busca..." id="busca">
                            </div>
                            <div class="small-4 columns">
                                <a href="#" onclick="buscarPalavra();return false;" class="button"><i class="fa fa-search"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <a class="exit-off-canvas"></a>

    </div>
</div>

<script src="<?php echo URLSITE; ?>assets/js/modernizr.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.cookie.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/foundation.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/classie.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/owl.carousel.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.validate.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.countdown.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.counterup.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.scrollTo.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.waypoints.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.countdown.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.scrollTo.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/masonry.pkgd.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/jquery.swipebox.js"></script>
<script src="<?php echo URLSITE; ?>assets/js/lightgallery.js"></script>

<script src="<?php echo URLSITE; ?>assets/js/application.js"></script>

<script>
    APP.init();
    APP.initPlugins();

    function buscarPalavra(){
		var palavra = $("#busca").val();

		$.ajax({
			method: "POST",
			url: "service/service.php?acao=buscarPalavra",
			data: {palavra:palavra},
			 type: 'POST',
				success: function(dados) {
					window.location.assign("<?php echo URLSITE; ?>artigos");
				}
		});
		return false;
    }

    function enviarComentario(){
    	//openLoad();
    	var dados = $("form#rvwForm").serializeArray();
    	$.ajax({
    		method: "POST",
    		url: "<?php echo URLSITE; ?>service/service.php?acao=enviaComentario",
    		data: dados,
    		type: 'POST',
    		success: function(dados) {
    			//closeLoad();
    			$("#msgContato").html("");
    			if(dados == "sucesso"){
    				$("#btnEnviar").remove();
    				$("#msgContato").html('<div class="alert alert-success wow fadeInLeft delay-03s" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><strong>Sucesso </strong>ao enviar contato!</div>');
    				location.reload();	
    			}else{
    				$("#msgContato").html('<div class="alert alert-danger wow fadeInLeft delay-03s" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><strong>Falha </strong> ao enviar contato. Tente novamente.</div>');
    			}
    		}
    	});
    }

</script>