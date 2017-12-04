
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var RoleListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		RoleListController.prototype =  new ListController();

		RoleListController.prototype.constructor = RoleListController;

		RoleListController.prototype.divLoad =  "#corpoDoSistema";
		RoleListController.prototype.urlPage = "util/role/rolelist.jsp";
		RoleListController.prototype.urlLoadList = "role/list";
		RoleListController.prototype.url = "role/";
		RoleListController.prototype.urlDelete = "role/";
		RoleListController.prototype.urlDeleteAll = "role/deleteAll/";


		return new RoleListController();
	}
});
