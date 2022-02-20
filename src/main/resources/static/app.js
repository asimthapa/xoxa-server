var sessionId;
var sub = null;

class Session {
    constructor(id, token, users, joinToken) {
        this.id = id;
        this.token = token;
        this.users = users;
        this.joinToken = joinToken;
    }
}

class JoinRequest {
 constructor(sessionId, joinToken, userId) {
    this.sessionId = sessionId;
    this.joinToken = joinToken;
    this.userId = userId;
 }
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

async function connect() {
    sessionId = await fetch("http://192.168.0.155:8080/xoxa/session/create", {
            method: 'POST',
            body: 'John'
        }).then(response => response.text());

   const socket = new SockJS("http://localhost:8080/xoxa-ws");
    stompClient = Stomp.over(socket);

//    // TODO no session id in db, no existing session going on, add ip and generate token
//
//    let topic1 = "/xoxa-app/topic/" + session.id + "/history";

    let topic = "/topic/" + sessionId + "/message";
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe(topic, function (messageResponse) {
            showGreeting(JSON.parse(messageResponse.body).message);
        });
    });
}

async function join() {
    sessionId = $("#sessionId").val();
    console.log(sessionId);
    request = new JoinRequest($("#sessionId").val(), $("#joinToken").val(), $("#displayName").val());
    console.log(request);
    await fetch("http://localhost:8080/xoxa/session/join", {
            method: 'POST',
            headers: new Headers({'content-type': 'application/json', 'Accept': 'application/json'}),
            body: JSON.stringify(request)
        });

        const socket = new SockJS("http://localhost:8080/xoxa-ws");
        stompClient = Stomp.over(socket);


        let topic = "/topic/" + $("#sessionId").val() + "/message";
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe(topic, function (messageResponse) {
                message = JSON.parse(messageResponse.body);
                showGreeting(message.sender + ": " + message.content);
            });
        // TODO remove subscription
        });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    console.log(sessionId);
    console.log("_:Lf");
   var sendEndPoint = "/xoxa-app/" + sessionId + "/message";
    stompClient.send(sendEndPoint, {}, JSON.stringify({
            'sender': $("#name").val(),
            'sessionId': sessionId,
            'content': "this is message contefddddddddddddddddddddddddddddfdfd  fd fdfdf dfd fd fdf df df dnt",
            'messageTime': '2013-11-18T11:55:00-05:00'
    }));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#join" ).click(function() {join(); });
});