package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_posisi{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label1").vw.setTop((int)((10d / 100 * height)));
views.get("label1").vw.setHeight((int)((21d / 100 * height) - ((10d / 100 * height))));
views.get("webview1").vw.setTop((int)((30d / 100 * height)));
views.get("webview1").vw.setHeight((int)((80d / 100 * height) - ((30d / 100 * height))));
views.get("webview1").vw.setLeft((int)((0d / 100 * width)));
views.get("webview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("imageview1").vw.setTop((int)((33d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((40d / 100 * height) - ((33d / 100 * height))));
views.get("imageview1").vw.setLeft((int)((82d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((93d / 100 * width) - ((82d / 100 * width))));

}
}