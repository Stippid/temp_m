<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>
.tab {
	
}

.tablinks {
	outline: 0 !important;
	background-color: #cccccc;
	color: black;
	font-size: 14px;
	padding: 10px 5px;
	/*     border-radius: 5px;  */
	font-family: system-ui;
	border: none;
	margin: 5px 5px;
	border-right: 2px solid black;
	border-left: 2px solid black;
	width: 164px;
	height: 67px;
}

button.tablinks.active {
	background-color: #343a40 !important;
	color: white;
	box-shadow: 5px 10px 8px #888888 !important;
	border-right: 2px solid white;
	border-bottom: 2px solid white;
}

.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 8px;
}

.card-body.card-block {
	text-align: left;
	background-color: #cccccc21;
}

.card .card-footer {
	padding: .65rem 1.25rem;
	background-color: #cccccc;
	border-top: 1px solid #c2cfd6;
	position: relative;
}
</style>

<div class="container-fluid" align="center">
	<form id="Personnel_no_form">
		<div class="animated fadeIn">
			<div class="container-fluid" align="center">
				<div class="card">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default" id="insp_div1">
							<div class="panel-heading">
								<div class="panel-heading">
									<h4 class="panel-title main_title" id="insp_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapse1in" id="insp_final"
											style="color: blue;"><b>UPDATE OFFR DATA</b></a>
									</h4>
								</div>
								<div class="col-md-12"
									style="padding-top: 20px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no"
													class="form-control autocomplete" autocomplete="off">
												<input type="hidden" id="comm_id" name="comm_id"
													class="form-control autocomplete" autocomplete="off">
												<input type="text" id="census_id" name="census_id" value="0"
													class="form-control autocomplete" autocomplete="off">


											</div>
										</div>
									</div>

									<div class="col-md-6">
										<input type="button" class="btn btn-success btn-sm" id="btn1"
											value="Search" onclick="return personal_number();">
									</div>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" id="insp_count" name="count" value='1'>
					<input type="hidden" id="c_i" name="c_i" value=''> <input
						type="hidden" id="insert_count" name="insert_count" value="0">
					<input type="hidden" id="lastcount" name="lastcount"
						value='${inspDtls.size()}'>
				</div>
			</div>
		</div>
	</form>
<form id="madical_category_form">
	<div class="card">
		<div class="card-header">
			<h5>Medical Category</h5>
			<h6 class="enter_by">
				<span style="font-size: 12px; color: red;">(To be entered by
					Unit)</span>
			</h6>
		</div>
		<div class="card-body card-block">
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Authority</label>
						</div>
						<div class="col-md-6">
<!-- 					 <input type="hidden" id="med_cat_ch_id" name="med_cat_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                      -->
						
							<input type="text" id="madical_authority"
								name="madical_authority" class="form-control autocomplete" onkeyup="return specialcharecter(this)"
								autocomplete="off">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Authority</label>
						</div>
						<div class="col-md-6">
							<input type="date" id="madical_date_of_authority"
								name="madical_date_of_authority"
								class="form-control autocomplete" autocomplete="off">

						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="mad_classification_count" name="mad_classification_count" value="NIL">
			<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-12" style="font-size: 15px; margin:10px; ">
								<input type="checkbox" id="check_1bx" name="check_1bx"
									onclick="oncheck_1bx()" value="1BX"> <label for="text-input"
									class=" form-control-label"
									style="margin-left: 10px;"> SHAPE 1BX </label>
							</div>
					</div>
				</div>
			<div class="col-md-12" id="shape1bxdiv" style="display:none;">
				
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>From Date</label>
						</div>
						<div class="col-md-8">
						<input type="date" id="1bx_from_date" name="1bx_from_date" class="form-control autocomplete" autocomplete="off">

						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>To Date</label>
						</div>
						<div class="col-md-8">
							<input type="date" id="1bx_to_date" name="1bx_to_date" class="form-control autocomplete" autocomplete="off">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Diagnosis</label>
						</div>
						<div class="col-md-8">
							<input type="text" name="1bx_diagnosis_code" id="1bx_diagnosis_code" class="form-control-sm form-control" autocomplete="off"
								placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">
						</div>
					</div>
				</div>
			</div>

			
							
			<div  id="shapediv" >				
							
			<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>S - Psychological</strong></div>
			<div>
	<table id="s_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass1">Diagnosis</th>	
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass1" >Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="s_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_sShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="s_status1" id="s_status1" 
																class="form-control-sm form-control" onchange="statusChange(1,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="sShape_value1" id="sShape_value1" 
																class="form-control-sm form-control">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
															<input type="date" id="s_from_date1" name="s_from_date1" class="form-control autocomplete" autocomplete="off">						                             
			 											 </td>
						                               
							                        <td style="">
															<input type="date" id="s_to_date1" name="s_to_date1" class="form-control autocomplete" autocomplete="off">						                             
						                               </td>
						                                <td style="display:none; "  class="diagnosisClass11" >
														 <input type="text" name="_diagnosis_code11" id="_diagnosis_code11" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="s_diagnosis_code21" id="s_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="s_diagnosis_code31" id="s_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="s_diagnosis_code41" id="s_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="s_diagnosis_code51" id="s_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="s_diagnosis_code61" id="s_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="sShape_ch_id1" name="sShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass11">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add1" onclick="sShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
				
		<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>H - Hearing</strong></div>
				<div>
	<table id="h_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass2">Diagnosis</th>		
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass2">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="h_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_hShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="h_status1" id="h_status1" 
																class="form-control-sm form-control" onchange="statusChange(2,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="hShape_value1" id="hShape_value1" 
																class="form-control-sm form-control">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
															<input type="date" id="h_from_date1" name="h_from_date1" class="form-control autocomplete" autocomplete="off">						                             
			 											 </td>
						                               
							                        <td style="">
															<input type="date" id="h_to_date1" name="h_to_date1" class="form-control autocomplete" autocomplete="off">						                             
						                               </td>
						                                <td style=" display:none;"  class="diagnosisClass21">
														 <input type="text" name="_diagnosis_code21" id="_diagnosis_code21" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="h_diagnosis_code21" id="h_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="h_diagnosis_code31" id="h_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="h_diagnosis_code41" id="h_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="h_diagnosis_code51" id="h_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="h_diagnosis_code61" id="h_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="hShape_ch_id1" name="hShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass21">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add1" onclick="hShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
				<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>A - Appendagis</strong></div>		
					<div>
	<table id="a_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass3">Diagnosis</th>	
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass3">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="a_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_aShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="a_status1" id="a_status1" 
																class="form-control-sm form-control" onchange="statusChange(3,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="aShape_value1" id="aShape_value1" 
																class="form-control-sm form-control">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
															<input type="date" id="a_from_date1" name="a_from_date1" class="form-control autocomplete" autocomplete="off">						                             
			 											 </td>
						                               
							                        <td style="">
															<input type="date" id="a_to_date1" name="a_to_date1" class="form-control autocomplete" autocomplete="off">						                             
						                               </td>
						                                <td style=" display:none; "  class="diagnosisClass31">
														 <input type="text" name="_diagnosis_code31" id="_diagnosis_code31" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="a_diagnosis_code21" id="a_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="a_diagnosis_code31" id="a_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="a_diagnosis_code41" id="a_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="a_diagnosis_code51" id="a_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="a_diagnosis_code61" id="a_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="aShape_ch_id1" name="aShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass31">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add1" onclick="aShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>P - Physical Capacity</strong></div>	
					<div>
	<table id="p_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none; " class="diagnosisClass4">Diagnosis</th>	
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass4">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="p_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="p_status1" id="p_status1" 
																class="form-control-sm form-control" onchange="statusChange(4,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="pShape_value1" id="pShape_value1" 
																class="form-control-sm form-control">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
															<input type="date" id="p_from_date1" name="p_from_date1" class="form-control autocomplete" autocomplete="off">						                             
			 											 </td>
						                               
							                        <td style="">
															<input type="date" id="p_to_date1" name="p_to_date1" class="form-control autocomplete" autocomplete="off">						                             
						                               </td>
						                                <td style="display:none; "  class="diagnosisClass41">
														 <input type="text" name="_diagnosis_code41" id="_diagnosis_code41" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="p_diagnosis_code21" id="p_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="p_diagnosis_code31" id="p_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="p_diagnosis_code41" id="p_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="p_diagnosis_code51" id="p_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="p_diagnosis_code61" id="p_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="pShape_ch_id1" name="pShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass41">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add1" onclick="pShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>E - Eye Sight</strong></div>			
					<div>
	<table id="e_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass5">Diagnosis</th>		
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass5">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="e_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="e_status1" id="e_status1" 
																class="form-control-sm form-control" onchange="statusChange(5,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="eShape_value1" id="eShape_value1" 
																class="form-control-sm form-control">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
															<input type="date" id="e_from_date1" name="e_from_date1" class="form-control autocomplete" autocomplete="off">						                             
			 											 </td>
						                               
							                        <td style="">
															<input type="date" id="e_to_date1" name="e_to_date1" class="form-control autocomplete" autocomplete="off">						                             
						                               </td>
						                                <td style="display:none;" class="diagnosisClass51">
														 <input type="text" name="_diagnosis_code51" id="_diagnosis_code51" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="e_diagnosis_code21" id="e_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="e_diagnosis_code31" id="e_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="e_diagnosis_code41" id="e_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="e_diagnosis_code51" id="e_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="e_diagnosis_code61" id="e_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="eShape_ch_id1" name="eShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass51">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add1" onclick="eShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					</div>
					
				<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>C - Climate and terrain restrictions</strong></div>			
					<div>
	<table id="c_cmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="cCopClass">Other</th>  													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass1">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_cmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_cCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_cvalue1" id="c_cvalue1" onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getcCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             <td style="display:none;" class="cCopClass1" >
														 <input type="text" name="c_cother1" id="c_cother1" class="form-control-sm form-control" autocomplete="off" >						                              
														   </td>
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_cdescription1" id="c_cdescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="cCope_ch_id1" name="cCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass11">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add1" onclick="cCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>O - Degree of medical Observation required</strong></div>			
					<div>
	<table id="c_omadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass2">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_omadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_oCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_ovalue1" id="c_ovalue1" onchange="onCopeChangebt(this,2,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getoCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_odescription1" id="c_odescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="oCope_ch_id1" name="oCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass21">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add1" onclick="oCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>P - Physical capability limitations</strong></div>			
					<div>
	<table id="c_pmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass3">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_pmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_pCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_pvalue1" id="c_pvalue1" onchange="onCopeChangebt(this,3,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getpCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_pdescription1" id="c_pdescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="pCope_ch_id1" name="pCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass31">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add1" onclick="pCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>	
					

<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>E - Exclusive limitations</strong></div>			
					<div>
	<table id="c_emadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="eCopClass">SubValue</th> 
													<th style="display:none;" class="eCop_otherClass">Other</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass4">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_emadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_evalue1" id="c_evalue1" onchange="onECopeChange(this,1); onCopeChangebt(this,4,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${geteCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                <td style="display:none;" class="eCopClass1">
													<select name="c_esubvalue1" id="c_esubvalue1" onchange="onECopeSubChange(this,1)"
																class="form-control-sm form-control" >
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getesubCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                             <td style="display:none;" class="eCop_otherClass1" >
														 <input type="text" name="c_esubvalueother1" id="c_esubvalueother1" class="form-control-sm form-control" autocomplete="off" >						                              
														   </td>
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_edescription1" id="c_edescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="eCope_ch_id1" name="eCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass41">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add1" onclick="eCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
				

		</div>
		<div class="card-footer" align="center" class="form-control"> 
		<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="return medical_cat_save();">		              
		 </div> 
	</div>
	
		<input type="hidden" id="sShape_count" name="sShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="sShapeOld_count" name="sShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="hShape_count" name="hShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="hShapeOld_count" name="hShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="aShape_count" name="aShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="aShapeOld_count" name="aShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="pShape_count" name="pShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pShapeOld_count" name="pShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="eShape_count" name="eShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eShapeOld_count" name="eShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				
				<input type="hidden" id="cCope_count" name="cCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="cCopeOld_count" name="cCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="oCope_count" name="oCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="oCopeOld_count" name="oCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="pCope_count" name="pCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pCopeOld_count" name="pCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="eCope_count" name="eCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eCopeOld_count" name="eCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
			
</form>
</div>
<script>
	//*************************************MAIN Personel Number Onchange*****************************//
	function personal_number() {

		if ($("input#personnel_no").val() == "") {
			alert("Please select Personal No");
			$("input#personnel_no").focus();
			return false;
		}

		var personnel_no = $("input#personnel_no").val();
		$.post('GetPersonnelNoData?' + key + "=" + value, {
			personnel_no : personnel_no
		}, function(j) {

			$("#comm_id").val(j[0][0]);

			var comm_id = j[0][0];

			$.post('GetCensusData?' + key + "=" + value, {
				comm_id : comm_id
			}, function(k) {

				if (k.length > 0) {

					$('#census_id').val(k[0].id);
					
					getsShapeDetails();
					gethShapeDetails();
					getaShapeDetails();
					getpShapeDetails();
					geteShapeDetails();
					
					getcCopeDetails();
// 					getoCopeDetails();
// 					getpCopeDetails();
// 					geteCopeDetails();
					

				}
			});
		});
		$("input#personnel_no").attr('readonly', true);
	}

	$("input#personnel_no").keyup(function() {

		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getpersonnel_noListApproved?" + key + "=" + value,
					data : {
						personel_no : personel_no
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
					alert("Please Enter Approved Personal No");
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

	
	
	//*************************************END Personel Number Onchange*****************************//
</script>
<script>
var classification='1';
$("#mad_classification").change(function(){
	
	classification=this.value;
	setDiagnosis();
	
});

function oncheck_1bx(){
	
	if ($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
		}
	else{
		$("#shape1bxdiv").hide();
		$("#shapediv").show();
	}
}

function onCopeChangebt(e,cope,ind){
	if(e.value == 0 || (cope!= 4 && cope!= 2&& e.value == 1)){
		$('.CopaddbtClass'+cope+ind).hide();
		$('.CopaddbtClass'+cope).hide();
		
	}
	else{
		$('.CopaddbtClass'+cope+ind).show();
		$('.CopaddbtClass'+cope).show();
		
	}
}
function onCCopeChange(e,ind){	
	if(e.value != '2 (c)'){
		$('.cCopClass'+ind).hide();
		$('.cCopClass').hide();
		
	}
	else{
		$('.cCopClass'+ind).show();
		$('.cCopClass').show();
		
	}
	var styleC = $(".cCopClass").css("display");
	for(var i = 1; i<=cCope; i++){
		var	st = $("#c_cvalue"+i).val();
		 
		 if(st == '2 (c)'){
			 $('.cCopClass').show();
				 $('#c_cother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
		 styleC = $(".cCopClass").css("display");
		if(i == cCope && styleC != "none"  ){
			 if(st == '2 (c)'){
				 $('#c_cother'+i).show();
					$('.cCopClass'+i).show();
			 }
			 else {
				 $('#c_cother'+i).hide();
					$('.cCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=cCope; i++){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
	}
	
}

function onECopeChange(e,ind){
	if(e.value == '1'){
		$('.eCopClass'+ind).show();
		$('.eCopClass').show();
	}
	else{
		$('.eCopClass'+ind).hide();
		$('.eCopClass').hide();
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
		$('#c_esubvalue'+ind).val(0);
	}
	
	var styleC = $(".eCopClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_evalue"+i).val();
		 if(st == '1'){
			 $('.eCopClass').show();
				 $('#c_esubvalue'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
		 styleC = $(".eCopClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == '1'){
				 $('#c_esubvalue'+i).show();
					$('.eCopClass'+i).show();
			 }
			 else {
				 $('#c_esubvalue'+i).hide();
					$('.eCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
	}
	
	onECopeSubChange(e,ind);
	
}

function onECopeSubChange(e,ind){
	if(e.value == 'k'){
		$('.eCop_otherClass'+ind).show();
		$('.eCop_otherClass').show();
	}
	else{
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
	}
	
	var styleC = $(".eCop_otherClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_esubvalue"+i).val();
		 
		 if(st == 'k'){
			 $('.eCop_otherClass').show();
				 $('#c_esubvalueother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
		 styleC = $(".eCop_otherClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == 'k'){
				 $('#c_esubvalueother'+i).show();
					$('.eCop_otherClass'+i).show();
			 }
			 else {
				 $('#c_esubvalueother'+i).hide();
					$('.eCop_otherClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
	}
}

function setDiagnosis(){
// 	if(classification=='1'){
// 		$('.diagnosisClass1').hide();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
		
// 	}
// 	if(classification=='2'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='3'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='4'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').show();
// 		$('.diagnosisClass4').show();
// 		$('.diagnosisClass5').show();
// 		$('.diagnosisClass6').show();
// 	}
}

function statusChange(Shape,position,Shape_status){
	
// 	if(classification=='1' && (Shape_status==2  || Shape_status==3)){
// 		 $("select#s_status"+position).val('1');
// 		 alert("Please First Change Medical Classification ");	
// 	}
// 	else{
	
	if( Shape_status==1 || Shape_status==0){
		$('.diagnosisClass'+Shape+position).hide();
		$('.diagnosisClass'+Shape).hide();
		$('.addbtClass'+Shape+position).hide();
		$('.addbtClass'+Shape).hide();
		
	}else{
		$('.diagnosisClass'+Shape+position).show();
		$('.diagnosisClass'+Shape).show();
		$('.addbtClass'+Shape+position).show();
		$('.addbtClass'+Shape).show();
	}
// 	var shapeVal = 0;
// 	var stlab;
// 	if(Shape==1){
// 		shapeVal = sShape;
// 		stlab = "s_status";
// 	 }
// 	 else  if(Shape==2){
// 		 shapeVal = hShape;
// 		 stlab = "h_status";
// 	 }
// 	else  if(Shape==3){
// 		shapeVal = aShape;
// 		stlab = "a_status";
// 	}
// 	else  if(Shape==4){
// 		shapeVal = pShape;
// 		stlab = "p_status";
// 	}
// 	else  if(Shape==5){
// 		shapeVal = eShape;
// 		stlab = "e_status";
// 	}
	
// 	for(var i = 1; i<=shapeVal; i++){
		 
// 		var	st = $("#"+stlab+i).val();
// 		 var styleC = $(".diagnosisClass"+Shape).css("display");
// 		 var styleCval = $(".diagnosisClass"+Shape+i).css("display");
		 
// 		 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape).show();
// 			 //if(styleCval == "none"){
// 				 $('#_diagnosis_code'+Shape+i).show();
// 			 //}
// 		 }
// 		 else if(i == shapeVal && styleC == "none"  ){
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 $('.diagnosisClass'+Shape+i).hide();
// 		 }
		 
// 		 if(i == shapeVal && styleC != "none"  ){
// 			 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape+i).show();
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 }
// 			 else {
// 				 $('.diagnosisClass'+Shape+i).show();
// 				 $('#_diagnosis_code'+Shape+i).hide();
// 			}
// 		 }
		 
	 
// 	}
	 $.post('getShapevalueUrl?' + key + "=" + value, {Shape_status:Shape_status }, function(j){
		 
		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
             var length = j.length;
             if(length != 0){           
		             for ( var i = 0; i < length; i++) {                  
		                             options += '<option value="' + j[i][0]+ '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		             }  
		             
		             if(Shape==1){
		            	   $("select#sShape_value"+position).html(options);
		    			 
		    		 }
		    		 else  if(Shape==2){
		    			   $("select#hShape_value"+position).html(options);
		    		 }
		    		else  if(Shape==3){
		    			   $("select#aShape_value"+position).html(options);
		    				 }
		    		else  if(Shape==4){
		    			   $("select#pShape_value"+position).html(options);
		    		}
		    		else  if(Shape==5){
		    			   $("select#eShape_value"+position).html(options);
		    		}
		             
		            
             }
		 
		 
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	 
// 	}
}

function AutoCompleteDiagnosis(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getMNHDistinctICDList?" + key + "=" + value,
				data : {
					a : code,b:"ALL"
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
				alert("Please Enter Approved Diagnosis ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}





sShape=1;
function sShape_add_fn(q){
	 if( $('#sShape_add'+q).length )        
	 {
			$("#sShape_add"+q).hide();
			 $("#sShape_remove"+q).hide();	

	 }
	 sShape=sShape+1;

	 $("input#sShape_count").val(sShape);
	 
	$("table#s_madtable").append('<tr id="tr_sShape'+sShape+'">'
	+'<td  style="width: 2%;">'+sShape+'</td>'
	+'<td>'
	+'<select name="s_status'+sShape+'" id="s_status'+sShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(1,'+sShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="sShape_value'+sShape+'" id="sShape_value'+sShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	+'	<td style="">'
	+'		<input type="date" id="s_from_date'+sShape+'" name="s_from_date'+sShape+'" class="form-control autocomplete" autocomplete="off">	'					                             
	+'		 </td>'	   
	+'<td style="">'
	+'		<input type="date" id="s_to_date'+sShape+'" name="s_to_date'+sShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
	+'   </td>'
	+'  <td style="display:none; "  class="diagnosisClass1'+sShape+'">'
	+' <input type="text" name="_diagnosis_code1'+sShape+'" id="_diagnosis_code1'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="s_diagnosis_code2'+sShape+'" id="s_diagnosis_code2'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="s_diagnosis_code3'+sShape+'" id="s_diagnosis_code3'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="s_diagnosis_code4'+sShape+'" id="s_diagnosis_code4'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="s_diagnosis_code5'+sShape+'" id="s_diagnosis_code5'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="s_diagnosis_code6'+sShape+'" id="s_diagnosis_code6'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="sShape_ch_id'+sShape+'" name="sShape_ch_id'+sShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+sShape+'" onclick="sShape_add_fn('+sShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sShape_remove'+sShape+'" onclick="sShaperemove_fn('+sShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	if(q!=0){
		var preShape=sShape-1;
			$("#sShape_value"+preShape+" > option").clone().appendTo("#sShape_value"+sShape);
			$("#s_status"+sShape).val($("#s_status"+preShape).val());
			$("#sShape_value"+sShape).val($("#sShape_value"+preShape).val());
			statusChange(1,sShape,$("#s_status"+preShape).val());
			$("#s_status"+sShape+" option[value='1']").remove();
			$("#s_status"+preShape+" option[value='1']").remove();
			$("#s_status"+preShape+" option[value='0']").remove();
		 }
	setDiagnosis();
}

function sShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_sShape"+R).remove(); 
						 R = R-1;
					 $("#sShape_add"+R).show();
					 $("#sShape_remove"+R).show();	
					 $("input#sShape_count").val(R);
					 sShape=sShape-1;
						 if(sShape == 1){
							var s_val = $("#s_status"+sShape).val();
							var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
							 var lis2 = $("#s_status"+sShape).html();
							 $('#s_status'+sShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
							 $("#s_status"+sShape).val(s_val);
							}
			
	 }

	 }
	 

hShape=1;
function hShape_add_fn(q){
	 if( $('#hShape_add'+q).length )        
	 {
			$("#hShape_add"+q).hide();
			 $("#hShape_remove"+q).hide();	

	 }
	 hShape=hShape+1;
	
	
	 $("input#hShape_count").val(hShape);
	 
	$("table#h_madtable").append('<tr id="tr_hShape'+hShape+'">'
	+'<td  style="width: 2%;">'+hShape+'</td>'
	+'<td>'
	+'<select name="h_status'+hShape+'" id="h_status'+hShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(2,'+hShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="hShape_value'+hShape+'" id="hShape_value'+hShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	+'	<td style="">'
	+'		<input type="date" id="h_from_date'+hShape+'" name="h_from_date'+hShape+'" class="form-control autocomplete" autocomplete="off">	'					                             
	+'		 </td>'	   
	+'<td style="">'
	+'		<input type="date" id="h_to_date'+hShape+'" name="h_to_date'+hShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
	+'   </td>'
	+'  <td style="display:none; "  class="diagnosisClass2'+hShape+'">'
	+' <input type="text" name="_diagnosis_code2'+hShape+'" id="_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="h_diagnosis_code2'+hShape+'" id="h_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="h_diagnosis_code3'+hShape+'" id="h_diagnosis_code3'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="h_diagnosis_code4'+hShape+'" id="h_diagnosis_code4'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="h_diagnosis_code5'+hShape+'" id="h_diagnosis_code5'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="h_diagnosis_code6'+hShape+'" id="h_diagnosis_code6'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="hShape_ch_id'+hShape+'" name="hShape_ch_id'+hShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+hShape+'" onclick="hShape_add_fn('+hShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "hShape_remove'+hShape+'" onclick="hShaperemove_fn('+hShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	if(q!=0){
		var preShape=hShape-1;
			$("#hShape_value"+preShape+" > option").clone().appendTo("#hShape_value"+hShape);
			$("#h_status"+hShape).val($("#h_status"+preShape).val());
			$("#hShape_value"+hShape).val($("#hShape_value"+preShape).val());
			statusChange(2,hShape,$("#h_status"+preShape).val());
			$("#h_status"+hShape+" option[value='1']").remove();
			$("#h_status"+preShape+" option[value='1']").remove();
			$("#h_status"+preShape+" option[value='0']").remove();
		 }
	setDiagnosis();
}

function hShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_hShape"+R).remove(); 
						 R = R-1;
					 $("#hShape_add"+R).show();
					 $("#hShape_remove"+R).show();
					 $("input#hShape_count").val(R);
					 hShape=hShape-1;
					 if(hShape == 1){
						var s_val = $("#h_status"+hShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#h_status"+hShape).html();
						 $('#h_status'+hShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#h_status"+hShape).val(s_val);
						}
	 }

	 }


aShape=1;
function aShape_add_fn(q){
	 if( $('#aShape_add'+q).length )        
	 {
			$("#aShape_add"+q).hide();
			 $("#aShape_remove"+q).hide();	

	 }
	 aShape=aShape+1;
	 $("input#aShape_count").val(aShape);
	 
	$("table#a_madtable").append('<tr id="tr_aShape'+aShape+'">'
	+'<td  style="width: 2%;">'+aShape+'</td>'
	+'<td>'
	+'<select name="a_status'+aShape+'" id="a_status'+aShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(3,'+aShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="aShape_value'+aShape+'" id="aShape_value'+aShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	+'	<td style="">'
	+'		<input type="date" id="a_from_date'+aShape+'" name="a_from_date'+aShape+'" class="form-control autocomplete" autocomplete="off">	'					                             
	+'		 </td>'	   
	+'<td style="">'
	+'		<input type="date" id="a_to_date'+aShape+'" name="a_to_date'+aShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
	+'   </td>'
	+'  <td style="display:none; "  class="diagnosisClass3'+aShape+'">'
	+' <input type="text" name="_diagnosis_code3'+aShape+'" id="_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="a_diagnosis_code2'+aShape+'" id="a_diagnosis_code2'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="a_diagnosis_code3'+aShape+'" id="a_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="a_diagnosis_code4'+aShape+'" id="a_diagnosis_code4'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="a_diagnosis_code5'+aShape+'" id="a_diagnosis_code5'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="a_diagnosis_code6'+aShape+'" id="a_diagnosis_code6'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="aShape_ch_id'+aShape+'" name="aShape_ch_id'+aShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+aShape+'" onclick="aShape_add_fn('+aShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "aShape_remove'+aShape+'" onclick="aShaperemove_fn('+aShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	if(q!=0){
		var preShape=aShape-1;
			$("#aShape_value"+preShape+" > option").clone().appendTo("#aShape_value"+aShape);
			$("#a_status"+aShape).val($("#a_status"+preShape).val());
			$("#aShape_value"+aShape).val($("#aShape_value"+preShape).val());
			statusChange(3,aShape,$("#a_status"+preShape).val());
			$("#a_status"+aShape+" option[value='1']").remove();
			$("#a_status"+preShape+" option[value='1']").remove();
			$("#a_status"+preShape+" option[value='0']").remove();
		 }
	setDiagnosis();
}


function aShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_aShape"+R).remove(); 
						 R = R-1;
					 $("#aShape_add"+R).show();
					 $("#aShape_remove"+R).show();	
					 $("input#aShape_count").val(R);
					 aShape=aShape-1;
					 if(aShape == 1){
						var s_val = $("#a_status"+aShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#a_status"+aShape).html();
						 $('#a_status'+aShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#a_status"+aShape).val(s_val);
						}
	 }

	 }
	 


pShape=1;
function pShape_add_fn(q){
	 if( $('#pShape_add'+q).length )        
	 {
			$("#pShape_add"+q).hide();
			 $("#pShape_remove"+q).hide();	

	 }
	 pShape=pShape+1;
	 $("input#pShape_count").val(pShape);
	
	 
	$("table#p_madtable").append('<tr id="tr_pShape'+pShape+'">'
	+'<td  style="width: 2%;">'+pShape+'</td>'
	+'<td>'
	+'<select name="p_status'+pShape+'" id="p_status'+pShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(4,'+pShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="pShape_value'+pShape+'" id="pShape_value'+pShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	+'	<td style="">'
	+'		<input type="date" id="p_from_date'+pShape+'" name="p_from_date'+pShape+'" class="form-control autocomplete" autocomplete="off">	'					                             
	+'		 </td>'	   
	+'<td style="">'
	+'		<input type="date" id="p_to_date'+pShape+'" name="p_to_date'+pShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
	+'   </td>'
	+'  <td style="display:none; "  class="diagnosisClass4'+pShape+'">'
	+' <input type="text" name="_diagnosis_code4'+pShape+'" id="_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="p_diagnosis_code2'+pShape+'" id="p_diagnosis_code2'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="p_diagnosis_code3'+pShape+'" id="p_diagnosis_code3'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="p_diagnosis_code4'+pShape+'" id="p_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="p_diagnosis_code5'+pShape+'" id="p_diagnosis_code5'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="p_diagnosis_code6'+pShape+'" id="p_diagnosis_code6'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="pShape_ch_id'+pShape+'" name="pShape_ch_id'+pShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+pShape+'" onclick="pShape_add_fn('+pShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pShape_remove'+pShape+'" onclick="pShaperemove_fn('+pShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	if(q!=0){
		var preShape=pShape-1;
			$("#pShape_value"+preShape+" > option").clone().appendTo("#pShape_value"+pShape);
			$("#p_status"+pShape).val($("#p_status"+preShape).val());
			$("#pShape_value"+pShape).val($("#pShape_value"+preShape).val());
			statusChange(4,pShape,$("#p_status"+preShape).val());
			$("#p_status"+pShape+" option[value='1']").remove();
			$("#p_status"+preShape+" option[value='1']").remove();
			$("#p_status"+preShape+" option[value='0']").remove();
		 }
	setDiagnosis();
}

function pShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_pShape"+R).remove(); 
						 R = R-1;
					 $("#pShape_add"+R).show();
					 $("#pShape_remove"+R).show();	
					 $("input#pShape_count").val(R);
					 pShape=pShape-1;
					 if(pShape == 1){
						var s_val = $("#p_status"+pShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#p_status"+pShape).html();
						 $('#p_status'+pShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#p_status"+pShape).val(s_val);
						}
	 }

	 }

eShape=1;
function eShape_add_fn(q){
	 if( $('#eShape_add'+q).length )        
	 {
			$("#eShape_add"+q).hide();
			 $("#eShape_remove"+q).hide();	
	 }
	 eShape=eShape+1;
	 $("input#eShape_count").val(eShape);
	 
	$("table#e_madtable").append('<tr id="tr_eShape'+eShape+'">'
	+'<td  style="width: 2%;">'+eShape+'</td>'
	+'<td>'
	+'<select name="e_status'+eShape+'" id="e_status'+eShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(5,'+eShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="eShape_value'+eShape+'" id="eShape_value'+eShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	+'	<td style="">'
	+'		<input type="date" id="e_from_date'+eShape+'" name="e_from_date'+eShape+'" class="form-control autocomplete" autocomplete="off">	'					                             
	+'		 </td>'	   
	+'<td style="">'
	+'		<input type="date" id="e_to_date'+eShape+'" name="e_to_date'+eShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
	+'   </td>'
	+'  <td style="display:none; "  class="diagnosisClass5'+eShape+'">'
	+' <input type="text" name="_diagnosis_code5'+eShape+'" id="_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="e_diagnosis_code2'+eShape+'" id="e_diagnosis_code2'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="e_diagnosis_code3'+eShape+'" id="e_diagnosis_code3'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="e_diagnosis_code4'+eShape+'" id="e_diagnosis_code4'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="e_diagnosis_code5'+eShape+'" id="e_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="e_diagnosis_code6'+eShape+'" id="e_diagnosis_code6'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="eShape_ch_id'+eShape+'" name="eShape_ch_id'+eShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+eShape+'" onclick="eShape_add_fn('+eShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eShape_remove'+eShape+'" onclick="eShaperemove_fn('+eShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	if(q!=0){
		var preShape=eShape-1;
			$("#eShape_value"+preShape+" > option").clone().appendTo("#eShape_value"+eShape);
			$("#e_status"+eShape).val($("#e_status"+preShape).val());
			$("#eShape_value"+eShape).val($("#eShape_value"+preShape).val());
			statusChange(5,eShape,$("#e_status"+preShape).val());
			$("#e_status"+eShape+" option[value='1']").remove();
			$("#e_status"+preShape+" option[value='1']").remove();
			$("#e_status"+preShape+" option[value='0']").remove();
		 }
	setDiagnosis();
}

function eShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_eShape"+R).remove(); 
						 R = R-1;
					 $("#eShape_add"+R).show();
					 $("#eShape_remove"+R).show();
					 $("input#eShape_count").val(R);
					 eShape=eShape-1;
					 if(eShape == 1){
						var s_val = $("#e_status"+eShape).val();
						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
						 var lis2 = $("#e_status"+eShape).html();
						 $('#e_status'+eShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
						 $("#e_status"+eShape).val(s_val);
						}
	 }
	 
	 
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 cCope=1;
	 function cCope_add_fn(q){
	 	 if( $('#cCope_add'+q).length )        
	 	 {
	 			$("#cCope_add"+q).hide();
	 			 $("#cCope_remove"+q).hide();	
	 	 }
	 	 cCope=cCope+1;
	 	 $("input#cCope_count").val(cCope);
	 	 
	 	$("table#c_cmadtable").append('<tr id="tr_cCope'+cCope+'">'
	 	+'<td  style="width: 2%;">'+cCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_cvalue'+cCope+'" id="c_cvalue'+cCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+cCope+')">'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="cCopClass'+cCope+'" >'
		+'<input type="text" name="c_cother'+cCope+'" id="c_cother'+cCope+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
		+' </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_cdescription'+cCope+'" id="c_cdescription'+cCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+cCope+'" name="cCope_ch_id'+cCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+cCope+'" onclick="cCope_add_fn('+cCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "cCope_remove'+cCope+'" onclick="cCoperemove_fn('+cCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	
	 	var thisT = document.getElementById('c_cvalue'+cCope)
	 	onCCopeChange(thisT,cCope);
	 	$("#c_cvalue"+cCope+" option[value='0']").remove();
	 	$("#c_cvalue"+cCope+" option[value='1']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='0']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='1']").remove();
	 	
	 }

	 function cCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_cCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#cCope_add"+R).show();
	 					 $("#cCope_remove"+R).show();
	 					 $("input#cCope_count").val(R);
	 					cCope=cCope-1;
	 					if(cCope == 1){
							var s_val = $("#c_cvalue"+cCope).val();
							var lis1 = '<option value="${getcCopeList[0][1]}" name="${getcCopeList[0][0]}">${getcCopeList[0][0]}</option>'
							var lis2 = '<option value="${getcCopeList[1][1]}" name="${getcCopeList[1][0]}">${getcCopeList[1][0]}</option>'
							var lis3 = $("#c_cvalue"+cCope).html();
							 $('#c_cvalue'+cCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_cvalue"+cCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_cvalue'+cCope)
	 				 	onCCopeChange(thisT,cCope);
	 	 }

	 }
	 
	
	 
	 oCope=1;
	 function oCope_add_fn(q){
	 	 if( $('#oCope_add'+q).length )        
	 	 {
	 			$("#oCope_add"+q).hide();
	 			 $("#oCope_remove"+q).hide();	
	 	 }
	 	 oCope=oCope+1;
	 	 $("input#oCope_count").val(oCope);
	 	 
	 	$("table#c_omadtable").append('<tr id="tr_oCope'+oCope+'">'
	 	+'<td  style="width: 2%;">'+oCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_ovalue'+oCope+'" id="c_ovalue'+oCope+'" '
	 	+'	class="form-control-sm form-control"  >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_odescription'+oCope+'" id="c_odescription'+oCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+oCope+'" name="oCope_ch_id'+oCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+oCope+'" onclick="oCope_add_fn('+oCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "oCope_remove'+oCope+'" onclick="oCoperemove_fn('+oCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	$("#c_ovalue"+oCope+" option[value='0']").remove();
	 	$("#c_ovalue"+oCope+" option[value='1']").remove();
		$("#c_ovalue"+(oCope-1)+" option[value='0']").remove();
		$("#c_ovalue"+(oCope-1)+" option[value='1']").remove();
	 	
	 }

	 function oCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_oCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#oCope_add"+R).show();
	 					 $("#oCope_remove"+R).show();
	 					 $("input#oCope_count").val(R);
	 					oCope=oCope-1;
	 					if(oCope == 1){
							var s_val = $("#c_ovalue"+oCope).val();
							var lis1 = '<option value="${getoCopeList[0][1]}" name="${getoCopeList[0][0]}">${getoCopeList[0][0]}</option>'
							var lis2 = '<option value="${getoCopeList[1][1]}" name="${getoCopeList[1][0]}">${getoCopeList[1][0]}</option>'
							var lis3 = $("#c_ovalue"+oCope).html();
							 $('#c_ovalue'+oCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_ovalue"+oCope).val(s_val);
							}
	 	 }

	 }
	 
	 pCope=1;
	 function pCope_add_fn(q){
	 	 if( $('#pCope_add'+q).length )        
	 	 {
	 			$("#pCope_add"+q).hide();
	 			 $("#pCope_remove"+q).hide();	
	 	 }
	 	 pCope=pCope+1;
	 	 $("input#pCope_count").val(pCope);
	 	 
	 	$("table#c_pmadtable").append('<tr id="tr_pCope'+pCope+'">'
	 	+'<td  style="width: 2%;">'+pCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_pvalue'+pCope+'" id="c_pvalue'+pCope+'" '
	 	+'	class="form-control-sm form-control"  >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_pdescription'+pCope+'" id="c_pdescription'+pCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+pCope+'" name="pCope_ch_id'+pCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+pCope+'" onclick="pCope_add_fn('+pCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pCope_remove'+pCope+'" onclick="pCoperemove_fn('+pCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	$("#c_pvalue"+pCope+" option[value='0']").remove();
	 	$("#c_pvalue"+pCope+" option[value='1']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='0']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='1']").remove();
	 	
	 }

	 function pCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_pCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#pCope_add"+R).show();
	 					 $("#pCope_remove"+R).show();
	 					 $("input#pCope_count").val(R);
	 					pCope=pCope-1;
	 					if(pCope == 1){
							var s_val = $("#c_pvalue"+pCope).val();
							var lis1 = '<option value="${getpCopeList[0][1]}" name="${getpCopeList[0][0]}">${getpCopeList[0][0]}</option>'
							var lis2 = '<option value="${getpCopeList[1][1]}" name="${getpCopeList[1][0]}">${getpCopeList[1][0]}</option>'
							var lis3 = $("#c_pvalue"+pCope).html();
							 $('#c_pvalue'+pCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_pvalue"+pCope).val(s_val);
							}
	 	 }

	 }
	 
	 
	 eCope=1;
	 function eCope_add_fn(q){
	 	 if( $('#eCope_add'+q).length )        
	 	 {
	 			$("#eCope_add"+q).hide();
	 			 $("#eCope_remove"+q).hide();	
	 	 }
	 	 eCope=eCope+1;
	 	 $("input#eCope_count").val(eCope);
	 	 
	 	$("table#c_emadtable").append('<tr id="tr_eCope'+eCope+'">'
	 	+'<td  style="width: 2%;">'+eCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_evalue'+eCope+'" id="c_evalue'+eCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onECopeChange(this,'+eCope+')" >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="eCopClass'+eCope+'">'
	 	+'<select name="c_esubvalue'+eCope+'" id="c_esubvalue'+eCope+'" onchange="onECopeSubChange(this,'+eCope+')"'
	 	+'			class="form-control-sm form-control" >'
	 	+'			<option value="0">--Select--</option>'																
	 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
	 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'			</c:forEach></select>   </td>'
	 	+'<td style="display:none;" class="eCop_otherClass'+eCope+'" >'
	 	+'	 <input type="text" name="c_esubvalueother'+eCope+'" id="c_esubvalueother'+eCope+'" class="form-control-sm form-control" autocomplete="off" >'						                              
	 	+'	   </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_edescription'+eCope+'" id="c_edescription'+eCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+eCope+'" name="eCope_ch_id'+eCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+eCope+'" onclick="eCope_add_fn('+eCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eCope_remove'+eCope+'" onclick="eCoperemove_fn('+eCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	
	 	$("#c_evalue"+eCope+" option[value='0']").remove();
		$("#c_evalue"+(eCope-1)+" option[value='0']").remove();
		var thisT = document.getElementById('c_evalue'+eCope)
	 	onECopeChange(thisT,eCope);
	 	
	 }

	 function eCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_eCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#eCope_add"+R).show();
	 					 $("#eCope_remove"+R).show();
	 					 $("input#eCope_count").val(R);
	 					eCope=eCope-1;
	 					if(eCope == 1){
							var s_val = $("#c_evalue"+eCope).val();
							var lis1 = '<option value="${geteCopeList[0][1]}" name="${geteCopeList[0][0]}">${geteCopeList[0][0]}</option>'
							var lis2 = $("#c_evalue"+eCope).html();
							 $('#c_evalue'+eCope).empty().append(""+lis1+lis2);
							 $("#c_evalue"+eCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_evalue'+eCope)
	 				 	onECopeChange(thisT,eCope);
	 	 }

	 }
	 
	 
	 function medical_cat_save(){
		 var count_classi = 0;
				for(var j=1; j<=sShape; j++){
					if($("#s_status"+j).val() != "0" && $("#s_status"+j).val() != "1")
						count_classi++;
				}
				for(var j=1; j<=hShape; j++){
					if($("#h_status"+j).val() != "0" && $("#h_status"+j).val() != "1")
						count_classi++;
				}
				for(var j=1; j<=aShape; j++){
					if($("#a_status"+j).val() != "0" && $("#a_status"+j).val() != "1")
						count_classi++;
				}
				for(var j=1; j<=pShape; j++){
					if($("#p_status"+j).val() != "0" && $("#p_status"+j).val() != "1")
						count_classi++;
				}
				for(var j=1; j<=eShape; j++){
					if($("#e_status"+j).val() != "0" && $("#e_status"+j).val() != "1")
						count_classi++;
				}
			if(count_classi >= 3){
				$("#mad_classification_count").val("Z");
			}
			else if(count_classi == 2){
				$("#mad_classification_count").val("Y");
			}
			else if(count_classi == 1){
				$("#mad_classification_count").val("X");
			}
			else
				$("#mad_classification_count").val("NIL");
			
		  var formvalues=$("#madical_category_form").serialize();
			var census_id=$("#census_id").val();	
			var comm_id=$('#comm_id').val();			
			formvalues+="&census_id="+census_id+"&comm_id="+comm_id;	
		   $.post('medical_categoryAction?' + key + "=" + value,formvalues, function(data){
			      
		      
		             	 
		        	 if(data=='1'){
		        		 alert('Data Save/Update Succesfully');
		        	  getsShapeDetails();
		        	  gethShapeDetails();
		        	  getaShapeDetails();
		        	  getpShapeDetails();
		        	  geteShapeDetails();
		        	  getcCopeDetails();
// 		        	  getoCopeDetails();
// 		        	  getpCopeDetails();
// 		        	  geteCopeDetails();
		        	 }
		        	 else{
		        		 alert(data);
		        	 }
		         
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
	}
	 
	 
	 
// function sShape_save_fn(ps){
// 	var Shape="S";
// 	var Shape_status=$('#s_status'+ps).val();
// 	var Shape_value=$('#sShape_value'+ps).val();
// 	var from_date=$('#s_from_date'+ps).val();
// 	var to_date=$('#s_to_date'+ps).val();
// 	var diagnosis_code1=$('#s_diagnosis_code1'+ps).val();
// 	var diagnosis_code2=$('#s_diagnosis_code2'+ps).val();
// 	var diagnosis_code3=$('#s_diagnosis_code3'+ps).val();
// 	var diagnosis_code4=$('#s_diagnosis_code4'+ps).val();
// 	var diagnosis_code5=$('#s_diagnosis_code5'+ps).val();
// 	var diagnosis_code6=$('#s_diagnosis_code6'+ps).val();
// 	var authority=$('#madical_authority').val();
// 	var date_of_authority=$('#madical_date_of_authority').val();
// 	var mad_classification=$('#mad_classification').val();
// 	var comm_id=$('#comm_id').val();	
// 	var p_id=$('#census_id').val();
// 	var sShape_ch=$('#SShape_ch_id'+ps).val();
	
	
// 	if (authority == null || authority=="") {
// 		alert("Please Enter The Authority");
// 		$("#madical_authority").focus()
// 		return false;
// 	}
// 	if (date_of_authority == null || date_of_authority=="") {
// 		alert("Please Enter  Date Of Authority");
// 		$("#madical_date_of_authority").focus()
// 		return false;
// 	}
// 	if (mad_classification == null || mad_classification=="0") {
// 		alert("Please Select The Medical Classification");
// 		$("#mad_classification").focus()
// 		return false;
// 	}
// 	if (Shape_status == null || Shape_status=="0") {
// 		alert("Please Select The Shape Status");
// 		$("#s_status"+ps).focus()
// 		return false;
// 	}
// 	if (Shape_value == null || Shape_value=="0") {
// 		alert("Please Select The Shape Value");
// 		$("#sShape_value"+ps).focus()
// 		return false;
// 	}
// 	if (from_date == null || from_date=="") {
// 		alert("Please Enter The From Date");
// 		$("#s_form_date"+ps).focus()
// 		return false;
// 	}
// 	if (to_date == null || to_date=="") {
// 		alert("Please Enter The To Date");
// 		$("#s_to_date"+ps).focus()
// 		return false;
// 	}
// 	if(mad_classification=='1'){
// 		diagnosis_code2="";
// 		diagnosis_code3="";
// 		diagnosis_code4="";
// 		diagnosis_code5="";
// 		diagnosis_code6="";
// 	}
	
// 	else if(mad_classification=='2'){
// 		if(diagnosis_code1=="" || diagnosis_code1 == null){
// 			alert("Please  Enter The Diagnosis-1");
// 			$("#s_diagnosis_code1"+ps).focus()
// 			return false;
// 		}
// 		if(diagnosis_code2=="" || diagnosis_code2 == null){
// 			alert("Please  Enter The Diagnosis-2");
// 			$("#s_diagnosis_code2"+ps).focus()
// 			return false;
// 		}
// 		diagnosis_code3="";
// 		diagnosis_code4="";
// 		diagnosis_code5="";
// 		diagnosis_code6="";
// 	}
// 	else if(mad_classification=='3'){
// 		if(diagnosis_code1=="" || diagnosis_code1 == null){
// 			alert("Please  Enter The Diagnosis-1");
// 			$("#s_diagnosis_code1"+ps).focus()
// 			return false;
// 		}
// 		if(diagnosis_code2=="" || diagnosis_code2 == null){
// 			alert("Please  Enter The Diagnosis-2");
// 			$("#s_diagnosis_code2"+ps).focus()
// 			return false;
// 		}
// 		if(diagnosis_code3=="" || diagnosis_code3 == null){
// 			alert("Please  Enter The Diagnosis-3");
// 			$("#s_diagnosis_code3"+ps).focus()
// 			return false;
// 		}
		
// 		if(diagnosis_code6!="" && diagnosis_code6 != null){
// 			if(diagnosis_code4=="" || diagnosis_code4 == null){
// 				alert("Please First Enter The Diagnosis-4");
// 				$("#s_diagnosis_code4"+ps).focus()
// 				return false;
// 			}
			
// 			if(diagnosis_code5=="" || diagnosis_code5 == null){
// 				alert("Please First Enter The Diagnosis-5");
// 				$("#s_diagnosis_code5"+ps).focus()
// 				return false;	
// 					}
// 		}
		
// 		if(diagnosis_code5!="" && diagnosis_code5 != null){
// 			if(diagnosis_code4=="" || diagnosis_code4 == null){
// 				alert("Please First Enter The Diagnosis-4");
// 				$("#s_diagnosis_code4"+ps).focus();
// 				return false;
// 			}
// 		}
// 	}
	
	
// 	   $.post('medical_categoryAction?' + key + "=" + value, {Shape_status:Shape_status,Shape_value:Shape_value,from_date:from_date,
// 		   to_date:to_date,diagnosis_code1:diagnosis_code1,diagnosis_code2:diagnosis_code2,diagnosis_code3:diagnosis_code3,diagnosis_code4:diagnosis_code4,
// 		   diagnosis_code5:diagnosis_code5,diagnosis_code6:diagnosis_code6,authority:authority,date_of_authority:date_of_authority,mad_classification:mad_classification,
// 		   comm_id:comm_id,p_id:p_id,sShape_ch:sShape_ch,Shape:Shape}, function(data){
		      
	      
// 	          if(parseInt(data)>0){
	        	
// 	        	 $('#SShape_ch_id'+ps).val(data);
//  	        	 $("#sShape_add"+ps).show();
// 	        	 $("#sShape_remove"+ps).show();
// 	        	  alert("Data Saved SuccesFully")
// 	          }
// 	          else
// 	        	  alert(data);
// 	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 	  		});
// }


function getsShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='S'
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			 
			
			$('#s_madtable').show(); 
				$('#s_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
					 var fd="";
					 var td="";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=new Date(j[i][2]).toISOString().split('T')[0];
					  	 td=new Date(j[i][3]).toISOString().split('T')[0];
					 }
					
						$("table#s_madtable").append('<tr id="tr_sShape'+x+'">'
							+'<td class="sShape_srno" style="width: 2%;">'+x+'</td>'
							+'<td>'
							
							+'<select name="s_status'+x+'" id="s_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="sShape_value'+x+'" id="sShape_value'+x+'" '
							+'		class="form-control-sm form-control">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<input type="date" id="s_from_date'+x+'" name="s_from_date'+x+'" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'		<input type="date" id="s_to_date'+x+'" name="s_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
							+'   </td>'
							+'  <td style="display:none; "  class="diagnosisClass1'+x+'">'
							+' <input type="text" name="_diagnosis_code1'+x+'" id="_diagnosis_code1'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="sShape_ch_id'+x+'" name="sShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass1'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+x+'" onclick="sShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							

							+'</tr>');
						$("#s_status"+x).val(j[i][0]);
						$.ajaxSetup({
  							async : false
  						});

						statusChange(1,x,j[i][0]);
						
						$("#sShape_value"+x).val(j[i][1]); 
						if(x>1){
							$("#s_status"+x+" option[value='1']").remove();
							$("#s_status"+(x-1)+" option[value='1']").remove();
							
						}
						 
						
						
					
		}
			sShape=v;
			$("input#sShape_count").val(v);
			$("input#sShapeOld_count").val(v);
			$("#sShape_add"+v).show(); 
			$("#madical_authority").val(j[i-1][6]); 
			$("#madical_date_of_authority").val(new Date(j[i-1][7]).toISOString().split('T')[0]); 
			$('#mad_classification_count').val(j[i-1][8]);
//  			$('#mad_classification').attr('readonly', true);
			
			setDiagnosis();
			
			
			}
			
	  });
}


function gethShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='H';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#h_madtable').show(); 
				$('#h_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=new Date(j[i][2]).toISOString().split('T')[0];
					  	 td=new Date(j[i][3]).toISOString().split('T')[0];
					 }
					
					
						$("table#h_madtable").append('<tr id="tr_hShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							
							+'<select name="h_status'+x+'" id="h_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="hShape_value'+x+'" id="hShape_value'+x+'" '
							+'		class="form-control-sm form-control">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<input type="date" id="h_from_date'+x+'" name="h_from_date'+x+'" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'		<input type="date" id="h_to_date'+x+'" name="h_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
							+'   </td>'
							+'  <td style="display:none; "  class="diagnosisClass2'+x+'">'
							+' <input type="text" name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="hShape_ch_id'+x+'" name="hShape_ch_id'+x+'" value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass2'+x+'">'
							+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+x+'" onclick="hShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						$("#h_status"+x).val(j[i][0]);
						$.ajaxSetup({
 							async : false
 						});
						
						statusChange(2,x,j[i][0]);
						

						$("#hShape_value"+x).val(j[i][1]); 
						if(x>1){
							$("#h_status"+x+" option[value='1']").remove();
							$("#h_status"+(x-1)+" option[value='1']").remove();
							
						}
						 
						
						
					
		}
			hShape=v;
			$("input#hShape_count").val(v);
			$("input#hShapeOld_count").val(v);
			$("#hShape_add"+v).show();
			
			setDiagnosis();
			}
			
	  });
}



function getaShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='A';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			classification=j[0][13];
			$('#a_madtable').show(); 
				$('#a_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=new Date(j[i][2]).toISOString().split('T')[0];
					  	 td=new Date(j[i][3]).toISOString().split('T')[0];
					 }
					
					
						$("table#a_madtable").append('<tr id="tr_aShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="a_status'+x+'" id="a_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="aShape_value'+x+'" id="aShape_value'+x+'" '
							+'		class="form-control-sm form-control">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<input type="date" id="a_from_date'+x+'" name="a_from_date'+x+'" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'		<input type="date" id="a_to_date'+x+'" name="a_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
							+'   </td>'
							
							+'  <td style="display:none; "  class="diagnosisClass3'+x+'">'
							+' <input type="text" name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="aShape_ch_id'+x+'" name="aShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass3'+x+'" >'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+x+'" onclick="aShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						$("#a_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(3,x,j[i][0]);
						
						$("#aShape_value"+x).val(j[i][1]); 
						if(x>1){
							$("#a_status"+x+" option[value='1']").remove();
							$("#a_status"+(x-1)+" option[value='1']").remove();
							
						}
						 
						
						
					
		}
			aShape=v;
			$("input#aShape_count").val(v);
			$("input#aShapeOld_count").val(v);
			$("#aShape_add"+v).show(); 		
			
			setDiagnosis();
			}
			
	  });
}



function getpShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='P';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#p_madtable').show(); 
				$('#p_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=new Date(j[i][2]).toISOString().split('T')[0];
					  	 td=new Date(j[i][3]).toISOString().split('T')[0];
					 }
					
					
						$("table#p_madtable").append('<tr id="tr_pShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="p_status'+x+'" id="p_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="pShape_value'+x+'" id="pShape_value'+x+'" '
							+'		class="form-control-sm form-control">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<input type="date" id="p_from_date'+x+'" name="p_from_date'+x+'" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'		<input type="date" id="p_to_date'+x+'" name="p_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
							+'   </td>'													
							+'  <td style="display:none; "  class="diagnosisClass4'+x+'">'
							+' <input type="text" name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="pShape_ch_id'+x+'" name="pShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass4'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+x+'" onclick="pShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						$("#p_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(4,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#pShape_value"+x).val(j[i][1]); 
						if(x>1){
							$("#p_status"+x+" option[value='1']").remove();
							$("#p_status"+(x-1)+" option[value='1']").remove();
							
						}
						 
						
						
					
		}
			pShape=v;
			$("input#pShape_count").val(v);
			$("input#pShapeOld_count").val(v);
			$("#pShape_add"+v).show(); 	
			setDiagnosis();
			}
			
	  });
}


function geteShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='E';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			$('#e_madtable').show(); 
				$('#e_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=new Date(j[i][2]).toISOString().split('T')[0];
					  	 td=new Date(j[i][3]).toISOString().split('T')[0];
					 }
					
					
						$("table#e_madtable").append('<tr id="tr_eShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="e_status'+x+'" id="e_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="eShape_value'+x+'" id="eShape_value'+x+'" '
							+'		class="form-control-sm form-control">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<input type="date" id="e_from_date'+x+'" name="e_from_date'+x+'" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'		<input type="date" id="e_to_date'+x+'" name="e_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
							+'   </td>'
							+'  <td style="display:none; "  class="diagnosisClass5'+x+'">'
							+' <input type="text" name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="eShape_ch_id'+x+'" name="eShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass5'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+x+'" onclick="eShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						$("#e_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(5,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#eShape_value"+x).val(j[i][1]); 
						if(x>1){
							$("#e_status"+x+" option[value='1']").remove();
							$("#e_status"+(x-1)+" option[value='1']").remove();
							
						}
						 
						
						
					
		}
			eShape=v;
			$("input#eShape_count").val(v);
			$("input#eShapeOld_count").val(v);
			$("#eShape_add"+v).show(); 
			setDiagnosis();
			}
			
	  });
}



function getcCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='C_C';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_cmadtable').show(); 
				$('#c_cmadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					
					$("table#c_cmadtable").append('<tr id="tr_cCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	
						 	+'<select name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
						 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');">'
						 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'
						 	+'<td style="display:none;" class="cCopClass'+x+'" >'
							+'<input type="text" name="c_cother'+x+'" id="c_cother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
							+' </td>'
						 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%; display:none;" class="CopaddbtClass11" >'
						 	+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+x+'" onclick="cCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					
						$("#c_cvalue"+x).val(j[i][1]); 
						var thisT = document.getElementById('c_cvalue'+x)
					 	onCCopeChange(thisT,cCope);
						onCopeChangebt(thisT,1,x);
						if(x>1){
						 	$("#c_cvalue"+x+" option[value='0']").remove();
						 	$("#c_cvalue"+x+" option[value='1']").remove();
							$("#c_cvalue"+(x-1)+" option[value='0']").remove();
							$("#c_cvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
			cCope=v;
			$("input#cCope_count").val(v);
			$("input#cCopeOld_count").val(v);
			$("#cCope_add"+v).show(); 														
			}
			
	  });
}


function getoCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='C_O';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_omadtable').show(); 
				$('#c_omadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					
					$("table#c_omadtable").append('<tr id="tr_oCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');">'
						 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'  <td style="" >'
						 	+' <input type="text" name="c_odescription'+x+'" id="c_odescription'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
						 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
						 	+'   </td>'
						 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][10]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" >'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+x+'" onclick="oCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					
						$("#c_ovalue"+x).val(j[i][1]); 
		
		}
			oCope=v;
			$("input#oCope_count").val(v);
			$("input#oCopeOld_count").val(v);
			$("#oCope_add"+v).show(); 														
			}
			
	  });
}

function getpCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='C_P';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_pmadtable').show(); 
				$('#c_pmadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					
					$("table#c_pmadtable").append('<tr id="tr_pCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');">'
						 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'  <td style="" >'
						 	+' <input type="text" name="c_pdescription'+x+'" id="c_pdescription'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
						 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
						 	+'   </td>'
						 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][10]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" >'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+x+'" onclick="pCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					
						$("#c_pvalue"+x).val(j[i][1]); 
		
		}
			pCope=v;
			$("input#pCope_count").val(v);
			$("input#pCopeOld_count").val(v);
			$("#pCope_add"+v).show(); 														
			}
			
	  });
}


function geteCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	
	 var Shape='C_E';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_emadtable').show(); 
				$('#c_emadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					
					$("table#c_emadtable").append('<tr id="tr_eCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_evalue'+x+'" id="c_evalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');">'
						 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'  <td style="" >'
						 	+' <input type="text" name="c_edescription'+x+'" id="c_edescription'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
						 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
						 	+'   </td>'
						 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'"  value="'+j[i][10]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" >'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+x+'" onclick="eCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
						 	+'</tr>');
					
						$("#c_evalue"+x).val(j[i][1]); 
		
		}
			eCope=v;
			$("input#eCope_count").val(v);
			$("input#eCopeOld_count").val(v);
			$("#eCope_add"+v).show(); 														
			}
			
	  });
}

 </script> 
