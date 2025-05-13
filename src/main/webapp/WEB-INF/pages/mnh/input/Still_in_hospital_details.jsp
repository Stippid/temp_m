<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<style>
div.dataTables_wrapper{
   margin: 0 auto;
}
</style>    

<form:form action="SearchOffi_Admi_AmcAdcMnsAction" id="SearchOffi_Admi_AmcAdcMns" method="post" class="form-horizontal" commandName="SearchOffi_Admi_AmcAdcMnsCMD">

      <div class="container" align="center">
      <div class="card">
              <div class="card-header"  align="center">
		             <h5>STILL IN HOSPITAL DETAILS</h5>
		             <h6>
						<span style="font-size: 12px; color: red">(To be entered by Medical Unit)</span>
					</h6>
		      </div>  
		      
		      	<div class="card-body card-block">
				  <div class="row">
					<div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-9">
	               		      <input type="hidden" id="id" name="id" value="0">	
	               			  <input type="text" id="unit_name" name ="unit_name" value="${unit_name}"class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						 </div>	 
						  </div>	 
						   </div>	 
							<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="text" id="sus_no" name="sus_no" value="${sus_no}"class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search" maxlength="8"/>
	               		 </div>  
  					</div> 
  					 </div>	 
  					  </div>	 
  					
  					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>From Date</label>
	               		 </div>
	               		 <div class="col-md-8"> 
	               			  <input type="date" id="frm_dt" name ="frm_dt" class="form-control-sm form-control" autocomplete="off" min="1899-01-01" max="${date}">
						 </div>	 
						 </div>	 
						 </div>	 
						 
						 <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4"> 
	               			  <label class=" form-control-label"> <strong style="color: red;">* </strong>To Date</label>
	             		 </div>
	             		<div class="col-md-8">
	             			  <input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control" onchange="checkDate();"  autocomplete="off" min="1899-01-01" max="${date}">
	               		 </div>  
  					</div> 
		      </div>
		    </div>
		    			<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Personnel
										No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="persnl_no" name="persnl_no"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">And No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="and_no" name="and_no"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type SUS No or Part of SUS No to Search" />
								</div>
							</div>
						</div>
					</div>
		     </div>
		      </div>
		      <div class="card-footer" align="center">
			        <a href="STILL_HOSPITAL_DTLS"  class="btn btn-primary btn-sm" >Clear</a> 
		             <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search" onclick="return Search();" />      
              </div> 
            </div> 
          </div> 
              
                       
                 <div class="container-fluid" id="divPrint" style="display: none;" >
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px; width:50%;" placeholder="Search Word"  size="35" class="form-control">			
	 
		                       <div  class="watermarked" data-watermark="" id="divwatermark" ><span id="ip"></span> 				 
				             <table id="DataReport" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead >
		                         <tr style="text-align: center;">
			                         <th style ="width:5%;">Ser No</th>
										<th>Personnel No</th>
										<th>Patient Name</th>
										<th>Relation</th>
										<th>Adimission Type</th>
										<th>A&D No</th>
										<th>Discharge Date</th>
										<th>Disposal</th>
										<th>Discharge Diagnosis</th>
										<th>Discharge ICD Code</th>
										<th>Discharge ICD Cause</th>
										<th>Discharge Remarks</th>
										<th>Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
											 <td style ="width:5%;"> ${num.index+1}</td>
									          <td>${item.persnl_no}</td>
									          <td>${item.name}</td>
									          <td>${item.relationship}</td>
									          <td>${item.admsn_type}</td>
									          <td>${item.and_no}</td>
									          <td>
									          <input type="date" id="dschrg_date${item.id}" class="form-control-sm form-control"
									          name="dschrg_date${item.id}" value="${item.dschrg_date}">
									          </td>
									          <td>
									          <select id="disposal${num.index+1}" name="disposal${num.index+1}" 
									          class="form-control-sm form-control" ></select>
									          </td>
									      	  <td>
									          <input type="text" id="icd_remarks_d${item.id}"class="form-control-sm form-control"
									           name="icd_remarks_d${item.id}" value="${item.icd_remarks_d}" >
									          </td>
									 	          	<td>
									          	<input type="text" id="a_rem${item.id}"
								name="a_rem${item.id}" onkeypress="a_remAuto(this);" onchange="a_remonchge(this);" autocomplete="off" value="${item.diagnosis_code1d}" class="form-control-sm form-control">
							</td>
							<td>
							<input type="text" id="d_rem${item.id}"
								name="d_rem${item.id}" autocomplete="off" onkeypress="d_remAuto(this);"  class="form-control-sm form-control" value="${item.icd_cause_code1d}" readonly>
							</td>
									          <td>
									          <input type="text" id="discharge_remarks${item.id}" class="form-control-sm form-control"
									          name="discharge_remarks${item.id}" value="${item.discharge_remarks}">
									          </td>
										 <td id="thAction1" style="font-size: 13px; text-align: center;" width="8%" onclick="edit();">${item.edit}</td>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                     <c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
		                 </table>
		             </div>
		        </div>           
</form:form>

<c:url value="search_Still_Hospdtls" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus1">
		<input type="hidden" name="sus1" id="sus1"/>
		<input type="hidden" name="frm_dt1" id="frm_dt1"/>
		<input type="hidden" name="to_dt1" id="to_dt1"/>
		<input type="hidden" name="unit1" id="unit1"/>
		<input type="hidden" name="persnl_no1" id="persnl_no1"/>
		<input type="hidden" name="and_no1" id="and_no1"/>
</form:form> 


<c:url value="edit_still_hosdtl" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="hdid">
		<!-- <input type="hidden" name="unit1" id="unit1"/> -->
	  <input type="hidden" name="hdid" id="hdid">
	  <input type="hidden" name="hddschrg_date" id="hddschrg_date">
	  
	  <input type="hidden" name="sus2" id="sus2"/>
	  <input type="hidden" name="from_date2" id="from_date2"/>
	  <input type="hidden" name="to_date2" id="to_date2"/>	
	  
	  <input type="hidden" name="hddisposal" id="hddisposal">
	  <input type="hidden" name="hdicd_remarks_d" id="hdicd_remarks_d">
	  <input type="hidden" name="a_rem" id="a_rem">
	  <input type="hidden" name="d_rem" id="d_rem">
	  <input type="hidden" name="hddischarge_remarks" id="hddischarge_remarks">
</form:form> 
		
  

<!-- for Functions -->
<script>


function editdata(id){
	$("#hdid").val(id);
	
	if($("#dschrg_date"+id).val() ==""){
		alert("Please Select Discharge Date");
		$('#dschrg_date' + id).focus();
		return false;
		}
		
		if($("#disposal"+id).val() == "0"){
			alert("Please Select Disposal");
			$('#disposal' + id).focus();
			return false;
			}
		if($("#icd_remarks_d"+id).val() == ""){
			alert("Please Enter Discharge Diagnosis");
			$('#icd_remarks_d' + id).focus();
			return false;
			}
		if($("#a_rem"+id).val() == ""){
			alert("Please Select Discharge ICD Code");
			$('#a_rem' + id).focus();
			return false;
			}

		var b_1 = $("#a_rem"+id).val();
		var b_2 = b_1[0].substring(0,1).toUpperCase();
		
	   if(b_2 == "S" || b_2== "T" || b_2 == "V" || b_2 == "W" || b_2 == "X" || b_2 == "Y"){
		
		   if($("#d_rem"+id).val() == ""){
			alert("Please Select Discharge ICD Cause");
			$('#d_rem' + id).focus();
			return false;
			}
	   } 
		if($("#discharge_remarks"+id).val() == ""){
			alert("Please Enter Discharge Remarks");
			$('#discharge_remarks' + id).focus();
			return false;
			}
	
	$("#hddschrg_date").val($("#dschrg_date"+id).val());
	$("#sus2").val($("#sus_no1").val()); 
	$("#from_date2").val($("#from_date").val());
	$("#to_date2").val($("#to_date").val());
	$("#hddisposal").val($("#disposal"+id).val());
	$("#hdicd_remarks_d").val($("#icd_remarks_d"+id).val());
	 
	$("#a_rem").val($("input#a_rem"+id).val());
	$("#d_rem").val($("input#d_rem"+id).val());
	$("#hddischarge_remarks").val($("#discharge_remarks"+id).val());
	
	$("#updateForm").submit();
}

function checkDate(){   
	  var startDate = document.getElementById("frm_dt").value;
	  var endDate = document.getElementById("to_dt").value;
	
	  if ((Date.parse(endDate) <= Date.parse(startDate))) {
	        alert("Effective To date should be greater than Effective From date");
	        document.getElementById("to_dt").value = "";
	    }
}
function Search(){
	if ($("#unit_name").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name").focus();
		return false;
	}
   if ($("#sus_no").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no").focus();
		return false;
	}
	if($("#frm_dt").val() == "") {
		alert("Please Select From Date");
		$("#frm_dt").focus();
		return false;
	}
	
	if($("#to_dt").val() == "") {
		alert("Please Select To Date");
		$("#to_dt").focus();
		return false;
	}
	 var startDate = document.getElementById("frm_dt").value;
	  var endDate = document.getElementById("to_dt").value;
	
	  if ((Date.parse(endDate) <= Date.parse(startDate))) {
	        alert("Effective To date should be greater than Effective From date");
	        document.getElementById("to_dt").value = "";
	        $("#to_dt").focus();
			return false;
	    }
	
	$("#sus1").val($("#sus_no").val());
	$("#unit1").val($("#unit_name").val());
	$("#frm_dt1").val($("#frm_dt").val());
	$("#to_dt1").val($("#to_dt").val());
	$("#searchForm").submit();
}


var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";

jQuery(function() {
	// Source SUS No
	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});
</script>
<script>








function a_remAuto(obj) {
	
	var check_id = obj.id;
	var k = check_id.replace('a_rem','');
	
	// Part No AutoComplete
	var wepetext=$("#"+obj.id);
    wepetext.autocomplete({
        source: function( request, response ) {
          $.ajax({
          type: 'POST',
          url: "getMNHDistinctICDList?"+key+"="+value,
          data: {a:$("#"+obj.id).val(),b:"ALL"},
            success: function( data ) { 
                      var codeval = [];
                      var length = data.length-1;
                      var enc = data[length].substring(0,16);
                          for(var i = 0;i<data.length;i++){
                                  codeval.push(dec(enc,data[i]));
                          } 
                          var dataCountry1 = codeval.join("|");
                          var myResponse = []; 
                      var autoTextVal=wepetext.val();
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
                    alert("Please Enter Approved Diagnosis Code"); 
                    return false;                     
            }                    
        }, 
        select: function( event, ui ) {
        	var nom = ui.item.value;
        	
             
             } 
      });
}

function  a_remonchge(obj) {
	
		var check_id = obj.id;
		
		var k = check_id.replace('a_rem','');
		
		
		var b_1 = $("#a_rem"+k).val().split("-");
		var b_2 = b_1[0].substring(0,1).toUpperCase();
		
	   if(b_2 == "S" || b_2== "T" || b_2 == "V" || b_2 == "W" || b_2 == "X" || b_2 == "Y"){
		
		$("#d_rem"+k).attr('readonly',false);
			
	   }
	
	else{
		$("#d_rem"+k).attr('readonly',true);
		$("#d_rem"+k).val('');
	}
}


function d_remAuto(obj) {
	
	var check_id = obj.id;
	var k = check_id.replace('d_rem','');
	var wepetext=$("#"+obj.id);
	
	var b_1 = $("#a_rem"+k).val().split("-");
	var b_2 = b_1[0].substring(0,1).toUpperCase();

if (b_2 == "S" || b_2 == "T" || b_2 == "X" || b_2 == "V" || b_2 == "W" || b_2 == "Y") 
	{


	 wepetext.autocomplete({
        source: function( request, response ) {
          $.ajax({
          type: 'POST',
          url: "getMNHDistinctICDList?"+key+"="+value,
          data: {a:$("#"+obj.id).val(),b:"ALL"},
            success: function( data ) { 
                      var codeval = [];
                      var length = data.length-1;
                      var enc = data[length].substring(0,16);
                          for(var i = 0;i<data.length;i++){
                                  codeval.push(dec(enc,data[i]));
                          } 
                          var dataCountry1 = codeval.join("|");
                          var myResponse = []; 
                      var autoTextVal=wepetext.val();
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
                    alert("Please Enter Approved Diagnosis Cause"); 
                    return false;                     
            }                    
        }, 
        select: function( event, ui ) {
        	var nom = ui.item.value;
        	
             
             } 
      });
	}  

}

</script>
<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	$().getCurDt(to_dt); 
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#DataReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	if('${size}' != 0){
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
		
	}
	
			var q = '${sus1}';
			if(q != ""){ 
				$("#sus_no").val(q);
			}
			
			var q1 = '${unit1}';
			if(q1 != ""){ 
				$("#unit_name").val(q1);
			}
			
			var q2 = '${frm_dt1}';
			if(q2 != ""){ 
				$("#frm_dt").val(q2);
			}
			
			var q3 = '${to_dt1}';
			if(q3 != ""){ 
				$("#to_dt").val(q3);
			}
			


	
	var size= parseInt('${size}');

	 if(size != 0)
	{
		 var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
			<c:forEach var="item" items="${DISPOSAL_list}" varStatus="num" >
				options += '<option value="${item}">${item}</option>';
			</c:forEach>
		for(i=1;i<=size;i++)
		{
			
			$("select#disposal"+i).html(options);
		}
	} 
});
</script>