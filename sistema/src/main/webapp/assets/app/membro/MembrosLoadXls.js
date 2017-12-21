define(['util/AbstractForm',"router"],function (AbstractForm,router) {
    
	function MembrosLoadXls(){
		this.loadPage(this.loadData.bind(this));
		

	};

	MembrosLoadXls.prototype = new AbstractForm();
	MembrosLoadXls.prototype.constructor = MembrosLoadXls;
//	MembrosLoadXls.prototype.urlPage = 'membro/membrosxls.jsp';
//	MembrosLoadXls.prototype.url = 'membro/';
	MembrosLoadXls.prototype.urlAction = 'membro/upload';
	MembrosLoadXls.prototype.divLoad = '.formMembros';

		MembrosLoadXls.prototype.urlPage = "membro/membrosxls.jsp";
		MembrosLoadXls.prototype.urlLoadList = "membro/list";
		MembrosLoadXls.prototype.url = "membro/";
		MembrosLoadXls.prototype.urlDelete = "membro/";
		MembrosLoadXls.prototype.urlDeleteAll = "membro/deleteAll/";


	MembrosLoadXls.prototype.termineteLoadForm = function(){

		
	}

	MembrosLoadXls.prototype.save = function() {

		this.openLoad(this.divLoad);
		var dataForm = this.serializeForm();

		var formData = new FormData();

		var file = $('#file')[0].files[0];

		formData.append('file',file);
		var self = this;
		if(this.urlAction!=null){
			// this.post(this.urlAction,formData,this.success.bind(this),this.error.bind(this),Metronic.unblockUI.bind(this,this.form));
			var request = new XMLHttpRequest();
			request.open("POST", this.urlAction);
			request.onload = function(oEvent) {
    			if (request.status == 200) {
      				self.success(request.responseText);
    			} else {
      				this.error();
    			}
			};

			request.send(formData);
		}else{
			console.error('not define urlAction');
		}
	};

	MembrosLoadXls.prototype.success = function(data) {
		data = JSON.parse(data);
		if (data.valid==true){
			$(this).trigger('destroy',data);
		}else{
			$(this).trigger('destroy');
			var texto = "";
			for(var i in data.errors){
				var dado = data.errors[i];
				texto += dado.message + '\n';
			}

			var modal = app.getObject('ModalDialogConfirm');
			modal.openModal({titulo:"Erros foram encontrados ",texto:texto,tipo:"error",btnConfirma:"ok",functionConfirma:function(){}});
			
		}
	};

	MembrosLoadXls.prototype.error = function(data) {
	};

	MembrosLoadXls.prototype.complete = function() {

	};

	return MembrosLoadXls;
});