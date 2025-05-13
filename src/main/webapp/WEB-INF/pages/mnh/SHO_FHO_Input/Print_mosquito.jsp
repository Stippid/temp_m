<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>		
<script src="js/cue/cueWatermark.js"></script>


 <form:form name="Sho_input_mosquito_print" id="Sho_input_mosquito_print" action="Sho_input_mosquito_printAction" method='POST' commandName="Sho_input_mosquito_printCMD">
  <!-- <div id="print_all">
 <div id="divShow1" style="">
                 <div id="divShow" align="center" style="display: block;"></div> 
  --> 
  
    <!--  <div class="nkpageland" id="printableArea"> -->
    <div class="animated fadeIn"  id="printableArea">
       <div class="container" >
       <div id="divShow" style="display: block;">
     <!--   <div id="divShow1" align="center" style="display:block;"></div> -->
       <div class="col-md-12"  id="divPrint" style="display: block;" >
       <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
  
         
          <div class="card">
          
          <div class="card-header"  id="printableArea3" style="display: none;"> 
		   				<table class="col-md-12">
		   					<tr>
		   						<td align="left">
		   							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
		   						</td>
		   						<td align="center">
		   						<h6 ><b style="margin-left:10px;"><u>${list[0].repo_type}  </u></b><b><u style="margin-left:610px;">CASE NO: ${list[0].case_no}</u></b></h6>
                    <h5 align="center">EPIDEMIOLOGICAL INVESTIGATION REPORT OF  ${list[0].disease_name}
		           IN RESPECT OF  ${list[0].persnl_no},${list[0].personal_name} </h5>
		   						</td>
		   						<td align="right">
		   							<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
		   						</td>
		   					</tr>
		   				</table>
		   			</div>   	
          
              <div class="card-header" id = "div_show" style="display: none;">
              <h6 ><b style="margin-left:10px;"><u>${list[0].repo_type}  </u></b><b><u style="margin-left:610px;">CASE NO: ${list[0].case_no}</u></b></h6>
            </br>
              <h5 align="center">EPIDEMIOLOGICAL INVESTIGATION REPORT OF  ${list[0].disease_name}
		           IN RESPECT OF  ${list[0].persnl_no},${list[0].personal_name} </h5>
				
			</div>
			
	<div class="card-body card-block" >
			
					<!-- <div class="card-header-inner" id="aa" > <strong>1.Personnel Information of the Individual</strong></div>  -->
						<div class="row">
					      <label for="text-input" class=" form-control-label">
							<strong>1.Personnel Information of the Individual
						</strong>
							</label>
				</div>
			
			
			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label">(a) No, Rank & Name</label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">: ${list[0].persnl_no} ,${list[0].rank_desc} , ${list[0].personal_name}</label>
			</div>
			</div>
			  	  
  			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label">(b) Age</label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:${list[0].age}</label>
			</div>
			</div> 
			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label">(c) Unit</label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">: ${list[0].personnel_unit}</label>
			</div>
			</div> 
			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label">(d) Present Address</label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:  ${list[0].present_address}</label>
			</div>
			</div>  
			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label">(e) Contact No</label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">: ${list[0].contact_no}</label>
			</div>
			</div>  
			<!-- <div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label"><strong>2.Type Of First Symptom</strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">: </label>
			</div>
			</div>  --> 	
			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label"><strong>2.Onset of Symptoms</strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:  ${list[0].date_onset_symp} </label>
			</div>
			</div>  	
			<div class="row">
			<div class="col-4">
				<label for="text-input" class=" form-control-label"><strong>3.Date & Time Of Reporting</strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:  ${list[0].date_repo_med_centre} </label>
			</div>
			</div>  
				
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>4.Brief History of the case</strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:  ${list[0].history}</label>
			</div>
			
			</div>
			<%-- <div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>6.Period Of Infectivity Of THis Case</strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:${list[0].ip_start_date} TO  ${list[0].ip_end_date}</label>
			</div>
			</div> --%>
			<!-- <div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>7.Incubation Period (IP) of the case </strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div> -->
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>5.Details Of Travel History </strong></label>
			</div>
			
			<div class="col-8" >
	             <label for="text-input" class=" form-control-label">: ${list[0].ip_start_date} TO  ${list[0].ip_end_date}</label>
			</div>
			</div>
			<div class="row" style="margin-top: 20px">
			<div class="col-8"  >
				<label for="text-input" class=" form-control-label">(a)The date and time wise breakdown of patient's stay:</label>
			</div>
			</div>		
				
			<div class="container"   id="divPrint" >
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead style="text-align: center;">
							<tr>
							<th>Date</th>
							<th colspan="3" >Time</th>  
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty list}">
								<c:forEach var="item" items="${list}" varStatus="num">
									<tr>
									<td style="text-align: center;">${item.ip_date}</td>
									<td>${item.time1} </br> ${item.loc1}</td>
									<td>${item.time2} </br> ${item.loc2}</td>
									<td>${item.time3} onwards</br> ${item.loc3}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
							<c:if test="${list.size()==0}">
								<tr>
								<td style=" text-align: center; color: red;" colspan="3">Data Not Available</td>
								</tr>
							</c:if>
					</table>
				</div>
			</div>
			
			<div class="row" style="margin-top: 20px">
			<div class="col-4"   >
				<label for="text-input" class=" form-control-label"><strong>6.List Of Close Contacts  </strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">: </label>
			</div>
			</div>	
			<div class="row">
			<div class="col-8"  >
				<label for="text-input" class=" form-control-label">(a)List of close contact as per above investigation :</label>
			</div>
			</div>
			
			<div class="container" id="divPrint"  >
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="SearchReport"  class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr style=" text-align: center">
						<th width="55px" >Ser No</th>
						<th>Name Of Close contact</th>
						<th>Date Of Exposure</th>
						<th>Remarks</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty list}">
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
							<td width="55px">${num.index+1}</td>
							<td></td>
							<td></td>
							<td></td>
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
					<c:if test="${list.size()==0}">
						<tr>
						<td style=" text-align: center; color: red;"colspan="4">Data Not Available</td>
						</tr>
					</c:if>
				</table>
			</div>
			</div>
			
			
			
			<div class="row" style="margin-top: 20px">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>7.Epidemiological Diagnosis</strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:${list[0].disease_name} ,${list[0].sys_code_desc}</label>
			</div>
			</div>
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>8.Action Undertaken </strong></label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div>
				
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(a) For Patient</label>
			</div>
			<div class="col-8">
	             <label for="text-input" class=" form-control-label">:${list[0].action_for_patient}</label>
			</div>
			</div>
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(b) For Close Contacts:</label>
			</div>
			<!-- <div class="col-md-8">
	             <label for="text-input" class=" form-control-label">:The Offr i/c of the section  intimated about the close contact and advised to segregate 
				 them till the end of the incubation period (here 14 days or two weeks). These are as follows:</label>
			</div> -->
			</div>	
			<div class="row">
			
			<div class="col-md-12">
	             <label for="text-input" class=" form-control-label">The Offr i/c of the section  intimated about the close contact and advised to segregate 
							them till the end of the incubation period (here 14 days or two weeks). These are as follows:</label>
			</div>
			</div>	
			
			<div class="container" id="divPrint"  >
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="SearchReport" 
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead>
						<tr style=" text-align: center">
							<th width="55px">Ser No</th>
							<th>Name Of Close contact</th>
							<th>Date Of Exposure</th>
							<th>Period Of segregation</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty list}">
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
							<td width="55px">${num.index+1}</td>
							<td></td>
							<td> </td>
							<td></td>
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
					<c:if test="${list.size()==0}">
						<tr>
						<td style=" text-align: center; color: red;" colspan="4">Data Not Available</td>
						</tr>
					</c:if>
				</table>
			</div>
			</div>
			
			<!-- <div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(c)Action in the Patient's room</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div> -->	
			<!-- <div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(i)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label"></label>
			</div>
			</div>	
			
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(ii)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label"></label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(iii)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label"></label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(d)Action in the Segregation Room :</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(i)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label"></label>
			</div>
			</div>	
			
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(ii)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label"></label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(iii)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label"></label>
			</div>
			</div>				 -->
			
			<!-- <div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>12. Certificate of infection free</strong></label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div> -->	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>9. Recommendation for the indl:barrack/married accm</strong></label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(a)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div>				
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">(b)</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><strong>10.Recommendation for the Unit</label>
			</div>
		    <div class="col-8">
	             <label for="text-input" class=" form-control-label">:</label>
			</div>
			</div>			
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">Date:</label>
			</div>
		    <div class="col-8" style="text-align: right;">
	             <label for="text-input"  class=" form-control-label">(signature of Med Offr)</label>
			</div>
			</div>				
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label"><b><u>Copy to:</u></b></label>
			</div>
		    <div class="col-8" style="text-align: right;">
	             <label for="text-input"  class=" form-control-label"></label>
			</div>
			</div>	
			<div class="row">
			<div class="col-4"  >
				<label for="text-input" class=" form-control-label">CO,unit</label>
			</div>
		    <div class="col-8" style="text-align: right;">
	             <label for="text-input"  class=" form-control-label">For info please</label>
			</div>
			</div>			
	</div>
		 	<div class="form-control card-footer" align="center" id = "footer_div">			  
            	 <a href="Search_mnh_inputs_mosq_food_air" class="btn btn-danger btn-sm" id="btn_cancl" onClick="javascript:window.close('','_parent','');">  Cancel </a>
	         	<input type="button" id="btn_update" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')" /> 
         	</div> 
    </div>	
   </div>
  </div>
 </div> 
</div>
</div>
</form:form>

<script>

 function printDiv(divName) {
	
   /*   $("div#divwatermark").val('').addClass('watermarked');        
     watermarkreport();
     let popupWinindow
     let innerContents = document.getElementById('printableArea').innerHTML;
     popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
     popupWinindow.document.open();
     popupWinindow.oncontextmenu = function () {
             return false;
     }
     popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:12px  !important;} table th{font-size:12px !important;} table thead{background-color:#9c27b0 !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
 */ //popupWinindow.document.close();
 
     //window.print();
     
      $("#div_show").hide() ;
	  $("#printableArea").show() ;
		$("#printableArea3").show() ;
		
		 $("#footer_div").hide() ;
		
		let popupWinindow
		let innerContents = document.getElementById(divName).innerHTML;
		popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWinindow.document.open();
		popupWinindow.oncontextmenu = function () {
			return false;
		}
		//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:8px;} table th{font-size:9px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   	popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		
		///watermarkreport();
	    popupWinindow.document.close();
	    $("#footer_div").show() ;
}

$(document).ready(function() {
	 /*  var date_of_birth = '${Sho_input_mosquito_printCMD.date_of_birth}';
	date_of_birth= date_of_birth.substring(0,10);
	
	$("#date_of_birth1").val(date_of_birth);
	calculate_age_edit('${Sho_input_mosquito_printCMD.date_of_birth}');
	
  */
 
  
  
  
  
  $("#div_show").show() ;
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("&msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}
});

</script>

<script>

function btn_clc(){
	location.reload(true);
}

function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
		}else{
			if(charCode == 46){
				return false;
				}
			}
	return true;
	}

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";
	
</script>

