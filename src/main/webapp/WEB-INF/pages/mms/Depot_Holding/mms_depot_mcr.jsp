<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 
<link rel="stylesheet" href="js/common/nrcssp.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
var username="${username}";
var curDate = "${curDate}";
</script>


<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_ro_approver_screen_viewCMD">
	

   <div class="nkpageland" id="printableArea">
     <div class="container" align="center" id="headerView" style="display: block;">  

	       <div class="card">
	       
				<div class="card-header mms-card-header">
				      <b>MONTHLY CENSUS RETURN : DEPOT</b>
				</div> 
			
			 	<div class="card-body card-block">
			  		<div class="col-md-12 row form-group">		 		
		                	<div class="col-md-2">
		                  		<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>SUS NO</label>
		                	</div>
		                	<div class="col-md-2">
		                  		<input type="text" id="sus_no1" name="sus_no1" maxlength="8" placeholder="Search..." class="form-control-sm form-control" autocomplete="off">
							</div>
		  										
		                    <div class="col-md-2">
		                  		<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
		                	</div>
		                	<div class="col-md-6">
		                  		<input type="text" id="unit_name1" name="unit_name1"  maxlength="100" placeholder="Search..." class="form-control-sm form-control" autocomplete="off">
							</div>		
			  		 </div>
				</div> 
				
				<div class="card-footer" align="center">
				    <input type="button" class="btn btn-success btn-sm" onclick="isvalid();" value="Get MCR"> 
			    </div>
	       </div>
	
	 </div>  
	 <div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>MONTHLY CENSUS RETURN : DEPOT</b> </div>
						
	 <div class="card" id="re_tb" style="display: none;background: transparent;">
	  	 <table id ="SearchViewtr"> 
<tbody style="overflow: hidden;">
						  <tr style="width:100%;">
						
		    				<td class="nrTableMain2Search" colspan='1'> 
								<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
								<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;" autocomplete="off">
						    </td>
</tr>
	</tbody>					
</table> 
	   
					<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			
					<input type="hidden" id="selectedid" name="selectedid"> 
		            <input type="hidden" id="statushid" name="statushid">
		            <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		            		 	
 <div class="nrTableDataDiv">
                          				<table border="1" class="table_collapse report_print" width="100%">
									<thead style="text-align: center; ">
										<tr>
            							<th  width="5%;" rowspan='2'>Material No</th>
                    					<th  width="5%" rowspan='2'>Census No.</th>
                    					<th  width="10%" rowspan='2'>Nomenclature</th>
                    					<th  width="3%" rowspan='2' title="Free Stock">Free Stk</th>
                    					<th  width="6%" colspan='2'>Repairable Stock</th>
            							<th  width="12%" colspan='4'>Reserve Stocks</th>
            							<th  width="3%" rowspan='2'>BLR /R4</th>
            							<th  width="3%" rowspan='2'>BER /US</th>
            							<th  width="4%" rowspan='2'>Remarks</th>
            							</tr>
            						<tr>
            							<th  width="3%" rowspan='2' title="">Depot</th>
            							<th  width="3%" rowspan='2' title="">Wksp</th>            						
            							<th width="3%">WWR</th>
            							<th  width="3%">TRSS</th>
            							<th  width="3%">ETSR</th>
            							<th  width="3%">Other Res</th>
                  					</tr>
                        			</thead>
    								<tbody id="nrTable" style="overflow: scroll;">
    								    <c:if test="${m_1[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='15' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_1[0][0] != 'NIL'}"> 
										        <c:forEach var="item" items="${m_1}" varStatus="num">
										             <tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
										                 <c:if test="${item[1] != ''}">
											                 <c:if test="${temp != item[1]}">
																	 <tr class='nrGroupHeading'>
																		 <td colspan='13' style='text-align:left;'>PRF Group&nbsp;:&nbsp;${item[1]}</td>
																		 <c:set var="temp" value="${item[1]}"></c:set>
																	 </tr>
															 </c:if> 
											                      <tr class='nrTableLineData'>
											                     <td width="5%;"></td>
											                     <td width="5%" style='text-align:center;'>${item[2]}</td>
											                     <td width="10%">${item[3]}</td>
											                     <td width="3%"style='text-align:center;'>${item[4]}</td>
											                     <td width="3%"style='text-align:center;'>${item[5]}</td>
											                     <td width="3%"style='text-align:center;'>${item[6]}</td>
											                     <td width="3%"style='text-align:center;'>${item[7]}</td>
											                     <td width="3%"style='text-align:center;'>${item[8]}</td>
											                     <td width="3%"style='text-align:center;'>${item[9]}</td>
											                     <td width="3%"style='text-align:center;'>${item[10]}</td>
											                     <td width="3%"style='text-align:center;'>${item[11]}</td>
											                     <td width="3%"style='text-align:center;'>${item[12]}</td>
											                     <td width="4%"style='text-align:center;'>${item[13]}</td>
											                     <% ntotln++; %>
											                 </tr>
										                 </c:if>			                  
										              </tr>
												</c:forEach>
	                                      </c:if>
    								</tbody>
								</table>
								</div>
						
				
		   </div>
	   
	    
	    <div class="card-footer">	
	    	 <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
                   <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 				 
             <input type="button" class="btn btn-primary btn-sm" value="View / Print Data" id="pId" onclick="return viewPrint();" style="display: none;float: right;">
        </div> 
	 </div>
	 </div>

</form:form>

<c:url value="mms_print_depot_mcr" var="printUrl" />
<form:form action="${printUrl}" method="post" id="printForm" name="printForm" modelAttribute="printDepotId" target="result">
	<input type="hidden" name="printDepotId" id="printDepotId"/>

</form:form>  

<c:url value="depotMcrList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no2">
    <input type="hidden" name="sus_no2" id="sus_no2" />
    <input type="hidden" name="unit_name2" id="unit_name2"/> 
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 


<script>
 $("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"DSUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
});

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"DNAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});
 

function printDiv() {
	$("#SearchViewtr").hide();
	$("#tdheaderView").show();
	$("#headerView").hide();
	$("#btn_e").hide();
	$("#btn_p").hide();
	$("#pId").hide();
	
	let innerContents = document.getElementById('printableArea').innerHTML;
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	popupWindow.document.close();
	$("#SearchViewtr").show();
	$("#tdheaderView").hide();
	$("#headerView").show();
	$("#btn_e").show();
	$("#btn_p").show();
	$("#pId").show();
}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}
function setid(a,st){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
	$("#NRRDOO"+a).attr("background-color","yellow");
}

function isvalid(){
	if($("#sus_no1").val()==""){
		alert("Please Select SUS No...");
		$("#sus_no1").focus();
	    return false;
	} 
	
	if($("#unit_name1").val()==""){
		alert("Please Select Unit Name...");
		$("#unit_name1").focus();
	    return false;
	} 
	
	$("#pId").show();
	getdepotmcrList();
	return true; 
}

function getdepotmcrList(){
	$("#sus_no2").val($("#sus_no1").val());    
	$("#unit_name2").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#searchForm").submit();     
}

function viewPrint(){
	 var a = $("input#sus_no1").val();
	 if(a!= null && a !="" && a!="null"){	
		 var statusid = document.getElementById("statushid").value;
	     var x = screen.width / 2 - 1100/2;
	     var y = screen.height / 2 - 900/2;
		 popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		 window.onfocus = function () { } 
	     document.getElementById('printDepotId').value=a;
	     document.getElementById('printForm').submit();
	 }else{
		 alert("Please Select a Request...");
	 } 	
}
</script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var y1 = '${m_1[0][0]}';
	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#re_tb").show();
		$("#pId").show();
		$("#sus_no1").val('${m_2}');
	    $("#unit_name1").val('${m_3}');
	    $("#ntotln").text(<%=ntotln%>);
	    nrSetWaterMark(<%=ntotln+2.5%>);
	} 
	try{
		if(window.location.href.includes("id=")){
			var url = window.location.href.split("?id")[0];
	      	window.location = url;
	    } 	
	}catch (e){}  		
	      		
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	});
});	


</script>

