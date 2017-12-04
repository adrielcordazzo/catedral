define(['util/AbstractForm'],function (AbstractForm) {
    
	function PastaFormWidGet(data){
		AbstractForm.apply(this);
		this.dado = data;

	};

	PastaFormWidGet.prototype = new AbstractForm();
	PastaFormWidGet.prototype.constructor = PastaFormWidGet;

	PastaFormWidGet.prototype.urlPage = 'util/pasta/pastaformwidget.jsp';

	PastaFormWidGet.prototype.urlAction = 'pasta/save';
	
	PastaFormWidGet.prototype.divLoad = '.formpastawidget';


	PastaFormWidGet.prototype.formDidLoad = function(){
		
	}

	PastaFormWidGet.prototype.save = function() {

		var dataForm = this.serializeForm();
		
		if(this.dado.data && this.dado.data.id)
			dataForm.id = this.dado.data.id;
		





		if(this.urlAction!=null){
			this.post(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),this.complete.bind(this));
		}else{
			console.error('not define urlAction');
		}
	};

	PastaFormWidGet.prototype.success = function(data) {
		if (data.valid==true){
			
			$(this).trigger('destroy');
		}else{
			this.populateFormError(data);
		}
	};

	PastaFormWidGet.prototype.error = function(data) {
	};

	PastaFormWidGet.prototype.complete = function() {

	};

	return PastaFormWidGet;
});


