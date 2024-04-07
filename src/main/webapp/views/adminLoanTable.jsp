<%@ include file="header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cashnex.controller.adminDashboardController"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css/adminPage.css"
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
</head>

<style>
/* 
Transfer Form Popup */
.popup-wrapper {
	padding-top: 10px;
	position: fixed;
	display: flex;
	margin-top: 50px;
	align-items: center;
	width: 100%;
	height: 100%;
	flex-wrap: wrap;
	flex-direction: column;
	gap: 10px;
	justify-content: center;
	backdrop-filter: blur(10px);
	z-index: 5;
}

.popup-center {
	padding: 40px;
	display: flex;
	align-items: center;
	border-radius: 10px;
	z-index: 6;
	position: relative;
	box-shadow: 5px 5px 10px 5px #0c03037d;
	margin: auto;
	flex-wrap: wrap;
	flex-direction: column;
	gap: 10px;
	background-color: white;
	justify-content: center;
}

.input-field input:is(:focus, :valid) {
	border: 1px solid var(--brand-color);
}

.input-field {
	position: relative;
	height: 50px;
}

.input-field input {
	padding-left: 10px;
	height: 40px;
}

.input-field label {
	position: absolute;
	top: 40%;
	left: 5px;
	transform: translateY(-160%);
	font-size: 0.75rem;
	color: #4a4646;
	pointer-events: none;
	background-color: white;
}

.hide {
	display: none;
}
</style>

<body class="sb-nav-fixed">

	<div class="popup-wrapper hide" id="transfer-popup">

		<div class="popup-center" id="abc">
			<div class="header">
				<h3 class="title">
					Transfer Money to <span id="user_name"></span>
				</h3>
				<button type="button" class="btn-close text-reset"
					style="position: absolute; top: 5%; right: 5%;"
					onclick="togglePopupadmin(true)"></button>
			</div>
			<div class="bb" id="0912">
				<form method="post"
					action="${pageContext.request.contextPath}/adminDashboardController?action=ADD_BALANCE&id="
					style="padding: 30px;">
					<div class="input-field">
						<input type="text" name="amount" required> <label for="">Amount</label>
					</div>
					<div class="input-field">
						<input type="password" name="password" required> <label
							for="">Password</label>
					</div>
					<button type="submit" class="btn btn-primary">Confirm</button>
				</form>
			</div>
		</div>

	</div>
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="#" id="logo"><span
			class="brand">C</span>ash<span class="brand">N</span>ex</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<div class="input-group">
				<input class="form-control" type="text" placeholder="Search for..."
					aria-label="Search for..." aria-describedby="btnNavbarSearch" />
				<button class="btn btn-primary" id="btnNavbarSearch" type="button">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="#!">Settings</a></li>
					<li><a class="dropdown-item" href="#!">Activity Log</a></li>
					<li><hr class="dropdown-divider" /></li>
					<li><a class="dropdown-item" href="views/adminLogin.jsp">Logout</a></li>
				</ul></li>
		</ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">Core</div>
						<a class="nav-link"
							href="${pageContext.request.contextPath}/adminDashboardController">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Dashboard
						</a> <a class="nav-link"
							href="${pageContext.request.contextPath}/AdminLoanTableController">
							<div class="sb-nav-link-icon">
								<i class="fas fa-table"></i>
							</div> Loan Table


						</a>
						<div class="sb-sidenav-menu-heading">Interface</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts" aria-expanded="false"
							aria-controls="collapseLayouts">
							<div class="sb-nav-link-icon">
								<i class="fas fa-columns"></i>
							</div> Layouts
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="layout-static.html">Static
									Navigation</a> <a class="nav-link" href="layout-sidenav-light.html">Light
									Sidenav</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapsePages" aria-expanded="false"
							aria-controls="collapsePages">
							<div class="sb-nav-link-icon">
								<i class="fas fa-book-open"></i>
							</div> Pages
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapsePages"
							aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
									data-bs-target="#pagesCollapseAuth" aria-expanded="false"
									aria-controls="pagesCollapseAuth"> Authentication
									<div class="sb-sidenav-collapse-arrow">
										<i class="fas fa-angle-down"></i>
									</div>
								</a>
								<div class="collapse" id="pagesCollapseAuth"
									aria-labelledby="headingOne"
									data-bs-parent="#sidenavAccordionPages">
									<nav class="sb-sidenav-menu-nested nav">
										<a class="nav-link" href="login.html">Login</a> <a
											class="nav-link" href="register.html">Register</a> <a
											class="nav-link" href="password.html">Forgot Password</a>
									</nav>
								</div>
								<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
									data-bs-target="#pagesCollapseError" aria-expanded="false"
									aria-controls="pagesCollapseError"> Error
									<div class="sb-sidenav-collapse-arrow">
										<i class="fas fa-angle-down"></i>
									</div>
								</a>
								<div class="collapse" id="pagesCollapseError"
									aria-labelledby="headingOne"
									data-bs-parent="#sidenavAccordionPages">
									<nav class="sb-sidenav-menu-nested nav">
										<a class="nav-link" href="401.html">401 Page</a> <a
											class="nav-link" href="404.html">404 Page</a> <a
											class="nav-link" href="500.html">500 Page</a>
									</nav>
								</div>
							</nav>
						</div>
						<!-- <div class="sb-sidenav-menu-heading">Addons</div>
						<a class="nav-link" href="charts.html">
							<div class="sb-nav-link-icon">
								<i class="fas fa-chart-area"></i>
							</div> Charts
						</a> <a class="nav-link" href="tables.html">
							<div class="sb-nav-link-icon">
								<i class="fas fa-table"></i>
							</div> Tables
						</a> -->
					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Logged in as:</div>
					<span style="color: #ff7e0c;">Admin</span>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Admin Loan Control Panel</h1>

					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Loan Table Overview
						</div>
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-top">
									<div class="datatable-dropdown">
										<label> <select id="entriesPerPage"
											class="datatable-selector">
												<option value="5">5</option>
												<option value="10" selected="">10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												<option value="25">25</option>
										</select> entries per page
										</label>

									</div>
									<div class="datatable-search">
										<input class="datatable-input" placeholder="Search..."
											type="search" title="Search within table"
											aria-controls="datatablesSimple">
									</div>

								</div>

								<!-- Loan Details -->
								<c:set var="loanDao" value="<%=new com.cashnex.dao.LoanDao()%>" />
								<c:set var="loans" value="${loanDao.getLoanDetails()}" />

								<div class="datatable-container">
									<p>
										<b>Loan History</b>
									</p>
									<table class="datatable-table" id="datatablesSimple">
										<thead>
											<tr>
												<th>User Name</th>
												<th>Reason</th>
												<th>Loan Amount</th>
												<th>Repayment Amount</th>
												<th>Paid Amount</th>
												<th>Due Date</th>
												<th>Fully Paid?</th>
											</tr>
										</thead>
										<!--  Following needs to be configured -->
										<!-- <tfoot>
								<tr>
									<th>User Name</th>
									<th>Gmail</th>
									<th>Career</th>
									<th>Balance</th>
									<th>Actions</th>
								</tr>
								</tfoot> -->
										<tbody>
											<c:forEach items="${loans}" var="loan">
												<tr>
													<td><c:out value="${loan.userName}" /></td>
													<td><c:out value="${loan.reason}" /></td>
													<td><c:out value="${loan.loanAmount}" /></td>
													<td><c:out value="${loan.repayment_amount}" /></td>
													<td><c:out value="${loan.paidAmount}" /></td>
													<td><fmt:formatDate value="${loan.dueDate}"
															pattern="yyyy-MM-dd" /></td>
													<td><c:if test="${loan.fullyPaid}">Yes</c:if> <c:if
															test="${not loan.fullyPaid}">No</c:if></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<p>
										<b>Pending Loan Applications</b>
									</p>
									<table class="datatable-table" id="datatablesSimple">
										<thead>
											<tr>
												<th>User Name</th>
												<th>Requested Amount</th>
												<th>Reason</th>
												<th>Estimated Repayment Date</th>
												<th>Account Number</th>
												<th>Actions</th>
											</tr>
										</thead>
										<!--  Following needs to be configured -->
										<!-- <tfoot>
								<tr>
									<th>User Name</th>
									<th>Gmail</th>
									<th>Career</th>
									<th>Balance</th>
									<th>Actions</th>
								</tr>
								</tfoot> -->
										<tbody>
											<c:forEach items="${pendingLoans}" var="loan">
												<tr>
													<td>${loan.username}</td>
													<td>${loan.loan_amount}</td>
													<td>${loan.reason}</td>
													<td>${loan.estimated_date}</td>
													<td>${loan.accountNumber}</td>
													<td><a
														href="${pageContext.request.contextPath}/AdminLoanTableController?action=APPROVE&id=${loan.user_id}&loan_amount=${loan.loan_amount}">Approve</a>
														|<a
														href="${pageContext.request.contextPath}/AdminLoanTableController?action=DENY&id=${loan.user_id}">Deny</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>

									</table>
								</div>

							</div>
							<div class="datatable-bottom">
								<div class="datatable-info">Showing 1 to 10 of 17 entries</div>
								<nav class="datatable-pagination">
									<ul class="datatable-pagination-list">
										<li
											class="datatable-pagination-list-item datatable-hidden datatable-disabled"><a
											data-page="1" class="datatable-pagination-list-item-link">&lsaquo;</a></li>
										<li class="datatable-pagination-list-item datatable-active"><a
											data-page="1" class="datatable-pagination-list-item-link">1</a></li>
										<li class="datatable-pagination-list-item"><a
											data-page="2" class="datatable-pagination-list-item-link">2</a></li>
										<li class="datatable-pagination-list-item"><a
											data-page="3" class="datatable-pagination-list-item-link">3</a></li>
										<li class="datatable-pagination-list-item"><a
											data-page="4" class="datatable-pagination-list-item-link">4</a></li>
										<li class="datatable-pagination-list-item"><a
											data-page="5" class="datatable-pagination-list-item-link">5</a></li>
										<li class="datatable-pagination-list-item"><a
											data-page="6" class="datatable-pagination-list-item-link">6</a></li>
										<li class="datatable-pagination-list-item"><a
											data-page="2" class="datatable-pagination-list-item-link">&rsaquo;</a></li>
									</ul>
								</nav>
							</div>

						</div>


						<footer class="py-4 bg-light mt-auto">
							<div class="container-fluid px-4">
								<div
									class="d-flex align-items-center justify-content-between small">
									<div class="text-muted">Copyright &copy; CashNex</div>
									<div>
										<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
											&amp; Conditions</a>
									</div>
								</div>
							</div>
						</footer>
					</div>
				</div>
			</main>
		</div>
		<script>
			document
					.addEventListener(
							"DOMContentLoaded",
							function() {
								const dataTable = new SimpleDatatables(
										"#datatablesSimple");

								// Get the select element
								const selectEntries = document
										.getElementById("entriesPerPage");

								// Add event listener to handle change event
								selectEntries
										.addEventListener(
												"change",
												function() {
													// Get the selected value
													const selectedValue = parseInt(selectEntries.value);

													// Update the DataTable with the new limit
													dataTable
															.pageLength(selectedValue);
												});
							});
		</script>

		<script
			src="${pageContext.request.contextPath}/resources/js/admintransferpopup.js?version=2"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			crossorigin="anonymous"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
			crossorigin="anonymous"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/chart-area-demo.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/chart-bar-demo.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
			crossorigin="anonymous"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/datatables-simple-demo.js"></script>
</body>
</html>