<!--
                       _oo0oo_
                      o8888888o
                      88" . "88
                      (| -_- |)
                      0\  =  /0
                    ___/`---'\___
                  .' \\|     |// '.
                 / \\|||  :  |||// \
                / _||||| -:- |||||- \
               |   | \\\  -  /// |   |
               | \_|  ''\---/''  |_/ |
               \  .-\__  '-'  ___/-. /
             ___'. .'  /--.--\  `. .'___
          ."" '<  `.___\_<|>_/___.' >' "".
         | | :  - \.;\ _ /;./ -  : | |
         \  \ _.   \_ __\ /__ _/   .- /  /
     =====-.____.___ \_____/___.-`___.-'=====
                       `=---='

     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            ဘုရား        တရား        သံဃာ
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    -->
<%@ include file="header.jsp"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/StyleSheet.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
p {
	line-height: 2;
}

.plogo {
	margin: 0px;
	padding: 0px;
	text-decoration: none;
}
</style>
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
					<li class="nav-item"><a class="nav-link" href="userLogin.jsp">Dashboard</a>
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
					<li class="nav-item"><a class="nav-link" target="_blank" href="https://maps.app.goo.gl/WgniDSKq3K1owfMo9">Location</a>
					</li>

				</ul>
			</div>
		</div>

	</nav>

	<div class="container">
		<h1>About Us</h1>
		<div class="about-section">
			<h2>Our Mission</h2>
			<p>
				At <a style="color: black" class="plogo" href="#" id="logo"><span
					class="brand">C</span>ash<span class="brand">N</span>ex</a>, our
				mission resonates with the core values of dedication and excellence.
				We are committed to delivering tailored solutions that seamlessly
				align with the diverse needs of our clients. Our unwavering focus on
				innovation drives us to constantly evolve, ensuring that we provide
				not just services, but transformative experiences. With a deep
				understanding of the intricate dynamics of today's digital
				landscape, we strive to exceed expectations by offering trusted
				services and facilitating secure transactions. Our mission isn't
				merely to meet requirements; it's to exceed them, setting new
				benchmarks in reliability, efficiency, and client satisfaction.
			</p>
		</div>

		<div class="about-section">
			<h2>Our Team</h2>
			<p>
				Behind every milestone achieved and every challenge conquered stands
				the remarkable team at <a style="color: black" class="plogo"
					href="#" id="logo"><span class="brand">C</span>ash<span
					class="brand">N</span>ex</a>. United by a passion for excellence and a
				shared vision for success, our team embodies diversity, creativity,
				and expertise. From seasoned veterans to emerging talents, each
				member contributes a unique perspective and skill set, enriching our
				collaborative environment. Together, we navigate the complexities of
				the modern business world with agility and determination, fostering
				innovation and embracing challenges as opportunities for growth. Our
				team isn't just a collection of individuals; we are a cohesive force
				dedicated to achieving excellence, driving positive change, and
				empowering our clients to thrive in an ever-evolving landscape.
			</p>
		</div>

		<div class="inquiry-box">
			<h2>Contact Us</h2>
			<p>If you have any inquiries or questions, feel free to contact
				us.</p>
			<a href="ContactUs.jsp"><button class="inquiry-btn">Contact Us</button></a>
		</div>
	</div>
</body>
</html>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>