
    define(["util/AbstractForm","router",'util/pasta/PastaWidgetController','util/anexo/AnexoListController'] ,function (AbstractForm,router,PastaWidgetController,Anexo) {
    return function(){
        var CelulaEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        CelulaEditDataController.prototype =  new AbstractForm();

        CelulaEditDataController.prototype.constructor = CelulaEditDataController;

        CelulaEditDataController.prototype.divLoad =  "#corpoDoSistema";
        CelulaEditDataController.prototype.url = "celula/";
        CelulaEditDataController.prototype.urlAction = "celula/save";
        CelulaEditDataController.prototype.urlPage = "celula/celulaedit.jsp";

        CelulaEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
            this.activeFotos();
        }

        CelulaEditDataController.prototype.activeFotos = function() {
            if (this.dado && this.dado.id && this.dado.pasta) {
                var self = this;
                
                self.anexo = new Anexo(self.dado.pasta,true);
                self.anexo.loadPage(self.anexo.viewDidLoad.bind(self.anexo));

                //self.pastaWidget = new PastaWidgetController(self.dado.pasta.id,null,true);
                //this.pastaWidget.loadPage(self.pastaWidget.viewDidLoad.bind(self.pastaWidget));
                
            } 

        }

        return new CelulaEditDataController();
    }
});
