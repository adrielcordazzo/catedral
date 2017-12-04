
define(["util/ListController","router",'assets/js/dropzone.min.js'] ,function (ListController,router,dropzones) {
	return function(pasta,btnIncorporar){
		var AnexoListController = function(pasta,btnIncorporar){

			this.pasta = pasta;
			if(pasta.hasOwnProperty("id")){
				this.pasta = pasta.id;
			}

			this.pagination.campos = ["pasta.id"];
			this.pagination.values = [this.pasta];
			this.btnIncorporar = btnIncorporar;
		}

		AnexoListController.prototype =  new ListController();

		AnexoListController.prototype.constructor = AnexoListController;

		AnexoListController.prototype.divLoad =  "#anexocontroller";
		AnexoListController.prototype.urlPage = "util/anexo/anexolist.jsp";
		AnexoListController.prototype.url = "anexo/";
		AnexoListController.prototype.urlLoadList = "arquivo/listAll";
		AnexoListController.prototype.urlDelete = "arquivo/";
		AnexoListController.prototype.urlDeleteAll = "arquivo/deleteAll/";
		AnexoListController.prototype.princial = "arquivo/setPrincipal/";

		AnexoListController.prototype.download = function(id){
			window.open('arquivo/getFile/'+id,'_blank');
		}

		AnexoListController.prototype.del = function(id,element){
			if(this.urlDelete){
				var modal = app.getObject('ModalDialogConfirm');
				modal.openModal({titulo:"Deseja realmente excluir?",texto:"",tipo:"error",btnConfirma:"Sim",btnCancela:"Não",functionCancela:this.cancelarDel.bind(this),functionConfirma:this.confirmaDel.bind(this, id, element)});
			}
		};

		AnexoListController.prototype.confirmaDel = function(id, element){
			this.delete(this.urlDelete+id,null,this.delSuccess.bind(this,element),this.error,this.complete);
		};

		AnexoListController.prototype.setPrincipal = function(id,element){
			this.get(this.princial+id,null,this.fimPrincipal.bind(this),null);
		}

		AnexoListController.prototype.fimPrincipal = function(dados){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		AnexoListController.prototype.incorporar = function(id, element) {
			var incorp = '<img src="http://www.xlabi.com.br:8080/imogle/api/arquivo/getFile/' + id + '" />';
			//var incorp = '<img src="http://localhost:8080/api/arquivo/getFile/' + id + '" />';

			tinymce.activeEditor.execCommand('mceInsertContent', false,incorp);

		}

		AnexoListController.prototype.editar = function(dados){
			var self = this;
			var modal = app.getObject('Modal');
            modal.newInstance("util/anexo/AnexoEditDataController",dados,'#formulario',function(){
            	self.loadPage(self.viewDidLoad.bind(self));
            	self.loadPage(self.viewDidLoad.bind(self));
            });
		}

		AnexoListController.prototype.termineteLoadList = function(data){
			var self = this;
			$('.btn-upload',this.divLoad).unbind();
			$(".dropzone",this.divLoad).unbind();

			$(".dropzone",this.divLoad).dropzone({clickable:'.btn-upload', url: "arquivos/uploads/"+this.pasta ,queuecomplete:function(){
				self.loadPage(self.viewDidLoad.bind(self));
			}});
			$('.dz-message').remove();

			$('.filemanager',this.divLoad).html('');

			if(data.list){
				var list = data.list;
				console.info("info",data.list);
				var count = 0;
				for(var i in list){
					var element = $(this.modelLine).clone();
					var user = list[i];
					user.extension = user.extensao;
					$('.m-title',element).text(user.nome);

					if(user.principal=="s"){
						$('img',element).css("border","solid 1px red");
					}

					$(element).attr("id",user.id);
					$(element).attr("count",count);
					
					$('p',element).text(user.legenda);

					if(user.extension == 'png' || user.extension == 'jpg' || user.extension == 'gif'){
						$('img',element).attr('src','arquivo/getFile/'+user.id);	
						$('.prettyPhoto',element).attr('href','arquivo/getFile/'+user.id);
					}else{
						$parent = $('img',element).parent().parent();
						$('img',element).parent().remove();
						var icone = "fa fa-download";

						if(user.extension == 'pdf'){
							icone = "fa fa-file-pdf-o";
						}
						if(user.extension == 'mp3' || user.extension == 'wav' || user.extension == 'wma' || user.extension == 'm4a'){
							icone = "fa fa-audio-o";
						}
						if(user.extension == 'mp4' || user.extension == 'avi' || user.extension == 'wmv'){
							icone = "fa fa-movie-o";
						}
						$a = $('<a/>').html('<i class="' + icone + '" style="font-size: 80px; line-height: 80px;"></i><br>'+user.nome);

						$a.click(this.download.bind(this,user.id));
						$parent.append($a);
					}

					if(count % 4 == 0 && count != 0){
						$('.filemanager',this.divLoad).append('<div class="col-xs-12 pt20"></div>');
					}

					count++;

					if(user.principal == "s"){
						$('.btn-default',element).addClass('btn-primary');
						$('.btn-default',element).removeClass('btn-default');
					}

					$('.principal',element).click(this.setPrincipal.bind(this,user.id,element));
					$('.incorporar',element).click(this.incorporar.bind(this,user.id,element));

					$('.excluir',element).click(this.del.bind(this,user.id,element));
					$('.editar',element).click(this.editar.bind(this,user,element));

					if(!this.btnIncorporar){
						$('.incorporar',element).remove();
					}

					$('.filemanager',this.divLoad).append(element);
				}
			}


			$( "#sortable" ,this.divLoad).sortable({
				// placeholder: "ui-state-highlight",
				// handle: ".model",
				over: function(event, ui){
					event.stopPropagation();
				},
				update: function(event, ui) {
					var i = 1;
					var arquivos = [];
					$('#sortable .model',this.divLoad).each(function() {
						var id = $(this).attr("id");
						arquivos.push({id:id,posicao:i});
						i = i+1;
					}); 
					
					self.post('/arquivo/ordenar',{ordem:arquivos},null,null);
				}
			});
    		$( "#sortable", this.divLoad).disableSelection();
    		

		}

		return new AnexoListController(pasta,btnIncorporar);
	}
});