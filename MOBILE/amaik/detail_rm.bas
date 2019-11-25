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
	Private rm="rm" As String
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
	Dim Label6 As Label
	Dim Label7 As Label
	Dim Label8 As Label
	Dim Panel1 As Panel
	Dim Panel2 As Panel
	Dim ImageView1 As ImageView
	Dim ImageView2 As ImageView
	Dim ImageView3 As ImageView
	Dim ImageView4 As ImageView
	Dim ImageView5 As ImageView
	
	Dim id_kul2 As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If FirstTime Then
	If  rm_nama.id<>"" Then
		id_kul2=rm_nama.id		
		Log("nama rm")
		Log(id_kul2)
	Else if rm_menu.id<>"" Then
			id_kul2=rm_menu.id
			Log("menu rm")
	Else if umkm_tipe.id<>"" Then
			id_kul2=umkm_tipe.id
			Log("tipe umkm")
	End If
	End If
	
	Activity.LoadLayout("detailinform")
	Log(id)
	If  rm_nama.id<>"" Then
		id_kul2=rm_nama.id		
		Log("nama rm")
		Log(id_kul2)
	Else if rm_menu.id<>"" Then
			id_kul2=rm_menu.id
			Log("menu rm")
	Else if kul_nama.id<>"" Then
			id_kul2=kul_nama.id
			Log("nama kul")
	Else if radius_rm.id<>"" Then
			id_kul2=radius_rm.id
			Log("nama kul")				
	
	End If
	kul_tem.id=""
	kul_kec.id=""
	kul_nama.id=""
	radius_rm.id=""
	
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
	ExecuteRemoteQuery("Select id, name, address, special_menu, price,open,close, st_x(st_centroid(geom)), st_y(st_centroid(geom)) from culinary where culinary.id='"&id_kul2&"'","rm")
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
			Case rm
			Dim umkm_array As List
			umkm_array = parser.NextArray
				If (umkm_array.Size>0) Then
					Dim m As Map
					'meletakkan data detail industri kerajinan pada setiap text
					m= umkm_array.Get(0)
					id=m.Get("id")
					Label2.Text=m.Get("name")
					Label3.Text=m.Get("address")
					Label4.Text=m.Get("special_menu")
					Label5.Text=m.Get("price")
					Label6.Text=m.Get("open")
					Label8.Text=m.Get("close")
					lng=m.Get("st_x")
					lat=m.Get("st_y")
					Log(Label2)
				Else
					Msgbox("No Data ","Info")
				End If		
		End Select
	End If	
End Sub

Sub Button1_Click 
	'mengambil lat dan long untuk di pakai di activity posisiIK
	kulposisi.latTujuan=lat
	kulposisi.lngTujuan=lng	
	''activity menuju posisi industri kerajinan
	StartActivity("kulposisi")
End Sub

Sub Button2_Click 'activity menuju galeri ik
	StartActivity(galeri_rm2)
End Sub
Sub ImageView4_Click  'activity menuju halaman awal
	StartActivity(Main)
End Sub