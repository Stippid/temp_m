<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) {
		response.sendRedirect("/login");
		return;
	}
%>
<style>
.search-container {
  position: relative;
  display: inline-block; 
}

.search-icon {
  position: absolute;
  top: 8px;
  left: 5px;
  color: white;
}


#menu-search {
  background-color: transparent;
  border: none;
  /*border-bottom: 2px solid #ccc; */ 
  padding: 5px;
  padding-left: 25px; 
  color: white; 
}

#menu-search::placeholder {
        color: white !important;
}
</style>




<script>
	var username="${username}";
	var curDate = "${curDate}";
	var role = '${roleid}';
	var nSPara = '${nSPara}';
	//alert("Menu-"+nSPara+nSPara);
	
	//var addiphostna ='${ip}';
</script>



	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<c:set var="panelstyle" value=""/>
	<c:set var="panelstyle1" value=""/>
	<c:set var="nfplay" value="N"/>
	<c:if test="${fn:contains(role,'fp') or role=='cor'}">
		<c:set var="nfplay" value="Y"/>
	</c:if>
	<c:if test="${nfplay == 'Y'}">
		<c:set var="panelstyle" value="background:#2875e8!important;z-index:1000;position:fixed;left:0px;top:100px;overflow: auto;"/>
		<c:set var="panelstyle1" value="background:#2875e8!important;margin-top:10px;"/>
	</c:if>
	<aside id="left-panel" class="left-panel" style="${panelstyle}">
		<nav class="navbar navbar-expand-sm navbar-default" style="${panelstyle1}">
		<c:if test="${nfplay == 'Y'}">
			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					${menu}
				</ul> 
	    	</div> 		
		</c:if>
		<c:if test="${nfplay != 'Y'}">
			<div class="navbar-header">
	      		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation"> <i class="fa fa-bars"></i> </button>
	      		<a class="navbar-brand"><i class="menu-icon fa fa-user"></i>${roleloginName}</a> 
	      	</div>
	      	<div align="center">
 				<span style="position: relative; color: white;border-radius: 2px;"> Session timeout in &nbsp; <i style="font-size: 10px;" class="fa fa-hourglass fa-spin"></i>  :  <b style="color: orangered; min-width: 20px" id="div_timeout">600</b></span>
 			</div>
 			
 			<div class="search-container">
  <div class="search-icon">
    <i class="fa fa-search"></i>
  </div>
  <input type="text" id="menu-search" name="menu-search" placeholder="Search Menu" autocomplete="off" >
</div>
 			
	    	<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					${menu}
				</ul> 
	    	</div>
	    </c:if> 
	  	</nav>
	  	
	  	<script>
	
	
	 var menuItems = document.querySelectorAll('.nav-item.dropdown');// bind all menu
	 var subItems = document.querySelectorAll('.dropdown-item'); // bind sub menu

	 var searchInput = document.getElementById('menu-search');

	 searchInput.addEventListener('input', function () {
	     var query = this.value.toLowerCase();

	     menuItems.forEach(item => {
	         var itemName = item.querySelector('.nav-link').innerText.toLowerCase();//main menu
	         var submoduleDropdown = item.querySelector('.dropdown-menu'); //bind submenu and screen

	         if (itemName.includes(query)) {
	             item.style.display = 'block';
	             if (submoduleDropdown) {
	                 submoduleDropdown.style.display = 'block';
	                 submoduleDropdown.classList.add('show');
	                 submoduleDropdown.querySelectorAll('.dropdown-item').forEach(subItem => {
	                     subItem.style.display = 'block';
	                 });
	                 submoduleDropdown.querySelectorAll('ul.dropdown-menu').forEach(subMenu => {
	                     subMenu.style.display = 'block'; // Show all ul.dropdown-menu elements
	                 });
	             }
	         } else {
	             let submoduleMatch = false;
	             let screenMatch = false;

	             if (submoduleDropdown) {
	                 submoduleDropdown.style.display = 'none';
	                 submoduleDropdown.classList.remove('show');

	                 submoduleDropdown.querySelectorAll('.dropdown-item').forEach(subItem => {
	                     var subItemName = subItem.querySelector('a').innerText.toLowerCase();
	                     var screenLinks = subItem.querySelectorAll('ul.dropdown-menu li');

	                     if (subItemName.includes(query)) {
	                         submoduleMatch = true;
	                         subItem.style.display = 'block';
	                         submoduleDropdown.style.display = 'block';
	                         submoduleDropdown.classList.add('show');
	                         subItem.querySelectorAll('ul.dropdown-menu').forEach(subMenu => {
	                             subMenu.style.display = 'block'; // Show all ul.dropdown-menu elements
	                         });
	                     } else {
	                         let screenItemMatch = false;

	                         screenLinks.forEach(screenItem => {
	                             var screenName = screenItem.querySelector('a').innerText.toLowerCase();

	                             if (screenName.includes(query)) {
	                                 screenItem.style.display = 'block';
	                                 screenItemMatch = true;
	                                 submoduleDropdown.style.display = 'block';
	                                 submoduleDropdown.classList.add('show');
	                                 subItem.style.display = 'block';
	                                 item.style.display = 'block';
	                                 subItem.querySelector('ul.dropdown-menu').style.display = 'block'; // Show the ul element
	                                 var parentModules = item.querySelectorAll('.nav-item.dropdown');
	                                 parentModules.forEach(parentModule => {
	                                     parentModule.style.display = 'block';
	                                 });
	                             } else {
	                                 screenItem.style.display = 'none';
	                             }
	                         });

	                         if (screenItemMatch) {
	                             submoduleMatch = true;
	                             subItem.style.display = 'block';
	                         } else {
	                             subItem.style.display = 'none';
	                         }
	                     }
	                 });

	                 if (submoduleMatch) {
	                     item.style.display = 'block';
	                 } else {
	                     item.style.display = 'none';
	                 }
	             } else {
	                 item.style.display = 'none';
	             }
	         }
	     });
	 });


	</script>
	  	
	</aside>