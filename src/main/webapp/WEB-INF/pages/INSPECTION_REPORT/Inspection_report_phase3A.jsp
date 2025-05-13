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
		 <a href="merghesearchreport" class="btn btn-primary btn-sm" style="position: absolute; top: 10px; left: 10px;">Back</a>
			<h4>ADM INSP RPT</h4>
			
			<div class="col-md-12 mb_1" align="center">
					<div class="col-md-4"></div>
					<div class="col-md-4 ">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">

									<li class="nav-item" role="presentation">
									<button class="nav-link active" id="part3A" data-bs-toggle="tab"
										data-bs-target="#part3A_div" type="button" role="tab"
										aria-controls="profile" aria-selected="false"><b>Part III (A)</b></button>
								</li>
								
								
							</ul>
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>
		</div>
		<div class="card-body"   id="part1_div">
			<div class="ins_main">


<div class="ins_item clr1" id="individual_training_item" onclick="openModal('#individual_training')">Individual Training</div>
<div class="ins_item clr2" id="collective_training_item" onclick="openModal('#collective_training')">Collective Training</div>
<div class="ins_item clr3" id="management_training_item" onclick="openModal('#management_training')">Management of Training</div>
<div class="ins_item clr4" id="personnel_management_item" onclick="openModal('#personnel_management')">Personnel Management</div>
<div class="ins_item clr5" id="interior_economy_item" onclick="openModal('#interior_economy')">Interior Economy</div>
<div class="ins_item clr6" id="morale_motivation_item" onclick="openModal('#morale_motivation')">Morale and Motivation</div>
<div class="ins_item clr7" id="material_management_item" onclick="openModal('#material_management')">Material Management</div>
<div class="ins_item clr8" id="other_miscellaneous_aspects_item" onclick="openModal('#other_miscellaneous_aspects')">Other Miscellaneous Aspects</div>
<div class="ins_item clr9" id="measures_core_values_item" onclick="openModal('#measures_core_values')">Measures taken to imbibe the following Indian Army  Core Values</div>
<div class="ins_item clr10" id="human_resource_developement_item" onclick="openModal('#human_resource_developement')">Human Resource Developement</div>
<div class="ins_item clr11" id="additional_information_item" onclick="openModal('#additional_information')">Additional Information</div>
<div class="ins_item clr2" id="comments_insp_offr_item" onclick="openModal('#comments_insp_offr')">Comments of Insp Offr on Part II</div>

			</div>
			
		</div>
	</div>
</div>


<div class="modal fade" id="individual_training" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#individual_training')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Individual Training</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#collective_training')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="physicalTraining" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="physicalTraining" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Physical Training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pt_excellent" name="physicalTrainingRating" value="excellent">
                            <label class="form-check-label" for="pt_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pt_good" name="physicalTrainingRating" value="good">
                            <label class="form-check-label" for="pt_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pt_satisfactory" name="physicalTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="pt_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pt_needs_improvement" name="physicalTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="pt_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="weaponTraining" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Weapon Training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wt_excellent" name="weaponTrainingRating" value="excellent">
                            <label class="form-check-label" for="wt_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wt_good" name="weaponTrainingRating" value="good">
                            <label class="form-check-label" for="wt_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wt_satisfactory" name="weaponTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="wt_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wt_needs_improvement" name="weaponTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="wt_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="weaponTrainingResults" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Weapon Training Results
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wtr_excellent" name="weaponTrainingResultsRating" value="excellent">
                            <label class="form-check-label" for="wtr_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wtr_good" name="weaponTrainingResultsRating" value="good">
                            <label class="form-check-label" for="wtr_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wtr_satisfactory" name="weaponTrainingResultsRating" value="satisfactory">
                            <label class="form-check-label" for="wtr_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="wtr_needs_improvement" name="weaponTrainingResultsRating" value="needs_improvement">
                            <label class="form-check-label" for="wtr_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="nightTraining" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4) Night Training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="nt_excellent" name="nightTrainingRating" value="excellent">
                            <label class="form-check-label" for="nt_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="nt_good" name="nightTrainingRating" value="good">
                            <label class="form-check-label" for="nt_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="nt_satisfactory" name="nightTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="nt_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="nt_needs_improvement" name="nightTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="nt_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="specialistTraining" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Specialist Training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="st_excellent" name="specialistTrainingRating" value="excellent">
                            <label class="form-check-label" for="st_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="st_good" name="specialistTrainingRating" value="good">
                            <label class="form-check-label" for="st_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="st_satisfactory" name="specialistTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="st_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="st_needs_improvement" name="specialistTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="st_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="YOsTraining" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (6) YOs Training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="yost_excellent" name="yosTrainingRating" value="excellent">
                            <label class="form-check-label" for="yost_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="yost_good" name="yosTrainingRating" value="good">
                            <label class="form-check-label" for="yost_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="yost_satisfactory" name="yosTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="yost_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="yost_needs_improvement" name="yosTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="yost_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="officersTraining" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (7) Officers Training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ot_excellent" name="officersTrainingRating" value="excellent">
                            <label class="form-check-label" for="ot_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ot_good" name="officersTrainingRating" value="good">
                            <label class="form-check-label" for="ot_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ot_satisfactory" name="officersTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="ot_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ot_needs_improvement" name="officersTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="ot_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="trainingJCOsNCOs" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (8) Training of JCOs & NCOs
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tjn_excellent" name="jcoNcoTrainingRating" value="excellent">
                            <label class="form-check-label" for="tjn_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tjn_good" name="jcoNcoTrainingRating" value="good">
                            <label class="form-check-label" for="tjn_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tjn_satisfactory" name="jcoNcoTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="tjn_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tjn_needs_improvement" name="jcoNcoTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="tjn_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="trainingAccCommissionSCO" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (9) Training of ACC Commission/SCO
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tac_excellent" name="accCommissionTrainingRating" value="excellent">
                            <label class="form-check-label" for="tac_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tac_good" name="accCommissionTrainingRating" value="good">
                            <label class="form-check-label" for="tac_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tac_satisfactory" name="accCommissionTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="tac_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tac_needs_improvement" name="accCommissionTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="tac_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
 
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.individual_training != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve"  onclick="validate_Individual_training();" >Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>




<div class="modal fade" id="collective_training" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#individual_training')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Collective Training</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#management_training')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="collectiveForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="trainingExercise" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Training Exercise / Training Camp
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tec_excellent" name="exerciseTrainingRating" value="excellent">
                            <label class="form-check-label" for="tec_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tec_good" name="exerciseTrainingRating" value="good">
                            <label class="form-check-label" for="tec_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tec_satisfactory" name="exerciseTrainingRating" value="satisfactory">
                            <label class="form-check-label" for="tec_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tec_needs_improvement" name="exerciseTrainingRating" value="needs_improvement">
                            <label class="form-check-label" for="tec_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="fieldFiring" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Field Firing
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ff_excellent" name="fieldfiringRating" value="excellent">
                            <label class="form-check-label" for="ff_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ff_good" name="fieldfiringRating" value="good">
                            <label class="form-check-label" for="ff_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ff_satisfactory" name="fieldfiringRating" value="satisfactory">
                            <label class="form-check-label" for="ff_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ff_needs_improvement" name="fieldfiringRating" value="needs_improvement">
                            <label class="form-check-label" for="ff_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="mobilisation" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Mobilisation
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="m_excellent" name="mobilisationRating" value="excellent">
                            <label class="form-check-label" for="m_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="m_good" name="mobilisationRating" value="good">
                            <label class="form-check-label" for="m_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="m_satisfactory" name="mobilisationRating" value="satisfactory">
                            <label class="form-check-label" for="m_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="m_needs_improvement" name="mobilisationRating" value="needs_improvement">
                            <label class="form-check-label" for="m_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

            
            </div>
        </div>
    </div>
    <div class="modal-footer">
    
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.collective_training != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve"  onclick="validate_Collective_training();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>


<div class="modal fade" id="management_training" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#collective_training')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Management of Training</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#personnel_management')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="ManagementForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Use of Training Infrastructure Including Training Aids
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iit_excellent" name="trainingAidsRating" value="excellent">
                            <label class="form-check-label" for="iit_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iit_good" name="trainingAidsRating" value="good">
                            <label class="form-check-label" for="iit_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iit_satisfactory" name="trainingAidsRating" value="satisfactory">
                            <label class="form-check-label" for="iit_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iit_needs_improvement" name="trainingAidsRating" value="needs_improvement">
                            <label class="form-check-label" for="iit_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Special Points
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                          <input type="text" id="special_points" name="special_points"
                                           class="form-control form-control-sm">
                    </div>
                </div>
            
            </div>
        </div>
    </div>
    <div class="modal-footer">

        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.management_training != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve"  onclick="validate_management_training();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>


<div class="modal fade" id="personnel_management" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#management_training')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Personnel Management</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#interior_economy')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="personnelForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Discipline
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="e_excellent" name="disciplineRating" value="excellent">
                            <label class="form-check-label" for="e_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="e_good" name="disciplineRating" value="good">
                            <label class="form-check-label" for="e_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="e_satisfactory" name="disciplineRating" value="satisfactory">
                            <label class="form-check-label" for="e_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="e_needs_improvement" name="disciplineRating" value="needs_improvement">
                            <label class="form-check-label" for="e_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Health of Troops
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ht_excellent" name="HealthTroopsRating" value="excellent">
                            <label class="form-check-label" for="ht_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ht_good" name="HealthTroopsRating" value="good">
                            <label class="form-check-label" for="ht_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ht_satisfactory" name="HealthTroopsRating" value="satisfactory">
                            <label class="form-check-label" for="ht_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ht_needs_improvement" name="HealthTroopsRating" value="needs_improvement">
                            <label class="form-check-label" for="ht_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Personal Documentation
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pd_excellent" name="personalDocumentationRating" value="excellent">
                            <label class="form-check-label" for="pd_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pd_good" name="personalDocumentationRating" value="good">
                            <label class="form-check-label" for="pd_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pd_satisfactory" name="personalDocumentationRating" value="satisfactory">
                            <label class="form-check-label" for="pd_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pd_needs_improvement" name="personalDocumentationRating" value="needs_improvement">
                            <label class="form-check-label" for="pd_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

            
            </div>
        </div>
    </div>
    <div class="modal-footer">
        
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.personnel_management != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_personnel_management();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>


<div class="modal fade" id="interior_economy" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#personnel_management')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Interior Economy</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#morale_motivation')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="interiorForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Living Condition of All Ranks And Families
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="l_excellent" name="livingConditionRating" value="excellent">
                            <label class="form-check-label" for="l_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="l_good" name="livingConditionRating" value="good">
                            <label class="form-check-label" for="l_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="l_satisfactory" name="livingConditionRating" value="satisfactory">
                            <label class="form-check-label" for="l_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="l_needs_improvement" name="livingConditionRating" value="needs_improvement">
                            <label class="form-check-label" for="l_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) State of issue of personal clothing 
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="s_excellent" name="personalClothingRating" value="excellent">
                            <label class="form-check-label" for="s_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="s_good" name="personalClothingRating" value="good">
                            <label class="form-check-label" for="s_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="s_satisfactory" name="personalClothingRating" value="satisfactory">
                            <label class="form-check-label" for="s_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="s_needs_improvement" name="personalClothingRating" value="needs_improvement">
                            <label class="form-check-label" for="s_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Initiative taken by units to carry out improvement in their living habitat
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="i_excellent" name="livingHabitatRating" value="excellent">
                            <label class="form-check-label" for="i_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="i_good" name="livingHabitatRating" value="good">
                            <label class="form-check-label" for="i_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="i_satisfactory" name="livingHabitatRating" value="satisfactory">
                            <label class="form-check-label" for="i_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="i_needs_improvement" name="livingHabitatRating" value="needs_improvement">
                            <label class="form-check-label" for="i_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landUtilized" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4) Facilities provided  in the living area, in terms of comfort
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="f_excellent" name="facilitiesRating" value="excellent">
                            <label class="form-check-label" for="f_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="f_good" name="facilitiesRating" value="good">
                            <label class="form-check-label" for="f_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="f_satisfactory" name="facilitiesRating" value="satisfactory">
                            <label class="form-check-label" for="f_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="f_needs_improvement" name="facilitiesRating" value="needs_improvement">
                            <label class="form-check-label" for="f_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="vacantLandDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Modern equipment provided in the Company kitchens to facilitate  cooking as well as to  save manpower
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mm_excellent" name="modernEquipmentRating" value="excellent">
                            <label class="form-check-label" for="mm_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mm_good" name="modernEquipmentRating" value="good">
                            <label class="form-check-label" for="mm_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mm_satisfactory" name="modernEquipmentRating" value="satisfactory">
                            <label class="form-check-label" for="mm_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mm_needs_improvement" name="modernEquipmentRating" value="needs_improvement">
                            <label class="form-check-label" for="mm_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="safetyMeasuresAdequate" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (6) Equipment Procured by unit for area  maintenance to economise on manpower, that can be employed on operational duties or for training
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ep_excellent" name="EquipmentRating" value="excellent">
                            <label class="form-check-label" for="ep_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ep_good" name="EquipmentRating" value="good">
                            <label class="form-check-label" for="ep_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ep_satisfactory" name="EquipmentRating" value="satisfactory">
                            <label class="form-check-label" for="ep_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ep_needs_improvement" name="EquipmentRating" value="needs_improvement">
                            <label class="form-check-label" for="ep_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="evictionActionDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (7) Timely and correct Publication of individual casualties
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tc_excellent" name="timelyCorrectRating" value="excellent">
                            <label class="form-check-label" for="tc_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tc_good" name="timelyCorrectRating" value="good">
                            <label class="form-check-label" for="tc_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tc_satisfactory" name="timelyCorrectRating" value="satisfactory">
                            <label class="form-check-label" for="tc_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tc_needs_improvement" name="timelyCorrectRating" value="needs_improvement">
                            <label class="form-check-label" for="tc_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (8) State of individual Documentation
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="si_excellent" name="individualDocumentationRating" value="excellent">
                            <label class="form-check-label" for="si_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="si_good" name="individualDocumentationRating" value="good">
                            <label class="form-check-label" for="si_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="si_satisfactory" name="individualDocumentationRating" value="satisfactory">
                            <label class="form-check-label" for="si_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="si_needs_improvement" name="individualDocumentationRating" value="needs_improvement">
                            <label class="form-check-label" for="si_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

          
            </div>
        </div>
    </div>
    <div class="modal-footer">
    
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.interior_economy != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_interior_economy();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>

<div class="modal fade" id="morale_motivation" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#interior_economy')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Morale and Motivation</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#material_management')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="moralForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) State of Leave
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sl_excellent" name="stateLeaveRating" value="excellent">
                            <label class="form-check-label" for="sl_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sl_good" name="stateLeaveRating" value="good">
                            <label class="form-check-label" for="sl_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sl_satisfactory" name="stateLeaveRating" value="satisfactory">
                            <label class="form-check-label" for="sl_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sl_needs_improvement" name="stateLeaveRating" value="needs_improvement">
                            <label class="form-check-label" for="sl_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Discipline State
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="d_excellent" name="disciplineStateRating" value="excellent">
                            <label class="form-check-label" for="d_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="d_good" name="disciplineStateRating" value="good">
                            <label class="form-check-label" for="d_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="d_satisfactory" name="disciplineStateRating" value="satisfactory">
                            <label class="form-check-label" for="d_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="d_needs_improvement" name="disciplineStateRating" value="needs_improvement">
                            <label class="form-check-label" for="d_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) State of Sick Report
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ss_excellent" name="stateSickReportRating" value="excellent">
                            <label class="form-check-label" for="ss_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ss_good" name="stateSickReportRating" value="good">
                            <label class="form-check-label" for="ss_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ss_satisfactory" name="stateSickReportRating" value="satisfactory">
                            <label class="form-check-label" for="ss_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ss_needs_improvement" name="stateSickReportRating" value="needs_improvement">
                            <label class="form-check-label" for="ss_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landUtilized" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4) Performance in Trg /Professional Competitions
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pp_excellent" name="PerformanceTrgRating" value="excellent">
                            <label class="form-check-label" for="pp_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pp_good" name="PerformanceTrgRating" value="good">
                            <label class="form-check-label" for="pp_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pp_satisfactory" name="PerformanceTrgRating" value="satisfactory">
                            <label class="form-check-label" for="pp_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pp_needs_improvement" name="PerformanceTrgRating" value="needs_improvement">
                            <label class="form-check-label" for="pp_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="vacantLandDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Performance on Course
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pc_excellent" name="performanceCourseRating" value="excellent">
                            <label class="form-check-label" for="pc_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pc_good" name="performanceCourseRating" value="good">
                            <label class="form-check-label" for="pc_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pc_satisfactory" name="performanceCourseRating" value="satisfactory">
                            <label class="form-check-label" for="pc_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="pc_needs_improvement" name="performanceCourseRating" value="needs_improvement">
                            <label class="form-check-label" for="pc_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="safetyMeasuresAdequate" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (6) Performance in Sports
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ps_excellent" name="performanceSportsRating" value="excellent">
                            <label class="form-check-label" for="ps_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ps_good" name="performanceSportsRating" value="good">
                            <label class="form-check-label" for="ps_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ps_satisfactory" name="performanceSportsRating" value="satisfactory">
                            <label class="form-check-label" for="ps_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ps_needs_improvement" name="performanceSportsRating" value="needs_improvement">
                            <label class="form-check-label" for="ps_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="evictionActionDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (7) State of Eqpt Maint
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="se_excellent" name="stateEqptMaintRating" value="excellent">
                            <label class="form-check-label" for="se_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="se_good" name="stateEqptMaintRating" value="good">
                            <label class="form-check-label" for="se_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="se_satisfactory" name="stateEqptMaintRating" value="satisfactory">
                            <label class="form-check-label" for="se_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="se_needs_improvement" name="stateEqptMaintRating" value="needs_improvement">
                            <label class="form-check-label" for="se_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (8) Interior Economy
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ie_excellent" name="interiorEconomyRating" value="excellent">
                            <label class="form-check-label" for="ie_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ie_good" name="interiorEconomyRating" value="good">
                            <label class="form-check-label" for="ie_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ie_satisfactory" name="interiorEconomyRating" value="satisfactory">
                            <label class="form-check-label" for="ie_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ie_needs_improvement" name="interiorEconomyRating" value="needs_improvement">
                            <label class="form-check-label" for="ie_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
                
                     <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (9)Documentation
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="o_excellent" name="documentationRating" value="excellent">
                            <label class="form-check-label" for="o_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="o_good" name="documentationRating" value="good">
                            <label class="form-check-label" for="o_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="o_satisfactory" name="documentationRating" value="satisfactory">
                            <label class="form-check-label" for="o_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="o_needs_improvement" name="documentationRating" value="needs_improvement">
                            <label class="form-check-label" for="o_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
                
                     <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (10) Involvement of Junior Leader in unit/Formation tasks
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="uf_excellent" name="involvementRating" value="excellent">
                            <label class="form-check-label" for="uf_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="uf_good" name="involvementRating" value="good">
                            <label class="form-check-label" for="uf_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="uf_satisfactory" name="involvementRating" value="satisfactory">
                            <label class="form-check-label" for="uf_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="uf_needs_improvement" name="involvementRating" value="needs_improvement">
                            <label class="form-check-label" for="uf_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
                
                     <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (11) State of Regimental ­- Institutions
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sr_excellent" name="regimentalRating" value="excellent">
                            <label class="form-check-label" for="sr_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sr_good" name="regimentalRating" value="good">
                            <label class="form-check-label" for="sr_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sr_satisfactory" name="regimentalRating" value="satisfactory">
                            <label class="form-check-label" for="sr_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sr_needs_improvement" name="regimentalRating" value="needs_improvement">
                            <label class="form-check-label" for="sr_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
                
                     <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (12) Pers Discipline and Turnout
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="dt_excellent" name="persDisciplineRating" value="excellent">
                            <label class="form-check-label" for="dt_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="dt_good" name="persDisciplineRating" value="good">
                            <label class="form-check-label" for="dt_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="dt_satisfactory" name="persDisciplineRating" value="satisfactory">
                            <label class="form-check-label" for="dt_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="dt_needs_improvement" name="persDisciplineRating" value="needs_improvement">
                            <label class="form-check-label" for="dt_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

          
            </div>
        </div>
    </div>
    <div class="modal-footer">
   
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.morale_motivation != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_morale_motivation();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>


<div class="modal fade" id="material_management" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#morale_motivation')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Material Management</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#other_miscellaneous_aspects')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="materialForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Vehicles
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="v_excellent" name="VehiclesRating" value="excellent">
                            <label class="form-check-label" for="v_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="v_good" name="VehiclesRating" value="good">
                            <label class="form-check-label" for="v_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="v_satisfactory" name="VehiclesRating" value="satisfactory">
                            <label class="form-check-label" for="v_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="v_needs_improvement" name="VehiclesRating" value="needs_improvement">
                            <label class="form-check-label" for="v_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Eqpt including off rd eqpt for more than six months
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="er_excellent" name="eqptIncludingRating" value="excellent">
                            <label class="form-check-label" for="er_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="er_good" name="eqptIncludingRating" value="good">
                            <label class="form-check-label" for="er_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="er_satisfactory" name="eqptIncludingRating" value="satisfactory">
                            <label class="form-check-label" for="er_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="er_needs_improvement" name="eqptIncludingRating" value="needs_improvement">
                            <label class="form-check-label" for="er_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Maintenance of Arms
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ma_excellent" name="maintenanceArmsRating" value="excellent">
                            <label class="form-check-label" for="ma_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ma_good" name="maintenanceArmsRating" value="good">
                            <label class="form-check-label" for="ma_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ma_satisfactory" name="maintenanceArmsRating" value="satisfactory">
                            <label class="form-check-label" for="ma_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ma_needs_improvement" name="maintenanceArmsRating" value="needs_improvement">
                            <label class="form-check-label" for="ma_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landUtilized" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4) Maintenance of Amn
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mn_excellent" name="maintenanceAmnRating" value="excellent">
                            <label class="form-check-label" for="mn_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mn_good" name="maintenanceAmnRating" value="good">
                            <label class="form-check-label" for="mn_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mn_satisfactory" name="maintenanceAmnRating" value="satisfactory">
                            <label class="form-check-label" for="mn_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="mn_needs_improvement" name="maintenanceAmnRating" value="needs_improvement">
                            <label class="form-check-label" for="mn_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="vacantLandDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Maintenance of Ordnance Stores
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sm_excellent" name="maintenanceOrdnanceStoresRating" value="excellent">
                            <label class="form-check-label" for="sm_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sm_good" name="maintenanceOrdnanceStoresRating" value="good">
                            <label class="form-check-label" for="sm_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sm_satisfactory" name="maintenanceOrdnanceStoresRating" value="satisfactory">
                            <label class="form-check-label" for="sm_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sm_needs_improvement" name="maintenanceOrdnanceStoresRating" value="needs_improvement">
                            <label class="form-check-label" for="sm_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="safetyMeasuresAdequate" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (6) Management of Public Funds & Financial Grants
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="fg_excellent" name="PublicFundsRating" value="excellent">
                            <label class="form-check-label" for="fg_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="fg_good" name="PublicFundsRating" value="good">
                            <label class="form-check-label" for="fg_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="fg_satisfactory" name="PublicFundsRating" value="satisfactory">
                            <label class="form-check-label" for="fg_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="fg_needs_improvement" name="PublicFundsRating" value="needs_improvement">
                            <label class="form-check-label" for="fg_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

            
            </div>
        </div>
    </div>
    <div class="modal-footer">
    
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.material_management != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_material_management();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>

<div class="modal fade" id="other_miscellaneous_aspects" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#material_management')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Other Miscellaneous Aspects</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#measures_core_values')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="miscellaneousForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Security
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sw_excellent" name="SecurityRating" value="excellent">
                            <label class="form-check-label" for="sw_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sw_good" name="SecurityRating" value="good">
                            <label class="form-check-label" for="sw_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sw_satisfactory" name="SecurityRating" value="satisfactory">
                            <label class="form-check-label" for="sw_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sw_needs_improvement" name="SecurityRating" value="needs_improvement">
                            <label class="form-check-label" for="sw_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

            
            </div>
        </div>
    </div>
    <div class="modal-footer">
       
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.other_miscellaneous_aspects != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_other_miscellaneous_aspects();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>

<div class="modal fade" id="measures_core_values" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#other_miscellaneous_aspects')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Measures taken to imbibe the following Indian Army  Core Values</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#human_resource_developement')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="measuresForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Integrity (Imaandari)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="integrity_excellent" name="integrityRating" value="excellent">
                            <label class="form-check-label" for="integrity_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="integrity_good" name="integrityRating" value="good">
                            <label class="form-check-label" for="integrity_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="integrity_satisfactory" name="integrityRating" value="satisfactory">
                            <label class="form-check-label" for="integrity_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="integrity_needs_improvement" name="integrityRating" value="needs_improvement">
                            <label class="form-check-label" for="integrity_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Loyalty (Wafadari)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="loyalty_excellent" name="loyaltyRating" value="excellent">
                            <label class="form-check-label" for="loyalty_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="loyalty_good" name="loyaltyRating" value="good">
                            <label class="form-check-label" for="loyalty_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="loyalty_satisfactory" name="loyaltyRating" value="satisfactory">
                            <label class="form-check-label" for="loyalty_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="loyalty_needs_improvement" name="loyaltyRating" value="needs_improvement">
                            <label class="form-check-label" for="loyalty_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Duty (Kartavya)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="duty_excellent" name="dutyRating" value="excellent">
                            <label class="form-check-label" for="duty_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="duty_good" name="dutyRating" value="good">
                            <label class="form-check-label" for="duty_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="duty_satisfactory" name="dutyRating" value="satisfactory">
                            <label class="form-check-label" for="duty_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="duty_needs_improvement" name="dutyRating" value="needs_improvement">
                            <label class="form-check-label" for="duty_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landUtilized" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4)Respect (Samman)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="respect_excellent" name="respectRating" value="excellent">
                            <label class="form-check-label" for="respect_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="respect_good" name="respectRating" value="good">
                            <label class="form-check-label" for="respect_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="respect_satisfactory" name="respectRating" value="satisfactory">
                            <label class="form-check-label" for="respect_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="respect_needs_improvement" name="respectRating" value="needs_improvement">
                            <label class="form-check-label" for="respect_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="vacantLandDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Selfless Service (Niswarth Seva)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sf_excellent" name="selflessRating" value="excellent">
                            <label class="form-check-label" for="sf_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sf_good" name="selflessRating" value="good">
                            <label class="form-check-label" for="sf_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sf_satisfactory" name="selflessRating" value="satisfactory">
                            <label class="form-check-label" for="sf_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="sf_needs_improvement" name="selflessRating" value="needs_improvement">
                            <label class="form-check-label" for="sf_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="safetyMeasuresAdequate" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (6) Courage (Himmat)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="hi_excellent" name="courageRating" value="excellent">
                            <label class="form-check-label" for="hi_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="hi_good" name="courageRating" value="good">
                            <label class="form-check-label" for="hi_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="hi_satisfactory" name="courageRating" value="satisfactory">
                            <label class="form-check-label" for="hi_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="hi_needs_improvement" name="courageRating" value="needs_improvement">
                            <label class="form-check-label" for="hi_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="evictionActionDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (7) Honour (Izzat)
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iz_excellent" name="honourRating" value="excellent">
                            <label class="form-check-label" for="iz_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iz_good" name="honourRating" value="good">
                            <label class="form-check-label" for="iz_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iz_satisfactory" name="honourRating" value="satisfactory">
                            <label class="form-check-label" for="iz_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="iz_needs_improvement" name="honourRating" value="needs_improvement">
                            <label class="form-check-label" for="iz_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="modal-footer">
     
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.measures_core_values != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_measures_taken();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>


<div class="modal fade" id="human_resource_developement" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#measures_core_values')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Human Resource Developement</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#additional_information')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="humanForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Junior Leader Development
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="junior_excellent" name="juniorRating" value="excellent">
                            <label class="form-check-label" for="junior_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="junior_good" name="juniorRating" value="good">
                            <label class="form-check-label" for="junior_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="junior_satisfactory" name="juniorRating" value="satisfactory">
                            <label class="form-check-label" for="junior_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="junior_needs_improvement" name="juniorRating" value="needs_improvement">
                            <label class="form-check-label" for="junior_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) Welfare of Troops
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="of_excellent" name="welfareRating" value="excellent">
                            <label class="form-check-label" for="of_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="of_good" name="welfareRating" value="good">
                            <label class="form-check-label" for="of_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="of_satisfactory" name="welfareRating" value="satisfactory">
                            <label class="form-check-label" for="of_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="of_needs_improvement" name="welfareRating" value="needs_improvement">
                            <label class="form-check-label" for="of_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) Measures for resolution of individual problems of soldiers
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="for_excellent" name="measuresRating" value="excellent">
                            <label class="form-check-label" for="for_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="for_good" name="measuresRating" value="good">
                            <label class="form-check-label" for="for_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="for_satisfactory" name="measuresRating" value="satisfactory">
                            <label class="form-check-label" for="for_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="for_needs_improvement" name="measuresRating" value="needs_improvement">
                            <label class="form-check-label" for="for_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landUtilized" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4) Enhancement of education qualifications in the unit 
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="in_excellent" name="enhancementRating" value="excellent">
                            <label class="form-check-label" for="in_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="in_good" name="enhancementRating" value="good">
                            <label class="form-check-label" for="in_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="in_satisfactory" name="enhancementRating" value="satisfactory">
                            <label class="form-check-label" for="in_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="in_needs_improvement" name="enhancementRating" value="needs_improvement">
                            <label class="form-check-label" for="in_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="vacantLandDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Training for career enhancement with respect to ACC/SCO Commission
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="to_excellent" name="trainingRating" value="excellent">
                            <label class="form-check-label" for="to_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="to_good" name="trainingRating" value="good">
                            <label class="form-check-label" for="to_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="to_satisfactory" name="trainingRating" value="satisfactory">
                            <label class="form-check-label" for="to_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="to_needs_improvement" name="trainingRating" value="needs_improvement">
                            <label class="form-check-label" for="to_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="safetyMeasuresAdequate" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (6) Trainin provided for sports/ professional activities like SA Firing etc to the tps to excel at Army/Service level
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="po_excellent" name="serviceLevelRating" value="excellent">
                            <label class="form-check-label" for="po_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="po_good" name="serviceLevelRating" value="good">
                            <label class="form-check-label" for="po_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="po_satisfactory" name="serviceLevelRating" value="satisfactory">
                            <label class="form-check-label" for="po_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="po_needs_improvement" name="serviceLevelRating" value="needs_improvement">
                            <label class="form-check-label" for="po_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="evictionActionDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (7) Audit Objection
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="audit_excellent" name="auditRating" value="excellent">
                            <label class="form-check-label" for="audit_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="audit_good" name="auditRating" value="good">
                            <label class="form-check-label" for="audit_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="audit_satisfactory" name="auditRating" value="satisfactory">
                            <label class="form-check-label" for="audit_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="audit_needs_improvement" name="auditRating" value="needs_improvement">
                            <label class="form-check-label" for="audit_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (8) State of Complaints 
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tj_excellent" name="complaintsRating" value="excellent">
                            <label class="form-check-label" for="tj_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tj_good" name="complaintsRating" value="good">
                            <label class="form-check-label" for="tj_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tj_satisfactory" name="complaintsRating" value="satisfactory">
                            <label class="form-check-label" for="tj_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="tj_needs_improvement" name="complaintsRating" value="needs_improvement">
                            <label class="form-check-label" for="tj_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (9) ACR Related Aspects
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ta_excellent" name="relatedRating" value="excellent">
                            <label class="form-check-label" for="ta_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ta_good" name="relatedRating" value="good">
                            <label class="form-check-label" for="ta_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ta_satisfactory" name="relatedRating" value="satisfactory">
                            <label class="form-check-label" for="ta_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="ta_needs_improvement" name="relatedRating" value="needs_improvement">
                            <label class="form-check-label" for="ta_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
                    <div class="form-group row">
                    <label for="remarksSuggestions" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (10) Miscellaneous Aspects
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="xl_excellent" name="miscellaneousRating" value="excellent">
                            <label class="form-check-label" for="xl_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="xl_good" name="miscellaneousRating" value="good">
                            <label class="form-check-label" for="xl_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="xl_satisfactory" name="miscellaneousRating" value="satisfactory">
                            <label class="form-check-label" for="xl_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="xl_needs_improvement" name="miscellaneousRating" value="needs_improvement">
                            <label class="form-check-label" for="xl_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    <div class="modal-footer">

        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.human_resource_developement != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_human_resource();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>



<div class="modal fade" id="additional_information" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#human_resource_developement')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Additional Information</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#comments_insp_offr')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="additionalForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) State of all Public Funds
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="public_excellent" name="publicFundsRating" value="excellent">
                            <label class="form-check-label" for="public_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="public_good" name="publicFundsRating" value="good">
                            <label class="form-check-label" for="public_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="public_satisfactory" name="publicFundsRating" value="satisfactory">
                            <label class="form-check-label" for="public_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="public_needs_improvement" name="publicFundsRating" value="needs_improvement">
                            <label class="form-check-label" for="public_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landRecordRegisterMaintained" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (2) State of discp in the fmn incl state pending GCMs and DCMs, as applicable
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="the_excellent" name="stateDiscpRating" value="excellent">
                            <label class="form-check-label" for="the_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="the_good" name="stateDiscpRating" value="good">
                            <label class="form-check-label" for="the_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="the_satisfactory" name="stateDiscpRating" value="satisfactory">
                            <label class="form-check-label" for="the_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="the_needs_improvement" name="stateDiscpRating" value="needs_improvement">
                            <label class="form-check-label" for="the_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landDemarcated" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (3) State of complaints and petitions
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="state_excellent" name="stateComplaintsPetitionsRating" value="excellent">
                            <label class="form-check-label" for="state_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="state_good" name="stateComplaintsPetitionsRating" value="good">
                            <label class="form-check-label" for="state_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="state_satisfactory" name="stateComplaintsPetitionsRating" value="satisfactory">
                            <label class="form-check-label" for="state_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="state_needs_improvement" name="stateComplaintsPetitionsRating" value="needs_improvement">
                            <label class="form-check-label" for="state_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="landUtilized" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (4) State of work in the Formation 
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="excellent_excellent" name="stateWorkRating" value="excellent">
                            <label class="form-check-label" for="excellent_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="excellent_good" name="stateWorkRating" value="good">
                            <label class="form-check-label" for="excellent_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="excellent_satisfactory" name="stateWorkRating" value="satisfactory">
                            <label class="form-check-label" for="excellent_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="excellent_needs_improvement" name="stateWorkRating" value="needs_improvement">
                            <label class="form-check-label" for="excellent_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="vacantLandDetails" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (5) Any peculiar aspect observed
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="any_excellent" name="anyPeculiarRating" value="excellent">
                            <label class="form-check-label" for="any_excellent">Excellent</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="any_good" name="anyPeculiarRating" value="good">
                            <label class="form-check-label" for="any_good">Good</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="any_satisfactory" name="anyPeculiarRating" value="satisfactory">
                            <label class="form-check-label" for="any_satisfactory">Satisfactory</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="any_needs_improvement" name="anyPeculiarRating" value="needs_improvement">
                            <label class="form-check-label" for="any_needs_improvement">Needs Improvement</label>
                        </div>
                    </div>
                </div>

             
            </div>
        </div>
    </div>
    <div class="modal-footer">
      
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.additional_information != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_additional_information();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>

<div class="modal fade" id="comments_insp_offr" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            
            <button class="btn btn-sm"
					onclick="openModal('#additional_information')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">Comments of Insp Offr on Part II</h5>
			<button class="btn btn-sm" id="next"
					onclick="openModal('#comments_insp_offr')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

                <button type="button" class="close btn-close" onclick="closeModal()">
                    <span aria-hidden="true">×</span>
                </button>

            </div>
  <form id="additionalForm" method="POST">  <!-- Added ID to the form -->
    <div class="modal-body">
        <div class="col-md-12" id="getSearch_Letter" style="display: block;">
            <div class="watermarked" data-watermark="" id="divwatermark">
                <span id="ip"></span>

                <div class="form-group row">
                    <label for="defenceLandParticulars" class="col-md-4 col-form-label">  <!-- Reduced label width -->
                        (1) Comments of Insp Offr on Part II
                    </label>
                    <div class="col-md-8">  <!-- Increased input width -->
                        <input type="text" id="state_ii" name="state_ii" class="form-control form-control-sm">
                    </div>
                </div>
             
            </div>
        </div>
    </div>
     <div class="modal-footer">
      
        <div class="col-6 text-right">
            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
             <c:if test="${report.comments_insp_offr != 1}">
            <button type="button" class="btn btn-primary btn-sm add-to-submit_approve" onclick="validate_cmt_on_partii();">Submit for Approval</button>
            </c:if>
        </div>
    </div>
</form>
        </div>
    </div>
</div>


<input type="hidden" name="physicalTraining" id="physicalTraining">

<script type="text/javascript">

$(document).ready(function() {
  $('input[type="checkbox"]').on('change', function() {debugger;
    const name = $(this).attr('name');
    
    if ($(this).prop('checked')) {
      $('input[name="' + name + '"]').not(this).prop('checked', false);
    }
  });
  
  const checkboxGroups = {};
  
  $('input[type="checkbox"]').each(function() {
    const name = $(this).attr('name');
    if (!checkboxGroups[name]) {
      checkboxGroups[name] = true;
      
      const checked = $('input[name="' + name + '"]:checked');
      if (checked.length > 1) {
        checked.not(checked.first()).prop('checked', false);
      }
    }
  });
});

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


function openModal(modalId) {

	if (modalId == '#Establishment') {
		GetData('establishment_report_url', modalId);
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
	fetchFormData(modalId);
}


function closeModal() {debugger
	var modals = document.querySelectorAll('.modal.show');
	modals.forEach(function(modal) {
		modal.classList.remove('show');
		modal.style.display = 'none'; // Hide the modal
	});
}


$("#part1").click(function() {
	// Show profile tab and hide home tab
	$("#part1_div").show().removeClass('display_none').addClass('active');
	$("#part2_div ").hide().addClass('display_none').removeClass('active');
	$("#part3A_div").hide().addClass('display_none').removeClass('active'); // Added hiding for part 3A

	// Update tab states
	$("#part1").addClass('active');
	$("#part2").removeClass('active');
	$("#part3A").removeClass('active'); // Added deactivation for part 3A button

});


function validate_Individual_training() {
	var sus_no='${sus_no}';
	var physicalTrainingRating = $("input[name='physicalTrainingRating']:checked").val();
	var weaponTrainingRating = $("input[name='weaponTrainingRating']:checked").val();
	var weaponTrainingResultsRating = $("input[name='weaponTrainingResultsRating']:checked").val();
	var nightTrainingRating = $("input[name='nightTrainingRating']:checked").val();
	var specialistTrainingRating = $("input[name='specialistTrainingRating']:checked").val();
	var yosTrainingRating = $("input[name='yosTrainingRating']:checked").val();
	var officersTrainingRating = $("input[name='officersTrainingRating']:checked").val();
	var jcoNcoTrainingRating = $("input[name='jcoNcoTrainingRating']:checked").val();
	var accCommissionTrainingRating = $("input[name='accCommissionTrainingRating']:checked").val();

	// 		var ind_training_remarks = $("#ind_training_remarks").val();
	$.post('Change_of_Individual_training?' + key + "=" + value, {
		physicalTrainingRating : physicalTrainingRating,
		weaponTrainingRating : weaponTrainingRating,
		weaponTrainingResultsRating : weaponTrainingResultsRating,
		nightTrainingRating : nightTrainingRating,
		specialistTrainingRating : specialistTrainingRating,
		yosTrainingRating : yosTrainingRating,
		officersTrainingRating : officersTrainingRating,
		jcoNcoTrainingRating : jcoNcoTrainingRating,
		accCommissionTrainingRating : accCommissionTrainingRating,sus_no:sus_no
	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		}else
			alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function validate_Collective_training() {
	var sus_no='${sus_no}';
	var exerciseTrainingRating = $(
			"input[name='exerciseTrainingRating']:checked").val();
	var fieldfiringRating = $("input[name='fieldfiringRating']:checked")
			.val();
	var mobilisationRating = $("input[name='mobilisationRating']:checked")
			.val();

	$.post('Change_of_Collective_training?' + key + "=" + value, {
		exerciseTrainingRating : exerciseTrainingRating,
		fieldfiringRating : fieldfiringRating,
		mobilisationRating : mobilisationRating,sus_no:sus_no

	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} else
			alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function validate_management_training() {
	var sus_no='${sus_no}';
	var trainingAidsRating = $("input[name='trainingAidsRating']:checked").val();
	var special_points = $("#special_points").val();

	$.post('Change_of_Management_Training?' + key + "=" + value, {
		trainingAidsRating : trainingAidsRating,
		special_points : special_points,sus_no:sus_no
	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		}else
			alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function validate_personnel_management() {
	var sus_no='${sus_no}';
	var disciplineRating = $("input[name='disciplineRating']:checked").val();
	var HealthTroopsRating = $("input[name='HealthTroopsRating']:checked").val();
	var personalDocumentationRating = $("input[name='personalDocumentationRating']:checked").val();

	$.post('Change_of_Personnel_Management?' + key + "=" + value, {
		disciplineRating : disciplineRating,
		HealthTroopsRating : HealthTroopsRating,
		personalDocumentationRating : personalDocumentationRating,sus_no:sus_no
	},function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} 
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function validate_interior_economy() {
	var sus_no='${sus_no}';
	var livingConditionRating = $("input[name='livingConditionRating']:checked").val();
	var personalClothingRating = $("input[name='personalClothingRating']:checked").val();
	var livingHabitatRating = $("input[name='livingHabitatRating']:checked").val();
	var facilitiesRating = $("input[name='facilitiesRating']:checked").val();
	var modernEquipmentRating = $("input[name='modernEquipmentRating']:checked").val();
	var EquipmentRating = $("input[name='EquipmentRating']:checked").val();
	var timelyCorrectRating = $("input[name='timelyCorrectRating']:checked").val();
	var individualDocumentationRating = $("input[name='individualDocumentationRating']:checked").val();

	$.post('Change_of_Interior_Economy?' + key + "=" + value, {
		livingConditionRating : livingConditionRating,
		personalClothingRating : personalClothingRating,
		livingHabitatRating : livingHabitatRating,
		facilitiesRating : facilitiesRating,
		modernEquipmentRating : modernEquipmentRating,
		EquipmentRating : EquipmentRating,
		timelyCorrectRating : timelyCorrectRating,
		individualDocumentationRating : individualDocumentationRating,sus_no:sus_no
	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function validate_morale_motivation() {
	var sus_no='${sus_no}';
	var stateLeaveRating = $("input[name='stateLeaveRating']:checked").val();
	var disciplineStateRating = $("input[name='disciplineStateRating']:checked").val();
	var stateSickReportRating = $("input[name='stateSickReportRating']:checked").val();
	var PerformanceTrgRating = $("input[name='PerformanceTrgRating']:checked").val();
	var performanceCourseRating = $("input[name='performanceCourseRating']:checked").val();
	var performanceSportsRating = $("input[name='performanceSportsRating']:checked").val();
	var stateEqptMaintRating = $("input[name='stateEqptMaintRating']:checked").val();
	var interiorEconomyRating = $("input[name='interiorEconomyRating']:checked").val();

	var documentationRating = $("input[name='documentationRating']:checked").val();
	var involvementRating = $("input[name='involvementRating']:checked").val();
	var regimentalRating = $("input[name='regimentalRating']:checked").val();
	var persDisciplineRating = $("input[name='persDisciplineRating']:checked").val();
	$.post('Change_of_Morale_Motivation?' + key + "=" + value, {
		stateLeaveRating : stateLeaveRating,
		disciplineStateRating : disciplineStateRating,
		stateSickReportRating : stateSickReportRating,
		PerformanceTrgRating : PerformanceTrgRating,
		performanceCourseRating : performanceCourseRating,
		performanceSportsRating : performanceSportsRating,
		stateEqptMaintRating : stateEqptMaintRating,
		interiorEconomyRating : interiorEconomyRating,
		documentationRating : documentationRating,
		involvementRating : involvementRating,
		regimentalRating : regimentalRating,
		persDisciplineRating : persDisciplineRating,sus_no:sus_no
	},function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}


function validate_material_management() {

	var VehiclesRating = $("input[name='VehiclesRating']:checked").val();
	var eqptIncludingRating = $("input[name='eqptIncludingRating']:checked").val();
	var maintenanceArmsRating = $("input[name='maintenanceArmsRating']:checked").val();
	var maintenanceAmnRating = $("input[name='maintenanceAmnRating']:checked").val();
	var maintenanceOrdnanceStoresRating = $("input[name='maintenanceOrdnanceStoresRating']:checked").val();
	var PublicFundsRating = $("input[name='PublicFundsRating']:checked").val();
	var sus_no='${sus_no}';
	$.post('Change_of_Material_Management?' + key + "=" + value, {

		VehiclesRating : VehiclesRating,
		eqptIncludingRating : eqptIncludingRating,
		maintenanceArmsRating : maintenanceArmsRating,
		maintenanceAmnRating : maintenanceAmnRating,
		maintenanceOrdnanceStoresRating : maintenanceOrdnanceStoresRating,
		PublicFundsRating : PublicFundsRating,sus_no:sus_no

	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} 
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function validate_other_miscellaneous_aspects() {
	
	var SecurityRating = $("input[name='SecurityRating']:checked").val();
	var sus_no='${sus_no}';
	$.post('Change_of_Other_Miscellaneous_Aspects?' + key + "=" + value, {

		SecurityRating : SecurityRating,sus_no:sus_no

	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} 
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function validate_measures_taken() {
	var sus_no='${sus_no}';
	var integrityRating = $("input[name='integrityRating']:checked").val();
	var loyaltyRating = $("input[name='loyaltyRating']:checked").val();
	var dutyRating = $("input[name='dutyRating']:checked").val();
	var respectRating = $("input[name='respectRating']:checked").val();
	var selflessRating = $("input[name='selflessRating']:checked").val();
	var courageRating = $("input[name='courageRating']:checked").val();
	var honourRating = $("input[name='honourRating']:checked").val();

	$.post('Change_of_Measures_Taken?' + key + "=" + value, {

		integrityRating : integrityRating,
		loyaltyRating : loyaltyRating,
		dutyRating : dutyRating,
		respectRating : respectRating,
		selflessRating : selflessRating,
		courageRating : courageRating,
		honourRating : honourRating,sus_no:sus_no

	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} 
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function validate_human_resource() {

	var juniorRating = $("input[name='juniorRating']:checked").val();
	var welfareRating = $("input[name='welfareRating']:checked").val();
	var measuresRating = $("input[name='measuresRating']:checked").val();
	var enhancementRating = $("input[name='enhancementRating']:checked").val();
	var trainingRating = $("input[name='trainingRating']:checked").val();
	var serviceLevelRating = $("input[name='serviceLevelRating']:checked").val();
	var auditRating = $("input[name='auditRating']:checked").val();
	var complaintsRating = $("input[name='complaintsRating']:checked").val();
	var relatedRating = $("input[name='relatedRating']:checked").val();
	var miscellaneousRating = $("input[name='miscellaneousRating']:checked").val();
	var sus_no='${sus_no}';
	$.post('Change_of_Human_Resource?' + key + "=" + value, {

		juniorRating : juniorRating,
		welfareRating : welfareRating,
		measuresRating : measuresRating,
		enhancementRating : enhancementRating,
		trainingRating : trainingRating,
		serviceLevelRating : serviceLevelRating,
		auditRating : auditRating,
		complaintsRating : complaintsRating,
		relatedRating : relatedRating,
		miscellaneousRating : miscellaneousRating,sus_no:sus_no

	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} 	
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function validate_additional_information() {

	var publicFundsRating = $("input[name='publicFundsRating']:checked").val();
	var stateDiscpRating = $("input[name='stateDiscpRating']:checked").val();
	var stateComplaintsPetitionsRating = $("input[name='stateComplaintsPetitionsRating']:checked").val();
	var stateWorkRating = $("input[name='stateWorkRating']:checked").val();
	var anyPeculiarRating = $("input[name='anyPeculiarRating']:checked").val();
	var sus_no='${sus_no}';
	$.post('Change_of_Additional_Information?' + key + "=" + value, {

		publicFundsRating : publicFundsRating,
		stateDiscpRating : stateDiscpRating,
		stateComplaintsPetitionsRating : stateComplaintsPetitionsRating,
		stateWorkRating : stateWorkRating,
		anyPeculiarRating : anyPeculiarRating,sus_no:sus_no

	},function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} else
			alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function validate_cmt_on_partii() {

	var cmt = $("#state_ii").val();
var sus_no='${sus_no}';

	$.post('Change_of_cmts_on_partii?' + key + "=" + value, {

		cmt : cmt,sus_no:sus_no

	}, function(data) {
		if (parseInt(data) > 0) {
			$('#ch_r_id').val(data);
			alert("Data Saved Successfully")
		} else
			alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}



function fetchFormData(formType) {
// 	  clearFormSelections(formType);
var sus_no='${sus_no}';
	  $.ajax({
	    url: 'Fetch_Form_Data',
	    type: 'GET',
	    data: {
	      formType: formType,
	      sus_no:sus_no
	    },
	    dataType: 'json',
	    success: function(response) {
	      if (response && response.status === "success") {
	        // Populate the form with retrieved data
	        populateFormData(formType, response.data);
	      }
	    },
	    error: function(xhr, status, error) {
	      console.error("Error fetching data: " + error);
	      alert("Failed to fetch saved data. Please try again.");
	    },
	    complete: function() {
	    }
	  });
	}


function populateFormData(formType, data) {debugger
	  switch(formType) {
	  case '#individual_training':
	      if (data.nightTrainingRating) {
	        $("input[name='nightTrainingRating'][value='" + data.nightTrainingRating + "']").prop('checked', true);
	      }
	      if (data.officersTrainingRating) {
	        $("input[name='officersTrainingRating'][value='" + data.officersTrainingRating + "']").prop('checked', true);
	      }
	      if (data.physicalTrainingRating) {
	        $("input[name='physicalTrainingRating'][value='" + data.physicalTrainingRating + "']").prop('checked', true);
	      }
	      if (data.specialistTrainingRating) {
	        $("input[name='specialistTrainingRating'][value='" + data.specialistTrainingRating + "']").prop('checked', true);
	      }
	      if (data.accCommissionTrainingRating) {
	        $("input[name='accCommissionTrainingRating'][value='" + data.accCommissionTrainingRating + "']").prop('checked', true);
	      }
	      if (data.jcoNcoTrainingRating) {
	        $("input[name='jcoNcoTrainingRating'][value='" + data.jcoNcoTrainingRating + "']").prop('checked', true);
	      }
	      if (data.weaponTrainingRating) {
	        $("input[name='weaponTrainingRating'][value='" + data.weaponTrainingRating + "']").prop('checked', true);
	      }
	      if (data.weaponTrainingResultsRating) {
		        $("input[name='weaponTrainingResultsRating'][value='" + data.weaponTrainingResultsRating + "']").prop('checked', true);
		      }
	      if (data.yosTrainingRating) {
		        $("input[name='yosTrainingRating'][value='" + data.yosTrainingRating + "']").prop('checked', true);
		      }
	      break;
	      
	  case '#collective_training':
	      if (data.fieldfiringRating) {
	        $("input[name='fieldfiringRating'][value='" + data.fieldfiringRating + "']").prop('checked', true);
	      }
	      if (data.mobilisationRating) {
	        $("input[name='mobilisationRating'][value='" + data.mobilisationRating + "']").prop('checked', true);
	      }
	      if (data.exerciseTrainingRating) {
	        $("input[name='exerciseTrainingRating'][value='" + data.exerciseTrainingRating + "']").prop('checked', true);
	      }
	      
	      break;
	      
	  case '#management_training':
	      if (data.special_points) {
	    	  $("#special_points").val(data.special_points);
	      }
	      if (data.trainingAidsRating) {
	        $("input[name='trainingAidsRating'][value='" + data.trainingAidsRating + "']").prop('checked', true);
	      }
	    
	      break;
	      
	  case '#personnel_management':
	      if (data.disciplineRating) {
	        $("input[name='disciplineRating'][value='" + data.disciplineRating + "']").prop('checked', true);
	      }
	      if (data.HealthTroopsRating) {
	        $("input[name='HealthTroopsRating'][value='" + data.HealthTroopsRating + "']").prop('checked', true);
	      }
	      if (data.personalDocumentationRating) {
	        $("input[name='personalDocumentationRating'][value='" + data.personalDocumentationRating + "']").prop('checked', true);
	      }
	     
	      break;
	      
	  case '#interior_economy':
	      if (data.EquipmentRating) {
	        $("input[name='EquipmentRating'][value='" + data.EquipmentRating + "']").prop('checked', true);
	      }
	      if (data.facilitiesRating) {
	        $("input[name='facilitiesRating'][value='" + data.facilitiesRating + "']").prop('checked', true);
	      }
	      if (data.livingHabitatRating) {
	        $("input[name='livingHabitatRating'][value='" + data.livingHabitatRating + "']").prop('checked', true);
	      }
	      if (data.livingConditionRating) {
	        $("input[name='livingConditionRating'][value='" + data.livingConditionRating + "']").prop('checked', true);
	      }
	      if (data.modernEquipmentRating) {
	        $("input[name='modernEquipmentRating'][value='" + data.modernEquipmentRating + "']").prop('checked', true);
	      }
	      if (data.individualDocumentationRating) {
	        $("input[name='individualDocumentationRating'][value='" + data.individualDocumentationRating + "']").prop('checked', true);
	      }
	      if (data.personalClothingRating) {
	        $("input[name='personalClothingRating'][value='" + data.personalClothingRating + "']").prop('checked', true);
	      }
	      if (data.timelyCorrectRating) {
		        $("input[name='timelyCorrectRating'][value='" + data.timelyCorrectRating + "']").prop('checked', true);
		  }
	      break;
	      
	  case '#morale_motivation':
	      if (data.disciplineStateRating) {
	        $("input[name='disciplineStateRating'][value='" + data.disciplineStateRating + "']").prop('checked', true);
	      }
	      if (data.documentationRating) {
	        $("input[name='documentationRating'][value='" + data.documentationRating + "']").prop('checked', true);
	      }
	      if (data.interiorEconomyRating) {
	        $("input[name='interiorEconomyRating'][value='" + data.interiorEconomyRating + "']").prop('checked', true);
	      }
	      if (data.involvementRating) {
	        $("input[name='involvementRating'][value='" + data.involvementRating + "']").prop('checked', true);
	      }
	      if (data.performanceCourseRating) {
	        $("input[name='performanceCourseRating'][value='" + data.performanceCourseRating + "']").prop('checked', true);
	      }
	      if (data.performanceSportsRating) {
	        $("input[name='performanceSportsRating'][value='" + data.performanceSportsRating + "']").prop('checked', true);
	      }
	      if (data.PerformanceTrgRating) {
	        $("input[name='PerformanceTrgRating'][value='" + data.PerformanceTrgRating + "']").prop('checked', true);
	      }
	      if (data.persDisciplineRating) {
		        $("input[name='persDisciplineRating'][value='" + data.persDisciplineRating + "']").prop('checked', true);
		      }
	      
	      if (data.stateSickReportRating) {
		        $("input[name='stateSickReportRating'][value='" + data.stateSickReportRating + "']").prop('checked', true);
		      }
	      if (data.stateEqptMaintRating) {
		        $("input[name='stateEqptMaintRating'][value='" + data.stateEqptMaintRating + "']").prop('checked', true);
		      }
	      if (data.stateLeaveRating) {
		        $("input[name='stateLeaveRating'][value='" + data.stateLeaveRating + "']").prop('checked', true);
		      }
	      break;
	      
	  case '#material_management':
	      if (data.eqptIncludingRating) {
	        $("input[name='eqptIncludingRating'][value='" + data.eqptIncludingRating + "']").prop('checked', true);
	      }
	      if (data.maintenanceAmnRating) {
	        $("input[name='maintenanceAmnRating'][value='" + data.maintenanceAmnRating + "']").prop('checked', true);
	      }
	      if (data.maintenanceArmsRating) {
	        $("input[name='maintenanceArmsRating'][value='" + data.maintenanceArmsRating + "']").prop('checked', true);
	      }
	      if (data.maintenanceOrdnanceStoresRating) {
	        $("input[name='maintenanceOrdnanceStoresRating'][value='" + data.maintenanceOrdnanceStoresRating + "']").prop('checked', true);
	      }
	      if (data.PublicFundsRating) {
	        $("input[name='PublicFundsRating'][value='" + data.PublicFundsRating + "']").prop('checked', true);
	      }
	      if (data.VehiclesRating) {
	        $("input[name='VehiclesRating'][value='" + data.VehiclesRating + "']").prop('checked', true);
	      }

	      break;
	      
	  case '#other_miscellaneous_aspects':
	      if (data.SecurityRating) {
	        $("input[name='SecurityRating'][value='" + data.SecurityRating + "']").prop('checked', true);
	      }
	      break;
	      
	  case '#measures_core_values':
	      if (data.integrityRating) {
	        $("input[name='integrityRating'][value='" + data.integrityRating + "']").prop('checked', true);
	      }
	      if (data.loyaltyRating) {
	        $("input[name='loyaltyRating'][value='" + data.loyaltyRating + "']").prop('checked', true);
	      }
	      if (data.dutyRating) {
	        $("input[name='dutyRating'][value='" + data.dutyRating + "']").prop('checked', true);
	      }
	      if (data.respectRating) {
	        $("input[name='respectRating'][value='" + data.respectRating + "']").prop('checked', true);
	      }
	      if (data.selflessRating) {
	        $("input[name='selflessRating'][value='" + data.selflessRating + "']").prop('checked', true);
	      }
	      if (data.courageRating) {
	        $("input[name='courageRating'][value='" + data.courageRating + "']").prop('checked', true);
	      }
	      if (data.honourRating) {
	        $("input[name='honourRating'][value='" + data.honourRating + "']").prop('checked', true);
	      }
	      break;
	      
	      
	  case '#human_resource_developement':
	      if (data.relatedRating) {
	        $("input[name='relatedRating'][value='" + data.relatedRating + "']").prop('checked', true);
	      }
	      if (data.auditRating) {
	        $("input[name='auditRating'][value='" + data.auditRating + "']").prop('checked', true);
	      }
	      if (data.enhancementRating) {
	        $("input[name='enhancementRating'][value='" + data.enhancementRating + "']").prop('checked', true);
	      }
	      if (data.juniorRating) {
	        $("input[name='juniorRating'][value='" + data.juniorRating + "']").prop('checked', true);
	      }
	      if (data.measuresRating) {
	        $("input[name='measuresRating'][value='" + data.measuresRating + "']").prop('checked', true);
	      }
	      if (data.miscellaneousRating) {
	        $("input[name='miscellaneousRating'][value='" + data.miscellaneousRating + "']").prop('checked', true);
	      }
	      if (data.complaintsRating) {
	        $("input[name='complaintsRating'][value='" + data.complaintsRating + "']").prop('checked', true);
	      }
	      if (data.trainingRating) {
		        $("input[name='trainingRating'][value='" + data.trainingRating + "']").prop('checked', true);
		      }
	      if (data.serviceLevelRating) {
		        $("input[name='serviceLevelRating'][value='" + data.serviceLevelRating + "']").prop('checked', true);
		      }
	      if (data.welfareRating) {
		        $("input[name='welfareRating'][value='" + data.welfareRating + "']").prop('checked', true);
		      }
	      break;
	      
	  case '#additional_information':
	      if (data.publicFundsRating) {
	        $("input[name='publicFundsRating'][value='" + data.publicFundsRating + "']").prop('checked', true);
	      }
	      if (data.stateComplaintsPetitionsRating) {
	        $("input[name='stateComplaintsPetitionsRating'][value='" + data.stateComplaintsPetitionsRating + "']").prop('checked', true);
	      }
	      if (data.stateDiscpRating) {
	        $("input[name='stateDiscpRating'][value='" + data.stateDiscpRating + "']").prop('checked', true);
	      }
	      if (data.anyPeculiarRating) {
	        $("input[name='anyPeculiarRating'][value='" + data.anyPeculiarRating + "']").prop('checked', true);
	      }
	      if (data.stateWorkRating) {
	        $("input[name='stateWorkRating'][value='" + data.stateWorkRating + "']").prop('checked', true);
	      }
	      
	      break;	    
	  case '#comments_insp_offr':
	      if (data.state_ii) {
	    	  $("#state_ii").val(data.state_ii);
	      }	      
	      break;	    
	  }
	}





</script>