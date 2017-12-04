define(['util/Abstract','router','assets/js/jquery.cosyAlert.min.js'] ,function (ABSTRACT,router,cosyAlert) {
	return function(){
		var NotificationCenter = function(){
			this.notificationCenter = $('#notificationCenter');
			$('.close',this.notificationCenter).click(this.hide.bind(this));
			this.timeOut = null;
			this.messageAnterior =  null;
		}

		NotificationCenter.prototype =  new ABSTRACT();

		NotificationCenter.prototype.constructor = NotificationCenter;

		NotificationCenter.prototype.viewDidLoad = function(){

		};

		NotificationCenter.prototype.cosyAlert = function(message, type, options, callback){

			console.info(toastr);

			toastr[type](message, "");
			console.log('message ',message);

		// // Check if callback function is supplied
		// if (!callback){
		// 	// Check if type is a function
		// 	if (typeof(type) == 'function'){
		// 		// Set to callback
		// 		callback = type;
		// 	}
		// 	// Check if options is a function
		// 	else if (typeof(options) == 'function'){
		// 		// Set to callback
		// 		callback = options;
		// 	}
		// }
		
		// // Check if options is supplied
		// if (!options){
		// 	// Check if type is an object
		// 	if (typeof(type) == 'object'){
		// 		// Set to options
		// 		options = type;
		// 	}
		// }
		
		// // Check if type is supplied, and is a string
		// if (!type || typeof(type) != 'string'){
		// 	// Set default type
		// 	type = $.cosyAlert.configuration.defaultType;
		// }
		
		// // Add an alert
		// elmAlert = $.cosyAlert.add(message, type, options, callback);
		
		// // Show the alert
		// $.cosyAlert.show(elmAlert);
		
		// // Return the alert element
		// return elmAlert;
	};

	NotificationCenter.prototype.veriNotificationCenter = function(){ };

	NotificationCenter.prototype.callback = function(){
		this.messageAnterior = null;
	}

	NotificationCenter.prototype.hide = function(){}

	NotificationCenter.prototype.addNotification = function(text,type,time){
		
		//if(text!=this.messageAnterior){
			this.cosyAlert(text, type.toLowerCase(),this.configurationAlert,this.callback);
			this.messageAnterior = text;
		//}
	}

	return new NotificationCenter();
}
});