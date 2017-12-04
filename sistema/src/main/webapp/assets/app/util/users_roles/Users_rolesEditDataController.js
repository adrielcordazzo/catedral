
define(["util/AbstractForm"],function (AbstractForm) {

	function Users_rolesEditDataController(data){
		AbstractForm.apply(this);
		this.dado = data;
	};

	Users_rolesEditDataController.prototype = new AbstractForm();
	Users_rolesEditDataController.prototype.constructor = Users_rolesEditDataController;

	Users_rolesEditDataController.prototype.urlPage = "users_roles/users_roleseditdata";

	Users_rolesEditDataController.prototype.divLoad = "#formulario";

	Users_rolesEditDataController.prototype.urlAction = "users_roles/save";

	Users_rolesEditDataController.prototype.formDidLoad = function(){

	}


	return Users_rolesEditDataController;
});
