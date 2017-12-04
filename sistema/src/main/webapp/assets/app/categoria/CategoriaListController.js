
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var CategoriaListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		CategoriaListController.prototype =  new ListController();

		CategoriaListController.prototype.constructor = CategoriaListController;

		CategoriaListController.prototype.divLoad =  "#corpoDoSistema";
		CategoriaListController.prototype.urlPage = "categoria/categoria.jsp";
		CategoriaListController.prototype.urlLoadList = "categoria/list";
		CategoriaListController.prototype.url = "categoria/";
		CategoriaListController.prototype.urlDelete = "categoria/";
		CategoriaListController.prototype.urlDeleteAll = "categoria/deleteAll/";


		return new CategoriaListController();
	}
});