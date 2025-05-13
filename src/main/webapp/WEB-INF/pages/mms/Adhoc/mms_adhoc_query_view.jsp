<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_wpn_eqpt_status_viewCMD">
  <div>		
     <div class="container" align="center"> 
        <div class="card">
				<div class="card-header mms-card-header">
		               <b>WEAPON AND EQPT ADHOC QUERY</b>
		        </div> 	
		        
		        <div class="card-body card-block">
			            <div class="col-md-12 row form-group">
					        <div class="col-md-2" style="text-align: left;">
								<label for="text-input" class=" form-control-label">PRF Group</label>
							</div>
						
							
								<div class="col-md-2" style="text-align: left;">
	                  				 <input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10"/>
	                			</div>
	                			
	                			<div class="col-md-1" style="text-align: left;">
	                			      <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
	                			</div>
	                			
	                			<div class="col-md-7" style="text-align: left;">
	                			      <select id="prf_code" name="prf_code" class="form-control-sm form-control" disabled="disabled">
	                                     <option value="ALL">--All PRF Groups --</option> 
	                                  </select>
	                			</div>
						</div>
				        
				        <div class="col-md-12 row form-group" style="margin-top: -10px;">
				            <div class="col-md-2" style="text-align: left;"> 
					            <label for="text-input" class=" form-control-label">Type of Holding</label>
					        </div>
					        <div class="col-md-5">
					            <select name="type_of_hldg" id="type_of_hldg" class="form-control-sm form-control" title="Select a Type of Holding" >	
										<option value="ALL">-- ALL Holdings --</option>
										<c:forEach var="item" items="${ml_2}">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
										</c:forEach>                  							
								 </select>
					        </div>
					        <div class="col-md-2" style="text-align: left;"> 
					            <label class=" form-control-label">Period</label>
					        </div>
					        <div class="col-md-3">
					            <input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control" title="Select Reporting Month"/>
					        </div>
				        </div>
				        
				        <div class="col-md-12 row form-group" style="margin-top: -10px;">
				               <div class="col-md-2" style="text-align: left;"> 
						               <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Arm</label>
						       </div>
						       <div class="col-md-4">
						           <select name="arm_code" id="arm_code" class="form-control-sm form-control">	 
									        <option value="ALL">-- All ARMS --</option>
									      
			           						<c:forEach var="item" items="${ml_6}">
			           							<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
			           						</c:forEach>                  								
								   </select>
						       </div>
				          </div>
				        
		                  <div class="col-md-12 row form-group" style="margin-top: -10px;">       
					          <div class="col-md-2" style="text-align: left;"> 
					               <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Command</label>
					          </div>
					          <div class="col-md-4">
									<select name="command_code" id="command_code" class="form-control-sm form-control">	
									        <option value="ALL">-- All Command --</option>
									      
									        <c:set var="data" value="${ml_1[0]}"/>
	    								    <c:set var="datap" value="${fn:split(data,',')}"/>
	    								    
	    								    <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
	    								       <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
	    								       <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
	    								    </c:forEach>					
								    </select>
					          </div>       
								     
					          <div class="col-md-2" style="text-align: left;"> 
					              <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Corps</label>
					          </div>
					          <div class="col-md-4">
								   <select name="corps_code" id="corps_code" class="form-control-sm form-control">
								            <option value="ALL">-- All Corps --</option>
								           
								            <c:set var="data" value="${ml_3[0]}"/>
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
						            <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Div</label>
						        </div>
						        <div class="col-md-4">
								   <select name="div_code" id="div_code" class="form-control-sm form-control">
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
					               <label for="text-input" class=" form-control-label" style="margin-left: 13px;">Bde</label>
					           </div>
					           <div class="col-md-4">
								   <select name="bde_code" id="bde_code" class="form-control-sm form-control">	
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
				       
				          <div class="col-md-12 row form-group" style="margin-top: -10px;">
		            	     <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
		             		 </div>
		             		 <div class="col-md-2">
		             			  <input type="text" id="sus_no1" name="sus_no1" maxlength="8" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
		               		 </div>
		             		 								
		               		 <div class="col-md-2" style="text-align: right;">
		                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
		               		 </div>
		               		 <div class="col-md-6">
		               			  <input type="text" id="unit_name1" name ="unit_name1" maxlength="100" class="form-control-sm form-control" placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
							 </div>	   
	  			         </div>
	             </div>
	             
	             <div class="card-footer" align="center" style="margin-top: -10px;">
                       <input type="button" class="btn btn-primary btn-sm" value="Get Hirarchy" onclick="GetHir();" >
			           <input type="button" class="btn btn-primary btn-sm" value="Get Hirarchy Box" onclick="GetHirBox();" >
			           <input type="button" class="btn btn-primary btn-sm" value="Observation" onclick="Obser();" >
			           <input type="button" class="btn btn-primary btn-sm" value="Barrel Wise" onclick="Barr();" >
	             </div>
	             
	             <div class="card-footer" align="center">
	                   <input type="button" class="btn btn-primary btn-sm" value="CMD Wise Holding" onclick="CmdAih();" >
	                   <input type="button" class="btn btn-primary btn-sm" value="CMD Type Wise Holding" onclick="CmdType();" >
			           <input type="button" class="btn btn-primary btn-sm" value="Unit Status" onclick="Ustatus();" >
			           <input type="button" class="btn btn-primary btn-sm" value="Wastage Report" onclick="Wast();" > 
	             </div>
		   </div>
      </div>
  
  
  <div class="card" id="unit_hid2" style="display: none;background: transparent;">
    <div class="card-body card-block">	
			<div id="" class="nrTableMainDiv">
						
						<input type="hidden" id="selectedid" name="selectedid">
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						
						<table class="nrTableMain" width="100%">
			    			<tr style="width:100%;">
			    				<td class="nrTableMain2Search" colspan='2'> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;" autocomplete="off">
							    </td>
					
					<tr id="stmenu" cellspacing="5px">
						<td colspan='2'></td>
						
						
					<tr width="100%" ><td colspan='2' style="text-align:right">
			              			<table class="nrTableDataHead" style="width:calc(100% - 19px);">
			                			<thead>
			                  		<tr style="font-size: 12px;text-align:center;">
			                  			<th class="nrBox" width="10%">Fmn HQ</th>
			                  			<th class="nrBox" width="10%">Unit Name</th>
										<th class="nrBox" width="12%">PRF Group</th>
										<th class="nrBox" width="20%">Item Name</th>
										<th class="nrBox" width="7%">Census No</th>
										<th class="nrBox" width="12%">Nomenclature</th>
										<th class="nrBox" width="9%">Type Holding</th>
										<th class="nrBox" width="5%">UE</th>
										<th class="nrBox" width="5%">UH</th>
										<th class="nrBox" width="5%">Defi</th>
										<th class="nrBox" width="5%">%</th>
									</tr>
								</thead>
							</table>
							
							<div class="nrTableDataDiv">
							    <div class="nrWatermarkBase" style="z-index: -1">  
									 <p></p>
								</div>
								<table class="nrTableData" border="1">
									<thead>
										<tr class="listheading nr-medium nr-blue box" style="font-size: 12px">
											<th class="nrBox" width="10%">Fmn HQ</th>
				                  			<th class="nrBox" width="10%">Unit Name</th>
											<th class="nrBox" width="12%">PRF Group</th>
											<th class="nrBox" width="20%">Item Name</th>
											<th class="nrBox" width="7%">Census No</th>
											<th class="nrBox" width="12%">Nomenclature</th>
											<th class="nrBox" width="9%">Type Holding</th>
											<th class="nrBox" width="5%">UE</th>
											<th class="nrBox" width="5%">UH</th>
											<th class="nrBox" width="5%">Defi</th>
											<th class="nrBox" width="5%">%</th>
										</tr>
									</thead>
									<tbody id="nrTable">
									    <c:if test="${m_1[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='13' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_1[0][0] != 'NIL'}"> 
										    <c:forEach var="item" items="${m_1}" varStatus="num">
										        <tr style="font-size: 12px" id="NRRDO" name="NRRDO">
										            <td>${item[11]}</td>
										            <td>${item[9]}</td>
										            <td>${item[0]}</td>
										            <td>${item[1]}</td>
											        <td style="text-align: center;">${item[2]}</td>
											        <td>${item[3]}</td>
											        <td>${item[4]}</td>
											        <td style="text-align: center;">${item[6]}</td>
											        <td style="text-align: center;">${item[7]}</td>
											                
											        <c:if test="${item[8] == 'NIL'}">
											            <td style="color:red;text-align: center;">0</td>
											        </c:if>
											        
											        <c:if test="${item[8] != 'NIL'}">
											            <td style="color:red;text-align: center;">${item[8]}</td>
											        </c:if>
											    
											        <td>${item[12]}</td>
											        <% ntotln++; %>
										        </tr>
										    </c:forEach>
										</c:if>
									
									</tbody>
								</table>
							</div>
				</table>
			</div>
		</div>
	</div>
	
	
</div>	
		
</form:form>

<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<c:url value="AdhocReport" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1" name="m4_unit1" modelAttribute="m4_c_code">
      <input type="hidden" name="m4_c_code" id="m4_c_code"/>
	  <input type="hidden" name="m4_q_code" id="m4_q_code"/>
	  <input type="hidden" name="m4_d_code" id="m4_d_code"/>
	  <input type="hidden" name="m4_b_code" id="m4_b_code"/>
	  <input type="hidden" name="m4_p_code" id="m4_p_code"/>
	  <input type="hidden" name="m4_d_from" id="m4_d_from"/>
	  <input type="hidden" name="m4_d_to" id="m4_d_to"/>
	  <input type="hidden" name="m4_hldg" id="m4_hldg"/>
	  <input type="hidden" name="m4_prfs" id="m4_prfs"/>
</form:form>

<c:url value="printWpnEqptList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit1" name="p_unit1" modelAttribute="p_c_code" target="result">
	<input type="hidden" name="p_c_code" id="p_c_code"/>
	<input type="hidden" name="p_q_code" id="p_q_code"/>
	<input type="hidden" name="p_d_code" id="p_d_code"/>
	<input type="hidden" name="p_b_code" id="p_b_code"/>
	<input type="hidden" name="p_p_code" id="p_p_code"/>
	<input type="hidden" name="p_hldg" id="p_hldg"/>
	<input type="hidden" name="p_d_from" id="p_d_from"/>
	<input type="hidden" name="p_d_to" id="p_d_to"/>
</form:form> 


<c:url value="AdhocList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit3" name="p_unit3" modelAttribute="p_c_code1" target="result">
	<input type="hidden" name="p_c_code1" id="p_c_code1"/>
	<input type="hidden" name="p_q_code1" id="p_q_code1"/>
	<input type="hidden" name="p_d_code1" id="p_d_code1"/>
	<input type="hidden" name="p_b_code1" id="p_b_code1"/>
	<input type="hidden" name="p_p_code1" id="p_p_code1"/>
	<input type="hidden" name="p_hldg1" id="p_hldg1"/>
	<input type="hidden" name="p_sus12" id="p_sus12"/>
	<input type="hidden" name="p_unit12" id="p_unit12"/>
	<input type="hidden" name="p_d_from1" id="p_d_from1"/>
	<input type="hidden" name="p_d_to1" id="p_d_to1"/>
</form:form> 


<c:url value="ObsList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit4" name="p_unit4" modelAttribute="p_c_code2" target="result">
	<input type="hidden" name="p_c_code2" id="p_c_code2"/>
	<input type="hidden" name="p_q_code2" id="p_q_code2"/>
	<input type="hidden" name="p_d_code2" id="p_d_code2"/>
	<input type="hidden" name="p_b_code2" id="p_b_code2"/>
	<input type="hidden" name="p_p_code2" id="p_p_code2"/>
	<input type="hidden" name="p_hldg2" id="p_hldg2"/>
	<input type="hidden" name="p_sus14" id="p_sus14"/>
	<input type="hidden" name="p_unit14" id="p_unit14"/>
	<input type="hidden" name="p_d_from2" id="p_d_from2"/>
	<input type="hidden" name="p_d_to2" id="p_d_to2"/>
</form:form> 


<c:url value="BarrList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit5" name="p_unit5" modelAttribute="p_c_code3" target="result">
	<input type="hidden" name="p_c_code3" id="p_c_code3"/>
	<input type="hidden" name="p_q_code3" id="p_q_code3"/>
	<input type="hidden" name="p_d_code3" id="p_d_code3"/>
	<input type="hidden" name="p_b_code3" id="p_b_code3"/>
	<input type="hidden" name="p_p_code3" id="p_p_code3"/>
	<input type="hidden" name="p_hldg3" id="p_hldg3"/>
	<input type="hidden" name="p_sus15" id="p_sus15"/>
	<input type="hidden" name="p_unit15" id="p_unit15"/>
	<input type="hidden" name="p_d_from3" id="p_d_from3"/>
	<input type="hidden" name="p_d_to3" id="p_d_to3"/>
</form:form> 


<c:url value="Hirarueuh" var="printUrl" /> 
<form:form action="${printUrl}" method="post" id="p_unit6" name="p_unit6" modelAttribute="np_c_code" target="result">
	<input type="hidden" name="np_c_code" id="np_c_code"/>
	<input type="hidden" name="np_q_code" id="np_q_code"/>
	<input type="hidden" name="np_d_code" id="np_d_code"/>
	<input type="hidden" name="np_b_code" id="np_b_code"/>
	<input type="hidden" name="np_u_code" id="np_u_code"/>
	<input type="hidden" name="np_p_code" id="np_p_code"/>
	<input type="hidden" name="np_i_code" id="np_i_code"/>
	<input type="hidden" name="np_hldg" id="np_hldg"/>
	<input type="hidden" name="p_sus13" id="p_sus13"/>
	<input type="hidden" name="p_unit13" id="p_unit13"/>
	<input type="hidden" name="np_d_from" id="np_d_from"/>
	<input type="hidden" name="np_d_to" id="np_d_to"/>
	<input type="hidden" name="nrflowcontrol" id="nrflowcontrol"/>
</form:form> 



<c:url value="CmdAih" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit7" name="p_unit7" modelAttribute="np_c_code1" target="result">
	<input type="hidden" name="np_c_code1" id="np_c_code1"/>
	<input type="hidden" name="np_q_code1" id="np_q_code1"/>
	<input type="hidden" name="np_d_code1" id="np_d_code1"/>
	<input type="hidden" name="np_b_code1" id="np_b_code1"/>
	<input type="hidden" name="np_u_code1" id="np_u_code1"/>
	<input type="hidden" name="np_p_code1" id="np_p_code1"/>
	<input type="hidden" name="np_i_code1" id="np_i_code1"/>
	<input type="hidden" name="np_hldg1" id="np_hldg1"/>
	<input type="hidden" name="np_sus16" id="np_sus16"/>
	<input type="hidden" name="np_unit16" id="np_unit16"/>
	<input type="hidden" name="np_d_from1" id="np_d_from1"/>
	<input type="hidden" name="np_d_to1" id="np_d_to1"/>
	<input type="hidden" name="nrflowcontrol1" id="nrflowcontrol1"/>
</form:form>

<c:url value="CmdTypewiseHld" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit9" name="p_unit9" modelAttribute="np_c_code3" target="result">
	<input type="hidden" name="np_c_code3" id="np_c_code3"/>
	<input type="hidden" name="np_q_code3" id="np_q_code3"/>
	<input type="hidden" name="np_d_code3" id="np_d_code3"/>
	<input type="hidden" name="np_b_code3" id="np_b_code3"/>
	<input type="hidden" name="np_u_code3" id="np_u_code3"/>
	<input type="hidden" name="np_p_code3" id="np_p_code3"/>
	<input type="hidden" name="np_i_code3" id="np_i_code3"/>
	<input type="hidden" name="np_hldg3" id="np_hldg3"/>
	<input type="hidden" name="np_sus17" id="np_sus17"/>
	<input type="hidden" name="np_unit17" id="np_unit17"/>
	<input type="hidden" name="np_d_from3" id="np_d_from3"/>
	<input type="hidden" name="np_d_to3" id="np_d_to3"/>
	<input type="hidden" name="nrflowcontrol3" id="nrflowcontrol3"/>
</form:form>


 <c:url value="Ustatuslist" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="p_unit8" name="p_unit8"  target="result">
	<input type="hidden" name="np_sus18" id="np_sus18"/>
	<input type="hidden" name="np_unit18" id="np_unit18"/>
</form:form> 


 <c:url value="wastreportlist" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="p_unit10" name="p_unit10"  target="result">
	<input type="hidden" name="p_sus19" id="p_sus19"/>
	<input type="hidden" name="p_unit19" id="p_unit19"/>
</form:form> 

 <script>

$("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	
	var c_code=$("#command_code").val();
	var q_code=$("#corps_code").val();
	var d_code=$("#div_code").val();
	var b_code=$("#bde_code").val();
	
	if(c_code != "ALL" && c_code != undefined){
		paravalue=c_code.substring(0,1);
	}
	if(q_code != "ALL" && q_code != undefined){
		paravalue=q_code.substring(0,3);;
	}
	if(d_code != "ALL" && d_code != undefined){
		paravalue=d_code.substring(0,6);;
	}	
	if(b_code != "ALL" && b_code != undefined){
		paravalue=b_code;
	}	
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
});

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	
	var c_code=$("#command_code").val();
	var q_code=$("#corps_code").val();
	var d_code=$("#div_code").val();
	var b_code=$("#bde_code").val();
	
	if(c_code != "ALL" && c_code != undefined){
		paravalue=c_code.substring(0,1);
	}
	if(q_code != "ALL" && q_code != undefined){
		paravalue=q_code.substring(0,3);;
	}
	if(d_code != "ALL" && d_code != undefined){
		paravalue=d_code.substring(0,6);;
	}	
	if(b_code != "ALL" && b_code != undefined){
		paravalue=b_code;
	}	
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});
</script> 

<script>
function Wast(){
	var w=700;
	var h=600;
	var x = (screen.width) ? (screen.width-w)/2 : 0;
	var y = (screen.height) ? (screen.height-h)/2 : 0;
	popupWindow = window.open("", 'result', 'height='+h+',width='+w+',left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	$("#p_sus19").val($("#sus_no1").val());
	$("#p_unit19").val($("#unit_name1").val());
	$("#p_unit10").submit();
}

function printDiv(a){
	$().getPrintDiv(a,'WEAPON AND EQPT ADHOC QUERY');
}
function nrSetWaterMark(pline){
	getUserIP(function(ip){
		var roleid="${roleid}";
		var username="${username}";
		var userid="${userId}";
		var ab="";
		
		var today = new Date();
		var date = today.getDate()+'-'+ (today.getMonth()+1)+'-'+today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var currentDate = date+' '+time;
		
		abip="Generated by " + username +" on "+currentDate+" with IP-"+ip;
		 for (var i = 0; i <pline*1.5; i++) {
				ab=ab+abip+" ";
		 }
		$(".nrWatermarkBase p").text(ab);
	});
}
function getselected() {
	var checkedVals = $('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr("id");
    }).get();
	$("#nrSrcSel").val(checkedVals.join(","));
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
		$("#sus_no1").val('');
		$("#unit_name1").val('');
	}).fail(function(xhr, textStatus, errorThrown) {});
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
        		$("#sus_no1").val('');
        		$("#unit_name1").val('');
           
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
        		$("#sus_no1").val('');
        		$("#unit_name1").val('');
      
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
	
	
}

</script>

<script>

$(document).ready(function() {
	var y1 = '${m_1[0][0]}';
	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#unit_hid2").show();
		$("#command_code").val('${m_2}');
	    $("#corps_code").val('${m_3}');
		$("#div_code").val('${m_4}');
		$("#bde_code").val('${m_5}');
		$("#prf_code").val('${m_6}');
		$("#date_from").val('${m_7}');
		$("#date_to").val('${m_8}');
	   	$("#type_of_hldg").val('${m_9}');
	   	$("#from_prf_Search").val('${m_10}');
	   	$("#ntotln").text(<%=ntotln%>);	
	} 
	
	
	
	
	
	$('#command_code').change(function(){
		bindcoorp(this.value,"COMMAND");
		binddiv(this.value,"COMMAND");
		bindbde(this.value,"COMMAND");	
	});   
	
	$('#corps_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=this.value;
		//alert("in val : "+corcd);
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
	
	var d = new Date();
	var Fulldate = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	$("#mnth_year").val(Fulldate);

	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });
	
});

function setid(a,st){
	$("#selectedid").val(a);
	$("#statushid").val(st);
	$("#nrSrcSel").val(a);
}

function getmmsUeUhist(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	$("#m4_c_code").val($("#command_code").val());
	$("#m4_q_code").val($("#corps_code").val());
	$("#m4_d_code").val($("#div_code").val());
	$("#m4_b_code").val($("#bde_code").val());
	$("#m4_p_code").val($("#prf_code").val());
	$("#m4_d_from").val($("#date_from").val());
	$("#m4_d_to").val($("#date_to").val());
	$("#m4_hldg").val($("#type_of_hldg").val());
	$("#m4_prfs").val($("#from_prf_Search").val());
	$("#nrWaitLoader").show();
	$("#m4_unit1").submit();
	
}
	

	
function viewPrint(){
	var x = screen.width/2 - 1100 / 2;
	var y = screen.height/2 - 900 / 2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
  
    $("#p_c_code").val($("#command_code").val());
	$("#p_q_code").val($("#corps_code").val());
	$("#p_d_code").val($("#div_code").val());
	$("#p_b_code").val($("#bde_code").val());
	$("#p_p_code").val($("#prf_code").val());
	$("#p_hldg").val($("#type_of_hldg").val());
	$("#p_d_from").val("");
	$("#p_d_to").val("");
	$("#p_unit1").submit();
}

function GetHir(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
  
    $("#p_c_code1").val($("#command_code").val());
	$("#p_q_code1").val($("#corps_code").val());
	$("#p_d_code1").val($("#div_code").val());
	$("#p_b_code1").val($("#bde_code").val());
	$("#p_p_code1").val($("#prf_code").val());
	$("#p_hldg1").val($("#type_of_hldg").val());
	$("#p_sus12").val($("#sus_no1").val());
	$("#p_unit12").val($("#unit_name1").val());
	$("#p_d_from1").val("");
	$("#p_d_to1").val("");
	$("#p_unit3").submit();
}


function Obser(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
 
    $("#p_c_code2").val($("#command_code").val());
	$("#p_q_code2").val($("#corps_code").val());
	$("#p_d_code2").val($("#div_code").val());
	$("#p_b_code2").val($("#bde_code").val());
	$("#p_p_code2").val($("#prf_code").val());
	$("#p_hldg2").val($("#type_of_hldg").val());
	$("#p_sus14").val($("#sus_no1").val());
	$("#p_unit14").val($("#unit_name1").val());
	$("#p_d_from2").val("");
	$("#p_d_to2").val("");
	$("#p_unit4").submit();
}


function Barr(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 400/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
   
    $("#p_c_code3").val($("#command_code").val());
	$("#p_q_code3").val($("#corps_code").val());
	$("#p_d_code3").val($("#div_code").val());
	$("#p_b_code3").val($("#bde_code").val());
	$("#p_p_code3").val($("#prf_code").val());
	$("#p_hldg3").val($("#type_of_hldg").val());
	$("#p_sus15").val($("#sus_no1").val());
	$("#p_unit15").val($("#unit_name1").val());
	$("#p_d_from3").val("");
	$("#p_d_to3").val("");
	$("#p_unit5").submit();
}


function GetHirBox(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
   
 
	$("#np_c_code").val($("#command_code").val());
	$("#np_q_code").val($("#corps_code").val());
	$("#np_d_code").val($("#div_code").val());
	$("#np_b_code").val($("#bde_code").val());
	$("#np_u_code").val("ALL");
	$("#np_p_code").val($("#prf_code").val());
	$("#np_i_code").val("ALL");
	$("#np_hldg").val($("#type_of_hldg").val());
	$("#p_sus13").val($("#sus_no1").val());
	$("#p_unit13").val($("#unit_name1").val());
	$("#np_d_from").val("");
	$("#np_d_to").val("");
	$("#nrflowcontrol").val("LIST");
	$("#p_unit6").submit();
	
}


function CmdAih(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 400/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	$("#np_c_code1").val($("#command_code").val());
	$("#np_q_code1").val($("#corps_code").val());
	$("#np_d_code1").val($("#div_code").val());
	$("#np_b_code1").val($("#bde_code").val());
	$("#np_u_code1").val("ALL");
	$("#np_p_code1").val($("#prf_code").val());
	$("#np_i_code1").val("ALL");
	$("#np_hldg1").val($("#type_of_hldg").val());
	$("#np_sus16").val($("#sus_no1").val());
	$("#np_unit16").val($("#unit_name1").val());
	$("#np_d_from1").val("");
	$("#np_d_to1").val("");
	$("#nrflowcontrol1").val("LIST");
	$("#p_unit7").submit();
}

function CmdType(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 400/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	$("#np_c_code3").val($("#command_code").val());
	$("#np_q_code3").val($("#corps_code").val());
	$("#np_d_code3").val($("#div_code").val());
	$("#np_b_code3").val($("#bde_code").val());
	$("#np_u_code3").val("ALL");
	$("#np_p_code3").val($("#prf_code").val());
	$("#np_i_code3").val("ALL");
	$("#np_hldg3").val($("#type_of_hldg").val());
	$("#np_sus17").val($("#sus_no1").val());
	$("#np_unit17").val($("#unit_name1").val());
	$("#np_d_from3").val("");
	$("#np_d_to3").val("");
	$("#nrflowcontrol3").val("LIST");
	$("#p_unit9").submit();
}


function Ustatus(){
	var w=700;
	var h=600;
	var x = (screen.width) ? (screen.width-w)/2 : 0;
	var y = (screen.height) ? (screen.height-h)/2 : 0;
	popupWindow = window.open("", 'result', 'height='+h+',width='+w+',left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	$("#np_sus18").val($("#sus_no1").val());
	$("#np_unit18").val($("#unit_name1").val());
	$("#p_unit8").submit();
}
</script>
<script>

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}
	else{
		$.post("getMMSPRFtListBySearch?"+key+"="+value, {
                	nParaValue:nParaValue
            }, function(data) {
                 

            }).done(function(data) {
                 
            
                    if(data.length <= 0 || data == null || data == ''){
        				alert("No Search Result Found");
        	 			$("#from_prf_Search").focus();
        	 		}else{
        	 			$("#prf_code").attr('disabled',false);
        	 			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups --" + '</option>';
        	 			
        	 			var a = [];
        	 			var enc = data[data.length-1].substring(0,16);
        	 			for(var i = 0; i < data.length; i++){
        					a[i] = dec(enc,data[i]);
                        }
        	 			
        				var data=a[0].split(",");
        				var datap;
        				for ( var i = 0; i < data.length-1; i++) {
        					datap=data[i].split(":");
        					if (datap!=null) {
        						if (datap[1].length>60) {
        							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
        						} else {
        							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
        						}
        					}
        				}	
        				$("select#prf_code").html(options); 
        				
        				if(q != ""){
        					$("#prf_code").val(q);
        				}
        	 		}
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
	}
} 
</script>
	