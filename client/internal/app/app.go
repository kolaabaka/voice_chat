package app

import (
	"audio-chat/client/internal/wrapper"
	"bufio"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
	"os"
)

var idByte []byte = make([]byte, 4)
var textMessageByte []byte = make([]byte, 1028)
var id uint32

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

	fmt.Scan(&id)
	msg := InitMessage{UserID: fmt.Sprint(id)}

	binary.BigEndian.PutUint32(idByte, id)

	data, _ := json.Marshal(msg)

	conn.Write(data)

	for {
		reader := bufio.NewReader(os.Stdin)
		fmt.Print("Enter text: ")
		inputMessage, _ := reader.ReadString('\n')

		copy(textMessageByte, wrapper.WrapMessageToByteSlice(inputMessage, idByte))

		conn.Write(textMessageByte)

	}

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
