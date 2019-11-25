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

public class nearwis extends Activity implements B4AActivity{
	public static nearwis mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.nearwis");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (nearwis).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.nearwis");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.nearwis", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (nearwis) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (nearwis) Resume **");
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
		return nearwis.class;
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
        BA.LogInfo("** Activity (nearwis) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (nearwis) Resume **");
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
public static String _umkm_nama1 = "";
public static String _umkm_nama2 = "";
public static String _umkm_nama3 = "";
public static String _umkm_nama4 = "";
public static String _umkm_nama5 = "";
public static String _latasal = "";
public static String _lngasal = "";
public static String _lattujuan = "";
public static String _lngtujuan = "";
public static String _mode = "";
public static String _latsimpang = "";
public static String _lngsimpang = "";
public static int _kd1 = 0;
public static int _kd2 = 0;
public static int _kd3 = 0;
public static int _kd4 = 0;
public static int _kd5 = 0;
public static int _radius2 = 0;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.WebViewWrapper _webview1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview3 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview4 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview5 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview6 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox2 = null;
public anywheresoftware.b4a.objects.WebViewWrapper _webview2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox5 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
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
 //BA.debugLineNum = 40;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 44;BA.debugLine="latTujuan=wisposisi.latTujuan";
mostCurrent._lattujuan = mostCurrent._wisposisi._lattujuan;
 //BA.debugLineNum = 45;BA.debugLine="lngTujuan=wisposisi.lngTujuan";
mostCurrent._lngtujuan = mostCurrent._wisposisi._lngtujuan;
 //BA.debugLineNum = 46;BA.debugLine="Activity.LoadLayout(\"near\")";
mostCurrent._activity.LoadLayout("near",mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 48;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 49;BA.debugLine="CheckBox1.Checked=False";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 50;BA.debugLine="CheckBox2.Checked=False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 51;BA.debugLine="CheckBox3.Checked=False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 52;BA.debugLine="CheckBox4.Checked=False";
mostCurrent._checkbox4.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 53;BA.debugLine="CheckBox5.Checked=False";
mostCurrent._checkbox5.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 124;BA.debugLine="ListView6.Clear";
mostCurrent._listview6.Clear();
 //BA.debugLineNum = 125;BA.debugLine="radius2=500";
_radius2 = (int) (500);
 //BA.debugLineNum = 126;BA.debugLine="kd1=0";
_kd1 = (int) (0);
 //BA.debugLineNum = 127;BA.debugLine="kd2=0";
_kd2 = (int) (0);
 //BA.debugLineNum = 128;BA.debugLine="kd3=0";
_kd3 = (int) (0);
 //BA.debugLineNum = 129;BA.debugLine="kd4=0";
_kd4 = (int) (0);
 //BA.debugLineNum = 130;BA.debugLine="kd5=0";
_kd5 = (int) (0);
 //BA.debugLineNum = 132;BA.debugLine="If CheckBox1.Checked Then";
if (mostCurrent._checkbox1.getChecked()) { 
 //BA.debugLineNum = 133;BA.debugLine="kd1=1";
_kd1 = (int) (1);
 //BA.debugLineNum = 134;BA.debugLine="ExecuteRemoteQuery(\"SELECT id, name, st_x(st_cent";
_executeremotequery("SELECT id, name, st_x(st_centroid(geom)) As lng,st_y(st_centroid(geom)) As lat,st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), umkm.geom) as jarak FROM umkm where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), umkm.geom) <= "+BA.NumberToString(_radius2)+"","umkm_nama5");
 };
 //BA.debugLineNum = 137;BA.debugLine="If CheckBox2.Checked Then";
if (mostCurrent._checkbox2.getChecked()) { 
 //BA.debugLineNum = 138;BA.debugLine="kd2=1";
_kd2 = (int) (1);
 //BA.debugLineNum = 139;BA.debugLine="ExecuteRemoteQuery(\"SELECT id, name, st_x(st_cent";
_executeremotequery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), culinary.geom) as jarak FROM culinary where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), culinary.geom) <= "+BA.NumberToString(_radius2)+"","umkm_nama5");
 };
 //BA.debugLineNum = 142;BA.debugLine="If CheckBox3.Checked Then";
if (mostCurrent._checkbox3.getChecked()) { 
 //BA.debugLineNum = 143;BA.debugLine="kd3=1";
_kd3 = (int) (1);
 //BA.debugLineNum = 144;BA.debugLine="ExecuteRemoteQuery(\"SELECT id, name, st_x(st_cent";
_executeremotequery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), mosque.geom) as jarak FROM mosque where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), mosque.geom) <= "+BA.NumberToString(_radius2)+"","umkm_nama5");
 };
 //BA.debugLineNum = 147;BA.debugLine="If CheckBox4.Checked Then";
if (mostCurrent._checkbox4.getChecked()) { 
 //BA.debugLineNum = 148;BA.debugLine="kd4=1";
_kd4 = (int) (1);
 //BA.debugLineNum = 149;BA.debugLine="ExecuteRemoteQuery(\"SELECT id, name, st_x(st_cent";
_executeremotequery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), greatcharacter.geom) as jarak FROM greatcharacter where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), greatcharacter.geom) <= "+BA.NumberToString(_radius2)+"","umkm_nama5");
 };
 //BA.debugLineNum = 152;BA.debugLine="If CheckBox5.Checked Then";
if (mostCurrent._checkbox5.getChecked()) { 
 //BA.debugLineNum = 153;BA.debugLine="kd5=1";
_kd5 = (int) (1);
 //BA.debugLineNum = 154;BA.debugLine="ExecuteRemoteQuery(\"SELECT id, name, st_x(st_cent";
_executeremotequery("SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), tourism.geom) as jarak FROM tourism where st_distance_sphere(ST_GeomFromText('POINT("+mostCurrent._lngtujuan+" "+mostCurrent._lattujuan+")',-1), tourism.geom) <= "+BA.NumberToString(_radius2)+"","umkm_nama5");
 };
 //BA.debugLineNum = 157;BA.debugLine="WebView2.LoadUrl(\"\"&Main.Server&\"sekitar.php?lat=";
mostCurrent._webview2.LoadUrl(""+mostCurrent._main._server+"sekitar.php?lat="+mostCurrent._lattujuan+"&lng="+mostCurrent._lngtujuan+"&kd1="+BA.NumberToString(_kd1)+"&kd2="+BA.NumberToString(_kd2)+"&kd3="+BA.NumberToString(_kd3)+"&kd4="+BA.NumberToString(_kd4)+"&kd5="+BA.NumberToString(_kd5));
 //BA.debugLineNum = 158;BA.debugLine="iknama5";
_iknama5();
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox1_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Sub CheckBox1_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 107;BA.debugLine="kd1=1";
_kd1 = (int) (1);
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox2_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Sub CheckBox2_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 110;BA.debugLine="kd2=1";
_kd2 = (int) (1);
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox3_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub CheckBox3_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 113;BA.debugLine="kd3=1";
_kd3 = (int) (1);
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox4_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 115;BA.debugLine="Sub CheckBox4_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 116;BA.debugLine="kd4=1";
_kd4 = (int) (1);
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox5_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Sub CheckBox5_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 119;BA.debugLine="kd5=1";
_kd5 = (int) (1);
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _executeremotequery(String _query,String _jobname) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _job = null;
 //BA.debugLineNum = 65;BA.debugLine="Sub ExecuteRemoteQuery(Query As String, JobName As";
 //BA.debugLineNum = 66;BA.debugLine="Dim Job As HttpJob";
_job = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 67;BA.debugLine="Job.Initialize(JobName, Me)";
_job._initialize(processBA,_jobname,nearwis.getObject());
 //BA.debugLineNum = 68;BA.debugLine="Job.PostString(\"\"&Main.Server&\"json.php\",Query)";
_job._poststring(""+mostCurrent._main._server+"json.php",_query);
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim latAsal, lngAsal, latTujuan, lngTujuan,mode,l";
mostCurrent._latasal = "";
mostCurrent._lngasal = "";
mostCurrent._lattujuan = "";
mostCurrent._lngtujuan = "";
mostCurrent._mode = "";
mostCurrent._latsimpang = "";
mostCurrent._lngsimpang = "";
 //BA.debugLineNum = 20;BA.debugLine="Dim kd1,kd2,kd3,kd4,kd5,radius2 As Int";
_kd1 = 0;
_kd2 = 0;
_kd3 = 0;
_kd4 = 0;
_kd5 = 0;
_radius2 = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private WebView1 As WebView";
mostCurrent._webview1 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private ListView3 As ListView";
mostCurrent._listview3 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private ListView4 As ListView";
mostCurrent._listview4 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private ListView5 As ListView";
mostCurrent._listview5 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private ListView2 As ListView";
mostCurrent._listview2 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private CheckBox1 As CheckBox";
mostCurrent._checkbox1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private CheckBox3 As CheckBox";
mostCurrent._checkbox3 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private CheckBox4 As CheckBox";
mostCurrent._checkbox4 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private ListView6 As ListView";
mostCurrent._listview6 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private CheckBox2 As CheckBox";
mostCurrent._checkbox2 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private WebView2 As WebView";
mostCurrent._webview2 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private CheckBox5 As CheckBox";
mostCurrent._checkbox5 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private CheckBox6 As CheckBox";
mostCurrent._checkbox6 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _iknama5() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub iknama5";
 //BA.debugLineNum = 61;BA.debugLine="ProgressDialogShow(\"Loading...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Loading...");
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _res = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _umkm_nama_array = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
b4a.example.kul_tem._daftarkul_lines _b = null;
 //BA.debugLineNum = 71;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 72;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 73;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 74;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 75;BA.debugLine="res = Job.GetString";
_res = _job._getstring();
 //BA.debugLineNum = 76;BA.debugLine="Log(\"Response from server :\"& res)";
anywheresoftware.b4a.keywords.Common.Log("Response from server :"+_res);
 //BA.debugLineNum = 77;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 78;BA.debugLine="parser.Initialize(res)";
_parser.Initialize(_res);
 //BA.debugLineNum = 79;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,_umkm_nama5)) {
case 0:
 //BA.debugLineNum = 81;BA.debugLine="Dim umkm_nama_array As List";
_umkm_nama_array = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 82;BA.debugLine="umkm_nama_array = parser.NextArray";
_umkm_nama_array = _parser.NextArray();
 //BA.debugLineNum = 83;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 84;BA.debugLine="For i=0 To umkm_nama_array.Size -1";
{
final int step61 = 1;
final int limit61 = (int) (_umkm_nama_array.getSize()-1);
for (_i = (int) (0); (step61 > 0 && _i <= limit61) || (step61 < 0 && _i >= limit61); _i = ((int)(0 + _i + step61))) {
 //BA.debugLineNum = 85;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 86;BA.debugLine="m = umkm_nama_array.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_umkm_nama_array.Get(_i)));
 //BA.debugLineNum = 87;BA.debugLine="Dim b As daftarkul_lines";
_b = new b4a.example.kul_tem._daftarkul_lines();
 //BA.debugLineNum = 88;BA.debugLine="b.Initialize";
_b.Initialize();
 //BA.debugLineNum = 89;BA.debugLine="b.isiKUL1 = m.Get(\"id\")";
_b.isiKUL1 = BA.ObjectToString(_m.Get((Object)("id")));
 //BA.debugLineNum = 90;BA.debugLine="b.isiKUL2 = m.Get(\"name\")";
_b.isiKUL2 = BA.ObjectToString(_m.Get((Object)("name")));
 //BA.debugLineNum = 91;BA.debugLine="ListView6.AddSingleLine2(b.isiKUL2,b)";
mostCurrent._listview6.AddSingleLine2(_b.isiKUL2,(Object)(_b));
 }
};
 break;
}
;
 };
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private umkm_nama1 = \"umkm_nama1\" As String";
_umkm_nama1 = "umkm_nama1";
 //BA.debugLineNum = 10;BA.debugLine="Private umkm_nama2 = \"umkm_nama2\" As String";
_umkm_nama2 = "umkm_nama2";
 //BA.debugLineNum = 11;BA.debugLine="Private umkm_nama3 = \"umkm_nama3\" As String";
_umkm_nama3 = "umkm_nama3";
 //BA.debugLineNum = 12;BA.debugLine="Private umkm_nama4 = \"umkm_nama4\" As String";
_umkm_nama4 = "umkm_nama4";
 //BA.debugLineNum = 13;BA.debugLine="Private umkm_nama5 = \"umkm_nama5\" As String";
_umkm_nama5 = "umkm_nama5";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
}
