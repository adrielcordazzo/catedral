define(['util/Abstract','router'] ,function (ABSTRACT,router) {
    return function(){
        var WebSoketController = function(){
            this.stompClient = null;
            this.socket = null;
        }

        WebSoketController.prototype =  new ABSTRACT();

        WebSoketController.prototype.constructor = WebSoketController;

        WebSoketController.prototype.divLoad =  "#corpo";

        WebSoketController.prototype.urlSocket = '/webSocket';
        WebSoketController.prototype.urlsubscribe = '/sync/messages/xxx';
        WebSoketController.prototype.urlsend = "/app/webSocket";

        WebSoketController.prototype.setConnected = function(status){
            this.connected = status;
            console.info(this.connected);
        }
        WebSoketController.prototype.connect = function(){
            this.socket = new SockJS(this.urlSocket);
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect({},this.subscribe.bind(this));
        }

        WebSoketController.prototype.subscribe = function(frame){
         this.setConnected(true);
         console.log('Connected: ' + frame);
         this.stompClient.subscribe(this.urlsubscribe,this.showMessageOutput.bind(this));
     }

     WebSoketController.prototype.disconnect = function(){
        if(this.stompClient != null) {
            this.stompClient.disconnect();
        }

        this.setConnected(false);
        console.log("Disconnected");
    }
    WebSoketController.prototype.sendMessage = function(json){
        this.stompClient.send(this.urlsend, {}, JSON.stringify(json));
    }

    WebSoketController.prototype.showMessageOutput = function(messageOutput){
        console.info(messageOutput.body)
        this.execCommandSocket(JSON.parse(messageOutput.body));
        return;
    }

    WebSoketController.prototype.execCommandSocket = function(comamnd){

        if(!comamnd.type)
            return;
        
        if(comamnd.type == "modal"){
            this.execModal(comamnd);
            return;
        }

        this.execUrl(comamnd);
        
    }

    WebSoketController.prototype.execUrl = function(comamnd){

       window.location.hash = '/'+comamnd.url
   }

   WebSoketController.prototype.execRequire = function(comamnd){

       require([comamnd.url],function(itemController){
        var item = new itemController(comamnd.data);
        item.loadPage();
    });
   }

   WebSoketController.prototype.execModal = function(comamnd){

    require([comamnd.url],function(itemController){
        var item = new itemController(comamnd.data);
        item.loadPage();
    });
}

return new WebSoketController();
}
});