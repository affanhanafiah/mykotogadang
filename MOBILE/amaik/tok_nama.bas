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
	Private inputnama11 = "inputnama11" As String
	Private tok_nama = "tok_nama" As String
	Dim  name, cp, address, id, latitude, longitude As String
	'Type daftarkul_lines (isiKUL1 As String,isiKUL2 As String,isiKUL3 As String,isiKUL4 As String)
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Private EditText1 As EditText
	
	Private Label1 As Label
	Private Label2 As Label
	
	Private ListView1 As ListView
	Private Panel1 As Panel
	Private Panel2 As Panel
	Private WebView1 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("tok_nama")
		WebView1.Visible=False
	ListView1.Visible=True
	Label2.Visible=False
	Panel2.Visible=True
		ListView1.SingleLineLayout.Label.TextColor=Colors.RGB(72, 39, 39)
	ListView1.SingleLineLayout.Label.TextSize=16
	ListView1.SingleLineLayout.Label.Left=2%y
	'ListView1.Color=Colors.RGB(255,255,255)
	EditText1.Color=Colors.RGB(167, 115, 93)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub iknama1
	ProgressDialogShow("Loading...")	
	ExecuteRemoteQuery("SELECT distinct id, name,address, information, st_x(st_centroid(geom)) as longitude, st_y(st_centroid(geom)) As latitude from greatcharacter where lower(name) like lower('%"&inputnama11&"%')","tok_nama")
End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.Server&"json.php",Query)
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
			Case tok_nama
				Dim umkm_nama_array As List
				umkm_nama_array = parser.NextArray
				ListView1.Clear
				For i=0 To umkm_nama_array.Size -1
				Dim m As Map
				m = umkm_nama_array.Get(i)
				Dim b As daftarkul_lines
				b.Initialize
				b.isiKUL1 = m.Get("id")
				b.isiKUL2 = m.Get("name")
				b.isiKUL3 = m.Get("latitude")
				b.isiKUL4 = m.Get("longitude")
				ListView1.AddSingleLine2(b.isiKUL2,b)
			Next
		End Select
	End If
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim b As daftarkul_lines
	b=Value
	id=b.isiKUL1
	name=b.isiKUL2

	StartActivity("detail_tok")

End Sub

Sub EditText1_EnterPressed
	inputnama11 = EditText1.Text
	Log(inputnama11)
	WebView1.Visible=True
	ListView1.Visible=True
	EditText1.Visible=False
	Label1.Visible=False
	Label2.Visible=True
	WebView1.LoadUrl(""&Main.Server&"tok_nama.php?cari_nama="&EditText1.Text)
	iknama1
End Sub