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


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>


<form:form name="Edit_census_letter" id="Edit_census_letter" action="Edit_census_letterAction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Edit_censusCMD"> 
		<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Personal Details</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			
	              			<div class="card-header-inner" id="a1" > <strong>Personal Details</strong></div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no5" name="personnel_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Name</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="name" name="name" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              			
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Present PartII Order No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="part2_no" name="part2_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Date of Present Part II Order No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="date_part2_no" name="date_part2_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Offi's Last Part II Order No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="offi_part2_no" name="offi_part2_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Date of Offi's Last Part II Order No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="offi_date_part2_no" name="offi_date_part2_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              				
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Acting/Local Rank</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="acting_rank" name="acting_rank" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Substantive RK</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="sub_rk" name="sub_rk" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Serving Status</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="ser_status" name="ser_status" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Present Appt</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="present_appt" name="present_appt" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Auth letter No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="auth_letter_no" name="auth_letter_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Auth letter No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="part_auth_letter_no" name="part_auth_letter_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              				
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>CDA(O)A/C No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="auth_letter_no" name="auth_letter_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>
	              				</div>
	              				<div class="card-header-inner" id="aa" > <strong>Posting Details</strong></div> 	
	              					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="unit_sus_no5" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" > 
						                   
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Comd</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="comd" name="comd" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Parent Arm/Service </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="parent_arm5" name="parent_arm" class="form-control autocomplete" > 
						                </div>
						            </div>
	              				</div>	               				
	              			</div>
	              				<div class="card-header-inner" id="aa" > <strong>Details</strong></div> 	
	              					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Nature of Cas</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="nature_of_cas" onclick="naturecas();" id="nature_of_cas" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<option value="1" >Posting In</option>
												<option value="2">Marital event</option>
											</select>

						                </div>
						            </div>
	              				</div>	     
	              				</div>	
	              				<div class="col-md-12">	         				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Remarks</label>
						                </div>
						                <div class="col-md-8">
						                   <textarea id="remark" name="remark" class="form-control autocomplete" autocomplete="off" > 
						                </textarea>
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
            	</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Institute" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="return Validation();">
		              
			            </div> 		
	        	</div>
			</div>
	</div>
	
	<input type=hidden id="unit_sus" name="unit_sus">
	<div class="container" id="divPrint" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_census " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center; width:10%;">Ser No</th>
			                         <th style="text-align: center;"> Unit SUS No </th>	
			                         <!-- <th style="text-align: center;"> Parent Arm </th> -->	
			                         <th style="text-align: center;"> Personal No </th>
			                         			                         
			                         <th style="text-align: center; width:30%;">View</th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="4">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
										<td style="font-size: 15px;">${item[0]}</td>	
										<%-- <td style="font-size: 15px;">${item[1]}</td> --%>	
										<td style="font-size: 15px;">${item[2]}</td>										
										<td style="font-size: 15px;text-align: center; width:30%;" >${item[3]} </td> 
									
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> 
</form:form>


<c:url value="GetSearch_Census" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no2">
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0"/>
		<input type="hidden" name="parent_arm1" id="parent_arm1" value="0"/>
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>
		
	</form:form> 

<c:url value="view_censusUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
</form:form>
				
<Script>
$(document).ready(function() {
	
	$("#personnel_no5").val('${Edit_censusCMD.personnel_no}');
	$("#name").val('${Edit_censusCMD.name}');
	$("#parent_arm5").val('${Edit_censusCMD.parent_arm}');
	$("#unit_sus_no5").val('${Edit_censusCMD.unit_sus_no}');	
	
	$("#id").val('${Edit_censusCMD.id}');
	var sus_no ='${Edit_censusCMD.unit_sus_no}';

	getunit(sus_no);
	
	function getunit(val) {	
        $.post("getTargetUnitNameList?"+key+"="+value, {
        	sus_no : sus_no
    }, function(j) {
            //        alert(data);
            //var json = JSON.parse(data);
            //...

    }).done(function(j) {
           // alert(j);
            for (var i = 0; i < j.length; i++) {
				   var length = j.length-1;
	                   var enc = j[length].substring(0,16); 
	                   $("#unit_posted_to").val(dec(enc,j[0])); 
			}
    }).fail(function(xhr, textStatus, errorThrown) {
    });
} 
	//$().getCurDt(date_of_commission); 
	
	 if('${list.size()}' == ""){
		   $("div#getSearch_census").hide();
		}	
		$("#unit_sus_no").val('${unit_sus_no2}');
		$("#parent_arm").val('${parent_arm1}');
		$("#personnel_no").val('${personnel_no1}');
		
		

		var q = '${unit_sus_no2}';
		
		if(q != ""){
			$("#unit_sus_no").val(q);
		}
		 
		var q1 = '${parent_arm1}';
		
		if(q1 != ""){
			$("#parent_arm").val(q1);
		}
		var q2 = '${personnel_no1}';
		
		if(q2 != ""){
			$("#personnel_no").val(q2);
		}
		
		
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
});

function Search(){

	$("#unit_sus_no2").val($("#unit_sus_no").val()) ;	
	$("#parent_arm1").val($("#parent_arm").val()) ;	
	$("#personnel_no1").val($("#personnel_no").val()) ;		
	document.getElementById('searchForm').submit();
	
}

 function ViewData(id){	
	alert(id);
	$("#id2").val(id);
	document.getElementById('ViewForm').submit();
		 
} 

	 function naturecas() {

		    var post = document.getElementById("nature_of_cas");

		    if (post.value == "1"){

		        alert("You clicked Post In.");
		        window.location = "postin";
		       
		    }
		}
	</script>

<script>

//Unit SUS No

$("#unit_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#unit_sus_no");

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
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_posted_to").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

// unit name
 $("input#unit_posted_to").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_posted_to");
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
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name.");
			        	  document.getElementById("unit_posted_to").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});


 $("input#parent_arm").keypress(function(){
	 
		var arm_desc = this.value;
			 var susNoAuto=$("#parent_arm");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getParentArmList?"+key+"="+value,
			        data: {arm_desc:arm_desc},
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
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Parent Arm.");
			        	  document.getElementById("parent_arm").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var target_unit_name = ui.item.value;
			    
				           /*  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            }); */
			      } 	     
			}); 			
	});
</script>

