<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script>
var username="${username}";
var curDate = "${curDate}";

</script>
<body class="mmsbg">
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" id="" name=""  method="post" class="form-horizontal"> 
  <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
	 <div class="card">
		  <div class="card-header mms-card-header">
		        <b>UPDATION OF EQPT REGISTRATION NO</b>
		  </div> 
	          		
	      <div class="card-body card-block">
	                <div class="col-md-12 row form-group">
               			 <div class="col-md-2" style="text-align: left;">
                 				  <label class=" form-control-label"><strong style="color: red;">* </strong><span id='from_sus_Search_label'>Unit</span></label>
               			 </div>
               			
               			 <div class="col-md-2">
                 				  <input type="text" id="from_sus_Search" name="from_sus_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" required/>
               			 </div>
               			
               			 <div class="col-md-1">
               			       <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromunit($('#from_sus_Search').val());" 
               			       title="Click to Search" style="cursor:pointer;" id="im_1">
               			 </div>
               			
               			 <div class="col-md-7">
               			       <select id="from_sus_no" name="from_sus_no" class="form-control-sm form-control" disabled="disabled">
                                     <option selected value="-1">--Select Unit--</option>     
                               </select>
               			 </div>
					</div>
  					
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>PRF Group</label>
	             		 </div>
	             		 <div class="col-md-10">
	             			    <select id="prf_code" name="prf_code"  class="form-control-sm form-control" disabled="disabled" required></select>
	               		 </div>    
  					</div>
  					
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Nomenclature</label> 
	             		 </div>
	             		 <div class="col-md-10">
	             			   <select id="census_no" name="census_no"  class="form-control-sm form-control" disabled="disabled" required></select>
	               		 </div>    
  					</div>
  				
  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
             	 		 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Holding</label> 
	             		 </div>
	             		 <div class="col-md-4">
                                <select id="type_of_hldg" name="type_of_hldg" class="form-control-sm form-control" disabled="disabled" required>
			                         <option selected value="-1">--Select Type of Holding--</option>     
			                    </select>
	               		 </div>
	             		 								
	               		 <div class="col-md-3" style="text-align: right;">
	                 		  <label class=" form-control-label" style="margin-left: 13px;">Registration No</label>
	               		 </div>
	               		 <div class="col-md-3">
	               			 <input id="regn_seq_no" name="regn_seq_no" placeholder="Enter Registration No" class="form-control-sm form-control" autocomplete="off" maxlength="20" required>
						  </div>	    
  					</div>
		</div>
			
	    <div class="card-footer" align="center" style="margin-top: -10px;">
	    <a href="mms_regno_update" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
			<!-- <input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" /> -->
            <input type="button" class="btn btn-success btn-sm" value="Search" onclick="return isvalid();">
            <a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
        </div>  
    </div>
</div>
</div>	

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>UPDATION OF EQPT REGISTRATION NO</b></div> 
						
<div class="card" id="re_tb" style="display: none;background: transparent;">
<div  width="100%">

								
						<div class="nrTableMain2Search" align="left" id = "SearchViewtr"> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
			    		
	</div>	
	<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			
		            
		            <input type="hidden" id="selectedid" name="selectedid"> 
		            <input type="hidden" id="nrSrcSel" name="nrSrcSel">
		             <div class="nrTableDataDiv">                      
		             <table  border="1" class="report_print" width="100%">
	                                   <thead style="text-align: center;">
	                          	         <tr>
			 
                          				    <th width="10%" id="rdViewth" style="text-align: center;" class="nrBox">Select</th>
	            							<th width="20%" style="text-align: center;" class="nrBox">Census No</th>
	                    					<th width="40%" style="text-align: center;" class="nrBox">Nomenclature</th>
	                    					<th width="10%" style="text-align: center;" class="nrBox">Type of Holding</th>
	                    					<th width="10%" style="text-align: center;" class="nrBox">Type of Eqpt</th>
	            							<th width="10%" style="text-align: center;" class="nrBox">Registration No</th>
                          				</tr>
                        			</thead>
    								<tbody id="nrTable">
    							
						                    <c:if test="${empty m_1[0]}">
    								        <tr class='nrSubHeading'>
									            <td colspan='13' style='text-align:center;'>Data Not Available...</td>
									            <% ntotln=0; %>
									        </tr>
    								    </c:if>
    								    
    								    <c:if test="${not empty m_1[0]}"> 
    								        <c:set var="data" value="${m_1[0]}"/>
    								        <c:set var="datap" value="${fn:split(data,',')}"/>
    								      
    								        <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
    								           <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
    								           <tr  id='NRRDO' name='NRRDO'>
    							               		<td width="10%" style="text-align: center;" id="rdView">
    							               			<c:if test="${dataf[7] != 'KMS'}">
    							               				<input type="radio" id="NRRDOO${dataf[5]}" name="n" onclick="setid('${dataf[5]}');">
    							               			</c:if>
    							               		</td>
					                              	<td width="20%" style="text-align:center;">${dataf[0]}</td>
						                            <td width="40%" style="text-align:left;">${dataf[1]}</td>
						                            <td width="10%" style="text-align:left;">${dataf[2]}</td>
						                            <td width="10%" style="text-align:left;">${dataf[3]}</td>
						                            <td width="10%" style="text-align:left;">
						                            	<c:if test="${dataf[7] != 'KMS'}">
						                            		<input type=text id="NRIN${dataf[5]}" name="NRIN" value="${dataf[4]}" readonly='readonly' onkeypress="return AvoidSpace(event)" onkeyup="return specialcharectereqpt_regn_no(this)" style='background-color: white;width: 100px;'>
						                            	</c:if>
						                            </td>
						                            <td style="display: none;"><input type="hidden" id="NRIN${dataf[5]}" name="NRIN${dataf[5]}"  value="${dataf[4]}"></td>
						                            <% ntotln++; %>
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
		  <!-- <input type="button" class="btn btn-success btn-sm" value="Change Registration No" onclick="changeregn();" id="btn_chg" style="display: none;float: right;">  -->
		  <input type="button" class="btn btn-primary btn-sm" value="Update Registration No" onclick="updateregn();" id="btn_upd" style="display: none;float: right;">   
    </div> 		
</div>
	</div>
	

</form:form>

<c:url value="mms_list_regn_no2" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m3_unit3" name="m3_unit3" modelAttribute="m3_sus">
      <input type="hidden" name="m3_sus" id="m3_sus"/>
	  <input type="hidden" name="m3_cen" id="m3_cen"/>
	  <input type="hidden" name="m3_hldg" id="m3_hldg"/>
	  <input type="hidden" name="m3_regnseq" id="m3_regnseq"/>
	  <input type="hidden" name="m3_prf" id="m3_prf"/>
	  <input type="hidden" name="m3_unit" id="m3_unit"/>
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
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_upd").hide();
	  $("td#rdView").hide();
	  $("th#rdViewth").hide();
	
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
$("#btn_upd").show();
$("td#rdView").show();
$("th#rdViewth").show();


}

function exportData(b){
	
	$().tbtoExcel(b);
	b.preventDefault();
}


function btn_clc(){
	location.reload(true);
}

function isvalid(){
	if($("#from_sus_Search").val() == ""){
		alert("Please Search From SUS No or Unit Name");
		$("#from_sus_Search").focus();
	    return false;
	}else if($("#from_sus_no").val() == "-1"){
		alert("Please Select From Unit");
		$("#from_sus_no").focus();
	    return false;
	}
	
	if($("#from_sus_no").val() != "" && $("#regn_seq_no").val() != ""){
		
		mms_list_regn_no();
	} 
	else if($("#from_sus_Search").val() != "" && $("#from_sus_no").val() != "" && $("#regn_seq_no").val() == ""){
		
		if($("#prf_code").val() == -1){
			alert("Please Select the PRF Group");
			$("#prf_code").focus();
			return false;
		}
		
		if($("#census_no").val() == -1){
			alert("Please Select the Census No");
			$("#census_no").focus();
			return false;
		}
		
		if($("#type_of_hldg").val() == -1){
			alert("Please Select the Type of Holding");
			$("#type_of_hldg").focus();
			return false;
		}
		mms_list_regn_no();
		return true;
	}
}

function getfromunit(nValue) {
    if (nValue.length<=0) {
    	alert("Enter Search Word...");
    	return false;
    } else {
    	
                $.post("getMMSUnitListBySearch?"+key+"="+value, {
                	nValue:nValue
            }, function(j) {
                   

            }).done(function(j) {
       
            
            	if(j.length <= 0 || j == null || j == ''){
    	 			alert("No Search Result Found");
    	 			$("#from_sus_Search").focus();
    	 		}else{
    	 			$("#from_sus_no").attr('disabled',false);
    	 			
    	 			if('${r_1[0][7]}' != "UNIT"){
    	 				var options = '<option value="' + "-1" + '">'+ "--Select Unit--" + '</option>';
    	 			}
    	 			
    	 			var a = [];
    	 			var enc = j[j.length-1].substring(0,16);
    	 			for(var i = 0; i < j.length; i++){
    					a[i] = dec(enc,j[i]);
                    }
    	 			
    	 			var data=a[0].split(",");
    				var datap;
    				
    				for(var i = 0; i < data.length-1; i++){
    					datap=data[i].split(":");
    					if (datap!=null) {
    						if (datap[1].length>90) {
    							options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[1]+ ' - '+ datap[2].substring(1,50)+ '</option>';
    						} else {
    							options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[1]+ ' - '+ datap[2]+ '</option>';
    						}
    					}
    				}
    				$("select#from_sus_no").html(options); 
    				
    				var sq = '${m_2}';
    				var usq = '${sus_n}';
    			    if(sq != ""){
    					$("select#from_sus_no").val(sq); 
    				}
    			    if(usq != ""){
    			    	$("select#from_sus_no").val(usq); 
    			    }
    	 		}
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
    	

    }   
}

function changePrf(j){
	$('#prf_code').attr('disabled',false);
	var nParaValue ="";
	var sus_no = $("#from_sus_no").val();
	
	if(j == ""){
		nParaValue = sus_no;
	}else{
		nParaValue = j;
	}
	
	var nPara = "SUS";
 
            $.post("getmms_distinct_prf_group_by_sus?"+key+"="+value, {
            	nParaValue:nParaValue,nPara:nPara
        }, function(j) {
                
        }).done(function(j) {
          
        
        		if (j.length<=0 || j=="") {
			alert("Sorry! No PRF Group Found.");
			var options = '<option value="' + "-1" + '">'+ "--Select PRF Group--" + '</option>';
			$("select#prf_code").html(options);
			$("#prf_code").attr('disabled',true);
			$("#census_no").attr('disabled',true);
			
			return false;
		}else{
			var options = '<option value="' + "-1" + '">'+ "--Select PRF Group--" + '</option>'; 
			
			var a = [];
			var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
				a[i] = dec(enc,j[i]);
            }
			
			var data=a[0].split(",");
			var datap;
			
			for(var i = 0; i < data.length-1; i++){
				datap=data[i].split(":");
				if (datap!=null) {
					if (datap[1].length>90) {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1].substring(1,90)+ '</option>';
					} else {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1]+ '</option>';
					}
				}
			}
			$("select#prf_code").html(options); 
			
			var q = '${m_6}';
			var q1 = '${m_2}';
			var pq = $("#from_sus_no").val();
			
			if(q != "" && q1 != ""){
				$("select#prf_code").val(q); 
			}
		}
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	

}

function chgcensus(nSusNo,nPrfCode){
	
	

            $.post("getmms_distinct_census_by_sus?"+key+"="+value, {
            	nSusNo:nSusNo,nPrfCode:nPrfCode
        }, function(j) {
          
        }).done(function(j) {
        
        
        		if (j.length<=0 || j=="") {
				alert("Sorry! Census No Not Found.");
				var options = '<option value="' + "-1" + '">'+ "--Select Census--" + '</option>';
				$("select#census_no").html(options); 
				$("#census_no").attr('disabled',true);
			
				return false;
			}else{
				var options = '<option value="' + "-1" + '">'+ "--Select Census--" + '</option>';
				
				var a = [];
				var enc = j[j.length-1].substring(0,16);
	 			for(var i = 0; i < j.length; i++){
					a[i] = dec(enc,j[i]);
	            }
	 			
	 			var data=a[0].split(",");
				var datap;
				
				for(var i = 0; i < data.length-1; i++){
					datap=data[i].split(":");
					if (datap!=null) {
						if (datap[1].length>90) {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
						} else {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
						}
					}
				}
				$("select#census_no").html(options);	
				
				var q = '${m_3}';
			  
			    var pq = $("#prf_code").val();
			    
			   
			    
			    if(q != "" && nSusNo != "" && nPrfCode != ""){
			    	$("#census_no").val(q); 
			    }
			}
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	

}

/* function chghldgType(nParaValue){
	var nPara = "SUS";
	
            $.post("getmms_distinct_type_of_hldg_by_sus?"+key+"="+value, {
            	nParaValue:nParaValue,nPara:nPara
        }, function(j) {
          

        }).done(function(j) {
      
               
               
        	 if(j.length<=0 || j==""){
    			 alert("Sorry! No Type of Holding Found.");
    			 var options = '<option value="' + "-1" + '">'+ "--Select Type of Hldg--" + '</option>';
    			 $("select#type_of_hldg").html(options);
    			 $("#type_of_hldg").attr('disabled',true);
    			 return false;
    		 }else{
    			 var options = '';
    				
    			 var a = [];
    			 var enc = j[j.length-1].substring(0,16);
    			 for(var i = 0; i < j.length; i++){
    				 a[i] = dec(enc,j[i]);
    	         }
    				
    			 var data=a[0].split(",");
    			 var datap;
    				
    			 for(var i = 0; i < data.length-1; i++){
    				 datap=data[i].split(":");
    				 if(datap!=null){
    					 if(datap[1].length>90){
    						 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1].substring(1,90)+ '</option>';
    					 }else{
    						 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1]+ '</option>';
    					 }
    				 }
    			  }
    			  $("select#type_of_hldg").html(options);
    			  var q1 = '${m_4}';
    			  if(q1 != ""){
    				  $("#type_of_hldg").val(q1);  
    			  }
    			  $("#type_of_hldg").attr('disabled',false);
    		 } 
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	

} */


function chghldgType(nParaValue){
	var nPara = "SUS";
	
	$.post("getmms_distinct_type_of_hldg_by_sus_frmtbl?"+key+"="+value, {
    	nParaValue:nParaValue,nPara:nPara
	}, function(j) {
       
	}).done(function(j) {
	
	if(j.length<=0 || j==""){
		 alert("Sorry! No Type of Holding Found.");
		 var options = '<option value="' + "-1" + '">'+ "--Select Type of Hldg--" + '</option>';
		 $("select#type_of_hldg").html(options);
		 $("#type_of_hldg").attr('disabled',true);
		 return false;
	 }else{
		 
		 var options = '';
		 var a = [];
		 var enc = j[j.length-1].substring(0,16);
		 for(var i = 0; i < j.length; i++){
			 a[i] = dec(enc,j[i]);
         }
			
		 var data=a[0].split(",");
		 var datap;
		 for(var i = 0; i < data.length-1; i++){
			 datap=data[i].split(":");
			 if(datap!=null){
				 if(datap[1].length>90){
					 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1].substring(1,90)+ '</option>';
				 }else{
					 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1]+ '</option>';
				 }
			 }
		  }
		 $("select#type_of_hldg").html(options);
		  var q1 = '${m_4}';
		  if(q1 != ""){
			  $("#type_of_hldg").val(q1);  
		  }
		  $("#type_of_hldg").attr('disabled',false);
	 }
        
}).fail(function(xhr, textStatus, errorThrown) {
});
	
}

function setid(a){
	$("input[name=NRIN]").css('background-color','white');
	$("input[name=NRIN]").attr('readOnly', true);
	
	$("#NRIN"+a).css('background-color','yellow');
	$("#NRIN"+a).attr('readOnly', false);
	$("#NRIN"+a).attr('focus', true);
	$("#NRIN"+a).focus();
	$("#nrSrcSel").val(a);
}

function mms_list_regn_no(){
	$("#m3_sus").val($("#from_sus_no").val());
	$("#m3_cen").val($("#census_no").val());
	$("#m3_hldg").val($("#type_of_hldg").val());
	$("#m3_regnseq").val($("#regn_seq_no").val());
	$("#m3_prf").val($("#prf_code").val());  
	$("#m3_unit").val($("#from_sus_Search").val());
	$("#nrWaitLoader").show();
	$("#m3_unit3").submit();		  
}
function updateregn(){
	var regseqno=$("#nrSrcSel").val();
	var regn_no=$("#NRIN"+regseqno).val();
	var type_of_hldg = $("#type_of_hldg").val();
	
	if(regseqno!= null && regseqno!=""){
		if(regn_no!=null && regn_no!=""){
			$("#nrWaitLoader").show();
			$.ajax({
				type: 'POST',
			    url: 'getSearchRegno?'+key+'='+value,
			    data: {e:regn_no},
			    success: function(response) {
			    	if(response == "Success dtl"){
			    		$("#nrWaitLoader").hide();
			    		alert("Current Reg NO Already Exists");
			       	    $("#NRIN"+regseqno).val($("#regn_seq_no").val());
			        }else{
			        	$("#nrWaitLoader").hide();
			        	$.ajax({
			        		type: 'POST',
			        	    url: 'mms_update_regn_no?'+key+'='+value,
			        	    data: {regn_no:regn_no,regn_seq_no:regseqno,type_of_hldg:type_of_hldg}, 
			                success: function(response){
			                	alert(response);
			        	        mms_list_regn_no();
			        	    }
			        	}); 
			        }
			     }
			 });
		}else{
			alert("Please Enter Registration No...");
		} 
	}else{
		alert("Please Select a Census No...");
	}
	
}

</script>
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	if('${sus_n}' != ""){
		$("#from_sus_Search").val('${sus_n}');
		$("#from_sus_Search").attr('disabled',true);
		$("#im_1").prop("onclick", false);
		getfromunit('${sus_n}');
		chghldgType('${sus_n}');
		changePrf('${sus_n}');
	}
	
	var y1 = '${m_1[0]}';
	if('${m_2}' != ""){
		if('${r_1[0][7]}' == "UNIT"){
			$("#from_sus_Search").attr('disabled',true);
			$("#im_1").prop("onclick", false);
		}
		
		$("#re_tb").show();
		//$("#btn_chg").show();
		$("#btn_upd").show();
		
		$("#from_sus_Search").val('${m_7}');	
		getfromunit('${m_7}');
		chghldgType('${m_2}');
		
		$("#type_of_hldg").val('${m_4}');
		$("#regn_seq_no").val('${m_5}');
		changePrf('${m_2}');
		$("#census_no").attr('disabled',false);
		chgcensus('${m_2}','${m_6}');
		nrSetWaterMark(1000);
		$("#ntotln").text(<%=ntotln%>);
	}
	
	if(y1 == "NIL"){
		$("#pId").hide();
	}
	
	$('#from_sus_no').change(function(){
		var sus = $("#from_sus_no").val();
		changePrf(sus);
		
		chghldgType(sus);
		
		var options1 = '<option value="' + "-1" + '">'+ "--Select Census--" + '</option>';
		$("select#census_no").html(options1); 	
		$("#census_no").attr('disabled',true);
    });   
	
	$('#prf_code').change(function() {
  		$('#census_no').attr('disabled',false);
  		var nPrfCode = $("#prf_code").val();
		var nSusNo = $("#from_sus_no").val();
		chgcensus(nSusNo,nPrfCode);
 	});

  	$("#nrInput").on("keyup", function() {
  		var value = $(this).val().toLowerCase();
  		$("#nrTable tr").filter(function() {
  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  	    });
    });
});
function specialcharectereqpt_regn_no(a)
{
    //var iChars = "@#^&*$,.:;%!+_-[]";  
    var iChars = "@#^&*$,.:;%!/[]+"; 
    var data = a.value;
    for (var i = 0; i < data.length; i++)
    {      
        if (iChars.indexOf(data.charAt(i)) != -1)
        {    
        alert ("This " +data.charAt(i)+" special characters not allowed.");
        a.value = "";
        return false; 
        } 
    }
    return true;
}
</script>