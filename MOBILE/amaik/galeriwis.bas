Type=Activity
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim ViewFoto="ViewFoto" As String
	Dim id As String

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private ImageView1 As ImageView
	Private ListView1 As ListView
	Private ScrollView1 As ScrollView
	Private ImageView2 As ImageView
	Private ImageView3 As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("galeri")
	id=detail_wis.id
	detail_wis.id=""
'	ResetImagesBackground
	fotoquery
End Sub

Sub fotoquery
	ProgressDialogShow("Loading")
	'eksekusi query mengambil data foto industri kerajinan berdasarkan id ik yang dipilih
	ExecuteRemoteQuery("select distinct id_tourism, gallery_tourism from tourism_gallery where id_tourism='"&id&"'","ViewFoto")
End Sub

Sub ExecuteRemoteQuery(Query As String, JobName As String)
	Dim Job As HttpJob
	Job.Initialize(JobName, Me)
	Job.PostString(""&Main.server&"json.php", Query)
End Sub

Sub Jobdone (Job As HttpJob)

	ProgressDialogHide
	If Job.Success Then
	Dim res As String
	res= Job.GetString
	Log ("Respon from server:" & res)
	Dim parser As JSONParser
	parser.Initialize(res)
	Select Job.JobName
		Case ViewFoto
'			ResetImagesBackground
			Dim agn As List
			agn=parser.NextArray
			If agn.Size - 1 < 0 Then
			Log(agn.Size)
				Msgbox("Gallery Photos Not Found", "Peringatan")
			Else
				Dim serverimage = "http://192.168.1.2/kotogadang/" As String
				
				For i=0 To agn.Size-1
					Dim w As Map
					w=agn.Get(i)	
					Log("haha")		
					Dim image = w.Get("gallery_tourism") As String
					'Dim image1 = serverimage&"/"&image As String
					Dim gambar = ""&serverimage&""&image As String
					If i=0 Then
						Dim link As Map
						link.Initialize
						'meletakkan foto pada imageview
						link.Put(ImageView1,gambar)
						CallSubDelayed2(imageDownloader, "Download", link)
					else if i=1 Then
						Dim link As Map
						link.Initialize
						'meletakkan foto pada imageview
						link.Put(ImageView2,gambar)
						CallSubDelayed2(imageDownloader, "Download", link)
					Else If i=2 Then
					Dim link As Map
						link.Initialize
						'meletakkan foto pada imageview
						link.Put(ImageView3,gambar)
						CallSubDelayed2(imageDownloader, "Download", link)
					End If	
				Next
					
			End If
	End Select
	End If

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
