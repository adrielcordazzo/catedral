
define(["util/AbstractForm"],function (AbstractForm) {

	function ArquivoEditDataController(data){
		AbstractForm.apply(this);
		this.dado = data;
	};

	ArquivoEditDataController.prototype = new AbstractForm();
	ArquivoEditDataController.prototype.constructor = ArquivoEditDataController;

	ArquivoEditDataController.prototype.urlPage = "util/arquivoeditdata";

	ArquivoEditDataController.prototype.divLoad = "#formulario";

	ArquivoEditDataController.prototype.urlAction = "arquivo/save";

	ArquivoEditDataController.prototype.formDidLoad = function(){

	}


	return ArquivoEditDataController;
});
