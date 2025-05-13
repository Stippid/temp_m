<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<%-- <form:form name="" id="" action="" method="post" class="form-horizontal" commandName="">  --%>
	<div class="animated fadeIn">
	    <div class="container" align="center">
	    	<div class="card">
	    		<div class="card-header"><h5> SEARCH IAFF - 3008 MONTHLY UNIT STR REPORT(MNS)  </h5> <h6 class="enter_by"></h6></div>
	          			<div class="card-body card-block">	            			
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> SUS No </label>
						                </div>
						                <div class="col-md-8">
						                	 <label id="unit_sus_no_hid" style="display: none" ><b>${roleSusNo}</b></label> 
						                	 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" style="display: none" maxlength="8" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">	
						                   <input type="hidden" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" > 						                   
						                </div>
						            </div>
	              				</div>               					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none" onkeyup="return specialcharecter(this)">
						                 <label id="unit_name_l" style="display: none"><b>${unit_name}</b></label> 	
						                </div>
						            </div>
	              				</div>	             					              				
	              			</div> 
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Month </label>
						                </div>
						                <div class="col-md-8">						                		
						                   <select id="month" name="month" class="required form-control" >
						                   		<option value="0">--Select--</option>
												<option value="1">January</option>
											    <option value="2">February</option>
											    <option value="3">March</option>
											    <option value="4">April</option>
											    <option value="5">May</option>
											    <option value="6">June</option>
											    <option value="7">July</option>
											    <option value="8">August</option>
											    <option value="9">September</option>
											    <option value="10">October</option>
											    <option value="11">November</option>
											    <option value="12">December</option>												
										</select>
						                </div>
						            </div>
	              				</div>               					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year </label>
						                </div>
						                <div class="col-md-8">
						                		<input type="text" id="year" name="year" class="form-control autocomplete" maxlength = "4" onkeypress="return isNumber(event)" 
						                 autocomplete="off"> 	 						                  
						                </div>
						            </div>
	              				</div>	             					              				
	              			</div> 
	              		 	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="status" id="status" class="form-control"  >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
												    <option value="3">Rejected</option>	
											</select>
										</div>
						            </div>
	              				</div>	             					              				
	              			</div>
	              			<!--  bisag v2 260822 (Added as per miso sir guidance) -->
	              			
	              			<div class="col-md-12">
					
					<strong style="color: red;">Note:- For Printing of Approved MNS IAFF-3008 Data, Please click on PERSONNEL -> REPORTS -> IAFF 3008 MNS REPORT</strong>
			<!-- <div class="col-md-6">
						<div class="row form-group">
							
									<strong style="color: red;">Note:- For Print of Approved IAFF-3008 Data, Please Visit Into Module: PSG -> QUERIES -> IAFF 3008 REPORT</strong>
							
							</div>

						</div>
						
							<div class="col-md-6">
							</div> -->
					</div>
	              			
	              			
					<!-- <div class="col-md-12">
			<div class="col-md-6">
						<div class="row form-group">
							
									<strong style="color: red;">Note:- For Print of Approved IAFF-3008 Data, Please Visit Into Module: PSG -> QUERIES -> IAFF 3008 REPORT MNS</strong>
							
							</div>

						</div>
						
							<div class="col-md-6">
							</div>
					</div> -->
	              			
            			</div>									
						<div class="card-footer" align="center" class="form-control" >
						 <input type="hidden" id="role" name="role" class="form-control ">
							<a href="Search_Report_Url_mns" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
			            </div> 
			            
			</div>
	</div>
	<div class="container" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		          <table id="getSearch_Letter " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center; width:5%;">Ser No</th>	
			                         <th style="text-align: center;"> Unit Name </th>		                         
			                         <th style="text-align: center;"> Month </th>
			                         <th style="text-align: center;"> Year </th>			                       
			                         <th style="text-align: center;"> Version </th>
			                            <c:if test="${status1 == 0 }">
											<th style="text-align: center;">Approve/View/Dustbin</th>
										</c:if>
										<c:if test="${status1 == 1 }">
											<th style="text-align: center;">View <!-- / Print Report --></th>
										</c:if>
			                        
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="6">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item1" items="${list}" varStatus="num" >
		                      		 <tr >
										<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td>  
										<td style="font-size: 15px;">${item1[0]}</td>	
										<td style="font-size: 15px;">${item1[1]}</td>	
										<td style="font-size: 15px; text-align: center;	">${item1[2]}</td>	
										<td style="font-size: 15px; text-align: center;">${item1[3]}</td>	
										<td style="font-size: 15px; text-align: center;">${item1[4]} <%-- ${item1[5]} --%></td>	 
									</tr>
		                              									
							</c:forEach>
		                  </tbody>
		              </table>
		         </div>				  
		</div> 
		</div>
<%-- 		</form:form> --%>
		
<c:url value="GetSearch_Report_3008_mns" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no1">	
	 	<input type="hidden" name="month1" id="month1" value="0">
	 	<input type="hidden" name="year1" id="year1" >			
	 	<input type="hidden" name="status1" id="status1" value="0">	
	 	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" >
	 	<%--  <c:if test="${roleAccess == MISO}">
	  	 
	  	 </c:if> --%>
	</form:form> 
	
<!-- Chandani -->	
	<c:url value="Approve_Report_Serving_Url_mns" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="unit_sus_no5">
	  	<input type="hidden" name="year5" id="year5" value="0">
	  	<input type="hidden" name="version5" id="version5" value="0">
	  	<input type="hidden" name="month5" id="month5" value="0">
	  	<input type="hidden" name="unit_sus_no5" id="unit_sus_no5" >
	  		<input type="hidden" name="status5" id="status5" value="0" >
	  		<input type="hidden" name="pagename" id="pagename" value="0" >
	</form:form>
	
<c:url value="delete_Report_3008_mns" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="month6">
	 	<input type="hidden" name="month6" id="month6" value="0">
	  	<input type="hidden" name="year6" id="year6" value="0">
	  	<input type="hidden" name="version6" id="version6" value="0">
	    <input type="hidden" name="unit_sus_no6" id="unit_sus_no6" >
	    
</form:form> 

<c:url value="Download_IAFF3008_query_mns" var="downloadUrl" /> 
<form:form action="${downloadUrl}" method="post" id="downloadForm"
	name="downloadForm" modelAttribute="cont_comd_tx">
	<input type="hidden" name="cont_comd_tx" id="cont_comd_tx">
	<input type="hidden" name="cont_corps_tx" id="cont_corps_tx">
	<input type="hidden" name="cont_div_tx" id="cont_div_tx">
	<input type="hidden" name="cont_bde_tx" id="cont_bde_tx">
	<input type="hidden" name="month2" id="month2" value="0">
	<input type="hidden" name="year2" id="year2" value="0">
	<input type="hidden" name="unit_sus_no3" id="unit_sus_no3" value="0">
	<input type="hidden" name="rank2" id="rank2" value="0">
	<input type="hidden" name="hd_cmd_sus2" id="hd_cmd_sus2" value="0">
	<input type="hidden" name="hd_corp_sus2" id="hd_corp_sus2" value="0">
	<input type="hidden" name="hd_div_sus2" id="hd_div_sus2" value="0">
	<input type="hidden" name="hd_bde_sus2" id="hd_bde_sus2" value="0">
	<input type="hidden" name="hd_remarks1" id="hd_remarks1" value="">
	<input type="hidden" name="hd_remarks2" id="hd_remarks2" value="">
	<input type="hidden" name="hd_remarks3" id="hd_remarks3" value="">
	<input type="hidden" name="hd_remarks4" id="hd_remarks4" value="">
	<input type="hidden" name="hd_remarks5" id="hd_remarks5" value="">
	<input type="hidden" name="hd_remarks6" id="hd_remarks6" value="">
</form:form>

	
		<script>
		<!-- Chandani -->	
	
		function editData(month,year,version,sus_no,status){

			$("#month5").val(month);
			$("#year5").val(year);
			$("#version5").val(version);
			$("#unit_sus_no5").val(sus_no);
			$("#status5").val(status);
			$("#pagename").val("Search_Report_Serving_mns");
			document.getElementById('updateForm').submit();
		}
		
		function deleteData(month,year,version,sus_no){		
			$("#month6").val(month);
			$("#year6").val(year);
			$("#version6").val(version);
			$("#unit_sus_no6").val(sus_no);
			document.getElementById('deleteForm').submit();
		}
		function Search(){	
			/*   var t = new Date();
			 var Newmonth = t.getMonth() + 1;
			 var Newyear = t.getFullYear();
			 var mon = $("#month").val();
			 var yr = $("#year").val();
			 
			if(mon == Newmonth){
				 $("#month").val(Newmonth); 	
			}		
			if(yr == Newyear){
				 $("#year").val(Newyear); 	
			}   */
			
		$("#month1").val($("#month").val()) ;	
		$("#year1").val($("#year").val()) ;	
		$("#unit_sus_no1").val($("#unit_sus_no").val()) ;	
		$("#status1").val($("#status").val()) ;	
		document.getElementById('searchForm').submit();
	}
		$(document).ready(function() {
			
			 $("#role").val('${roleAccess}');
			 
			var r =('${roleAccess}');
			
			if( r == "Unit"){
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
			}
			else  if(r == "MISO" || r == "DGMS"){
				
				 $("input#unit_sus_no").show();		 
				 $("input#unit_name").show();
			}
			
			if('${roleSusNo}' != ""){
				$('#roleSusNo').val('${roleSusNo}');
			}	
			
		if('${roleType}' == "APP"){		
			
				/* $.post('getReport_Upper_Data?' + key + "=" + value, {}, function(data){		
					var present_return_no = data[0][0];
					var present_return_dt = data[0][1];
					var last_return_no = data[0][2];
					var last_return_dt = data[0][3];
					var month = data[0][4];
					var year = data[0][5];
					$("#present_return_no").val(present_return_no);	
					$("#present_return_dt").val(present_return_dt);	
					$("#last_return_no").val(last_return_no);	
					$("#last_return_dt").val(last_return_dt);
					//$("#month").val(month);
					//$("#year").val(year);
				}); */
	   }
		 	
			if('${roleType}' == "DEO"){	
				
				$("#month").attr("disabled", false);
				$("#year").attr("disabled", false);
			} 
			//-----Chandani
			 if('${status1}' != 0){
				 $("#status").val('${status1}');		
			 }
			 if('${month1}' != 0){
				 $("#month").val('${month1}');		
			 }else{
				 var d = new Date();
				    var month = d.getMonth() + 1;
				    $("#month").val(month); 		   
			 } 
			  if('${year1}' != ''){		
				 $("#year").val('${year1}');
			 } else{
				 var d = new Date();		  
				 var year = d.getFullYear();
				 $("#year").val(year);   
			 } 
			  
			/*   if('${unit_sus_no1}' != ''){		
					 $("#unit_sus_no").val('${unit_sus_no1}');
			  } */
			  
			 if('${unit_sus_no1}' != "" ){
				 $("#unit_sus_no").val('${unit_sus_no1}');		
				 
				 var sus_no = '${unit_sus_no1}';			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				 
				 $("#unit_sus_no").attr("disabled", true);
				 $("#unit_name").attr("disabled", true);
			 }	
			 if('${size}'!= "" || '${size}'!= 0 ){
				 $("div#getSearch_Letter").show();	
				
				/*  var t1 = new Date();
				 var Newmonth1 = t1.getMonth() + 1;
				 var Newyear1 = t1.getFullYear();
				 var mon1 = $("#month").val();
				 var yr1 = $("#year").val(); */
			}
			 else{		 
				  $("div#getSearch_Letter").hide();	
			 }
			
				$.post('getEstablishment_No?' + key + "=" + value, {}, function(data){
					if(data.length > 0){
						var we_pe = data[0][0];
						$("#we_pe_no").val(we_pe);
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
			return true;
		}
		
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
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 	     
			});	
		});

		// unit name
		 $("input#unit_name").keypress(function(){
				var unit_name = this.value;
					 var susNoAuto=$("#unit_name");
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
					        	  document.getElementById("unit_name").value="";
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
		
		
		 jQuery(function($){ //on document.ready  
			 datepicketDate('present_return_dt');
			 datepicketDate('last_return_dt');
			});
		 
		 function print_report(month,year,version,sus_no){
				
				$("#cont_comd_tx").val("");
				$("#cont_corps_tx").val("");
				$("#cont_div_tx").val("");
				$("#cont_bde_tx").val("");
				$("#rank2").val("");
				$("#month2").val(month);
				$("#year2").val(year);			
				$("#unit_sus_no3").val(sus_no);
				$("#hd_cmd_sus2").val("");
				$("#hd_corp_sus2").val("");
				$("#hd_div_sus2").val("");
				$("#hd_bde_sus2").val("");

				$("#hd_remarks1").val("");
				$("#hd_remarks2").val("");
				$("#hd_remarks3").val("");
				$("#hd_remarks4").val("");
				$("#hd_remarks5").val("");
				$("#hd_remarks6").val("");
				document.getElementById('downloadForm').submit();
			} 
		</script>