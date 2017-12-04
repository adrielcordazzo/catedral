
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var CampoconfiguracaoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        CampoconfiguracaoEditDataController.prototype =  new AbstractForm();

        CampoconfiguracaoEditDataController.prototype.constructor = CampoconfiguracaoEditDataController;

        CampoconfiguracaoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        CampoconfiguracaoEditDataController.prototype.url = "campoconfiguracao/";
        CampoconfiguracaoEditDataController.prototype.urlAction = "campoconfiguracao/save";
        CampoconfiguracaoEditDataController.prototype.urlPage = "campoconfiguracao/campoconfiguracaoedit.jsp";

        CampoconfiguracaoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new CampoconfiguracaoEditDataController();
    }
});