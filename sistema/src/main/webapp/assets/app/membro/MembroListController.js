
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var MembroListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
			this.openLoad();
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

				$("[data-action='imprimir']",this.divLoad).click(function(){
			        self.imprimir(data.list);
			    });

				var list = data.list;
				$(".totalregistros",this.divLoad).text(data.countResult);
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
					
					//if(dado.imagem){
						//$(".m-foto",element).html('<img src="' + dado.imagem + '" width="100%" />'); 
						//$(".m-foto",element).html('<img src="membro/getImagem/' + dado.id + '" width="100" />');
					//}

					this.get("membro/getImagem/"+dado.id,null,this.colocafoto.bind(this,element),null,null);

					$(".m-cargos",element).html(cargos); 


					var ficha = dado.numeroficha;
					if(dado.numeroficha == null){
						ficha = "Sem Ficha";
					}
					var numeroficha = '<a class="editable numeroficha" href="#" data-type="text" data-title="Ficha">' + ficha + '</a>';
					$(".m-numeroficha",element).html(numeroficha);
					
					$('.editable',element).editable()
					.on('hidden', self.saveNumeroficha.bind(this,dado.id,element));

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
			$("[name='foto']",this.divLoad).change(this.aplicaFiltro.bind(this));
			$("[name='ficha']",this.divLoad).change(this.aplicaFiltro.bind(this));
			$("[name='ativo']",this.divLoad).change(this.aplicaFiltro.bind(this));

			this.closeLoad();
		}

		MembroListController.prototype.colocafoto = function(element,data){
			$(".m-foto",element).html('<img src="' + data + '" width="100%" />'); 
		}

		MembroListController.prototype.saveNumeroficha = function(dado, element, event, value){
			var numeroficha = $(".numeroficha",element).text();
			this.openLoad();
			this.get("membro/saveFicha/"+dado+"/"+numeroficha,null,this.retornaSaveFicha.bind(this),null,null);
		}

		MembroListController.prototype.retornaSaveFicha = function(){
			this.closeLoad();
		}

		MembroListController.prototype.imprimir = function(data){
			/*var aniversario = $("[name='aniversario']",this.divLoad).val();
		    var cargo = $("[name='cargo']",this.divLoad).val();
		    var ficha = $("[name='ficha']",this.divLoad).val();
			//window.open('membro/imprimir','_blank');	
			var data = {"dados":{"aniversario":aniversario,"cargo":cargo,"ficha":ficha}};
			data = JSON.stringify({"dados":{"aniversario":aniversario,"cargo":cargo,"ficha":ficha}});
			//var oi = JSON.parse(data)
			$.post("membro/imprimir", data, function () {
			    var w = window.open('about:blank');
			    w.document.open();
			    //w.document.write(data);
			    //w.document.close();
			});*/

			var aniversario = $("[name='aniversario']",this.divLoad).val();
			var cargo = $("[name='cargo']",this.divLoad).val();
		    var ficha = $("[name='ficha']",this.divLoad).val();
			var data = {"aniversario":aniversario,"cargo":cargo,"ficha":ficha};


			var form = $("<form/>");
			form.attr("id","formImprimir");
			form.attr("method","POST");
			form.attr("target","_blank");
			form.append("<input />");
			form.find("input").attr("name","dados");
			form.find("input").attr("value",JSON.stringify(data));
			//form.attr('action','http://moderna.xlabi.com.br/relatorio/relatoriocatedral.php');
			form.attr('action','membro/imprimir');
			form.attr("onsubmit","");
			$("body").append(form);
			form.submit();
			$("body").remove(form);

			/*$('#json','#formRelatorio').val(JSON.stringify(data));
		
			$('#formRelatorio').attr('action','http://www.xlabi.com.br/cinex/relatorio.php');
			$('#formRelatorio').attr('onsubmit','');
			$('#formRelatorio').submit();*/


		}

		MembroListController.prototype.aplicaFiltro = function(){

			this.openLoad();	
    
		      var self = this;
		      var aniversario = $("[name='aniversario']",this.divLoad).val();
		      var cargo = $("[name='cargo']",this.divLoad).val();
		      var foto = $("[name='foto']",this.divLoad).val();
		      var ficha = $("[name='ficha']",this.divLoad).val();
		      var ativo = $("[name='ativo']",this.divLoad).val();

		      self.pagination.campos = [];
		      self.pagination.values = [];
		      self.pagination.pagina = 1;

		      this.dado.aniversario = aniversario;
		      if(aniversario!=null && aniversario!=""){
		        self.pagination.campos.push("aniversario");
		        self.pagination.values.push(aniversario);
		      }

		      this.dado.cargo = cargo;
		      if(cargo!=null && cargo!=""){
		        self.pagination.campos.push("cargo");
		        self.pagination.values.push(cargo);
		      }

		      this.dado.foto = foto;
		      if(foto!=null && foto!=""){
		        self.pagination.campos.push("foto");
		        self.pagination.values.push(foto);
		      }

		      this.dado.ficha = ficha;
		      if(ficha!=null && ficha!=""){
		        self.pagination.campos.push("ficha");
		        self.pagination.values.push(ficha);
		      }

		      this.dado.ativo = ativo;
		      if(ativo!=null && ativo!=""){
		        self.pagination.campos.push("ativo");
		        self.pagination.values.push(ativo);
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