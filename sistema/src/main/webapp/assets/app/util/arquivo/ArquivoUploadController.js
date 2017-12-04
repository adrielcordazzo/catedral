define(['util/Abstract','assets/js/dropzone.min.js'],function (Abstract,dropzones) {
    
	function ArquivoUpload(data){
		Abstract.apply(this);
		this.folder = data;
		this.dado = {};
	};

	ArquivoUpload.prototype = new Abstract();
	ArquivoUpload.prototype.constructor = ArquivoUpload;

	ArquivoUpload.prototype.urlPage = 'util/arquivo/arquivoupload.jsp';

	ArquivoUpload.prototype.urlAction = 'arquivo/save';
	
	ArquivoUpload.prototype.divLoad = '.arquivoUpload';


	ArquivoUpload.prototype.formDidLoad = function(){
		this.form = $('#formUploadFile');
		var self = this;
		Dropzone.autoDiscover = false;
		var rand = 'v'+Math.floor(Math.random()*1000000000);
		$('.btn-upload',this.divLoad).addClass(rand);
		//console.log("RAND",'.'+rand);
		$(".dropzone",this.divLoad).dropzone({clickable:'.'+rand, url: "arquivos/uploads/"+this.folder ,queuecomplete:function(){
			$(self).trigger('destroy');
			$(self.divLoad).remove();
		}});

		$('.dz-message').remove();
	}

	ArquivoUpload.prototype.save = function() {

		var dataForm = this.serializeForm();
		
		if(this.dado.data && this.dado.data.id)
			dataForm.id = this.dado.data.id;

		if(this.urlAction!=null){
			this.post(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),this.complete.bind(this));
		}else{
			console.error('not define urlAction');
		}
	};

	ArquivoUpload.prototype.success = function(data) {
		if (data.valid==true){
			$(this).trigger('destroy');
		}else{
			this.populateFormError(data);
		}
	};

	ArquivoUpload.prototype.error = function(data) {
	};

	ArquivoUpload.prototype.complete = function() {

	};

	return ArquivoUpload;
});


