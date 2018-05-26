
	define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
    return function(data){
        var EventomembroEditDataController = function(data){
            //this.loadPage(this.loadData.bind(this));
            //this.loadData();
            this.openLoad();

            this.dado = data;

        }

        EventomembroEditDataController.prototype =  new AbstractForm();

        EventomembroEditDataController.prototype.constructor = EventomembroEditDataController;

        EventomembroEditDataController.prototype.divLoad =  "#corpoDoSistema";
        EventomembroEditDataController.prototype.url = "eventomembro/";
        EventomembroEditDataController.prototype.urlAction = "eventomembro/save";
        EventomembroEditDataController.prototype.urlPage = "eventomembro/eventomembroeditdata.jsp";

        EventomembroEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();
            var self = this;
            $('[data-action="remove"]',this.divLoad).click(function(){
                if(self.dado && self.dado.id){
                    self.del(self.dado.id,self.divLoad);
                }else{
                    $(self).trigger('destroy');
                }
                //$(self).trigger("mudaCampo");
            });
        }

        EventomembroEditDataController.prototype.getData = function() {

            var dataForm = {};

            $.extend(dataForm, this.dado);
            this.monteSelectSave(dataForm);
            this.processData(dataForm);

            return dataForm;
        };

        EventomembroEditDataController.prototype.del = function(id,element){
            if(this.url){
                var modal = app.getObject('ModalDialogConfirm');
                modal.openModal({titulo:"Deseja realmente excluir?",texto:"",tipo:"error",btnConfirma:"Sim",btnCancela:"Não",functionCancela:this.cancelarDel.bind(this),functionConfirma:this.confirmaDel.bind(this, id, element)});
            }
        };

        EventomembroEditDataController.prototype.cancelarDel = function(id){
            var modal = app.getObject('ModalDialogConfirm');
            modal.showError("Exclusão Cancelada","O registro não foi removido");
        };

        EventomembroEditDataController.prototype.confirmaDel = function(id, element){
            this.delete(this.url+id,null,this.delSuccess.bind(this,element),this.error,this.complete);
        };

        EventomembroEditDataController.prototype.delSuccess = function(element,data){
            if(data.valid){
                $(element).remove();
                var modal = app.getObject('ModalDialogConfirm');
                modal.showSuccess("Exclusão Realizada com sucesso","O registro foi removido");
                $(this).trigger('destroy');
            }else{
                this.showErrors(data);
            }
        }

        return new EventomembroEditDataController(data);
    }
});