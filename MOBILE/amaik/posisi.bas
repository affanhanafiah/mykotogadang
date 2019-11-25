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
	Dim gpsClient As GPS
	Dim userLocation As Location
	Dim latUser, lngUser As Float
	Dim Server="http://192.168.1.2/kotogadang/andro/" As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim Label1 As Label
	Dim Panel1 As Panel
	Dim ImageView1 As ImageView
	Dim WebView1 As WebView
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("posisi")
	latUser=0
	lngUser=0
		gpsClient.Initialize("gpsClient")
		userLocation.Initialize
	posisi1
End Sub

Sub posisi1 
	WebView1.LoadUrl(""&Main.Server&"peta.php")
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

Sub petaClient
	WebView1.LoadUrl(""&Main.Server&"peta.php?lat="&latUser&"&lng="&lngUser)
End Sub




Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
gpsClient.Stop
End Sub




Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.server&"json.php", Query)
End Sub

Sub ImageView1_Click
	cekGPS
End Sub

