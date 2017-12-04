
define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(){
        var ConfiguracaocampoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ConfiguracaocampoEditDataController.prototype =  new AbstractForm();

        ConfiguracaocampoEditDataController.prototype.constructor = ConfiguracaocampoEditDataController;

        ConfiguracaocampoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ConfiguracaocampoEditDataController.prototype.url = "configuracaocampo/";
        ConfiguracaocampoEditDataController.prototype.urlAction = "config/save";
        ConfiguracaocampoEditDataController.prototype.urlPage = "configuracaocampo/configuracaocampoedit.jsp";

        ConfiguracaocampoEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
            var self = this;

            var campos = [];
            if(this.modelComponente["campos"]){
                campos = this.modelComponente["campos"];
            }
            var dados = [];

            if(this.dado){
                for(var i in this.dado){
                    var item = this.dado[i];
                    if(item && item.campo){
                        dados.push(item);
                    }
                    
                }
            }

            console.error(dados);

            require(["util/groupcampo/GroupCampoController"],function(GroupForm){
                var groupForm = new GroupForm(campos,dados,"campo","tipocampo");
                self.campos = groupForm;
                groupForm.divLoad = '#outros';
                groupForm.loadPage(groupForm.viewDidLoad.bind(groupForm));
                $(groupForm).on("destroy",self.loadPage.bind(self));
            });

        }


        ConfiguracaocampoEditDataController.prototype.save = function() {

         var dataForm = [];
         var json = this.campos.getData();
         if(json){
            dataForm = json;
        }
        this.processData(dataForm);

        if(this.urlAction!=null){
          this.openLoad(this.divLoad);
          if(!dataForm.id){
           this.post(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null); 
       }else{
           this.put(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null); 
       }

   }else{
    console.error("not define urlAction");
}
};

ConfiguracaocampoEditDataController.prototype.success = function(data) {
   if (data.valid==true){
    this.dado = data.data;
    this.loadPage(this.loadData.bind(this));
}else{

  this.populateFormError(data);
}
};



return new ConfiguracaocampoEditDataController();
}
});