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
  
<form:form name="View_App_Maritial_Discord" id="View_App_Maritial_Discord" action="View_App_Maritial_Discord_Action" method="post" class="form-horizontal" commandName="View_App_Maritial_Discord_CMD"> 
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>VIEW/CANCEL MARITAL DISCORD HISTORY</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by Unit)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">

						<div class="col-md-6" id="per_no_id" >
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Army No </label>
								</div>
								<div class="col-md-8">
								
								<label class=" form-control-label" id="personnel_no1"></label> 
									<input type="hidden" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"
										onchange="personal_number()"> 
									<input type="hidden" id="jco_id" name="jco_id" class="form-control" autocomplete="off"> 
									<input type="hidden" id="p_id" name="p_id" class="form-control autocomplete"  autocomplete="off" >
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Rank </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="p_rank"></label> 
									<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cadet_name"></label>
									<input type="hidden" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Unit Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_unit_name"></label>
									<input type="hidden" id="un" name="un" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SUS No </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_sus_no"></label>
								</div>
							</div>
						</div>

					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Name of Complainant </label>
								</div>
								<div class="col-md-8">
								<label class=" form-control-label" id="name_lady" > </label>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Complaint/Allegations
									</label>
								</div>
								<div class="col-md-8">
                                   <label class=" form-control-label" id="dt_of_comp" > </label>
									
								</div>
							</div>
						</div>


					</div>
					<div class="col-md-12">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Complaint/Allegations
									</label>
								</div>
								<div class="col-md-8" align="right">
								    <label class=" form-control-label" id= "complaint"></label>
								</div>
							</div>
					
					</div>
					
				
		<div class="col-md-12" id="divPrint" style="display: block;"> 	 
		           <table id="getSearch_Status " class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                          <tr>
			                          <th style="text-align: center;">Status of The Case</th>	
			                           <th style="font-size: 15px">Created By</th>
                                    <th style="font-size: 15px">Created Date</th>
                                    <th style="font-size: 15px">Approved By</th>
                                    <th style="font-size: 15px">Approved Date</th>
                                    <th style="font-size: 15px">Action</th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${listch.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="1">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${listch}" varStatus="num" >
		                        <c:if test="${item.cancel_status==-1 || item.cancel_status==2}">
									<tr>
								    	<td style="font-size: 15px; text-align: center;">${item.status_of_case}</td>	
								    	<td style="font-size: 15px;">${item.created_by}</td>
										<td style="font-size: 15px;" class="_dt">${item.created_date}</td>
										<td style="font-size: 15px;">${item.modified_by}</td>
										<td style="font-size: 15px;" class="_dt">${item.modified_date}</td>
										<td style="font-size: 15px;text-align: center;" >
										<a class="btn btn-danger btn-sm"  title = "CANCEL"  onclick="CancelHistoryChData(${item.id});"><i class="fa fa-times"></i></a>
										</td>
									</tr>
									</c:if>
								</c:forEach>
		                     </tbody>
		                 </table>
		        				  
		</div> 

					
					

				</div>
				<input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" >
				
				<div class="card-footer" align="center" class="form-control">
				<c:if test="${listp[0].status==1}">
			      	<input type="button" class="btn btn-info btn-sm" onclick="if (confirm('Are You Sure You Want to Cancel This Data?') ){CancelHistoryData();}else{ return false;}" value="Cancel">
			      	</c:if>
				</div>
			</div>
		</div>
	</div>

</form:form>

<c:url value="Cancel_Marital_DiscordfromView_JCO" var="cancelHisytoryUrl" />
<form:form action="${cancelHisytoryUrl}" method="post" id="CancelHistoryForm" name="CancelHistoryForm" >
	<input type="hidden" name="id7" id="id7" value="0"/> 
</form:form>

<c:url value="Cancel_Maritial_Discord_ChildFromView_JCO" var="cancelChildHisytoryUrl" />
<form:form action="${cancelChildHisytoryUrl}" method="post" id="CancelChildHistoryForm" name="CancelChildHistoryForm" >
	<input type="hidden" name="id_p" id="id_p" value="0"/> 
	<input type="hidden" name="id_ch" id="id_ch" value="0"/> 
</form:form>

<script>	

$(document).ready(function() {
	$("#p_id").val('${listp[0].id}');
	
 	$("#personnel_no").val('${listp[0].personnel_no}');
 	$("#personnel_no1").text('${listp[0].personnel_no}');
 	personal_number();
 	$("#name_lady").text('${listp[0].name_lady}');
 	 
 	$("#complaint").text('${listp[0].complaint}');  
 	 
 	$("#dt_of_comp").text(ParseDateColumncommission('${listp[0].dt_of_complaint}'));
 	
 	$('._dt').each(function(i, obj) {
 		$(this).text(formateDate($(this).text()));
	 });
 
});

function formateDate(value){
	var date = new Date(value);
	var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	return formattedDate;
}

function CancelHistoryData(){
	$("#id7").val($("#p_id").val());
	document.getElementById('CancelHistoryForm').submit();
}

function CancelHistoryChData(ch_id){
	$("#id_p").val($("#p_id").val());
	$("#id_ch").val(ch_id);
	document.getElementById('CancelChildHistoryForm').submit();
	
}

function personal_number() {

	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	} 
	var army_no = $("input#personnel_no").val();
	$.post('GetArmyNoData?' + key + "=" + value,{army_no : army_no},function(j) {
						if (j != "") {
							$("#jco_id").val(j[0][0]);
							var jco_id = j[0][0];
							$.post('GetCensusDataApproveJCO?' + key+ "=" + value,{jco_id : jco_id},function(k) {
								if (k.length > 0) {
									curr_marital_status = k[0][13];
									$('#marital_event').val('0');
									$("#cadet_name").text(k[0].full_name);
									$("#p_rank").text(k[0].rank);
									$("#hd_p_rank").val(k[0].rank);
									$("#app_sus_no").text(k[0].unit_sus_no);
									
									$.ajaxSetup({
										async : false
									});
									
									var sus_no = k[0].unit_sus_no;
									getunit(sus_no);
									function getunit(val) {
							                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
												var length = j.length - 1;
												var enc = j[length].substring(0,16);
												$("#app_unit_name").text(dec(enc,j[0]));
											}).fail(function(xhr,textStatus,errorThrown) {
									        });
									}
									$.ajaxSetup({
										async : false
									});
									 $.post("CheckJCOStatus?"+ key+ "="+ value,{jco_id:jco_id},function(h) {}).done(function(h) {
								    	 if (h.length > 0) {
								    		 if (h[0].status == "0"){
								    			 $('#Hid').val(h[0].id);
												 $("#name_lady").val(h[0].name_lady);
												 $("#dt_of_comp").val(ParseDateColumncommission(h[0].dt_of_complaint));
												 $("#complaint").val(h[0].complaint);
												 $.ajaxSetup({
														async : false
													});
												 var id = h[0].id;
												 $.post('GetJCOStatusCase?' + key + "=" + value,{id:id}, function(data){
													if(data!=0){
														 $('#Child_Hid').val(data[0].id);
														$("#status_of_case").val(data[0].status_of_case);
													}
											  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
												});
												 
								    		 } else {
									    		 $('#Hid').val(h[0].id);
												 $("#name_lady").val(h[0].name_lady);
												 $("#name_lady").attr('readonly', true);
												 $("#dt_of_comp").val(ParseDateColumncommission(h[0].dt_of_complaint));
												 $("#dt_of_comp").attr('readonly', true);
												 $("#complaint").val(h[0].complaint);
									    		 $("#complaint").attr('readonly', true);
								    		 }
								    	 }
										}).fail(function(xhr,textStatus,errorThrown) {
								        }); 
							}
						});
							
							$.post("Marital_discord_JCO?"+key+"="+value, {jco_id : jco_id}).done(function(j) {
							 	if(j == 1){
							 		$("#name_lady").attr('readonly', true);
							 		$("#dt_of_comp").attr('readonly', true);
							 		$("#complaint").attr('readonly', true);
								} 
								
							}); 
						     
						}
					});
	
	$("input#personnel_no").attr('readonly', true);
}

$("input#personnel_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
		        data: {personel_no:personel_no},
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
jQuery(function($){ //on document.ready 
 	datepicketDate('dt_of_comp');
}); 

</script>