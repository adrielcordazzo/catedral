define(['util/Abstract','router'] ,function (ABSTRACT,router) {
	return function(){
		var ModalController = function(){
			this.modalDialog = $('#ajaxModalController');
			$('.close',this.modalDialog).click(this.hide.bind(this));
			this.timeOut = null;
			this.hide();
			$('.ajaxModalControllerBody').html('');
			this.arrayEvent=[];
			this.idElement = null;

		}

		ModalController.prototype =  new ABSTRACT();

		ModalController.prototype.constructor = ModalController;

		ModalController.prototype.show = function(){
			$(this.modalDialog).modal('show')
		}

		ModalController.prototype.hide = function(){
			$(this.modalDialog).modal('hide');
		}

		ModalController.prototype.ok = function(event,data){
			for(var i in this.arrayEvent){
				var events = this.arrayEvent[i];
				events.unbind();
			}
			this.arrayEvent =[];
			this.hide();
		}

		ModalController.prototype.newInstance = function(uri,data,idElement,functionLoad){
			
			var nameModalController = "modal"+Math.floor((Math.random() * 1000) + 1);
			var divModalController = $("<div/>").prop("id",nameModalController);
			$(divModalController).addClass('modal-lg');
			$("body").append(divModalController);
			var self = this;
			$(divModalController).load("assets/app/util/modal.jsp",function(){self.completeLoadModalController(nameModalController,uri,data,idElement,functionLoad)});	
		}

		ModalController.prototype.completeLoadModalController = function(div,uri,data,idElement,functionLoad){
			$(".modal","#"+div).modal();
			this.idElement = idElement;
			var self = this;
			require([uri],function(loadClass){
				var load = new loadClass(data);
				load.divLoad = $(".modal-body","#"+div);
				load.loadPage(load.loadData.bind(load));
				load.isRedirect = false;
				if ( self.idElement && self.idElement.length > 2){
					var item = $(self.idElement,"#"+div);
					$(load.divLoad).html(item);
					// $(load.divLoad).append(item);
				}

				$(load).on("destroy",function(events,data){
					$("#"+div).remove();
					$("body").removeClass("modal-open");
					if(functionLoad)
						functionLoad(events,data);
				});

				$(".modal","#"+div).on("hidden.bs.modal",function(){
					$("#"+div).remove();
					$("body").removeClass("modal-open");
				});
			});
		}

		ModalController.prototype.cancel = function(){
			this.hide();
			$(this).trigger('cancel');
		}

		ModalController.prototype.showModalController = function(title,text,buttonOk, buttonCancel){
			$('.modal-body',this.modalDialog).text(text);
			$('.modal-title',this.modalDialog).text(title);
			this.show();
		}

		return new ModalController();
	}
	
});