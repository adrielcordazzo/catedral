
  define(["util/AbstractController"],function (AbstractController) {

  function ContratanteMenuContextoController(listController){
    this.listController = listController;
  };

  ContratanteMenuContextoController.prototype = new AbstractController();
  ContratanteMenuContextoController.prototype.constructor = ContratanteMenuContextoController;

  ContratanteMenuContextoController.prototype.urlPage = "contratante/contratantemenucontexto";

  ContratanteMenuContextoController.prototype.divLoad = "#contexto";

  ContratanteMenuContextoController.prototype.viewDidLoad = function(){
    var self = this;
    $("[data-action='inserir']",this.divLoad).click(function(){
        self.listController.editData({});
    });
    $("[data-action='deletarSelecionados']",this.divLoad).click(this.listController.deleteAllAction.bind(this.listController));
  }

  return ContratanteMenuContextoController;
});