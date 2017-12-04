
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var ContatoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		ContatoListController.prototype =  new ListController();

		ContatoListController.prototype.constructor = ContatoListController;

		ContatoListController.prototype.divLoad =  "#corpoDoSistema";
		ContatoListController.prototype.urlPage = "contato/contatolist.jsp";
		ContatoListController.prototype.urlLoadList = "contato/list";
		ContatoListController.prototype.url = "contato/";
		ContatoListController.prototype.urlDelete = "contato/";
		ContatoListController.prototype.urlDeleteAll = "contato/deleteAll/";


		

		return new ContatoListController();
	}
});