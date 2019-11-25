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
	Private wis="wis" As String
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
	If  wis_nama.id<>"" Then
		id_kul2=wis_nama.id		
		Log("nama wiis")
		Log(id_kul2)
	Else if umkm_product.id<>"" Then
			id_kul2=umkm_product.id
			Log("product umkm")
	Else if umkm_tipe.id<>"" Then
			id_kul2=umkm_tipe.id
			Log("tipe umkm")
	End If
	End If
	
	Activity.LoadLayout("detailinfowis")
	Log(id)
	If  wis_nama.id<>"" Then
		id_kul2=wis_nama.id		
		Log("nama wis")
		Log(id_kul2)
	Else if wis_fas.id<>"" Then
			id_kul2=wis_fas.id
			Log("wis fas")
	Else if radius_wis.id<>"" Then
			id_kul2=radius_wis.id
			Log("rad wis")			
	
	End If
	kul_tem.id=""
	wis_fas.id=""
	kul_nama.id=""
	radius_wis.id=""
	
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
	ExecuteRemoteQuery("Select id, name, address, ticket,open,close,information,st_x(st_centroid(geom)), st_y(st_centroid(geom)) from tourism where tourism.id='"&id_kul2&"'","wis")
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
			Case wis
			Dim umkm_array As List
			umkm_array = parser.NextArray
				If (umkm_array.Size>0) Then
					Dim m As Map
					'meletakkan data detail industri kerajinan pada setiap text
					m= umkm_array.Get(0)
					id=m.Get("id")
					Label2.Text=m.Get("name")
					Label3.Text=m.Get("address")
					Label4.Text=m.Get("information")
					Label5.Text=m.Get("open")
					Label7.Text=m.Get("close")
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
	wisposisi.latTujuan=lat
	wisposisi.lngTujuan=lng	
	''activity menuju posisi industri kerajinan
	StartActivity("wisposisi")
End Sub

Sub Button2_Click 
	StartActivity(galeriwis)
End Sub
Sub ImageView4_Click  'activity menuju halaman awal
	StartActivity(Main)
End Sub