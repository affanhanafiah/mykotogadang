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
	Dim id, id_ik2,id_angkot,cost,destination,warna,latik,lngik As String
	Dim latAsal, lngAsal, latTujuan, lngTujuan,mode,latsimpang,lngsimpang As String

	'Type angkotik_lines (isi1 As String,isi2 As String,isi3 As String,isi4 As String, isi5 As String)
	Dim ANGKOTIK="ANGKOTIK" As String
	Dim sekitar="sekitar" As String
	Dim SIMPANG="SIMPANG" As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim Panel1 As Panel
	Dim Panel2 As Panel
	Dim Label1 As Label
	Dim WebView1 As WebView
	Dim WebView2 As WebView
	Private ListView1 As ListView
	Private Button2 As Button
	Private ListView2 As ListView
End Sub





Sub Button3_Click
	StartActivity(near)	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("posisiIK")
	latAsal=dashboardd.latUser
	lngAsal=dashboardd.lngUser
	Log(dashboardd.latUser)
	Log(dashboardd.lngUser)
	id_ik2=detail_umkm.id
	detail_umkm.lng=""
	detail_umkm.lat=""
	mode="DRIVING"
	Panel1.Visible=False
	ListView2.Visible=True
	posisi1
End Sub

Sub posisi1 'menampilkan industri kerajinan pada webview
	WebView1.LoadUrl(""&Main.Server&"petaSaya.php?lat="&latTujuan&"&lng="&lngTujuan)
	Log(""&Main.Server&"petaSaya.php?lat="&latTujuan&"&lng="&lngTujuan)
	ListView2.Visible=False
End Sub



Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
Activity.Finish
End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.server&"json.php", Query)
End Sub

Sub Jobdone (Job As HttpJob)
	ProgressDialogHide
	If Job.Success Then
	Dim res As String
	res= Job.GetString
	Log ("Respon from server:" & res)
	Dim parser As JSONParser
	parser.Initialize(res)
		Select Job.JobName
			Case ANGKOTIK
			Dim angkotik_array As List
			angkotik_array = parser.NextArray
				For i=0 To angkotik_array.Size -1
					Dim a As Map
					a = angkotik_array.Get(i)
					Dim b As angkotik_lines
					b.Initialize
					b.isi1 = a.Get("id_angkot")
					b.isi2 = a.Get("destination")
					b.isi3 = a.Get("cost")
					b.isi4 = a.Get("track")
					latsimpang = a.Get("lat")
					lngsimpang = a.Get("long")
					ListView1.AddSingleLine2(b.isi2, b) 'meletakkan jurusan pada listview
					Log(b.isi2)

				Next
			Case sekitar
			Dim sekitar_array As List
			sekitar_array = parser.NextArray
				For i=0 To angkotik_array.Size -1
					Dim a As Map
					a = sekitar_array.Get(i)
					Dim b As angkotik_lines
					b.Initialize
					b.isi1 = a.Get("id")
					b.isi2 = a.Get("name")
					ListView1.AddSingleLine2(b.isi2, b) 'meletakkan jurusan pada listview
					Log(b.isi2)

				Next
		End Select
	End If
End Sub


Sub Button1_Click
	
	Panel2.Visible=False
	Panel1.Visible=True
	If latAsal=="0" And lngAsal=="0"   Then
	Msgbox("Click My Location First!!","Alert")
	StartActivity(Main)
	Else
	Dim lat As String
	Dim lng As String
	'ListView1.Visible=False
	Log("jalan")
	'menampilkan rute pada webview
	WebView2.LoadUrl(""&Main.server&"route.php?latAsal="&latAsal&"&lngAsal="&lngAsal&"&latTujuan="&latTujuan&"&lngTujuan="&lngTujuan&"&mode="&mode)		
	Log("jalan")
	End If
End Sub


Sub ListView1_ItemClick (Position As Int, Value As Object)
	'simpangik
	WebView2.Visible=True
	ListView1.Visible=False
	Dim b As angkotik_lines
	b=Value
	id_angkot=b.isi1
	destination=b.isi2
	warna="Red"
Log (id_ik2)
	Log(lngsimpang)	
	Log(latsimpang)	
	'menampilkan angkutan kota disekitar industri kerajinan pada webview
	WebView2.LoadUrl(Main.Server&"petaSaya.php?lat="&latTujuan&"&lng="&lngTujuan&"&warna="&warna&"&id_angkot="&id_angkot&"&latsimpang="&latsimpang&"&lngsimpang="&lngsimpang)	
	
End Sub




Sub Button2_Click 
	ListView1.Clear
	ProgressDialogShow("Loading ....")
	'eksekusi query angkutan kota industri kerajinan
	ExecuteRemoteQuery("Select distinct angkot.id_angkot,angkot.destination, angkot.cost, angkot.track,detail_umkm.lat, detail_Umkm.long, st_x(st_centroid(angkot.geom)) As longitude,st_y(st_centroid(angkot.geom)) As latitude FROM angkot left join detail_umkm on angkot.id_angkot=detail_umkm.id_angkot where detail_umkm.id_umkm='"&id_ik2&"'","ANGKOTIK")
'	ExecuteRemoteQuery("SELECT distinct id_angkot,jurusan, warna_angkot, jalur_angkot, warna, st_x(st_centroid(geom)) as lng,st_y(st_centroid(geom)) as lat,st_distance_sphere(ST_GeomFromText('POINT("&lngIK&" "&latIK&")',-1), angkot.geom) as jarak FROM angkot where st_distance_sphere(ST_GeomFromText('POINT("&lngIK&" "&latIK&")',-1), angkot.geom) <= 350 order by jarak", "ANGKOTIK")	
	
	Panel1.Visible=True
	WebView2.Visible=False
	Panel2.Visible=False
	
End Sub