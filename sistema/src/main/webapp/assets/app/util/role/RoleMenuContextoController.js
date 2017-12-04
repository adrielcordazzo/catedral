
  define(["util/AbstractController"],function (AbstractController) {

  function RoleMenuContextoController(listController){
    this.listController = listController;
  };

  RoleMenuContextoController.prototype = new AbstractController();
  RoleMenuContextoController.prototype.constructor = RoleMenuContextoController;

  RoleMenuContextoController.prototype.urlPage = "util/role/rolemenucontexto";

  RoleMenuContextoController.prototype.divLoad = "#contexto";

  RoleMenuContextoController.prototype.viewDidLoad = function(){
    var self = this;
    $("[data-action='inserir']",this.divLoad).click(function(){
        self.listController.editData({});
    });
    $("[data-action='deletarSelecionados']",this.divLoad).click(this.listController.deleteAllAction.bind(this.listController));
  }

  return RoleMenuContextoController;
});