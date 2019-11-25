Type=Activity
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim Server="http://192.168.1.2/kotogadang/andro/" As String 'variable untuk koneksi ke database
		Dim gpsClient As GPS
	Dim userLocation As Location
	Dim latUser, lngUser As String

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	latUser="-0.31865703276391205 "
	lngUser="100.35777860093731"
	Private Button5 As Button
	Private Button6 As Button
	Private Button7 As Button
	Private Button8 As Button
	Private WebView1 As WebView
	Private Panel1 As Panel
	Private Panel3 As Panel
End Sub


Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	
	Activity.LoadLayout("dashboard")
	Panel3.Visible=False
	If FirstTime Then
		gpsClient.Initialize("gpsClient")
		userLocation.Initialize
	End If	
End Sub

Sub gpsClient_LocationChanged (gpsLocation As Location) 'mengambil koordinat pengguna
	ProgressDialogHide
	userLocation=gpsLocation
	gpsClient.Stop
	latUser=userLocation.Latitude
	lngUser=userLocation.Longitude
	petaClient
End Sub

Sub petaClient 'menampilkan posisi pengguna pada peta

	Log(lngUser&" "&latUser)
	WebView1.LoadUrl(""&Server&"petaSaya.php?lat="&latUser&"&lng="&lngUser)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
gpsClient.Stop
End Sub

Sub Button1_Click
cekGPS
	If (Panel3.Visible=False) Then
		Panel3.Visible=True
	Else 
		Panel3.Visible=False
	End If	
End Sub




Sub cekGPS 'melakukan mengecekan GPS pada pengguna

	If (latUser=0 And lngUser=0) Then
		If gpsClient.GPSEnabled=False Then
			ToastMessageShow("Aktifkan GPS", True)
			StartActivity(gpsClient.LocationSettingsIntent)
		Else
			gpsClient.Start(0,0)
			ProgressDialogShow("Tunggu Lokasi")
		End If
	Else
		petaClient
	End If
End Sub
Sub Button2_Click
	StartActivity(search_cul)
End Sub

'Sub Button3_Click
'	StartActivity(search_ik)
'End Sub

Sub Button4_Click
	StartActivity(search_umkm)
End Sub


Sub Button8_Click
	StartActivity(search_wis)
End Sub
Sub Button7_Click
	StartActivity(search_tok)
End Sub
Sub Button6_Click
	StartActivity(search_mosque)
End Sub
Sub Button5_Click
	StartActivity(search_rm)
End Sub
Sub ImageView1_Click
	Panel3.Visible=False
End Sub