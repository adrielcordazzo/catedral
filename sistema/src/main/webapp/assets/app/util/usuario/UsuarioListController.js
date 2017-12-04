
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var UsuarioListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		UsuarioListController.prototype =  new ListController();

		UsuarioListController.prototype.constructor = UsuarioListController;

		UsuarioListController.prototype.divLoad =  "#corpoDoSistema";
		UsuarioListController.prototype.urlPage = "util/usuario/usuariolist.jsp";
		UsuarioListController.prototype.urlLoadList = "usuario/list";
		UsuarioListController.prototype.url = "usuario/";
		UsuarioListController.prototype.urlDelete = "usuario/";
		UsuarioListController.prototype.urlDeleteAll = "usuario/deleteAll/";


		return new UsuarioListController();
	}
});
