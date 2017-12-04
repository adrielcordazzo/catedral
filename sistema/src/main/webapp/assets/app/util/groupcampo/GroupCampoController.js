define(["util/ListController"],function (ListController) {

	function GroupCampo(dadosTela,dadosForm,variavelName,tipocampo,restricao){

		ListController.apply(this);
		this.dadosTela = dadosTela;
		this.dadosForm = dadosForm;
		this.variavelName = variavelName;
		this.tipocampo = tipocampo;	
		this.restricao=restricao;
		if(dadosForm){
				var dados = {};
                for(c in dadosForm){
                    var item = dadosForm[c];
                    var id = item.campo.id;
                    var valor = item.valor;
                    dados[id] = valor;
                }
			this.dado = dados;
		}else{
			this.dado = {};
		}
		
	};

	GroupCampo.prototype = new ListController();
	GroupCampo.prototype.constructor = GroupCampo;

	GroupCampo.prototype.urlPage = 'util/groupcampo/groupcampo.jsp';

	GroupCampo.prototype.divLoad = '.group';

	GroupCampo.prototype.caminhoForm = "caminhoForm";
	// GroupCampo.prototype.nameButton = "new button";
	// GroupCampo.prototype.title = "titulo";
	GroupCampo.prototype.btnVisible = true;

	GroupCampo.prototype.getData = function(){
		 var dataForm = [];
         var json = this.dado;
         for(var i in json){
            var idC = i;
            var valor = json[i];
            var jsonI = {campo:{id:idC},valor:valor};
            if(valor){
                dataForm.push(jsonI);    
            }
        }

		return dataForm;
	}


	GroupCampo.prototype.viewDidLoad = function(){
		
		this.modelTexto = $('.model-texto',this.divLoad).clone();
		$('.model-texto',this.divLoad).remove();
		this.modelSelect = $('.model-select',this.divLoad).clone();
		$('.model-select',this.divLoad).remove();
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

				if(tipocampo == 1){
					model = this.modelTexto.clone();	
					$('input',model).attr("rv-value",item.id);
				}else if(tipocampo == 2){
					model = this.modelSelect.clone();	
					$('select',model).attr("rv-value",item.id);
					if(item.opcoes){
						var opcoes = item.opcoes.split("|");
						for(var i in opcoes){
							var opcao = opcoes[i];
							var option = $("<option/>",{value:opcao}).text(opcao);
							$('select',model).append(option);
						}
					}

				}else {
					$('input',model).attr("rv-checked",item.id);
				}
				
				$('.nomecampo',model).text(nomeCampo);
				$('input',model).attr("name",item.id);
				
				$('input',model).attr("placeholder",nomeCampo);

				

				if(item[this.restricao]){
					for(var i in item[this.restricao]){
						var classe = item[this.restricao][i];
						$(model).addClass(classe.id);
					}
				}
				
				this.model.append(model);
			}
		}
		this.detectselect();
		this.dataBind();



	}

	GroupCampo.prototype.success = function(data) {
		if (data.valid==true){
			$(this).trigger('destroy');
		}else{
			this.populateFormError(data);
		}
	};

	GroupCampo.prototype.error = function(data) {
	};

	GroupCampo.prototype.complete = function() {

	};

	return GroupCampo;
});



