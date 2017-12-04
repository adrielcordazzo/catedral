
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var ContratanteEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ContratanteEditDataController.prototype =  new AbstractForm();

        ContratanteEditDataController.prototype.constructor = ContratanteEditDataController;

        ContratanteEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ContratanteEditDataController.prototype.url = "contratante/";
        ContratanteEditDataController.prototype.urlAction = "contratante/save";
        ContratanteEditDataController.prototype.urlPage = "util/contratante/contratanteedit.jsp";

        ContratanteEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new ContratanteEditDataController();
    }
});