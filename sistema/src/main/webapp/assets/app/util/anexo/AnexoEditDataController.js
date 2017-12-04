
define(["util/AbstractForm","router"] ,function (AbstractForm,router) {
  return function(dados){
    var AnexoEditData = function(dados){
            // this.loadPage(this.loadData.bind(this));
            this.openLoad();
            this.dado = dados;
            this.image = {};
          }

          AnexoEditData.prototype =  new AbstractForm();

          AnexoEditData.prototype.constructor = AnexoEditData;

        // AnexoEditData.prototype.divLoad =  "#corpoDoSistema";
        AnexoEditData.prototype.url = "arquivo/";
        AnexoEditData.prototype.urlAction = "arquivo/crop";
        AnexoEditData.prototype.urlPage = "util/anexo/anexoedit.jsp";


        AnexoEditData.prototype.loadData = function(){

          this.termineteLoad();
        }

        AnexoEditData.prototype.ativaCrop = function(){
          var self = this;
          this.crop =  $('#image-crop',this.divLoad).cropper({
              // aspectRatio: 4 / 3,
              scalable:false,
              zoomable:false,
              viewMode:1,
              crop: function(e) {
               


               var item = $('#image-crop',this.divLoad).cropper('getImageData');
               var item2 = $('#image-crop',this.divLoad).cropper('getCropBoxData');
               console.info(item,arguments);
               self.image = {x:item2.left
                ,y:item2.top
                ,height:item2.height
                ,width:item2.width
                ,rotate:e.rotate
                ,scaleX:e.scaleX
                ,scaleY:e.scaleY
                ,largura:item.width
                ,altura:item.height};
              }
            });

        }

        AnexoEditData.prototype.removecrop = function(){
          this.image = {};
          $('#image-crop',this.divLoad).cropper('destroy')
        }        

        AnexoEditData.prototype.termineteLoadForm = function(){
          this.closeLoad();
          var self = this


          $('#legenda',this.divLoad).val(this.dado.legenda);
          $('#mascaraalign',this.divLoad).val(this.dado.mascaraalign);
          $('#mascaraalign',this.divLoad).selectpicker('render');
          $('#mascaraalign',this.divLoad).selectpicker('refresh');
            //$('#principal',this.divLoad).val(this.dado.principal);


            $('.crop',this.divLoad).click(this.ativaCrop.bind(this));
            $('.removecrop',this.divLoad).click(this.removecrop.bind(this));


            $('#image-crop',this.divLoad).attr('src','arquivo/getFile/'+this.dado.id);

          }


          AnexoEditData.prototype.success = function() {
            
              $(this).trigger("destroy");
          }

          AnexoEditData.prototype.save = function() {
            
            var dataForm = {id:this.dado.id
              ,legenda:$('#legenda',this.divLoad).val()
              ,mascaraalign:$('#mascaraalign',this.divLoad).val()
              ,x:this.image.x
              ,y:this.image.y
              ,largura:this.image.largura
              ,altura:this.image.altura
              ,height:this.image.height
              ,width:this.image.width
              ,rotate:this.image.rotate
              ,scaleX:this.image.scaleX
              ,scaleY:this.image.scaleY};


              this.processData(dataForm);


              if(this.urlAction!=null){
                this.openLoad(this.divLoad);
                if(!dataForm.id)
                 this.post(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null);
               else
                 this.put(this.urlAction,dataForm,this.success.bind(this),this.error.bind(this),null);
             }else{
              console.error("not define urlAction");
            }
          };

          return new AnexoEditData(dados);
        }
      });