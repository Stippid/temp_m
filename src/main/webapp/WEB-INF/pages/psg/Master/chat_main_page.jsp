<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<style>
.disabled-container {
        opacity: 0.5;
        pointer-events: none;
        background-color: #f0f0f0;
}

.question {
  margin-bottom: 10px;
  margin-top: 10px;
      color: #494949;
}
.chat_div {
    height: calc(100vh - 110px);
    position: relative;
        background-color: #cfd2d5;
            padding: 5px;
            z-index: 99;
}
.custom-container {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    margin: 5px 0;
}
.send_btn {
	position: absolute;
	bottom: 4px;
	right: 10px;
	border-radius: 5px;
}
div#classificationDiv {
    background-color: #daeaff;
    padding: 10px;
    border-radius: 10px 10px 0 0;
}
.chat_div .form-check-input {
    margin-left: 0;
    position: relative;
}
.que_div {
	background-color: #fff;
    padding: 10px;
    margin: 5px 20px;
    border-radius: 10px;
}
.default_div {
	text-align: center;
    left: 0;
    right: 0;
    top: 35%;
    position: absolute;
}
.default_div p {
	        color: #0c0c0c;
    font-weight: bold;
}
.chat_main {
	overflow: auto;
    max-height: 695px;
        height: 100%;
    position: relative;
}
.m-0 {
	margin: 0;
}
.inpt_cls {
	    border: 2px solid #122c43;
}
div#classificationDiv p {
    color: #512424;
}
.que_div p {
	margin-bottom: 0;
}
.tbl-scroll {
    max-height: 400px;
    overflow: auto;
}
::placeholder {
            color: #b4b8bc !important;
        }
        .que_div thead th {
    font-size: 16px;
    text-transform: capitalize;
}
.spinner2 {
      display: flex;
        position: relative;
        background: white;
        max-width: 30%;
        border-radius: 10px 10px 10px 10px;
        justify-content: center;
        padding: 10px;
            margin: 5px 20px;
}
/* .spinner2:before {
    content: '';
        position: absolute;
        bottom: -6px;
        border-top: 6px solid #fff;
        left: 0;
        border-right: 7px solid transparent;
}  */
.spinner2 .sp_circle {
    width: 1rem;
    height: 1rem;
    top: 0;
    background-color: #a1a3a5;
    border-radius: 50%;
    margin: 0.1rem;
    -webkit-animation: scale 0.5s linear 0s infinite alternate;
                    animation: scale 0.5s linear 0s infinite alternate;         
}
  .spinner2 .sp_circle.one {
    -webkit-animation-delay: 0s;
                    animation-delay: 0s;
}
  .spinner2 .sp_circle.two {
    -webkit-animation-delay: 0.2s;
                    animation-delay: 0.2s;
}
  .spinner2 .sp_circle.three {
    -webkit-animation-delay: 0.4s;
                    animation-delay: 0.4s;
}
    
    
    @-webkit-keyframes scale {
    from {
        transform: scale(0.2);
    }
    to {
        transform: scale(1);
    }
}
@keyframes scale {
    from {
        transform: scale(0.2);
    }
    to {
        transform: scale(1);
    }
}
</style>
<%-- <form:form name="chatForm" id="chatForm" action="chatAction" method="post" class="form-horizontal" commandName="chatCMD">  --%>
<body>

    
	<div class="chat_div">
	<div class="chat_main">
		<div class="default_div">
			<img class="" src="js/img/chatbot.png">
			<p>How can I help you?</p>
		</div>
	
    	<div id="question_history" class="">    	    
	    </div>	    	    
	</div>
	<div class="custom-container">
	<div id="classificationDiv" style="display:none">
	        <p class="m-0">Please select any one classification from below.</p>
	        <label><input class="form-check-input" type="radio" name="option" value="vehicle_category A"> Vehicle Type A</label>
	        <label><input class="form-check-input" type="radio" name="option" value="Vehicle Category B"> Vehicle Type B</label>
	        <label><input class="form-check-input" type="radio" name="option" value="Vehicle Category C"> Vehicle Type C</label>
        </div>
	        <input class="form-control inpt_cls" type="text" id="inputText" placeholder="Write your question here ..."><button class="btn btn-primary send_btn"><i class="fa fa-send"></i></button>
	        <div id="radioButtons"></div>
	        <div id="responseTable">
	    </div>
	    </div>
	</div>
<%-- </form:form> --%>

<Script>

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";
var que_cnt = 1;
$(document).ready(function() {
	document.getElementById("classificationDiv").style.display = 'none';
    $('input[type="radio"]').change(function() {
    	var input_text = $("#inputText").val();
        var selectedOption = input_text+ " "+$('input[type="radio"]:checked').val();
        $("#question_history").append('<div class="que_div"><p><strong class="question">' +que_cnt + '</strong></p></div> ');
        que_cnt+=1;
        $("#inputText").val("");
        var radioButtons = document.querySelectorAll('#classificationDiv input[type="radio"]');
        radioButtons.forEach(function(radioButton) {
            radioButton.checked = false;
        });
        document.getElementById("classificationDiv").style.display = 'none';
        $(".default_div").hide();
        var spinHTML = '<div class="spinner2" id="spinnerDiv"><div class="sp_circle one"></div><div class="sp_circle two"></div><div class="sp_circle three"></div></div>';
        $("#question_history").append(spinHTML);
        document.getElementById("question_history").scrollTop = document.getElementById("question_history").scrollHeight;

		$.post("http://131.3.54.209:5000/chatbot?" + key + "=" + value, {
		    question: input_text,
		})
		.done(function(data, status) {
			console.log("Data",data);
			var elementToRemove = document.getElementById("spinnerDiv");
	 		  if (elementToRemove) {
	 		    elementToRemove.parentNode.removeChild(elementToRemove);
	 		    console.log("Element Remove with id"+elementToRemove);
	 		  } else {
	 		    console.log("Element with ID " + id + " not found.");
	 		  }
			var table_str = '<div class="que_main"><div class="que_div tbl-scroll"><table class="table no-margin table-striped table-hover table-bordered">';
			for(var i=0;i<data.length;i++){
				var cur_dict = data[i];
				if(i==0){
					table_str+='<thead><tr>';
					Object.entries(cur_dict).forEach(([key,value])=>{
						table_str+=('<th>'+key.replaceAll("_"," ")+'</th>');
					});
					table_str+='</tr></thead>';
					table_str+='<tbody><tr>';
					Object.entries(cur_dict).forEach(([key,value])=>{
						table_str+=('<td>'+value+'</td>');
					});
					table_str+='</tr>';
				}
				else{
					table_str+='<tr>';
					Object.entries(cur_dict).forEach(([key,value])=>{
						table_str+=('<td>'+value+'</td>');
					});
					table_str+='</tr>';
				}
			}
			table_str+='<tbody></table></div></div>';
			var delayInMilliseconds = 100; 
			$("#question_history").append(table_str);
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
		    console.error("<--Error-->", errorThrown,"<--jqXHR-->",jqXHR,"<--textStatus-->", textStatus);
			var elementToRemove = document.getElementById("spinnerDiv");
	 		  if (elementToRemove) {
	 		    elementToRemove.parentNode.removeChild(elementToRemove);
	 		    console.log("Element Remove with id"+elementToRemove);
	 		  } else {
	 		    console.log("Element with ID " + id + " not found.");
	 		  }
			$("#question_history").append('<div>Unknown Error, Please ask again !!</div>');
		});
    });
});

document.getElementById("inputText").addEventListener("keyup", function(event) {
	  if (event.key === "Enter") {
		  var input_text = $("#inputText").val();
		  var myarr = ["vehicle_unit A", "vehicle_unit B", "vehicle_unit C"];
		  var flag = true;
		  console.log("here");
		  if (flag){
			  document.getElementById("classificationDiv").style.display = 'none';
			  $(".default_div").hide();
			  $("#question_history").append('<p><strong class="question"> ' +que_cnt+". "+ input_text + '</strong></p>');
			  que_cnt+=1;
			  var spinHTML = '<div class="spinner2" id="spinnerDiv"><div class="sp_circle one"></div><div class="sp_circle two"></div><div class="sp_circle three"></div></div>';
		        $("#question_history").append(spinHTML);
		        document.getElementById("question_history").scrollTop = document.getElementById("question_history").scrollHeight;
					$("#inputText").val("");
					$.post("http://131.3.54.209:5000/chatbot?" + key + "=" + value, {
						question : input_text,
					}, function(data, status) {
						console.log("Data",data);
						var elementToRemove = document.getElementById("spinnerDiv");
				 		  if (elementToRemove) {
				 		    elementToRemove.parentNode.removeChild(elementToRemove);
				 		    console.log("Element Remove with id"+elementToRemove);
				 		  } else {
				 		    console.log("Element witAI Databoth ID " + id + " not found.");
				 		  }
						var table_str = '<div class="que_main"><div class="que_div tbl-scroll"><table class="table no-margin table-striped table-hover table-bordered">';
						for(var i=0;i<data.length;i++){
							var cur_dict = data[i];
							if(i==0){
								table_str+='<thead><tr>';
								Object.entries(cur_dict).forEach(([key,value])=>{
									table_str+=('<th>'+key.replaceAll("_"," ")+'</th>');
								});
								table_str+='</tr></thead>';
								table_str+='<tbody><tr>';
								Object.entries(cur_dict).forEach(([key,value])=>{
									table_str+=('<td>'+value+'</td>');
								});
								table_str+='</tr>';
							}
							else{
								table_str+='<tr>';
								Object.entries(cur_dict).forEach(([key,value])=>{
									table_str+=('<td>'+value+'</td>');
								});
								table_str+='</tr>';
							}
						}
						table_str+='<tbody></table></div></div>';
						$("#question_history").append(table_str);
					});
		  }
		  else{
			  document.getElementById("classificationDiv").style.display = 'block';
		  }
	  }
	});
	
</Script>


