define(['util/Abstract'],function (Abstract) {

	function GroupDados(dadosTela,dadosForm,variavelName,tipocampo){

		Abstract.apply(this);
		this.dadosTela = dadosTela;
		this.dadosForm = dadosForm;
		this.variavelName = variavelName;
		this.tipocampo = tipocampo;	
		if(dadosForm){
			this.dado = dadosForm;
			console.info(dadosForm);
		}else{
			this.dado = {};
		}
		
	};

	GroupDados.prototype = new Abstract();
	GroupDados.prototype.constructor = GroupDados;

	GroupDados.prototype.urlPage = 'util//groupdados/groupdados.jsp';

	GroupDados.prototype.divLoad = '.group';

	GroupDados.prototype.caminhoForm = "caminhoForm";
	// GroupDados.prototype.nameButton = "new button";
	// GroupDados.prototype.title = "titulo";
	GroupDados.prototype.btnVisible = true;

	GroupDados.prototype.getData = function(){

		return this.dado;
	}


	GroupDados.prototype.viewDidLoad = function(){
		
		this.modelTexto = $('.model-texto',this.divLoad).clone();
		$('.model-texto',this.divLoad).remove();
		this.modelChek  = $('.model-check',this.divLoad).clone();
		$('.model-check',this.divLoad).remove();
		$('.model',this.divLoad).html();
		this.model = $('.model',this.divLoad);

		
		if(this.dadosTela){
			for(var i in this.dadosTela){
				var item = this.dadosTela[i];
				var nomeCampo = item[this.variavelName];
				var tipocampo = item[this.tipocampo];
				var model = this.modelChek.clone();

				if(tipocampo){
					model = this.modelTexto.clone();	
					$('input',model).attr("rv-value",item.id);
				}else{
					$('input',model).attr("rv-checked",item.id);
				}
				
				$('.nomecampo',model).text(nomeCampo);
				$('input',model).attr("name",item.id);
				
				$('input',model).attr("placeholder",nomeCampo);
				
				this.model.append(model);
			}
		}

		this.dataBind();



	}

	GroupDados.prototype.success = function(data) {
		if (data.valid==true){
			$(this).trigger('destroy');
		}else{
			this.populateFormError(data);
		}
	};

	GroupDados.prototype.error = function(data) {
	};

	GroupDados.prototype.complete = function() {

	};

	return GroupDados;
});



