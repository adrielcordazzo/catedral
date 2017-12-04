
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var CategoriaEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        CategoriaEditDataController.prototype =  new AbstractForm();

        CategoriaEditDataController.prototype.constructor = CategoriaEditDataController;

        CategoriaEditDataController.prototype.divLoad =  "#corpoDoSistema";
        CategoriaEditDataController.prototype.url = "categoria/";
        CategoriaEditDataController.prototype.urlAction = "categoria/save";
        CategoriaEditDataController.prototype.urlPage = "categoria/categoriaedit.jsp";

        CategoriaEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new CategoriaEditDataController();
    }
});