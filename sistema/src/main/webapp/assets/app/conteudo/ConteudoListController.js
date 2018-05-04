
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var ConteudoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		ConteudoListController.prototype =  new ListController();

		ConteudoListController.prototype.constructor = ConteudoListController;

		ConteudoListController.prototype.divLoad =  "#corpoDoSistema";
		ConteudoListController.prototype.urlPage = "conteudo/conteudo.jsp";
		ConteudoListController.prototype.urlLoadList = "conteudo/list";
		ConteudoListController.prototype.url = "conteudo/";
		ConteudoListController.prototype.urlDelete = "conteudo/";
		ConteudoListController.prototype.urlDeleteAll = "conteudo/deleteAll/";

		ConteudoListController.prototype.uploadProdutos = function(data) {
			var self = this;
			var modal = app.getObject('Modal');
			// var m = new modal(data);
			modal.newInstance('conteudo/OrdemUpload', data, function() {
				if (data.valid) {
					for ( var i in data.list) {
						var dado = data.list[i];
						$("#log", self.divLoad).append(dado + "<br>");
					}
				}
				self.loadPage();
			});

		}

		ConteudoListController.prototype.termineteLoadList = function(data){

			$("[data-action='upload']", this.divLoad).click(this.uploadProdutos.bind(this));

			this.detectselect();

			$("tbody",this.divLoad).html("");
			if(data.list){
				var list = data.list;
				for(var i in list){
					var element = $(this.modelLine).clone();
					var dado = list[i];

					rivets.bind(element, dado);

					$("[data-action='edit']", element).prop("href",'#/'+this.url+'edit/'+dado.id);
					$("[data-action='delete']", element).click(this.del.bind(this,dado.id,element));

					$("tbody",this.divLoad).append(element);

					this.paginate(data.countResult);
				}
			}

			$("[name='conteudotipofilter']",this.divLoad).change(this.aplicaFiltro.bind(this));
		}

		ConteudoListController.prototype.aplicaFiltro = function(){
    
		    var self = this;
			var conteudotipoId = $("[name='conteudotipofilter']",this.divLoad).val();

			self.pagination.campos = [];
			self.pagination.values = [];
			self.pagination.pagina = 1;


			if(conteudotipoId!=null && conteudotipoId!="null"){
				self.pagination.campos.push("conteudotipo.id");
				self.pagination.values.push(conteudotipoId);
			}

			self.loadTable();
		 }

		return new ConteudoListController();
	}
});