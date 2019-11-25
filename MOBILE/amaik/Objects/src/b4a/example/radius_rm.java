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

public class radius_rm extends Activity implements B4AActivity{
	public static radius_rm mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.radius_rm");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (radius_rm).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.radius_rm");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.radius_rm", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (radius_rm) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (radius_rm) Resume **");
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
		return radius_rm.class;
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
        BA.LogInfo("** Activity (radius_rm) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (radius_rm) Resume **");
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
public static String _mesjid_radius = "";
public static String _id = "";
public static String _nama = "";
public static String _latitude = "";
public static String _longitude = "";
public static String _jarak = "";
public anywheresoftware.b4a.objects.WebViewWrapper _webview1 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button4 = null;
public static double _radius1 = 0;
public static double _rad2 = 0;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel5 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
public b4a.example.dashboardd _dashboardd = null;
public b4a.example.detail_umkm _detail_umkm = null;
public b4a.example.galeri _galeri = null;
public b4a.example.radius _radius = null;
public b4a.example.ikposisi _ikposisi = null;
public b4a.example.umkm_nama _umkm_nama = null;
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
public static class _rm_lines{
public boolean IsInitialized;
public String isi1;
public String isi2;
public String isi3;
public String isi4;
public String isi5;
public void Initialize() {
IsInitialized = true;
isi1 = "";
isi2 = "";
isi3 = "";
isi4 = "";
isi5 = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 39;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"radius\")";
mostCurrent._activity.LoadLayout("radius",mostCurrent.activityBA);
 //BA.debugLineNum = 43;BA.debugLine="ListView1.TwoLinesLayout.Label.TextSize=14";
mostCurrent._listview1.getTwoLinesLayout().Label.setTextSize((float) (14));
 //BA.debugLineNum = 44;BA.debugLine="ListView1.TwoLinesLayout.SecondLabel.TextSize=13";
mostCurrent._listview1.getTwoLinesLayout().SecondLabel.setTextSize((float) (13));
 //BA.debugLineNum = 45;BA.debugLine="ListView1.Color=Colors.white";
mostCurrent._listview1.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 46;BA.debugLine="ListView1.TwoLinesLayout.Label.TextColor=Colors.B";
mostCurrent._listview1.getTwoLinesLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 47;BA.debugLine="ListView1.TwoLinesLayout.SecondLabel.TextColor=Co";
mostCurrent._listview1.getTwoLinesLayout().SecondLabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 48;BA.debugLine="ListView1.TwoLinesLayout.Label.Color=Colors.White";
mostCurrent._listview1.getTwoLinesLayout().Label.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 49;BA.debugLine="ListView1.TwoLinesLayout.SecondLabel.Color=Colors";
mostCurrent._listview1.getTwoLinesLayout().SecondLabel.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 50;BA.debugLine="ListView1.Visible=False";
mostCurrent._listview1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 51;BA.debugLine="posisi1";
_posisi1();
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 123;BA.debugLine="If dashboardd.latUser==\"0\" And dashboardd.lnguse";
if ((mostCurrent._dashboardd._latuser).equals("0") && (mostCurrent._dashboardd._lnguser).equals("0")) { 
 //BA.debugLineNum = 124;BA.debugLine="Msgbox(\"Click Button Current Position First\",\"Al";
anywheresoftware.b4a.keywords.Common.Msgbox("Click Button Current Position First","Alert",mostCurrent.activityBA);
 //BA.debugLineNum = 125;BA.debugLine="StartActivity(dashboardd)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._dashboardd.getObject()));
 }else {
 //BA.debugLineNum = 127;BA.debugLine="ListView1.Visible=True";
mostCurrent._listview1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 128;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 129;BA.debugLine="WebView1.LoadUrl(\"\"&Main.Server&\"rm_radius.php?l";
mostCurrent._webview1.LoadUrl(""+mostCurrent._main._server+"rm_radius.php?lat="+mostCurrent._dashboardd._latuser+"&lng="+mostCurrent._dashboardd._lnguser+"&rad="+BA.NumberToString(_radius1));
 //BA.debugLineNum = 130;BA.debugLine="mesjidradius";
_mesjidradius();
 };
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 136;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 137;BA.debugLine="StartActivity(dashboardd)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._dashboardd.getObject()));
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 140;BA.debugLine="Sub Button3_Click";
 //BA.debugLineNum = 141;BA.debugLine="StartActivity(umkm_nama)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._umkm_nama.getObject()));
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 144;BA.debugLine="Sub Button4_Click";
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _executeremotequery(String _query,String _jobname) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _job = null;
 //BA.debugLineNum = 66;BA.debugLine="Sub ExecuteRemoteQuery(Query As String, JobName As";
 //BA.debugLineNum = 67;BA.debugLine="Dim Job As HttpJob";
_job = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 68;BA.debugLine="Job.Initialize(JobName, Me)";
_job._initialize(processBA,_jobname,radius_rm.getObject());
 //BA.debugLineNum = 69;BA.debugLine="Job.PostString(\"\"&Main.server&\"json.php\", Query)";
_job._poststring(""+mostCurrent._main._server+"json.php",_query);
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private WebView1 As WebView";
mostCurrent._webview1 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private SeekBar1 As SeekBar";
mostCurrent._seekbar1 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private Button5 As Button";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Button3 As Button";
mostCurrent._button3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Button4 As Button";
mostCurrent._button4 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim radius1, rad2 As Double";
_radius1 = 0;
_rad2 = 0;
 //BA.debugLineNum = 31;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Panel5 As Panel";
mostCurrent._panel5 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _res = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _mes_rad_array = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _a = null;
b4a.example.radius_rm._rm_lines _b = null;
 //BA.debugLineNum = 78;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 79;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 80;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 81;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 82;BA.debugLine="res = Job.GetString";
_res = _job._getstring();
 //BA.debugLineNum = 83;BA.debugLine="Log(\"Response from server :\"& res)";
anywheresoftware.b4a.keywords.Common.Log("Response from server :"+_res);
 //BA.debugLineNum = 84;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 85;BA.debugLine="parser.Initialize(res)";
_parser.Initialize(_res);
 //BA.debugLineNum = 86;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,_mesjid_radius)) {
case 0:
 //BA.debugLineNum = 88;BA.debugLine="Dim mes_rad_array As List";
_mes_rad_array = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 89;BA.debugLine="mes_rad_array = parser.NextArray";
_mes_rad_array = _parser.NextArray();
 //BA.debugLineNum = 90;BA.debugLine="If mes_rad_array.Size -1 < 0 Then";
if (_mes_rad_array.getSize()-1<0) { 
 //BA.debugLineNum = 91;BA.debugLine="Msgbox(\"Culinary Not Found\", \"Notice\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Culinary Not Found","Notice",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 93;BA.debugLine="Log(\"hy\")";
anywheresoftware.b4a.keywords.Common.Log("hy");
 //BA.debugLineNum = 94;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 95;BA.debugLine="For i=0 To mes_rad_array.Size -1";
{
final int step68 = 1;
final int limit68 = (int) (_mes_rad_array.getSize()-1);
for (_i = (int) (0); (step68 > 0 && _i <= limit68) || (step68 < 0 && _i >= limit68); _i = ((int)(0 + _i + step68))) {
 //BA.debugLineNum = 96;BA.debugLine="Dim a As Map";
_a = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 97;BA.debugLine="a = mes_rad_array.Get(i)";
_a.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_mes_rad_array.Get(_i)));
 //BA.debugLineNum = 98;BA.debugLine="Dim b As rm_lines";
_b = new b4a.example.radius_rm._rm_lines();
 //BA.debugLineNum = 99;BA.debugLine="b.Initialize";
_b.Initialize();
 //BA.debugLineNum = 100;BA.debugLine="b.isi1 = a.Get(\"id\")";
_b.isi1 = BA.ObjectToString(_a.Get((Object)("id")));
 //BA.debugLineNum = 101;BA.debugLine="b.isi2 = a.Get(\"name\")";
_b.isi2 = BA.ObjectToString(_a.Get((Object)("name")));
 //BA.debugLineNum = 102;BA.debugLine="rad2=a.Get(\"jarak\")";
_rad2 = (double)(BA.ObjectToNumber(_a.Get((Object)("jarak"))));
 //BA.debugLineNum = 103;BA.debugLine="rad2=Floor(rad2)";
_rad2 = anywheresoftware.b4a.keywords.Common.Floor(_rad2);
 //BA.debugLineNum = 104;BA.debugLine="b.isi3=rad2&\" m\"";
_b.isi3 = BA.NumberToString(_rad2)+" m";
 //BA.debugLineNum = 105;BA.debugLine="b.isi4 = a.Get(\"latitude\")";
_b.isi4 = BA.ObjectToString(_a.Get((Object)("latitude")));
 //BA.debugLineNum = 106;BA.debugLine="b.isi5 = a.Get(\"longitude\")";
_b.isi5 = BA.ObjectToString(_a.Get((Object)("longitude")));
 //BA.debugLineNum = 107;BA.debugLine="ListView1.AddTwoLines2(b.isi2, b.isi3,b)";
mostCurrent._listview1.AddTwoLines2(_b.isi2,_b.isi3,(Object)(_b));
 }
};
 };
 break;
}
;
 };
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
b4a.example.radius_rm._rm_lines _b = null;
 //BA.debugLineNum = 148;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 149;BA.debugLine="Dim b As rm_lines";
_b = new b4a.example.radius_rm._rm_lines();
 //BA.debugLineNum = 150;BA.debugLine="b=Value";
_b = (b4a.example.radius_rm._rm_lines)(_value);
 //BA.debugLineNum = 151;BA.debugLine="id=b.isi1";
_id = _b.isi1;
 //BA.debugLineNum = 152;BA.debugLine="nama=b.isi2";
_nama = _b.isi2;
 //BA.debugLineNum = 153;BA.debugLine="latitude=b.isi4";
_latitude = _b.isi4;
 //BA.debugLineNum = 154;BA.debugLine="longitude=b.isi5";
_longitude = _b.isi5;
 //BA.debugLineNum = 155;BA.debugLine="Log(\"posisi \"&dashboardd.latUser &\" \"&dashboardd.";
anywheresoftware.b4a.keywords.Common.Log("posisi "+mostCurrent._dashboardd._latuser+" "+mostCurrent._dashboardd._lnguser);
 //BA.debugLineNum = 156;BA.debugLine="StartActivity(\"detail_rm\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("detail_rm"));
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static String  _mesjidradius() throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Sub mesjidradius";
 //BA.debugLineNum = 73;BA.debugLine="ProgressDialogShow(\"Loading...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Loading...");
 //BA.debugLineNum = 74;BA.debugLine="ExecuteRemoteQuery(\"SELECT id, name,st_x(st_centr";
_executeremotequery("SELECT id, name,st_x(st_centroid(geom)) as longitude,st_y(st_centroid(geom)) as latitude,st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._dashboardd._lnguser+" "+mostCurrent._dashboardd._latuser+")', -1), culinary.geom) as jarak FROM culinary where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._dashboardd._lnguser+" "+mostCurrent._dashboardd._latuser+")', -1), culinary.geom) <= "+BA.NumberToString(_radius1)+" ","mesjid_radius");
 //BA.debugLineNum = 75;BA.debugLine="Log(\"SELECT id, name,st_x(st_centroid(geom)) as l";
anywheresoftware.b4a.keywords.Common.Log("SELECT id, name,st_x(st_centroid(geom)) as longitude,st_y(st_centroid(geom)) as latitude,st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._dashboardd._lnguser+" "+mostCurrent._dashboardd._latuser+")', -1), culinary.geom) as jarak FROM culinary where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._dashboardd._lnguser+" "+mostCurrent._dashboardd._latuser+")', -1), culinary.geom) <= "+BA.NumberToString(_radius1)+"");
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _posisi1() throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Sub posisi1";
 //BA.debugLineNum = 63;BA.debugLine="WebView1.LoadUrl(\"\"&Main.Server&\"peta1.html\")";
mostCurrent._webview1.LoadUrl(""+mostCurrent._main._server+"peta1.html");
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private mesjid_radius = \"mesjid_radius\" As String";
_mesjid_radius = "mesjid_radius";
 //BA.debugLineNum = 10;BA.debugLine="Dim id, nama, latitude, longitude, jarak As Strin";
_id = "";
_nama = "";
_latitude = "";
_longitude = "";
_jarak = "";
 //BA.debugLineNum = 12;BA.debugLine="Type rm_lines (isi1 As String,isi2 As String,isi3";
;
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar1_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub SeekBar1_ValueChanged (Value As Int, UserChang";
 //BA.debugLineNum = 118;BA.debugLine="radius1=SeekBar1.Value*10";
_radius1 = mostCurrent._seekbar1.getValue()*10;
 //BA.debugLineNum = 119;BA.debugLine="Log(radius1)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_radius1));
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
}
