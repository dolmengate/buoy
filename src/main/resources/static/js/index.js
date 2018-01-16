var stompClient = null;

window.onload = function () {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect(
        {},                 // empty headers
        function (frame) {  // connect callback
            // upon connecting successfully, subscribe to the messages endpoint
            stompClient.subscribe('/topic/messages', function (message) {
                // upon receiving a message pushed from the subscription endpoint...
                console.log(JSON.parse(message.body).text);
            });
    });
};

document.onbeforeunload = function() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
};

function sendMessage() {
    stompClient.send(
        "/app/test",        // url
        {},                 // empty headers object (required to send frame with body)
        JSON.stringify(     // body
            {
                'text': document.querySelector('textarea').value
            }
        )
    );
}
