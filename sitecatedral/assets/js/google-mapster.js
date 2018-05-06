/*!
 * Mapster plugin v.1.0
 * author: bumbella
 * use Google Maps
 *
 * example:
 *
 * +function (window, google, mapster) {
 *
 *     var mapElement = document.getElementById('map-canvas');
 *
 *     var mapOptions = mapster.MAP_OPTIONS;
 *
 *     mapster.MAP_OPTIONS.center.lat = 47.508743;
 *     mapster.MAP_OPTIONS.center.lng = 19.036706;
 *
 *     var map = mapster.create(mapElement, mapOptions);
 *
 *     map.addMarker({
 *         position: {
 *             lat: 47.509257,
 *             lng: 19.073465
 *         },
 *         event: {
 *             name: 'click',
 *             callback: function () {
 *
 *             }
 *         },
 *         content: '<div style="width:120px; float:left; margin-right:10px;"><img style="max-width:100%;display:block;" src="/images/fruit/thumbs/1440220_19940818.jpg"></div><div style="float:left; width:200px;"><h3 style="margin-top:0px;">Lorem ispsum dolor</h3><p>In harum movet nominavi ius, ei vix homero nostrud inimicus.</p></div>',
 *         contentShow: false,
 *         id: 1,
 *         draggable: false,
 *         icon: '/images/mapicons/winebar.png'
 *     });
 *
 *     map.addMarker({
 *         position: {
 *             lat: 47.486459,
 *             lng: 18.999865
 *         },
 *         content: '<div style="width:120px; float:left; margin-right:10px;"><img style="max-width:100%;display:block;" src="/images/fruit/thumbs/1440220_19940818.jpg"></div><div style="float:left; width:200px;"><h3 style="margin-top:0px;">Lorem ispsum dolor</h3><p>In harum movet nominavi ius, ei vix homero nostrud inimicus.</p></div>',
 *         contentShow: false,
 *         event: {
 *             name: 'click',
 *             callback: function () {
 *
 *             }
 *         },
 *         id: 2,
 *         draggable: false,
 *         icon: '/images/mapicons/marina-2.png'
 *     });
 * }(window, google, window.Mapster || (window.Mapster = {}));
 *
 * MIT License
 */

if (typeof google != "undefined") {
    
    (function(window, google) {
    
        var Mapster = (function() {
    
            function Mapster(element, options) {
    
                this.gMap = new google.maps.Map(element, options);
            }
    
            Mapster.prototype = {
                zoom: function(zoomLevel) {
                    if (zoomLevel) {
                        this.gMap.setZoom(zoomLevel);
                    } else {
                        return this.gMap.getZoom();
                    }
                },
                _on: function(opts) {
                    var self = this;
                    google.maps.event.addListener(opts.obj, opts.event, function(e) {
                        opts.callback.call(self, e);
                    });
                },
                addMarker: function(opts) {
                    var marker;
                    marker = this._createMarker(opts);
                    if (opts.event) {
                        this._on({
                            obj: marker,
                            event: opts.event.name,
                            callback: opts.event.callback
                        });
                    }
    
                    if (opts.content) {
    
                        var infoWindow = new google.maps.InfoWindow({
                            content: opts.content
                        });
    
                        if (opts.contentShow) {
    
                            infoWindow.open(this.gMap, marker);
    
                        }
    
                        this._on({
                            obj: marker,
                            event: opts.event.name,
                            callback: function() {
    
                                infoWindow.open(this.gMap, marker);
    
                            }
                        });
    
                    }
    
                },
                _createMarker: function(opt) {
                    opt.map = this.gMap;
                    return new google.maps.Marker(opt);
                }
            };
            return Mapster;
        }());
    
        Mapster.MAP_OPTIONS = {
            zoom: 11,
            scrollwheel: false,
            draggable: true,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            center: {
                lat: 40.6700,
                lng: -73.9400
            },
            styles: [{featureType: 'water', elementType: 'all', stylers: [{hue: '#e9ebed'}, {saturation: -78}, {lightness: 67}, {visibility: 'simplified'}]}, {featureType: 'landscape', elementType: 'all', stylers: [{hue: '#ffffff'}, {saturation: -100}, {lightness: 100}, {visibility: 'simplified'}]}, {featureType: 'road', elementType: 'geometry', stylers: [{hue: '#bbc0c4'}, {saturation: -93}, {lightness: 31}, {visibility: 'simplified'}]}, {featureType: 'poi', elementType: 'all', stylers: [{hue: '#ffffff'}, {saturation: -100}, {lightness: 100}, {visibility: 'off'}]}, {featureType: 'road.local', elementType: 'geometry', stylers: [{hue: '#e9ebed'}, {saturation: -90}, {lightness: -8}, {visibility: 'simplified'}]}, {featureType: 'transit', elementType: 'all', stylers: [{hue: '#e9ebed'}, {saturation: 10}, {lightness: 69}, {visibility: 'on'}]}, {featureType: 'administrative.locality', elementType: 'all', stylers: [{hue: '#2c2e33'}, {saturation: 7}, {lightness: 19}, {visibility: 'on'}]}, {featureType: 'road', elementType: 'labels', stylers: [{hue: '#bbc0c4'}, {saturation: -93}, {lightness: 31}, {visibility: 'on'}]}, {featureType: 'road.arterial', elementType: 'labels', stylers: [{hue: '#bbc0c4'}, {saturation: -93}, {lightness: -2}, {visibility: 'simplified'}]}]
        };
    
        Mapster.create = function(element, options) {
            return new Mapster(element, options);
        };
    
        window.Mapster = Mapster;
    
    }(window, google));
}
