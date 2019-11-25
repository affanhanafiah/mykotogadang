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
	Private UMKM="UMKM" As String
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

	Dim id_kul2 As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If FirstTime Then
	If  umkm_nama.id<>"" Then
		id_kul2=umkm_nama.id		
		Log("nama umkm")
		Log(id_kul2)
	Else if umkm_product.id<>"" Then
			id_kul2=umkm_product.id
			Log("product umkm")
	Else if umkm_tipe.id<>"" Then
			id_kul2=umkm_tipe.id
			Log("tipe umkm")
	End If
	End If
	
	Activity.LoadLayout("detailinfoumkm")
	Log(id)
	If  umkm_nama.id<>"" Then
		id_kul2=umkm_nama.id		
		Log("nama umkm")
		Log(id_kul2)
	Else if umkm_product.id<>"" Then
			id_kul2=umkm_product.id
			Log("product umkm")
	Else if kul_nama.id<>"" Then
			id_kul2=kul_nama.id
			Log("nama kul")
	Else if radius.id<>"" Then
			id_kul2=radius.id
			Log("rad kul")			
	
	End If
	kul_tem.id=""
	kul_kec.id=""
	kul_nama.id=""
	radius.id=""
	detailKUL2
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

Sub detailKUL2
	ProgressDialogShow("Loading ....")	
	ExecuteRemoteQuery("Select id, name, address, cp, owner, st_x(st_centroid(geom)), st_y(st_centroid(geom)) from umkm where umkm.id='"&id_kul2&"'","UMKM")
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
			Case UMKM
			Dim umkm_array As List
			umkm_array = parser.NextArray
				If (umkm_array.Size>0) Then
					Dim m As Map
					'meletakkan data detail industri kerajinan pada setiap text
					m= umkm_array.Get(0)
					id=m.Get("id")
					Label2.Text=m.Get("name")
					Label3.Text=m.Get("address")
					Label4.Text=m.Get("cp")
					Label5.Text=m.Get("owner")
					lng=m.Get("st_x")
					lat=m.Get("st_y")
					
				Else
					Msgbox("No Data ","Info")
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

Sub bGalery_Click 'activity menuju galeri ik
	StartActivity(galeri)
End Sub
Sub ImageView4_Click  'activity menuju halaman awal
	StartActivity(Main)
End Sub
Sub Button2_Click
	StartActivity(galeri)
End Sub