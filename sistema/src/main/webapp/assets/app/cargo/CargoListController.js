
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var CargoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		CargoListController.prototype =  new ListController();

		CargoListController.prototype.constructor = CargoListController;

		CargoListController.prototype.divLoad =  "#corpoDoSistema";
		CargoListController.prototype.urlPage = "bairro/bairrolist.jsp";
		CargoListController.prototype.urlLoadList = "bairro/list";
		CargoListController.prototype.url = "bairro/";
		CargoListController.prototype.urlDelete = "bairro/";
		CargoListController.prototype.urlDeleteAll = "bairro/deleteAll/";


		

		return new CargoListController();
	}
});