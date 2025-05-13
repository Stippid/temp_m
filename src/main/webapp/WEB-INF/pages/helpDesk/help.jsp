<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<style>
textarea {
    width:690px;
    height:200px;
    text-transform: capitalize;
}
input[type="text"]{
  text-transform: capitalize;
}
</style>

<form:form name="help" id="help" action="helpAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="helpCMD" enctype="multipart/form-data">
<div class="animated fadeIn">

<div class="container" align="center">
	<div class="card">
		<div class="card-header"> <h5>Open a New Ticket</h5></div>
		
		<strong style="font-size: x-large;text-align: center;color:red;display: none" id="note" >For observation with respect to wpn and eqpt. pl use UNIT MCR Screen
		 by clicking update with observation button.</strong>
		<div class="card-body card-block cue_text">
			<div class="col-md-12">				  
				<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">User </label>
		                </div>
		                <div class="col-12 col-md-8">
		                <input id="username" name="username" class="form-control" maxlength="30"  value="${username}" autocomplete="off" readonly="readonly">
						</div>
	             	</div>
               </div>
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">SUS No </label>
		                </div>
		                <div class="col-12 col-md-8">
               				<input type="text" id="sus_no" name="sus_no"  maxlength="8" style="font-family: 'FontAwesome',Arial;" class="form-control" autocomplete="off" value="${sus_no}" readonly="readonly">
						</div>
	             	</div>
               </div>
             </div>
            <div class="col-md-12">	
				<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label">Unit Name</label>
		                </div>
		                <div class="col-12 col-md-8">
               				<input type="text" id="unit_name" name="unit_name" style="font-family: 'FontAwesome',Arial;" class="form-control" value='${unit_name}' readonly="readonly" maxlength="50">
						</div>
	             	</div>
               </div>
             <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Module</label>
		                </div>
		                <div class="col-12 col-md-8">
		                <input type="hidden" id="module_name" name="module_name" value="">
               				<select  id="module" name="module" class="form-control" onchange="mms_note();"> 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
       								<option style="text-transform: uppercase;"  value="${item.id}">${item.module_name}</option>
       							</c:forEach>
						    </select>
						</div>
	             	</div>
               </div>
           </div>
		  <div class="col-md-12">				  
               <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Sub Module</label>
		                </div>
		                <div class="col-12 col-md-8">
		                <input type="hidden" id="sub_module_name" name="sub_module_name" value="">
               				<select  id="sub_module" name="sub_module" class="form-control" > 
						   </select>
						</div>
	             	</div>
               </div> 
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Screen Name </label>
		                </div>
		                <div class="col-12 col-md-8">
		                <input type="hidden" id="screen_name_text" name="screen_name_text" value="">
               				<select  id="screen_name" name="screen_name" class="form-control" > 
						   </select>
						</div>
	             	</div>
               </div>
           </div>
			<div class="col-md-12">	           
               <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Help Topic </label>
		                </div>
		                <div class="col-12 col-md-8">
		                
		                <select  id="help_topic" name="help_topic" class="form-control"> 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${GetHelpTopic}" varStatus="num" >
       								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
       							</c:forEach>
						    </select>
<!--                				<select  id="help_topic" name="help_topic" class="form-control" >  -->
<!--                				  <option  value="0">--Select--</option> -->
<!-- 							  <option  value="1">Feedback</option> -->
<!-- 							  <option  value="2">General Inquiry</option> -->
<!-- 							  <option  value="3">Report a Problem</option> -->
<!-- 							  <option  value="4">Report a Problem/Access Issues</option> -->
<!-- 							  <option  value="5">Feature Request</option> -->
<!-- 						    </select> -->
						</div>
	             	</div>
                </div> 
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"> File</label>
		                	
		                </div>
		                <div class="col-12 col-md-8">
               				<input type="file" id="filedoc" name="filedoc" class="form-control" onchange="ValidateSize(this)">
						</div>
						<span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.pdf file extensions and file size max 200KB are allowed.)</span>
	             	</div>
                </div>   
			</div>
			<div class="col-md-12 form-group">				  
            		<div class="col-2 col-md-2">	             		
                	<label for="text-input"  class=" form-control-label"><strong style="color: red;" >*</strong> Issue summary </label>
                </div>
                <div class="col-10 col-md-10">
       				<input type="text" style="width:680;" id="issue_summary" name="issue_summary" placeholder="Maximum 100 characters" class="form-control char-counter"  autocomplete="off" maxlength="100"></input>
				</div>
              </div>
              <div class="col-md-12 form-group">	
		            <div class="col-2 col-md-2">	             		
		                <label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Description<br><span style="font-size: 10px;font-size:12px;color:red">(Max 1000 words)</span></label>
		             </div>
		             <div class="col-10 col-md-10">
						 <textarea  rows="2" cols="250" id="description" oninput="validateDescription(this)"style="height:150px;" name="description" class="form-control char-counter1" autocomplete="off" maxlength="1000"></textarea>
					</div>
            	</div>
	        </div>
	        <div class="card-footer" align="center">
	            <input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return isvalidData();">       
            </div> 
            </div>
       		</div>
	
</div>
</form:form> 
<Script>
 $(document).ready(function () {
	 var file = document.getElementById('filedoc');
		file.onchange = function(e) {
		  	var ext = this.value.match(/\.([^\.]+)$/)[1];
			switch (ext) {
			  case 'jpg':
			  case 'jpeg':
			  case 'pdf':
			  case 'JPG':
			  case 'JPEG':
			  case 'PDF':
			    break;
			  default:
			    alert('Please Only Allowed *.jpeg/.pdf File Extension');
			    this.value = '';
			}
			 if(this.files[0].size > 200000){
			        alert("File size must be 200 Kb!");
			        this.value = "";
			     }
		};
		
	
		/*  const descriptionField = document.getElementById('description');
		   descriptionField.addEventListener('dragenter', function(event) {
		       event.preventDefault();
		   });
		   descriptionField.addEventListener('dragover', function(event) {
		       event.preventDefault();
		   });
		   descriptionField.addEventListener('drop', function(event) {
		       event.preventDefault();
		   }); */
		   
		   // Prevent drag and drop on the entire page
		   document.addEventListener('dragenter', function(event) {
		       event.preventDefault();
		   });
	
		   document.addEventListener('dragover', function(event) {
		       event.preventDefault();
		   });

		   document.addEventListener('drop', function(event) {
		       event.preventDefault();
		   });
		   
	
   	$('select#module').change(function() {
     		
   	 $("#module_name").val($(this).find("option:selected").text());
   	 
		    var mid = this.value; 
		    var sList = new Array();
		    if($("select#module").val() == "-1"){
				document.getElementById("sub_module").disabled = true;
				document.getElementById("screen_name").disabled = true;
			}
		    if($("select#module").val() != "-1"){
		    	document.getElementById("sub_module").disabled = false;
				document.getElementById("screen_name").disabled = false;
			} 
		
				var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
				<c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
					if('${item.module.id}' == mid){
						options += '<option style="text-transform: uppercase;" value="${item.id}">${item.submodule_name}</option>';
					}
				</c:forEach>
				$("select#sub_module").html(options); 
			
		    
	});  
	$('select#sub_module').change(function() {
		 $("#sub_module_name").val($(this).find("option:selected").text());
 	    var mid = this.value; 
 	    var sList = new Array();
 	    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
 		<c:forEach var="item" items="${getScreenList}" varStatus="num" >
 			if('${item.sub_module.id}' == mid){
 				options += '<option style="text-transform: uppercase;" value="${item.id}">${item.screen_name}</option>';
 			}
 		</c:forEach>
 		$("select#screen_name").html(options); 
 	});  
	$("select#screen_name").change(function() {
		$("#screen_name_text").val($(this).find("option:selected").text());	
	});
	
}); 
</Script>
<script>
function isvalidData()
{
	
	 if($("select#module").val() == "0")
		{
			alert("Please Select module");
			$("select#module").focus();
			return false;
		} 
	
	 if($("select#module").val() != "-1"){
		 
		       if($("select#sub_module").val() == "0")
			{
				alert("Please Select Sub Module");
				$("select#sub_module").focus();
				return false;
			}
			if($("select#screen_name").val() == "0")
			{
					alert("Please Select Screen Name");
					$("select#screen_name").focus();
					return false;
			}
		}

	if($("select#help_topic").val() == "0")
	{
			alert("Please Select Help Topic");
			$("select#help_topic").focus();
			return false;
	}
	if($("input#issue_summary").val() == "")
	{
		alert("Please Enter issue summary");
		$("input#issue_summary").focus();
		return false;
	}
	if($("textarea#description").val() == "")
	{
		alert("Please Enter description");
		$("textarea#description").focus();
		return false;
	}
/* 	
	document.getElementById("module_name").value=$("#module option:selected").text();
	document.getElementById("sub_module_name").value=$("#sub_module option:selected").text();
	document.getElementById("screen_name_text").value=$("#screen_name option:selected").text(); */
	return true; 
	
}
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 100
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter").charCounter();
</script>
<Script>
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 1000
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter1").charCounter();

function mms_note(){
	var modle = $("#module").val();
	if(modle == "8"){
		$("#note").show();
		 $("#module").val("0")
	}else{
		$("#note").hide();
	}
	if (modle == "4"){
		 $.post("GetHelpTopicT?"+key+"="+value, function(data) {
         }).done(function(data) {
         	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
         	  if(data.length != 0)
		            {
         			//var length =  data.length-1;
						//var enc =  data[length].substring(0,16);
			 			
			 			for ( var i = 0; i < data.length; i++) {
			 				
			 				
			 				options += '<option value="' + data[i].codevalue +'" >'+ data[i].label + '</option>';
			 				
			 			}	
					
		            }
         	  $("select#help_topic").html(options);
         }).fail(function(xhr, textStatus, errorThrown) {
         });
	}
}
</Script>

<script>

function validateDescription(textarea) {
    const value = textarea.value;
    const maxLength = 1000;
    const regex = /^[a-zA-Z0-9./ ]*$/; 

    
    if (value.includes('\n')) {
        alert("Line breaks are not allowed.");
        textarea.value = value.replace(/\n/g, ''); 
    }

    
    if (/  +/.test(value)) {
        alert("Only one space is allowed at a time.");
        textarea.value = value.replace(/ {2,}/g, ' '); 
    }

    if (!regex.test(value)) {
        alert("Only letters, numbers, '.', and '/' are allowed.");
        textarea.value = value.replace(/[^a-zA-Z0-9./ ]/g, ''); 
    }

   
  /*   const words = value.split(' '); 
    for (let i = 0; i < words.length; i++) {
        if (words[i].length > 15) {
            alert(`Words cannot exceed 15 characters.`);
            words[i] = words[i].substring(0, 15); 
        }
    }
    textarea.value = words.join(' ');  */

    if (value.length > maxLength) {
        alert("Description cannot exceed 1000 characters.");
        textarea.value = value.substring(0, maxLength); 
    }
}

</script>