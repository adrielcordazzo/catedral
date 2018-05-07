
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var EventoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		EventoListController.prototype =  new ListController();

		EventoListController.prototype.constructor = EventoListController;

		EventoListController.prototype.divLoad =  "#corpoDoSistema";
		EventoListController.prototype.urlPage = "evento/eventolist.jsp";
		EventoListController.prototype.urlLoadList = "evento/list";
		EventoListController.prototype.url = "evento/";
		EventoListController.prototype.urlDelete = "evento/";
		EventoListController.prototype.urlDeleteAll = "evento/deleteAll/";


		

		return new EventoListController();
	}
});