package app

import (
	"encoding/json"
	"net"
)

type InitMessage struct {
	UserID string `json:"userId"`
}

func App() {
	// conn, _, err := websocket.DefaultDialer.Dial("ws://localhost:8080/", nil)
	conn, err := net.Dial("tcp", "localhost:8080")
	if err != nil {
		panic(err)
	}
	defer conn.Close()

	msg := InitMessage{UserID: "5596"}
	data, _ := json.Marshal(msg)

	conn.Write(data)

	// portaudio.Initialize()
	// defer portaudio.Terminate()

	// conn.SetReadDeadline(time.Now().Add(10 * time.Second))

	// buf := make([]byte, 1024*4)

	// stream, err := portaudio.OpenDefaultStream(0, 1, 44100, 1024, buf)

	// stream.Start()
	// defer stream.Close()

	// for {

	// 	_, message, _ := conn.ReadMessage()
	// 	for i := range message {
	// 		buf[i] = message[i]
	// 	}
	// 	fmt.Println(buf)
	// 	stream.Write()
	// }
}
