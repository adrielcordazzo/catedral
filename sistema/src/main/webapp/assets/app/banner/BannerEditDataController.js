
	define(["util/AbstractForm","router",'util/pasta/PastaWidgetController'] ,function (AbstractForm,router,PastaWidgetController) {
    return function(){
        var BannerEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        BannerEditDataController.prototype =  new AbstractForm();

        BannerEditDataController.prototype.constructor = BannerEditDataController;

        BannerEditDataController.prototype.divLoad =  "#corpoDoSistema";
        BannerEditDataController.prototype.url = "banner/";
        BannerEditDataController.prototype.urlAction = "banner/save";
        BannerEditDataController.prototype.urlPage = "banner/banneredit.jsp";

        BannerEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
            this.activeFotos();
            $("[data-action='saveedit']",this.divLoad).click(this.save.bind(this,true));
        }

        BannerEditDataController.prototype.activeFotos = function() {
            if (this.dado && this.dado.id && this.dado.pasta) {
                var self = this;
                
                self.pastaWidget = new PastaWidgetController(self.dado.pasta.id,null,true);
                this.pastaWidget.loadPage(self.pastaWidget.viewDidLoad.bind(self.pastaWidget));
                
            } 
        }

        BannerEditDataController.prototype.save = function(edit) {

             var dataForm = {};

             $.extend(dataForm, this.dado);
             console.info(dataForm);
             this.monteSelectSave(dataForm);
             this.processData(dataForm);

            if(this.urlAction!=null){
                var success = this.success.bind(this);
                if(edit){
                    success = this.successedit.bind(this)
                }
                this.openLoad(this.divLoad);
                if(!dataForm.id)
                    this.post(this.urlAction,dataForm,success,this.error.bind(this),null);
                else
                    this.put(this.urlAction,dataForm,success,this.error.bind(this),null);
            }else{
                console.error("not define urlAction");
            }
        };

        BannerEditDataController.prototype.successedit = function(data) {
            this.dado = data.data;
            this.activeFotos();
            this.closeLoad(this.divLoad);
        }

        return new BannerEditDataController();
    }
});