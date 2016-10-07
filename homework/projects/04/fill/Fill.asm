// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.




//1  将屏幕清空
//2  如果循环清空过程中键盘值变为有值，则进入屏幕填黑循环
//3  如果填黑过程中，键盘值变为0，则进入清空循环


@8191  //loop times
D=A
@n
M=D

(LISTENLOOP)
@SCREEN
D=A
@addr
M=D
@KBD
D=M
@CLEAR
D;JEQ
@FILL
D;JNE

(CLEAR)
@i
M=0

(CLEARLOOP)
@n
D=M
@i
D=M-D
@LISTENLOOP
D;JGT
@addr
A=M
M=0
@i
M=M+1
@addr
M=M+1
@CLEARLOOP
0;JMP


(FILL)
@i
M=0
(FILLLOOP)
@n
D=M
@i
D=M-D
@LISTENLOOP
D;JGT
@addr
A=M
M=-1

@i
M=M+1
@addr
M=M+1
@FILLLOOP
0;JMP









