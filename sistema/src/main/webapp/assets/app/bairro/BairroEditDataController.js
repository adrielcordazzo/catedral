
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var BairroEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        BairroEditDataController.prototype =  new AbstractForm();

        BairroEditDataController.prototype.constructor = BairroEditDataController;

        BairroEditDataController.prototype.divLoad =  "#corpoDoSistema";
        BairroEditDataController.prototype.url = "bairro/";
        BairroEditDataController.prototype.urlAction = "bairro/save";
        BairroEditDataController.prototype.urlPage = "bairro/bairroedit.jsp";

        BairroEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new BairroEditDataController();
    }
});