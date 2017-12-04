define(['text!util/menu/menu.jsp','util/Abstract','router'] ,function (HTMLPAGINA,ABSTRACT,router) {
	return function(){
		var MenuController = function(){
			this.contentHtml = HTMLPAGINA;
			this.loadPage(this.viewDidLoad.bind(this));
		}

		MenuController.prototype =  new ABSTRACT();

		MenuController.prototype.constructor = MenuController;

		MenuController.prototype.divLoad = '.menuSistema';

		MenuController.prototype.viewDidLoad =  function(){
			
		}

		return new MenuController();
	}
});