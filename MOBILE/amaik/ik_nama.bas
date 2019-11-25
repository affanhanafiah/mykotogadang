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
	Private inputnama1 = "inputnama1" As String
	Private ik_nama = "ik_nama" As String
	Dim produk, nama_industri, cp, alamat, id, latitude, longitude As String
	Type daftarik_lines (isiIK1 As String,isiIK2 As String,isiIK3 As String,isiIK4 As String)
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
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("ik_nama")
	
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
	ExecuteRemoteQuery("SELECT distinct id, nama_industri,alamat, cp, st_x(st_centroid(geom)) as longitude, st_y(st_centroid(geom)) As latitude from industri_kecil_region where lower(nama_industri) like lower('%"&inputnama1&"%')","ik_nama")
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
			Case ik_nama
				Dim ik_nama_array As List
				ik_nama_array = parser.NextArray
				ListView1.Clear
				For i=0 To ik_nama_array.Size -1
				Dim m As Map
				m = ik_nama_array.Get(i)
				Dim b As daftarik_lines
				b.Initialize
				b.isiIK1 = m.Get("id")
				b.isiIK2 = m.Get("nama_industri")
				b.isiIK3 = m.Get("latitude")
				b.isiIK4 = m.Get("longitude")
				ListView1.AddSingleLine2(b.isiIK2,b)
			Next
		End Select
	End If
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim b As daftarik_lines
	b=Value
	id=b.isiIK1
	nama_industri=b.isiIK2
'	latitude=b.isiIK3
'	longitude=b.isiIK4
	StartActivity("detailIK")
'	Log (id)
End Sub

Sub EditText1_EnterPressed
	inputnama1 = EditText1.Text
	Log(inputnama1)
	iknama1
End Sub