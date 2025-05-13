
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
   
    $button.on('click', addMessage.bind(this));
   // $textarea.on('keyup', addMessageEnter.bind(this));
    
    
	 var key = "${_csrf.parameterName}";
	 var value = "${_csrf.token}";
	 
	 
	 
}


function render(message, userName) {
  $chatHistory.scrollTop($chatHistory[0]);

    // responses
    var templateResponse = Handlebars.compile($("#message-response-template").html());
    var contextResponse = {
        response: message,
        time: getCurrentTime(),
        userName: userName
    };

    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse));
  		$chatHistory.scrollTop($chatHistory[0]);
    }.bind(this), 1000);
}

	function sendMessage(message) {
	      alert(message);
	 	if($textarea.val() == ''){
	 	//alert("Please Select Module From above ");
	  	}
	else{
	    //let username = $('#userName').val();
	   //  alert(username);
	   
	   var c_id = $('#AutotextHid').val();
	   alert(c_id+"--ddcc")
	  
	   if($('#AutotextHid').val() != ''){
		   alert("autoooooo")
	   ans_formAuto(c_id);
	   }
	   
	   else{
	   sendMsg(message);
	   }
	   
	   
	  
	    

	    
	   	$chatHistory.scrollTop($chatHistory[0]);
	
	    if (message.trim() !== '') {
	        var template = Handlebars.compile($("#message-template").html());
	        var context = {
	            messageOutput: message,
	            time: getCurrentTime(),
	            toUserName: 'bot'
	        };
	
	        $chatHistoryList.append(template(context));
	  		$chatHistory.scrollTop($chatHistory[0]);
	        $textarea.val('');
	    }
	    
	    }
	    
	    
	}

/*function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0]);
}
*/
function getCurrentTime() {
    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
}

function addMessage() {
    sendMessage($textarea.val());
   
}

/*function addMessageEnter(event) {
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage();
    }
}
*/
init();




function cacheDOM() {

    $chatHistory = $('.chat-history');
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
    $chatHistoryList = $chatHistory.find('ul');
}