define(['util/Abstract','util/SelectController'],function (ABSTRACT,SelectController) {

	return function(){
		function List(){ 
			ABSTRACT.apply(this);
			this.menuContexto = null;
			this.editForm = null;
			this.paginationActive = false;
			this.relation = false;
			this.controllerEdit = null;
		};


		List.prototype = new ABSTRACT();
		List.prototype.constructor = List;

		List.prototype.divLoad = "#APP";

		List.prototype.pagination = {maxResult:20,pagina:1,campos:[],values:[]};

		List.prototype.loadTable = function(){
			
			if(this.urlLoadList){
				this.post(this.urlLoadList,this.pagination,this.termineteLoad.bind(this),this.error,this.complete);
			}else{
				this.termineteLoad();
			}
		}

		List.prototype.termineteLoad = function(data){
			this.termineteLoadList(data);
		}

		List.prototype.inserir = function(){
			window.location.href = "#/"+this.url+"add";
		}

		List.prototype.viewDidLoad = function(view){
			$("[data-action='inserir']",this.divLoad).click(this.inserir.bind(this,{}));
			$("[data-action='deletarSelecionados']",this.divLoad).click(this.deleteAllAction.bind(this));
			
			this.loadTable();
			
			this.modelLine = $('.model',this.divLoad).clone();

			// $('#selectall',this.divLoad).click(this.selectallAction.bind(this,$('#selectall',this.divLoad)));
			// $('#deleteAll',this.divLoad).click(this.deleteAllAction.bind(this));

			var self = this;

			$('.busca').keyup(function(){
				self.pagination.pagina = 1;	
				self.pagination.search = $(this).val();
				self.loadTable();
			});

			if(this.relation){

				var title = $("<h3/>").text($(this.divLoad).find("h1").text()).addClass("pull-left").css("margin","0px");
				var botaoAdd = $("<a/>").text("Inserir").addClass("btn btn-primary pull-right");
				var clear = $("<div/>").addClass("clearfix");
				var hr = $("<hr/>");
				
				$(".table-responsive",this.divLoad).prepend(title,botaoAdd,clear);
				
				var div = $(".table-responsive",this.divLoad);
				$(this.divLoad).html(div);
				botaoAdd.click(this.editRelation.bind(this));

			}

		}

		List.prototype.editRelation = function(){
			if(this.controllerEdit !=null){
				var modal = app.getObject('Modal');
	            modal.newInstance(this.controllerEdit,this.dado,'#formulario',function(){

	            });
        	}
		}

		List.prototype.paginate = function(paginas){
			var self = this;
			var paginastotal = Math.ceil(paginas/self.pagination.maxResult);


			$('#pagination',this.divLoad).unbind();
			$('#pagination',this.divLoad).bootpag({
			total: paginastotal,          // total pages
			page: self.pagination.pagina,            // default page
			maxVisible: 5,     // visible pagination
			leaps: true         // next/prev leaps through maxVisible
		}).on("page", function(event, page){
			
			if(self.pagination.pagina!=page){
				carregar = true;
			}

			self.pagination.pagina = page;
			if(carregar){
				self.loadTable();
			}

			$(this).bootpag({total: paginastotal, maxVisible: 5});
		});

		if(paginastotal<=0 && self.pagination.pagina == 1){
			$('#pagination',this.divLoad).hide();
		}else{
			$('#pagination',this.divLoad).show();
		}
	}

	List.prototype.del = function(id,element){
		if(this.url){
			var modal = app.getObject('ModalDialogConfirm');
			modal.openModal({titulo:"Deseja realmente excluir?",texto:"",tipo:"error",btnConfirma:"Sim",btnCancela:"Não",functionCancela:this.cancelarDel.bind(this),functionConfirma:this.confirmaDel.bind(this, id, element)});
		}
	};

	List.prototype.cancelarDel = function(id){
		var modal = app.getObject('ModalDialogConfirm');
		modal.showError("Exclusão Cancelada","O registro não foi removido");
	};

	List.prototype.confirmaDel = function(id, element){
		this.delete(this.url+id,null,this.delSuccess.bind(this,element),this.error,this.complete);
	};

	List.prototype.delSuccess = function(element,data){
		if(data.valid){
			$(element).remove();
			var modal = app.getObject('ModalDialogConfirm');
			modal.showSuccess("Exclusão Realizada com sucesso","O registro foi removido");
			this.loadTable();
		}else{
			this.showErrors(data);
		}
	}

	List.prototype.deleteAllAction = function(){
		if($('.ckbox').is(":checked")){
			var modal = app.getObject('ModalDialogConfirm');
			modal.openModal({titulo:"Deseja excluir?",texto:"Todos os registros selecionados?",tipo:"error",btnConfirma:"Sim",btnCancela:"Não",functionCancela:this.cancelarDel.bind(this),functionConfirma:this.deleteAllActionActive.bind(this)});
		}else{
			var modal = app.getObject('ModalDialogConfirm');
			modal.showInfo("Nenhum registro selecionado","Nada aconteceu");
		}
	}

	List.prototype.deleteAllActionActive = function(){
		var self = this;
		var campos = [];
		var elementos = {};
		$('input.ckbox:checked').each(function(){
			var id = $(this).val();
			campos.push(id);
			var element = $(this).parent().parent().parent();
			elementos[id] = element;
			
		});
		this.delete(this.urlDeleteAll,campos,this.deleteAllSuccess.bind(this, elementos),this.deleteAllError.bind(this,elementos),self.complete);
		
	}

	List.prototype.deleteAllSuccess = function(elementos,data){
		
		if(data.valid){
			var modal = app.getObject('ModalDialogConfirm');
			modal.showSuccess("Exclusão Realizada com sucesso","Os registros foram removidos");
			for(var i in data.list){
				var dado = data.list[i];
				elementos[dado].remove();
			}
			this.loadTable();
		}else{
			this.showErrors(data);
		}
	}

	List.prototype.showErrors = function(data){
		var texto = "";
		for(var i in data.errors){
			var dado = data.errors[i];
			texto += dado.message + '\n';
		}
		var modal = app.getObject('ModalDialogConfirm');
		modal.openModal({titulo:"Erros foram encontrados ",texto:texto,tipo:"error",btnConfirma:"ok",functionConfirma:function(){}});
	}

	List.prototype.deleteAllError = function(elementos,data){
		for(var i in data.list){
			var dado = data.list[i];
			elementos[dado].remove();
		}
		var erros ="";
		for(var i in data.errors){
			var dado = data.list[i];
			erros += dado.message + "<br>";
		}
		var modal = app.getObject('ModalDialogConfirm');
		modal.showError("Falha ao excluir registros",erros);
	}


	List.prototype.termineteLoadList = function(data){
			$("tbody",this.divLoad).html("");
			if(data.list){
				var list = data.list;
				for(var i in list){
					var element = $(this.modelLine).clone();
					var dado = list[i];

					rivets.bind(element, dado);
					
					// $(".m-id input",element).attr("id",dado.id);
					// $(".m-id label",element).attr("for",dado.id);
					// $(".m-id input",element).val(dado.id);

					// $(".m-nome",element).html(dado.nome); 
					// $(".m-email",element).html(dado.email); 

					$("[data-action='edit']", element).prop("href",'#/'+this.url+'edit/'+dado.id);
					$("[data-action='delete']", element).click(this.del.bind(this,dado.id,element));

					$("tbody",this.divLoad).append(element);

					this.paginate(data.countResult);
				}
			}
		}

	List.prototype.detectselect = function(){
		var self = this;
        this.selects = $('select',this.divLoad);
        
        $(this.selects).each(function( index ) {
            var selectController = SelectController($(this),null,self);

        });
	}

	List.prototype.detectselectFixo = function(){
		var self = this;
        this.selects = $('.selectfixo',this.divLoad);
        
        $(this.selects).each(function( index ) {
            $(this).selectpicker();

        });
	}
	

	return new List();

}
});


