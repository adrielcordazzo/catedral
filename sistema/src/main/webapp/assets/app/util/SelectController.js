define(['util/Abstract'],function (ABSTRACT) {

    return function(select,data,delegate){
        function SelectController(select,data,delegate){ 
            ABSTRACT.apply(this);
            this.SELECT = select;
            this.DATA = data;
            this.DELEGATE = delegate;

            this.URL = select.attr('data-select');
            this.VALUE = select.attr('data-value');
            this.NAME = select.attr('name');
            this.DESCRICAO = select.attr('data-desc');
            this.DESCRICAO2 = select.attr('data-desc2');
            this.DESCRICAO3= select.attr('data-desc3');
            this.AUTOCOMPLETE = select.attr('autocomplete');
            this.dataFromId = {};
            this.reload = this.reloadRequests.bind(this);
            
            if(this.AUTOCOMPLETE=="true"){
                this.autoComplete(); 
            }else{
                if(this.DATA){
                    this.montaSelect();      
                }else{
                    this.get(this.URL,null,this.requestSelect.bind(this),null,null);
                }
            }

            $(this.SELECT).change(this.changeEvent.bind(this));
        };

        SelectController.prototype = new ABSTRACT();
        SelectController.prototype.constructor = SelectController;


        SelectController.prototype.reloadRequests = function(data){
            this.get(this.URL,null,this.requestSelect.bind(this),null,null);
        }

        SelectController.prototype.requestSelect = function(data){
            if(data && data.list){
                this.DATA = data.list;
                this.montaSelect();
            }
        }

        SelectController.prototype.montaSelect = function(){

            $(this.SELECT).selectpicker('destroy');
            $(this.SELECT).html('');
            if(!$(this.SELECT).attr("multiple")){
                var option = $('<option/>',{value:""}).text("Selecione");
            }
                
            this.appendSelect(option);
            
            for(var i in this.DATA){
                
                var dado = this.DATA[i];
                var id = dado[this.VALUE];
                this.dataFromId[dado.id] = dado;

                var texto = this.trataDescricao(this.DESCRICAO,dado);
                var desc2 = this.trataDescricao(this.DESCRICAO2,dado);
                if(desc2){ texto =  texto + ' - ' + desc2; }
                var desc3 = this.trataDescricao(this.DESCRICAO3,dado);
                if(desc3){ texto =  texto + ' - ' + desc3; }
                
                var option = $('<option/>',{value:id}).text(texto);
                
                
                if(this.DELEGATE && this.DELEGATE.dado &&  this.DELEGATE.dado[this.NAME]){
                    var item = this.DELEGATE.dado[this.NAME];
                    if(id == item){
                        option.attr("selected","selected");
                    }else if(id == item.id){
                        option.attr("selected","selected");

                    }

                    
                }

                this.appendSelect(option);
            }
            $(this.SELECT).selectpicker({liveSearch: true,size:6});
            this.refresh();
        }

        SelectController.prototype.refresh = function(option){
            $(self).trigger('dadosRecebidos',[this.DATA]);
            $(this.SELECT).trigger('dadosRecebidos',[this.DATA]);
            $(this.SELECT).selectpicker('render');
            $(this.SELECT).selectpicker('refresh');
        }

        SelectController.prototype.appendSelect = function(option){

            $(this.SELECT).append(option);
        }  

        SelectController.prototype.returnTexto  = function(item){
            var texto = this.trataDescricao(this.DESCRICAO,item);
            var desc2 = this.trataDescricao(this.DESCRICAO2,item);
            if(desc2){ texto =  texto + ' - ' + desc2; }
            var desc3 = this.trataDescricao(this.DESCRICAO3,item);
            if(desc3){ texto =  texto + ' - ' + desc3; }

            return texto;
        }

        SelectController.prototype.autocompletOption = function(){

            if(this.DELEGATE && this.DELEGATE.dado && this.DELEGATE.dado[this.NAME]){
                var dado = this.DELEGATE.dado[this.NAME];
                var texto = this.returnTexto(dado);
                var option = $('<option/>',{value:dado['id']}).text(texto);
                $(this.SELECT).append(option);
                $(option).attr('selected','selected');
            }
        }


        SelectController.prototype.autoComplete = function(){
            this.autocompletOption();
            var self = this;
            $(this.SELECT)
            .selectpicker({
                liveSearch: true
            })
            .ajaxSelectPicker({
                ajax: {
                    url: self.URL,
                    dataType: 'json',
                    contentType: "application/json",
                    data: function () {                 
                        return JSON.stringify({
                            maxResult:30 , // search term
                            pagina: 1,
                            search:'{{{q}}}'
                        });
                    }
                },
                // locale: {
                //     emptyTitle: placeholder
                // },
                preprocessData: function(data){
                    $(self).trigger('dadosRecebidos',data);
                    var items = [];
                    if(data.hasOwnProperty('list')){
                        var len = data.list.length;
                        self.dataFromId = {};
                        for(var i = 0; i < len; i++){
                            var item = data.list[i];

                            self.dataFromId[item.id] = item;
                            var texto = self.trataDescricao(self.DESCRICAO,item);
                            var desc2 = self.trataDescricao(self.DESCRICAO2,item);
                            if(desc2){ texto =  texto + ' - ' + desc2; }
                            var desc3 = self.trataDescricao(self.DESCRICAO3,item);
                            if(desc3){ texto =  texto + ' - ' + desc3; }


                            items.push(
                            {
                                text: texto,
                                value: item[self.VALUE]
                            }
                            );
                        }
                    }
                    return items;
                },
                preserveSelected: true
            });
        }    


        SelectController.prototype.reloadData = function(data){

            this.DATA = data;
            this.montaSelect();
        } 


        SelectController.prototype.changeEvent = function(){
            
            var valor = $(this.SELECT).val();
            if(this.dataFromId[valor]){
                $(this.SELECT).trigger("dadosSelecionados",this.dataFromId[valor]);
            }
        } 


        SelectController.prototype.trataDescricao = function(caminho,dado){

            if(caminho){
                if(caminho.indexOf('.') > -1){
                    var arrayCaminho = caminho.split('.');
                    var jsonAux = arrayCaminho;
                    for(var i in arrayCaminho){
                        jsonAux = jsonAux[i];
                    }
                    return jsonAux;
                }

                return dado[caminho];
            }
            return null;
        }


        return new SelectController(select,data,delegate);

    }
});


