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
	
	Private IK="IK" As String
	Dim id, lat, lng As String
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
	Dim id_ik2 As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If FirstTime Then
	If  ik_nama.id<>"" Then
		id_ik2=ik_nama.id		
		Log("nama ik")
		Log(id_ik2)
	Else if ik_kec.id<>"" Then
			id_ik2=ik_kec.id
			Log("kecamatan ik")
	Else if ik_jenis.id<>"" Then
			id_ik2=ik_jenis.id
			Log("tipe ik")			
			
	
	End If	
	End If

	Activity.LoadLayout("detailinfoik")
	
	Log(id)
	If  ik_nama.id<>"" Then
		id_ik2=ik_nama.id		
		Log("nama ik")
		Log(id_ik2)
	Else if ik_kec.id<>"" Then
			id_ik2=ik_kec.id
			Log("kecamatan ik")
	Else If ik_jenis.id<>"" Then
			id_ik2=ik_jenis.id
			Log("tipe ik")
	
	End If
	ik_nama.id=""
	ik_kec.id=""
	ik_jenis.id=""
	
	detailIK2

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

Sub detailIK2
	ProgressDialogShow("Loading ....")	
	ExecuteRemoteQuery("Select id, nama_industri, alamat, produk,harga_barang, st_x(st_centroid(geom)), st_y(st_centroid(geom)) from industri_kecil_region where industri_kecil_region.id='"&id_ik2&"'","IK")
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
			Case IK
			Dim ik_array As List
			ik_array = parser.NextArray
				If (ik_array.Size>0) Then
					Dim m As Map
					'meletakkan data detail industri kerajinan pada setiap text
					m= ik_array.Get(0)
					id=m.Get("id")
					Label2.Text=m.Get("nama_industri")
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

Sub Button1_Click 
	'mengambil lat dan long untuk di pakai di activity posisiIK
	ikposisi.latTujuan=lat
	ikposisi.lngTujuan=lng	
	''activity menuju posisi industri kerajinan
	StartActivity("ikposisi")
End Sub

Sub Button2_Click 'activity menuju galeri ik
	StartActivity(ik_gallery)
End Sub
Sub ImageView4_Click  'activity menuju halaman awal
	StartActivity(Main)
End Sub