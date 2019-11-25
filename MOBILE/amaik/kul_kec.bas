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
	
	Dim KEC11 ="KEC11" As String
	Dim KECKUL11 ="KECKUL11" As String
	Dim jenis_daftar As Map
	Type jeniskeckul_lines (isi1 As String,isi2 As String,isi3 As String,isi4 As String)
	Dim id_kecamatan As String
	Dim nama_kecamatan As String
	Dim id, idkulkec As String 
	Dim name As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Dim Button1 As Button
	Dim Label1 As Label
	Dim Label2 As Label
	Dim Label3 As Label
	Dim Panel1 As Panel
	Dim Panel2 As Panel
	Dim Spinner1 As Spinner
	Dim ListView1 As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("kul_kec")
	
	Panel1.Visible=False
	daftarjenis
	
	ListView1.SingleLineLayout.Label.TextColor=Colors.RGB(72, 39, 39)
	ListView1.SingleLineLayout.Label.TextSize=16
	'Spinner1.TextColor=Colors.RGB(43, 154, 188)
	Spinner1.DropdownBackgroundColor=Colors.RGB(255, 255, 255)
	'ListView1.SingleLineLayout.Label.TextColor=Colors.RGB(43, 154, 188)
	ListView1.SingleLineLayout.Label.Left=2%y
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
Activity.Finish
End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.Server&"json.php", Query)
End Sub

Sub daftarjenis
	Spinner1.Add("--Pilih Kecamatan--")
	ProgressDialogShow("Loading ....")
	ExecuteRemoteQuery("select * from district","KEC11") 'eksekusi query pencarian jenis ik
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
			Case KECKUL11
			Dim kecik_array As List
			kecik_array = parser.NextArray
			If kecik_array.Size - 1 < 0 Then
				Msgbox("Tidak ditemukan", "Peringatan")
			Else
				For i=0 To kecik_array.Size -1
					Dim a As Map
					a = kecik_array.Get(i)
					Dim b As jeniskeckul_lines
					b.Initialize
					b.isi1 = a.Get("id")
					b.isi2 = a.Get("name")
					b.isi3 = a.Get("address")
					b.isi4 = a.Get("cp")
					ListView1.AddSingleLine2(b.isi2,b) 'meletakkan nama industri kecil pada listview
				Next
			End If
				
			Case KEC11
			Dim jenis_array As List
			jenis_daftar.Initialize
			jenis_array = parser.NextArray	
			If(jenis_array.Size>0) Then
				For i=0 To jenis_array.Size -1
					Dim a As Map
					a = jenis_array.Get(i)
					id=a.Get("id")
					name=a.Get("name")
					Spinner1.Add(name) 
					jenis_daftar.Put(name, id)
				Next
			End If
			
		End Select
	End If	
	Job.Release
End Sub

Sub Spinner1_ItemClick (Position As Int, Value As Object)
	idkulkec= jenis_daftar.Get(Value) 'mengambil jenis dari spinner
End Sub

Sub Button1_Click
	Panel1.Visible=True
	Panel2.Visible=False
	ListView1.Clear
	'eksekusi query industrikerajinan berdasarkan jenis yang dipilih
	ExecuteRemoteQuery("SELECT distinct a.id, a.name, a.address, a.cp, ST_X(ST_Centroid(a.geom)) AS longitude, ST_Y(ST_CENTROID(a.geom)) As latitude from culinary_place as a, district WHERE st_contains(district.geom, a.geom) and district.id='"&idkulkec&"' order by a.name asc","KECKUL11")
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	'mengambil data id industrikerajinan yang akan dilihat detailnya
	Dim b As jeniskeckul_lines
	b=Value
	id=b.isi1
	name=b.isi2
	StartActivity("detailKUL")
End Sub