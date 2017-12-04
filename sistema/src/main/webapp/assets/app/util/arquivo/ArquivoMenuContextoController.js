
  define(["util/AbstractController"],function (AbstractController) {

  function ArquivoMenuContextoController(listController){
    this.listController = listController;
  };

  ArquivoMenuContextoController.prototype = new AbstractController();
  ArquivoMenuContextoController.prototype.constructor = ArquivoMenuContextoController;

  ArquivoMenuContextoController.prototype.urlPage = "paciente/pacientemenucontexto";

  ArquivoMenuContextoController.prototype.divLoad = "#contexto";

  ArquivoMenuContextoController.prototype.viewDidLoad = function(){
    var self = this;
    $("[data-action='inserir']",this.divLoad).click(function(){
        self.listController.editData({});
    });
    $("[data-action='deletarSelecionados']",this.divLoad).click(this.listController.deleteAllAction.bind(this.listController));
  }

  return ArquivoMenuContextoController;
});