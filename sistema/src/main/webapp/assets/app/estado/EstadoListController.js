
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var EstadoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		EstadoListController.prototype =  new ListController();

		EstadoListController.prototype.constructor = EstadoListController;

		EstadoListController.prototype.divLoad =  "#corpoDoSistema";
		EstadoListController.prototype.urlPage = "estado/estadolist.jsp";
		EstadoListController.prototype.urlLoadList = "estado/list";
		EstadoListController.prototype.url = "estado/";
		EstadoListController.prototype.urlDelete = "estado/";
		EstadoListController.prototype.urlDeleteAll = "estado/deleteAll/";


		

		return new EstadoListController();
	}
});