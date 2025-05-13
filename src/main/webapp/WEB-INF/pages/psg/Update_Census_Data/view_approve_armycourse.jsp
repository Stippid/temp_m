<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>


<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>


	<!-- <div class="card">

		<div class="panel-group" id="accordion98">
			<div class="panel panel-default" id="cc_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title lightgreen" id="cc_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion98" href="#" id="cc_final"
							onclick="divN(this)" style="color: #90EE90;"><b>ARMY
								COURSE</b></a>
					</h4>
				</div>
				<div id="collapse1cc" class="panel-collapse collapse">

					<div class="card-body card-block">
					<div class="card-body card-block" id="total_table">
						<div class="card-body-header">
							<h5></h5>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Authority</label>
									</div>
									<div class="col-md-8">
										<label id="army_course_authority" name="army_course_authority"
											class=" autocomplete"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Date of Authority</label>
									</div>
									<div class="col-md-8">
										<label id="army_course_date_of_authority"
											name="army_course_date_of_authority" class=" autocomplete">
										</label>
									</div>
								</div>
							</div>
							<input type=hidden id="comm_id" name="comm_id" class="form-control autocomplete">
						</div>
						<table id="army_course_table" class="table-bordered "
							style="margin-top: 10px; width: -webkit-fill-available;">
							<thead style="color: white; text-align: center;">
								<tr>
									<th style="width: 2%;">Sr No</th>
									<th style="width: 10%;">Course Name</th>
									<th style="width: 10%;">Course Name Abbreviation</th>
									<th style="width: 10%;">Course Institute</th>
									<th style="width: 10%;">Div/Grade</th>
									<th style="width: 10%;">Course Type</th>
									<th style="width: 10%;">Start Date</th>
									<th style="width: 10%;">Date of Completion</th>

								</tr>
							</thead>
							<tbody data-spy="scroll" id="army_coursetbody"
								style="min-height: 46px; max-height: 650px; text-align: center;">
								<tr id="tr_army_course1">
									<td class="army_course_srno" style="width: 2%;">1</td>
								</tr>
							</tbody>
						</table>
					</div>

					</div>
				</div>
			</div>
		</div>
	</div> -->
<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
						<h5>
							<b>ARMY COURSE</b><br>
						</h5>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Personal No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="personnel_no" name="personnel_no"
												class="form-control autocomplete" autocomplete="off" readonly>
												
												<input type="hidden" id="comm_id" name="comm_id"
												value="0">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Authority</label>
										</div>
										<div class="col-md-8">
											<label id="army_course_authority"
												name="army_course_authority" class=" autocomplete"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Date of Authority</label>
										</div>
										<div class="col-md-8">
											<label id="army_course_date_of_authority"
												name="army_course_date_of_authority" class=" autocomplete">
											</label>
										</div>
									</div>
								</div>
							</div>
							<table id="army_course_table" class="table-bordered "
								style="margin-top: 10px; width: -webkit-fill-available;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Course Name</th>
										<th style="width: 10%;">Course Name Abbreviation</th>
										<th style="width: 10%;">Course Institute</th>
										<th style="width: 10%;">Course Institute Other</th>
										<th style="width: 10%;">Div/Grade</th>
										<th style="width: 10%;">Div/Grade Other</th>
										<th style="width: 10%;">Course Type</th>
										<th style="width: 10%;">Start Date</th>
										<th style="width: 10%;">Date of Completion</th>

									</tr>
								</thead>
								<tbody data-spy="scroll" id="army_coursetbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_army_course1">
										<td class="army_course_srno" style="width: 2%;">1</td>
									</tr>
								</tbody>
							</table>
						</div>
				</div>
						
		</div>
		</div>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#comm_id").val('${comm_id}');
	$("#personnel_no").val('${personnel_no}');
	get_army_course_details();
});
function get_army_course_details(){
	debugger;
	xaco=0;
	<c:forEach var="j" items="${Army_Course}" varStatus="num"> 
		
		xaco=xaco+1;
			if(xaco==1){
				$('#army_coursetbody').empty(); 
			}
			var dauth=convertDateFormate('${j.date_of_authority}');
			var start_date=convertDateFormate('${j.start_date}');
			var date_of_completion=convertDateFormate('${j.date_of_completion}');
			$("table#army_course_table").append('<tr id="tr_lang'+xaco+'">'
					//1
					+'<td class="army_course_srno" style="width: 2%;">'+xaco+'</td>'
					//2
					+'<td style="width: 10%;">'
					+'<label  id="army_course_name'+xaco+'" name="army_course_name'+xaco+'"   class=" autocomplete" >'+"${j.course_name}"+'</label>'
					+'</td>'
					//3
					+'<td style="width: 10%;">'
					+'<label  id="army_course_abbreviation'+xaco+'" name="army_course_abbreviation'+xaco+'"   class=" autocomplete" >'+"${j.course_abbreviation}"+'</label>'
					+'</td>'
					//4
					+'<td style="width: 10%;">'	
					+'<label  id="army_course_institutelb'+xaco+'" name="army_course_institutelb'+xaco+'" class=" autocomplete" > </label>'
					+'<select style="display:none;"  id="army_course_institute'+xaco+'" name="army_course_institute'+xaco+'" class="form-control autocomplete"  >'
					+'<option value="0">--Select--</option>'
					+'<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
					+'</c:forEach></select></td>'
					//5
					+'<td style="width: 10%;">'
					+ '<label  id="army_course_institute_ot'+xaco+'" name="army_course_institute_ot'+xaco+'" class=" autocomplete" > </label></td>'
					
					//6
					+'<td style="width: 10%;">'
					+'<label  id="army_course_div_gradelb'+xaco+'" name="army_course_div_gradelb'+xaco+'" class=" autocomplete" > </label>'
					+'<select style="display:none;" id="army_course_div_grade'+xaco+'" name="army_course_div_grade'+xaco+'" class="form-control autocomplete"  >'
					+'<option value="0">--Select--</option>'
					+'<c:forEach var="item" items="${getDivclass}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
					+'</c:forEach></select></td>'
					//7
					+'<td style="width: 10%;">'
						+ '<label  id="ar_course_div_ot'+xaco+'" name="ar_course_div_ot'+xaco+'" class=" autocomplete" > </label></td>'
					

					
					
					//8
					+'<td style="width: 10%;">'
					+'<label  id="army_course_typelb'+xaco+'" name="army_course_typelb'+xaco+'" class=" autocomplete" > </label>'
					+'<select style="display:none;" id="army_course_type'+xaco+'" name="army_course_type'+xaco+'" class="form-control autocomplete"  >'
					+'<option value="0">--Select--</option>'
					+'<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
					+'</c:forEach> </select>	</td>'
					//9
					+'<td style="width: 10%;">'
					+'<label  id="army_course_start_date'+xaco+'" name="army_course_start_date'+xaco+'"   class=" autocomplete"  >'+start_date+'</label>'
					+'</td>'
					//10
					+'<td style="width: 10%;">'
					+'<label  id="army_course_date_of_completion'+xaco+'" name="army_course_date_of_completion'+xaco+'"  class=" autocomplete" >'+date_of_completion+'</label>'
					+'</td>'
					 
					+'</tr>');
			
			 $('#army_course_div_grade'+xaco).val('${j.div_grade}' );
			 $('#army_course_div_gradelb'+xaco).text($('#army_course_div_grade'+xaco+" option:selected").text() );
			 if('${j.ar_course_div_ot}'!=null && '${j.ar_course_div_ot}'!='' && ($('#army_course_div_gradelb'+xaco).text().toUpperCase()==other || $('#army_course_div_gradelb'+xaco).text().toUpperCase()==others)){
				 $('#army_course_div_gradelb'+xaco).text('${j.ar_course_div_ot}');
				}
			 
			 $('#army_course_institute'+xaco).val('${j.course_institute}' );
			 $('#army_course_institutelb'+xaco).text($('#army_course_institute'+xaco+" option:selected").text() );
			 if('${j.course_institute_other}'!=null && '${j.course_institute_other}'!='' && ($('#army_course_institutelb'+xaco).text().toUpperCase()==other || $('#army_course_institutelb'+xaco).text().toUpperCase()==others)){
				 $('#army_course_institutelb'+xaco).text('${j.course_institute_other}');
				}
			 
			 $('#army_course_type' +xaco).val('${j.course_type}' );
			 
			 $('#army_course_typelb'+xaco).text($('#army_course_type'+xaco+" option:selected").text() );
			 if('${j.course_type_ot}'!=null && '${j.course_type_ot}'!='' && ( $('#army_course_typelb'+xaco).text().toUpperCase()==other ||  $('#army_course_typelb'+xaco).text().toUpperCase()==others)){
				 $('#army_course_typelb'+xaco).text('${j.course_type_ot}');
				}
			 $('#army_course_authority').text('${j.authority}' );
			 $('#army_course_date_of_authority').text(dauth );
		
		</c:forEach>
	
}

</script>
