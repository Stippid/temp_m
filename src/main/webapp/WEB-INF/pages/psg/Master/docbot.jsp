<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/js_css/internal_css.css">
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
      height: calc(100vh - 160px);  
/*     height: calc(100vh - 120px); */
    position: relative;
        background-color: #cfd2d5;
            padding: 5px;
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
    padding-bottom:5px;
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
.uploaded_file{
    padding: 11px;
/*     display:none; */
background-color: darkgrey;
display:flex;

}

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

// Just to show spinner bar in center of body
// DELETE body css before using
body {
    width:100%;
    height:100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.spinner-square {
     display: flex; 
    flex-direction: row;
    width: 230px;
    height: 30px;
    font-family: cursive;
    font-style: oblique;
    left:50%;
    top:41%;
    position:fixed;

}

.spinner-square > .square {
    width: 17px;
    height: 25px !important;
    margin: auto auto;
    border-radius: 4px;
}

.square-1 {
    animation: square-anim 1200ms cubic-bezier(0.445, 0.05, 0.55, 0.95) 0s infinite;
}

.square-2 {
    animation: square-anim 1200ms cubic-bezier(0.445, 0.05, 0.55, 0.95) 200ms infinite;
}

.square-3 {
    animation: square-anim 1200ms cubic-bezier(0.445, 0.05, 0.55, 0.95) 400ms infinite;
}

@keyframes square-anim {
    0% {
        height: 80px;
        background-color: rgb(111, 163, 240);
    }
    20% {
        height: 80px;
    }
    40% {
        height: 120px;
        background-color: rgb(111, 200, 240);
    }
    80% {
        height: 80px;
    }
    100% {
        height: 80px;
        background-color: rgb(111, 163, 240);
    }
}
.upload_btn{
	height:26px;
    background-color: blanchedalmond;
    border-radius: 6px;
    width: 56px;
    cursor:pointer;
}
.upload_btn:hover {
   background-color: blue;
   color:white;
}
text_style{
font-family:italic;
}
.question_history{
padding-bottom:45px;
}
</style>
<body>

    
		<div class="uploaded_file" id="upload_file">
		<input type="file" name="file_upload" id="file_upload">
		<input id= "upload_btn" name = "upload_btn" type="button" value= "Upload" class="upload_btn">
<!-- 		<div id="procdiv2" class="processing2 custom-d-none"><div class="spinner-border text-light"></div> Uploading your file...</div> -->
<!-- 			<div id="procdiv2" class="spinner-square custom-d-none"> -->
<!-- 		        <div class="square-1 square"></div> -->
<!-- 		        <div class="square-2 square"></div> -->
<!-- 		        <div class="square-3 square"></div> <p class="text_style">your file is uploading .. </p> -->
<!-- 			</div> -->
		</div>
		
	<div class="chat_div">
	<div id="procdiv2" class="spinner-square custom-d-none">
		        <div class="square-1 square"></div>
		        <div class="square-2 square"></div>
		        <div class="square-3 square"></div> <p class="text_style">your file is uploading .. </p>
			</div>
	<div class="chat_main">
		<div class="default_div">
			<img class="" src="js/img/chatbot.png">
			<p>Ask me anything about Defence Procurement Procedure</p>
		</div>
	
    	<div id="question_history" class="question_history">    	    
	    </div>	    	    
	</div>
	<div class="custom-container">

	        <input class="form-control inpt_cls" type="text" id="inputText" maxlength="100" placeholder="Write your question here ..."><button id= "send_id" class="btn btn-primary send_btn"><i class="fa fa-send"></i></button>
	        <div id="responseTable">

	    </div>
	    </div>
	</div>
	<script>
$(document).ready(function() {
	$("#procdiv2").hide();
var que_cnt =1;

document.getElementById('send_id').onclick = function() {
	

	var input_text = $("#inputText").val();
	if (input_text !== '') {
   
	$(".default_div").hide();
	  $("#question_history").append('<p><strong class="question"> ' + que_cnt +". "+ input_text + '</strong></p>');
	  que_cnt+=1;
	  var spinHTML = '<div class="spinner2" id="spinnerDiv"><div class="sp_circle one"></div><div class="sp_circle two"></div><div class="sp_circle three"></div></div>';
      $("#question_history").append(spinHTML);
      document.getElementById("question_history").scrollTop = document.getElementById("question_history").scrollHeight;
			$("#inputText").val("");
			document.getElementById("inputText").disabled = true;
			document.getElementById("send_id").disabled = true;
			$.post("http://192.168.14.38:4545/ask" , {
				question : input_text,
			},function(data) {
				console.log("Data",data);
				var elementToRemove = document.getElementById("spinnerDiv");
		 		  if (elementToRemove) {
		 		    elementToRemove.parentNode.removeChild(elementToRemove);
		 		    console.log("Element Remove with id"+elementToRemove);
		 		  } else {
		 		    console.log("Element witAI Databoth ID " + id + " not found.");
		 		  }
				var table_str = '<div class="que_main">'+data.answer+'</div></br>'
				$("#question_history").append(table_str);
				document.getElementById("inputText").disabled = false;
				document.getElementById("send_id").disabled = false;
			});
	}else {
		        alert("Input text cannot be empty!");
		    }
	};
// code for enter

	document.addEventListener('keypress', function(event) {
		  if (event.keyCode === 13) {
		    document.getElementById('send_id').click();
		  }
		});
// 	end enter code

	document.getElementById('upload_btn').onclick = function() {
	    var formData = new FormData();
	    formData.append("file", $("#file_upload")[0].files[0]);
	    
	    if($("#file_upload")[0].files[0]){
	    $("#procdiv2").show();
	    $.ajax({
	        url: "http://192.168.14.38:4545/uploadfile",
	        type: "POST",
	        data: formData,
	        contentType: false,
	        processData: false,
	        success: function(data, status) {
				alert("PDF uploaded!");
				$("#procdiv2").hide();
	        },
	        error: function(xhr, status, error) {
	        }
	    });
	    }else{
			alert("No file to upload !!!");
			return
	    }
	};
	
});
</script>


