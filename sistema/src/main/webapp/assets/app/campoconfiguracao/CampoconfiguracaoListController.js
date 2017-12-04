
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var CampoconfiguracaoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		CampoconfiguracaoListController.prototype =  new ListController();

		CampoconfiguracaoListController.prototype.constructor = CampoconfiguracaoListController;

		CampoconfiguracaoListController.prototype.divLoad =  "#corpoDoSistema";
		CampoconfiguracaoListController.prototype.urlPage = "campoconfiguracao/campoconfiguracaolist.jsp";
		CampoconfiguracaoListController.prototype.urlLoadList = "campoconfiguracao/list";
		CampoconfiguracaoListController.prototype.url = "campoconfiguracao/";
		CampoconfiguracaoListController.prototype.urlDelete = "campoconfiguracao/";
		CampoconfiguracaoListController.prototype.urlDeleteAll = "campoconfiguracao/deleteAll/";


		

		return new CampoconfiguracaoListController();
	}
});