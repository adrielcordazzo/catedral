
  define(["util/AbstractController"],function (AbstractController) {

  function Users_rolesMenuContextoController(listController){
    this.listController = listController;
  };

  Users_rolesMenuContextoController.prototype = new AbstractController();
  Users_rolesMenuContextoController.prototype.constructor = Users_rolesMenuContextoController;

  Users_rolesMenuContextoController.prototype.urlPage = "paciente/pacientemenucontexto";

  Users_rolesMenuContextoController.prototype.divLoad = "#contexto";

  Users_rolesMenuContextoController.prototype.viewDidLoad = function(){
    var self = this;
    $("[data-action='inserir']",this.divLoad).click(function(){
        self.listController.editData({});
    });
    $("[data-action='deletarSelecionados']",this.divLoad).click(this.listController.deleteAllAction.bind(this.listController));
  }

  return Users_rolesMenuContextoController;
});