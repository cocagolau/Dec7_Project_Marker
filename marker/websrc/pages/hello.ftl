<%@page session="false"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<title>hello</title>
</head>

<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>


	<h1>spring4 websocker</h1>
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="calculationDiv">
			<label>Number One:</label>
			<input type="text" id="num1" /><br/>
			<label>Number Two:</label>
			<input type="text" id="num2" /><br/><br/>
			
			<button id="sendNum" onclick="sendNum();">Send to Add</button>
			<p id="calResponse"></p>
		</div>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/0.3.4/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
	<script type="text/javascript">
		var stompClient = null;
		function setConnected(connected) {
			document.getElementById('connect').disabled = connected;
			document.getElementById('disconnect').disabled = !connected;
			document.getElementById('calculationDiv').style.visibility = connected ? 'visible' : 'hidden';
			document.getElementById('calResponse').innerHTML = '';
		}
		
		function connect() {
			var socket = new SockJS('/add');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				setConnected(true);
				console.log('Connected: ' + frame);
				stompClient.subscribe('/topic/showResult', function(calResult) {
					showResult(JSON.parse(calResult.body).result);
				});
			});
		}
		
		function disconnect() {
			stompClient.disconnect();
			setConnected(false);
			console.log('Disconnected');
		}
		
		function sendNum() {
			var num1 = document.getElementById('num1').value;
			var num2 = document.getElementById('num2').value;
			stompClient.send('/calcApp/add', {}, JSON.stringify({'num1':num1, 'num2':num2}));	
		}
		
		function showResult(message) {
			var response = document.getElementById('calResponse');
			var p = document.createElement('p');
			p.style.wordWrap = 'break-word';
			p.appendChild(document.createTextNode(message));
			response.appendChild(p);
		}
	</script>
</body>


</html>