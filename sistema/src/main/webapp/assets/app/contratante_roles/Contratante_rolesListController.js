
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var Contratante_rolesListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		Contratante_rolesListController.prototype =  new ListController();

		Contratante_rolesListController.prototype.constructor = Contratante_rolesListController;

		Contratante_rolesListController.prototype.divLoad =  "#corpoDoSistema";
		Contratante_rolesListController.prototype.urlPage = "contratante_roles/contratante_roleslist.jsp";
		Contratante_rolesListController.prototype.urlLoadList = "contratante_roles/list";
		Contratante_rolesListController.prototype.url = "contratante_roles/";
		Contratante_rolesListController.prototype.urlDelete = "contratante_roles/";
		Contratante_rolesListController.prototype.urlDeleteAll = "contratante_roles/deleteAll/";


		

		return new Contratante_rolesListController();
	}
});