<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>


<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal"  >
   <div class="">	
      <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
         <div class="card">  
				
				  <div class="card-header mms-card-header">
				       <b>RIO APPROVER SCREEN</b>
				  </div> 
		
				  <div class="card-body card-block" id="src_div" style="display: none;">
						<div class="col-md-12 row form-group">
				       	        <div class="col-md-2" style="text-align: left;">
				                  		<label class=" form-control-label">PRF</label>
				                </div>
				                			
				                <div class="col-md-2">
				                  	    <input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10"/>
				                </div>
				                			
				                <div class="col-md-1">
				                	    <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
				                </div>
				                			
				                <div class="col-md-7">
		               			       
					                    <select name="prf_code" id="prf_code" class="form-control-sm form-control" disabled="disabled">	
			           						<option value="ALL">--All PRF Groups --</option>
			           						<c:forEach var="item" items="${m_11}">
			           						     <option value="${item[0]}" name="${item[1]}">${item[0]} - ${item[1]}</option>	
			           						</c:forEach>                   								
						                </select> 
				                </div>
				        </div>
					    
						<div class="col-md-12 row form-group" style="margin-top: -10px;">		
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">Command</label>
								</div>
								<div class="col-md-4">
									
									<select name="command_code" id="command_code" class="form-control-sm form-control" >	
			           						<option value="ALL">-- All Command --</option>
			           						
			           						<c:set var="data" value="${ml_2[0]}"/>
    						                <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
										    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
										       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
										       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
										    </c:forEach>
							    
			           						              								
					                </select>
								</div>
					    </div>
			
					    
					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">CORPS</label>
								</div>
								<div class="col-md-4">
									
									<select name="corps_code" id="corps_code" class="form-control-sm form-control" >	
		           						<option value="ALL">-- All Corps --</option>
		           						
		           						<c:set var="data" value="${ml_3[0]}"/>
    						            <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
									    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
									       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
									       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
									    </c:forEach>
		           						
		           						               								
							        </select>
								</div>
				
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">Date From To</label>
								</div>
								<div class="col-md-3">
									<input type="date" id="date_from" name="date_from" class="form-control-sm form-control" placeholder="dd-mm-yyyy" />
								</div>
					    </div>
					    
					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">Div</label>
								</div>
								<div class="col-md-4">
								
									<select name="div_code" id="div_code" class="form-control-sm form-control" >	
		           						<option value="ALL">-- All Div --</option>
		           						
		           						<c:set var="data" value="${ml_4[0]}"/>
    						            <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
									    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
									       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
									       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
									    </c:forEach>
		           						
		           						             								
							        </select>
								</div>
				
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">Date To</label>
								</div>
								<div class="col-md-3">
									<input type="date" id="date_to" name="date_to" class="form-control-sm form-control" placeholder="dd-mm-yyyy" />
								</div>
					    </div>
					    
					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class="form-control-label">BDE</label>
								</div>
								<div class="col-md-4">
									<select name="bde_code" id="bde_code" class="form-control-sm form-control" >	
		           						<option value="ALL">-- All Bde --</option>
		           						
		           						<c:set var="data" value="${ml_5[0]}"/>
    						            <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
									    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
									       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
									       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
									    </c:forEach>
		           						         								
							        </select>
								</div>		
					    </div>
					</div>
		
					<div class="card-body card-block">
					    <div class="col-md-12 row form-group">		
							<div class="col-md-2" style="text-align: left;">
								<label for="text-input" class="form-control-label">Search Status</label>
							</div>
							<div class="col-md-4">
								
								<select name="rio_status" id="rio_status" class="form-control-sm form-control" >	
										<option value="ALL">-- All Status --</option>
										<c:forEach var="item" items="${ml_1}">
											<c:if test="${item[0] != 2}">
								                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								            </c:if>	
										</c:forEach>                  							
							    </select>
								<input type="hidden" id="rio_id" name="rio_id"/>
							</div>
							<div class="col-md-2" align="left">
						    </div>
						    <div class="col-md-4" align="right">
								<input type="button" class="btn btn-primary btn-sm" onclick="return src_chg();" value="More Search Options">
						    </div>
				        </div>
					</div>
					
					<div class="card-footer" align="center" style="margin-top: -10px;">
				          <input type="button" class="btn btn-success btn-sm" onclick="return getEqptAppList();" value="Search">
			        </div>
		</div>	
    </div>		
 </div>	
 	
<div id ="tdheaderView" style="display: none;" colspan='13' align="center" class="nrTableHeading">   <b>RIO APPROVER SCREEN</b> </div>						
 <div class="card" id="re_tb" style="background: transparent;">	

					<table id="SearchViewtr">
					   <tbody style="overflow: hidden;"> 
						  <tr class='nrTableLineData' >
							<td width="40%" colspan="1" style="padding-left: 20px;">
								<b>&nbsp;<input type=checkbox id='nSelAll' name='tregn' onclick='setselectall();'>&nbsp;Select all [ <b></b><span id="tregn" style='font-size: 16px;'>0</span>/<span id='nTotRow1'>0</span></b> ]
							</td>
		    				<td class="nrTableMain2Search" colspan='1' style="padding-right: 20px;"> 
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
							  <table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center;">
	                          				<tr>
	                        				<th width="15%">RIO No</th>
	                    					<th width="16%">RO No with Date</th>
	            							<th width="7%">RO Type</th>
	                    					<th width="13%">To Unit</th>
	                    					<th width="11%">Issue Against</th>
	                    					<th width="18%">PRF Group</th>
	            							<th width="5%">Qty</th>
	            							<th width="12%">Status</th>
                          				</tr>
                        			</thead>
    								<tbody id="nrTable">
    								    <c:if test="${m_1[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='8' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
									
										<c:if test="${m_1[0][0] != 'NIL'}"> 
										     <c:forEach var="item" items="${m_1}" varStatus="num">
										          <tr id="NRRDO" name="NRRDO" style="width:100%;">
										              <td style='text-align:left;'width="15%"><input class="nrCheckBox" type="checkbox" id="NRRDOO${item[0]}" name="${item[7]}" onclick="setid('${item[0]}','${item[7]}','${item[8]}')">&nbsp;${item[0]}</td>
										              <td style='text-align:left;'width="16%">${item[1]}</td>
										              <td style='text-align:center;'width="7%">${item[2]}</td>
										              <td style='text-align:left;'width="13%">${item[3]}</td>
										              <td style='text-align:center;'width="11%">${item[4]}</td>
										              <td style='text-align:left;'width="18%">${item[5]}</td>
										              <td style='text-align:center;'width="5%">${item[6]}</td>
										              <td style='text-align:center;'width="12%">${item[7]}</td>
										              <% ntotln++; %>
										          </tr>
										     </c:forEach>
										</c:if>
    								</tbody>
								</table>
								</div>	
							</div>
						</div>
					</div>
	<div class="card-footer">
	    <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
        <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
	    
	    <input type="button" class="btn btn-danger btn-sm" id="cancel" value="Cancel RIO" onclick="return CancelRIO();" style="float: right;margin-left: 4px;">
	    <input type="button" class="btn btn-success btn-sm"id="e_r" value="Extend RIO" onclick="return ExtendRIO();" style="background-color: purple;float: right;margin-left: 4px;"> 
	    <input type="button" class="btn btn-primary btn-sm"id="v_p" value="View / Print RIO" onclick="return viewPrint();" style="float: right;margin-left: 4px;"> 
		<input type="button" class="btn btn-success btn-sm"id="a_r" value="Approve RIO" onclick="return setUpdateStatus();" style="float: right;">    		
    </div> 
</div>		

</form:form>

<c:url value="printMMSRIOUrl" var="printUrl" />
<form:form action="${printUrl}" method="post" id="printForm" name="printForm" modelAttribute="printMMSId" target="result">
	<input type="hidden" name="printMMSId" id="printMMSId" value="0"/>
</form:form>

<c:url value="RIOApproverList" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1" name="m4_unit1" modelAttribute="m4_c_code">
      <input type="hidden" name="m4_c_code" id="m4_c_code"/>
	  <input type="hidden" name="m4_q_code" id="m4_q_code"/>
	  <input type="hidden" name="m4_d_code" id="m4_d_code"/>
	  <input type="hidden" name="m4_b_code" id="m4_b_code"/>
	  <input type="hidden" name="m4_p_code" id="m4_p_code"/>
	  <input type="hidden" name="m4_d_from" id="m4_d_from"/>
	  <input type="hidden" name="m4_d_to" id="m4_d_to"/>
	  <input type="hidden" name="m4_stat" id="m4_stat"/>
	  <input type="hidden" name="m4_prfs" id="m4_prfs"/>
</form:form>

<c:url value="DataApproved" var="backUrl" />
<form:form action="${backUrl}" method="post" id="a_app" name="a_app" modelAttribute="a_ro">
      <input type="hidden" name="a_ro" id="a_ro"/>
	  <input type="hidden" name="a_type" id="a_type"/>
	  <input type="hidden" name="a_para" id="a_para"/>
</form:form>

<c:url value="DataExtended" var="backUrl" />
<form:form action="${backUrl}" method="post" id="a_ext" name="a_ext" modelAttribute="a_ro2">
      <input type="hidden" name="a_ro2" id="a_ro2"/>
	  <input type="hidden" name="a_para2" id="a_para2"/>
	  <input type="hidden" name="a_id2" id="a_id2"/>
</form:form>

<c:url value="DataCancelled" var="backUrl" />
<form:form action="${backUrl}" method="post" id="a_can" name="a_can" modelAttribute="a_ro3">
      <input type="hidden" name="a_ro3" id="a_ro3"/>
	  <input type="hidden" name="a_para3" id="a_para3"/>
	  <input type="hidden" name="a_id3" id="a_id3"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
	
<script>

function printDiv() {
	$("#SearchViewtr").hide();
	$("#SearchViewtrSelect").hide();
	$("#tdheaderView").show();
	$("#headerView").hide();
	$("#btn_e").hide();
	$("#btn_p").hide();
	$("#cancel").hide();
	$("#e_r").hide();
	$("#v_p").hide();
	$("#a_r").hide();
	$('.nrCheckBox').css('display','none');
	 
	 
	//let popupWinindow
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
	$("#cancel").show();
	$("#e_r").show();
	$("#v_p").show();
	$("#a_r").show();
	$("#SearchViewtrSelect").show();
	$('.nrCheckBox').css('display','block');
}

function exportData(b){
	
	$().tbtoExcel(b);
	
}

function src_chg(){
	$("#src_div").toggle();
}

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}else{
		
		
        $.post("getMMSPRFtListBySearch?"+key+"="+value, {
        	nParaValue:nParaValue
    }, function(j) {
          

    }).done(function(j) {
    	if(j.length <= 0 || j == null || j == ''){
			alert("No Search Result Found");
 			$("#from_prf_Search").focus();
 		}else{
 			$("#prf_code").attr('disabled',false);
 			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups--" + '</option>';
			
 			var a = [];
 			var enc = j[j.length-1].substring(0,16);
 			for(var i = 0; i < j.length; i++){
				a[i] = dec(enc,j[i]);
            }
 			
			var data=a[0].split(",");
			var datap;
			for ( var i = 0; i < data.length-1; i++) {
				datap=data[i].split(":");
				if (datap!=null) {
					if (datap[1].length>90) {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,90)+ '</option>';
					} else {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
					}
				}
			}	
			$("select#prf_code").html(options); 
 		}
            
    }).fail(function(xhr, textStatus, errorThrown) {
    });
	}
}

function findselected() {
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		$(this).attr('background-color','yellow');
		return $(this).attr('id');
	}).get();
	var b=nrSel.join(',');
	$("#nrSrcSel").val(b);
	$('#tregn').text(nrSel.length);
	var nrSel1=$('.nrCheckBox:checkbox:checked').map(function() {
		$(this).attr('background-color','yellow');
		return $(this).attr('name');
	}).get();
	var b1=nrSel1.join(',');
	$("#statushid").val(b1);
}

function getselected() {
 	var checkedVals = $('.nrCheckBox:checkbox:checked').map(function() {
	    return $(this).attr("id");
	}).get();
	
	$("#nrSrcSel").val(checkedVals.join(","));
}

function setid(a,st,id){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#rio_id").val(id);
	$("#nrSrcSel").val(a);
}
	
function setUpdateStatus(){
	getselected();
	var ro_no = $("#nrSrcSel").val();
	var statusid = $("#statushid").val();
	var para = "RIO";
	var ro_type = "";
	
	var ro_forVals = $('.nrCheckBox:checkbox:checked').map(function() {
	    return $(this).attr("name");
	}).get();
	var ro_type = ro_forVals.join(",");
	var a = ro_type.split(",");

    var n1 = a.includes("APPROVED");
    var n3 = a.includes("CANCELLED"); 
 
	if(ro_no!= null && ro_no !="" && ro_no!="null"){	
		if(n3 == true){
			alert("Selected Request is already Cancelled. Please Select Only Pending Records.");
			getEqptAppList();
		}
		else if(n1 == true){
			alert("Selected Request is already Approved. Please Select Only Pending Records.");
			getEqptAppList();
		}else{
			
			$("#a_ro").val(ro_no);
			$("#a_type").val(ro_type);
			$("#a_para").val(para);
			$("#a_app").submit();
		} 
	}else{
		alert("Please Select a Request...");
	} 	
}

function ExtendRIO() {
	getselected();
	var rio = $("#nrSrcSel").val();
	var rio_id = $("#rio_id").val();
	var para = "RIO";
	
	if(rio !="" && rio!=null && rio!="null"){
		var arr = rio.split(',');
		if(arr.length > 1){
			alert("You Can Extend Only One RIO At a time...");
		}else{
			var d = new Date();
			var Fulldate = d.getDate()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+d.getFullYear();
		 
			var txt; 
			var c;
			var ext = '${ex_dt[0]}';
			var f_d = new Date(ext);
			var e_date = f_d.getDate()+"-"+("0" + (f_d.getMonth()+1)).slice(-2)+"-"+f_d.getFullYear();
			c = confirm("Are You Sure You Want to Extend RO No : "+rio + "\nFrom "+Fulldate+"\nTo "+e_date+" ?");	
			
			if(c == true){
				
				$("#a_ro2").val(rio);
				$("#a_para2").val(para);
				$("#a_id2").val(rio_id);
				$("#a_ext").submit();
			}else{
				txt = "You pressed Cancel!";
			}  
			return false;
		}	
	}else{
		alert("Please select a Request.." );
	}
}
	
function CancelRIO(){
	getselected();
	var ro =$("#nrSrcSel").val();
	var rio_id = $("#rio_id").val();
	var statusid = document.getElementById("statushid").value;
	var para = "RIO";
	
	if(ro !="" && ro!=null && ro!="null"){	
		if(statusid != "Cancelled"){	
			var arr = ro.split(',');
			var txt;
			
			if(arr.length > 1){
				alert("You Can Cancel Only One RIO At a time...");
			}else{
				var r = confirm("Are You Sure You Want to cancel " +ro);
				
				if(r == true){
				
					$("#a_ro3").val(ro);
					$("#a_para3").val(para);
					$("#a_id3").val(rio_id);
					$("#a_can").submit();       
			    }else{
			    	txt = "You pressed Cancel!";
			    }
			    document.getElementById("cancel").innerHTML = txt;
			}
			
		}else{
			alert("Already Cancelled.." );
		    getEqptAppList();
	    }  
	}else{
		alert("Please select a Request.." );
	}   
}

function getEqptAppList(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	if($("#date_from").val() > c_d){
		$("#date_from").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	if($("#date_to").val() > c_d){
		$("#date_to").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	$("#m4_c_code").val($("#command_code").val());
	$("#m4_q_code").val($("#corps_code").val());
	$("#m4_d_code").val($("#div_code").val());
	$("#m4_b_code").val($("#bde_code").val());
	$("#m4_p_code").val($("#prf_code").val());
	$("#m4_d_from").val($("#date_from").val());
	$("#m4_d_to").val($("#date_to").val());
	$("#m4_stat").val($("#rio_status").val());
	$("#m4_prfs").val($("#from_prf_Search").val());
	$("#nrWaitLoader").show();
	$("#m4_unit1").submit();
}

function setselectall(){
	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	findselected();
}
	
function viewPrint(){
	getselected();
	var a = $("#nrSrcSel").val();
	
	if(a!= null && a !="" && a!="null"){
		var arr = a.split(',');
		if(arr.length > 1){
			alert("You Can Select Only One RIO At a time...");
		}else{			
			var x = screen.width / 2 - 1100 / 2; 
			var y = screen.height / 2 - 900 / 2;  
			popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');

			$("#printMMSId").val(a);
			$("#printForm").submit();
		}
	}else{
		alert("Please Select a Request...");
	}
}
</script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');        
    watermarkreport();
	$().getCurDt(date_from);
	$().getCurDt(date_to);
	
    var y1 = '${m_1[0][0]}';
	
	if(y1 != "NIL"){
		$("#btn_p").show();
	}

	
	if(y1 != "" || '${m_1[0]}'.length > 0){
		
		$("#command_code").val('${m_2}');
	    $("#corps_code").val('${m_3}');
		$("#div_code").val('${m_4}');
		$("#bde_code").val('${m_5}');
		$("#prf_code").val('${m_6}');
		$("#date_from").val('${m_7}');
		$("#date_to").val('${m_8}');
	   	$("#rio_status").val('${m_9}');
	   	$("#from_prf_Search").val('${m_10}');
	   	nrSetWaterMark(<%=ntotln%>);
	   	$("#ntotln").text(<%=ntotln%>);	
	   	if('${m_10}' != ""){
	   		getfromprf('${m_10}');
	   	}
	}
	
	function binddiv(code,codelevel){
		
		
        $.post("getMMSDistinctHirarName?"+key+"="+value, {
        	nHirar : "DIVISION", nCnd:code, codelevel:codelevel
    }, function(j) {
          

    }).done(function(j) {
    	var options = '<option value="ALL">-- All Div --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}
		
		$("select#div_code").html(options);
    	
            
    }).fail(function(xhr, textStatus, errorThrown) {
    });
	}

	function bindcoorp(code,codelevel){
        $.post("getMMSDistinctHirarName?"+key+"="+value, {
        	nHirar : "CORPS", nCnd:code, codelevel:codelevel
    }, function(j) {
          

    }).done(function(j) {
    	var options = '<option value="ALL">-- All Corps --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}
		
		$("select#corps_code").html(options);
    	
            
    }).fail(function(xhr, textStatus, errorThrown) {
    });
	}

	function bindbde(code,codelevel){ 
		
		
        $.post("getMMSDistinctHirarName?"+key+"="+value, {
        	nHirar : "BRIGADE", nCnd:code, codelevel:codelevel
    }, function(j) {
          

    }).done(function(j) {
    	
    	var options = '<option value="ALL">-- All Bde --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}	
		
		$("select#bde_code").html(options);
            
    }).fail(function(xhr, textStatus, errorThrown) {
    });
	}
	
	$('#command_code').change(function(){
		bindcoorp(this.value,"COMMAND");
		binddiv(this.value,"COMMAND");
		bindbde(this.value,"COMMAND");	
	});   
	
	$('#corps_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=this.value;
		if (corcd=="ALL") {
			corcd=cmdcd;
			binddiv(corcd,"COMMAND");
			bindbde(corcd,"COMMAND");	
		}else{
			binddiv(corcd,"CORPS");
			bindbde(corcd,"CORPS");	
		}
	});   
	
	$('#div_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=$("#corps_code").val();
		var divcd=this.value;
		
		if(divcd=="ALL"){
			if (corcd=="ALL") {
				divcd=cmdcd;
				bindbde(divcd,"COMMAND");	
			} else {
				divcd=corcd;
				bindbde(divcd,"CORPS");
			}
		}else{
			bindbde(divcd,"DIVISION");	
		}
	});
	
	try{
    	if(window.location.href.includes("?")){
  			var url = window.location.href.split("?")[0];
			window.location = url;
	    }	
  	}catch(e){	
    }  				
	      		
  	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
  	
});
</script>