<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
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

<style>
.sticky {
	position: fixed;
	top: 0;
	z-index: 1000;
	padding-right: 35px;
}

.sticky+.subcontent {
	padding-top: 330px;
}

.ins_item {
	transition: transform 0.3s ease;
	/* Smooth transition for the transform property */
	cursor: pointer; /* Change cursor to pointer on hover */
}

.ins_item:hover {
	transform: scale(1.1); /* Enlarge the item by 10% on hover */
}

.modal {
	align-content: center;
}

.close.btn-close {
	color: red;
	font-size: xxx-large;
	padding-left: 30px;
}

.modal {
	display: none; /* Hidden by default */
}

.modal.show {
	display: block; /* Show the modal */
}

#part1, #part2  {
	font-weight: bold;
	font-size: 16px;
}

.mb_1
{
margin-bottom: -14px;
}
.mb {
	margin-bottom: 0px;
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
}


.card {
	    background: #fff;
    margin-bottom: 10px;
    box-shadow: 0px 0px 5px #cfcbcb;
    border-radius: 5px;
}
.card-header{
	padding: 10px;	
	text-align: center;
	background: aliceblue;
    border-bottom: 0;
    border-radius: 5px 5px 0 0 !important;
}

.card-footer
{
	padding: 10px;	
	text-align: center;
	background: aliceblue;
    border-bottom: 0;
    border-radius: 5px 5px 0 0 !important;
}
.card-header h4  {
	text-align:center;
    font-weight: bold;
    color: #864663;
  	 font-size: xx-large;
}
.modal-title h5{
	
		text-align:center;
    font-weight: bold;
    color: #864663;
  	 font-size: x-large;
}
.card-body {
    padding: 10px;
}
.ins_main {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
.ins_item {
	width: calc((100% / 5) - 5px);
    margin: 2px;
    padding: 10px;
    background: #d7e9e1;
    text-align: center;
    font-weight: bold;
    border-radius: 10px;
    min-height: 80px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 5px double #fff;
    cursor: pointer;
}
.clr1 {
	background: #d7e9e1;
}
.clr2 {
	background: #e9e0d7;
}
.clr3 {
	background: #d7d7e9;
}
.clr4 {
	background: #e8e9d7;
}
.clr5 {
	background: #d7dee9;
}
.clr6 {
	background: #e6d7e9;
}
.clr7 {
	background: #dce9d7;
}
.clr8 {
	background: #e9d7d7;
}
.clr9 {
	background: #d7e7e9;
}
.clr10 {
	background: #e5e9d7;
}
body .modal-dialog {
    max-width: 80%;
}
.modal-header {
    background: aliceblue;
    border-bottom: 0;
}
.modal-header .modal-title {
    font-weight: bold;
    text-align: center;
    width: 100%;
}
.btn, .button {
    margin: 2px;
}
@media (max-width: 1200px) {
	.ins_item {
		width: calc((100% / 4) - 5px);
	}
}

@media (max-width: 1024px) {
	.ins_item {
		width: calc((100% / 3) - 5px);
	}
}
@media (max-width: 768px) {
	.ins_item {
		width: calc((100% / 2) - 5px);
	}
}

.display_none {
	display: none;
}
.width_7
{
width:3%;
}

</style>

<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			<h4>ADM INSP RPT</h4>
			
			<div class="col-md-12 mb_1" align="center">
					<div class="col-md-4"></div>
					<div class="col-md-4 ">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
									<li class="nav-item" role="presentation">
									<button class="nav-link" id="part1"
										data-bs-toggle="tab" data-bs-target="#part1_div" type="button"
										role="tab" aria-controls="home" aria-selected="true" onclick="radioch(1)">Part I</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link active" id="part2" data-bs-toggle="tab"
										data-bs-target="#part2_div" type="button" role="tab"
										aria-controls="profile" aria-selected="false">Part II</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>
		</div>

	

<div class="card-body" id="part2_div">
	<div class="ins_main">
		<div class="ins_item clr1" id="op_preparedness_item" onclick="openModal('#OpPreparedness')">Op Preparedness</div>
		<div class="ins_item clr2" id="training_item" onclick="openModal('#Training')">Training</div>
		<div class="ins_item clr3" id="state_weapon_item" onclick="openModal('#StateWeapEqpt')">State of Weapons/ Equipment</div>
		<div class="ins_item clr1" id="state_personnel_item" onclick="openModal('#StatePersonnel')">State of Personnel</div>
		<div class="ins_item clr2" id="administation_item" onclick="openModal('#Administration')">Administration</div>
		<div class="ins_item clr3" id="aspect_item" onclick="openModal('#AspectMorale')">Aspects Affecting Morale and Motivation</div>
		<div class="ins_item clr1" id="interior_item" onclick="openModal('#InteriorEconomy')">Interior Economy</div>
		
		 <div class="ins_item clr8" id="major_achievements_item" onclick="openModal('#MajorAchievements')">Major Achievements</div>
         <div class="ins_item clr9" id="strength_of_unit_item" onclick="openModal('#StrengthOfTheUnit')">Strength of the Unit</div>
         <div class="ins_item clr10" id="challenges_item" onclick="openModal('#Challenges')">Challenges</div>
         <div class="ins_item clr1" id="improve_following_item" onclick="openModal('#improveFollowing')">Recommendations / Innovations to improve the following</div>
         <div class="ins_item clr2" id="attention_of_higher_item" onclick="openModal('#AttentionOfHigherHQ')">Issues Requiring Attention of Higher HQ</div>
         <div class="ins_item clr3" id="mitigation_item" onclick="openModal('#MitigationActionSummary')">Mitigation Action Summary</div>
         <div class="ins_item clr4" id="points_discussion_item" onclick="openModal('#PointsForDiscussion')">Points for Discussion</div>

		
	</div>
	
	<c:if test="${report.op_preparedness_item == 1 && report.training_item == 1 
			&& report.state_weapon_item == 1 && report.state_personnel_item == 1 && report.administation_item == 1
			&& report.aspect_item == 1 && report.interior_item == 1 && report.major_achievements_item == 1
			&& report.strength_of_unit_item == 1 && report.challenges_item == 1 && report.improve_following_item == 1
			&& report.attention_of_higher_item == 1 && report.mitigation_item == 1 && report.points_discussion_item == 1}">
				<div align="center">
				
				
				<button type="button" class="btn btn-primary btn-sm" id="pritnrepot" onclick="return phase2pdf();" >
					<i class="fa fa-download" id="icon_download"></i> Download Controller PDF
				</button>
				</div>
				
			</c:if>
</div>
</div>
</div>

<!--   PART II   -->

<div class="modal fade" id="OpPreparedness" tabindex="-1" role="dialog"
     aria-labelledby="operationalPreparednessLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title" id="operationalPreparednessLabel">Operational Preparedness</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#Training')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="opForm" method="POST">  <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="opContent" style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermarkOp">
                            <span id="ip"></span>
							
							<div class="col-md-12">
                                <span id="ip"></span> <label class="col-form-label">
                                The issues included shall dwell on following aspects :-<br><br>
                                           <strong>(a)</strong> Capability of unit to undertake operational task at short notice.
                                           <br><br>
                                           <strong>(b)</strong> Challenges, if any, in operational preparedness for the envisaged operational task with reasons such as:-
                                           <br><br>
                                           <div style="margin-left: 20px;">
                                                   <strong>(i)</strong> Shortage of weapons/ ammunition/ transport facilities/ equipment/ Sector Stores authorised in the location.
                                                   <br><br>
                                                   <strong>(ii)</strong> Critical deficiency of manpower, with special reference to officers state.
                                                   <br><br>
                                                   <strong>(iii)</strong> Challenges, due to lack of training for want of Army courses.
                                                   <br><br>
                                                   <strong>(iv)</strong> Degree of operational preparedness, on account of new raising or a disbanded unit.
                                           </div>
                                   </label>
                            </div>
							<div class="col-md-12">
								<textarea class="form-control" id="op" name="op" rows="3"></textarea>
							</div>
							
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                    <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                        <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                        <div class="form-group">
                            <label for="remarks"><strong>Remarks: </strong></label>
                            <textarea class="form-control" id="user_remarks1" name="user_remarks1" rows="2"></textarea>
                        </div>

                        <div class="form-group" style="display:none">
                            <label for="establishment_screenshot"><strong>Screenshot (Optional):</strong></label>
                            <input type="file" class="form-control-file" id="op_screenshot" name="op_screenshot">
                            <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                        </div>
                    </div>
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
                        <c:if test="${report.op_preparedness_item != 1}">
                            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="op_preparedness_item">
                                Submit for Approval</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="Training" tabindex="-1" role="dialog"
     aria-labelledby="trainingLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#OpPreparedness')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Training</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#StateWeapEqpt')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="trainingForm" method="POST">  <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="trainingContent" style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermarkTraining">
                            <span id="ip"></span>
							
							<div class="col-md-12">
                                <span id="ip"></span> <label class="col-form-label">
                                The issues included shall dwell on following aspects :-<br><br>
                                       <strong>(a) State of Training of Unit</strong>
                                       <br><br>
                                       <div style="margin-left: 20px;">
                                               <strong>(i) Individual Training</strong>. The under mentioned aspects shall be included:-
                                               <br><br>
                                               <div style="margin-left: 20px;">
                                                       <strong>(aa)</strong> Physical Fitness standards to include BPET, PPT and Route Marches.
                                                       <br><br>
                                                       <strong>(ab)</strong> Weapon Training including Firing standards.
                                                       <br><br>
                                                       <strong>(ac)</strong> Night Training.
                                                       <br><br>
                                                       <strong>(ad)</strong> Specialist Training.
                                                       <br><br>
                                                       <strong>(ae)</strong> Junior Leaders Training (YOs and JCOs).
                                                       <br><br>
                                                       <strong>(af)</strong> Officers Training.
                                                       <br><br>
                                                       <strong>(ag)</strong> Courses of instructions attended by personnel at Category 'A' and Category 'B' Establishments.
                                               </div>
                                               <br>
                                               <strong>(ii) Sub-Unit Level Training</strong>. Appraisal be carried out in respect of sub-unit level training as applicable to all Arms and Services, e.g. Infantry at Company level, Armoured Corps at Squadron level, Artillery at Battery level, etc.
                                               <br><br>
                                               <strong>(iii) Collective Training</strong>. The following may be covered under this:-
                                               <br><br>
                                               <div style="margin-left: 20px;">
                                                       <strong>(aa)</strong> Training exercises and training camps.
                                                       <br><br>
                                                       <strong>(ab)</strong> Field Firing.
                                               </div>
                                       </div>
                                       <br>
                                       <strong>(b)</strong> Factors having affected or contributed towards unit training (could include opportunities afforded for training, availability of training areas / operational areas, allocation of funds etc).
                                       <br><br>
                                       <strong>(c)</strong> Actions taken to conduct training effectively.
                                       <br><br>
                                       <strong>(d)</strong> Steps taken for development of JCOs and NCOs
                               </label>
                            </div>
							
							<div class="col-md-12">
								<textarea class="form-control" id="training" name="training" rows="3"></textarea>
							</div>
							
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                    <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                        <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                        <div class="form-group">
                            <label for="remarks"><strong>Remarks: </strong></label>
                            <textarea class="form-control" id="user_remarks2" name="user_remarks2" rows="3"></textarea>
                        </div>

                        <div class="form-group" style="display:none">
                            <label for="establishment_screenshot"><strong>Screenshot (Optional):</strong></label>
                            <input type="file" class="form-control-file" id="training_screenshot" name="training_screenshot">
                            <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                        </div>
                    </div>
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
                        <c:if test="${report.training_item != 1}">
                            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="training_item">
                                Submit for Approval</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<div class="modal fade" id="StateWeapEqpt" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#Training')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">State of Weapons/ Equipment</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#StatePersonnel')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						
						<label for="operationalPreparednessDegree" class="col-md-8 col-form-label">
						The issues included shall dwell on following aspects :-<br><br>
                            (a)	Critical deficiencies of arms, weapons, major equipment and transport having bearing on unit training and administration
                        </label>
						
						<table id="stateWeapons"
							class="table no-margin table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th rowspan="1"
										style="text-align: center; height: 50px; vertical-align: middle;"class="width_7"
										id="serial_number">Sr No</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="nature_defi">Nature Of Deficiency</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="action_defi">Action Taken to Meet the Deficiency</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="effect_conduct">Effect on Conduct of Training or Operational Preparedness</th>
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="remarkssp">Remarks</th>
									<c:if test="${report.state_weapon_item != 1}">
									<th rowspan="1"
										style="text-align: center; vertical-align: middle;"
										id="sw_action">Action</th>
									</c:if>
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
									<c:if test="${report.state_weapon_item != 1}">
									<th style="text-align: center;">(f)</th>
									</c:if>
								</tr>
							</thead>
							
							<tbody style="font-size: 12px;" id="SW_tbody">
								<tr id="firstRow">
									<td name="srno"></td>
									<td><input type="text" name="natureDeficiency"></td>
									<td><input type="text" name="actionDeficiency"></td>
									<td><input type="text" name="effectConduct"></td>
									<td><input type="text" name="remarksSW"></td>
									<c:if test="${report.state_weapon_item != 1}">
										<td>
											<button type="button" class="fa fa-plus btn btn-success btn-sm add-to-recommended" data-context="stateWeapons"></button>
								        </td>
							        </c:if>
								</tr>
							</tbody>
						</table>
					</div>
					
					
							<div class="col-md-12">                                
                                   
                                   <div class="form-group">
						 <strong>(b)</strong> Adequacy of administrative support provided to facilitate training and administration in the unit </strong></label>
						<textarea class="form-control" id="weaponb" name="weaponb" rows="3"></textarea>
					</div>
					              <div class="form-group">
						 <strong>(c)</strong> Adequacy of technical maintenance support and availability of spares in respect of arms, weapons, major equipment and transport affecting operational efficiency.</strong></label>
						<textarea class="form-control" id="weaponc" name="weaponc" rows="3"></textarea>
					</div>
                            </div>
				</div>
			</div>
		<div class="modal-footer">
		<div class="col-6">
		<p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
					<p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
					<div class="form-group">
						<label for="remarks"><strong>Remarks: </strong></label>
						<textarea class="form-control" id="user_remarks3" name="user_remarks3" rows="3"></textarea>
					</div>

					<div class="form-group" style="display:none">
						<label for="establishment_screenshot"><strong>Screenshot (Optional):</strong></label>
						<input type="file" class="form-control-file" id="establishment_screenshot" name="establishment_screenshot">
						<small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
					</div>
				</div><div class="col-6 text-right">
				<button type="button" class="btn btn-secondary btn-sm" 
					data-dismiss="modal" onclick="closeModal()">Close</button>				
					<c:if test="${report.state_weapon_item != 1}">
	       <button type="button" class="btn btn-primary btn-sm add-to-submit_approve"  data-context="state_weapon_item" >Submit for Approval</button>
	       </c:if>
			</div>	</div>
		</div>
	</div>
</div>

<div class="modal fade" id="StatePersonnel" tabindex="-1"
    role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#StateWeapEqpt')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">State of Personnel</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#Administration')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                <div class="col-md-12" id="getSearch_Letter" style="display: block;">
                    <div class="watermarked" data-watermark="" id="divwatermark">
                        <span id="ip"></span>
                        
                        <label for="operationalPreparednessDegree" class="col-md-8 col-form-label">
                        The issues included shall dwell on following aspects :-<br><br>
                                                        (a)    Critical Deficiency of Manpower Affecting Operational Efficiency
                                                </label>
                        
                        <table id="statepersonnel"
                            class="table no-margin table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                       <th rowspan="2" style="text-align: center; height: 50px; vertical-align: middle;" class="width_7" id="serial_number">Serial Number</th>
                                       <th colspan="3" style="text-align: center; vertical-align: middle;" id="nature_defi">Deficiency of Manpower</th>
                                       <th rowspan="2" style="text-align: center; vertical-align: middle;" id="action_defi">Action Taken to Meet the Deficiency</th>
                                       <th rowspan="2" style="text-align: center; vertical-align: middle;" id="effect_conduct">Effect on Conduct of Training</th>
                                       <th rowspan="2" style="text-align: center; vertical-align: middle;" id="remarks">Remarks</th>
                                       <c:if test="${report.state_personnel_item != 1}">
                                       <th rowspan="2" style="text-align: center; vertical-align: middle;" id="remarks">Action</th>
                                       </c:if>
                               </tr>
                               <tr>
                                       <th style="text-align: center;">Officers</th>
                                       <th style="text-align: center;">JCOs</th>
                                       <th style="text-align: center;">OR</th>
                               </tr>
                                <tr>
                                    <th style="text-align: center;">(a)</th>
                                    <th style="text-align: center;">(b)</th>
                                    <th style="text-align: center;">(c)</th>
                                    <th style="text-align: center;">(d)</th>
                                    <th style="text-align: center;">(e)</th>
                                    <th style="text-align: center;">(f)</th>
                                    <th style="text-align: center;">(g)</th>
                                    <c:if test="${report.state_personnel_item != 1}">
                                    <th style="text-align: center;"></th>
                                    </c:if>
                                </tr>
                            </thead>
                            
                            <tbody style="font-size: 12px;" id="SWp_tbody">
                                <tr id="firstRow">   
                              <td name="srno"></td>                               
                                    <td><input type="text" name="manpower_deficiencyoffrs"></td>
                                    <td><input type="text" name="manpower_deficiencyjco"></td>
                                    <td><input type="text" name="manpower_deficiencyor"></td>
                                    <td><input type="text" name="action_taken"></td>
                                    <td><input type="text" name="training_effect"></td>
                                    <td><input type="text" name="remarkssp"></td>
                                    <c:if test="${report.state_personnel_item != 1}">
                                    <td>
                                        <button type="button" class="fa fa-plus btn btn-success btn-sm add-to-recommended" data-context="statepersonnel"></button>
									</td>
									</c:if>
                                </tr>
                            </tbody>
                        </table>
                       
                    </div>
                    
                </div>
                
                <div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						 <label for="operationalPreparednessDegree" class="col-md-8 col-form-label">
                                                       (b)	Discipline State including Court Martial and Summary Disposal Cases.
                                                </label>
						 <table id="statepersonnel1"
                            class="table no-margin table-striped table-hover table-bordered">
                            <thead>
								<tr>
									<th
										style="text-align: center; height: 50px; vertical-align: middle;"
										class="width_7" id="serial_number">Serial Number</th>
									<th style="text-align: center; vertical-align: middle;"
										id="ongoing_cases">Ongoing Court Martial Cases in the
										unit/ Formation HQ including very Old Cases</th>
									<th style="text-align: center; vertical-align: middle;"
										id="pending_cases">Cases Pending Decision</th>
									<th style="text-align: center; vertical-align: middle;"
										id="disposed_cases">Cases Reported in the Current
										Reporting Year and Summarily Disposed Off by the Commanding
										Officer</th>
									<th style="text-align: center; vertical-align: middle;"
										id="remarks">Remarks</th>
									<c:if test="${report.state_personnel_item != 1}">
									<th style="text-align: center; vertical-align: middle;"
										id="action">Action</th>
									</c:if>
								</tr>
								<tr>
                                    <th style="text-align: center;">(a)</th>
                                    <th style="text-align: center;">(b)</th>
                                    <th style="text-align: center;">(c)</th>
                                    <th style="text-align: center;">(d)</th>
                                    <th style="text-align: center;">(e)</th>
                                    <c:if test="${report.state_personnel_item != 1}">
                                    <th style="text-align: center;"></th>
                                   	</c:if>
                                </tr>
                            </thead>
                            
                            <tbody style="font-size: 12px;" id="statepersonnel1_tbody">
                                <tr id="firstRow">
                                    <td name="srno"></td>
                                    <td><input type="text" name="ongoingCount"></td>
                                    <td><input type="text" name="pendingCases"></td>
                                    <td><input type="text" name="casesCurrent"></td>
                                    <td><input type="text" name="remarksSW1"></td>
                                    <c:if test="${report.state_personnel_item != 1}">
                                    <td>
                                        <button type="button" class="fa fa-plus btn btn-success btn-sm add-to-recommended" data-context="statepersonnel1"></button>
                                           </td>
									</c:if>
                                </tr>
                            </tbody>
                        </table>
					</div>
					
					<div class="col-md-12">                                
						<div class="form-group">
							(c) Availability of Officers in the Unit. Deficiency of officers/ profile etc. affecting operational preparedness of the Unit/ Formation HQ/ Establishment be highlighted</strong></label>
							<textarea class="form-control" id="personnelInput" name="personnelInput" rows="3"></textarea>
						</div>
					</div>
					
				</div>

                
                
                
            </div>
        <div class="modal-footer">
        <div class="col-6">
        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                    <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                    <div class="form-group">
                        <label for="remarks"><strong>Remarks: </strong></label>
                        <textarea class="form-control" id="user_remarks4" name="user_remarks4" rows="3"></textarea>
                    </div>

                    <div class="form-group" style="display:none">
                        <label for="personnel_screenshot"><strong>Screenshot (Optional):</strong></label>
                        <input type="file" class="form-control-file" id="personnel_screenshot" name="personnel_screenshot">
                        <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                    </div>
                </div><div class="col-6 text-right">
                <button type="button" class="btn btn-secondary btn-sm" 
                    data-dismiss="modal" onclick="closeModal()">Close</button>                
                    <c:if test="${report.state_personnel_item != 1}">
                 <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="state_personnel_item" >Submit for Approval</button>
                 </c:if>
            </div>    </div>
        </div>
    </div>
</div>

<div class="modal fade" id="Administration" tabindex="-1" role="dialog"
     aria-labelledby="administrationLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#StatePersonnel')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Administration</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#AspectMorale')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="administrationForm" method="POST">  <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="opContent" style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermarkOp">
                            <span id="ip"></span>


							<div class="col-md-12">
								<span id="ip"></span>
								The issues included shall dwell on following aspects :-<br><br>
								 <label class="col-form-label">
							       <strong>(a)</strong> Accommodation and Ancilliaries<br>
							       <strong>(b)</strong> Water Supply to troops (including married accommodation).<br>
							       <strong>(c)</strong> Timely adjustment of pay and allowances in Quarterly Statement of Accounts by PAO (OR)<br>
							       <strong>(d)</strong> Health/ Hygiene. <br>
							       <strong>(e)</strong> Leave State  <br>
							       <strong>(f)</strong> State of rations provided to the troops including variety, quality and as authorised   <br>
							       <strong>(g)</strong> Welfare measures in place for families residing in Field Area Family Accommodation (FAFA) <br>
							       <strong>(h)</strong> Sports facilities in terms of equipment and accessories made available to all ranks and opportunity afforded to them to make judicious use of the same.
							   </label>
							</div>
							
							<div class="col-md-12">
								<textarea class="form-control" id="administration" name="administration" rows="3"></textarea>
							</div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                    <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                        <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                        <div class="form-group">
                            <label for="remarks"><strong>Remarks: </strong></label>
                            <textarea class="form-control" id="user_remarks5" name="user_remarks5" rows="3"></textarea>
                        </div>

                        <div class="form-group" style="display:none">
                            <label for="administration_screenshot"><strong>Screenshot (Optional):</strong></label>
                            <input type="file" class="form-control-file" id="administration_screenshot" name="administration_screenshot">
                            <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                        </div>
                    </div>
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
                        <c:if test="${report.administation_item != 1}">
                            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="administation_item">
                                Submit for Approval</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 	Aspects Affecting Morale and Motivation		-->

<div class="modal fade" id="AspectMorale" tabindex="-1" role="dialog"
     aria-labelledby="aspectLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#Administration')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Aspects Affecting Morale and Motivation</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#InteriorEconomy')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="aspectForm" method="POST">  <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="opContent" style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermarkOp">
                            <span id="ip"></span>


							<div class="col-md-12">
								<span id="ip"></span>
								The issues included shall dwell on following aspects :-<br><br>
								 <label class="col-form-label">
							       <strong>(a)</strong> Number of untoward incidents with details<br>
							       <strong>(b)</strong> Number of Mechanical Transport accidents<br>
							       <strong>(c)</strong> ACC/ SL/ RCO commission.<br>
							       <strong>(d)</strong> Measures taken to improve level of morale and motivation of personnel <br>
							       <strong>(e)</strong> Measures to ensure adherence to and imbibing of Indian Army Core Values by all ranks  <br>
							       <strong>(f)</strong> Measures taken for resolution of problems of individual soldiers   <br>
							   </label>
							</div>
							
							<div class="col-md-12">
								<textarea class="form-control" id="aspect" name="aspect" rows="3"></textarea>
							</div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                    <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                        <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                        <div class="form-group">
                            <label for="remarks"><strong>Remarks: </strong></label>
                            <textarea class="form-control" id="user_remarks6" name="user_remarks6" rows="3"></textarea>
                        </div>

                        <div class="form-group" style="display:none">
                            <label for="aspect_screenshot"><strong>Screenshot (Optional):</strong></label>
                            <input type="file" class="form-control-file" id="aspect_screenshot" name="aspect_screenshot">
                            <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                        </div>
                    </div>
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
                        <c:if test="${report.aspect_item != 1}">
                            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="aspect_item">
                                Submit for Approval</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 							Interior Economy -->

<div class="modal fade" id="InteriorEconomy" tabindex="-1" role="dialog"
     aria-labelledby="interiorLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#AspectMorale')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Interior Economy</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#MajorAchievements')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form id="interiorForm" method="POST">  <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="interiorContent" style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermarkInterior">
                            <span id="ip"></span>


							<div class="col-md-12">
								<span id="ip"></span> <label class="col-form-label">
								The issues included in the interior economy shall dwell on following aspects :-<br>
							       <strong>(a)</strong> Living conditions of all ranks and families.<br>
							       <strong>(b)</strong> State of issue of personal clothing.<br>
							       <strong>(c)</strong> Initiative taken by units to carry out improvement in their living habitat.<br>
							       <strong>(d)</strong> Facilities provided in the living area in terms of comfort  <br>
							       <strong>(e)</strong> Modern equipment provided in the company kitchens to facilitate cooking, as well as save manpower .   <br>
							       <strong>(f)</strong> Equipment procured by the unit for area maintenance to economise on the manpower that can be usefully employed on maintenance duties or for training.   <br>
							  <strong>(g)</strong> Timely and correct publication of individual casualties.  <br>
							  <strong>(f)</strong> State of individual documentation.<br>
							   </label>
							</div>
							
							<div class="col-md-12">
								<textarea class="form-control" id="interior" name="interior" rows="3"></textarea>
							</div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                    <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                        <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                        <div class="form-group">
                            <label for="remarks"><strong>Remarks: </strong></label>
                            <textarea class="form-control" id="user_remarks7" name="user_remarks7" rows="3"></textarea>
                        </div>

                        <div class="form-group" style="display:none">
                            <label for="interior_screenshot"><strong>Screenshot (Optional):</strong></label>
                            <input type="file" class="form-control-file" id="interior_screenshot" name="interior_screenshot">
                            <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                        </div>
                    </div>
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
                        <c:if test="${report.interior_item != 1}">
                            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="interior_item">
                                Submit for Approval</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- -----------------------------------Major Achievements--------------------------- -->
    <div class="modal fade" id="MajorAchievements" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                 <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#InteriorEconomy')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Major Achievements</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#StrengthOfTheUnit')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="getSearch_Letter"
                        style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermark">
                            <div class="col-md-12">
                                <span id="ip"></span>
                                The issues included shall dwell on following aspects :-<br><br>
                                 <label class="col-form-label"> 
                                    (a) Operations.<br>
                                    (b) Training /improvement of training infrastructure.<br> 
                                    (c) Administration.<br>
                                    (d) Sports<br>
                                    (e) Innovations made in the field of training and training aids etc.<br>
                                    (f) Individual achievements of special nature.<br>
                                    (g) Achievements Resulting in the Overall Improvement of the Unit.
                            </label>
                            </div>
                            <div class="col-md-12">
                                <textarea id="majorAchiInput" name="majorAchiInput"
                                    class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks8" name="user_remarks8" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.major_achievements_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="major_achievements_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

<!-- -----------------------------------Strength of the Unit--------------------------- -->
    <div class="modal fade" id="StrengthOfTheUnit" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
          <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#major_achievements_item')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Strength of the Unit</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#Challenges')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                    <div class="modal-body">
                        <div class="col-md-12" id="getSearch_Letter"
                            style="display: block;">
                                <div class="watermarked" data-watermark="" id="divwatermark">
                                    <div class="col-md-12">
                                    <span id="ip"></span>
                                    The issues included shall dwell on following aspects :-<br><br>
                                     <label class="col-form-label">
                                        (a) Operations. <br>
                                        (b) Training <br>
                                        (c) Administration <br>
                                        (d) Sports and Miscellaneous. </label>
                                </div>
                                    <div class="col-md-12">
                                        <textarea id="strengthUnitInput" name="strengthUnitInput" class="form-control" rows="3"></textarea>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks9" name="user_remarks9" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.strength_of_unit_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="strength_of_unit_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

<!-- -----------------------------------Challenges--------------------------- -->
    <div class="modal fade" id="Challenges" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#StrengthOfTheUnit')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Challenges</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#improveFollowing')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                    <div class="modal-body">
                    <div class="col-md-12" id="getSearch_Letter"
                        style="display: block;">
                            <div class="watermarked" data-watermark="" id="divwatermark">
                                <div class="col-md-12">
                                    <span id="ip"></span>
                                    The issues included shall dwell on following aspects :-<br><br>
                                     <label class="col-form-label">
                                        (a) Operational shortcomings with respect to manpower, weapons, ammunition, vehicles and equipment.<br> 
                                        (b) Training with respect to training infrastructure, training area, equipment and training aids. <br>
                                        (c) Administration <br>
                                        (d) Sports with respect to sports field and sports equipment
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="challInput" name="challInput" class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                    </div>
                </div>
                    <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks10" name="user_remarks10" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.challenges_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="challenges_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- -----------------------------------Recommendations / Innovations to improve the following--------------------------- -->
    <div class="modal fade" id="improveFollowing" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">

                <button class="btn btn-sm"
                        onclick="openModal('#Challenges')" id="previous">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Recommendations / Innovations to improve the following</h5>
                <button class="btn btn-sm" id="next"
                        onclick="openModal('#AttentionOfHigherHQ')">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                <div class="modal-body">
                    <div class="col-md-12" id="getSearch_Letter"
                        style="display: block;">
                            <div class="watermarked" data-watermark="" id="divwatermark">
                                <div class="col-md-12">
                                    <span id="ip"></span>
                                    The issues included shall dwell on following aspects :-<br><br>
                                     <label class="col-form-label">
                                        (a) Training<br> 
                                        (b) Administration <br> 
                                        (c) Infrastructure <br> 
                                        (d) Welfare and Quality of Life <br>
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="inpfollInput" name="inpfollInput" class="form-control" rows="3"></textarea>
                                </div>
                            </div>

                    </div>
                </div>
                <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks11" name="user_remarks11" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.improve_following_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="improve_following_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- -----------------------------------Issues Requiring Attention of Higher HQ--------------------------- -->
    <div class="modal fade" id="AttentionOfHigherHQ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn btn-sm" onclick="openModal('#improveFollowing')" id="previous">
                        <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                    </button>
                    <h5 class="modal-title" id="exampleModalLabel">Issues Requiring Attention of Higher HQ</h5>
                    <button class="btn btn-sm" id="next" onclick="openModal('#MitigationActionSummary')">
                        Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                    </button>
    
                    <button type="button" class="close btn-close" onclick="closeModal()">
                        <span aria-hidden="true">&times;</span>
                    </button>
    
                </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                    <div class="modal-body">
                        <div class="col-md-12" id="getSearch_Letter"
                            style="display: block;">
                                <div class="watermarked" data-watermark="" id="divwatermark">
                                <div class="col-md-12">
                                    <span id="ip"></span>
                                    The issues included shall dwell on following aspects :-<br><br>
                                     <label class="col-form-label">
                                        (a)    Operational efficiency        <br>                  
                                        (b)    Training        <br>                          
                                        (c)    Administration        <br>                          
                                        (d)    Miscellaneous    <br>    
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="higherInput" name="higherInput" class="form-control" rows="3"></textarea>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks12" name="user_remarks12" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.attention_of_higher_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="attention_of_higher_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- -----------------------------------Mitigation Action Summary--------------------------- -->
    <div class="modal fade" id="MitigationActionSummary" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn btn-sm" onclick="openModal('#AttentionOfHigherHQ')" id="previous">
                        <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                    </button>
                    <h5 class="modal-title" id="exampleModalLabel">Mitigation Action Summary</h5>
                    <button class="btn btn-sm" id="next" onclick="openModal('#PointsForDiscussion')">
                        Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                    </button>
    
                    <button type="button" class="close btn-close" onclick="closeModal()">
                        <span aria-hidden="true">&times;</span>
                    </button>
    
                </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                    <div class="modal-body">
                        <div class="col-md-12" id="getSearch_Letter"
                            style="display: block;">
                            <div class="watermarked" data-watermark="" id="divwatermark">
                                <div class="col-md-12">
                                    <span id="ip"></span> 
                                    <label class="col-form-label">
                                    I have taken following actions to mitigate the observations of the Inspection Team highlighted in Extracts of Inspection Reports as per Annexure to Part I of Appendix 'F'.<br></label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="actionInput" name="actionInput" class="form-control" rows="3"></textarea>
                                </div>
                            
                                
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks13" name="user_remarks13" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.mitigation_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="mitigation_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- -----------------------------------Points for Discussion--------------------------- -->
    <div class="modal fade" id="PointsForDiscussion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn btn-sm" onclick="openModal('#MitigationActionSummary')" id="previous">
                        <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                    </button>
                    <h5 class="modal-title" id="exampleModalLabel">Points for Discussion</h5>
    
                    <button type="button" class="close btn-close" onclick="closeModal()">
                        <span aria-hidden="true">&times;</span>
                    </button>
    
                </div>
                <form id="MajorAchievementsForm" method="POST">
                    <!-- Added ID to the form -->
                    <div class="modal-body">
                        <div class="col-md-12" id="getSearch_Letter"
                            style="display: block;">
                            <div class="watermarked" data-watermark="" id="divwatermark">
                                <span id="ip"></span>

                            <div class="col-md-12">
                                    <span id="ip"></span> <label class="col-form-label">
                                        Points for Discussion
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="pointsInput" name="pointsInput" class="form-control" rows="3"></textarea>
                                </div>                                    
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please do not use Special Character like ( , ), `, \, ', ;, |, ", :, <, >, ?.</p>
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks14" name="user_remarks14" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.points_discussion_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="points_discussion_item">Submit for Approval</button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


<c:url value="Download_Form_part2" var="downloadUrl" /> 
<form:form action="${downloadUrl}" method="post" id="downloadForm"
	name="downloadForm" modelAttribute="cont_comd_tx">
</form:form>

<script type="text/javascript">

$(document).ready(function() {
	  try{
		   if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
		}
		catch (e) {
			
		}
		
	   $(document).on('mousedown', function(event) {
	        if ($('.modal.show').length > 0) {
	            var modalContent = $('.modal.show .modal-content');
	            if (!modalContent.is(event.target) && modalContent.has(event.target).length === 0) {
	                closeModal();
	            }
	        }
	    });
	});
	
function openModal(modalId) {
	
	
	//GET DATA
	
	if (modalId == '#OpPreparedness') {
		GetData('op_url', modalId);
	}
	
	if (modalId == '#Training') {
		GetData('training_url', modalId);
	}
	
	if (modalId == '#StateWeapEqpt') {		
		GetData('weap_url', modalId)
		searchupGradation("stateWeapons");
	}
	
	if (modalId == '#StatePersonnel') {
		GetData('pers_url', modalId);
		searchupGradation("statepersonnel");
		searchupGradation("statepersonnel1");
	}
	
	if (modalId == '#Administration') {
		GetData('admin_url', modalId);
	}
	
	if (modalId == '#AspectMorale') {
		GetData('aspect_url', modalId);
	}
	
	if (modalId == '#InteriorEconomy') {
		GetData('inte_url', modalId);
	}
	
	if (modalId == '#MajorAchievements') {
		GetData('major_url', modalId);
	}
	
	if (modalId == '#StrengthOfTheUnit') {
		GetData('strength_url', modalId);
	}
	
	if (modalId == '#Challenges') {
		GetData('challenge_url', modalId);
	}
	
	if (modalId == '#improveFollowing') {
		GetData('improve_url', modalId);
	}
	
	if (modalId == '#AttentionOfHigherHQ') {
		GetData('attention_url', modalId);
	}
	
	if (modalId == '#MitigationActionSummary') {
		GetData('mitigation_url', modalId);
	}
	
	if (modalId == '#PointsForDiscussion') {
		GetData('points_url', modalId);
	}

	var modals = document.querySelectorAll('.modal.show');
	modals.forEach(function(modal) {
		modal.classList.remove('show');
		modal.style.display = 'none';
	});
	var modalToOpen = document.querySelector(modalId);
	if (modalToOpen) {
		modalToOpen.classList.add('show');
		modalToOpen.style.display = 'block';
	}

}
	
	function closeModal() {
        var modals = document.querySelectorAll('.modal.show');
        modals.forEach(function(modal) {
            modal.classList.remove('show');
            modal.style.display = 'none';
        });
    }
	
	
function downloadData(id){
	 $("#downloadid").val(id);
	document.getElementById('downloadForm').submit();
}


	
function set_data_op(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#op').val(rowData.op_preparedness_item || '');
    }
}

function set_data_training(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#training').val(rowData.training_item || '');
    }
}

function set_data_weap(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#weaponb').val(rowData.state_weapon_item || '');
        $('#weaponc').val(rowData.state_weapon_item_ii || '');
    }
}

function set_data_pers(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#personnelInput').val(rowData.state_personnel_item || '');
    }
}

function set_data_admin(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#administration').val(rowData.administation_item || '');
    }
}

function set_data_aspect(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#aspect').val(rowData.aspect_item || '');
    }
}

function set_data_interior(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#interior').val(rowData.interior_item || '');
    }
}

function set_data_major(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#majorAchiInput').val(rowData.major_achievements_item || '');
    }
}

function set_data_strength(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#strengthUnitInput').val(rowData.strength_of_unit_item || '');
    }
}

function set_data_challenge(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#challInput').val(rowData.challenges_item || '');
    }
}

function set_data_improve(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#inpfollInput').val(rowData.improve_following_item || '');
    }
}

function set_data_attention(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#higherInput').val(rowData.attention_of_higher_item || '');
    }
}

function set_data_mitigation(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#actionInput').val(rowData.mitigation_item || '');
    }
}

function set_data_points(data) {
    if (Array.isArray(data) && data.length > 0) {
        var rowData = data[0];      
        $('#pointsInput').val(rowData.points_discussion_item || '');
    }
}
	

function GetData(url, modalId) {
	var unit_no = "0";
	$("#nrWaitLoader").show();

	$.post(url + "?" + key + "=" + value, {
		unit_no : unit_no
	}, function(j) {
		
		if (modalId == '#OpPreparedness') {
			set_data_op(j);				
		}
		
		if (modalId == '#Training') {
			set_data_training(j);				
		}
		
		if (modalId == '#StateWeapEqpt') {
			set_data_weap(j);				
		}
		
		if (modalId == '#StatePersonnel') {
			set_data_pers(j);				
		}
		
		if (modalId == '#Administration') {
			set_data_admin(j);				
		}
		
		if (modalId == '#AspectMorale') {
			set_data_aspect(j);				
		}
		
		if (modalId == '#InteriorEconomy') {
			set_data_interior(j);				
		}
		
		if (modalId == '#MajorAchievements') {
			set_data_major(j);
		}
		
		if (modalId == '#StrengthOfTheUnit') {
			set_data_strength(j);
		}
		
		if (modalId == '#Challenges') {
			set_data_challenge(j);				
		}
		
		if (modalId == '#improveFollowing') {
			set_data_improve(j);				
		}
		
		if (modalId == '#AttentionOfHigherHQ') {
			set_data_attention(j);				
		}
		
		if (modalId == '#MitigationActionSummary') {
			set_data_mitigation(j);				
		}
		
		if (modalId == '#PointsForDiscussion') {
			set_data_points(j);				
		}
		
		if (modalId == '#StateWeapEqpt') {		
			searchupGradation("stateWeapons");
		}
		
		if (modalId == '#StatePersonnel') {		
			searchupGradation("statepersonnel");
			searchupGradation("statepersonnel1");
		}
	});
	$("#nrWaitLoader").hide();
}
	
$("#part1").click(function() {
    // Show profile tab and hide home tab
    $("#part1_div").show().removeClass('display_none').addClass('active');
    $("#part2_div ").hide().addClass('display_none').removeClass('active');

    // Update tab states
    $("#part1").addClass('active');
    $("#part2").removeClass('active');
   
});

$("#part2").click(function() {
    // Show home tab and hide profile tab
    $("#part2_div").show().removeClass('display_none').addClass('active');
    $("#part1_div").hide().addClass('display_none').removeClass('active');

    // Update tab states
    $("#part2").addClass('active');
    $("#part1").removeClass('active');
});

	
  $(document).on("click", ".add-to-recommended", function () {
	    var $row = $(this).closest("tr");	
	    var $button = $(this);
	    var buttonContext = $button.data("context");		  
	    var formData={};
	    var Url = "";	 
	    
	    if(buttonContext == "stateWeapons"){
			    	var natureDeficiency = $row.find("input[name='natureDeficiency']").val();
				    if (natureDeficiency === "") {
				        alert("Please Enter natureDeficiency");
				        return;
				    }			    
				   
				    formData = {
				        natureDeficiency: natureDeficiency, 
				        actionDeficiency: $row.find("input[name='actionDeficiency']").val(),
				        effectConduct: $row.find("input[name='effectConduct']").val(),
				        remarksSW: $row.find("input[name='remarksSW']").val()				    		
				    };
				    
			    } 
	    
	    if(buttonContext == "statepersonnel"){
	    	var manpower_deficiencyoffrs = $row.find("input[name='manpower_deficiencyoffrs']").val();
		    if (manpower_deficiencyoffrs === "") {
		        alert("Please Enter Manpower Deficiency Officers");
		        return;
		    }	   
		   
		    var manpower_deficiencyjco = $row.find("input[name='manpower_deficiencyjco']").val();
		    if (manpower_deficiencyjco === "") {
		        alert("Please Enter Manpower Deficiency Jco");
		        return;
		    }	
		    
		    var manpower_deficiencyor = $row.find("input[name='manpower_deficiencyor']").val();
		    if (manpower_deficiencyor === "") {
		        alert("Please Enter Manpower Deficiency Or");
		        return;
		    }	
		    
		    formData = {
		    		manpower_deficiencyoffrs: manpower_deficiencyoffrs, 
		    		manpower_deficiencyjco: manpower_deficiencyjco,
		    		manpower_deficiencyor: manpower_deficiencyor,
		    		action_taken: $row.find("input[name='action_taken']").val(),
		    		training_effect: $row.find("input[name='training_effect']").val(),
		    		remarkssp: $row.find("input[name='remarkssp']").val()				    		
		    };
		    
	    } 
	    
	    if(buttonContext == "statepersonnel1"){
	    	var ongoingCount = $row.find("input[name='ongoingCount']").val();
		    if (ongoingCount === "") {
		        alert("Please Enter Ongoing Court Martial Cases in the unit/ Formation HQ including very Old Cases");
		        return;
		    }				   
		   
		    
		    formData = {
		    		ongoingCount: ongoingCount, 			    	
		    		pendingCases: $row.find("input[name='pendingCases']").val(),
		    		casesCurrent: $row.find("input[name='casesCurrent']").val(),
		    		remarksSW1: $row.find("input[name='remarksSW1']").val()				    		
		    };
		    
	    } 
	    
	    formData.buttonContext = buttonContext;	

	    $.ajax({
	        type: "POST",
	        url: "reportsAddMoreActionPhaseii",
	        data: formData,
	        dataType: "json",
	        headers: {
	            'X-CSRF-TOKEN': '${_csrf.token}'
	        },
	        success: function (response) {
	            if (response.success) {
	              //  alert("Successfully Added Data");		      
	             searchupGradation(buttonContext);
	            $row.find("input[type='text']").val("");
	            $row.find("input[type='date']").val("");
	             
	            } 
	        },
	        error: function (xhr, status, error) {
	            /* console.error("AJAX error adding to recommended list:", status, error, xhr.responseText); */
	        }
	    });
	});
  
  
//TABLE DELETE BUTTON
  $(document).on("click", ".delete-from-recommended", function () {
	    var id = $(this).data("id");
	    var $button = $(this);
	    var buttonContext = $button.data("context");
	
	    
	    if (confirm("Are you sure you want to delete this record?")) {
	        $.ajax({
	            type: "POST",
	            url: "deleteTbleRowphaseiiAction",
	            data: {
	                id: id,
	                tblName:buttonContext
	            },
	            dataType: "json",
	            headers: {
	                'X-CSRF-TOKEN': '${_csrf.token}'
	            },
	            success: function (response) {
	                if (response.success) {                           
	                    alert("Record deleted successfully!"); 		                  
	                     searchupGradation(buttonContext); 		              
	                } else {		      
	                	alert("Record deleted successfully!");
	                }
	            },
	            error: function (xhr, status, error) {
	                console.error("AJAX error deleting record:", status, error, xhr.responseText);
	                let errorMessage = "An unexpected error occurred during deletion."; 

	                try {
	                  const response = JSON.parse(xhr.responseText);
	                  if (response && response.message) {
	                      errorMessage = "Error: " + response.message; 
	                  }
	                } catch (e) {
	                    console.warn("Could not parse error response:", xhr.responseText, e);
	                }

	                alert(errorMessage);	
	            }
	        });
	    }
	});

// TABLE DATA SET
	  function searchupGradation(tblname) {
          var sus_no="";	  
		    var $tbody; 
		    var $firstRow;
		    
		    if (tblname=="stateWeapons" || tblname=="stateWeaponsDelete"){
        		$tbody = $("#SW_tbody");
        		 $firstRow = $tbody.find("#firstRow"); 
        	}
		    
		    if (tblname=="statepersonnel" || tblname=="statepersonnelDelete"){
        		$tbody = $("#SWp_tbody");
        		 $firstRow = $tbody.find("#firstRow"); 
        	}
		    if (tblname=="statepersonnel1" || tblname=="statepersonnel1Delete"){
        		$tbody = $("#statepersonnel1_tbody");
        		 $firstRow = $tbody.find("#firstRow"); 
        	}

		    $.ajax({
		        type: "GET",
		        url: "getTbleAddDataphaseii",
		        data: {
		            sus_no: sus_no,
		            tblname:tblname
		        },
		        dataType: "json",
		        headers: {
		            'X-CSRF-TOKEN': '${_csrf.token}'
		        },
		        success: function (response) {
		            console.log("AJAX Response:", response);

		            if (response.success) {
		                var data = response.data;
		                if (data && data.length > 0) {
		                    $tbody.empty(); 
		                    $tbody.append($firstRow); 
		                    if (tblname=="stateWeapons" || tblname=="stateWeaponsDelete"){
		                    	$.each(data, function (index, item) {
			                   		 var deleteButtonHtml = '${report.state_weapon_item}' != 1 ? "<td> <button type='button' class='fa fa-trash btn btn-danger btn-sm delete-from-recommended' data-context='stateWeaponsDelete'  data-id='" + item.id + "'></button></td>":"";
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.nature_deficiency + "</td>" +
			                   			"<td>" + item.action_deficiency + "</td>" +
			                   			"<td>" + item.effect_conduct + "</td>" +    	
			                   			"<td>" + item.remarks + "</td>" + deleteButtonHtml +
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                	});
							}
		                    
		                    if (tblname=="statepersonnel" || tblname=="statepersonnelDelete"){
		                    	$.each(data, function (index, item) {
			                   		 var deleteButtonHtml = '${report.state_personnel_item}' != 1 ? "<td> <button type='button' class='fa fa-trash btn btn-danger btn-sm delete-from-recommended' data-context='statepersonnelDelete'  data-id='" + item.id + "'></button></td>":"";
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.manpower_deficiencyoffrs + "</td>" +
			                   			"<td>" + item.manpower_deficiencyjco + "</td>" +
			                   			"<td>" + item.manpower_deficiencyor + "</td>" +    
			                   			"<td>" + item.action_taken + "</td>" +
			                   			"<td>" + item.training_effect + "</td>" +    
			                   			"<td>" + item.remarks + "</td>" + deleteButtonHtml +
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                   	});
							}
		                    
		                    if (tblname=="statepersonnel1" || tblname=="statepersonnel1Delete"){
		                    	$.each(data, function (index, item) {
			                   		 var deleteButtonHtml = '${report.state_personnel_item}' != 1 ? "<td> <button type='button' class='fa fa-trash btn btn-danger btn-sm delete-from-recommended' data-context='statepersonnel1Delete'  data-id='" + item.id + "'></button></td>"
			                   				 : "";
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.ongoing_count + "</td>" +
			                   			"<td>" + item.pending_cases + "</td>" +
			                   			"<td>" + item.cases_current + "</td>" +  		                  
			                   			"<td>" + item.remarks + "</td>" + deleteButtonHtml +
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                   	});
							}
		                }
		            } else {
		                console.error("Error fetching recommended data: " + response.message);
		                $tbody.empty(); 
	                    $tbody.append($firstRow); 
		            }
		        },
		        error: function (xhr, status, error) {
		            console.error("AJAX error fetching recommended data:", status, error, xhr.responseText);
		            $tbody.empty(); 
                   $tbody.append($firstRow); 
		        }
		    });
		}
	  
	  

// SAVE
$(document).on("click", ".add-to-submit_approve", function () {
    var $button = $(this);
    var buttonContext = $button.data("context");
    var formData = new FormData(); 

    formData.append("buttonContext", buttonContext); 

    if (buttonContext === "op_preparedness_item") {
        var op = $("#op").val();
        var user_remarks = $("#user_remarks1").val();        
        formData.append("op", op);
        formData.append("user_remarks1", user_remarks);
        
    } else if (buttonContext === "training_item"){
    	var training = $("#training").val();
        var user_remarks = $("#user_remarks2").val();
        formData.append("training", training);
        formData.append("user_remarks2", user_remarks);
        
    } else if (buttonContext === "state_weapon_item"){
    	var weaponbInput = $("#weaponb").val();
    	var weaponcInput = $("#weaponc").val();
        var user_remarks = $("#user_remarks3").val();
        formData.append("weaponbInput", weaponbInput);
        formData.append("weaponcInput", weaponcInput);
        formData.append("user_remarks3", user_remarks);
        
    } else if (buttonContext === "state_personnel_item"){
    	var personnelInput = $("#personnelInput").val();
    	var user_remarks = $("#user_remarks4").val();
    	formData.append("personnelInput", personnelInput);
    	formData.append("user_remarks4", user_remarks);
    	
    } else if (buttonContext === "administation_item"){
    	var administration = $("#administration").val();
        var user_remarks = $("#user_remarks5").val();
        formData.append("administration", administration);
        formData.append("user_remarks5", user_remarks);
        
    } else if (buttonContext === "aspect_item"){
    	var aspect = $("#aspect").val();
        var user_remarks = $("#user_remarks6").val();
        formData.append("aspect", aspect);
        formData.append("user_remarks6", user_remarks);
        
    } else if (buttonContext === "interior_item"){
    	var interior = $("#interior").val();
        var user_remarks = $("#user_remarks7").val();
        formData.append("interior", interior);
        formData.append("user_remarks7", user_remarks);

    } else if (buttonContext === "major_achievements_item"){
    	var majorAchiInput = $("#majorAchiInput").val();
        var user_remarks = $("#user_remarks8").val();
        formData.append("majorAchiInput", majorAchiInput);
        formData.append("user_remarks8", user_remarks);

    } else if (buttonContext === "strength_of_unit_item"){
    	var strengthUnitInput = $("#strengthUnitInput").val();
        var user_remarks = $("#user_remarks9").val();
        formData.append("strengthUnitInput", strengthUnitInput);
        formData.append("user_remarks9", user_remarks);

    } else if (buttonContext === "challenges_item"){
    	var challInput = $("#challInput").val();
        var user_remarks = $("#user_remarks10").val();
        formData.append("challInput", challInput);
        formData.append("user_remarks10", user_remarks);

    } else if (buttonContext === "improve_following_item"){
    	var inpfollInput = $("#inpfollInput").val();
        var user_remarks = $("#user_remarks11").val();
        formData.append("inpfollInput", inpfollInput);
        formData.append("user_remarks11", user_remarks);

    } else if (buttonContext === "attention_of_higher_item"){
    	var higherInput = $("#higherInput").val();
        var user_remarks = $("#user_remarks12").val();
        formData.append("higherInput", higherInput);
        formData.append("user_remarks12", user_remarks);

    } else if (buttonContext === "mitigation_item"){
    	var actionInput = $("#actionInput").val();
        var user_remarks = $("#user_remarks13").val();
        formData.append("actionInput", actionInput);
        formData.append("user_remarks13", user_remarks);

    } else if (buttonContext === "points_discussion_item"){
    	var pointsInput = $("#pointsInput").val();
        var user_remarks = $("#user_remarks14").val();
        formData.append("pointsInput", pointsInput);
        formData.append("user_remarks14", user_remarks);
    }
    
    
    $.ajax({
        type: "POST",
        url: "formDataPhaseIISaveAction",
        data: formData,
        dataType: "json",
        processData: false, 
        contentType: false,
        headers: {
            'X-CSRF-TOKEN': '${_csrf.token}'
        },
        success: function (response) {
            if (response.success) {
            	alert(response.message);
            	window.location.reload();
            } else {
                alert("Data Not Saved Successfully");
            }
        },
        error: function (xhr, status, error) {
            console.error("AJAX error saving data:", status, error, xhr.responseText);
            alert("An error occurred while saving data. Please try again.");
        }
    });
});


function phase2pdf() {
	document.getElementById('downloadForm').submit();
}

	
</script>

<!-- Need to be removed--> 
<Script>
	  




function radioch(a){
	debugger;
	if(a==1)
		{
		 document.getElementById('Para1Form').submit();
		}
	if(a==2)
	{
	 document.getElementById('Para2Form').submit();
	}

		 
	}
	  </Script>
	  
	  
	  	<c:url value="inspection_report" var="searchUrl" />
		<form:form action="${searchUrl}" method="get" id="Para1Form" name="Para1Form" >
		</form:form>
		<c:url value="inspection_report_2" var="searchUrl" />
		<form:form action="${searchUrl}" method="get" id="Para2Form" name="Para2Form" >
		</form:form>