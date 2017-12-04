define(['text!util/usuario/usuarioedit.jsp','util/AbstractForm','router','util/pasta/PastaWidgetController','util/anexo/AnexoListController'] ,function (HTMLPAGINA,AbstractForm,router,PastaWidgetController,Anexo) {
    return function(){
        var UsuarioEditDataController = function(){
            this.contentHtml = HTMLPAGINA;
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        UsuarioEditDataController.prototype =  new AbstractForm();

        UsuarioEditDataController.prototype.constructor = UsuarioEditDataController;

        UsuarioEditDataController.prototype.divLoad =  "#corpoDoSistema";
        UsuarioEditDataController.prototype.url = "usuario/";
        UsuarioEditDataController.prototype.urlAction = "usuario/save";

      

        UsuarioEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
            this.activeFotos();
            this.dado.roles = this.dado.permissoes;
            console.log("oioi",this.dado);
            $('#roles',this.divLoad).selectpicker('render');
            $('#roles',this.divLoad).selectpicker('refresh');
        }

        UsuarioEditDataController.prototype.activeFotos = function() {
            if (this.dado && this.dado.id && this.dado.pasta) {

                var self = this;
                //self.pastaWidget = new PastaWidgetController(self.dado.pasta.id,null,true);
                //this.pastaWidget.loadPage(self.pastaWidget.viewDidLoad.bind(self.pastaWidget));

                self.anexo = new Anexo(self.dado.pasta.id);
                self.anexo.loadPage(self.anexo.viewDidLoad.bind(self.anexo));
                
            } 
        }

        return new UsuarioEditDataController();
    }
});