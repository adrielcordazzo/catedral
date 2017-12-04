
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var ConteudotipoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ConteudotipoEditDataController.prototype =  new AbstractForm();

        ConteudotipoEditDataController.prototype.constructor = ConteudotipoEditDataController;

        ConteudotipoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ConteudotipoEditDataController.prototype.url = "conteudotipo/";
        ConteudotipoEditDataController.prototype.urlAction = "conteudotipo/save";
        ConteudotipoEditDataController.prototype.urlPage = "conteudotipo/conteudotipoedit.jsp";

        ConteudotipoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new ConteudotipoEditDataController();
    }
});