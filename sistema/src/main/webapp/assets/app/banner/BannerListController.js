
	define(["util/ListController","router"] ,function (ListController,router) {
	return function(){
		var BannerListController = function(){
			this.loadPage(this.viewDidLoad.bind(this));
		}

		BannerListController.prototype =  new ListController();

		BannerListController.prototype.constructor = BannerListController;

		BannerListController.prototype.divLoad =  "#corpoDoSistema";
		BannerListController.prototype.urlPage = "banner/bannerlist.jsp";
		BannerListController.prototype.urlLoadList = "banner/list";
		BannerListController.prototype.url = "banner/";
		BannerListController.prototype.urlDelete = "banner/";
		BannerListController.prototype.urlDeleteAll = "banner/deleteAll/";


		

		return new BannerListController();
	}
});