
define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var ComentarioEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ComentarioEditDataController.prototype =  new AbstractForm();

        ComentarioEditDataController.prototype.constructor = ComentarioEditDataController;

        ComentarioEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ComentarioEditDataController.prototype.url = "comentario/";
        ComentarioEditDataController.prototype.urlAction = "comentario/save";
        ComentarioEditDataController.prototype.urlPage = "comentario/comentarioedit.jsp";

        ComentarioEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
        }

        return new ComentarioEditDataController();
    }
});
