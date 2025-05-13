<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form  action="fmnmasterEditAction" method='POST' commandName="fmnmasterEditCMD" >

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					<h5>
						<b>Updation Of Formation</b>
					</h5>
					<div class="card-header">
						<strong></strong>
					</div>


					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong> </strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 1</label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="id" name="id" class="form-control" value="${fmnmasterEditCMD.id}">
										<input type="text" id="unit_name1" name="unit_name1"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level1}"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextField(1);">
									</div>
								</div>
							</div>
						</div>
					
              
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 2</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name2" name="unit_name2"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level2}"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextField(2);" />
									</div>
								</div>
							</div>
						</div>

                            
						<!-- Level 3 -->

						
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 3</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name3" name="unit_name3"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level3}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(3);">
										</div>
									</div>
								</div>
                         </div>


							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 4</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name4" name="unit_name4"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level4}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(4);">
										</div>
									</div>
								</div>
						</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 5</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name5" name="unit_name5"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level5}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(5);">
										</div>
									</div>
								</div>
                              </div>


							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Level 6</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name6" name="unit_name6"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level6}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(6);">
										</div>
									</div>
								</div>
							</div>
                    <div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 7</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name7" name="unit_name7"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level7}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(7);">
										</div>
									</div>
								</div>
							</div>
                           <div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 8</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name8" name="unit_name8"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level8}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(8);">
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 9</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name9" name="unit_name9"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level9}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(9);">
										</div>
									</div>
								</div>
                             </div>

						<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Level 10</label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="unit_name10" name="unit_name10"
												maxlength="100" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Search Unit Name" value="${fmnmasterEditCMD.level10}"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(10);">
										</div>
									</div>
								</div>
              			</div>
                    
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong> Arm Name</label>
										</div>
										<div class="col-12 col-md-8">
											<select id="arm_name" name="arm_name" class="form-control"
												onchange="updateFormCode()">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getArmNameList}"
													varStatus="num">
													<option value="${item.arm_code}">${item.arm_code}
														- ${item.arm_desc}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Formation Code </label>
										</div>
										<div class="col-12 col-md-8">
											<input type="text" id="form_code" name="form_code"
												maxlength="8" style="font-family: 'FontAwesome', Arial;"
												placeholder="&#xF002; Enter Form code" value="${fmnmasterEditCMD.fmn_code}"
												class="form-control autocomplete" autocomplete="off"
												readonly>
										</div>
									</div>
								</div>
							</div>



					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return checkValidation();">
	              		<a href="search_fmn_master" type="reset" class="btn btn-danger btn-sm"> Cancel </a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</form:form>

<script>
      $(document).ready(function() {  
    	  
    	  if($("input#unit_name1").val()==""){
    		  document.getElementById("unit_name1").readOnly = true;
  			}
    	  
    	  if($("input#unit_name2").val()==""){
    		  document.getElementById("unit_name2").readOnly = true;
  			}
    	  
    	  if($("input#unit_name3").val()==""){
    		  document.getElementById("unit_name3").readOnly = true;
  			}
    	  
    	  if($("input#unit_name4").val()==""){
    		  document.getElementById("unit_name4").readOnly = true;
  			}
    	  
    	  if($("input#unit_name5").val()==""){
    		  document.getElementById("unit_name5").readOnly = true;
  			}
    	  
    	  if($("input#unit_name5").val()==""){
    		  document.getElementById("unit_name5").readOnly = true;
  			}
    	  
    	  if($("input#unit_name6").val()==""){
    		  document.getElementById("unit_name6").readOnly = true;
  			}
    	  
    	  if($("input#unit_name7").val()==""){
    		  document.getElementById("unit_name7").readOnly = true;
  			}
    	  
    	  if($("input#unit_name8").val()==""){
    		  document.getElementById("unit_name8").readOnly = true;
  			}
    	  
    	  if($("input#unit_name9").val()==""){
    		  document.getElementById("unit_name9").readOnly = true;
  			}
    	  
    	  if($("input#unit_name10").val()==""){
    		  document.getElementById("unit_name10").readOnly = true;
  			}
    	  
    	  
    	 
    	});
      
   function handleInput(currentLabelId){
	   
	   var labels = []
	   
	   for (var i = 1; i<= 10 ; i++){
		   
		   labels.push(document.getElementById("unit_name" + i));
		   
	   }
	   
	   var currentIndex = parseInt(currentLabelId.match(/\d+/)[0]);
	   
	   for (var i = 0; i<labels.length ; i++){
		   
		   if(i < currentIndex){
			   
			   labels[i].removeAttribute("readonly");
		   }
		   
		   else {
			   labels[i].setAttribute("readonly","readonly");
		   }
	   }
   }   
      
</script>


<script>
       $(document).ready(function() {
    	   
    	   
    	   $("select#arm_name").val('${fmnmasterEditCMD.arm_code}');
    	   
    	  
    	   
    	    
  
    	    
    	
    	 
    	    //***********************END*************************************************************************//
    	    
    	    $('#unit_name').change(function() {
   	        	var unitName = this.value;
   	        	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unitName}, function(j) {
   	        		if(j.length != 0){
   	        			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#sus_no").val(dec(enc,j[0][0]));
   	        		}
   	        	});
   	    	});      
       });
       
       
       /* //////////////////////////////////DETAIL OF UNIT/////////////////////////////////////////////////////////////// */
	    
	   
      
     
    </script>
    
    <script>
    
    function checkValidation() {
		if($("input#unit_name1").val()==""){
			alert("Please Enter Level 1")
			$("input#unit_name1").focus();
			return false;
		}
		if($("input#arm_name").val()==""){
			alert("Please Enter LOC Code")
			$("input#arm_name").focus();
			return false;
		}
		if($("input#form_code").val()==""){
			alert("Please Enter FMN Code")
			$("input#form_code").focus();
			return false;
		}
		if($("select#arm_name").val()=="0"){
			alert("Please Select Arm name")
			$("input#arm_name").focus();
			return false;
		}
		
		return true;
	}
    
    </script>

<script>

$('input[id^="sus_no"]').each(function() {
	  var susNoField = $(this);
	  var currentIndex = susNoField.attr('id').replace('sus_no', '');
	  var unitnameField = document.getElementById('unit_name' + currentIndex);

	  susNoField.autocomplete({
	    source: function(request, response) {
	      $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?" + key + "=" + value,
	        data: { sus_no: susNoField.val() },
	        success: function(data) {
	          var susval = [];
	          var length = data.length - 1;
	          var enc = data[length].substring(0, 16);
	          for (var i = 0; i < data.length; i++) {
	            susval.push(dec(enc, data[i]));
	          }
	          var dataCountry1 = susval.join("|");
	          var myResponse = [];
	          $.each(dataCountry1.toString().split("|"), function(i, e) {
	            var newE = e.substring(0, susNoField.val().length);
	            if (e.toLowerCase().includes(susNoField.val().toLowerCase())) {
	              myResponse.push(e);
	            }
	          });
	          response(myResponse);
	        }
	      });
	    },
	    minLength: 1,
	    autoFill: true,
	    select: function(event, ui) {
	      var sus_no = ui.item.value;
	      $.post("getTargetUnitNameList?" + key + "=" + value, {
	        sus_no: sus_no
	      }, function(j) {
	        var length = j.length - 1;
	        var enc = j[length].substring(0, 16);
	        unitnameField.value = dec(enc, j[0]);
	      }).fail(function(xhr, textStatus, errorThrown) {
	      });
	    }
	  });
	});

// unit name
$('input[id^="unit_name"]').keypress(function() {
		var unit_name = this.value;
			 var susNoAuto=$('input[id^="unit_name"]');
			 var currentIndex = this.id.replace('unit_name', '');
			  var susNoField = document.getElementById('sus_no' + currentIndex);
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      
			      select: function( event, ui ) {
			    	  var unit_name = ui.item.value;
			          $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
			          }).done(function(data) {
			            var length = data.length-1;
			            var enc = data[length].substring(0,16);
			            susNoField.value = dec(enc,data[0]);
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});

	function enableNextField(currentIndex) {

		var currentField = document.getElementById("unit_name" + currentIndex);
		var nextField = document.getElementById("unit_name"
				+ (currentIndex + 1));
		
		var nextField_sus = document.getElementById("sus_no"
				+ (currentIndex + 1));

		if (currentField.value.trim() !== "") {

			nextField.readOnly = false;
			nextField_sus.readOnly = false;
		} else {
			for (var i = currentIndex + 1; i <= 10; i++) {
				document.getElementById("unit_name" + i).value = "";
				document.getElementById("unit_name" + i).readOnly = true;
				
				document.getElementById("sus_no" + i).value = "";
				document.getElementById("sus_no" + i).readOnly = true;
			}
		}
	}
	
	function enableNextFieldsus(currentIndex) {

		var currentField = document.getElementById("sus_no" + currentIndex);
		var nextField = document.getElementById("sus_no"
				+ (currentIndex + 1));
		
		var nextField_unit = document.getElementById("unit_name"
				+ (currentIndex + 1));

		if (currentField.value.trim() !== "") {

			nextField.readOnly = false;
			nextField_unit.readOnly = false;
		} else {
			for (var i = currentIndex + 1; i <= 10; i++) {
				document.getElementById("sus_no" + i).value = "";
				document.getElementById("sus_no" + i).readOnly = true;
				
				document.getElementById("unit_name" + i).value = "";
				document.getElementById("unit_name" + i).readOnly = true;
			}
		}
	}

	/* window.onload = function() {

		document.getElementById("unit_name1").readOnly = false;

		for (var i = 2; i <= 10; i++) {
			document.getElementById("unit_name" + i).readOnly = true;
		}
	}

	window.onload = function() {

		document.getElementById("unit_name1").readOnly = false;

		for (var i = 2; i <= 10; i++) {
			document.getElementById("unit_name" + i).readOnly = true;
		}
	} */
	
	function updateFormCode() {
		var armName = document.getElementById("arm_name").value;

		var url = "create_fmncode?arm_name=" + encodeURIComponent(armName);

		$.ajax({
			url : url,
			type : "GET",
			success : function(formCode) {
				document.getElementById("form_code").value = formCode;
			},
			error : function(xhr, status, error) {
				console.error('Error updating form code:', error);
			}
		});
	}
	
	window.onload = function() {
		  for (var i = 1; i <= 10; i++) {
		    document.getElementById("sus_no" + i).readOnly = true;
		    document.getElementById("unit_name" + i).readOnly = true;
		  }
		  document.getElementById("unit_name1").readOnly = false;
		  document.getElementById("sus_no1").readOnly = false;
		}
</script>