
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var EventoListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		EventoListController.prototype =  new ListController();

		EventoListController.prototype.constructor = EventoListController;

		EventoListController.prototype.divLoad =  "#corpoDoSistema";
		EventoListController.prototype.urlPage = "evento/eventolist.jsp";
		EventoListController.prototype.urlLoadList = "evento/list";
		EventoListController.prototype.url = "evento/";
		EventoListController.prototype.urlDelete = "evento/";
		EventoListController.prototype.urlDeleteAll = "evento/deleteAll/";
		
		EventoListController.prototype.criarEventos = function() {
			this.openLoad();
			var self = this;
			this.get("evento/criarEventos",null,function(){self.closeLoad();},null,null);

		}

		EventoListController.prototype.termineteLoadList = function(data){
			this.detectselect();
			
			$("[data-action='criarEventos']",this.divLoad).click(this.criarEventos.bind(this));
			
			this.dataBind();

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

			
		}
		

		return new EventoListController();
	}
});