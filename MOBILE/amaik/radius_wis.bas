Type=Activity
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private mesjid_radius = "mesjid_radius" As String
	Dim id, nama, latitude, longitude, jarak As String

	Type wis_lines (isi1 As String,isi2 As String,isi3 As String,isi4 As String, isi5 As String)

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private WebView1 As WebView
	Private SeekBar1 As SeekBar
	Private Button5 As Button
	Private ListView1 As ListView
	Private Button1 As Button
	Private Button2 As Button
	Private Button3 As Button
	Private Button4 As Button
	Dim radius1, rad2 As Double
	
	
	Private Label1 As Label
	Private Panel1 As Panel
	Private Panel2 As Panel
	Private Panel3 As Panel
	Private Panel4 As Panel
	Private Panel5 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	
	Activity.LoadLayout("radius")
	ListView1.TwoLinesLayout.Label.TextSize=14
	ListView1.TwoLinesLayout.SecondLabel.TextSize=13
	ListView1.Color=Colors.white
	ListView1.TwoLinesLayout.Label.TextColor=Colors.Black
	ListView1.TwoLinesLayout.SecondLabel.TextColor=Colors.Black
	ListView1.TwoLinesLayout.Label.Color=Colors.White
	ListView1.TwoLinesLayout.SecondLabel.Color=Colors.White
	ListView1.Visible=False
	posisi1
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
End Sub

Sub posisi1 
	WebView1.LoadUrl(""&Main.Server&"peta1.html")
End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.server&"json.php", Query)
End Sub

Sub mesjidradius
	ProgressDialogShow("Loading...")	
	ExecuteRemoteQuery("SELECT id, name,st_x(st_centroid(geom)) as longitude,st_y(st_centroid(geom)) as latitude,st_distance_sphere(ST_GeomFromText('POINT("&dashboardd.lngUser&" "&dashboardd.latUser&")', -1), tourism.geom) as jarak FROM tourism where st_distance_sphere(ST_GeomFromText('POINT("&dashboardd.lngUser&" "&dashboardd.latUser&")', -1), tourism.geom) <= "&radius1&" ","mesjid_radius")
	Log("SELECT id, name,st_x(st_centroid(geom)) as longitude,st_y(st_centroid(geom)) as latitude,st_distance_sphere(ST_GeomFromText('POINT("&dashboardd.lngUser&" "&dashboardd.latUser&")', -1), tourism.geom) as jarak FROM tourism where st_distance_sphere(ST_GeomFromText('POINT("&dashboardd.lngUser&" "&dashboardd.latUser&")', -1), tourism.geom) <= "&radius1&"")
End Sub

Sub JobDone(Job As HttpJob)
	ProgressDialogHide
	If Job.Success Then
		Dim res As String
		res = Job.GetString
		Log("Response from server :"& res)
		Dim parser As JSONParser
		parser.Initialize(res)
		Select Job.JobName
			Case mesjid_radius
			Dim mes_rad_array As List
			mes_rad_array = parser.NextArray
			If mes_rad_array.Size -1 < 0 Then
				Msgbox("Tourism Not Found", "Notice")
			Else
			Log("hy")
			ListView1.Clear
			For i=0 To mes_rad_array.Size -1
			Dim a As Map
			a = mes_rad_array.Get(i)
			Dim b As wis_lines
			b.Initialize
			b.isi1 = a.Get("id")
			b.isi2 = a.Get("name")
			rad2=a.Get("jarak")
			rad2=Floor(rad2)
			b.isi3=rad2&" m"
			b.isi4 = a.Get("latitude")
			b.isi5 = a.Get("longitude")
			ListView1.AddTwoLines2(b.isi2, b.isi3,b)
			Next
			End If
		End Select
	End If
		
End Sub



Sub SeekBar1_ValueChanged (Value As Int, UserChanged As Boolean)
	radius1=SeekBar1.Value*10
	Log(radius1)
End Sub

Sub Button1_Click
		If dashboardd.latUser=="0" And dashboardd.lnguser=="0" Then
		Msgbox("Click Button Current Position First","Alert")
		StartActivity(dashboardd)
	Else
		ListView1.Visible=True
		ListView1.Clear
		WebView1.LoadUrl(""&Main.Server&"wis_radius.php?lat="&dashboardd.latUser&"&lng="&dashboardd.lngUser&"&rad="&radius1)
		mesjidradius
		End If
End Sub



Sub Button2_Click
	StartActivity(dashboardd)
End Sub

Sub Button3_Click
	StartActivity(umkm_nama)
End Sub

Sub Button4_Click
	
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim b As wis_lines
	b=Value
	id=b.isi1
	nama=b.isi2
	latitude=b.isi4
	longitude=b.isi5
	Log("posisi "&dashboardd.latUser &" "&dashboardd.lngUser)
	StartActivity("detail_wis")
End Sub