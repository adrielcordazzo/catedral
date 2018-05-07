
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var EventomembroListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		EventomembroListController.prototype =  new ListController();

		EventomembroListController.prototype.constructor = EventomembroListController;

		EventomembroListController.prototype.divLoad =  "#corpoDoSistema";
		EventomembroListController.prototype.urlPage = "eventomembro/eventomembrolist.jsp";
		EventomembroListController.prototype.urlLoadList = "eventomembro/list";
		EventomembroListController.prototype.url = "eventomembro/";
		EventomembroListController.prototype.urlDelete = "eventomembro/";
		EventomembroListController.prototype.urlDeleteAll = "eventomembro/deleteAll/";


		

		return new EventomembroListController();
	}
});