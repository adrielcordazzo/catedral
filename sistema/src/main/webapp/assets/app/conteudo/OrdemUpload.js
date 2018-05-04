define(['util/AbstractForm'],function (AbstractForm) {
	
	function OrdemUpload(){
		this.dado = {};
		// AbstractForm.apply(this);

		//this.loadPage(this.loadData.bind(this));
         // this.openLoad();
		

	};

	OrdemUpload.prototype = new AbstractForm();
	OrdemUpload.prototype.constructor = OrdemUpload;

	OrdemUpload.prototype.urlPage = 'conteudo/uploadxls.jsp';
	OrdemUpload.prototype.divLoad = "#corpoDoSistema";

	OrdemUpload.prototype.urlAction = 'conteudo/uploadfoto';
	OrdemUpload.prototype.url = 'conteudo/';
	
	


	OrdemUpload.prototype.termineteLoadForm = function(){
		


		
	}

	OrdemUpload.prototype.save = function() {

		this.openLoad(this.divLoad);
		var dataForm = this.serializeForm();

		var formData = new FormData();

		var file = $('#file',this.divLoad)[0].files[0];

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

	OrdemUpload.prototype.success = function(data) {
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

	OrdemUpload.prototype.error = function(data) {
	};

	OrdemUpload.prototype.complete = function() {

	};

	return OrdemUpload;
});