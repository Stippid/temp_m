<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Upload XML File</title>
<!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
</head>
<style>
#xmlFileDetails{
margin-top:10px;

}
 #xmlFileDetails .card-header { 
    background-color: #a129ad;
   
 } 
  #xmlFileDetails  h5{
  color: #fff;
  }
    #xmlFileDetails  .card-color{
 background-color:#f2f2f2;
  }
 
    #xmlFileDetails   {
    margin-top:20px;
        font-weight: bold;
     
    }
    #getSearch_Letter_a
    {
    margin-top:20px;
/*     height: 200px; */
/*    overflow: auto; */
    }
      #getSearch_Letter_a
    {
    margin-top:20px;
/*     height: 200px; */
/*    overflow: auto; */
    }
    #status_col{
    margin-top:20px;
    }
    
/*          #getSearchtable { */
/*     max-height: 100px; */
/*     overflow: auto; */
/* } */

    #fileTableBody
    {
     max-height: 200px !important;
   overflow: auto;
 
    }
 .headh h5{
color:#18191a !important;
    font-weight: 700;
font-size:1.25rem;
} 
    #getSearchtable{
    width:100%;
    }
   .instruction
    {
    color:#007bff;
    }
    .footer
    {
    margin-top:10px;
    
    }
#getSearchtable thead{
position: sticky;
    top: 0;
}

.cstm-table-responsive{
	overflow: auto;
	max-height: 200px;
}

.select-position{
    position: absolute;
    top: 57px;
    z-index: 9;
    left: 32px;
    cursor: pointer;
}

</style>
<body>


	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> Upload XML File </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
	          			<div class="card-body card-block">	
	          			   
			  		              <div class="row"  align="center">
			  		              
	 <div class="col-md-12  form-group" style="margin-top: 5px;">
			  		                    <div class="col-12" style="text-align: left;">
			  		                    <div class="col-md-12 instruction">
			  		                     <h6 class=" form-control-label" style="margin-left:13px;">Instructions</h6> 
			  		                    </div>
			  		                   <div class="col-md-12 instruction">

<div class="col-md-6">
    <label class="form-control-label" >1. Check the details of the selected files by clicking on the view icon.</label> 
</div>
<div class="col-md-6">
    <label class="form-control-label" style="margin-left:13px;">2. For any updates to the uploaded files, visit <a href="xml_upload_logs"><u>XML Upload logs</u></a>.</label> 
</div>
  
</div>
<div class="col-md-12 instruction">

<div class="col-md-7">
    <label class="form-control-label" >3. After upload select the files to Approve.</label> 
</div>

  
</div>
			                  				
			                  				 
			                			</div>
			                			<div class="col-md-10">
<!-- 			                  				 <textarea id="inst_rem" name="inst_rem" class="form-control-sm form-control" placeholder="Enter Any Instructions..."></textarea> -->
										</div>
			</div>
	
			<div class="col-md-12">
				<div style="width:100%" >
<%-- <form:form action="upload_xml?${_csrf.parameterName}=${_csrf.token}" --%>
<%-- 	method="POST" class="form-horizontal" enctype="multipart/form-data" id ="file_upload_form" >  --%>
<form id="file_upload_form"  enctype="multipart/form-data" >

							<div class="col-md-2">
								<label class=" form-control-label"><strong style="color: red;"> *</strong>Select XML</label>
							</div>
							<div class="col-md-8">
								<input type="file" id="doc1" name="doc1"
									class="form-control-sm form-control" accept=".xml"   multiple="mulitple"
									
									onchange="showFileTable()"  autocomplete="off"/>
							</div>
							<div class="col-md-2">
							<label class=" form-control-label"><strong style="color: red;">(MAX 100 FILES AT ONCE)</strong> </label>
							</div>
							</form>
						</div>
		 </div>
		 
		 
		 
		 <div class="col-md-12" id="status_col" style="display:none;">
		 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="status" id="status" class="form-control-sm form-control"   >
													<option value="0">Success</option>
												    <option value="-1">Failure</option>	
<!-- 													<option value="1">Approved</option> -->
													
											</select>
										</div>
						            </div>
	              				</div>	
		 </div>
		 
		<div class="col-md-12" id="getSearch_Letter_a" style="display: none;">
		<div class="datatablediv">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				
				<div class="cstm-table-responsive">
				
				<table id="getSearchtable"
					class="table no-margin table-striped  table-hover  table-bordered  "    >
						<thead >
						<tr>
							<th style="text-align: center" id="ser_no">Ser No</th>
							<th style="text-align: center " id="file_name"> File Name</th>
							<th style="text-align: center " id="action">Action</th>
						
						</tr>
					</thead>
						<tbody id="fileTableBody" class="report_print_scroll"></tbody>
				</table>
</div>
			</div>
		</div>
	</div>
		 
		 
		 
		
		 
		 
	  <div id="xmlFileDetails"  class="col-md-12" style="display:none" >
		
	  	<div class="container"  >
	  	<div class="card">
	  	<div class="headh" align="center"><h5>XML Details </h5> </div>
	  	
	    		<div class="card-body" >
	  <h5 align="center"  class="card-header ">Unit Details</h5> 
	 <div class="col-md-12 card-color">	  

	           				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>SUS NO</label>
						                </div>
						               
						                <div class="col-md-4">
						                <label id="susno"></label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>UNIT NAME</label>
						                </div>
						                <div class="col-md-8">	
                                           <label id="unitname"></label>

										</div>
						            </div>
	              				</div>

	              			</div>
	              			  <h5 align="center" class="card-header">Part II Order Details</h5> 
	              			
	 <div class="col-md-12 card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-8">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>PRESENT PART II ORDER NO</label>
						                </div>						               
						                <div class="col-md-4">
						                <label id="Present_part"></label>					                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-8">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>PRESENT PART II ORDER DATE</label>
						                </div>
						                <div class="col-md-4">											
                                           <label id="Present_part_date"></label>					                          									 
										</div>
						            </div>
	              				</div>

	              			</div>
	  
	  	 <div class="col-md-12 card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-8">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>TOTAL CASUALTIES</label>
						                </div>
						               
						                <div class="col-md-4">
						                <label id="total_casuality"></label>
						                
						                </div>
						            </div>
	              				</div>
	              			</div>  
	    <h5 align="center"  class="card-header">Officer  Details</h5> 
	   
	 <div class="col-md-12  card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>ARMY NO</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="army_no"></label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>RANK</label>
						                </div>
						                <div class="col-md-8">
											
                                           <label class=" form-control-label" id="rank"></label>
						                          
											 
										</div>
						            </div>
	              				</div>

	              			</div>
	              			 <div class="col-md-12 card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>NAME</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="name"></label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>ARM/SERVICE</label>
						                </div>
						                <div class="col-md-8">
											
                                           <label class=" form-control-label" id="arm_service"    ></label>
						                          
											 
										</div>
						            </div>
	              				</div>

	              			</div>
	              			 <div class="col-md-12 card-color"  >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>CDA ACNO</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="cdaac_no"></label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>PAN</label>
						                </div>
						                <div class="col-md-8">
											
                                           <label class=" form-control-label" id="pan"></label>
						                          
											 
										</div>
						            </div>
	              				</div>

	              			</div>	            	              		
	              			  <h5 align="center" class="card-header">CASUALITIES</h5> 
	              				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="datatablediv">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				
				<div class="cstm-table-responsive">
				
				<table id="getSearchtable"
					class="table no-margin table-striped  table-hover  table-bordered  "    >
						<thead >
						<tr>
									<th id="sern">Ser No</th>						
									<th id="case">Casseqno </th>
									<th id="des">Description</th>
									<th id="fmdate">Fmdate</th> 									
									<th id="rmk1"> Rmk1</th>								
									<th id="remark"> Remark</th>

																	
																	
							</tr>
					</thead>
						<tbody id="fileTableBody_cas" class="report_print_scroll"></tbody>
				</table>
</div>
			</div>
		</div>
	</div>
	              			
	              			
	              			
	              			
	              			
	           		</div>
	  </div>              			
	  </div>	
	  </div>
	 <div class="col-md-12 card-footer footer"  id="footer" align="center"  >
	  <div  style="width:100%;"  >
	  
	<input type="submit" class="btn btn-success btn-sm" value="Upload"  onclick="showXmlFileDetails()" />
			<a href="Xml_reader" class="btn btn-danger btn-sm" >Clear	</a>  
			
			</div>
	</div>
	
	
	
	</div>
	
	</div>
	</div>
	</div>
	</div>
<!-- --------------------------------------------------------------------------------------------------------------------------- -->


<div class="animated fadeIn"   id="search_xml"  style="display:none" >
	    <form:form action="search_xml?${_csrf.parameterName}=${_csrf.token}"
		method="POST" class="form-horizontal" enctype="multipart/form-data">
	    		<div class="card">
	    		<div class="card-header" align="center"><h5> XML Upload Logs </h5> </div>
	          			<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Personal No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="9" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="name" name="name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()">
										   
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Date
										From </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="from_date" id="from_date"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 90%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Date
										To </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="to_date" id="to_date" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 90%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">	              					
<!-- 	              				<div class="col-md-6"> -->
<!-- 	              					<div class="row form-group"> -->
<!-- 						               <div class="col-md-4"> -->
<!-- 						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label> -->
<!-- 						                </div>						                -->
<!-- 						                <div class="col-md-8">				            -->
												
<!-- 											<select name="status" id="status" class="form-control-sm form-control"   > -->
<!-- 													<option value="0">Success</option> -->
<!-- 												    <option value="-1">Failure</option>	 -->
<!-- 													<option value="1">Approved</option> -->
													
<!-- 											</select> -->
<!-- 										</div> -->
<!-- 						            </div> -->
<!-- 	              				</div>	        -->
	              				
	              				
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Authority </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<input type="text" id="auth" name="auth"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" 
										oninput="this.value = this.value.toUpperCase()">
										</div>
						            </div>
	              				</div>	
	              				      					              				
	              			</div>		
				</div>						
					
						<div class="card-footer" align="center" class="form-control">
							<a href="xml_upload_logs" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Serarch"> 

			            </div> 		
	        	</div>
			</form:form>
	</div>
	
	<div class="datatablediv" id ="datatablediv"  style="display:none;">
		<div class="col-md-12" id="getSearch_Letter_b" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_uploadxmltbl"
					class="table no-margin table-striped  table-hover  table-bordered ">
<!-- 				<div class="select-position"><input type="checkbox" onclick="setAllChecked()"  id="checked_box_adfcvgbh"></div> -->
					
					<thead>
						<tr>
<!-- 							<th style="text-align: center;" id="select_all">  Select All </th> -->
							<th style="text-align: center;" id="cadet_no">Ser No</th>
							<th style="text-align: center;" id="file_name">File Name</th>
							<th style="text-align: center;" id="part_2no">Authority</th>
							<th style="text-align: center;" id="personnel_no">Army No </th>
							<th style="text-align: center;" id="name">Name</th>
							<th style="text-align: center;" id="uploaded_on">Uploaded On</th>
							<th style="text-align: center;" id="upload_status">Upload Status</th>
							<th style="text-align: center;" id="error_msg">Error Message</th>
							<th style="text-align: center;" id="action"> Action</th>
						</tr>
					</thead>
				</table>

			</div>
			<div class="card-footer" align="center" class="form-control">

<!-- 		              		<input type="button" class="btn btn-success btn-sm"  id="approve" onclick="Approve();" id ="submit_app" value=" Approve">  -->
<a href="Xml_reader" class="btn btn-danger btn-sm" >Clear</a>  
			            </div> 		
		</div>

	</div>
	
<input type="hidden" name="selected_comm_id" id="selected_comm_id" value="0">
	
	<c:url value="Approve_UpadteComm_DataUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm1" name="ViewForm1" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="personnel_no2" id="personnel_no2" value="0">
</form:form>
	<c:url value="Approve_UpadteOfficer_xml_popup" var="ViewUrl"/>
		<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id3">
	  <input type="hidden" name="personnel_no1" id="personnel_no1" value="0">
	   <input type="hidden" name="comm_id1" id="comm_id1" value="0">
	   <input type="hidden" name="event1" id="event1" value="0"/>
</form:form>
<c:url value="Edit_UpadteComm_DataUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="eid2">
  	<input type="hidden" name="eid2" id="eid2" value="0">
 	<input type="hidden" name="epersonnel_no2" id="epersonnel_no2" value="0">
</form:form><input type="hidden" name="selected_comm_id" id="selected_comm_id" value="0">
	
	<c:url value="Approve_UpadteComm_DataUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm1" name="ViewForm1" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="personnel_no2" id="personnel_no2" value="0">
</form:form>
	<c:url value="Approve_UpadteOfficerDataUrl_xml" var="ViewUrl"/>
		<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id3">
	  <input type="hidden" name="personnel_no1" id="personnel_no1" value="0">
	   <input type="hidden" name="comm_id1" id="comm_id1" value="0">
	   <input type="hidden" name="event1" id="event1" value="0"/>
</form:form>
<c:url value="Edit_UpadteComm_DataUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="eid2">
  	<input type="hidden" name="eid2" id="eid2" value="0">
 	<input type="hidden" name="epersonnel_no2" id="epersonnel_no2" value="0">
</form:form>
	<c:url value="XML_Update_Data" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateFormXML" name="updateFormXML" modelAttribute="id2">
	  	<input type="hidden" name="personnel_no_edit" id="personnel_no_edit" value="0">
	  	 	<input type="hidden" name="census_id_edit" id="census_id_edit" value="0">
	  <input type="hidden" name="comm_id_edit" id="comm_id_edit" value="0">
	</form:form>

		<c:url value="Approved_xml_data_view_pop_up" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="viewapproedForm"
	name="viewapproedForm" modelAttribute="cont_comd4" target="result"> 
	<input type="hidden" name="comm_id_view" id="comm_id_view" />	
	<input type="hidden" name="date_modified" id="date_modified" />	
		<input type="hidden" name="personnel_no5" id="personnel_no5" />	
</form:form> 
	 	<c:url value="casuality_xml_data_view_pop_up" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="casualtyForm"
	name="casualtyForm" modelAttribute="cont_comd4" target="result"> 
	<input type="hidden" name="comm_id_casualty" id="comm_id_casualty" />	
	<input type="hidden" name="upload_id_casualty" id="upload_id_casualty" />		
</form:form> 

	  <input type="hidden" name="uploaded_id" id="uploaded_id" value="">
	
	<script>
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
	
	
		
	});

	
	function showFileTable() {
		  var fileInput = document.getElementById('doc1');
		  var fileTableBody = document.getElementById('fileTableBody');
		  fileTableBody.innerHTML = '';
		  if (fileInput.files.length > 100) {
		        alert('You can select up to 100 files only.');	
		        fileInput.value = '';
		        return false;
		    } 
		  
		  
		  else{	
		  if (fileInput.files.length > 0) {
		    for (var i = 0; i < fileInput.files.length; i++) {
		      var file = fileInput.files[i];

		      // Create table row HTML
		      var rowHtml = '<tr>';
		      rowHtml += '<td>' + (i + 1) + '</td>';
		      rowHtml += '<td>' + file.name + '</td>';
		      rowHtml += '<td> <i class="fa fa-eye" onclick=" createDeselectHandler('+ i+')" title="View Data"></i></td>';
		     
		      rowHtml += '</tr>';

		      // Append row HTML to table body
		      fileTableBody.innerHTML += rowHtml;
		    }
		    $("#getSearch_Letter_a").css("display", "block");
		    $("#xmlFileDetails").css("display", "none");
		  }
		  else{
			  $("#xmlFileDetails").css("display", "none");
			  $("#getSearch_Letter_a").css("display", "none");
				 alert("Please Select XML File To Upload");
				 return false;
			  
		  }
		  
	}
		 
		}
	
	
	
	
	
	
	
	function createDeselectHandler(i) {
	    var fileInput = document.getElementById('doc1');
	    if (!fileInput.files || fileInput.files.length == 0) {
	        $("#xmlFileDetails").css("display", "none");
	        alert("Please Select XML File To Upload");
	        return false;
	    }
	    var file = fileInput.files[i];

	    if (file) {
	        file.text().then(xmlText => {
	            var parser = new DOMParser();
	            var xmlDoc = parser.parseFromString(xmlText, "text/xml");

	            var tableBody = document.getElementById("fileTableBody_cas");
	            tableBody.innerHTML = '';
	            var k = 1;

	            xmlDoc.querySelectorAll("CasualtyDetails").forEach(function(detail) {
	                var row = tableBody.insertRow();
	                var i = 1;
	                detail.childNodes.forEach(function(node) {
	                    if (node.nodeType === Node.ELEMENT_NODE) { // Check if it's an element node
	                        var cell = row.insertCell();
	                        cell.textContent = node.textContent; // Set tag value as cell content
	                        i++;
	                    }
	                });
	                row.innerHTML =
	                    "<td>" + k++ + "</td>" +
	                    "<td>" + detail.querySelector("CasSeqNo").textContent + "</td>" +
	                    "<td>" + detail.querySelector("Description").textContent + "</td>" +
	                    "<td>" + detail.querySelector("FmDate").textContent + "</td>" +
	                    "<td>" + detail.querySelector("Rmk1").textContent + "</td>" +
	                    "<td>" + detail.querySelector("Remark").textContent + "</td>";
	            });

	            // Now you can use the extracted values as needed
	            $("#susno").text(xmlDoc.querySelector("SUSNo").textContent);
	            $("#unitname").text(xmlDoc.querySelector("UnitName").textContent);
	            $("#Present_part").text(xmlDoc.querySelector("PresentP2No").textContent);
	            $("#Present_part_date").text(xmlDoc.querySelector("PresentP2Date").textContent);
	            $("#total_casuality").text(xmlDoc.querySelector("TotalCasualties").textContent);
	            $("#army_no").text(xmlDoc.querySelector("ArmyNo").textContent);
	            $("#rank").text(xmlDoc.querySelector("Rank").textContent);
	            $("#name").text(xmlDoc.querySelector("Name").textContent);
	            $("#cdaac_no").text(xmlDoc.querySelector("CDAACNo").textContent);
	            $("#arm_service").text(xmlDoc.querySelector("ArmService").textContent);
	            $("#pan").text(xmlDoc.querySelector("PAN").textContent);

	            $("#xmlFileDetails").css("display", "block");
	        });
	    }
	}

	
	
	/* 
	function createDeselectHandler(i) {
		
	    var fileInput = document.getElementById('doc1');
		 if (!fileInput.files || fileInput.files.length == 0) 
		 {
		  $("#xmlFileDetails").css("display", "none");
		 alert("Please Select XML File To Upload");
		 return false;
		 }
	    var file = fileInput.files[i];

	    if (file) {
	        file.text().then(xmlText => {
	            var parser = new DOMParser();
	            var xmlDoc = parser.parseFromString(xmlText, "text/xml");

	            var susno = xmlDoc.querySelector("SUSNo").textContent;
	            var unitname = xmlDoc.querySelector("UnitName").textContent;
	            var Present_part = xmlDoc.querySelector("PresentP2No").textContent;
	            var Present_part_date = xmlDoc.querySelector("PresentP2Date").textContent;
	            var total_casuality = xmlDoc.querySelector("TotalCasualties").textContent;
	            var army_no = xmlDoc.querySelector("ArmyNo").textContent;
	            var rank = xmlDoc.querySelector("Rank").textContent;
	            var name = xmlDoc.querySelector("Name").textContent;
	            var cdaac_no = xmlDoc.querySelector("CDAACNo").textContent;
	            var arm_service = xmlDoc.querySelector("ArmService").textContent;
	            var pan = xmlDoc.querySelector("PAN").textContent;

			var tableBody = document.getElementById("fileTableBody_cas");
			tableBody.innerHTML = '';
			var i=1;
			  var k=1;
// 			xmlDoc.querySelectorAll("CasualtyDetails").forEach(function(detail) {
			 
// 			    // Loop through each child node of the current <CasualtyDetails> element
// 			    detail.childNodes.forEach(function(node) {
// 			    	 var row = tableBody.insertRow();
					    
// 					    var rowHTML = '';
// 			        if (node.nodeType === Node.ELEMENT_NODE) { 
// 			        	  rowHTML += '<td>' +i + '</td>'; 
// 			            rowHTML += '<td>' + node.tagName + '</td>'; 
// 			            rowHTML += '<td>' + node.textContent + '</td>'; 
// 			            rowHTML += '<td>' +k + '</td>'; 
// 			            i++;
// 			        }
// 			        row.innerHTML = rowHTML;
// 			    });
// 			   k++;
// 			});
		
			
 	         xmlDoc.querySelectorAll("CasualtyDetails").forEach(function(detail) {
 	            	debugger;
	              var row = tableBody.insertRow();
	              detail.childNodes.forEach(function(node) {
	                  if (node.nodeType === Node.ELEMENT_NODE) { // Check if it's an element node
	                      var cell = row.insertCell();
	                      cell.textContent = node.tagName; // Set tag name as cell content

	                      var valueCell = row.insertCell();
	                      valueCell.textContent = node.textContent; // Set tag value as cell content
	                  }
	              });
	              row.innerHTML = 
	            	     "<td>" + k+ "</td>" +
	                  "<td>" + detail.querySelector("CasSeqNo").textContent + "</td>" +
	                  "<td>" + detail.querySelector("Description").textContent + "</td>" +
	                  "<td>" + detail.querySelector("FmDate").textContent + "</td>" +
	                  "<td>" + detail.querySelector("Rmk1").textContent + "</td>" +
	                  "<td>" + detail.querySelector("Remark").textContent + "</td>";
 	            }); 
 	            
 	         
 	        k++;
			});
 	            
 	            
	            // Now you can use the extracted values as needed
	            $("#susno").text(susno);
	            $("#unitname").text(unitname);
	            $("#Present_part").text(Present_part);
	            $("#Present_part_date").text(Present_part_date);
	            $("#total_casuality").text(total_casuality);
	            $("#army_no").text(army_no);
	            $("#rank").text(rank);
	            $("#name").text(name);
	            $("#cdaac_no").text(cdaac_no);
	            $("#arm_service").text(arm_service);
	            $("#pan").text(pan);

	            $("#xmlFileDetails").css("display", "block");
	        });
	    }
	}
	 */
	
	
		 function showXmlFileDetails() {
			 var fileInput = document.getElementById("doc1");

			 if (!fileInput.files || fileInput.files.length == 0) 
				 {
				  $("#xmlFileDetails").css("display", "none");
				 alert("Please Select XML File To Upload");
				 return false;
				 }
			 else{
					$("#WaitLoader").show();
					 $.ajaxSetup({
					        async : false
						});
					var formData = new FormData($("#file_upload_form")[0]);
					$.ajax({
					    type: "POST",
					    url: 'upload_xml?' + key + "=" + value,
					    data: formData,
					    processData: false,
					    contentType: false,
					    success: function(data) {
					    	$("#WaitLoader").show();
					    	$("#getSearch_Letter_a").hide();
					    	$("#xmlFileDetails").hide();
					    	$("#footer").hide();
					    	
					    	$("#doc1").prop("disabled", true);
					    	
					        alert(data[1]);
					     
				if(data[0]!="")
					{
					$("#WaitLoader").show();
					 $("#uploaded_id").val(data[0]);
					 
				        $("#status_col").show();;
				        $("#datatablediv").show();
				        load_data();
					}else{
					
						window.location.reload();

					}
					       
					      
					    },
					    error: function(xhr, textStatus, errorThrown) {
					        alert("Failed To Upload ");
					    }
					});

					
					
					
					
					
					
					  $("#WaitLoader").hide();
// 					var formvalues=$("#file_upload_form").serialize();				
// 					 $.post('upload_xml?' + key + "=" + value,formvalues, function(data){
					        
// 					        	  alert(data);
// 					 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("Failed To Upload ")
// 					  		});
				// $("#file_upload_form").submit();
// 					$("#WaitLoader").hide();
				 
// 				 var fileInput = event.target;
// 			        var file = fileInput.files[0];

// 			        if (file) {
			        	
// 			            var reader = new FileReader();
// 			            reader.onload = function(e) {
// 			                var xmlText = e.target.result;
// 			                var parser = new DOMParser();
// 			                var xmlDoc = parser.parseFromString(xmlText, "text/xml");
// 			                var susno = xmlDoc.querySelector("SUSNo").textContent;
// 							var unitname=xmlDoc.querySelector("UnitName").textContent;
// 							var Present_part=xmlDoc.querySelector("PresentP2No").textContent;
// 							var Present_part_date=xmlDoc.querySelector("PresentP2Date").textContent;
// 							var total_casuality=xmlDoc.querySelector("TotalCasualties").textContent;
// 							var army_no=xmlDoc.querySelector("ArmyNo").textContent;
// 							var rank=xmlDoc.querySelector("Rank").textContent;
// 							var name=xmlDoc.querySelector("Name").textContent;
// 							var cdaac_no=xmlDoc.querySelector("CDAACNo").textContent;
// 							var arm_service=xmlDoc.querySelector("ArmService").textContent;
// 							var pan=xmlDoc.querySelector("PAN").textContent;
							
							
							
// 			                // Now you can use the 'susno' value as needed
// 			                 $("#susno").text(susno);
// 			                 $("#unitname").text(unitname);
// 			                 $("#Present_part").text(Present_part);
// 			                 $("#Present_part_date").text(Present_part_date);
// 			                 $("#total_casuality").text(total_casuality);
// 			                 $("#army_no").text(army_no);
// 			                 $("#rank").text(rank);
// 			                 $("#name").text(name);
// 			                 $("#cdaac_no").text(cdaac_no);
// 			                 $("#arm_service").text(arm_service);
// 			                 $("#pan").text(pan);
			                
// 			            };
// 			            reader.readAsText(file);
// 			        }
			    
// 		            $("#xmlFileDetails").css("display", "block");
			    }
			 }
				 
				 
// 				 var fileInput = document.getElementById("doc1");
// 			        var files = fileInput.files;

// 			        if (files.length > 0) {
// 			            var formData = new FormData();
			            
// 			            // Append each selected file to the FormData object
// 			            for (let i = 0; i < files.length; i++) {
// 			                formData.append("file", files[i]);
// 			            }
// 			       	 $.post("Read_Xml_file?"+key+"="+value, {formData : formData}).done(function(j) {
// 			 		alert("jhojljjkj");
							
// 						}); 
// 			            $.ajax({
// 			                url: "Read_Xml_file?"+key+"="+value, // Replace with your actual URL
// 			                method: "POST",
// 			                data: formData,
// 			                contentType: false, // To prevent jQuery from setting the Content-Type
// 			                processData: false, // To prevent jQuery from processing the data
// 			                success: function(response) {
// 			                    // Handle the server's response here
// 			                 alert(response);
// 			                },
// 			                error: function(error) {
// 			                    // Handle errors here
// 			                    console.error(error);
// 			                }
// 			            });
			      //  }
			       
			 

		 
		
		
		
// 		 function showXmlFileDetails() {
// 	            // Get the selected XML file
// 	            var fileInput = document.getElementById("doc1");
// 	            var file = fileInput.files[0];
// alert(file);
// 	            if (file) {
// 	                var xmlFileDetails = document.getElementById("xmlFileDetails");

// 	                // Create a FileReader to read the file
// 	                var reader = new FileReader();

// 	                reader.onload = function(event) {
// 	                    var xmlContent = event.target.result;

// 	                    // Display the XML content in a textarea
// 	                    xmlFileDetails.innerHTML = `
// 	                        <h2>File Details:</h2>
// 	                        <p>Name: ${file.name}</p>
// 	                        <p>Type: ${file.type}</p>
// 	                        <p>Size: ${file.size} bytes</p>
// 	                        <h2>XML Content:</h2>
// 	                        <pre>${xmlContent}</pre>
// 	                    `;
// 	                };

// 	                // Read the XML file as text
// 	                reader.readAsText(file);
// 	            }
// 	        }
		
		function load_data(){
			$("#WaitLoader").show();
			mockjax1('getSearch_uploadxmltbl');
			table = dataTable11('getSearch_uploadxmltbl');
			colAdj("getSearch_uploadxmltbl");
			table.ajax.reload();
		}
		function ViewData(comm_id,personnel_no){	

	 		$.post("getcensus_id?" + key + "=" + value, {
	 			comm_id:comm_id
			}, function(j) {
				
			 			$("#comm_id1").val(comm_id);
		 	 		$("#personnel_no1").val(personnel_no);
		 			document.getElementById('ViewForm').submit();
		 			colAdj("getSearch_uploadxmltbl");
		 	
				
			});
	 	}  
	 	
	 	function editData(comm_id,personnel_no){		
	 		$.post("getcensus_id?" + key + "=" + value, {
	 			comm_id:comm_id
			}, function(j) {
		 			$("#comm_id_edit").val(comm_id);
		 			$("#census_id_edit").val(j);
		 	 		$("#personnel_no_edit").val(personnel_no);
		 			document.getElementById('updateFormXML').submit();
		 			colAdj("getSearch_uploadxmltbl");			
			});
	
	 	}
	 	
		function data(tableName) {
			//$("#WaitLoader").hide();
			jsondata = [];

			var table = $('#' + tableName).DataTable();
			var info = table.page.info();
			var currentPage = info.page;
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).attr('id');
			var orderType = order[0][1];
			   var army=$("#personnel_no").val();
			   var name=$("#name").val();
			   var frm_dt1 =	$("#from_date").val();
				var to_dt1 =	$("#to_date").val();
				var status=$("#status").val(); 
				var present_p2_no=$("#auth").val(); 
				var selected_id=$("#uploaded_id").val(); 
				var susno="";
				var unitname="";
				
			if (tableName == "getSearch_uploadxmltbl" ) {
	
				$.post("getUploadedFiles_count?" + key + "=" + value, {
					Search : Search,
					army : army,
					name : name,
					frm_dt1:frm_dt1,
					to_dt1:to_dt1,
					status:status,
					present_p2_no:present_p2_no,
					selected_id:selected_id
					,susno:susno,unitname:unitname
					
				}, function(j) {
					FilteredRecords = j;
					
				});
				$.post("getUploadedFiles?" + key + "=" + value, {
					startPage : startPage,
					pageLength : pageLength,
					Search : Search,
					orderColunm : orderColunm,
					orderType : orderType,
					army : army,
					name : name,
					frm_dt1:frm_dt1,to_dt1:to_dt1,status:status,present_p2_no:present_p2_no,selected_id:selected_id,susno:susno,unitname:unitname
				}, function(j) {
					
					if(status==0)
					{
				
// 					table.column(0).visible(true);
						table.column(8).visible(true);
					}
					
					for (var i = 0; i < j.length; i++) {
						
						var sr = i + 1;

// 						if(status==0)
// 							{
// 							jsondata.push([ '<input type="checkbox" id ="checkbox_approve_'+i+' " value="'+j[i].comm_id+'" >'+sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
// 								 j[i].error_msg,j[i].action]);
// 	table.column(8).visible(true);
// //	 						jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
// //	 							 j[i].error_msg,j[i].action]);
// 							}
// 						else{
// 						table.column(8).visible(false);
// 							jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
// 								 j[i].error_msg,'']);
							
// 						}

	if(status==0)
						{
// 						jsondata.push([ '<input type="checkbox" class="setcheckedasas" onclick="checked_uchecked()"  id ="checkbox_approve_'+i+' " value="'+j[i].comm_id+'" >',sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
// 							 j[i].error_msg,j[i].action]);
// 						table.column(0).visible(true);
// 							table.column(8).visible(true);
							jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
								 j[i].error_msg,j[i].action]);
						
								table.column(8).visible(true);
						}
	else if(status==1)
	{
	jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
		 j[i].error_msg,j[i].action]);
		table.column(8).visible(false);

	}
					else{
					table.column(8).visible(false);
						jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
							 j[i].error_msg,'']);
						
					}
					}
					
				});
			}
		
		}
		$("#status").change(function() {
		 	
	 		var a=$("#status").val();
	 	    if (a!="0") {
	 	        $("#approve").hide();
	 	       $(".select-position").hide();
	 	       
	 	    }
	 	    else{
	 	    	  $("#approve").show();
	 	    	 $(".select-position").show();
	 	    }
	 	   table.ajax.reload();
	 	});
		
		
		function Approve()
		{
			
			if (confirm('Are You Sure You Want to Approve Officer Data?')){
				findselectedcomm_id();
				var comm_id=$("#selected_comm_id").val();
				if(comm_id=="")
					{
					alert("Please Select Data To Approve.");
					return false;
					}
				else{
					$("#WaitLoader").show();
					  $.ajaxSetup({
					        async : false
						});
					$("#submit_app").prop('disable',true);
					$.post("Approve_officer_DataAction_xml_multiple?" + key + "=" + value, {
						comm_id:comm_id
					}, function(j) {
						
						alert(j);
						if(j=="Data Approve Successfully.")
							{
							 $("#status").append('<option value="1">Approved</option>');
							 $("#status").val(1);
							  $(".select-position").hide();
							  $("#approve").hide();
							  
							}
						
					});
					
					table.ajax.reload();
					$("#submit_app").prop('disable',false);
					$("#WaitLoader").hide();
				}
				
				}else{
					return false;
					}
			
		}
		function findselectedcomm_id() {
		
		    var nrSel = $("#getSearch_uploadxmltbl tbody input:checkbox:checked").map(function() {
		        return $(this).val();
		    }).get();
		    var b = nrSel.join('|');
		    $("#selected_comm_id").val(b);
		}

		
		
		
		var popupWindow=null;
		function 	AppViewData(comm_id,date,army_no)
		{
			$("#comm_id_view").val(comm_id);
			$("#date_modified").val(date);
			$("#personnel_no5").val(army_no);
			if(popupWindow != null && !popupWindow.closed){
				popupWindow.close();
				popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");		
				window.onfocus = function () { 
				}
		 		document.getElementById('viewapproedForm').submit();
		 	
		}
			
			
				else
					{
					popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
				
					window.onfocus = function () { 
					}
					document.getElementById('viewapproedForm').submit();
					
					}
				
				}	
			
		function edit_new_Data(comm_id,upload_id)
		{

			$("#comm_id_casualty").val(comm_id);
			$("#upload_id_casualty").val(upload_id);
		
			if(popupWindow != null && !popupWindow.closed){
				popupWindow.close();
				popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");		
				window.onfocus = function () { 
				}
		 		document.getElementById('casualtyForm').submit();
		 	
		}
			
			
				else
					{
					popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
				
					window.onfocus = function () { 
					}
					document.getElementById('casualtyForm').submit();
					
					}
			
		}
		function setAllChecked() {    
    if ($("#checked_box_adfcvgbh").prop("checked")) {
		$(".setcheckedasas").prop('checked', true);
	} else {
		$(".setcheckedasas").prop('checked', false);
	}
    
    
}
		
		function checked_uchecked() {		
		    var selectAllCheckbox = document.getElementById('checked_box');
		    selectAllCheckbox.checked=false;
		}
		
		
		
	</script>
</body>
</html>
