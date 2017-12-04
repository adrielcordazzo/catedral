
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var CidadeListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		CidadeListController.prototype =  new ListController();

		CidadeListController.prototype.constructor = CidadeListController;

		CidadeListController.prototype.divLoad =  "#corpoDoSistema";
		CidadeListController.prototype.urlPage = "cidade/cidadelist.jsp";
		CidadeListController.prototype.urlLoadList = "cidade/list";
		CidadeListController.prototype.url = "cidade/";
		CidadeListController.prototype.urlDelete = "cidade/";
		CidadeListController.prototype.urlDeleteAll = "cidade/deleteAll/";


		

		return new CidadeListController();
	}
});