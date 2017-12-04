define(['util/Abstract','router'] ,function (ABSTRACT,router) {
	return function(){
		var ModalDialogConfirm = function(){
			
		}

		ModalDialogConfirm.prototype =  new ABSTRACT();

		ModalDialogConfirm.prototype.constructor = ModalDialogConfirm;

		ModalDialogConfirm.prototype.openModal = function(json){
		/*
		TIPOS
		info, error, warning, success
		*/
		var showCancel = false;
		if(json.btnCancela != null){
			showCancel = true;
		}
		swal({
			title: json.titulo,
			text: json.texto,
			type: json.tipo,
			showCancelButton: showCancel,
			confirmButtonClass: "btn-primary",
			confirmButtonText: json.btnConfirma,
			cancelButtonText: json.btnCancela,
			closeOnConfirm: true,
			closeOnCancel: true
		},
		function(isConfirm) {
			if (isConfirm) {
				json.functionConfirma();
			} else {
				json.functionCancela();
			}
		});     
	};

	ModalDialogConfirm.prototype.showSuccess = function(titulo,texto){
		swal(titulo, texto, "success");
	};

	ModalDialogConfirm.prototype.showError = function(titulo,texto){
		swal(titulo, texto, "error");
	};

	ModalDialogConfirm.prototype.showInfo = function(titulo,texto){
		swal(titulo, texto, "info");
	};

	

	return new ModalDialogConfirm();
}
});