
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var ContatotipoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		ContatotipoListController.prototype =  new ListController();

		ContatotipoListController.prototype.constructor = ContatotipoListController;

		ContatotipoListController.prototype.divLoad =  "#corpoDoSistema";
		ContatotipoListController.prototype.urlPage = "contatotipo/contatotipolist.jsp";
		ContatotipoListController.prototype.urlLoadList = "contatotipo/list";
		ContatotipoListController.prototype.url = "contatotipo/";
		ContatotipoListController.prototype.urlDelete = "contatotipo/";
		ContatotipoListController.prototype.urlDeleteAll = "contatotipo/deleteAll/";


		

		return new ContatotipoListController();
	}
});