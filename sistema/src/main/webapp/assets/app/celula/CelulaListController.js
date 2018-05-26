
	define(["util/ListController","router"] ,function (ListController,router) {
    	return function(){
    		var CelulaListController = function(){
    			this.loadPage(this.viewDidLoad.bind(this));
    		}
    
    		CelulaListController.prototype =  new ListController();
    
    		CelulaListController.prototype.constructor = CelulaListController;
    
    		CelulaListController.prototype.divLoad =  "#corpoDoSistema";
    		CelulaListController.prototype.urlPage = "celula/celulalist.jsp";
    		CelulaListController.prototype.urlLoadList = "celula/list";
    		CelulaListController.prototype.url = "celula/";
    		CelulaListController.prototype.urlDelete = "celula/";
    		CelulaListController.prototype.urlDeleteAll = "celula/deleteAll/";
    
    
    		CelulaListController.prototype.termineteLoadList = function(data){


                $("tbody",this.divLoad).html("");
                if(data.list){ console.log("MAS QUE MERDA",data.list);
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
    
    		return new CelulaListController();
    	}
    });
	