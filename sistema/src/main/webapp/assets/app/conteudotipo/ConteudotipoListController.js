
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var ConteudotipoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		ConteudotipoListController.prototype =  new ListController();

		ConteudotipoListController.prototype.constructor = ConteudotipoListController;

		ConteudotipoListController.prototype.divLoad =  "#corpoDoSistema";
		ConteudotipoListController.prototype.urlPage = "conteudotipo/conteudotipo.jsp";
		ConteudotipoListController.prototype.urlLoadList = "conteudotipo/list";
		ConteudotipoListController.prototype.url = "conteudotipo/";
		ConteudotipoListController.prototype.urlDelete = "conteudotipo/";
		ConteudotipoListController.prototype.urlDeleteAll = "conteudotipo/deleteAll/";

		return new ConteudotipoListController();
	}
});