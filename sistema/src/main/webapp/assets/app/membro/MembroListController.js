
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var MembroListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		MembroListController.prototype =  new ListController();

		MembroListController.prototype.constructor = MembroListController;

		MembroListController.prototype.divLoad =  "#corpoDoSistema";
		MembroListController.prototype.urlPage = "membro/membrolist.jsp";
		MembroListController.prototype.urlLoadList = "membro/list";
		MembroListController.prototype.url = "membro/";
		MembroListController.prototype.urlDelete = "membro/";
		MembroListController.prototype.urlDeleteAll = "membro/deleteAll/";


		

		return new MembroListController();
	}
});