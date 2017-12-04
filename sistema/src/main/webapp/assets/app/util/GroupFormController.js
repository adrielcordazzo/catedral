define(['util/Abstract'],function (Abstract) {

	function GroupForm(data){

		Abstract.apply(this);
		this.dado = data;
		this.forms = [];
		
	};

	GroupForm.prototype = new Abstract();
	GroupForm.prototype.constructor = GroupForm;

	GroupForm.prototype.urlPage = 'util/GroupForm.jsp';

	GroupForm.prototype.divLoad = '.group';

	GroupForm.prototype.caminhoForm = "caminhoForm";
	GroupForm.prototype.nameButton = "new button";
	GroupForm.prototype.title = "titulo";
	GroupForm.prototype.btnVisible = true;

	GroupForm.prototype.hideButtonAdd = function(data){
		$('.btn',this.divLoad).hide();
	}

	GroupForm.prototype.editData = function(data){
		if(data && !data.id){
			data = {};
		}
		var self = this;
		var classe = "v"+Math.floor(Math.random()*1000000000);
		var model = this.$modelo.clone();
		$('.linha',model).addClass(classe);
		$('fieldset',this.divLoad).prepend(model);
		require([this.caminhoForm],function(Fomr){
			var form = new Fomr(data);
			form.divLoad = '.'+classe;
			form.loadPage(form.loadData.bind(form));
			self.forms.push(form);
			$(form).on("destroy",self.removeItem.bind(self,form));
		});
	}

	GroupForm.prototype.removeItem = function(formv){
		var index = this.forms.indexOf(formv);
		this.forms.splice(index,1);
		$(formv.divLoad).remove();
	}


	GroupForm.prototype.getData = function(){

		var arrayReturn = [];
		for (var i in this.forms){

			var f = this.forms[i];
			var dado = f.getData();
			arrayReturn.push(dado);

		}

		return arrayReturn;
	}


	GroupForm.prototype.viewDidLoad = function(){
		
		$('.btn',this.divLoad).click(this.editData.bind(this));
		$('.btn',this.divLoad).text(this.nameButton);

		if(this.btnVisible == false){
			this.hideButtonAdd();
		}

		$('fieldset span',this.divLoad).text(this.title);

		this.$modelo = $('.model',this.divLoad).clone();
		$('.model',this.divLoad).remove();


		if(this.dado){

			for(var i in this.dado){
				var item = this.dado[i];
				this.editData(item);
			}
		}

	}


	GroupForm.prototype.success = function(data) {
		if (data.valid==true){
			$(this).trigger('destroy');
		}else{
			this.populateFormError(data);
		}
	};

	GroupForm.prototype.error = function(data) {
	};

	GroupForm.prototype.complete = function() {

	};

	return GroupForm;
});



