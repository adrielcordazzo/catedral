
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var Contratante_rolesEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        Contratante_rolesEditDataController.prototype =  new AbstractForm();

        Contratante_rolesEditDataController.prototype.constructor = Contratante_rolesEditDataController;

        Contratante_rolesEditDataController.prototype.divLoad =  "#corpoDoSistema";
        Contratante_rolesEditDataController.prototype.url = "contratante_roles/";
        Contratante_rolesEditDataController.prototype.urlAction = "contratante_roles/save";
        Contratante_rolesEditDataController.prototype.urlPage = "contratante_roles/contratante_rolesedit.jsp";

        Contratante_rolesEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new Contratante_rolesEditDataController();
    }
});