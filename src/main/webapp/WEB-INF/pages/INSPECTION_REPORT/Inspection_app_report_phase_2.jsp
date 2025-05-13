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
  	 font-size: &times;-large;
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
									<button class="nav-link " id="part1"
										data-bs-toggle="tab" data-bs-target="#part1_div" type="button" onclick="radioch(1)"
										role="tab" aria-controls="home" aria-selected="true">Part I</button>
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
			<c:if test="${report.op_preparedness_item == 0 || report.op_preparedness_item == 1}">
				<div class="ins_item clr1" id="op_preparedness_item" onclick="openModal('#OpPreparedness')">Op Preparedness</div>
			</c:if>
			<c:if test="${report.training_item == 0 || report.training_item == 1}">
				<div class="ins_item clr2" id="training_item" onclick="openModal('#Training')">Training</div>
			</c:if>
			<c:if test="${report.state_weapon_item == 0 || report.state_weapon_item == 1}">
				<div class="ins_item clr3" id="state_weapon_item" onclick="openModal('#StateWeapEqpt')">State of Weapons/ Equipment</div>
			</c:if>
			<c:if test="${report.state_personnel_item == 0 || report.state_personnel_item == 1}">
				<div class="ins_item clr1" id="state_personnel_item" onclick="openModal('#StatePersonnel')">State of Personnel</div>
			</c:if>
			<c:if test="${report.administation_item == 0 || report.administation_item == 1}">
				<div class="ins_item clr2" id="administation_item" onclick="openModal('#Administration')">Administration</div>
			</c:if>
			<c:if test="${report.aspect_item == 0 || report.aspect_item == 1}">
				<div class="ins_item clr3" id="aspect_item" onclick="openModal('#AspectMorale')">Aspects Affecting Morale and Motivation</div>
			</c:if>
			<c:if test="${report.interior_item == 0 || report.interior_item == 1}">
				<div class="ins_item clr1" id="interior_item" onclick="openModal('#InteriorEconomy')">Interior Economy</div>
			</c:if>
			<c:if test="${report.major_achievements_item == 0 || report.major_achievements_item == 1}">
				 <div class="ins_item clr8" id="major_achievements_item" onclick="openModal('#MajorAchievements')">Major Achievements</div>
			</c:if>
			<c:if test="${report.strength_of_unit_item == 0 || report.strength_of_unit_item == 1}">
		         <div class="ins_item clr9" id="strength_of_unit_item" onclick="openModal('#StrengthOfTheUnit')">Strength of the Unit</div>
			</c:if>
			<c:if test="${report.challenges_item == 0 || report.challenges_item == 1}">
		         <div class="ins_item clr10" id="challenges_item" onclick="openModal('#Challenges')">Challenges</div>
			</c:if>
			<c:if test="${report.improve_following_item == 0 || report.improve_following_item == 1}">
		         <div class="ins_item clr1" id="improve_following_item" onclick="openModal('#improveFollowing')">Recommendations / Innovations to improve the following</div>
			</c:if>
			<c:if test="${report.attention_of_higher_item == 0 || report.attention_of_higher_item == 1}">
		         <div class="ins_item clr2" id="attention_of_higher_item" onclick="openModal('#AttentionOfHigherHQ')">Issues Requiring Attention of Higher HQ</div>
			</c:if>
			<c:if test="${report.mitigation_item == 0 || report.mitigation_item == 1}">
		         <div class="ins_item clr3" id="mitigation_item" onclick="openModal('#MitigationActionSummary')">Mitigation Action Summary</div>
			</c:if>
			<c:if test="${report.points_discussion_item == 0 || report.points_discussion_item == 1}">
		         <div class="ins_item clr4" id="points_discussion_item" onclick="openModal('#PointsForDiscussion')">Points for Discussion</div>
			</c:if>
		
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

<div class="modal fade" id="OpPreparedness" tabindex="-1" role="dialog"
     aria-labelledby="operationalPreparednessLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title" id="operationalPreparednessLabel">Operational Preparedness</h5>
                <button class="btn btn-sm nex">
                    Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
                </button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <form:form id="opForm" name="opForm" action="OpFormAction" method="post" commandName="OpFormCmd">
                <div class="modal-body">
                    <div class="col-md-12" id="opContent" style="display: block;">
                        <div class="watermarked" data-watermark="" id="divwatermarkOp">
                            <span id="ip"></span>
							
							<div class="col-md-12">
                                <span id="ip"></span> <label class="col-form-label">
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
								<textarea class="form-control" id="op" name="op" rows="3" disabled></textarea>
							</div>
							
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                        <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                        <div class="form-group">
                            <label for="remarks"><strong>Remarks: </strong></label>
                            <textarea class="form-control" id="user_remarks1" name="user_remarks1" rows="2"></textarea>
                        </div>

                        <div class="form-group" style="display:none">
                            <label for="op_screenshot"><strong>Screenshot (Optional):</strong></label>
                            <input type="file" class="form-control-file" id="op_screenshot" name="op_screenshot">
                            <small class="form-text text-muted">Please upload a screenshot to illustrate your suggestions (optional). Max file size: [Insert Maximum File Size Here, e.g., 2MB]</small>
                        </div>
                    </div>
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
                        
                        <c:if test="${report.op_preparedness_item == 0}">
							<button type="button"
								class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="op_preparedness_item">Approve</button>
						</c:if>

						<c:if test="${report.op_preparedness_item == 1}">
							<label class="form-control-label"><strong
								style="color: red;">Data Approved </strong> </label>
						</c:if>
                        
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="modal fade" id="Training" tabindex="-1" role="dialog"
     aria-labelledby="trainingLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Training</h5>
                <button class="btn btn-sm nex">
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
								<textarea class="form-control" id="training" name="training" rows="3" disabled></textarea>
							</div>
							
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
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
                            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" data-context="training_item">Approve</button>
                        </c:if>
                        
                        <c:if test="${report.training_item == 1}">
							<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">State of Weapons/ Equipment</h5>
                <button class="btn btn-sm nex">
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
								</tr>
								<tr>
									<th style="text-align: center;">(a)</th>
									<th style="text-align: center;">(b)</th>
									<th style="text-align: center;">(c)</th>
									<th style="text-align: center;">(d)</th>
									<th style="text-align: center;">(e)</th>
								</tr>
							</thead>
							
							<tbody style="font-size: 12px;" id="SW_tbody">
								<tr id="firstRow">
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-md-12">                                
						<div class="form-group">
							 <strong>(b)</strong> Adequacy of administrative support provided to facilitate training and administration in the unit </strong></label>
							<textarea class="form-control" id="weaponb" name="weaponb" rows="3" disabled></textarea>
						</div>
						              <div class="form-group">
							 <strong>(c)</strong> Adequacy of technical maintenance support and availability of spares in respect of arms, weapons, major equipment and transport affecting operational efficiency.</strong></label>
							<textarea class="form-control" id="weaponc" name="weaponc" rows="3" disabled></textarea>
						</div>
					</div>
				</div>
			</div>
		<div class="modal-footer">
		<div class="col-6">
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
			       		<button type="button" class="btn btn-primary btn-sm add-to-submit_approve"  data-context="state_weapon_item">Approve</button>
			       </c:if>
			       
			       <c:if test="${report.state_weapon_item == 1}">
						<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
					</c:if>
			       
				</div>	
			</div>
		</div>
	</div>
</div>





<div class="modal fade" id="StatePersonnel" tabindex="-1"
    role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">State of Personnel</h5>
                <button class="btn btn-sm nex">
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
                                </tr>
                            </thead>
                            
                            <tbody style="font-size: 12px;" id="SWp_tbody">
                                <tr id="firstRow">   
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
								</tr>
								<tr>
                                    <th style="text-align: center;">(a)</th>
                                    <th style="text-align: center;">(b)</th>
                                    <th style="text-align: center;">(c)</th>
                                    <th style="text-align: center;">(d)</th>
                                    <th style="text-align: center;">(e)</th>
                                </tr>
                            </thead>
                            
                            <tbody style="font-size: 12px;" id="statepersonnel1_tbody">
                                <tr id="firstRow">
                                </tr>
                            </tbody>
                        </table>
					</div>
					
					<div class="col-md-12">                                
						<div class="form-group">
							(c) Availability of Officers in the Unit. Deficiency of officers/ profile etc. affecting operational preparedness of the Unit/ Formation HQ/ Establishment be highlighted</strong></label>
							<textarea class="form-control" id="personnelInput" name="personnelInput" rows="3" disabled></textarea>
						</div>
					</div>
					
				</div>

                
                
                
            </div>
        <div class="modal-footer">
        <div class="col-6">
                    <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                    <div class="form-group">
                        <label for="remarks"><strong>Remarks: </strong></label>
                        <textarea class="form-control" id="user_remarks2" name="user_remarks2" rows="3"></textarea>
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
						<button type="button" class="btn btn-primary btn-sm add-to-submit_approve"   data-context="state_personnel_item">Approve</button>
					</c:if>
					
					<c:if test="${report.state_personnel_item == 1}">
						<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
					</c:if>
					
            	</div>
            </div>
        </div>
    </div>
</div>






<div class="modal fade" id="Administration" tabindex="-1" role="dialog"
     aria-labelledby="administrationLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Administration</h5>
                <button class="btn btn-sm nex">
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
								<span id="ip"></span> <label class="col-form-label">
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
								<textarea class="form-control" id="administration" name="administration" rows="3" disabled></textarea>
							</div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
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
                                Approve</button>
                        </c:if>
                        
                        <c:if test="${report.administation_item == 1}">
							<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
						</c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- 							Aspects Affecting Morale and Motivation -->

<div class="modal fade" id="AspectMorale" tabindex="-1" role="dialog"
     aria-labelledby="aspectLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Aspects Affecting Morale and Motivation</h5>
                <button class="btn btn-sm nex">
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
								<span id="ip"></span> <label class="col-form-label">
							       <strong>(a)</strong> Number of untoward incidents with details<br>
							       <strong>(b)</strong> Number of Mechanical Transport accidents<br>
							       <strong>(c)</strong> ACC/ SL/ RCO commission.<br>
							       <strong>(d)</strong> Measures taken to improve level of morale and motivation of personnel <br>
							       <strong>(e)</strong> Measures to ensure adherence to and imbibing of Indian Army Core Values by all ranks  <br>
							       <strong>(f)</strong> Measures taken for resolution of problems of individual soldiers   <br>
							   </label>
							</div>
							
							<div class="col-md-12">
								<textarea class="form-control" id="aspect" name="aspect" rows="3" disabled></textarea>
							</div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
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
                                Approve</button>
                        </c:if>
                        
                        <c:if test="${report.aspect_item == 1}">
							<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Interior Economy</h5>
                <button class="btn btn-sm nex">
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
								<textarea class="form-control" id="interior" name="interior" rows="3" disabled></textarea>
							</div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
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
                                Approve</button>
                        </c:if>
                        
                        <c:if test="${report.interior_item == 1}">
							<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Major Achievements</h5>
                <button class="btn btn-sm nex">
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
                                <span id="ip"></span> <label class="col-form-label"> 
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
                                <textarea id="majorAchiInput" name="majorAchiInput" class="form-control" rows="3" disabled></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks8" name="user_remarks8" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.major_achievements_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="major_achievements_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.major_achievements_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Strength of the Unit</h5>
                <button class="btn btn-sm nex">
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
                                    <span id="ip"></span> <label class="col-form-label">
                                        (a) Operations. <br>
                                        (b) Training <br>
                                        (c) Administration <br>
                                        (d) Sports and Miscellaneous. </label>
                                </div>
                                    <div class="col-md-12">
                                        <textarea id="strengthUnitInput" name="strengthUnitInput" class="form-control" rows="3" disabled></textarea>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks9" name="user_remarks9" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.strength_of_unit_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="strength_of_unit_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.strength_of_unit_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Challenges</h5>
                <button class="btn btn-sm nex">
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
                                    <span id="ip"></span> <label class="col-form-label">
                                        (a) Operational shortcomings with respect to manpower, weapons, ammunition, vehicles and equipment.<br> 
                                        (b) Training with respect to training infrastructure, training area, equipment and training aids. <br>
                                        (c) Administration <br>
                                        (d) Sports with respect to sports field and sports equipment
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="challInput" name="challInput" class="form-control" rows="3" disabled></textarea>
                                </div>
                            </div>
                    </div>
                </div>
                    <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks10" name="user_remarks10" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.challenges_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="challenges_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.challenges_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

                <button class="btn btn-sm prev">
                    <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                </button>
                <h5 class="modal-title" id="trainingLabel">Recommendations / Innovations to improve the following</h5>
                <button class="btn btn-sm nex">
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
                                    <span id="ip"></span> <label class="col-form-label">
                                        (a) Training<br> 
                                        (b) Administration <br> 
                                        (c) Infrastructure <br> 
                                        (d) Welfare and Quality of Life <br>
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="inpfollInput" name="inpfollInput" class="form-control" rows="3" disabled></textarea>
                                </div>
                            </div>

                    </div>
                </div>
                <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks11" name="user_remarks11" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.improve_following_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="improve_following_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.improve_following_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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
                    <button class="btn btn-sm prev">
                        <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                    </button>
                    <h5 class="modal-title" id="exampleModalLabel">Issues Requiring Attention of Higher HQ</h5>
                    <button class="btn btn-sm nex">
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
                                    <span id="ip"></span> <label class="col-form-label">
                                        (a)    Operational efficiency        <br>                  
                                        (b)    Training        <br>                          
                                        (c)    Administration        <br>                          
                                        (d)    Miscellaneous    <br>    
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="higherInput" name="higherInput" class="form-control" rows="3" disabled></textarea>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks12" name="user_remarks12" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.attention_of_higher_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="attention_of_higher_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.attention_of_higher_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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
                    <button class="btn btn-sm prev">
                        <span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
                    </button>
                    <h5 class="modal-title" id="exampleModalLabel">Mitigation Action Summary</h5>
                    <button class="btn btn-sm nex">
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
                                    <span id="ip"></span> <label class="col-form-label">
                                        I have taken following actions to mitigate the observations of the Inspection Team highlighted in Extracts of Inspection Reports as per Annexure to Part I of Appendix 'F'.
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <textarea id="actionInput" name="actionInput" class="form-control" rows="3" disabled></textarea>
                                </div>
                            
                                
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks13" name="user_remarks13" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.mitigation_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="mitigation_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.mitigation_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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
                    <button class="btn btn-sm prev">
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
                                    <textarea id="pointsInput" name="pointsInput" class="form-control" rows="3" disabled></textarea>
                                </div>                                    
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-6">
                            <p style="color: red; font-weight: bold;">Please provide any suggestions or feedback regarding changes to this report:</p>
                            <div class="form-group">
                                <label for="remarks"><strong>Remarks: </strong></label>
                                <textarea class="form-control" id="user_remarks14" name="user_remarks14" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="col-6 text-right">
                            <button type="button" class="btn btn-secondary btn-sm"data-dismiss="modal" onclick="closeModal()">Close</button>
                            <c:if test="${report.points_discussion_item != 1}">
                                <button type="button"class="btn btn-primary btn-sm add-to-submit_approve" data-context="points_discussion_item">Approve</button>
                            </c:if>
                            
                            <c:if test="${report.points_discussion_item == 1}">
								<label class=" form-control-label"><strong style="color: red;">Data Approved</strong> </label>
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

$("#printGenrItAsset").click(function() {
    var iframe = document.getElementById('genrItAssetIframe');
    window.open("admin/inspection_reports").print();
});

$("#printFormate").click(function() {
    var iframe = document.getElementById('genrFormateIframe');
    window.open("admin/getPdfForInspReport").print();
   
});

$("#printFormate1").click(function() {
    var iframe = document.getElementById('genrFormateIframe1');
    window.open("admin/getPdfForInspReport").print();
    
});

$("#printFormate2").click(function() {
    var iframe = document.getElementById('genrFormateIframe2');
    window.open("admin/getPdfForInspReport").print();
});


$("#printFormate3").click(function() {
    var iframe = document.getElementById('genrFormateIframe3');
    window.open("admin/getPdfForInspReport").print();
});

$(document).ready(function() {
	doc_Path();
// 	courses_add_fn(1);	
	datepicketDate('when_used1');
	datepicketDate('whenusedffrs'); 
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
	
	
	function closeModal() {
        var modals = document.querySelectorAll('.modal.show');
        modals.forEach(function(modal) {
            modal.classList.remove('show');
            modal.style.display = 'none';
        });
    }
	
	
function phase2pdf() {
	document.getElementById('downloadForm').submit();
}

//GET DATA

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
			
			//TODO
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
	
			
		});
		$("#nrWaitLoader").hide();
	}
	

	function openModal(modalId) {
		
		if (modalId == '#StateWeapEqpt') {		
			GetData('weap_url', modalId)
			searchupGradation("stateWeapons");
		}
		
		if (modalId == '#StatePersonnel') {
			GetData('pers_url', modalId);
			searchupGradation("statepersonnel");
			searchupGradation("statepersonnel1");
		}
		
		//GET DATA
		
		if (modalId == '#OpPreparedness') {
			GetData('op_url', modalId);
		}
		
		if (modalId == '#Training') {
			GetData('training_url', modalId);
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
			modal.style.display = 'none'; // Hide the modal
		});
	}
	

	  document.getElementById('remarks').addEventListener('input', function (e) {
	        // Allow alphabetic characters, digits, and spaces
	        this.value = this.value.replace(/[^a-zA-Z0-9\s]/g, '');
	    });
	  function calculateTotals() {
	    let affectedTotal = 0;
	    let qualifiedTotal = 0;
	    let notQualifiedTotal = 0;
	    let mapTotal = 0;
	    let educationTotal = 0;
	    let promotionTotal = 0;

	    numericInputs.forEach(input => {
	      const column = input.dataset.column;
	      const value = parseFloat(input.value) || 0;

	      switch (column) {
	        case 'affected':
	          affectedTotal += value;
	          break;
	        case 'qualified':
	          qualifiedTotal += value;
	          break;
	        case 'not_qualified':
	          notQualifiedTotal += value;
	          break;
	        case 'map':
	          mapTotal += value;
	          break;
	        case 'education':
	          educationTotal += value;
	          break;
	        case 'promotion':
	          promotionTotal += value;
	          break;
	      }
	    });

	    totalAffected.textContent = affectedTotal.toFixed(2);
	    totalQualified.textContent = qualifiedTotal.toFixed(2);
	    totalNotQualified.textContent = notQualifiedTotal.toFixed(2);
	    totalMap.textContent = mapTotal.toFixed(2);
	    totalEducation.textContent = educationTotal.toFixed(2);
	    totalPromotion.textContent = promotionTotal.toFixed(2);
	    
	    document.getElementById('total_affected_display').textContent = affectedTotal.toFixed(2);
	    document.getElementById('total_affected').value = affectedTotal.toFixed(2);

	    document.getElementById('total_qualified_display').textContent = qualifiedTotal.toFixed(2);
	    document.getElementById('total_qualified').value = qualifiedTotal.toFixed(2);

	    document.getElementById('total_not_qualified_display').textContent = notQualifiedTotal.toFixed(2);
	    document.getElementById('total_not_qualified').value = notQualifiedTotal.toFixed(2);

	    document.getElementById('total_map_display').textContent = mapTotal.toFixed(2);
	    document.getElementById('total_map').value = mapTotal.toFixed(2);

	    document.getElementById('total_education_display').textContent = educationTotal.toFixed(2);
	    document.getElementById('total_education').value = educationTotal.toFixed(2);

	    document.getElementById('total_promotion_display').textContent = promotionTotal.toFixed(2);
	    document.getElementById('total_promotion').value = promotionTotal.toFixed(2);
	  }
	  
	  
	  
	  $("#btnSubmit").click(function(event) {
			event.preventDefault();
			
			var form = $('#fileUploadForm')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "uploadAnnexureUrl?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
	  
	  
	  $("#btnSubmitcatB").click(function(event) {
			event.preventDefault();
			
			var form = $('#fileUploadFormcatB')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "upload21ReportRecTraining?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
	  
	  
	  $("#deffi_exp_resp_to_trainngSubmit").click(function(event) {
			event.preventDefault();
			
			var form = $('#deffi_exp_resp_to_trainngId')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "upload_deffi_exp_resp_to_trainngUrl?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
	  
	  
	  $("#emp_of_unit_during_periodSubmit").click(function(event) {
			event.preventDefault();
			
			var form = $('#emp_of_unit_during_periodId')[0];
			var data = new FormData(form);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "emp_of_unit_during_periodUrl?${_csrf.parameterName}=${_csrf.token}",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					$("#uploadAnnexure").val(null);
				}
			});
		});
	  
		function ffrs_save1_add_fn(num) {
		    var tbody = document.getElementById('classfication_tbody');
		    
		    var newRow = '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
		        '<td>' + (tbody.children.length + 1) + '.</td>' + // Incrementing the row number
		        '<td><input type="text" name="number_of_ranges' + (num + 1) + '" id="number_of_ranges' + (num + 1) + '" class="form-control form-control-sm numeric-input"></td>' +
		        '<td><input type="text" name="when_used' + (num + 1) + '" id="when_used' + (num + 1) + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 94%;display: inline;" ' +
		        'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
		        'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"></td>' +
		        '<td><input type="text" name="difficulties_experienced' + (num + 1) + '" id="difficulties_experienced' + (num + 1) + '" class="form-control form-control-sm"></td>' +
		        '<td>' +
		        '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="classification_save' + (num + 1) + '" onclick="classfication_save_fn(' + (num + 1) + ');"><i class="fa fa-save"></i></a>' +
		        '<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="promo_exam_add' + (num + 1) + '" onclick="classification_add_fn(' + (num + 1) + ');"><i class="fa fa-plus"></i></a>' +
		        '<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="promo_exam_remove' + (num + 1) + '" onclick="classification_remove_fn(' + (num + 1) + ');"><i class="fa fa-trash"></i></a>' +
		        '</td>' +
		        '</tr>';
		       
		    tbody.insertAdjacentHTML('beforeend', newRow);
		    datepicketDate('when_used' + (num + 1));
		}
		function set_data14(j) {
		    var tbody = document.getElementById('classfication_tbody');
		 
		    var data = '';
		    tbody.innerHTML = ''; 
		
		    
	for (var i = 0; i < j.length; i++) {
		var date=formatDate(j[i].difficulties_experienced );
	    data += 
	        '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
	        '<td><input type="hidden" id="class_id'+(i+1)+'"name="class_id'+(i+1)+'"vlaue="'+j[i].id+'">' + (i + 1) + '.</td>' + 
	        
	        '<td><input type="text" name="number_of_ranges' + (i + 1) + '" id="number_of_ranges' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].number_of_ranges || '') + '"></td>' +
	        '<td><input type="text" name="when_used' + (i + 1) + '" id="when_used' + (i + 1) + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 94%; display: inline;" ' +
	        'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	        'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="' + date + '"></td>' +
	        '<td><input type="text" name="difficulties_experienced' + (i + 1) + '" id="difficulties_experienced' + (i + 1) + '" class="form-control form-control-sm" value="' + j[i].number_of_ranges + '"></td>' +
	        '<td>' +
	        '<a style="display: none;" class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="classification_save' + (i + 1) + '" onclick="classfication_save_fn(' + (i + 1) + ');"><i class="fa fa-save"></i></a>' +
	        
	        '<a  class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="promo_exam_remove' + (i + 1) + '" onclick="classification_remove_fn(' +j[i].id  + ');"><i class="fa fa-trash"></i></a>' +
	        '</td>' +
	        '</tr>';
	}

	// Adding a new empty row for additional input
	 var newIndex = j.length + 1;

	    // Adding a new empty row for additional input
	    var newRow = 
	        '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
	        '<td>'+newIndex+'</td>' +
	        '<td><input type="text" name="number_of_ranges' + newIndex + '" id="number_of_ranges' + newIndex + '" class="form-control form-control-sm numeric-input"></td>' +
	        '<td><input type="text" name="when_used' + newIndex + '" id="when_used' + newIndex + '" class="form-control" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" ' +
	        'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	        'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"></td>' +
	        '<td><input type="text" name="difficulties_experienced' + newIndex + '" id="difficulties_experienced' + newIndex + '" class="form-control form-control-sm"></td>' +
	        '<td>' +
	        '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="classification_save_new" onclick="classfication_save_fn();"><i class="fa fa-save"></i></a>' +
	        '<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="promo_exam_add_new" onclick="classification_add_fn();"><i class="fa fa-plus"></i></a>' +
	        '<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="promo_exam_remove_new" onclick="classification_remove_fn();"><i class="fa fa-trash"></i></a>' +
	        '</td>' +
	        '</tr>';
		    tbody.insertAdjacentHTML('beforeend', data); 
		    
		  
		    tbody.insertAdjacentHTML('beforeend', newRow);
		    for (var i = 0; i <j.length+1; i++) {
			    datepicketDate('when_used' + (i + 1));
			    }
		    
		    set_data_ffrs();	    
		}
		function classification_remove_fn(id){

		    if (confirm("Are you sure you want to delete this record?")) {
		  
		    	$.post( "deleteTbleRowClassification?" + key + "=" + value, {
					id : id
				
				}, function(j) {
					alert(j);
					
				

		});
			
		    }
			
			
		}
		
		
		
		function ffrs_remove_fn(id){
		    if (confirm("Are you sure you want to delete this record?")) {
		    	$.post( "deleteRowFfrs?" + key + "=" + value, {
					id : id
				
				}, function(j) {
					alert(j);
		});
		    }
		}	
		
		

		function set_data13(j){
			document.getElementById('broughtPreviousObj').value = j[0].broughtPreviousObj;
			document.getElementById('broughtPreviousObserv').value = j[0].broughtPreviousObserv;
			document.getElementById('broughtPreviousRemark').value = j[0].broughtPreviousRemark;

			document.getElementById('raisedReportObj').value = j[0].raisedReportObj; 
			document.getElementById('raisedReportObserv').value = j[0].raisedReportObserv; 
			document.getElementById('raisedReportRemark').value = j[0].raisedReportRemark; 

			document.getElementById('settledDuringObj').value = j[0].settledDuringObj; 
			document.getElementById('settledDuringObserv').value = j[0].settledDuringObserv; 
			document.getElementById('settledDuringRemark').value = j[0].settledDuringRemark; 

			document.getElementById('remainingObj').value = j[0].remainingObj; 
			document.getElementById('remainingObserv').value = j[0].remainingObserv; 
			document.getElementById('remainingRemark').value = j[0].remainingRemark; 
			
			document.getElementById('difficultiesObj1').value = j[0].difficultiesObj1;
			
			document.getElementById('difficultiesObj3').value = j[0].difficultiesObj3; 
			document.getElementById('difficultiesObserv1').value = j[0].difficultiesObserv1; 
			document.getElementById('difficultiesObserv3').value = j[0].difficultiesObserv3; 
			document.getElementById('difficultiesRemark1').value = j[0].difficultiesRemark1; 
			document.getElementById('difficultiesRemark3').value = j[0].difficultiesRemark3; 
		}
		

		
		
		function ffrs_save_fn(num)
	{
			var ffrs_id = $("#ffrs_id" + num).val();
			var range_available = $("#range_available" + num).val();
		    var range_utilized = $("#range_utilized" + num).val();
			var whenusedffrs=$("#whenusedffrs" + num).val();
		    var difficulties_experienced = $("#difficulties_experienced" + num).val();
		    
				
		$.post( "ffrs_save_url?" + key + "=" + value, {
			ffrs_id:ffrs_id,
			range_available : range_available,
			range_utilized:range_utilized,
			whenusedffrs:whenusedffrs,difficulties_experienced:difficulties_experienced
		}, function(j) {
			alert(j);
			
			if(j=="Data Saved Successfully.")
				{
				var promo_exam_remove = $("#promo_exam_remove" + num).show();

			
				}

	});
	}
	function set_data_ffrs() {
	    $("#nrWaitLoader").show();

	    $.post("ffrs_url?" + key + "=" + value, {}, function(j) {
	        var tbody = document.getElementById('ffrs_tbody');
	        var data = '';
	        tbody.innerHTML = '';

	        for (var i = 0; i < j.length; i++) {	
	            var date = formatDate(j[i].whenusedffrs);
	            data += 
	                '<tr style="font-size: 12px; padding: 10px; text-align:center;">' +
	                '<td><input type="hidden" name="ffrs_id' + (i + 1) + '" id="ffrs_id' + (i + 1) + '" class="form-control form-control-sm" value="' + (j[i].id || 0) + '">'+ (i + 1)+'.</td>' +
	                '<td><input type="text" name="range_available' + (i + 1) + '" id="range_available' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].range_available || '') + '"></td>' +
	                '<td><input type="text" name="range_utilized' + (i + 1) + '" id="range_utilized' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].range_utilized || '') + '"></td>' +
	                '<td><input type="text" name="whenusedffrs' + (i + 1) + '" id="whenusedffrs' + (i + 1) + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 90%; display: inline;" ' +
	                'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	                'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="' + date + '"></td>' +
	                '<td><input type="text" name="difficulties_ffrs' + (i + 1) + '" id="difficulties_ffrs' + (i + 1) + '" class="form-control form-control-sm numeric-input" value="' + (j[i].difficulties_experienced || '') + '"></td>' +
	                '<td>' +
	                '<a class="btn btn-primary btn-sm" style="display: none;"  value="SAVE" title="SAVE" id="ffrs_save' + (i + 1) + '" onclick="ffrs_save_fn(\'' + (i + 1) + '\');"><i class="fa fa-save"></i></a>' +
	                '<a class="btn btn-success btn-sm" style="display: none;" value="ADD" title="ADD" id="ffrs_save_add' + (i + 1) + '" onclick="ffrs_save_add_fn(' + (i + 1) + ');"><i class="fa fa-plus"></i></a>' +
	                '<a  class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="ffrs_save_remove' + (i + 1) + '" onclick="ffrs_remove_fn(\'' + j[i].id + '\');"><i class="fa fa-trash"></i></a>' +
	                '</td>' +
	                '</tr>';
	        }

	        // Adding a new empty row for additional input
	        var newIndex = j.length + 1;
	        var newRow = 
	            '<tr style="font-size: 12px; padding: 10px; text-align:center;" >' +
	            '<td><input type="hidden" name="ffrs_id' + newIndex + '" id="ffrs_id' + newIndex + '" class="form-control form-control-sm" value="0">'+newIndex+'.</td>' +
	            '<td><input type="text" name="range_available' + newIndex + '" id="range_available' + newIndex + '" class="form-control form-control-sm numeric-input"></td>' +
	            '<td><input type="text" name="range_utilized' + newIndex + '" id="range_utilized' + newIndex + '" class="form-control form-control-sm numeric-input"></td>' +
	            '<td><input type="text" name="whenusedffrs' + newIndex + '" id="whenusedffrs' + newIndex + '" maxlength="10" onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 90%; display: inline;" ' +
	            'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' +
	            'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"></td>' +
	            '<td><input type="text" name="difficulties_ffrs' + newIndex + '" id="difficulties_ffrs' + newIndex + '" class="form-control form-control-sm"></td>' +
	            '<td>' +
	            '<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="ffrs_save_new" onclick="ffrs_save_fn('+newIndex+');"><i class="fa fa-save"></i></a>' +
	            '<a class="btn btn-success btn-sm" value="ADD" title="ADD" id="ffrs_save_add_new" onclick="ffrs_save_add_fn('+newIndex+');"><i class="fa fa-plus"></i></a>' +
	            '<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="ffrs_save_remove_new" onclick="ffrs_remove_fn('+newIndex+');"><i class="fa fa-trash"></i></a>' +
	            '</td>' +
	            '</tr>';

	        tbody.insertAdjacentHTML('beforeend', data);
	        tbody.insertAdjacentHTML('beforeend', newRow);

	        for (var i = 0; i < j.length + 1; i++) {
	            datepicketDate('whenusedffrs' + (i + 1));
	        }
	    });

	    $("#nrWaitLoader").hide();
	}




	function ffrs_save_fn(num)
	{

	var ffrs_id = $("#ffrs_id" + num).val();
	var range_available = $("#range_available" + num).val();
	var range_utilized = $("#range_utilized" + num).val();
	var whenusedffrs=$("#whenusedffrs" + num).val();
	var difficulties_experienced = $("#difficulties_experienced" + num).val();

		
	$.post( "ffrs_save_url?" + key + "=" + value, {
	ffrs_id:ffrs_id,
	range_available : range_available,
	range_utilized:range_utilized,
	whenusedffrs:whenusedffrs,difficulties_experienced:difficulties_experienced
	}, function(j) {
	alert(j);

	if(j=="Data Saved Successfully.")
		{
		var promo_exam_remove = $("#promo_exam_remove" + num).show();


		}

	});
	}
	
	
	function calculateSurplusDeficient(authId, postId, surDefId) {
	    var authInput = document.getElementById(authId);
	    var postInput = document.getElementById(postId);
	    var surDefInput = document.getElementById(surDefId);

	    if (authInput && postInput && surDefInput) {
	    	var authValue = parseFloat(authInput.value) || 0;
	    	var postValue = parseFloat(postInput.value) || 0;

	    	var surplusDeficient =postValue - authValue;

	        if (surplusDeficient >= 0) {
	            surDefInput.value = surplusDeficient + "/0";
	        } else {
	            surDefInput.value = "0/" + Math.abs(surplusDeficient); 
	        }
	    }
	}

	
	document.addEventListener('DOMContentLoaded', function() {

	    // Officers
	    var authoffiInput = document.getElementById('authoffi');
	    var postoffiInput = document.getElementById('postoffi');
	    if (authoffiInput && postoffiInput) {
	        authoffiInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authoffi', 'postoffi', 'surdefioffi');
	        });
	        postoffiInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authoffi', 'postoffi', 'surdefioffi');
	        });
	    }

	    // JCOs
	    var authjcoInput = document.getElementById('authjco');
	    var postjcoInput = document.getElementById('postjco');
	    if (authjcoInput && postjcoInput) {
	        authjcoInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authjco', 'postjco', 'surdefijco');
	        });
	        postjcoInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authjco', 'postjco', 'surdefijco');
	        });
	    }

	    // Warrant Officers
	    var authwarrantInput = document.getElementById('authwarrant');
	    var postwarrantInput = document.getElementById('postwarrant');
	    if (authwarrantInput && postwarrantInput) {
	        authwarrantInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authwarrant', 'postwarrant', 'surdewarrant');
	        });
	        postwarrantInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authwarrant', 'postwarrant', 'surdewarrant');
	        });
	    }

	    // OR
	    var authorInput = document.getElementById('author');
	    var postorInput = document.getElementById('postor');
	    if (authorInput && postorInput) {
	        authorInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('author', 'postor', 'surdefior');
	        });
	        postorInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('author', 'postor', 'surdefior');
	        });
	    }

	    // Civilians
	    var authcivInput = document.getElementById('authciv');
	    var postcivInput = document.getElementById('postciv');
	    if (authcivInput && postcivInput) {
	        authcivInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authciv', 'postciv', 'surdeciv');
	        });
	        postcivInput.addEventListener('keyup', function() {
	            calculateSurplusDeficient('authciv', 'postciv', 'surdeciv');
	        });
	    }
	  
	  //DYNAMIC NEXT AND PREVIOUS

	    const modalOrder = [
	        '#OpPreparedness',
	        '#Training',
	        '#StateWeapEqpt',
	        '#StatePersonnel',
	        '#Administration',
	        '#AspectMorale',
	        '#InteriorEconomy',
	        '#MajorAchievements',
	        '#StrengthOfTheUnit',
	        '#Challenges',
	        '#improveFollowing',
	        '#AttentionOfHigherHQ',
	        '#MitigationActionSummary',
	        '#PointsForDiscussion'
	    ];

	    const modalStatuses = {
	        '#OpPreparedness': ${report.op_preparedness_item},
	        '#Training': ${report.training_item},
	        '#StateWeapEqpt': ${report.state_weapon_item},
	        '#StatePersonnel': ${report.state_personnel_item},
	        '#Administration': ${report.administation_item},
	        '#AspectMorale': ${report.aspect_item},
	        '#InteriorEconomy': ${report.interior_item},
	        '#MajorAchievements': ${report.major_achievements_item},
	        '#StrengthOfTheUnit': ${report.strength_of_unit_item},
	        '#Challenges': ${report.challenges_item},
	        '#improveFollowing': ${report.improve_following_item},
	        '#AttentionOfHigherHQ': ${report.attention_of_higher_item},
	        '#MitigationActionSummary': ${report.mitigation_item},
	        '#PointsForDiscussion': ${report.points_discussion_item}
	    };

	    function getCurrentModalId() {
	        for (const modalId of modalOrder) {
	            if ($(modalId).hasClass('show')) {
	                return modalId;
	            }
	        }
	        return null;
	    }

	    //NEXT BUTTON
	    const nextButtons = document.querySelectorAll('.nex');
	    nextButtons.forEach(button => {
	        button.addEventListener('click', findNextModalAndOpen);
	    });
	    
	    //PREVIOUS BUTTON
	    const previousButtons = document.querySelectorAll('.prev');
	    previousButtons.forEach(button => {
	        button.addEventListener('click', findPreviousModalAndOpen);
	    });
	    

	    function findNextModalAndOpen() {
	        const currentModalId = getCurrentModalId();
	        if (!currentModalId) {
	            console.warn("No modal is currently open.");
	            return;
	        }
	        const currentIndex = modalOrder.indexOf(currentModalId);
	        if (currentIndex === -1) {
	            console.error("Current modal ID not found in modalOrder.");
	            return;
	        }
	        for (let i = currentIndex + 1; i < modalOrder.length; i++) {
	            const nextModalId = modalOrder[i];
	            if (modalStatuses[nextModalId] === 0 || modalStatuses[nextModalId] === 1) {
	                openModal(nextModalId);
	                return;
	            }
	        }
	        console.warn("No *next* modal with status 0 or 1 found.");
	    }

	    function findPreviousModalAndOpen() {
	        const currentModalId = getCurrentModalId();
	        if (!currentModalId) {
	            console.warn("No modal is currently open.");
	            return;
	        }
	        const currentIndex = modalOrder.indexOf(currentModalId);
	        if (currentIndex === -1) {
	            console.error("Current modal ID not found in modalOrder.");
	            return;
	        }
	        for (let i = currentIndex - 1; i >= 0; i--) {
	            const previousModalId = modalOrder[i];
	            if (modalStatuses[previousModalId] === 0 || modalStatuses[previousModalId] === 1) {
	                openModal(previousModalId);
	                return;
	            }
	        }
	        console.warn("No *previous* modal with status 0 or 1 found.");
	    }
	    
	});
	
	

	
	/* updatecode */
	
	  
	  $(document).on("click", ".add-to-recommended", function () {
		    var $row = $(this).closest("tr");	
		    var $button = $(this);
		    var buttonContext = $button.data("context");		  
		    var formData={};
		    var Url = "";
		    
		    if(buttonContext === "personalTrainedCourses"){
		       	var courseName = $row.find("input[name='courseName']").val();
			    if (courseName == "") {
			        alert("Please Enter Name Of Course");
			        return;
			    }	
			    
			    
			    formData = {
			    		courseName: courseName,
			    		officersTrained: $row.find("input[name='officersTrained']").val(),
			    		jcosTrained: $row.find("input[name='jcosTrained']").val(),
			    		ncosTrained: $row.find("input[name='ncosTrained']").val(),
			    		orTrained: $row.find("input[name='orTrained']").val(),
			    		officersAvailable: $row.find("input[name='officersAvailable']").val(),
			    		jcosAvailable: $row.find("input[name='jcosAvailable']").val(),
			    		ncosAvailable: $row.find("input[name='ncosAvailable']").val(),
			    		orAvailable: $row.find("input[name='orAvailable']").val(),
			    		totalOfficers: $row.find("input[name='totalOfficers']").val(),
			    		totalJcos: $row.find("input[name='totalJcos']").val(),
			    		totalNcos: $row.find("input[name='totalNcos']").val(),
			    		totalOr: $row.find("input[name='totalOr']").val()
				    };	    
			  

		    } /* end personalTrainedCourses */
		    else if(buttonContext == "upGradation"){
		    	var trade = $row.find("input[name='trade']").val();
			    if (trade === "") {
			        alert("Please Enter Trade");
			        return;
			    }			    
			    
			    formData = {
				        trade: trade,
				        affected_up_gradation_class_iv: $row.find("input[name='affected_up_gradation_class_iv']").val(),
				        affected_up_gradation_class_iii: $row.find("input[name='affected_up_gradation_class_iii']").val(),
				        affected_up_gradation_class_ii: $row.find("input[name='affected_up_gradation_class_ii']").val(),
				        affected_up_gradation_class_i: $row.find("input[name='affected_up_gradation_class_i']").val(),
				        during_up_gradation_class_iv: $row.find("input[name='during_up_gradation_class_iv']").val(),
				        during_up_gradation_class_iii: $row.find("input[name='during_up_gradation_class_iii']").val(),
				        during_up_gradation_class_ii: $row.find("input[name='during_up_gradation_class_ii']").val(),
				        during_up_gradation_class_i: $row.find("input[name='during_up_gradation_class_i']").val(),
				        total_available_class_iv: $row.find("input[name='total_available_class_iv']").val(),
				        total_available_class_iii: $row.find("input[name='total_available_class_iii']").val(),
				        total_available_class_ii: $row.find("input[name='total_available_class_ii']").val(),
				        total_available_class_i: $row.find("input[name='total_available_class_i']").val()
				    };
			    
			  
		    }/* end upGradation */
		    else if(buttonContext == "defOfEqupMaintenance"){
		    	var nomentclature = $row.find("input[name='nomentclature']").val();
			    if (nomentclature === "") {
			        alert("Please Enter Nomenclature");
			        return;
			    }			    
			    
			    formData = {
			    		nomentclature: nomentclature,
			    		au_id: $row.find("input[name='au_id']").val(),
			    		qunt_auth: $row.find("input[name='qunt_auth']").val(),
			    		qunt_held: $row.find("input[name='qunt_held']").val(),
			    		qunt_defi: $row.find("input[name='qunt_defi']").val(),
			    		due_in: $row.find("input[name='due_in']").val(),
			    		due_out: $row.find("input[name='due_out']").val(),
			    		remark: $row.find("input[name='remark']").val()
				    };
			    
			  
		    } /* end defOfEqupMaintenance */
		    else if(buttonContext == "financialGrants"){
		    	var typeOfFund = $row.find("input[name='typeOfFund']").val();
		    	if (typeOfFund === "") {
		    		alert("Please Enter Type Of Fund / Grants");
		    		return;
		    	}
		    	
		    	formData = {
		    		typeOfFund: typeOfFund,
		    		amtAuth: $row.find("input[name='amtAuth']").val(),
		    		amtAlloted: $row.find("input[name='amtAlloted']").val(),
		    		reasonOverUnder: $row.find("input[name='reasonOverUnder']").val(),
		    		amtExpended: $row.find("input[name='amtExpended']").val(),
		    		amtUnutilised: $row.find("input[name='amtUnutilised']").val(),
		    		reasonNonUtil: $row.find("input[name='reasonNonUtil']").val(),
		    		detailedRemarks: $row.find("input[name='detailedRemarks']").val()
		    	};
		    }/* end financialGrants */
		    
		    else if(buttonContext == "classSave"){
		    	var numberOfRanges = $row.find("input[name='numberOfRanges']").val();
		    	if (numberOfRanges === "") {
		    		alert("Please Enter Number Of Ranges");
		    		return;
		    	}
		    	
		    	formData = {
	    			numberOfRanges: numberOfRanges,
	    			whenUsed: $row.find("input[name='whenUsed']").val(),
	    			difficultiesExperienced: $row.find("input[name='difficultiesExperienced']").val(),
		    	};
		    }/* end classSave */
		    
		    
		    else if(buttonContext == "ffrsSave"){
		    	var rangeAvailable = $row.find("input[name='rangeAvailable']").val();
		    	if (rangeAvailable === "") {
		    		alert("Please Enter Range Available");
		    		return;
		    	}
		    	
		    	formData = {
	    			rangeAvailable: rangeAvailable,
	    			rangeUtilized: $row.find("input[name='rangeUtilized']").val(),
	    			whenUsedFfrs: $row.find("input[name='whenUsedFfrs']").val(),
	    			difficultiesFfrs: $row.find("input[name='difficultiesFfrs']").val(),
		    	};
		    }/* end ffrsSave */
		    
		    else if(buttonContext == "equipmentTble"){
		    	var type_of_duty = $row.find("input[name='type_of_duty']").val();
			    if (type_of_duty === "") {
			        alert("Please Enter Type of Duty ie Administration, Training and Formation Detailment etc");
			        return;
			    }			    
			    
			    formData = {
			    		type_of_duty: type_of_duty,
			    		equp_authorize: $row.find("input[name='equp_authorize']").val(),
			    		equp_cove: $row.find("input[name='equp_cove']").val(),
			    		equp_remark: $row.find("input[name='equp_remark']").val()			    		
				    };
			    
			  
		    }/* end equipmentTble */
		    else if(buttonContext == "trainingAmmunitionTble"){
		    	var typeOfAmmunation = $row.find("input[name='typeOfAmmunation']").val();
			    if (typeOfAmmunation === "") {
			        alert("Please Enter Type of Ammunition");
			        return;
			    }			    
			    
			    formData = {
			    		typeOfAmmunation: typeOfAmmunation,
			    		tmAuth: $row.find("input[name='tmAuth']").val(),
			    		tm_qunt_year: $row.find("input[name='tm_qunt_year']").val(),
			    		received_inc_carry_forward: $row.find("input[name='received_inc_carry_forward']").val(),
			    		tmExpended: $row.find("input[name='tmExpended']").val(),
			    		tmBlance: $row.find("input[name='tmBlance']").val(),			    		
			    		tmreason: $row.find("input[name='tmreason']").val()
				    };
			    
			  
		    } /* end defOfEqupMaintenance */
		    else if(buttonContext == "summaryTble"){
		    	var typeOfTechinsp = $row.find("input[name='Otherinsp']").val();
			    if (typeOfTechinsp === "") {
			        alert("Please Enter Type of Technical Inspection");
			        return;
			    }			    
			    
			    formData = {
			    		typeOfTechinsp: typeOfTechinsp,
			    		otherinspDate: $row.find("input[name='otherinspDate']").val(),
			    		byWhomOther: $row.find("input[name='byWhomOther']").val(),
			    		resultOther: $row.find("input[name='resultOther']").val(),
			    		remarkOther: $row.find("input[name='remarkOther']").val()
				    };
			    
			  
		    } /* end summaryTble */
		    
		    else if(buttonContext == "OutstandingTble"){
		    	var year = $row.find("input[name='year']").val();
			    if (year === "") {
			        alert("Please Enter Year");
			        return;
			    }			    
			    
			    formData = {
			    		year: year,
			    		amount_outstanding: $row.find("input[name='amount_outstanding']").val(),
			    		on_acc: $row.find("input[name='on_acc']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end Outstanding */
		    

		    else if(buttonContext == "OutstandingMTTble"){
		    	var year = $row.find("input[name='year']").val();
			    if (year === "") {
			        alert("Please Enter Year");
			        return;
			    }			    
			    
			    formData = {
			    		year: year,
			    		nature_of_loss: $row.find("input[name='nature_of_loss']").val(),
			    		value: $row.find("input[name='value']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end Outstanding MT */
		    
		    else if(buttonContext == "fatalIncidentsTble"){
		    	var date = $row.find("input[name='date']").val();
			    if (date === "") {
			        alert("Please Enter Date");
			        return;
			    }			    
			    var nature_of_insident = $row.find("input[name='nature_of_insident']").val();
			    if (nature_of_insident === "") {
			        alert("Please Enter Nature of Insident");
			        return;
			    }
			    formData = {
			    		date: date,
			    		nature_of_insident: nature_of_insident,
			    		casualty: $row.find("input[name='casualty']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end fatal Incidents */
		    
		    else if(buttonContext == "securityLapsesTble"){
		    	var date = $row.find("input[name='date']").val();
			    if (date === "") {
			        alert("Please Enter Date");
			        return;
			    }			    
			    var nature_of_security_lapse = $row.find("input[name='nature_of_security_lapse']").val();
			    if (nature_of_security_lapse === "") {
			        alert("Please Enter Nature of Security Lapse");
			        return;
			    }
			    formData = {
			    		date: date,
			    		nature_of_security_lapse: nature_of_security_lapse,
			    		resulted_in: $row.find("input[name='resulted_in']").val(),
			    		remarks: $row.find("input[name='remarks']").val(),
				    };
			    
			  
		    } /* end security lapses */
		    
		    else if(buttonContext == "outsideAttachmentsTble"){
		    	 var location = $row.find("input[name='location']").val();
		    	    if (location === "") {
		    	        alert("Please Enter Location");
		    	        return;
		    	    }		    
			   
			    formData = {
			    		location: location,
			            number_of_personnel_attached: $row.find("input[name='number_of_personnel_attached']").val(),
			            duration: $row.find("input[name='duration']").val(),
			            remarks: $row.find("input[name='remarks']").val()
				    };
			    
			  
		    } /* end outside attachments */
		    else if(buttonContext === "coursesUndertaken"){
		    	var courseNameUndertaken = $row.find("input[name='courseNameUndertaken']").val();
		    	if (courseNameUndertaken == "") {
		    		alert("Please Enter Name Of Course");
		    		return;
		    	}	


		    	formData = {
		    		serialNumber: $row.find("input[name='serialNumber']").val(),
		    		courseNameUndertaken: courseNameUndertaken,
		    		numberOfCourse: $row.find("input[name='numberOfCourse']").val(),
		    		periodFrom: $row.find("input[name='periodFrom']").val(),
		    		periodTo: $row.find("input[name='periodTo']").val(),
		    		totalAllotted: $row.find("input[name='totalAllotted']").val(),
		    		attended: $row.find("input[name='attended']").val(),
		    		rtu: $row.find("input[name='rtu']").val(),
		    		detailedRemarks: $row.find("input[name='detailedRemarksCourseUndertaken']").val()
		    	};	    


		    	} /* end coursesUndertaken */
		    	else if(buttonContext === "otherCourses"){
		    	var courseNameOther = $row.find("input[name='courseNameOther']").val();
		    	if (courseNameOther == "") {
		    		alert("Please Enter Name Of Course");
		    		return;
		    	}	

		    	formData = {
		    		serialNumber: $row.find("input[name='serialNumberOther']").val(),
		    		courseNameOther: courseNameOther,
		    		numberOfCourse: $row.find("input[name='numberOfCourse']").val(),
		    		totalOther: $row.find("input[name='totalOther']").val(),
		    		gradingOther: $row.find("input[name='gradingOther']").val(),
		    		daOther: $row.find("input[name='daOther']").val(),
		    		failedOther: $row.find("input[name='failedOther']").val(),
		    		rtuOther: $row.find("input[name='rtuOther']").val(),
		    		detailedRemarksOther: $row.find("input[name='detailedRemarksOther']").val()
		    	};	    


		    	} /* end otherCourses */
		    	else if(buttonContext === "tdOfficers"){
		    	var rankAndName = $row.find("input[name='rankAndName']").val();
		    	if (rankAndName == "") {
		    		alert("Please Enter Rank & Name");
		    		return;
		    	}	

		    	formData = {
		    			serialNumberTD: $row.find("input[name='serialNumberTD']").val(),
		    			rankAndName: rankAndName,
		    			natureOfDuty: $row.find("input[name='natureOfDuty']").val(),
		    			orderedBy: $row.find("input[name='orderedBy']").val(),
		    			detailedRemarksTD: $row.find("input[name='detailedRemarksTD']").val()
		    	};	    


		    	} /* end tdOfficers */

		    	else if(buttonContext === "socialMediaLapses"){
		    	var SMLdateViolationReportedHQ = $row.find("input[name='SMLdateViolationReportedHQ']").val();
		    	if (SMLdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			SMLserialNumber: $row.find("input[name='SMLserialNumber']").val(),
		    			SMLdateViolationReportedHQ: SMLdateViolationReportedHQ,
		    			SMLdateViolationReportedUnit: $row.find("input[name='SMLdateViolationReportedUnit']").val(),
		    			SMLnumberRankName: $row.find("input[name='SMLnumberRankName']").val(),
		    			SMLcurrentStatus: $row.find("input[name='SMLcurrentStatus']").val(),
		    			SMLlossOfInformation: $row.find("input[name='SMLlossOfInformation']").val(),
		    			SMLremarks: $row.find("input[name='SMLremarks']").val()
		    	};	    


		    	} /* end socialMediaLapses */

		    	else if(buttonContext === "pioCallsLapses"){
		    	var PCLdateViolationReportedHQ = $row.find("input[name='PCLdateViolationReportedHQ']").val();
		    	if (PCLdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			PCLserialNumber: $row.find("input[name='PCLserialNumber']").val(),
		    			PCLdateViolationReportedHQ: PCLdateViolationReportedHQ,
		    			PCLdateViolationReportedUnit: $row.find("input[name='PCLdateViolationReportedUnit']").val(),
		    			PCLnumberRankName: $row.find("input[name='PCLnumberRankName']").val(),
		    			PCLcurrentStatus: $row.find("input[name='PCLcurrentStatus']").val(),
		    			PCLlossOfInformation: $row.find("input[name='PCLlossOfInformation']").val(),
		    			PCLremarks: $row.find("input[name='PCLremarks']").val()
		    	};


		    	} /* end pioCallsLapses */

		    	else if(buttonContext === "espionageLapses"){
		    	var ELdateViolationReportedHQ = $row.find("input[name='ELdateViolationReportedHQ']").val();
		    	if (ELdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			ELserialNumber: $row.find("input[name='ELserialNumber']").val(),
		    			ELdateViolationReportedHQ: ELdateViolationReportedHQ,
		    			ELdateViolationReportedUnit: $row.find("input[name='ELdateViolationReportedUnit']").val(),
		    			ELnumberRankName: $row.find("input[name='ELnumberRankName']").val(),
		    			ELcurrentStatus: $row.find("input[name='ELcurrentStatus']").val(),
		    			ELlossOfInformation: $row.find("input[name='ELlossOfInformation']").val(),
		    			ELremarks: $row.find("input[name='ELremarks']").val()
		    	};


		    	} /* end espionageLapses */

		    	else if(buttonContext === "cellPhoneCompromiseLapses"){
		    	var CPCLdateViolationReportedHQ = $row.find("input[name='CPCLdateViolationReportedHQ']").val();
		    	if (CPCLdateViolationReportedHQ == "") {
		    		alert("Please Enter Date of Violation Reported Initially Higher HQ");
		    		return;
		    	}	

		    	formData = {
		    			CPCLserialNumber: $row.find("input[name='CPCLserialNumber']").val(),
		    			CPCLdateViolationReportedHQ: CPCLdateViolationReportedHQ,
		    			CPCLdateViolationReportedUnit: $row.find("input[name='CPCLdateViolationReportedUnit']").val(),
		    			CPCLnumberRankName: $row.find("input[name='CPCLnumberRankName']").val(),
		    			CPCLcurrentStatus: $row.find("input[name='CPCLcurrentStatus']").val(),
		    			CPCLlossOfInformation: $row.find("input[name='CPCLlossOfInformation']").val(),
		    			CPCLremarks: $row.find("input[name='CPCLremarks']").val()
		    	};


		    	} /* end cellPhoneCompromiseLapses */
		    	 else if(buttonContext == "UntraceableTble"){
		    		 var untraceable_document = $row.find("input[name='untraceable_document']").val();
		    		    if (untraceable_document === "") {
		    		        alert("Please Enter Untraceable Document");
		    		        return;
		    		    }			    
					   
					    formData = {
					    		untraceable_document: untraceable_document,
					            classification: $row.find("input[name='classification']").val(),
					            originator_doc: $row.find("input[name='originator_doc']").val(),
					            custodian_doc: $row.find("input[name='custodian_doc']").val(),
					            current_status: $row.find("input[name='current_status']").val(),
					            remarks: $row.find("input[name='remarks']").val()
						    };
					    
					  
				    } /* end Untraceable */
				    
				    else if(buttonContext == "lossCdDvdTble"){
				    	var untraceble_cd_dvd = $row.find("input[name='untraceble_cd_dvd']").val();
					    if (untraceble_cd_dvd === "") {
					        alert("Please Enter Untraceable Cd/Dvd");
					        return;
					    }			    
					   
					    formData = {
					    		untraceble_cd_dvd: untraceble_cd_dvd,
					    		classification: $row.find("input[name='classification']").val(),
					    		originator_cd: $row.find("input[name='originator_cd']").val(),
					    		custodian_cd: $row.find("input[name='custodian_cd']").val(),
					    		current_status: $row.find("input[name='current_status']").val(),
					    		remarks: $row.find("input[name='remarks']").val(),
						    };
					    
					  
				    } /* Loss of cd/dvd */
				    else if(buttonContext == "lostIdEcrTble"){
				    	var untraceable_document_token = $row.find("input[name='untraceable_document_token']").val();
					    if (untraceable_document_token === "") {
					        alert("Please Enter Untraceable Document Token");
					        return;
					    }			    
					   
					    formData = {
					    		untraceable_document_token: untraceable_document_token, 
					    	    classification: $row.find("input[name='classification']").val(),
					    	    originator_doc_ecr: $row.find("input[name='originator_doc_ecr']").val(),  
					    	    custodian_doc_ecr: $row.find("input[name='custodian_doc_ecr']").val(),    
					    	    current_status: $row.find("input[name='current_status']").val(),
					    	    remarks: $row.find("input[name='remarks']").val()
						    };
					    
					  
				    } /* Loss of id and ecr token */
				    
				    else if(buttonContext == "regtFunds"){
				    	var nameOfAcct = $row.find("input[name='nameOfAcct']").val();
					    if (nameOfAcct === "") {
					        alert("Please Enter Name Of Acct");
					        return;
					    }			    
					   
					    formData = {
				    		nameOfAcct: nameOfAcct, 
				    		assetI: $row.find("input[name='assetI']").val(),
				    		liabilityI: $row.find("input[name='liabilityI']").val(),
				    		assetII: $row.find("input[name='assetII']").val(),
				    		liabilityII: $row.find("input[name='liabilityII']").val(),
				    		assetIII: $row.find("input[name='assetI']").val(),
				    		liabilityIII: $row.find("input[name='liabilityIII']").val(),
				    		incrDecrAcct: $row.find("input[name='incrDecrAcct']").val(),
				    		yearI: $('#year1').text(),
				    		yearII: $('#year2').text(),
				    		yearIII: $('#year3').text()
					    };
					    
				    } /* Regt Funds incl Offrs Mess */
		    
		    formData.buttonContext = buttonContext;	

		    $.ajax({
		        type: "POST",
		        url: "reportsAddMoreAction",
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
	  
	  

	  $(document).on("click", ".delete-from-recommended", function () {
		    var id = $(this).data("id");
		    var $button = $(this);
		    var buttonContext = $button.data("context");
		
		    
		    if (confirm("Are you sure you want to delete this record?")) {
		        $.ajax({
		            type: "POST",
		            url: "deleteTbleRowAction",
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
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.nature_deficiency + "</td>" +
			                   			"<td>" + item.action_deficiency + "</td>" +
			                   			"<td>" + item.effect_conduct + "</td>" +    	
			                   			"<td>" + item.remarks + "</td>" +
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                	});
							}
		                    
		                    if (tblname=="statepersonnel" || tblname=="statepersonnelDelete"){
		                    	$.each(data, function (index, item) {
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.manpower_deficiencyoffrs + "</td>" +
			                   			"<td>" + item.manpower_deficiencyjco + "</td>" +
			                   			"<td>" + item.manpower_deficiencyor + "</td>" +    
			                   			"<td>" + item.action_taken + "</td>" +
			                   			"<td>" + item.training_effect + "</td>" +    
			                   			"<td>" + item.remarks + "</td>" +
			                   			"</tr>";
			                   		$firstRow.before(rowHtml);
			                   	});
			                   }
		                    if (tblname=="statepersonnel1" || tblname=="statepersonnel1Delete"){
		                    	$.each(data, function (index, item) {
			                   		  var rowHtml = "<tr class='fade-in'>" +
			                   			"<td>" + (index + 1) + "</td>" +
			                   			"<td>" + item.ongoing_count + "</td>" +
			                   			"<td>" + item.pending_cases + "</td>" +
			                   			"<td>" + item.cases_current + "</td>" +  		                  
			                   			"<td>" + item.remarks + "</td>" +
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
    } else if (buttonContext === "state_weapon_item"){
    	var weaponb = $("#weaponb").val();
    	var weaponc = $("#weaponc").val();
        var user_remarks = $("#user_remarks3").val();
        
        formData.append("weaponb", weaponb);
        formData.append("weaponc", weaponc);
        formData.append("user_remarks3", user_remarks);
    } else if (buttonContext === "state_personnel_item"){
    	var personnelInput = $("#personnelInput").val();
        var user_remarks = $("#user_remarks4").val();
        
        formData.append("personnelInput", personnelInput);
        formData.append("user_remarks4", user_remarks);
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

		
		function doc_Path()
		 {
			var sus_no = '${sus_no}';
			
			$.post("getpathdoc_anl_rpt_extracts?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_anl_rpt_extracts").val(j);
				//console.log("Path pathdoc_anl_rpt_extracts --",j);	
			}).fail(function(xhr, textStatus, errorThrown) { });
			
			$.post("getpathdoc_anl_rpt_recruit_trg?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_recruit_trg").val(j);
			}).fail(function(xhr, textStatus, errorThrown) { });  
				
			

			$.post("getpathdoc_anl_rpt_difficulties_trg?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_difficulties_trg").val(j);
			}).fail(function(xhr, textStatus, errorThrown) { });
			
			$.post("getpathdoc_anl_rpt_emp_of_unit_during_period?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
				$("#getpathdoc_emp_of_unit_during_period").text(j);
			}).fail(function(xhr, textStatus, errorThrown) { }); 
		 }

</script>

<script>

var filePath;
var sus_no = '${sus_no}';
function getDownloadImageTransAmdt(filePath) {
	var filePath1 = document.getElementById("getpathdoc_anl_rpt_extracts").value;
	console.log("val of filepath " + filePath1)
    $.ajax({
    	type: 'POST',
        url: "DownloadPdfInspExtracts?"+key+"="+value,
        data: { filePath: filePath1 },
        xhrFields: {
            responseType: 'blob'
        },
        success: function (data, status, xhr) {
            const contentType = xhr.getResponseHeader('Content-Type');
            const blob = new Blob([data], { type: contentType });
            const downloadUrl = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = downloadUrl;
            a.download = sus_no; 
            document.body.appendChild(a);
            a.click();
            a.remove();
        },
        error: function () {
        	alert('No Document found!..');
        }
    });
}

function getDownloadEmpOfUnitDuringPeriod(filePath) {
	var filePath1 = document.getElementById("getpathdoc_emp_of_unit_during_period").value;
	//console.log("val od filepath " + filePath1)
    $.ajax({
    	type: 'POST',
        url: "DownloadPdfInspExtracts?"+key+"="+value,
        data: { filePath: filePath1 },
        xhrFields: {
            responseType: 'blob'
        },
        success: function (data, status, xhr) {
            const contentType = xhr.getResponseHeader('Content-Type');
            const blob = new Blob([data], { type: contentType });
            const downloadUrl = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = downloadUrl;
            a.download = sus_no; 
            document.body.appendChild(a);
            a.click();
            a.remove();
        },
        error: function () {
        	alert('No Document found!..');
        }
    });
}


function getDownloadDifficultiesTraining(filePath) {
	var filePath1 = document.getElementById("getpathdoc_difficulties_trg").value;
	//console.log("val od filepath " + filePath1)
    $.ajax({
    	type: 'POST',
        url: "DownloadPdfInspExtracts?"+key+"="+value,
        data: { filePath: filePath1 },
        xhrFields: {
            responseType: 'blob'
        },
        success: function (data, status, xhr) {
            const contentType = xhr.getResponseHeader('Content-Type');
            const blob = new Blob([data], { type: contentType });
            const downloadUrl = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = downloadUrl;
            a.download = sus_no; 
            document.body.appendChild(a);
            a.click();
            a.remove();
        },
        error: function () {
        	alert('No Document found!..');
        }
    });
}

function getDownloadRecruitTraining(filePath) {
	var filePath1 = document.getElementById("getpathdoc_recruit_trg").value;
	//console.log("val od filepath " + filePath1)
    $.ajax({
    	type: 'POST',
        url: "DownloadPdfInspExtracts?"+key+"="+value,
        data: { filePath: filePath1 },
        xhrFields: {
            responseType: 'blob'
        },
        success: function (data, status, xhr) {
            const contentType = xhr.getResponseHeader('Content-Type');
            const blob = new Blob([data], { type: contentType });
            const downloadUrl = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = downloadUrl;
            a.download = sus_no; 
            document.body.appendChild(a);
            a.click();
            a.remove();
        },
        error: function () {
            alert('No Document found!..');
        }
    });
}

</script>

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
		  
		  
		  	<c:url value="inspection_app_report" var="searchUrl" />
			<form:form action="${searchUrl}" method="get" id="Para1Form" name="Para1Form" >
			</form:form>
			<c:url value="inspection_app_report_2" var="searchUrl" />
			<form:form action="${searchUrl}" method="get" id="Para2Form" name="Para2Form" >
			</form:form>