define(['util/Abstract','util/websoketController',
	'util/menu/MenuController','util/NotificationCenterController',
	'util/ModalDialogConfirmController','util/ModalController'],function (ABSTRACT,websoketController,MENU,NOTIFICA,DIALOG,MODAL) {

	return function(){
		function ApplicationController(){ 
			this.componentes = {};
			this.componentes['menu'] = new MENU();
			this.componentes['NotificationCenter'] = new NOTIFICA();
			this.componentes['ModalDialogConfirm'] = new DIALOG();
			this.componentes['Modal'] = new MODAL();
		
			this.soket();
		};


		ApplicationController.prototype = new ABSTRACT();
		ApplicationController.prototype.constructor = ApplicationController;

		ApplicationController.prototype.urlPage = 'application';

		ApplicationController.prototype.divLoad = "#APP";

		ApplicationController.prototype.CACHEPAGES = {};
		ApplicationController.prototype.CONFIG = {};

		ApplicationController.prototype.CACHEJSON = {};

		ApplicationController.prototype.soket = function(){
				// var web = new websoketController();
				// web.connect();
		}

		ApplicationController.prototype.nl2br = function(data){
			return  data.replace(/(?:\r\n|\r|\n)/g, '<br />');
		}

		ApplicationController.prototype.consoleinfo = function(titulo,json){ 
			console.info(titulo,JSON.parse(JSON.stringify(json)));
		}

		ApplicationController.prototype.alteraUrl = function(urls){
			EVENTHASH = false;
			window.location.hash = urls;
		}

		ApplicationController.prototype.removeDestroy = function(classe){
			if(classe && this.componentes[classe]){
				$(this.componentes[classe].divLoad).html('');
				delete this.componentes[classe];
			}
		}

		ApplicationController.prototype.getObject = function(classe){
			if(this.componentes[classe]){
				return  this.componentes[classe];
			}
			console.error('class informada não existe ',classe);
		}

		// ApplicationController.prototype.importJs = function(link){
		// 	var self = this;
		// 	console.info(link);
		// 	require([link+'Controller'],function(newClass){
		// 		console.info(link);
		// 		var imports = new newClass();
		// 		self.componentes[imports.constructor.name] = imports;
		// 		imports.loadPage();
		// 	});
		// }

		return new ApplicationController();

	}


});


