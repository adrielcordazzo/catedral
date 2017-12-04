define(['util/ListController'],function (ListController) {

	function ArquivoList(folder){
		this.modelLine = null; // modelo da linha da tabela
		this.folder = folder;
		this.urlLoadList = this.urlLoadList+folder;


	};

	ArquivoList.prototype = new ListController();
	ArquivoList.prototype.constructor = ArquivoList;

	ArquivoList.prototype.urlPage = 'util/arquivo/arquivolist.jsp';
	ArquivoList.prototype.urlDelete = 'arquivo/';
	ArquivoList.prototype.urlGet = "arquivo/";
	ArquivoList.prototype.urlLoadList = "arquivo/listAll/";
	ArquivoList.prototype.divLoad = ".arquivoList";
	ArquivoList.prototype.princial = "arquivo/setPrincipal/";

	

	ArquivoList.prototype.editData = function(data){

		// var self = this;
		// require(['role/RoleEditDataController'],function(RoleEditData){
		// 	var roleEditData = new RoleEditData(data);
		// 	roleEditData.loadPage();
		// 	$(roleEditData).on('destroy',self.loadPage.bind(self));
		// });
	}


	ArquivoList.prototype.setPrincipal = function(id,element){
		this.get(this.princial+id,null,this.fimPrincipal.bind(this),null);
	}

	ArquivoList.prototype.fimPrincipal = function(dados){
		this.loadPage();
	}

	ArquivoList.prototype.download = function(id){
		window.open('arquivo/getFile/'+id,'_blank');
	}

	ArquivoList.prototype.del = function(id,element){
		if(this.urlDelete){
			var modal = app.getObject('ModalDialogConfirm');
			modal.openModal({titulo:"Deseja realmente excluir?",texto:"",tipo:"error",btnConfirma:"Sim",btnCancela:"Não",functionCancela:this.cancelarDel.bind(this),functionConfirma:this.confirmaDel.bind(this, id, element)});
		}
	};

	ArquivoList.prototype.confirmaDel = function(id, element){
		this.delete(this.urlDelete+id,null,this.delSuccess.bind(this,element),this.error,this.complete);
	};

	ArquivoList.prototype.termineteLoadList = function(data){
		
		$('.filemanager',this.divLoad).html('');
		
		if(data.list){
			var list = data.list;
			var count = 0;
			for(var i in list){
				var element = $(this.modelLine).clone();
				var user = list[i];
				user.extension = user.extensao;
        		$('.m-title',element).text(user.nome);
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
			
        		$('.btn-danger',element).click(this.del.bind(this,user.id,element));

				$('.filemanager',this.divLoad).append(element);
			}
			
			this.paginate(data.countResult);
		}
		
		// this.actionFunctions();
    $("a[rel^='prettyPhoto']").prettyPhoto();
	};

	return ArquivoList;
});


