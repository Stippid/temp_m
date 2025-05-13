
  $("#chat-circle").click(function() {    
    $("#chat-circle").toggle('scale');
    $(".chat-box").toggle('scale');
  })
    $(".chat-box-toggle").click(function() {
    $("#chat-circle").toggle('scale');
    $(".chat-box").toggle('scale');
  })

let $chatHistory;
let $button;
let $textarea;
let $chatHistoryList;
 
 
function init() {
    $chatHistory = $('.chat-history');
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
    $chatHistoryList = $chatHistory.find('ul');
   //alert($button)
    $button.on('click', addMessage.bind(this));
   // $textarea.on('keyup', addMessageEnter.bind(this));
    
    
	 var key = "${_csrf.parameterName}";
	 var value = "${_csrf.token}";
	 
	 
	 
}




	

/*function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0]);
}
*/
 
function getCurrentTime() {
    //return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
	var d = new Date();
	var h = d.getHours();
	var m  = d.getMinutes()
	 var t = h + ":"+ m;
	return t;
}
function addMessage() {
	 
    sendMessage($textarea.val());
   
}

 
init();




function cacheDOM() {

    $chatHistory = $('.chat-history');
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
    $chatHistoryList = $chatHistory.find('ul');
}