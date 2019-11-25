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
	
	Dim NAMAKUL1 ="NAMAKUL1" As String
	Dim JENISNAMA1 ="JENISNAMA1" As String
	Dim jenis_daftarkul As Map
	Type jeniskulnama_lines (isi1 As String,isi2 As String,isi3 As String,isi4 As String)
	Dim id As String
	Dim name As String
	Dim id, idkuliner As String 'id industri kecil
	Dim name As String
	Dim address As String
	Dim cp As String
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
	Private WebView1 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("kul_nama")
	
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
	Spinner1.Add("--Pilih Jenis--")
	ProgressDialogShow("Loading ....")
	ExecuteRemoteQuery("select * from culinary","NAMAKUL1") 'eksekusi query pencarian jenis ik
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
			Case JENISNAMA1
			Dim jeniskuliner_array As List
			jeniskuliner_array = parser.NextArray
			If jeniskuliner_array.Size - 1 < 0 Then
				Msgbox("Tidak ditemukan", "Peringatan")
			Else
				For i=0 To jeniskuliner_array.Size -1
					Dim a As Map
					a = jeniskuliner_array.Get(i)
					Dim b As jeniskulnama_lines
					b.Initialize
					b.isi1 = a.Get("id")
					b.isi2 = a.Get("name")
					b.isi3 = a.Get("address")
					b.isi4 = a.Get("cp")
					ListView1.AddSingleLine2(b.isi2,b)  
				Next
			End If
				
			Case NAMAKUL1
			Dim jenis_array As List
			jenis_daftarkul.Initialize
			jenis_array = parser.NextArray	
			If(jenis_array.Size>0) Then
				For i=0 To jenis_array.Size -1
					Dim a As Map
					a = jenis_array.Get(i)
					id=a.Get("id")
					name=a.Get("name")
					Spinner1.Add(name) 'meletakkan namajenis pada spinner
					jenis_daftarkul.Put(name, id)
				Next
			End If
			
		End Select
	End If	
	Job.Release
End Sub

Sub Spinner1_ItemClick (Position As Int, Value As Object)
	idkuliner= jenis_daftarkul.Get(Value) 'mengambil jenis dari spinner
End Sub




Sub Button1_Click
	Panel1.Visible=True
	Panel2.Visible=False
	ListView1.Clear
	'eksekusi query industrikerajinan berdasarkan jenis yang dipilih
	ExecuteRemoteQuery("select culinary_place.id,culinary_place.name,culinary_place.address, culinary_place.cp, ST_X(ST_Centroid(culinary_place.geom)) AS lng, ST_Y(ST_CENTROID(culinary_place.geom)) As lat from culinary_place join detail_culinary on culinary_place.id=detail_culinary.id_culinary_place where detail_culinary.id_culinary='"&idkuliner&"' order by name asc","JENISNAMA1")
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	'mengambil data id industrikerajinan yang akan dilihat detailnya
	Dim b As jeniskulnama_lines
	b=Value
	id=b.isi1
	name=b.isi2
	'address=b.isi3
	'cp=b.isi4
	StartActivity("detailKUL")
End Sub