	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
		
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
		
	<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<style>
.ui-autocomplete{
     z-index: 1111;
}
</style>
<form:form name="add_animal_ue_details" id="add_animal_ue_details" action="add_animal_ue_details_act?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="add_animal_ue_details_cmnd" enctype="multipart/form-data">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5><label for="text-input" id="lbladd" name="lbladd" class=" form-control-label"></label>  ANIMAL UNIT ENTITLEMENT</h5>
			</div>
			<div class="card-body card-block">
					<div class="col-md-6">
						<div class="row form-group">
		   					<div class="col col-md-6">	                					
			                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Select Animal Type</label>
			                </div>
								<div class="form-check-inline form-check">
									<label for="inline-radio1" class="form-check-label ">
		  								<input type="radio" id="anml_type1" name="anml_type" value="Army Dog" class="form-check-input" onclick="dogradio();">Army Dogs
									</label>&nbsp;&nbsp;
									<label for="inline-radio2" class="form-check-label ">
										<input type="radio" id="anml_type2" name="anml_type" value="Army Equines" class="form-check-input" onclick="dogradio1();"> Army Equines
		    						</label>
		  						</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> SUS No
									</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no" class="form-control" autocomplete="off" value="${sus_no1}" maxlength="8">
									<input type="hidden" id="id" name="id"  class="form-control">
								</div>
							</div>
					 </div>
					 	<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name
									</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="unit_name" name="unit_name" class="form-control" autocomplete="off" value="${unit_name1}" maxlength="100">
								</div>
							</div>
						</div> 
					</div>
	
					<div class="col-md-12" id="ueofdog" style="display: none">
						
						 <div class="col-md-6" id="spz" style="display: none;">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Specialisation</label>
								</div>
								<div class="col-12 col-md-8">
								   <input type = "hidden" id = "hdspz" name = "hdspz" > 
									<select id="specialization" name="specialization" class="form-control">
										<option value="0">--Select--</option>	
										<c:forEach var="item" items="${getsplzList}" varStatus="num" >
											<option value="${item[1]}" >${item[0]}</option>
										</c:forEach>
									</select> 
								</div>	
							</div>
						</div>
						<div class="col-md-6" >
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>
										UE of Army Dogs
									</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="ue_of_dogs" name="ue_of_dogs" class="form-control" placeholder="NUMERIC" onkeypress="return validateNumber(event, this)" autocomplete="off" maxlength="5"  value="${ue_of_dogs1}">
								</div>
							</div>
						</div>
					</div>	
					<div class="col-md-12" id="ueofequ" style="display: none">	
						<div class="col-md-6" id="tyequ" >
							<div class="row form-group" >
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Type</label>
								</div>
								<div class="col-12 col-md-8" style="padding-bottom: 40px">
									<select id="type_equines" name="type_equines"  class="form-control">
									<option value="0">--Select--</option>
										<c:forEach var="item" items="${getAnimalTypeList}" varStatus="num" >
											<option value="${item[1]}" >${item[0]}</option>
										</c:forEach>
									</select> 							
								</div>
							</div>
						</div>
						<div class="col-md-6" >
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>
										UE of Army Equines
									</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="ue_of_equins" name="ue_of_equins" class="form-control" placeholder="NUMERIC" onkeypress="return validateNumber(event, this)" autocomplete="off" maxlength="5" value="${ue_of_equins1}">	
								</div>
							</div>
						</div> 
					</div> 
				</div>
		
				<div class="form-control card-footer" align="center">
					<input type="submit" id="svbtn" class="btn btn-primary btn-sm" value="Save" onclick="Validate();">
					<a href="Animal_Ue_Master" type="reset" class="btn btn-success btn-sm"> Clear </a> 
					<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
				</div>
			</div>
	</div>	
</form:form>
	
	<div class="container" id="ArmyDogTbl" style=" display: none; "  >
		 <div id="divShow" style="display: block;">
    </div>
	
	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>
			<table id="getAnimalUESearch" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
				<tr>
					<th width="10%">Ser No</th>
					<th width="20%">SUS No</th>
				    <th width="40%">Specialisation</th>  
					<th width="10%">UE of Army Dogs</th>
					<th width="20%">Action</th>
				</tr>
				</thead>
				<tbody >
				<c:if test="${list.size() == 0}" >
					<tr>
						<td align="center" style="color: red;"> Data Not Available </td>
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num" >
						<tr>
							<td width="10%" style="text-align: center;">${num.index+1}</td> 
							<td width="20%" style="text-align: center;">${item[0]}</td>
							<td width="40%">${item[2]}</td>
							<td width="10%" style="text-align: center;">${item[3]}</td>
							<td width="20%" style="text-align: center;">${item[5]}</td> 
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</div> 
		
<div class="container" id="ArmyEqnsTbl" style=" display: none; "  >
		 <div id="divShow" style="display: block;">
</div>						

<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
		<span id="ip"></span>
			<table id="getAnimalUESearchArmyEqn" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;text-align: center;">
					<tr>
					<th width="10%">Ser No</th>
					<th width="20%">SUS No</th>
					<th width="40%">Type</th>
					<th width="10%">UE of Army Equines</th>
					<th width="20%">Action</th>
				</tr>
				</thead>
				<tbody >
				<c:if test="${listEq.size() == 0}" >
					<tr>
						<td align="center" style="color: red;"> Data Not Available </td>
					</tr>
				</c:if>
				<c:forEach var="item1" items="${listEq}" varStatus="num" >
						<tr>
							<td width="10%" style="text-align: center;">${num.index+1}</td> 
							<td width="20%" style="text-align: center;">${item1[0]}</td>
							<td width="40%" >${item1[2]}</td>
							<td width="10%" style="text-align: center;">${item1[3]}</td>
							<td width="20%" style="text-align: center;">${item1[5]}</td>  
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</div> 
	
<script>

// Unit SUS No

$("#sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  document.getElementById("unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});


//Unit Unit Name
$("#unit_name").keyup(function(){
	var unit_name = this.value;
var unit_nameAuto=$("#unit_name");

unit_nameAuto.autocomplete({
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
                    var dataCountry1 = susval.join("|");
                    var myResponse = []; 
                    var autoTextVal=unit_nameAuto.val();
			$.each(dataCountry1.toString().split("|"), function(i,e){
				var newE = e.substring(0, autoTextVal.length);
				if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
				  myResponse.push(e);
				}
			});      	          
			response( myResponse ); 
          }
        });
      },
      minLength: 1,
      autoFill: true,
     /*  change: function(event, ui) {
    	 if (ui.item) {   	        	  
        	  return true;    	            
          } /* else {
        	  alert("Please Enter Approved Unit Name.");
        	  document.getElementById("sus_no").value="";
        	  unit_nameAuto.val("");	        	  
        	  unit_nameAuto.focus();
        	  return false;	             
          } 	         
      }, */ 
      select: function( event, ui ) {
    	  var target_unit_name = ui.item.value;
	
    		 $.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
    			 target_unit_name:target_unit_name
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#sus_no").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
      } 	     
}); 			
});
</script>
	
<script>
  function validateNumber(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			 if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {  
				  return false;
			} else {
				if (charCode == 46) {
					return false;
				}
			}
			return true; }   
		
	  
	    function Validate() {
		  var anml_type = $('input[name=anml_type]:checked').val();
		  if (anml_type == undefined){
			    $("input#anml_type").focus();
		  		alert("Please Select Animal Type");
		  		return false;
		  	}
			if ($("input#sus_no").val() == "") {
				alert("Please Select Sus No");
				$("input#sus_no").focus();
				return false;
			}
			if ($("input#unit_name").val() == "") {
				alert("Please Select Unit Name");
				$("input#unit_name").focus();              
				return false;
			}
			if (anml_type == "Army Dog") {
				if ($("input#ue_of_dogs").val() == "") {
					alert("Please Enter UE Of Dogs");
					$("input#ue_of_dogs").focus();              
					return false;
				}
				if ($("#specialization").val() == "0") {
					alert("Please Select Specialisation");
					$("#specialization").focus();
					return false;
				}
			}
			if(anml_type == "Army Equines"){
				if ($("input#ue_of_equins").val() == "") {
					alert("Please enter ue of equeins");
					$("input#ue_of_equins").focus();              
					return false;
				}
				if ($("select#type_equines").val() == "0") {
					alert("Please Enter Type");
					$("input#type_equines").focus();              
					return false;
				}
			}
			return true;
	  }  
	
	  $(document).ready(function() {	
			document.getElementById("id").value = 0;
			document.getElementById('lbladd').innerHTML = "ADD ";
			$('input[name="anml_type"]').on('click', function() {
				if ($(this).val() == 'Army Dog') {
					$('#tyequ').hide();
					$('#spz').show();
			}
				else {
					 $('#tyequ').show();
					 $('#spz').hide();
				}
		});
		}); 

		function editData(id,sus,unit,ued,spz,ueEq,eqname){	
			$("#id").val(id);
			document.getElementById('lbladd').innerHTML = "UPDATE";
			var anml_type = $('input[name=anml_type]:checked').val(); 
								
			if (anml_type == "Army Dog") {
				document.getElementById("anml_type1").checked = true;
				 $("#sus_no").val(sus);
				 $("#unit_name").val(unit);
				 $("#ue_of_dogs").val(ued);
				 $("#specialization").val(spz); 
	    }
								
			else {
				document.getElementById("anml_type2").checked = true;
				 $("#sus_no").val(sus);
				 $("#unit_name").val(unit);
				 $("#ue_of_equins").val(ueEq);  
				 $("#type_equines").val(eqname); 
			}
						
		}
		
		function deleteData(id){	
                    $.post("deleteUE?"+key+"="+value, {
                        id : id
                }, function(data) {
                        //        alert(data);
                        //var json = JSON.parse(data);
                        //...

                }).done(function(data) {
                        alert(data);
                
               		 var anml_type = $('input[name=anml_type]:checked').val(); 
				     if (anml_type == undefined){
				  		alert("Please Select Animal Type");
				  	}
						 if (anml_type == "Army Dog"){
						  $('#ArmyEqnsTbl').hide();
						  $('#ArmyDogTbl').show();
						  $("#anml_type3").val(anml_type);
						  $("#sus_no1").val($("#sus_no").val());
					      $("#unit_name1").val($("#unit_name").val());
						  $("#ue_of_dogs1").val($("#ue_of_dogs").val());
						  $("#specialization1").val($("#specialization").val());
						  document.getElementById('searchFormDog').submit();
						}
						else  if (anml_type == "Army Equines") {
							  $('#ArmyEqnsTbl').show();
							  $('#ArmyDogTbl').hide();
							  $("#anml_type4").val(anml_type); 
							  $("#sus_no3").val($("#sus_no").val());
						      $("#unit_name3").val($("#unit_name").val());
						      $("#ue_of_equins1").val($("#ue_of_equins").val());
						      $("#type_equines1").val($("#type_equines").val());
							  document.getElementById('searchFormEqu').submit();
						}
						else{
							$('#ArmyEqnsTbl').hide();
							$('#ArmyDogTbl').hide();
						}
                        
                }).fail(function(xhr, textStatus, errorThrown) {
                });
	}

	

			function Search(){
					 var anml_type = $('input[name=anml_type]:checked').val(); 
				     if (anml_type == undefined){
				  		alert("Please Select Animal Type");
				  	}
						 if (anml_type == "Army Dog"){
						  $('#ArmyEqnsTbl').hide();
						  $('#ArmyDogTbl').show();
						  $("#anml_type3").val(anml_type);
						  $("#sus_no1").val($("#sus_no").val());
					      $("#unit_name1").val($("#unit_name").val());
						  $("#ue_of_dogs1").val($("#ue_of_dogs").val());
						  $("#specialization1").val($("#specialization").val());
						  document.getElementById('searchFormDog').submit();
						}
						else  if (anml_type == "Army Equines") {
							  $('#ArmyEqnsTbl').show();
							  $('#ArmyDogTbl').hide();
							  $("#anml_type4").val(anml_type); 
							  $("#sus_no3").val($("#sus_no").val());
						      $("#unit_name3").val($("#unit_name").val());
						      $("#ue_of_equins1").val($("#ue_of_equins").val());
						      $("#type_equines1").val($("#type_equines").val());
							  document.getElementById('searchFormEqu').submit();
						}
						else{
							$('#ArmyEqnsTbl').hide();
							$('#ArmyDogTbl').hide();
						}
					}
			
				function dogradio()
				{
					$("#sus_no").val('');
					$("#unit_name").val('');
					$("#ue_of_dogs").val('');
					$("#ueofdog").show();
					$("#ueofequ").hide();
				}
				function dogradio1()
				{
					$("#sus_no").val('');
					$("#unit_name").val('');    
					$("#ue_of_equins").val(''); 
					$("#ueofdog").hide();
					$("#ueofequ").show();
				}
		
</script>
	  
	     <c:url value="search_ue_master_dog" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchFormDog" name="searchFormDog" modelAttribute="anml_type3">
			<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
			<input type="hidden" name="anml_type3" id="anml_type3" value="0"/>
			<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>             
			<input type="hidden" name="ue_of_dogs1" id="ue_of_dogs1" value="0"/>
			<input type="hidden" name="specialization1" id="specialization1" value="0"/>
		</form:form> 
		
		
	 	<c:url value="search_ue_master_equ" var="searchUrl" />
		<form:form action="${searchUrl}" method="post" id="searchFormEqu" name="searchFormEqu" modelAttribute="anml_type4">
			<input type="hidden" name="sus_no3" id="sus_no3" value="0"/>
			<input type="hidden" name="anml_type4" id="anml_type4" value="0"/>
			<input type="hidden" name="unit_name3" id="unit_name3" value="0"/>             
			<input type="hidden" name="ue_of_equins1" id="ue_of_equins1" value="0"/>
			<input type="hidden" name="type_equines1" id="type_equines1" value="0"/>
		</form:form>  

<script>
	$(document).ready(function() {
		$("div#divwatermark").val('').addClass('watermarked');								
		watermarkreport(); 
		  if('${anml_type3}' == "Army Dog")
			{
			   document.getElementById(anml_type1.id).checked = true;
			   
			 $("#anml_type").val('${anml_type3}');
				$("#sus_no").val('${sus_no1}');
				$("#unit_name").val('${unit_name1}');
				$("#ue_of_dogs").val('${ue_of_dogs1}');
				$("#specialization").val('${specialization1}');
				$("#ArmyDogTbl").show();
				$("#ueofdog").show();
				$("#spz").show();
				$('#tyequ').hide();
			} 
		  if('${anml_type4}' == "Army Equines")
			{
			    $("#anml_type").val('${anml_type4}');
			 	$("#sus_no").val('${sus_no3}');
				$("#unit_name").val('${unit_name3}');
				$("#type_equines").val('${type_equines1}'); 
				$("#ue_of_equins").val('${ue_of_equins1}');
				$("#ArmyEqnsTbl").show();
				$('#tyequ').show();
				$("#ueofequ").show();
				$("#ueofdog").hide();
				$("#spz").hide();
				document.getElementById(anml_type2.id).checked = true;
				
				
			}   
		  if('${list.size()}' != "" && '${list.size()}' != "0"  ){
					 $("#ArmyDogTbl").show();
					 $("#ueofdog").show();
				     $("#spz").show();
				}
		  else  if('${listEq.size()}' != "" && '${listEq.size()}' != "0"  ){
					 $("#ArmyEqnsTbl").show();
					 $('#tyequ').show();
					 $("#ueofequ").show();
				} 
		  else{
					 $("#tyequ").hide();
					 $("#ueofequ").hide();
					 $("#ueofdog").hide();
				     $("#spz").hide();
				} 
});
</script>
	
	
