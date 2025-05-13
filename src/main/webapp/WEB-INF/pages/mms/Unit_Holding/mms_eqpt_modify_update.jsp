<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

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
<form  class="form-horizontal" method="post" > 
 <div class="nkpageland" id="printableArea">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center" id="headerView" style="display: block;">  
    		<div class="card">  
    		
		        <div class="card-header mms-card-header">
		             <b>UPDATION OF EQPT SERVICEABILITY STATUS</b>
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
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Census No</label> 
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
	             		 								
	               		<div class="col-md-3" style="text-align: left;">
	                 		  <label class=" form-control-label" style="margin-left: 13px;">Registered No Search</label>
	               		 </div>
	               		 <div class="col-md-3">
	               			 <input  id="regn_seq_no" name="regn_seq_no" placeholder="Enter Regd No" class="form-control-sm form-control" autocomplete="off" maxlength="20" required>
						  </div>	    
  					</div>					
  				</div>
  				
  			    <div class="card-footer" align="center" style="margin-top: -10px;">
  			    <a href="mms_eqpt_modify_update" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a>
					<!-- <input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" /> -->
             		<input type="button" class="btn btn-success btn-sm" value="Search" onclick="return validate();">
             		<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
               </div>  
  			</div>   
  		</div>
	</div>	
<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b>UPDATION OF EQPT SERVICEABILITY STATUS</b></div> 
		
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
					
								            <th width="10%" id="rdViewth" style="text-align: center;" class="nrBox"></th>
	            							<th width="20%" style="text-align: center;" class="nrBox">Census No</th>
	                    					<th width="40%" style="text-align: center;" class="nrBox">Nomenclature</th>
	                    					<th width="10%" style="text-align: center;" class="nrBox">Types of Holdings</th>
	                    					<th width="10%" style="text-align: center;" class="nrBox">Types of Eqpt.</th>
	            							<th width="10%" style="text-align: center;" class="nrBox">Registered No</th>
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
    								           <%-- <c:out value="${fn:length(dataf)}"></c:out>  --%>
    
    								           <tr id='NRRDO' name='NRRDO'>
    								                  <td width="10%" id="rdView"><input type="radio" id="NRRDOO${dataf[5]}" name="n" onclick="setid('${dataf[5]}');"></td>
						                              <td width="20%">${dataf[0]}</td>
						                              <td width="40%">${dataf[1]}</td>
						                              <td width="10%">${dataf[2]}</td>
						                              <td width="10%">${dataf[3]}</td>
						                              <td width="10%">${dataf[4]}</td>
						                              <td style="display: none;">
						                              	<input type="hidden" id="NRIN${dataf[5]}" name="NRIN${dataf[5]}"  value="${dataf[4]}">
						                              	<input type="hidden" id="sus_no_${dataf[5]}" name="sus_no_${dataf[5]}"  value="${dataf[8]}">
						                              </td>
						                              <% ntotln++; %>
    								           </tr>   
    								        </c:forEach>   
    								    </c:if> 
    								    
    								</tbody>
								</table>
								</div>
							</div>
							
							<div class="card-footer" align="center">
								<a hreF="#" id="update" type="submit"  class="btn btn-primary btn-sm" data-target="#rejectModal" data-toggle="modal" style="display:none;"  onclick="return changeregn();">Update</a> 
             				</div>
				
		   </div>
		</div>
	
		<div class="card-footer" id="pId" style="display: none;">
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');">
		     <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">
        </div>
     	   
	
</form>

<c:url value="mms_list_regn_no" var="backUrl" />
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

<!-- The Reject Modal -->
 <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 700px;">
        <!-- Modal Header -->
        <div class="modal-header" >
           <h4 class="modal-title"><b><strong style="text-decoration: underline;padding-left: 180px;">SERVICEABLITY STATE</strong></b></h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">	
 			 <div class="col-md-12 row form-group">
       	 		 <div class="col-md-4" style="text-align: left;"> 
          			  <label class=" form-control-label"><strong style="color: red;">* </strong>Eqpt Registration No.</label> 
        		 </div>
        		 <div class="col-md-7">
        	        <input type="hidden" id="nrSrcSel">
         			<input type="hidden" id="id" name="id" placeholder=""  class="form-control" >
           			<input type="text" id="eqpt_regn_no" name="eqpt_regn_no" maxlength="25" placeholder=""  class="form-control-sm form-control" readonly="readonly">
          		 </div>
          		     
  			 </div>
 			
 			 <div class="col-md-12 row form-group">
			      <div class="col-md-4" style="text-align: left;"> 
            	         <label class=" form-control-label"><strong style="color: red;">* </strong>Serviceability</label> 
                  </div>
                  <div class="col-md-7">
             	         <select id="service_status" name="service_status"  class="form-control-sm form-control">
             	                  <option selected disabled value="-1">--Select--</option>
								  <c:forEach var="item" items="${ml_2}">
									  <option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
								  </c:forEach>
             	         </select>
                  </div>
                  
  			 </div>
					
		     <div class="col-md-12 row form-group">
           	 	  <div class="col-md-4" style="text-align: left;"> 
              			  <label class=" form-control-label" style="margin-left: 13px;">Barrel - I</label> 
            	  </div>
            	  <div class="col-md-7">
            			 <input id="barrel1_detl" name="barrel1_detl" maxlength="150" placeholder="" class="form-control-sm form-control"   >
              	  </div>
            </div>
					
  					<div class="col-md-12 row form-group">
             	 		 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label" style="margin-left: 13px;">Barrel - II</label> 
	             		 </div>
	             		 <div class="col-md-7">
	             			 <input id="barrel2_detl" name="barrel2_detl" maxlength="150" placeholder="" class="form-control-sm form-control"   >
	               		 </div>
	               	</div>
  			
  					<div class="col-md-12 row form-group">
             	 		 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label" style="margin-left: 13px;">Barrel - III</label> 
	             		 </div>
	             		 <div class="col-md-7">
	             			 <input id="barrel3_detl" name="barrel3_detl" maxlength="150" placeholder="" class="form-control-sm form-control"  >
	               		 </div>
	               	</div>
  					
  				    <div class="col-md-12 row form-group">
             	 		 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label" style="margin-left: 13px;">Barrel - IV</label> 
	             		 </div>
	             		 <div class="col-md-7">
	             			 <input id="barrel4_detl" name="barrel4_detl" maxlength="150" placeholder="" class="form-control-sm form-control"  >
	               		 </div>
	               	</div>
  					<div class="col-md-12 row form-group">
             	 		 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label" style="margin-left: 13px;">Remarks</label> 
	             		 </div>
	             		 <div class="col-md-8">
	             			<textarea  id="spl_remarks" name="spl_remarks" maxlength="255" placeholder="" class="form-control-sm form-control"  ></textarea>
	               		 </div>
  					</div>
  					<div class="col-md-12 row form-group" id="arty_unit_flag" style="display: none;" align="center">
             	 		 <div class="col-md-4">
	             		 	<button type="button" data-target='#ohModel' data-toggle='modal' class="btn btn-primary btn-sm" onclick="oh_popup();" style="border-radius: 5px;">Over Haul Detls</button>
	             		 </div>
	             		 <div class="col-md-4">
	             		 	<button type="button" data-target='#barrelModel' data-toggle='modal' class="btn btn-primary btn-sm" onclick="barrel_popup('II',1);" style="border-radius: 5px;">Barrel Detls</button>
	             		 </div>
	             		 <div class="col-md-4">
	             		 	<button type="button" data-target='#strip_inspModel' data-toggle='modal' class="btn btn-primary btn-sm" onclick="strip_insp_popup();" style="border-radius: 5px;">Strip Inspection</button>
	             		 </div>
	             	</div>
  			<div align="center">
				<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="border-radius: 5px;">Close</button>
				<button type="button" name="submit" class="btn btn-success btn-sm" data-dismiss="modal" onclick="update_popup();" style="border-radius: 5px;">Update Data</button>
			</div>
        </div>
      </div>
    </div>
  </div>
  
 
<!-- Barrel modal -->
	<div class="modal fade" id="barrelModel" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px;">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b><strong style="text-decoration: underline; padding-left: 180px;">BARREL DETAILS</strong> (<span id="regnHeading"></span>)</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
				<form:form action="#" id="Barrel_detailsForm" commandName="Barrel_detailsFormCMD" method="post" class="form-horizontal">
					<input type="hidden" id="b_id" name="b_id" value="0">
					<input type="hidden" id="eqpt_regn_no1" name="eqpt_regn_no1">
					
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label"><strong style="color: red;">* </strong> Barrel Regn no</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="barrel_regn_no" name="barrel_regn_no"  class="form-control-sm form-control">
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">Op Clearance</label>
						</div>
						<div class="col-md-3">
							<select id="op_clear" name="op_clear" class="form-control-sm form-control">
								<option value="YES">YES</option>
								<option value="NO">NO</option>
							</select>
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">Op Clearance Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="op_clear_dt" name="op_clear_dt"  class="form-control-sm form-control"  >
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">WKSP Name</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="wksp_name" name="wksp_name"  class="form-control-sm form-control"  >
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">WKSP in Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="wksp_in_dt" name="wksp_in_dt"  class="form-control-sm form-control"  >
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">CoFR Vertical (mm)</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="rifling_vertical" name="rifling_vertical"  class="form-control-sm form-control" placeholder="Ex. 0000.0000" >
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">CoFR Horizontal (mm)</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="rifling_horizontal" name="rifling_horizontal"  class="form-control-sm form-control" placeholder="Ex. 0000.0000" >
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">Qtr of Life</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="qtr_of_life" name="qtr_of_life"  class="form-control-sm form-control" placeholder="Ex. 1,2,3,4">
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">EFC</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="efc" name="efc"  class="form-control-sm form-control" placeholder="Ex. 0000.0000">
						</div>
						
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">Total Rds Fired</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="total_rds" name="total_rds"  class="form-control-sm form-control"  >
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">Last Fired Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="dt_last_fired" name="dt_last_fired"  class="form-control-sm form-control">
						</div>
					</div>
					<div align="center">
						<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="border-radius: 5px;">Close</button>
						<button type="button" class="btn btn-success btn-sm" onclick="update_Barrel_details();" style="border-radius: 5px;">Update Barrel Data</button>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- Barrel modal --> 

<!-- Strip Inspection modal -->
	<div class="modal fade" id="strip_inspModel" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px;">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b><strong style="text-decoration: underline; padding-left: 180px;">Strip Inspection </strong> (<span id="regn_strip_Heading"></span>)</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form:form action="#" id="strip_inspForm" commandName="Barrel_detailsFormCMD" method="post" class="form-horizontal">
						<input type="hidden" id="si_id" name="si_id">
						<input type="hidden" id="eqpt_regn_no2" name="eqpt_regn_no2">
						<table id="strip_insp_table" border="1" style="width: 100%;">
							<thead>
							<tr>
								<th><strong style="color: red;">* </strong> Recoil Sys Regn No</th>
								<th>Periodicity (in years)</th>
								<th>Dt of insp</th>
								<th>Dt of next insp</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div align="center">
							<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="border-radius: 5px;">Close</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
<!-- Strip Inspection model  -->

<!-- OH modal -->
	<div class="modal fade" id="ohModel" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px;">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<b><strong style="text-decoration: underline; padding-left: 180px;">OH DETAILS</strong> (<span id="ohHeading"></span>)</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
				<form:form action="#" id="oh_detailsForm" commandName="oh_detailsFormCMD" method="post" class="form-horizontal">
					<input type="hidden" id="eqpt_regn_no3" name="eqpt_regn_no3">
					<input type="hidden" id="oh_id" name="oh_id" value="0">
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label"><strong style="color: red;">* </strong> OH Type</label>
						</div>
						<div class="col-md-3">
							<select id="oh_type" name="oh_type" class="form-control-sm form-control" onchange="OH_TypeChange()">
								<option value="0">--Select--</option>
								<option value="1">OH 1</option>
								<option value="2">OH 2</option>
								<option value="3">OH 3</option>
							</select>
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">OH Due Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="oh_due_dt" name="oh_due_dt"  class="form-control-sm form-control"  >
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">OH Done Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="oh_done_dt" name="oh_done_dt"  class="form-control-sm form-control"  >
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">WKSP Name</label>
						</div>
						<div class="col-md-3">
							<input type="text" id="wksp_name_oh" name="wksp_name_oh"  class="form-control-sm form-control"  >
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">WKSP in Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="wksp_in_dt_oh" name="wksp_in_dt_oh"  class="form-control-sm form-control"  >
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">Dispatch dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="dispatch_dt" name="dispatch_dt"  class="form-control-sm form-control" >
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">BOH Compl Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="boh_compl_dt" name="boh_compl_dt"  class="form-control-sm form-control">
						</div>
					</div>
					<div class="col-md-12 row form-group">
						<div class="col-md-3">
							<label class=" form-control-label">Gun Recd Dt</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="gun_recd_dt" name="gun_recd_dt"  class="form-control-sm form-control">
						</div>
						<div class="col-md-3">
							<label class=" form-control-label">Dt of Intro</label>
						</div>
						<div class="col-md-3">
							<input type="date" id="dt_of_intro" name="dt_of_intro"  class="form-control-sm form-control">
						</div>
					</div>
					<div align="center">
						<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="border-radius: 5px;">Close</button>
						<button type="button" class="btn btn-success btn-sm" onclick="update_oh_details();" style="border-radius: 5px;">Update OH Data</button>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- OH modal --> 

<script>

	function oh_popup(){
		$("span#ohHeading").text($("#eqpt_regn_no").val());
		$("#eqpt_regn_no3").val($("#eqpt_regn_no").val());
		$("#oh_id").val(0);
		$("#oh_type").val("0");
		$("#oh_due_dt").val("");
		$("#oh_done_dt").val("");
		$("#dispatch_dt").val("");
		$("#boh_compl_dt").val("");
		$("#gun_recd_dt").val("");
		$("#dt_of_intro").val("");
		$("#wksp_name_oh").val("");
		$("#wksp_in_dt_oh").val("");
	}
	function OH_TypeChange(){
		var oh_type = $("#oh_type").val();
		if(oh_type == "0"){
			$("#oh_id").val(0);
			$("#oh_due_dt").val("");
			$("#oh_done_dt").val("");
			$("#dispatch_dt").val("");
			$("#boh_compl_dt").val("");
			$("#gun_recd_dt").val("");
			$("#dt_of_intro").val("");
			$("#wksp_name_oh").val("");
			$("#wksp_in_dt_oh").val("");
		}else{
			$.post("getOH_details?"+key+"="+value,{oh_type:oh_type,eqpt_regn_no:$("#eqpt_regn_no").val()},function(j) {
				if(j.id > 0){
					$("#oh_id").val(j.id);
					$("#oh_due_dt").val(ParseDateColumn(j.oh_due_dt));
					$("#oh_done_dt").val(ParseDateColumn(j.oh_done_dt));
					$("#dispatch_dt").val(ParseDateColumn(j.dispatch_dt));
					$("#boh_compl_dt").val(ParseDateColumn(j.boh_compl_dt));
					$("#gun_recd_dt").val(ParseDateColumn(j.gun_recd_dt));
					$("#dt_of_intro").val(ParseDateColumn(j.dt_of_intro));
					$("#wksp_name_oh").val(j.wksp_name);
					$("#wksp_in_dt_oh").val(ParseDateColumn(j.wksp_in_dt));
				}
			});
		}
	}
	function update_oh_details(){
		var oh_type = $("#oh_type").val();
		if(oh_type == "0"){
			alert("please select OH Type");
			 $("#oh_type").focus();
			 return false;
		}else{
			var form = $('#oh_detailsForm')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "oh_detailsAction?"+key+"="+value,
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
				}
			});
		}
	}
	
	function strip_insp_popup(){
		$("span#regn_strip_Heading").text($("#eqpt_regn_no").val());
		$("#eqpt_regn_no2").val($("#eqpt_regn_no").val());
		
		$.post("getstrip_insp_details?"+key+"="+value,{eqpt_regn_no:$("#eqpt_regn_no").val()},function(j) {
			var markup = "";
			var i = 1;
			$("#strip_insp_table tbody").empty();
			for(var p=0;p<j.length;p++){
				i = (p+1);
				markup += "<tr>"+
							"<td>"+j[p].recoil_sys_regd_no+"</td>"+
							"<td>"+j[p].periodicity+"</td>"+
							"<td>"+ParseDateColumn(j[p].done_dt)+"</td>"+
							"<td>"+ParseDateColumn(j[p].due_dt)+"</td>"+
							"<td></td>"+
						"</tr>";
			}
			
			markup += "<tr><td><input type='text' id='recoil_sys_regd_no"+i+"' name='recoil_sys_regd_no"+i+"' class='form-control-sm form-control'></td>"+
				"<td><input type='text' id='periodicity"+i+"' name='periodicity"+i+"' class='form-control-sm form-control' max='10'></td>"+
				"<td><input type='date' id='done_dt"+i+"' name='done_dt"+i+"' class='form-control-sm form-control'></td>"+
				"<td><input type='date' id='due_dt"+i+"' name='due_dt"+i+"' class='form-control-sm form-control'></td>"+
				"<td><button type='button' id='bthstrip"+i+"' class='btn btn-success btn-sm' onclick='update_strip_insp_details("+i+");' style='border-radius: 5px;'><i class='fa fa-plus' aria-hidden='true'></i></button></td>"+
			"</tr>";
			$("#strip_insp_table tbody").append(markup);
		});
	}
	function update_strip_insp_details(si_id){
		$("#si_id").val(si_id);
		if($("#recoil_sys_regd_no"+si_id).val() == ""){
			alert("please enter Recoil Sys Regn No");
			$("#recoil_sys_regd_no"+si_id).focus();
			return false;
		}else{
			var form = $('#strip_inspForm')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "strip_inspAction?"+key+"="+value,
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					if(data == "Data Submitted"){
						$("#bthstrip"+si_id).hide();
						$("#recoil_sys_regd_no"+si_id).prop("readonly", true);
						$("#periodicity"+si_id).prop("readonly", true);
						$("#done_dt"+si_id).prop("readonly", true);
						$("#due_dt"+si_id).prop("readonly", true);
						
						i = (si_id+1);
						var markup = "<tr><td><input type='text' id='recoil_sys_regd_no"+i+"' name='recoil_sys_regd_no"+i+"' class='form-control-sm form-control'></td>"+
							"<td><input type='text' id='periodicity"+i+"' name='periodicity"+i+"' class='form-control-sm form-control' max='10'></td>"+
							"<td><input type='date' id='done_dt"+i+"' name='done_dt"+i+"' class='form-control-sm form-control'></td>"+
							"<td><input type='date' id='due_dt"+i+"' name='due_dt"+i+"' class='form-control-sm form-control'></td>"+
							"<td><button type='button' id='bthstrip"+i+"' class='btn btn-success btn-sm' onclick='update_strip_insp_details("+i+");' style='border-radius: 5px;'><i class='fa fa-plus' aria-hidden='true'></i></button></td>"+
						"</tr>";
						$("#strip_insp_table tbody").append(markup);
					}else{
						alert(data);
					}
				}
			});
		}
	}
	function ParseDateColumn(data) {
		var date = "";
		if(data != null && data != ""){
			var d = new Date(data);
		  	var date =   d.getFullYear() +'-'+ ("0" + (d.getMonth()+1)).slice(-2)+'-'+ ("0" + d.getDate()).slice(-2);
		}
		return date;
	}

	
	
	function barrel_popup(barrel_id,id) {
		$("span#barrelHeading").text(barrel_id);
		$("span#regnHeading").text($("#eqpt_regn_no").val());
		$("#eqpt_regn_no1").val($("#eqpt_regn_no").val());
		
		$("#b_id").val(0);
		$("#barrel_regn_no").val("");
		$("#op_clear").val("");
		$("#op_clear_dt").val(null);
		$("#wksp_name").val("");
		$("#wksp_in_dt").val(null);
		$("#rifling_vertical").val("");
		$("#qtr_of_life").val("");
		$("#rifling_horizontal").val("");
		$("#efc").val("");
		$("#total_rds").val("");
		$("#dt_last_fired").val(null);
	}
	
	function update_Barrel_details(){
		var barrel_regn_no = $("#barrel_regn_no").val();
		if(barrel_regn_no == ""){
			alert("please enter Barrel Regn No");
			 $("#barrel_regn_no").focus();
			 return false;
		}else{
			var form = $('#Barrel_detailsForm')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "Barrel_detailsAction?"+key+"="+value,
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
				}
			});
		}
	}

	function printDiv() {
		$("#SearchViewtr").hide();
		$("#tdheaderView").show();
		$("#headerView").hide();
		$("#btn_e").hide();
		$("#btn_p").hide();
		$("#btn_modify").hide();
		$("td#rdView").hide();
		$("#update").hide();
		$("th#rdViewth").hide();
		// $('.rdView').css('display','none');
		//let popupWinindow
		let innerContents = document.getElementById('printableArea').innerHTML;

		popupWindow = window
				.open(
						'',
						'_blank',
						'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();
		popupWindow.document
				.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');

		popupWindow.document.close();
		$("#SearchViewtr").show();
		$("#tdheaderView").hide();
		$("#headerView").show();
		$("#btn_e").show();
		$("#btn_p").show();
		$("#btn_modify").show();
		$("td#rdView").show();
		$("#update").hide();
		$("th#rdViewth").show();
		//$('.rdView').css('display','block');

	}

	function exportData(b) {

		$().tbtoExcel(b);

	}

	function btn_clc() {
		location.reload(true);
	}

	function validate() {

		if ($("#from_sus_Search").val() == "") {
			alert("Please Search From SUS No or Unit Name");
			$("#from_sus_Search").focus();
			return false;
		} else if ($("#from_sus_no").val() == "-1") {
			alert("Please Select From Unit");
			$("#from_sus_no").focus();
			return false;
		}

		if ($("#from_sus_no").val() != "" && $("#regn_seq_no").val() != "") {

			mms_list_regn_no();
		} else if ($("#from_sus_Search").val() != ""
				&& $("#from_sus_no").val() != ""
				&& $("#regn_seq_no").val() == "") {

			if ($("#prf_code").val() == -1) {
				alert("Please Select the PRF Group");
				$("#prf_code").focus();
				return false;
			}

			if ($("#census_no").val() == -1) {
				alert("Please Select the Census No");
				$("#census_no").focus();
				return false;
			}

			if ($("#type_of_hldg").val() == -1) {
				alert("Please Select the Type of Holding");
				$("#type_of_hldg").focus();
				return false;
			}
			mms_list_regn_no();
			return true;
		}

	}

	function getfromunit(nValue) {
		if (nValue.length <= 0) {
			alert("Enter Search Word...");
			return false;
		} else {

			$
					.post("getMMSUnitListBySearch?" + key + "=" + value, {
						nValue : nValue
					}, function(j) {

					})
					.done(
							function(j) {

								if (j.length <= 0 || j == null || j == '') {
									alert("No Search Result Found");
									$("#from_sus_Search").focus();
								} else {
									$("#from_sus_no").attr('disabled', false);

									if ('${r_1[0][7]}' != "UNIT") {
										var options = '<option value="' + "-1" + '">'
												+ "--Select Unit--"
												+ '</option>';
									}

									var a = [];
									var enc = j[j.length - 1].substring(0, 16);
									for (var i = 0; i < j.length; i++) {
										a[i] = dec(enc, j[i]);
									}

									var data = a[0].split(",");
									var datap;

									for (var i = 0; i < data.length - 1; i++) {
										datap = data[i].split(":");
										if (datap != null) {
											if (datap[1].length > 90) {
												options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'
														+ datap[1]
														+ ' - '
														+ datap[2].substring(1,
																50)
														+ '</option>';
											} else {
												options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'
														+ datap[1]
														+ ' - '
														+ datap[2]
														+ '</option>';
											}
										}
									}
									$("select#from_sus_no").html(options);

									var sq = '${m_2}';
									var usq = '${sus_n}';
									if (sq != "") {
										$("select#from_sus_no").val(sq);
									}
									if (usq != "") {
										$("select#from_sus_no").val(usq);
									}
								}

							}).fail(function(xhr, textStatus, errorThrown) {
					});
		}

	}

	function changePrf(j) {
		$('#prf_code').attr('disabled', false);
		var nParaValue = "";
		var sus_no = $("#from_sus_no").val();

		if (j == "") {
			nParaValue = sus_no;
		} else {
			nParaValue = j;
		}

		var nPara = "SUS";

		$
				.post("getmms_distinct_prf_group_by_sus?" + key + "=" + value,
						{
							nParaValue : nParaValue,
							nPara : nPara
						}, function(j) {

						})
				.done(
						function(j) {

							if (j.length <= 0 || j == "") {
								alert("Sorry! No PRF Group Found.");
								var options = '<option value="' + "-1" + '">'
										+ "--Select PRF Group--" + '</option>';
								$("select#prf_code").html(options);
								$("#prf_code").attr('disabled', true);
								$("#census_no").attr('disabled', true);

								return false;
							} else {
								var options = '<option value="' + "-1" + '">'
										+ "--Select PRF Group--" + '</option>';

								var a = [];
								var enc = j[j.length - 1].substring(0, 16);
								for (var i = 0; i < j.length; i++) {
									a[i] = dec(enc, j[i]);
								}

								var data = a[0].split(",");
								var datap;

								for (var i = 0; i < data.length - 1; i++) {
									datap = data[i].split(":");
									if (datap != null) {
										if (datap[1].length > 90) {
											options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'
													+ datap[1].substring(1, 90)
													+ '</option>';
										} else {
											options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'
													+ datap[1] + '</option>';
										}
									}
								}
								$("select#prf_code").html(options);

								var q = '${m_6}';
								var q1 = '${m_2}';
								var pq = $("#from_sus_no").val();

								if (q != "" && q1 != "") {
									$("select#prf_code").val(q);
								}
							}

						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function chgcensus(nSusNo, nPrfCode) {

		$
				.post("getmms_distinct_census_by_sus?" + key + "=" + value, {
					nSusNo : nSusNo,
					nPrfCode : nPrfCode
				}, function(j) {

				})
				.done(
						function(j) {

							if (j.length <= 0 || j == "") {
								alert("Sorry! Census No Not Found.");
								var options = '<option value="' + "-1" + '">'
										+ "--Select Census--" + '</option>';
								$("select#census_no").html(options);
								$("#census_no").attr('disabled', true);

								return false;
							} else {
								var options = '<option value="' + "-1" + '">'
										+ "--Select Census--" + '</option>';

								var a = [];
								var enc = j[j.length - 1].substring(0, 16);
								for (var i = 0; i < j.length; i++) {
									a[i] = dec(enc, j[i]);
								}

								var data = a[0].split(",");
								var datap;

								for (var i = 0; i < data.length - 1; i++) {
									datap = data[i].split(":");
									if (datap != null) {
										if (datap[1].length > 90) {
											options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'
													+ datap[0]
													+ ' - '
													+ datap[1].substring(1, 60)
													+ '</option>';
										} else {
											options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'
													+ datap[0]
													+ ' - '
													+ datap[1] + '</option>';
										}
									}
								}
								$("select#census_no").html(options);

								var q = '${m_3}';

								var pq = $("#prf_code").val();

								if (q != "" && nSusNo != "" && nPrfCode != "") {
									$("#census_no").val(q);
								}
							}

						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function chghldgType(nParaValue) {
		var nPara = "SUS";

		$
				.post(
						"getmms_distinct_type_of_hldg_by_sus_frmtbl?" + key
								+ "=" + value, {
							nParaValue : nParaValue,
							nPara : nPara
						}, function(j) {

						})
				.done(
						function(j) {

							if (j.length <= 0 || j == "") {
								alert("Sorry! No Type of Holding Found.");
								var options = '<option value="' + "-1" + '">'
										+ "--Select Type of Hldg--"
										+ '</option>';
								$("select#type_of_hldg").html(options);
								$("#type_of_hldg").attr('disabled', true);
								return false;
							} else {

								var options = '';
								var a = [];
								var enc = j[j.length - 1].substring(0, 16);
								for (var i = 0; i < j.length; i++) {
									a[i] = dec(enc, j[i]);
								}

								var data = a[0].split(",");
								var datap;
								for (var i = 0; i < data.length - 1; i++) {
									datap = data[i].split(":");
									if (datap != null) {
										if (datap[1].length > 90) {
											options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'
													+ datap[1].substring(1, 90)
													+ '</option>';
										} else {
											options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'
													+ datap[1] + '</option>';
										}
									}
								}
								$("select#type_of_hldg").html(options);
								var q1 = '${m_4}';
								if (q1 != "") {
									$("#type_of_hldg").val(q1);
								}
								$("#type_of_hldg").attr('disabled', false);
							}
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function setid(a) {
		$("#nrSrcSel").val(a);
		$("#update").show();
	}
	function mms_list_regn_no() {
		$("#m3_sus").val($("#from_sus_no").val());
		$("#m3_cen").val($("#census_no").val());
		$("#m3_hldg").val($("#type_of_hldg").val());
		$("#m3_regnseq").val($("#regn_seq_no").val());
		$("#m3_prf").val($("#prf_code").val());
		$("#m3_unit").val($("#from_sus_Search").val());
		$("#nrWaitLoader").show();
		$("#m3_unit3").submit();
	}
	function changeregn() {
		Reject();
	}
	function Reject() {
		var regseqno = $("#nrSrcSel").val();
		var regn_no = $("#NRIN" + regseqno).val();
		var sus_no = $("#sus_no_" + regseqno).val();
		$.post("getArmCodeFromSus_no?" + key + "=" + value, {
			sus_no : sus_no
		}, function(j) {
			if (j.length > 0) {
				if (j.toString().substring(0, 2) == '02') {
					$("div#arty_unit_flag").show();
				}else{
					$("div#arty_unit_flag").hide();
				}
			} else {
				alert('Data Not Available');
			}
		});

		$("#eqpt_regn_no").val(regn_no);
		var service_status = $("#service_status").val();
		$.post("editdata_eqpt?" + key + "=" + value, {
			r1 : regseqno,
			r2 : regn_no
		}, function(j) {
		}).done(function(j) {
			var a = [];
			var enc = j[j.length - 1].substring(0, 16);
			for (var i = 0; i < j.length; i++) {
				a[i] = dec(enc, j[i]);
			}
			var data = a[0].split(",");
			var datap;
			for (var i = 0; i < data.length - 1; i++) {
				datap = data[i].split(":");
				$("#depres_dur_year").val(datap[0]);
				$("#barrel1_detl").val(datap[1]);
				$("#service_status").val(datap[2]);
				$("#barrel2_detl").val(datap[3]);
				$("#barrel3_detl").val(datap[4]);
				$("#barrel4_detl").val(datap[5]);
				$("#spl_remarks").val(datap[6]);
				$("#id").val(datap[7]);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}

	function update_popup() {

		if ($("#eqpt_regn_no").val() == "") {
			alert("Eqpt Regn No can't be Null");
			$("#eqpt_regn_no").focus();
			return false;
		}

		if ($("#service_status").val() == -1) {
			alert("Please Select the Service Status");
			$("#service_status").focus();
			return false;
		}

		var regn_seq_no = $("#nrSrcSel").val();
		var eqpt_regn_no = $("#eqpt_regn_no").val();
		var service_status = $("#service_status").val();
		var barrel1_detl = $("#barrel1_detl").val();
		var barrel2_detl = $("#barrel2_detl").val();
		var barrel3_detl = $("#barrel3_detl").val();
		var barrel4_detl = $("#barrel4_detl").val();
		var spl_remarks = $("#spl_remarks").val();

		$.ajax({
			type : 'POST',
			url : "ChangeEqptReg?" + key + "=" + value,
			data : {
				regn_seq_no : regn_seq_no,
				eqpt_regn_no : eqpt_regn_no,
				service_status : service_status,
				barrel1_detl : barrel1_detl,
				barrel2_detl : barrel2_detl,
				barrel3_detl : barrel3_detl,
				barrel4_detl : barrel4_detl,
				spl_remarks : spl_remarks
			},
			success : function(response) {
				alert(response);

			}
		});
	}
</script>
<script>
$(document).ready(function(){
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
		$("#pId").show();
		
		$("#from_sus_Search").val('${m_7}');	
		getfromunit('${m_7}');
		chghldgType('${m_2}');
		
		$("#type_of_hldg").val('${m_4}');
		$("#regn_seq_no").val('${m_5}');
		changePrf('${m_2}');
		
		$("#census_no").attr('disabled',false);
		chgcensus('${m_2}','${m_6}');
		nrSetWaterMark(<%=ntotln+2%>);
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
</script>    