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
D;JNE  //fasdf
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









