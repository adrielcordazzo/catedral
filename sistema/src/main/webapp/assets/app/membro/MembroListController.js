
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var MembroListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		MembroListController.prototype =  new ListController();

		MembroListController.prototype.constructor = MembroListController;

		MembroListController.prototype.divLoad =  "#corpoDoSistema";
		MembroListController.prototype.urlPage = "membro/membrolist.jsp";
		MembroListController.prototype.urlLoadList = "membro/list";
		MembroListController.prototype.url = "membro/";
		MembroListController.prototype.urlDelete = "membro/";
		MembroListController.prototype.urlDeleteAll = "membro/deleteAll/";

		MembroListController.prototype.termineteLoadList = function(data){
			var self = this;
			
			$("[data-action='uploadMembros']",this.divLoad).click(function(){
		        self.uploadMembros();
		    });
			
			$("tbody",this.divLoad).html("");
			if(data.list){
				var list = data.list;
				for(var i in list){
					var element = $(this.modelLine).clone();
					var dado = list[i];

					rivets.bind(element, dado);

					var cargos = "";
					if(dado.cargos){
						for(var i in dado.cargos){
							cargos += "<li>"+dado.cargos[i].cargo.cargo + "</li>";
						}
					}
					if(cargos != ""){
						cargos = "<ul>"+cargos+"</ul>";
					}
					
					if(dado.imagem){
						$(".m-foto",element).html('<img src="' + dado.imagem + '" width="100%" />'); 
						//$(".m-foto",element).html('<img src="membro/getImagem/' + dado.id + '" width="100" />');
					}

					$(".m-cargos",element).html(cargos); 

					$("[data-action='edit']", element).prop("href",'#/'+this.url+'edit/'+dado.id);
					$("[data-action='delete']", element).click(this.del.bind(this,dado.id,element));

					$("tbody",this.divLoad).append(element);

					this.paginate(data.countResult);
				}
			}

			this.detectselect();
			this.detectselectFixo();

			$("[name='aniversario']",this.divLoad).change(this.aplicaFiltro.bind(this));
			$("[name='cargo']",this.divLoad).change(this.aplicaFiltro.bind(this));
		}

		MembroListController.prototype.aplicaFiltro = function(){
    
		      var self = this;
		      var aniversario = $("[name='aniversario']",this.divLoad).val();
		      var cargo = $("[name='cargo']",this.divLoad).val();

		      self.pagination.campos = [];
		      self.pagination.values = [];
		      self.pagination.pagina = 1;

		      
		      if(aniversario!=null && aniversario!=""){
		        self.pagination.campos.push("aniversario");
		        self.pagination.values.push(aniversario);
		        this.dado.aniversario = aniversario;
		      }

		      if(cargo!=null && cargo!=""){
		        self.pagination.campos.push("cargo");
		        self.pagination.values.push(cargo);
		        this.dado.cargo = cargo;
		      }

		      self.loadTable();
		}
		
		MembroListController.prototype.uploadMembros = function(data){
			var self = this;
			$("#log",self.divLoad).html('');
			var self = this;
			var modal = app.getObject('Modal');
			//var m = new modal(data);
			modal.newInstance('membro/MembrosLoadXls',data,null,function(event,data){
				$("#log",self.divLoad).html('');
				console.log(arguments);
				if (!data){
					data = event;
				}
				if(data.valid){
					for(var i in data.list){
						var dado = data.list[i];
						$("#log",self.divLoad).append(dado + "<br>");
					}
					$bu = $('<button/>',{class:"btn btn-primary"}).text('limpar');
				$bu.click(function(){
					$("#log").html('');
				});
				$("#log",self.divLoad).prepend('<br/>');
				$("#log",self.divLoad).prepend($bu);
				}
				

				self.loadPage(self.viewDidLoad.bind(self));
			});

		}
		

		return new MembroListController();
	}
});