[string]$phone = $args[0]
[string]$data = $args[1]
$port8 = new-Object System.IO.Ports.SerialPort COM8,9600,None,8,one
$port8.Open()
$data = $phone + ";" + $data
$port8.Write($data)
$port8.Close()