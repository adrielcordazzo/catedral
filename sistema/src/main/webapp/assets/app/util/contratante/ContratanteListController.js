
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var ContratanteListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		ContratanteListController.prototype =  new ListController();

		ContratanteListController.prototype.constructor = ContratanteListController;

		ContratanteListController.prototype.divLoad =  "#corpoDoSistema";
		ContratanteListController.prototype.urlPage = "util/contratante/contratantelist.jsp";
		ContratanteListController.prototype.urlLoadList = "contratante/list";
		ContratanteListController.prototype.url = "contratante/";
		ContratanteListController.prototype.urlDelete = "contratante/";
		ContratanteListController.prototype.urlDeleteAll = "contratante/deleteAll/";

		


		

		return new ContratanteListController();
	}
});