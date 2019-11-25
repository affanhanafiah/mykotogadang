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

public class galeri extends Activity implements B4AActivity{
	public static galeri mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.galeri");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (galeri).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.galeri");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.galeri", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (galeri) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (galeri) Resume **");
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
		return galeri.class;
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
        BA.LogInfo("** Activity (galeri) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (galeri) Resume **");
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
public static String _viewfoto = "";
public static String _id = "";
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview3 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
public b4a.example.dashboardd _dashboardd = null;
public b4a.example.detail_umkm _detail_umkm = null;
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
 //BA.debugLineNum = 25;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout(\"galeri\")";
mostCurrent._activity.LoadLayout("galeri",mostCurrent.activityBA);
 //BA.debugLineNum = 28;BA.debugLine="id=detail_umkm.id";
_id = mostCurrent._detail_umkm._id;
 //BA.debugLineNum = 29;BA.debugLine="detail_umkm.id=\"\"";
mostCurrent._detail_umkm._id = "";
 //BA.debugLineNum = 31;BA.debugLine="fotoquery";
_fotoquery();
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _executeremotequery(String _query,String _jobname) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _job = null;
 //BA.debugLineNum = 40;BA.debugLine="Sub ExecuteRemoteQuery(Query As String, JobName As";
 //BA.debugLineNum = 41;BA.debugLine="Dim Job As HttpJob";
_job = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 42;BA.debugLine="Job.Initialize(JobName, Me)";
_job._initialize(processBA,_jobname,galeri.getObject());
 //BA.debugLineNum = 43;BA.debugLine="Job.PostString(\"\"&Main.server&\"json.php\", Query)";
_job._poststring(""+mostCurrent._main._server+"json.php",_query);
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _fotoquery() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub fotoquery";
 //BA.debugLineNum = 35;BA.debugLine="ProgressDialogShow(\"Loading\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Loading");
 //BA.debugLineNum = 37;BA.debugLine="ExecuteRemoteQuery(\"select distinct id_umkm, gall";
_executeremotequery("select distinct id_umkm, gallery_umkm from umkm_gallery where id_umkm='"+_id+"'","ViewFoto");
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private ImageView2 As ImageView";
mostCurrent._imageview2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private ImageView3 As ImageView";
mostCurrent._imageview3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _res = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _agn = null;
String _serverimage = "";
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _w = null;
String _image = "";
String _gambar = "";
anywheresoftware.b4a.objects.collections.Map _link = null;
 //BA.debugLineNum = 46;BA.debugLine="Sub Jobdone (Job As HttpJob)";
 //BA.debugLineNum = 48;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 49;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 50;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 51;BA.debugLine="res= Job.GetString";
_res = _job._getstring();
 //BA.debugLineNum = 52;BA.debugLine="Log (\"Respon from server:\" & res)";
anywheresoftware.b4a.keywords.Common.Log("Respon from server:"+_res);
 //BA.debugLineNum = 53;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 54;BA.debugLine="parser.Initialize(res)";
_parser.Initialize(_res);
 //BA.debugLineNum = 55;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,_viewfoto)) {
case 0:
 //BA.debugLineNum = 58;BA.debugLine="Dim agn As List";
_agn = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 59;BA.debugLine="agn=parser.NextArray";
_agn = _parser.NextArray();
 //BA.debugLineNum = 60;BA.debugLine="If agn.Size - 1 < 0 Then";
if (_agn.getSize()-1<0) { 
 //BA.debugLineNum = 61;BA.debugLine="Log(agn.Size)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_agn.getSize()));
 //BA.debugLineNum = 62;BA.debugLine="Msgbox(\"Gallery Photos Not Found\", \"Peringatan";
anywheresoftware.b4a.keywords.Common.Msgbox("Gallery Photos Not Found","Peringatan",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 64;BA.debugLine="Dim serverimage = \"http://192.168.1.2/kotogada";
_serverimage = "http://192.168.1.2/kotogadang/";
 //BA.debugLineNum = 66;BA.debugLine="For i=0 To agn.Size-1";
{
final int step43 = 1;
final int limit43 = (int) (_agn.getSize()-1);
for (_i = (int) (0); (step43 > 0 && _i <= limit43) || (step43 < 0 && _i >= limit43); _i = ((int)(0 + _i + step43))) {
 //BA.debugLineNum = 67;BA.debugLine="Dim w As Map";
_w = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 68;BA.debugLine="w=agn.Get(i)";
_w.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_agn.Get(_i)));
 //BA.debugLineNum = 69;BA.debugLine="Log(\"haha\")";
anywheresoftware.b4a.keywords.Common.Log("haha");
 //BA.debugLineNum = 70;BA.debugLine="Dim image = w.Get(\"gallery_umkm\") As String";
_image = BA.ObjectToString(_w.Get((Object)("gallery_umkm")));
 //BA.debugLineNum = 72;BA.debugLine="Dim gambar = \"\"&serverimage&\"\"&image As Strin";
_gambar = ""+_serverimage+""+_image;
 //BA.debugLineNum = 73;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 74;BA.debugLine="Dim link As Map";
_link = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 75;BA.debugLine="link.Initialize";
_link.Initialize();
 //BA.debugLineNum = 77;BA.debugLine="link.Put(ImageView1,gambar)";
_link.Put((Object)(mostCurrent._imageview1.getObject()),(Object)(_gambar));
 //BA.debugLineNum = 78;BA.debugLine="CallSubDelayed2(imageDownloader, \"Download\",";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(mostCurrent.activityBA,(Object)(mostCurrent._imagedownloader.getObject()),"Download",(Object)(_link));
 }else if(_i==1) { 
 //BA.debugLineNum = 80;BA.debugLine="Dim link As Map";
_link = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 81;BA.debugLine="link.Initialize";
_link.Initialize();
 //BA.debugLineNum = 83;BA.debugLine="link.Put(ImageView2,gambar)";
_link.Put((Object)(mostCurrent._imageview2.getObject()),(Object)(_gambar));
 //BA.debugLineNum = 84;BA.debugLine="CallSubDelayed2(imageDownloader, \"Download\",";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(mostCurrent.activityBA,(Object)(mostCurrent._imagedownloader.getObject()),"Download",(Object)(_link));
 }else if(_i==2) { 
 //BA.debugLineNum = 86;BA.debugLine="Dim link As Map";
_link = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 87;BA.debugLine="link.Initialize";
_link.Initialize();
 //BA.debugLineNum = 89;BA.debugLine="link.Put(ImageView3,gambar)";
_link.Put((Object)(mostCurrent._imageview3.getObject()),(Object)(_gambar));
 //BA.debugLineNum = 90;BA.debugLine="CallSubDelayed2(imageDownloader, \"Download\",";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(mostCurrent.activityBA,(Object)(mostCurrent._imagedownloader.getObject()),"Download",(Object)(_link));
 };
 }
};
 };
 break;
}
;
 };
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim ViewFoto=\"ViewFoto\" As String";
_viewfoto = "ViewFoto";
 //BA.debugLineNum = 10;BA.debugLine="Dim id As String";
_id = "";
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
}
