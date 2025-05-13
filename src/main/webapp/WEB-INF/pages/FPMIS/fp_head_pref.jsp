<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script>
	var role = "${role}";
</script>
<body onload="setMenu();">
<form:form action="fp_head_assignAction?${_csrf.parameterName}=${_csrf.token}" id="ms_hos_assign"  method="post" class="form-horizontal fp-form" commandName="fp_head_assignCMD">
      <div class="containerr" align="center">
          <div class="card">
              <div class="card-header mms-card-header" style="padding:20px;">
		             <h5>ALLOCATION OF CODE HEAD TO BUDGET HOLDERS ( ALLOTTEE )</h5>
		      </div> 		      
		      
		      <div class="card-body card-block ncard-body-bg">
	               		<div class="row form-group">
	               		 	<div class="col-md-2">							
	                 			  <input type="hidden" id="username" name="username" class="" value="${username}" readonly>
	                 		</div>
 							 <div class="col-md-7">
 							 	  	<label for="text-input" class=""> Est / Allottee </label>&nbsp;&nbsp; 							 			               		 
		               		 		<select id="sus_no" name="sus_no"  class="form-control-sm" title="Select Est to Configure Budget Holders" onchange="javascript:refreshUnit();">
										<c:forEach var="item" items="${n_unt}" varStatus="num">
											<option value="${item[1]}">${item[2]}</option>
								        </c:forEach>
						            </select>
		               			  	<input type="hidden" id="id" name="id" class="" value="0" autocomplete="off">
							 </div>
							 <div class="col-md-3" >
		                 		  <input type="button" class="btn btn-primary btn-sm nGlow" value="Get Head List" onclick="getheadlist();" title="Click to Get Head List.">
		               		 </div>
							  
						</div> 
		      </div>
              <input type="hidden" id="sus_tar" name="sus_tar">
              <input type="hidden" id="unit_tar" name="unit_tar">
               <input type="hidden" id="hidden_assign" name="hidden_assign">
               <input type="hidden" id="hidden_unit_name" name="hidden_unit_name">
              
              <div class="card-body card-block" id="d_reg" style="display:">
            	    <div class="col-md-12 row form-group">
            	        <div class="col-md-5 nPopTable">
            	             <b>List of Budget Heads</b>
            	             <input id="nrInputa" type="text" placeholder="Search Budget Heads..." size="20" style="float:right;" title="Search Budget Heads.">
					    </div>   
					    <div class="col-md-5" align="right">
					         <b>Selected Budget Heads - <span id="tregn" style='font-size:14px;'>0</span></b>
					    </div> 
				    </div>   
					          
					<div class="col-md-12 row form-group nPopTable" style="margin-left: 1px;">
					     <div class="col-md-5" style="height: 45vh;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="srctable">
					    </div>
					              
					     <div class="col-md-2" style="padding-left: 45px;">
					           <img src="js/miso/images/r_arrow.png" width="40px;" height="40px;">
					     </div>
					              
					      <div class="col-md-5" style="height:45vh;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="tartable">
						
					     </div> 
					</div>
		       </div>
              
          </div>
     	 </div>
     	 <c:if test="${n_noed=='Y'}">
  			  <div class="form-control card-footer" align="center" >
            	    <input type="submit" class="btn btn-success btn-sm nGlow" id="btn_save" value="Save" title="Click to Save Data.">
              </div>
         </c:if>
         <c:if test="${n_noed=='N'}">
         	  <div class="form-control card-footer" align="center" style="color:red!important">
            	    ${n_noed_msg}
              </div>         
         </c:if>
</form:form>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>
<script>
function btn_clc(){
	location.reload(true);
}

function findselected() {
	var trc_sus_no;
	var trc_unit_name;
	$("#tartable").empty();
	 trc_sus_no = $("input#hidden_assign").val();
	 trc_unit_name = $("input#hidden_unit_name").val();
	$("#srctable tr").css('background-color','white');	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb=$(this).attr('id').replaceAll('`','');
		bb=bb.replaceAll('[','');
		bb=bb.replaceAll(']','');
		bb=bb.replaceAll('&','And');
		bb=bb.replaceAll(',','');
		bb=bb.replaceAll('.','');
		bb=bb.replaceAll('(','');
		bb=bb.replaceAll(')',' ');
		bb=bb.replaceAll('/',' ');
		bb=bb.replaceAll('&','And');		
		$("#SRC"+bb).css('background-color','yellow');
		return bb;
	}).get();	
	
	var b=nrSel.join('__');	
	b= trc_sus_no + b; 
	$("#c_val").val(b.length);
	$("#sus_tar").val(b);
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb1=$(this).attr('name');
		return bb1;
	}).get();
	var c=nrSel.join('__');
	c= trc_unit_name + c; 
	$("#unit_tar").val(c);
	drawtregn(c,b);
}

function drawtregn(data,data1) {
	var ii=0;
	$("#tartable").empty();
	
	var datap=data.split("__");
	var datap2=data1.split("__");
	 for (var i = 0; i <datap.length; i++) {
		var  row ="<tr id='tarTableTr' padding='5px;'><td id='"+datap[1]+"' name='"+datap2[0]+"'>&nbsp;&nbsp;"+ datap[i]+" -- "+datap2[i]+"</td>";
		 $("#tartable").append(row);
		 ii=ii+1;
	 } 
	 $("#tregn").text(ii);
}

function drawregn(j) {
	var hdtmp;
	var ii=0;
	var selsus= $("#sus_no").val();
	var bassus="${roleSusNo}";
	$("#srctable").empty();
	$("#tartable").empty();	
	var a = [];	
	var enc = j[j.length-1];
	a=j;
    var customId;
	for(var i = 0;i < a.length;i++){		
		datap=a[i];
		if(datap[0] != null && hdtmp!=datap[0]){   
			var rowS="<tr id='SRC"+datap[0]+"' padding='5px;'>";
			 	rowS=rowS+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+datap[1]+"' name='"+datap[0]+"' onclick='findselected();' title='Select Budget Head.'>";
			 	rowS=rowS+"&nbsp;&nbsp;"+datap[0]+" - "+datap[1]+"</td>"; 
			$("#srctable").append(rowS);
			if(datap[3] != null && datap[2]==selsus && datap[4]==bassus){
				customId = "[name='"+datap[0]+"']";
		 		$(customId).prop('checked','true');
		 	}
			hdtmp=datap[0];
		}
		if(datap[3] != null && datap[2]==selsus && datap[4]==bassus){
			var rowT="<tr id='TRC"+datap[3]+"' padding='5px;'>";
			 rowT=rowT+"<td>&nbsp;"+datap[3]+" - "+datap[1]+"</td>"; 
			$("#tartable").append(rowT);	
			customId = "[name='"+datap[0]+"']";
			$(customId).prop("checked",true);
			ii=ii+1;
		}
		
	}
	$("#sregn").text(ii);
	$("#tregn").text(ii);
} 

function Assign_text(j) {
	$("#tartable").empty();
	
	var a = [];
	 var trc_sus_no = "";
	 var trc_unit_name = "";
	for(var i = 0;i < j.length;i++)
	{
	 var row="<tr id='TRC"+j[i][1]+" ' padding='5px;'>";
	 row=row+"<td>&nbsp;"+j[i][0]+" -- "+j[i][1]+"</td>";
	 
	 $("#tartable").append(row);
		 if(i == 0)
		 {
			 trc_sus_no = j[i][1];
			 trc_unit_name = j[i][0];
		 }
		 else
		 {
			 trc_sus_no += "--" + j[i][1];
			 trc_unit_name += "--" + j[i][0];
		 }
		
		 if(i == (j.length-1))
		{
			 trc_sus_no += "--";
			 trc_unit_name += "--";
		}
		 $("#tregn").text(i+1);
		 
	 }
	
	$("input#hidden_assign").val(trc_sus_no);
	$("input#hidden_unit_name").val(trc_unit_name);
} 

function getheadlist(){
	var selsus= $("#sus_no").val();	
	$.post("getHeadPrefList?"+key+"="+value,{nPara:selsus}, function(j) {
		if(j == ""){
			$("#srctable").empty();
			$("#d_reg").show();
    		$("#d_reg2").show();
    		$("#ch_d2").show();
    		$("#tartable").show();
    	}else{
    		$("#d_reg").show();
    		$("#d_reg2").show();
    		$("#ch_d2").show();
    		$("#tartable").show();
    		drawregn(j);
    	}
	}); 
}

function getAssignData()
{
	var user_id1=$("#user_id").val();
	user_id1= $("#sus_no").val();
	$.post("getfpHeadAssignList?"+key+"="+value,{user_id:user_id1}, function(j) {
	
   		$("#d_reg").show();
   		$("#d_reg2").show();
   		$("#ch_d2").show();
   		$("#tartable").show();
   		
   		Assign_text(j);
	}); 
}

function refreshUnit() {
	$("#tartable").empty();
	$("#srctable").empty();
	getheadlist();
}

$(document).ready(function() {
	refreshUnit();
	
	$("#sus_no").select2();
	
	$("#nrInputa").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#srctable tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
	
});	
</script>