
define(["util/ListController"],function (ListController) {

	function Users_rolesListController(){ 
		
	};

	Users_rolesListController.prototype = new ListController();
	
	Users_rolesListController.prototype.constructor = Users_rolesListController;

	Users_rolesListController.prototype.urlPage = "users_roles/users_roleslist";
	Users_rolesListController.prototype.urlDelete = "users_roles/";
	Users_rolesListController.prototype.urlDeleteAll = "users_roles/deleteAll/";
	Users_rolesListController.prototype.urlGet = "users_roles/";
	Users_rolesListController.prototype.urlLoadList = "users_roles/list";

	Users_rolesListController.prototype.formController = "users_roles/Users_rolesEditDataController";

	Users_rolesListController.prototype.contextoController = "users_roles/Users_rolesMenuContextoController";

	

	Users_rolesListController.prototype.termineteLoadList = function(data){
		$("tbody",this.divLoad).html("");
		
		if(data.list){
			
			var list = data.list;
			for(var i in list){
				var element = $(this.modelLine).clone();
				var dado = list[i];
				$(".m-id input",element).attr("id",dado.id);
				$(".m-id label",element).attr("for",dado.id);
				$(".m-id input",element).val(dado.id);
				 
				$(".m-nome",element).html(dado.nome); 
				$(".m-sexo",element).html(dado.sexo); 
				
				$("[data-action='edit']", element).click(this.edit.bind(this,dado.id));
				$("[data-action='delete']", element).click(this.del.bind(this,dado.id,element));

				$("tbody",this.divLoad).append(element);

			}
			
			this.paginate(data.countResult);
		}
	}

	return Users_rolesListController;
});
