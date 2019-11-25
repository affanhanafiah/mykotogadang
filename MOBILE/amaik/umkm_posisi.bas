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
	Dim latAsal, lngAsal, latTujuan, lngTujuan As String
	Dim id_ik, id_ik2, lngIK, latIK, jurusan, jalur_angkot, warna_angkot, mode, id_angkot,  id_angkot2, warna, latsimpang, lngsimpang As String
	Type angkotik_lines (isi1 As String,isi2 As String,isi3 As String,isi4 As String, isi5 As String)
	Dim ANGKOTIK="ANGKOTIK" As String
	Dim SIMPANG="SIMPANG" As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim bJalurA As Button
	Dim Label2 As Label
	Dim Panel1 As Panel
	Dim Panel2 As Panel
	Dim WebView1 As WebView
	Dim ImageView2 As ImageView
	Dim bRoute As Button
	Dim Label1 As Label
	Dim ListView1 As ListView

	Dim WebView2 As WebView
	Dim Panel4 As Panel
	Dim Panel3 As Panel
	
	Dim Label3 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("posisiikjenis")
	

	posisi1
	
	ListView1.SingleLineLayout.Label.TextColor=Colors.RGB(105, 105, 105)
	ListView1.SingleLineLayout.Label.TextSize=14
	Panel3.Visible=False
	
End Sub
Sub posisi1 'menampilkan industri kerajinan pada webview
	WebView2.LoadUrl(""&Main.Server&"petaSaya.php?lat="&latTujuan&"&lng="&lngTujuan)
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
					b.isi2 = a.Get("jurusan")
					b.isi3 = a.Get("warna_angkot")
					b.isi4 = a.Get("jalur_angkot")
					b.isi5 = a.Get("warna")
					latsimpang = a.Get("lat")
					lngsimpang = a.Get("lng")
					ListView1.AddSingleLine2(b.isi2, b) 'meletakkan jurusan pada listview
					Log(b.isi2)

				Next
		End Select
	End If
End Sub
Sub bJalurA_Click
	ListView1.Clear
	latIK=latTujuan
	lngIK=lngTujuan
	ProgressDialogShow("Loading ....")
	'eksekusi query angkutan kota industri kerajinan
	ExecuteRemoteQuery("Select distinct angkot.id_angkot,angkot.jurusan, angkot.warna_angkot, angkot.jalur_angkot,angkot.warna, detailangkotik.lat, detailangkotik.lng, st_x(st_centroid(angkot.geom)) As longitude,st_y(st_centroid(angkot.geom)) As latitude,st_distance_sphere(ST_GeomFromText('POINT("&lngIK&" "&latIK&")',-1), angkot.geom) as jarak FROM angkot left join detailangkotik on angkot.id_angkot=detailangkotik.id_angkot where st_distance_sphere (ST_GeomFromText('POINT("&lngIK&" "&latIK&")',-1), angkot.geom) <= 350  and detailangkotik.id_ik='"&id_ik2&"'","ANGKOTIK")
'	ExecuteRemoteQuery("SELECT distinct id_angkot,jurusan, warna_angkot, jalur_angkot, warna, st_x(st_centroid(geom)) as lng,st_y(st_centroid(geom)) as lat,st_distance_sphere(ST_GeomFromText('POINT("&lngIK&" "&latIK&")',-1), angkot.geom) as jarak FROM angkot where st_distance_sphere(ST_GeomFromText('POINT("&lngIK&" "&latIK&")',-1), angkot.geom) <= 350 order by jarak", "ANGKOTIK")	
'	simpangik

	Panel4.Visible=True
	Panel3.Visible=False
End Sub
'Sub tampilAngkot(warna As String, id_angkot As String)
'
'End Sub
Sub ImageView2_Click
	StartActivity("Main")
End Sub
Sub bRoute_Click
	Panel4.Visible=False
	Panel3.Visible=True
	ListView1.Visible=False
	If latAsal=="0" And lngAsal=="0"   Then
	Msgbox("Aktifkan lokasi saat ini","Alert")
	StartActivity(Main)
	Else
	Log("jalan")
	'menampilkan rute pada webview
	WebView1.LoadUrl(""&Main.server&"route.php?latAsal="&latAsal&"&lngAsal="&lngAsal&"&latTujuan="&latTujuan&"&lngTujuan="&lngTujuan&"&mode="&mode)		
	Log("jalan")
	End If
End Sub
Sub Label1_Click
	
End Sub
Sub Panel3_Click
	
End Sub
Sub Button3_Click
	WebView1.LoadUrl(""&Main.Server&"sekitar.php?lat="&latTujuan&"&lng="&lngTujuan)
End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)
'	simpangik
	Dim b As angkotik_lines
	b=Value
	id_angkot=b.isi1
	jurusan=b.isi2
	warna=b.isi5

	Log (id_ik2)
	Log(lngsimpang)	
	Log(latsimpang)	
	'menampilkan angkutan kota disekitar industri kerajinan pada webview
	WebView2.LoadUrl(Main.Server&"petaSaya.php?lat="&latTujuan&"&lng="&lngTujuan&"&warna="&warna&"&id_angkot="&id_angkot&"&latsimpang="&latsimpang&"&lngsimpang="&lngsimpang)
End Sub