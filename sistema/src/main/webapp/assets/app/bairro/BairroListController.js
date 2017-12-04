
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var BairroListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		BairroListController.prototype =  new ListController();

		BairroListController.prototype.constructor = BairroListController;

		BairroListController.prototype.divLoad =  "#corpoDoSistema";
		BairroListController.prototype.urlPage = "bairro/bairrolist.jsp";
		BairroListController.prototype.urlLoadList = "bairro/list";
		BairroListController.prototype.url = "bairro/";
		BairroListController.prototype.urlDelete = "bairro/";
		BairroListController.prototype.urlDeleteAll = "bairro/deleteAll/";


		

		return new BairroListController();
	}
});