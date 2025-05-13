<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<form>
				<div class="card-header">
					<h5>Search/Approve Posting</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by unit)</span>
					</h6>
				</div>
			<div class="card-body card-block">	   
				<div class="col-md-12">
					<div class="col-md-6" id=""  >
						<div class="row form-group">
							<div class="col-md-4">
								<strong style="color: red;">* </strong><label class=" form-control-label"> Action </label>
							</div>
							<div class="col-md-8">
								<select name="type" id="type" onchange="fnType()" class="form-control"   >
									<c:forEach var="item" items="${getPostINOutstatusList}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						
						</div>
					</div>						
				</div>
			 </div>
		</form>
			
<!-- POST OUT FORM START  -->
	<div id="div_post_out" class="tabcontent" style="display:none ;">
		<form id="post_out_form">		
				<div class="card">
					<div class="card-header">
						<h5>POST OUT</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
					</div>
				<div class="card-body card-block"><br>			 
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">
							<input type="text" id="from_sus_no_out" name="from_sus_no_out" value="${roleSusNo}"
										class="form-control autocomplete" autocomplete="off" onkeyup="search_sus1from(this,'from_unit_name_out');specialcharecter(this)" 
										maxlength="8" onkeypress="return AvoidSpace(event)" > 		 	
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="from_unit_name_out" name="from_unit_name_out" value="${unit_name}"
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit1_from(this,'from_sus_no_out');specialcharecter(this)"
										maxlength="50" >     
								</div>
							</div>
						</div>
					</div>					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Sus No </label>
								</div>
								<div class="col-md-8">
								<input type="text" id="to_sus_no" name="to_sus_no" onkeyup="search_sus(this,'to_unit_name_out');specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" onkeypress="return AvoidSpace(event)" >
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_unit_name_out" name="to_unit_name_out" value=""
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit_name(this,'to_sus_no');specialcharecter(this)"
											maxlength="50" >   
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Army No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="12" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Rank </label>
								</div>
								<div class="col-md-8">
									<select name="rank" id="rank"
										class="form-control-sm form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${getRankjcoList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">	              					
             			<div class="col-md-6">
             				<div class="row form-group">
				               <div class="col-md-4">
				                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status</label>
				                </div>						               
				                <div class="col-md-8">				           								
									<select name="statusout" id="statusout" class="form-control-sm form-control"  >
										<option value="0">Pending</option>
									    <option value="1">Approved</option>	
										<option value="3">Rejected</option>
										<option value="4">View History/Cancel</option>
									</select>
								</div>
				            </div>
             			</div>	             					              				
	              	</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created by </label>
										</div>
										<div class="col-md-8">
											<select name="cr_by_out" id="cr_by_out"
												class="form-control-sm form-control">
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getRoleNameList_dash}"
													varStatus="num">
													<option value="${item.userId}" name="${item.userName}">${item.userName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created Date </label>
										</div>
										<div class="col-md-8">
											<input type="text" name="cr_date_out" id="cr_date_out" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;">

										</div>
									</div>
								</div>
							</div>
						</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Posting_In_Out_JCOUrl" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm" onclick="getPostData();" value="Search">
				</div>
			</div>
	</form>			
</div>
<!-- --------------------INNN---------------------------- -->
<div id="div_post_in" class="tabcontent" style="display:none ;">
		<form id="post_out_in">			
				<div class="card">
						<div class="card-header">
							<h5>POST IN</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
			<div class="card-body card-block"><br>			 
				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">
								<input type="text" id="from_sus_no_in" name="from_sus_no_in" 
										class="form-control autocomplete" autocomplete="off" onkeyup="search_sus1from(this,'from_unit_name_in');specialcharecter(this)" 
											maxlength="8" onkeypress="return AvoidSpace(event)"> 		
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="from_unit_name_in" name="from_unit_name_in" 
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit1_from(this,'from_sus_no_in');specialcharecter(this)"
											maxlength="50" >    
								</div>
							</div>
						</div>
					</div>				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Sus No </label>
								</div>
								<div class="col-md-8">
								 <!-- <input type="hidden" name="comm_id" id="comm_id" value="0"/>  -->
									<input type="text" id="to_sus_no_in" name="to_sus_no_in" value="${roleSusNo}" onkeyup="search_sus(this,'to_unit_name_in');specialcharecter(this)" 
										maxlength="8" onkeypress="return AvoidSpace(event)" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_unit_name_in" name="to_unit_name_in" value="${unit_name}"
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit_name(this,'to_sus_no_in');specialcharecter(this)"
											maxlength="50" >    
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong> Army No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="inpersonnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="12" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Rank </label>
								</div>
								<div class="col-md-8">
									<select name="rank" id="inrank"
										class="form-control-sm form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${getRankjcoList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Status </label>
										</div>
										<div class="col-md-8">
											<select name="statusin" id="statusin"
												class="form-control-sm form-control">
												<option value="0">Pending</option>
												<option value="1">Approved</option>
												<option value="3">Rejected</option>
												<option value="4">View History/Cancel</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created by </label>
										</div>
										<div class="col-md-8">
											<select name="cr_by_in" id="cr_by_in"
												class="form-control-sm form-control">
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getRoleNameList_dash}"
													varStatus="num">
													<option value="${item.userId}" name="${item.userName}">${item.userName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Created Date </label>
										</div>
										<div class="col-md-8">
											<input type="text" name="cr_date_in" id="cr_date_in" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;">
										</div>
									</div>
								</div>
							</div>
						</div>
					<div class="card-footer" align="center" class="form-control">
					<a href="Search_Posting_In_Out_JCOUrl" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm" onclick="getPostData();" value="Search">
				</div>
			</div> 
	</form>			
</div>
<!-- OUT TABLE -->
		<div class="container" id="divPrint" style="display:none;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="searchPostTable"
					class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
					<thead>
						<tr>
							<th style="font-size: 15px; text-align: center">Ser No</th>
							<th style="font-size: 15px; text-align: center">Army No</th>
							<th style="font-size: 15px; text-align: center">Name</th>
							<th style="font-size: 15px; text-align: center">Rank</th>
							<th class="postoutclass" style="font-size: 15px; text-align: center">Auth No</th>
							<th class="postoutclass" style="font-size: 15px; text-align: center">Auth Date</th>
							<th style="font-size: 15px; text-align: center">Unit SUS No</th>
							<!-- <th style="font-size: 15px; text-align: center">Date of SOS</th> -->
							<th style="font-size: 15px; text-align: center; display: none;" id="th_sos">Date of SOS</th>
				            <th style="font-size: 15px; text-align: center; display: none;" id="th_tos">Date of TOS</th>
							<th class="remarks" style="font-size: 15px; text-align: center">Reject Remarks</th>
							<th class="actionclass" style="font-size: 15px; text-align: center">ACTION</th>
							
						</tr>
					</thead>
					<tbody id="searchposttbody">				
					</tbody>
				</table>
			</div>
		</div>
	 </div>		 
   </div>
</div>

<c:url value="Search_Posting_Out_JCO" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="rank1" id="rank1" />
	<input type="hidden" name="to_sus_no1" id="to_sus_no1" value="0" />
	<input type="hidden" name="from_sus_no1" id="from_sus_no1" value="0" />
	<input type="hidden" name="type1" id="type1" value="0" />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
</form:form>

<c:url value="Search_Posting_In_JCO" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm1"
	name="searchForm1" modelAttribute="personnel_no2">
	<input type="hidden" name="personnel_no2" id="personnel_no2" value="0" />
	<input type="hidden" name="rank2" id="rank2" />
	<input type="hidden" name="to_sus_no2" id="to_sus_no2" value="0" />
	<input type="hidden" name="from_sus_no2" id="from_sus_no2" value="0" />
	<input type="hidden" name="type2" id="type2" value="0" />
	<input type="hidden" name="cr_date2" id="cr_date2"  />
	<input type="hidden" name="cr_by2" id="cr_by2"  />
</form:form>

<c:url value="Approve_PostOUT_JCO" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	 <input type="hidden" name="id3" id="id3" value="0"/> 
	 <input type="hidden" name="statusapp1" id="statusapp1" value="0"/> 
	 <input type="hidden" name="jco_idapp1" id="jco_idapp1" value="0"/>  
	 <input type="hidden" name="from_sus_noapp1" id="from_sus_noapp1" value="0"/>  
	 <input type="hidden" name="unit_sus_noapp1" id="unit_sus_noapp1" value="0"/> 
	 <input type="hidden" name="personnel_noapp1" id="personnel_noapp1" value="0" />
	 <input type="hidden" name="rankapp1" id="rankapp1" />
	 <input type="hidden" name="to_sus_noapp1" id="to_sus_noapp1" value="0" />
	 <input type="hidden" name="typeappapp1" id="typeapp1" value="0" />
</form:form> 

<c:url value="Approve_PostIN_JCO" var="inapproveUrl" />
<form:form action="${inapproveUrl}" method="post" id="inapproveForm" name="inapproveForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0"/> 
	<input type="hidden" name="statusapp2" id="statusapp2" value="0"/> 
	<input type="hidden" name="jco_idapp2" id="jco_idapp2" value="0"/>  
	<input type="hidden" name="from_sus_noapp2" id="from_sus_noapp2" value="0"/> 
	<input type="hidden" name="unit_sus_noapp2" id="unit_sus_noapp2" value="0"/>  
	<input type="hidden" name="personnel_noapp2" id="personnel_noapp2" value="0" />
	<input type="hidden" name="rankapp2" id="rankapp2" />
	<input type="hidden" name="to_sus_noapp2" id="to_sus_noapp2" value="0" />
	<input type="hidden" name="typeapp2" id="typeapp2" value="0" />	
</form:form> 

<c:url value="Reject_PostOUT_JCO" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="idoutr">
	 <input type="hidden" name="idoutr" id="idoutr" value="0"/> 
	 <input type="hidden" name="statusoutr" id="statusoutr" value="0"/> 
	 <input type="hidden" name="jco_idoutr" id="jco_idoutr" value="0"/>  
	 <input type="hidden" name="from_sus_nooutr" id="from_sus_nooutr" value="0"/>  
	 <input type="hidden" name="unit_sus_nooutr" id="unit_sus_nooutr" value="0"/> 
	 <input type="hidden" name="personnel_nooutr" id="personnel_nooutr" value="0" />
	 <input type="hidden" name="rankoutr" id="rankoutr" />
	 <input type="hidden" name="to_sus_nooutr" id="to_sus_nooutr" value="0" />
	 <input type="hidden" name="typeoutr" id="typeoutr" value="0" />
	 <input type="hidden" name="rej_remarksoutjco" id="rej_remarksoutjco" value="0"/> 
</form:form> 

<c:url value="Reject_PostIN_JCO" var="inrejectUrl" />
<form:form action="${inrejectUrl}" method="post" id="inrejectForm" name="inrejectForm" modelAttribute="idinr">
	<input type="hidden" name="idinr" id="idinr" value="0"/> 
	<input type="hidden" name="statusinr" id="statusinr" value="0"/> 
	<input type="hidden" name="jco_idinr" id="jco_idinr" value="0"/>  
	<input type="hidden" name="from_sus_noinr" id="from_sus_noinr" value="0"/>  
	<input type="hidden" name="unit_sus_noinr" id="unit_sus_noinr" value="0"/> 
	<input type="hidden" name="personnel_noinr" id="personnel_noinr" value="0" />
	<input type="hidden" name="rankinr" id="rankinr" />
	<input type="hidden" name="to_sus_noinr" id="to_sus_noinr" value="0" />
	<input type="hidden" name="typeinr" id="typeinr" value="0" />	
	<input type="hidden" name="rej_remarksinjco" id="rej_remarksinjco" value="0"/> 
</form:form> 

<c:url value="Edit_Post_Out_JCOUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="personnel_no6" id="personnel_no6" value="0" />
	<input type="hidden" name="rank6" id="rank6" />
	<input type="hidden" name="to_sus_no6" id="to_sus_no6" value="0" />
	<input type="hidden" name="status6" id="status6" value="0" />
	<input type="hidden" name="from_sus_no6" id="from_sus_no6" value="0" />
	<input type="hidden" name="type6" id="type6" value="0" />
</form:form>

<c:url value="Edit_Post_In_JCOUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="inupdateForm" name="inupdateForm" modelAttribute="updateid">
	<input type="hidden" name="inupdateid" id="inupdateid" value="0" />
	<input type="hidden" name="status3" id="status3" value="0" />
	<input type="hidden" name="personnel_no3" id="personnel_no3" value="0" />
	<input type="hidden" name="rank3" id="rank3" />
	<input type="hidden" name="to_sus_no3" id="to_sus_no3" value="0" />
	<input type="hidden" name="from_sus_no3" id="from_sus_no3" value="0" />
	<input type="hidden" name="type3" id="type3" value="0" />

</form:form>
 
<!--  //////////////////////Cancel History//////////////////////////// -->

<c:url value="CancelRejectHistory_PostINOUT_JCO" var="CancelRejectUrl" />
<form:form action="${CancelRejectUrl}" method="post" id="CancelRejectForm" name="CancelRejectForm" modelAttribute="idcrh">
	 <input type="hidden" name="idcrh" id="idcrh" value="0"/> 
	 <input type="hidden" name="statuscrh" id="statuscrh" value="0"/> 
	 <input type="hidden" name="jco_idcrh" id="jco_idcrh" value="0"/>  
	 <input type="hidden" name="from_sus_nocrh" id="from_sus_nocrh" value="0"/>  
	 <input type="hidden" name="personnel_nocrh" id="personnel_nocrh" value="0" />
	 <input type="hidden" name="rankcrh" id="rankcrh" />
	 <input type="hidden" name="to_sus_nocrh" id="to_sus_nocrh" value="0" />
	 <input type="hidden" name="typecrh" id="typecrh" value="0" />
</form:form>
 
<c:url value="ApproveCancelHistory_PostINOUT_JCO" var="ApproveCancelUrl" />
<form:form action="${ApproveCancelUrl}" method="post" id="ApproveCancelForm" name="ApproveCancelForm" modelAttribute="idach">
	<input type="hidden" name="idach" id="idach" value="0"/> 
	<input type="hidden" name="statusach" id="statusach" value="0"/> 
	<input type="hidden" name="jco_idach" id="jco_idach" value="0"/>  
	<input type="hidden" name="from_sus_noach" id="from_sus_noach" value="0"/>  
	<input type="hidden" name="personnel_noach" id="personnel_noach" value="0" />
	<input type="hidden" name="rankach" id="rankach" />
	<input type="hidden" name="to_sus_noach" id="to_sus_noach" value="0" />
	<input type="hidden" name="typeach" id="typeach" value="0" />
</form:form> 

<script>

$(document).ready(function() {
	jQuery(function($){ 
   		 datepicketDate('cr_date_in');
       	 datepicketDate('cr_date_out');
        
	});
	if('${service_category4}'!= 0){
		$("#service_category").val('${service_category4}');
	}
			   
     if ('${type4}' == 'postin') {
    	   $("#inpersonnel_no").val('${personnel_no4}');
	       $("#inrank").val('${rank4}');
	       $("#to_sus_no_in").val('${to_sus_no4}');	     
	       $("#from_sus_no_in").val('${from_sus_no4}');
	       $("#statusin").val('${status4}');
	       var sus_no ='${to_sus_no4}';
		   var sus_no2='${from_sus_no4}';
		   getunit(sus_no,'to_unit_name_in');
	       getunit(sus_no2,'from_unit_name_in');
    	   $("#div_post_in").show();
   	       $("#div_post_out").hide();
   	       $("#type").val('${type4}');
     }
     if ('${type4}' == 'postout') {
    	 $("#personnel_no").val('${personnel_no4}');
	     $("#rank").val('${rank4}');	
	     $("#to_sus_no").val('${to_sus_no4}');		  
	     $("#from_sus_no_out").val('${from_sus_no4}');
	     $("#statusout").val('${status4}');     
	 	 var sus_no ='${to_sus_no4}';
	 	 var sus_no2='${from_sus_no4}';
    	 getunit(sus_no,'to_unit_name_out');
    	 getunit(sus_no2,'from_unit_name_out');
    	 $("#div_post_out").show();
    	 $("#div_post_in").hide();
    	 $("select#type").val('${type4}');
     }
	
    if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}
					
	if('${roleAccess}'=='Unit'){		
		$("#from_sus_no_out").prop('readonly',true);
		$("#from_unit_name_out").prop('readonly',true);
		$("#to_sus_no_in").prop('readonly',true);
		$("#to_unit_name_in").prop('readonly',true);
	}

	 if ( $('#type').val() == 'postout' ) {
   	  $("#div_post_out").show();
   	  $("#div_post_in").hide();
     }
    else if ( $('#type').val() == 'postin' ) {
   	  $("#div_post_in").show();
   	  $("#div_post_out").hide();
     }
	 getPostData();
	});

	function getunit(val,id) {	
        $.post("getTargetUnitNameList?"+key+"="+value, {
        	sus_no : val
    }, function(j) {
            //var json = JSON.parse(data);
            //...

    }).done(function(j) {
				   var length = j.length-1;
	                   var enc = j[length].substring(0,16); 
	                   //alert("unit---" + dec(enc,j[0]));
	                   $("#"+id).val(dec(enc,j[0])); 
    }).fail(function(xhr, textStatus, errorThrown) {
    });
} 
	
  function fnType() {
      if ( $('#type').val() == 'postout' ) {
    	  $("#div_post_out").show();
    	  $("#div_post_in").hide();

      }
      else if ( $('#type').val() == 'postin' ) {
    	  $("#div_post_in").show();
    	  $("#div_post_out").hide();

      }
      $("#personnel_no").val('');
      $("#inpersonnel_no").val('');
  	  $("#inrank").val('');
  	  $("#from_sus_no_in").val('');
  	  $("#from_unit_name_in").val('');
  	  $("#to_sus_no_out").val('');
  	  $("#to_unit_name_out").val('');
  	  $("#rank").val('');
  	  $("#to_sus_no").val(''); 	 
  	  $("#to_unit_name_out").val('');
  	  $("#divPrint").hide();	 
  }
	function Search() {
		$("#personnel_no1").val($("#personnel_no").val());
		$("#rank1").val($("#rank").val());
		$("#to_sus_no1").val($("#to_sus_no").val());
		$("#from_sus_no1").val($("#from_sus_no_out").val());
		$("#type1").val($("#type").val());
		$("#cr_by1").val($("#cr_by_out").val()) ;
		$("#cr_date1").val($("#cr_date_out").val()) ;
 		document.getElementById('searchForm').submit();
	}
	function INSearch() {		 
		$("#personnel_no2").val($("#inpersonnel_no").val());
		$("#rank2").val($("#inrank").val());
		$("#to_sus_no2").val($("#to_sus_no_in").val());
		$("#from_sus_no2").val($("#from_sus_no_in").val());
		$("#type2").val($("#type").val());
		$("#cr_by2").val($("#cr_by_in").val()) ;
		$("#cr_date2").val($("#cr_date_in").val()) ;
		document.getElementById('searchForm1').submit();
	}
	function Approved(id,jco_id,unit_sus_no){
		$("#id3").val(id);
		$("#jco_idapp1").val(jco_id);
		$("#unit_sus_noapp1").val(unit_sus_no);
		$("#typeapp1").val($("#type").val());		
		$("#personnel_noapp1").val($("#personnel_no").val());
		$("#rankappapp1").val($("#rank").val());
		$("#to_sus_noapp1").val($("#to_sus_no").val());
		$("#from_sus_noapp1").val($("#from_sus_no_out").val());
		$("#statusapp1").val($("#statusout").val());		
		document.getElementById('approveForm').submit();		
	}
	function InApproved(id,jco_id,unit_sus_no){
		$("#id4").val(id);
		$("#jco_idapp2").val(jco_id);
		$("#unit_sus_noapp2").val(unit_sus_no);
		$("#typeapp2").val($("#type").val());	
		$("#personnel_noapp2").val($("#inpersonnel_no").val());
		$("#rankapp2").val($("#inrank").val());
		$("#to_sus_noapp2").val($("#to_sus_no_in").val());
		$("#from_sus_noapp2").val($("#from_sus_no_in").val());		
		$("#statusapp2").val($("#statusin").val());		
		document.getElementById('inapproveForm').submit();				 
	}
	function Reject(id,jco_id,unit_sus_no){
		$("#idoutr").val(id);
		$("#jco_idoutr").val(jco_id);
		$("#unit_sus_nooutr").val(unit_sus_no);
		$("#typeoutr").val($("#type").val());		
		$("#personnel_nooutr").val($("#personnel_no").val());
		$("#rankoutr").val($("#rank").val());
		$("#to_sus_nooutr").val($("#to_sus_no").val());
		$("#from_sus_nooutr").val($("#from_sus_no_out").val());
		$("#statusoutr").val($("#statusout").val());	
		$("#rej_remarksoutjco").val($("#reject_remarks").val()); 	
		document.getElementById('rejectForm').submit();		
	}
	function InReject(id,jco_id,unit_sus_no){
		$("#idinr").val(id);
		$("#jco_idinr").val(jco_id);
		$("#unit_sus_noinr").val(unit_sus_no);
		$("#typeinr").val($("#type").val());	
		$("#personnel_noinr").val($("#inpersonnel_no").val());
		$("#rankinr").val($("#inrank").val());
		$("#to_sus_noinr").val($("#to_sus_no_in").val());
		$("#from_sus_noinr").val($("#from_sus_no_in").val());		
		$("#statusinr").val($("#statusin").val());
		$("#rej_remarksinjco").val($("#reject_remarks").val()); 	
		document.getElementById('inrejectForm').submit();				 
	}
	function editData(id) {		
		$("#personnel_no6").val($("#personnel_no").val());
		$("#rank6").val($("#rank").val());
		$("#to_sus_no6").val($("#to_sus_no").val());
		$("#from_sus_no6").val($("#from_sus_no_out").val());
		$("#type6").val($("#type").val());
		$("#status6").val($("#statusout").val());
		document.getElementById('updateid').value = id;
		document.getElementById('updateForm').submit();
	}
	function IneditData(id) {			
		$("#personnel_no3").val($("#inpersonnel_no").val());
		$("#rank3").val($("#inrank").val());
		$("#to_sus_no3").val($("#to_sus_no_in").val());
		$("#from_sus_no3").val($("#from_sus_no_in").val());
		$("#type3").val($("#type").val());
		$("#status3").val($("#statusin").val());		
		document.getElementById('inupdateid').value = id;
		document.getElementById('inupdateForm').submit();
	}
		
function getPostData(){	
		var type=$("#type").val();
		var service_category=$('#service_category').val();
		var personnel_no=$("#inpersonnel_no").val();
		var rank=$("#rank").val();
		var to_sus_no=$("#to_sus_no").val();
		var status=$("#statusout").val();
		var from_sus_no=$("#from_sus_no_out").val();
		var cr_by =	$("#cr_by_out").val() ;	
		var cr_date =	$("#cr_date_out").val() ;	
		var cpurl;
		if(type!='0'){
			
		if(type=='postin' ){
			cpurl='Search_Posting_In_JCO?';
			 personnel_no=$("#inpersonnel_no").val();
			 rank=$("#inrank").val();
			 from_sus_no=$("#from_sus_no_in").val();
			 to_sus_no=$("#to_sus_no_in").val();
			 status=$("#statusin").val();
			 cr_by=$("#cr_by_in").val();
			 cr_date=$("#cr_date_in").val();
			 
		}
		else if(type=='postout'){
			cpurl='Search_Posting_Out_JCO?';
			 personnel_no=$("#personnel_no").val();
			 rank=$("#rank").val();
			 from_sus_no=$("#from_sus_no_out").val();
			 to_sus_no=$("#to_sus_no").val();
			 status=$("#statusout").val();
			 cr_by=$("#cr_by_out").val();
			 cr_date=$("#cr_date_out").val();
		}
		
		if(to_sus_no!='' &&  from_sus_no!=''){
			if(to_sus_no==from_sus_no){
				alert("From Sus and To Sus Can't Be Same");
				return false;
			}
		}			
			$.post(cpurl + key + "=" + value,{ service_category:service_category,personnel_no:personnel_no,rank:rank,to_sus_no:to_sus_no,from_sus_no:from_sus_no,status:status,cr_by:cr_by,cr_date:cr_date},function(k) {
				$('#searchposttbody').empty();
	    		 if(k.length > 0)
	    		 { 
	    			 if(k[0][0] == "error")
	      			{
	      				 alert(k[0][1]);
	      				 return false;
	      			}
	      			if(k[0][0] != "error")
	  	    		{
		    			 var x=0
		    			 for(i=0;i<k.length;i++){
		    				 x=x+1;
	    				 $("table#searchPostTable").append('<tr>'
	 	 						+'<td>'+x+'</td>'
	 	 						+'<td > '+k[i][0]+' </td>'	
	 	 						+'<td > '+k[i][1]+' </td>'
	 	 						+'<td > '+k[i][2]+' </td>'
	 	 						+'<td  class="postoutclass" > '+k[i][3]+' </td>'
	 	 						+'<td  class="postoutclass" > '+k[i][4]+'</td>'
	 	 						+'<td > '+k[i][5]+' </td>'
	 	 						+'<td > '+k[i][6]+' </td>'
	 	 						+'<td  class="remarks" > '+k[i][9]+'</td>'
	 	 						+'<td class="actionclass" align="center"> '+k[i][7]+' '+ k[i][8]+' </td>'
	 	 						+'</tr>');
		    			 }
		    		 if(type=='postin' ){
		    			 
		    			 $("#th_tos").show();
							$("#th_sos").hide();
		    			 
		    			$('.postoutclass').hide();
		    			if($("#statusin").val() == 3){
	 	    				$('.remarks').show();
	 	    			}else{
	 	    				$('.remarks').hide();
	 	    			}
		    				if($("#statusin").val() == 1){
		    					$('.actionclass').hide();
		    				}else{
		    					$('.actionclass').show();
		    				}
		    			}
		    			else if(type=='postout'){
		    				
		    				$("#th_tos").hide();
							$("#th_sos").show(); 
		    				
		    			  $('.postoutclass').show();
		    			  if($("#statusin").val() == 3){
		 	    				$('.remarks').show();
		 	    			}else{
		 	    				$('.remarks').hide();
		 	    			}
		    				if($("#statusout").val() == 1){
		    					$('.actionclass').hide();
		    				}else{
		    					$('.actionclass').show();
		    				}
		    			}
		    		 
			    		 $("#divPrint").show();
			    		 }
					   }
		    	else{	    			 
	    			$("#divPrint").show();
 					$("table#searchPostTable").append('<tr><td colspan="10" align="center">Data Not Available</td>'
		 		+'</tr>');
	    		 }
	    		 colAdj("searchPostTable");
		 		 colAdj("searchposttbody");
			});						
			$("#personnel_no3").val($("#inpersonnel_no").val());
			$("#rank3").val($("#inrank").val());
			$("#to_sus_no3").val($("#to_sus_no").val());
			$("#from_sus_no3").val($("#from_sus_no_out").val());
			$("#type3").val($("#type").val());
			$("#service_category3").val($("#service_category").val());							
		}
		else{			
			 $("#divPrint").hide();
		}		
	}
 
 /////
function deletePostData(id){
	$.post( 'Delete_PostIN_JCO?'+ key + "=" + value,{id:id},function(k) {
		alert(k);
		getPostData();   		 
	});
}
	///Sus No
	function  search_sus(sus_obj,unit_id){		
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);	
	susNoAuto.autocomplete({
      source: function( request, response ) {
        $.ajax({
        type: 'POST',
        url: "getTargetSUSNoList?"+key+"="+value,
        data: {sus_no:sus_no},
          success: function( data ) {
        	  var susval = [];
               var length = data.length-1;
               var enc = data[length].substring(0,16);
                     for(var i = 0;i<data.length;i++){
                             susval.push(dec(enc,data[i]));
                     }
                     var dataCountry1 = susval.join("|");
                     var myResponse = []; 
			        var autoTextVal=susNoAuto.val();
			$.each(dataCountry1.toString().split("|"), function(i,e){
				var newE = e.substring(0, autoTextVal.length);
				if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
				  myResponse.push(e);
				}
			});      	          
			response( myResponse ); 
          }
        });
      },
	  minLength: 1,
      autoFill: true,
      change: function(event, ui) {
    	 if (ui.item) {   	        	  
        	  return true;    	            
          } else {
        	  alert("Please Enter Approved Unit SUS No.");
        	  $('#'+unit_id).val('');
        	  susNoAuto.val("");	        	  
        	  susNoAuto.focus();
        	  return false;	             
          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
       }, function(j) {
              
       }).done(function(j) {
      	 var length = j.length-1;
           var enc = j[length].substring(0,16);
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
}
	

	////Personal No

	$("input#personnel_no").keypress(function() {
		var roleSus=$("#from_sus_no_out").val();		
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		var perurl;
		/* if($('#service_category').val()=='1'){
			perurl='getpersonnel_noListApproved?';
		}
		else if($('#service_category').val()=='2'){
			perurl='getArmy_noListApproved?';
		} */
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : 'getArmy_noListApproved?' + key + "=" + value,
					data : {
						personel_no : personel_no,army_no:personel_no,roleSus:roleSus
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}

						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Army No");
					document.getElementById("personnel_no").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
	});
		
function search_unit_name(unitObj,sus_id){
	var unit_name = unitObj.value;
		 var susNoAuto=$("#"+unitObj.id);
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
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
		        	  $('#'+sus_id).val('');
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	 var unit_name = ui.item.value;
			            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#"+sus_id).val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
		      } 	     
		}); 			
}
	
////Personal No
	$("input#inpersonnel_no").keypress(function() {
		var personel_no = this.value;
		var susNoAuto = $("#inpersonnel_no");
		var roleSus=$("#from_sus_no_in").val();
		var perurl;
		/* if($('#service_category').val()=='1'){
			perurl='getpersonnel_noListApproved?';
		}
		else if($('#service_category').val()=='2'){
			perurl='getArmy_noListApproved?';
		} */
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : 'getArmy_noListApproved?' + key + "=" + value,
					data : {
						personel_no : personel_no,army_no:personel_no,roleSus:roleSus
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}

						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Army No");
					document.getElementById("inpersonnel_no").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
	});

/////////////////History cancel//////////////

function RejectCancelHisData(idcan,jco_idcan){
	
	$("#idcrh").val(idcan);
	$("#jco_idcrh").val(jco_idcan);
	$("#typecrh").val($("#type").val());
	
	if($("#type").val()=="postin"){
		$("#personnel_nocrh").val($("#inpersonnel_no").val());
		$("#rankcrh").val($("#inrank").val());
		$("#to_sus_nocrh").val($("#to_sus_no_in").val());
		$("#from_sus_nocrh").val($("#from_sus_no_in").val());
		$("#statuscrh").val($("#statusin").val());
	}
	else{
		$("#personnel_nocrh").val($("#personnel_no").val());
		$("#rankcrh").val($("#rank").val());
		$("#to_sus_nocrh").val($("#to_sus_no").val());
		$("#from_sus_nocrh").val($("#from_sus_no_out").val());
		$("#statuscrh").val($("#statusout").val());
	}

	document.getElementById('CancelRejectForm').submit();		
}


function ApprovedCancelHisData(idcan,jco_idcan){
	
	$("#idach").val(idcan);
	$("#jco_idach").val(jco_idcan);	
	$("#typeach").val($("#type").val());
	
	if($("#type").val()=="postin"){
		$("#personnel_noach").val($("#inpersonnel_no").val());
		$("#rankach").val($("#inrank").val());
		$("#to_sus_noach").val($("#to_sus_no_in").val());
		$("#from_sus_noach").val($("#from_sus_no_in").val());
		$("#statusach").val($("#statusin").val());
	}
	else{
		$("#personnel_noach").val($("#personnel_no").val());
		$("#rankach").val($("#rank").val());
		$("#to_sus_noach").val($("#to_sus_no").val());
		$("#from_sus_noach").val($("#from_sus_no_out").val());
		$("#statusach").val($("#statusout").val());
	}
	document.getElementById('ApproveCancelForm').submit();					 
}
/* ///bisag 20_03_2023 v2(change invalid status) */
function  search_sus1from(sus_obj,unit_id){
// 	removeMinDate();
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);

	
	
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getSUSNoList_Active_or_Inactive?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                var length = data.length-1;
                var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              susval.push(dec(enc,data[i]));
                      }
                      var dataCountry1 = susval.join("|");
                      var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  $('#'+unit_id).val('');
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getUnitNameList_Active_or_Inactive?"+key+"="+value, {
				 sus_no:sus_no
       }, function(j) {
              
       }).done(function(j) {
      	 var length = j.length-1;
           var enc = j[length].substring(0,16);
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}

function search_unit1_from(obj,id){
// 	removeMinDate();
			var unit_name = obj.value;
				 var susNoAuto=$("#"+obj.id);
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "UnitsNameList_active_or_inactive?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
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
				        	  $("#"+id).val('');
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
					            $.post("SUSFromUNITNAMEActive_or_InactiveList?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#"+id).val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		
}
	
</script>
