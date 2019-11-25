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

public class dashboardd extends Activity implements B4AActivity{
	public static dashboardd mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.dashboardd");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (dashboardd).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.dashboardd");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.dashboardd", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (dashboardd) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (dashboardd) Resume **");
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
		return dashboardd.class;
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
        BA.LogInfo("** Activity (dashboardd) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (dashboardd) Resume **");
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
public static String _server = "";
public static anywheresoftware.b4a.gps.GPS _gpsclient = null;
public static anywheresoftware.b4a.gps.LocationWrapper _userlocation = null;
public static String _latuser = "";
public static String _lnguser = "";
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button6 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button7 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button8 = null;
public anywheresoftware.b4a.objects.WebViewWrapper _webview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
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
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
mostCurrent._activity.LoadLayout("dashboard",mostCurrent.activityBA);
 //BA.debugLineNum = 35;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 36;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 37;BA.debugLine="gpsClient.Initialize(\"gpsClient\")";
_gpsclient.Initialize("gpsClient");
 //BA.debugLineNum = 38;BA.debugLine="userLocation.Initialize";
_userlocation.Initialize();
 };
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 61;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="gpsClient.Stop";
_gpsclient.Stop();
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 66;BA.debugLine="cekGPS";
_cekgps();
 //BA.debugLineNum = 67;BA.debugLine="If (Panel3.Visible=False) Then";
if ((mostCurrent._panel3.getVisible()==anywheresoftware.b4a.keywords.Common.False)) { 
 //BA.debugLineNum = 68;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 70;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 91;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 92;BA.debugLine="StartActivity(search_cul)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._search_cul.getObject()));
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Button4_Click";
 //BA.debugLineNum = 100;BA.debugLine="StartActivity(search_umkm)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._search_umkm.getObject()));
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub Button5_Click";
 //BA.debugLineNum = 114;BA.debugLine="StartActivity(search_rm)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._search_rm.getObject()));
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _button6_click() throws Exception{
 //BA.debugLineNum = 110;BA.debugLine="Sub Button6_Click";
 //BA.debugLineNum = 111;BA.debugLine="StartActivity(search_mosque)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._search_mosque.getObject()));
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _button7_click() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub Button7_Click";
 //BA.debugLineNum = 108;BA.debugLine="StartActivity(search_tok)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._search_tok.getObject()));
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _button8_click() throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Button8_Click";
 //BA.debugLineNum = 105;BA.debugLine="StartActivity(search_wis)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._search_wis.getObject()));
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _cekgps() throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub cekGPS 'melakukan mengecekan GPS pada pengguna";
 //BA.debugLineNum = 79;BA.debugLine="If (latUser=0 And lngUser=0) Then";
if (((_latuser).equals(BA.NumberToString(0)) && (_lnguser).equals(BA.NumberToString(0)))) { 
 //BA.debugLineNum = 80;BA.debugLine="If gpsClient.GPSEnabled=False Then";
if (_gpsclient.getGPSEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 81;BA.debugLine="ToastMessageShow(\"Aktifkan GPS\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Aktifkan GPS",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 82;BA.debugLine="StartActivity(gpsClient.LocationSettingsIntent)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_gpsclient.getLocationSettingsIntent()));
 }else {
 //BA.debugLineNum = 84;BA.debugLine="gpsClient.Start(0,0)";
_gpsclient.Start(processBA,(long) (0),(float) (0));
 //BA.debugLineNum = 85;BA.debugLine="ProgressDialogShow(\"Tunggu Lokasi\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Tunggu Lokasi");
 };
 }else {
 //BA.debugLineNum = 88;BA.debugLine="petaClient";
_petaclient();
 };
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="latUser=\"-0.31865703276391205 \"";
_latuser = "-0.31865703276391205 ";
 //BA.debugLineNum = 20;BA.debugLine="lngUser=\"100.35777860093731\"";
_lnguser = "100.35777860093731";
 //BA.debugLineNum = 21;BA.debugLine="Private Button5 As Button";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private Button6 As Button";
mostCurrent._button6 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Button7 As Button";
mostCurrent._button7 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Button8 As Button";
mostCurrent._button8 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private WebView1 As WebView";
mostCurrent._webview1 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _gpsclient_locationchanged(anywheresoftware.b4a.gps.LocationWrapper _gpslocation) throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub gpsClient_LocationChanged (gpsLocation As Loca";
 //BA.debugLineNum = 43;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 44;BA.debugLine="userLocation=gpsLocation";
_userlocation = _gpslocation;
 //BA.debugLineNum = 45;BA.debugLine="gpsClient.Stop";
_gpsclient.Stop();
 //BA.debugLineNum = 46;BA.debugLine="latUser=userLocation.Latitude";
_latuser = BA.NumberToString(_userlocation.getLatitude());
 //BA.debugLineNum = 47;BA.debugLine="lngUser=userLocation.Longitude";
_lnguser = BA.NumberToString(_userlocation.getLongitude());
 //BA.debugLineNum = 48;BA.debugLine="petaClient";
_petaclient();
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _imageview1_click() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub ImageView1_Click";
 //BA.debugLineNum = 117;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _petaclient() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub petaClient 'menampilkan posisi pengguna pada p";
 //BA.debugLineNum = 53;BA.debugLine="Log(lngUser&\" \"&latUser)";
anywheresoftware.b4a.keywords.Common.Log(_lnguser+" "+_latuser);
 //BA.debugLineNum = 54;BA.debugLine="WebView1.LoadUrl(\"\"&Server&\"petaSaya.php?lat=\"&la";
mostCurrent._webview1.LoadUrl(""+_server+"petaSaya.php?lat="+_latuser+"&lng="+_lnguser);
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim Server=\"http://192.168.1.2/kotogadang/andro/\"";
_server = "http://192.168.1.2/kotogadang/andro/";
 //BA.debugLineNum = 10;BA.debugLine="Dim gpsClient As GPS";
_gpsclient = new anywheresoftware.b4a.gps.GPS();
 //BA.debugLineNum = 11;BA.debugLine="Dim userLocation As Location";
_userlocation = new anywheresoftware.b4a.gps.LocationWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Dim latUser, lngUser As String";
_latuser = "";
_lnguser = "";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
}
