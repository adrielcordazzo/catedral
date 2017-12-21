
    define(["util/AbstractForm","router",'util/pasta/PastaWidgetController','util/anexo/AnexoListController'] ,function (AbstractForm,router,PastaWidgetController,Anexo) {
    return function(){
        var MembroEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        MembroEditDataController.prototype =  new AbstractForm();

        MembroEditDataController.prototype.constructor = MembroEditDataController;

        MembroEditDataController.prototype.divLoad =  "#corpoDoSistema";
        MembroEditDataController.prototype.url = "membro/";
        MembroEditDataController.prototype.urlAction = "membro/save";
        MembroEditDataController.prototype.urlPage = "membro/membroedit.jsp";

        MembroEditDataController.prototype.termineteLoadForm = function(){
            this.closeLoad();

            $("#cpf").mask("999.999.999-99");
            $("#rg").mask("9999999999");
            $("#cep").mask("99999-999");

            /**/

            var cargos = [];
            if(this.modelComponente["cargos"]){
                cargos = this.modelComponente["cargos"];
            }

            var dados = {};

            if(this.dado.cargos){
                for(c in this.dado.cargos){
                    var item = this.dado.cargos[c];
                    var id = item.cargo.id;
                    var valor = true;
                    dados[id] = valor;
                }
            }

            console.log("CARGOS",cargos);

            require(["util/groupdados/GroupDadosController"],function(GroupForm){
                var groupForm = new GroupForm(cargos,dados,"cargo");
                self.cargos = groupForm;
                groupForm.divLoad = '#cargos';
                groupForm.loadPage(groupForm.viewDidLoad.bind(groupForm));
                $(groupForm).on("destroy",self.loadPage.bind(self));
            });

            /**/

            $('[data-action="abrirCamera"]',this.divLoad).click(this.abrirCamera.bind(this));
            $('[data-action="tirarFoto"]',this.divLoad).hide();


            if(this.dado && this.dado.imagem){
                var img = document.createElement('img');

                $(img).on('load', function() {
                    $('#foto').html(img);
                });
                $(img).addClass("thumbnail");
                img.src = this.dado.imagem;
                img.width = "300";
            }

            var self = this;

            $("#selectfoto",this.divLoad).change(function() {
              self.readURL(this);
            });
        }

        MembroEditDataController.prototype.readURL = function(input){
            var self = this;
          if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                var img = document.createElement('img');

                $(img).on('load', function() {
                    $('#foto').html(img);
                });
                $(img).addClass("thumbnail");
                img.src = e.target.result;
                img.width = "300";

                self.dado.imagem = reader.result;
            }

            reader.readAsDataURL(input.files[0]);
          }
        }

        MembroEditDataController.prototype.abrirCamera = function(){
            var self = this;

            var sayCheese = new SayCheese('#camera-container', { snapshots: true });

            sayCheese.on('start', function() {
                $('[data-action="tirarFoto"]',this.divLoad).show();
                $('[data-action="abrirCamera"]',this.divLoad).hide();
                $('#foto',this.divLoad).hide();

                $('[data-action="tirarFoto"]',this.divLoad).on('click', function(evt) {
                    var width = 320, height = 240;
                    sayCheese.takeSnapshot(width,height);

                    $('[data-action="tirarFoto"]',this.divLoad).hide();
                    $('[data-action="abrirCamera"]',this.divLoad).show();
                    $('#foto',this.divLoad).show();
                    $('#camera-container',this.divLoad).html("");
                    sayCheese.stop();
                });
            });

            sayCheese.on('error', function(error) {
                var $alert = $('<div>');
                $alert.addClass('alert alert-error').css('margin-top', '20px');

                if (error === 'NOT_SUPPORTED') {
                    $alert.html("<strong>:(</strong> Seu navegador não suporta vídeo");
                } else if (error === 'AUDIO_NOT_SUPPORTED') {
                    $alert.html("<strong>:(</strong> Seu navegador não suporta áudio!");
                } else {
                    $alert.html("<strong>:(</strong> É necessário habilitar a câmera!");
                }

                $('.camera').prepend($alert);
            });

            sayCheese.on('snapshot', function(snapshot) {
                var img = document.createElement('img');

                $(img).on('load', function() {
                    $('#foto').html(img);
                });
                $(img).addClass("thumbnail");
                img.src = snapshot.toDataURL('image/png');

                self.dado.imagem = snapshot.toDataURL('image/png');
            });

            sayCheese.start();
        }

        MembroEditDataController.prototype.save = function(edit) {

           var dataForm = {};

           $.extend(dataForm, this.dado);
           console.info(dataForm);
           this.monteSelectSave(dataForm);

           dataForm.cargos = [];
           var json = this.cargos.getData();
           for(var i in json){
                var idC = i;
                var valor = json[i];
                var jsonI = {cargo:{id:idC}};
                if(valor){
                    dataForm.cargos.push(jsonI);    
                }
            }

            this.processData(dataForm);

            if(this.urlAction!=null){
              this.openLoad(this.divLoad);
              if(!dataForm.id){
                 var success = this.success.bind(this);
                 this.post(this.urlAction,dataForm,success,this.error.bind(this),null);
              }else{
                 this.put(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null);
              }
             }else{
                console.error("not define urlAction");
            }
        };

        return new MembroEditDataController();
    }
});