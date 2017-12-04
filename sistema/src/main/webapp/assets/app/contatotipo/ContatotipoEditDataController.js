
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var ContatotipoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ContatotipoEditDataController.prototype =  new AbstractForm();

        ContatotipoEditDataController.prototype.constructor = ContatotipoEditDataController;

        ContatotipoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ContatotipoEditDataController.prototype.url = "contatotipo/";
        ContatotipoEditDataController.prototype.urlAction = "contatotipo/save";
        ContatotipoEditDataController.prototype.urlPage = "contatotipo/contatotipoedit.jsp";

        ContatotipoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new ContatotipoEditDataController();
    }
});