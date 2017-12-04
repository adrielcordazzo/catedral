define(['util/Abstract','util/SelectController','util/InputController'],function (ABSTRACT,SelectController,InputController) {

	return function(){
		function Form(){ 
			ABSTRACT.apply(this);
			this.menuContexto = null;
      this.modelJson = {};
      this.modelComponente = {};
      this.componentes = {};
      this.selectTela = {};
      this.inputTela = {};
      this.isRedirect = true;
      this.dado = null;


    };

    Form.prototype = new ABSTRACT();
    Form.prototype.constructor = Form;

    Form.prototype.divLoad = "#APP";


    Form.prototype.ajustLayout = function(){

      $('#tabAcoes').hide();
      $('#tabPesquisa').hide();
      $('#acoes').hide();
      $('#tabEdicao').removeClass('hidden');
      $('#tabEdicao').addClass('active');
      $('#edicao').addClass('active');
    }

    Form.prototype.loadData = function(){

      var id = this.dadosRota.id;

      if(id && id!='add' && this.dado==null){
        this.get(this.url+id,null,this.formData.bind(this),this.error,this.complete);    
      }else{
        this.formData({data:{},valid:true});
      }
    }

    Form.prototype.formData = function(data){

      if(data && data.valid && !this.dado){
        this.dado = data.data;
      }

      this.get(this.url+'form',null,this.termineteLoad.bind(this),this.error,this.complete);
    }

    Form.prototype.montaComponentes = function(){
      var self = this;
      this.selects = $('select',this.divLoad);

      $(this.selects).each(function( index ) {
        var nome = $(this).attr('name');
        self.selectTela[nome] = {element:$(this),class:null};
                // if(self.modelComponente[nome]){
                  var json = self.modelComponente[nome];
                  var selectController = SelectController($(this),json,self);
                  self.selectTela[nome].class = self.modelComponente[nome] = selectController;
                // }
              });

      this.inputs = $('input',this.divLoad);
      $(this.inputs).each(function( index ) {
        var nome = $(this).attr('name');
        self.inputTela[nome] = {element:$(this),class:null};
        var inputController = InputController($(this),null,self);
        self.inputTela[nome].class = inputController;
      });
    }


    Form.prototype.setValue = function(nome,data){
      var input = this.inputTela[nome];
      var classe = input.class;
      classe.setValue(data);
    }

    Form.prototype.updateDataSelect = function(nome,data){
      var select = this.selectTela[nome];
      var classe = select.class;
      classe.reloadData(data);
    }

    Form.prototype.termineteLoad = function(data){ 
      if(data && data.data){
        this.modelComponente = data.data;    
        this.modelJson = data.data.modelo;
      }else{
        this.modelComponente = {};
        this.modelJson = {};
      }

      this.ajustLayout();
      this.montaComponentes();
      this.monteSelectBind();
      this.dataBind();
      this.ajustacheckbox();
      this.updateSelect();
      this.termineteLoadForm(this.dado);

      $('[data-action="save"]', this.divLoad).unbind();
      $('[data-action="save"]', this.divLoad).click(this.save.bind(this));
      $('[data-action="cancelar"]', this.divLoad).unbind();
      $('[data-action="cancelar"]', this.divLoad).click(this.cancelar.bind(this));
      console.log("fafa",this.dado);
    }

    Form.prototype.ajustacheckbox = function() {
      this.checksbox = $("input[type='checkbox']",this.divLoad);

      if(this.checksbox.length > 0 && this.dado && !this.dado.id){

        for(var i =0 ; i<this.checksbox.length;i++){
          var itemcheck = this.checksbox[i];
          var name =  $(itemcheck).attr("name");
          var checked = $(itemcheck).attr("checked");

          if(name != undefined && checked){
            this.dado[name] = true;
          }
        }
      }                        

    };

    Form.prototype.cancelar = function() {
      $(this).trigger('destroy');
      if(this.isRedirect){
        window.location.href = '#/'+this.url.replace("/","");    
      }
    };

    Form.prototype.populateFormError = function(data) {
     if (data && data.responseJSON){
      data = data.responseJSON;
    }
    if(data && data.errors){
      var texto = "";
      $('.errorfield',this.divLoad).remove();
      $('.erroTab').remove();
      $erroTab = $('<span class="label label-danger erroTab pull-right">1</span>');
      for(var i in data.errors){
       var dado = data.errors[i];
       var $ctrl = $('[name=' + dado.field + ']', this.divLoad);
       var label = $('<label/>',{class:'text-danger errorfield',for:dado.field}).text(dado.message);
       var x = $ctrl.closest('.tab-pane');
       var tab = x.attr("id");

       if(tab){
        var tabbar = $('[href="#'+tab+'"]');
        if($('.erroTab',tabbar).length>0){
         var count = $('.erroTab',tabbar).text();
         $('.erroTab',tabbar).text(parseInt(count)+1);
       }else{
         var tbclonex = $erroTab.clone();
         tabbar.append(tbclonex);
       }
     }


     texto += dado.message + '\n';
     switch ($ctrl.attr("type")) {
      case "radio":
      case "checkbox":
      $ctrl.each(function() {
       if ($(this).attr('value') == value) {
        $(this).attr("checked", value);
      }
    });
      break;
      default:
      $ctrl.parent().append(label);
      break;
    }


  }
  var modal = app.getObject('ModalDialogConfirm');
  modal.openModal({titulo:"Erros foram encontrados ",texto:texto,tipo:"error",btnConfirma:"ok",functionConfirma:function(){}});
}

};

Form.prototype.success = function(data) {
 if (data.valid==true){
  this.cancelar();
}else{

  this.populateFormError(data);
}
};


Form.prototype.error = function(data) {
  this.closeLoad(this.divLoad);
  
  if (data.valid==true){

  }else{
    
    this.populateFormError(data);
  }
};




Form.prototype.processData =  function (el,parent,parentkey) {


	var self = this;


    // If the element is an array...
    if ($.isArray(el)) {
    	for (var i = 0; i < el.length; i++) {
    		var value = el[i];
    		self.processData(value,el,i);
    	}
    }
    // If the element is an object...
    else if ($.isPlainObject(el)) {
    	for (var key in el) {
            // Make sure the key is not part of the prototype chain
            if (el.hasOwnProperty(key)) {
            	var value = el[key];


                    // If the key has been seen, remove it
                    if (!value || value == undefined || value == null || value == "null") {
                    	var count2 = Object.keys(el).length;
                    	delete el[key];
                    	var count = Object.keys(el).length;
                    	
                    	if(count<=0 && parentkey){
                    		delete parent[parentkey];
                    	}
                        continue; // Skip further processing
                      } 
                    //vai comitar
                    var count = Object.keys(el[key]).length;

                    if((count<=0 && key) || (!value || value == undefined || value == null || value == "null")){

                    	if(typeof el[key] === 'object')
                    		delete el[key];
                    	else if (value == undefined || value == null || value == "null"){
                    		delete el[key];
                    	}
                    }


                    self.processData(value,el,key);
                  }
                }
              }
            }

            Form.prototype.monteSelectSave = function(dataForm){
              for(var i in this.selectTela){
                var campo  = i;
                var array = [];
                var multiple = $(this.selectTela[i].element).attr("multiple");
                var simple = $(this.selectTela[i].element).attr("data-type");
                
                if(multiple){
                  for(var w in dataForm[campo]){
                    var item = dataForm[campo][w];
                    if( typeof item  ==  "string"){
                       array.push({id:item});
                    }
                  }
                  dataForm[campo] = array;
                }else{
                    if(!simple){
                      dataForm[campo] = {id:dataForm[campo]};  
                    }
                }
              }
            }

            Form.prototype.monteSelectBind = function(){
              for(var i in this.selectTela){
                var campo  = i;
                var array = [];
                var multiple = $(this.selectTela[i].element).attr("multiple");
                if(multiple){
                  for(var w in this.dado[campo]){
                    var item = this.dado[campo][w];
                    array.push(item.id);
                  }
                  this.dado[campo] = array;
                }else{
                  if(this.dado && this.dado[campo]){
                    if(this.dado[campo].id){
                      this.dado[campo] = this.dado[campo].id;   
                    }else{
                      this.dado[campo] = this.dado[campo];   
                    }
                    
                  }
                }
              }
            }


            Form.prototype.updateSelect = function(){
              for(var i in this.selectTela){
                var itemx = this.selectTela[i];
                var classe = itemx.class;
                if(classe)
                  classe.refresh();
              }
            }

            Form.prototype.save = function() {

             var dataForm = {};

             $.extend(dataForm, this.dado);
             console.info(dataForm);
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


        Form.prototype.del = function(id,element){
         if(this.url){
          var modal = app.getObject('ModalDialogConfirm');
          modal.openModal({titulo:"Deseja realmente excluir?",texto:"",tipo:"error",btnConfirma:"Sim",btnCancela:"Não",functionCancela:this.cancelarDel.bind(this),functionConfirma:this.confirmaDel.bind(this, id, element)});
        }
      };

      Form.prototype.cancelarDel = function(id){
       var modal = app.getObject('ModalDialogConfirm');
       modal.showError("Exclusão Cancelada","O registro não foi removido");
     };

     Form.prototype.confirmaDel = function(id, element){
       this.delete(this.url+id,null,this.delSuccess.bind(this,element),this.error,this.complete);
     };

     Form.prototype.delSuccess = function(element,data){
       if(data.valid){
        $(element).remove();
        var modal = app.getObject('ModalDialogConfirm');
        modal.showSuccess("Exclusão Realizada com sucesso","O registro foi removido");
        this.loadTable();
      }else{
        this.showErrors(data);
      }
    }


    return new Form();

  }
});


