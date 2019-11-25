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
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Dim ImageView1 As ImageView
	Dim Label1 As Label
	Dim ListView1 As ListView
	Dim Panel1 As Panel
	Dim Button1 As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("search_sou")
	
	ListView1.Height=160dip
	ListView1.SingleLineLayout.ItemHeight=40dip
	ListView1.AddSingleLine("Berdasarkan Nama")
	ListView1.AddSingleLine("Berdasarkan Kecamatan")
	ListView1.AddSingleLine("Berdasarkan Tipe")
	ListView1.AddSingleLine("Filter")
	
	ListView1.SingleLineLayout.Label.TextSize=14
	ListView1.SingleLineLayout.Label.TextColor=Colors.RGB(83, 1, 22)
	ListView1.SingleLineLayout.Label.Left=2%y
	ListView1.Visible=False

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim menu = Value As String
	Select menu
		Case "Berdasarkan Tipe"
			ListView1.Visible=True
			StartActivity("sou_jenis")
			ListView1.Visible=False
		Case "Berdasarkan Kecamatan"
			ListView1.Visible=True
			StartActivity("sou_kec")
			ListView1.Visible=False
		Case "Berdasarkan Nama"
			ListView1.Visible=True
			StartActivity("sou_nama")
			ListView1.Visible=False
	End Select	
End Sub

Sub Button1_Click
	If (ListView1.Visible=False) Then
		ListView1.Visible=True
		Button1.Visible=True
	Else 
		ListView1.Visible=False
		Button1.Visible=True
	End If
End Sub


