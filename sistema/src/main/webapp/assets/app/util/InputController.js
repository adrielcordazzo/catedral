define(['util/Abstract'],function (ABSTRACT) {

	return function(input,data,delegate){
		function InputController(input,data,delegate){ 
			ABSTRACT.apply(this);
            this.INPUT = input;
            this.DATA = data;
            this.DELEGATE = delegate;
            this.type = input.attr('data-type');
            this.mask = input.attr('data-mask');
            this.name = input.attr('name');

            if(!this.type){
                this.type = input.attr('type');
            }
            
            this.ajustInput();

        };

        InputController.prototype = new ABSTRACT();
        InputController.prototype.constructor = InputController;


        InputController.prototype.ajustInput = function(){
            var self = this;
            if(this.type=="valor"){
                $(this.INPUT).priceFormat({
                    prefix : "",
                    centsSeparator : ".",
                    thousandsSeparator : ""
                });

                $(this.INPUT).blur(function(){
                    self.DELEGATE.dado[self.name] = $(this).val();
                });
            }else if(this.type=="date"){
                $(this.INPUT).datepicker(
                {
                    dateFormat : "dd/mm/yy",
                    language : "pt-BR"
                });
                $(this.INPUT).mask("99/99/9999");
                $(this.INPUT).change(function(){
                    self.DELEGATE.dado[self.name] = $(this).val();
                });
            }else if(this.type=="mask"){

                $(this.INPUT).mask(this.mask);

            }else if(this.type=="checkbox"){

                
            }else if(this.type=="number"){
                if(this.DELEGATE.dado && !this.DELEGATE.dado[this.name]){
                    if($(this.INPUT).val()){
                        this.DELEGATE.dado[this.name] = $(this.INPUT).val();    
                    }
                }
                $(this.INPUT).keypress(function(event){

                    return  (event.charCode  == 0 || (event.charCode >= 48 && event.charCode <= 57));

                });

            }else{
                if(this.DELEGATE.dado && !this.DELEGATE.dado[this.name]){
                    this.DELEGATE.dado[this.name] = $(this.INPUT).val();
                }
            }
        }

        InputController.prototype.montaCheckBox = function(option){

        }

        InputController.prototype.montaRadioButton = function(option){

        }

        InputController.prototype.getValue = function(){
            return $(this.INPUT).val();
        }

        InputController.prototype.setValue = function(value){
            return $(this.INPUT).val(value);
        }

        return new InputController(input,data,delegate);

    }
});


