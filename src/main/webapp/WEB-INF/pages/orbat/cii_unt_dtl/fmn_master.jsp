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

<form:form name="fmnmaster" id="fmnmaster"
	action="fmnmasterAction?${_csrf.parameterName}=${_csrf.token}"
	method='POST' commandName="fmnmasterCMD" enctype="multipart/form-data">

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					<h5>
						<b>CREATION OF FORMATION</b>
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
										<input type="text" id="unit_name1" name="unit_name1"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextField(1);" onblur="getOrbatDetailsFromUnitName(this.value);">
									</div>
								</div>
							</div>
                             <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 1 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no1" name="sus_no1"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(1);" onblur="getOrbatDetailsFromUnitName();">
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
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextField(2);" />
									</div>
								</div>
							</div>
							
                          <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 2 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no2" name="sus_no2"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(2);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(3);">
										</div>
									</div>
								</div>

							
                          <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 3 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no3" name="sus_no3"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(3);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(4);">
										</div>
									</div>
								</div>

						

                        <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 4 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no4" name="sus_no4"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(4);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(5);">
										</div>
									</div>
								</div>
                              <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 5 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no5" name="sus_no5"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(5);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(6);">
										</div>
									</div>
								</div>

							


                            <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 6 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no6" name="sus_no6"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(6);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(7);">
										</div>
									</div>
								</div>
							
                            <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 7 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no7" name="sus_no7"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(7);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(8);">
										</div>
									</div>
								</div>

							 <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 8 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no8" name="sus_no8"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(8);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(9);">
										</div>
									</div>
								</div>

							

                                  <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 9 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no9" name="sus_no9"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(9);">
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
												placeholder="&#xF002; Search Unit Name"
												class="form-control autocomplete" autocomplete="off"
												onkeyup="enableNextField(10);">
										</div>
									</div>
								</div>

							


                        <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Level 10 SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no10" name="sus_no10"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off"
											onkeyup="enableNextFieldsus(10);">
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
												placeholder="&#xF002; Enter Form code"
												class="form-control autocomplete" autocomplete="off"
												readonly>
										</div>
									</div>
								</div>
							</div>



					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">
						<input type="submit" class="btn btn-primary btn-sm" value="Save">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

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




<script>
 	   
    var Opcom,Opcorps,Opdiv,Opbde,Contcom,Contcorps,Contdiv,Contbde,Admincom,Admincorps,Admindiv,Adminbde,ParentArm,TypeOfArm;
  
  	function getOrbatDetailsFromUnitName(unitName){
  		var unitName = document.getElementById("unit_name1").value;
  		console.log(unitName);
		$.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
	       	if(j.length > 0) {
	        	var length = j.length-1;
	 			var enc = j[length].substring(0,16);
	 			
	      	 	var Arm = dec(enc,j[1]);
	      	 	
	       		ParentArm = Arm[0] + Arm[1]; 
	 	   		TypeOfArm = Arm[0] + Arm[1] + Arm[2] + Arm[3]; 
	 	        	 
		   		$("#code").val(dec(enc,j[8]));
		   		$("#loc").val(dec(enc,j[9]));
		   		$("#nrs_code").val(dec(enc,j[10]));
		   		$("#nrs_name").val(dec(enc,j[11]));
		   		$('#type_of_location').val(dec(enc,j[12]));
		    	$('#modification').val(dec(enc,j[13]));
			    
	 			$('#arm_code').val(dec(enc,j[7]));
	   	    	$("select#arm_name").val(dec(enc,j[7]));
	   	    	$("select#type_force").val(dec(enc,j[14]));
	   	    	updateFormCode();
	   	    }else{
	   			
	   		}
		});
	}
  	
  	</script>
