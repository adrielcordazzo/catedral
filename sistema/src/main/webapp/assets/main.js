var appName = "clin";
var versionRandom = Math.random();
var importar =  require;
var EVENTHASH = true;
importar.config({
	baseUrl: 'assets/app/',
	urlArgs: 'v=0.1'+versionRandom,
	paths: {
		"text" : "util/text",
		"router" : "util/router"
	}
    });
var app = null;
importar(['util/Abstract','util/Application','router'] ,function (ABSTRACT,APLICATION,router) {
	return function(){
		var Main = function(){
			app = new APLICATION();
			this.rota();
		}

		Main.prototype =  new ABSTRACT();

		Main.prototype.constructor = Main;

		Main.prototype.divLoad =  "#corpo";


		Main.prototype.rota =  function(){

			router
			.registerRoutes({

                membro: { path: '/membro', moduleId: 'membro/MembroListController' },
                membroadd: { path: '/membro/add', moduleId: 'membro/MembroEditDataController' },
                membroedit: { path: '/membro/edit/:id', moduleId: 'membro/MembroEditDataController' }, 

                cargo: { path: '/cargo', moduleId: 'cargo/CargoListController' },
                cargoadd: { path: '/cargo/add', moduleId: 'cargo/CargoEditDataController' },
                cargoedit: { path: '/cargo/edit/:id', moduleId: 'cargo/CargoEditDataController' }, 


                conteudo: { path: '/conteudo', moduleId: 'conteudo/ConteudoListController' },
                conteudo1: { path: '/conteudo/add', moduleId: 'conteudo/ConteudoEditDataController' },
                conteudo2: { path: '/conteudo/edit/:id', moduleId: 'conteudo/ConteudoEditDataController' },

                conteudotipo1: { path: '/conteudotipo', moduleId: 'conteudotipo/ConteudotipoListController' },
                conteudotipo2: { path: '/conteudotipo/add', moduleId: 'conteudotipo/ConteudotipoEditDataController' },
                conteudotipo3: { path: '/conteudotipo/edit/:id', moduleId: 'conteudotipo/ConteudotipoEditDataController' },
                
                categoria1: { path: '/categoria', moduleId: 'categoria/CategoriaListController' },
                categoria2: { path: '/categoria/add', moduleId: 'categoria/CategoriaEditDataController' },
                categoria3: { path: '/categoria/edit/:id', moduleId: 'categoria/CategoriaEditDataController' },

            
                campoconfiguracao: { path: '/campoconfiguracao', moduleId: 'campoconfiguracao/CampoconfiguracaoListController' },
                campoconfiguracaoadd: { path: '/campoconfiguracao/add', moduleId: 'campoconfiguracao/CampoconfiguracaoEditDataController' },
                campoconfiguracaoedit: { path: '/campoconfiguracao/edit/:id', moduleId: 'campoconfiguracao/CampoconfiguracaoEditDataController' }, 

                configuracaocampo: { path: '/configuracaocampo', moduleId: 'configuracaocampo/ConfiguracaocampoListController' },
                configuracaocampoadd: { path: '/configuracaocampo/add', moduleId: 'configuracaocampo/ConfiguracaocampoEditDataController' },
                configuracaocampoedit: { path: '/configuracaocampo/edit/:id', moduleId: 'configuracaocampo/ConfiguracaocampoEditDataController' }, 

                contratante: { path: '/contratante', moduleId: 'util/contratante/ContratanteListController' },
                contratanteadd: { path: '/contratante/add', moduleId: 'util/contratante/ContratanteEditDataController' },
                contratanteedit: { path: '/contratante/edit/:id', moduleId: 'util/contratante/ContratanteEditDataController' },

                usuario: { path: '/usuario', moduleId: 'util/usuario/UsuarioListController' },
                usuarioadd: { path: '/usuario/add', moduleId: 'util/usuario/UsuarioEditDataController' },
                usuarioedit: { path: '/usuario/edit/:id', moduleId: 'util/usuario/UsuarioEditDataController' }, 

                role: { path: '/role', moduleId: 'util/role/RoleListController' },
                roleadd: { path: '/role/add', moduleId: 'util/role/RoleEditDataController' },
                roleedit: { path: '/role/edit/:id', moduleId: 'util/role/RoleEditDataController' }, 
                // matches an exact path
                cidade: { path: '/cidade', moduleId: 'cidade/CidadeListController' },
                cidade1: { path: '/cidade/add', moduleId: 'cidade/CidadeEditDataController' },
                cidade2: { path: '/cidade/edit/:id', moduleId: 'cidade/CidadeEditDataController' },



                bairro: { path: '/bairro', moduleId: 'bairro/BairroListController' },
                bairro1: { path: '/bairro/add', moduleId: 'bairro/BairroEditDataController' },
                bairro2: { path: '/bairro/edit/:id', moduleId: 'bairro/BairroEditDataController' },


                estado: { path: '/estado', moduleId: 'estado/EstadoListController' },
                estado1: { path: '/estado/add', moduleId: 'estado/EstadoEditDataController' },
                estado2: { path: '/estado/edit/:id', moduleId: 'estado/EstadoEditDataController' },

                contato: { path: '/contato', moduleId: 'contato/ContatoListController' },
                contato1: { path: '/contato/add', moduleId: 'contato/ContatoEditDataController' },
                contato2: { path: '/contato/edit/:id', moduleId: 'contato/ContatoEditDataController' },

                contatotipo1: { path: '/contatotipo', moduleId: 'contatotipo/ContatotipoListController' },
                contatotipo2: { path: '/contatotipo/add', moduleId: 'contatotipo/ContatotipoEditDataController' },
                contatotipo3: { path: '/contatotipo/edit/:id', moduleId: 'contatotipo/ContatotipoEditDataController' },

                evento: { path: '/evento', moduleId: 'evento/EventoListController' },
                evento1: { path: '/evento/add', moduleId: 'evento/EventoEditDataController' },
                evento2: { path: '/evento/edit/:id', moduleId: 'evento/EventoEditDataController' },
                
                banner: { path: '/banner', moduleId: 'banner/BannerListController' },
                banner1: { path: '/banner/add', moduleId: 'banner/BannerEditDataController' },
                banner2: { path: '/banner/edit/:id', moduleId: 'banner/BannerEditDataController' },
                
                
                // homeEdit: { path: '/home/:brasil', moduleId: 'user/user' },
                // regex: { path: /^\/\w+\/\d+$/i, moduleId: 'user/user' },
                // matches everything else
                //notFound: { path: '*', moduleId: 'imovel/ImovelListController' }
                }).on('routeload', function(module, routeArguments) {
                    
                    $("#dock").dockmodal("close");
                    
                    if(module){
                       new module();	
                   }

                   }).init(); // Set up event handlers and trigger the initial page load

                
            }


            new Main();

            }();
            });