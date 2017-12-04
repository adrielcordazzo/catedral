define(['util/ListController', 'util/arquivo/ArquivoUploadController', 'util/pasta/PastaFormWidGetController', 'util/arquivo/ArquivoListController'],function (ListController, ArquivoUpload, PastaFormWidGet, ArquivoList) {

	function PastaWidget(idPasta,functionPrincial,incorporar){
		this.modelLine = null; // modelo da linha da tabela
		this.folder = null;
		this.idPasta = idPasta;
		this.pagination.campos = [];
		this.pagination.values = [];
		this.pagination.campos.push("id");
		this.pagination.values.push(idPasta);
		this.functionPrincial = functionPrincial;
		this.incorporar = incorporar;

	};

	PastaWidget.prototype = new ListController();
	PastaWidget.prototype.constructor = PastaWidget;

	PastaWidget.prototype.urlPage = 'util/pasta/pastawidget.jsp';
	PastaWidget.prototype.urlDelete = 'pasta/delete/';
	PastaWidget.prototype.urlGet = "pasta/get/";
	PastaWidget.prototype.urlLoadList = "pasta/list";
	PastaWidget.prototype.divLoad = ".pastasController";

	PastaWidget.prototype.pagination = {maxResult:10,pagina:1};

	PastaWidget.prototype.tabsAction = function(){
		
		$('.addPasta',this.divLoad).click(this.editData.bind(this));

		//$('.btn-upload',this.divLoad).click(this.btnUpload.bind(this));

	}

	PastaWidget.prototype.btnUpload = function(data){
		var self = this;
		self.arquivoupload = new ArquivoUpload(self.folder);
		self.arquivoupload.divLoad = $('.arquivoUpload',self.divLoad);
		self.arquivoupload.loadPage(self.arquivoupload.formDidLoad.bind(self.arquivoupload));
		$(self.arquivoupload).on('destroy',self.loadPage.bind(self,self.viewDidLoad.bind(self)));
		
	}

	PastaWidget.prototype.editData = function(data){

		var self = this;
		self.pastaFomrWidget = new PastaFormWidGet(data);
		self.pastaFomrWidget.divLoad = $('.formpastawidge',self.divLoad);
		self.pastaFomrWidget.loadPage(self.pastaFomrWidget.viewDidLoad.bind(self.pastaFomrWidget));
		$(self.pastaFomrWidget).on('destroy',self.loadPage.bind(self));
		
	}


	PastaWidget.prototype.selectFolder = function(id,element){
		
		$('.folder-list .linha-selectText',this.divLoad).removeClass('linha-selectText');
		$('a',element).addClass('linha-selectText');
		this.folder = id;
		var self = this;
		self.arquivoList = new ArquivoList(self.folder,self.incorporar);
		self.arquivoList.divLoad = $('.arquivoList',self.divLoad);
		self.arquivoList.loadPage(self.arquivoList.viewDidLoad.bind(self.arquivoList));

		if(self.functionPrincial){
			self.arquivoList.setPrincipal = self.functionPrincial;
		}

		
	}



	PastaWidget.prototype.termineteLoadList = function(data){ 
		
		$('.folder-list',this.divLoad).html('');
		
		if(data.list){
			
			var list = data.list;
			for(var i in list){
				
				var user = list[i];
				if(this.idPasta)
					if(this.idPasta!=user.id)
						continue;
				
				var element = $(this.modelLine).clone();
				$('.m-pasta',element).html(user.pasta);
				this.folder = user.id;
				$(element).click(this.selectFolder.bind(this,user.id,element));
				

				//$('.folder-list',this.divLoad).append(element);
			}

			$(element).click();
			
			this.paginate(data.countResult);
		}
		this.btnUpload();
		
	};

	return PastaWidget;
});


