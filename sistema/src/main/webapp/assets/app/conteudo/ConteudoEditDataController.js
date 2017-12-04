
	define(["util/AbstractForm","router",'util/pasta/PastaWidgetController','util/anexo/AnexoListController'] ,function (AbstractForm,router,PastaWidgetController,Anexo) {
    return function(){
        var ConteudoEditDataController = function(){
            this.loadPage(this.loadData.bind(this));
            this.openLoad();
        }

        ConteudoEditDataController.prototype =  new AbstractForm();

        ConteudoEditDataController.prototype.constructor = ConteudoEditDataController;

        ConteudoEditDataController.prototype.divLoad =  "#corpoDoSistema";
        ConteudoEditDataController.prototype.url = "conteudo/";
        ConteudoEditDataController.prototype.urlAction = "conteudo/save";
        ConteudoEditDataController.prototype.urlPage = "conteudo/conteudoedit.jsp";

        ConteudoEditDataController.prototype.termineteLoadForm = function(){
            tinyMCE.editors=[];     
            this.editor();
            this.closeLoad();
            this.activeFotos();
        }

        ConteudoEditDataController.prototype.activeFotos = function() {
            if (this.dado && this.dado.id && this.dado.pasta) {
                var self = this;
                
                self.anexo = new Anexo(self.dado.pasta,true);
                self.anexo.loadPage(self.anexo.viewDidLoad.bind(self.anexo));

                //self.pastaWidget = new PastaWidgetController(self.dado.pasta.id,null,true);
                //this.pastaWidget.loadPage(self.pastaWidget.viewDidLoad.bind(self.pastaWidget));
                
            } 

        }

        ConteudoEditDataController.prototype.editor = function() {

            var self = this;


            tinymce.EditorManager.editors = [];
            tinymce.EditorManager.execCommand('mceRemoveEditor',true, "#conteudo");
            // tinymce.execCommand('mceRemoveControl', true, "#texto");
            // tinymce.init({selector: "#texto",language : 'pt_BR',  toolbar: 'mybutton' , setup:this.setupEditor.bind(this)});
            tinymce.init({

                selector: "#conteudo",
                language : 'pt_BR',
                force_hex_style_colors : true,


                content_css: 'https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css',
                noneditable_noneditable_class: 'fa',
                
                extended_valid_elements: 'span[*]',


                plugins: "fontawesome noneditable code textcolor",



                body_class: 'texto',

                style_formats: [
                /*{title: 'Recursos', items: [
                {title: 'Título principal', block: 'p', classes: 'titulo-principal'},
                {title: 'Título tabulado', block: 'p', classes: 'titulo-tabulado'},
                {title: 'Parágrafo normal', block: 'p', classes: 'paragrafo-normal'},
                {title: 'Parágrafo citação', block: 'p', classes: 'paragrafo-citacao'},
                {title: 'Parágrafo final', block: 'p', classes: 'paragrafo-final'}
                ]},*/
                {title: 'Headers', items: [
                {title: 'Cabeçalho 1', block: 'h1'},
                {title: 'Cabeçalho 2', block: 'h2'},
                {title: 'Cabeçalho 3', block: 'h3'},
                {title: 'Cabeçalho 4', block: 'h4'},
                {title: 'Cabeçalho 5', block: 'h5'},
                {title: 'Cabeçalho 6', block: 'h6'}
                ]},
                /*{title: 'Inline', items: [
                {title: 'Bold', icon: 'bold', format: 'bold'},
                {title: 'Italic', icon: 'italic', format: 'italic'},
                {title: 'Underline', icon: 'underline', format: 'underline'},
                {title: 'Strikethrough', icon: 'strikethrough', format: 'strikethrough'},
                {title: 'Superscript', icon: 'superscript', format: 'superscript'},
                {title: 'Subscript', icon: 'subscript', format: 'subscript'},
                {title: 'Code', icon: 'code', format: 'code'},
                {title: 'Marca texto', icon: 'backcolor', inline: 'span', styles: {'background-color': '#FFFF00', margin: '2px 1px'}},
                {title: 'Texto vermelho', icon: 'forecolor', inline: 'span', styles: {'color': '#FF0000'}}
                ]},
                /*{title: 'Blocks', items: [
                {title: 'Paragraph', format: 'p'},
                {title: 'Blockquote', format: 'blockquote'},
                {title: 'Div', format: 'div'},
                {title: 'Pre', format: 'pre'}
                ]},*/
                {title: 'Alignment', items: [
                {title: 'Left', icon: 'alignleft', format: 'alignleft'},
                {title: 'Center', icon: 'aligncenter', format: 'aligncenter'},
                {title: 'Right', icon: 'alignright', format: 'alignright'},
                {title: 'Justify', icon: 'alignjustify', format: 'alignjustify'}
                ]}
                ],
                // toolbar: "mybutton",
                // toolbar: "mybutton  undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | sizeselect | fontselect fontsizeselect ",
                
                toolbar1: "fontawesome, code ,undo redo | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | styleselect | forecolor fontsizeselect ",
                // menubar: "tools",


                //image_advtab: true,
                relative_urls: true,
                nonbreaking_force_tab: true,
                // visualblocks_default_state: true,
                fontsize_formats: "8pt 10pt 12pt 14pt 16pt 18pt 20pt 22pt 24pt 36pt",
                // plugins: "textcolor",

                theme_advanced_fonts : "Arialxxxxx=arial,helvetica,sans-serif;",
                //setup: self.setupEditor.bind(self)



            });
            
            tinymce.execCommand('mceAddControl',true, "#conteudo");

            $(this).on('destroy',function(){

                
            });
        }

       

        ConteudoEditDataController.prototype.save = function() {
            $("#conteudo").change();

            var dataForm = {};

            $.extend(dataForm, this.dado);
            console.info(dataForm);
            this.monteSelectSave(dataForm);
            this.processData(dataForm);

            dataForm.conteudo = tinyMCE.get('conteudo').getContent();

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

        return new ConteudoEditDataController();
    }
});