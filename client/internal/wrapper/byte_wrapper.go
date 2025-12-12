package wrapper

import (
	"encoding/binary"
	"fmt"
)

var messageSizeByte []byte = make([]byte, 4)
var textMessageByte []byte = make([]byte, 1028)

func WrapMessageToByteSlice(inputMessage string, idByte []byte) []byte {
	fmt.Printf("Your input text: %s", inputMessage)
	//id + size + message byte

	inputMessageByteArray := []byte(inputMessage)

	binary.BigEndian.PutUint32(messageSizeByte, uint32(len(inputMessageByteArray)))

	fmt.Println("size bytes - " + fmt.Sprint(len(inputMessageByteArray)))

	copy(textMessageByte[:4], idByte)
	copy(textMessageByte[4:], messageSizeByte)
	copy(textMessageByte[8:], []byte(inputMessage))

	return textMessageByte
}
