
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var CidadeEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        CidadeEditDataController.prototype =  new AbstractForm();

        CidadeEditDataController.prototype.constructor = CidadeEditDataController;

        CidadeEditDataController.prototype.divLoad =  "#corpoDoSistema";
        CidadeEditDataController.prototype.url = "cidade/";
        CidadeEditDataController.prototype.urlAction = "cidade/save";
        CidadeEditDataController.prototype.urlPage = "cidade/cidadeedit.jsp";

        CidadeEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new CidadeEditDataController();
    }
});