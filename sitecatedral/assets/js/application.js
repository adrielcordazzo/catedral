// APPLICATION.JS
//--------------------------------------------------------------------------------------------------------------------------------
// This is application javascript file that contains custom javascripts and initialization used in this template and plugins */
// -------------------------------------------------------------------------------------------------------------------------------
// Template Name: Hope - Church Responsive HTML5 Template
// Template URL: http://church.pistaciatheme.com
// Description: Flat church and congregation HTML5 template
// File name: application.js
// Author: Pistaciatheme
// Support: http://themeforest.net/user/pistaciatheme
// Version: 1.4
// -------------------------------------------------------------------------------------------------------------------------------

// -----------------------------------------------
// Application
// -----------------------------------------------

var APP = {
    isMobile: function(){

        "use strict";

        if (navigator.userAgent.match(/Android/i) ||
            navigator.userAgent.match(/webOS/i) ||
            navigator.userAgent.match(/iPhone/i) ||
            navigator.userAgent.match(/iPad/i) ||
            navigator.userAgent.match(/iPod/i) ||
            navigator.userAgent.match(/BlackBerry/i) ||
            navigator.userAgent.match(/Windows Phone/i)) {
            return true;
        } else {
            return false;
        }
    },
    windowWidth: 0,
    windowHeight: 0,
    /**
     * initialize the application
     * global scripts
     *
     * foundation : run foundation framework
     * pageLoader : page loader script
     * svgIcons : load svg icon file and prepend to body
     * fullPageSection : resize full-page-section height to window
     */
    init:function(){

        "use strict";

        APP.windowWidth = window.innerWidth;
        APP.windowHeight = window.innerHeight;

        window.addEventListener("resize", function(){
            APP.windowWidth = window.innerWidth;
            APP.windowHeight = window.innerHeight;
        });

        APP.themeSwitcher();

        jQuery(document).foundation();

        APP.fullPageSection();
        APP.pageLoader();

    },
    /**
     * initialize the theme plugins
     *
     * swipebox : touchable lightbox
     * psModal : custom modal window
     * owlCarousels : Touch enabled jQuery plugin that lets you create a beautiful responsive carousel slider.
     * countDown : The Final Countdown for jQuery
     * contactForm : Contact form ajax
     * fadeElements : Fade effect when scrolling down
     * parallax : parallax bg elements
     * counterup : Count up numbers
     * elementsAnimation : Add css animate class if element is in the window
     * flexslider : Flexslider plugin
     * scrollTo : scroll to element
     * mapster: google map with points
     * offcanvasFavourite : Favourite element actions
     * foundationAccordionAnimation : Foundation accordian add animation
     * fullCalendar : jQuery fullCalendar plugin
     * list : Filter function
     * gallery : filtered masonry
     * navsideMenu : left collapse menu
     * noUiSlider :range slider without bloat
     * responsiveTable : Make Your Tables Responsive
     *
     */
    initPlugins : function(){
        APP.swipebox();
        APP.psModal();
        APP.owlCarousels();
        APP.countDown();
        APP.contactForm();
        APP.commentForm();
        APP.fadeOutElements();
        APP.parallax();
        APP.countUp();
        APP.elementsAnimation();
        APP.flexslider();
        APP.scrollTo();
        APP.mapster();
        APP.timeline();
        APP.offcanvasFavourite();
        APP.foundationAccordionAnimation();
        APP.fullCalendar();
        APP.list();
        APP.gallery();
        APP.navsideMenu();
        APP.noUiSlider();
        APP.responsiveTable();
        APP.revolutionSlider();
    }
};

APP.swipebox = function(){
    if(document.querySelector('.swipebox')) {
        jQuery('.swipebox').swipebox(
            {
                useCSS : true,
                initialIndexOnArray: 0,
                hideCloseButtonOnMobile : false,
                hideBarsDelay : 3000,
                videoMaxWidth : 1140,
                beforeOpen: function(){},
                afterOpen: null,
                afterClose: function(){},
                loopAtEnd: false,
                autoplayVideos: false,
                queryStringData: {},
                toggleClassOnLoad: ''
            }
        );
    }
};

APP.revolutionSlider = function(){

    "use strict";

    jQuery(document).ready(function() {
        if(document.getElementById("slider1")) {
            jQuery("#slider1").revolution({
                sliderType:"standard",
                sliderLayout:"fullwidth",
                delay:9000,
                navigation: {
                    arrows:{enable:true}
                },
                gridwidth:1230,
                gridheight:520
            });
        }
    });
};


/**
 * NoUiSlider plugin
 * noUiSlider is a range slider without bloat.
 * It offers a ton off features, and it is as small, lightweight and minimal as possible
 * which is great for mobile use on the many supported devices, including iPhone, iPad,
 * Android devices & Windows (Phone) 8 desktops, tablets and all-in-ones.
 * It works on desktops too, of course!
 */
APP.noUiSlider = function () {

    "use strict";

    if (document.getElementById("uiSlider")) {
        var elem = jQuery("#uiSlider");
        elem.noUiSlider({
            start: [20, 80],
            connect: true,
            range: {
                'min': 0,
                'max': 100
            }
        });
        elem.Link('lower').to(jQuery('#valueLower'), 'html');
        elem.Link('upper').to(jQuery('#valueUpper'), 'html');
    }
};


/**
 * Make Your Tables Responsive
 */
APP.responsiveTable = function(){

    "use strict";

    function splitTable(original) {
        original.wrap("<div class='table-wrapper' />");
        var copy = original.clone();
        copy.find("td:not(:first-child), th:not(:first-child)").css("display", "none");
        copy.removeClass("responsive");
        original.closest(".table-wrapper").append(copy);
        copy.wrap("<div class='pinned' />");
        original.wrap("<div class='scrollable' />");
        setCellHeights(original, copy);
    }

    function unsplitTable(original) {
        original.closest(".table-wrapper").find(".pinned").remove();
        original.unwrap();
        original.unwrap();
    }

    function setCellHeights(original, copy) {
        var tr = original.find('tr'),
            tr_copy = copy.find('tr'),
            heights = [];
        tr.each(function (index) {
            var self = jQuery(this),
                tx = self.find('th, td');
            tx.each(function () {
                var height = jQuery(this).outerHeight(true);
                heights[index] = heights[index] || 0;
                if (height > heights[index]) heights[index] = height;
            });
        });
        tr_copy.each(function (index) {
            jQuery(this).height(heights[index]);
        });
    }

    jQuery(document).ready(function() {
        if (document.querySelector('.table.responsive')) {
            var switched = false;
            var updateTables = function () {
                if ((jQuery(window).width() < 767) && !switched) {
                    switched = true;
                    jQuery("table.responsive").each(function (i, element) {
                        splitTable(jQuery(element));
                    });
                    return true;
                }
                else if (switched && (jQuery(window).width() > 767)) {
                    switched = false;
                    jQuery("table.responsive").each(function (i, element) {
                        unsplitTable(jQuery(element));
                    });
                }
            };
            jQuery(window).load(updateTables);
            jQuery(window).on("redraw", function () {
                switched = false;
                updateTables();
            });
            jQuery(window).on("resize", updateTables);
        }
    });
};


/**
 * Navside toggle menu
 */
APP.navsideMenu = function(){

    "use strict";

    if(document.querySelector('.nav-side-menu')) {

        jQuery('.nav-side-menu li').each(function(){
            var _this = jQuery(this);
            if(_this.has('ul').length > 0){
                _this.addClass('has-dropdown');
            }
        });
        jQuery('.nav-side-menu a').on('click', function (e) {
            var _this = jQuery(this);
            var parent = _this.parent('li');
            if (parent.has('ul').length>0){
                e.preventDefault();
                parent.toggleClass('active');
                _this.next('ul').slideToggle();
            }
        });
    }
};


/**
 * Filtered masonry gallery
 */
APP.gallery = function () {

    "use strict";

    jQuery.fn.filteredPortfolio = function (options) {
        var elements = [];
        var filters = [];
        var init = function (container) {
            proc(container);
            container.find(options.itemSelector).each(function() {
                elements.push(jQuery(this));
            });
            Foundation.utils.image_loaded(jQuery('img',container),function(){
                container.masonry();
            });
        };

        var filterItems = function (selector) {
            var result = [];
            jQuery(elements).each(function (item) {
                jQuery(selector).each(function (index, sel) {
                    if (elements[item].is(sel)) {
                        if (jQuery.inArray(elements[item], result) === -1)
                            result.push(elements[item]);
                    }
                });
            });
            return result;
        };

        var reload = function (container, items) {
            container.empty();
            jQuery(items).each(function () {
                jQuery(container).append($(this));
            });
            container.masonry('reloadItems');
            container.masonry();
        };

        var hashFilter = function (container) {
            var hash = window.location.hash.replace("#", "");
            if (jQuery.inArray(hash, filters) !== -1) {
                reload(container, jQuery('.' + hash));
            }
        };

        var proc = function (container) {
            jQuery(options.filtersGroupSelector).find('input[type=checkbox]').each(function () {
                jQuery(this).change(function () {
                    var selector = [];
                    jQuery(this).parent().toggleClass('active');
                    jQuery(options.filtersGroupSelector).find('input[type=checkbox]').each(function () {
                        if (jQuery(this).is(':checked')) {
                            selector.push('.' + jQuery(this).val());
                        }
                    });
                    var items = elements;
                    if (selector.length > 0) {
                        items = filterItems(selector);
                    }
                    reload(container, items);
                });
            });
        };

        return this.each(function () {
            init(jQuery(this));
        });
    };

    if(document.querySelector('.gallery-items')) {
        jQuery(document).ready(function(){
            jQuery('.gallery-items').filteredPortfolio({
                itemSelector: '.item',
                filtersGroupSelector: '.filters'
            });
        });
    }
};



/**
 * List elements with filter
 */
APP.list = function(){

    "use strict";

    var list = document.querySelector('.list-section');
    if (list){
        jQuery('.filter-view .filter').on('click',function(e){
            var _this = jQuery(this);
            var parent = _this.parents('.inner');
            var listContent = parent.find('.list-content');
            listContent
                .removeClass('thumb')
                .removeClass('list')
                .addClass(_this.data('action'));
            _this
                .addClass('active')
                .parent()
                .siblings()
                .find('.filter')
                .removeClass('active');
            e.preventDefault();
        });
    }
};



/**
 * Full page section
 * change full-page-section class height to window height
 */
APP.fullPageSection = function(){

    "use strict";

    var section = document.querySelector('.full-page-section');
    if(section) {
        jQuery('.full-page-section').css({ 'height' : APP.windowHeight + 'px'});
        jQuery(window).resize(function(){
            jQuery('.full-page-section').css({ 'height' : APP.windowHeight + 'px'});
        });
    }
};



/**
 * fullCalendar
 */
APP.fullCalendar = function(){

    "use strict";

    var calendar = document.getElementById('fullCalendar');
    if(calendar) {
        jQuery(document).ready(function () {
            jQuery('#fullCalendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                defaultDate: '2015-02-12',
                selectable: true,
                selectHelper: true,
                select: function(start, end) {
                    var title = prompt('Event Title:');
                    var eventData;
                    if (title) {
                        eventData = {
                            title: title,
                            start: start,
                            end: end
                        };
                        $('#fullCalendar').fullCalendar('renderEvent', eventData, true);
                    }
                    jQuery('#fullCalendar').fullCalendar('unselect');
                },
                editable: true,
                eventLimit: true,
                events: [
                    {
                        title: 'All Day Event',
                        start: '2015-02-01'
                    },
                    {
                        title: 'Long Event',
                        start: '2015-02-07',
                        end: '2015-02-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2015-02-09T16:00:00'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2015-02-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2015-02-11',
                        end: '2015-02-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2015-02-12T10:30:00',
                        end: '2015-02-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2015-02-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2015-02-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2015-02-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2015-02-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2015-02-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2015-02-28'
                    }
                ]
            });
        });
    }
};



/**
 * Theme switcher
 */
APP.themeSwitcher = function(){

    "use strict";

    if (document.getElementById('themeSwitcher')) {
        jQuery(window).load(function() {
            var themeSwitcher = {
                defaults: {
                    window: jQuery('#themeSwitcher'),
                    opened: false,
                    skin: 'red'
                },
                opts: {},
                button: {},
                close: function () {
                    themeSwitcher.opts.window.removeClass('active');
                },
                open: function () {
                    themeSwitcher.opts.window.addClass('active');
                },
                toggleWindow: function () {
                    if (themeSwitcher.opts.window.hasClass('active')) {
                        themeSwitcher.close();
                    } else {
                        themeSwitcher.open();
                    }
                },
                changeColor: function (color) {
                    jQuery("link[href*='style-']").attr("href", 'assets/css/style-' + color + '.css');
                    jQuery('[data-color="' + color + '"]')
                        .parent()
                        .addClass('active')
                        .siblings()
                        .removeClass('active');
                },
                init: function (param) {
                    themeSwitcher.opts = jQuery.extend(themeSwitcher.defaults, param);
                    themeSwitcher.button = themeSwitcher.opts.window.find('.toggle-button');
                    if (themeSwitcher.opts.opened){
                        themeSwitcher.open();
                    } else {
                        themeSwitcher.close();
                    }
                    themeSwitcher.opts.window.addClass('visible');
                }
            };
            themeSwitcher.init({
                opened: false
            });
            themeSwitcher.button.on("click", function (e) {
                themeSwitcher.toggleWindow();
                e.preventDefault();
            });
            themeSwitcher.opts.window.find('.colors a').on("click", function (e) {
                var that = jQuery(this);
                themeSwitcher.changeColor(that.data('color'));
                e.preventDefault();
            });
            themeSwitcher.opts.window.find('.fixedmenu').on("change", function () {
                var id = jQuery(this).attr('id');
                if (id == 'fixed-navbar') {
                    jQuery('#pageTop').addClass('fixed-navbar');
                } else {
                    jQuery('#pageTop').removeClass('fixed-navbar');
                }
            });

            themeSwitcher.opts.window.find('.boxed-layout').on("change", function () {
                var id = jQuery(this).attr('id');
                if (id == 'boxed-layout') {
                    jQuery('body').addClass('boxed');
                } else {
                    jQuery('body').removeClass('boxed');
                }
            });
        });
    }
};


/**
 * Foundation accordion animation
 */
APP.foundationAccordionAnimation = function(){

    (function(jQuery) {
        jQuery.fn.accordionAnimated = function() {

            var accordion = this,
                items = accordion.find('> li'),
                targets = items.find('.content'),
                options = {
                    active_class : 'active',
                    multi_expand: false,
                    speed : 500,
                    toggleable: true
                };

            jQuery.extend(options, Foundation.utils.data_options(accordion));

            items.each(function(i) {
                jQuery(this).find('a:eq(0)').on('click.accordion', function() {
                    if(!options.toggleable && items.eq(0).hasClass(options.active_class)) {
                        return;
                    }

                    targets.eq(i)
                        .stop(true, true)
                        .slideToggle(options.speed);

                    if(!options.multi_expand) {
                        targets.not(':eq('+i+')')
                            .stop(true, true)
                            .slideUp(options.speed);
                    }
                });
            });
        };
    }(jQuery));

    jQuery('.accordion').accordionAnimated();

};


/**
 * Timeline
 */
APP.timeline = function(){

    var sidescroll = (function() {
        var rows = jQuery('.sidescroll-container > div.ss-row'),
            rowsViewport, rowsOutViewport,
            win = jQuery(window),
            winSize = {},
            anim = false,
            scollPageSpeed = 2000 ,
            scollPageEasing = 'easeInOutExpo',
            hasPerspective = true,
            perspective = hasPerspective && Modernizr.csstransforms3d,
            init = function() {
                getWinSize();
                initEvents();
                defineViewport();
                setViewportRows();
                if( perspective ) {
                    rows.css({
                        '-webkit-perspective' : 600,
                        '-webkit-perspective-origin' : '50% 0%'
                    });
                }
                placeRows();
            },
            defineViewport = function() {
                jQuery.extend( jQuery.expr[':'], {
                    inviewport : function ( el ) {
                        if ( jQuery(el).offset().top < winSize.height ) {
                            return true;
                        }
                        return false;
                    }
                });
            },
            setViewportRows = function() {
                rowsViewport = rows.filter(':inviewport');
                rowsOutViewport = rows.not( rowsViewport )
            },
            getWinSize = function() {
                winSize.width = win.width();
                winSize.height = win.height();
            },
            initEvents = function() {
                jQuery(window).on({
                    'resize.Scrolling' : function( event ) {
                        getWinSize();
                        setViewportRows();
                        rowsViewport.each( function() {
                            jQuery(this).find('div.ss-left')
                                .css({ left   : '0%' })
                                .end()
                                .find('div.ss-right')
                                .css({ right  : '0%' });
                        });
                    },
                    'scroll.Scrolling' : function( event ) {
                        if( anim ) return false;
                        anim = true;
                        setTimeout( function() {
                            placeRows();
                            anim = false;
                        }, 10 );
                    }
                });
            },
            placeRows= function() {
                var winscroll = win.scrollTop(),
                    winCenter = winSize.height / 2 + winscroll;
                rowsOutViewport.each( function(i) {
                    var row = jQuery(this),
                        rowL = row.find('div.ss-left'),
                        rowR = row.find('div.ss-right'),
                        rowT = row.offset().top;
                    if( rowT > winSize.height + winscroll ) {
                        if( perspective ) {
                            rowL.css({
                                '-webkit-transform': 'translate3d(-75%, 0, 0) rotateY(-90deg) translate3d(-75%, 0, 0)',
                                'opacity': 0
                            });
                            rowR.css({
                                '-webkit-transform': 'translate3d(75%, 0, 0) rotateY(90deg) translate3d(75%, 0, 0)',
                                'opacity': 0
                            });
                        }
                        else {
                            rowL.css({ left: '-50%' });
                            rowR.css({ right: '-50%' });
                        }
                    }
                    else {
                        var rowH = row.height(),
                            factor = ( ( ( rowT + rowH / 2 ) - winCenter ) / ( winSize.height / 2 + rowH / 2 ) ),
                            val = Math.max( factor * 50, 0 );
                        if( val <= 0 ) {
                            if( !row.data('pointer') ) {
                                row.data( 'pointer', true );
                            }
                        }
                        else {
                            if( row.data('pointer') ) {
                                row.data( 'pointer', false );
                            }
                        }
                        if( perspective ) {
                            var t= Math.max( factor * 75, 0 ),
                                r= Math.max( factor * 90, 0 ),
                                o= Math.min( Math.abs( factor - 1 ), 1 );

                            rowL.css({
                                '-webkit-transform' : 'translate3d(-' + t + '%, 0, 0) rotateY(-' + r + 'deg) translate3d(-' + t + '%, 0, 0)',
                                'opacity': o
                            });
                            rowR.css({
                                '-webkit-transform': 'translate3d(' + t + '%, 0, 0) rotateY(' + r + 'deg) translate3d(' + t + '%, 0, 0)',
                                'opacity': o
                            });
                        }
                        else {
                            rowL.css({ left: - val + '%' });
                            rowR.css({ right: - val + '%' });
                        }
                    }
                });
            };
        return { init : init };
    })();
    sidescroll.init();
};


/**
 * Mapster
 * Google map
 */
APP.mapster = function(){

    "use strict";

    var contactMap = document.getElementById('contactMap');
    if(contactMap) {
        +function (window, google, mapster) {
            var mapElement = contactMap;
            var mapOptions = mapster.MAP_OPTIONS;
            mapster.MAP_OPTIONS.center.lat = 47.550000;
            mapster.MAP_OPTIONS.center.lng = 19.040706;
            mapster.MAP_OPTIONS.zoom = 16;
            var map = mapster.create(mapElement, mapOptions);
            map.addMarker({
                position: {
                    lat: 47.550000,
                    lng: 19.040706
                },
                event: {
                    name: 'click',
                    callback: function () {
                    }
                },
                content: '<div style="width:250px"><div style="width:200px;"><h4 style="margin-top:10px;margin-bottom:0;">Pistacia church</h4><ul style="list-style:none;padding:0;margin:0;"><li><b>Tues - Sat:</b> 7AM - 10PM</li><li><b>Sunday:</b> 7AM- 2PM</li><li><b>Monday:</b> 6AM - 10PM</li></ul></div></div >',
                contentShow: false,
                id: 1,
                draggable: false,
                icon: 'assets/img/elements/map.png'
            });
            map.addMarker({
                position: {
                    lat: 47.551210,
                    lng: 19.045706
                },
                event: {
                    name: 'click',
                    callback: function () {
                    }
                },
                content: '<div style="width:250px"><div style="width:200px;"><h4 style="margin-top:10px;margin-bottom:0;">Pistacia church</h4><ul style="list-style:none;padding:0;margin:0;"><li><b>Tues - Sat:</b> 7AM - 10PM</li><li><b>Sunday:</b> 7AM- 2PM</li><li><b>Monday:</b> 6AM - 10PM</li></ul></div></div >',
                contentShow: false,
                id: 2,
                draggable: false,
                icon: 'assets/img/elements/map.png'
            });
        }(window, google, window.Mapster || (window.Mapster = {}));
    }
};



/**
 * Flexslider
 */
APP.flexslider = function(){

    "use strict";

    jQuery(window).load(function() {
        var testimonialfs = document.querySelector('.flexslider-testimonial');
        if(testimonialfs) {
            jQuery('.flexslider-testimonial').flexslider({
                animation: "fade",
                controlNav: false,
                prevText: '<i class="fa fa-chevron-left"></i>',
                nextText: '<i class="fa fa-chevron-right"></i>'
            });
        }
    });
};



/**
 * Owl carousels
 * All carousel
 */
APP.owlCarousels = function(){

    "use strict";

    jQuery(document).ready(function(){

        /* Full slider */
        var fullCarousel = document.querySelector('.full-slider-carousel');
        if(fullCarousel) {
            var topBarHeight = jQuery('.top-bar').height();
            jQuery('.full-slider-carousel .item ').css({ 'height' : APP.windowHeight - topBarHeight + 'px' });
            jQuery('.full-slider-carousel').owlCarousel({
                items: 1,
                dots: false,
                nav: true,
                loop: true,
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>']
            });
        }

        /* Galeries carousel */
        var galeriesCarousel = document.querySelector('.galeries-carousel');
        if(galeriesCarousel) {
            jQuery('.galeries-carousel').owlCarousel({
                items: 8,
                dots: true,
                nav: false,
                loop: true,
                autoWidth: true,
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>'],
                responsive: {
                    0: {
                        items: 1
                    },
                    480: {
                        items: 5
                    },
                    768: {
                        items: 5
                    },
                    1560: {
                        items: 10
                    }
                }
            });
        }

        /* Testimonial carousel */
        var testimonialCarousel = document.querySelector('.testimonial-carousel');
        if(testimonialCarousel) {
            jQuery('.testimonial-carousel').owlCarousel({
                items: 1,
                animateOut: 'fadeOut',
                animateIn: 'fadeIn',
                dots: false,
                loop: true,
                smartSpeed: 450,
                nav: true,
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>']
            });
        }

        /* Testimony carousel */
        var testimonyCarousel = document.querySelector('.testimony-carousel');
        if(testimonyCarousel) {
            jQuery('.testimony-carousel').owlCarousel({
                items: 3,
                dots: false,
                loop: true,
                nav: true,
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>'],
                responsive: {
                    0: {
                        items: 1
                    },
                    480: {
                        items: 2
                    },
                    768: {
                        items: 3
                    }
                }
            });
        }

        /* Logo carousel */
        var logoCarousel = document.querySelector('.logo-carousel');
        if(logoCarousel) {
            jQuery('.logo-carousel').owlCarousel({
                items: 4,
                dots: false,
                nav: true,
                loop: true,
                responsive: {
                    0: {
                        items: 1
                    },
                    480: {
                        items: 2
                    },
                    768: {
                        items: 5
                    }
                },
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>']
            });
        }

        /* Shop item carousel */
        var shopItemCarousel = jQuery('.shop-item-carousel');
        var shopItemNavCarousel = jQuery('.shop-item-nav-carousel');
        if((shopItemCarousel.length>0) && (shopItemNavCarousel.length>0)) {
            shopItemCarousel.owlCarousel({
                items: 1,
                dots: false,
                nav: false,
                loop: true,
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>']
            });
            shopItemNavCarousel.owlCarousel({
                items: 1,
                dots: false,
                nav: true,
                loop: true,
                margin:8,
                responsive: {
                    0: {
                        items: 1
                    },
                    480: {
                        items: 2
                    },
                    768: {
                        items: 3
                    }
                },
                navText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>']
            });

            jQuery('.shop-item-nav-carousel').on('click','.owl-item',function(e){
                var position = jQuery(this).find('.item').data('number');
                shopItemCarousel.trigger('to.owl.carousel',[position,400,true]);
            });
        }
    });
};



/**
 * countDown
 * Count down script
 */
APP.countDown = function () {

    "use strict";

    var austDay = new Date();
    var y = austDay.getFullYear();
    var m = austDay.getMonth()+2;
    if (m > 13){
        y++;
        m=1;
    }
    var d = 5;
    var date = y+'/'+m+'/'+d;

    /* worshipCountDown */
    var worshipCountDown = document.getElementById('worshipCountDown');

    if ( worshipCountDown ) {
        jQuery('#worshipCountDown').countdown(date, function(event) {
            jQuery(this).html(event.strftime('<div class="item"><span>%w</span>weeks</div><div class="item"><span>%d</span>days</div><div class="item"><span>%H</span>hrs</div><div class="item"><span>%M</span>mins</div><div class="item"><span>%S</span>secs</div>'));
        });
    }

    /* upcomingEventCountDown */
    var upcomingEventCountDown = document.getElementById('upcomingEventCountDown');

    if ( upcomingEventCountDown ) {
        jQuery('#upcomingEventCountDown').countdown(date, function(event) {
            jQuery(this).html(event.strftime('<div class="item"><span>%w</span>weeks</div><div class="item"><span>%d</span>days</div><div class="item"><span>%H</span>hrs</div><div class="item"><span>%M</span>mins</div><div class="item"><span>%S</span>secs</div>'));
        });
    }

};



/**
 * Contact form
 */
APP.contactForm = function(){

    "use strict";

    var form = jQuery('#contactForm');

    form.find('.btn-send').on('click', function (e) {

        form.validate({
            rules : {
                contactFirstname : 'required',
                contactLastname : 'required',
                contactEmail : {
                    required : true,
                    email : true
                },
                contactPhone : {
                    required : true
                }
            },
            messages: {
                contactFirstname : '<span class="error-icon">!</span>',
                contactLastname : '<span class="error-icon">!</span>',
                contactEmail : {
                    required : '<span class="error-icon">!</span>',
                    email : '<span class="error-icon">!</span>'
                },
                contactPhone : {
                    required : '<span class="error-icon">!</span>'
                }
            }
        });

        if (form.valid() === true && jQuery('#contactSec').val() === '') {

            jQuery.ajax({
                type: "POST",
                url: "/assets/php/contactform.php",
                data: form.serialize(),
                success: function (message) {
                    form.find('.success-message').css({'display':'block'});
                    form.reset();
                    e.preventDefault();
                },
                error: function () {
                    form.find('.alert-message').css({'display':'block'});
                    e.preventDefault();
                }
            });

        } else {
            return false;
        }
        e.preventDefault();
    });
};



APP.commentForm = function(){

    "use strict";

    var form = jQuery('#rvwForm');

    form.find('.btn-send').on('click', function (e) {

        form.validate({
            rules : {
                rvwname : 'required',
                rvwmessage : 'required',
                rvwemail : {
                    required : true,
                    email : true
                }
            },
            messages: {
                rvwname : '<span class="error-icon">!</span>',
                rvwmessage : '<span class="error-icon">!</span>',
                rvwemail : {
                    required : '<span class="error-icon">!</span>',
                    email : '<span class="error-icon">!</span>'
                }
            }
        });

        if (form.valid() === true && jQuery('#rvwSec').val() === '') {

            /*ajax post*/

        } else {
            return false;
        }
        e.preventDefault();
    });
};



/**
 * page Loader
 * hide page-loader div when page is loaded
 */
APP.pageLoader = function() {

    "use strict";

    var element = document.getElementById('pageLoader');

    if (element) {
        window.addEventListener("load", function() {
            jQuery('#pageLoader').hide();
            console.log('Page was loaded...');

        }, false);
    }
};


APP.offcanvasFavourite = function(){

    "use strict";

    jQuery('.favourites.offcanvas').find('.btn-remove').on('click',function(e){
        e.preventDefault();
        var item = jQuery(this).parents('.item');
        item.fadeTo("slow",0,function(){
            jQuery(this).remove();
        });
    });

};


APP.scrollTo = function () {

    "use strict";

    /* Scroll to element */
    jQuery(document).ready(function () {
        jQuery("a.scroll-to").on('click', function (e) {
            var elem = '#'+jQuery(this).attr('href');
            e.preventDefault();
            jQuery(window).scrollTo(jQuery(elem), 800);
        });
    });

    /* Canvas move left right scroll to top */
    jQuery('.right-off-canvas-toggle, .left-off-canvas-toggle').on('click',function(){
        jQuery("html, body").scrollTop(0);
    });
};



/**
 * Parallax
 * Prallax image background
 *
 */
APP.parallax = function(){

    "use strict";

    if(APP.isMobile() == false) {
        var parallax = document.querySelector('.parallax-section');
        if (parallax) {
            jQuery('.parallax-section').parallax('50%', 0.4, false);
        }
    }
};




/**
 * counterup plugin
 */
APP.countUp = function(){

    "use strict";

    if (document.querySelector('.counter-numbers')) {
        jQuery('.counter-numbers').find('.counter').counterUp({
            delay: 10,
            time: 2000
        });
    }

};



/**
 * Animation
 * Animation element with cssanimate if element is in screen
 */
APP.elementsAnimation = function(){

    "use strict";

    jQuery('.animated').waypoint({
        handler: function(direction) {
            var elem = jQuery(this.element);
            var animation = elem.data('animation');
            if (animation) {
                if (!elem.hasClass('visible')) {
                    var animationDelay = elem.data('animation-delay');
                    if (animationDelay) {
                        setTimeout(function () {
                            elem.addClass(animation + " visible");
                        }, animationDelay);
                    } else {
                        elem.addClass(animation + " visible");
                    }
                }
            }
        },
        offset: '50%'
    },{
        triggerOnce:true,
        offset: function() {
            return jQuery(window).height()-300;
        }
    });

};



/**
 * fadeOutElements
 * add fade effect to h1 h2 p and button group selected elements if scrolling down
 */
APP.fadeOutElements = function(){

    "use strict";

    var wrap = jQuery('.fade-effect-onscroll');
    var h1 = wrap.find('h1');
    var h2 = wrap.find('h2');
    var p = wrap.find('p');
    var buttonGroup = wrap.find('.button-group');

    function fadingElements(){

        var top=jQuery(window).scrollTop();

        if(APP.windowWidth > 992){
            if(h1.length>0) {
                h1.animate({
                    top: (top / 5) + 'px',
                    opacity: 1 - top / 260
                }, 0);
            }

            if(h2.length>0) {
                h2.animate({
                    top: (top / 4) + 'px',
                    opacity: 1 - top / 350
                }, 0);
            }

            if(p.length>0) {
                p.animate({
                    top: (top / 3) + 'px',
                    opacity: 1 - top / 450
                }, 0);
            }

            if(buttonGroup.length>0) {
                buttonGroup.animate({
                    top: (top / 3) + 'px',
                    opacity: 1 - top / 560
                }, 0);
            }
        }
    }

    window.addEventListener("scroll", fadingElements);
    window.addEventListener("resize", fadingElements);

};



/**
 * psModal
 * show modern custom overlay window with close button
 *
 * button:
 * <a href="#" class="btn-ps-modal" data-ps-modal-id="#searchModal">Open</a>
 *
 * modal window html:
 * <div class="ps-modal-overlay" id="#searchModal">
 *     <button type="button" class="overlay-close">Close</button>
 *     content
 * </div>
 */
APP.psModal = function(){

    "use strict";

    var transEndEventNames = {
        'WebkitTransition': 'webkitTransitionEnd',
        'MozTransition': 'transitionend',
        'OTransition': 'oTransitionEnd',
        'msTransition': 'MSTransitionEnd',
        'transition': 'transitionend'
    };

    var transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ];
    var support = { transitions : Modernizr.csstransitions };

    /** toggleOverlayWindow */
    function toggleOverlayWindow(element) {
        if( classie.has( element, 'open' ) ) {

            classie.remove( element, 'open' );
            classie.add( element, 'close' );

            classie.remove( document.getElementsByTagName("html")[0], 'overflow-hidden' );
            classie.remove( document.getElementsByTagName("body")[0], 'overflow-hidden' );

            var onEndTransitionFn = function( ev ) {
                if( support.transitions ) {
                    if( ev.propertyName !== 'visibility' ) return;
                    this.removeEventListener( transEndEventName, onEndTransitionFn );
                }
                classie.remove( element, 'close' );
            };

            if( support.transitions ) {
                element.addEventListener( transEndEventName, onEndTransitionFn );
            }
            else {
                onEndTransitionFn();
            }

        } else if( !classie.has( element, 'close' ) ) {
            classie.add( element, 'open' );
            element
                .querySelector('.overlay-close')
                .addEventListener('click',function(){
                    toggleOverlayWindow(element);
                    return false;
                });

            classie.add( document.getElementsByTagName("html")[0], 'overflow-hidden' );
            classie.add( document.getElementsByTagName("body")[0], 'overflow-hidden' );

        }
    }

    /**
     * btn-ps-modal on click event
     * if find the data-ps-modal-id then toggle it
     * */
    var items = document.getElementsByClassName('btn-ps-modal');

    for(var i=0; i< items.length; i++){

        items[i].addEventListener('click', function(){
            var id = this.getAttribute('data-ps-modal-id').replace('#','');
            var psWindow = document.getElementById(id);
            toggleOverlayWindow(psWindow);
        });
    }

};
