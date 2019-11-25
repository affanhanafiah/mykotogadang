package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class umkm_nama extends Activity implements B4AActivity{
	public static umkm_nama mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.umkm_nama");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (umkm_nama).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.umkm_nama");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.umkm_nama", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (umkm_nama) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (umkm_nama) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return umkm_nama.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (umkm_nama) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (umkm_nama) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _inputnama11 = "";
public static String _umkm_nama = "";
public static String _name = "";
public static String _cp = "";
public static String _address = "";
public static String _id = "";
public static String _latitude = "";
public static String _longitude = "";
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.WebViewWrapper _webview1 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
public b4a.example.dashboardd _dashboardd = null;
public b4a.example.detail_umkm _detail_umkm = null;
public b4a.example.galeri _galeri = null;
public b4a.example.radius _radius = null;
public b4a.example.ikposisi _ikposisi = null;
public b4a.example.detail_rm _detail_rm = null;
public b4a.example.neartok _neartok = null;
public b4a.example.tokposisi _tokposisi = null;
public b4a.example.wisposisi _wisposisi = null;
public b4a.example.nearwis _nearwis = null;
public b4a.example.near _near = null;
public b4a.example.nearculinary _nearculinary = null;
public b4a.example.kulposisi _kulposisi = null;
public b4a.example.nearmes _nearmes = null;
public b4a.example.mesposisi _mesposisi = null;
public b4a.example.radius_mosque _radius_mosque = null;
public b4a.example.dashboard _dashboard = null;
public b4a.example.ik_posisi _ik_posisi = null;
public b4a.example.imagedownloader _imagedownloader = null;
public b4a.example.detail_mes _detail_mes = null;
public b4a.example.galeri_rm _galeri_rm = null;
public b4a.example.galerirm _galerirm = null;
public b4a.example.rm_nama _rm_nama = null;
public b4a.example.search_wis _search_wis = null;
public b4a.example.wis_fas _wis_fas = null;
public b4a.example.search_umkm _search_umkm = null;
public b4a.example.wis_nama _wis_nama = null;
public b4a.example.detail_wis _detail_wis = null;
public b4a.example.search_rm _search_rm = null;
public b4a.example.radius_rm _radius_rm = null;
public b4a.example.search_mosque _search_mosque = null;
public b4a.example.posisi _posisi = null;
public b4a.example.umkm_posisi _umkm_posisi = null;
public b4a.example.search_tok _search_tok = null;
public b4a.example.detail_tok _detail_tok = null;
public b4a.example.radius_wis _radius_wis = null;
public b4a.example.kul_nama _kul_nama = null;
public b4a.example.rm_menu _rm_menu = null;
public b4a.example.mes_nama _mes_nama = null;
public b4a.example.tok_nama _tok_nama = null;
public b4a.example.sou_jenis _sou_jenis = null;
public b4a.example.detailik _detailik = null;
public b4a.example.starter _starter = null;
public b4a.example.detailkul _detailkul = null;
public b4a.example.search_cul _search_cul = null;
public b4a.example.umkm_product _umkm_product = null;
public b4a.example.sou_nama _sou_nama = null;
public b4a.example.search_ik _search_ik = null;
public b4a.example.search_sou _search_sou = null;
public b4a.example.ik_kec _ik_kec = null;
public b4a.example.ik_nama _ik_nama = null;
public b4a.example.kul_kec _kul_kec = null;
public b4a.example.kul_fasilitas _kul_fasilitas = null;
public b4a.example.kul_tem _kul_tem = null;
public b4a.example.posisiik _posisiik = null;
public b4a.example.ik_jeniss _ik_jeniss = null;
public b4a.example.detailsouv _detailsouv = null;
public b4a.example.ik_jenis _ik_jenis = null;
public b4a.example.sou_kec _sou_kec = null;
public b4a.example.ik_gallery _ik_gallery = null;
public b4a.example.detailsou _detailsou = null;
public b4a.example.umkm_tipe _umkm_tipe = null;
public b4a.example.galero _galero = null;
public b4a.example.radius_tok _radius_tok = null;
public b4a.example.galerimes _galerimes = null;
public b4a.example.galeritok _galeritok = null;
public b4a.example.galeriwis _galeriwis = null;
public b4a.example.imagedownloader1 _imagedownloader1 = null;
public b4a.example.galeri_rm2 _galeri_rm2 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"umkm_nama\")";
mostCurrent._activity.LoadLayout("umkm_nama",mostCurrent.activityBA);
 //BA.debugLineNum = 34;BA.debugLine="posisi1";
_posisi1();
 //BA.debugLineNum = 35;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor=Colors";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (72),(int) (39),(int) (39)));
 //BA.debugLineNum = 36;BA.debugLine="ListView1.SingleLineLayout.Label.TextSize=16";
mostCurrent._listview1.getSingleLineLayout().Label.setTextSize((float) (16));
 //BA.debugLineNum = 37;BA.debugLine="ListView1.SingleLineLayout.Label.Left=2%y";
mostCurrent._listview1.getSingleLineLayout().Label.setLeft(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA));
 //BA.debugLineNum = 39;BA.debugLine="EditText1.Color=Colors.RGB(167, 115, 93)";
mostCurrent._edittext1.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (167),(int) (115),(int) (93)));
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public static String  _edittext1_enterpressed() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Sub EditText1_EnterPressed";
 //BA.debugLineNum = 102;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 103;BA.debugLine="inputnama11 = EditText1.Text";
_inputnama11 = mostCurrent._edittext1.getText();
 //BA.debugLineNum = 104;BA.debugLine="Log(inputnama11)";
anywheresoftware.b4a.keywords.Common.Log(_inputnama11);
 //BA.debugLineNum = 105;BA.debugLine="WebView1.LoadUrl(\"\"&Main.Server&\"umkm_nama.php?ca";
mostCurrent._webview1.LoadUrl(""+mostCurrent._main._server+"umkm_nama.php?cari_nama="+mostCurrent._edittext1.getText());
 //BA.debugLineNum = 106;BA.debugLine="iknama1";
_iknama1();
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _executeremotequery(String _query,String _jobname) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _job = null;
 //BA.debugLineNum = 57;BA.debugLine="Sub ExecuteRemoteQuery(Query As String, JobName As";
 //BA.debugLineNum = 58;BA.debugLine="Dim Job As HttpJob";
_job = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 59;BA.debugLine="Job.Initialize(JobName, Me)";
_job._initialize(processBA,_jobname,umkm_nama.getObject());
 //BA.debugLineNum = 60;BA.debugLine="Job.PostString(\"\"&Main.Server&\"json.php\",Query)";
_job._poststring(""+mostCurrent._main._server+"json.php",_query);
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private EditText1 As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private WebView1 As WebView";
mostCurrent._webview1 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _iknama1() throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Sub iknama1";
 //BA.debugLineNum = 53;BA.debugLine="ProgressDialogShow(\"Loading...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Loading...");
 //BA.debugLineNum = 54;BA.debugLine="ExecuteRemoteQuery(\"SELECT distinct id, name,addr";
_executeremotequery("SELECT distinct id, name,address, cp, st_x(st_centroid(geom)) as longitude, st_y(st_centroid(geom)) As latitude from umkm where lower(name) like lower('%"+_inputnama11+"%')","umkm_nama");
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _res = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _umkm_nama_array = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
b4a.example.kul_tem._daftarkul_lines _b = null;
 //BA.debugLineNum = 63;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 64;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 65;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 66;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 67;BA.debugLine="res = Job.GetString";
_res = _job._getstring();
 //BA.debugLineNum = 68;BA.debugLine="Log(\"Response from server :\"& res)";
anywheresoftware.b4a.keywords.Common.Log("Response from server :"+_res);
 //BA.debugLineNum = 69;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 70;BA.debugLine="parser.Initialize(res)";
_parser.Initialize(_res);
 //BA.debugLineNum = 71;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,_umkm_nama)) {
case 0:
 //BA.debugLineNum = 73;BA.debugLine="Dim umkm_nama_array As List";
_umkm_nama_array = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 74;BA.debugLine="umkm_nama_array = parser.NextArray";
_umkm_nama_array = _parser.NextArray();
 //BA.debugLineNum = 75;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 76;BA.debugLine="For i=0 To umkm_nama_array.Size -1";
{
final int step51 = 1;
final int limit51 = (int) (_umkm_nama_array.getSize()-1);
for (_i = (int) (0); (step51 > 0 && _i <= limit51) || (step51 < 0 && _i >= limit51); _i = ((int)(0 + _i + step51))) {
 //BA.debugLineNum = 77;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 78;BA.debugLine="m = umkm_nama_array.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_umkm_nama_array.Get(_i)));
 //BA.debugLineNum = 79;BA.debugLine="Dim b As daftarkul_lines";
_b = new b4a.example.kul_tem._daftarkul_lines();
 //BA.debugLineNum = 80;BA.debugLine="b.Initialize";
_b.Initialize();
 //BA.debugLineNum = 81;BA.debugLine="b.isiKUL1 = m.Get(\"id\")";
_b.isiKUL1 = BA.ObjectToString(_m.Get((Object)("id")));
 //BA.debugLineNum = 82;BA.debugLine="b.isiKUL2 = m.Get(\"name\")";
_b.isiKUL2 = BA.ObjectToString(_m.Get((Object)("name")));
 //BA.debugLineNum = 83;BA.debugLine="b.isiKUL3 = m.Get(\"latitude\")";
_b.isiKUL3 = BA.ObjectToString(_m.Get((Object)("latitude")));
 //BA.debugLineNum = 84;BA.debugLine="b.isiKUL4 = m.Get(\"longitude\")";
_b.isiKUL4 = BA.ObjectToString(_m.Get((Object)("longitude")));
 //BA.debugLineNum = 85;BA.debugLine="ListView1.AddSingleLine2(b.isiKUL2,b)";
mostCurrent._listview1.AddSingleLine2(_b.isiKUL2,(Object)(_b));
 }
};
 break;
}
;
 };
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
b4a.example.kul_tem._daftarkul_lines _b = null;
 //BA.debugLineNum = 91;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 92;BA.debugLine="Dim b As daftarkul_lines";
_b = new b4a.example.kul_tem._daftarkul_lines();
 //BA.debugLineNum = 93;BA.debugLine="b=Value";
_b = (b4a.example.kul_tem._daftarkul_lines)(_value);
 //BA.debugLineNum = 94;BA.debugLine="id=b.isiKUL1";
_id = _b.isiKUL1;
 //BA.debugLineNum = 95;BA.debugLine="name=b.isiKUL2";
_name = _b.isiKUL2;
 //BA.debugLineNum = 97;BA.debugLine="StartActivity(\"detail_umkm\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("detail_umkm"));
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _posisi1() throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub posisi1";
 //BA.debugLineNum = 42;BA.debugLine="WebView1.LoadUrl(\"\"&Main.Server&\"peta1.html\")";
mostCurrent._webview1.LoadUrl(""+mostCurrent._main._server+"peta1.html");
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private inputnama11 = \"inputnama11\" As String";
_inputnama11 = "inputnama11";
 //BA.debugLineNum = 10;BA.debugLine="Private umkm_nama = \"umkm_nama\" As String";
_umkm_nama = "umkm_nama";
 //BA.debugLineNum = 11;BA.debugLine="Dim  name, cp, address, id, latitude, longitude A";
_name = "";
_cp = "";
_address = "";
_id = "";
_latitude = "";
_longitude = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
}
