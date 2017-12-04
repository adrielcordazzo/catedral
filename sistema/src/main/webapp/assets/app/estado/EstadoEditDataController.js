
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var EstadoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        EstadoEditDataController.prototype =  new AbstractForm();

        EstadoEditDataController.prototype.constructor = EstadoEditDataController;

        EstadoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        EstadoEditDataController.prototype.url = "estado/";
        EstadoEditDataController.prototype.urlAction = "estado/save";
        EstadoEditDataController.prototype.urlPage = "estado/estadoedit.jsp";

        EstadoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new EstadoEditDataController();
    }
});