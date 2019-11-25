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
	Private SOU="SOU" As String
	Dim id_oleh_oleh, lat, lng As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim Button1 As Button
	Dim Button2 As Button
	Dim Label1 As Label
	Dim Label2 As Label
	Dim Label3 As Label
	Dim Label4 As Label
	Dim Label5 As Label
	Dim Panel1 As Panel
	Dim Panel2 As Panel
	Dim ImageView1 As ImageView
	Dim ImageView2 As ImageView
	Dim ImageView3 As ImageView
	Dim ImageView4 As ImageView
	Dim id_sou2 As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If FirstTime Then
	If  sou_nama.id_oleh_oleh<>"" Then
		id_sou2=sou_nama.id_oleh_oleh		
		Log("nama sou")
		Log(id_sou2)
	Else if sou_kec.id_oleh_oleh<>"" Then
			id_sou2=sou_kec.id_oleh_oleh
			Log("kecamatan sou")
	Else if sou_jenis.id_oleh_oleh<>"" Then
			id_sou2=sou_jenis.id_oleh_oleh
			Log("tipe sou")			
	End If	
	End If
	
	Activity.LoadLayout("detailinfosou")
	
		Log(id_oleh_oleh)
	If  sou_nama.id_oleh_oleh<>"" Then
		id_sou2=sou_nama.id_oleh_oleh		
		Log("nama sou")
		Log(id_sou2)
	Else if sou_kec.id_oleh_oleh<>"" Then
			id_sou2=sou_kec.id_oleh_oleh
			Log("kecamatan sou")
	Else if sou_jenis.id_oleh_oleh<>"" Then
			id_sou2=sou_jenis.id_oleh_oleh
			Log("tipe sou")		
	
	End If
	sou_nama.id_oleh_oleh=""
	sou_kec.id_oleh_oleh=""
	sou_jenis.id_oleh_oleh=""
	
	detailSOU2
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.server&"json.php", Query)
End Sub

Sub detailSOU2
	ProgressDialogShow("Loading ....")	
	ExecuteRemoteQuery("Select id_oleh_oleh, nama_oleh_oleh, alamat, produk,harga_barang, st_x(st_centroid(geom)), st_y(st_centroid(geom)) from oleh_oleh where oleh_oleh.id_oleh_oleh='"&id_sou2&"'","SOU")
End Sub

Sub JobDone(Job As HttpJob)
ProgressDialogHide
	If Job.Success Then
		Dim res As String
		res = Job.GetString
		Log("Response from server :"&res)
		Dim parser As JSONParser
		parser.Initialize(res)
		Select Job.JobName
			Case SOU
			Dim ik_array As List
			ik_array = parser.NextArray
				If (ik_array.Size>0) Then
					Dim m As Map
					'meletakkan data detail industri kerajinan pada setiap text
					m= ik_array.Get(0)
					id_oleh_oleh=m.Get("id_oleh_oleh")
					Label2.Text=m.Get("nama_oleh_oleh")
					Label3.Text=m.Get("alamat")
					Label4.Text=m.Get("produk")
					Label5.Text=m.Get("harga_barang")
					lng=m.Get("st_x")
					lat=m.Get("st_y")
					Log(Label2)
				Else
					Msgbox("Data Tidak Ada","Info")
				End If		
		End Select
	End If	
End Sub

Sub bLokasi_Click 
	'mengambil lat dan long untuk di pakai di activity posisiIK
	'ik_posisi.latTujuan=lat
	'ik_posisi.lngTujuan=lng	
	''activity menuju posisi industri kerajinan
	'StartActivity("sou_posisi")
End Sub

Sub bGalery_Click 'activity menuju galeri ik
	'StartActivity(sou_gallery)
End Sub
Sub ImageView4_Click  'activity menuju halaman awal
	StartActivity(Main)
End Sub