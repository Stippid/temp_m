<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form name="new_eqpt_details_view" id="new_eqpt_details_view" action="" method="post" class="form-horizontal" commandName="new_eqpt_details_viewCMD"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    		
		            <div class="card-header mms-card-header">
		                <b>VIEW NEW EQPT RELEASE DETAIL</b>
		            </div> 
		            	
	    			<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: left; background-color: white;"> <strong>Basic Details</strong></div>  
	    			<div class="card-body card-block">
	    			    <div class="col-md-6">
	    			       <div class="row form-group">	
    			         		<div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>Sus No :</strong></label>
									   
								</div>
								<div class="col-12 col-md-6">
                  					  <label id="susId"></label>
                		        </div>
                		    </div>
                		    
                		    <div class="row form-group">	
    			         		<div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>Census No :</strong></label>
									   
								</div>
								<div class="col-12 col-md-6">
                  					  <label id="CensusId"></label>
                		        </div>
                		    </div>    
	    			    </div>
	    			    <div class="col-md-6 col-md-offset-1">
	    			        <div class="row form-group">	
	    			            <div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>Type of Holding :</strong></label>
									   
								</div>
								<div class="col-12 col-md-6">
                  					  <label id="hldg"></label>
                		        </div>
                		     </div> 
                		     
                		     <div class="row form-group">	
    			         		<div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>IV NO :</strong></label>
									   
								</div>
								<div class="col-12 col-md-6">
                  					  <label id="rv"></label>
                		        </div>
                		    </div>  
                		    
                		     <div class="row form-group">	
    			         		<div class="col col-md-6">
									   <label for="text-input" class="form-control-label"><strong>IV DATED:</strong></label>
									   
								</div>
								<div class="col-12 col-md-6">
                  					  <label id="ivDate"></label> 
                  					  <input type="hidden" id="reg_no_cmb1" name="reg_no_cmb1">
                  					  <input type="hidden" id="reg_no_seq1" name="reg_no_seq1">
                  					  <input type="hidden" id="reg_cnt1" name="reg_cnt1">
                		        </div>
                		    </div>   
	    			    </div>
	        	</div>
	        	
	        	
	        	
	        	 <div class="card-footer" align="center"> 
	        		<c:if test="${new_eqpt_details_viewCMD[0][7] == 0}">      
					   <%-- <input type="button" class="btn btn-success btn-sm" value="Approve All" onclick="Approved('${new_eqpt_details_viewCMD[0][0]}','${new_eqpt_details_viewCMD[0][1]}','${new_eqpt_details_viewCMD[0][2]}','${new_eqpt_details_viewCMD[0][3]}','${reg_cnt}','','')" />    --%>
					   <input type="button" class="btn btn-success btn-sm" value="Approve All" onclick="Approved();" />
					 </c:if>
					  <input type="button" class="btn btn-danger btn-sm" value="Back" onclick="BackEqpt()"/>
			    </div> 	 
			</div>
		</div>
    </div>
  </div>  
</form:form>

<div class="container" align="center">
	<div id="tableshow">
			<table id="dtBasicExample" class="report_print" border="1">
				   <thead >
								<tr style="text-align:center;">
									<th class="th-sm" style="text-align:center;">Reg No</th> 
									<th class="th-sm" style="text-align:center;">Serviceable</th>
									<th class="th-sm" style="text-align:center;">Barrel1</th>
									<th class="th-sm" style="text-align:center;display: none;"">Ser No</th>
								</tr>
					</thead>
				   <tbody style="background-color: white;">
				     	<c:forEach items="${new_eqpt_details_viewCMD}" var="i" varStatus="loop"> 
				    		<tr>
				    			<td style="text-align:center;"><label id="loop${loop.index}"> ${i[4]} </label></td>
				    			<td style="text-align:center;">${i[12]}</td>
				    			<td style="text-align:center;">${i[6]}</td>
				    			<td style="text-align:center; display: none;"><label id="loop2${loop.index}"> ${i[14]} </label></td>
				    		</tr>
				    	 </c:forEach> 
					</tbody> 
			</table>
	</div>	
</div> 
	
<c:url value="ApproveNewEqptDetails" var="appUrl" />
<form:form action="${appUrl}" method="post" id="AppNewEqptForm" name="AppNewEqptForm" modelAttribute="issue_sus_no">
	<input type="hidden" name="issue_sus_no" id="issue_sus_no"/>
	<input type="hidden" name="census_no" id="census_no"/>
	<input type="hidden" name="type_of_hldg" id="type_of_hldg"/>
	<input type="hidden" name="iv_no" id="iv_no"/>
	<input type="hidden" name="reg_count" id="reg_count"/>
	<input type="hidden" name="reg_no_cmb" id="reg_no_cmb"/>
	<input type="hidden" name="reg_no_ser" id="reg_no_ser"/>
</form:form>

<c:url value="unitneweqptlist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m2_unit4" name="m2_unit4" modelAttribute="m2_sus">
      <input type="hidden" name="m2_sus" id="m2_sus"/>
	  <input type="hidden" name="m2_stat" id="m2_stat"/>
	  <input type="hidden" name="m2_frmdt" id="m2_frmdt"/>
	  <input type="hidden" name="m2_todt" id="m2_todt"/>
	  <input type="hidden" name="m2_unit" id="m2_unit"/>
</form:form> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>				
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
				
<script>
function BackEqpt() {
	$("#m2_sus").val('${new_eqpt_details_viewCMD[0][0]}');
	$("#m2_stat").val('${op_status}');
	$("#m2_frmdt").val('${eqpt_frdt}');
	$("#m2_todt").val('${eqpt_todt}');   
	$("#m2_unit").val('${new_eqpt_details_viewCMD[0][9]}');
	$("#nrWaitLoader").show();
	$("#m2_unit4").submit();	
}

function Approved(){
	document.getElementById('issue_sus_no').value='${new_eqpt_details_viewCMD[0][0]}';
	document.getElementById('census_no').value='${new_eqpt_details_viewCMD[0][1]}';
	document.getElementById('type_of_hldg').value='${new_eqpt_details_viewCMD[0][2]}';
	document.getElementById('iv_no').value='${new_eqpt_details_viewCMD[0][3]}';
	document.getElementById('reg_count').value=$("#reg_cnt1").val(); 
	document.getElementById('reg_no_cmb').value=$("#reg_no_cmb1").val();
	document.getElementById('reg_no_ser').value=$("#reg_no_seq1").val();
	document.getElementById('AppNewEqptForm').submit();
}

</script>

<script>
$(document).ready(function() {
	var f_reg_no_cmb = [];
    var f_reg_no_seq = [];
 
    var reg_count = '${reg_cnt}'; 
    $("#reg_cnt1").val(reg_count); 
           
    for(var i1= 0;i1<reg_count;i1++){
    	f_reg_no_cmb.push($("#loop"+i1).text());
    }
  
    var reg_no_cmb1 = f_reg_no_cmb.join(":");

    var a11 = reg_no_cmb1.toString();
    var a12 = a11.replace(/ : /g, ":");
    $("#reg_no_cmb1").val(a12);
    	   
    	   
    for(var i1= 0;i1<reg_count;i1++){
    	f_reg_no_seq.push($("#loop2"+i1).text());
    }
    var reg_no_seq1 = f_reg_no_seq.join(":");
   
    var b11 = reg_no_seq1.toString();
    var b12 = b11.replace(/ : /g, ":");
    $("#reg_no_seq1").val(b12);
    
    var d = new Date('${new_eqpt_details_viewCMD[0][13]}');
    var newDate = d.toLocaleDateString('en-GB', {
    	day: 'numeric', month: 'short', year: 'numeric'
    }).replace(/ /g, '-');
    	   
    $("label#susId").text('${new_eqpt_details_viewCMD[0][0]} - ${new_eqpt_details_viewCMD[0][9]}');  
    $("label#CensusId").text('${new_eqpt_details_viewCMD[0][1]} - ${new_eqpt_details_viewCMD[0][10]}');  
    $("label#hldg").text('${new_eqpt_details_viewCMD[0][11]}');  
    $("label#rv").text('${new_eqpt_details_viewCMD[0][3]}');  
    $("label#ivDate").text(newDate);
    	   
});
</script>
