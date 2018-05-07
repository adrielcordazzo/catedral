
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

          $("#membro",this.element).change(this.changemembro.bind(this));
          this.closeLoad();

          if(this.dado && this.dado.id && this.dado.membros){
            this.membros = this.dado.membros;
          }
        }

        EventoEditDataController.prototype.changemembro = function(e){
            if(!e.originalEvent)
                return;
            var idmembro = $("#membro",this.element).val();
            $("#membro",this.element).selectpicker("val","");
            $("#membro",this.element).selectpicker("refresh");
            this.buscamembroLista(idmembro);
        }

        EventoEditDataController.prototype.buscamembroLista = function(idmembro, valor){
            if(!idmembro)
                return;

            this.get("membro/"+idmembro,null,this.addmembroLista.bind(this, valor,1),null,null);
        }

        EventoEditDataController.prototype.addmembroLista = function(valor, quantidade, data,ordem){
            var element = $(this.cloneListaMembro).clone();
            var dado = data.data;
            
            $(".m-nome",element).html(dado.nome);  
            $(".m-email",element).html(dado.email);  
            $(".m-telefone",element).html(dado.telefone); 
            $(".m-rg",element).html(dado.rg);  
            $(".m-cpf",element).html(dado.cpf);  

            
            var json = {id:dado.id};
            
            this.membros.push(json);

            $("[data-action='delete']", element).click(this.deletamembro.bind(this,element,json));

            $("tbody",this.element).append(element);

            element.data('json',json);
        }

        EventoEditDataController.prototype.deletamembro = function(element,json){
            var index = this.membros.indexOf(json);
            this.membros.splice(index,1);
            $(element).remove();
        }

        EventoEditDataController.prototype.save = function() {

             var dataForm = {};

             $.extend(dataForm, this.dado);

             dataForm.membros = this.membros;

            
             this.monteSelectSave(dataForm);
             this.processData(dataForm);
 console.info("OBAA",dataForm);
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