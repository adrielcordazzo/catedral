
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var CargoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		CargoListController.prototype =  new ListController();

		CargoListController.prototype.constructor = CargoListController;

		CargoListController.prototype.divLoad =  "#corpoDoSistema";
		CargoListController.prototype.urlPage = "cargo/cargolist.jsp";
		CargoListController.prototype.urlLoadList = "cargo/list";
		CargoListController.prototype.url = "cargo/";
		CargoListController.prototype.urlDelete = "cargo/";
		CargoListController.prototype.urlDeleteAll = "cargo/deleteAll/";


		

		return new CargoListController();
	}
});