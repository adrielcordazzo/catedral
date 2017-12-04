define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
	return function(){
		var RoleEditDataController = function(){
			this.loadPage(this.loadData.bind(this));
			this.openLoad();
		}

		RoleEditDataController.prototype =  new AbstractForm();

		RoleEditDataController.prototype.constructor = RoleEditDataController;

		RoleEditDataController.prototype.divLoad =  "#corpoDoSistema";
		RoleEditDataController.prototype.url = "role/";
		RoleEditDataController.prototype.urlAction = "role/save";
		RoleEditDataController.prototype.urlPage = "util/role/roleeditdata.jsp";

		RoleEditDataController.prototype.termineteLoadForm = function(){
			this.closeLoad();
		}

		return new RoleEditDataController();
	}
});