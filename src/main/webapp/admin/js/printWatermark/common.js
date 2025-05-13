
$(document).ready(function () {

    //Disable cut copy paste
    $('body').bind('cut copy paste', function (e) {
        e.preventDefault();
    });
   
    //Disable mouse right click
    $("body").on("contextmenu",function(e){
        return false;
    });
});


function printDivOptimize(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
 	var row="";
 	var printLbl = allLbl; 
 	var printVal = allVal; 
	    row +='<div class="print_content"> ';
	 	row +='<div class="print_logo"> ';
		row +='<div class="row"> ';
		row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
		row +='<div class="col-6 col-sm-6 col-md-6"><h3>'+header+'</h3> </div> ';
		row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
		row +='</div> ';
		row +='</div> ';
		row +='	<div class="print_text"> ';
		row +='<div class="row"> ';
		
		var i;
		for (i = 0; i < printLbl.length; i++) {
			row +='<div class="col-12 col-sm-6 col-md-6"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';
			row +='<label class="label_right" id="uploaded_wepelbl">'+ printVal[i]+'</label> </div>';
		}
		row +='</div> ';
		row +='</div> ';
		row +='</div> ';
		
	 $("div#divShow").append(row); 
	 let popupWinindow
	    let innerContents = document.getElementById(divName).innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		 }
	   // popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><style>table td{font-size:12px;} table th{font-size:13px !important;}</style></head><body onload="window.print()"  oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' + innerContents + '</html>');
       popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/printWatermark/printWatermark.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   watermarkreport();
	   
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   $('.lastCol').show();
}

function printDivOptimizehelp(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
 	var row="";
 	var printLbl = allLbl; 
 	var printVal = allVal; 
	    row +='<div class="print_content"> ';
	 	row +='<div class="print_logo"> ';
		row +='<div class="row"> ';
		row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
		row +='<div class="col-6 col-sm-6 col-md-6"><h3>'+header+'</h3> </div> ';
		row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
		row +='</div> ';
		row +='</div> ';
		row +='	<div class="print_text"> ';
		row +='<div class="row"> ';
		
		var i;
		for (i = 0; i < printLbl.length; i++) {
			/*row +='<div class="col-6 col-sm-3 col-md-3"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';*/
			row +='<div class=col-6 col-6"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';
			row +='<label class="label_right" id="uploaded_wepelbl">'+ printVal[i]+'</label> </div>';
		}
		row +='</div> ';
		row +='</div> ';
		row +='</div> ';
		
	 $("div#divShow").append(row); 
	 let popupWinindow
	    let innerContents = document.getElementById(divName).innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		}
	    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/printWatermark/printWatermark.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		   watermarkreport();
	   
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   $('.lastCol').show();
}