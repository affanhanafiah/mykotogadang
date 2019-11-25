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
	
	Dim JENIS1 ="JENIS1" As String
	Dim JENISSOU1 ="JENISSOU1" As String
	Dim jenis_daftar As Map
	Type jenissou_lines (isi1 As String,isi2 As String,isi3 As String,isi4 As String)
	Dim id_jenis_oleh As String
	Dim jenis_oleh As String
	Dim id_oleh_oleh, idsou As String 'id industri kecil
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
	Activity.LoadLayout("sou_jenis")
	
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
	ExecuteRemoteQuery("select * from jenis_oleh_oleh","JENIS1") 'eksekusi query pencarian jenis ik
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
			Case JENISSOU1
			Dim jenisik_array As List
			jenisik_array = parser.NextArray
			If jenisik_array.Size - 1 < 0 Then
				Msgbox("Tidak ditemukan", "Peringatan")
			Else
				For i=0 To jenisik_array.Size -1
					Dim a As Map
					a = jenisik_array.Get(i)
					Dim b As jenissou_lines
					b.Initialize
					b.isi1 = a.Get("id_oleh_oleh")
					b.isi2 = a.Get("nama_oleh_oleh")
					b.isi3 = a.Get("alamat")
					b.isi4 = a.Get("cp")
					ListView1.AddSingleLine2(b.isi2,b) 'meletakkan nama souvenir pada listview
				Next
			End If
				
			Case JENIS1
			Dim jenis_array As List
			jenis_daftar.Initialize
			jenis_array = parser.NextArray	
			If(jenis_array.Size>0) Then
				For i=0 To jenis_array.Size -1
					Dim a As Map
					a = jenis_array.Get(i)
					id_jenis_oleh=a.Get("id_jenis_oleh")
					jenis_oleh=a.Get("jenis_oleh")
					Spinner1.Add(jenis_oleh) 'meletakkan namajenis pada spinner
					jenis_daftar.Put(jenis_oleh, id_jenis_oleh)
				Next
			End If
			
		End Select
	End If	
	Job.Release
End Sub

Sub Spinner1_ItemClick (Position As Int, Value As Object)
	idsou= jenis_daftar.Get(Value) 'mengambil jenis dari spinner
End Sub

Sub Button1_Click
	Panel1.Visible=True
	Panel2.Visible=False
	ListView1.Clear
	'eksekusi query industrikerajinan berdasarkan jenis yang dipilih
	ExecuteRemoteQuery("Select distinct id_oleh_oleh, nama_oleh_oleh, alamat, cp from oleh_oleh, jenis_oleh_oleh WHERE oleh_oleh.id_jenis_oleh='"&idsou&"' order by nama_oleh_oleh asc","JENISSOU1")
End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)
	'mengambil data id industrikerajinan yang akan dilihat detailnya
	Dim b As jenissou_lines
	b=Value
	id_oleh_oleh=b.isi1
	nama_oleh_oleh=b.isi2
	StartActivity("detailSOUV")
End Sub