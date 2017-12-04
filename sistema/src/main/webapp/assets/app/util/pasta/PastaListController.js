
define(["util/ListController"],function (ListController) {

	function PastaListController(){ 
		
	};

	PastaListController.prototype = new ListController();
	
	PastaListController.prototype.constructor = PastaListController;

	PastaListController.prototype.urlPage = "util/pasta/pastalist";
	PastaListController.prototype.urlDelete = "pasta/";
	PastaListController.prototype.urlDeleteAll = "pasta/deleteAll/";
	PastaListController.prototype.urlGet = "pasta/";
	PastaListController.prototype.urlLoadList = "pasta/list";

	PastaListController.prototype.formController = "pasta/PastaEditDataController";

	PastaListController.prototype.contextoController = "util/pasta/PastaMenuContextoController";

	

	PastaListController.prototype.termineteLoadList = function(data){
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

	return PastaListController;
});
