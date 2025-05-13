<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/scss/style.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<form:form name="change_address" id="change_address" action="change_addressaction" method="post"
	class="form-horizontal" commandName="changeaddresscmd">

	<div class="" id="divPrint" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<div class="card-header"><h5>Present Domicile Address</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
						<table id="ChangeInAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Authority</th>
										<th>Date of Authority</th>
										<th>Country</th>
										<th>State</th>
										<th>District</th>
										<th>Tehsil</th>
										<th>Created By</th>
                                        <th>Created Date</th>
                                        <th>Approved By</th>
                                        <th>Approved Date</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							<div class="card-header"><h5>Permanent Address</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="ChangeInPerAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Authority</th>
										<th>Date of Authority</th>
										<th>Country</th>
										<th>State</th>
										<th>District</th>
										<th>Tehsil</th>
										<th>Village</th>
										<th>Pin</th>
										<th>Nearest Railway Station</th>
										<th>Is rural/Urban/Semi Urban</th>
										<th>Border Area</th>
										<th>Created By</th>
                                        <th>Created Date</th>
                                        <th>Approved By</th>
                                        <th>Approved Date</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInPerAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							<div class="card-header"><h5>Present Address</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="ChangeInPreAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Authority</th>
										<th>Date of Authority</th>
										<th>Country</th>
										<th>State</th>
										<th>District</th>
										<th>Tehsil</th>
										<th>Village</th>
										<th>Pin</th>
										<th>Nearest Railway Station</th>
										<th>Is rural/Urban/Semi Urban</th>
										<th>Created By</th>
                                        <th>Created Date</th>
                                        <th>Approved By</th>
                                        <th>Approved Date</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInPreAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
							<div id="spouseAddressDiv" style="display: none">
							<div class="card-header"><h5>SF ACCN</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
							<table id="ChangeInSpouseAddress_table" class="table-bordered " style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Authority</th>
										<th>Date of Authority</th>
										<th>Country</th>
										<th>State</th>
										<th>District</th>
										<th>Tehsil</th>
										<th>Village</th>
										<th>Pin</th>
										<th>Nearest Railway Station</th>
										<th>Is rural/Urban/Semi Urban</th>
										<th >Stn HQ SUS No</th>
										<th >Stn HQ Name</th>
									 	<th>Created By</th>
                                        <th>Created Date</th>
                                        <th>Approved By</th>
                                        <th>Approved Date</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="ChangeInSpouseAddress_tbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									
								</tbody>
							</table>
		</div>
		
	</div>
	
	
	


</form:form>

<script type="text/javascript">
<!--

//-->

$(document).ready(function() {
	
	colAdj("ChangeInAddress_table");
	colAdj("ChangeInPerAddress_table");
	colAdj("ChangeInPreAddress_table");
	colAdj("ChangeInSpouseAddress_table");
	
		 $('#ChangeInAddress_tbody').empty();
		 $('#ChangeInPerAddress_tbody').empty();
		 $('#ChangeInPreAddress_tbody').empty();
		 $('#ChangeInSpouseAddress_tbody').empty();
		 var i=1;
		 var cor=0;
		 <c:if test="${list.size() != 0}">
			<c:forEach var="j" items="${list}" varStatus="num">
			
	     		cor=i++;
	     		var doa=convertDateFormate('${j.date_authority}');
	     		var dmod=convertDateFormate('${j.modified_date}');
	     		var dcod=convertDateFormate('${j.created_date}');
	     		var pre_country ='${j.pre_country}';
	     		var pre_state ='${j.pre_state}';
	     		var pre_district ='${j.pre_district}';
	     		var pre_tehsil ='${j.pre_tehsil}';
	     		
	     		if('${j.pre_country}'=="OTHERS"){
	     			pre_country='${j.pre_country_other}';
	     		}
	     		if('${j.pre_state}'=="OTHERS"){
	     			pre_state='${j.pre_domicile_state_other}';
	     		}
	     		if('${j.pre_district}'=="OTHERS"){
	     			pre_district='${j.pre_domicile_district_other}';
	     		}
	     		if('${j.pre_tehsil}'=="OTHERS"){
	     			pre_tehsil='${j.pre_domicile_tesil_other}';
	     		}
				$("table#ChangeInAddress_table").append('<tr id="tr_ChangeInAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td>'+'${j.authority}'+'</td>'
	                     +'<td>'+doa+'</td>'
	                     +'<td>'+pre_country+'</td>'
	                     +'<td>'+pre_state+'</td>'
	                     +'<td>'+pre_district+'</td>'
	                     +'<td>'+pre_tehsil+'</td>'
	                     +'<td>'+'${j.created_by}'+'</td>'
	                     +'<td>'+dcod+'</td>'
	                     +'<td>'+'${j.modified_by}'+'</td>'
	                     +'<td>'+dmod+'</td>'
	                   
	                     +'</td></tr>');
				
				var home_country ='${j.home_country}';
	     		var home_state ='${j.home_state}';
	     		var home_district ='${j.home_district}';
	     		var home_tehsil ='${j.home_tehsil}';
	     		
	     		if('${j.home_country}'=="OTHERS"){
	     			home_country='${j.per_home_addr_country_other}';
	     		}
	     		if('${j.home_state}'=="OTHERS"){
	     			home_state='${j.per_home_addr_state_other}';
	     		}
	     		if('${j.home_district}'=="OTHERS"){
	     			home_district='${j.per_home_addr_district_other}';
	     		}
	     		if('${j.home_tehsil}'=="OTHERS"){
	     			home_tehsil='${j.per_home_addr_tehsil_other}';
	     		}
				$("table#ChangeInPerAddress_table").append('<tr id="tr_ChangeInPerAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td>'+'${j.authority}'+'</td>'
	                     +'<td>'+doa+'</td>'
	                     +'<td>'+home_country+'</td>'
	                     +'<td>'+home_state+'</td>'
	                     +'<td>'+home_district+'</td>'
	                     +'<td>'+home_tehsil+'</td>'
	                     +'<td>'+'${j.permanent_village}'+'</td>'
	                     +'<td>'+'${j.permanent_pin_code}'+'</td>'
	                     +'<td>'+'${j.permanent_near_railway_station}'+'</td>'
	                     +'<td>'+'${j.permanent_rural_urban_semi}'+'</td>'
	                     +'<td>'+'${j.permanent_border_area}'+'</td>'
	                     +'<td>'+'${j.created_by}'+'</td>'
	                     +'<td>'+dcod+'</td>'
	                     +'<td>'+'${j.modified_by}'+'</td>'
	                     +'<td>'+dmod+'</td>'
	                    
	                     +'</td></tr>');
				
				
				var present_country ='${j.present_country}';
	     		var present_state ='${j.present_state}';
	     		var present_district ='${j.present_district}';
	     		var present_tehsil ='${j.present_tehsil}';
	     		
	     		if('${j.present_country}'=="OTHERS"){
	     			present_country='${j.pers_addr_country_other}';
	     		}
	     		if('${j.present_state}'=="OTHERS"){
	     			present_state='${j.pers_addr_state_other}';
	     		}
	     		if('${j.present_district}'=="OTHERS"){
	     			present_district='${j.pers_addr_district_other}';
	     		}
	     		if('${j.present_tehsil}'=="OTHERS"){
	     			present_tehsil='${j.pers_addr_tehsil_other}';
	     		}
				$("table#ChangeInPreAddress_table").append('<tr id="tr_ChangeInPreAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td>'+'${j.authority}'+'</td>'
	                     +'<td>'+doa+'</td>'
	                     +'<td>'+present_country+'</td>'
	                     +'<td>'+present_state+'</td>'
	                     +'<td>'+present_district+'</td>'
	                     +'<td>'+present_tehsil+'</td>'
	                     +'<td>'+'${j.present_village}'+'</td>'
	                     +'<td>'+'${j.present_pin_code}'+'</td>'
	                     +'<td>'+'${j.present_near_railway_station}'+'</td>'
	                     +'<td>'+'${j.present_rural_urban_semi}'+'</td>'
	                     +'<td>'+'${j.created_by}'+'</td>'
	                     +'<td>'+dcod+'</td>'
	                     +'<td>'+'${j.modified_by}'+'</td>'
	                     +'<td>'+dmod+'</td>'
	                     
	                     +'</td></tr>');
				
				if('${j.spouse_country}'!=''){
				var spouse_country ='${j.spouse_country}';
	     		var spouse_state ='${j.spouse_state}';
	     		var spouse_district ='${j.spouse_district}';
	     		var spouse_tehsil ='${j.spouse_tehsil}';
	     		
	     		if('${j.spouse_country}'=="OTHERS"){
	     			spouse_country='${j.spouse_addr_country_other}';
	     		}
	     		if('${j.spouse_state}'=="OTHERS"){
	     			spouse_state='${j.spouse_addr_state_other}';
	     		}
	     		if('${j.spouse_district}'=="OTHERS"){
	     			spouse_district='${j.spouse_addr_district_other}';
	     		}
	     		if('${j.spouse_tehsil}'=="OTHERS"){
	     			spouse_tehsil='${j.spouse_addr_tehsil_other}';
	     		}
	     		var sus_no = '${j.stn_hq_sus_no}';
	     	
	     		if(sus_no!=""){
	     			var Stn_HQ_unit_name ;
					 $.post('getTargetUnitNameListStnHQ?'+key+"="+value, {
							 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             Stn_HQ_unit_name=dec(enc,j[0]);   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
		        	 });
	     		}
	     		
				$("table#ChangeInSpouseAddress_table").append('<tr id="tr_ChangeInPreAddress'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td>'+'${j.authority}'+'</td>'
	                     +'<td>'+doa+'</td>'
	                     +'<td>'+spouse_country+'</td>'
	                     +'<td>'+spouse_state+'</td>'
	                     +'<td>'+spouse_district+'</td>'
	                     +'<td>'+spouse_tehsil+'</td>'
	                     +'<td>'+'${j.spouse_village}'+'</td>'
	                     +'<td>'+'${j.spouse_pin_code}'+'</td>'
	                     +'<td>'+'${j.spouse_near_railway_station}'+'</td>'
	                     +'<td>'+'${j.spouse_rural_urban_semi}'+'</td>'
	                     +'<td>'+'${j.st_sus_no}'+'</td>'
	                     +'<td>'+'${j.st_unit_name}'+'</td>'
	                     +'<td>'+'${j.created_by}'+'</td>'
	                     +'<td>'+dcod+'</td>'
	                     +'<td>'+'${j.modified_by}'+'</td>'
	                     +'<td>'+dmod+'</td>'
	                   
	                     +'</td></tr>');
				$("#spouseAddressDiv").show();
				}
			
				</c:forEach>
			</c:if>
	
	} );
</script>
