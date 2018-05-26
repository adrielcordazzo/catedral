
	define(["util/ListController","router"] ,function (ListController,router) {
    	return function(){
    		var ComentarioListController = function(){
    			this.loadPage(this.viewDidLoad.bind(this));
    		}
    
    		ComentarioListController.prototype =  new ListController();
    
    		ComentarioListController.prototype.constructor = ComentarioListController;
    
    		ComentarioListController.prototype.divLoad =  "#corpoDoSistema";
    		ComentarioListController.prototype.urlPage = "comentario/comentariolist.jsp";
    		ComentarioListController.prototype.urlLoadList = "comentario/list";
    		ComentarioListController.prototype.url = "comentario/";
    		ComentarioListController.prototype.urlDelete = "comentario/";
    		ComentarioListController.prototype.urlDeleteAll = "comentario/deleteAll/";
    
    
    		
    
    		return new ComentarioListController();
    	}
    });
	