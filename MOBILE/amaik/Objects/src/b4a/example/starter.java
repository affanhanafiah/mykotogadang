package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends android.app.Service {
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "b4a.example", "b4a.example.starter");
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        processBA.setActivityPaused(false);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.starter", processBA, _service);
		}
        BA.LogInfo("** Service (starter) Create **");
        processBA.raiseEvent(null, "service_create");
        processBA.runHook("oncreate", this, null);
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		handleStart(intent);
    }
    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    	handleStart(intent);
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (starter) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _cache = null;
public static anywheresoftware.b4a.objects.collections.Map _tasks = null;
public static anywheresoftware.b4a.objects.collections.Map _ongoingtasks = null;
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
public static String  _activityispaused() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub ActivityIsPaused";
 //BA.debugLineNum = 61;BA.debugLine="tasks.Clear";
_tasks.Clear();
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _download(anywheresoftware.b4a.objects.collections.Map _imageviewsmap) throws Exception{
int _i = 0;
String _link = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
 //BA.debugLineNum = 25;BA.debugLine="Sub Download (ImageViewsMap As Map)";
 //BA.debugLineNum = 26;BA.debugLine="For i = 0 To ImageViewsMap.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_imageviewsmap.getSize()-1);
for (_i = (int) (0); (step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15); _i = ((int)(0 + _i + step15))) {
 //BA.debugLineNum = 27;BA.debugLine="tasks.Put(ImageViewsMap.GetKeyAt(i), ImageViewsM";
_tasks.Put(_imageviewsmap.GetKeyAt(_i),_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 28;BA.debugLine="Dim link As String = ImageViewsMap.GetValueAt(i)";
_link = BA.ObjectToString(_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 29;BA.debugLine="If cache.ContainsKey(link) Then";
if (_cache.ContainsKey((Object)(_link))) { 
 //BA.debugLineNum = 30;BA.debugLine="Dim iv As ImageView = ImageViewsMap.GetKeyAt(i)";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv.setObject((android.widget.ImageView)(_imageviewsmap.GetKeyAt(_i)));
 //BA.debugLineNum = 31;BA.debugLine="iv.SetBackgroundImage(cache.Get(link))";
_iv.SetBackgroundImage((android.graphics.Bitmap)(_cache.Get((Object)(_link))));
 }else if(_ongoingtasks.ContainsKey((Object)(_link))==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 33;BA.debugLine="ongoingTasks.Put(link, \"\")";
_ongoingtasks.Put((Object)(_link),(Object)(""));
 //BA.debugLineNum = 34;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 35;BA.debugLine="j.Initialize(link, Me)";
_j._initialize(processBA,_link,starter.getObject());
 //BA.debugLineNum = 36;BA.debugLine="j.Download(link)";
_j._download(_link);
 };
 }
};
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
int _i = 0;
String _link = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
 //BA.debugLineNum = 41;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 42;BA.debugLine="ongoingTasks.Remove(Job.JobName)";
_ongoingtasks.Remove((Object)(_job._jobname));
 //BA.debugLineNum = 43;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 44;BA.debugLine="Dim bmp As Bitmap = Job.GetBitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = _job._getbitmap();
 //BA.debugLineNum = 45;BA.debugLine="cache.Put(Job.JobName, bmp)";
_cache.Put((Object)(_job._jobname),(Object)(_bmp.getObject()));
 //BA.debugLineNum = 46;BA.debugLine="If tasks.IsInitialized Then";
if (_tasks.IsInitialized()) { 
 //BA.debugLineNum = 47;BA.debugLine="For i = 0 To tasks.Size - 1";
{
final int step35 = 1;
final int limit35 = (int) (_tasks.getSize()-1);
for (_i = (int) (0); (step35 > 0 && _i <= limit35) || (step35 < 0 && _i >= limit35); _i = ((int)(0 + _i + step35))) {
 //BA.debugLineNum = 48;BA.debugLine="Dim link As String = tasks.GetValueAt(i)";
_link = BA.ObjectToString(_tasks.GetValueAt(_i));
 //BA.debugLineNum = 49;BA.debugLine="If link = Job.JobName Then";
if ((_link).equals(_job._jobname)) { 
 //BA.debugLineNum = 50;BA.debugLine="Dim iv As ImageView = tasks.GetKeyAt(i)";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv.setObject((android.widget.ImageView)(_tasks.GetKeyAt(_i)));
 //BA.debugLineNum = 51;BA.debugLine="iv.SetBackgroundImage(bmp)";
_iv.SetBackgroundImage((android.graphics.Bitmap)(_bmp.getObject()));
 };
 }
};
 };
 }else {
 //BA.debugLineNum = 56;BA.debugLine="Log(\"Error downloading image: \" & Job.JobName &";
anywheresoftware.b4a.keywords.Common.Log("Error downloading image: "+_job._jobname+anywheresoftware.b4a.keywords.Common.CRLF+_job._errormessage);
 };
 //BA.debugLineNum = 58;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private cache As Map";
_cache = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Private tasks As Map";
_tasks = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 8;BA.debugLine="Private ongoingTasks As Map";
_ongoingtasks = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 12;BA.debugLine="tasks.Initialize";
_tasks.Initialize();
 //BA.debugLineNum = 13;BA.debugLine="cache.Initialize";
_cache.Initialize();
 //BA.debugLineNum = 14;BA.debugLine="ongoingTasks.Initialize";
_ongoingtasks.Initialize();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}
