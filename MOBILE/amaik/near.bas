Type=Activity
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
Private umkm_nama1 = "umkm_nama1" As String
Private umkm_nama2 = "umkm_nama2" As String
Private umkm_nama3 = "umkm_nama3" As String
Private umkm_nama4 = "umkm_nama4" As String
Private umkm_nama5 = "umkm_nama5" As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim latAsal, lngAsal, latTujuan, lngTujuan,mode,latsimpang,lngsimpang As String
	Dim kd1,kd2,kd3,kd4,kd5,radius2 As Int
	Private ListView1 As ListView
	Private WebView1 As WebView
	Private ListView3 As ListView
	Private ListView4 As ListView
	Private ListView5 As ListView
	Private ListView2 As ListView
	Private Panel2 As Panel
	Private CheckBox1 As CheckBox
	Private CheckBox3 As CheckBox
	Private CheckBox4 As CheckBox
	Private Button1 As Button
	Private ListView6 As ListView
	Private CheckBox2 As CheckBox
	Private WebView2 As WebView
	Private CheckBox5 As CheckBox
	Private CheckBox6 As CheckBox
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	latTujuan=ikposisi.latTujuan
	lngTujuan=ikposisi.lngTujuan
	Activity.LoadLayout("near")
	Panel1.Visible=False
	Panel2.Visible=True
	CheckBox1.Checked=False
	CheckBox2.Checked=False
	CheckBox3.Checked=False
	CheckBox4.Checked=False
	CheckBox5.Checked=False
End Sub





Sub iknama1
	ProgressDialogShow("Loading...")	
	
End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.Server&"json.php",Query)
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
			Case umkm_nama1
				Dim umkm_nama_array As List
				umkm_nama_array = parser.NextArray
				ListView1.Clear
				For i=0 To umkm_nama_array.Size -1
				Dim m As Map
				m = umkm_nama_array.Get(i)
				Dim b As daftarkul_lines
				b.Initialize
				b.isiKUL1 = m.Get("id")
				b.isiKUL2 = m.Get("name")
				ListView6.AddSingleLine2(b.isiKUL2,b)
			Next
			
		End Select
	End If
End Sub

 
Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub CheckBox1_CheckedChange(Checked As Boolean)
kd1=1
End Sub
Sub CheckBox2_CheckedChange(Checked As Boolean)
kd2=1
End Sub
Sub CheckBox3_CheckedChange(Checked As Boolean)
kd3=1
End Sub
Sub CheckBox4_CheckedChange(Checked As Boolean)
kd4=1
End Sub
Sub CheckBox5_CheckedChange(Checked As Boolean)
kd5=1
End Sub


Sub Button1_Click 
	ListView6.Clear
	radius2=500
	kd1=0
	kd2=0
	kd3=0
	kd4=0
	kd5=0
	
	If CheckBox1.Checked Then
	kd1=1
	ExecuteRemoteQuery("SELECT id, name, st_x(st_centroid(geom)) As lng,st_y(st_centroid(geom)) As lat,st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), umkm.geom) as jarak FROM umkm where st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), umkm.geom) <= "&radius2&"","umkm_nama1")
	End If
	
	If CheckBox2.Checked Then
	kd2=1
	ExecuteRemoteQuery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), culinary.geom) as jarak FROM culinary where st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), culinary.geom) <= "&radius2&"","umkm_nama1")
	
	End If
	If CheckBox3.Checked Then
	kd3=1
	ExecuteRemoteQuery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), mosque.geom) as jarak FROM mosque where st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), mosque.geom) <= "&radius2&"","umkm_nama1")
	
	End If
	If CheckBox4.Checked Then
	kd4=1
	ExecuteRemoteQuery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), greatcharacter.geom) as jarak FROM greatcharacter where st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), greatcharacter.geom) <= "&radius2&"","umkm_nama1")
	
	End If
	If CheckBox5.Checked Then
	kd5=1
	ExecuteRemoteQuery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), tourism.geom) as jarak FROM tourism where st_distance_sphere(ST_GeomFromText('POINT("&lngTujuan&" "&latTujuan&")',-1), tourism.geom) <= "&radius2&"","umkm_nama1")
	
	End If
	WebView2.LoadUrl(""&Main.Server&"sekitar.php?lat="&latTujuan&"&lng="&lngTujuan&"&kd1="&kd1&"&kd2="&kd2&"&kd3="&kd3&"&kd4="&kd4&"&kd5="&kd5)
	iknama1
End Sub
