<?php 
$banners = listarBanners();

$htmlBanner = '';
$count = 0;
foreach($banners as $banner){
    //$img = URL . 'api/arquivo/getPasta/' . $banner->pasta->id;
    //$img = URL . 'api/arquivo/getPastaImage/' . $banner->pasta->id . '/1900/1200';
	$img = URLSITE . 'imagem.php?id=' . $banner->pasta->id . '&a=pasta&altura=1900&largura=1200';
	
	$active = '';
	if($count == 0){
		$active = 'active';
	}
	
	$htmlBanner .= '<div class="item" style="background-image:url(\'' . $img . '\');">
                    <div class="caption">
                        <h2 class="animated2" data-animation-delay="200">' . $banner->titulo . '</h2>
                        <p class="animated2" data-animation-delay="200">' . $banner->texto1 . '</p>
                    </div>
                </div>';
	$count++;

}

if($count > 0){
?>

<section class="full-slider-section" id="homeHeadSlider">
    <div class="inner">
        <div class="carousel-wrapper">
            <div class="owl-carousel full-slider-carousel">
                <?php echo $htmlBanner; ?>
            </div>
        </div>
    </div>
</section>

<?php } ?>