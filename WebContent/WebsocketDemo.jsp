<html>
<head>
    <title>websocket example</title>
</head>
<body>
    {{ . }}
    <script type="text/javascript">
        var ws;
        if (window.WebSocket === undefined) {
            alert("Your browser does not support WebSockets.");
        } else {
            ws = initWS();
        }

        function initWS() {
            var socket = new WebSocket("ws://localhost:9090/ws");
            socket.onopen = function () {
                console.log("Socket is open.");
            };
            socket.onmessage = function (e) {
                console.log("Received data.", e.data);
            }
            socket.onclose = function () {
                console.log("Socket closed.");
                setTimeout(function () {
                    initWS()
                }, 5000);
            }
            return socket;
        }
    </script>
</body>
</html>