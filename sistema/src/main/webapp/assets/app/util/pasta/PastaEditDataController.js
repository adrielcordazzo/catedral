
define(["util/AbstractForm"],function (AbstractForm) {

	function PastaEditDataController(data){
		AbstractForm.apply(this);
		this.dado = data;
	};

	PastaEditDataController.prototype = new AbstractForm();
	PastaEditDataController.prototype.constructor = PastaEditDataController;

	PastaEditDataController.prototype.urlPage = "pasta/pastaeditdata";

	PastaEditDataController.prototype.divLoad = "#formulario";

	PastaEditDataController.prototype.urlAction = "pasta/save";

	PastaEditDataController.prototype.formDidLoad = function(){

	}


	return PastaEditDataController;
});
