
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var CargoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        CargoEditDataController.prototype =  new AbstractForm();

        CargoEditDataController.prototype.constructor = CargoEditDataController;

        CargoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        CargoEditDataController.prototype.url = "cargo/";
        CargoEditDataController.prototype.urlAction = "cargo/save";
        CargoEditDataController.prototype.urlPage = "cargo/cargoedit.jsp";

        CargoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new CargoEditDataController();
    }
});