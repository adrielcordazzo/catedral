
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var ConfiguracaocampoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		ConfiguracaocampoListController.prototype =  new ListController();

		ConfiguracaocampoListController.prototype.constructor = ConfiguracaocampoListController;

		ConfiguracaocampoListController.prototype.divLoad =  "#corpoDoSistema";
		ConfiguracaocampoListController.prototype.urlPage = "configuracaocampo/configuracaocampolist.jsp";
		ConfiguracaocampoListController.prototype.urlLoadList = "configuracaocampo/list";
		ConfiguracaocampoListController.prototype.url = "configuracaocampo/";
		ConfiguracaocampoListController.prototype.urlDelete = "configuracaocampo/";
		ConfiguracaocampoListController.prototype.urlDeleteAll = "configuracaocampo/deleteAll/";


		

		return new ConfiguracaocampoListController();
	}
});