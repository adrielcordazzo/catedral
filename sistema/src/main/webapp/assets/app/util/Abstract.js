dependencias = [];
define(['router'] ,function (router) {
	return function(){
		var Abstract = function(){
			this.config = {};
			this.contentHtml = null;
			this.dado = {};
			this.data = {};
			this.vue = null;
			this.requests = {};
			this.delegateExec = null;
			this.dadosRota = router.routeArguments();
		};

		Abstract.prototype.divLoad = "body";

		Abstract.prototype.loadPage = function (func){

			
			
			if(!func){
					func = function(){};
			}

			if(this.contentHtml){
				$(this.divLoad).html(this.contentHtml);
				func();
			}else{
				if(this.urlPage){
					$(this.divLoad).load("assets/app/"+this.urlPage,func);
				}else{
					func();
				}
			}
		};

		Abstract.prototype.dataBind = function (){
			var self = this;			
			rivets.bind($(this.divLoad), this.dado);
		};

		

		Abstract.prototype.delegateExec = function (param,json){
			$(this).trigger(param,json);
		};

		Abstract.prototype.ajax = function(url,type,data,success,error,complete){
			var self = this;
			
			function tratadado(){
				if(data!=null)
					console.info(data);
					return  JSON.stringify(data);
				return null;
			}
			var ajax = $.ajax({
				url: url,
				type: type,
				contentType: "application/json",
				data: tratadado(),
				success: success,
				error: error,
				complete: this.completeRequest.bind(this,complete,url,tratadado())
			});

			this.controlRequest(url,ajax);
		};

		Abstract.prototype.completeRequest = function(func,urlJson,dado,request) {
			
			if(this.requests[urlJson])
				delete this.requests[urlJson];
			var notification = app.getObject('NotificationCenter');
			switch(request.status) {
				case 302:
				notification.addNotification('Erro 302 ','error',true);
				window.location.href = "entrar";
				break;	
				case 401:
				notification.addNotification('Acesso não autorizado','error',true);
				setTimeout(function(){
					window.location.href = "entrar";	
				},1000);
				break;
				case 500:
					notification.addNotification('500 Erro inesperado no servidor tente novamente mais tarde ','error',true);
				break;
				case 400:
					notification.addNotification('400 Verifique os dados do formulário ','warning',true);
				break;
				case 404:
				notification.addNotification('404 Erro a requisão feita para endereço inesistente  ','warning',true);
				break;
				case 200:
				break;
			}

			this.messagesNotice(request);

			if(func)
				func();
		};

		Abstract.prototype.messagesNotice = function(data) {
			var notification = app.getObject('NotificationCenter');
			dado = data.responseJSON;
			if(dado && dado.messages){
				for(var b in dado.messages ){
					var severity = dado.messages[b].severity;
					var message = dado.messages[b].text;
					notification.addNotification(message,severity,true);
				}
			}
		};

		Abstract.prototype.controlRequest = function(url,ajax){

			if(this.requests[url]){
				var xrq = this.requests[url];
				xrq.abort();
			}
			this.requests[url] = ajax;
		}

		Abstract.prototype.post = function(url,data,success,error,complete) {
			this.ajax(url,"post", data,success,error,complete);
		};

		Abstract.prototype.put = function(url,data,success,error,complete) {
			this.ajax(url,"put", data,success,error,complete);
		};

		Abstract.prototype.delete = function(url,data,success,error,complete) {
			this.ajax(url,"delete", data,success,error,complete);
		};

		Abstract.prototype.get = function(url,data,success,error,complete) {
			this.ajax(url,"get", data,success,error,complete);
		};


		Abstract.prototype.postFormData = function(url,data,success,error,complete){
			var self = this;

			$.ajax({
				method: "POST",
				url: url,
				data: data,
				cache: false,
				processData: false,
				contentType: false,
				type: 'POST',
				success: success,
				error: error,
				complete: complete
			});
			this.controlRequest(url,ajax);
		};
		Abstract.prototype.openLoad = function(target){


			options = {
				target: this.divLoad,
				boxed: true,
				message: "Processando..."
			};

			if(!target){
				delete options.target;
			}

			options = $.extend(true, {}, options);

			var html = '';
			if (options.animate) {
				html = '<div class="loading-message ' + (options.boxed ? 'loading-message-boxed' : '') + '">' + '<div class="block-spinner-bar"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div>' + '</div>';
			} else if (options.iconOnly) {
				html = '<div class="loading-message ' + (options.boxed ? 'loading-message-boxed' : '') + '"><i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i></div>';
			} else if (options.textOnly) {
				html = '<div class="loading-message ' + (options.boxed ? 'loading-message-boxed' : '') + '"><span>&nbsp;&nbsp;' + (options.message ? options.message : 'LOADING...') + '</span></div>';
			} else {
				html = '<div class="loading-message ' + (options.boxed ? 'loading-message-boxed' : '') + '"><i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i><span>&nbsp;&nbsp;' + (options.message ? options.message : 'LOADING...') + '</span></div>';
			}

        if (options.target) { // element blocking
        	var el = $(options.target);
        	if (el.height() <= ($(window).height())) {
        		options.cenrerY = true;
        	}
        	el.block({
        		message: html,
        		baseZ: options.zIndex ? options.zIndex : 1020,
        		centerY: options.cenrerY !== undefined ? options.cenrerY : false,
        		css: {
        			top: '10%',
        			border: '0',
        			padding: '0',
        			backgroundColor: 'none'
        		},
        		overlayCSS: {
        			backgroundColor: options.overlayColor ? options.overlayColor : '#555',
        			opacity: options.boxed ? 0.05 : 0.5,
        			cursor: 'wait'
        		}
        	});
        } else { // page blocking
        	$.blockUI({
        		message: html,
        		baseZ: options.zIndex ? options.zIndex : 99999000,
        		css: {
        			border: '0',
        			padding: '0',
        			backgroundColor: 'none'
        		},
        		overlayCSS: {
        			backgroundColor: options.overlayColor ? options.overlayColor : '#555',
        			opacity: options.boxed ? 0.05 : 0.5,
        			cursor: 'wait'
        		}
        	});
        }
        
    };

    Abstract.prototype.closeLoad = function(target){
    	if (target) {
    		$(target).unblock({
    			onUnblock: function() {
    				$(target).css('position', '');
    				$(target).css('zoom', '');
    			}
    		});
    	} else {
    		$.unblockUI();
    	}
    };	

    return  new Abstract();
}
});