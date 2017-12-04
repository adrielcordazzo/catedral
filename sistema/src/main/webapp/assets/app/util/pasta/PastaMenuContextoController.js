
  define(["util/AbstractController"],function (AbstractController) {

  function PastaMenuContextoController(listController){
    this.listController = listController;
  };

  PastaMenuContextoController.prototype = new AbstractController();
  PastaMenuContextoController.prototype.constructor = PastaMenuContextoController;

  PastaMenuContextoController.prototype.urlPage = "paciente/pacientemenucontexto";

  PastaMenuContextoController.prototype.divLoad = "#contexto";

  PastaMenuContextoController.prototype.viewDidLoad = function(){
    var self = this;
    $("[data-action='inserir']",this.divLoad).click(function(){
        self.listController.editData({});
    });
    $("[data-action='deletarSelecionados']",this.divLoad).click(this.listController.deleteAllAction.bind(this.listController));
  }

  return PastaMenuContextoController;
});