<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
${jsCSS}
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function () {
		setvalue();
		$.ajaxSetup({
		    async: false
		});
	});
	function setvalue(){
		document.getElementById("getTypeOnclick").value = '${wepe}';
	} 
</script>
<script>
$(document).ready(function() {
		$("tr#list_tr").show();
		$("tr#list4_tr").show();
		$("tr#list8_tr").show();
		$("tr#list9_tr").show();
		
		if( '${roleType}' == 'ALL'){
			 $("select#type_of_cue").val(0);
			document.getElementById("h3Id").innerHTML="UNIT ENTITLEMENT DETAILS"; 
		}
		else{
			if ('${wepe}' == '1')//Personnel //for PERSONNEL value is 1
			{
				document.getElementById("h3Id").innerHTML="VIEW UNIT ENTITLEMENT";
				$("div#wet_pet_no_sh").hide();
			}
			else if ('${wepe}' == '2')  //Transport // for TRANSPORT value is 2
			{
				document.getElementById("h3Id").innerHTML="VIEW UNIT ENTITLEMENT";
				$("div#wet_pet_no_sh").hide();
			}
			else if ('${wepe}' == '3' )  //Weapon // for WEAPON value is 3
			{
				document.getElementById("h3Id").innerHTML="VIEW UNIT ENTITLEMENT";
				$("div#wet_pet_no_sh").show();
			}
		}
		
		if('${sus_no01}' != "" )
		{
			$("#sus_no").val('${sus_no01}');
			if('${type_of_cue01}' == ""){
				$("#type_of_cue").val("0");	
			}else{
				$("#type_of_cue").val('${type_of_cue01}');
			}
			
			/// 26-01-1994
			getOrbatDetails('${sus_no01}');
			
			if('${roleAccess}' != "Unit" ){
				getSusnoByName('${unit_name}');
				getNameBySusno('${sus_no01}');
			}
			OnchangeOfTypeOfRole('${type_of_cue01}');
			setvalue();
			
			if('${type_of_cue01}' == "1")
			{
				$("#type_of_cue").val('${type_of_cue01}');
				$("#getSearchReport").show();
				$("#summaryhide").show();
				document.getElementById("h3Id").innerHTML="VIEW UNIT ENTITLEMENT";
				$("div#wet_pet_no_sh").hide();
				$("#tableStdTrans").show();
				$("#tableStdTransMod").show();
				$("#tableStdTransfot").show();
				$("#tableStdTransInLu").show();
				
				$("#tableshowwaepon").hide();
				$("#tableStdWaepon").hide();
				$("#tableStdWaeponMod").hide();
				$("#tableStdWaeponfot").hide();
				$("#tableStdWaeponces").hide();
				
				$("#tableshowUnitDtlPers").hide();
				$("#tableshowPerson").hide();
				$("#tableStdPerson").hide();
				$("#tableStdPersonMod").hide();
				$("#tableStdPersonfot").hide();
				$("#type_of_cue").val('${type_of_cue01}');
				
				if('${list.size()}' == 0 ){
					$("tr#list_tr").hide();
					$("table#getSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list1.size()}' == 0 ){
					$("table#getStdTransSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list2.size()}' == 0 ){
					$("table#getStdTransModeSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list3.size()}' == 0 ){
					$("table#getStdTransfotnoteSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list13.size()}' == 0 ){
				     $("table#getStdTransInlieuSearchReport").append("<tr><td colspan='8' style='text-align :center;'>Data Not Available</td></tr>");
				}
			}
			if('${type_of_cue01}' == "2")
			{
				$("#type_of_cue").val('${type_of_cue01}');
				document.getElementById("h3Id").innerHTML="VIEW UNIT ENTITLEMENT";
				$("div#wet_pet_no_sh").show();
				$("#tableshowwaepon").show();
				$("#tableStdWaepon").show();
				$("#tableStdWaeponMod").show();
				$("#tableStdWaeponfot").show();
				$("#tableStdWaeponces").show();
				
				$("#getSearchReport").hide();
				$("#summaryhide").hide();
				$("#tableStdTrans").hide();
				$("#tableStdTransMod").hide();
				$("#tableStdTransfot").hide();
				$("#tableStdTransInLu").hide();
				
				$("#tableshowUnitDtlPers").hide();
				$("#tableshowPerson").hide();
				$("#tableStdPerson").hide();
				$("#tableStdPersonMod").hide();
				$("#tableStdPersonfot").hide();
				

				if('${list4.size()}' == 0 ){
					$("tr#list4_tr").hide();
					$("table#getSearchReportgetWeapon").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list5.size()}' == 0 ){
					$("table#getStdWaeponSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list6.size()}' == 0 ){
					$("table#getStdWaeponModeSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list7.size()}' == 0 ){
					$("table#getStdWaeponfotnoteSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list_ces.size()}' == 0 ){
					$("table#getStdWaeponcesSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				}
			}
			if('${type_of_cue01}' == "3")
			{
				$("#type_of_cue").val('${type_of_cue01}');
				document.getElementById("h3Id").innerHTML="VIEW UNIT ENTITLEMENT";
				$("div#wet_pet_no_sh").hide();
				$("#tableshowUnitDtlPers").show();
				$("#tableshowPerson").show();
				$("#tableStdPerson").show();
				$("#tableStdPersonMod").show();
				$("#tableStdPersonfot").show();
				
				$("#getSearchReport").hide();
				$("#summaryhide").hide();
				$("#tableStdTrans").hide();
				$("#tableStdTransMod").hide();
				$("#tableStdTransfot").hide();
				$("#tableStdTransInLu").hide();
				
				$("#getSearchReport").hide();
				$("#tableStdTrans").hide();
				$("#tableStdTransMod").hide();
				$("#tableStdTransfot").hide();
				$("#tableStdTransInLu").hide();
				
				if('${list8.size()}' == 0 ){
					$("tr#list8_tr").hide(); 
					$("table#getSearchUnitDtlPersReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				}
				if('${list9.size()}' == 0 ){
					$("tr#list9_tr").hide();
					$("table#getSearchReportgetPerson").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list10.size()}' == 0 ){
				     $("table#getStdPersonSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list11.size()}' == 0 ){
				     $("table#getStdPersonModeSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				if('${list12.size()}' == 0 ){
				     $("table#getStdPersonfotnoteSearchReport").append("<tr><td colspan='12' style='text-align :center;'>Data Not Available</td></tr>");
				} 
				
			}
			if('${wet_pet_no01}' != ""){
				$("#wet_pet_no").val('${wet_pet_no01}');
			}
						
			$("div#divwatermark").val('').addClass('watermarked'); 
			watermarkreport();
			document.getElementById("printId").disabled = false;
		}
		
		
		try{
	   		if(window.location.href.includes("we_pe_no1="))
	   		{
	   			var url = window.location.href.split("?we_pe_no1")[0];
	   			window.location = url;
	   		}
	   		else if(window.location.href.includes("we_pe_no2="))
	   		{
	   			var url = window.location.href.split("?we_pe_no2")[0];
	   			window.location = url;
	   		}
	   		else if(window.location.href.includes("wet_pet_no1="))
	   		{
	   			var url = window.location.href.split("?wet_pet_no1")[0];
	   			window.location = url;
	   		}
	   		else if(window.location.href.includes("wet_pet_no2="))
	   		{
	   			var url = window.location.href.split("?wet_pet_no2")[0];
	   			window.location = url;
	   		}
	   		 
	   	}
	   	catch (e) {
	   		// TODO: handle exception
	   	}
	   
	});
</script>
<input type="hidden" name="getTypeOnclick" id="getTypeOnclick" value="${wepe}"/> 	
<form:form name="#" id="#" action="transportAuthorizationUnderDTLAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="viewtransportAuthorizationUnderDTLCMD" enctype="multipart/form-data">
	<div class="animated fadeIn" id="printableArea2" style="display: block;">
		<div class="">
	       	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"> <h5 id="h3Id"></h5></div>
	            		<div class="card-body card-block cue_text" id="mainViewSelection" style="display: block;">
	            		 <div class="col-md-12">
							<div class="col-md-6">
	            			   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">SUS No <strong style="color: red;">*</strong>  </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="sus_no" name="sus_no" style="font-family: 'FontAwesome',Arial;"  value="${sus_no01}"  placeholder="&#xF002; Search"  class="form-control autocomplete" autocomplete="off" >
                  					</div>
              					</div>
              				</div>
              				<div class="col-md-6">
	            			   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit Name </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="unit_name" name="unit_name" value="${unit_name}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  class="form-control autocomplete" autocomplete="off">
                					</div>
              					</div>
							</div>
						  </div>
						  <div class="col-md-12">
              				<div class="col-md-6">
							   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Type <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<select id="type_of_cue" name="type_of_cue" onchange="OnchangeOfTypeOfRole(this.value);" class="form-control" tabindex="1" >
	                 						<option value="0">--Select--</option>
	                 						<option value="1">Transport</option>
	                 						<option value="2">Weapon</option>
	                 						<option value="3">Personnel</option>
	                 					</select>
                					</div>
              					</div>
              				</div>
              				<div class="col-md-6">
	            			   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Location </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="location" name="location" class="form-control" value="${code_value}" autocomplete="off" readonly="readonly">
                					</div>
              					</div>
							</div>
              			</div>	
              			<div class="col-md-12">
              				<div class="col-md-6">
	            			   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">CT Part I/II</label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="ctpart" name="ctpart" value="${ct_part_i_ii}" class="form-control" autocomplete="off" readonly="readonly">
                					</div>
              					</div>
							</div>
							<div class="col-md-6">
	            			   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MISO WE/PE No </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea id="we_pe_no" name="we_pe_no" rows="3" class="form-control" readonly="readonly"></textarea>
                					</div>
              					</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6" style="display: none;" id="rejectModal12">
	            			   <div class="row form-group">
                					
                					<div class="col-12 col-md-6">
                  						<a href="rejectModal"  data-target="#rejectModal" data-toggle="modal"  class="btn btn-danger btn-sm" onclick="we_pe_doc_down();">  Download</a>
                					</div>
              					</div>
							</div>
							<div class="col-md-6" id="wet_pet_no_sh" style="display: none;">
	            			   <div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">WET/PET No </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<textarea id="wet_pet_no" name="wet_pet_no" rows="3" class="form-control"  readonly="readonly"></textarea>
                					</div>
              					</div>
							</div>
						</div>
					</div>
					
<!-- 					26-01-1994 -->
					<div class="col-sm-12" id="pers_orbat_div" style="display: none;" align="center" >
            	<h3 class="heading_content_inner">Orbat Details</h3>
			<table id="orbatLinkPers" class="table no-margin table-striped  table-hover  table-bordered">
				<thead>
					<tr>
						<th style="width: 6%">Ser No</th>
						<th>SUS No</th>
						<th>Unit Name</th>
						<th>Command</th>
						<th>Corps</th>
						<th>Division</th>
						<th>Brigade</th>
						<th>Location</th>
						<th>Arm</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
					<div  class="card-footer" align="center" id="btnhide" style="display: block;">
						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
	              		<i class="fa fa-print"></i>	<input type="button" class="btn btn-primary btn-sm btn_report" id="printId" value="Print" onclick="printDiv('printableArea')" disabled>
					</div>
				</div> 
			</div>    
		</div>
	</div>
	<div class="animated fadeIn" id="printableArea1" >
		<div id="printableArea" style="display: none;"></div>	
    	<div class="col s12" id="tableshow" >
	    	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    			<h3 class="heading_content_inner" id="summaryhide" style="display: none;">Summary of Transport</h3>
							<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%" style="display: none;"> 
								<thead >
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >MCT No</th>
										<th >Nomenclature</th>
										<th >Base Authorisation</th>
										<th >Modification Inc/Dec</th>
										<th >General Notes Inc/Dec</th>
										<th >In Lieu Notes Inc/Dec</th>
										<th >Reduced Amount(Due to Inlieu)</th>
										<th >Total</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
									<td class="tableheadSer">${num.index+1}</td>
									<td >${item.mct_no}</td>
									<td >${item.mct_nomen}</td>
									<td >${item.base}</td>
									<td >${item.modification}</td>
									<td >${item.gennotes}</td>
									<td >${item.inlieu}</td>
									<td >${item.reducedueinlieu}</td>
									<td >${item.total}</td>
								</tr>
								</c:forEach>
								<tr id="list_tr" style="display: none;">
									<td class="tableheadSer"></td>
									<td colspan="2" style="text-align:center;color: red"><B>Total</B></td>
									<td align="left" style="text-align:left;color: red"><B>${base}</B></td>
									<td align='left' style="text-align:left;color: red" ><B>${modification}</B></td> 
									<td align='left' style="text-align:left;color: red"><B>${gennotes}</B></td>
									<td align='left' style="text-align:left;color: red"><B>${inlieu}</B></td>
									<td align='left' style="text-align:left;color: red"><B>${reducedueinlieu}</B></td>
									<td align='left' style="text-align:left;color: red"><B>${total}</B></td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>	
					<div class="col s12" id="tableStdTrans" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
						<span id="ip"></span>
	    					<h3 class="heading_content_inner">Standard Authorisation Of Transport <i class="fa fa-file-excel-o" aria-hidden="true" onclick="tableToExcel('getStdTransSearchReport', 'Transport')" title="Export to Excel"></i></h3> 
							<table id="getStdTransSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr id="tbtrans" style="display:none;">
										<th colspan="4" >SUS NO : <lable id="tsus_no"></lable></th>
									</tr>
									<tr id="tbtrans" style="display:none;">
										<th colspan="4" >MISO WE/PE NO : <lable id="twepe_no"></lable></th>
									</tr>
									<tr>
										<th class="tableheadSer">Ser No</td>
										<th >MCT No</th>
										<th >Nomenclature</th>
										<th >Base Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list1}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.mct_no}</td>
											<td >${item1.mct_nomen}</td>
											<td >${item1.auth_amt}</td>
										</tr>
									</c:forEach>								
								</tbody>
							</table>
							</div>
						</div>
						<div class="col s12" id="tableStdTransMod" style="display: none;">
							<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
							<span id="ip"></span>
	    					<h3 class="heading_content_inner">Authorisation of Transport Under Modification</h3>
							<table id="getStdTransModeSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead >
									<tr>
										<th class="tableheadSer">Ser No</td>
										<th >MCT No</th>
										<th >Nomenclature</th>
										<th >Modification</th>
										<th >Base Authorisation</th>
										<th >Inc/Dec Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list2}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.mct_no}</td>
											<td >${item1.mct_nomen}</td>
											<td >${item1.modification}</td>
											<td >${item1.auth_amt}</td>
											<td >${item1.amt_inc_dec}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						<div class="col s12" id="tableStdTransfot" style="display: none;">
							<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
								<span id="ip"></span>
		    					<h3 class="heading_content_inner">Incr/Decr  General Notes Of Transport</h3>
								<table id="getStdTransfotnoteSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
									<thead >
										<tr>
											<th class="tableheadSer">Ser No</td>
											<th >MCT No</th>
											<th >Nomenclature</th>
											<th >Gen Notes Condition</th>
											<th >Base Authorisation</th>									
											<th >Inc/Dec Authorisation</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item1" items="${list3}" varStatus="num" >
											<tr>
												<td class="tableheadSer">${num.index+1}</td>
												<td >${item1.mct_no}</td>
												<td >${item1.mct_nomen}</td>
												<td >${item1.condition}</td>
												<td >${item1.auth_amt}</td>
												<td >${item1.amt_inc_dec}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col s12" id="tableStdTransInLu" style="display: none;">
							<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
								<span id="ip"></span>
		    					<h3 class="heading_content_inner">In Lieu General Notes for Transport Report</h3>
								<table id="getStdTransInlieuSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
									<thead >
										<tr>
											<th class="tableheadSer">Ser No</th>
											<th >MCT No</th>
											<th >MCT Nomenclature</th>
											<th >Auth Amount</th>
											<th >In Lieu MCT</th>									
											<th >Nomenclature</th>
											<th >Actual In Lieu Auth</th>
											<th >Condition</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item1" items="${list13}" varStatus="num" >
											<tr>
												<td class="tableheadSer">${num.index+1}</td>
												<td >${item1.mct_no}</td>
												<td >${item1.mct_name1}</td>
												<td >${item1.auth_amt}</td>
												<td >${item1.in_lieu_mct}</td>
												<td >${item1.mct_nomen}</td>
												<td >${item1.actual_inlieu_auth}</td>
												<td >${item1.condition}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col s12" id="tableshowwaepon" style="display: none;">
					    	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
								<span id="ip"></span>
					    		<h3 class="heading_content_inner">Summary Of Weapon</h3>
								<table id="getSearchReportgetWeapon" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>																				
										<th >Type of Auth</th>
										<th >CES/CCES/ETS No</th>	
										<th >Base Authorisation</th>
										<th >Modification</th> 
										<th >Modification Inc/Dec</th>
										<th >General Notes Inc/Dec</th>
										<th >Total</th>	
								</tr>
								</thead>
								<tbody>
									<% String tmpprf = "";%>
								
									<c:forEach var="item1" items="${list4}" varStatus="num" >
									<c:if test="${tmpprf != item1.prf_group}">
									<tr>								
				 						<td colspan='2'></td>           
					                    <td colspan='8' style='text-align:left;text-decoration: underline;font-weight: bold;'>PRF Group&nbsp;:&nbsp;${item1.prf_group}</td>
									</tr>									
									</c:if>
										<tr>
											<td class="tableheadSer">${num.index+1}</td>										
											<td >${item1.item_seq_no}</td>
											<td >${item1.item_type}</td>
											<td >${item1.type_we_wet}</td>
											<td >${item1.ces_cces_no} </td>
											<td >${item1.base_auth}</td>
											<td >${item1.modification}</td>
											<td >${item1.mod_auth}</td>
											<td >${item1.foot_auth}</td>
											<td >${item1.total}</td>											
										</tr>				
										<c:set var="tmpprf" value="${item1.prf_group}"/>
									</c:forEach>
									<%-- <c:forEach var="item1" items="${list4}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.item_seq_no}</td>
											<td >${item1.item_type}</td>
											<td >${item1.type_we_wet}</td>
											<td >${item1.ces_cces_no} </td>
											<td >${item1.base_auth}</td>
											<td >${item1.modification}</td>
											<td >${item1.mod_auth}</td>
											<td >${item1.foot_auth}</td>
											<td >${item1.total}</td>
											
										</tr>				
									</c:forEach> --%>
								<tr id="list4_tr" style="display: none;" >
								<td class="tableheadSer"></td>
									<td  colspan="4" style="text-align:center;color: red"><B>Total</B></td>
									<td align="left" style="text-align:left;color: red"><B>${base_authW}</B></td><td></td>
									<td align='left' style="text-align:left;color: red"><B>${mod_authW}</B></td>
									<td align='left' style="text-align:left;color: red"><B>${foot_authW}</B></td>
									<td align='left' style="text-align:left;color: red"><B>${totalW}</B></td>
								</tr>
									
								</tbody>
							</table>
							</div>
						</div>	
						<div class="col s12" id="tableStdWaepon" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Standard Authorisation Of Weapon<i class="fa fa-file-excel-o" aria-hidden="true" onclick="tableToExcel('getStdWaeponSearchReport', 'Weapon')" title="Export to Excel"></i></h3>
							<table id="getStdWaeponSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr id="tbwep" style="display:none;">
										<th colspan="4" >SUS NO : <lable id="wsus_no"></lable></th>
									</tr>
									<tr id="tbwep" style="display:none;">
										<th colspan="4" >MISO WE/PE NO : <lable id="wwepe_no"></lable></th>
									</tr>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>
										<th >Base Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list5}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.item_seq_no}</td>
											<td >${item1.item_type}</td>
											<td >${item1.base_auth}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						<div class="col s12" id="tableStdWaeponMod" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Authorisation of Weapon Under Modification</h3>
							<table id="getStdWaeponModeSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>
										<th >Modification</th>
										<th >Base Authorisation</th>
										<th >Inc/Dec Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list6}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.item_seq_no}</td>
											<td >${item1.item_type}</td>
											<td >${item1.modification}</td>
											<td >${item1.auth_weapon}</td>
											<td >${item1.amt_inc_dec}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						<div class="col s12" id="tableStdWaeponfot" style="display: none;">
	    					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Inc/Dec General Notes Of Weapon</h3>
							<table id="getStdWaeponfotnoteSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>										
										<th >Gen Notes Condition</th>
										<th >Base Authorisation</th>
										<th >Inc/Dec Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list7}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.item_seq_no}</td>
											<td >${item1.item_type}</td>
											<td >${item1.condition}</td>
											<td >${item1.auth_weapon}</td>
											<td >${item1.amt_inc_dec}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						
						
						
							<div class="col s12" id="tableStdWaeponces" style="display: none;">
	    					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">CES/CCES/ETS Details</h3>
							<table id="getStdWaeponcesSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>		
										<th >Item Code</th>						
										<th >Nomenclature</th>										
										<th >Sub Nomenclature</th>										
										<th >Proposed Auth</th>
										<th >Total </th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list_ces}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
												<td >${item1.item_seq_no}</td>
											<td >${item1.mainnomen}</td>
											<td >${item1.subnomen}</td>										
											<td >${item1.auth_proposed}</td>
											<td >${item1.total}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						
						
						
						
						
						
						
						
						
						
				       <div class="col s12" id="tableshowUnitDtlPers" style="display: none;">
								<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
								<h3 class="heading_content_inner">Summary of Personnel</h3>
									<table id="getSearchUnitDtlPersReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
										<thead>
											<tr>
												<th rowspan="2" class="tableheadSer">Ser No</th>
												<th rowspan="2" >Category</th>	
												<th colspan="2" style="text-align: center;">Personnel Category</th>
												<th rowspan="2" >Total</th>
												</tr>
											<tr>
												<th style="text-align: center;">ERE</th>
												<th style="text-align: center;">Regimental</th>
											</tr>
											
										</thead>
										<tbody>
											<c:forEach var="item1" items="${list8}" varStatus="num" >
												<tr>
													<td class="tableheadSer">${num.index+1}</td>
													<td >${item1.rank_category}</td>
													<td >${item1.ere}</td>
													<td >${item1.regimental}</td>
													<td >${item1.total}</td>
												</tr>		
											</c:forEach>
											<tr id="list8_tr" style="display: none;">
												<td colspan="2" style="width: 40%;text-align:center;color: red"><B>Total</B></td>
												<td align="left" style="width: 10%;text-align:left;color: red"><B>${ere}</B></td>
												<td align='left' style="width: 10%;text-align:left;color: red" ><B>${regimental}</B></td>
												<td align='left' style="width: 10%;text-align:left;color: red"><B>${totalP}</B></td>
											</tr>
										</tbody>
									</table>
									</div>
								</div>	
					    	<div class="col s12" id="tableshowPerson" style="display: none;">
					    	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					    	<h3 class="heading_content_inner">Authorization of Personnel</h3>
								<table id="getSearchReportgetPerson" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Personnel Category</th>
										<th >Category</th>
										<th >Appt/Trade</th>
										<th >Rank</th>
										<th >Arm</th>
										<th >Base Authorisation</th>
										<th >Modification</th>
										<th >Modification Authorisation</th>
										<th >General Notes Authorisation</th>
										<th >Total</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list9}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.person_category}</td>
											<td >${item1.rank_category}</td>
											<td >${item1.appointment}</td>
											<td >${item1.rank}</td>
											<td >${item1.arm_desc}</td>
											<td >${item1.base_auth}</td>
											<td >${item1.modification}</td>
											<td >${item1.mod_auth}</td>
											<td >${item1.foot_auth}</td>
											<td >${item1.total}</td>
										</tr>	
									</c:forEach>
									
									
									<tr id="list9_tr" style="display: none;">
									<td class="tableheadSer"></td>
										<td colspan="5" style="text-align:center;color: red"><B>Total</B></td>
										<td align="left" style="text-align:left;color: red"><B>${base_auth}</B></td><td></td>
										<td align='left' style="text-align:left;color: red" ><B>${mod_auth}</B></td>
										<td align='left' style="text-align:left;color: red" ><B>${foot_auth}</B></td>
										<td align='left' style="text-align:left;color: red"><B>${totalSP}</B></td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>	
						<div class="col s12" id="tableStdPerson" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: none;">
					<span id="ip"></span>
	    				<h3 class="heading_content_inner">Standard Authorisation Of Personnel <i class="fa fa-file-excel-o" aria-hidden="true" onclick="tableToExcel('getStdPersonSearchReport', 'Personnel')" title="Export to Excel"></i></h3> 
							<table id="getStdPersonSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr id="tbpers" style="display:none;">
										<th colspan="3" >SUS NO : <lable id="psus_no"></lable></th>
										<th colspan="4" >MISO WE/PE NO : <lable id="pwepe_no"></lable></th>
									</tr>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Personnel Category</th>
										<th >Category</th>
										<th >Appt/Trade</th>
										<th >Rank</th>
										<th >Arm</th>
										<th >Base Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list10}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.pers_cat}</td>
											<td >${item1.rank}</td>
											<td >${item1.appt_trade}</td>
											<td >${item1.rank}</td>
											<td >${item1.arm}</td>
											<td >${item1.auth_amt}</td>
										</tr>		
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						<div class="col s12" id="tableStdPersonMod" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Authorisation of Personnel Under Modification</h3>
							<table id="getStdPersonModeSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Personnel Category</th>
										<th >Category</th>
										<th >Appt/Trade</th>
										<th >Rank</th>
										<th >Arm</th>
										<th >Modification</th>
										<th >Base Authorisation</th>
										<th >Inc/Dec Authorisation</th>
									</tr>
								<tbody>
									<c:forEach var="item1" items="${list11}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.cat_per}</td>
											<td >${item1.rank_cat}</td>
											<td >${item1.app_trade}</td>
											<td >${item1.rank}</td>
											<td >${item1.parent_arm}</td>
											<td >${item1.modification}</td>
											<td >${item1.base_amt}</td>
											<td >${item1.amt_inc_dec}</td>
										</tr>	
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>	
						<div class="col s12" id="tableStdPersonfot" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Inc/Dec General Notes Of Personnel</h3>
							<table id="getStdPersonfotnoteSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>									
										<th >Personnel Category</th>
										<th >Category</th>
										<th >Appt/Trade</th>
										<th >Rank</th>
										<th >Arm</th>
										<th >Gen Notes Condition</th>
										<th >Base Authorisation</th>
										<th >Inc/Dec Authorisation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item1" items="${list12}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item1.cat_per}</td>
											<td >${item1.rank_cat}</td>
											<td >${item1.app_trade}</td>
											<td >${item1.rank}</td>
											<td >${item1.parent_arm}</td>
											<td >${item1.condition}</td>
											<td >${item1.base_amt}</td>
											<td >${item1.amt_inc_dec}</td>
										</tr>	
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>	

		</div>
<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true"  data-backdrop="static">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h4 class="modal-title">Download</h4>
      <button type="button" class="close" data-dismiss="modal">&times;</button>
    </div>
    <div class="modal-body">
    	<div class="form-group">	 
			<div class="col-md-12" >		
				<div class="row" style="font-size: 16px; font-weight: bold;">
					<label style="font-size: 18px; font-weight: bold; text-decoration: center; color: blue;">WE/PE Document</label>
				</div>
				<div class="row" style="font-size: 16px; font-weight: bold;">
					<label style="font-size: 16px;">Table Title :</label>
					<label id="table_title" style="color: maroon;"></label>&nbsp;&nbsp;&nbsp;
					<a href='#' onclick="getDownloadImageTrans(we_pe_no);" >Download</a>
					<div class="col-md-12"><span class="line"></span></div>
				</div>
				
				<div class="row" style="font-size: 16px; font-weight: bold;">
				<label style="font-size: 18px; font-weight: bold; text-decoration: center; color: blue;">WE/PE Amendment Document</label></div>
				<div class="row" style="font-size: 16px; font-weight: bold;">
				<label style="font-size: 16px;">Table Title :</label>
				<label id="table_title1" style="color: maroon;"></label>&nbsp;&nbsp;&nbsp;
				<a href='#' onclick="getDownloadImageTransAmdt(we_pe_no)" >Download</a>
				<div class="col-md-12"><span class="line"></span></div>
				</div>
				
				<div id="wetpetdwnld" style="display: none;">
				<div class="row" style="font-size: 16px; font-weight: bold;">
				<label style="font-size: 18px; font-weight: bold; text-decoration: center; color: blue;">WET/PET Document</label></div>
				<div class="row" style="font-size: 16px; font-weight: bold;">
				<label style="font-size: 16px;">Table Title :</label>
			 	<label id="table_title2" style="color: maroon;"></label>&nbsp;&nbsp;&nbsp;
			 	<a href='#' onclick="getDownloadImageWetPetDoc(wet_pet_no)" >Download</a>
			 	<div class="col-md-12"><span class="line"></span></div>
			 	</div></div>
				
				<div  id="wetpetamdtdwnld" style="display: none;">
				<div class="row" style="font-size: 16px; font-weight: bold;">
				<label style="font-size: 18px; font-weight: bold; text-decoration: center; color: blue;">WET/PET Amendment Document</label></div>
				<div class="row" style="font-size: 16px; font-weight: bold;">
				<label style="font-size: 16px;">Table Title :</label>
				<label id="table_title3" style="color: maroon;"></label>&nbsp;&nbsp;&nbsp;
				<a href='#' onclick="getDownloadImageAmdtWetPetDoc(wet_pet_no)" >Download</a>
	       		</div></div>
	       	</div>
		</div>		 
    </div>
   
    <div class="modal-footer">
      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
    </div>
    
  </div>
</div>
</div>
</form:form>

		<c:url value="getViewTransEntitlementDtl1" var="searchUrl" />
  		<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
  			<input type="hidden" name="sus_no01" id="sus_no01" value="0"/>
  			<input type="hidden" name="type_of_cue01" id="type_of_cue01" value="0"/>
  			<input type="hidden" name="we_pe_no01" id="we_pe_no01" value="0"/>
  		</form:form> 
      	<c:url value="getDownloadImageTrans" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="we_pe_no1">
        	<input type="hidden" name="we_pe_no1" id="we_pe_no1" value=""/>
        	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
        	<input type="hidden" name="contraint" id="contraint" value="WE/PE Document"/>
        	<input type="hidden" name="sus_no2" id="sus_no2" value="0"/>
   			<input type="hidden" name="type_of_cue1" id="type_of_cue1" value="0"/>
        </form:form>
        
       <c:url value="getDownloadImageTransAmdt" var="imageDownloadUrl1" />
        <form:form action="${imageDownloadUrl1}" method="post" id="getDownloadImageFormAmdt" name="getDownloadImageFormAmdt" modelAttribute="we_pe_no2">
        	<input type="hidden" name="we_pe_no2" id="we_pe_no2" value=""/>
        	<input type="hidden" name="pageUrl2" id="pageUrl2" value=""/>
        	<input type="hidden" name="contraint2" id="contraint2" value=""/>
        	<input type="hidden" name="sus_no3" id="sus_no3" value="0"/>
   			<input type="hidden" name="type_of_cue3" id="type_of_cue3" value="0"/>
        </form:form>

 		<c:url value="getDownloadImageWetPetDoc" var="imageDownloadUrl2" />
        <form:form action="${imageDownloadUrl2}" method="post" id="getDownloadImageFormWetPet" name="getDownloadImageFormWetPet" modelAttribute="wet_pet_no1">
        	<input type="hidden" name="wet_pet_no1" id="wet_pet_no1" value=""/>
        	<input type="hidden" name="pageUrl3" id="pageUrl3" value=""/>
        	<input type="hidden" name="contraint3" id="contraint3" value=""/>
        	<input type="hidden" name="sus_no4" id="sus_no4" value="0"/>
   			<input type="hidden" name="type_of_cue4" id="type_of_cue4" value="0"/>
        </form:form>
        
        <c:url value="getDownloadImageAmdtWetPetDoc" var="imageDownloadUrl3" />
        <form:form action="${imageDownloadUrl3}" method="post" id="getDownloadImageFormAmdtWetPet" name="getDownloadImageFormAmdtWetPet" modelAttribute="wet_pet_no2">
        	<input type="hidden" name="wet_pet_no2" id="wet_pet_no2" value=""/>
        	<input type="hidden" name="pageUrl4" id="pageUrl4" value=""/>
        	<input type="hidden" name="contraint4" id="contraint4" value=""/>
        	<input type="hidden" name="sus_no5" id="sus_no5" value="0"/>
   			<input type="hidden" name="type_of_cue5" id="type_of_cue5" value="0"/>
        </form:form>

	<div class="modal fade" id="rejectModal_ces" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
	      	<div class="modal-content">
		        <div class="modal-header">
		          <h4 class="modal-title">CES/CCES/ETS Details</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
				<div class="modal-body">
					<div class="form-group">	 
						<div class="col-md-12">			
							<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
								<div class="col-sm-12">	
									<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
									<input id="rejectid_model1" name="rejectid_model1" placeholder="" class="form-control" type ="hidden" >			 
							  		<table class="table no-margin table-striped  table-hover  table-bordered" id="addQuantity">
						            	<thead>
						               	</thead>
						                <tbody>
						               	</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>		 
			        <div class="modal-footer">
			          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			        </div>
				</div>
			</div>
		</div>
	</div>    
<script>
function Search(){
	if($("#sus_no").val()==""){
		alert("Please Enter SUS No");
		$("#sus_no").focus();
		return false;
	} 
	if($("select#type_of_cue").val()=="0" || $("select#type_of_cue").val()==""){
		alert("Please Select Type")
		$("select#type_of_cue").focus();
		return false;
	} 
	$("#sus_no01").val($("#sus_no").val());
	$("#type_of_cue01").val($("#type_of_cue").val());
	$("#we_pe_no01").val($("#we_pe_no").val());
	document.getElementById('searchForm').submit();
}

function Reject12(ces_cces_no,total){
	document.getElementById('rejectid_model').value=ces_cces_no;
	document.getElementById('rejectid_model1').value=total;
	var id = document.getElementById('rejectid_model').value;

	$("table#addQuantity tbody").empty();
	
	$.post("ces_cess_popup?"+key+"="+value, {id:id}).done(function(k) {
    	$("table#addQuantity").append('<tr><th>Main Item</th><td colspan="4">'+k[0][3]+'</label></td></tr>');
		$("table#addQuantity").append('<tr><th>Ser No</th><th>Item Code</th><th>Sub Nomenclature</th><th>Proposed Auth</th><th>Total</th></tr>');
		for ( var x = 0; x < k.length; x++) {
			var j;
			j = (k[x][0] * total);
			$("table#addQuantity").append('<tr><td>'+(x+1)+'</td><td><label>'+k[x][2]+'</label></td><td><label>'+k[x][4]+'</label></td><td><label>'+k[x][0]+'</label></td><td><label>'+j+'</label></td></tr>'); 
		}

   }).fail(function(xhr, textStatus, errorThrown) { }); 
} 
 
 function we_pe_doc_down()
 {
	var we_pe_no = document.getElementById("we_pe_no").value;
	
	$.post("getTableTitlewepe?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
		$("#table_title").text(j);
	}).fail(function(xhr, textStatus, errorThrown) { });
	
	$.post("getTableTitlewepeamdt?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
		$("#table_title1").text(j);
	}).fail(function(xhr, textStatus, errorThrown) { });  
		
	var wet_pet_no = document.getElementById("wet_pet_no").value;

	$.post("getTableTitlewtpet?"+key+"="+value, {wet_pet_no:wet_pet_no}).done(function(j) {
		$("#table_title2").text(j);
	}).fail(function(xhr, textStatus, errorThrown) { });
	
	$.post("getTableTitlewtpetamdt?"+key+"="+value, {wet_pet_no:wet_pet_no}).done(function(j) {
		$("#table_title3").text(j);
	}).fail(function(xhr, textStatus, errorThrown) { }); 
	download_hide_show();
 }

</script>


<script>
var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
		  
		if(name == 'Personnel'){
			$("tr#tbpers").show();
			$("lable#psus_no").text($("#sus_no").val());
			$("lable#pwepe_no").text($("#we_pe_no").val());
		}else if(name == 'Weapon'){
			$("tr#tbwep").show();
			$("lable#wsus_no").text($("#sus_no").val());
			$("lable#wwepe_no").text($("#we_pe_no").val());
		}else if(name == 'Transport'){
			$("tr#tbtrans").show();
			$("lable#tsus_no").text($("#sus_no").val());
			$("lable#twepe_no").text($("#we_pe_no").val());
		}
		
		if (!table.nodeType) table = document.getElementById(table)
		var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	    $("tr#tbpers").hide();
		$("tr#tbwep").hide();
		$("tr#tbtrans").hide();
	  }
	})()
function toHex(str) {
	var hex = '';
	for(var i=0;i<str.length;i++) {
		hex += ''+str.charCodeAt(i).toString(16);
	}
   	return hex;    		
}
</script>

<script>
function printDiv(divName) {
	
	 document.getElementById('printableArea').style.display='block'; 
	 document.getElementById('printableArea2').style.display='none';
	 
	$("div#printableArea").empty();
	$("div#printableArea").show();	
		
	var row="";
	row +='<div class="print_content"> ';
	row +='<div class="print_logo"> ';
	row +='<div class="row"> ';
	 	row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
	 	row +='<div class="col-6 col-sm-6 col-md-6"><h3>SUMMARY OF PERSONNEL/TRANSPORT/WEAPON</h3> </div> ';
	 	row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
	 	row +='</div> ';
	 	row +='</div> ';
	 	row +='	<div class="print_text"> ';
	 	row +='<div class="row"> ';
	 	row +='<div class="col-12 col-sm-6 col-md-3"><label class=" label_left form-control-label" id="lbluc"> SUS No :</label> ';
	 	row +='<label  id="we_pe_nolbl" class="label_right">'+document.getElementById('sus_no').value+'</label> </div>';
	 	row +='<div class="col-12 col-sm-6 col-md-3"><label class="label_left form-control-label" id="lbluc"> Unit Name :</label> ';
	 	row +='<label  id="we_pe_nolbl" class="label_right">'+document.getElementById('unit_name').value+'</label> </div>';
		 	
	 	if(document.getElementById('location').value != "" && document.getElementById('location').value != null){
		 	row +='<div class="col-12 col-sm-6 col-md-3"><label class="label_left form-control-label" id="lbluc"> Location :</label> ';
		 	row +='<label  id="we_pe_nolbl" class="label_right">'+document.getElementById('location').value+'</label> </div>';
	 	}
		 	
	 	if(document.getElementById('ctpart').value != "" && document.getElementById('ctpart').value != null){
		 	row +='<div class="col-12 col-sm-6 col-md-3"><label class="label_left form-control-label" id="lbluc">CT Part I/II :</label> ';
		 	row +='<label  id="we_pe_nolbl" class="label_right">'+document.getElementById('ctpart').value+'</label> </div>';
	 	}
		 	
	 	if(document.getElementById('we_pe_no').value != "" && document.getElementById('we_pe_no').value != null){
		  	row +='<div class="col-12 col-sm-6 col-md-3"><label class="label_left form-control-label" id="lbluc"> MISO WE/PE No :</label> ';
		 	row +='<label  id="we_pe_nolbl" class="label_right">'+document.getElementById('we_pe_no').value+'</label></div>';
	 	}
		 	
	 	if(document.getElementById('wet_pet_no').value != "" && document.getElementById('wet_pet_no').value != null){
		 	row +='<div class="col-12 col-sm-6 col-md-3"><label class="label_left form-control-label" id="lbluc"> WET/PET No :</label> ';
		 	row +='<label  id="we_pe_nolbl" class="label_right">'+document.getElementById('wet_pet_no').value+'</label></div>'; 
	 	}
		 	
	 	if(document.getElementById('type_of_cue').value !="0"){
	 		var a = document.getElementById('type_of_cue').value ;
	 		var b = "";
	 		if(a==3)
	 			b="Personnel";
	 		if(a==2)
	 		 	b="Weapon"; 
	 		if(a==1)
	 			 b="Transport";
	 		
		 	row +='<div class="col-12 col-sm-6 col-md-3"><label class="label_left form-control-label" id="lbluc"> Type :</label> ';
		 	row +='<label  id="we_pe_nolbl" class="label_right">'+b+'</label></div>'; 
	 	}
		 	
	 	row +='</div>	';
	 	row +='	</div> ';
	 	row +=' </div>  ';
	 	$("div#printableArea").append(row); 
	 
	   let popupWinindow
	   let innerContents = document.getElementById('printableArea1').innerHTML;
	   popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	   popupWinindow.document.open();
	   popupWinindow.oncontextmenu = function () {
		 	 return false;
		 }

	   ///popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()"  oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' + innerContents + '</html>');
		popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style>table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   	watermarkreport();
	   	popupWinindow.document.close();	   
    	document.getElementById('printableArea').style.display='none'; 
	 	document.getElementById('printableArea2').style.display='block'; 
} 
function clearAll()
{	
	document.getElementById("printId").disabled = true;
	clearAllTableDiv();	    			
	//document.location.reload();
 	localStorage.clear();
	localStorage.Abandon();
}
</script>

<script>

$(function() {
	
	if('${roleAccess}' != "Unit" && '${roleAccess}' != "Line_dte")
	{  console.log("1");
		var wepetext=$("#unit_name");
		
   	  	wepetext.autocomplete({
		source: function( request, response ) {
   	        $.ajax({
   	        type: 'POST',
    	    url: "getUnitsNameListActive?"+key+"="+value,
   	        data: {unit_name:document.getElementById('unit_name').value},
   	          success: function( data ) {
   	            //response( data );
   	            if(data.length > 1){
   	        	var susval = [];
   	        	var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                 for(var i = 0;i<data.length-1;i++){
                  susval.push(dec(enc,data[i].columnName));
       	  		}
		        	var dataCountry1 = susval.join("|");
   	            var myResponse = []; 
   	            var autoTextVal=wepetext.val();
   				$.each(dataCountry1.toString().split("|"), function(i,e){
   					//var newE = e.substring(0, autoTextVal.length);
   					//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
   					
   					  myResponse.push(e);
   					//}
   				});      	          
   				response( myResponse ); 
   	            }
   	          }
   	        });
   	      },
   	      minLength: 1,
   	      autoFill: true,
   	      change: function(event, ui) {
   	    	 if (ui.item) {   	        	  
   	        	  return true;    	            
   	          } else {
   	        	  alert("Please Enter Approved Unit Name");
   	        	  $("#sus_no").val("");
   	        	  wepetext.val("");	        	  
   	        	  wepetext.focus();
   	        	  return false;	             
   	          }   	         
   	      }, 
   	         select: function( event, ui ) {
   	        	$(this).val(ui.item.value);    	        	
   	        	getSusnoByName($(this).val());	   
   	        	getCodeByName($(this).val());
   	        	
   	        } 	     
   	    });
       
   	  $.ajaxSetup({
   		    async: false
   		});
   	  
   	  wepetext1=$("#sus_no");
   	  	wepetext1.autocomplete({
   	      source: function( request, response ) {
   	        $.ajax({
   	        type: 'POST',
    	    url: "getSusNoListActive?"+key+"="+value,
   	        data: {sus_no:document.getElementById('sus_no').value},
   	          success: function( data ) {
   	            //response( data );
   	            if(data.length > 1){
   	        	var susval = [];
   	        	var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                 for(var i = 0;i<data.length-1;i++){
                  susval.push(dec(enc,data[i].columnName));
       	  			}
		        	var dataCountry1 = susval.join("|");
   	            var myResponse = []; 
   	            var autoTextVal=wepetext1.val();
   				$.each(dataCountry1.toString().split("|"), function(i,e){
   					var newE = e.substring(0, autoTextVal.length);
   					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
   					
					myResponse.push(e);
   					}
   				});    
   				response( myResponse ); 
   	            }
   	          }
   	        });
   	      },
   	      minLength: 1,
   	      autoFill: true,
   	      change: function(event, ui) {
   	    	 if (ui.item) {   	        	  
   	        	  return true;    	            
   	          } else {
   	        	  alert("Please Enter Approved SUS No");
   	        	  $("#unit_name").val("");
   	        	  wepetext1.val("");	        	  
   	        	  wepetext1.focus();
   	        	  return false;	             
   	          }   	         
   	      }, 
   	       select: function( event, ui ) {
   	        	$(this).val(ui.item.value);    	        	
   	        	getNameBySusno($(this).val());	
   	        	getOrbatDetails($(this).val());
   	        } 	     
   	    });	
   	  
	} else{
    	//$("#sus_no").attr("readonly","readonly");
    	//$("#unit_name").attr("readonly","readonly");
    } 
});		

function getSusnoByName(val)
{
	if(val != "" || val != null){
			var unitName=val;
			
			  $.post("getUnitDetailsListActive?"+key+"="+value, {unitName:unitName}).done(function(j) {
				  for ( var i = 0; i < j.length; i++) {
				 		$("#sus_no").val(j[0]);
					}
			 }).fail(function(xhr, textStatus, errorThrown) { });
			$.ajaxSetup({
			    async: false
			});
			$.post("getViewUnitDtlTransReport?"+key+"="+value, {sus_no: $("#sus_no").val()}).done(function(j) {
				 if(j!="" && j!= null){
						$("input#ctpart").val(j[0][0]);	
						$("input#location").val(j[0][1]);	
					}
					else
					{
						$("input#ctpart").val("");	
						$("input#location").val("");	
					}
			 }).fail(function(xhr, textStatus, errorThrown) { }); 
			$.ajaxSetup({
			    async: false
			});
			if($("select#type_of_cue").val() !="0")
				OnchangeOfTypeOfRole($("select#type_of_cue").val());
		}
		
	}		
		
function getNameBySusno(val)
{
	if(val != "" || val != null){
		var sus_no=val;
		
	 	 $.post("getSusNoDTLList?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
	 		$("#unit_name").val(j);	
	 	}).fail(function(xhr, textStatus, errorThrown) { }); 
		$.ajaxSetup({
		    async: false
		});
		$.post("getViewUnitDtlTransReport?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
			if(j!="" && j!= null){
				$("input#ctpart").val(j[0][0]);	
				$("input#location").val(j[0][1]);	
			}
			else
			{
				$("input#ctpart").val("");	
				$("input#location").val("");	
			}
	 	}).fail(function(xhr, textStatus, errorThrown) { }); 
		
		$.ajaxSetup({
		    async: false
		});
		if($("select#type_of_cue").val() !="0")
			OnchangeOfTypeOfRole($("select#type_of_cue").val());
	}
}		
		
function OnchangeOfTypeOfRole(val)
{
	//we_pe_no_hide_show();	
	var sus_no = $("input#sus_no").val();
	var role = val;  //	$("select#type_of_cue").val();
	$("#we_pe_no").val("");
	$("#wet_pet_no").val("");
	if(sus_no !="" && sus_no !=null ){
	
	  $.post("getSearchsusbywepenoDetailsList?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
		  if(j[0]!='undefined' && j[0]!=null)
			{
				if(role == 1)
				{
					$("#we_pe_no").val(j[0][1]);
					$("div#wet_pet_no_sh").hide();
					document.getElementById("getTypeOnclick").value = '2'; // for TRANSPORT value is 2
				}
				if(role == 2)
				{
					$("#we_pe_no").val(j[0][2]);
					$("div#wet_pet_no_sh").show();
					var x = document.getElementById("getTypeOnclick").value;
					x= '3'; // for WEAPON value is 3
					if($("#we_pe_no").val() !="" && $("#we_pe_no").val() != null)
					{ 
						$.post("getDetailsByWePeCondiNox?"+key+"="+value, {val : $("#we_pe_no").val(), x : "3"}).done(function(j) {
							 if(j!="" && j!= null)
									$("#wet_pet_no").val(j[0]);
								else
									$("#wet_pet_no").val("");

						 }).fail(function(xhr, textStatus, errorThrown) { }); 
						
					}
				}
				if(role == 3)
				{	
					$("#we_pe_no").val(j[0][0]);	
					$("div#wet_pet_no_sh").hide();
					document.getElementById("getTypeOnclick").value = '1'; //for PERSONNEL value is 1
				}
			}
			we_pe_no_hide_show();

	 }).fail(function(xhr, textStatus, errorThrown) { }); 
	}
	else
	{
		alert("Please enter SUS No");
		$("select#type_of_cue").val(0);
		$("input#sus_no").focus();
		return false;
	}
}
function getView()
{
	if(document.getElementById('PrintViewSelection').style.display=='none') 
	{ 
		document.getElementById('PrintViewSelection').style.display='block'; 
		document.getElementById('mainViewSelection').style.display='none'; 
		document.getElementById('btnhide').style.display='none';		               
	} 
	else
	{
		document.getElementById('mainViewSelection').style.display='block'; 
	}				 
	window.print();
}		
				
function clearAllTableDiv()
{
	var tab = $("#getSearchReport > tbody");
	tab.empty(); 		        
	var tab1 = $("#getStdTransSearchReport > tbody");
	tab1.empty(); 		        
	var tab11 = $("#getStdTransModeSearchReport > tbody");
	tab11.empty();			        
	var tab111 = $("#getStdTransfotnoteSearchReport > tbody");
	tab111.empty(); 
       
	var tab112 = $("#getStdTransInlieuSearchReport > tbody");
	tab112.empty(); 
       
	var tab2 = $("#getSearchReportgetWeapon > tbody");
	tab2.empty(); 		        
	var tab22 = $("#getStdWaeponSearchReport > tbody");
	tab22.empty();			        
	var tab222 = $("#getStdWaeponModeSearchReport > tbody");
	tab222.empty();			        
	var tab2222 = $("#getStdWaeponfotnoteSearchReport > tbody");
	tab2222.empty(); 
       
	var tab3 = $("#getSearchReportgetPerson > tbody");
    tab3.empty(); 			        
    var tab33 = $("#getStdPersonSearchReport > tbody");
    tab33.empty();			        
    var tab333 = $("#getStdPersonModeSearchReport > tbody");
    tab333.empty(); 			        
    var tab31 = $("#getStdPersonfotnoteSearchReport > tbody");
    tab31.empty(); 			        			      
    var tab32 = $("#getSearchUnitDtlPersReport > tbody");
    tab32.empty();			        
       
    document.getElementById('tableshow').style.display='none'; 
    document.getElementById('tableStdTrans').style.display='none'; 
  	document.getElementById('tableStdTransMod').style.display='none'; 
  	document.getElementById('tableStdTransfot').style.display='none'; 
 	document.getElementById('tableStdTransInLu').style.display='none'; 
  	
	document.getElementById('tableshowwaepon').style.display='none'; 
    document.getElementById('tableStdWaepon').style.display='none'; 
    document.getElementById('tableStdWaeponMod').style.display='none'; 
    document.getElementById('tableStdWaeponfot').style.display='none'; 
    document.getElementById('tableStdWaeponces').style.display='none'; 


	document.getElementById('tableshowUnitDtlPers').style.display='none'; 		    			      
	document.getElementById('tableshowPerson').style.display='none'; 	    			 	   
	document.getElementById('tableStdPerson').style.display='none';  			       
	document.getElementById('tableStdPersonMod').style.display='none'; 		    			      
	document.getElementById('tableStdPersonfot').style.display='none'; 	
}
			
function getViewReportList(){ 	
	 watermarkreport();
	 var s ;
	 if($("#sus_no").val() == "")
		 s = $("input#sus_no1").val();
	 else
		 s = $("#sus_no").val();
	 
	if(s == "" ){
		alert("Please Enter the SUS No");
   		$("#sus_no").focus();
   		return false;
   	} 
  	var unit_name = $("#unit_name").val();
   	if( $("input#sus_no1").val() == "" ){
      	var sus_no = $("#sus_no").val();
   	}
   	else
   		var sus_no = $("input#sus_no1").val();
    	  
   	clearAllTableDiv();	        
	        
	var e = document.getElementById("type_of_cue");
    strtypeOfcue = e.options[e.selectedIndex].text;
    		
    document.getElementById("printId").disabled = false;
    if(strtypeOfcue  == 'Transport')		
  	{
  		getTransportDetails(sus_no);	    				
 	}
  	else if(strtypeOfcue == 'Weapon')
  	{	    				
  		getWeaponDetails(sus_no);	    			         
  	}
  	else if(strtypeOfcue == 'Personnel')
  	{
  		getPersonnelDetails(sus_no);
  	}		    		
  	else
  	{		    			
  		getTransportDetails(sus_no);
  		$.ajaxSetup({
  		    async: false
  		});
  		getWeaponDetails(sus_no);
  		$.ajaxSetup({
  		    async: false
  		});
  		getPersonnelDetails(sus_no);
  	}
}	
</script>   
<script>
function we_pe_no_hide_show()
{  
	var we_pe_no = document.getElementById("we_pe_no").value;
	if(we_pe_no != null && we_pe_no != '' && we_pe_no != "" && we_pe_no != undefined ){
		$("#rejectModal12").show();
	}
	else{
		$("#rejectModal12").hide();
	}
}
function getDownloadImageTrans(we_pe_no) 
{	
	we_pe_no = document.getElementById("we_pe_no").value;
	document.getElementById('we_pe_no1').value=we_pe_no;
	$("#sus_no2").val($("#sus_no").val());
	$("#type_of_cue1").val($("#type_of_cue").val());
	document.getElementById("contraint").value="";
	document.getElementById("pageUrl").value="viewstandardEntitlementForTransportTiles";
	document.getElementById("getDownloadImageForm").submit();
}
function getDownloadImageTransAmdt(we_pe_no) 
{	
	we_pe_no = document.getElementById("we_pe_no").value;
	document.getElementById('we_pe_no2').value=we_pe_no;
	document.getElementById("contraint2").value="";
	$("#sus_no3").val($("#sus_no").val());
	$("#type_of_cue3").val($("#type_of_cue").val());
	document.getElementById("pageUrl2").value="viewstandardEntitlementForTransportTiles";
	document.getElementById("getDownloadImageFormAmdt").submit();  
}
function getDownloadImageWetPetDoc(wet_pet_no) 
{	
	wet_pet_no = document.getElementById("wet_pet_no").value;
	document.getElementById('wet_pet_no1').value=wet_pet_no;
	document.getElementById("contraint3").value="";
	$("#sus_no4").val($("#sus_no").val());
	$("#type_of_cue4").val($("#type_of_cue").val());
	document.getElementById("pageUrl3").value="viewstandardEntitlementForTransportTiles";
	document.getElementById("getDownloadImageFormWetPet").submit();  
}
function getDownloadImageAmdtWetPetDoc(wet_pet_no) 
{	
	wet_pet_no = document.getElementById("wet_pet_no").value;
	document.getElementById('wet_pet_no2').value=wet_pet_no;
	document.getElementById("contraint4").value="";
	$("#sus_no5").val($("#sus_no").val());
	$("#type_of_cue5").val($("#type_of_cue").val());
	document.getElementById("pageUrl4").value="viewstandardEntitlementForTransportTiles";
	document.getElementById("getDownloadImageFormAmdtWetPet").submit();  
}
function download_hide_show()
{
	var wet_pet_no = document.getElementById("wet_pet_no").value;
	if(wet_pet_no != null && wet_pet_no != '' && wet_pet_no != "" && wet_pet_no != undefined )
	{
		$("div#wetpetdwnld").show();
		$("div#wetpetamdtdwnld").show();
	}
	else{
		$("div#wetpetdwnld").hide();
		$("div#wetpetamdtdwnld").hide();
		
	}
} 



/// 26-01-1994

function getOrbatDetails(sus)
{
	
	var tab = $("#orbatLinkPers > tbody");
 	tab.empty();
 	
	
    $.post("getOrbatDetails?"+key+"="+value, {unit_sus_no:sus}).done(function(data) {
        	drawTable1(data); 
         }).fail(function(xhr, textStatus, errorThrown) { });  
            
	function drawTable1(data) {
		$("div#pers_orbat_div").show();
		if(data.length == 0)
		{
			var row = $("<tr />")
			$("#orbatLinkPers").append(row);
			row.append($("<td colspan='9' align='center'>Data Not Available...</td>"));
		}
		else{
			
			for (var i = 0; i < data.length; i++) {
				var row = $("<tr />")
				$("#orbatLinkPers").append(row);
				row.append($("<td style='width: 6%'>" + [i+1] + "</td>"));
				row.append($("<td>" + data[0].sus_no + "</td>"));
				row.append($("<td>" + data[0].unit_name + "</td>"));
				
				if( data[0].cmd_name  != null){
					row.append($("<td align='center'>" + data[0].cmd_name  + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].coprs_name != null){
					row.append($("<td align='center'>" + data[0].coprs_name + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].div_name != null){
					row.append($("<td align='center'>" + data[0].div_name + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].bde_name != null){ 
					row.append($("<td align='center'>" + data[0].bde_name + "</td>"));
				}else{
					row.append($("<td align='center'></td>"));
				}
				if( data[0].location != null){
					row.append($("<td align='center'>" + data[0].location + "</td>"));
				}else{
					row.append($("<td></td>"));
				}
				if( data[0].arm_desc != null){
					row.append($("<td align='center'>" + data[0].arm_desc + "</td>"));
				}else{
					row.append($("<td></td>"));
				}
			}
	
		//$('#orbatLinkPers').DataTable;
		} 
	}
}
function getCodeByName(val)
{	
	if(val != "" || val != null) {		
		var unit_name = val;
		 $.post("getCUEUnitDetailsList?"+key+"="+value, {unitName : unit_name }).done(function(j) {
	    	if(j!="" && j!=null){
				document.getElementById("sus_no").value=j[0].sus_no;
				getOrbatDetails(j[0].sus_no);
			}
			else
				document.getElementById("sus_no").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 	
	}
}


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
		        	  alert("Please Enter Approved SUS No.");
		        	  document.getElementById("sus_no").value="";
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	var sus_no = ui.item.value;
		    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
		    		var length = j.length-1;
				   	var enc = j[length].substring(0,16);
				   	jQuery("#unit_name").val(dec(enc,j[0]));	
		    	}).fail(function(xhr, textStatus, errorThrown) {
		    });
		} 	     
	});	
});
// End

// Source Unit Name
if('${roleAccess}' == "Line_dte"){
	debugger;
	console.log("2");
jQuery("#unit_name").keyup(function(){
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
			        	  document.getElementById("sus_no").value="";
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
}

</script>   