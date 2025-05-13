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
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<form:form name="Search_view_update_off" id="Search_view_update_off" action="Search_view_update_off_Action" method="post" class="form-horizontal" commandName="Search_view_update_off_CMD"> 
	<div class="animated fadeIn">
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> SERVICE RECORD </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
	          			<div class="card-body card-block">
	            	
	            	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" maxlength="8" 
						                 onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"
						                 autocomplete="off" style="display: none"> 
						               
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)" style="display: none">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
										</div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="name" name="name" class="form-control autocomplete" maxlength="50" autocomplete="off"  onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"> 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              					
	              				
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> RANK </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="rank" id="rank" class="form-control  form-control"   >
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>  --%>
											</select> 
						                </div>
						            </div>
	              				</div>	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year of Commission </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="year_of_comm" name="year_of_comm" class="form-control autocomplete"  maxlength = "4" onkeypress="return isNumber(event)" 
						                   onclick="return AvoidSpace(event)" autocomplete="off" onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>              				
	              			</div>
	              			<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year of Birth </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="year_of_dob" name="year_of_dob" class="form-control autocomplete"  maxlength = "4" onkeypress="return isNumber(event)" 
						                   onclick="return AvoidSpace(event)" autocomplete="off" onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>              					
						         <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Parent Arm </label>
						                </div>
						                <div class="col-md-8">
						                <select name="parent_arm" id="parent_arm" class="form-control"  >
						                        <option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getParentArmList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select> 
					                      
						                </div>
						            </div>
	              				</div>
	              				</div>	
	              				
	              				<div class="col-md-12">	              					
	              				
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Status </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="comm_status" id="comm_status" class="form-control  form-control"   >
												<!-- <option value="0">--Select--</option> -->
												<option value="1">Serving</option>
												<option value="4">Non Effective</option>
												
											</select> 
						                </div>
						            </div>
	              				</div>		              				      				
	              			</div>             					              				
	              		</div>	              				              			              				              			
            		</div>									
				<div class="card-footer" align="center" class="form-control">
					<a href="View_UpdateOfficerData_mnsUrl" class="btn btn-success btn-sm" >Clear</a>  
              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
	            </div> 		        	
	</div> 			
	<input type=hidden id="unit_sus" name="unit_sus">	 
	 <div class="col-md-12" >
		<div class="">
		  <div id="divPrint" style="display: block;"></div> 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_census" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>		                       
	                       		  <th style="text-align: center;">Ser No</th>
		                          <th style="text-align: center;"> Cadet No </th>	
		                          <th style="text-align: center;"> Personal No </th>		                        
		                          <th style="text-align: center;"> Rank </th> 		                          
		                          <th style="text-align: center;"> Name </th> 
		                          <th style="text-align: center;"> Unit Name </th> 	
		                          <th style="text-align: center;">Action</th> 	                       
		                      </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
								  <tr>
									<td style="font-size: 15px;text-align: center ;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>	
									<td style="font-size: 15px;">${item[1]}</td> 	
									<td style="font-size: 15px;">${item[2]}</td>									
									<td style="font-size: 15px;">${item[3]}</td> 	
									<td style="font-size: 15px;">${item[4]}</td>	
									<td style="font-size: 15px;text-align: center;" >${item[5]} </td> 
								  </tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>	
		      </div>			
		</div>	
</form:form>

<c:url value="GetSearch_UpdateOfficerData_view" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="name1" id="name1"  />
		<input type="hidden" name="year_of_comm1" id="year_of_comm1"  />
		<input type="hidden" name="year_of_dob1" id="year_of_dob1"  />
		<input type="hidden" name="p_arm1" id="p_arm1"  />
		<input type="hidden" name="comm_status1" id="comm_status1"  />	
		<input type="hidden" name="IsMns" id="IsMns" value="true" />		
	</form:form>  


<c:url value="view_UpadteOfficerDataUrl" var="ViewUrl"/>
	<form:form action="${ViewUrl}" method="post" id="View1Form" name="View1Form" modelAttribute="idV">
	  <input type="hidden" name="idV" id="idV" value="0">
	  <input type="hidden" name="comm_idV" id="comm_idV" value="0">
	  <input type="hidden" name="sus_noV" id="sus_noV" value="0">
	  <input type="hidden" name="comm_statusV" id="comm_statusV" value="0">	 
	  <input type="hidden" name="IsMnsv" id="IsMnsv" value="true" />	 
</form:form> 


<Script>

$(document).ready(function() {

           rank_list("mns")  
           parent_arm("mns")
       
});

$("input#personnel_no").keyup(function(){	 
	var personnel_no = this.value;	
		 var susNoAuto=$("#personnel_no");
		 var IsMns = true;
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getApprove_noListApproved_non_eff?"+key+"="+value, data: {personnel_no:personnel_no,IsMns:IsMns},			    
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
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 
		  
});

$("#unit_sus_no")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#unit_sus_no");

			susNoAuto
					.autocomplete({
						source : function(request, response) {
							$
							.ajax({
								type : 'POST',
								url : "getTargetSUSNoList?"
										+ key + "=" + value,
								data : {
									sus_no : sus_no
								},
								success : function(data) {
								var susval = [];
								var length = data.length - 1;
								var enc = data[length].substring(0,16);
								for (var i = 0; i < data.length; i++) {
									susval.push(dec(enc,data[i]));
								}
								var dataCountry1 = susval.join("|");
								var myResponse = [];
								var autoTextVal = susNoAuto.val();
								$.each(dataCountry1.toString().split("|"),
								function(i,e) {
									var newE = e.substring(0,autoTextVal.length);
									if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
										myResponse.push(e);
									}
								});
								response(myResponse);
								}
							});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No.");
								document
										.getElementById("unit_name").value = "";
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var sus_no = ui.item.value;
							$.post(
									"getTargetUnitNameList?" + key
											+ "=" + value, {
										sus_no : sus_no
									}, function(j) {

									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#unit_name").val(
												dec(enc, j[0]));

									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});

//unit name
$("input#unit_name").keypress(function() {
var unit_name = this.value;
var susNoAuto = $("#unit_name");
susNoAuto.autocomplete({
source : function(request, response) {
	$.ajax({
		type : 'POST',
		url : "getTargetUnitsNameActiveList?" + key + "=" + value,
		data : {
			unit_name : unit_name
		},
		success : function(data) {
		
			var susval = [];
			var length = data.length - 1;
			var enc = data[length].substring(0, 16);
			for (var i = 0; i < data.length; i++) {
				susval.push(dec(enc, data[i]));
			}

			response(susval);
		}
	});
},
minLength : 1,
autoFill : true,
change : function(event, ui) {
	if (ui.item) {
		return true;
	} else {
		alert("Please Enter Approved Unit Name.");
		document.getElementById("unit_name").value = "";
		susNoAuto.val("");
		susNoAuto.focus();
		return false;
	}
},
select : function(event, ui) {
	var unit_name = ui.item.value;
	$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
		target_unit_name : unit_name
	}, function(data) {
	}).done(function(data) {
		var length = data.length - 1;
		var enc = data[length].substring(0, 16);
		$("#unit_sus_no").val(dec(enc, data[0]));
	}).fail(function(xhr, textStatus, errorThrown) {
	});
}
});
});

 $(document).ready(function() {
	
	colAdj("getSearch_census");
	 
	 if('${list.size()}' == ""){
		   $("div#getSearch_census").hide();
		}	
	 var r =('${roleAccess}');
		
		
		if( r == "Unit"){
			
			 $("#unit_sus_no_hid").show();
			 $("#unit_name_l").show();
			 
		}
		else  if(r == "MISO"){
			
			 $("input#unit_sus_no").show();		 
			 $("input#unit_name").show();
			
		}
		
	      var q3 = '${unit_name1}';		
			if(q3 != ""){
				$("#unit_name").val(q3);
			}
			var q5 = '${unit_sus_no1}';		
			if(q5 != ""){
				$("#unit_sus_no").val(q5);
			}
		
		var q2 = '${personnel_no1}';
		if(q2 != ""){
			$("#personnel_no").val(q2);
		}
		/* var q6 = '${rank1}';
		console.log("val of rank " + q6);
		if(q6 != ""){
			$("#rank").val(q6);
		} */
		var q4 = '${status1}';
		if(q4 != ""){
			$("#status").val(q4);
		}
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
	    
	    
    var q3 = '${name1}';		
	if(q3 != ""){
		$("#name").val(q3);
	}
	var q5 = '${year_of_comm1}';		
	if(q5 != ""){
		$("#year_of_comm").val(q5);
	}

	var q2 = '${year_of_dob1}';
	if(q2 != ""){
		$("#year_of_dob").val(q2);
	}
	/* var q6 = '${p_arm1}';
	if(q6 != ""){
		$("#parent_arm").val(q6);
	} */
	var q7 = '${comm_status1}';
	if(q7 != ""){
		$("#comm_status").val(q7);
	}
	
}); 

 function Search(){
		
		$("#personnel_no1").val($("#personnel_no").val()) ;	
		$("#status1").val($("#statusA").val()) ;		
		$("#rank1").val($("#rank").val()) ;	
		$("#comm_id").val($("#comm_id").val()) ;
		$("#unit_name1").val($("#unit_name").val()) ;	
		$("#unit_sus_no1").val($("#unit_sus_no").val()) ;		 			 	
		$("#p_arm1").val($("#parent_arm").val()) ;		
		$("#name1").val($("#name").val()) ;	
		$("#year_of_comm1").val($("#year_of_comm").val()) ;
		$("#year_of_dob1").val($("#year_of_dob").val()) ;		
		$("#comm_status1").val($("#comm_status").val()) ;
				
		document.getElementById('searchForm').submit();
		
	}

function View1Data(id,comm_id,sus_no,comm_status){        
    $("#idV").val(id);                
    $("#comm_idV").val(comm_id);
    $("#sus_noV").val(sus_no);
    $("#comm_statusV").val(comm_status);
    document.getElementById('View1Form').submit();
             
} 

function rank_list(types){

	   var options = '<option value="0">' + "--Select--" + '</option>';
	   var type = types;

	   $.post("getrank_list?" + key + "=" + value, { type: type })
	     .done(function(j) {
	       for (var i = 0; i < j.length; i++) {
	         options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">' + j[i][1] + '</option>';
	       }
	       $("#rank").html(options);
	       
	       var q6 = '${rank1}';
			console.log("val of rank " + q6);
			if(q6 != ""){
				$("#rank").val(q6);
			}
			
	     })
	     .fail(function(xhr, textStatus, errorThrown) {
	      
	     });
}

function parent_arm(types){

	   var options = '<option value="0">' + "--Select--" + '</option>';
	   var type = types;

	   $.post("getparent_arm?" + key + "=" + value, { type: type })
	     .done(function(j) {
	       for (var i = 0; i < j.length; i++) {
	         options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">' + j[i][1] + '</option>';
	       }
	       $("#parent_arm").html(options);
	       var q6 = '${p_arm1}';
	   	if(q6 != ""){
	   		$("#parent_arm").val(q6);
	   	}
	     })
	     .fail(function(xhr, textStatus, errorThrown) {
	      
	     });
}

</Script>


