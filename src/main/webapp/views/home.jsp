<%@ include file="header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home_style.css" />

<title>Home Page</title>



<link
	href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://unpkg.com/boxicons@latest/s/boxicons.min.css">



</head>
<body>
	<!-- Below For Eclipse-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">

		<div class="container">

			<a class="navbar-brand" href="#" id="logo"><span class="brand">C</span>ash<span
				class="brand">N</span>ex</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0" id="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="home.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="adminLogin.jsp">Admin Dashboard</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Accounts </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="userLogin.jsp">Loans</a></li>
							<li><a class="dropdown-item" href="userLogin.jsp">Transactions</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="userLogin.jsp">Log in</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" href="AboutUs.jsp">About Us</a>
					</li>

				</ul>
			</div>
		</div>

	</nav>

	<!-- Header and paragraph-->

	<div class="container mt-5" id="header_text">

		<div class="row">
			<div class="col-6">
				<h1>
					Tailored solutions aligning <br> with trusted services and
					secure transactions
				</h1>
				<p class="mt-3">
					<span class="checkmark"></span> Customized solutions that match
					your financial <br> &emsp;&ensp;&nbsp; goals securely.
				</p>

				<p class="mt-3">
					<span class="checkmark"></span> Dedicated to fostering financial
					success for <br> &emsp;&ensp;&nbsp; individuals and
					businesses.
				</p>

				<div class="inquiry">
					<div class="inquiryText">
						<h5>You need Money for :</h5>

						<div class="bigBox">
							<div class="boxItems">
								<button class="btn-cars">Cars</button>
							</div>
							<div class="boxItems">
								<button class="btn-land">Land</button>
							</div>
							<div class="boxItems">
								<button>Houses</button>
							</div>
							<div class="boxItems">
								<button>Gold</button>
							</div>
							<div class="boxItems">
								<button>Watches</button>
							</div>
							<div class="boxItems">
								<button>Rings</button>
							</div>
						</div>

						<div class="money">
							<input type="number" class="number"> <span
								class="dollar-sign">$</span>
							<button class="btn btn-success" id="iqbtn"><a class="inquireButton" href="userLogin.jsp">Inquire Now</a></button>
						</div>
					</div>


				</div>
			</div>

			<div class="col-6">
				<!-- Photo Gallery -->
				<div class="gallery-container">

					<div class="parent">

						<div class="div3">
							<img
								src="${pageContext.request.contextPath}/resources/images/big_one.JPG">
						</div>
						<div class="div4">
							<img
								src="${pageContext.request.contextPath}/resources/images/small_right.JPG">
						</div>
						<div class="div5">
							<img
								src="${pageContext.request.contextPath}/resources/images/small_bot.JPG">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Banner -->

	<div class="container" id="banner">
		<div class="container">
			<div class="row">
				<div class="col-md-12" id="banner-row">
					<!-- Replace the src attribute with your banner image URL -->

					<img
						src="${pageContext.request.contextPath}/resources/images/bank.png"
						alt="Bank Image" class="bank"> <img
						src="${pageContext.request.contextPath}/resources/images/loan2.png"
						alt="Loan Image" class="loan"> <img
						src="${pageContext.request.contextPath}/resources/images/transaction.png"
						alt="Transaction Image" class="transaction"> <img
						src="${pageContext.request.contextPath}/resources/images/wallet.png"
						alt="Wallet Image" class="wallet">
				</div>
			</div>
		</div>
	</div>

	<!-- Video Section -->

	<div class="container text-center mt-20" id="video_container">
		<div class="col-md-12">
			<span class="ad_header"><h2 class="ad_text">
					Elevate Your Banking with <span class="brand_text">C</span>ash<span
						class="brand_text">N</span>ex
				</h2></span> <span id="video" class="d-block mx-auto"> <!-- Added "border-radius" and "overflow" properties to create border radius -->
				<video src="${pageContext.request.contextPath}/resources/video/CashNex.mp4" controls></video>
			</span>
		</div>
	</div>

	<!-- Footer -->
	<footer class="container">
		<div class="footer-content">
			<h1 class="ad_text">
				<span class="brand_text">C</span>ash<span class="brand_text">N</span>ex
			</h1>
			<p class="footer_text">
				Discover seamless banking solutions with CashNex. Streamlining your
				banking experience with easy transactions and personalized services.
				<br> Trust CashNex for secure, convenient, and innovative
				banking solutions. Thank you for choosing us for your financial
				needs.
			</p>

		</div>
		<div class="footer-bottom">
			<p class="footer_copyright_text">
				Copyright &copy;2024 <span class="brand_text">C</span>ash<span
						class="brand_text">N</span>ex. Designed by <span>The Pixels</span>
			</p>
		</div>
	</footer>

	<!-- End -->
	
	<style>
/* Custom styling for the banner */
#banner {
	background-color: var(--inquiry-box);
	padding: 20px 0;
	text-align: center;
	margin-top: 20px;
	border-radius: 10px;
}

#banner-row {
	position: relative;
	display: flex;
	justify-content: space-evenly;
}

.bank {
	max-width: 15%;
	height: 100px;
}

.loan {
	max-width: 15%;
	height: 100px;
}

.transaction {
	max-width: 15%;
	height: 100px;
}

.wallet {
	max-width: 15%;
	height: 100px;
}

/* Video section */
#video_container {
	text-align: center;
	margin-top: 60px;
}

#ad_header {
	margin-bottom: 20px;
	cursor: default;
}

.ad_text {
	cursor: default;
	color: var(--text-color);
}

#video {
	display: block;
	max-width: 800px;
	border-radius: 15px;
	overflow: hidden;
	margin-top: 50px;
}

#video video {
	max-width: 100%;
	height: auto;
}

.brand_text {
	color: var(--brand-color);
}

/* Footer */
footer {
	position: relative;
	z-index: 1;
	overflow-x: hidden; /* Add this line */
	padding-top: 5rem;
}

span.brand {
	color: var(--brand-color);
}

.footer-content {
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	text-align: center;
}

.footer-content h3 {
	font-size: 2rem;
	font-weight: 400;
	text-transform: capitalize;
	line-height: 3rem;
	user-select: none;
}

.footer-content p {
	color: var(--text-color);
	margin: 10px auto;
	line-height: 28px;
	font-size: 15px;
	user-select: none;
}

.footer-bottom {
	margin-top: 40px;
	text-align: center;
	font-family: 'Inter', sans-serif;
	color: var(--text-color);
}

.footer-bottom p {
	font-size: 15px;
	word-spacing: 2px;
	text-transform: capitalize;
	color: var(--text-color);
	user-select: none;
	opacity: 60%;
}

.footer-bottom span {
	text-transform: uppercase;
	font-weight: 200;
	color: var(--brand-color);
	opacity: .6;
	user-select: none;
}

.footer_text {
	margin-top: 10px;
	font-family: 'Inter', sans-serif;
	opacity: 70%;
}

.footer_copyright_text {
	margin-right: 20px;
}

.inquireButton{
	text-decoration: none;
	color: inherit;
}
.inquireButton:hover{
	text-decoration: none;
	color: inherit;
}

</style>
	

	<%@ include file="footer.jsp"%>