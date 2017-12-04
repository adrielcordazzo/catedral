
	define(["util/AbstractForm","router",'util/pasta/PastaWidgetController'] ,function (AbstractForm,router,PastaWidgetController) {
    return function(){
        var ContatoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ContatoEditDataController.prototype =  new AbstractForm();

        ContatoEditDataController.prototype.constructor = ContatoEditDataController;

        ContatoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ContatoEditDataController.prototype.url = "contato/";
        ContatoEditDataController.prototype.urlAction = "contato/save";
        ContatoEditDataController.prototype.urlPage = "contato/contatoedit.jsp";

        ContatoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
            this.activeFotos();
            if(this.dado && this.dado.id && this.dado.imovelid){
                this.get("imovel/" + this.dado.imovelid,null,this.preencheImovel.bind(this),null,null);
            }
        }

        ContatoEditDataController.prototype.preencheImovel = function(data) {
            var imovel = data.data;
            $("#imovel").html("<strong>Imóvel Relacionado: </strong>" + imovel.titulo + " <strong>Cód: </strong>" + imovel.cod);
        }

        ContatoEditDataController.prototype.activeFotos = function() {
            if (this.dado && this.dado.id && this.dado.pasta) {
                var self = this;
                
                self.pastaWidget = new PastaWidgetController(self.dado.pasta.id,null,true);
                this.pastaWidget.loadPage(self.pastaWidget.viewDidLoad.bind(self.pastaWidget));
                
            } 
        }

        return new ContatoEditDataController();
    }
});