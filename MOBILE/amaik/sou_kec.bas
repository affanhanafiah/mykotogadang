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
	Dim KEC1 ="KEC1" As String
	Dim KECIK1 ="KECIK1" As String
	Dim jenis_daftar As Map
	Type jeniskecsou_lines (isi11 As String,isi22 As String,isi33 As String,isi44 As String)
	Dim id_kecamatan As String
	Dim nama_kecamatan As String
	Dim id_oleh_oleh, idikec As String 
	Dim nama_oleh_oleh As String
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
	Activity.LoadLayout("sou_kec")
	
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
	ExecuteRemoteQuery("select * from kecamatan","KEC1") 'eksekusi query pencarian jenis ik
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
			Case KECIK1
			Dim kecik_array As List
			kecik_array = parser.NextArray
			If kecik_array.Size - 1 < 0 Then
				Msgbox("Tidak ditemukan", "Peringatan")
			Else
				For i=0 To kecik_array.Size -1
					Dim a As Map
					a = kecik_array.Get(i)
					Dim z As jeniskecsou_lines
					z.Initialize
					z.isi11 = a.Get("id_oleh_oleh")
					z.isi22 = a.Get("nama_oleh_oleh")
					z.isi33 = a.Get("alamat")
					z.isi44 = a.Get("cp")
					ListView1.AddSingleLine2(z.isi22,z) 'meletakkan nama industri kecil pada listview
				Next
			End If
				
			Case KEC1
			Dim jenis_array As List
			jenis_daftar.Initialize
			jenis_array = parser.NextArray	
			If(jenis_array.Size>0) Then
				For i=0 To jenis_array.Size -1
					Dim a As Map
					a = jenis_array.Get(i)
					id_kecamatan=a.Get("id_kecamatan")
					nama_kecamatan=a.Get("nama_kecamatan")
					Spinner1.Add(nama_kecamatan) 
					jenis_daftar.Put(nama_kecamatan, id_kecamatan)
				Next
			End If
			
		End Select
	End If	
	Job.Release
End Sub

Sub Spinner1_ItemClick (Position As Int, Value As Object)
	idikec= jenis_daftar.Get(Value) 'mengambil jenis dari spinner
End Sub

Sub Button1_Click
	Panel1.Visible=True
	Panel2.Visible=False
	ListView1.Clear
	'eksekusi query industrikerajinan berdasarkan jenis yang dipilih
	ExecuteRemoteQuery("SELECT distinct a.id_oleh_oleh, a.nama_oleh_oleh, a.alamat, a.cp, ST_X(ST_Centroid(a.geom)) AS longitude, ST_Y(ST_CENTROID(a.geom)) As latitude from oleh_oleh as a, kecamatan WHERE st_contains(kecamatan.geom, a.geom) and kecamatan.id_kecamatan='"&idikec&"' order by nama_oleh_oleh asc","KECIK1")
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	'mengambil data id industrikerajinan yang akan dilihat detailnya
	Dim z As jeniskecsou_lines
	z=Value
	id_oleh_oleh=z.isi11
	nama_oleh_oleh=z.isi22
	StartActivity("detailSOUV")
End Sub
