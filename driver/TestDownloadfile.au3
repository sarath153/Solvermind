Sleep(1000)
ConsoleWrite("Before ControlFocus: " & @CRLF)
ControlFocus("Save As", "", "Edit1")

Sleep(1000)
ControlSend("Save As", "", "Edit1", "^a")
Sleep(1000)

; Press Backspace to delete the selected text
ControlSend("Save As", "", "Edit1", "{BS}")

Sleep(2000)

ControlSend("Save As", "", "Edit1", $CmdLine[1])

Sleep(2000)
ConsoleWrite("Before ControlClick: " & @CRLF)
ControlClick("Save As", "", "Button2")
