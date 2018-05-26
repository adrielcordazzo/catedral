
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var EventoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
            this.membros = [];
            this.cloneListaMembro = null;
        }

        EventoEditDataController.prototype =  new AbstractForm();

        EventoEditDataController.prototype.constructor = EventoEditDataController;

        EventoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        EventoEditDataController.prototype.url = "evento/";
        EventoEditDataController.prototype.urlAction = "evento/save";
        EventoEditDataController.prototype.urlPage = "evento/eventoedit.jsp";

        EventoEditDataController.prototype.termineteLoadForm = function(){

          if(this.cloneListaMembro == null){
            this.cloneListaMembro = $(".model",this.element).clone();
            $(".model",this.element).remove();
          }

          
        }

        

        EventoEditDataController.prototype.save = function() {

             var dataForm = {};

             $.extend(dataForm, this.dado);

                         
             this.monteSelectSave(dataForm);
             this.processData(dataForm);

             if(this.urlAction!=null){
              this.openLoad(this.divLoad);
              if(!dataForm.id)
               this.post(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null);
             else
               this.put(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null);
           }else{
            console.error("not define urlAction");
          }
        };

        return new EventoEditDataController();
    }
});