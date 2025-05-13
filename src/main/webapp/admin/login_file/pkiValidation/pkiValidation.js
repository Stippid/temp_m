var timeOut = 0;
function getCheckPIKValidation()
{
	//for Test
	jQuery.ajax({
	    type: "POST",
	    url: "checkTest?"+key+"="+value,
	    contentType: false,
		cache: false,
		processData : false,
		success: function (result) {
			if(result == "Success"){
				window.location.href = 'admin/commonDashboard';
			}else{
				alert(result);
			}
		}
	});
	// for test
	
	/*//without JNLP
	document.getElementById("b1").click();
	jQuery("#TokenLoader").show();
	jQuery("#jnlpButton").attr("disabled", true);
	document.getElementById("getDownloadJarForm").submit();*/
}
function run(){
	//alert("Run Jar");
	jQuery.ajax({
		type: 'GET',
		url: "http://localhost:8089",
		success: function( data ,textstatus ,jqXHR) {
			console.log(data);
			var fd = new FormData();
			var myblob = new Blob([data])
	   		fd.append("doc", myblob);
			jQuery.ajax({
	   		    type: "POST",
	   		    data: fd,
	   		    url: "uploadDoc?"+key+"="+value,
	   		    contentType: false,
	   			cache: false,
	   			processData : false,
	   			success: function (result) {
	   				jQuery("#TokenLoader").hide();
	   				jQuery("#jnlpButton").attr("disabled", false);
	   				if(result == "Success"){
	   					window.location.href = 'admin/commonDashboard';
	   				}else{
	   					alert(result);
	   				}
	   			},
	   			error: function (req, err) {
	   				alert("DataBase Server side Connection Error");
	   			}
			}); 
		},
		error: function( jqXHR, textStatus,errorThrown){
			button = jQuery('#b1');
			button.delay(5000).queue(function(){
				jQuery(this).click().dequeue();
			});
		}
	});
}

function getCheckPIKValidationForRegistration()
{
	document.getElementById("b1").click();
	document.getElementById("getDownloadJarForm").submit();
}
function runForRegistration(){
	jQuery.ajax({
		type: 'GET',
		url: "http://localhost:8089",
		success: function( data ,textstatus ,jqXHR) {
			console.log(data);
			var fd = new FormData();
			var myblob = new Blob([data])
	   		fd.append("doc", myblob);
			fd.append("Army_no_field", $("input#army_no").val().trim());
			jQuery.ajax({
	   		    type: "POST",
	   		    data: fd,
	   		    url: "ocspCheckRegistrationDetails?"+key+"="+value,
	   		    contentType: false,
	   			cache: false,
	   			processData : false,
	   			success: function (result) {
	   				if(result == "Success"){
	   					var form = jQuery('#user_regitrastion')[0];
	   					var data = new FormData(form);
	   					$.ajax({
	   						url : 'user_registration_action?' + key + "=" + value,
	   						type : "POST",
	   						data : data,
	   						enctype : 'multipart/form-data',
	   						processData : false,
	   						contentType : false
	   					}).done(function(data) {
	   						if (parseInt(data) > 0) {
	   							alert("Thank you! \n Your submission is received and we will contact you soon.");
	   							window.location.href='login';
	   						} else{
	   							alert(data)
	   						}
	   					}).fail(function(jqXHR, textStatus) {
	   						alert("No Transaction Made");
	   					});
	   				}else{
	   					alert(result);
	   				}
	   			},
	   			error: function (req, err) {
	   				alert("DataBase Server side Connection Error");
	   			}
			}); 
		},
		error: function( jqXHR, textStatus, errorThrown ){
			button = jQuery('#b1');
			button.delay(5000).queue(function(){
				jQuery(this).click().dequeue();
			});
		}
	});
}